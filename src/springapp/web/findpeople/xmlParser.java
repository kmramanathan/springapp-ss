package springapp.web.findpeople;
import java.io.StringReader;
import java.util.Hashtable;
import java.util.ResourceBundle;

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

import springapp.web.findpeople.BGxmlManager.DEASearch;
import springapp.web.findpeople.BGxmlManager.FAAPilotLicenses;
import springapp.web.findpeople.BGxmlManager.FCCLicenses;
import springapp.web.findpeople.BGxmlManager.FFLFirearmDealerLicenses;
import springapp.web.findpeople.BGxmlManager.OccupantData;
import springapp.web.findpeople.BGxmlManager.USDomainNameOwnership;
import springapp.web.findpeople.BGxmlManager.addressHistory;
import springapp.web.findpeople.BGxmlManager.aircraftSearch;
import springapp.web.findpeople.BGxmlManager.birthRecords;
import springapp.web.findpeople.BGxmlManager.businessSearch;
import springapp.web.findpeople.BGxmlManager.campaignContributors;
import springapp.web.findpeople.BGxmlManager.censusData;
import springapp.web.findpeople.BGxmlManager.concealedWeaponPermits;
import springapp.web.findpeople.BGxmlManager.deathRecords;
import springapp.web.findpeople.BGxmlManager.merchantVessels;
import springapp.web.findpeople.BGxmlManager.otherPeople;
import springapp.web.findpeople.BGxmlManager.profileReport;
import springapp.web.findpeople.SearchLandingForm.SearchFPFormCommand;


public class xmlParser
{
	private static ResourceBundle resBun=ResourceBundle.getBundle("webservice");
	
	private final static String URL = resBun.getString("fp.url");
	private final static String USERNAME = resBun.getString("fp.username");
	private final static String PASSWORD = resBun.getString("fp.password");
	
	public static Hashtable getSearchResult(SearchFPFormCommand sfpfc, String resType, String key)
	{
		Logger logger = Logger.getRootLogger();
		Hashtable resHash=null;
		
		try
		{
			
			HttpClient client = new HttpClient();
			client.getParams().setAuthenticationPreemptive(true);
			PostMethod post = new PostMethod(URL);
			
			Credentials defaultcreds = new UsernamePasswordCredentials(
					USERNAME, PASSWORD);
			client.getState().setCredentials(AuthScope.ANY, defaultcreds);
			
			logger.info("Key for Request XML :: "+key);
			String strXML = getXmlRequest(sfpfc,resType,key);
			logger.info("Request XML is :: "+strXML);
				
			NameValuePair[] XML= {new NameValuePair("XML",strXML) };
			post.setQueryString(XML);
			int returnCode = client.executeMethod(post);
			String resXML = post.getResponseBodyAsString();
			//logger.info("Response XML :: "+resXML);
			logger.info("Return code :"+returnCode );
									
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();   
			Document doc = builder.parse(new InputSource(new StringReader(resXML.replace("&nbsp;", "&#160;"))));
			NodeList nList = null;
			
			//reading the head nodes and registering to logger if any error occured
			NodeList tempList = doc.getElementsByTagName("FDSResponse");
			Node headNode = (Node)tempList.item(0);
			System.out.println(headNode.getNodeName());
			String resultCount = readXMLNode(headNode,"ResultCount");
			String receivedAt = readXMLNode(headNode,"Generated");
			logger.info("Result Count :"+resultCount);
			logger.info("Received At :"+receivedAt);
			if(resultCount.equals("-1"))
			{
				String reasonString = readXMLNode(headNode,"searchResults");
				logger.info("Reason Text :"+reasonString);
				logger.info("Response XML :"+resXML);
				
			}

			if(resType.equals("BG"))
			{		
				nList = doc.getElementsByTagName("searchResults");
				return BGResultDetail(nList);				
			}
			else
			{
				nList = doc.getElementsByTagName("Result");
				int total = 0;
				total=nList.getLength();
							
				if(total > 0){	return fpResultDetail(nList, total, key); }

			}
			return resHash;
					
		}
		catch (Exception e) {	
			logger.error("Exception :"+e.getMessage() +" Cause :"+e.getCause()+" Class +"+e.getClass());
			return resHash;
		}

	}
	
