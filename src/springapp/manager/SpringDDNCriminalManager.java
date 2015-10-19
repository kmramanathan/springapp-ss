package springapp.manager;
import java.io.DataOutputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.searchsystems.limestone.BjlDataSources;
import net.searchsystems.limestone.BjlDataSourcesPeer;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.apache.commons.httpclient.HttpClient; 
import org.apache.commons.httpclient.methods.PostMethod;	
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import springapp.domain.DnnCriminalXMLParser;
import springapp.domain.RealPropertyXMLParser;
import springapp.domain.CriminalSearch.CriminalRequestBean;
import springapp.domain.CriminalSearch.CriminalResponseBean;
import springapp.domain.RealProperty.RealPropResponseBean;
import springapp.manager.SearchException;
import springapp.repository.CriminalRequestDao;

import org.w3c.dom.Attr;

import java.io.*;

import javax.net.ssl.HttpsURLConnection;

import java.io.File;

/**
 * Provides support for querying the Real Property interface.
 */
@Service("DDNSearchSearchManager")
public class SpringDDNCriminalManager implements ResourceLoaderAware {
	protected static ArrayList<BjlDataSources> bjlSources;

	public final static String liveUrl="https://secure.datadirectnow.com/webservice/default.cfm";//"https://www.tracersinfo.com/xml.php";

	public static final short MAX_RESULTS = 20;
	
	protected static final Short SEARCH_CATEGORY_ID = 66;
	
	protected static final Short STATUS_FOUND_RESULTS = 1;
	protected static final Short STATUS_NO_RESULTS = 1;
	protected static final Short STATUS_TOO_MANY_RESULTS = 1;
	

	
	protected final Logger logger = Logger.getLogger(getClass());

	// this is necessary to force torque to load before init() gets called
	// can we move this to an event listener in torque mgr?
	
	@Autowired
	protected CriminalRequestDao requestDao;
	
