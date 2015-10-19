package springapp.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import net.searchsystems.limestone.BjlDataSources;
import net.searchsystems.limestone.BjlDataSourcesPeer;
import net.searchsystems.limestone.SsSearchCategoriesPeer;
import net.searchsystems.limestone.SsSearchSubCategories;
import net.searchsystems.limestone.SsSearchSubCategoriesPeer;
import net.searchsystems.limestone.SsUserSearches;
import net.searchsystems.limestone.SsUserSearchesPeer;
import net.searchsystems.limestone.SsUsersPeer;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
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


import springapp.domain.BJLXmlParserHelper;
import springapp.domain.NewBJLResults;
import springapp.domain.NewBJLResultsPeer;
import springapp.domain.NewBJLSearches;
import springapp.domain.NewBJLSearchesPeer;
import springapp.manager.SearchException;


/**
 * Provides support for querying the BJL interface.
 * author : Vivek
 */
@Service("bjlSearchManager")
public class SpringBJLSearchManager implements ResourceLoaderAware {
	protected static ArrayList<BjlDataSources> bjlSources;

	// BJL settings
	//public static final String ENDPOINT = "https://www.insightgateways.com/webservices/newsrch.cfm";
	//public static final String ENDPOINT_NOSSL = "http://www.insightgateways.com/webservices/newsrch.cfm";
	public static final String liveUrl="https://www.tracersinfo.com/xmlgw.php";

	public static final short MAX_RESULTS = 250;
	