	public static String getXmlRequest(SearchFPFormCommand sfpfc, String resType, String key)
	{
		Logger logger = Logger.getRootLogger();
		String strXML="";
		if(resType.equals("FP"))
		{
			
			strXML= "<FDSRequest>";
			strXML = strXML+ "<username>"+USERNAME+"</username>";
			strXML = strXML+ "<password>"+PASSWORD+"</password>";
			strXML = strXML+ "<sType>PFSBN</sType>";
			if(key.equals("teaser")) {	strXML = strXML+ "<detail>0</detail>"; }
			if(key.equals("one")) { strXML = strXML+ "<detail>1</detail>"; }
			strXML = strXML+ "<testmode>false</testmode>";
			strXML = strXML+ "<searchParams>" ;
			strXML = strXML+ "<firstName>"+sfpfc.getSearchFirstName()+"</firstName>" ;
			strXML = strXML+ "<middleName>"+sfpfc.getSearchMidName()+" </middleName>";
			strXML = strXML+ "<lastName>"+sfpfc.getSearchLastName()+"</lastName>" ;
			strXML = strXML+ "<city>"+sfpfc.getSearchCity()+"</city>" ;
			strXML = strXML+ "<state>"+sfpfc.getSearchState()+"</state>";
			strXML = strXML+ "<DOB>"+sfpfc.getSearchDob()+"</DOB>" ;
			strXML = strXML+ "</searchParams>" ;
			strXML = strXML+ "</FDSRequest>";	
		}

		if(resType.equals("BG"))
		{
			strXML = "";
			try
			{
				
				
				strXML= "<FDSRequest>";
				strXML = strXML+ "<username>"+USERNAME+"</username>";
				strXML = strXML+ "<password>"+PASSWORD+"</password>";
				strXML = strXML+ "<sType>PRO</sType>";
				strXML = strXML+ "<detail>1</detail>";
				strXML = strXML+ "<testmode>false</testmode>";
				strXML = strXML+ "<searchParams>" ;
				strXML = strXML+ "<lastName>"+sfpfc.getSearchLastName()+"</lastName>" ;
				strXML = strXML+ "<personID>"+key+"</personID>";
				strXML = strXML+ "</searchParams>" ;
				strXML = strXML+ "</FDSRequest>";
			
			}
			catch(Exception e)
			{
				logger.info("Exception occured :"+e.getMessage()+" cause :"+e.getCause()+" class :"+e.getClass());
			}
		}
		
		return strXML;
		
	}
	
	
	
	//Parsing teaser result and FP details
	public static Hashtable fpResultDetail(NodeList nList, int total, String key)
	{
		Hashtable fpResult= new Hashtable();
		Hashtable ageHash = new Hashtable();
		for(int i=0;i<total;i++)
		{
			Node firstNode = nList.item(i);
			
			fpDetail fp = new fpDetail(readXMLNode(firstNode,"ValidityDate"),
					readXMLNode(firstNode,"LastName"),
					readXMLNode(firstNode,"FirstName"),
					readXMLNode(firstNode,"MiddleName"),
					readXMLNode(firstNode,"DOB"),
					readXMLNode(firstNode,"Address"),
					readXMLNode(firstNode,"City"),
					readXMLNode(firstNode,"State"),
					readXMLNode(firstNode,"Phone"),
					readXMLNode(firstNode,"personID"));
			
			//getting and setting Age
			/* Fp detail XML dont have age in DOB tag. So we are getting the age from 
			 * teaser results only 
			 * */
			if(key.equals("teaser"))
			{
				String DOB = readXMLNode(firstNode,"DOB");
				String personId = readXMLNode(firstNode,"personID");
				if(!DOB.equals(""))
				{
					String[] DOBToken = DOB.split(" ");
					if(DOBToken.length > 1)
					{
						char[] ageChar = DOBToken[1].toCharArray();
						String age = ageChar[5]+""+ageChar[6];
						ageHash.put(personId, age);
						fp.setAge(age);
					}
				}
			}
			fpResult.put(readXMLNode(firstNode,"personID"), fp);
		}
		if(key.equals("teaser")){ fpResult.put("ageHash",ageHash); }
		return fpResult;
	
	}
	
