package springapp.web.funnel.mexico;

import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import springapp.service.NeonValidator;


@Controller

public class SearchMexicanRecordForm extends springapp.web.funnel.AbstractFunnelController {
	
	public void init() {
		populateMexicanSearchPurposes();
		populateMexicanSearchRegions();
	}
	
	@RequestMapping(value = "/funnel/mexico/searchMexicanRecord.do", method = RequestMethod.GET)
	public String setupFormFull(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="searchType",required=false) String searchType
			) {	
		return setupFormSearchMexicanRecord(session, map, test, sample, searchType, vwSearchMexicanRecordFull);
	}
	
	/*
	 * modify this to use rate instead of nationwide
	 * keep list of rates which are nationwide?
	 */
	private String setupFormSearchMexicanRecord(
			HttpSession session, ModelMap map, 
			Boolean test, Boolean sample, String searchType, 
			String viewName) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		if (searchType == null) { searchType = "nation"; }
		
		SearchMexicanRecordFormCommand cmd = new SearchMexicanRecordFormCommand();
		if (test || sample) { setSample(cmd); }
		cmd.setTest(test);
		cmd.setSearchType(searchType);
		cmd.setSearchRecordType("");
		cmd.setMexicanSearchRegion("");
		
		map.addAttribute("command", cmd);	
		
		if (searchType.equals("state")) {
			return vwSearchMexicanRecordByState;
		} 
		
		if (searchType.equals("region")) {
			return vwSearchMexicanRecordByRegion;
		}
		
		return viewName;
	}
	
	protected void setSample(SearchMexicanRecordFormCommand cmd) {
		cmd.setMexicanSearchIndividualName("David");
		cmd.setSearchRecordType("");
		cmd.setMexicanSearchPurpose("Personal");
		cmd.setSearchType("nation");		
	}
	
	@RequestMapping(value = "/funnel/mexico/searchMexicanRecord.do", method = RequestMethod.POST)
	public ModelAndView processSubmit(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") SearchMexicanRecordFormCommand sfc,
			Errors errors,
			SessionStatus status) {
		return this.processSubmitSearchMexicanRecord(session, map, sfc, errors, status, vwSearchMexicanRecordFull);
	}
	
	private ModelAndView processSubmitSearchMexicanRecord(HttpSession session,
			ModelMap map,
			SearchMexicanRecordFormCommand sfc,
			Errors errors,
			SessionStatus status,
			String vwErrorReturn) {
		logger.info("Mexican search string => Individual Name: " + sfc.getMexicanSearchIndividualName());
		logger.info("Mexican search string => Record Type: " + sfc.getSearchRecordType());

		if (session.getAttribute("searchMexicanRecordFormCommand") != null) {
			session.removeAttribute("searchMexicanRecordFormCommand");
			//need to implement terminate query
		}
		
		if (session.getAttribute("teaserResultCount") != null) session.removeAttribute("teaserResultCount");
		if (session.getAttribute("teaserQueryId") != null) session.removeAttribute("teaserQueryId");
		if (session.getAttribute("resultData") != null) session.removeAttribute("resultData");
		if (session.getAttribute("searchData") != null) session.removeAttribute("searchData");
				
		vwErrorReturn = sfc.getSearchType().equals("state") ? vwSearchMexicanRecordByState : (sfc.getSearchType().equals("region") ? vwSearchMexicanRecordByRegion : vwErrorReturn) ;
		
		SearchMexicanRecordFormValidator v = new SearchMexicanRecordFormValidator();
		v.validate(sfc, errors);
		if (errors.hasErrors()) {
			return new ModelAndView(vwErrorReturn);
		} else {
			logger.info("Setting search mexican record command object & continuing");
			
			
			//mexico user count
			userManager.teaserUserCount();
			
			String strSearchName = sfc.getMexicanSearchIndividualName();
			String strSearchType = sfc.getSearchType();
			String strPurpose = sfc.getMexicanSearchPurpose();
			String strSearchRecordType = sfc.getSearchRecordType().substring(0,sfc.getSearchRecordType().indexOf("-"));

			String strUserName = "searchsystems";
			String strPassword = "testsystems";

			String strQueryResult = "";
			String strTotalMatches = "";

			String strStates = "";
			
			if(strSearchRecordType.equals("all")){
				strSearchRecordType = "";
			}
			if(strSearchType.equals("nation")){
				strStates = "";
			} else if(strSearchType.equals("state")){
				strStates = sfc.getMexicanSearchStates();
			}
			else if(strSearchType.equals("region")){
				strStates = sfc.getMexicanSearchRegion();
			}

			logger.info("Mexican search string =>  States: " + strStates);
			logger.info("Mexican search string =>  Purpose: " + strPurpose);

			String strResult = "";
			
			try{
				//Call the WFC Web service here using HttpClient API and assign the result to strXML.......
				HttpClient client = new HttpClient();
				PostMethod post = new PostMethod("https://66.238.54.220:8443/SearchSystems/Search?method=GetSearchQueryResultCount");
				NameValuePair[] data = {
											new NameValuePair("UserID", strUserName),
											new NameValuePair("Password", strPassword),
											new NameValuePair("Name", strSearchName),
											new NameValuePair("States", strStates),
											new NameValuePair("Type", strSearchRecordType)
									   };
				post.setRequestBody(data);		
				client.executeMethod(post);
				int returnCode = client.executeMethod(post);
				 
				String strXML = post.getResponseBodyAsString();    
				logger.info("Mexican WFC Call => Response Status Code: " + post.getStatusCode() + "; Response Status Text: " + post.getStatusText()); 

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();   
				Document doc = builder.parse(new InputSource(new StringReader(strXML)));

				NodeList nList = null;
				nList = doc.getElementsByTagName("ns1:wcfresponse");
				int total = 0;
				String strQueryId = "";
				if(nList!=null){
					total = nList.getLength();	
					logger.info("Mexican WFC Call Response Result => Total Nodes: " + total);
					for(int s=0; s<total ; s++){
						Node firstNode = nList.item(s);
						Element firstElement = (Element)firstNode;

						NodeList queryResultList = firstElement.getElementsByTagName("queryResult");
						Element firstqueryResultElement = (Element)queryResultList.item(0);
						NodeList textqueryResultList = firstqueryResultElement.getChildNodes();
						strQueryResult = ((Node)textqueryResultList.item(0)).getNodeValue().trim();

						NodeList totalMatchesList = firstElement.getElementsByTagName("totalMatches");
						Element firsttotalMatchesElement = (Element)totalMatchesList.item(0);
						NodeList texttotalMatchesList = firsttotalMatchesElement.getChildNodes();
						strTotalMatches = ((Node)texttotalMatchesList.item(0)).getNodeValue().trim();

						NodeList queryIDList = firstElement.getElementsByTagName("queryId");
						Element firstqueryIDListElement = (Element)queryIDList.item(0);
						NodeList textqueryIDList = firstqueryIDListElement.getChildNodes();
						strQueryId = ((Node)textqueryIDList.item(0)).getNodeValue().trim();
					}
				}
			
				if(strQueryResult.equals("Success")){
					logger.info("Mexican WFC Call Response Success Loop => Total Matches: " + strTotalMatches);
					session.setAttribute("teaserResultCount", strTotalMatches);
					session.setAttribute("teaserQueryId", strQueryId);
					session.setAttribute("searchMexicanRecordFormCommand", sfc);	
					//status.setComplete();
					if(strTotalMatches.equals("0")){
						return new ModelAndView(vwSearchMexicanRecordNoTeaserResult);					}
					else{
						ModelAndView vwResult = new ModelAndView(vwSearchMexicanRecordTeaserResult);
						vwResult.addObject("searchType", strSearchRecordType);
						vwResult.addObject("searchBy", (strSearchType.equalsIgnoreCase("state") ? "State" : (strSearchType.equalsIgnoreCase("region") ? "Region" : "Nationwide")));
						vwResult.addObject("searchPrice", sfc.getSearchRecordType().substring(sfc.getSearchRecordType().indexOf("-") + 1));
						return vwResult;
					}
				}
				else{
					return new ModelAndView("Error");
				}
			}
			catch(Exception e){
				logger.error("While retrieving teaser reults from WFC=> Error: " + e);
				return new ModelAndView("Error");
			}
		}
	}
	
	public static class SearchMexicanRecordFormCommand {
		// NOTE: fields need to be initialised to avoid type mismatch
		// errors in web form validation. this is necessary because we
		// use boxed types (which initiated as null) rather than primitives.
		// one way around this is to use String rather than Integer or
		// Boolean but then we have to deal with type conversions.
		
		// required fields
		private String mexicanSearchIndividualName;
		private String mexicanSearchPurpose;
		private String searchType = "nation";  //defaulted to "nation", other values are state and region
		private String searchRecordType = ""; //valid values are "all", PENAL and CIVIL + $price
		
		// additional options
		private String mexicanSearchStates;
		private String mexicanSearchRegion;
		
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
		
		public String getSearchRecordType() {
			return searchRecordType;
		}
		public void setSearchRecordType(String searchRecordType) {
			this.searchRecordType = searchRecordType;
		}
		
		public String getMexicanSearchIndividualName() {
			return mexicanSearchIndividualName;
		}
		public void setMexicanSearchIndividualName(String mexicanSearchIndividualName) {
			this.mexicanSearchIndividualName = mexicanSearchIndividualName;
		}
		
		public String getMexicanSearchPurpose() {
			return mexicanSearchPurpose;
		}
		public void setMexicanSearchPurpose(String mexicanSearchPurpose) {
			this.mexicanSearchPurpose = mexicanSearchPurpose;
		}
		
		public String getMexicanSearchStates() {
			return mexicanSearchStates;
		}
		public void setMexicanSearchStates(String mexicanSearchStates) {
			this.mexicanSearchStates = mexicanSearchStates;
		}
		
		public String getMexicanSearchRegion() {
			return mexicanSearchRegion;
		}
		public void setMexicanSearchRegion(String mexicanSearchRegion) {
			this.mexicanSearchRegion = mexicanSearchRegion;
		}
	}
	
	public class SearchMexicanRecordFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(SearchMexicanRecordFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			Object[] errorArgs;
			
			SearchMexicanRecordFormCommand cmd = (SearchMexicanRecordFormCommand) target;

			validateString("mexicanSearchIndividualName", cmd.getMexicanSearchIndividualName(), errors,  3, 65, regexBasicSpaceAmpersand, "Individual or Business Name");
			//For this Purpose selection, it only is required when the customer selects a Criminal search or the All court records option.  The Purpose selection should
			//not be required if they select Civil court records only
			if((cmd.getSearchRecordType() != null && !cmd.getSearchRecordType().startsWith("CIVIL") && !cmd.getSearchRecordType().equals("")) && (cmd.getMexicanSearchPurpose() == null || (cmd.getMexicanSearchPurpose() != null && cmd.getMexicanSearchPurpose().equals("")))) {
				errorArgs = new Object[] { "Purpose" };
				errors.rejectValue("mexicanSearchPurpose", "error.purpose_not_selected", errorArgs, "A required field is missing.");
			}
			if(cmd.getSearchRecordType() == null || (cmd.getSearchRecordType() != null && (cmd.getSearchRecordType().equals("") || cmd.getSearchRecordType().equalsIgnoreCase("all")))) {
				errorArgs = new Object[] { "Search Type" };
				errors.rejectValue("searchRecordType", "error.radio_button_not_selected", errorArgs, "A required field is missing.");
			}
			if (cmd.getSearchType().equals("state")) {
				//validateString("mexicanSearchStates",   cmd.getMexicanSearchStates(), errors,  2, 150, regexBasicExtended, "State");
				if (cmd.getMexicanSearchStates() == null) {
					errorArgs = new Object[] { "State" };
					errors.rejectValue("mexicanSearchStates", "error.check_box_not_selected", errorArgs, "A required field is missing.");
				} else {
					logger.info("SearchMexicanRecorForm => States Selected: " + cmd.getMexicanSearchStates());
					StringTokenizer strTokens = new StringTokenizer(cmd.getMexicanSearchStates(),",");
					logger.info("SearchMexicanRecorForm => No. of state tokens: " + strTokens.countTokens());
					if (strTokens.countTokens() > 6) {
						logger.info("SearchMexicanRecorForm => No. of state tokens: " + strTokens.countTokens());
						errorArgs = new Object[] { "6", "States" };
						errors.rejectValue("mexicanSearchStates", "error.max_no_of_states_reached", errorArgs, "Maximum number of selection exceeded." );
						logger.info("SearchMexicanRecordForm => No. of state tokens: " + strTokens.countTokens());
					}
				}
			}
			if (cmd.getSearchType().equals("region")) {
				if (cmd.getMexicanSearchRegion() == null || (cmd.getMexicanSearchRegion() != null && cmd.getMexicanSearchRegion().equals(""))) {
					errorArgs = new Object[] { "Region" };
					errors.rejectValue("mexicanSearchRegion", "error.check_box_not_selected", errorArgs, "A required field is missing.");
				}
			}
		}		
	}
	
	// static lookups
	protected static final LinkedHashMap<String,String> mexicanSearchPurposes = new LinkedHashMap<String,String>();
	
	@ModelAttribute("mexicanSearchPurposes")
	public LinkedHashMap<String, String> getMexicanSearchPurposes() {
		return mexicanSearchPurposes;
	}
	
	protected final void populateMexicanSearchPurposes() {
		mexicanSearchPurposes.put("Personal",  "For personal use (non-FCRA)");
		mexicanSearchPurposes.put("Business",  "For international business use not restricted by FCRA rules");
		mexicanSearchPurposes.put("604.a1",    "Court order or subpoena. Section 604(a)(1)");
		mexicanSearchPurposes.put("604.a2",    "Instructed by consumer in writing. Section 604(a)(2)");
		mexicanSearchPurposes.put("604.a3b",   "Employment purposes with written permission. Section 604(a)(3)(B)");
		mexicanSearchPurposes.put("604.a3c",   "Consumer application for insurance. Section 604(a)(3)(C)");
		mexicanSearchPurposes.put("604.a3d",   "Business transaction initiated by consumer. Section 604(a)(3)(F)(i)");
		mexicanSearchPurposes.put("604.a3e",   "Determine if consumer meets terms of account. Section 604(a)(3)(F)(ii)");
		mexicanSearchPurposes.put("604.a3fi",  "Eligibility for a license or benefit. Section 604(a)(3)(D)");
		mexicanSearchPurposes.put("604.a3fii", "Use by potential investor, servicer or insurer. Section 604(a)(3)(E)");
	}
	
	// static lookups
	protected static final LinkedHashMap<String,String> mexicanSearchRegions = new LinkedHashMap<String,String>();
	
	@ModelAttribute("mexicanSearchRegions")
	public LinkedHashMap<String, String> getMexicanSearchRegions() {
		return mexicanSearchRegions;
	}
	
	protected final void populateMexicanSearchRegions() {
		mexicanSearchRegions.put("Baja California,Baja California Sur,Sonora,Chihuahua,Coahuila,Nuevo Leon,Tamaulipas",  "Region 1 - Northern Border States");
		mexicanSearchRegions.put("Federal District,State of Mexico,Hidalgo,Morelos",  "Region 2 - Mexico City Area");
		mexicanSearchRegions.put("Aguascalientes,Campeche,Chiapas,Colima,Durango,Guanajuato,Guerrero,Jalisco,Michoacan,Queretaro,Quintana Roo,Sinaloa,San Luis Potosi,Tabasco,Veracruz,Yucatan,Zacatecas",    "Region 3 - All other Central & Southern States");
	}
}