	protected static final Short SEARCH_CATEGORY_ID = 1;
	
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
			NewBJLSearches search = NewBJLSearchesPeer.retrieveByPK(userSearchId);
			search.setReferenceCode(referenceCode);
			search.save();
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}


	/**
	 * Retrieve the count of matches returned from a BJL search.
	 */
	public int getResultCount(long userSearchId) throws SearchException {
		try {
			List<NewBJLResults> results = NewBJLResultsPeer.doSelect(new Criteria());
			return results.size();
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	  
	/**
	 * Delete a list of BJL matches.
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
			c.add(NewBJLResultsPeer.USER_SEARCH_ID, userSearchId);
			c.addIn(NewBJLResultsPeer.RESULT_ID, resultIds);
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
	public List<NewBJLResults> getResults(long userSearchId, int[] resultIds) throws SearchException {
		try {
			Criteria c = new Criteria();			
			c.add(NewBJLResultsPeer.USER_SEARCH_ID, userSearchId);
			c.addIn(NewBJLResultsPeer.RESULT_ID, resultIds);
			List<NewBJLResults> results = NewBJLResultsPeer.doSelect(c);
			return results;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	
	public NewBJLResults getResult(int resultId) throws SearchException {
		try {
			NewBJLResults result = NewBJLResultsPeer.retrieveByPK(resultId);
			return result;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}

		
	public List<NewBJLResults> getBankruptyResult(String[] resultId,Integer userSearchID, String bankrupticname) throws SearchException
	{
		
		try 
		{
			Criteria cr=new Criteria();
			cr.addIn(NewBJLResultsPeer.DOCKET_NUMBER, resultId);
			cr.add(NewBJLResultsPeer.USER_SEARCH_ID, userSearchID);
			cr.add(NewBJLResultsPeer.FILING_GROUP_DESC, bankrupticname);			
			
			List<NewBJLResults> result=NewBJLResultsPeer.doSelect(cr);
			return result;
		} 
		catch (TorqueException e) 
		{
			// TODO Auto-generated catch block
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	/** get Result for JUDGEMENT **/
	public List<NewBJLResults> getJudgementResults(String[] resultId,Integer userSearchID, List<String> arr) throws SearchException
	{
		
		try 
		{
			Criteria cr=new Criteria();
			cr.addIn(NewBJLResultsPeer.DOCKET_NUMBER, resultId);
			cr.add(NewBJLResultsPeer.USER_SEARCH_ID, userSearchID);
			cr.addIn(NewBJLResultsPeer.FILING_GROUP_DESC, arr);
			List<NewBJLResults> result=NewBJLResultsPeer.doSelect(cr);
			return result;
		} catch (TorqueException e) 
		{
			// TODO Auto-generated catch block
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	/** get Result for Taxliens **/
	public List<NewBJLResults> getTaxliensResults(String[] resultId,Integer userSearchID, String taxlineName) throws SearchException
	{
		
		try {
			Criteria cr=new Criteria();
			cr.addIn(NewBJLResultsPeer.DOCKET_NUMBER, resultId);
			cr.add(NewBJLResultsPeer.USER_SEARCH_ID, userSearchID);
			cr.add(NewBJLResultsPeer.FILING_GROUP_DESC, taxlineName);
			List<NewBJLResults> result=NewBJLResultsPeer.doSelect(cr);
			return result;
		    } 
			catch (TorqueException e) {
			// TODO Auto-generated catch block
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	/** get ALL Result  **/
	public List<NewBJLResults> getAllBJLResults(int[] resultId) throws SearchException
	{
		
		try {
			Criteria cr=new Criteria();
			cr.addIn(NewBJLResultsPeer.RESULT_ID, resultId);
			List<NewBJLResults> result=NewBJLResultsPeer.doSelect(cr);
			return result;
		    } 
			catch (TorqueException e) {
			// TODO Auto-generated catch block
			logger.error("error", e);
			throw new SearchException(e);
		}
	}


	/**
	 * Retrieve a detailed description of a BJL search result.
	 * @param typeOfItem 
	 */
	public List<NewBJLResults> getAllResults(long userSearchId,List<String> typeOfItem, String LastName, String FirstName) throws SearchException {		
		try {
			Criteria c = new Criteria();
			c.add(NewBJLResultsPeer.USER_SEARCH_ID, userSearchId);
			c.addIn(NewBJLResultsPeer.FILING_GROUP_DESC, typeOfItem);
			
			//c.add(NewBJLResultsPeer.LASTNAME, LastName);
			//c.add(NewBJLResultsPeer.FIRSTNAME, FirstName); 
			c.addAscendingOrderByColumn(NewBJLResultsPeer.FILING_GROUP_DESC);
			List<NewBJLResults> results = NewBJLResultsPeer.doSelect(c);
			return results;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	/** Retrieve business search results*/
	public List<NewBJLResults> getAllResults(long userSearchId,List<String> typeOfItem,String BusinessName) throws SearchException {		
		try {
			Criteria c = new Criteria();
			c.add(NewBJLResultsPeer.USER_SEARCH_ID, userSearchId);
			c.addIn(NewBJLResultsPeer.FILING_GROUP_DESC, typeOfItem);
			
			//c.add(NewBJLResultsPeer.LASTNAME, BusinessName);
			
			c.addAscendingOrderByColumn(NewBJLResultsPeer.FILING_GROUP_DESC);
			List<NewBJLResults> results = NewBJLResultsPeer.doSelect(c);
			//logger.info("ResltDetls: "+ results);
			return results;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	/** Retrieve business search results*/
	public List<NewBJLResults> getAllSSNResults(long userSearchId,List<String> typeOfItem, String bjlSSN) throws SearchException {		
		try {
			Criteria c = new Criteria();
			c.add(NewBJLResultsPeer.USER_SEARCH_ID, userSearchId);
			c.addIn(NewBJLResultsPeer.FILING_GROUP_DESC, typeOfItem);
			
			//c.add(NewBJLResultsPeer.SSN, bjlSSN);
			c.addAscendingOrderByColumn(NewBJLResultsPeer.FILING_GROUP_DESC);
			List<NewBJLResults> results = NewBJLResultsPeer.doSelect(c);
			return results;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}


	/**
	 * Retrieve a single BJL search.
	 */
	public NewBJLSearches getSearch(long userSearchId) throws SearchException {
		try {
			NewBJLSearches search = NewBJLSearchesPeer.retrieveByPK(userSearchId);
			return search;
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}
	
	/**
	 * Retrieve a single BJL search.
	 */
	public NewBJLSearches getSearch(String username, long userSearchId) throws SearchException {
		try {
			Criteria c = new Criteria();
			c.add(SsUsersPeer.USERNAME, username);
			c.add(NewBJLSearchesPeer.USER_SEARCH_ID, userSearchId);
			c.addJoin(NewBJLSearchesPeer.USER_ID, NewBJLSearchesPeer.USER_ID);
			List<NewBJLSearches> list = NewBJLSearchesPeer.doSelect(c);
			return list.get(0);
		} catch (TorqueException e) {
			logger.error("error", e);
			throw new SearchException(e);
		}
	}

	/**
	 * Retrieve all BJL searches for a user.
	 */
	public List<NewBJLSearches> getSearches(String username) throws SearchException {
		try {
			Criteria c = new Criteria();
			c.add(SsUsersPeer.USERNAME, username);
			c.addJoin(SsUsersPeer.USER_ID, NewBJLSearchesPeer.USER_ID);
			List<NewBJLSearches> list = NewBJLSearchesPeer.doSelect(c);
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
		return getCost("Bankruptcies, Judgments and Tax Liens", subCategory);
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
	public long queryFunnel(Integer userId, Integer transactionId, 
			String bjllastname, String bjlfirstname, String bjlmiddlename,
			String bjlState,String bjlssn,String bjlssn_type,String bjlBusinessName, 
			String bjlReference, String bjlCaseType) {
		return this.query(userId, 1, transactionId,
				bjllastname, bjlfirstname, bjlmiddlename, 
				bjlState, bjlssn, bjlssn_type, bjlBusinessName, bjlReference, null);
	}
	
	/**
	 * Query the BJL interface and store the results in the database.
	 * 
	 * @throws SearchException
	 *             If the SOAP request failed for any reason.
	 */
	public long query(Integer userId, Integer searchSubCategoryId, Integer transactionId,
			String bjllastame, String bjlfirstname, String bjlmiddlename,
			String bjlstate, String bjlssn, 
			String bjlssn_type,
			String bjlBusinessName, String bjlReference, String bjlCaseType) throws SearchException {
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
		
		
		String middlename="";
		String firstname="";
		String lastname="";
		String business="";
		String ssn="";
		String state="";
		String ssn_type="";
		
		
		firstname=bjlfirstname;
		lastname=bjllastame;
		business=bjlBusinessName;
		ssn=bjlssn;
		state=bjlstate;
		
		StringBuffer xml=new StringBuffer();

		if(bjlssn_type != null)
		{
			ssn_type=bjlssn_type;
			
		}
		if(bjlmiddlename != null)
		{
			middlename=bjlmiddlename;			
		}
		
		//logger.info("Business Name "+ bjlBusinessName);
			xml.append("<?xml version='1.0'?>");
			xml.append("<xmlgw>");
			xml.append("<session>");
			xml.append("<user_id>"+userID+"</user_id>");
			xml.append("<password>"+password+"</password>");
			xml.append("<customer_reference>"+bjlReference+"</customer_reference>");
			xml.append("</session>");
			xml.append("<search_options>");
			xml.append("</search_options>");
			xml.append("<search_request>");
			xml.append("<search>"+"blj"+"</search>");
			xml.append("<search_identifier></search_identifier>");
			xml.append("<search_criteria>");
			
			if(firstname != null && lastname != null)
			{
				xml.append("<lastname>"+lastname+"</lastname>");
				xml.append("<firstname>"+firstname+"</firstname>");
				xml.append("<middlename>"+middlename+"</middlename>");
				business="";
				ssn="";
				//xml.append("<lastname>European Tile</lastname>");
			}
			else
			{
				firstname="";
				lastname="";
			}
			
			if(business != null && !business.isEmpty())
			{
			 xml.append("<lastname>"+business+"</lastname>");			 
			 firstname ="";
			 lastname="";
			 //logger.info("business");
			}
			else
			{
				business="";
			}
			if(ssn != null && !ssn.isEmpty())
			{
				xml.append("<ssn>"+ssn+"</ssn>");//000000009
				xml.append("<ssn_type>"+ssn_type+"</ssn_type>");//000000009
				firstname="";
				lastname="";
				business="";
				//logger.info("ssn");
			}
			else 
			{
				ssn="";
			}
			xml.append("<state>"+state+"</state>");
			xml.append("</search_criteria>");
			xml.append("</search_request>");
			xml.append("</xmlgw>");			
		
		 
		 
		 	logger.info("request xml"+xml);
		 	String xmlResponse="";
		 	try
		 	{
			 	HttpClient client=new HttpClient();
				PostMethod post=new PostMethod(liveUrl);
			 	post.setParameter("request", xml.toString());
			 	int returncode=client.executeMethod(post);
				logger.info("Return Code:"+returncode);
		        BufferedReader  br=new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));
		        String readline=null;
		        while((readline=br.readLine()) != null)
		         {
		        	sb.append(readline+"\n"); 
		         }
		        
		          xmlResponse=sb.toString();
		         logger.info("_BJLResponse:"+ xmlResponse);
		 	}
		 	catch(Exception exp)
		 	{
		 		logger.info("Service Post Error:"+ exp);
		 	}
	      
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
		 //InputSource resxml = new InputSource(new FileInputStream(new File("C:/daniel.xml")));
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
				//logger.info("Reason Text:"+reasonstring);
	    	}
	    		nList=doc.getElementsByTagName("response_data");
	    		totalMatches=nList.getLength();
	    		
		if(totalMatches > 0 && totalMatches != 0)
		{
			
			NewBJLSearches searchdata = new NewBJLSearches();
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
			
			// bjl fields
			
				searchdata.setFirstName(firstname);				
				searchdata.setLastName(lastname);
			    searchdata.setBusinessName(business);				
			    searchdata.setMiddleName(middlename);			     
				searchdata.setState(bjlstate); 
				searchdata.setSSN(ssn);
			    searchdata.setSSNType(bjlssn_type);
			    searchdata.setWhoIsSearchFor("bank ruptic"); 
			   searchdata.setReferenceCode(bjlReference);
			   searchdata.save();
			searchId = searchdata.getUserSearchId();
			logger.info("search id: " + searchId);
			
			logger.info("adding results");
			
			
			BJLXmlParserHelper xp=new BJLXmlParserHelper();
				
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
						if(classNode.getNodeName().equals("blj_name"))
						{
							
							xp.setName_filing_state(readXMLNode(classNode, "name_filing_state")); 
							
							xp.setFirstname(readXMLNode(classNode, "firstname")); 
							xp.setMiddlename(readXMLNode(classNode, "middlename"));
							xp.setLastname(readXMLNode(classNode, "lastname"));
							
							//Udhay Aug8 ==> New Tracers fields addedd;
							xp.setFullname(readXMLNode(classNode, "fullname"));
							xp.setBusinessname(readXMLNode(classNode, "businessname"));
							xp.setDob(readXMLNode(classNode, "dob"));							
							
							xp.setName_suffix(readXMLNode(classNode, "name_suffix")); 
							xp.setName_type(readXMLNode(classNode, "name_type")); 
							xp.setName_type_desc(readXMLNode(classNode, "name_type_desc"));
							xp.setSsn_taxid_flag(readXMLNode(classNode, "ssn_taxid_flag"));
							
							xp.setSsn(readXMLNode(classNode, "ssn"));
							
							xp.setComment_sequence(readXMLNode(classNode, "comment_sequence")); 
							xp.setComment_type(readXMLNode(classNode, "comment_type")); 
							xp.setComment_type_desc(readXMLNode(classNode, "comment_type_desc"));
							
							continue;
						}
						if(classNode.getNodeName().equals("blj_address"))
						{
							xp.setHouse_number(readXMLNode(classNode, "house_number"));
							xp.setStreet_direction(readXMLNode(classNode, "street_direction")); 
							xp.setStreet_name(readXMLNode(classNode, "street_name")); 
							xp.setStreet_suffix(readXMLNode(classNode, "street_suffix")); 
						    xp.setApartment_num(readXMLNode(classNode, "apartment_num")); 
						    xp.setCity(readXMLNode(classNode, "city"));
					  
					        xp.setState(readXMLNode(classNode, "state"));
					        xp.setZipcode(readXMLNode(classNode, "zipcode"));
					       continue;
						}
						if(classNode.getNodeName().equals("blj_filing"))
						{
							xp.setFiling_state(readXMLNode(classNode, "filing_state"));
							
							xp.setFiling_group(readXMLNode(classNode, "filing_group")); 
							xp.setFiling_group_desc(readXMLNode(classNode, "filing_group_desc"));
							
							xp.setFiling_type(readXMLNode(classNode, "filing_type"));
							
							xp.setFiling_type_desc(readXMLNode(classNode, "filing_type_desc")); 
							xp.setFiling_date(readXMLNode(classNode, "filing_date"));
							
							xp.setFiling_number(readXMLNode(classNode, "filing_number"));
							
							xp.setTax_lien_type_desc(readXMLNode(classNode, "tax_lien_type")); 
							xp.setTax_lien_type(readXMLNode(classNode, "tax_lien_type_desc")); 
							xp.setBankruptcy_type(readXMLNode(classNode, "bankruptcy_type")); 
							xp.setBankruptcy_type_desc(readXMLNode(classNode, "bankruptcy_type_desc")); 
							xp.setDocket_number(readXMLNode(classNode, "docket_number"));
							
							xp.setUnlawful_detainer(readXMLNode(classNode, "unlawful_detainer"));
							
							xp.setInitial_date(readXMLNode(classNode, "initial_date")); 
							xp.setInitial_amount(readXMLNode(classNode, "initial_amount")); 
							xp.setInitial_docket(readXMLNode(classNode, "initial_docket")); 
							xp.setJudgement_date(readXMLNode(classNode, "judgement_date")); 
							xp.setJudgement_amount(readXMLNode(classNode, "judgement_amount")); 
							xp.setJudgement_docket(readXMLNode(classNode, "judgement_docket")); 
							xp.setRemove_date(readXMLNode(classNode, "remove_date")); 
							xp.setRemove_docket(readXMLNode(classNode, "remove_docket")); 
							xp.setDismissal_date(readXMLNode(classNode, "dismissal_date")); 
							xp.setDismissal_docket(readXMLNode(classNode, "dismissal_docket")); 
							
							xp.setAsset_amount(readXMLNode(classNode, "asset_amount"));
							
							xp.setLiability_amount(readXMLNode(classNode, "liability_amount"));
							
							xp.setPlaintiff(readXMLNode(classNode, "plaintiff"));
							xp.setBeneficiary(readXMLNode(classNode, "beneficiary")); 
							xp.setSitus(readXMLNode(classNode, "situs")); 
							xp.setTrustee(readXMLNode(classNode, "trustee")); 
							xp.setCounty_code(readXMLNode(classNode, "county_code")); 
							xp.setCounty_name(readXMLNode(classNode, "county_name")); 
							xp.setCounty_state(readXMLNode(classNode, "county_state"));
							
							xp.setCourt_code(readXMLNode(classNode, "court_code"));
							xp.setCourt_desc(readXMLNode(classNode, "court_desc")); 
							xp.setCourt_state(readXMLNode(classNode, "court_state"));
							//Udhay-Aug8-New Tracers XML fileds
							xp.setCourt_address(readXMLNode(classNode, "court_address"));
							xp.setCourt_city(readXMLNode(classNode, "court_city"));							
							xp.setCourt_zip(readXMLNode(classNode, "court_zip"));
							xp.setCourt_phone(readXMLNode(classNode, "court_phone"));

							xp.setAssets_available(readXMLNode(classNode, "assets_available"));
							
							xp.setPerfected_date(readXMLNode(classNode, "perfected_date")); 
							xp.setAction_state_code(readXMLNode(classNode, "action_state_code"));
							
							xp.setAction_desc(readXMLNode(classNode, "action_desc"));
							
							xp.setDisposition_state_code(readXMLNode(classNode, "disposition_state_code")); 
							xp.setDisposition_desc(readXMLNode(classNode, "disposition_desc")); 
							xp.setAmount(readXMLNode(classNode, "amount")); 
							xp.setRelease_date(readXMLNode(classNode, "release_date"));
							
							xp.setRelease_number(readXMLNode(classNode, "release_number")); 
							xp.setSuit_case_number(readXMLNode(classNode, "suit_case_number"));
							
							xp.setSuit_date(readXMLNode(classNode, "suit_date")); 
							xp.setSuit_amount(readXMLNode(classNode, "suit_amount")); 
							xp.setSatisfaction_date(readXMLNode(classNode, "satisfaction_date")); 
							xp.setDischarge_date(readXMLNode(classNode, "discharge_date")); 
							xp.setClosed_date(readXMLNode(classNode, "closed_date")); 
							xp.setTrust_deed_number(readXMLNode(classNode, "trust_deed_number")); 
							xp.setTrust_deed_date(readXMLNode(classNode, "trust_deed_date")); 
							xp.setSale_number(readXMLNode(classNode, "sale_number")); 
							xp.setSale_date(readXMLNode(classNode, "sale_date")); 
							xp.setCancellation_number(readXMLNode(classNode, "cancellation_number")); 
							xp.setCancellation_date(readXMLNode(classNode, "cancellation_date")); 
							xp.setSched_341_date(readXMLNode(classNode, "sched_341_date"));
							
							xp.setUpdate_date(readXMLNode(classNode, "update_date"));
							//
							 xp.setSched_341_time(readXMLNode(classNode, "sched_341_time"));
					          xp.setJudge(readXMLNode(classNode, "judge"));
					          xp.setLawfirm(readXMLNode(classNode, "lawfirm"));
					          xp.setBook(readXMLNode(classNode, "book"));
					          xp.setPage(readXMLNode(classNode, "page"));
					          xp.setOrigdept(readXMLNode(classNode, "origdept"));
					          xp.setOrigcase(readXMLNode(classNode, "origcase"));
					          xp.setOrigbook(readXMLNode(classNode, "origbook"));
					          xp.setOrigpage(readXMLNode(classNode, "origpage"));
					          xp.setAssoccode(readXMLNode(classNode, "assoccode"));
					          xp.setActiontype(readXMLNode(classNode, "actiontype"));
					          xp.setActiontypedesc(readXMLNode(classNode, "actiontypedesc"));
							continue;
							}
						
						
						}
					  NewBJLResults result=new NewBJLResults();
					  result.setUserSearchId(searchId);
					  result.setName_filing_state(xp.getName_filing_state());
			          result.setFirstname(xp.getFirstname());
			          result.setMiddlename(xp.getMiddlename());
			          result.setLastname(xp.getLastname());
			          result.setName_suffix(xp.getName_suffix());
			          result.setName_type(xp.getName_type());
			          result.setName_type_desc(xp.getName_type_desc());
			          result.setSsn_taxid_flag(xp.getSsn_taxid_flag());
			          result.setSsn(xp.getSsn());
			          result.setComment_sequence(xp.getComment_sequence());
			          result.setComment_type(xp.getComment_type());
			          result.setComment_type_desc(xp.getComment_type_desc());
			          result.setHouse_number(xp.getHouse_number());
			          result.setStreet_direction(xp.getStreet_direction());
			          result.setStreet_name(xp.getStreet_name());
			          result.setStreet_suffix(xp.getStreet_suffix());
			          result.setApartment_num(xp.getApartment_num());
			          result.setCity(xp.getCity());
			          result.setState(xp.getState());
			          result.setZipcode(xp.getZipcode());
			          result.setFiling_state(xp.getFiling_state());
			          result.setFiling_group(xp.getFiling_group());
			          result.setFiling_group_desc(xp.getFiling_group_desc());
			          result.setFiling_type(xp.getFiling_type());
			          result.setFiling_type_desc(xp.getFiling_type_desc());
			          result.setFiling_date(xp.getFiling_date());
			          result.setFiling_number(xp.getFiling_number());
			          result.setTax_lien_type(xp.getTax_lien_type());
			          result.setTax_lien_type_desc(xp.getTax_lien_type_desc());
			          result.setBankruptcy_type(xp.getBankruptcy_type());
			          result.setBankruptcy_type_desc(xp.getBankruptcy_type_desc());
			          if(xp.getDocket_number().isEmpty())
			          {
			          result.setDocket_number("UNKNOWN");
			          }
			          else
			          {
			        	  result.setDocket_number(xp.getDocket_number());  
			          }
			          result.setUnlawful_detainer(xp.getUnlawful_detainer());
			          result.setInitial_date(xp.getInitial_date());
			          result.setInitial_amount(xp.getInitial_amount());
			          result.setInitial_docket(xp.getInitial_docket());
			          
			          result.setJudgement_date(xp.getJudgement_date());
			          result.setJudgement_amount(xp.getJudgement_amount());
			          result.setJudgement_docket(xp.getJudgement_docket());
			          result.setRemove_date(xp.getRemove_date());
			          result.setRemove_docket(xp.getRemove_docket());
			          result.setDismissal_date(xp.getDismissal_date());
			          result.setDismissal_docket(xp.getDismissal_docket());
			          result.setAsset_amount(xp.getAsset_amount());
			          
			          result.setLiability_amount(xp.getLiability_amount());
			          
			          result.setPlaintiff(xp.getPlaintiff());
			          result.setBeneficiary(xp.getBeneficiary());
			          result.setSitus(xp.getSitus());
			          result.setTrustee(xp.getTrustee());
			          result.setCounty_code(xp.getCounty_code());
			          result.setCounty_name(xp.getCounty_name());
			          result.setCounty_state(xp.getCounty_state());
			          
			          result.setCourt_code(xp.getCourt_code());
			          result.setCourt_desc(xp.getCourt_desc());
			          result.setCourt_state(xp.getCourt_state());
			          result.setAssets_available(xp.getAssets_available());
			          result.setPerfected_date(xp.getPerfected_date());
			          result.setAction_state_code(xp.getAction_state_code());
			          result.setAction_desc(xp.getAction_desc());
			          result.setDisposition_state_code(xp.getDisposition_state_code());
			          
			          result.setDisposition_desc(xp.getDisposition_desc());
			         
			          result.setAmount(xp.getAmount());
			          result.setRelease_date(xp.getRelease_date());
			          result.setRelease_number(xp.getRelease_number());
			          result.setSuit_case_number(xp.getSuit_case_number());
			          result.setSuit_date(xp.getSuit_date());
			          result.setSuit_amount(xp.getSuit_amount());
			          result.setSatisfaction_date(xp.getSatisfaction_date());
			          
			          result.setDischarge_date(xp.getDischarge_date());
			          result.setClosed_date(xp.getClosed_date());
			          result.setTrust_deed_number(xp.getTrust_deed_number());
			          result.setTrust_deed_date(xp.getTrust_deed_date());
			          result.setSale_number(xp.getSale_number());
			          result.setSale_date(xp.getSale_date());
			          result.setCancellation_number(xp.getCancellation_number());
			          result.setCancellation_date(xp.getCancellation_date());
			          result.setSched_341_date(xp.getSched_341_date());
			          result.setUpdate_date(xp.getUpdate_date());

			          //Udhay Aug8 ==> new Tracers fileds addedd
			          result.setFullname(xp.getFullname());
			          result.setBusinessname(xp.getBusinessname());
			          result.setDob(xp.getDob());
			          result.setCourt_address(xp.getCourt_address());
			          result.setCourt_city(xp.getCourt_city());
			          result.setCourt_zip(xp.getCourt_zip());
			          result.setCourt_phone(xp.getCourt_phone());
			          //
			          result.setSched_341_time(xp.getSched_341_time());
			          result.setJudge(xp.getJudge());
			          result.setLawfirm(xp.getLawfirm());
			          result.setBook(xp.getBook());
			          result.setPage(xp.getPage());
			          result.setOrigdept(xp.getOrigdept());
			          result.setOrigcase(xp.getOrigcase());
			          result.setOrigbook(xp.getOrigbook());
			          result.setOrigpage(xp.getOrigpage());
			          result.setAssoccode(xp.getAssoccode());
			          result.setActiontype(xp.getActiontype());
			          result.setActiontypedesc(xp.getActiontypedesc());
			          
			          //logger.info("BJL_New_Fields_Data@@@@: " + xp.getFullname()+" "+xp.getBusinessname());
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
				if ((totalMatches == 0) || (rowsReturned == 0)) 
				{
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
