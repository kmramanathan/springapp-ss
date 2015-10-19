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

public class NewBusinessSearchForm extends AbstractFunnelController {
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
	@RequestMapping(value = "/funnel/NewBusinessSearch.do", method = RequestMethod.GET)
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
		
		return setupFormBGC(session, map, test, sample, nationwide, newvwSearchBusiness, campaignId);		
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
		if (nationwide == null) { nationwide = false; }
		
		BusinessSearchFormCommand cmd = new BusinessSearchFormCommand();
		if (test || sample) { setSample(cmd); }
		cmd.setTest(test);
		cmd.setNationwideSearch(nationwide);
		cmd.setCampaignId(campaignId);
		
		map.addAttribute("command", cmd);	
		
		return viewName;
	}
	
	protected void setSample(BusinessSearchFormCommand cmd) {
		cmd.setBgcFirstName("Ronald");
		cmd.setBgcLastName("Ross");
		cmd.setBgcDobMonth(2);
		cmd.setBgcDobDay(22);
		cmd.setBgcDobYear(1974);
		cmd.setBgcState("KY");
		cmd.setBgcPurpose("Personal");
		cmd.setNationwideSearch(true);		
	}
	

	
	@RequestMapping(value = "/funnel/NewBusinessSearch.do", method = RequestMethod.POST)
	public String processBusinessSubmitFull(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") BusinessSearchFormCommand sfc,
			Errors errors,
			SessionStatus status) {
		return this.processSubmitBGC(session, map, sfc, errors, status, newvwSearchBusiness);
	}
	
	
	
	private String processSubmitBGC(HttpSession session,
			ModelMap map,
			BusinessSearchFormCommand sfc,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		logger.info("dobRange: " + sfc.getBgcDobRange());
		logger.info("bgcFirstNameExact: " + sfc.getBgcFirstNameExact());
		

		if(sfc.getBgcState().equalsIgnoreCase("all"))
			sfc.setNationwideSearch(true);

		SearchFormValidator v = new SearchFormValidator();
		v.validate(sfc, errors);
		if (errors.hasErrors()) {
			return vwErrorReturn;
		} else {
			logger.info("setting sfc & continuing");

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
			
			session.setAttribute("businessSearchFormCommand", sfc);	
			session.removeAttribute("registerFormCommand");
			session.removeAttribute("businessRegisterFormCommand");
			session.removeAttribute("searchFormCommand");
			session.removeAttribute("bjlSearchFormCommand");
			session.removeAttribute("evictionSearchFormCommand");
			session.removeAttribute("evictionBusinessSearchFormCommand");
			session.removeAttribute("corpIndSearchFormCommand");
			session.removeAttribute("corpBusSearchFormCommand");
			status.setComplete();
			
			if(session.getAttribute("username") != null){
				return newconfirmSearchRedir;
			}
			map.addAttribute("searchType", "newbgc");
			return redirLogin;
			
		}
	}
	
	public static class BusinessSearchFormCommand {
		// NOTE: fields need to be initialized to avoid type mismatch
		// errors in web form validation. this is necessary because we
		// use boxed types (which init as null) rather than primitives.
		// one way around this is to use String rather than Integer or
		// Boolean but then we have to deal with type conversions.
		
		// required fields
		private String bgcFirstName;
		private String bgcLastName;
		private Integer bgcDobMonth = 0;
		private Integer bgcDobDay = 0;
		private Integer bgcDobYear = 0;
		private String bgcPurpose;
		private String bgcState;
		
		// range search, base year, fuzz factor (plus/minus) 
		// if range is true, we need to set matchFuzzyDates for the search also
		private Boolean bgcDobRange = false;
		private Integer bgcDobRangeBaseYear = 0;
		private Integer bgcDobRangeFuzz = 0;
		
		// additional options
		private String bgcReferenceCode = "";
		private String bgcMiddleInitial = "";
		private Boolean bgcFirstNameExact = true;
		private Boolean bgcLastNameExact = true;
		private Boolean bgcMatchMissingDates = false;

		// this is not displayed on the form, customer could modify it
		private Integer campaignId;
		private Integer rateId;
		
		// deprecate these for rate
		// hmm. nationwide is probably useful so we don't have to check the
		// rate id all the time. price too		
		private Boolean nationwideSearch = true;
		private BigDecimal price;

		private Boolean test = false;	
				
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
		public Boolean getNationwideSearch() {
			return nationwideSearch;
		}
		public void setNationwideSearch(Boolean nationwideSearch) {
			this.nationwideSearch = nationwideSearch;
		}
		public String getBgcState() {
			return bgcState;
		}
		public void setBgcState(String bgcState) {
			this.bgcState = bgcState;
		}
		public String getBgcFirstName() {
			return bgcFirstName;
		}
		public void setBgcFirstName(String bgcFirstName) {
			this.bgcFirstName = bgcFirstName;
		}
		public String getBgcLastName() {
			return bgcLastName;
		}
		public void setBgcLastName(String bgcLastName) {
			this.bgcLastName = bgcLastName;
		}
		public Integer getBgcDobMonth() {
			return bgcDobMonth;
		}
		public void setBgcDobMonth(Integer bgcDobMonth) {
			this.bgcDobMonth = bgcDobMonth;
		}
		public Integer getBgcDobDay() {
			return bgcDobDay;
		}
		public void setBgcDobDay(Integer bgcDobDay) {
			this.bgcDobDay = bgcDobDay;
		}
		public Integer getBgcDobYear() {
			return bgcDobYear;
		}
		public void setBgcDobYear(Integer bgcDobYear) {
			this.bgcDobYear = bgcDobYear;
		}
		public String getBgcPurpose() {
			return bgcPurpose;
		}
		public void setBgcPurpose(String bgcPurpose) {
			this.bgcPurpose = bgcPurpose;
		}
		public Boolean getBgcDobRange() {
			return bgcDobRange;
		}
		public void setBgcDobRange(Boolean bgcDobRange) {
			this.bgcDobRange = bgcDobRange;
		}
		public Integer getBgcDobRangeBaseYear() {
			return bgcDobRangeBaseYear;
		}
		public void setBgcDobRangeBaseYear(Integer bgcDobRangeBaseYear) {
			this.bgcDobRangeBaseYear = bgcDobRangeBaseYear;
		}
		public Integer getBgcDobRangeFuzz() {
			return bgcDobRangeFuzz;
		}
		public void setBgcDobRangeFuzz(Integer bgcDobRangeFuzz) {
			this.bgcDobRangeFuzz = bgcDobRangeFuzz;
		}
		public String getBgcReferenceCode() {
			return bgcReferenceCode;
		}
		public void setBgcReferenceCode(String bgcReferenceCode) {
			this.bgcReferenceCode = bgcReferenceCode;
		}
		public String getBgcMiddleInitial() {
			return bgcMiddleInitial;
		}
		public void setBgcMiddleInitial(String bgcMiddleInitial) {
			this.bgcMiddleInitial = bgcMiddleInitial;
		}
		public Boolean getBgcFirstNameExact() {
			return bgcFirstNameExact;
		}
		public void setBgcFirstNameExact(Boolean bgcFirstNameExact) {
			this.bgcFirstNameExact = bgcFirstNameExact;
		}
		public Boolean getBgcLastNameExact() {
			return bgcLastNameExact;
		}
		public void setBgcLastNameExact(Boolean bgcLastNameExact) {
			this.bgcLastNameExact = bgcLastNameExact;
		}
		public Boolean getBgcMatchMissingDates() {
			return bgcMatchMissingDates;
		}
		public void setBgcMatchMissingDates(Boolean bgcMatchMissingDates) {
			this.bgcMatchMissingDates = bgcMatchMissingDates;
		}
	}
	
	public class SearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(BusinessSearchFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			BusinessSearchFormCommand cmd = (BusinessSearchFormCommand) target;
			//vivek changes
			//validateString("bgcFirstName", cmd.getBgcFirstName(), errors,  3, 50, regexBasicSpace, "First Name");
			validateStringEmptyMsg("bgcFirstName",  cmd.getBgcFirstName(), errors,  2, 50, regexBasicSpace, "First Name", "Enter in Subject's First Name");
			//validateString("bgcLastName",  cmd.getBgcLastName(), errors,  3, 50, regexBasicSpace, "Last Name");
			validateStringEmptyMsg("bgcLastName",  cmd.getBgcLastName(), errors,  2, 50, regexBasicSpace, "Last Name", "Enter in Subject's Last Name");
			validateString("bgcPurpose",   cmd.getBgcPurpose(), errors,  3, 50, regexBasicExtended, "Purpose");
			//validateStringEmptyMsg("bgcPurpose",  cmd.getBgcPurpose(), errors,  2, 50, regexBasicSpace, "Purpose", "Enter in Subject's Purpose");
			if (cmd.getNationwideSearch() == false) {
				validateString("bgcState",   cmd.getBgcState(), errors,  2, 2, regexBasicNoSpace, "State");
			}
			
			// figure out dob
			if (cmd.getBgcDobRange()) {
				// validate year range
				validateInteger("bgcDobRangeBaseYear", cmd.getBgcDobRangeBaseYear(), errors, 1900, 1999, "Year");
				validateInteger("bgcDobRangeFuzz", cmd.getBgcDobRangeFuzz(), errors, 0, 3, "Year Range");
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