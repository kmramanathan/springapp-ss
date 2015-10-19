package springapp.web.flatrate;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import springapp.domain.FRBGCRequest;
import springapp.domain.FRBGCResponse;
import springapp.manager.FRBGCManager;
import springapp.repository.SubscriptionDao;
import springapp.service.NeonValidator;
import springapp.service.user.UserManager;
import springapp.web.flatrate.FindPeopleSearch.SearchFRFPFormCommand;
import springapp.web.funnel.AbstractFunnelController;

@Controller
public class BGCSearchForm extends AbstractFunnelController {
	
	@Autowired
	protected SubscriptionDao subscriptionDao;
	
	protected Logger logger = Logger.getLogger(getClass());
	
		
	protected static final TreeMap<String,String> bgcDobMonths = new TreeMap<String,String>();
	protected static final TreeMap<String,String> bgcDobDays = new TreeMap<String,String>();
	protected static final TreeMap<String,String> bgcDobYears = new TreeMap<String,String>();
	
	public void init() {
		populateBgcSearchPurposes();
		populateBGCDobDays();
		populateBGCDobMonths();
		populateBGCDobYears();
	}
	
	@RequestMapping(value = "/flatrate/BGCsearchLanding.do", method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample){	
		
		if(session.getAttribute("FRusername") == null || session.getAttribute("FRuserId") == null){
			return FRLoginRedir;
		}
		
		//checked BGC search availablity
		int subId;
		if(session.getAttribute("BGCSearch") != null && session.getAttribute("BGCComSearch") != null){
			int BGCsubId = (Integer)session.getAttribute("BGCSubId");
			int COMsubId = (Integer)session.getAttribute("ComSubId");
			if(BGCsubId < COMsubId){
				subId = BGCsubId;				
			}else{
				subId = COMsubId;
			}
		}else if(session.getAttribute("BGCSearch") != null){
			subId = (Integer)session.getAttribute("BGCSubId");
		}else if(session.getAttribute("BGCComSearch") != null){
			subId = (Integer)session.getAttribute("ComSubId");
		}else{
			// No BGC available search	
			return FRMenuRedir;
		}
		
		//check whether the plan is expired
		if(subscriptionDao.isExpired(subId)){
			return FRMenuRedir;
		}
		session.setAttribute("activeBGCSubId", new Integer(subId));
		return setupFormBGC(session, map, test, sample, FRBGCLandingURL);
	}

	private String setupFormBGC(
			HttpSession session, ModelMap map, 
			Boolean test, Boolean sample, String viewName) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		FRBGCRequest cmd = new FRBGCRequest();
		cmd.setTest(test);
			
		map.addAttribute("command", cmd);	
		