    public static Hashtable BGResultDetail(NodeList nList)
    {

		BGxmlManager bgResult= new BGxmlManager();
		Node node=nList.item(0);
		NodeList childNodes= node.getChildNodes();
		Hashtable BGResult = new Hashtable();
		
		for(int i=0; i<childNodes.getLength(); i++)
		{
			  Node classNode = childNodes.item(i);
			  
			  if(classNode.getNodeName().equals("ProfileReport"))
			  {
				  bgResult.setProRpt(new profileReport(readXMLNode(classNode,"ValidityDate"),
						  			readXMLNode(classNode,"LastName"),
						  			readXMLNode(classNode,"FirstName"),
						  			readXMLNode(classNode,"MiddleName"),
						  			readXMLNode(classNode,"DOB"),
						  			readXMLNode(classNode,"Address"),
						  			readXMLNode(classNode,"City"),
						  			readXMLNode(classNode,"State"),
						  			readXMLNode(classNode,"Zip"),
						  			readXMLNode(classNode,"County"),
						  			readXMLNode(classNode,"Phone")));

				  continue;
			  }
			  
			  if(classNode.getNodeName()=="AddressHistory")
			  {
				 NodeList resultNodeList = classNode.getChildNodes();
				 for(int j =0; j<resultNodeList.getLength(); j++)
				 {
					 Node resultNode=resultNodeList.item(j);
					 if(resultNode.getNodeName().equals("result"))
					 {
						 addressHistory addHis = new addressHistory(readXMLNode(resultNode,"ValidityDate"),
						  			readXMLNode(resultNode,"address"),
						  			readXMLNode(resultNode,"city"),
						  			readXMLNode(resultNode,"state"),
						  			readXMLNode(resultNode,"zip"),
						  			readXMLNode(resultNode,"county"),
						  			readXMLNode(resultNode,"phone"));
					  
						 bgResult.addHisList.add(addHis);
					 }
						 
				 }
				 continue;
			
			  }
			  
			  if(classNode.getNodeName().equals("OccupantData"))
			  {
				  
				  NodeList resultNodeList = classNode.getChildNodes();
				  
				  for(int c = 0; c < resultNodeList.getLength(); c++)
				  {
					  Node resultNode = resultNodeList.item(c);
					  if(resultNode.getNodeName().equals("result"))
						 {	
					  
						  OccupantData occObj = new OccupantData();
						  occObj.setAddress(readXMLNode(resultNode,"address"));
						  occObj.setCity(readXMLNode(resultNode,"city"));
						  occObj.setState(readXMLNode(resultNode,"state"));
						  occObj.setZip(readXMLNode(resultNode,"zip"));
					  
						  NodeList innerResultList = resultNode.getChildNodes();
					 						  
						  for(int d = 0; d < innerResultList.getLength(); d++)
						  {
							  Node innerResult = innerResultList.item(d);
						  					  
							  if(innerResult.getNodeName().equals("listedPhones"))
							  {
							 								  
								  NodeList phoneList = innerResult.getChildNodes();
								  if(phoneList != null)
								  {
									  	String[] phoneArrray=new String[phoneList.getLength()];
								  		for(int f = 0; f < phoneList.getLength(); f++)
								  		{
								  			Node phoneNode = phoneList.item(f);
								  			if(phoneNode.getNodeName().equals("phone")) {
								  				//phoneArrray[f] = phoneNode.getTextContent();
								  			}
								  		}
								  		occObj.setListedPhones(phoneArrray);
								  }
							  }
						  
							  if(innerResult.getNodeName().equals("otherPeople"))
							  {
								  NodeList othersList = innerResult.getChildNodes();
								  for(int f = 0; f < othersList.getLength(); f++)
								  {
									  	Node peopleNode = othersList.item(f);
									  	 if(peopleNode.getNodeName().equals("person"))
										 {
									  		 otherPeople otherObj = new otherPeople(readXMLNode(peopleNode,"ValidityDate"),
									  				 	readXMLNode(peopleNode,"possibleRelative"), 
									  				 	readXMLNode(peopleNode,"LastName"),
									  				 	readXMLNode(peopleNode,"FirstName"),
									  				 	readXMLNode(peopleNode,"MiddleName"),
									  				 	readXMLNode(peopleNode,"DOB"));
								  
									  		 occObj.otherList.add(otherObj);
										 }
								  
							  }
							 
						  }
						  if(innerResult.getNodeName().equals("censusData"))
						  {
							  NodeList cenList = innerResult.getChildNodes();
							  for(int k=0; k<cenList.getLength(); k++)
							  {
								  Node cenNode = cenList.item(k);
								  if(cenNode.getNodeName().equals("zipcode"))
								  {
									  String zip=cenNode.getAttributes().item(0).getNodeValue();
							  
									  censusData cenObj = new censusData(zip,
											  	readXMLNode(innerResult,"city"), 
											  	readXMLNode(innerResult,"city_alias_name"), 
											  	readXMLNode(innerResult,"county_name"), 
											  	readXMLNode(innerResult,"population"), 
											  	readXMLNode(innerResult,"households_per_zip"), 
											  	readXMLNode(innerResult,"white_population"), 
											  	readXMLNode(innerResult,"black_population"), 
											  	readXMLNode(innerResult,"hispanic_population"),
											  	readXMLNode(innerResult,"persons_per_household"), 
											  	readXMLNode(innerResult,"avg_house_value"), 
											  	readXMLNode(innerResult,"income_per_household"),
											  	readXMLNode(innerResult,"latitude"),
											  	readXMLNode(innerResult,"longitude"),
											  	readXMLNode(innerResult,"elevation"),
											  	readXMLNode(innerResult,"state"), 
											  	readXMLNode(innerResult,"city_type"), 
											  	readXMLNode(innerResult,"city_alias_abbrev"), 
											  	readXMLNode(innerResult,"area_code"));
							  
									  occObj.censusList.add(cenObj);
								  }
							  }
						  }
					  }
				  
					  bgResult.OccupantList.add(occObj);
						 }
					  
				  }
				  
				 continue;				  
			  } 
			  
			  if(classNode.getNodeName().equals("merchantVessels"))
			  {
				  NodeList resultNodeList = classNode.getChildNodes();
					 for(int j =0; j<resultNodeList.getLength(); j++)
					 {
						 Node resultNode=resultNodeList.item(j);
						 if(resultNode.getNodeName().equals("result"))
						 {
							 merchantVessels merVes = new merchantVessels(readXMLNode(resultNode,"vesselname"),
							  			readXMLNode(resultNode,"hullnumber"),
							  			readXMLNode(resultNode,"hullidnumber"),
							  			readXMLNode(resultNode,"companyowner"),
							  			readXMLNode(resultNode,"companytype"),
							  			readXMLNode(resultNode,"ownerlastname"),
							  			readXMLNode(resultNode,"ownerfirstname"),
							  			readXMLNode(resultNode,"servicetype"),
							  			readXMLNode(resultNode,"address"),
							  			readXMLNode(resultNode,"address2"), 
							  			readXMLNode(resultNode,"address3"),
							  			readXMLNode(resultNode,"address4"),
							  			readXMLNode(resultNode,"city"),
							  			readXMLNode(resultNode,"state"),
							  			readXMLNode(resultNode,"province"),
							  			readXMLNode(resultNode,"zip"),
							  			readXMLNode(resultNode,"callsign"),
							  			readXMLNode(resultNode,"imonumber"),
							  			readXMLNode(resultNode,"boatmaker"),
							  			readXMLNode(resultNode,"yearbuilt"),
							  			readXMLNode(resultNode,"hullmaterial"), 
							  			readXMLNode(resultNode,"motortype"), 
							  			readXMLNode(resultNode,"horsepower"), 
							  			readXMLNode(resultNode,"grosstons"), 
							  			readXMLNode(resultNode,"length"), 
							  			readXMLNode(resultNode,"width"), 
							  			readXMLNode(resultNode,"depth"), 
							  			readXMLNode(resultNode,"countrybuilt"),
							  			readXMLNode(resultNode,"shipyard"), 
							  			readXMLNode(resultNode,"homeport"), 
							  			readXMLNode(resultNode,"stateofport"));
						  
						  bgResult.merVessList.add(merVes);
						 }
					 }
					 
					 continue;
			  
			  }
			  if(classNode.getNodeName().equals("aircraftSearch"))
			  {
				  	NodeList resultNodeList = classNode.getChildNodes();
					 for(int j =0; j<resultNodeList.getLength(); j++)
					 {
						 Node resultNode=resultNodeList.item(j);
						 if(resultNode.getNodeName().equals("result"))
						 {
							 aircraftSearch airSer = new aircraftSearch(readXMLNode(resultNode,"nnumber"),
							  			readXMLNode(resultNode,"serialnumber"),
							  			readXMLNode(resultNode,"yearbuilt"),
							  			readXMLNode(resultNode,"model"),
							  			readXMLNode(resultNode,"ownername"),
							  			readXMLNode(resultNode,"certdate"),
							  			readXMLNode(resultNode,"address"),
							  			readXMLNode(resultNode,"address2"),
							  			readXMLNode(resultNode,"city"),
							  			readXMLNode(resultNode,"state"), 
							  			readXMLNode(resultNode,"zip"));
						  
						  bgResult.airSerList.add(airSer);
						 }
					 }
					 continue;
					 			  
			  }
			  if(classNode.getNodeName().equals("birthRecords"))
			  {
				  NodeList resultNodeList = classNode.getChildNodes();
					 for(int j =0; j<resultNodeList.getLength(); j++)
					 {
						 Node resultNode=resultNodeList.item(j);
						 if(resultNode.getNodeName().equals("result"))
						 {
							 birthRecords birthRec = new birthRecords(readXMLNode(resultNode,"lastname"),
							  			readXMLNode(resultNode,"firstname"),
							  			readXMLNode(resultNode,"middlename"),
							  			readXMLNode(resultNode,"DOB"),
							  			readXMLNode(resultNode,"gender"),
							  			readXMLNode(resultNode,"birthCounty"),
							  			readXMLNode(resultNode,"St"));
						  
						  bgResult.birthList.add(birthRec);
						 }
					 }
					 continue;
						  
			  }
			  if(classNode.getNodeName().equals("deathRecords"))
			  {
				  NodeList resultNodeList = classNode.getChildNodes();
					 for(int j =0; j<resultNodeList.getLength(); j++)
					 {
						 Node resultNode=resultNodeList.item(j);
						 if(resultNode.getNodeName().equals("result"))
						 {
							 deathRecords deathRec = new deathRecords(readXMLNode(resultNode,"lastname"),
							  			readXMLNode(resultNode,"suffix"),
							  			readXMLNode(resultNode,"firstname"),
							  			readXMLNode(resultNode,"middlename"),
							  			readXMLNode(resultNode,"dateofdeath"),
							  			readXMLNode(resultNode,"SSN"),
							  			readXMLNode(resultNode,"dateofbirth"));
						  
						  bgResult.deathList.add(deathRec);
						 }
					 }
					 continue;
					  
			  }
			  
			  //Dea search Has two state fileds
			  if(classNode.getNodeName().equals("DEASearch"))
			  {
				  NodeList resultNodeList = classNode.getChildNodes();
					 for(int j =0; j<resultNodeList.getLength(); j++)
					 {
						 Node resultNode=resultNodeList.item(j);
						 if(resultNode.getNodeName().equals("result"))
						 {
							 DEASearch DEARec = new DEASearch(readXMLNode(resultNode,"lastname"),
							  			readXMLNode(resultNode,"firstname"),
							  			readXMLNode(resultNode,"DEANumber"),
							  			readXMLNode(resultNode,"address"),
							  			readXMLNode(resultNode,"city"),
							  			readXMLNode(resultNode,"state"),
							  			readXMLNode(resultNode,"zip"),
							  			readXMLNode(resultNode,"businesstype"),
							  			readXMLNode(resultNode,"expirationdate"),
							  			readXMLNode(resultNode,"county"),
							  			readXMLNode(resultNode,"schedules"));
						  
						  bgResult.DEAList.add(DEARec);
						 }
					 }
					 continue;
				
			  }
			  if(classNode.getNodeName().equals("FAAPilotLicenses"))
			  {
				  NodeList resultNodeList = classNode.getChildNodes();
					 for(int j =0; j<resultNodeList.getLength(); j++)
					 {
						 Node resultNode=resultNodeList.item(j);
						 if(resultNode.getNodeName().equals("result"))
						 {
							 FAAPilotLicenses faaRec = new FAAPilotLicenses(readXMLNode(resultNode,"lastname"),
							  			readXMLNode(resultNode,"firstname"),
							  			readXMLNode(resultNode,"middlename"),
							  			readXMLNode(resultNode,"address"),
							  			readXMLNode(resultNode,"address2"),
							  			readXMLNode(resultNode,"city"),
							  			readXMLNode(resultNode,"state"),
							  			readXMLNode(resultNode,"zip"),
							  			readXMLNode(resultNode,"FAANumber"),
							  			readXMLNode(resultNode,"medicalExpDate"), 
							  			readXMLNode(resultNode,"certificationType"), 
							  			readXMLNode(resultNode,"certlevel"),
							  			readXMLNode(resultNode,"ratings"));
						  
						  bgResult.FAAList.add(faaRec);
						 }
					 }
					 continue;
					
			  }
			  if(classNode.getNodeName().equals("concealedWeaponPermits"))
			  {
				  NodeList resultNodeList = classNode.getChildNodes();
					 for(int j =0; j<resultNodeList.getLength(); j++)
					 {
						 Node resultNode=resultNodeList.item(j);
						 if(resultNode.getNodeName().equals("result"))
						 {
							 concealedWeaponPermits consRec = new concealedWeaponPermits(readXMLNode(resultNode,"lastname"),
							  			readXMLNode(resultNode,"firstname"),
							  			readXMLNode(resultNode,"middlename"),
							  			readXMLNode(resultNode,"span"),
							  			readXMLNode(resultNode,"licenseNumber"),
							  			readXMLNode(resultNode,"state"),
							  			readXMLNode(resultNode,"expirationdate"),
							  			readXMLNode(resultNode,"address"),
							  			readXMLNode(resultNode,"city"),
							  			readXMLNode(resultNode,"state1"),
							  			readXMLNode(resultNode,"zip"), 
							  			readXMLNode(resultNode,"countyName"),
							  			readXMLNode(resultNode,"race"), 
							  			readXMLNode(resultNode,"sex"));
						  
						  bgResult.WeaponList.add(consRec);
						 }
					 }
					 continue;
				
			  }
			  if(classNode.getNodeName().equals("FCCLicenses"))
			  {
				  NodeList resultNodeList = classNode.getChildNodes();
					 for(int j =0; j<resultNodeList.getLength(); j++)
					 {
						 Node resultNode=resultNodeList.item(j);
						 if(resultNode.getNodeName().equals("result"))
						 {
							 FCCLicenses fccRec = new FCCLicenses(readXMLNode(resultNode,"lastname"),
							  			readXMLNode(resultNode,"firstname"),
							  			readXMLNode(resultNode,"middlename"),
							  			readXMLNode(resultNode,"callsign"),
							  			readXMLNode(resultNode,"address"),
							  			readXMLNode(resultNode,"city"),
							  			readXMLNode(resultNode,"state"),
							  			readXMLNode(resultNode,"zip"),
							  			readXMLNode(resultNode,"FRNnumber"));
						  
						  bgResult.FCCList.add(fccRec);
						  
						 }
						  
					 }
					 continue;
						  
			  }
			  
			  if(classNode.getNodeName().equals("FFLFirearmDealerLicenses"))
			  {
				  NodeList resultNodeList = classNode.getChildNodes();
					 for(int j =0; j<resultNodeList.getLength(); j++)
					 {
						 Node resultNode=resultNodeList.item(j);
						 if(resultNode.getNodeName().equals("result"))
						 {
							 FFLFirearmDealerLicenses fflRec = new FFLFirearmDealerLicenses(readXMLNode(resultNode,"Expires"),
							  			readXMLNode(resultNode,"licensee_name"),
							  			readXMLNode(resultNode,"business_name"),
							  			readXMLNode(resultNode,"premise_street"),
							  			readXMLNode(resultNode,"premise_city"),
							  			readXMLNode(resultNode,"premise_state"),
							  			readXMLNode(resultNode,"premise_zip"),
							  			readXMLNode(resultNode,"premise_phone"),
							  			readXMLNode(resultNode,"mailing_address"),
							  			readXMLNode(resultNode,"mailing_city"),
							  			readXMLNode(resultNode,"mailing_state"), 
							  			readXMLNode(resultNode,"mailing_zip"),
							  			readXMLNode(resultNode,"mailing_phone"));
						  
						  bgResult.FFLList.add(fflRec);
						 }
					 }
					 continue;
				
			  }
			  if(classNode.getNodeName().equals("USDomainNameOwnership"))
			  {
				  NodeList resultNodeList = classNode.getChildNodes();
					 for(int j =0; j<resultNodeList.getLength(); j++)
					 {
						 Node resultNode=resultNodeList.item(j);
						 if(resultNode.getNodeName().equals("result"))
						 {
							 USDomainNameOwnership USRec = new USDomainNameOwnership(readXMLNode(resultNode,"domain_Name"),
							  			readXMLNode(resultNode,"lastname"),
							  			readXMLNode(resultNode,"firstname"),
							  			readXMLNode(resultNode,"middlename"),
							  			readXMLNode(resultNode,"company"),
							  			readXMLNode(resultNode,"address"),
							  			readXMLNode(resultNode,"city"),
							  			readXMLNode(resultNode,"state"),
							  			readXMLNode(resultNode,"zip"),
							  			readXMLNode(resultNode,"phone"),
							  			readXMLNode(resultNode,"fax"), 
							  			readXMLNode(resultNode,"emailaddress"));
						  
						  bgResult.USDomainList.add(USRec);
						 }
					 }
					 continue;
		
			  }
			  if(classNode.getNodeName().equals("campaignContributors"))
			  {
				  NodeList resultNodeList = classNode.getChildNodes();
					 for(int j =0; j<resultNodeList.getLength(); j++)
					 {
						 Node resultNode=resultNodeList.item(j);
						 if(resultNode.getNodeName().equals("result"))
						 {
							 campaignContributors camRec = new campaignContributors(readXMLNode(resultNode,"lastname"),
							  			readXMLNode(resultNode,"firstname"),
							  			readXMLNode(resultNode,"middlename"),
							  			readXMLNode(resultNode,"occupation"),
							  			readXMLNode(resultNode,"address"),
							  			readXMLNode(resultNode,"city"),
							  			readXMLNode(resultNode,"state"),
							  			readXMLNode(resultNode,"zip"),
							  			readXMLNode(resultNode,"contributionDate"),
							  			readXMLNode(resultNode,"candidate"),
							  			readXMLNode(resultNode,"term"));
						  
						  bgResult.campaignList.add(camRec);
						 }
					 }
					 continue;
			
			  }
			  if(classNode.getNodeName().equals("businessSearch"))
			  {
				  NodeList resultNodeList = classNode.getChildNodes();
					 for(int j =0; j<resultNodeList.getLength(); j++)
					 {
						 Node resultNode=resultNodeList.item(j);
						 if(resultNode.getNodeName().equals("result"))
						 {
							 businessSearch busRec = new businessSearch(readXMLNode(resultNode,"company"),
							  			readXMLNode(resultNode,"address"),
							  			readXMLNode(resultNode,"city"),
							  			readXMLNode(resultNode,"state"),
							  			readXMLNode(resultNode,"zip"),
							  			readXMLNode(resultNode,"county"),
							  			readXMLNode(resultNode,"phone"),
							  			readXMLNode(resultNode,"fax"),
							  			readXMLNode(resultNode,"ownertitle"),
							  			readXMLNode(resultNode,"ownerfirst"),
							  			readXMLNode(resultNode,"ownerlast"),
							  			readXMLNode(resultNode,"annualsales"),
							  			readXMLNode(resultNode,"numberofemployees"),
							  			readXMLNode(resultNode,"squarefootage"),
							  			readXMLNode(resultNode,"creditscorelettergrade"),
							  			readXMLNode(resultNode,"creditscorenumericgrade"), 
							  			readXMLNode(resultNode,"primarysic"),
							  			readXMLNode(resultNode,"primarysicdescription"), 
							  			readXMLNode(resultNode,"website"));
						  
						  bgResult.businessList.add(busRec);
						 }
					 }
					 continue;
				
			  }
			  
  		}
		   BGResult.put("BGResult", bgResult);
		   return BGResult;
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
	
	public static class fpDetail {
		
		String ValidityDate;
		String LastName;
		String FirstName;
		String MiddleName;
		String DOB;
		String Address;
		String City;
		String State;
		String Phone;
		String personID;
		String age;
		
			
		public fpDetail(String validityDate, String lastName, String firstName,
				String middleName, String dob, String address, String city,
				String state, String phone, String personID) {
			ValidityDate = validityDate;
			LastName = lastName;
			FirstName = firstName;
			MiddleName = middleName;
			DOB = dob;
			Address = address;
			City = city;
			State = state;
			Phone = phone;
			this.personID = personID;
		}
		
		
		public String getAge() {
			return age;
		}


		public void setAge(String age) {
			this.age = age;
		}


		public String getValidityDate() {
			return ValidityDate;
		}
		public String getLastName() {
			return LastName;
		}
		public String getFirstName() {
			return FirstName;
		}
		public String getMiddleName() {
			return MiddleName;
		}
		public String getDOB() {
			return DOB;
		}
		public String getAddress() {
			return Address;
		}
		public String getCity() {
			return City;
		}
		public String getState() {
			return State;
		}
		public String getPhone() {
			return Phone;
		}
		public String getPersonID() {
			return personID;
		}
		
	}

}


 

