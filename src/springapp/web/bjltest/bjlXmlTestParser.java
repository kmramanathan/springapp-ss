package springapp.web.bjltest;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import springapp.web.funnel.BjlXMLbean.bjlAddressObj;
import springapp.web.funnel.BjlXMLbean.bjlFilingObj;
import springapp.web.funnel.BjlXMLbean.bjlNameObj;

public class bjlXmlTestParser {

	/**
	 * @param args
	 */
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
		 xml=xml+"<state>"+""+"</state>";
		 xml=xml+"</search_criteria>";
		 xml=xml+"</search_request>";
		 xml=xml+"</xmlgw>";
	    	return xml;
	    }


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList bjlResult=new ArrayList();
		ArrayList<xmlParserHelper> parsedata=null;
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			//Document doc = builder.parse(new InputSource(new StringReader(xmlResponse.replace("&nbsp;", "&#160;"))));
//Reading from Local Disk
						
			
			InputSource resxml = new InputSource(new FileInputStream(new File("C:/BJLResultnational.xml")));
			//InputSource resxml = new InputSource(new FileInputStream(new File("C:/BJLResult.xml")));
			Document doc=builder.parse(resxml);
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
				
				//ArrayList<xmlParserHelper> xp=new ArrayList<xmlParserHelper>();
				xmlParserHelper xp=new xmlParserHelper();
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
								//xmlParserHelper xp=new xmlParserHelper();
												xp.setName_filing_state(readXMLNode(classNode, "name_filing_state")); 
												xp.setFirstname(readXMLNode(classNode, "firstname")); 
												xp.setMiddlename(readXMLNode(classNode, "middlename"));
												xp.setLastname(readXMLNode(classNode, "lastname")); 
												xp.setName_suffix(readXMLNode(classNode, "name_suffix")); 
												xp.setName_type(readXMLNode(classNode, "name_type")); 
												xp.setName_type_desc(readXMLNode(classNode, "name_type_desc"));
												xp.setSsn_taxid_flag(readXMLNode(classNode, "ssn_taxid_flag"));
												xp.setSsn(readXMLNode(classNode, "ssn"));
												xp.setComment_sequence(readXMLNode(classNode, "comment_sequence")); 
												xp.setComment_type(readXMLNode(classNode, "comment_type")); 
												xp.setComment_type_desc(readXMLNode(classNode, "comment_type_desc"));
												
											
							
					
								continue;
							}
							if(classNode.getNodeName().equals("blj_address"))
							{
								
								//xmlParserHelper xp=new xmlParserHelper();
										xp.setHouse_number(readXMLNode(classNode, "house_number"));
										xp.setStreet_direction(readXMLNode(classNode, "street_direction")); 
										xp.setStreet_name(readXMLNode(classNode, "street_name")); 
										xp.setStreet_suffix(readXMLNode(classNode, "street_suffix")); 
									    xp.setApartment_num(readXMLNode(classNode, "apartment_num")); 
								        xp.setCity(readXMLNode(classNode, "city"));
								        xp.setState(readXMLNode(classNode, "state")); 
								        xp.setZipcode(readXMLNode(classNode, "zipcode"));
								    	
								continue;
							}
							 
							if(classNode.getNodeName().equals("blj_filing"))
							{
								//xmlParserHelper xp=new xmlParserHelper();
												xp.setFiling_state(readXMLNode(classNode, "filing_state"));
												//System.out.println(xp.getFiling_state());
												xp.setFiling_group(readXMLNode(classNode, "filing_group")); 
												xp.setFiling_group_desc(readXMLNode(classNode, "filing_group_desc")); 
												xp.setFiling_type(readXMLNode(classNode, "filing_type")); 
												xp.setFiling_type_desc(readXMLNode(classNode, "filing_type_desc")); 
												xp.setFiling_date(readXMLNode(classNode, "filing_date")); 
												xp.setFiling_number(readXMLNode(classNode, "filing_number"));
												//System.out.println("filingnumber:"+xp.getFiling_number());
												xp.setTax_lien_type_desc(readXMLNode(classNode, "tax_lien_type")); 
												xp.setTax_lien_type(readXMLNode(classNode, "tax_lien_type_desc")); 
												xp.setBankruptcy_type(readXMLNode(classNode, "bankruptcy_type")); 
												xp.setBankruptcy_type_desc(readXMLNode(classNode, "bankruptcy_type_desc")); 
												xp.setDocket_number(readXMLNode(classNode, "docket_number")); 
												xp.setUnlawful_detainer(readXMLNode(classNode, "unlawful_detainer")); 
												xp.setInitial_date(readXMLNode(classNode, "initial_date")); 
												xp.setInitial_amount(readXMLNode(classNode, "initial_amount")); 
												xp.setInitial_docket(readXMLNode(classNode, "initial_docket")); 
												xp.setJudgement_date(readXMLNode(classNode, "judgement_date")); 
												xp.setJudgement_amount(readXMLNode(classNode, "judgement_amount")); 
												xp.setJudgement_docket(readXMLNode(classNode, "judgement_docket")); 
												xp.setRemove_date(readXMLNode(classNode, "remove_date")); 
												xp.setRemove_docket(readXMLNode(classNode, "remove_docket")); 
												xp.setDismissal_date(readXMLNode(classNode, "dismissal_date")); 
												xp.setDismissal_docket(readXMLNode(classNode, "dismissal_docket")); 
												xp.setAsset_amount(readXMLNode(classNode, "asset_amount")); 
												xp.setLiability_amount(readXMLNode(classNode, "liability_amount")); 
												xp.setPlaintiff(readXMLNode(classNode, "plaintiff")); 
												xp.setBeneficiary(readXMLNode(classNode, "beneficiary")); 
												xp.setSitus(readXMLNode(classNode, "situs")); 
												xp.setTrustee(readXMLNode(classNode, "trustee")); 
												xp.setCounty_code(readXMLNode(classNode, "county_code")); 
												xp.setCounty_name(readXMLNode(classNode, "county_name")); 
												xp.setCounty_state(readXMLNode(classNode, "county_state")); 
												xp.setCourt_code(readXMLNode(classNode, "court_code")); 
												xp.setCourt_desc(readXMLNode(classNode, "court_desc")); 
												xp.setCourt_state(readXMLNode(classNode, "court_state")); 
												xp.setAssets_available(readXMLNode(classNode, "assets_available")); 
												xp.setPerfected_date(readXMLNode(classNode, "perfected_date")); 
												xp.setAction_state_code(readXMLNode(classNode, "action_state_code")); 
												xp.setAction_desc(readXMLNode(classNode, "action_desc")); 
												xp.setDisposition_state_code(readXMLNode(classNode, "disposition_state_code")); 
												xp.setDisposition_desc(readXMLNode(classNode, "disposition_desc")); 
												xp.setAmount(readXMLNode(classNode, "amount")); 
												xp.setRelease_date(readXMLNode(classNode, "release_date")); 
												xp.setRelease_number(readXMLNode(classNode, "release_number")); 
												xp.setSuit_case_number(readXMLNode(classNode, "suit_case_number")); 
												xp.setSuit_date(readXMLNode(classNode, "suit_date")); 
												xp.setSuit_amount(readXMLNode(classNode, "suit_amount")); 
												xp.setSatisfaction_date(readXMLNode(classNode, "satisfaction_date")); 
												xp.setDischarge_date(readXMLNode(classNode, "discharge_date")); 
												xp.setClosed_date(readXMLNode(classNode, "closed_date")); 
												xp.setTrust_deed_number(readXMLNode(classNode, "trust_deed_number")); 
												xp.setTrust_deed_date(readXMLNode(classNode, "trust_deed_date")); 
												xp.setSale_number(readXMLNode(classNode, "sale_number")); 
												xp.setSale_date(readXMLNode(classNode, "sale_date")); 
												xp.setCancellation_number(readXMLNode(classNode, "cancellation_number")); 
												xp.setCancellation_date(readXMLNode(classNode, "cancellation_date")); 
												xp.setSched_341_date(readXMLNode(classNode, "sched_341_date")); 
												xp.setUpdate_date(readXMLNode(classNode, "update_date"));
												
										   continue;
										  
								}
							
						
							}
						if(xp.getLastname().equals("JOHN Q SMITH"))
						{
						 System.out.println("last name:"+xp.getLastname());
						}
						// System.out.println("last name:"+xp.getLastname().length());
						//bjlResult.add(xp);
						
						}
					
					}
				
			
			/*parsedata=bjlResult;
				
				for (xmlParserHelper xmlParserHelper : parsedata) {
					if(xmlParserHelper != null)
					{
					System.out.println("Filing Number:"+xmlParserHelper.getFiling_number());
					}
				}*/	
				}
		}
		catch (Exception e) {
			System.out.println("cause:"+e);
		}
		

	}
	private static ArrayList ArrayList(xmlParserHelper xp) {
		// TODO Auto-generated method stub
		return null;
	}
	public static ArrayList bjlParseDisp(NodeList nList){
		ArrayList bjlResult=new ArrayList<xmlParserHelper>();
		//ArrayList<xmlParserHelper> xp=new ArrayList<xmlParserHelper>();
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
						xmlParserHelper xp=new xmlParserHelper();
										xp.setName_filing_state(readXMLNode(classNode, "name_filing_state")); 
										xp.setFirstname(readXMLNode(classNode, "firstname")); 
										xp.setMiddlename(readXMLNode(classNode, "middlename"));
										xp.setLastname(readXMLNode(classNode, "lastname")); 
										xp.setName_suffix(readXMLNode(classNode, "name_suffix")); 
										xp.setName_type(readXMLNode(classNode, "name_type")); 
										xp.setName_type_desc(readXMLNode(classNode, "name_type_desc"));
										xp.setSsn_taxid_flag(readXMLNode(classNode, "ssn_taxid_flag"));
										xp.setSsn(readXMLNode(classNode, "ssn"));
										xp.setComment_sequence(readXMLNode(classNode, "comment_sequence")); 
										xp.setComment_type(readXMLNode(classNode, "comment_type")); 
										xp.setComment_type_desc(readXMLNode(classNode, "comment_type_desc"));
									bjlResult.add(xp);	
					
			
						continue;
					}
					if(classNode.getNodeName().equals("blj_address"))
					{
						
						xmlParserHelper xp=new xmlParserHelper();
								xp.setHouse_number(readXMLNode(classNode, "house_number"));
								xp.setStreet_direction(readXMLNode(classNode, "street_direction")); 
								xp.setStreet_name(readXMLNode(classNode, "street_name")); 
								xp.setStreet_suffix(readXMLNode(classNode, "street_suffix")); 
							    xp.setApartment_num(readXMLNode(classNode, "apartment_num")); 
						        xp.setCity(readXMLNode(classNode, "city"));
						        xp.setState(readXMLNode(classNode, "state")); 
						        xp.setZipcode(readXMLNode(classNode, "zipcode"));
						      bjlResult.add(xp);
						continue;
					}
					if(classNode.getNodeName().equals("blj_filing"))
					{
						xmlParserHelper xp=new xmlParserHelper();
										xp.setFiling_state(readXMLNode(classNode, "filing_state"));
										//System.out.println(xp.getFiling_state());
										xp.setFiling_group(readXMLNode(classNode, "filing_group")); 
										xp.setFiling_group_desc(readXMLNode(classNode, "filing_group_desc")); 
										xp.setFiling_type(readXMLNode(classNode, "filing_type")); 
										xp.setFiling_type_desc(readXMLNode(classNode, "filing_type_desc")); 
										xp.setFiling_date(readXMLNode(classNode, "filing_date")); 
										xp.setFiling_number(readXMLNode(classNode, "filing_number"));
										//System.out.println(xp.getFiling_number());
										xp.setTax_lien_type_desc(readXMLNode(classNode, "tax_lien_type")); 
										xp.setTax_lien_type(readXMLNode(classNode, "tax_lien_type_desc")); 
										xp.setBankruptcy_type(readXMLNode(classNode, "bankruptcy_type")); 
										xp.setBankruptcy_type_desc(readXMLNode(classNode, "bankruptcy_type_desc")); 
										xp.setDocket_number(readXMLNode(classNode, "docket_number")); 
										xp.setUnlawful_detainer(readXMLNode(classNode, "unlawful_detainer")); 
										xp.setInitial_date(readXMLNode(classNode, "initial_date")); 
										xp.setInitial_amount(readXMLNode(classNode, "initial_amount")); 
										xp.setInitial_docket(readXMLNode(classNode, "initial_docket")); 
										xp.setJudgement_date(readXMLNode(classNode, "judgement_date")); 
										xp.setJudgement_amount(readXMLNode(classNode, "judgement_amount")); 
										xp.setJudgement_docket(readXMLNode(classNode, "judgement_docket")); 
										xp.setRemove_date(readXMLNode(classNode, "remove_date")); 
										xp.setRemove_docket(readXMLNode(classNode, "remove_docket")); 
										xp.setDismissal_date(readXMLNode(classNode, "dismissal_date")); 
										xp.setDismissal_docket(readXMLNode(classNode, "dismissal_docket")); 
										xp.setAsset_amount(readXMLNode(classNode, "asset_amount")); 
										xp.setLiability_amount(readXMLNode(classNode, "liability_amount")); 
										xp.setPlaintiff(readXMLNode(classNode, "plaintiff")); 
										xp.setBeneficiary(readXMLNode(classNode, "beneficiary")); 
										xp.setSitus(readXMLNode(classNode, "situs")); 
										xp.setTrustee(readXMLNode(classNode, "trustee")); 
										xp.setCounty_code(readXMLNode(classNode, "county_code")); 
										xp.setCounty_name(readXMLNode(classNode, "county_name")); 
										xp.setCounty_state(readXMLNode(classNode, "county_state")); 
										xp.setCourt_code(readXMLNode(classNode, "court_code")); 
										xp.setCourt_desc(readXMLNode(classNode, "court_desc")); 
										xp.setCourt_state(readXMLNode(classNode, "court_state")); 
										xp.setAssets_available(readXMLNode(classNode, "assets_available")); 
										xp.setPerfected_date(readXMLNode(classNode, "perfected_date")); 
										xp.setAction_state_code(readXMLNode(classNode, "action_state_code")); 
										xp.setAction_desc(readXMLNode(classNode, "action_desc")); 
										xp.setDisposition_state_code(readXMLNode(classNode, "disposition_state_code")); 
										xp.setDisposition_desc(readXMLNode(classNode, "disposition_desc")); 
										xp.setAmount(readXMLNode(classNode, "amount")); 
										xp.setRelease_date(readXMLNode(classNode, "release_date")); 
										xp.setRelease_number(readXMLNode(classNode, "release_number")); 
										xp.setSuit_case_number(readXMLNode(classNode, "suit_case_number")); 
										xp.setSuit_date(readXMLNode(classNode, "suit_date")); 
										xp.setSuit_amount(readXMLNode(classNode, "suit_amount")); 
										xp.setSatisfaction_date(readXMLNode(classNode, "satisfaction_date")); 
										xp.setDischarge_date(readXMLNode(classNode, "discharge_date")); 
										xp.setClosed_date(readXMLNode(classNode, "closed_date")); 
										xp.setTrust_deed_number(readXMLNode(classNode, "trust_deed_number")); 
										xp.setTrust_deed_date(readXMLNode(classNode, "trust_deed_date")); 
										xp.setSale_number(readXMLNode(classNode, "sale_number")); 
										xp.setSale_date(readXMLNode(classNode, "sale_date")); 
										xp.setCancellation_number(readXMLNode(classNode, "cancellation_number")); 
										xp.setCancellation_date(readXMLNode(classNode, "cancellation_date")); 
										xp.setSched_341_date(readXMLNode(classNode, "sched_341_date")); 
										xp.setUpdate_date(readXMLNode(classNode, "update_date"));
										bjlResult.add(xp);
								   continue;
						}
					
				
					}
				
				}
			
			}
		
		
		return bjlResult;
	}
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
