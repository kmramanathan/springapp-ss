package springapp.web.corporate;
import org.quartz.JobExecutionContext;
import org.springframework.context.ApplicationContext;


import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


import springapp.web.corporate.xmlParsingHelper.*;
//import springapp.web.corporate.xmlParsingHelper.addressObj;
import springapp.web.corporate.xmlParsingHelper.officerObj.offAddressObj;
import springapp.web.corporate.xmlParsingHelper.officerObj.offNameObj;

public class testXML
{

	public static void main(String[] args)
	{
		ResourceBundle resBun=null;
		try
		{
			HttpClient client = new HttpClient();
			client.getParams().setAuthenticationPreemptive(true);
			
			resBun=ResourceBundle.getBundle("axciom.corporate");
			String postUrl=resBun.getString("corporate.postUrl");
			String userName=resBun.getString("corporate.userName");
			String passWord=resBun.getString("corporate.passWord");
			
			PostMethod post = new PostMethod(postUrl);

			Credentials defaultcreds = new UsernamePasswordCredentials(
					userName, passWord);
			client.getState().setCredentials(AuthScope.ANY, defaultcreds);

			String data = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://id.acxiom.com/risk-natcorp/1.0\">";
			data = data + "<soapenv:Header>";
			data = data + "<ns:RiskHeader>";
			data = data + "<clientIP>192.168.1.2</clientIP>";
			data = data + "<reasonCode>1</reasonCode>";
			data = data + "</ns:RiskHeader>";
			data = data + "</soapenv:Header>";
			data = data + "<soapenv:Body>";
			data = data + "<ns:searchByNameAddress>";
			data = data + "<input>";
			data = data + "<businessName></businessName>";
			data = data + "<city>wales</city>";//wales
			data = data + "<emailAddress></emailAddress>";
			data = data + "<faxNumber></faxNumber>";
			data = data + "<filingDate></filingDate>";
			data = data + "<incorporationState></incorporationState>";
			data = data + "<officerName>tony</officerName>"; //tony
			data = data + "<phoneNumber></phoneNumber>";
			data = data + "<SICCode></SICCode>";
			data = data + "<state>california</state>";//Alaska
			data = data + "<street></street>";
			data = data + "<taxID></taxID>";
			data = data + "<unitNumber></unitNumber>";
			data = data + "<zip></zip>";
			data = data + "</input>";
			data = data + "</ns:searchByNameAddress>";
			data = data + "</soapenv:Body>";
			data = data + "</soapenv:Envelope>";

			post.setRequestBody(data);
			client.executeMethod(post);
			int returnCode = client.executeMethod(post);
			System.out.println("Return code: " + returnCode);
			String strXML = post.getResponseBodyAsString();
			System.out.println("Response XML: " + strXML); 
						
				
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();   
			Document doc = builder.parse(new InputSource(new StringReader(strXML.replace("&nbsp;", "&#160;"))));
			NodeList nList = null;

			
			
			if(returnCode==500)
			{
				nList = doc.getElementsByTagName("env:Fault");
				Node firstNode=(Node)nList.item(0);
				if(readXMLNode(firstNode, "faultstring").equalsIgnoreCase("Query returned too many results"));
					{
						System.out.println("Tooo many rows");
					}
				} 
			
		
			
			nList = doc.getElementsByTagName("return");
			int total = 0;
			total=nList.getLength();
			System.out.println("Result Count : "+total);
			
			//address,history,mailingAddress,merger,officer,prinBusAddress,stock
			
			
			
		/*	if(nList!=null)
			{
				for(int i=0;i<total;i++)
				{
					Node firstNode = nList.item(i);
					NodeList childNodes=firstNode.getChildNodes();
					System.out.println("Length :"+childNodes.getLength());
					
					
					for(int j=0; j<childNodes.getLength();j++)
					{
						Node firstChildNode=(Node)childNodes.item(j);
						
						
						if(firstChildNode.getNodeName()== "address")
						{
							address.put("address"+j, getHashTable(firstChildNode));
							
							keys=address.keys();
							values=address.elements();
							while(keys.hasMoreElements())
							{
								System.out.println(keys.nextElement()+"::"+values.nextElement());
							}
							
							continue;
						}
						
						if(firstChildNode.getNodeName()== "officer" )
						{
							NodeList innerChildList=firstChildNode.getChildNodes();
							for(int k=0;k>innerChildList.getLength();k++)
							{
								Node firstChildInNode=(Node)innerChildList.item(k);
								if(firstChildInNode.getNodeName()=="address")
								{
									off.put(firstChildInNode.getNodeName(), getHashTable(firstChildInNode));
									continue;
								}
								if(firstChildInNode.getNodeName()=="name")
								{
									off.put(firstChildInNode.getNodeName(), getHashTable(firstChildInNode));
									continue;
								}
								off.put(firstChildInNode.getNodeName(), readXMLNode(firstChildNode, firstChildInNode.getNodeName()));
							}
							officer.put(firstChildNode.getNodeName()+j, off);
							
				
							continue;
						} 
						
						if(firstChildNode.getNodeName()== "merger")
						{
							merger.put("merger"+j, getHashTable(firstChildNode));
							continue;							
						}
						
						if(firstChildNode.getNodeName()=="history")
						{
							history.put("history"+j, getHashTable(firstChildNode));
							continue;
						}
						
						if(firstChildNode.getNodeName()== "stock")
						{
							history.put("history"+j, getHashTable(firstChildNode));
							continue;
						}
						
						if(firstChildNode.getNodeName()== "mailingAddress")
						{
							history.put("history"+j, getHashTable(firstChildNode));
							continue;
						}
						
						if(firstChildNode.getNodeName()=="prinBusAddress")
						{
							history.put("history"+j, getHashTable(firstChildNode));
							continue;
						}
				
						name.put(firstChildNode.getNodeName(), readXMLNode(firstNode, firstChildNode.getNodeName()));
					}
						
					
				}
				
			} */
			
			
			if(nList!=null)
			{
				Hashtable searchResult= new Hashtable();
				
				for(int i=0;i<total;i++)
				{
					xmlParsingHelper xPH=new xmlParsingHelper();
					
					Node firstNode = nList.item(i);
					NodeList childNodes=firstNode.getChildNodes();
					//System.out.println("Length :"+childNodes.getLength());
					
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
							
							
						/*	
						 * Reading Address Object
						 * 
						 *  addressObj ad=(addressObj)xPH.getAObj();
							System.out.println("getAddress1::"+ad.getAddress1());
							System.out.println("getAddress2::"+ad.getAddress2());
							System.out.println("getCity::"+ad.getCity());
							System.out.println("getCountry::"+ad.getCountry());
							System.out.println("getCounty::"+ad.getCounty());
							System.out.println("getState::"+ad.getState());
							System.out.println("getStateCode::"+ad.getStateCode());
							System.out.println("getZip::"+ad.getZip());
							System.out.println("getZip4::"+ad.getZip4()); */
							
						
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
							
							
						/*	System.out.println("Corp File Key ::"+offObj.getCorpFileKey());
							offNameObj ofNaObj=(offNameObj)offObj.getOnObj();
							offAddressObj ofAdObj=(offAddressObj)offObj.getOaObj();
							System.out.println("Name ::"+ofNaObj.getFirst());
							System.out.println("Addresss::"+ofAdObj.getAddress1());
							
						*/	
							/*NodeList innerChildList=firstChildNode.getChildNodes();
							for(int k=0;k>innerChildList.getLength();k++)
							{
								Node firstChildInNode=(Node)innerChildList.item(k);
								if(firstChildInNode.getNodeName()=="address")
								{
									off.put(firstChildInNode.getNodeName(), getHashTable(firstChildInNode));
									continue;
								}
								if(firstChildInNode.getNodeName()=="name")
								{
									off.put(firstChildInNode.getNodeName(), getHashTable(firstChildInNode));
									continue;
								}
								off.put(firstChildInNode.getNodeName(), readXMLNode(firstChildNode, firstChildInNode.getNodeName()));
							}
							officer.put(firstChildNode.getNodeName()+j, off);
							
				             */
							
							
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
							
							//System.out.println(hObj.getStateCode());
						    		
							xPH.historyList.add(hObj);
							continue;
						}
						
						if(firstChildNode.getNodeName()== "stock")
						{
							System.out.println("Stock");
							xPH.setSObj(
									new stockObj (
											readXMLNode(firstChildNode,"corpFileKey"),
											readXMLNode(firstChildNode,"stateCode"),
											readXMLNode(firstChildNode,"stockClass"),
											readXMLNode(firstChildNode,"stockDate"),
											readXMLNode(firstChildNode,"stockParValue"),
											readXMLNode(firstChildNode,"stockRestrictInd"),
											readXMLNode(firstChildNode,"stockShareAuth")
											
											)
										);
									
							
						
						/*	
						 * Reading Stock Object
						 * 
						 * 	stockObj so=(stockObj)xPH.getSObj();
						 * 	System.out.println("corpFileKey::"+so.getCorpFileKey());
							System.out.println("stateCode::"+so.getStateCode());
							System.out.println("stockClass::"+so.getStockClass());
							System.out.println("stockDate::"+so.getStockDate());
							System.out.println("stockParValue::"+so.getStockParValue());
							System.out.println("stockRestrictInd::"+so.getStockRestrictInd());
							System.out.println("stockShareAuth::"+so.getStockShareAuth()); */
							
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
							
							//history.put("history"+j, getHashTable(firstChildNode));
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
							//history.put("history"+j, getHashTable(firstChildNode));
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
						
				/*	
					System.out.println("BankruptDate::"+xPH.getBankruptDate());
					System.out.println("Capital::"+xPH.getCapital());
					System.out.println("getCorpDescription::"+xPH.getCorpDescription());
					System.out.println("getCorpFileKey::"+xPH.getCorpFileKey());
					System.out.println("getCorpStatus::"+xPH.getCorpStatus());
					System.out.println("getCorpStatusDate::"+xPH.getCorpStatusDate());
					System.out.println("getCorpType::"+xPH.getCorpType());
					System.out.println("getDBAName::"+xPH.getDBAName());
					System.out.println("getEmail1::"+xPH.getEmail1());
					System.out.println("getExpireDate::"+xPH.getExpireDate());
					System.out.println("getExt::"+xPH.getExt());
					System.out.println("getFax::"+xPH.getFax());
					System.out.println("getFileDataDate::"+xPH.getFileDataDate());
					System.out.println("getFilingDate::"+xPH.getFilingDate());
					System.out.println("getFilingType::"+xPH.getFilingType());
					System.out.println("getHomeState::"+xPH.getHomeState());
					System.out.println("getIncorporationState::"+xPH.getIncorporationState());
					System.out.println("getJurisdiction::"+xPH.getJurisdiction());
					System.out.println("getLastReportDate::"+xPH.getLastReportDate());
					System.out.println("getName::"+xPH.getName());
					System.out.println("getNonProfitType::"+xPH.getNonProfitType());
					System.out.println("getPhone::"+xPH.getPhone());
					System.out.println("getPurpose::"+xPH.getPurpose());
					System.out.println("getRegistryNumber::"+xPH.getRegistryNumber());
					System.out.println("getSIC::"+xPH.getSIC());
					System.out.println("getStatute::"+xPH.getStatute());
					System.out.println("getTaxID::"+xPH.getTaxID());
					System.out.println("getTerm::"+xPH.getTerm()); */
					
					
					// Putting XML object inside arrayList
					searchResult.put("cb"+i, xPH);
					
				}
				
				Enumeration keys=searchResult.keys();
				String chkKey="";
				
				
				while(keys.hasMoreElements())
				{
					chkKey=(String)keys.nextElement();
					xmlParsingHelper xp=(xmlParsingHelper)searchResult.get(chkKey);
					mergerObj merObj=(mergerObj)xp.getMerObj();
					
					if(merObj!=null)
					{
						System.out.println("10");
						
						System.out.println("CorpFileKey::"+merObj.getCorpFileKey());
						System.out.println("EventDescription::"+merObj.getEventDescription());
						System.out.println("MergeDate::"+merObj.getMergeDate());
						System.out.println("getMergedCorpID::"+merObj.getMergedCorpID());
						System.out.println("getMergedCorpName::"+merObj.getMergedCorpName());
						System.out.println("getStateCode::"+merObj.getStateCode());
						System.out.println("getSurvivingCorpID::"+merObj.getSurvivingCorpID());
						
					}
					
				}	
			}
			   
		
		}
		catch(Exception e)
		{
			System.out.println(e.getClass());
		}
	}
	
	public static String readXMLNode(Node childNodes, String strTagName)
	{
		String strQueryResult = "";
		Element firstElement = (Element)childNodes;
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
	
	/*
	public static Hashtable getHashTable(Node firstChildNode)
	{
	
		Hashtable hTemp=new Hashtable();
		NodeList innerList=firstChildNode.getChildNodes();
		for(int t=0;t<innerList.getLength();t++)
		{
			Node firstInnerChild=(Node)innerList.item(t);
			hTemp.put(firstInnerChild.getNodeName(),readXMLNode(firstChildNode, firstInnerChild.getNodeName()));
		}
		return hTemp;
	}
	
	*/
	
}