		return viewName;
	}
	
	@RequestMapping(value = "/flatrate/BGCsearchLanding.do", method = RequestMethod.POST)
	public String processDelay(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command")  FRBGCRequest sfc,
			Errors errors,
			SessionStatus status) {
		
		return this.processDelayBGC(session, map, sfc, errors, status, FRBGCLandingURL);
	}
	
	private String processDelayBGC(HttpSession session,
			ModelMap map,
			FRBGCRequest sfc,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		if(session.getAttribute("BGCResultHash")!= null){session.removeAttribute("BGCResultHash");	}
		SearchFormValidator v = new SearchFormValidator();
		v.validate(sfc, errors);
		if (errors.hasErrors()) {return vwErrorReturn;} 
		map.addObject("sfc", sfc);
		return FRBGCDelayURL;
	}
	
	@RequestMapping(value = "/flatrate/BGCResultRedirect.do", method = RequestMethod.POST)
	public String processSubmit(HttpSession session, ModelMap map,
			@ModelAttribute("command") FRBGCRequest sfc,
			Errors errors, SessionStatus status) {
		
		//Check BGCStatus
		if(session.getAttribute("activeBGCSubId") == null){
			return FRBGCLandingRedir;
		}
		int subId = (Integer) session.getAttribute("activeBGCSubId");
		if(subscriptionDao.updateBGCSearchCount(subId) <=0 ){
			if(subscriptionDao.getPlanTypeIdBySubId(subId) == 2){
				session.removeAttribute("BGCSearch");
			}else{
				session.removeAttribute("BGCComSearch");
			}
		}
		
		return this.processSubmitBGC(session, map, sfc, errors, status);
	}
		
	private String processSubmitBGC(HttpSession session, ModelMap map, FRBGCRequest sfc,
			Errors errors,	SessionStatus status) {
		
			sfc.setSType(sfc.getExactDob());
			HashMap<String, FRBGCResponse> BGCResultHash = FRBGCManager.parseBGConFR(sfc);
			if(BGCResultHash.isEmpty()){
				map.addObject("sfc",sfc);
		    	return FRBGCNoResult;
		    }
			session.setAttribute("FRBGCRequest", sfc);
			session.setAttribute("BGCResultHash", BGCResultHash);
			status.setComplete();
			return FRBGCResultRedir;
		
	}
	
	public class SearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(FRBGCRequest.class);
		}
		
		public void validate(Object target, Errors errors) {
			FRBGCRequest cmd = (FRBGCRequest) target;

			validateString("bgcFirstName", cmd.getBgcFirstName(), errors,  3, 50, regexBasicSpace, "First Name");
			validateString("bgcLastName",  cmd.getBgcLastName(), errors,  3, 50, regexBasicSpace, "Last Name");
			validateString("bgcPurpose",   cmd.getBgcPurpose(), errors,  3, 50, regexBasicExtended, "Purpose");
			if(cmd.getBgcDobYear().isEmpty())
				errors.reject("bgcDobYear", "Select DOB Year");
			if(cmd.getBgcDobMonth().isEmpty())
				errors.reject("bgcDobMonth", "Select DOB Month");
			if(cmd.getBgcDobDay().isEmpty())
				errors.reject("bgcDobDay", "Select DOB Day");
			
		}		
	}
	
	// db lookups
	protected static final LinkedHashMap<String,String> bgcSearchPurposes = new LinkedHashMap<String,String>();
	
	@ModelAttribute("bgcSearchPurposes")
	public LinkedHashMap<String, String> getBgcSearchPurposes() {
		return bgcSearchPurposes;
	}
	
	protected final void populateBgcSearchPurposes() {
		bgcSearchPurposes.put("Personal",  "For personal use (non-FCRA).");
		bgcSearchPurposes.put("604.a1",    "Court order or subpoena. Section 604(a)(1)");
		bgcSearchPurposes.put("604.a2",    "Instructed by consumer in writing. Section 604(a)(2)");
		bgcSearchPurposes.put("604.a3b",   "Employment purposes with written permission. Section 604(a)(3)(B)");
		bgcSearchPurposes.put("604.a3c",   "Consumer application for insurance. Section 604(a)(3)(C)");
		bgcSearchPurposes.put("604.a3d",   "Business transaction initiated by consumer. Section 604(a)(3)(F)(i)");
		bgcSearchPurposes.put("604.a3e",   "Determine if consumer meets terms of account. Section 604(a)(3)(F)(ii)");
		bgcSearchPurposes.put("604.a3fi",  "Eligibility for a license or benefit. Section 604(a)(3)(D)");
		bgcSearchPurposes.put("604.a3fii", "Use by potential investor, servicer, or insurer. Section 604(a)(3)(E)");
	}
	
	protected final void populateBGCDobMonths() {
		for (int i=1; i<=12; i++) {
			String s = String.valueOf(i);
			if(s.length()==1){
				bgcDobMonths.put("0"+s, "0"+s);
				continue;
			}
			bgcDobMonths.put(s, s);		
		}
	}
	protected final void populateBGCDobDays() {
		for (int i=1; i<=31; i++) {
			String s = String.valueOf(i);
			if(s.length()==1){
				bgcDobDays.put("0"+s, "0"+s);
				continue;
			}
			bgcDobDays.put(s,s);
		}
	}
	protected final void populateBGCDobYears() {
		for (int i=1900; i<=1992; i++) {
			String s = String.valueOf(i);
			bgcDobYears.put(s, s);
		}
	}

	@ModelAttribute("bgcDobMonths")
	public static TreeMap<String, String> getBGCDobMonths() {
		return bgcDobMonths;
	}

	@ModelAttribute("bgcDobDays")
	public static TreeMap<String, String> getBGCDobDays() {
		return bgcDobDays;
	}
	
	@ModelAttribute("bgcDobYears")
	public static TreeMap<String, String> getBGCDobYears() {
		return bgcDobYears;
	}
	
	
}