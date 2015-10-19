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

public class NewCorpBusSearchForm extends AbstractFunnelController {
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
	@RequestMapping(value = "/funnel/corp-bus-search.do", method = RequestMethod.GET)
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
		
		return setupFormIndCorp(session, map, test, sample, nationwide, corpBusView, campaignId);		
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
		CorpBusSearchFormCommand cmd = new CorpBusSearchFormCommand();
		if (test || sample) { setSample(cmd); }
		cmd.setTest(test);
		cmd.setNationwideSearch(nationwide);
		cmd.setCampaignId(campaignId);
		map.addAttribute("command", cmd);	
		return viewName;
	}
	
	protected void setSample(CorpBusSearchFormCommand cmd) {
		
		cmd.setBusinessname("Smith");
		cmd.setState("CA");
		cmd.setReference("");
		cmd.setNationwideSearch(true);
		
	}
	

	
	@RequestMapping(value = "/funnel/corp-bus-search.do", method = RequestMethod.POST)
	public String processCorpSubmitFull(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") CorpBusSearchFormCommand cisfc,
			Errors errors,
			SessionStatus status) {
		return this.processSubmitCorp(session, map, cisfc, errors, status, corpBusView);
	}
	
	
	
	private String processSubmitCorp(HttpSession session,
			ModelMap map,
			CorpBusSearchFormCommand cbsfc,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		
		if(cbsfc.getState().equalsIgnoreCase("all"))
		
			cbsfc.setNationwideSearch(true);
		
		
		CorpBusSearchFormValidator v = new CorpBusSearchFormValidator();
		v.validate(cbsfc, errors);
		if (errors.hasErrors()) {
			return vwErrorReturn;
		} else {
			Integer campaignId= cbsfc.getCampaignId();
			switch (campaignId) {
			case CAMPAIGN_OLD_PRICES:
				if(cbsfc.getNationwideSearch())
				{
					cbsfc.setPrice(new BigDecimal("7.00"));
				}
				else {
					cbsfc.setPrice(new BigDecimal("4.00"));
				}
				
				break;

			case CAMPAIGN_NEW_PRICES:
				if(cbsfc.getNationwideSearch())
				{
					cbsfc.setPrice(new BigDecimal("7.00"));
				}
				else {
					cbsfc.setPrice(new BigDecimal("4.00"));
				}
				break;
			}
			if(session.getAttribute("corpBusSearchFormCommand") != null)
			{
				session.removeAttribute("corpBusSearchFormCommand");
			}
			
			logger.info("setting cibfc & continuing");

			session.removeAttribute("searchFormCommand");
			session.removeAttribute("businessSearchFormCommand");
			session.removeAttribute("evictionBusinessSearchFormCommand");
			session.removeAttribute("registerFormCommand");
			session.removeAttribute("bjlSearchFormCommand");
			session.removeAttribute("evictionSearchFormCommand");
			session.removeAttribute("corpIndSearchFormCommand");
			session.removeAttribute("nationSearchFormCommand");
			session.setAttribute("corpBusSearchFormCommand", cbsfc);
			status.setComplete();
			if(cbsfc.getNewacc() == true)
			{
				return newregisterRedir;
				
			}
			
			if(session.getAttribute("username") != null){
				return newconfirmSearchRedir;
			}
			map.addAttribute("searchType", "corpbus");
			return redirLogin;
		}	
		}
	//search delay interim page
	@RequestMapping(value="/funnel/newCorpBusSearchDelay.do")
	public String CorpDelay(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
	{
		return corpDelayView;
	}
	

	public static class CorpBusSearchFormCommand {
		// NOTE: fields need to be initialized to avoid type mismatch
		// errors in web form validation. this is necessary because we
		// use boxed types (which init as null) rather than primitives.
		// one way around this is to use String rather than Integer or
		// Boolean but then we have to deal with type conversions.
		
		// required fields
		private String businessname;
		
		private String state;
		
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
	
		
		public String getBusinessname() {
			return businessname;
		}
		public void setBusinessname(String businessname) {
			this.businessname = businessname;
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
	
	public class CorpBusSearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(CorpBusSearchFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			CorpBusSearchFormCommand cmd = (CorpBusSearchFormCommand) target;
			//vivek changes
			//validateString("bgcFirstName", cmd.getBgcFirstName(), errors,  3, 50, regexBasicSpace, "First Name");
			
			validateStringEmptyMsg("businessname",  cmd.getBusinessname(), errors,  2, 50, regexBasicSpace, "First Name", "Enter in Subject's Business Name");
			
			
			if(cmd.getNationwideSearch() == false)
			{
			validateString("state",   cmd.getState(), errors,  2, 2, regexBasicNoSpace, "State");
			}
			
			}		
	}
	
	
	
}