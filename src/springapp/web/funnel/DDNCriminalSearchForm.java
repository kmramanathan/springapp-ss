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
import springapp.web.funnel.NewAliasSearchForm.AliasSearchFormCommand;


@Controller
@SessionAttributes({"command","nationwideSearch"})

public class DDNCriminalSearchForm extends AbstractFunnelController {
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
	// evicReal Property normal searches 
	@RequestMapping(value = "/funnel/criminalSearch.do", method = RequestMethod.GET)
	public String setupEvictionFormCampaign(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="nationwide",required=false) Boolean nationwide,
			@RequestParam(value="campaign",required=false) Integer campaignId,
			@RequestParam(value="sample",required=false) Boolean sample) {	
		 
		// check campaign id here
				if (campaignId == null) { campaignId = CAMPAIGN_OLD_PRICES; }
				if (!(campaignId == CAMPAIGN_OLD_PRICES || campaignId == CAMPAIGN_NEW_PRICES)) {
					return newvwError;
				}
		return setupPropertyForm(session, map, test, sample,nationwide,vwDdnCriminalSearch,campaignId);		
	}	

	/*
	 * modify this to use rate instead of nationwide
	 * keep list of rates which are nationwide?
	 */
	
	private String setupPropertyForm(
			HttpSession session, ModelMap map, 
			Boolean test, Boolean sample,Boolean nationwide, 
			String viewName, Integer campaignId) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		if (nationwide == null) { nationwide = false; }
		
		CriminalSearchFormCommand cmd = new CriminalSearchFormCommand();
		if (test || sample) { setSample(cmd); }
		cmd.setTest(test);
		cmd.setNationwideSearch(nationwide);
		cmd.setCampaignId(campaignId);
		//cmd.setFirstname("THOMAS");
		//cmd.setLastname("MCDERMOTT");
		
		map.addAttribute("command", cmd);	
		return viewName;
	}
	
	protected void setSample(CriminalSearchFormCommand cmd) {
		cmd.setFirstname("John");
		cmd.setLastname("Smith");
		cmd.setCrmnlDobDay(15);
		cmd.setCrmnlDobMonth(5);
		cmd.setCrmnlDobYear(1950);
		cmd.setSsn("149587526");
	}
	
	@RequestMapping(value = "/funnel/criminalSearch.do", method = RequestMethod.POST)
	public String processCriminalRecordSearchSubmitForPersonal(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="nationwide",required=false) Boolean nationwide,
			@RequestParam(value="campaign",required=false) Integer campaignId,
			@ModelAttribute("command") CriminalSearchFormCommand crmnlsf,
			Errors errors,
			SessionStatus status) {
		
		session.setAttribute("CriminalSearchFormCommand", crmnlsf);
		 
		//CriminalSearchFormCommand crimfc=(CriminalSearchFormCommand) session.getAttribute("CriminalSearchFormCommand");
		
			// check campaign id here
				if (campaignId == null) { campaignId = CAMPAIGN_OLD_PRICES; }
				if (!(campaignId == CAMPAIGN_OLD_PRICES || campaignId == CAMPAIGN_NEW_PRICES)) 
				{
					return newvwError;
				}
				
		return this.processSubmitProperty(session, map, crmnlsf, errors, status, vwDdnCriminalSearch);
	}
	
	
	
	private String processSubmitProperty(HttpSession session,
			ModelMap map,
			CriminalSearchFormCommand crmnlsf,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		logger.info("Non-FCRA Search - firstName: " + crmnlsf.getFirstname());
		logger.info("Non-FCRA Search - dob: " + crmnlsf.getCrmnlDobMonth() + "/" + crmnlsf.getCrmnlDobDay() + "/" + crmnlsf.getCrmnlDobYear());
		
		if(crmnlsf.getState().equalsIgnoreCase("all"))
			crmnlsf.setNationwideSearch(true);
		else
			crmnlsf.setNationwideSearch(false);
		
		CriminalSearchFormValidator v = new CriminalSearchFormValidator();
		v.validate(crmnlsf, errors);
		if (errors.hasErrors()) {
			return vwErrorReturn;
		} else {
			if(session.getAttribute("CriminalSearchFormCommand") != null)
			{
				session.removeAttribute("CriminalSearchFormCommand");
			}
			
			Integer campaignId = crmnlsf.getCampaignId();
			switch (campaignId) {
			
			case CAMPAIGN_OLD_PRICES:
				if (crmnlsf.getNationwideSearch()) {
					crmnlsf.setPrice(new BigDecimal("14.95"));
				} else {
					crmnlsf.setPrice(new BigDecimal("9.95"));
				}
				break;
				
			case CAMPAIGN_NEW_PRICES:
				if (crmnlsf.getNationwideSearch()) {
					crmnlsf.setPrice(new BigDecimal("19.95"));
				} else {
					crmnlsf.setPrice(new BigDecimal("9.95"));
				}
				break;
			}
			
			if(crmnlsf.getState().equals("all"))
				crmnlsf.setPrice(new BigDecimal("14.95"));
			else
				crmnlsf.setPrice(new BigDecimal("9.95"));
			
			//crmnlsf.setBgcPurpose("Personal");
			
			logger.info("setting ddnCriminal & continuing" + crmnlsf.getPrice());
			session.removeAttribute("searchFormCommand");
			session.removeAttribute("businessSearchFormCommand");
			session.removeAttribute("registerFormCommand");
			session.removeAttribute("aliasSearchFormCommand");
			session.removeAttribute("bjlSearchFormCommand");
			session.removeAttribute("corpIndSearchFormCommand");
			session.removeAttribute("corpBusSearchFormCommand");
			session.removeAttribute("nationSearchFormCommand");
			session.removeAttribute("evictionSearchFormCommand");
			session.setAttribute("CriminalSearchFormCommand", crmnlsf);
			session.removeAttribute("RPAdresSearch");
			//session.setAttribute("RPNameSearch", "RPName");
			
			map.addAttribute("searchType", "SSN Criminal Search");
			
			status.setComplete();
			if(crmnlsf.getNewacc() == true)
			{
				return newregisterRedir;
				
			}
			
			if(session.getAttribute("username") != null){
				return newconfirmSearchRedir;
			}
			
			return redirLogin;
		}	
		}
	//search delay interim page
	@RequestMapping(value="/funnel/DDNCriminalStateDelay.do")
	public String CriminalSearchDelay(HttpServletRequest request, HttpServletResponse response,
			HttpSession session)
	{
		
		return "funnel/CriminalStateSearchDelay";
	}
	@RequestMapping(value="/funnel/DDNCriminalNationalDelay.do")
	public String CriminalNationSearchDelay(HttpServletRequest request, HttpServletResponse response,
			HttpSession session)
	{
		return "funnel/CriminalNationalSearchDelay";
	}
	

	public static class CriminalSearchFormCommand {
		// NOTE: fields need to be initialized to avoid type mismatch
		// errors in web form validation. this is necessary because we
		// use boxed types (which init as null) rather than primitives.
		// one way around this is to use String rather than Integer or
		// Boolean but then we have to deal with type conversions.
		
		// required fields
		private String offenderCount;
		private String firstname;
		private String lastname;
		private String middlename;
		private String middleInitial;
		private Boolean matchMissingDates = true;

		private String bgcPurpose;
		
		private Boolean firstNameExact  = true;
		private Boolean lastNameExact  = true;
		private String address;
		private String state;
		private String offense;
		private String casenum;
		private String filedate;
		private String disposition;		
		private String dispositiondate;
		private String offensecounty;
		private String sourceofrecord;
		private String offencecitation;
		private String offencecount;
		private String offencecourt;
		private String offencedlstate;
		private String casetype;
		private String courtaddress;
		private String incarcerationDetail;
		private String courtname;
		private String dob;
		private String ssn;
		private Boolean bgcDobRange = false;
		private Integer crmnlDobMonth = 0;
		private Integer crmnlDobDay = 0;
		private Integer crmnlDobYear = 0;
		//private String test;
		private Boolean test = false;	
		private Boolean newacc=false;
		
		// deprecate these for rate
		// hmm. nationwide is probably useful so we don't have to check the
		// rate id all the time. price too		
		private BigDecimal price;
		private Boolean nationwideSearch = true;
		private String bgcState;
		private Integer campaignId;
		private String referenceCode;
		
		
		
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
		
		public Boolean getBgcDobRange() {
			return bgcDobRange;
		}
		public void setBgcDobRange(Boolean bgcDobRange) {
			this.bgcDobRange = bgcDobRange;
		}
		
		public Boolean getNewacc() {
			return newacc;
		}
		public void setNewacc(Boolean newacc) {
			this.newacc = newacc;
		}			
		
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		
		public Integer getCrmnlDobMonth() {
			return crmnlDobMonth;
		}
		public void setCrmnlDobMonth(Integer crmnlDobMonth) {
			this.crmnlDobMonth = crmnlDobMonth;
		}
		
		public Integer getCrmnlDobDay() {
			return crmnlDobDay;
		}
		public void setCrmnlDobDay(Integer crmnlDobDay) {
			this.crmnlDobDay = crmnlDobDay;
		}
		
		public Integer getCrmnlDobYear() {
			return crmnlDobYear;
		}
		public void setCrmnlDobYear(Integer crmnlDobYear) {
			this.crmnlDobYear = crmnlDobYear;
		}
		

		public String getBgcPurpose() {
			return bgcPurpose;
		}
		public void setBgcPurpose(String bgcPurpose) {
			this.bgcPurpose = bgcPurpose;
		}
		
		public String getSsn() {
			return ssn;
		}
		
		public void setSsn(String ssn) {
			this.ssn = ssn;
		}
		
		
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getMiddlename() {
			return middlename;
		}
		public void setMiddlename(String middlename) {
			this.middlename = middlename;
		}
		
		public String getMiddleInitial() {
			return middleInitial;
		}
		public void setMiddleInitial(String middleInitial) {
			this.middleInitial = middleInitial;
		}
		
		public Boolean getMatchMissingDates() {
			return matchMissingDates;
		}
		public void setMatchMissingDates(Boolean matchMissingDates) {
			this.matchMissingDates = matchMissingDates;
		}
		
		
		public Boolean getfirstNameExact() {
			return firstNameExact;
		}
		public void setfirstNameExact(Boolean firstNameExact) {
			this.firstNameExact = firstNameExact;
		}
		public Boolean getlastNameExact() {
			return lastNameExact;
		}
		public void setlastNameExact(Boolean lastNameExact) {
			this.lastNameExact = lastNameExact;
		}
		
		
		public String getOffenderCount() {
			return offenderCount;
		}
		public void setOffenderCount(String offenderCount) {
			this.offenderCount = offenderCount;
		}		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}	
		
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}	
		
		
		public String getOffense() {
			return offense;
		}
		public void setOffense(String offense) {
			this.offense = offense;
		}
		public String getCasenum() {
			return casenum;
		}
		public void setCasenum(String casenum) {
			this.casenum = casenum;
		}
		
		public String getFiledate() {
			return filedate;
		}
		public void setFiledate(String filedate) {
			this.filedate = filedate;
		}		
		
		public String getDisposition() {
			return disposition;
		}
		public void setDisposition(String disposition) {
			this.disposition = disposition;
		}
		
		
		public String getDispositiondate() {
			return dispositiondate;
		}
		public void setDispositiondate(String dispositiondate) {
			this.dispositiondate = dispositiondate;
		}	
		
		public String getOffensecounty() {
			return offensecounty;
		}
		public void setOffensecounty(String offensecounty) {
			this.offensecounty = offensecounty;
		}

		public String getOffencecitation() {
			return offencecitation;
		}
		public void setOffencecitation(String offencecitation) {
			this.offencecitation = offencecitation;
		}
		
		public String getOffencecount() {
			return offencecount;
		}
		public void setOffencecount(String offencecount) {
			this.offencecount = offencecount;
		}

		public String getOffencecourt() {
			return offencecourt;
		}
		public void setOffencecourt(String offencecourt) {
			this.offencecourt = offencecourt;
		}

		public String getOffencedlstate() {
			return offencedlstate;
		}
		public void setOffencedlstate(String offencedlstate) {
			this.offencedlstate = offencedlstate;
		}
		public String getCasetype() {
			return casetype;
		}
		public void setCasetype(String casetype) {
			this.casetype = casetype;
		}
		public String getcourtaddress() {
			return courtaddress;
		}
		public void setcourtaddress(String courtaddress) {
			this.courtaddress = courtaddress;
		}
		public String getIncarcerationDetail() {
			return incarcerationDetail;
		}
		public void setIncarcerationDetail(String incarcerationDetail) {
			this.incarcerationDetail = incarcerationDetail;
		}
		public String getCourtname() {
			return courtname;
		}
		public void setCourtname(String courtname) {
			this.courtname = courtname;
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
		public Integer getCampaignId() {
			return campaignId;
		}
		public void setCampaignId(Integer campaignId) {
			this.campaignId = campaignId;
		}
		
		public String getReferenceCode() {
			return referenceCode;
		}
		public void setReferenceCode(String referenceCode) {
			this.referenceCode = referenceCode;
		}
		
	}
	

	// db lookups
	protected static final LinkedHashMap<String,String> bgcSearchPurposes = new LinkedHashMap<String,String>();
	
	@ModelAttribute("bgcSearchPurposes")
	public LinkedHashMap<String, String> getBgcSearchPurposes() {
		return bgcSearchPurposes;
	}
	
	protected final void populateBgcSearchPurposes() {
		//bgcSearchPurposes.put("Personal",  "For personal use (non-FCRA).");
		bgcSearchPurposes.put("Personal", "Personal Use Only");
		/*bgcSearchPurposes.put("604.a1",    "Court order or subpoena. Section 604(a)(1)");
		bgcSearchPurposes.put("604.a2",    "Instructed by consumer in writing. Section 604(a)(2)");
		bgcSearchPurposes.put("604.a3b",   "Employment purposes with written permission. Section 604(a)(3)(B)");
		bgcSearchPurposes.put("604.a3c",   "Consumer application for insurance. Section 604(a)(3)(C)");
		bgcSearchPurposes.put("604.a3d",   "Business transaction initiated by consumer. Section 604(a)(3)(F)(i)");
		bgcSearchPurposes.put("604.a3e",   "Determine if consumer meets terms of account. Section 604(a)(3)(F)(ii)");
		bgcSearchPurposes.put("604.a3fi",  "Eligibility for a license or benefit. Section 604(a)(3)(D)");
		bgcSearchPurposes.put("604.a3fii", "Use by potential investor, servicer, or insurer. Section 604(a)(3)(E)");*/
		
	}
	
	public class CriminalSearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(CriminalSearchFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			CriminalSearchFormCommand cmd = (CriminalSearchFormCommand) target;
			
			validateStringEmptyMsg("firstname",  cmd.getFirstname(), errors,  2, 50, regexBasicSpace, "First Name", "Enter in First Name");
			validateStringEmptyMsg("lastname",  cmd.getLastname(), errors,  2, 50, regexBasicSpace, "First Name", "Enter in Last Name");
			//SSN number
			//validateStringEmptyMsg("ssn",  cmd.getSsn(), errors,  2, 9, regexBasicExtended, "SSN Numebr", "Enter in Subject's SSN");
			
			//if (cmd.getCrmnlDobYear() != 0 || cmd.getCrmnlDobMonth() != 0 || cmd.getCrmnlDobDay() != 0) {
				Calendar cal = Calendar.getInstance();
				cal.setLenient(false);
				try {
					cal.set(cmd.getCrmnlDobYear(), cmd.getCrmnlDobMonth() - 1, cmd.getCrmnlDobDay());
					cal.getTime();
				} catch (Exception e) {
					errors.reject("invalid-date", "The date of birth you entered is invalid.");
				}
			//}
		}
	}
	
	
	
}