	@Autowired
	protected ResourceLoader resourceLoader;
	public void setResourceLoader(ResourceLoader resourceLoader) 
	{
		this.resourceLoader = resourceLoader;		
	}
	
	
	public void init() {
		try {
			bjlSources = new ArrayList<BjlDataSources>();
			bjlSources.addAll(BjlDataSourcesPeer.doSelect(new Criteria()));
		} catch (TorqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public long queryFunnel(Integer userId, Integer searchSubCategoryId, Integer transactionId, 
			String sFirstname,String sLastname,String sDob,String sSsn,String sState,HttpSession session) {
		return this.query(userId, searchSubCategoryId, transactionId,sFirstname, sLastname, sDob,sSsn,sState,session);
	}
	
	/**
	 * Query the DDN Criminal interface and store the results in the database.
	 * 
	 * @throws SearchException
	 *             If the SOAP request failed for any reason.
	 */
	public long query(Integer userId, Integer searchSubCategoryId, Integer transactionId, 
			String sFirstname,String sLastname,String sDob,String sSsn,String sState,HttpSession session) throws SearchException {

			long searchId = 0;
			StringBuffer sb=new StringBuffer();
			 Short searchStatusId;
	         Integer totalMatches = 0;
	         Integer rowsReturned = 0;	        
	         	String username = "tjkoster";
				String password = "tksearch2014";
				String lastname= sLastname;
				String firstname= sFirstname;
				String dob= "";
				String state= sState;
				String reference="";
				String ssn="";
				lastname=sLastname;
				firstname= sFirstname;
				if(sSsn!=null)
					ssn=sSsn;
				state=sState;	
				dob= sDob;
				
			try
			{
				String xml= "<?xml version='1.0' encoding='utf-8'?><OrderXML><Method>SEND ORDER</Method>";
				xml += "<Authentication><Username>"+ username +"</Username><Password>"+ password +"</Password></Authentication>";
				xml += "<ReturnResultURL>http://database02.searchsystems.net:8080/springapp/funnel/ddnResults.do</ReturnResultURL>";
				xml += "<OrderingUser></OrderingUser><Order><BillingReferenceCode></BillingReferenceCode><Subject>";
				 //if(session.getAttribute("ddnXMLValueNameSearch") != null)
				 {
					 xml += "<FirstName>"+ firstname +"</FirstName><MiddleName></MiddleName><LastName>"+ lastname +"</LastName><Generation></Generation>";
					 xml += "<DOB>"+ dob +"</DOB><SSN>"+ ssn +"</SSN><Gender></Gender><Ethnicity></Ethnicity><DLNumber></DLNumber>";
				 }				 				
				xml += "<ApplicantPosition></ApplicantPosition><CurrentAddress><StreetAddress></StreetAddress><City></City>";
				if(state!= null && !state.equals("Natcrim") )
					xml += "<State>"+ state +"</State>";
				else 
					xml += "<State></State>";
			    xml += "<Zipcode></Zipcode></CurrentAddress><Aliases><Alias><FirstName></FirstName>";
				xml += "<MiddleName></MiddleName><LastName></LastName></Alias></Aliases></Subject>";
				if(state!= null && state.equals("Natcrim") ) 
					xml += "<OrderDetail ServiceCode='Natcrim' OrderId='25678950055'><State></State></OrderDetail>";
				else
					xml += "<OrderDetail ServiceCode='State Instant' OrderId='25678445'><State>"+state +"</State></OrderDetail>";
				xml += "</Order></OrderXML> ";		
				
				logger.info("request xml"+xml);  //SSN:numbr: -- 149-58-7526

	         /*
	 		 *  step 2: Sending Request
	 		 *  
	 		 */	     
			
			/*
			 * Testing with local XML file
			 * 
			*/	
			/*	 File fXmlFile = new File("C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/springapp/src/xmlToRead/DDN_StateResponse.xml");
		    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    	Document doc = dBuilder.parse(fXmlFile);
		    	doc.getDocumentElement().normalize();
		      */
				
		 	HttpClient client=new HttpClient();
			PostMethod post=new PostMethod(liveUrl);
		 	post.setParameter("REQUEST", xml);			 	
		 	int returncode=client.executeMethod(post);		 	
			logger.info("DDN___Return Code:"+returncode);
	        BufferedReader  br=new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));
	        String readline=null;
	        while((readline=br.readLine()) != null)
	         {
	        	sb.append(readline+"\n"); 
	         }
	        String xmlResponse=sb.toString();	
	        logger.info("DDN::@@@@::::xmlResponse xml"+xmlResponse); 
	        
	      
	    	/*
	 		 *  step 3: parse response
	 		 *  
	 		 */	
	        if(xmlResponse != null)
		    {
		    	searchStatusId=STATUS_NO_RESULTS;
	         
		    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    	DocumentBuilder builder = factory.newDocumentBuilder();
		    	Document doc = builder.parse(new InputSource(new StringReader(xmlResponse.replace("&nbsp;", "&#160;"))));
		    				 
		    	NodeList nList=null;	      		         
		    	NodeList rowList=doc.getElementsByTagName("Result"); //getting Order tag		    	
		    	Node headNode =(Node) rowList.item(0);		    	
		    	rowsReturned=rowList.getLength(); // no of records		    	
		    	logger.info("Rows Returnd:"+rowsReturned);		    	
		    	
		    	if(rowsReturned.equals("-1"))
		    	{
		    		String reasonstring=readXMLNode(headNode, "Result");
					logger.info("Reason Text:"+reasonstring);
		    	}
		    		
		    		
				if(rowsReturned >0  )
				{
					session.removeAttribute("DDNResults");					
					CriminalRequestBean ddnRequstbean = new CriminalRequestBean();
					// create a new transaction conn.setAutoCommit(false);
					// add search to database
					//Saving the Request Values to DB Ends Here	
					ddnRequstbean.setSearchCategoryId(SEARCH_CATEGORY_ID);		
					ddnRequstbean.setSearchSubCategoryId(searchSubCategoryId.shortValue()); 				
					ddnRequstbean.setUserId(userId);			
					ddnRequstbean.setTransactionId(transactionId);
					ddnRequstbean.setMatchCount(rowsReturned.shortValue());
					ddnRequstbean.setCreateDate(Calendar.getInstance().getTime());					
					ddnRequstbean.setCompleted(Boolean.TRUE);
					ddnRequstbean.setSearchStatusId(searchStatusId);
					ddnRequstbean.setInvoiced(Boolean.TRUE);
						// DDN  Property fields
						ddnRequstbean.setFirstName(firstname);
						ddnRequstbean.setLastName(lastname);
						ddnRequstbean.setState(state);
						if(reference!=null)
							//ddnRequstbean.setReference(reference);
							
						try
						{			
							//requestDao.save(ddnRequstbean);
						}
						catch(Exception e)
						{
							logger.info("DDN_Request_Data_Save:::" + e.getMessage());
						}
						
						//Parsing XML response - DDN
						//logger.info("RealProp_Hybrnt_ReqstSAVE:::"+searchId);			
						
						nList=doc.getElementsByTagName("Result"); //getting Order tag		
								
						DnnCriminalXMLParser ddnXMLValue = new DnnCriminalXMLParser();
						List<CriminalResponseBean> lstDDNResults = new ArrayList<CriminalResponseBean>();					
						
						Node node = nList.item(0);
						NodeList childNodeList=node.getChildNodes();
						for(int i=0;i<childNodeList.getLength();i++)
						{				
							Node resultNode=childNodeList.item(i);
					        //Record tag
							if(resultNode.getNodeName().equals("Record"))
							{
								CriminalResponseBean ddnCrimValue = new CriminalResponseBean();
								logger.info("DDN  Record Tage Child Count FOR- II----: "+ childNodeList.getLength());
								//Getting all child node from Record node
								NodeList resultNodeList=resultNode.getChildNodes();
								for(int j=0;j<resultNodeList.getLength();j++)
								{									
									Node classNode=resultNodeList.item(j);
								 	//Getting name and Address info
									if(classNode.getNodeName().equals("OnFileDetail"))
									{										
										ddnXMLValue.setFirstName(readXMLNode(classNode, "FirstName"));
										ddnXMLValue.setLastName(readXMLNode(classNode, "LastName"));
										ddnXMLValue.setMiddleName(readXMLNode(classNode, "MiddleName"));
										ddnXMLValue.setDOB(readXMLNode(classNode, "DOB"));
										ddnXMLValue.setSex(readXMLNode(classNode, "Sex"));
										ddnXMLValue.setAddress(readXMLNode(classNode, "Address"));										
										continue;
									 }	
									if(classNode.getNodeName().equals("VerifiedBy"))
									{
										ddnXMLValue.setVerifiedBy(readXMLNode(classNode, "VerifiedBy"));
									}
									if(classNode.getNodeName().equals("Count"))
									{																			
											//Node cntNode=countNodeList.item(k);
										 	//Getting name and Address info
											if (node.getNodeType() == Node.ELEMENT_NODE) 
											{
												Element element = (Element) node;
												//logger.info("Count Length ValueEE@@:: -->>>---: " +  getValue("Offense", element));
												ddnXMLValue.setOffense(readXMLNode(classNode, "Offense"));
												ddnXMLValue.setCaseNum(readXMLNode(classNode, "CaseNum"));
												ddnXMLValue.setFileDate(readXMLNode(classNode, "FileDate"));
												ddnXMLValue.setDisposition(readXMLNode(classNode, "Disposition"));
												ddnXMLValue.setDispositionDate(readXMLNode(classNode, "DispositionDate"));
												ddnXMLValue.setOffenseCounty(readXMLNode(classNode, "OffenseCounty"));
												ddnXMLValue.setSourceofRecord(readXMLNode(classNode, "SourceofRecord"));											
												
											}

											Node countNode=resultNodeList.item(j);
											
											NodeList countNodeList= countNode.getChildNodes();
											
											
											for(int k=0;k <countNodeList.getLength();k++)
											{
												Node offnsNode=countNodeList.item(k);
												if(offnsNode.getNodeName().equals("OffenseData"))
												{
													if (offnsNode.getNodeType() == Node.ELEMENT_NODE)
													{	
														//tst
														/*NodeList nodeList = doc.getElementsByTagName("OffenseDetail"); 
														for (int iofs = 0; iofs < nodeList.getLength(); iofs++) 
														{ 
														
															logger.info("New Test.getAttribute(title) Value  ==>>>---: " + nodeList.item(iofs).getNodeValue());
															//System.out.println(nodeList.item(iofs).getNodeValue()); 
														}
														*/
														Element offnsElemnt = (Element) offnsNode;	
														if(offnsElemnt.hasAttribute("title"))
														{
															logger.info("offnsElemnt.getAttribute(title) Value  ==>>>---: " + offnsNode.getAttributes());
														}
																										
														logger.info("GetAttribute(title) attr.getNodeValue();==>>>---: " + offnsElemnt.getAttribute("title"));
														
														ddnXMLValue.setCitation(getValue("OffenseDetail",offnsElemnt));
														ddnXMLValue.setCourtNumber(getValue("OffenseDetail",offnsElemnt));
														ddnXMLValue.setCitation(getValue("OffenseDetail",offnsElemnt));
														ddnXMLValue.setCitation(getValue("OffenseDetail",offnsElemnt));
															
													}													
																					
												}
											}
											

																		
									}
								} // For J loop ends
								
								 ddnCrimValue.setUser_search_id(searchId);
								 ddnCrimValue.setFirstName(ddnXMLValue.getFirstName());
								 ddnCrimValue.setLastName(ddnXMLValue.getLastName());
								 ddnCrimValue.setMiddleName(ddnXMLValue.getMiddleName());
								 ddnCrimValue.setDOB(ddnXMLValue.getDOB());	
								 ddnCrimValue.setSex(ddnXMLValue.getSex());
								 ddnCrimValue.setAddress(ddnXMLValue.getAddress());
								 ddnCrimValue.setVerifiedBy(ddnXMLValue.getVerifiedBy());
								 ddnCrimValue.setOffense(ddnXMLValue.getOffense());
								 ddnCrimValue.setCaseNum(ddnXMLValue.getCaseNum());	
								 ddnCrimValue.setFileDate(ddnXMLValue.getFileDate());
								 ddnCrimValue.setDisposition(ddnXMLValue.getDisposition());
								 ddnCrimValue.setDispositionDate(ddnXMLValue.getDispositionDate());
								 ddnCrimValue.setSourceofRecord(ddnXMLValue.getSourceofRecord());
								 ddnCrimValue.setCitation(ddnXMLValue.getCitation());	
								 ddnCrimValue.setOffnsCount(ddnXMLValue.getOffnsCount());
								 ddnCrimValue.setCourtNumber(ddnXMLValue.getCourtNumber() );
								 ddnCrimValue.setOffnsDLState(ddnXMLValue.getOffnsDLState());
								 ddnCrimValue.setCaseType(ddnXMLValue.getCaseType());
								 ddnCrimValue.setCourtAddress(ddnXMLValue.getCourtAddress());
								 ddnCrimValue.setCourtName(ddnXMLValue.getCourtName());
								 ddnCrimValue.setFacilityCounty(ddnXMLValue.getFacilityCounty());
								 lstDDNResults.add(ddnCrimValue);
					         } // Record tag IF ends
							
						} //For i ends 
						//if(lstDDNResults.size() > 0)	
						//	logger.info("DDN  criminal List Count--%$##: "+ lstDDNResults.size());
						session.setAttribute("DDNResults", lstDDNResults);
							
						
						
						
					} //totalMatches If Ends
						
					
			  }
		    
			} //Try ends
			catch(Exception e)
			{
				logger.info("DDN_catch_Err_:::::----"+ e.getMessage());
				logger.info("DDN_catch_Err_TRACE:::::----"+ e.getStackTrace());
				//logger.info("DDN_catch_Err_:::::----"+ e.);
			}
			
		
		return searchId;
		
	}
	
	
	private static String getValue(String tag, Element element)
	{
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	 }

