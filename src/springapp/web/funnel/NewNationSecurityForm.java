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

@Controller
@SessionAttributes({"command","nationwideSearch"})

public class NewNationSecurityForm extends AbstractFunnelController {
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
	@RequestMapping(value = "/funnel/national-security-search.do", method = RequestMethod.GET)
	public String setupBusinessFormCampaign(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="nationwide",required=false) Boolean nationwide,
			@RequestParam(value="campaign",required=false) Integer campaignId
			
			) {	
		// check campaign id here
		if (campaignId == null) { campaignId = CAMPAIGN_OLD_PRICES; }
		if (!(campaignId == CAMPAIGN_OLD_PRICES || campaignId == CAMPAIGN_NEW_PRICES)) {
			return newvwError;
		}
		
		return setupFormBGC(session, map, test, sample, nationwide, nationSecurityView, campaignId);		
	}

	/*
	 * modify this to use rate instead of nationwide
	 * keep list of rates which are nationwide?
	 */
	private String setupFormBGC(
			HttpSession session, ModelMap map, 
			Boolean test, Boolean sample, Boolean nationwide, 
			String viewName, Integer campaignId) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		NationSearchFormCommand cmd = new NationSearchFormCommand();
		if (test || sample) { setSample(cmd); }
		
		cmd.setTest(test);
		
		cmd.setCampaignId(campaignId);
		
		map.addAttribute("command", cmd);	
		
		return viewName;
	}
	
	protected void setSample(NationSearchFormCommand cmd) {
		cmd.setNssFirstName("Ronald");
		cmd.setNssLastName("Ross");
		
		cmd.setNssPurpose("Personal");
		
	}
	

	
	@RequestMapping(value = "/funnel/national-security-search.do", method = RequestMethod.POST)
	public String processBusinessSubmitFull(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") NationSearchFormCommand sfc,
			Errors errors,
			SessionStatus status) {
		return this.processSubmitBGC(session, map, sfc, errors, status, nationSecurityView);
	}
	
	
	
	private String processSubmitBGC(HttpSession session,
			ModelMap map,
			NationSearchFormCommand sfc,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		
		logger.info("bgcFirstNameExact: " + sfc.getNssFirstNameExact());
		

		

		NationSearchFormValidator v = new NationSearchFormValidator();
		v.validate(sfc, errors);
		if (errors.hasErrors()) {
			return vwErrorReturn;
		} else {
			logger.info("setting sfc & continuing");

			Integer campaignId = sfc.getCampaignId();
			switch (campaignId) {
			
			case CAMPAIGN_OLD_PRICES:
				
					sfc.setPrice(new BigDecimal("5.00"));
				
				break;
				
			case CAMPAIGN_NEW_PRICES:
				sfc.setPrice(new BigDecimal("5.00"));
				break;
			}
			
			
			
			
			session.setAttribute("nationSearchFormCommand", sfc);
			session.removeAttribute("searchFormCommand");
			session.removeAttribute("registerFormCommand");
			session.removeAttribute("bjlSearchFormCommand");
			session.removeAttribute("evictionSearchFormCommand");
			session.removeAttribute("evictionBusinessSearchFormCommand");
			session.removeAttribute("corpIndSearchFormCommand");
			session.removeAttribute("corpBusSearchFormCommand");
			session.removeAttribute("aliasSearchFormCommand");
			
			status.setComplete();
			
			if(sfc.getNewacc() == true)
			{
				return newregisterRedir;
			}
			
			if(session.getAttribute("username") != null){
				return newconfirmSearchRedir;
			}
			map.addAttribute("searchType", "nation");
			return redirLogin;
			
		}
	}
	
	@RequestMapping(value="/funnel/NationalSecurityDelay.do")
	public String CriminalSearchDelay(HttpServletRequest request, HttpServletResponse response,
			HttpSession session)
	{
		
		return nssSearchDelayView;
	}
	public static class NationSearchFormCommand {
		// NOTE: fields need to be initialized to avoid type mismatch
		// errors in web form validation. this is necessary because we
		// use boxed types (which init as null) rather than primitives.
		// one way around this is to use String rather than Integer or
		// Boolean but then we have to deal with type conversions.
		
		// required fields
		private String nssFirstName;
		private String nssLastName;
		private Integer nssDobMonth = 0;
		private Integer nssDobDay = 0;
		private Integer nssDobYear = 0;
		private String nssPurpose;
		
		
		
		
		// additional options
		private String nssReferenceCode = "";
		
		private Boolean nssFirstNameExact = true;
		private Boolean nssLastNameExact = true;
		

		// this is not displayed on the form, customer could modify it
		private Integer campaignId;
		private Integer rateId;
		
		// deprecate these for rate
		// hmm. nationwide is probably useful so we don't have to check the
		// rate id all the time. price too		
		
		private BigDecimal price;

		private Boolean test = false;
		
		private Boolean newacc= false;
		
				
		public Boolean getNewacc() {
			return newacc;
		}
		public void setNewacc(Boolean newacc) {
			this.newacc = newacc;
		}
		public Integer getRateId() {
			return rateId;
		}
		public void setRateId(Integer rateId) {
			this.rateId = rateId;
		}
		public Integer getCampaignId() {
			return campaignId;
		}
		public void setCampaignId(Integer campaignId) {
			this.campaignId = campaignId;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public Boolean getTest() {
			return test;
		}
		public void setTest(Boolean test) {
			this.test = test;
		}
		public String getNssFirstName() {
			return nssFirstName;
		}
		public void setNssFirstName(String nssFirstName) {
			this.nssFirstName = nssFirstName;
		}
		public String getNssLastName() {
			return nssLastName;
		}
		public void setNssLastName(String nssLastName) {
			this.nssLastName = nssLastName;
		}
		public Integer getNssDobMonth() {
			return nssDobMonth;
		}
		public void setNssDobMonth(Integer nssDobMonth) {
			this.nssDobMonth = nssDobMonth;
		}
		public Integer getNssDobDay() {
			return nssDobDay;
		}
		public void setNssDobDay(Integer nssDobDay) {
			this.nssDobDay = nssDobDay;
		}
		public Integer getNssDobYear() {
			return nssDobYear;
		}
		public void setNssDobYear(Integer nssDobYear) {
			this.nssDobYear = nssDobYear;
		}
		public String getNssPurpose() {
			return nssPurpose;
		}
		public void setNssPurpose(String nssPurpose) {
			this.nssPurpose = nssPurpose;
		}
		public String getNssReferenceCode() {
			return nssReferenceCode;
		}
		public void setNssReferenceCode(String nssReferenceCode) {
			this.nssReferenceCode = nssReferenceCode;
		}
		public Boolean getNssFirstNameExact() {
			return nssFirstNameExact;
		}
		public void setNssFirstNameExact(Boolean nssFirstNameExact) {
			this.nssFirstNameExact = nssFirstNameExact;
		}
		public Boolean getNssLastNameExact() {
			return nssLastNameExact;
		}
		public void setNssLastNameExact(Boolean nssLastNameExact) {
			this.nssLastNameExact = nssLastNameExact;
		}
		
	
		
	}
	
	public class NationSearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(NationSearchFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			NationSearchFormCommand cmd = (NationSearchFormCommand) target;
			//vivek changes
			//validateString("bgcFirstName", cmd.getBgcFirstName(), errors,  3, 50, regexBasicSpace, "First Name");
			validateStringEmptyMsg("nssFirstName",  cmd.getNssFirstName(), errors,  2, 50, regexBasicExtended, "First Name", "Enter in Subject's First Name");
			//validateString("bgcLastName",  cmd.getBgcLastName(), errors,  3, 50, regexBasicSpace, "Last Name");
			validateStringEmptyMsg("nssLastName",  cmd.getNssLastName(), errors,  2, 50, regexBasicExtended, "Last Name", "Enter in Subject's Last Name");
			validateString("nssPurpose",   cmd.getNssPurpose(), errors,  3, 50, regexBasicExtended, "Purpose");
			//validateStringEmptyMsg("bgcPurpose",  cmd.getBgcPurpose(), errors,  2, 50, regexBasicSpace, "Purpose", "Enter in Subject's Purpose");
			
			
			// validate exact date
			Calendar cal = Calendar.getInstance();
			cal.setLenient(false);
			try {
				cal.set(cmd.getNssDobYear(), cmd.getNssDobMonth() - 1, cmd.getNssDobDay());
				cal.getTime();
			} catch (Exception e) {
				errors.reject("invalid-date", "The date of birth you entered is invalid.");
			}

			// check additional options
			//validateStringEmptyOk("bgcMiddleInitial", cmd.getBgcMiddleInitial(), errors, 1, 1, regexLettersOnly, "Middle Initial");
			validateStringEmptyOk("nssReferenceCode", cmd.getNssReferenceCode(), errors, 1, 20, regexBasicExtended, "Reference Code");
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