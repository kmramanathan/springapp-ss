package springapp.web.flatrate;
import java.io.File;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import springapp.domain.FRBGCResponse;


public class testParser
{
	
	public static void main(String args[])
	{
		
	
		Hashtable resHash=null;
		
		try
		{
			
			HttpClient client = new HttpClient();
			client.getParams().setAuthenticationPreemptive(true);
			
		    String postUrl="https://www.nationalpublicdata.net/feeds/FDSFeed.cfm";
		    String userName = "searchsystemsplans";
		    String passWord= "p1623dw";
			PostMethod post = new PostMethod(postUrl);
			
			Credentials defaultcreds = new UsernamePasswordCredentials(
					userName, passWord);
			client.getState().setCredentials(AuthScope.ANY, defaultcreds);
			
			String strXML = getXmlRequest();
			System.out.println("Request XML is :: "+strXML);
			
			NameValuePair[] XML= {new NameValuePair("XML",strXML) };
			post.setQueryString(XML);
			int returnCode = client.executeMethod(post);
			String resXML = post.getResponseBodyAsString();
			System.out.println("Response XML is :: "+resXML);
			System.out.println("Return code :"+returnCode );
				
			//wrinting into file
			File BGresult=new File("c:/BGC.txt");
			PrintStream p=new PrintStream(BGresult);
			p.print(resXML);
		
			HashMap<String, FRBGCResponse> resMap = parseResultXml(resXML);
			System.out.println(resMap.size());
		}
		catch (Exception e) {	
			System.out.println("Exception :"+e.getMessage() +" Cause :"+e.getCause()+" Class +"+e.getClass());
			System.out.println(resHash);
		}
       
	}
	
	public static String getXmlRequest()
	{
		Logger logger = Logger.getRootLogger();
		String strXML="";
			strXML= "<FDSRequest>";
			strXML = strXML+ "<username>searchsystemsplans</username>";
			strXML = strXML+ "<password>p1623dw</password>";
			strXML = strXML+ "<sType>NCRIM</sType>";
			strXML = strXML+ "<detail>1</detail>";
			strXML = strXML+ "<testmode>false</testmode>";
			strXML = strXML+ "<searchParams>" ;
			strXML = strXML+ "<firstName>asdfa</firstName>" ;
			strXML = strXML+ "<lastName>dsfda</lastName>" ;
			strXML = strXML+ "<state>ALL</state>";
			strXML = strXML+ "<DOB>10/10/1984</DOB>" ;
			strXML = strXML+ "</searchParams>" ;
			strXML = strXML+ "</FDSRequest>";	
			return strXML;
		
	}
		
	public static HashMap<String, FRBGCResponse> parseResultXml(String resultXml){
		int resultID = 0;
		HashMap<String, FRBGCResponse> resultMap = new HashMap<String, FRBGCResponse>();
		try{	
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();   
			Document doc = builder.parse(new InputSource(new StringReader(resultXml.replace("&nbsp;", "&#160;"))));
				
			//parsing criminal records
			NodeList CRNodeList = doc.getElementsByTagName("CriminalSearch");
			Node CRNode = CRNodeList.item(0);
			NodeList CRresults = CRNode.getChildNodes();
			for(int i=0; i<CRresults.getLength(); i++){
				Node resultNode = CRresults.item(i);
				if(resultNode.getNodeName().equals("Result") && resultNode.hasChildNodes()){
						resultMap.put(String.valueOf(++resultID), 
							new FRBGCResponse(String.valueOf(resultID), readXMLNode(resultNode, "lastname"), readXMLNode(resultNode, "firstname"),  
									readXMLNode(resultNode, "middlename"), readXMLNode(resultNode, "dob"), readXMLNode(resultNode, "address"), readXMLNode(resultNode, "address2"), 
									readXMLNode(resultNode, "city"), readXMLNode(resultNode, "state"), readXMLNode(resultNode, "zip"), readXMLNode(resultNode, "haircolor"), 
									readXMLNode(resultNode, "eyecolor"), readXMLNode(resultNode, "height"), readXMLNode(resultNode, "weight"), readXMLNode(resultNode, "race"), 
									readXMLNode(resultNode, "sex"), "", readXMLNode(resultNode, "SourceName"), readXMLNode(resultNode, "SourceState"), 
									"", readXMLNode(resultNode, "offensedescription1"), readXMLNode(resultNode, "offensedescription2"), readXMLNode(resultNode, "Disposition"), 
									readXMLNode(resultNode, "Dispositiondate"), readXMLNode(resultNode, "ArrestingAgency"), readXMLNode(resultNode, "SentenceYYYMMDDD"), readXMLNode(resultNode, "ProbationYYYMMDDD"), 
									readXMLNode(resultNode, "OffenseDate"), readXMLNode(resultNode, "Plea"), readXMLNode(resultNode, "casenumber"), readXMLNode(resultNode, "Fines"), readXMLNode(resultNode, "OffenseCode")));
				}
			}
			
			//parsing sex offenders records
			NodeList SONodeList = doc.getElementsByTagName("SORSearch");
			Node SONode = SONodeList.item(0);
			NodeList SOresults = SONode.getChildNodes();
			for(int i=0; i<SOresults.getLength(); i++){
				Node resultNode = SOresults.item(i);
				if(resultNode.getNodeName().equals("Result") && resultNode.hasChildNodes()){
					resultMap.put(String.valueOf(++resultID), 
							new FRBGCResponse(String.valueOf(resultID), readXMLNode(resultNode, "lastname"), readXMLNode(resultNode, "firstname"),  
									readXMLNode(resultNode, "middlename"), readXMLNode(resultNode, "dob"), readXMLNode(resultNode, "address"), readXMLNode(resultNode, "address2"), 
									readXMLNode(resultNode, "city"), readXMLNode(resultNode, "state"), readXMLNode(resultNode, "zip"), readXMLNode(resultNode, "haircolor"), 
									readXMLNode(resultNode, "eyecolor"), readXMLNode(resultNode, "height"), readXMLNode(resultNode, "weight"), readXMLNode(resultNode, "race"), 
									readXMLNode(resultNode, "sex"), readXMLNode(resultNode, "county"), "N/A", readXMLNode(resultNode, "source"), 
									readXMLNode(resultNode, "Aliases"), readXMLNode(resultNode, "offensedescription1"), readXMLNode(resultNode, "offensedescription2"), "", 
									"", "","",readXMLNode(resultNode, "convictiondate"), 
									readXMLNode(resultNode, "offensedate"), "", readXMLNode(resultNode, "casenumber"), "", readXMLNode(resultNode, "offensecode")));
				}
			}
					 	 
	 	}catch(Exception e){
	 		System.out.println("exception while parsing XML"+ e);
	 	}
	 	return resultMap;
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
	
	
	
