package springapp.web.funnel.mexico;

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

import springapp.web.funnel.mexico.SearchMexicanRecordForm.SearchMexicanRecordFormCommand;

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
public class ResultsMexicanRecordDisplay extends springapp.web.funnel.AbstractFunnelController {
	
	@RequestMapping(value = "/funnel/mexico/resultsMexicanRecords.do", method = RequestMethod.GET)
	public String getResults(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="pageNumber",required=false) String pageNumber
			)  {		
		logger.info("We are in mexican result page");
		if (test == null) { test = false; }
		if (pageNumber == null) { pageNumber = "1"; }
		SearchMexicanRecordFormCommand searchmrfc = (SearchMexicanRecordFormCommand) session.getAttribute("searchMexicanRecordFormCommand");
		logger.info("ResultsMexicanRecordDisplay => SearchMexicanRecordFormCommand: " + searchmrfc);
		String strTotalMatches = (String)session.getAttribute("teaserResultCount");
		logger.info("ResultsMexicanRecordDisplay => strTotalMatches: " + strTotalMatches);
		String strQueryId = (String)session.getAttribute("teaserQueryId");
		logger.info("ResultsMexicanRecordDisplay => strQueryId: " + strQueryId);
		
		if (searchmrfc == null || strTotalMatches == null || strQueryId == null) {
			return landingMexicanRecordRedir;
		}
		
		String strSearchName = searchmrfc.getMexicanSearchIndividualName();
				
		String strRowIndex = "0";
		String strRowsReturned = "";
		String strUserName = "searchsystems";
		String strPassword = "testsystems";
		String strXML = "";
		
		try {
			Hashtable<String, String> hData = (Hashtable<String, String>)session.getAttribute("searchData");
			if(hData == null) {
				//Assign result to strXML variable
				HttpClient client = new HttpClient();
				PostMethod post = new PostMethod("https://66.238.54.220:8443/SearchSystems/Search?method=GetSearchQueryResults");
				NameValuePair[] data = {
											new NameValuePair("UserID", strUserName),
											new NameValuePair("Password", strPassword),
											new NameValuePair("queryId", strQueryId)									
									   };
				post.setRequestBody(data);				 
				int returnCode = client.executeMethod(post);
				strXML = post.getResponseBodyAsString();
				strXML = strXML.replace("&nbsp;", "&#160;");
				hData = new Hashtable<String, String>();
				hData.put("1",strXML);
				post.releaseConnection();

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();   
				Document doc = builder.parse(new InputSource(new StringReader(strXML.replace("&nbsp;", "&#160;"))));
				
				NodeList nList = null;
				nList = doc.getElementsByTagName("ns1:wcfresponse");
				int total = 0;
				if(nList!=null){
					total = nList.getLength();	
					for(int s=0; s<total ; s++){
						Node firstNode = nList.item(s);
						String strQueryResult = readXMLNode(firstNode, "queryResult");
						if(strQueryResult !=null && !strQueryResult.equals("Failed")){
							strTotalMatches = readXMLNode(firstNode, "totalMatches");
							logger.info("ResultsMexicanRecordDisplay => First Response string - strTotalMatches: " + strTotalMatches);
							session.setAttribute("teaserResultCount",strTotalMatches);
							strRowIndex = readXMLNode(firstNode, "rowIndex");
							logger.info("ResultsMexicanRecordDisplay => First Response string - strRowIndex: " + strRowIndex);
							strRowsReturned = readXMLNode(firstNode, "rowsreturned");
							logger.info("ResultsMexicanRecordDisplay => First Response string - strRowsReturned: " + strRowsReturned);
							
							int intTotalMatches  = Integer.parseInt(strTotalMatches);
							int intTotalPage = 0;
							if(intTotalMatches!=0){
								if(intTotalMatches<=50){
									intTotalPage = 1;
								}
								else{
									intTotalPage = intTotalMatches/50;
									int intExtra = intTotalMatches%50;
									if(intExtra>0){
										intTotalPage = intTotalPage + 1;
									}
								}							 
							}

							for(int i=2;i<=intTotalPage;i++){
								post.setRequestBody(data);				 
								returnCode = client.executeMethod(post);
								strXML = post.getResponseBodyAsString();   							  
								strXML = strXML.replace("&nbsp;", "&#160;");
								hData.put(Integer.toString(i),strXML);
								logger.info("ResultsMexicanRecordDisplay => Index of result page stored in hashtable: " + Integer.toString(i) );
							}						
						}
					}
				}
				session.setAttribute("searchData",hData);
			} 
			
			strXML = (String)hData.get(pageNumber);
			//logger.info("ResultsMexicanRecordDisplay => page number: " + pageNumber + "; strXML of page: " + strXML );
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();   
			Document doc = builder.parse(new InputSource(new StringReader(strXML)));
			
			NodeList nList = null;
			nList = doc.getElementsByTagName("ns1:wcfresponse");
			int total = 0;
			if(nList!=null){
				total = nList.getLength();	
				for(int s=0; s<total ; s++){
					Node firstNode = nList.item(s);
					String strQueryResult = readXMLNode(firstNode, "queryResult");
					logger.info("strQueryResult = " + strQueryResult);
					if(strQueryResult !=null && !strQueryResult.equals("Failed")){
						strTotalMatches = readXMLNode(firstNode, "totalMatches");
						logger.info("strTotalMatches = " + strTotalMatches);
						strRowIndex = readXMLNode(firstNode, "rowIndex");
						logger.info("strRowIndex = " + strRowIndex );
						strRowsReturned = readXMLNode(firstNode, "rowsreturned");
						logger.info("strRowsReturned = " + strRowsReturned);
					}
				}
			}
			map.addAttribute("strRowIndex",strRowIndex);

			NodeList nList1 = null;
			nList1 = doc.getElementsByTagName("match");
			String datepub = "";
			String party1 = "";
			String hearingdate = "";
			String comment = "";
			String docket = "";
			String courtlocal = "";
			String party2 = "";
			String court = "";
			String location = "";
			String nature = "";
			String caption = "";
			if(nList1!=null){
				total = nList1.getLength();	
				Hashtable<String, Vector<String>> hList= new Hashtable<String, Vector<String>>();				
				for(int s=0; s<total ; s++){
					Node firstNode = nList1.item(s);
					datepub = readXMLNode(firstNode, "datepub");
					party1 = readXMLNode(firstNode, "party1");
					hearingdate = readXMLNode(firstNode, "hearingdate");
					comment = readXMLNode(firstNode, "comment");
					docket = readXMLNode(firstNode, "docket");
					courtlocal = readXMLNode(firstNode, "courtlocal");
					party2 = readXMLNode(firstNode, "party2");
					court = readXMLNode(firstNode, "court");
					location = readXMLNode(firstNode, "location");
					nature = readXMLNode(firstNode, "nature");
					caption = readXMLNode(firstNode, "caption");
					
					Vector<String> v = new Vector<String>();
					v.add(datepub);
					v.add(party1);
					v.add(hearingdate);
					v.add(comment);
					v.add(docket);
					v.add(courtlocal);
					v.add(party2);
					v.add(court);
					v.add(location);
					v.add(nature);
					v.add(caption);
					
					hList.put("chk" + s ,v);
					session.setAttribute("resultData",hList);
				}
			}
			map.addAttribute("searchName", strSearchName);
			
			return vwMexicanRecordResults;
			
		} catch (Exception e) {			
			logger.error("Error getting results", e);

			// send to error page
			return vwMexicanCommonError;			
		} 
	}
	
	
	// post handles checkboxes for view details selected
	@RequestMapping(value = "/funnel/mexico/ViewDetailsMexicanRecord.do", method = RequestMethod.POST)
	public String viewDetailedResults(
			WebRequest request,
			HttpSession session,
			ModelMap map
			)  {
		Map params = request.getParameterMap();
		for (Object o : params.keySet()) {
			logger.info("ResultsMexicanRecordDisplay => viewDetailedResults Parameters: " + o + ":" + params.get(o));
		}
		
		try {
			if(params.get("viewDetails.x") != null) {
				String strCheck[] = (String[])request.getParameterValues("chk");
				if(strCheck == null || strCheck.length == 0) {
					//Object[] errorArgs = new Object[] { "State" };
					//errors.reject("error.check_box_not_selected", errorArgs, "A required field is missing.");
					map.addAttribute("errorNoItemSelected","yes");
					return "redirect:resultsMexicanRecords.do";
				}
				map.addAttribute("selectedCourtRecordsIndex",strCheck);
				return vwMexicanRecordDetails;
			} else {
				map.addAttribute("highLightText", (String)request.getParameter("refineSearchText"));
				map.addAttribute("isHighLightText","yes");
				logger.info("ResultMexicanRecordDisplay => viewDetailedResults - request details: "+request.getDescription(false));
				return "redirect:resultsMexicanRecords.do?pageNumber=" + (String)request.getParameter("pageNumberDisplayed");
			}
		} catch (Exception e) {
			return vwMexicanCommonError;
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