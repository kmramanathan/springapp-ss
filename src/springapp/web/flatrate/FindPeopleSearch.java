package springapp.web.flatrate;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.io.Serializable;
import java.io.StringReader;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import springapp.domain.FRfpDetail;
import springapp.repository.SubscriptionDao;
import springapp.repository.UserDao;
import springapp.service.NeonValidator;
import springapp.service.user.UserManager;
import springapp.web.findpeople.SearchLandingForm.SearchFPFormCommand;
import springapp.web.funnel.AbstractFunnelController;


import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.*;
import org.apache.log4j.Logger;

import org.w3c.dom.*;
import org.xml.sax.*;

import com.sun.net.httpserver.HttpContext;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

@Controller

public class FindPeopleSearch  extends AbstractFunnelController{
	
	@Autowired
	protected SubscriptionDao subscriptionDao;
	
	protected Logger logger = Logger.getLogger(getClass());
			
	public void init() {
		populateSearchStates();
	}
    private static ResourceBundle resBun=ResourceBundle.getBundle("webservice");
	private final static String URL = resBun.getString("fr.fp.url");
	private final static String USERNAME = resBun.getString("fr.fp.username");
	private final static String PASSWORD = resBun.getString("fr.fp.password");
			
	@RequestMapping(value = "/flatrate/FPsearchLanding.do", method = RequestMethod.GET)
	public String setupFormFull(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="searchType",required=false) String searchType
				) {	
		
		if(session.getAttribute("FRusername") == null || session.getAttribute("FRuserId") == null){
			return FRLoginRedir;
		}
		
		//checked Find people search availablity
		int subId;
		if(session.getAttribute("FPSearch") != null && session.getAttribute("FPComSearch") != null){
			int FPsubId = (Integer)session.getAttribute("FPSubId");
			int COMsubId = (Integer)session.getAttribute("ComSubId");
			if(FPsubId < COMsubId){
				subId = (Integer)session.getAttribute("FPSubId");
			}else{
				subId = (Integer)session.getAttribute("ComSubId");
			}
		}else if(session.getAttribute("FPSearch") != null){
			subId = (Integer)session.getAttribute("FPSubId");
		}else if(session.getAttribute("FPComSearch") != null){
			subId = (Integer)session.getAttribute("ComSubId");
		}else{
			// No FP available search	
			return FRMenuRedir;
		}
		if(subscriptionDao.isExpired(subId)){
			return FRMenuRedir;
		}
		session.setAttribute("activeFPSubId", new Integer(subId));
		return setupFormSearch(session, map, test, sample, searchType, FRFPlandingUrl);
	}
	
	private String setupFormSearch(
			HttpSession session, ModelMap map, 
			Boolean test, Boolean sample, String searchType, 
			String viewName) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		if (searchType == null) { searchType = "basic"; }

		
		SearchFRFPFormCommand cmd = new SearchFRFPFormCommand();
		if (test || sample) { setSample(cmd); }
		cmd.setTest(test);
		cmd.setSearchType(searchType);
		
		map.addAttribute("command", cmd);
		map.addAttribute("searchType", searchType);

		return viewName;
	}
	
	protected void setSample(SearchFRFPFormCommand cmd) {
		cmd.setSearchFirstName("STEVE");
		cmd.setSearchLastName("SMITH");
		cmd.setSearchState("AK");
	}	
	
	@RequestMapping(value = "/flatrate/FPsearchLanding.do", method = RequestMethod.POST)
	public String processDelay(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") SearchFRFPFormCommand sfpfc,
			Errors errors,
			SessionStatus status) {
		
		//checked Find people search availability ( This is because user may use search banner on result page)
		int subId;
		if(session.getAttribute("FPSearch") != null && session.getAttribute("FPComSearch") != null){
			int FPsubId = (Integer)session.getAttribute("FPSubId");
			int COMsubId = (Integer)session.getAttribute("ComSubId");
			if(FPsubId < COMsubId){
				subId = (Integer)session.getAttribute("FPSubId");
			}else{
				subId = (Integer)session.getAttribute("ComSubId");
			}
		}else if(session.getAttribute("FPSearch") != null){
			subId = (Integer)session.getAttribute("FPSubId");
		}else if(session.getAttribute("FPComSearch") != null){
			subId = (Integer)session.getAttribute("ComSubId");
		}else{
			// No FP available search	
			return FRMenuRedir;
		}
		if(subscriptionDao.isExpired(subId)){
			return FRMenuRedir;
		}
		session.setAttribute("activeFPSubId", new Integer(subId));
		return this.processSubmitSearchRecord(session, map, sfpfc, errors, status, FRFPlandingUrl);
	}
	
	private String processSubmitSearchRecord(HttpSession session,
			ModelMap map,
			SearchFRFPFormCommand sfpfc,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		
		if(sfpfc.getSearchType().equals("basic")){
			sfpfc.setSearchDob("");
			sfpfc.setSearchCity("");
		}
		if(sfpfc.getSearchMidName() == null){
			sfpfc.setSearchMidName("");
		}
		SearchFormValidator v = new SearchFormValidator();
		v.validate(sfpfc, errors);
		
		if (errors.hasErrors()) {
			map.addAttribute("searchType", sfpfc.getSearchType());
			return vwErrorReturn;
		} 		
		map.addObject("sfpfc",sfpfc);
		return FRFPdelayUrl;
		
	}
	
	
	@RequestMapping(value = "/flatrate/FPResults.do", method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") SearchFRFPFormCommand sfpfc,
			SessionStatus status) {
		
		//Check FPStatus
		if(session.getAttribute("activeFPSubId") == null){
			return FRFPlandingRedir;
		}
		int subId = (Integer) session.getAttribute("activeFPSubId");
		if(subscriptionDao.updateFPSearchCount(subId) <=0 ){
			if(subscriptionDao.getPlanTypeIdBySubId(subId) == 1){
				session.removeAttribute("FPSearch");
			}else{
				session.removeAttribute("FPComSearch");
			}
		}
				
		ArrayList<FRfpDetail> fpList= new ArrayList<FRfpDetail>();
		fpList = getSearchResult(sfpfc);
		if(fpList.isEmpty()){	
			map.addObject("sfpfc", sfpfc);
			return FRFPnoResultUrl;
		}
		
		logger.info("size :"+fpList.size());
		map.addObject("sfpfc", sfpfc);
		map.addAttribute("fpList", fpList);
		map.addAttribute("searchState", searchState);
		return FRFPresultUrl;
	}
	
	@RequestMapping(value = "/flatrate/getBGResult.do", method = RequestMethod.POST)
	public String processBGSubmit(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") SearchFPFormCommand sfpfc,
			@RequestParam(value="key", required=true)String key,
			SessionStatus status) {
		
		logger.info("key fo BG from FRFP :"+key);
		session.setAttribute("key", key);
		session.setAttribute("searchFPFormCommand", sfpfc);
				
		return FRFPBGResultRedir+"?requestFrom=flatrate"+"&resultType=BG";
	}
	
	
		public static class SearchFRFPFormCommand {
		
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
			return clazz.equals(SearchFRFPFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			Object[] errorArgs;
			
			SearchFRFPFormCommand cmd = (SearchFRFPFormCommand) target;
			
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
	
	public ArrayList<FRfpDetail> getSearchResult(SearchFRFPFormCommand sfpfc)
	{
		Logger logger = Logger.getRootLogger();
		ArrayList<FRfpDetail> fpList = new ArrayList<FRfpDetail>();
		try
		{
			
			HttpClient client = new HttpClient();
			client.getParams().setAuthenticationPreemptive(true);
			PostMethod post = new PostMethod(URL);
			
			Credentials defaultcreds = new UsernamePasswordCredentials(
					USERNAME, PASSWORD);
			client.getState().setCredentials(AuthScope.ANY, defaultcreds);
			
			String strXML = getXmlRequest(sfpfc);
			logger.info("Request XML is :: "+strXML);
				
			NameValuePair[] XML= {new NameValuePair("XML",strXML) };
			post.setQueryString(XML);
			int returnCode = client.executeMethod(post);
			String resXML = post.getResponseBodyAsString();
			logger.info("Response XML :: "+resXML);
			logger.info("Return code :"+returnCode );
									
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();   
			Document doc = builder.parse(new InputSource(new StringReader(resXML.replace("&nbsp;", "&#160;"))));
			NodeList nList = null;
			
			//reading the head nodes and registering to logger if any error occured
			NodeList tempList = doc.getElementsByTagName("FDSResponse");
			Node headNode = (Node)tempList.item(0);
			System.out.println(headNode.getNodeName());
			String resultCount = readXMLNode(headNode,"ResultCount");
			String receivedAt = readXMLNode(headNode,"Generated");
			logger.info("Result Count :"+resultCount);
			logger.info("Received At :"+receivedAt);
			if(resultCount.equals("-1")){
				String reasonString = readXMLNode(headNode,"searchResults");
				logger.info("Reason Text :"+reasonString);
				logger.info("Response XML :"+resXML);
			}

			nList = doc.getElementsByTagName("Result");
			int total = 0;
			total=nList.getLength();
			if(total > 0){	return fpResultDetail(nList, total); }
			
			return fpList;
					
		}
		catch (Exception e) {	
			logger.error("Exception :"+e.getMessage() +" Cause :"+e.getCause()+" Class +"+e.getClass());
			return fpList;
		}

	}
	
	public String getXmlRequest(SearchFRFPFormCommand sfpfc)
	{
		Logger logger = Logger.getRootLogger();
		String strXML="";
			strXML= "<FDSRequest>";
			strXML = strXML+ "<username>"+USERNAME+"</username>";
			strXML = strXML+ "<password>"+PASSWORD+"</password>";
			strXML = strXML+ "<sType>PFSBN</sType>";
			strXML = strXML+ "<detail>1</detail>"; 
			strXML = strXML+ "<testmode>false</testmode>";
			strXML = strXML+ "<searchParams>" ;
			strXML = strXML+ "<firstName>"+sfpfc.getSearchFirstName()+"</firstName>" ;
			strXML = strXML+ "<middleName>"+sfpfc.getSearchMidName()+" </middleName>";
			strXML = strXML+ "<lastName>"+sfpfc.getSearchLastName()+"</lastName>" ;
			strXML = strXML+ "<city>"+sfpfc.getSearchCity()+"</city>" ;
			strXML = strXML+ "<state>"+sfpfc.getSearchState()+"</state>";
			strXML = strXML+ "<DOB>"+sfpfc.getSearchDob()+"</DOB>" ;
			strXML = strXML+ "</searchParams>" ;
			strXML = strXML+ "</FDSRequest>";	
			return strXML;
		
	}
	
	
	
	//Parsing teaser result and FP details
	public ArrayList<FRfpDetail> fpResultDetail(NodeList nList, int total)
	{
		ArrayList<FRfpDetail> fpList = new ArrayList<FRfpDetail>();
		for(int i=0;i<total;i++)
		{
			Node firstNode = nList.item(i);
			
			FRfpDetail fp = new FRfpDetail();
					
			fp.setValidityDate(readXMLNode(firstNode,"ValidityDate"));
			fp.setLastName(readXMLNode(firstNode,"LastName"));
			fp.setFirstName(readXMLNode(firstNode,"FirstName"));
			fp.setMiddleName(readXMLNode(firstNode,"MiddleName"));
			fp.setDob(readXMLNode(firstNode,"DOB"));
			fp.setAddress(readXMLNode(firstNode,"Address"));
			fp.setCity(readXMLNode(firstNode,"City"));
			fp.setState(readXMLNode(firstNode,"State"));
			fp.setPhone(readXMLNode(firstNode,"Phone"));
			fp.setPersonID(readXMLNode(firstNode,"personID"));
			
			fpList.add(fp);			
		}
		return fpList;
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