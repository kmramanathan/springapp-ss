package springapp.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.lang.Runnable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.searchsystems.limestone.BjlDataSources;
import net.searchsystems.limestone.BjlDataSourcesPeer;
import net.searchsystems.limestone.BjlResults;
import net.searchsystems.limestone.BjlResultsPeer;
import net.searchsystems.limestone.BjlSearches;
import net.searchsystems.limestone.BjlSearchesPeer;
import net.searchsystems.limestone.SsSearchCategoriesPeer;
import net.searchsystems.limestone.SsSearchSubCategories;
import net.searchsystems.limestone.SsSearchSubCategoriesPeer;
import net.searchsystems.limestone.SsUserSearches;
import net.searchsystems.limestone.SsUserSearchesPeer;
import net.searchsystems.limestone.SsUsersPeer;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMDocument;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.log4j.Logger;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.workingdogs.village.DataSet;
import com.workingdogs.village.QueryDataSet;
import com.workingdogs.village.Record;

import springapp.domain.BJLXmlParserHelper;
import springapp.domain.CorpAddress;
import springapp.domain.CorpHistory;
import springapp.domain.CorpNameInfo;
import springapp.domain.CorpResponses;
import springapp.domain.CorpXMLParser;
import springapp.domain.EvictionXMLParser;
import springapp.domain.NewBJLResults;
import springapp.domain.NewBJLResultsPeer;
import springapp.domain.NewBJLSearches;
import springapp.domain.NewBJLSearchesPeer;
import springapp.domain.corporation.CorporationResults;
import springapp.domain.corporation.CorporationResultsPeer;
import springapp.domain.corporation.CorporationSearches;
import springapp.domain.corporation.CorporationSearchesPeer;
import springapp.domain.eviction.EvictionResults;
import springapp.domain.eviction.EvictionResultsPeer;
import springapp.domain.eviction.EvictionSearches;
import springapp.domain.eviction.EvictionSearchesPeer;
import springapp.manager.SearchException;
import springapp.repository.CorpDao;
import springapp.service.user.UserManager;
import springapp.web.funnel.BjlXMLbean;
import springapp.web.funnel.BjlXMLbean.bjlAddressObj;
import springapp.web.funnel.BjlXMLbean.bjlFilingObj;
import springapp.web.funnel.BjlXMLbean.bjlNameObj;

/**
 * Provides support for querying the Corporation interface.
 * @author 
 * Vivek
 */
@Service("corpSearchManager")
public class SpringCorporationSearchManager implements ResourceLoaderAware {
	protected static ArrayList<BjlDataSources> bjlSources;
	@Autowired
	protected CorpDao corpDao;

	public final static String liveUrl="https://www.tracersinfo.com/xmlgw.php";

	public static final short MAX_RESULTS = 250;
	
	protected static final Short SEARCH_CATEGORY_ID = 38;
	
	protected static final Short STATUS_FOUND_RESULTS = 1;
	protected static final Short STATUS_NO_RESULTS = 0;
	protected static final Short STATUS_TOO_MANY_RESULTS = -1;
	
	protected final Logger logger = Logger.getLogger(getClass());

	// this is necessary to force torque to load before init() gets called
	// can we move this to an event listener in torque mgr?
//	@Autowired
//	protected TorqueManager torqueManager;
	
