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

public class PropertyAddressSearchForm extends AbstractFunnelController {
	
	public void init() {
		
	}
	
	
	/*
	 * get campaign id, item id from request
	 * verify item for campaign
	 * set rate id (item) 
	 */
	@RequestMapping(value = "/funnel/realpropAddressSearch.do", method = RequestMethod.GET)
	public String setupPropertyFormCampaign(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample) {	
		// check campaign id here
		
		
		return setupPropertyForm(session, map, test, sample,vwRealPropAddress);		
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
		RealPropAddressSearchFormCommand cmd = new RealPropAddressSearchFormCommand();
		if (test || sample) { setSample(cmd); }
		cmd.setTest(test);
		map.addAttribute("command", cmd);	
		return viewName;
	}
	
	protected void setSample(RealPropAddressSearchFormCommand cmd) {
		cmd.setAppartnum("2801");
		cmd.setStreet("Redwood");
		cmd.setCity("ANCHORAGE");
		cmd.setState("AK");
		cmd.setReference("");
		
	}
		
	@RequestMapping(value = "/funnel/realpropAddressSearch.do", method = RequestMethod.POST)
	public String processPropertyAddressSubmitFull(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") RealPropAddressSearchFormCommand rpafc,
			Errors errors,
			SessionStatus status) 
	{
		return this.processSubmitProperty(session, map, rpafc, errors, status, vwRealPropAddress);
	}
		
	private String processSubmitProperty(HttpSession session,
			ModelMap map,
			RealPropAddressSearchFormCommand rpafc,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		
		
		EvictionBusinessSearchFormValidator v = new EvictionBusinessSearchFormValidator();
		v.validate(rpafc, errors);
		if (errors.hasErrors()) {
			return vwErrorReturn;
		} else {
			if(session.getAttribute("RealPropAddressSearchFormCommand") != null)
			{
				session.removeAttribute("RealPropAddressSearchFormCommand");
			}
			logger.info("setting rpafc & continuing");
			session.removeAttribute("registerFormCommand");
			session.removeAttribute("searchFormCommand");
			session.removeAttribute("bjlSearchFormCommand");
			session.removeAttribute("evictionSearchFormCommand");
			session.removeAttribute("corpIndSearchFormCommand");
			session.removeAttribute("corpBusSearchFormCommand");
			session.removeAttribute("nationSearchFormCommand");
			session.removeAttribute("RealPropSearchFormCommand");
			session.removeAttribute("RPNameSearch");
			session.setAttribute("RPAdresSearch", "RPAddress");
			status.setComplete();
			

			rpafc.setPrice(new BigDecimal("5"));
			session.setAttribute("RealPropAddressSearchFormCommand", rpafc);
			
			status.setComplete();
			}
			
		if(rpafc.getNewacc() == true)
		{
			return newregisterRedir;
			
		}
		if(session.getAttribute("username") != null){
			
			map.addAttribute("searchType", "realpropAddress");
			return newconfirmSearchRedir;
		}
		map.addAttribute("searchType", "realpropAddress");
			return redirLogin;			
		}
	
	@RequestMapping(value="/funnel/PropertySearchDelay.do")
	public String showCriminalDelay(HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap map,
			SessionStatus status)
	{
		
		return "funnel/PropertySearchDelay";
	}
	
	public static class RealPropAddressSearchFormCommand {
		// NOTE: fields need to be initialized to avoid type mismatch
		// errors in web form validation. this is necessary because we
		// use boxed types (which init as null) rather than primitives.
		// one way around this is to use String rather than Integer or
		// Boolean but then we have to deal with type conversions.
		private String firstname;
		private String lastname;
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
	
	public class EvictionBusinessSearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(RealPropAddressSearchFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			RealPropAddressSearchFormCommand cmd = (RealPropAddressSearchFormCommand) target;
			validateStringEmptyMsg("appartnum",  cmd.getAppartnum(), errors,  2, 50, regexBasicSpace, "House Number", "Enter in House Number");
			validateStringEmptyMsg("street",  cmd.getStreet(), errors,  2, 50, regexBasicSpace, "Street", "Enter in Street");
			validateStringEmptyMsg("city",  cmd.getCity(), errors,  2, 50, regexBasicSpace, "City", "Enter in City");
			validateString("state",   cmd.getState(), errors,  2, 2, regexBasicNoSpace, "State");
			
			}			
	}
	
	
	
}