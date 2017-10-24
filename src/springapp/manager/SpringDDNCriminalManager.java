package springapp.manager;
import java.io.DataOutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPException;

import net.searchsystems.limestone.BGCOffender;
import net.searchsystems.limestone.BGCOffenderPeer;
import net.searchsystems.limestone.BGCProduct;
import net.searchsystems.limestone.BGCProductPeer;
import net.searchsystems.limestone.BGCRequest;
import net.searchsystems.limestone.BGCRequestPeer;
import net.searchsystems.limestone.BGCResponse;
import net.searchsystems.limestone.BjlDataSources;
import net.searchsystems.limestone.BjlDataSourcesPeer;
import net.searchsystems.limestone.bean.BGCRequestBean;
import net.searchsystems.limestone.bean.BGCResponseBean;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient; 
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
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
	 * Prepare to run a search.
	 * @throws  
	 * @throws TorqueException 
	 */
	public int prepareSearch(int userId, String firstName, String middleInitial, String lastName, 
			int dobMonth, int dobDay, int dobYear, int ssn,
			int bgcProductId, boolean isDpassUser,
			String crimJurisdiction, String crimPurpose, String referCode) throws SearchException {
		
		logger.info("Values from the form => userId - " +  userId + "; firstName - " +  firstName + "; lastName - " + lastName + 
				"; dobMonth - " + dobMonth + "; dobDay - " + dobDay + "; dobYear - " + dobYear + 
				"; bgcProductId - " + bgcProductId + "; crimJurisdiction - " + crimJurisdiction + "; crimPurpose - " + crimPurpose);
		
		// get product info first
		BGCProduct product;
		try {
			product = BGCProductPeer.retrieveByPK(bgcProductId);
		} catch (Exception e) {
			logger.error("Could not find BGC product", e);
			throw new SearchException(e);
		}
		
		BigDecimal price;
		if (isDpassUser) {
			price = product.getPriceDpass();
		} else {
			price = product.getPriceBasic();
		}
		logger.info("Basic price => " + price);
		
		BGCRequestBean bean = new BGCRequestBean();		

		bean.setBgcProductId(bgcProductId);
		bean.setBgcStatusId(1);
		bean.setUserId(userId);
		bean.setDateCreated(new Date());
		bean.setPrice(price);				
		
		// purpose?
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setMiddleName(middleInitial);
		bean.setDobMonth(dobMonth);
		bean.setDobDay(dobDay);
		bean.setDobYear(dobYear);
		bean.setReferenceCode(referCode);
		bean.setDobYearRange(ssn);  //Note to developer: DobYearRange is mapped to SSN  to adjust backend table
		
		// jurisdiction?
		if (crimJurisdiction.equals("Nationwide")) {
			bean.setUsonesearch(true);
		} else {
			bean.setUsonesearch(false);
			bean.setState(crimJurisdiction.substring(0, 2));
		}
		springapp.domain.BGCRequestBean purpose=new springapp.domain.BGCRequestBean();
		purpose.setUserSearchId(userId);
		purpose.setBgcPurpose(crimPurpose);
		purpose.setDateCreated(new Date());
		
		BGCRequest request;
		try {
			request = BGCRequest.createBGCRequest(bean);
			request.save();
		} catch (TorqueException e) {
			logger.error("Could not create BGC request for DDN Search", e);
			throw new SearchException(e);
		}
		int requestId = request.getBgcRequestId();
		logger.info("RequestId for DDN Search => " + requestId);
		purpose.setBgc_request_id(requestId);
		//purposeDao.bgcpurposeSave(purpose);
		
		return requestId;
	}
	
	
	/**
	 * Query the criminal records interface and store the results in the
	 * database.
	 * 
	 * @throws SOAPException
	 *             If the SOAP request failed for any reason.
	 */
	public BGCResponseBean runSearch(int requestId, HttpSession session) throws SearchException {
		BGCRequest req;
		try {
			req = BGCRequestPeer.retrieveByPK(requestId);
		} catch (NoRowsException e) {
			throw new SearchException("No such request while doing DDN Criminal Search", e);
		} catch (TooManyRowsException e) {
			throw new SearchException("Duplicate request detected while doing DDN Criminal Search", e);
		} catch (TorqueException e) {
			throw new SearchException("Problem with torque while doing DDN Criminal Search", e);
		}
		BGCRequestBean reqBean = req.getBean(); 
		
		short matchCount = 0;
		String searchStatus = null;
		
		BGCRequestHelper reqHelper = new BGCRequestHelper();
		BGCResponseHelper respHelper = new BGCResponseHelper();
		
		String xmlQuery = prepareXmlForDDNRequest(reqBean);
		logger.info("xmlQuery to post to DDN Criminal search for personal record\n: " + xmlQuery.replaceAll("><", ">\n<"));
		
		Document xmlData = reqHelper.doHttpPostQuery(liveUrl, xmlQuery);
		
		// This function only for reading XML for testing purpose
		//readXMLForTest(xmlData);
		 
		logger.info("Remote query completed for DDN Criminal personal search, parsing results - and Result is \n=> " + xmlData);
					
		// check for error element first
		NodeList errorlist = xmlData.getElementsByTagName("errors");
		logger.info("errorlist - " + errorlist + "; length - " + errorlist.getLength());
		if (errorlist.getLength() > 0) {
			Node n = errorlist.item(0);
			logger.info("errorlist - node - " + n);
			// an invalid search was created
			String error = n.getNodeValue();
			logger.info("errorlist - error - " +error);
			
			throw new SearchException(error);
			
			//Add email to sendd to Tim here - Ram Kumarappan (Search term - Remote query complete, parsing results)
		}		
		
		// process the results	
		Element eRoot = (Element) xmlData.getDocumentElement();
		
		Element eProduct = (Element) eRoot.getElementsByTagName("Order").item(0);
		Element eSearch;
		eSearch = (Element) eProduct.getElementsByTagName("OrderDetail").item(0);
		
		Element eResponse = (Element) eSearch.getElementsByTagName("Result").item(0);

		Integer qtyFound = Integer.parseInt(eResponse.getElementsByTagName("OffenderCount").item(0).getFirstChild().getNodeValue());
		Integer qtyReturned = qtyFound;
		
		logger.info("found: " + qtyFound + ": returned: " + qtyReturned);
		
		// this does all the work
		ArrayList<BGCOffenderMapHolder> listOffenders = null;
		Element eOffenders = eResponse;
		List<CriminalResponseBean> lstDDNResults = new ArrayList<CriminalResponseBean>();
		if (eSearch.getElementsByTagName("Status").item(0).getFirstChild().getNodeValue().equalsIgnoreCase("NO RECORD") || qtyFound == 0) {
			matchCount = 0;
			searchStatus = "No results";
		} else {
			matchCount = qtyFound.shortValue();
			searchStatus = "Found results";
			//listOffenders = respHelper.parseResponseForDDN(eResponse);
			
			

				//Parsing XML response - DDN
				//logger.info("RealProp_Hybrnt_ReqstSAVE:::"+searchId);			
				
				NodeList nList=xmlData.getElementsByTagName("Result"); //getting Order tag		
						
				DnnCriminalXMLParser ddnXMLValue = new DnnCriminalXMLParser();
				
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
								ddnXMLValue.setVerifiedBy(classNode.getFirstChild().getNodeValue());
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
						
						 ddnCrimValue.setUser_search_id(reqBean.getUserId());
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
							
			
			
		}
		logger.info("Results parsed ok, storing in db");

		BGCResponseBean resBean = new BGCResponseBean();
		resBean.setBgcRequestId(requestId);
		resBean.setQuantityFound(qtyFound);
		resBean.setQuantityReturned(qtyReturned);
		resBean.setDateCreated(new Date());
		resBean.setHashKey(generateHashKey(resBean.getClass().getName()));
		
		session.setAttribute("DDNResults", lstDDNResults);
		
		// store the search using torque
		BGCResponse response;
		try {
			response = BGCResponse.createBGCResponse(resBean);
			response.save();
			int responseId = response.getBgcResponseId();		
			logger.info("responseId: " + responseId);
			
			
		} catch (TorqueException e) {
			logger.error("Error storing BGC search results", e);
			throw new SearchException(e);
		}
		
		return response.getBean();
	}
	
	protected String generateHashKey(String className) {
		// generate hash code based on input
		Random rand = new Random(System.currentTimeMillis());
		String key = "here is our secret query key for DDN";
		//String data = key + className + id + String.valueOf(rand.nextLong());
		String data = key + className + String.valueOf(rand.nextLong());
		String hashKey = DigestUtils.shaHex(data);
		return hashKey;
	}


	private String prepareXmlForDDNRequest(BGCRequestBean reqBean) {
		String username = "tjkoster", password = "tksearch2014";
		String dob = reqBean.getDobMonth() + "/" +reqBean.getDobDay() +"/"+ reqBean.getDobYear();
		String ssn = String.valueOf(reqBean.getDobYearRange());  //Note to developer: DobYearRange is mapped to SSN  to adjust backend table
		ssn = (ssn != null && ssn.trim().length() == 9? String.format("%s-%s-%s", ssn.substring(0,3), ssn.substring(3,5), ssn.substring(5)) : ""); 
		
		logger.info("Dob in preparing DDN Search request => " + dob + "; SSN => " + ssn); //Note to developer: DobYearRange is mapped to SSN  to adjust backend table
		
		String xml= "<?xml version='1.0' encoding='utf-8'?><OrderXML><Method>SEND ORDER</Method>";
		xml += "<Authentication><Username>"+ username +"</Username><Password>"+ password +"</Password></Authentication>";
		//xml += "<TestMode>YES</TestMode>";
		xml += "<ReturnResultURL>/springapp/funnel/ddnResults.do</ReturnResultURL>";
		xml += "<OrderingUser></OrderingUser><Order><BillingReferenceCode></BillingReferenceCode><Subject>";
		 //if(session.getAttribute("ddnXMLValueNameSearch") != null)
		 //{
		xml += "<FirstName>"+ reqBean.getFirstName() +"</FirstName><MiddleName></MiddleName><LastName>"+ reqBean.getLastName() +"</LastName><Generation></Generation>";
		xml += "<DOB>"+ (dob != null? dob.replace("0/0/0", "26/08/1928") : dob) +"</DOB>";
		xml += "<SSN>"+ (ssn != null ? ssn : "") +"</SSN>";
		xml += "<Gender></Gender><Ethnicity></Ethnicity><DLNumber></DLNumber>";
		 //}				 				
		xml += "<ApplicantPosition></ApplicantPosition><CurrentAddress><StreetAddress></StreetAddress><City></City><State></State>";
		/*if(state!= null && !state.equals("all") )
			xml += "<State>"+ state +"</State>";
		else 
			xml += "<State></State>";*/
	    xml += "<Zipcode></Zipcode></CurrentAddress><Aliases><Alias><FirstName></FirstName>";
		xml += "<MiddleName></MiddleName><LastName></LastName></Alias></Aliases></Subject>";
		String strUniqueDateIdentifier = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		logger.info("Unique Identifier for oder id to send it to DDN Search - " + strUniqueDateIdentifier);
		
		if(reqBean.getUsonesearch()) 
			xml += "<OrderDetail ServiceCode='Natcrim' OrderId='" + strUniqueDateIdentifier + "'><State></State></OrderDetail>";
		else
			xml += "<OrderDetail ServiceCode='State Instant' OrderId='" + strUniqueDateIdentifier + "'><State>"+ reqBean.getState() +"</State></OrderDetail>";
		xml += "</Order></OrderXML> ";
		
		
		logger.info("Request xml: "+ xml.replaceAll("><", ">\n<"));  //SSN:numbr: -- 149-58-7526

		
		return xml;
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
         	String username = "tjkoster"; //= "kostertj";
			String password = "tksearch2014"; //= "Koster2016";
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
				//xml += "<TestMode>YES</TestMode>";
				xml += "<ReturnResultURL>https://dev.searchsystems.net/springapp/funnel/ddnResults.do</ReturnResultURL>";
				xml += "<OrderingUser></OrderingUser><Order><BillingReferenceCode></BillingReferenceCode><Subject>";
				 //if(session.getAttribute("ddnXMLValueNameSearch") != null)
				 //{
				xml += "<FirstName>"+ firstname +"</FirstName><MiddleName></MiddleName><LastName>"+ lastname +"</LastName><Generation></Generation>";
				xml += "<DOB>"+ (dob != null? dob.replace("0/0/0", "") : "") +"</DOB>";
				xml += "<SSN>"+ (ssn != null? ssn : "") +"</SSN>";
				xml += "<Gender></Gender><Ethnicity></Ethnicity><DLNumber></DLNumber>";
				 //}				 				
				xml += "<ApplicantPosition></ApplicantPosition><CurrentAddress><StreetAddress></StreetAddress><City></City><State></State>";
				/*if(state!= null && !state.equals("all") )
					xml += "<State>"+ state +"</State>";
				else 
					xml += "<State></State>";*/
			    xml += "<Zipcode></Zipcode></CurrentAddress><Aliases><Alias><FirstName></FirstName>";
				xml += "<MiddleName></MiddleName><LastName></LastName></Alias></Aliases></Subject>";
				String strUniqueDateIdentifier = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
				logger.info("Unique Identified - " + strUniqueDateIdentifier);
				if(state!= null && state.equals("all") ) 
					xml += "<OrderDetail ServiceCode='Natcrim' OrderId='" + (transactionId != 0 ? transactionId : strUniqueDateIdentifier) + "'><State></State></OrderDetail>";
				else
					xml += "<OrderDetail ServiceCode='State Instant' OrderId='" + (transactionId != 0 ? transactionId : strUniqueDateIdentifier) + "'><State>"+state +"</State></OrderDetail>";
				xml += "</Order></OrderXML> ";
				
				
				logger.info("Request xml: "+ xml.replaceAll("><", ">\n<"));  //SSN:numbr: -- 149-58-7526

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
		    	NodeList rowList=doc.getElementsByTagName("OffenderCount"); //getting Order tag		    	
		    	Node headNode =(Node) rowList.item(0).getChildNodes().item(0);		    	
		    	rowsReturned= Integer.parseInt(headNode.getNodeValue()); // no of records		    	
		    	logger.info("Rows Returned => " + rowsReturned);		    	
		    	
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
							
						
						
						
					} else
						throw new SearchException("No Records found.");
						
					
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
