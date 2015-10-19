package springapp.web.funnel;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.searchsystems.limestone.BjlResults;

import org.apache.axiom.om.OMElement;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import springapp.web.funnel.BjlXMLbean.bjlAddressObj;
import springapp.web.funnel.BjlXMLbean.bjlFilingObj;
import springapp.web.funnel.BjlXMLbean.bjlNameObj;

public class parseXmlTester {
	private final static String liveUrl="https://www.tracersinfo.com/xmlgw.php";
	private final static String userID="SEASYSXML";
	private final static String password="quVa9h8B";
	
	
	 public static String requestXml()
	    {
		 String xml="";
		 xml="<?xml version='1.0'?>";
		 xml=xml+"<xmlgw>";
		 xml=xml+"<session>";
		 xml=xml+"<user_id>"+userID+"</user_id>";
		 xml=xml+"<password>"+password+"</password>";
		 xml=xml+"<customer_reference>"+"1"+"</customer_reference>";
		 xml=xml+"</session>";
		 xml=xml+"<search_options>";
		 xml=xml+"</search_options>";
		 xml=xml+"<search_request>";
		 xml=xml+"<search>"+"blj"+"</search>";
		 xml=xml+"<search_identifier>"+"1"+"</search_identifier>";
		 xml=xml+"<search_criteria>";
		 xml=xml+"<lastname>"+"john"+"</lastname>";
		 xml=xml+"<firstname>"+"smith"+"</firstname>";
		 xml=xml+"<middlename>"+""+"</middlename>";
		 xml=xml+"<ssn>"+""+"</ssn>";
		 xml=xml+"<state>"+"CA"+"</state>";
		 xml=xml+"</search_criteria>";
		 xml=xml+"</search_request>";
		 xml=xml+"</xmlgw>";
	    	return xml;
	    }

