package springapp.web.corporate;

import java.util.Hashtable;
import java.util.Map;
import java.io.StringReader;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.apache.log4j.Logger;

import springapp.web.corporate.CorporateSearchLandingForm.SearchCorporateRecordFormCommand;
import springapp.web.corporate.xmlParsingHelper.addressObj;
import springapp.web.corporate.xmlParsingHelper.historyObj;
import springapp.web.corporate.xmlParsingHelper.mailingObj;
import springapp.web.corporate.xmlParsingHelper.mergerObj;
import springapp.web.corporate.xmlParsingHelper.officerObj;
import springapp.web.corporate.xmlParsingHelper.prinBusObj;
import springapp.web.corporate.xmlParsingHelper.stockObj;
import springapp.web.corporate.xmlParsingHelper.officerObj.offAddressObj;
import springapp.web.corporate.xmlParsingHelper.officerObj.offNameObj;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

/**
 * @author Ram Kumarappan
 *
 */

@Controller
public class ResultsCorporateRecordDisplay {
	
	protected Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping(value = "/corporate/resultsCorporateRecords.do", method = RequestMethod.GET)
	public String getResults(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test
			)  {		
		logger.info("We are in corporate result page");
		if (test == null) { test = false; }
		
		SearchCorporateRecordFormCommand searchcrfc = (SearchCorporateRecordFormCommand) session.getAttribute("searchCorporateRecordFormCommand");
		
		
		if (searchcrfc == null ) {
			return "redirect:corporateSearchLanding.do?type=company";
		}
		
		String strCompanyName = searchcrfc.getCorporateSearchCompanyName();
		String strOfficerName = searchcrfc.getCorporateSearchOfficerName();
		String strAddress = searchcrfc.getCorporateSearchAddress();
		String strCity=searchcrfc.getCorporateSearchCity();
		String strState=searchcrfc.getCorporateSearchState();
		
		String strXML=(String)session.getAttribute("strXML");
						
			try
			{
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();   
				Document doc = builder.parse(new InputSource(new StringReader(strXML.replace("&nbsp;", "&#160;"))));
				NodeList nList = null;
				nList = doc.getElementsByTagName("return");
				int total = 0;
				total=nList.getLength();
									
				if(nList!=null)
				{
					Hashtable searchResult= new Hashtable();
					
					for(int i=0;i<total;i++)
					{
						xmlParsingHelper xPH=new xmlParsingHelper();
						
						Node firstNode = nList.item(i);
						NodeList childNodes=firstNode.getChildNodes();
											
						for(int j=0; j<childNodes.getLength();j++)
						{
							Node firstChildNode=(Node)childNodes.item(j);
							if(firstChildNode.getNodeName()== "address")
							{
								xPH.setAObj(
										new addressObj(
												readXMLNode(firstChildNode,"address1"),
												readXMLNode(firstChildNode,"address2"),
												readXMLNode(firstChildNode,"city"),
												readXMLNode(firstChildNode,"country"),
												readXMLNode(firstChildNode,"county"),
												readXMLNode(firstChildNode,"state"),
												readXMLNode(firstChildNode,"stateCode"),
												readXMLNode(firstChildNode,"zip"), 
												readXMLNode(firstChildNode,"zip4")
												)
										);
									continue;
							}
							
							if(firstChildNode.getNodeName()== "officer" )
							{
								NodeList innerChildList=firstChildNode.getChildNodes();
								Node firstChildInNode=(Node)innerChildList;
								officerObj offObj=new officerObj
								(
										readXMLNode(firstChildNode,"corpFileKey"), readXMLNode(firstChildNode,"fax"),
										readXMLNode(firstChildNode,"fein"),readXMLNode(firstChildNode,"startDate"),
										readXMLNode(firstChildNode,"status"), 
										new offAddressObj
										(
												readXMLNode(firstChildInNode,"address1") , readXMLNode(firstChildInNode,"address2") ,
												readXMLNode(firstChildInNode,"city") ,	readXMLNode(firstChildInNode,"state") ,
												readXMLNode(firstChildInNode,"zip") , readXMLNode(firstChildInNode,"zip4")
										),
										new	offNameObj
										(
												readXMLNode(firstChildInNode,"first"), readXMLNode(firstChildInNode,"firstRest"),
												readXMLNode(firstChildInNode,"last"),	readXMLNode(firstChildInNode,"lastRest"),
												readXMLNode(firstChildInNode,"middle"),readXMLNode(firstChildInNode,"suffix") ,
												readXMLNode(firstChildInNode,"title") , readXMLNode(firstChildInNode,"unparsedName") 	
										)
								);
								
								
								xPH.officerList.add(offObj);
								continue;
							} 
							
							if(firstChildNode.getNodeName()== "merger")
							{
								
								mergerObj mObj=new mergerObj(
												readXMLNode(firstChildNode,"corpFileKey"),
												readXMLNode(firstChildNode,"eventDescription"),
												readXMLNode(firstChildNode,"mergeDate"),
												readXMLNode(firstChildNode,"mergedCorpID"),
												readXMLNode(firstChildNode,"mergedCorpName"),
												readXMLNode(firstChildNode,"stateCode"),
												readXMLNode(firstChildNode,"survivingCorpID")
												
												);
								xPH.mergerList.add(mObj);
							
								continue;							
							}
							
							if(firstChildNode.getNodeName()=="history")
							{
								historyObj hObj=new historyObj(
												readXMLNode(firstChildNode,"amendmentDate"),
												readXMLNode(firstChildNode,"amendmentType") ,
												readXMLNode(firstChildNode,"corpFileKey") , 
												readXMLNode(firstChildNode,"corporationName") ,
												readXMLNode(firstChildNode,"event") , 
												readXMLNode(firstChildNode,"stateCode")
												);

								xPH.historyList.add(hObj);
								continue;
							}
							
							if(firstChildNode.getNodeName()== "stock")
							{
								xPH.setSObj(
										new stockObj(
												readXMLNode(firstChildNode,"corpFileKey"),
												readXMLNode(firstChildNode,"stateCode") ,
												readXMLNode(firstChildNode,"stockClass") , 
												readXMLNode(firstChildNode,"stockDate") , 
												readXMLNode(firstChildNode,"stockParValue") ,
												readXMLNode(firstChildNode,"stockRestrictInd") , 
												readXMLNode(firstChildNode,"stockShareAuth")
												)
										);
				
								continue;
							}
							
							if(firstChildNode.getNodeName()== "mailingAddress")
							{
								xPH.setMObj(
										new mailingObj(
												readXMLNode(firstChildNode,"address1"),
												readXMLNode(firstChildNode,"address2"),
												readXMLNode(firstChildNode,"city"),
												readXMLNode(firstChildNode,"state"),
												readXMLNode(firstChildNode,"zip"), 
												readXMLNode(firstChildNode,"zip4")
												)
										);						
								continue;
							}
							
							if(firstChildNode.getNodeName()=="prinBusAddress")
							{
								xPH.setPrObj(
										new prinBusObj(
												readXMLNode(firstChildNode,"address1"),
												readXMLNode(firstChildNode,"address2"),
												readXMLNode(firstChildNode,"city"),
												readXMLNode(firstChildNode,"country"),
												readXMLNode(firstChildNode,"county"),
												readXMLNode(firstChildNode,"state"),
												readXMLNode(firstChildNode,"zip"), 
												readXMLNode(firstChildNode,"zip4")
												)
										);
								continue;
							}
							xPH.setBankruptDate(readXMLNode(firstNode, "bankruptDate"));
							xPH.setCapital(readXMLNode(firstNode, "capital"));
							xPH.setCorpDescription(readXMLNode(firstNode, "corpDescription"));
							xPH.setCorpFileKey(readXMLNode(firstNode, "corpFileKey"));
							xPH.setCorpStatus(readXMLNode(firstNode, "corpStatus"));
							xPH.setCorpStatusDate(readXMLNode(firstNode, "corpStatusDate"));
							xPH.setCorpType(readXMLNode(firstNode, "corpType"));
							xPH.setDBAName(readXMLNode(firstNode, "DBAName"));
							xPH.setEmail1(readXMLNode(firstNode, "email1"));
							xPH.setExpireDate(readXMLNode(firstNode, "expireDate"));
							xPH.setExt(readXMLNode(firstNode, "ext"));
							xPH.setFax(readXMLNode(firstNode, "fax"));
							xPH.setFileDataDate(readXMLNode(firstNode, "fileDataDate"));
							xPH.setFilingDate(readXMLNode(firstNode, "filingDate"));
							xPH.setFilingType(readXMLNode(firstNode, "filingType"));
							xPH.setHomeState(readXMLNode(firstNode, "homeState"));
							xPH.setIncorporationState(readXMLNode(firstNode, "incorporationState"));
							xPH.setJurisdiction(readXMLNode(firstNode, "jurisdiction"));
							xPH.setLastReportDate(readXMLNode(firstNode, "lastReportDate"));
							xPH.setName(readXMLNode(firstNode, "name"));
							xPH.setNonProfitType(readXMLNode(firstNode, "nonProfitType"));
							xPH.setPhone(readXMLNode(firstNode, "phone"));
							xPH.setPurpose(readXMLNode(firstNode, "purpose"));
							xPH.setRegistryNumber(readXMLNode(firstNode, "registryNumber"));
							xPH.setSIC(readXMLNode(firstNode, "SIC"));
							xPH.setStatute(readXMLNode(firstNode, "statute"));
							xPH.setTaxID(readXMLNode(firstNode, "taxID"));
							xPH.setTerm(readXMLNode(firstNode, "term")); 
										
						}
						// Putting XML object inside Hash table
						searchResult.put("cb"+i, xPH);
						
					}
					session.setAttribute("searchResult", searchResult);
				}
			
								
			if (searchcrfc.getSearchType().equalsIgnoreCase("company")) { 
				strCompanyName= searchcrfc.getCorporateSearchCompanyName();
				map.addAttribute("searchName", strCompanyName);
				}
			if (searchcrfc.getSearchType().equalsIgnoreCase("officer")) {
				strOfficerName = searchcrfc.getCorporateSearchOfficerName(); 
				map.addAttribute("searchName", strOfficerName);
				}
			if (searchcrfc.getSearchType().equalsIgnoreCase("address")) {
				strAddress = searchcrfc.getCorporateSearchAddress(); 
				map.addAttribute("searchName", strAddress);
				}
			    session.setAttribute("searchName", map.get("searchName"));
			return "corporate/corporateResults";
			
		} catch (Exception e) {			
			logger.error("Error getting results", e);

			// send to error page
			return "corporate/Error";			
		} 
	}
	
	
	// post handles checkboxes for view details selected
	@RequestMapping(value = "/corporate/resultsCorporateRecords.do", method = RequestMethod.POST)
	public String viewDetailedResults(
			WebRequest request,
			HttpSession session,
			ModelMap map
			)  {
		Map params = request.getParameterMap();
		for (Object o : params.keySet()) {
			logger.info("ResultsCorporateRecordDisplay => viewDetailedResults Parameters: " + o + ":" + params.get(o));
		}
		
		try {
			//if(params.get("viewDetails.x") != null) {
				String strCheck[] = (String[])request.getParameterValues("chk");
				if(strCheck == null || strCheck.length == 0) {
					//Object[] errorArgs = new Object[] { "State" };
					//errors.reject("error.check_box_not_selected", errorArgs, "A required field is missing.");
					map.addAttribute("errorNoItemSelected","yes");
					return "redirect:resultsCorporateRecords.do";
				}
				map.addAttribute("selectedCorporateRecordsIndex",strCheck);
				for(int i=0;i<strCheck.length;i++)
					logger.info("Selected chk bok key is ::"+strCheck[i]);
				return "corporate/corporateViewPrint";
			//} 
		} catch (Exception e) {
			return "corporate/Error";
		}
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