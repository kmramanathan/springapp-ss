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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.xml.bind.JAXBException;
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
import org.hibernate.HibernateException;
import org.hibernate.jdbc.TooManyRowsAffectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import springapp.domain.NSS.NSSOffenderBean;
import springapp.domain.NSS.NSSRequestBean;
import springapp.domain.NSS.NssOffenseBean;
import springapp.domain.NSS.NssOffenseSupplementBean;
import springapp.domain.NSS.NssProductBean;
import springapp.domain.NSS.NssResponseBean;
import springapp.domain.NSS.NSSResponse.NSSSummary;
import springapp.domain.NSS.NSSResponse.NssMapOffender;
import springapp.domain.NSS.NSSResponse.NssOffendeRecord;
import springapp.domain.NSS.NSSResponse.NssOffenderAddress;
import springapp.domain.NSS.NSSResponse.NssOffenderPersonal;
import springapp.domain.NSS.NSSResponse.NssOffenders;
import springapp.domain.NSS.NSSResponse.ObjectFactory;
import springapp.domain.NSS.NssDetailResponse.NssDetailOffence;
import springapp.domain.NSS.NssDetailResponse.NssDetailOffences;
import springapp.domain.NSS.NssDetailResponse.NssDetailOffenders;
import springapp.domain.NSS.NssDetailResponse.NssRecordDetailSupplement;
import springapp.repository.BGCRequestDao;
import springapp.repository.NssRequestDao;
import springapp.repository.NssResponseDao;
import springapp.service.user.UserManager;

/**
 * Provides support for querying the Criminal Records interface.
 */
@Service("NssSearchManager")
public class SpringNSSSearchManager implements ResourceLoaderAware {
	// ---------- PUBLIC DATA ----------
	// criminal settings	
	public static final String bgcPostUrlTest = "https://model.backgroundchecks.com/integration/bgcdirectpost2.aspx";
	public static final String bgcPostUrlLive = "https://direct.backgroundchecks.com/integration/bgcdirectpost2.aspx";
		
	// ---------- PRIVATE DATA ----------
	
	@Autowired
	protected NssRequestDao requestDao;
	@Autowired
	protected NssResponseDao responseDao;

	private static final Logger logger = Logger.getLogger("NssSearchManager");

