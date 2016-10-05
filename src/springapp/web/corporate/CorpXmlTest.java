package springapp.web.corporate;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import springapp.domain.CorpAddress;
import springapp.domain.CorpHistory;
import springapp.domain.CorpNameInfo;
import springapp.domain.CorpResponses;
import springapp.repository.CorpDao;

@SuppressWarnings("rawtypes")
public class CorpXmlTest {
	@Autowired
	protected CorpDao corpDao;

	public static void main(String args[])
	{
		Integer rowsReturned=0;
		Integer totalMatches=0;
		ArrayList corpList=new ArrayList();
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	    	//Document doc = builder.parse(new InputSource(new StringReader(xmlResponse.replace("&nbsp;", "&#160;"))));
		 //local test mode state and international
		 //InputSource resxml = new InputSource(new FileInputStream(new File("C:/BJLResultnational.xml")));
		 InputSource resxml = new InputSource(new FileInputStream(new File("C:/corp.xml")));
		 Document doc=builder.parse(resxml);
		 NodeList nList=null;
	         
	    	NodeList rowList=doc.getElementsByTagName("search_response");
	    	Node headNode =(Node) rowList.item(0);
	    	System.out.println("headnodes:"+headNode.getNodeName());
	    	String searchIdentifier=readXMLNode(headNode, "search_identifier");
	    	String customerRef=readXMLNode(headNode, "customer_refernece");
	    	String search=readXMLNode(headNode, "search");
	    	rowsReturned=Integer.parseInt(readXMLNode(headNode, "rows_returned"));
	    	System.out.println("Rows Returnd:"+rowsReturned);
	    	if(!searchIdentifier.isEmpty())
    		{
	    		System.out.println("search identifier:"+searchIdentifier);
    		}
    	if(!customerRef.isEmpty())
    		{
    		System.out.println("customer refernece:"+customerRef);
    		}
    	if(!search.isEmpty() && search != null)
    		{
    		System.out.println("Search"+search);
    		}
    	if(rowsReturned.equals("-1"))
    	{
    		String reasonstring=readXMLNode(headNode, "response_data");
    		System.out.println("Reason Text:"+reasonstring);
    	}
    		nList=doc.getElementsByTagName("response_data");
    		totalMatches=nList.getLength();
    		if(totalMatches > 0 && totalMatches != 0)
    		{
    			Node node=nList.item(0);
    			NodeList childNodeList=node.getChildNodes();
    			CorpResponses cp=new CorpResponses();
    			corpList=new ArrayList<CorpResponses>();
    			for(int i=0;i<childNodeList.getLength();i++)
    			{
    			
    				Node resultNode=childNodeList.item(i);
    				if(resultNode.getNodeName().equals("response_row"))
    				{
    					NodeList resultNodeList=resultNode.getChildNodes();
    					for(int j=0;j<resultNodeList.getLength();j++)
    					{
    						
    						Node classNode=resultNodeList.item(j);
    						if(classNode.getNodeName().equals("sos_corp"))
    						{
    							cp.setFiling_number(readXMLNode(classNode, "filing_number"));
    							cp.setFiling_date(readXMLNode(classNode, "filing_date"));
    							cp.setFiling_state(readXMLNode(classNode, "filing_state"));
    							cp.setState(readXMLNode(classNode, "state"));
    							cp.setCorporation_id(readXMLNode(classNode, "corporation_id"));
    							cp.setCorporation_name(readXMLNode(classNode, "corporation_name"));
    							cp.setIncorp_date(readXMLNode(classNode, "incorp_date"));
    							cp.setCorporation_status(readXMLNode(classNode, "corporation_status"));
    							cp.setCorporation_status_date(readXMLNode(classNode, "corporation_status_date"));
    							cp.setCorporation_type(readXMLNode(classNode, "corporation_type"));
    							cp.setBus_type(readXMLNode(classNode, "bus_type"));
    							cp.setOriginated_state(readXMLNode(classNode, "originated_state"));
    							cp.setCounty(readXMLNode(classNode, "county"));
    							cp.setFederal_tax_id(readXMLNode(classNode, "federal_tax_id"));
    							cp.setDuration_date(readXMLNode(classNode, "duration_date"));
    							
    							//logger.info("Duration Date: "+cp.getDuration_date());
    							NodeList mergerList=classNode.getChildNodes();
    							String name="";
    							String name_type="";
    							
    							String a_address1="";
    							String a_address2="";
    							String a_address3="";
    							String a_city="";
    							String a_state="";
    							String a_zip_code="";
    							
    							for(int k=0;k < mergerList.getLength();k++)
    							{
    								Node mergerResultNode=mergerList.item(k);
    								
    								if(mergerResultNode.getNodeName().equals("corporation_name_information"))
    								{
    									cp.setCorporation_name_type_desc(readXMLNode(mergerResultNode, "corporation_name_type_desc"));
    									cp.setCn_corporation_name1(readXMLNode(mergerResultNode, "corporation_name"));
    									cp.setCn_effective_date(readXMLNode(mergerResultNode, "effective_date"));
    									
    									continue;
    								}
    								if(mergerResultNode.getNodeName().equals("name_information"))
    								{
    								    corpList=new ArrayList<CorpNameInfo>();
    									CorpNameInfo ni=new CorpNameInfo();
    								   
    								    ni.setFiling_number(cp.getFiling_number());
    								    ni.setName(readXMLNode(mergerResultNode, "name"));
    								    
    									ni.setName_type(readXMLNode(mergerResultNode, "name_type"));
    									
    									ni.setCurrent_active(readXMLNode(mergerResultNode, "current_active"));
    									
    									NodeList nameAddressList=mergerResultNode.getChildNodes();
    									String address1="";
    									String address2="";
    									String address3="";
    									String city="";
    									String state1="";
    									String zip_code="";
    									
    									
    									for (int l = 0; l < nameAddressList.getLength(); l++) {
    										Node nameAddressNode=nameAddressList.item(l);
    										if(nameAddressNode.getNodeName().equals("name_address"))
    										{
    											System.out.println("Corp Address:"+cp.getFiling_number());
    											//address1=address1+readXMLNode(nameAddressNode, "address1")+"@";
    											ni.setNa_address1(readXMLNode(nameAddressNode, "address1"));
    											//address2=address2+readXMLNode(nameAddressNode, "address2")+"@";
    											ni.setNa_address2(readXMLNode(nameAddressNode, "address2"));
    											//address3 = address3+readXMLNode(nameAddressNode, "address3")+"@";
    											ni.setNa_address3(readXMLNode(nameAddressNode, "address3"));
    											//city = city+readXMLNode(nameAddressNode, "city")+"@";
    											ni.setNa_city(city+readXMLNode(nameAddressNode, "city"));
    											//state1 = state1+readXMLNode(nameAddressNode, "state")+"@";
    											ni.setNa_state(readXMLNode(nameAddressNode, "state"));
    											//zip_code= zip_code+readXMLNode(nameAddressNode, "zip_code")+"@";
    											ni.setNa_zip_code(readXMLNode(nameAddressNode, "zip_code"));
    											
    											ni.setNa_address_type(readXMLNode(nameAddressNode, "address_type"));
    											
    											ni.setNa_current_address(readXMLNode(nameAddressNode, "current_address;"));
    											
    											ni.setNa_parsed_flag(readXMLNode(nameAddressNode, "parsed_flag"));
    											corpList.add(ni);
    											
    											continue;
    										}
    										
    									}
    									
    								}
    								if(mergerResultNode.getNodeName().equals("address"))
    								{
    									
    									corpList=new ArrayList<CorpAddress>();
    								   CorpAddress ca=new CorpAddress();
    								    ca.setFiling_number(cp.getFiling_number());
    									ca.setA_address1(readXMLNode(mergerResultNode, "address1"));
    									
    									//a_address2=a_address2+readXMLNode(mergerResultNode, "address2")+"@";
    									ca.setA_address2(readXMLNode(mergerResultNode, "address2"));
    									//a_address3=a_address3+readXMLNode(mergerResultNode, "address3")+"@";
    									ca.setA_address3(readXMLNode(mergerResultNode, "address3"));
    									//a_city = a_city+readXMLNode(mergerResultNode, "city")+"@";
    									ca.setA_city(readXMLNode(mergerResultNode, "city"));
    									//a_state = a_state+readXMLNode(mergerResultNode, "state")+"@";
    									ca.setA_state(readXMLNode(mergerResultNode, "state"));
    									//a_zip_code= a_zip_code+readXMLNode(mergerResultNode, "zip_code")+"@";
    									ca.setA_zip_code(readXMLNode(mergerResultNode, "zip_code"));
    									
    									ca.setA_address_type(readXMLNode(mergerResultNode, "address_type"));
    									
    									ca.setA_current_address(readXMLNode(mergerResultNode, "current_address"));
    									
    									ca.setA_effective_date(readXMLNode(mergerResultNode, "effective_date"));
    									
    									ca.setA_parsed_flag(readXMLNode(mergerResultNode, "parsed_flag"));
    									corpList.add(ca);
    									continue;
    								}
    								CorpHistory ch=new CorpHistory();
    								if(mergerResultNode.getNodeName().equals("other_info"))
    								{
    									ch.setInfo_title(readXMLNode(mergerResultNode, "info_title"));
    									ch.setInfo_desc(readXMLNode(mergerResultNode, "info_desc"));
    									
    									continue;
    								}
    								if(mergerResultNode.getNodeName().equals("corp_history"))
    								{
    								corpList=new ArrayList<CorpHistory>();
    								
    								    ch.setFiling_number(cp.getFiling_number());
    									ch.setHistory_pages(readXMLNode(mergerResultNode, "history_pages"));
    									ch.setHistory_code(readXMLNode(mergerResultNode, "history_code"));
    									ch.setHistory_desc(readXMLNode(mergerResultNode, "history_desc"));
    									ch.setEffective_date(readXMLNode(mergerResultNode, "effective_date"));
    									ch.setHistory_num(readXMLNode(mergerResultNode, "history_num"));
    									ch.setHistory_name(readXMLNode(mergerResultNode, "history_name"));
    									ch.setLocator_num(readXMLNode(mergerResultNode, "locator_num"));
    									corpList.add(ch);
    									continue;
    								}
    								
    							}
    							
    						}
    						
    					}
    					corpList.add(cp);
    					}
    				}
    			}
    		}
    			
    	
	
		catch (Exception e) {
			// TODO: handle exception
		}

System.out.println("size"+corpList.size());
	
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
