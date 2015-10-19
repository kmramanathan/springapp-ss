package springapp.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class NssTest {

	/**
	 * @param args
	 */
	public static final String bgcPostUrlTest = "https://model.backgroundchecks.com/integration/bgcdirectpost2.aspx";
	public static final String bgcPostUrlLive = "https://direct.backgroundchecks.com/integration/bgcdirectpost2.aspx" ;
	private static final String bgcUser = "searchsystems";
	private static final String bgcPassword = "J@$0n11";
	private static final String bgcAccount = "10009807";
	private static final String firstname="John";
	private static final String lastname="Smith";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HttpClient client= new HttpClient();
		StringBuffer sb=new StringBuffer();
		PostMethod post=new PostMethod(bgcPostUrlTest);
		
		String strXml=requestXML();
		System.out.println("request xml:"+strXml);
		
		post.setParameter("XML", strXml);
		
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
		    	File resultXml=new File("D:/NationalSecurity/NSS_"+lastname+".xml");
		    	//File resultXml=new File("D:/NationalSecurity/NSS_offender.xml");
		    	PrintStream ps=new PrintStream(resultXml);
		    	ps.print(sb.toString());
		    	System.out.println(sb.toString());
		    	
		    }
		    catch (Exception e) {
		    	
				// TODO: handle exception
		    	System.out.println("Exception:"+e);
			}
		   

	}
	
	// this method for National Security Search
	public static String requestXML()
	{
		String xml;
		
	  xml="<BGC version='3.4'>" +
				"<login>" +
					"<user>"+bgcUser+"</user>" +
					"<password>"+bgcPassword+"</password>" +
					"<account>"+bgcAccount+"</account>" +
				"</login>" +
				"<product>" +
					"<NationalSecurityOneSearch version='1'>" +
						
							"<order>" +
								"<firstName>"+firstname+"</firstName>" +
								"<middleName></middleName>"+
								"<lastName>"+lastname+"</lastName>" +
								
							"</order>" +
							
					   
					"</NationalSecurityOneSearch>" +
				"</product>" +
			"</BGC>";
		
		return xml;
	}
	//This method for Criminal Record Search
	public static String requestCriminalXml()
	{
		String xml;
		xml="<BGC>" + 
				"<login>" + 
					"<user>" + bgcUser +"</user>" +
					"<password>"+bgcPassword+"</password>"+
					"<account>"+bgcAccount+"</account>" +
				"</login>" +
				"<product>" +
						"<SingleStateOneSearch>" +
							"<order>" +
								"<firstName>"+"John"+"</firstName>" +
								"<middleName></middleName>" +
								"<lastName>"+"Smith"+"</lastName>" +
								"<DOB>" +
									"<year>"+1957+"</year>" +
								"</DOB>" +
								"<state>"+"AZ"+"</state>" +
							"</order>" +
							"<custom>" +
							"<filters>" +
								"<SOF>" +
									"<firstName>"+"<filterType>"+"XM"+"</filterType>"+"</firstName>" +
									"<lastName>"+"<filterType>"+"XM"+"</filterType>"+"</lastName>" +
									"<DOB>"+"<yearRange>"+0+"</yearRange>"+"<matchFuzzyDates>"+"YES"+"</matchFuzzyDates>" +
									"<matchMissingDates>"+"YES"+"</matchMissingDates>" +
								"</DOB>" +
								"</SOF>" +
								"<GCF>" +
									"<firstName>"+"<filterType>"+"XM"+"</filterType>"+"</firstName>" +
									"<lastName>"+"<filterType>"+"XM"+"</filterType>"+"</lastName>" +
									"<DOB>"+
										"<yearRange>"+0+"</yearRange>" +
										"<matchFuzzyDates>"+"YES"+"</matchFuzzyDates>" +
										"<matchMissingDates>"+"YES"+"</matchMissingDates>" +
									"</DOB>" +
								"</GCF>" +
								"</filters>" +
								"<options>" +
									"<includeSources>"+"YES"+"</includeSources>" +
									"<noSummary>"+"NO"+"</noSummary>" +
									"<includeDetails>"+"NO"+"</includeDetails>" +
								"</options>" +
								"</custom>" +
							"</SingleStateOneSearch>" +
						"</product>" +
				"</BGC>" ;
		
		return xml;
	}
	
	public static String offenderInfo()
	{
		String xml;
		xml="<BGC>"+
				"<login>" + 
				"<user>" + bgcUser +"</user>" +
				"<password>"+bgcPassword+"</password>"+
				"<account>"+bgcAccount+"</account>" +
			"</login>" +
			"<product>"+
				"<USOneSearchKey>"+
					"<order>"+
						"<offenderID>SMITJOHN-HHS-19146</offenderID>"+
						"<state>NS</state>"+
						"<source>"+19+"</source>" +
						"<secureKey>"+27044167+"</secureKey>" +
					"</order>"+
				"</USOneSearchKey>"+
			"</product>"+
			"</BGC>";
		return xml;
	}

}
