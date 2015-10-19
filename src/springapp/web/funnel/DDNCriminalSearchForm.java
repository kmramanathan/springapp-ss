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

public class DDNCriminalSearchForm extends AbstractFunnelController {
	protected static final int CAMPAIGN_OLD_PRICES = 1018;
	protected static final int CAMPAIGN_NEW_PRICES = 2559;
	public void init() {
		
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
		
		map.addAttribute("command", cmd);	
		return viewName;
	}
	
	protected void setSample(CriminalSearchFormCommand cmd) {
		cmd.setFirstname("John");
		cmd.setLastname("Smith");
		cmd.setCrmnlDobDay(15);
		cmd.setCrmnlDobMonth(5);
		cmd.setCrmnlDobYear(1950);
	}
	
	@RequestMapping(value = "/funnel/criminalSearch.do", method = RequestMethod.POST)
	public String processEvictionSubmitFull(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="nationwide",required=false) Boolean nationwide,
			@RequestParam(value="campaign",required=false) Integer campaignId,
			@ModelAttribute("command") CriminalSearchFormCommand crmnlsf,
			Errors errors,
			SessionStatus status) {
		
			// check campaign id here
				if (campaignId == null) { campaignId = CAMPAIGN_OLD_PRICES; }
				if (!(campaignId == CAMPAIGN_OLD_PRICES || campaignId == CAMPAIGN_NEW_PRICES)) 
				{
					return newvwError;
				}
				
		crmnlsf.setDob(crmnlsf.getCrmnlDobMonth()+"/"+crmnlsf.getCrmnlDobDay() +"/"+ crmnlsf.getCrmnlDobYear());
		return this.processSubmitProperty(session, map, crmnlsf, errors, status, vwDdnCriminalSearch);
	}
	
	
	
	private String processSubmitProperty(HttpSession session,
			ModelMap map,
			CriminalSearchFormCommand crmnlsf,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		
		
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
			
			if(crmnlsf.getState().equals("Natcrim"))
				crmnlsf.setPrice(new BigDecimal("14.95"));
			else
				crmnlsf.setPrice(new BigDecimal("9.95"));
			
			logger.info("setting ddnCriminal & continuing" + crmnlsf.getPrice());			
			session.removeAttribute("businessSearchFormCommand");
			session.removeAttribute("registerFormCommand");
			session.removeAttribute("bjlSearchFormCommand");
			session.removeAttribute("corpIndSearchFormCommand");
			session.removeAttribute("corpBusSearchFormCommand");
			session.removeAttribute("nationSearchFormCommand");
			session.removeAttribute("evictionSearchFormCommand");
			session.setAttribute("CriminalSearchFormCommand", crmnlsf);
			session.removeAttribute("RPAdresSearch");
			//session.setAttribute("RPNameSearch", "RPName");
			
			
			status.setComplete();
			if(crmnlsf.getNewacc() == true)
			{
				return newregisterRedir;
				
			}
			
			if(session.getAttribute("username") != null){
				return newconfirmSearchRedir;
			}
			map.addAttribute("searchType", "ddnCriminal");
			return redirLogin;
		}	
		}
	//search delay interim page
	@RequestMapping(value="/funnel/criminalSearchDelay.do")
	public String RealPropDelay(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
	{
		return "funnel/PropertySearchDelay";
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
		
	}
	
	public class CriminalSearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(CriminalSearchFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			CriminalSearchFormCommand cmd = (CriminalSearchFormCommand) target;
			
			validateStringEmptyMsg("firstname",  cmd.getFirstname(), errors,  2, 50, regexBasicSpace, "First Name", "Enter in First Name");
			validateStringEmptyMsg("lastname",  cmd.getLastname(), errors,  2, 50, regexBasicSpace, "First Name", "Enter in Last Name");
					
			//validateString("state",   cmd.getState(), errors,  2, 2, regexBasicNoSpace, "State");
			
			}		
	}
	
	
	
}