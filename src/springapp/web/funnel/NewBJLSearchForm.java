package springapp.web.funnel;

import java.math.BigDecimal;
import java.util.TreeMap;


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

public class NewBJLSearchForm extends AbstractFunnelController {
	public enum BJLSearchType { INDIVIDUALNAME, SSN, BUSINESSNAME };

	public void init() {
		super.init();
		populateBjlNameTypes();
		populateBjlCaseTypes();
	}
	
	@RequestMapping(value = "/funnel/newSearchBJL.do", method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="searchType",required=false) String searchType
			) {			
		return setupFormBJL(session, map, test, sample, searchType, newvwBJLSearch);
	}
	
	private String setupFormBJL(
			HttpSession session, ModelMap map, 
			Boolean test, Boolean sample, String searchType, 
			String viewName) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		BJLSearchFormCommand cmd = new BJLSearchFormCommand();
		if (test || sample) { setSample(cmd); }
		cmd.setTest(test);
		if(searchType == null){ searchType = "individualname"; }
		
		if (searchType.equals("individualname")) {
			cmd.setBjlsearchByType("individualname");
			cmd.setBjlSearchType(BJLSearchType.INDIVIDUALNAME);			
		}
		else if (searchType.equals("businessname")) {
			cmd.setBjlsearchByType("businessname");
			cmd.setBjlSearchType(BJLSearchType.BUSINESSNAME);
			
		}else if (searchType.equals("ssn")) {
			cmd.setBjlsearchByType("ssn");
			cmd.setBjlSearchType(BJLSearchType.SSN);
			
		} else {
			// default is name
			//cmd.setBjlSearchType(BJLSearchType.INDIVIDUALNAME);	
			cmd.setBjlsearchByType("individualname");
		}
		
		setSearchTypeAttribute(cmd, map);
		map.addAttribute("command", cmd);	
		
		return viewName;
	}
	
	protected void setSample(BJLSearchFormCommand cmd) {
	    cmd.setBjllastname("Smith");
	    cmd.setBjlfirstname("John");
	    cmd.setBjlmiddlename("LA");
		cmd.setBjlNameType("Any");
		cmd.setBjlstate("CA");
		cmd.setBjlNameType("Any");
		cmd.setBjlCaseType("Any");
	}
	
	protected void setSearchTypeAttribute(BJLSearchFormCommand cmd, ModelMap map) {
		switch (cmd.getBjlSearchType()) {
		case INDIVIDUALNAME:
			map.addAttribute("searchType", "individualname");
			break;
		case BUSINESSNAME:
			map.addAttribute("searchType","businessname");
			break;
		case SSN:
			map.addAttribute("searchType", "ssn");
			break;
		}
	}
	
	@RequestMapping(value = "/funnel/newSearchBJL.do", method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") BJLSearchFormCommand sfc,
			Errors errors,
			SessionStatus status) {
		return this.processSubmitBJL(session, map, sfc, errors, status, newvwBJLSearch);
	}	
	
	private String processSubmitBJL(HttpSession session,
			ModelMap map,
			BJLSearchFormCommand sfc,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		BJLSearchFormValidator v = new BJLSearchFormValidator();
		v.validate(sfc, errors);
		if (errors.hasErrors()) {
			setSearchTypeAttribute(sfc, map);
			return vwErrorReturn;
		} 
		else 
		{
			if(session.getAttribute("bjlSearchFormCommand") != null)
			{ session.removeAttribute("bjlSearchFormCommand"); }
			logger.info("setting bjl sfc & continuing");
			session.removeAttribute("registerFormCommand");
			session.removeAttribute("searchFormCommand");
			session.removeAttribute("evictionSearchFormCommand");
			session.removeAttribute("evictionBusinessSearchFormCommand");
			session.removeAttribute("corpIndSearchFormCommand");
			session.removeAttribute("corpBusSearchFormCommand");
			session.removeAttribute("nationSearchFormCommand");
			//Setting price 
			sfc.setPrice(new BigDecimal("5"));
			
			
			session.setAttribute("bjlSearchFormCommand", sfc);	
			status.setComplete();
			if(sfc.getNewacc() == true)
			{
				return newregisterRedir;
				
			}
			if(session.getAttribute("username") != null){
				return newconfirmSearchRedir;
			}
			logger.info("businessname:"+sfc.getBjlBusinessName());
			map.addAttribute("searchType", "bjl");
			return redirLogin;
		}
	}
	
	//XXX Fix this
	@RequestMapping(value = "/funnel/newBJLsearchDelay.do")
	public String processDelaySubmit(
			HttpSession session, HttpServletRequest request, 
			HttpServletResponse response, ModelMap map,
			SessionStatus status) {
	        
		 return "funnel/NewBJLsearchDelay";
	}
	
	public static class BJLSearchFormCommand {
		// NOTE: fields need to be initialized to avoid type mismatch
		// errors in web form validation. this is necessary because we
		// use boxed types (which init as null) rather than primitives.
		// one way around this is to use String rather than Integer or
		// Boolean but then we have to deal with type conversions.
		
		// required fields
		
		private String bjlNameType;		
		private String bjlCaseType;
		
		
		
		private String bjllastname;
		private String bjlfirstname;
		private String bjlmiddlename;
		private String bjlstate;
		private String bjlssn;
		private String bjlssntype;
		private String bjlfiling_state;
		private String bjlsearchByType;
		private String bjlBusinessName;
		private String bjlReference;
		private BJLSearchType bjlSearchType;
		private Boolean test = false;	
		private BigDecimal price;
		private Boolean newacc=false;
		
		
		
		public Boolean getNewacc() {
			return newacc;
		}
		public void setNewacc(Boolean newacc) {
			this.newacc = newacc;
		}
		public BJLSearchType getBjlSearchType() {
			return bjlSearchType;
		}
		public void setBjlSearchType(BJLSearchType bjlSearchType) {
			this.bjlSearchType = bjlSearchType;
		}
		
		public String getBjlNameType() {
			return bjlNameType;
		}
		public void setBjlNameType(String bjlNameType) {
			this.bjlNameType = bjlNameType;
		}
		public String getBjlCaseType() {
			return bjlCaseType;
		}
		public void setBjlCaseType(String bjlCaseType) {
			this.bjlCaseType = bjlCaseType;
		}
		
		public Boolean getTest() {
			return test;
		}
		public void setTest(Boolean test) {
			this.test = test;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public String getBjllastname() {
			return bjllastname;
		}
		public void setBjllastname(String bjllastname) {
			this.bjllastname = bjllastname;
		}
		public String getBjlfirstname() {
			return bjlfirstname;
		}
		public void setBjlfirstname(String bjlfirstname) {
			this.bjlfirstname = bjlfirstname;
		}
		public String getBjlmiddlename() {
			return bjlmiddlename;
		}
		public void setBjlmiddlename(String bjlmiddlename) {
			this.bjlmiddlename = bjlmiddlename;
		}
		public String getBjlstate() {
			return bjlstate;
		}
		public void setBjlstate(String bjlstate) {
			this.bjlstate = bjlstate;
		}
		public String getBjlssn() {
			return bjlssn;
		}
		public void setBjlssn(String bjlssn) {
			this.bjlssn = bjlssn;
		}
		
		public String getBjlssntype() {
			return bjlssntype;
		}
		public void setBjlssntype(String bjlssntype) {
			this.bjlssntype = bjlssntype;
		}
		public String getBjlfiling_state() {
			return bjlfiling_state;
		}
		public void setBjlfiling_state(String bjlfiling_state) {
			this.bjlfiling_state = bjlfiling_state;
		}
		public String getBjlsearchByType() {
			return bjlsearchByType;
		}
		public void setBjlsearchByType(String bjlsearchByType) {
			this.bjlsearchByType = bjlsearchByType;
		}
		
		public String getBjlBusinessName() {
			return bjlBusinessName;
		}
		public void setBjlBusinessName(String bjlBusinessName) {
			this.bjlBusinessName = bjlBusinessName;
		}
		public String getBjlReference() {
			return bjlReference;
		}
		public void setBjlReference(String bjlReference) {
			this.bjlReference = bjlReference;
		}
		
		
		
	}
	
	public class BJLSearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(BJLSearchFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			BJLSearchFormCommand cmd = (BJLSearchFormCommand) target;

			switch (cmd.getBjlSearchType()) {
			case INDIVIDUALNAME:
				//vivek changes
				validateStringEmptyMsg("bjlfirstname", cmd.getBjlfirstname(), errors,  2, 50, regexBasicSpace, "Name","Enter in Subject's First Name");
				validateStringEmptyMsg("bjllastname",  cmd.getBjllastname(), errors,  2, 50, regexBasicSpace, "Name", "Enter in Subject's Last Name");
				break;
			case BUSINESSNAME:
				validateStringEmptyMsg("bjlBusinessName", cmd.getBjlBusinessName(), errors, 2, 50, regexBasicSpace, "Business Name", "Enter in Business Name");
				break;
			case SSN:
				validateString("bjlssn", cmd.getBjlssn(), errors, 9, 18, regexNumeric, "SSN / Tax Id");
				break;
			}
		}		
	}
	
	// XXX fix this, too keyed to crim search
	/*
	@RequestMapping("/funnel/searchAgainBJL.do")
	public String reset(HttpServletRequest request, HttpSession session) {		
		session.removeAttribute("searchFormCommand");
		session.removeAttribute("offenders");
		session.removeAttribute("offendersCount");
		String nationwide = request.getParameter("nationwide");
		if (nationwide == null) {
			nationwide = "0";
		}
		String redir = searchRedir + "?nationwide=" + nationwide;
		return redir;
	}
	*/

	protected static final TreeMap<String,String> bjlNameTypes = new TreeMap<String,String>();
	protected static final TreeMap<String,String> bjlCaseTypes = new TreeMap<String,String>();
    
	protected void populateBjlNameTypes() {
		bjlNameTypes.put("Any", "Any");
		bjlNameTypes.put("Defendant", "Defendant");
		bjlNameTypes.put("Plaintiff", "Plaintiff");
		bjlNameTypes.put("Attorney", "Attorney");
	}
	protected void populateBjlCaseTypes() {
		bjlCaseTypes.put("Any", "Any");
		bjlCaseTypes.put("Bankruptcy", "Bankruptcy");
		bjlCaseTypes.put("Judgment", "Judgment");
		bjlCaseTypes.put("Tax Lien", "Tax Lien");		
	}

	@ModelAttribute("bjlNameTypes")
	public TreeMap<String,String> getNameTypes() {
		return bjlNameTypes;
	}
	@ModelAttribute("bjlCaseTypes")
	public TreeMap<String,String> getCaseTypes() {
		return bjlCaseTypes;
	}
	
	
}