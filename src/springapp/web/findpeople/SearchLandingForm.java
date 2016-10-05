package springapp.web.findpeople;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import springapp.service.NeonValidator;
import springapp.web.funnel.AbstractFunnelController;

@Controller

public class SearchLandingForm  extends AbstractFunnelController{
	protected Logger logger = Logger.getLogger(getClass());
	public void init() {
		populateSearchStates();
		
	}
	
		
	@RequestMapping(value = "/findpeople/searchLanding.do", method = RequestMethod.GET)
	public String setupFormFull(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="searchType",required=false) String searchType,
			@RequestParam(value="resultType",required=false) String resultType
			) {	
		return setupFormSearch(session, map, test, sample, searchType, "findpeople/BasicSearch",resultType);
	}
	
	private String setupFormSearch(
			HttpSession session, ModelMap map, 
			Boolean test, Boolean sample, String searchType, 
			String viewName, String resultType) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		if (searchType == null) { searchType = "basic"; }
		if (resultType == null){ resultType="";}
		
		SearchFPFormCommand cmd = new SearchFPFormCommand();
		if (test || sample) { setSample(cmd); }
		cmd.setTest(test);
		cmd.setSearchType(searchType);
		
		map.addAttribute("command", cmd);	
		
		if(resultType.equals("findPeopleResult")){
			return "findpeople/TeaserResults";
		}
		
		if(resultType.equals("one")){
			return "findpeople/FpOneDetail";
		}
		
		if(resultType.equals("BG"))	{
			return "findpeople/BGDetails";
		}
		
		if(resultType.equals("BGlanding"))	{
			session.setAttribute("BGlanding", resultType);
			if(searchType.equalsIgnoreCase("advanced"))
				return "findpeople/AdvancedSearch";
			return "findpeople/BasicSearch";
		}
		
		if(resultType.equals("NoBGlanding"))	{
			if(session.getAttribute("BGlanding") != null){
				session.removeAttribute("BGlanding");
			}
			if(searchType.equalsIgnoreCase("advanced"))
				return "findpeople/AdvancedSearch";
			return "findpeople/BasicSearch";
		}
		
		if(resultType.equals("BGfindPeopleResult"))	{
			return "findpeople/TeaserResultsBG";
		}
		
		if (searchType.equals("basic")) {
			return "findpeople/BasicSearch";
		} 
		
		if (searchType.equals("advanced")) {
			return "findpeople/AdvancedSearch";
		}
		
		return viewName;
	}
	
	protected void setSample(SearchFPFormCommand cmd) {
		cmd.setSearchFirstName("STEVE");
		cmd.setSearchLastName("SMITH");
		cmd.setSearchState("AK");
	}	
	
	
	@RequestMapping(value = "/findpeople/searchLanding.do", method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") SearchFPFormCommand sfpfc,
			Errors errors,
			SessionStatus status,
			@RequestParam(value="resultType",required=false) String resultType) {
			//For banner
			if(resultType != null && resultType.equals("BGlanding"))	{
				session.setAttribute("BGlanding", resultType);
			}
		return this.processSubmitSearchRecord(session, map, sfpfc, errors, status, "findpeople/BasicSearch");
	}
	
	private String processSubmitSearchRecord(HttpSession session,
			ModelMap s ,
			SearchFPFormCommand sfpfc,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		
		if(sfpfc.getSearchType().equals("basic"))
		{
			sfpfc.setSearchDob("");
			sfpfc.setSearchCity("");
		}
		if(sfpfc.getSearchMidName()==null){
			sfpfc.setSearchMidName("");
		}

		logger.info("Find people search First Name =>" + sfpfc.getSearchFirstName());
		logger.info("Find people search Last Name =>" + sfpfc.getSearchLastName());
	
	//removing the session
		if (session.getAttribute("searchFormCommand") != null) { session.removeAttribute("searchFormCommand"); }
		if (session.getAttribute("key") != null){ session.removeAttribute("key"); }
		if (session.getAttribute("fpResult") != null) { session.removeAttribute("fpResult"); }
		if (session.getAttribute("teaserResult") != null) { session.removeAttribute("teaserResult"); }
		if (session.getAttribute("BGResult") != null) { session.removeAttribute("BGResult"); }
		if (session.getAttribute("resultType") != null) { session.removeAttribute("resultType"); }
		if (session.getAttribute("ageHash") != null) { session.removeAttribute("ageHash"); }
		if (session.getAttribute("BgcYob") != null) { session.removeAttribute("BgcYob"); }
		if (session.getAttribute("BGrePage") != null) { session.removeAttribute("BGrePage"); }
		if (session.getAttribute("searchDob") != null) { session.removeAttribute("searchDob"); }
		if (session.getAttribute("offToFetch") != null) { session.removeAttribute("offToFetch"); }
		if (session.getAttribute("BgcOffenders") != null) { session.removeAttribute("BgcOffenders"); }
		if (session.getAttribute("BgcAliases") != null) { session.removeAttribute("BgcAliases"); }
		if (session.getAttribute("BgcOffenses") != null) { session.removeAttribute("BgcOffenses"); }
			 
					
		vwErrorReturn = sfpfc.getSearchType().equals("basic") ? "findpeople/BasicSearch" : (sfpfc.getSearchType().equals("advanced") ? "findpeople/AdvancedSearch" : vwErrorReturn) ;
		
		SearchFormValidator v = new SearchFormValidator();
		v.validate(sfpfc, errors);
		
		if (errors.hasErrors()) {
			return vwErrorReturn;
		} else {
				
				//tracking the teasers
					userManager.fpTeaserCount();
			
					Hashtable teaserResult=null;
					session.setAttribute("searchFPFormCommand", sfpfc);
			    
					teaserResult = xmlParser.getSearchResult(sfpfc, "FP", "teaser");
					if(teaserResult == null){
						return "findpeople/NoResults";
					}
					else{
						//setting age Hash to session and deleting it from result hash
						Enumeration keys = teaserResult.keys();
						Hashtable ageHash = (Hashtable) teaserResult.get("ageHash");
						session.setAttribute("ageHash", ageHash);
						teaserResult.remove("ageHash");
						
						session.setAttribute("teaserResult", teaserResult);
						String BGlanding = (String) session.getAttribute("BGlanding");
						if(BGlanding != null){
						return "redirect:/findpeople/searchLanding.do?resultType=BGfindPeopleResult";
						}
						else {
							return "redirect:/findpeople/searchLanding.do?resultType=findPeopleResult";
						}
					}

		} 
	}
	
	@RequestMapping(value = "/findpeople/logout.do", method = RequestMethod.GET)
	public String logoutSession(HttpSession session) 
	{	
		if (session.getAttribute("searchFormCommand") != null) { session.removeAttribute("searchFormCommand"); }
		if (session.getAttribute("key") != null){ session.removeAttribute("key"); }
		if (session.getAttribute("fpResult") != null) { session.removeAttribute("fpResult"); }
		if (session.getAttribute("teaserResult") != null) { session.removeAttribute("teaserResult"); }
		if (session.getAttribute("BGResult") != null) { session.removeAttribute("BGResult"); }
		if (session.getAttribute("resultType") != null) { session.removeAttribute("resultType"); }
		if (session.getAttribute("ageHash") != null) { session.removeAttribute("ageHash"); }
		if (session.getAttribute("BgcYob") != null) { session.removeAttribute("BgcYob"); }
		if (session.getAttribute("BGrePage") != null) { session.removeAttribute("BGrePage"); }
		if (session.getAttribute("searchDob") != null) { session.removeAttribute("searchDob"); }
		if (session.getAttribute("offToFetch") != null) { session.removeAttribute("offToFetch"); }
		if (session.getAttribute("BgcOffenders") != null) { session.removeAttribute("BgcOffenders"); }
		if (session.getAttribute("BgcAliases") != null) { session.removeAttribute("BgcAliases"); }
		if (session.getAttribute("BgcOffenses") != null) { session.removeAttribute("BgcOffenses"); }
		session.removeAttribute("authenticated");
		session.removeAttribute("userId");
		session.removeAttribute("creditCardObj");
		session.removeAttribute("username");
		session.removeAttribute("noCreditCard");
		session.removeAttribute("userEmail");
		if (session.getAttribute("BGlanding") != null) { return "redirect:searchLanding.do?resultType=BGlanding"; }
		return "redirect:searchLanding.do";
	}
	
	
		public static class SearchFPFormCommand {
		
		private String searchFirstName;
		private String searchMidName;
		private String searchLastName;
		private String searchDob;
		private String searchCity;
		private String searchState;
		private String searchType = "basic";  //defaulted to "basic", other values are address and state
		private Boolean test = false;
		
		
		public String getSearchFirstName() {
			return searchFirstName;
		}
		public void setSearchFirstName(String searchFirstName) {
			this.searchFirstName = searchFirstName;
		}
		public String getSearchMidName() {
			return searchMidName;
		}
		public void setSearchMidName(String searchMidName) {
			this.searchMidName = searchMidName;
		}
		public String getSearchLastName() {
			return searchLastName;
		}
		public void setSearchLastName(String searchLastName) {
			this.searchLastName = searchLastName;
		}
		public String getSearchDob() {
			return searchDob;
		}
		public void setSearchDob(String searchDob) {
			this.searchDob = searchDob;
		}
		public String getSearchCity() {
			return searchCity;
		}
		public void setSearchCity(String searchCity) {
			this.searchCity = searchCity;
		}
		
		public String getSearchState() {
			return searchState;
		}
		public void setSearchState(String searchState) {
			this.searchState = searchState;
		}
		public String getSearchType() {
			return searchType;
		}
		public void setSearchType(String searchType) {
			this.searchType = searchType;
		}
		public Boolean getTest() {
			return test;
		}
		public void setTest(Boolean test) {
			this.test = test;
		}		

				
	}
	
	public class SearchFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(SearchFPFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			Object[] errorArgs;
			
			SearchFPFormCommand cmd = (SearchFPFormCommand) target;
			
			if(cmd.getSearchFirstName() == null || cmd.getSearchFirstName().equals("") || cmd.getSearchLastName() == null || cmd.getSearchLastName().equals("")){
				errors.rejectValue("searchFirstName", "error.FirstName_not_selected",  "A required field is missing.");
			}
			validateStringEmptyOk("searchFirstName",   cmd.getSearchFirstName() , errors,  3, 50, regexLettersOnly, "First Name");
			validateStringEmptyOk("searchMidName",   cmd.getSearchMidName(), errors,  1, 1, regexLettersOnly, "Middle Name");
			validateStringEmptyOk("searchLastName",   cmd.getSearchLastName(), errors,  3, 50, regexLettersOnly, "Last Name");
				
			
			if(cmd.getSearchType().equals("advanced"))
			{
				validateStringEmptyOk("searchCity", cmd.getSearchCity(), errors,  3, 65, regexLettersWithSpace , "City");
				validateStringEmptyOk("searchDob", cmd.getSearchDob() , errors,  10, 10, regexDOB, "Date of Birth");
			}
			
			
		}		
	}
	
	// static lookups
	protected static final LinkedHashMap<String,String> searchState = new LinkedHashMap<String,String>();
	
	@ModelAttribute("searchState")
	public LinkedHashMap<String, String> getSearchStates() {
		return searchState;
	}
	
	protected final void populateSearchStates() {
		searchState.put("","All States");
		searchState.put("AK","Alaska");
        searchState.put("AL","Alabama");
        searchState.put("AR","Arkansas");
        searchState.put("AZ","Arizona");
        searchState.put("CA","California");
        searchState.put("CO","Colorado");
        searchState.put("CT","Connecticut");
        searchState.put("DC","District of Columbia");
        searchState.put("DE","Delaware");
        searchState.put("FL","Florida");
        searchState.put("GA","Georgia");
        searchState.put("HI","Hawaii");
        searchState.put("IA","Iowa");
        searchState.put("ID","Idaho");
        searchState.put("IL","Illinois");
        searchState.put("IN","Indiana");
        searchState.put("KS","Kansas");
        searchState.put("KY","Kentucky");
        searchState.put("LA","Louisiana");
        searchState.put("MA","Massachusetts");
        searchState.put("MD","Maryland");
        searchState.put("ME","Maine");
        searchState.put("MI","Michigan");
        searchState.put("MN","Minnesota");
        searchState.put("MO","Missouri");
        searchState.put("MS","Mississippi");
        searchState.put("MT","Montana");
        searchState.put("NC","North Carolina");
        searchState.put("ND","North Dakota");
        searchState.put("NE","Nebraska");
        searchState.put("NH","New Hampshire");
        searchState.put("NJ","New Jersey");
        searchState.put("NM","New Mexico");
        searchState.put("NV","Nevada");
        searchState.put("NY","New York");
        searchState.put("OH","Ohio");
        searchState.put("OK","Oklahoma");
        searchState.put("OR","Oregon");
        searchState.put("PA","Pennsylvania");
        searchState.put("RI","Rhode Island");
        searchState.put("SC","South Carolina");
        searchState.put("SD","South Dakota");
        searchState.put("TN","Tennessee");
        searchState.put("TX","Texas");
        searchState.put("UT","Utah");
        searchState.put("VA","Virginia");
        searchState.put("VT","Vermont");
        searchState.put("WA","Washington");
        searchState.put("WI","Wisconsin");
        searchState.put("WV","West Virginia");
        searchState.put("WY","Wyoming");
	}
	
	public String readXMLNode(Node firstNode, String strTagName){
		String strQueryResult = "";
		Element firstElement = (Element)firstNode;
		NodeList queryResultList = firstElement.getElementsByTagName(strTagName);
		Element firstqueryResultElement = (Element)queryResultList.item(0);
		if(firstqueryResultElement!=null){
			NodeList textqueryResultList = firstqueryResultElement.getChildNodes();
			Node lNode = ((Node)textqueryResultList.item(0));			
			if(lNode!=null){
				strQueryResult = lNode.getNodeValue().trim();
			}
		}
		return (strQueryResult);
	}

}