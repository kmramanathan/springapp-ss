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

public class NewEvictionBusinessSearchForm extends AbstractFunnelController {
	
	public void init() {
		
	}
	
		
	/*
	 * get campaign id, item id from request
	 * verify item for campaign
	 * set rate id (item) 
	 */
	@RequestMapping(value = "/funnel/eviction-records-business.do", method = RequestMethod.GET)
	public String setupEvictionFormCampaign(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample) {	
		// check campaign id here
		
		
		return setupFormEviction(session, map, test, sample,vwEvictionBusiness);		
	}

	/*
	 * modify this to use rate instead of nationwide
	 * keep list of rates which are nationwide?
	 */
	private String setupFormEviction(
			HttpSession session, ModelMap map, 
			Boolean test, Boolean sample, 
			String viewName) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		EvictionBusinessSearchFormCommand cmd = new EvictionBusinessSearchFormCommand();
		if (test || sample) { setSample(cmd); }
		cmd.setTest(test);
		map.addAttribute("command", cmd);	
		return viewName;
	}
	
	protected void setSample(EvictionBusinessSearchFormCommand cmd) {
		cmd.setBusinessname("John Smith");
		cmd.setState("CA");
		cmd.setCity("");
		cmd.setReference("");
		
	}
	

	
	@RequestMapping(value = "/funnel/eviction-records-business.do", method = RequestMethod.POST)
	public String processEvictionBusinessSubmitFull(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") EvictionBusinessSearchFormCommand ebfc,
			Errors errors,
			SessionStatus status) {
		return this.processSubmitEviction(session, map, ebfc, errors, status, vwEvictionBusiness);
	}
	
	
	
	private String processSubmitEviction(HttpSession session,
			ModelMap map,
			EvictionBusinessSearchFormCommand ebfc,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		
		
		EvictionBusinessSearchFormValidator v = new EvictionBusinessSearchFormValidator();
		v.validate(ebfc, errors);
		if (errors.hasErrors()) {
			return vwErrorReturn;
		} else {
			if(session.getAttribute("evictionBusinessSearchFormCommand") != null)
			{
				session.removeAttribute("evictionBusinessSearchFormCommand");
			}
			logger.info("setting ebfc & continuing");
			session.removeAttribute("registerFormCommand");
			session.removeAttribute("searchFormCommand");
			session.removeAttribute("bjlSearchFormCommand");
			session.removeAttribute("evictionSearchFormCommand");
			session.removeAttribute("corpIndSearchFormCommand");
			session.removeAttribute("corpBusSearchFormCommand");
			session.removeAttribute("nationSearchFormCommand");
			session.removeAttribute("aliasSearchFormCommand");
			status.setComplete();
			

			ebfc.setPrice(new BigDecimal("5"));
			session.setAttribute("evictionBusinessSearchFormCommand", ebfc);
			
			status.setComplete();
			}
			
		if(ebfc.getNewacc() == true)
		{
			return newregisterRedir;
			
		}
		if(session.getAttribute("username") != null){
				return newconfirmSearchRedir;
			}
			map.addAttribute("searchType", "evicBusiness");
			return redirLogin;
			
		}
	
	@RequestMapping(value="/funnel/EvictionSearchDelay.do")
	public String showCriminalDelay(HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap map,
			SessionStatus status)
	{
		
		return "funnel/EvictionSearchDelay";
	}
	public static class EvictionBusinessSearchFormCommand {
		// NOTE: fields need to be initialized to avoid type mismatch
		// errors in web form validation. this is necessary because we
		// use boxed types (which init as null) rather than primitives.
		// one way around this is to use String rather than Integer or
		// Boolean but then we have to deal with type conversions.
		
		// required fields
		private String businessname;
		private String state;
		private String city;
		private String reference;
		
	
		
		// deprecate these for rate
		// hmm. nationwide is probably useful so we don't have to check the
		// rate id all the time. price too		
	
		private BigDecimal price;

		private Boolean test = false;	
		private Boolean newacc=false;
		
		
		
		
		public Boolean getNewacc() {
			return newacc;
		}
		public void setNewacc(Boolean newacc) {
			this.newacc = newacc;
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
		public String getBusinessname() {
			return businessname;
		}
		public void setBusinessname(String businessname) {
			this.businessname = businessname;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getReference() {
			return reference;
		}
		public void setReference(String reference) {
			this.reference = reference;
		}
	
		
		
	}
	
	public class EvictionBusinessSearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(EvictionBusinessSearchFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			EvictionBusinessSearchFormCommand cmd = (EvictionBusinessSearchFormCommand) target;
			//vivek changes
			//validateString("bgcFirstName", cmd.getBgcFirstName(), errors,  3, 50, regexBasicSpace, "First Name");
			validateStringEmptyMsg("businessname",  cmd.getBusinessname(), errors,  2, 50, regexBasicSpace, "First Name", "Enter in Subject's Business Name");
			validateString("state",   cmd.getState(), errors,  2, 2, regexBasicNoSpace, "State");
			
			}		
	}
	
	
	
}