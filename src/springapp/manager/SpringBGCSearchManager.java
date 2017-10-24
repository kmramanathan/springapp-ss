package springapp.manager;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.xml.soap.SOAPException;

import net.searchsystems.limestone.*;
import net.searchsystems.limestone.bean.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;
import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.TorqueException;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import springapp.domain.DnnCriminalXMLParser;
import springapp.domain.CriminalSearch.CriminalResponseBean;
import springapp.repository.BGCRequestDao;
import springapp.service.user.UserManager;

/**
 * Provides support for querying the Criminal Records interface.
 */
@Service("bgcSearchManager")
public class SpringBGCSearchManager implements ResourceLoaderAware {
	// ---------- PUBLIC DATA ----------
	// criminal settings	
	public static final String bgcPostUrlTest = "https://model.backgroundchecks.com/integration/bgcdirectpost2.aspx";
	public static final String bgcPostUrlLive = "https://direct.backgroundchecks.com/integration/bgcdirectpost2.aspx";
	public final static String liveUrl="https://secure.datadirectnow.com/webservice/default.cfm";//"https://www.tracersinfo.com/xml.php";
	
	// ---------- PRIVATE DATA ----------
	private static List<String> codes = new ArrayList<String>();
	private static HashMap<String, ArrayList<BGCDescription>> mapBgcDescriptions;
	private static HashMap<String, String> mapUsStates;
	@Autowired
	protected BGCRequestDao purposeDao;

	private static final Logger logger = Logger.getLogger("BGCSearchManager");

