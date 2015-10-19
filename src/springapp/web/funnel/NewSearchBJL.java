package springapp.web.funnel;

import java.math.BigDecimal;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;



@Controller
@SessionAttributes({"command","nationwideSearch"})
public class NewSearchBJL extends AbstractFunnelController {
	public enum BJLSearchType { NAME, ADDRESS, SSN };
	public void init() {
		super.init();
		populateBjlNameTypes();
		populateBjlCaseTypes();
	}
	@RequestMapping(value="/funnel/NewBJLSearch.do",method=RequestMethod.GET)
	public String BJLForm(HttpSession session,ModelMap map,
			@RequestParam(value="test", required=false) Boolean test,
			@RequestParam(value="sample", required=false) Boolean sample,
			@RequestParam(value="searchTypes",required=false) String searchTypes)
	{
		return setBJLForm(session, map, test, sample, searchTypes, "funnel/NewSearchBJLDisp");
	}
	private String setBJLForm(HttpSession session,
			ModelMap map, Boolean test, Boolean sample,
			String searchTypes, String viewname)
	{
		if(test == null){test=false;}
		if(sample == null){sample=false;}
		BJLSearchFormCommand cmd=new BJLSearchFormCommand();
		if(searchTypes == null){searchTypes="name";}
		if(searchTypes.equals("name"))
		{
			cmd.setBjlsearchByType("name");
			cmd.setBjlSearchType(BJLSearchType.NAME);
		}
		else if(searchTypes.equals("ssn"))
		{
			cmd.setBjlsearchByType("ssn");
			cmd.setBjlSearchType(BJLSearchType.SSN);
		}
		else{
			cmd.setBjlsearchByType("name");
		}
		setSearchTypeAttribute(cmd, map);
		map.addAttribute("command", cmd);
		return viewname;
	}
	protected void setSearchTypeAttribute(BJLSearchFormCommand cmd, ModelMap map) {
		switch (cmd.getBjlSearchType()) {
		case NAME:
			map.addAttribute("searchTypes", "name");
			break;
		case ADDRESS:
			map.addAttribute("searchTypes", "address");
			break;
		case SSN:
			map.addAttribute("searchTypes", "ssn");
			break;
		}
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
		private String bjlsearchByType;
		
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
		public String getBjlsearchByType() {
			return bjlsearchByType;
		}
		public void setBjlsearchByType(String bjlsearchByType) {
			this.bjlsearchByType = bjlsearchByType;
		}
		
		
		
	}
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