	@Autowired
	protected ResourceLoader resourceLoader;
	public void setResourceLoader(ResourceLoader resourceLoader) {
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
	
	
	// ---------- PUBLIC METHODS ----------
	/**
	 * Get a list of BJL data sources.
	 */
	public ArrayList<BjlDataSources> getBJLDataSources() {
		return bjlSources;
	}
	
	protected void completeSearch(long userSearchId, Connection conn) {
		SsUserSearches search;
		try {
			search = SsUserSearchesPeer.retrieveByPK(userSearchId);
			search.setCompleted(true);
			search.save();
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	
	public void updateSearchInvoiced(long userSearchId, boolean isInvoiced) throws SearchException {
		try {
			SsUserSearches search = SsUserSearchesPeer.retrieveByPK(userSearchId);
			search.setInvoiced(isInvoiced);
			search.save();
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	
	public void setReferenceCode(int userSearchId, String referenceCode) throws SearchException {
		try {
			CorporationSearches search = CorporationSearchesPeer.retrieveByPK(userSearchId);
			search.setReference(referenceCode);
			search.save();
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}


	/**
	 * Retrieve the count of matches returned from a eviction search.
	 */
	public int getResultCount(long userSearchId) throws SearchException {
		try {
			List<CorporationResults> results = CorporationResultsPeer.doSelect(new Criteria());
			return results.size();
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	
	/**
	 * Delete a list of Corporation matches.
	 * 
	 * @param userSearchId
	 *            User search id.
	 * @param resultIds
	 *            An array of BJL result ids.
	 * 
	 * @throws SearchException
	 *             If the SOAP request failed for any reason.
	 */
	public void deleteResults(long userSearchId, int[] resultIds) throws SearchException {
		try {
			Criteria c = new Criteria();			
			c.add(CorporationResultsPeer.USER_SEARCH_ID, userSearchId);
			c.addIn(CorporationResultsPeer.RESULT_ID, resultIds);
			NewBJLResultsPeer.doDelete(c);
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}


	/**
	 * Retrieve a detailed description of a BJL search result.
	 * 
	 * @param userSearchId
	 *            User search id.
	 * @param resultIds
	 *            An array of result ids.
	 * 
	 * @return A detailed description of a search result.
	 * 
	 * @throws SearchException
	 *             If the SOAP request failed for any reason.
	 */
	public List<CorporationResults> getResults(long userSearchId, int[] resultIds) throws SearchException {
		try {
			Criteria c = new Criteria();			
			c.add(CorporationResultsPeer.USER_SEARCH_ID, userSearchId);
			c.addIn(CorporationResultsPeer.RESULT_ID, resultIds);
			List<CorporationResults> results = CorporationResultsPeer.doSelect(c);
			return results;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	
	public CorporationResults getResult(int resultId) throws SearchException {
		try {
			CorporationResults result = CorporationResultsPeer.retrieveByPK(resultId);
			return result;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	
	/**
	 * Retrieve a detailed description of a Corporation search result.
	 * @param typeOfItem 
	 */
	public List<CorporationResults> getAllResults(long userSearchId) throws SearchException {		
		try {
			Criteria c = new Criteria();
			c.add(CorporationResultsPeer.USER_SEARCH_ID, userSearchId);
			
			List<CorporationResults> results = CorporationResultsPeer.doSelect(c);
			return results;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}

	/**
	 * Retrieve a single Corporation search.
	 */
	public CorporationSearches getSearch(long userSearchId) throws SearchException {
		try {
			CorporationSearches search = CorporationSearchesPeer.retrieveByPK(userSearchId);
			return search;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	
	/**
	 * Retrieve a single Corporation search.
	 */
	public CorporationSearches getSearch(String username, long userSearchId) throws SearchException {
		try {
			Criteria c = new Criteria();
			c.add(SsUsersPeer.USERNAME, username);
			c.add(CorporationSearchesPeer.USER_SEARCH_ID, userSearchId);
			c.addJoin(CorporationSearchesPeer.USER_ID, CorporationSearchesPeer.USER_ID);
			List<CorporationSearches> list = CorporationSearchesPeer.doSelect(c);
			return list.get(0);
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	
	/**
	 * Retrieve all Corporation searches for a user.
	 */
	public List<CorporationSearches> getSearches(String username) throws SearchException {
		try {
			Criteria c = new Criteria();
			c.add(SsUsersPeer.USERNAME, username);
			c.addJoin(SsUsersPeer.USER_ID, CorporationSearchesPeer.USER_ID);
			List<CorporationSearches> list = CorporationSearchesPeer.doSelect(c);
			return list;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}

	/**
	 * Retrieve the cost of a specific BJL search.
	 * 
	 * @param subCategory
	 *            Search sub-category.
	 * 
	 * @return double Cost of the search.
	 * 
	 * @throws SearchException
	 *             If the SOAP request failed for any reason.
	 */
	public String getSearchCost(String subCategory) throws SearchException {
		return getCost("Corporation Search", subCategory);
	}
	
	public String getCost(String category, String subCategory) throws SearchException {		
		try {
			Criteria c = new Criteria();
			c.add(SsSearchCategoriesPeer.TITLE, category);
			c.add(SsSearchSubCategoriesPeer.TITLE, subCategory);
			c.addJoin(SsSearchCategoriesPeer.SEARCH_CATEGORY_ID, SsSearchSubCategoriesPeer.SEARCH_CATEGORY_ID);
			// this should only return 1 row
			List<SsSearchSubCategories> list = SsSearchSubCategoriesPeer.doSelect(c);
			SsSearchSubCategories subcat = list.get(0);
			return subcat.getPrice().toPlainString();
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}	
	
	public List<SsSearchSubCategories> getAllSearchCosts() throws SearchException {
		final String bjlSearchType = "Bankruptcies, Judgments and Tax Liens";
		try {
			Criteria c = new Criteria();
			c.add(SsSearchCategoriesPeer.TITLE, bjlSearchType);
			c.addJoin(SsSearchCategoriesPeer.SEARCH_CATEGORY_ID, SsSearchSubCategoriesPeer.SEARCH_CATEGORY_ID);
			List<SsSearchSubCategories> list = SsSearchSubCategoriesPeer.doSelect(c);
			return list;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	

	/*public long querySimpleTest(Integer userId, Integer transactionId, String bjlName) {
		String foo = null;
		return this.query(userId, 1, transactionId, bjlName, 
				foo, foo, foo, 
				foo, foo, foo);
	}
	*/
	public long queryCorpFunnel(Integer userId, Integer searchSubCategoryId, Integer transactionId, 
			String corpfirstname,String corplastname,String corpBusinessnmae,
			String corpState, 
			String corpInitial,
			String corpreference) {
		return this.query(userId, searchSubCategoryId, transactionId,
				corpfirstname, corplastname, corpBusinessnmae, corpState, 
				corpInitial,
				corpreference);
	}
	
	XPathFactory xPathFactory;
	XPath xpath;
	Document doc;
	
	public String getKeyValueFromXml(String xPathFromPropFile) throws XPathExpressionException {
		String nodeValue = ".//text()";
		XPathExpression pathExpr = xpath.compile(xPathFromPropFile);
        Node n1 = ((NodeList) pathExpr.evaluate(this.doc, XPathConstants.NODESET)).item(0);
        
        pathExpr = xpath.compile(nodeValue);
        return (n1 != null ? (String) pathExpr.evaluate(n1,
                XPathConstants.STRING) : "");
	}
	
	/**
	 * Query the Corp interface and store the results in the database.
	 * 
	 * @throws SearchException
	 *             If the SOAP request failed for any reason.
	 */
	public long query(Integer userId, Integer searchSubCategoryId, Integer transactionId,
			String corpfirstname,String corplastname,String corpBusinessnmae,
			String corpState, 
			String corpInitial,
			String corpreference) throws SearchException {
		/*
		 * step 1: set up the httppost request
		 * 
		 */
        	//author by vivek	
		 long searchId = 0;
		 Short searchStatusId;
         Integer totalMatches = 0;
         Integer rowsReturned = 0;
         
         StringBuffer sb=new StringBuffer();
		 try {
		
		String userID = "SEASYSXML";
		String password = "quVa9h8B";
		String lastname="";
		String firstname="";
		String state="";
		String initial="";
		String reference="";
		String businessname="";
		
		lastname=corplastname;
		firstname= corpfirstname;
		businessname=corpBusinessnmae;
		state=corpState;
		reference=corpreference;
		logger.info("city"+corpInitial);
		logger.info("Reference"+corpreference);
		if(corpInitial != null)
		{
			initial=corpInitial;
		}
		if(corpreference != null)
		{
			reference = corpreference;
		}
		
		 String xml="";
		if(firstname != null && lastname != null)
		{
		 xml="<?xml version='1.0'?>";
		 xml=xml+"<xmlgw>";
		 xml=xml+"<session>";
		 xml=xml+"<user_id>"+userID+"</user_id>";
		 xml=xml+"<password>"+password+"</password>";
		 xml=xml+"<customer_reference>"+reference+"</customer_reference>";
		 xml=xml+"</session>";
		 xml=xml+"<search_request>";
		 xml=xml+"<search>"+"sos"+"</search>";
		 xml=xml+"<search_identifier>"+"S002"+"</search_identifier>";
		 xml=xml+"<search_criteria>";
		 xml=xml+"<name>"+lastname+";"+firstname+";"+initial+"</name>";
		 xml=xml+"<filing_state>"+state+"</filing_state>";
		 xml=xml+"</search_criteria>";
		 xml=xml+"</search_request>";
		 xml=xml+"</xmlgw>";
		}
		else {
			firstname="";
			lastname="";
		}
		if(businessname != null)
		{
		 xml="<?xml version='1.0'?>";
		 xml=xml+"<xmlgw>";
		 xml=xml+"<session>";
		 xml=xml+"<user_id>"+userID+"</user_id>";
		 xml=xml+"<password>"+password+"</password>";
		 xml=xml+"<customer_reference>"+reference+"</customer_reference>";
		 xml=xml+"</session>";
		 xml=xml+"<search_request>";
		 xml=xml+"<search>"+"sos"+"</search>";
		 xml=xml+"<search_identifier>"+"S002"+"</search_identifier>";
		 xml=xml+"<search_criteria>";
		 xml=xml+"<name>"+businessname+"</name>";
		 xml=xml+"<filing_state>"+state+"</filing_state>";
		 xml=xml+"</search_criteria>";
		 xml=xml+"</search_request>";
		 xml=xml+"</xmlgw>";
		}
		else {
			businessname="";
		}
		 	logger.info("request xml"+xml);
		 	HttpClient client=new HttpClient();
			PostMethod post=new PostMethod(liveUrl);
		 	post.setParameter("request", xml);
		 	int returncode=client.executeMethod(post);
			logger.info("Return Code:"+returncode);
	        BufferedReader  br=new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));
	        String readline=null;
	        while((readline=br.readLine()) != null)
	         {
	        	sb.append(readline+"\n"); 
	         }
	         String xmlResponse=sb.toString();
	      
         /*
 		 *  step 3: parse response
 		 *  
 		 */		
         //String searchStatus = "";
	    if(xmlResponse != null)
	    {
	    	searchStatusId=STATUS_FOUND_RESULTS;
         
	    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	    	doc = builder.parse(new InputSource(new StringReader(xmlResponse.replace("&nbsp;", "&#160;"))));
	    	
	    	xPathFactory = XPathFactory.newInstance();
			xpath = xPathFactory.newXPath();
			
			NodeList nList=null;
      		/*NodeList errList=doc.getElementsByTagName("errors");
      		Node errrNode = (Node) errList.item(0);
      		String errString=readXMLNode(errrNode, "error");
      		logger.info("xml error:"+errString);
      		if(errString.indexOf("too many results") != -1)
      		{
      			return STATUS_TOO_MANY_RESULTS;
      			
      		}*/
			
			String errorResponse = getKeyValueFromXml("/xmlgw/errors/error");
			logger.info("Error respons if any: " + errorResponse);
			if(errorResponse != null & errorResponse.indexOf("too many results") != -1) {
				logger.info("Status set to too many results");
				return STATUS_TOO_MANY_RESULTS;
      		}
			
			errorResponse = getKeyValueFromXml("/xmlgw/search_response/rows_returned");
			if(errorResponse != null & errorResponse.equals("0"))
      		{
				CorporationSearches searchdata = new CorporationSearches();
				// create a new transaction conn.setAutoCommit(false);
				// add search to database
				
				// inherited from user_search
				searchdata.setSearchCategoryId(SEARCH_CATEGORY_ID);			
				searchdata.setSearchSubCategoryId(searchSubCategoryId.shortValue()); 
				searchdata.setUserId(userId);			
				searchdata.setTransactionId(transactionId);
				searchdata.setMatchCount(STATUS_NO_RESULTS);
				searchdata.setCreateDate(Calendar.getInstance().getTime());
				searchdata.setSearchStatusId(searchStatusId);			
				
				// eviction fields
				searchdata.setFirstname(firstname);
				searchdata.setLastname(lastname);
				searchdata.setBusinessname(businessname);
				searchdata.setState(state);
				searchdata.setInitial(initial);
				searchdata.setReference(reference);
			 
				
				searchdata.save();
				searchId = searchdata.getUserSearchId();
				logger.info("search id: " + searchId);
				logger.info("adding results");
				
				searchStatusId = STATUS_NO_RESULTS;
				
				return searchId;
      			
      		}
			
	    	NodeList rowList=doc.getElementsByTagName("search_response");
	    	Node headNode =(Node) rowList.item(0);
	    	logger.info("headnodes:"+headNode.getNodeName());
	    	String searchIdentifier=readXMLNode(headNode, "search_identifier");
	    	String customerRef=readXMLNode(headNode, "customer_refernece");
	    	String search=readXMLNode(headNode, "search");
	    	rowsReturned=Integer.parseInt(readXMLNode(headNode, "rows_returned"));
	    	logger.info("Rows Returnd:"+rowsReturned);
	    	if(!searchIdentifier.isEmpty())
	    		{
	    			logger.info("search identifier:"+searchIdentifier);
	    		}
	    	if(!customerRef.isEmpty())
	    		{
	    			logger.info("customer refernece:"+customerRef);
	    		}
	    	if(!search.isEmpty() && search != null)
	    		{
	    			logger.info("Search"+search);
	    		}
	    	if(rowsReturned.equals("-1"))
	    	{
	    		String reasonstring=readXMLNode(headNode, "response_data");
				logger.info("Reason Text:"+reasonstring);
	    	}
	    		nList=doc.getElementsByTagName("response_data");
	    		totalMatches=nList.getLength();
	    		
		if(totalMatches > 0 && totalMatches != 0)
		{
			
			CorporationSearches searchdata = new CorporationSearches();
			// create a new transaction conn.setAutoCommit(false);
			// add search to database
			
			// inherited from user_search
			searchdata.setSearchCategoryId(SEARCH_CATEGORY_ID);			
			searchdata.setSearchSubCategoryId(searchSubCategoryId.shortValue()); 
			searchdata.setUserId(userId);			
			searchdata.setTransactionId(transactionId);
			searchdata.setMatchCount(rowsReturned.shortValue());
			searchdata.setCreateDate(Calendar.getInstance().getTime());
			searchdata.setSearchStatusId(searchStatusId);			
			
			// eviction fields
			searchdata.setFirstname(firstname);
			searchdata.setLastname(lastname);
			searchdata.setBusinessname(businessname);
			searchdata.setState(state);
			searchdata.setInitial(initial);
			searchdata.setReference(reference);
		 
			
			searchdata.save();
			searchId = searchdata.getUserSearchId();
			logger.info("search id: " + searchId);
			logger.info("adding results");
			
			
			CorpResponses cp=new CorpResponses();
				
			Node node=nList.item(0);
			NodeList childNodeList=node.getChildNodes();
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
							cp.setUser_search_id(searchId);
							cp.setFiling_number(readXMLNode(classNode, "filing_number").toUpperCase());
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
							NodeList mergerList=classNode.getChildNodes();
							
							
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
									CorpNameInfo ni=new CorpNameInfo();
									
									ni.setUser_search_id(searchId);
								    ni.setFiling_number(cp.getFiling_number().toUpperCase());
								    ni.setName(readXMLNode(mergerResultNode, "name"));
								    ni.setName_type(readXMLNode(mergerResultNode, "name_type"));
								    ni.setCurrent_active(readXMLNode(mergerResultNode, "current_active"));
									
									NodeList nameAddressList=mergerResultNode.getChildNodes();
									
									
									for (int l = 0; l < nameAddressList.getLength(); l++) {
										Node nameAddressNode=nameAddressList.item(l);
										if(nameAddressNode.getNodeName().equals("name_address"))
										{
											
											ni.setNa_address1(readXMLNode(nameAddressNode, "address1"));
											
											ni.setNa_address2(readXMLNode(nameAddressNode, "address2"));
											
											ni.setNa_address3(readXMLNode(nameAddressNode, "address3"));
											
											ni.setNa_city(readXMLNode(nameAddressNode, "city"));
											
											ni.setNa_state(readXMLNode(nameAddressNode, "state"));
											
											ni.setNa_zip_code(readXMLNode(nameAddressNode, "zip_code"));
											
											ni.setNa_address_type(readXMLNode(nameAddressNode, "address_type"));
											
											ni.setNa_current_address(readXMLNode(nameAddressNode, "current_address;"));
											
											ni.setNa_parsed_flag(readXMLNode(nameAddressNode, "parsed_flag"));
											corpDao.corpNameSave(ni);
											continue;
										}
										
									}
									
								}
								if(mergerResultNode.getNodeName().equals("address"))
								{
									CorpAddress ca=new CorpAddress();
									ca.setUser_search_id(searchId);
									ca.setFiling_number(cp.getFiling_number().toUpperCase());
									ca.setA_address1(readXMLNode(mergerResultNode, "address1"));
									
									ca.setA_address2(readXMLNode(mergerResultNode, "address2"));
									
									ca.setA_address3(readXMLNode(mergerResultNode, "address3"));
									
									ca.setA_city(readXMLNode(mergerResultNode, "city"));
									
									ca.setA_state(readXMLNode(mergerResultNode, "state"));
									
									ca.setA_zip_code(readXMLNode(mergerResultNode, "zip_code"));
									
									ca.setA_address_type(readXMLNode(mergerResultNode, "address_type"));
									
									ca.setA_current_address(readXMLNode(mergerResultNode, "current_address"));
									
									ca.setA_effective_date(readXMLNode(mergerResultNode, "effective_date"));
									
									ca.setA_parsed_flag(readXMLNode(mergerResultNode, "parsed_flag"));
									corpDao.corpAddressSave(ca);
									continue;
								}
								CorpHistory ch=new CorpHistory();
								if(mergerResultNode.getNodeName().equals("other_info"))
								{
									String infotitle="";
									String infodesc="";
									infotitle=readXMLNode(mergerResultNode, "info_title");
									if(infotitle == null || infotitle.isEmpty())
									{
										ch.setInfo_title("");
									}
									else{
									ch.setInfo_title(infotitle);
									}
									infodesc=readXMLNode(mergerResultNode, "info_desc");
									if(infodesc == null || infodesc.isEmpty())
									{
									ch.setInfo_desc("");
									}
									else {
										ch.setInfo_desc(infodesc);
									}
									
									continue;
								}
								if(mergerResultNode.getNodeName().equals("corp_history"))
								{
									ch.setUser_search_id(searchId);
									ch.setFiling_number(cp.getFiling_number());
									ch.setHistory_pages(readXMLNode(mergerResultNode, "history_pages"));
									ch.setHistory_code(readXMLNode(mergerResultNode, "history_code"));
									ch.setHistory_desc(readXMLNode(mergerResultNode, "history_desc"));
									ch.setEffective_date(readXMLNode(mergerResultNode, "effective_date"));
									ch.setHistory_num(readXMLNode(mergerResultNode, "history_num"));
									ch.setHistory_name(readXMLNode(mergerResultNode, "history_name"));
									ch.setLocator_num(readXMLNode(mergerResultNode, "locator_num"));
									corpDao.corpHistorySave(ch);
									continue;
								}
								
							}
							
						}
						
					}
					corpDao.insertCorpResposes(cp);
			         
					
				  }
				
				}
			
			}
		
        }
       else
         {
        	 searchStatusId=STATUS_NO_RESULTS;
        	 totalMatches=0;
         }
		 			logger.info("search status id: " + searchStatusId);
		 			if ((totalMatches == 0) || (rowsReturned == 0)) {
					logger.info("search successful but returned no results, continuing");
			
				}
		} 
		 
		 catch (TorqueException e) {
			 String error = "Failed to store Corporation results";
				logger.error(error, e);
				throw new SearchException(error, e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 logger.info("stored results");
		 return searchId;
		
		
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