	@Autowired
	protected ResourceLoader resourceLoader;
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;		
	}
	
	@Autowired
	private UserManager userManager;
	
	private static final String xmlBgcStrings = "BGCDescriptions.xml";
	
	/*
	 * called at request start
	 */
	public void init() {
		if (mapBgcDescriptions == null) {
			URL url;
			//this.getClass().getPackage()
			String path = "classpath:" + this.getClass().getPackage().getName().replace('.', '/') + "/" + xmlBgcStrings;
			try {				
				url = resourceLoader.getResource(path).getURL();				
				mapBgcDescriptions = loadBgcDescriptions(url);
			} catch (IOException e) {
				String msg = "loadBgcDescriptions failed: " + path;
				logger.error(msg, e);
				throw new IllegalStateException(msg, e);
			}
		}
		
		if (mapUsStates == null) {
			mapUsStates = userManager.getUSStates();
		}
	}
	
	/*
	 * initialization functions
	 */
	protected HashMap<String, ArrayList<BGCDescription>> loadBgcDescriptions(URL url) {
		HashMap<String, ArrayList<BGCDescription>> queryStrings = null;
		queryStrings = new HashMap<String, ArrayList<BGCDescription>>();
		
		try {
			XMLConfiguration config = new XMLConfiguration();
			config.setDelimiterParsingDisabled(true);
			config.load(url);			
			
			List<?> listRecords = config.configurationsAt("record");
			if (listRecords != null) {
				logger.debug("records: " + listRecords.size());
				for (Iterator<?> i = listRecords.iterator(); i.hasNext();) {
					HierarchicalConfiguration subRecord = (HierarchicalConfiguration) i.next();
					String sJurisdiction = subRecord.getString("jurisdiction");
					ArrayList<BGCDescription> listJuris = new ArrayList<BGCDescription>();
					queryStrings.put(sJurisdiction, listJuris);
					logger.debug("load: " + sJurisdiction);
					
					SubnodeConfiguration snDescriptions = subRecord.configurationAt("descriptions");
					List<?> listRows = snDescriptions.configurationsAt("row");
					logger.debug("rows: " + listRows.size());
					for (Iterator<?> j = listRows.iterator(); j.hasNext();) {
						HierarchicalConfiguration subRow = (HierarchicalConfiguration) j.next();
						SubnodeConfiguration snProvider = subRow.configurationAt("provider");
						SubnodeConfiguration snDesc = subRow.configurationAt("desc");
						String sProvider = snProvider.getString("");
						String sDesc = snDesc.getString("");
						
						BGCDescription b = new BGCDescription();
						b.setJurisdiction(sJurisdiction);
						b.setProvider(sProvider);
						b.setDescription(sDesc);
						
						queryStrings.get(sJurisdiction).add(b);
					}
				}
			}			
		} catch (ConfigurationException e) {
			logger.error("Failed to load BGC strings!", e);
		}
		return queryStrings;
	}
	
	
	/*
	 * exposed methods
	 */
	public int test() {
		return 1;
	}
	
	/*
	 * description info, stored in memory
	 */
	public BGCDescription[] getBGCJurisdictionInfo(String jurisdiction) {
		if (jurisdiction.equalsIgnoreCase("us")) {
			jurisdiction = "United States";
		}
		if (jurisdiction.equalsIgnoreCase("all")) {
			jurisdiction = "United States";
		}
		if (jurisdiction.equalsIgnoreCase("intl")) {
			jurisdiction = "International";
		}
		logger.info("jurisdiction: " + jurisdiction);
		ArrayList<BGCDescription> list = mapBgcDescriptions.get(jurisdiction);
		logger.info("List size :"+list.size());
		return list.toArray(new BGCDescription[0]);
	}
	
	public String[] getBGCJurisdictions() {	
		Set<String> set = mapBgcDescriptions.keySet();
		return set.toArray(new String[0]);
	}
	public HashMap<String, ArrayList<BGCDescription>> getAllBGCDescriptions() {
		return mapBgcDescriptions;
	}
	
	/*
	 * single object getters
	 */	
	public BGCProductBean getProduct(int productId) throws NoRowsException, TooManyRowsException, TorqueException {
		BGCProduct p = BGCProductPeer.retrieveByPK(productId);
		return p.getBean();
	}
	public BGCRequestBean getRequest(int requestId) throws NoRowsException, TooManyRowsException, TorqueException {
		BGCRequest r = BGCRequestPeer.retrieveByPK(requestId);		
		return r.getBean();
	}	
	public BGCResponseBean getResponse(int responseId) throws NoRowsException, TooManyRowsException, TorqueException {
		BGCResponse r = BGCResponsePeer.retrieveByPK(responseId);
		return r.getBean();
	}
	public BGCOffenderBean getOffender(int offenderId) throws NoRowsException, TooManyRowsException, TorqueException {
		BGCOffender o = BGCOffenderPeer.retrieveByPK(offenderId);
		return o.getBean();
	}
	
	/*
	 * these check the hash key also
	 */
	public BGCResponseBean getResponse(int responseId, String hashKeyResponse) throws NoRowsException, TooManyRowsException, TorqueException {
		BGCResponse r = BGCResponsePeer.retrieveByPK(responseId);
		if (r.getHashKey().equals(hashKeyResponse)) {
			return r.getBean();
		} else {
			return null;
		}
	}
	public BGCOffenderBean getOffender(int offenderId, String hashKeyOffender) throws NoRowsException, TooManyRowsException, TorqueException {
		BGCOffender o = BGCOffenderPeer.retrieveByPK(offenderId);
		if (o.getHashKey().equals(hashKeyOffender)) {
			return o.getBean();
		} else {
			return null;
		}
	}
	/*
	 * array of object getters
	 */	
	public BGCOffenderBean[] getOffenders(int responseId) throws TorqueException {		
		BGCResponse r;
		try {
			r = BGCResponsePeer.retrieveByPK(responseId);
		} catch (NoRowsException e) {
			String msg = "Response not found: " + responseId;
			logger.error(msg, e);
			return null;
		}	
		
		List<BGCOffender> os = r.getBGCOffenders();
		ArrayList<BGCOffenderBean> list = new ArrayList<BGCOffenderBean>();
		for (BGCOffender o : os) {		
				list.add(o.getBean());
		}
		return list.toArray(new BGCOffenderBean[0]);	
	}	
	
	public BGCAliasBean[] getAliases(int offenderId) throws TorqueException {
		BGCOffender o;
		try {
			o = BGCOffenderPeer.retrieveByPK(offenderId);
		} catch (NoRowsException e) {
			String msg = "Offender not found: " + offenderId;
			logger.error(msg, e);
			return null;
		}

		List<BGCAlias> as = o.getBGCAliass();
		ArrayList<BGCAliasBean> list = new ArrayList<BGCAliasBean>();
		for (BGCAlias a : as) {
			list.add(a.getBean());
		}
		return list.toArray(new BGCAliasBean[0]);
	}
	
	public BGCOffenseBean[] getOffenses(int offenderId) throws TorqueException {
		BGCOffender off;
		try {
			off = BGCOffenderPeer.retrieveByPK(offenderId);
		} catch (NoRowsException e) {
			String msg = "Offender not found: " + offenderId;
			logger.error(msg, e);
			return null;
		}

		List<BGCOffense> os = off.getBGCOffenses();
		ArrayList<BGCOffenseBean> list = new ArrayList<BGCOffenseBean>();
		for (BGCOffense o : os) {
			// need to do this to populate the supps in the main bean
			o.getBGCOffenseSupplements();
			list.add(o.getBean());
		}
		return list.toArray(new BGCOffenseBean[0]);
	}
	
	public BGCOffenseSupplementBean[] getOffenseSupplements(int offenseId) throws NoRowsException, TooManyRowsException, TorqueException {
		BGCOffense off;
		try {
			off = BGCOffensePeer.retrieveByPK(offenseId);
		} catch (NoRowsException e) {
			String msg = "Offense not found: " + offenseId;
			logger.error(msg, e);
			return null;
		}
		
		List<BGCOffenseSupplement> os = off.getBGCOffenseSupplements();
		ArrayList<BGCOffenseSupplementBean> list = new ArrayList<BGCOffenseSupplementBean>();
		for (BGCOffenseSupplement o : os) {
			list.add(o.getBean());
		}
		return list.toArray(new BGCOffenseSupplementBean[0]);
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
		
		BGCRequestBean bean = new BGCRequestBean();		

		bean.setBgcProductId(bgcProductId);
		bean.setBgcStatusId(1);
		bean.setUserId(userId);
		bean.setDateCreated(new Date());
		bean.setPrice(price);				
		
		// purpose?
		bean.setFirstName(firstName);
		bean.setFirstNameExact(true);
		bean.setLastName(lastName);
		bean.setLastNameExact(true);
		bean.setMiddleName(middleInitial);
		bean.setDobMonth(dobMonth);
		bean.setDobDay(dobDay);
		bean.setDobYear(dobYear);
		bean.setDobYearRange(ssn);
		bean.setReferenceCode(referCode);
		
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
			logger.error("Could not create BGC request", e);
			throw new SearchException(e);
		}
		int requestId = request.getBgcRequestId();
		logger.info("requestId: " + requestId);
		purpose.setBgc_request_id(requestId);
		purposeDao.bgcpurposeSave(purpose);
		
		return requestId;
	}

	public boolean setReferenceCode(int requestId, String referenceCode) throws NoRowsException, TooManyRowsException, TorqueException {
		BGCRequest req;
		req = BGCRequestPeer.retrieveByPK(requestId);
		if (referenceCode.length() > 50) {
			referenceCode = referenceCode.substring(0, 50);
		}
		req.setReferenceCode(referenceCode);
		req.save();
		return true;
	}
	
	public boolean setTransactionId(int responseId, int transactionId) throws SearchException {
		BGCResponse res;
		try {
			res = BGCResponsePeer.retrieveByPK(responseId);
			res.setTransactionId(transactionId);
			res.save();
		} catch (NoRowsException e) {
			throw new SearchException(e);
		} catch (TooManyRowsException e) {
			throw new SearchException(e);
		} catch (TorqueException e) {
			throw new SearchException(e);
		}
		return true;
	}

	/*
	public boolean authorizeSearch(int requestId) throws AxisFault, SOAPException, TorqueException {
		BGCRequest req;
		req = BGCRequestPeer.retrieveByPK(requestId);
		
		SsUsers u = SsUsersPeer.retrieveByPK(req.getUserId());
		if (u.getNoPremium()) {
			return false;			
		}
		String username = u.getUsername();
		
		TransactionManagerGlueImpl impl = new TransactionManagerGlueImpl();
		TransactionResponse res = impl.createBGC(username, req.getPrice().toString(), (short) -1, "BGC Search", false);		
		
		short resCode = Short.parseShort(res.getResponseCode());
		if (resCode == 1) {
			return true;
		} else {
			return false;
		}
	}
	*/
	
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
								logger.info("First child value for verified by - " + classNode.getFirstChild().getNodeName() + "; ");
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
	 * Query the criminal records interface and store the results in the
	 * database.
	 * 
	 * @throws SOAPException
	 *             If the SOAP request failed for any reason.
	 */
	public BGCResponseBean runSearchOld(int requestId) throws SearchException {
		BGCRequest req;
		try {
			req = BGCRequestPeer.retrieveByPK(requestId);
		} catch (NoRowsException e) {
			throw new SearchException("no such request", e);
		} catch (TooManyRowsException e) {
			throw new SearchException("duplicate request detected", e);
		} catch (TorqueException e) {
			throw new SearchException("problem with torque", e);
		}
		BGCRequestBean reqBean = req.getBean(); 
		
		short matchCount = 0;
		String searchStatus = null;
		
		BGCRequestHelper reqHelper = new BGCRequestHelper();
		BGCResponseHelper respHelper = new BGCResponseHelper();
		
		String xmlQuery = reqHelper.prepareXmlForBGCRequest(reqBean);
		logger.info("xmlQuery: " + xmlQuery);
		Document xmlData = reqHelper.doHttpPostQuery(bgcPostUrlLive, xmlQuery);
		
		// This function only for reading XML for testing purpose
		//readXMLForTest(xmlData);
		 
		logger.info("Remote query complete, parsing results");
					
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
		
		Element eProduct = (Element) eRoot.getElementsByTagName("product").item(0);
		Element eSearch;
		//if (crimJurisdiction.equals("ALL")) {
		if (reqBean.getUsonesearch()) {
			eSearch = (Element) eProduct.getElementsByTagName("USOneSearch").item(0);
		} else {
			eSearch = (Element) eProduct.getElementsByTagName("SingleStateOneSearch").item(0);
		}
		Element eResponse = (Element) eSearch.getElementsByTagName("response").item(0);
//		Element eSummary;
//		if (crimJurisdiction.equals("ALL")) {
//			eSummary = (Element) eResponse.getElementsByTagName("detail").item(0);			
//		} else {
//			eSummary = (Element) eResponse.getElementsByTagName("summary").item(0);			
//		}		
		Element eSummary = (Element) eResponse.getElementsByTagName("summary").item(0);
		Element eDetail = (Element) eResponse.getElementsByTagName("detail").item(0);
		// need to handle eDetail == null
		// as well as eSummary == null
		Element eOffenders;
		if (eDetail == null) {
			if (eSummary == null) {
				String msg = "eSummary & eDetail are both null? : ";
				logger.error(msg + eRoot.toString());
				throw new SearchException("eSummary & eDetail are both null?");
			} else {
				eOffenders = (Element) eSummary.getElementsByTagName("offenders").item(0);
			}
		} else {
			eOffenders = (Element) eDetail.getElementsByTagName("offenders").item(0);
		}
		
		Integer qtyFound = Integer.parseInt(eOffenders.getAttribute("qtyFound"));
		Integer qtyReturned = Integer.parseInt(eOffenders.getAttribute("qtyReturned"));
		
		logger.info("found: " + qtyFound + ": returned: " + qtyReturned);
		
		// this does all the work
		ArrayList<BGCOffenderMapHolder> listOffenders = null;
		if (qtyFound == 0) {
			matchCount = 0;
			searchStatus = "No results";
		} else {			
			matchCount = qtyFound.shortValue();
			searchStatus = "Found results";			
			listOffenders = respHelper.parseResponse(eOffenders);
		}
		
		logger.info("Results parsed ok, storing in db");

		BGCResponseBean resBean = new BGCResponseBean();
		resBean.setBgcRequestId(requestId);
		resBean.setQuantityFound(qtyFound);
		resBean.setQuantityReturned(qtyReturned);
		resBean.setDateCreated(new Date());
		resBean.setHashKey(generateHashKey(resBean.getClass().getName()));
		
		// store the search using torque
		BGCResponse response;
		try {
			response = BGCResponse.createBGCResponse(resBean);
			response.save();
			int responseId = response.getBgcResponseId();		
			logger.info("responseId: " + responseId);
			
			// bail out here if no results
			if (matchCount == 0) {
				logger.info("no results, returning");
			} else {				
				// loop through all matches and add them into a batch operation list 
				for (Iterator<BGCOffenderMapHolder> it = listOffenders.iterator(); it.hasNext(); ) {
					BGCOffenderMapHolder match = it.next();						
					
					// add offender
					int offenderId = processOffenderTop(responseId, match);
					//logger.info("added offender: " + offenderId);				
					
					// these are linked to offender
					ArrayList<Integer> aliasIdList = processAliases(offenderId, match);
					if (eDetail != null) {
						ArrayList<Integer> offenseIdList = processOffenses(offenderId, match);
						// mark details as fetched
						BGCOffender offender = BGCOffenderPeer.retrieveByPK(offenderId);
						offender.setHasDetail(true);
						offender.save();
					}
				}
			}
		} catch (TorqueException e) {
			logger.error("Error storing BGC search results", e);
			throw new SearchException(e);
		}
		
		return response.getBean();
	}

	// helper method for single case
	public void fetchOffenderDetails(Integer offenderToGet) throws TorqueException, SearchException {
		ArrayList<Integer> offendersToGet = new ArrayList<Integer>();
		offendersToGet.add(offenderToGet);
		fetchOffenderDetails(offendersToGet);
	}
	
	public void fetchOffenderDetails(ArrayList<Integer> offendersToGet) throws TorqueException, SearchException {
		for (int offenderId : offendersToGet) {
			// load offender ID & key
			BGCOffender offender;
			try {
				offender = BGCOffenderPeer.retrieveByPK(offenderId);				
			} catch (NoRowsException e) {
				logger.error("No such offender: " + offenderId);
				continue;
			} catch (TooManyRowsException e) {
				logger.error("Duplicate offender: " + offenderId);
				continue;
			} catch (TorqueException e) {
				logger.error("Torque failed: " + offenderId, e);
				throw new SearchException(e);
			}
			BGCOffenderBean offenderBean = offender.getBean();

			// don't fetch it again
			if (offenderBean.getHasDetail()) {
				logger.info("Already fetched offender: " + offenderId);
				continue;
			}
			
			// fetch the offender details (offenses)
			logger.info("fetching offender: " + offenderId);
			
			// build & run the query
			BGCRequestHelper reqHelper = new BGCRequestHelper();
			BGCResponseHelper respHelper = new BGCResponseHelper();
			
			String xmlQuery = reqHelper.prepareXmlForOffenderSecureKey(offenderBean);
			logger.info("xmlQuery: " + xmlQuery);
			Document xmlData = reqHelper.doHttpPostQuery(bgcPostUrlLive, xmlQuery);
			
			// This function only for reading XML for testing purpose
			 readXMLForTest(xmlData);
			
			
			logger.info("Remote query complete, parsing results");

			// process results
			BGCOffenderMapHolder match = respHelper.parseResponseSingle(xmlData);
			
			// update the database
			ArrayList<Integer> offenseIdList = processOffenses(offenderId, match);

			// update offender
			// XXX dont forget to enable this once store works
			offender.setHasDetail(true);
			offender.save();			
		}
	}
	
	protected int processOffenderTop(int responseId, BGCOffenderMapHolder match) throws TorqueException {	
		HashMap<String,String> mapPersonal = match.getMapPersonal();
		HashMap<String,String> mapAddress = match.getMapAddress();
		HashMap<String,String> mapRecord = match.getMapRecord();
	
		BGCOffenderBean bean = new BGCOffenderBean();
		bean.setBgcResponseId(responseId);

		String hashKey = generateHashKey(bean.getClass().getName());
		bean.setHashKey(hashKey);		
		
		// copy direct from personal map
		bean.setFullName(     mapPersonal.get("fullName"));
		bean.setGender(       mapPersonal.get("gender"));
		bean.setEyeColor(     mapPersonal.get("eyeColor"));
		bean.setHairColor(    mapPersonal.get("hairColor"));
		bean.setWeight(       mapPersonal.get("weight"));
		bean.setHeightFeet(   mapPersonal.get("feet"));
		bean.setHeightInches( mapPersonal.get("inches"));
		bean.setRace(         mapPersonal.get("race"));
		bean.setImageUrl(     mapPersonal.get("imageURL"));
		
		// special handling
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dob;
		String sDOB = mapPersonal.get("DOB");
		try {
			dob = df.parse(sDOB);
		} catch (ParseException e) {
			logger.error("bad DOB: " + sDOB);
			dob = null;
		}
		bean.setDateOfBirth(dob);
		
		Integer dobType = Integer.parseInt(mapPersonal.get("DOBType"));
		bean.setDobType(dobType);
		
		// copy direct from address map
		bean.setStreet(     mapAddress.get("street"));
		bean.setCity(       mapAddress.get("city"));
		bean.setPostalCode( mapAddress.get("postalCode"));
		bean.setState(      mapAddress.get("state"));
		bean.setCounty(     mapAddress.get("county"));
		bean.setCountry(    mapAddress.get("country"));
		
		// copy direct from record map
		bean.setRecordOffenderId( mapRecord.get("offenderID"));
		bean.setRecordSecureKey(  mapRecord.get("secureKey"));
		bean.setRecordSource(     mapRecord.get("source"));
		bean.setRecordState(      mapRecord.get("state"));
		
		bean.setProvider(         mapRecord.get("provider"));
		bean.setJurisdiction(     mapRecord.get("jurisdiction"));
		bean.setRealNameMatch(    mapRecord.get("realNameMatch"));
		bean.setDobMatch(         mapRecord.get("DOBMatch"));
		bean.setAkaMatch(         mapRecord.get("AKAMatch"));

		// now store it
		BGCOffender offender = BGCOffender.createBGCOffender(bean);
		offender.save();
		return offender.getBgcOffenderId();		
	}
	
	protected ArrayList<Integer> processAliases(int offenderId, BGCOffenderMapHolder match) throws TorqueException {
		ArrayList<HashMap<String,String>> listAliases = match.getListAliases();
		ArrayList<Integer> aliasIdList = new ArrayList<Integer>();				
		
		for (HashMap<String,String> map : listAliases) {
			BGCAliasBean bean = new BGCAliasBean();
			bean.setBgcOffenderId(offenderId);
			
			String hashKey = generateHashKey(bean.getClass().getName());
			bean.setHashKey(hashKey);
			
			
			bean.setFirstName(map.get("firstName") != null ? map.get("firstName") : " ");
			bean.setLastName(map.get("lastName") != null ? map.get("lastName") : " ");
			bean.setMiddleName(map.get("middleName") != null ? map.get("middleName") : " ");
			bean.setSuffix(map.get("suffix") != null? map.get("suffix") : " ");
						
			BGCAlias alias = BGCAlias.createBGCAlias(bean);
			alias.save();
			int aliasId = alias.getBgcAliasId();
			
			aliasIdList.add(aliasId);
		}
		
		return aliasIdList;
	}
	
	protected ArrayList<Integer> processOffenses(int offenderId, BGCOffenderMapHolder match) throws TorqueException {
		ArrayList<Integer> offenseIdList = new ArrayList<Integer>();
		ArrayList<BGCOffenseBean> listOffenses = match.getListOffenses();
		for (BGCOffenseBean bean : listOffenses) {
			bean.setBgcOffenderId(offenderId);
			bean.setHashKey(generateHashKey(bean.getClass().getName()));
			BGCOffense offense = BGCOffense.createBGCOffense(bean);
			offense.save();
			int offenseId = offense.getBgcOffenseId();			
			offenseIdList.add(offenseId);
		}
		
		return offenseIdList;
	}
	
	// XXX this needs work
	// MapHolder doesn't handle supplements right, there needs to be a
	// separate map for each offense
	/*
	protected ArrayList<Integer> processOffenseSupplements(int offenseId, BGCOffenderMapHolder match) throws TorqueException {
		//ArrayList<HashMap<String,String>> listOffenses = match.getListOffenses();
		HashMap<String,String> mapSupp = match.getMapSupplements();
		ArrayList<Integer> suppIdList = new ArrayList<Integer>();				
		for (HashMap<String,String> map : listOffenses) {
			BGCOffenseSupplementBean bean = new BGCOffenseSupplementBean();
			bean.setBgcOffenseId(offenseId);
			
			String hashKey = generateHashKey(bean.getClass().getName());
			bean.setHashKey(hashKey);

			BGCOffenseSupplement supp = BGCOffenseSupplement.createBGCOffenseSupplement(bean);
			supp.save();
			int suppId = supp.getBgcOffenseSupplementId();
			
			suppIdList.add(suppId);
		}
		return suppIdList;		
	}
	*/

	/*
	    stages:
		UserInit user submits req UserInit
		UserAuth user confirms (billing authorized)
		ReqSent  request sent
		RespOK   response received (external interaction complete)
		ResPres  response presented to user (billing completed)
	 */
	/*
	protected void sendBGCRequest() { 
		
	}
	protected void parseBGCResponse() { 
		
	}
	protected void updateSearchStatus() { 
		
	}
	*/
	
	public void readXMLForTest(Document doc){
		try{
			StringWriter sw = new StringWriter();
			XMLSerializer ser = new XMLSerializer(sw, new OutputFormat(doc));
			ser.serialize(doc.getDocumentElement());		 
			String XMLStr = sw.toString();
			//logger.info("Result XML is :"+XMLStr);
			File resultXml= new File("/home/shahul/ss.xml");
			PrintStream p=new PrintStream(resultXml);
			p.print(XMLStr+"\n");
		}catch (Exception e) {
			logger.error("Exception while reading XML for Test :"+e);
		}
		
		return;
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
}