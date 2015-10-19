package springapp.web.corporate;

import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.io.StringReader;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import springapp.service.NeonValidator;


import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.*;
import org.apache.log4j.Logger;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

/**
 * @author Ram Kumarappan
 *
 */

@Controller

public class CorporateSearchLandingForm {
	protected Logger logger = Logger.getLogger(getClass());
	public void init() {
		populateCorporateSearchStates();
		
	}
	
	@RequestMapping(value = "/corporate/corporateSearchLanding.do", method = RequestMethod.GET)
	public String setupFormFull(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="searchType",required=false) String searchType
			) {	
		return setupFormSearchMexicanRecord(session, map, test, sample, searchType, "corporate/corporateSearchLanding");
	}
	
	
	private String setupFormSearchMexicanRecord(
			HttpSession session, ModelMap map, 
			Boolean test, Boolean sample, String searchType, 
			String viewName) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		if (searchType == null) { searchType = "company"; }
		
		SearchCorporateRecordFormCommand cmd = new SearchCorporateRecordFormCommand();
		if (test || sample) { setSample(cmd); }
		cmd.setTest(test);
		cmd.setSearchType(searchType);
			
		map.addAttribute("command", cmd);	
		
		if (searchType.equals("address")) {
			return "corporate/corporateSearchByAddress";
		} 
		
		if (searchType.equals("officer")) {
			return "corporate/corporateSearchByOfficer";
		}
		
		return viewName;
	}
	
	protected void setSample(SearchCorporateRecordFormCommand cmd) {
		cmd.setCorporateSearchCompanyName("tony");
		cmd.setCorporateSearchCity("wales");
		cmd.setCorporateSearchState("alaska");
		cmd.setSearchType("officer");		
	}	
	
	
	@RequestMapping(value = "/corporate/corporateSearchLanding.do", method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") SearchCorporateRecordFormCommand sfc,
			Errors errors,
			SessionStatus status) {
		return this.processSubmitSearchCorporateRecord(session, map, sfc, errors, status, "corporate/corporateSearchLanding");
	}
	
	private String processSubmitSearchCorporateRecord(HttpSession session,
			ModelMap map,
			SearchCorporateRecordFormCommand sfc,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		logger.info("Corporate search city => City Name: " + sfc.getCorporateSearchCity());
		logger.info("Corporate search State => State Name: " + sfc.getCorporateSearchState());

		if (session.getAttribute("searchCorporateRecordFormCommand") != null) {
			session.removeAttribute("searchCorporateRecordFormCommand");
		
		}
		if (session.getAttribute("strXML") != null) session.removeAttribute("strXML");
					
		vwErrorReturn = sfc.getSearchType().equals("address") ? "corporate/corporateSearchByAddress" : (sfc.getSearchType().equals("officer") ? "corporate/corporateSearchByOfficer" : vwErrorReturn) ;
		
		SearchCorporateRecordFormValidator v = new SearchCorporateRecordFormValidator();
		v.validate(sfc, errors);
		
		if (errors.hasErrors()) {
			return vwErrorReturn;
		} else {
			
	
			logger.info("Setting search Corporate record command object & continuing");
			String strSearchType = sfc.getSearchType();
			String strState = sfc.getCorporateSearchState();
			String strCity = sfc.getCorporateSearchCity();
			
			if(strState.equalsIgnoreCase("AllStates"))
			{
				strState="";
			}
			
			String strCompany="";
			String strOfficer="";
			String strAddress="";
			
			String strQueryResult = "";
			String strTotalMatches = "";

					
			if(strSearchType.equals("company")){
				strCompany =sfc.getCorporateSearchCompanyName();
			} else if(strSearchType.equals("officer")){
				strOfficer = sfc.getCorporateSearchOfficerName();
			}
			else if(strSearchType.equals("address")){
				strAddress = sfc.getCorporateSearchAddress();
			}

		  	  
		  try {
			  	ResourceBundle resBun=null;
				HttpClient client = new HttpClient();
				client.getParams().setAuthenticationPreemptive(true);
				
				resBun=ResourceBundle.getBundle("axciom.corporate");
				String postUrl=resBun.getString("corporate.postUrl");
				String userName=resBun.getString("corporate.userName");
				String passWord=resBun.getString("corporate.passWord");
				
				PostMethod post = new PostMethod(postUrl);
				
				Credentials defaultcreds = new UsernamePasswordCredentials(userName, passWord);
				client.getState().setCredentials(AuthScope.ANY, defaultcreds); 
				
				String data = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://id.acxiom.com/risk-natcorp/1.0\">";
				data = data + "<soapenv:Header>";
				data = data + "<ns:RiskHeader>";
				data = data + "<clientIP>192.168.1.2</clientIP>";
				data = data + "<reasonCode>1</reasonCode>";
				data = data + "</ns:RiskHeader>";
				data = data + "</soapenv:Header>";
				data = data + "<soapenv:Body>";
				data = data + "<ns:searchByNameAddress>";
				data = data + "<input>";
				data = data + "<businessName>"+strCompany+"</businessName>";
				data = data + "<city>"+strCity+"</city>";
				data = data + "<emailAddress></emailAddress>";
				data = data + "<faxNumber></faxNumber>";
				data = data + "<filingDate></filingDate>";
				data = data + "<incorporationState></incorporationState>";
				data = data + "<officerName>"+strOfficer+"</officerName>";
				data = data + "<phoneNumber></phoneNumber>";
				data = data + "<SICCode></SICCode>";
				data = data + "<state>"+strState+"</state>";
				data = data + "<street>"+strAddress+"</street>";
				data = data + "<taxID></taxID>";
				data = data + "<unitNumber></unitNumber>";
				data = data + "<zip></zip>";
				data = data + "</input>";
				data = data + "</ns:searchByNameAddress>";
				data = data + "</soapenv:Body>";
				data = data + "</soapenv:Envelope>";
		
				
		
				post.setRequestBody(data);		
				client.executeMethod(post);
				int returnCode = client.executeMethod(post);
				String strXML = post.getResponseBodyAsString();
				post.releaseConnection();
				
			//	logger.info("Return code : "+returnCode);
			//	logger.info("Responce XML : "+strXML);
				
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();   
				Document doc = builder.parse(new InputSource(new StringReader(strXML.replace("&nbsp;", "&#160;"))));
				NodeList nList = null;
				
				
				if(returnCode==500)
				{
					nList = doc.getElementsByTagName("env:Fault");
					Node firstNode=(Node)nList.item(0);
					String fault=readXMLNode(firstNode, "faultstring");
					if(fault.equalsIgnoreCase("Query returned too many results"));
					{
						errors.reject("error.Too-Many-Results","Too Many Results Found");	
						return vwErrorReturn;
					}
				} 
				
				nList = doc.getElementsByTagName("return");
				Node firstNode=(Node)nList.item(0);
				
				if(!firstNode.hasChildNodes()){
					errors.reject("error.No-Result-Found","No Results Found For Given Search Term");				
					return vwErrorReturn;
				}
				else
				{
					session.setAttribute("strXML", strXML);
					session.setAttribute("searchCorporateRecordFormCommand", sfc);
					
					if (session.getAttribute("creditCardObj") != null) {
						return "redirect:confirmCorporateSearch.do";
					}
					
					return "corporate/corporateValuProp";
					//return "redirect:purchaseCorporateRecordSearch.do";
				}
				
				
			} catch (Exception e) {
				logger.info("Exception occured in Axciom Search:"+e);
				return "corporate/Error";
			}
		  
		  
		  
		} 
	}
	
	
	public static class SearchCorporateRecordFormCommand {
		
		private String corporateSearchCompanyName;
		private String corporateSearchOfficerName;
		private String corporateSearchAddress;
		
		private String corporateSearchCity;
		private String corporateSearchState;
		private String searchType = "company";  //defaulted to "company", other values are address and state
				

		
			
		
		public String getCorporateSearchCity() {
			return corporateSearchCity;
		}
		public void setCorporateSearchCity(String corporateSearchCity) {
			this.corporateSearchCity = corporateSearchCity;
		}
		public String getCorporateSearchCompanyName() {
			return corporateSearchCompanyName;
		}
		public void setCorporateSearchCompanyName(String corporateSearchCompanyName) {
			this.corporateSearchCompanyName = corporateSearchCompanyName;
		}
		public String getCorporateSearchState() {
			return corporateSearchState;
		}
		public void setCorporateSearchState(String corporateSearchState) {
			this.corporateSearchState = corporateSearchState;
		}
		public String getCorporateSearchAddress() {
			return corporateSearchAddress;
		}
		public void setCorporateSearchAddress(String corporateSearchAddress) {
			this.corporateSearchAddress = corporateSearchAddress;
		}
		
		public String getCorporateSearchOfficerName() {
			return corporateSearchOfficerName;
		}
		public void setCorporateSearchOfficerName(String corporateSearchOfficerName) {
			this.corporateSearchOfficerName = corporateSearchOfficerName;
		}
		
		
		private Boolean test = false;	
				
		public Boolean getTest() {
			return test;
		}
		public void setTest(Boolean test) {
			this.test = test;
		}
		
		public String getSearchType() {
			return searchType;
		}
		public void setSearchType(String searchType) {
			this.searchType = searchType;
		}
		
	
		
		
	}
	
	public class SearchCorporateRecordFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(SearchCorporateRecordFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			Object[] errorArgs;
			
			SearchCorporateRecordFormCommand cmd = (SearchCorporateRecordFormCommand) target;
			
			validateStringEmptyOk("corporateSearchCity",   cmd.getCorporateSearchCity(), errors,  3, 50, regexBasicExtended, "City For Search");
			validateStringEmptyOk("corporateSearchState",   cmd.getCorporateSearchState(), errors,  3, 50, regexBasicExtended, "State For Search");
			
			if(cmd.getSearchType().equals("company"))
			{
				validateString("corporateSearchCompanyName", cmd.getCorporateSearchCompanyName(), errors,  3, 65, regexBasicSpaceAmpersand, "Company Name");
			}
			if(cmd.getSearchType().equals("address"))
			{
				validateString("corporateSearchAddress", cmd.getCorporateSearchAddress(), errors,  3, 65, regexText, "Address");
			}
			if(cmd.getSearchType().equals("officer"))
			{
				validateString("corporateSearchOfficerName", cmd.getCorporateSearchOfficerName(), errors,  3, 65, regexBasicSpaceAmpersand, "Officer Name");
			}
			
			
	
			
		}		
	}
	
	// static lookups
	protected static final LinkedHashMap<String,String> corporateSearchState = new LinkedHashMap<String,String>();
	
	@ModelAttribute("corporateSearchState")
	public LinkedHashMap<String, String> getCorporateSearchStates() {
		return corporateSearchState;
	}
	
	protected final void populateCorporateSearchStates() {
		corporateSearchState.put("AllStates","AllStates");
		corporateSearchState.put("Alaska","Alaska");
        corporateSearchState.put("Alabama","Alabama");
        corporateSearchState.put("Arkansas","Arkansas");
        corporateSearchState.put("Arizona","Arizona");
        corporateSearchState.put("California","California");
        corporateSearchState.put("Colorado","Colorado");
        corporateSearchState.put("Connecticut","Connecticut");
        corporateSearchState.put("District of Columbia","District of Columbia");
        corporateSearchState.put("Delaware","Delaware");
        corporateSearchState.put("Florida","Florida");
        corporateSearchState.put("Georgia","Georgia");
        corporateSearchState.put("Hawaii","Hawaii");
        corporateSearchState.put("Iowa","Iowa");
        corporateSearchState.put("Idaho","Idaho");
        corporateSearchState.put("Illinois","Illinois");
        corporateSearchState.put("Indiana","Indiana");
        corporateSearchState.put("Kansas","Kansas");
        corporateSearchState.put("Kentucky","Kentucky");
        corporateSearchState.put("Louisiana","Louisiana");
        corporateSearchState.put("Massachusetts","Massachusetts");
        corporateSearchState.put("Maryland","Maryland");
        corporateSearchState.put("Maine","Maine");
        corporateSearchState.put("Michigan","Michigan");
        corporateSearchState.put("Minnesota","Minnesota");
        corporateSearchState.put("Missouri","Missouri");
        corporateSearchState.put("Mississippi","Mississippi");
        corporateSearchState.put("Montana","Montana");
        corporateSearchState.put("North Carolina","North Carolina");
        corporateSearchState.put("North Dakota","North Dakota");
        corporateSearchState.put("Nebraska","Nebraska");
        corporateSearchState.put("New Hampshire","New Hampshire");
        corporateSearchState.put("New Jersey","New Jersey");
        corporateSearchState.put("New Mexico","New Mexico");
        corporateSearchState.put("Nevada","Nevada");
        corporateSearchState.put("New York","New York");
        corporateSearchState.put("Ohio","Ohio");
        corporateSearchState.put("Oklahoma","Oklahoma");
        corporateSearchState.put("Oregon","Oregon");
        corporateSearchState.put("Pennsylvania","Pennsylvania");
        corporateSearchState.put("Rhode Island","Rhode Island");
        corporateSearchState.put("South Carolina","South Carolina");
        corporateSearchState.put("South Dakota","South Dakota");
        corporateSearchState.put("Tennessee","Tennessee");
        corporateSearchState.put("Texas","Texas");
        corporateSearchState.put("Utah","Utah");
        corporateSearchState.put("Virginia","Virginia");
        corporateSearchState.put("Vermont","Vermont");
        corporateSearchState.put("Washington","Washington");
        corporateSearchState.put("Wisconsin","Wisconsin");
        corporateSearchState.put("West Virginia","West Virginia");
        corporateSearchState.put("Wyoming","Wyoming");
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