	public static void main(String[] args)
	{
		ArrayList resultList=new ArrayList();
		try{
			HttpClient client =new HttpClient();
			HttpClientParams params=client.getParams();
			//set client params
			
			params.setParameter(HttpClientParams.MAX_REDIRECTS, new Integer(0));
			params.setParameter(HttpClientParams.SO_TIMEOUT, new Integer(15*1000));
			client.getHttpConnectionManager().getParams().setConnectionTimeout(30 * 1000);
			PostMethod post=new PostMethod(liveUrl);
			String strXml=requestXml();
			System.out.println("Request Xml:"+strXml);
			post.setParameter("request", strXml);
			//NameValuePair xmlPost[] = {
					//new NameValuePair("request", strXml)
				//};
		
			//post.setRequestBody(xmlPost);
			post.getParams().setParameter(HttpClientParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0,false));
			int returncode=client.executeMethod(post);
			System.out.println("return code: "+returncode);
			String xmlResponse=post.getResponseBodyAsString();
			//System.out.println("xml response:"+xmlResponse);
			if(xmlResponse == null)
			{
				System.out.println("response xml empty");
			}
		
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlResponse.replace("&nbsp;", "&#160;"))));
//Reading from Local Disk
						
			
			//InputSource resxml = new InputSource(new FileInputStream(new File("C:/BJLResultnational.xml")));
			//Document doc=builder.parse(resxml);
			NodeList nList=null;
			//resultList=new ArrayList<BjlResults>();
			NodeList rowList=doc.getElementsByTagName("search_response");
			Node headNode =(Node) rowList.item(0);
			System.out.println("headnodes:"+headNode.getNodeName());
			String searchIdentifier=readXMLNode(headNode, "search_identifier");
			String customerRef=readXMLNode(headNode, "customer_refernece");
			String search=readXMLNode(headNode, "search");
			Integer rowsReturned=Integer.parseInt(readXMLNode(headNode, "rows_returned"));
			System.out.println("search identifier:"+searchIdentifier+" customer refernece:"+customerRef);
			System.out.println("search :"+search+" rows returned:"+rowsReturned);
			if(rowsReturned == -1)
			{
				String reasonstring=readXMLNode(headNode, "response_data");
				System.out.println("Reason Text:"+reasonstring);
			}
			nList=doc.getElementsByTagName("response_data");
			//System.out.println("parse data:"+BJLResultDetail(nList).toString());
			if(nList != null)
			{
				
				Hashtable BJLResultDisp=new Hashtable();
				BjlXMLbean bjlResult=new BjlXMLbean();
					
				Node node=nList.item(0);
				NodeList childNodeList=node.getChildNodes();
				for(int i=0;i<childNodeList.getLength();i++)
				{
				
					Node resultNode=childNodeList.item(i);
					if(resultNode.getNodeName().equals("response_row"))
					{
						NodeList resultNodeList=resultNode.getChildNodes();
						for(int j=0;j<resultNodeList.getLength();j++)
						{
							
							Node classNode=resultNodeList.item(j);
							if(classNode.getNodeName().equals("blj_name"))
							{
								bjlNameObj bljname=new bjlNameObj
								(
												readXMLNode(classNode, "name_filing_state"), 
												readXMLNode(classNode, "firstname"), 
												readXMLNode(classNode, "middlename"), 
												readXMLNode(classNode, "lastname"), 
												readXMLNode(classNode, "name_suffix"), 
												readXMLNode(classNode, "name_type"), 
												readXMLNode(classNode, "name_type_desc"), 
												readXMLNode(classNode, "ssn_taxid_flag"), 
												readXMLNode(classNode, "ssn"), 
												readXMLNode(classNode, "comment_sequence"), 
												readXMLNode(classNode, "comment_type"), 
												readXMLNode(classNode, "comment_type_desc")
										);
								
							bjlResult.bjlNameList.add(bljname);
						//	ArrayList bgname=bjlResult.getBjlNameList();
							//bjlNameObj nameblj=(bjlNameObj) bjlResult.getNameObj();
						//	System.out.println("name_filing_state:"+);
								continue;
							}
							if(classNode.getNodeName().equals("blj_address"))
							{
								bjlAddressObj addrObj=new bjlAddressObj(
								//bjlResult.setAddressObj(new bjlAddressObj(
										readXMLNode(classNode, "house_number"),
										readXMLNode(classNode, "street_direction"), 
										readXMLNode(classNode, "street_name"), 
										readXMLNode(classNode, "street_suffix"), 
									    readXMLNode(classNode, "apartment_num"), 
								        readXMLNode(classNode, "city"),
								        readXMLNode(classNode, "state"), 
								        readXMLNode(classNode, "zipcode")
										
								);
								bjlResult.bjlAddressList.add(addrObj);
								//System.out.println("Address:"+bjlResult.getBjlAddressList().toString());
								//bjlAddressObj address=(bjlAddressObj) bjlResult.getAddressObj();
								//System.out.println("house no:"+address.getHouse_number());
								
								continue;
							}
							if(classNode.getNodeName().equals("blj_filing"))
							{
								
								bjlFilingObj bfo=	new bjlFilingObj(readXMLNode(classNode, "filing_state"), 
												readXMLNode(classNode, "filing_group"), 
												readXMLNode(classNode, "filing_group_desc"), 
												readXMLNode(classNode, "filing_type"), 
												readXMLNode(classNode, "filing_type_desc"), 
												readXMLNode(classNode, "filing_date"), 
												readXMLNode(classNode, "filing_number"), 
												readXMLNode(classNode, "tax_lien_type"), readXMLNode(classNode, "tax_lien_type_desc"), 
												readXMLNode(classNode, "bankruptcy_type"), readXMLNode(classNode, "bankruptcy_type_desc"), 
												readXMLNode(classNode, "docket_number"), readXMLNode(classNode, "unlawful_detainer"), 
												readXMLNode(classNode, "initial_date"), readXMLNode(classNode, "initial_amount"), 
												readXMLNode(classNode, "initial_docket"), readXMLNode(classNode, "judgement_date"), 
												readXMLNode(classNode, "judgement_amount"), readXMLNode(classNode, "judgement_docket"), 
												readXMLNode(classNode, "remove_date"), readXMLNode(classNode, "remove_docket"), 
												readXMLNode(classNode, "dismissal_date"), readXMLNode(classNode, "dismissal_docket"), 
												readXMLNode(classNode, "asset_amount"), readXMLNode(classNode, "liability_amount"), 
												readXMLNode(classNode, "plaintiff"), readXMLNode(classNode, "beneficiary"), 
												readXMLNode(classNode, "situs"), readXMLNode(classNode, "trustee"), 
												readXMLNode(classNode, "county_code"), readXMLNode(classNode, "county_name"), 
												readXMLNode(classNode, "county_state"), readXMLNode(classNode, "court_code"), 
												readXMLNode(classNode, "court_desc"), readXMLNode(classNode, "court_state"), 
												readXMLNode(classNode, "assets_available"), readXMLNode(classNode, "perfected_date"), 
												readXMLNode(classNode, "action_state_code"), readXMLNode(classNode, "action_desc"), 
												readXMLNode(classNode, "disposition_state_code"), readXMLNode(classNode, "disposition_desc"), 
												readXMLNode(classNode, "amount"), readXMLNode(classNode, "release_date"), 
												readXMLNode(classNode, "release_number"), readXMLNode(classNode, "suit_case_number"), 
												readXMLNode(classNode, "suit_date"), readXMLNode(classNode, "suit_amount"), 
												readXMLNode(classNode, "satisfaction_date"), readXMLNode(classNode, "discharge_date"), 
												readXMLNode(classNode, "closed_date"), readXMLNode(classNode, "trust_deed_number"), 
												readXMLNode(classNode, "trust_deed_date"), readXMLNode(classNode, "sale_number"), 
												readXMLNode(classNode, "sale_date"), readXMLNode(classNode, "cancellation_number"), 
												readXMLNode(classNode, "cancellation_date"), readXMLNode(classNode, "sched_341_date"), 
												readXMLNode(classNode, "update_date")
										     );
								bjlResult.bjlFilingList.add(bfo);
										
								//bjlFilingObj fileo=(bjlFilingObj) bjlResult.getFilingObj();
								//System.out.println("fil no:"+fileo.getFiling_number());
								
								continue;
								}
							
							
							}
						
						}
					
					
				}
				BJLResultDisp.put("i:", bjlResult);
			//ArrayList<BjlXMLbean> cd=(ArrayList<BjlXMLbean>)bjlResult;
			BjlXMLbean xml=new BjlXMLbean();
				xml=bjlResult;
				ArrayList<bjlAddressObj> cd=null;
				ArrayList<bjlFilingObj> fob=null;
				ArrayList<bjlNameObj> nameObj=null;
				 cd = xml.bjlAddressList;
				 fob=xml.bjlFilingList;
				 nameObj=xml.bjlNameList;
				 List<Object> mergedList=new ArrayList<Object>(cd);
				 mergedList.addAll(nameObj);
				 mergedList.addAll(fob);
				 for (Object object : mergedList) {
					System.out.println("obj"+object);
					String s= object.toString();
					System.out.println("str"+s);
				}
				
				/*for (bjlAddressObj bjlXMLbean : cd) {
					 for(bjlFilingObj bfo:fob)
					 {
						 if(!bjlXMLbean.equals("") && bjlXMLbean != null)
						 {
					System.out.println("state:"+bjlXMLbean.getState());
						 }
						 if(!bfo.equals("") && bfo != null)
						 {
					System.out.println("Filing Number:"+bfo.getFiling_number()); 
						 }
				 }
				}*/
			/*System.out.println("size:"+BJLResultDisp.size());
				Enumeration keys=BJLResultDisp.keys();
				while (keys.hasMoreElements()) {
					Object object = (Object) keys.nextElement();
					BjlXMLbean bxb=(BjlXMLbean) BJLResultDisp.get(object);
					
				ArrayList<bjlAddressObj> pop=	bxb.bjlAddressList;
			for (bjlAddressObj bjlAddressObj : pop) {
					if(!bjlAddressObj.getHouse_number().equals(""))
					{
					System.out.println("house no:"+bjlAddressObj.getHouse_number());
					}
				}
				}*/
				
			}	
						
	
		}
		catch (Exception e) {
			System.err.println("Exception while XML parsing:"+e);
		}
	}/*
	public static Hashtable BJLResultDetail(NodeList nList)
	{
		BjlXMLbean bjlResult=new BjlXMLbean();
		Hashtable<String, BjlXMLbean> BJLResultDisp=new Hashtable();
		Node node=nList.item(0);
		NodeList childNodeList=node.getChildNodes();
		for(int i=0;i<childNodeList.getLength();i++)
		{
			Node resultNode=childNodeList.item(i);
			if(resultNode.getNodeName().equals("response_row"))
			{
				NodeList resultNodeList=resultNode.getChildNodes();
				for(int j=0;j<resultNodeList.getLength();j++)
				{
					Node classNode=resultNodeList.item(j);
					if(classNode.getNodeName().equals("blj_name"))
					{
						bjlNameObj bljname=new bjlNameObj
						(
										readXMLNode(classNode, "name_filing_state"), 
										readXMLNode(classNode, "firstname"), 
										readXMLNode(classNode, "middlename"), 
										readXMLNode(classNode, "lastname"), 
										readXMLNode(classNode, "name_suffix"), 
										readXMLNode(classNode, "name_type"), 
										readXMLNode(classNode, "name_type_desc"), 
										readXMLNode(classNode, "ssn_taxid_flag"), 
										readXMLNode(classNode, "ssn"), 
										readXMLNode(classNode, "comment_sequence"), 
										readXMLNode(classNode, "comment_type"), 
										readXMLNode(classNode, "comment_type_desc")
								);
						
					bjlResult.bjlNameList.add(bljname);
				//	ArrayList bgname=bjlResult.getBjlNameList();
					//bjlNameObj nameblj=(bjlNameObj) bjlResult.getNameObj();
				//	System.out.println("name_filing_state:"+);
						continue;
					}
					if(classNode.getNodeName().equals("blj_address"))
					{
						bjlResult.setAddressObj(new bjlAddressObj(
								readXMLNode(classNode, "house_number"),
								readXMLNode(classNode, "street_direction"), 
								readXMLNode(classNode, "street_name"), 
								readXMLNode(classNode, "street_suffix"), 
							    readXMLNode(classNode, "apartment_num"), 
						        readXMLNode(classNode, "city"),
						        readXMLNode(classNode, "state"), 
						        readXMLNode(classNode, "zipcode")
								)
						);
						//bjlAddressObj address=(bjlAddressObj) bjlResult.getAddressObj();
						//System.out.println("house no:"+address.getHouse_number());
						continue;
					}
					if(classNode.getNodeName().equals("blj_filing"))
					{
						bjlResult.setFilingObj(
								new bjlFilingObj(readXMLNode(classNode, "filing_state"), 
										readXMLNode(classNode, "filing_group"), 
										readXMLNode(classNode, "filing_group_desc"), 
										readXMLNode(classNode, "filing_type"), 
										readXMLNode(classNode, "filing_type_desc"), 
										readXMLNode(classNode, "filing_date"), 
										readXMLNode(classNode, "filing_number"), 
										readXMLNode(classNode, "tax_lien_type"), readXMLNode(classNode, "tax_lien_type_desc"), 
										readXMLNode(classNode, "bankruptcy_type"), readXMLNode(classNode, "bankruptcy_type_desc"), 
										readXMLNode(classNode, "docket_number"), readXMLNode(classNode, "unlawful_detainer"), 
										readXMLNode(classNode, "initial_date"), readXMLNode(classNode, "initial_amount"), 
										readXMLNode(classNode, "initial_docket"), readXMLNode(classNode, "judgement_date"), 
										readXMLNode(classNode, "judgement_amount"), readXMLNode(classNode, "judgement_docket"), 
										readXMLNode(classNode, "remove_date"), readXMLNode(classNode, "remove_docket"), 
										readXMLNode(classNode, "dismissal_date"), readXMLNode(classNode, "dismissal_docket"), 
										readXMLNode(classNode, "asset_amount"), readXMLNode(classNode, "liability_amount"), 
										readXMLNode(classNode, "plaintiff"), readXMLNode(classNode, "beneficiary"), 
										readXMLNode(classNode, "situs"), readXMLNode(classNode, "trustee"), 
										readXMLNode(classNode, "county_code"), readXMLNode(classNode, "county_name"), 
										readXMLNode(classNode, "county_state"), readXMLNode(classNode, "court_code"), 
										readXMLNode(classNode, "court_desc"), readXMLNode(classNode, "court_state"), 
										readXMLNode(classNode, "assets_available"), readXMLNode(classNode, "perfected_date"), 
										readXMLNode(classNode, "action_state_code"), readXMLNode(classNode, "action_desc"), 
										readXMLNode(classNode, "disposition_state_code"), readXMLNode(classNode, "disposition_desc"), 
										readXMLNode(classNode, "amount"), readXMLNode(classNode, "release_date"), 
										readXMLNode(classNode, "release_number"), readXMLNode(classNode, "suit_case_number"), 
										readXMLNode(classNode, "suit_date"), readXMLNode(classNode, "suit_amount"), 
										readXMLNode(classNode, "satisfaction_date"), readXMLNode(classNode, "discharge_date"), 
										readXMLNode(classNode, "closed_date"), readXMLNode(classNode, "trust_deed_number"), 
										readXMLNode(classNode, "trust_deed_date"), readXMLNode(classNode, "sale_number"), 
										readXMLNode(classNode, "sale_date"), readXMLNode(classNode, "cancellation_number"), 
										readXMLNode(classNode, "cancellation_date"), readXMLNode(classNode, "sched_341_date"), 
										readXMLNode(classNode, "update_date")
								     )
								);
						continue;
						}
					
				
				}
					
			}
		
				}
		BJLResultDisp.put("bjl", bjlResult);
		return BJLResultDisp;
	}
*/
	

	public static  String readXMLNode(Node firstNode, String strTagName){
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
