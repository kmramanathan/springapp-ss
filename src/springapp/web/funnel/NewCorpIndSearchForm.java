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

public class NewCorpIndSearchForm extends AbstractFunnelController {
	protected static final int CAMPAIGN_OLD_PRICES = 1018;
	protected static final int CAMPAIGN_NEW_PRICES = 2559;
	public void init() {
		
	}
	
		
	/*
	 * get campaign id, item id from request
	 * verify item for campaign
	 * set rate id (item) 
	 */
	// eviction normal searches 
	@RequestMapping(value = "/funnel/corp-ind-search.do", method = RequestMethod.GET)
	public String setupCorpIndFormCampaign(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="nationwide",required=false) Boolean nationwide,
			@RequestParam(value="campaign",required=false) Integer campaignId) {	
		// check campaign id here
		if(campaignId == null){campaignId = CAMPAIGN_OLD_PRICES;}
		if (!(campaignId == CAMPAIGN_OLD_PRICES || campaignId == CAMPAIGN_NEW_PRICES)) {
			return newvwError;
		}
		
		return setupFormIndCorp(session, map, test, sample, nationwide, corpIndView, campaignId);		
	}

	/*
	 * modify this to use rate instead of nationwide
	 * keep list of rates which are nationwide?
	 */
	private String setupFormIndCorp(
			HttpSession session, ModelMap map, 
			Boolean test, Boolean sample, Boolean nationwide, 
			String viewName, Integer campaignId) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		if (nationwide == null){nationwide = false;}
		CorpIndSearchFormCommand cmd = new CorpIndSearchFormCommand();
		if (test || sample) { setSample(cmd); }
		cmd.setTest(test);
		cmd.setNationwideSearch(nationwide);
		cmd.setCampaignId(campaignId);
		map.addAttribute("command", cmd);	
		return viewName;
	}
	
	protected void setSample(CorpIndSearchFormCommand cmd) {
		cmd.setFirstname("John");
		cmd.setLastname("Smith");
		cmd.setState("CA");
		cmd.setMiddleinitial("");
		cmd.setReference("");
		cmd.setNationwideSearch(true);
		
	}
	

	
	@RequestMapping(value = "/funnel/corp-ind-search.do", method = RequestMethod.POST)
	public String processCorpSubmitFull(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") CorpIndSearchFormCommand cisfc,
			Errors errors,
			SessionStatus status) {
		return this.processSubmitCorp(session, map, cisfc, errors, status, corpIndView);
	}
	
	
	
	private String processSubmitCorp(HttpSession session,
			ModelMap map,
			CorpIndSearchFormCommand cisfc,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		
		if(cisfc.getState().equalsIgnoreCase("all"))
		
			cisfc.setNationwideSearch(true);
		
		
		CorpIndSearchFormValidator v = new CorpIndSearchFormValidator();
		v.validate(cisfc, errors);
		if (errors.hasErrors()) {
			return vwErrorReturn;
		} else {
			Integer campaignId= cisfc.getCampaignId();
			switch (campaignId) {
			case CAMPAIGN_OLD_PRICES:
				if(cisfc.getNationwideSearch())
				{
					cisfc.setPrice(new BigDecimal("7.00"));
				}
				else {
					cisfc.setPrice(new BigDecimal("4.00"));
				}
				
				break;

			case CAMPAIGN_NEW_PRICES:
				if(cisfc.getNationwideSearch())
				{
					cisfc.setPrice(new BigDecimal("7.00"));
				}
				else {
					cisfc.setPrice(new BigDecimal("4.00"));
				}
				break;
			}
			if(session.getAttribute("corpIndSearchFormCommand") != null)
			{
				session.removeAttribute("corpIndSearchFormCommand");
			}
			
			logger.info("setting cibfc & continuing");
			

			session.setAttribute("corpIndSearchFormCommand", cisfc);
			
			
			session.removeAttribute("evictionBusinessSearchFormCommand");
			session.removeAttribute("registerFormCommand");
			session.removeAttribute("bjlSearchFormCommand");
			session.removeAttribute("evictionSearchFormCommand");
			session.removeAttribute("evictionBusinessSearchFormCommand");
			session.removeAttribute("corpBusSearchFormCommand");
			session.removeAttribute("searchFormCommand");
			session.removeAttribute("nationSearchFormCommand");
			
			status.setComplete();
			if(cisfc.getNewacc() == true)
			{
				return newregisterRedir;
				
			}
			
			if(session.getAttribute("username") != null){
				return newconfirmSearchRedir;
			}
			map.addAttribute("searchType", "corporation");
			return redirLogin;
		}	
		}
	//search delay interim page
	@RequestMapping(value="/funnel/newCorpIndSearchDelay.do")
	public String CorpDelay(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
	{
		return corpDelayView;
	}
	

	public static class CorpIndSearchFormCommand {
		// NOTE: fields need to be initialized to avoid type mismatch
		// errors in web form validation. this is necessary because we
		// use boxed types (which init as null) rather than primitives.
		// one way around this is to use String rather than Integer or
		// Boolean but then we have to deal with type conversions.
		
		// required fields
		private String firstname;
		private String lastname;
		private String state;
		private String middleinitial;
		private String reference;
		private Boolean test = false;	
		private Boolean newacc=false;
		private Boolean nationwideSearch = true;
		// deprecate these for rate
		// hmm. nationwide is probably useful so we don't have to check the
		// rate id all the time. price too		
		private BigDecimal price;
		// this is not displayed on the form, customer could modify it
		private Integer campaignId;
		private Integer rateId;
		
		
		
		public Boolean getNationwideSearch() {
			return nationwideSearch;
		}
		public void setNationwideSearch(Boolean nationwideSearch) {
			this.nationwideSearch = nationwideSearch;
		}
		public Boolean getNewacc() {
			return newacc;
		}
		public void setNewacc(Boolean newacc) {
			this.newacc = newacc;
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
		
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
		public String getMiddleinitial() {
			return middleinitial;
		}
		public void setMiddleinitial(String middleinitial) {
			this.middleinitial = middleinitial;
		}
		public String getReference() {
			return reference;
		}
		public void setReference(String reference) {
			this.reference = reference;
		}
		public Integer getCampaignId() {
			return campaignId;
		}
		public void setCampaignId(Integer campaignId) {
			this.campaignId = campaignId;
		}
		public Integer getRateId() {
			return rateId;
		}
		public void setRateId(Integer rateId) {
			this.rateId = rateId;
		}
	
		
		
	}
	
	public class CorpIndSearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(CorpIndSearchFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			CorpIndSearchFormCommand cmd = (CorpIndSearchFormCommand) target;
			//vivek changes
			//validateString("bgcFirstName", cmd.getBgcFirstName(), errors,  3, 50, regexBasicSpace, "First Name");
			
			validateStringEmptyMsg("firstname",  cmd.getFirstname(), errors,  2, 50, regexBasicSpace, "First Name", "Enter in Subject's First Name");
			validateStringEmptyMsg("lastname",  cmd.getLastname(), errors,  2, 50, regexBasicSpace, "First Name", "Enter in Subject's Last Name");
			
			if(cmd.getNationwideSearch() == false)
			{
			validateString("state",   cmd.getState(), errors,  2, 2, regexBasicNoSpace, "State");
			}
			
			}		
	}
	
	
	
}