package springapp.web.funnel;

import java.math.BigDecimal;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
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

public class BJLSearchForm extends AbstractFunnelController {
	public enum BJLSearchType { NAME, ADDRESS, SSN };

	public void init() {
		super.init();
		populateBjlNameTypes();
		populateBjlCaseTypes();
	}
	
	@RequestMapping(value = "/funnel/searchBJL-old.do", method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="searchType",required=false) String searchType
			) {			
		return setupFormBJL(session, map, test, sample, searchType, vwBJLSearch);
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
		if(searchType == null){ searchType = "name"; }
		
		if (searchType.equals("name")) {
			cmd.setBjlSearchType(BJLSearchType.NAME);			
		} else if (searchType.equals("address")) {
			cmd.setBjlSearchType(BJLSearchType.ADDRESS);		
		} else if (searchType.equals("ssn")) {
			cmd.setBjlSearchType(BJLSearchType.SSN);		
		} else {
			// default is name
			cmd.setBjlSearchType(BJLSearchType.NAME);		
		}
		
		setSearchTypeAttribute(cmd, map);
		map.addAttribute("command", cmd);	
		
		return viewName;
	}
	
	protected void setSample(BJLSearchFormCommand cmd) {
		cmd.setBjlName("Smith John");
		cmd.setBjlNameType("Any");
		cmd.setBjlDefendantCity("California");
		cmd.setBjlDefendantState("CA");
		cmd.setBjlDefendantZip("10001");
		cmd.setBjlNameType("Any");
		cmd.setBjlCaseType("Any");
	}
	
	protected void setSearchTypeAttribute(BJLSearchFormCommand cmd, ModelMap map) {
		switch (cmd.getBjlSearchType()) {
		case NAME:
			map.addAttribute("searchType", "name");
			break;
		case ADDRESS:
			map.addAttribute("searchType", "address");
			break;
		case SSN:
			map.addAttribute("searchType", "ssn");
			break;
		}
	}
	
	@RequestMapping(value = "/funnel/searchBJL-old.do", method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") BJLSearchFormCommand sfc,
			Errors errors,
			SessionStatus status) {
		return this.processSubmitBJL(session, map, sfc, errors, status, vwBJLSearch);
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
		} else {
			if(session.getAttribute("bjlSearchFormCommand") != null){ session.removeAttribute("bjlSearchFormCommand"); }
			logger.info("setting bjl sfc & continuing");
			sfc.setPrice(new BigDecimal("8"));
			
			session.removeAttribute("signupFormCommand");
			session.removeAttribute("searchFormCommand");
			
			session.setAttribute("bjlSearchFormCommand", sfc);	
			status.setComplete();		
			return BJLValuProp;
		}
	}
	
	//XXX Fix this
	@RequestMapping(value = "/funnel/BJLsearchDelay.do")
	public String processDelaySubmit(
			HttpSession session, HttpServletRequest request, 
			HttpServletResponse response, ModelMap map,
			SessionStatus status) {
	        
		 return "funnel/BJLsearchDelay";
	}
	
	public static class BJLSearchFormCommand {
		// NOTE: fields need to be initialized to avoid type mismatch
		// errors in web form validation. this is necessary because we
		// use boxed types (which init as null) rather than primitives.
		// one way around this is to use String rather than Integer or
		// Boolean but then we have to deal with type conversions.
		
		// required fields
		private String bjlName;
		private String bjlNameType;		
		private String bjlCaseType;
		private String bjlSsnTaxId;
		private String bjlDefendantCity;
		private String bjlDefendantState;
		private String bjlDefendantZip;

		private BJLSearchType bjlSearchType;
		private Boolean test = false;	
		private BigDecimal price;
		
		public BJLSearchType getBjlSearchType() {
			return bjlSearchType;
		}
		public void setBjlSearchType(BJLSearchType bjlSearchType) {
			this.bjlSearchType = bjlSearchType;
		}
		public String getBjlName() {
			return bjlName;
		}
		public void setBjlName(String bjlName) {
			this.bjlName = bjlName;
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
		public String getBjlSsnTaxId() {
			return bjlSsnTaxId;
		}
		public void setBjlSsnTaxId(String bjlSsnTaxId) {
			this.bjlSsnTaxId = bjlSsnTaxId;
		}
		public String getBjlDefendantCity() {
			return bjlDefendantCity;
		}
		public void setBjlDefendantCity(String bjlDefendantCity) {
			this.bjlDefendantCity = bjlDefendantCity;
		}
		public String getBjlDefendantState() {
			return bjlDefendantState;
		}
		public void setBjlDefendantState(String bjlDefendantState) {
			this.bjlDefendantState = bjlDefendantState;
		}
		public String getBjlDefendantZip() {
			return bjlDefendantZip;
		}
		public void setBjlDefendantZip(String bjlDefendantZip) {
			this.bjlDefendantZip = bjlDefendantZip;
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
	}
	
	public class BJLSearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(BJLSearchFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			BJLSearchFormCommand cmd = (BJLSearchFormCommand) target;

			switch (cmd.getBjlSearchType()) {
			case NAME:
				validateString("bjlName", cmd.getBjlName(), errors,  3, 50, regexBasicSpace, "Name");
				break;
			case ADDRESS:
				validateString("bjlName", cmd.getBjlName(), errors,  3, 50, regexBasicSpace, "Name");
				validateStringEmptyOk("bjlDefendantCity",  cmd.getBjlDefendantCity(),  errors,  3, 50, regexBasicSpace, "City");
				validateStringEmptyOk("bjlDefendantZip",   cmd.getBjlDefendantZip(),   errors,  5, 10, regexNumeric, "ZIP Code");
				break;
			case SSN:
				validateString("bjlSsnTaxId", cmd.getBjlSsnTaxId(), errors, 9, 18, regexNumeric, "SSN / Tax Id");
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