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


@Controller
@SessionAttributes({"command","nationwideSearch"})

public class DdnNewCrimSearchForm extends AbstractFunnelController {
	
	public void init() {
		
	}
	
		
	/*
	 * get campaign id, item id from request
	 * verify item for campaign
	 * set rate id (item) 
	 */
	// evicReal Property normal searches 
	@RequestMapping(value = "/funnel/ddncrimSearch.do", method = RequestMethod.GET)
	public String setupEvictionFormCampaign(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample) {	
		// check campaign id here
				
		return setupPropertyForm(session, map, test, sample,vwDdnNewCrimSearch);		
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
		CriminalSearchFormCommand cmd = new CriminalSearchFormCommand();
		if (test || sample) { setSample(cmd); }
		cmd.setTest(test);
		map.addAttribute("command", cmd);	
		return viewName;
	}
	
	protected void setSample(CriminalSearchFormCommand cmd) {
		cmd.setFirstname("John");
		cmd.setLastname("Smith");
		cmd.setState("CA");
	}
	
	
	@RequestMapping(value = "/funnel/ddncrimSearch.do", method = RequestMethod.POST)
	public String processEvictionSubmitFull(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") CriminalSearchFormCommand rpfc,
			Errors errors,
			SessionStatus status) {
		return this.processSubmitProperty(session, map, rpfc, errors, status, vwDdnNewCrimSearch);
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
			
			logger.info("setting ddnCriminal & continuing");			
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
	@RequestMapping(value="/funnel/DdnNewCrimSearchDelay.do")
	public String RealPropDelay(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
	{
		return "funnel/DdnNewCrimSearchDelay";
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