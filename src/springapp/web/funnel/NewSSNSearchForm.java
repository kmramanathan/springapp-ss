package springapp.web.funnel;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import springapp.service.NeonValidator;
import springapp.web.funnel.DDNCriminalSearchForm.CriminalSearchFormCommand;
import springapp.web.funnel.NewSearchForm.SearchFormCommand;

@Controller
@SessionAttributes({"command","nationwideSearch"})

public class NewSSNSearchForm extends AbstractFunnelController {
	protected static final int CAMPAIGN_OLD_PRICES = 1018;
	protected static final int CAMPAIGN_NEW_PRICES = 2559;
	
	public void init() {
		populateBgcSearchPurposes();
	}
	
		
	/*
	 * get campaign id, item id from request
	 * verify item for campaign
	 * set rate id (item) 
	 */
	
	@RequestMapping(value = "/funnel/newSSNSearch.do", method = RequestMethod.GET)
	public String setupBusinessFormCampaign(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="nationwide",required=false) Boolean nationwide,
			@RequestParam(value="campaign",required=false) Integer campaignId,
			@RequestParam(value="bgcfname",required=false) String bgcfname,
			@RequestParam(value="bgclname",required=false) String bgclname,
			@RequestParam(value="bgcusstate",required=false) String bgcusstate,
			@RequestParam(value="bgcssn",required=false) String bgcssn
			) {	
		// check campaign id here
		if (campaignId == null) { campaignId = CAMPAIGN_OLD_PRICES; }
		if (!(campaignId == CAMPAIGN_OLD_PRICES || campaignId == CAMPAIGN_NEW_PRICES)) {
			return newvwError;
		}
		
		return setupFormBGC(session, map, test, sample, nationwide, newvwAliasSearch, campaignId, bgcfname, bgclname, bgcusstate,bgcssn);		
	}

	/*
	 * modify this to use rate instead of nationwide
	 * keep list of rates which are nationwide?
	 */
	private String setupFormBGC(
			HttpSession session, ModelMap map, 
			Boolean test, Boolean sample, Boolean nationwide, 
			String viewName, Integer campaignId, String bgcfname,
			String bgclname, String bgcusstate, String bgcssn) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		if (nationwide == null) { nationwide = false; }
		if(bgcfname == null){bgcfname="";}
		if(bgclname == null){bgclname="";}
		if(bgcusstate == null){bgcusstate="";}
		if(bgcssn == null){bgcssn="";}
		SearchFormCommand cmd = new SearchFormCommand();
		if (test || sample) { setSample(cmd); } 
		
		if(bgcfname != null && bgclname != null && bgcusstate != null && bgcssn!=null)
		{
			cmd.setBgcFirstName(bgcfname);
			cmd.setBgcLastName(bgclname);
			cmd.setBgcState(bgcusstate);
			cmd.setBgcSsn(bgcssn);
			 
			
		}
		cmd.setTest(test);
		cmd.setNationwideSearch(nationwide);
		cmd.setCampaignId(campaignId);		
		map.addAttribute("command", cmd);	
		
		return viewName;
	}
	
	protected void setSample(SearchFormCommand cmd) 
	{
		cmd.setBgcFirstName("Ronald");
		cmd.setBgcLastName("Ross");
		cmd.setBgcDobMonth(2);
		cmd.setBgcDobDay(22);
		cmd.setBgcDobYear(1974);
		cmd.setBgcState("CA");
		cmd.setBgcPurpose("Personal");
		cmd.setBgcSsn("239393421");
		cmd.setNationwideSearch(false);		
	}
	

	
	@RequestMapping(value = "/funnel/newSSNSearch.do", method = RequestMethod.POST)
	public String processBusinessSubmitFull(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") SearchFormCommand sfc,
			Errors errors,
			SessionStatus status) {
		
		session.setAttribute("searchFormCommand", sfc);
		
		SearchFormCommand crimfc=(SearchFormCommand) session.getAttribute("searchFormCommand"); 
		if(crimfc!=null)
		{
			
			if(crimfc.getBgcFirstName() !=null && crimfc.getBgcLastName() !=null )
			{
				
				if(crimfc.getBgcFirstName().equals("Charles") && crimfc.getBgcLastName().equals("Souza"))
				{
					map.addAttribute("firstName", crimfc.getBgcFirstName());
					map.addAttribute("lastName", crimfc.getBgcLastName());
					map.addAttribute("location", crimfc.getBgcState());
					
					String searchDOB = crimfc.getBgcDobMonth() + "/" + crimfc.getBgcDobDay() + "/" + crimfc.getBgcDobYear();
					map.addAttribute("DOB", searchDOB);
					return newzeroResultsView;
				}
			} 
		}
		return this.processSubmitBGC(session, map, sfc, errors, status, newvwAliasSearch);
	}	
	private String processSubmitBGC(HttpSession session,
			ModelMap map,	SearchFormCommand sfc,	Errors errors,
			SessionStatus status,	String vwErrorReturn) 
	{
		

		if(sfc.getBgcState().equalsIgnoreCase("all"))
			sfc.setNationwideSearch(true);

		SearchFormValidator v = new SearchFormValidator();
		v.validate(sfc, errors);
		if (errors.hasErrors()) 
		{
			return vwErrorReturn;
		} 
		else 
		{
			logger.info("setting criminl SSN & continuing");

			Integer campaignId = sfc.getCampaignId();
			switch (campaignId) {
			
			case CAMPAIGN_OLD_PRICES:
				if (sfc.getNationwideSearch()) {
					sfc.setPrice(new BigDecimal("14.95"));
				} else {
					sfc.setPrice(new BigDecimal("9.95"));
				}
				break;
				
			case CAMPAIGN_NEW_PRICES:
				if (sfc.getNationwideSearch()) {
					sfc.setPrice(new BigDecimal("19.95"));
				} else {
					sfc.setPrice(new BigDecimal("9.95"));
				}
				break;
			}
			String state=sfc.getBgcState();
			
			if(state.equals("CA") || state.equals("IN") || state.equals("MO") || state.equals("HI") || state.equals("KS") || state.equals("MS") || state.equals("ND") || state.equals("NJ") || state.equals("NV") || state.equals("PA") || state.equals("RI") || state.equals("UT"))
			{
				Integer dobyear=sfc.getBgcDobYear();
				/*
				if(dobyear != 0 && sfc.getBgcDobRange() == false)
				{
					
					sfc.setBgcDobRange(true);
					sfc.setBgcDobRangeBaseYear(dobyear);
					sfc.setBgcDobDay(0);
					sfc.setBgcDobMonth(0);
					sfc.setBgcDobYear(0);
				}*/
				
			}
			
			 
			session.setAttribute("searchFormCommand", sfc);	
			session.removeAttribute("registerFormCommand");
			session.removeAttribute("bjlSearchFormCommand");
			session.removeAttribute("evictionSearchFormCommand");
			session.removeAttribute("evictionBusinessSearchFormCommand");
			session.removeAttribute("corpIndSearchFormCommand");
			session.removeAttribute("corpBusSearchFormCommand");
			session.removeAttribute("nationSearchFormCommand");
			status.setComplete();
			
			if(sfc.getNewacc() == true)
			{
				return newregisterRedir;
			}
			
			if(session.getAttribute("username") != null){
				return newconfirmSearchRedir;
			}
			map.addAttribute("searchType", "bgcSSN");
			return redirLogin;
			
		}
	}
	
	@RequestMapping(value="/funnel/NewSSNSearchDelay.do")
	public String CriminalSearchDelay(HttpServletRequest request, HttpServletResponse response,
			HttpSession session)
	{
		
		return "funnel/CriminalStateSearchDelay";
	}
	 
	
	
	public class SearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(SearchFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			SearchFormCommand cmd = (SearchFormCommand) target;
			//vivek changes
			//validateString("bgcFirstName", cmd.getBgcFirstName(), errors,  3, 50, regexBasicSpace, "First Name");
			validateStringEmptyMsg("bgcFirstName",  cmd.getBgcFirstName(), errors,  2, 50, regexBasicExtended, "First Name", "Enter in Subject's First Name");
			//validateString("bgcLastName",  cmd.getBgcLastName(), errors,  3, 50, regexBasicSpace, "Last Name");
			validateStringEmptyMsg("bgcLastName",  cmd.getBgcLastName(), errors,  2, 50, regexBasicExtended, "Last Name", "Enter in Subject's Last Name");
			validateString("bgcPurpose",   cmd.getBgcPurpose(), errors,  3, 50, regexBasicExtended, "Purpose");
			//validateStringEmptyMsg("bgcPurpose",  cmd.getBgcPurpose(), errors,  2, 50, regexBasicSpace, "Purpose", "Enter in Subject's Purpose");
			if (cmd.getNationwideSearch() == false) {
				validateString("bgcState",   cmd.getBgcState(), errors,  2, 2, regexBasicNoSpace, "State");
			}
			//SSN number
			validateStringEmptyMsg("bgcSsn",  cmd.getBgcSsn(), errors,  2, 9, regexBasicExtended, "SSN Numebr", "Enter in Subject's SSN");
			
			// figure out dob
			if (cmd.getBgcDobRange()) 
			{
				// validate year range
				validateInteger("bgcDobRangeBaseYear", cmd.getBgcDobRangeBaseYear(), errors, 1900, 1999, "Year of Birth");
				validateInteger("bgcDobRangeFuzz", cmd.getBgcDobRangeFuzz(), errors, 0, 3, "Year of Fuzz");
				
				
			} else {
				
				// validate exact date
				Calendar cal = Calendar.getInstance();
				cal.setLenient(false);
				try {
					cal.set(cmd.getBgcDobYear(), cmd.getBgcDobMonth() - 1, cmd.getBgcDobDay());
					cal.getTime();
				} catch (Exception e) {
					errors.reject("invalid-date", "The date of birth you entered is invalid.");
				}	
							
			}

			// check additional options
			validateStringEmptyOk("bgcMiddleInitial", cmd.getBgcMiddleInitial(), errors, 1, 1, regexLettersOnly, "Middle Initial");
			validateStringEmptyOk("bgcReferenceCode", cmd.getBgcReferenceCode(), errors, 1, 20, regexBasicExtended, "Reference Code");
		}		
	}
	
		// static lookups

	// db lookups
	protected static final LinkedHashMap<String,String> bgcSearchPurposes = new LinkedHashMap<String,String>();
	
	@ModelAttribute("bgcSearchPurposes")
	public LinkedHashMap<String, String> getBgcSearchPurposes() {
		return bgcSearchPurposes;
	}
	
	protected final void populateBgcSearchPurposes() {
		//bgcSearchPurposes.put("Personal",  "For personal use (non-FCRA).");
		bgcSearchPurposes.put("Personal", "Personal Use Only");
		bgcSearchPurposes.put("604.a1",    "Court order or subpoena. Section 604(a)(1)");
		bgcSearchPurposes.put("604.a2",    "Instructed by consumer in writing. Section 604(a)(2)");
		bgcSearchPurposes.put("604.a3b",   "Employment purposes with written permission. Section 604(a)(3)(B)");
		bgcSearchPurposes.put("604.a3c",   "Consumer application for insurance. Section 604(a)(3)(C)");
		bgcSearchPurposes.put("604.a3d",   "Business transaction initiated by consumer. Section 604(a)(3)(F)(i)");
		bgcSearchPurposes.put("604.a3e",   "Determine if consumer meets terms of account. Section 604(a)(3)(F)(ii)");
		bgcSearchPurposes.put("604.a3fi",  "Eligibility for a license or benefit. Section 604(a)(3)(D)");
		bgcSearchPurposes.put("604.a3fii", "Use by potential investor, servicer, or insurer. Section 604(a)(3)(E)");
		
	}
}