	public   String readXMLNode(Node firstNode, String strTagName){
		String strQueryResult = "";
		Element firstElement = (Element)firstNode;
		//logger.info("DDN_readXMLNode MEtnd:::::---->>" + strTagName);
		NodeList queryResultList = firstElement.getElementsByTagName(strTagName);
		Element firstqueryResultElement = (Element)queryResultList.item(0);
		if(firstqueryResultElement!=null)
		{
			//logger.info("DDN_readXMLNode MEtnd:firstqueryResultElement::::---->>" + firstqueryResultElement);
			NodeList textqueryResultList = firstqueryResultElement.getChildNodes();
			Node lNode = ((Node)textqueryResultList.item(0));			
			if(lNode!=null){
				strQueryResult = lNode.getNodeValue().trim();
				//logger.info("DDN_readXMLNode MEtnd:::::---->>" + strQueryResult);
			}
		}
		return (strQueryResult);
	}


	public long queryBusinesFunnel(int userId, int transactionId,
			String businessname, String state, String city, String reference) {
		// TODO Auto-generated method stub
		return this.businessquery(userId, 1, transactionId, 
				businessname, state, city, reference);
	}
	public long businessquery(Integer  userId,Integer searchSubCategoryId,
			Integer transactionId, String evicbusinessname, String evicstate,
			String eviccity, String evicreference)
			
	{
		
		return 0;
	}
	
}