	@Autowired
	protected ResourceLoader resourceLoader;
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;		
	}
	
	@Autowired
	private UserManager userManager;
	
	
	
	/*
	 * called at request start
	 */
	public void init() {
		
	}
	
	/*
	 * initialization functions
	 */
	
	
	/*
	 * exposed methods
	 */
	public int test() {
		return 1;
	}
	
	/*
	 * description info, stored in memory
	 */
	
	
	
	
	/*
	 * single object getters
	 */	
	public BGCProductBean getProduct(int productId) throws NoRowsException, TooManyRowsException, TorqueException {
		BGCProduct p = BGCProductPeer.retrieveByPK(productId);
		return p.getBean();
	}
	public NSSRequestBean getRequest(int requestId) throws Exception {
		NSSRequestBean r =requestDao.getOneRequest(requestId);		
		return r;
	}	
	public NssResponseBean getResponse(int responseId) throws Exception {
		NssResponseBean r = responseDao.getNssResponseId(responseId);
		return r;
	}
	public NSSOffenderBean getOffender(int offenderId) throws Exception {
		NSSOffenderBean o = responseDao.getOffendersId(offenderId);
		return o;
	}
	
	/*
	 * these check the hash key also
	 */
	public NssResponseBean getResponse(int responseId, String hashKeyResponse) throws Exception {
		NssResponseBean r = responseDao.getNssResponseId(responseId);
		if (r.getHashKey().equals(hashKeyResponse)) {
			return r;
		} else {
			return null;
		}
	}
	public NSSOffenderBean getOffender(int offenderId, String hashKeyOffender) throws Exception {
		NSSOffenderBean o =responseDao.getOffendersId(offenderId);
		if (o.getHashKey().equals(hashKeyOffender)) {
			return o;
		} else {
			return null;
		}
	}
	/*
	 * array of object getters
	 */	
	public NSSOffenderBean[] getOffenders(int responseId) throws Exception {		
		
		List<NSSOffenderBean> list=new ArrayList<NSSOffenderBean>();
		
		 list = responseDao.getOffenderList(responseId);
		logger.info("fullname"+list.get(0).getFullName());
		//return list.toArray(new BGCOffenderBean[0]);
		return list.toArray(new NSSOffenderBean[0]);
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
	
	public NssOffenseBean[] getOffenses(int offenderId) throws Exception {
		
		List<NssOffenseBean> list = new ArrayList<NssOffenseBean>();
		list=responseDao.getOffenseList(offenderId);
		return list.toArray(new NssOffenseBean[0]);
	}
	
	public NssOffenseSupplementBean[] getOffenseSupplements(int offenseId) throws Exception {
		
		
		List<NssOffenseSupplementBean> list =new ArrayList<NssOffenseSupplementBean>();
		list= responseDao.getSupplementList(offenseId);
		
		return list.toArray(new NssOffenseSupplementBean[0]);
	}
	
	/**
	 * Set up a search for testing.
	 * 
	 * @return
	 * @throws TorqueException
	 */
	public int prepareSearchTest() throws TorqueException {
		return prepareSearch(9694, 
				"PATRICK", "OHANLON",
				
				4, 26, 1968,
				
				1,false,
				 "Personal", "");
	}
	
	public int prepareSearchTest2() throws TorqueException {
		return prepareSearch(9694, 
				"Judd", "Bourgeois",
				
				3, 20, 1977,
				
				1,false,
				 "Personal", "");
	}
	
	/**
	 * Prepare to run a search.
	 * @throws  
	 * @throws TorqueException 
	 */
	public int prepareSearch(int userId,  
			String firstName, String lastName, 
			
			int dobMonth, int dobDay, int dobYear,
			
			int bgcProductId, boolean isDpassUser,			
			String crimPurpose, String referCode) throws SearchException {
		
		// get product info first
		NssProductBean product = new NssProductBean();
		try {
			product =requestDao.getProductId(bgcProductId);
		} catch (Exception e) {
			logger.error("Could not find BGC product", e);
			throw new SearchException(e);
		}
		
		BigDecimal price;
		logger.info("id:"+product.getBgcProductId());
		logger.info("price:"+product.getPriceBasic());
		if(isDpassUser)
		{
			price=product.getPriceDpass();
		}
		else {
			price=product.getPriceBasic();
		}
		
		
		NSSRequestBean bean = new NSSRequestBean();		

		bean.setBgcProductId(bgcProductId);
		bean.setBgcStatusId(1);
		bean.setUserId(userId);
		bean.setDateCreated(new Date());
		bean.setPrice(price);				
		
		// purpose?
		bean.setFirstName(firstName);
		
		bean.setLastName(lastName);
		
		//bean.setMiddleName(middleInitial);
		bean.setDobMonth(dobMonth);
		bean.setDobDay(dobDay);
		bean.setDobYear(dobYear);
		bean.setPurpose(crimPurpose);
		bean.setReferenceCode(referCode);
		
		// jurisdiction?
		/*if (crimJurisdiction.equals("Nationwide")) {
			bean.setUsonesearch(true);
		} else {
			bean.setUsonesearch(false);
			bean.setState(crimJurisdiction.substring(0, 2));
		}*/
		
		requestDao.save(bean);
		
		int requestId = bean.getBgcRequestId();
		logger.info("requestId: " + requestId);
		
		
		
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
		NssResponseBean res;
		res = responseDao.getNssResponseId(responseId);
		res.setTransactionId(transactionId);
		responseDao.saveResponse(res);
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
	 * @throws JAXBException 
	 * 
	 * @throws SOAPException
	 *             If the SOAP request failed for any reason.
	 */
	public NssResponseBean runSearch(int requestId) throws SearchException, JAXBException {
		
		//BGCRequestBean reqBean = req.getBean(); 
		NSSRequestBean reqBean=requestDao.getOneRequest(requestId);
		short matchCount = 0;
		String searchStatus = null;
		ArrayList<Integer> OffList=new ArrayList<Integer>();
		
		NSSRequestHelper reqHelper = new NSSRequestHelper();
		NssResponseHelper respHelper = new NssResponseHelper();
		
		String xmlQuery = reqHelper.prepareXmlForBGCRequest(reqBean);
		logger.info("xmlQuery: " + xmlQuery);
		Document xmlData = reqHelper.doHttpPostQuery(bgcPostUrlLive, xmlQuery);
		
		// This function only for reading XML for testing purpose
		//readXMLForTest(xmlData);
		 
		logger.info("Remote query complete, parsing results");
					
		// check for error element first
		NodeList errorlist = xmlData.getElementsByTagName("errors");
		if (errorlist.getLength() > 0) {
			Node n = errorlist.item(0);
			// an invalid search was created
			String error = n.getNodeName();// getTextContent();
			
			throw new SearchException(error);
		}		
		
		
		
		//Integer qtyFound = Integer.parseInt(eOffenders.getAttribute("qtyFound"));
		//Integer qtyReturned = Integer.parseInt(eOffenders.getAttribute("qtyReturned"));
		NssOffenders quantityList=respHelper.parseOffendersFound(xmlData);
		Integer qtyFound=quantityList.getQtyFound();
		Integer qtyReturned= quantityList.getQtyReturned();
		
		logger.info("found: " + qtyFound + ": returned: " + qtyReturned);
		
		// this does all the work
		List<NssMapOffender> listOffenders = new ArrayList<NssMapOffender>();
		if (qtyFound == 0) {
			matchCount = 0;
			searchStatus = "No results";
		} else {			
			matchCount = qtyFound.shortValue();
			searchStatus = "Found results";			
			listOffenders = respHelper.parseOffenders(xmlData);
		}
		
		logger.info("Results parsed ok, storing in db");

		NssResponseBean resBean = new NssResponseBean();
		resBean.setNssRequestId(requestId);
		resBean.setQuantityFound(qtyFound);
		resBean.setQuantityReturned(qtyReturned);
		resBean.setDateCreated(new Date());
		resBean.setHashKey(generateHashKey(resBean.getClass().getName()));
		responseDao.saveResponse(resBean);
		int responseId = resBean.getNssResponseId();
		
		// store the search using torque
		
		
			logger.info("responseId: " + responseId);
			
			// bail out here if no results
			if (matchCount == 0) {
				logger.info("no results, returning");
			} else {				
				// loop through all matches and add them into a batch operation list 
				
				for (Iterator iterator = listOffenders.iterator(); iterator
						.hasNext();) {
					NssMapOffender match = (NssMapOffender) iterator
							.next();
					// add offender
					int offenderId = processOffenderTop(responseId, match);
						//logger.info("added offender: " + offenderId);
						
						//OffList.add(offenderId);
						//String recordOffenderId=match.getRecord().getKey().getOffenderID();
						//if(recordOffenderId != null)
						/*{
							fetchOffenderDetails(offenderId);
							NSSOffenderBean offender=new NSSOffenderBean();
							offender.setHasDetail(true);
							responseDao.saveOffenders(offender);
							
						}*/
						
					
					
					
				}	
				
					
			}	
					
				/*	// these are linked to offender
					ArrayList<Integer> aliasIdList = processAliases(offenderId, match);
					if (eDetail != null) {
						ArrayList<Integer> offenseIdList = processOffenses(offenderId, match);
						// mark details as fetched
						BGCOffender offender = BGCOffenderPeer.retrieveByPK(offenderId);
						offender.setHasDetail(true);
						offender.save();
					}
				}
		
		
		return response.getBean();*/
				return resBean;
	}

	// helper method for single case
	public void fetchOffenderDetails(Integer offenderToGet) throws  SearchException, JAXBException {
		ArrayList<Integer> offendersToGet = new ArrayList<Integer>();
		offendersToGet.add(offenderToGet);
		fetchOffenderDetails(offendersToGet);
	}
	
	public void fetchOffenderDetails(ArrayList<Integer> offendersToGet) throws JAXBException {
		for (int offenderId : offendersToGet) {
			// load offender ID & key
			NSSOffenderBean offender;
			try {
				offender = responseDao.getOffendersId(offenderId);				
			} catch (TooManyRowsAffectedException e) {
				logger.error("Duplicate offender: " + offenderId);
				continue;
			} catch (HibernateException e) {
				logger.error("hibernate failed: " + offenderId, e);
				throw new SearchException(e);
			}
			//BGCOffenderBean offenderBean = offender.getBean();
			//offender.isHasDetail();

			// don't fetch it again
			if (offender.isHasDetail()) {
				logger.info("Already fetched offender: " + offenderId);
				continue;
			}
			
			// fetch the offender details (offenses)
			logger.info("fetching offender: " + offenderId);
			
			// build & run the query
			NSSRequestHelper reqHelper = new NSSRequestHelper();
			NssResponseHelper respHelper = new NssResponseHelper();
			
			String xmlQuery = reqHelper.prepareXmlForOffenderSecureKey(offender);
			logger.info("xmlQuery: " + xmlQuery);
			Document xmlData = reqHelper.doHttpPostQuery(bgcPostUrlLive, xmlQuery);
			
			// This function only for reading XML for testing purpose
			 //readXMLForTest(xmlData);
			NssDetailOffenders foundList=respHelper.parseDetailOffendersFound(xmlData);
			logger.info("Offenses Found: "+foundList.getQtyFound()+" Offenses Returned: "+foundList.getQtyReturned());
			
			logger.info("Remote query complete, parsing results");

			// process results
			
			List<NssDetailOffence> match=respHelper.parseOffense(xmlData);
			
			// update the database
			ArrayList<Integer> offenseIdList = processOffenses(offenderId, match);
			
			
			

			// update offender
			// XXX dont forget to enable this once store works
			offender.setHasDetail(true);
			responseDao.saveOffenders(offender);		
		}
	}
	
	protected String generateHashKey(String className) {
		// generate hash code based on input
		Random rand = new Random(System.currentTimeMillis());
		String key = "here is our secret query key for Nss";
		//String data = key + className + id + String.valueOf(rand.nextLong());
		String data = key + className + String.valueOf(rand.nextLong());
		String hashKey = DigestUtils.shaHex(data);
		return hashKey;
	}
	
	protected int processOffenderTop(int responseId, NssMapOffender match)  {	
		
	
		NSSOffenderBean bean = new NSSOffenderBean();
		if(bean != null)
		{
		NssOffenderPersonal personal=match.getIdentity().getPersonal();
		NssOffenderAddress mapAddress= match.getIdentity().getAddress();
		NssOffendeRecord mapRecord = match.getRecord();
		bean.setNssResponseId(responseId);

		String hashKey = generateHashKey(bean.getClass().getName());
		if(hashKey != null)
		{
		bean.setHashKey(hashKey);
		}
		
		
		// copy direct from personal map
		bean.setFullName(personal.getFullName());
		bean.setGender(personal.getGender());
		bean.setEyeColor(     personal.getEyeColor());
		bean.setHairColor(   personal.getHairColor());
		bean.setWeight(       personal.getWeight());
		bean.setHeightFeet(   personal.getHeight().getFeet());
		bean.setHeightInches( personal.getHeight().getInches());
		bean.setRace(         personal.getRace());
		bean.setImageUrl(     personal.getImageURL());
		
		// special handling
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dob;
		String sDOB = personal.getDOB();
		try {
			dob = df.parse(sDOB);
		} catch (ParseException e) {
			logger.error("bad DOB: " + sDOB);
			dob = null;
		}
		bean.setDateOfBirth(personal.getDOB());
		
		Integer dobType = Integer.valueOf(personal.getDOBType());
		bean.setDobType(dobType);
		
		// copy direct from address map
		bean.setStreet(     mapAddress.getStreet());
		bean.setCity(       mapAddress.getCity());
		bean.setPostalCode( mapAddress.getPostalCode());
		bean.setState(      mapAddress.getState());
		bean.setCounty(     mapAddress.getCounty());
		bean.setCountry(    mapAddress.getCountry());
		
		// copy direct from record map
		bean.setRecordOffenderId( mapRecord.getKey().getOffenderID());
		bean.setRecordSecureKey(  mapRecord.getKey().getSecureKey());
		bean.setRecordSource(     mapRecord.getKey().getSource());
		bean.setRecordState(      mapRecord.getKey().getState());
		
		bean.setProvider(         mapRecord.getProvider());
		bean.setJurisdiction(     mapRecord.getJurisdiction());
		bean.setRealNameMatch(    mapRecord.getRealNameMatch());
		bean.setDobMatch(         mapRecord.getDOBMatch());
		bean.setAkaMatch(         mapRecord.getAKAMatch());
		}
		// now store it
		responseDao.saveOffenders(bean);
		return bean.getNssOffenderId();		
	}
	
	protected ArrayList<Integer> processAliases(int offenderId, BGCOffenderMapHolder match) throws TorqueException {
		ArrayList<HashMap<String,String>> listAliases = match.getListAliases();
		ArrayList<Integer> aliasIdList = new ArrayList<Integer>();				
		
		for (HashMap<String,String> map : listAliases) {
			BGCAliasBean bean = new BGCAliasBean();
			bean.setBgcOffenderId(offenderId);
			
			String hashKey = generateHashKey(bean.getClass().getName());
			bean.setHashKey(hashKey);
			
			bean.setFirstName(map.get("firstName"));
			bean.setLastName(map.get("lastName"));
			bean.setMiddleName(map.get("middleName"));
			bean.setSuffix(map.get("suffix"));
			
			BGCAlias alias = BGCAlias.createBGCAlias(bean);
			alias.save();
			int aliasId = alias.getBgcAliasId();
			
			aliasIdList.add(aliasId);
		}
		
		return aliasIdList;
	}
	
	protected ArrayList<Integer> processOffenses(int offenderId, List<NssDetailOffence> match) throws JAXBException {
		ArrayList<Integer> offenseIdList = new ArrayList<Integer>();
		//ArrayList<NssOffenseBean> listOffenses = match;
		
		NssOffenseBean bean=new NssOffenseBean();
		NSSOffenderBean offenderBean=responseDao.getOffendersId(offenderId);
		//if(offenderId == offenderBean.getNssOffenderId())
		//{
			logger.info("offenses added to DB");
		for (Iterator iterator = match.iterator(); iterator.hasNext();) {
			NssDetailOffence offenseList = (NssDetailOffence) iterator
					.next();
			
			bean.setNssOffenderId(offenderId);
			bean.setHashKey(generateHashKey(bean.getClass().getName()));
			bean.setArrestDate(offenseList.getArrestDate());
			bean.setArrestingAgency(offenseList.getArrestingAgency());
			bean.setConfinement(offenseList.getConfinement());
			bean.setCourtCosts(offenseList.getCourtCosts());
			bean.setCourtDecision(offenseList.getCourtDecision());
			bean.setDegreeOfOffense(offenseList.getDegreeOfOffense());
			bean.setDescription(offenseList.getDescription());
			bean.setDisposition(offenseList.getDisposition());
			bean.setDispositionDate(offenseList.getDispositionDate());
			bean.setFileDate(offenseList.getFileDate());
			bean.setFine(offenseList.getFine());
			bean.setJurisdiction(offenseList.getJurisdiction());
			bean.setOffenseDate(offenseList.getOffenseDate());
			bean.setOriginatingAgency(offenseList.getOriginatingAgency());
			bean.setPlea(offenseList.getPlea());
			bean.setProbation(offenseList.getProbation());
			bean.setSentence(offenseList.getSentence());
			bean.setSentenceDate(offenseList.getSentenceDate());
			bean.setStatute(offenseList.getStatute());
			//bean.setSupplementOrigin(offenseList.gets)
			responseDao.saveOffenses(bean);
			int offenseId= bean.getNssOffenseId();
			logger.info("Offense Id:"+offenseId);
			offenseIdList.add(offenseId);
			List<NssRecordDetailSupplement> suppList=offenseList.getRecordDetails().get(0).getRecordDetail().get(0).getSupplements().get(0).getSupplement();
			if(suppList.size() > 0)
			{
				logger.info("supplemets added to DB");
				for (Iterator it = suppList.iterator(); it.hasNext();) {
				NssRecordDetailSupplement supList = (NssRecordDetailSupplement) it
						.next();
				NssOffenseSupplementBean supbean=new NssOffenseSupplementBean();
				supbean.setNssOffenseId(offenderId);
				supbean.setHashKey(generateHashKey(supbean.getClass().getName()));
				supbean.setDisplayTitle(supList.getDisplayTitle());
				supbean.setDisplayValue(supList.getDisplayValue());
				responseDao.saveSupplements(supbean);
				}
			}
			
		}
	//}
		return offenseIdList;
	}
	
	// XXX this needs work
	// MapHolder doesn't handle supplements right, there needs to be a
	// separate map for each offense
	
	protected ArrayList<Integer> processOffenseSupplements(int offenseId, List<NssRecordDetailSupplement> match) throws JAXBException {
		//ArrayList<HashMap<String,String>> listOffenses = match.getListOffenses();
		//HashMap<String,String> mapSupp = match.getMapSupplements();
		ArrayList<Integer> suppIdList = new ArrayList<Integer>();	
		for (Iterator iterator = match.iterator(); iterator.hasNext();) {
			NssRecordDetailSupplement map = (NssRecordDetailSupplement) iterator.next();
			NssOffenseSupplementBean bean=new NssOffenseSupplementBean();
			bean.setNssOffenseId(offenseId);
			bean.setHashKey(generateHashKey(bean.getClass().getName()));
			bean.setDisplayTitle(map.getDisplayTitle());
			bean.setDisplayValue(map.getDisplayValue());
			responseDao.saveSupplements(bean);
			int suppId=bean.getNssOffenseSupplementId();
			suppIdList.add(suppId);
			
		}
					
		
		return suppIdList;		
	}
	

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
	
}