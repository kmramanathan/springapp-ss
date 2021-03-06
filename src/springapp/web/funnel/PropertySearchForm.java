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

public class PropertySearchForm extends AbstractFunnelController {
	
	public void init() {
		
	}
	
		
	/*
	 * get campaign id, item id from request
	 * verify item for campaign
	 * set rate id (item) 
	 */
	// evicReal Property normal searches 
	@RequestMapping(value = "/funnel/realpropSearch.do", method = RequestMethod.GET)
	public String setupEvictionFormCampaign(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample) {	
		// check campaign id here
				
		return setupPropertyForm(session, map, test, sample,vwRealPropSearch);		
	}	

	/*
	 * modify this to use rate instead of nationwide
	 * keep list of rates which are nationwide?
	 */
	
	private String setupPropertyForm(
			HttpSession session, ModelMap map, 
			Boolean test, Boolean sample, 
			String viewName) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		RealPropSearchFormCommand cmd = new RealPropSearchFormCommand();
		if (test || sample) { setSample(cmd); }
		cmd.setTest(test);
		map.addAttribute("command", cmd);	
		return viewName;
	}
	
	protected void setSample(RealPropSearchFormCommand cmd) {
		cmd.setFirstname("John");
		cmd.setLastname("Smith");
		cmd.setState("CA");
		cmd.setCity("");
		cmd.setReference("");
		
	}
	

	
	@RequestMapping(value = "/funnel/realpropSearch.do", method = RequestMethod.POST)
	public String processEvictionSubmitFull(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") RealPropSearchFormCommand rpfc,
			Errors errors,
			SessionStatus status) {
		return this.processSubmitProperty(session, map, rpfc, errors, status, vwRealPropSearch);
	}
	
	
	
	private String processSubmitProperty(HttpSession session,
			ModelMap map,
			RealPropSearchFormCommand rpsf,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		
		
		PropertySearchFormValidator v = new PropertySearchFormValidator();
		v.validate(rpsf, errors);
		if (errors.hasErrors()) {
			return vwErrorReturn;
		} else {
			if(session.getAttribute("RealPropSearchFormCommand") != null)
			{
				session.removeAttribute("RealPropSearchFormCommand");
			}
			
			logger.info("setting RelProp & continuing");

			rpsf.setPrice(new BigDecimal("5"));
			session.removeAttribute("businessSearchFormCommand");
			session.removeAttribute("registerFormCommand");
			session.removeAttribute("bjlSearchFormCommand");
			session.removeAttribute("corpIndSearchFormCommand");
			session.removeAttribute("corpBusSearchFormCommand");
			session.removeAttribute("nationSearchFormCommand");
			session.removeAttribute("evictionSearchFormCommand");
			session.setAttribute("RealPropSearchFormCommand", rpsf);
			session.removeAttribute("RPAdresSearch");
			session.setAttribute("RPNameSearch", "RPName");
			status.setComplete();
			if(rpsf.getNewacc() == true)
			{
				return newregisterRedir;
				
			}
			
			if(session.getAttribute("username") != null){
				return newconfirmSearchRedir;
			}
			map.addAttribute("searchType", "realprop");
			return redirLogin;
		}	
		}
	//search delay interim page
	@RequestMapping(value="/funnel/newPropertySearchDelay.do")
	public String RealPropDelay(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
	{
		return "funnel/PropertySearchDelay";
	}
	

	public static class RealPropSearchFormCommand {
		// NOTE: fields need to be initialized to avoid type mismatch
		// errors in web form validation. this is necessary because we
		// use boxed types (which init as null) rather than primitives.
		// one way around this is to use String rather than Integer or
		// Boolean but then we have to deal with type conversions.
		
		// required fields
		private String firstname;
		private String lastname;
		private String middlename;
		private String appartnum;
		private String street;
		private String city;
		private String state;
		private String reference;
		private Boolean test = false;	
		private Boolean newacc=false;
		
		// deprecate these for rate
		// hmm. nationwide is probably useful so we don't have to check the
		// rate id all the time. price too		
		private BigDecimal price;
		
		
		

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
		public String getMiddlename() {
			return middlename;
		}
		public void setMiddlename(String middlename) {
			this.middlename = middlename;
		}
		public String getAppartnum() {
			return appartnum;
		}
		public void setAppartnum(String appartnum) {
			this.appartnum = appartnum;
		}		
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
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
		
		
		public String getReference() {
			return reference;
		}
		public void setReference(String reference) {
			this.reference = reference;
		}
	
		
		
	}
	
	public class PropertySearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(RealPropSearchFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			RealPropSearchFormCommand cmd = (RealPropSearchFormCommand) target;
			
			validateStringEmptyMsg("firstname",  cmd.getFirstname(), errors,  2, 50, regexBasicSpace, "First Name", "Enter in First Name");
			validateStringEmptyMsg("lastname",  cmd.getLastname(), errors,  2, 50, regexBasicSpace, "First Name", "Enter in Last Name");
			
			
			validateString("state",   cmd.getState(), errors,  2, 2, regexBasicNoSpace, "State");
			
			}		
	}
	
	
	
}