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

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
import springapp.domain.EvictionXMLParser;
import springapp.domain.NewBJLResults;
import springapp.domain.NewBJLResultsPeer;
import springapp.domain.NewBJLSearches;
import springapp.domain.NewBJLSearchesPeer;
import springapp.domain.eviction.EvictionResults;
import springapp.domain.eviction.EvictionResultsPeer;
import springapp.domain.eviction.EvictionSearches;
import springapp.domain.eviction.EvictionSearchesPeer;
import springapp.manager.SearchException;
import springapp.service.user.UserManager;
import springapp.web.funnel.BjlXMLbean;
import springapp.web.funnel.BjlXMLbean.bjlAddressObj;
import springapp.web.funnel.BjlXMLbean.bjlFilingObj;
import springapp.web.funnel.BjlXMLbean.bjlNameObj;

/**
 * Provides support for querying the Eviction interface.
 */
@Service("evictionSearchManager")
public class SpringEvictionSearchManager implements ResourceLoaderAware {
	protected static ArrayList<BjlDataSources> bjlSources;

	public final static String liveUrl="https://www.tracersinfo.com/xmlgw.php";

	public static final short MAX_RESULTS = 250;
	
	protected static final Short SEARCH_CATEGORY_ID = 33;
	
	protected static final Short STATUS_FOUND_RESULTS = 1;
	protected static final Short STATUS_NO_RESULTS = 1;
	protected static final Short STATUS_TOO_MANY_RESULTS = 1;
	
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
			EvictionSearches search = EvictionSearchesPeer.retrieveByPK(userSearchId);
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
			List<EvictionResults> results = EvictionResultsPeer.doSelect(new Criteria());
			return results.size();
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	
	/**
	 * Delete a list of Eviction matches.
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
			c.add(EvictionResultsPeer.USER_SEARCH_ID, userSearchId);
			c.addIn(EvictionResultsPeer.RESULT_ID, resultIds);
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
	public List<EvictionResults> getResults(long userSearchId, int[] resultIds) throws SearchException {
		try {
			Criteria c = new Criteria();			
			c.add(EvictionResultsPeer.USER_SEARCH_ID, userSearchId);
			c.addIn(EvictionResultsPeer.RESULT_ID, resultIds);
			List<EvictionResults> results = EvictionResultsPeer.doSelect(c);
			return results;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	
	public EvictionResults getResult(int resultId) throws SearchException {
		try {
			EvictionResults result = EvictionResultsPeer.retrieveByPK(resultId);
			return result;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	
	/**
	 * Retrieve a detailed description of a Eviction search result.
	 * @param typeOfItem 
	 */
	public List<EvictionResults> getAllResults(long userSearchId) throws SearchException {		
		try {
			Criteria c = new Criteria();
			c.add(EvictionResultsPeer.USER_SEARCH_ID, userSearchId);
			
			List<EvictionResults> results = EvictionResultsPeer.doSelect(c);
			return results;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}

	/**
	 * Retrieve a single Eviction search.
	 */
	public EvictionSearches getSearch(long userSearchId) throws SearchException {
		try {
			EvictionSearches search = EvictionSearchesPeer.retrieveByPK(userSearchId);
			return search;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	
	/**
	 * Retrieve a single Eviction search.
	 */
	public EvictionSearches getSearch(String username, long userSearchId) throws SearchException {
		try {
			Criteria c = new Criteria();
			c.add(SsUsersPeer.USERNAME, username);
			c.add(EvictionSearchesPeer.USER_SEARCH_ID, userSearchId);
			c.addJoin(EvictionSearchesPeer.USER_ID, EvictionSearchesPeer.USER_ID);
			List<EvictionSearches> list = EvictionSearchesPeer.doSelect(c);
			return list.get(0);
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}

	/**
	 * Retrieve all Eviction searches for a user.
	 */
	public List<EvictionSearches> getSearches(String username) throws SearchException {
		try {
			Criteria c = new Criteria();
			c.add(SsUsersPeer.USERNAME, username);
			c.addJoin(SsUsersPeer.USER_ID, EvictionSearchesPeer.USER_ID);
			List<EvictionSearches> list = EvictionSearchesPeer.doSelect(c);
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
		return getCost("Eviction Search", subCategory);
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
	public long queryFunnel(Integer userId, Integer searchSubCategoryId, Integer transactionId, 
			String evicfirstname,String eviclastname,String evicBusinessnmae,
			String evicState, 
			String eviccity,
			String evicreference) {
		return this.query(userId, searchSubCategoryId, transactionId,
				evicfirstname, eviclastname, evicBusinessnmae, evicState, 
				eviccity,
				evicreference);
	}
	
	/**
	 * Query the BJL interface and store the results in the database.
	 * 
	 * @throws SearchException
	 *             If the SOAP request failed for any reason.
	 */
	public long query(Integer userId, Integer searchSubCategoryId, Integer transactionId,
			String evicfirstname,String eviclastname,String evicBusinessnmae,
			String evicState, 
			String eviccity,
			String evicreference) throws SearchException {
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
		String city="";
		String reference="";
		String businessname="";
		
		lastname=eviclastname;
		firstname= evicfirstname;
		businessname=evicBusinessnmae;
		state=evicState;
		reference=evicreference;
		logger.info("city"+eviccity);
		logger.info("Reference"+evicreference);
		if(eviccity != null)
		{
			city=eviccity;
		}
		if(evicreference != null)
		{
			reference = evicreference;
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
		 xml=xml+"<search>"+"EVICTIONS"+"</search>";
		 xml=xml+"<search_identifier>"+"S002"+"</search_identifier>";
		 xml=xml+"<search_criteria>";
		 xml=xml+"<defendant_firstname>"+firstname+"</defendant_firstname>";
		 xml=xml+"<defendant_lastname>"+lastname+"</defendant_lastname>";
		 xml=xml+"<state>"+state+"</state>";
		 xml=xml+"<city>"+city+"</city>";
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
		 xml=xml+"<search>"+"EVICTIONS"+"</search>";
		 xml=xml+"<search_identifier>"+"S002"+"</search_identifier>";
		 xml=xml+"<search_criteria>";
		 xml=xml+"<defendant_businessname>"+businessname+"</defendant_businessname>";
		 xml=xml+"<state>"+state+"</state>";
		 xml=xml+"<city>"+city+"</city>";
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
	    	searchStatusId=STATUS_NO_RESULTS;
         
	    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	    	Document doc = builder.parse(new InputSource(new StringReader(xmlResponse.replace("&nbsp;", "&#160;"))));
		 //local test mode state and international
		 //InputSource resxml = new InputSource(new FileInputStream(new File("C:/BJLResultnational.xml")));
		 //InputSource resxml = new InputSource(new FileInputStream(new File("C:/braddavis.xml")));
		 //Document doc=builder.parse(resxml);
		 
	    	NodeList nList=null;
      		         
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
			
			EvictionSearches searchdata = new EvictionSearches();
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
			searchdata.setCity(city);
			searchdata.setState(state);
			searchdata.setReference(reference);
		 
			
			searchdata.save();
			searchId = searchdata.getUserSearchId();
			logger.info("search id: " + searchId);
			logger.info("adding results");
			
			
			EvictionXMLParser ep=new EvictionXMLParser();
				
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
						if(classNode.getNodeName().equals("case"))
						{
							ep.setTis_sig_case(readXMLNode(classNode, "tis_sig_case"));
							ep.setTis_vendor_code(readXMLNode(classNode, "tis_vendor_code"));
							ep.setCase_number(readXMLNode(classNode, "case_number"));
							ep.setCase_sequence_number(readXMLNode(classNode, "case_sequence_number"));
							ep.setCase_year_date(readXMLNode(classNode, "case_year_date"));
							ep.setBook_number(readXMLNode(classNode, "book_number"));
							ep.setPage_number(readXMLNode(classNode, "page_number"));
							ep.setUnique_id(readXMLNode(classNode, "unique_id"));
							ep.setResult_code(readXMLNode(classNode, "result_code"));
							ep.setResult_desc(readXMLNode(classNode, "result_desc"));
							
							ep.setResult_date(readXMLNode(classNode, "result_date"));
							ep.setFile_date(readXMLNode(classNode, "file_date"));
							ep.setInput_by(readXMLNode(classNode, "input_by"));
							ep.setInput_date(readXMLNode(classNode, "input_date"));
							ep.setExecuted_date(readXMLNode(classNode, "executed_date"));
							ep.setReceived_date(readXMLNode(classNode, "received_date"));
							ep.setSatisfied_date(readXMLNode(classNode, "satisfied_date"));
							ep.setVerified_date(readXMLNode(classNode, "verified_date"));
							ep.setSource_id(readXMLNode(classNode, "source_id"));
							ep.setRecord_status(readXMLNode(classNode, "record_status"));
							
							ep.setJudgement_amount(readXMLNode(classNode, "judgement_amount"));
							ep.setJudgement_flag(readXMLNode(classNode, "judgement_flag"));
							ep.setState_abbr(readXMLNode(classNode, "state_abbr"));
							ep.setCourt_type_code(readXMLNode(classNode, "court_type_code"));
							ep.setCourt_type_desc(readXMLNode(classNode, "court_type_desc"));
							ep.setCourthouse_sub_code(readXMLNode(classNode, "courthouse_sub_code"));
							ep.setCounty_fips_code(readXMLNode(classNode, "county_fips_code"));
							ep.setCounty_fips_desc(readXMLNode(classNode, "county_fips_desc"));
							ep.setState_fips_code(readXMLNode(classNode, "state_fips_code"));
							ep.setState_fips_desc(readXMLNode(classNode, "state_fips_desc"));
							ep.setAffiliate(readXMLNode(classNode, "affiliate"));
							ep.setPossession_flag(readXMLNode(classNode, "possession_flag"));
							ep.setPossession_flag_desc(readXMLNode(classNode, "possession_flag_desc"));
							ep.setRegion(readXMLNode(classNode, "region"));
							NodeList innerNodeList=classNode.getChildNodes();
							for(int k=0;k < innerNodeList.getLength();k++)
							{
								Node innerResultNode=innerNodeList.item(k);
								if(innerResultNode.getNodeName().equals("evictions_defendant_list"))
								{
									NodeList innerChlidList=innerResultNode.getChildNodes();
									for (int l = 0; l < innerChlidList.getLength(); l++) {
										Node innerChlidResult=innerChlidList.item(l);
										if(innerChlidResult.getNodeName().equals("defendant"))
										{
											ep.setTis_sig_defendant(readXMLNode(innerChlidResult, "tis_sig_defendant"));
											ep.setDefendant_number(readXMLNode(innerChlidResult, "defendant_number"));
											ep.setD_county_fips_code(readXMLNode(innerChlidResult, "county_fips_code"));
											ep.setD_county_fips_desc(readXMLNode(innerChlidResult, "county_fips_desc"));
											ep.setD_state_fips_code(readXMLNode(innerChlidResult, "state_fips_code"));
											ep.setD_state_fips_desc(readXMLNode(innerChlidResult, "state_fips_desc"));
											ep.setD_ssn(readXMLNode(innerChlidResult, "ssn"));
											ep.setD_fullname(readXMLNode(innerChlidResult, "fullname"));
											ep.setD_firstname(readXMLNode(innerChlidResult, "firstname"));
											ep.setD_middlename(readXMLNode(innerChlidResult, "middlename"));
											ep.setD_lastname(readXMLNode(innerChlidResult, "lastname"));
											ep.setD_suffix(readXMLNode(innerChlidResult, "suffix"));
											ep.setD_alias_flag(readXMLNode(innerChlidResult, "alias_flag"));
											ep.setD_alias_firstname(readXMLNode(innerChlidResult, "alias_firstname"));
											ep.setD_alias_middlename(readXMLNode(innerChlidResult, "alias_middlename"));
											ep.setD_alias_lastname(readXMLNode(innerChlidResult, "alias_lastname"));
											ep.setD_alias_suffix(readXMLNode(innerChlidResult, "alias_suffix"));
											ep.setD_birth_date(readXMLNode(innerChlidResult, "birth_date"));
											ep.setD_address_line_1(readXMLNode(innerChlidResult, "address_line_1"));
											ep.setD_address_line_2(readXMLNode(innerChlidResult, "address_line_2"));
											ep.setD_apartment_number(readXMLNode(innerChlidResult, "apartment_number"));
											ep.setD_city(readXMLNode(innerChlidResult, "city"));
											ep.setD_state(readXMLNode(innerChlidResult, "state"));
											ep.setD_zipcode(readXMLNode(innerChlidResult, "zipcode"));
											ep.setD_phone(readXMLNode(innerChlidResult, "phone"));
											ep.setD_clean_area_code(readXMLNode(innerChlidResult, "clean_area_code"));
											ep.setD_clean_phone_number(readXMLNode(innerChlidResult, "clean_phone_number"));
											continue;
										}
									}
								}
								if(innerResultNode.getNodeName().equals("evictions_plaintiff_list"))
								{
									NodeList plaintifList=innerResultNode.getChildNodes();
									for (int l = 0; l < plaintifList.getLength(); l++) {
										
										Node plaintiffResult=plaintifList.item(l);
										if(plaintiffResult.getNodeName().equals("plaintiff"))
										{
											ep.setTis_sig_plaintiff(readXMLNode(plaintiffResult, "tis_sig_plaintiff"));
											ep.setPlaintiff_type(readXMLNode(plaintiffResult, "plaintiff_type"));
											ep.setPlaintiff_desc(readXMLNode(plaintiffResult, "plaintiff_desc"));
											ep.setP_county_fips_code(readXMLNode(plaintiffResult, "county_fips_code"));
											ep.setP_county_fips_desc(readXMLNode(plaintiffResult, "county_fips_desc"));
											ep.setP_state_fips_code(readXMLNode(plaintiffResult, "state_fips_code"));
											ep.setP_state_fips_desc(readXMLNode(plaintiffResult, "state_fips_desc"));
											
											ep.setP_fullname(readXMLNode(plaintiffResult, "fullname"));
											ep.setP_firstname(readXMLNode(plaintiffResult, "firstname"));
											ep.setP_middlename(readXMLNode(plaintiffResult, "middlename"));
											ep.setP_lastname(readXMLNode(plaintiffResult, "lastname"));
											ep.setP_address_line_1(readXMLNode(plaintiffResult, "address_line_1"));
											ep.setP_address_line_2(readXMLNode(plaintiffResult, "address_line_2"));
											ep.setP_city(readXMLNode(plaintiffResult, "city"));
											
											ep.setP_state(readXMLNode(plaintiffResult, "state"));
											ep.setP_zipcode(readXMLNode(plaintiffResult, "zipcode"));
											ep.setP_phone_number(readXMLNode(plaintiffResult, "phone_number"));
											ep.setP_clean_area_code(readXMLNode(plaintiffResult, "clean_area_code"));
											ep.setP_clean_phone_number(readXMLNode(plaintiffResult, "clean_phone_number"));
											continue;
										}
										
									}
									
								}
							}
							continue;
						}
						
						if(classNode.getNodeName().equals("courtinfo"))
						{
							ep.setName(readXMLNode(classNode, "name"));
							ep.setAddress1(readXMLNode(classNode, "address1"));
							ep.setAddress2(readXMLNode(classNode, "address2"));
							ep.setCity(readXMLNode(classNode, "city"));
							ep.setState(readXMLNode(classNode, "state"));
							ep.setZip(readXMLNode(classNode, "zip"));
							continue;
							}
						
						
						}
					EvictionResults result = new EvictionResults();
					result.setUserSearchId(searchId);
					  result.setTis_sig_case(ep.getTis_sig_case());
			          result.setTis_vendor_code(ep.getTis_vendor_code());
			          result.setCase_number(ep.getCase_number());
			          result.setCase_sequence_number(ep.getCase_sequence_number());
			          result.setCase_year_date(ep.getCase_year_date());
			          result.setBook_number(ep.getBook_number());
			          result.setPage_number(ep.getPage_number());
			          result.setUnique_id(ep.getUnique_id());
			          result.setResult_code(ep.getResult_code());
			          result.setResult_desc(ep.getResult_desc());
			          result.setResult_date(ep.getResult_date());
			          result.setFile_date(ep.getFile_date());
			          result.setInput_by(ep.getInput_by());
			          result.setInput_date(ep.getInput_date());
			          result.setExecuted_date(ep.getExecuted_date());
			          result.setReceived_date(ep.getReceived_date());
			          result.setSatisfied_date(ep.getSatisfied_date());
			          result.setVerified_date(ep.getVerified_date());
			          result.setSource_id(ep.getSource_id());
			          result.setRecord_status(ep.getRecord_status());
			          result.setJudgement_amount(ep.getJudgement_amount());
			          result.setJudgement_flag(ep.getJudgement_flag());
			          result.setState_abbr(ep.getState_abbr());
			          result.setCourt_type_code(ep.getCourt_type_code());
			          result.setCourt_type_desc(ep.getCourt_type_desc());
			          result.setCourthouse_sub_code(ep.getCourthouse_sub_code());
			          result.setCounty_fips_code(ep.getCounty_fips_code());
			          result.setCounty_fips_desc(ep.getCounty_fips_desc());
			          result.setState_fips_code(ep.getState_fips_code());
			          result.setState_fips_desc(ep.getState_fips_desc());
			          result.setAffiliate(ep.getAffiliate());
			          result.setPossession_flag(ep.getPossession_flag());
			          result.setPossession_flag_desc(ep.getPossession_flag_desc());
			          result.setRegion(ep.getRegion());
			          result.setTis_sig_defendant(ep.getTis_sig_defendant());
			          result.setDefendant_number(ep.getDefendant_number());
			          result.setD_county_fips_code(ep.getD_county_fips_code());
			          result.setD_county_fips_desc(ep.getD_county_fips_desc());
			          result.setD_state_fips_code(ep.getD_state_fips_code());
			          result.setD_state_fips_desc(ep.getD_state_fips_desc());
			          result.setD_ssn(ep.getD_ssn());
			          result.setD_fullname(ep.getD_fullname());
			          result.setD_firstname(ep.getD_firstname());
			          result.setD_middlename(ep.getD_middlename());
			          result.setD_lastname(ep.getD_lastname());
			          result.setD_suffix(ep.getD_suffix());
			          result.setD_alias_flag(ep.getD_alias_flag());
			          result.setD_alias_firstname(ep.getD_alias_firstname());
			          result.setD_alias_middlename(ep.getD_alias_middlename());
			          result.setD_alias_lastname(ep.getD_alias_lastname());
			          result.setD_alias_suffix(ep.getD_alias_suffix());
			          result.setD_birth_date(ep.getD_birth_date());
			          result.setD_address_line_1(ep.getD_address_line_1());
			          result.setD_address_line_2(ep.getD_address_line_2());
			          result.setD_apartment_number(ep.getD_apartment_number());
			          result.setD_city(ep.getD_city());
			          result.setD_state(ep.getD_state());
			          result.setD_phone(ep.getD_phone());
			          result.setD_zipcode(ep.getD_zipcode());
			          result.setD_clean_area_code(ep.getD_clean_area_code());
			          result.setD_clean_phone_number(ep.getD_clean_phone_number());
			          result.setTis_sig_plaintiff(ep.getTis_sig_plaintiff());
			          result.setPlaintiff_type(ep.getPlaintiff_type());
			          result.setPlaintiff_desc(ep.getPlaintiff_desc());
			          result.setP_county_fips_code(ep.getP_county_fips_code());
			          result.setP_county_fips_desc(ep.getP_county_fips_desc());
			          result.setP_state_fips_code(ep.getP_state_fips_code());
			          result.setP_state_fips_desc(ep.getP_state_fips_desc());
			          result.setP_fullname(ep.getP_fullname());
			          result.setP_firstname(ep.getP_firstname());
			          result.setP_middlename(ep.getP_middlename());
			          result.setP_lastname(ep.getP_lastname());
			          result.setP_address_line_1(ep.getP_address_line_1());
			          result.setP_address_line_2(ep.getP_address_line_2());
			          result.setP_city(ep.getP_city());
			          result.setP_state(ep.getP_state());
			          result.setP_phone_number(ep.getP_phone_number());
			          result.setP_zipcode(ep.getP_zipcode());
			          result.setP_clean_area_code(ep.getP_clean_area_code());
			          result.setP_clean_phone_number(ep.getP_clean_phone_number());
			          result.setName(ep.getName());
			          result.setAddress1(ep.getAddress1());
			          result.setAddress2(ep.getAddress2());
			          result.setCity(ep.getCity());
			          result.setState(ep.getState());
			          result.setZip(ep.getZip());
			          result.save();
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
			 String error = "Failed to store BJL results";
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
