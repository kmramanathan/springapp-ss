import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.omg.CORBA.PUBLIC_MEMBER;


public class bjltest {

	/**
	 * @param args
	 */
	private final static String liveUrl="https://www.tracersinfo.com/xmlgw.php";
	private final static String userID="SEASYSXML";
	private final static String password="quVa9h8B";
    
	public static void main(String[] args) {
		HttpClient client=new HttpClient();
		StringBuffer sb=new StringBuffer();
		HashMap<String, String> hmap=new HashMap<String, String>();
	    PostMethod post=new PostMethod(liveUrl);
	
	String strXML=requestXml();
	System.out.println("request xml:"+strXML);
	
	post.setParameter("request", strXML);
	
	
    try{
    	int returncode=client.executeMethod(post);
    	
    	//BufferedReader bf=new BufferedReader
    	BufferedReader reader=new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));
    	String line=null;
    	while ((line = reader.readLine()) != null) {
    		sb.append(line+"\n");
			
		}
    	reader.close();
    	System.out.println("return:"+returncode);
    	//writing into file
    	File resultXml=new File("G:/jackson.xml");
    	PrintStream ps=new PrintStream(resultXml);
    	ps.print(sb.toString());
    	System.out.println(sb.toString());
    	
    }
    catch (Exception e) {
    	
		// TODO: handle exception
    	System.out.println("Exception:"+e);
	}
   
	}
	 public static String requestXml()
	    {
		 String xml="";
		 xml="<?xml version='1.0'?>";
		 xml=xml+"<xmlgw>";
		 xml=xml+"<session>";
		 xml=xml+"<user_id>"+userID+"</user_id>";
		 xml=xml+"<password>"+password+"</password>";
		 xml=xml+"<customer_reference>"+""+"</customer_reference>";
		 xml=xml+"</session>";
		
		 xml=xml+"<search_request>";
		 xml=xml+"<search>"+"Eviction"+"</search>";
		 xml=xml+"<search_identifier>"+"S002"+"</search_identifier>";
		 xml=xml+"<search_criteria>";
		 xml=xml+"<defendant_businessname>"+"jackson david"+"</defendant_businessname>";
		 xml=xml+"<state>"+"GA"+"</state>";
		 xml=xml+"</search_criteria>";
		 xml=xml+"</search_request>";
		 xml=xml+"</xmlgw>";
	    	return xml;
	    }

}
