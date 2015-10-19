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


import java.util.TreeSet;

import javax.jms.Session;
import javax.servlet.http.HttpSession;
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
import org.hsqldb.lib.HashSet;
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

import springapp.domain.NewBJLResultsPeer;
import springapp.domain.RealPropertyXMLParser;
import springapp.domain.RealProperty.RealPropRequestBean;
import springapp.domain.RealProperty.RealPropResponseBean;
import springapp.manager.SearchException;
import springapp.repository.NssRequestDao;
import springapp.repository.RealPropRequestDao;
import springapp.repository.RealPropResponseDao;


/**
 * Provides support for querying the Real Property interface.
 */
@Service("realpropSearchManager")
public class SpringPropertySearchManager implements ResourceLoaderAware {
	protected static ArrayList<BjlDataSources> bjlSources;

	public final static String liveUrl="https://www.tracersinfo.com/xml.php";

	public static final short MAX_RESULTS = 20;
	
	protected static final Short SEARCH_CATEGORY_ID = 66;
	
	protected static final Short STATUS_FOUND_RESULTS = 1;
	protected static final Short STATUS_NO_RESULTS = 1;
	protected static final Short STATUS_TOO_MANY_RESULTS = 1;
	
	protected final Logger logger = Logger.getLogger(getClass());

	// this is necessary to force torque to load before init() gets called
	// can we move this to an event listener in torque mgr?
//	@Autowired
//	protected TorqueManager torqueManager;
	@Autowired
	protected RealPropRequestDao requestDao;
	@Autowired
	protected RealPropResponseDao responseDao;
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
		return getCost("RealProp Search", subCategory);
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
			String rpFirstname,String rpLastname,String rpBusinessnmae,String rpappartnum,String rpStreet,
			String rpcity,String rpState, String rpReference,HttpSession session) {
		return this.query(userId, searchSubCategoryId, transactionId,rpFirstname, rpLastname, rpBusinessnmae,rpappartnum,rpStreet,rpcity,rpState, rpReference,session);
	}
	
	/**
	 * Query the RealProperty interface and store the results in the database.
	 * 
	 * @throws SearchException
	 *             If the SOAP request failed for any reason.
	 */
	public long query(Integer userId, Integer searchSubCategoryId, Integer transactionId,
			String rpFirstname,String rpLastname,String rpBusinessnmae,String rpappartnum,String rpStreet,
			String rpcity,String rpState, String rpReference,HttpSession session) throws SearchException {
		/*
		 * step 1: set up the httppost request
		 * 
		 */
       //author by Udhay	
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
			String appartnum="";
			String street="";
			String state="";
			String city="";
			String reference="";
			lastname=rpLastname;
			firstname= rpFirstname;
			appartnum=rpappartnum;
			street=rpStreet;
			city=rpcity;
			state=rpState;
			reference=rpReference;
			//logger.info("RealProp_State"+ rpState);
			//logger.info("RealProp_Reference"+ rpReference);
			if(rpcity != null)
			{
				city=rpcity;
			}
			if(rpReference != null)
			{
				reference = rpReference;
			}
			
			 String xml="";
			//if(firstname != null && lastname != null)
			{
				 xml="<?xml version='1.0'?>";
				 xml=xml+"<xmlgw>";
				 xml=xml+"<session>";
				 xml=xml+"<user_id>"+userID+"</user_id>";
				 xml=xml+"<password>"+password+"</password>";
				 xml=xml+"<customer_reference>"+reference+"</customer_reference>";
				 xml=xml+"</session>"; 
				 xml=xml+"<search_options/>"; 
				 xml=xml+"<search_request>";
				 xml=xml+"<search>"+"REALPROP"+"</search>";
				// xml=xml+"<search_identifier></search_identifier>";
				 xml=xml+"<search_criteria>";
				 if(session.getAttribute("RPNameSearch") != null)
				 {
					 xml=xml+"<last>"+lastname+"</last>";
					 xml=xml+"<first>"+firstname+"</first>";
					 xml=xml+"<state>"+state+"</state>";
					 session.removeAttribute("RPAdresSearch");
				 }
				 if(session.getAttribute("RPAdresSearch") != null)
				 {
					 lastname="NULL";
					 firstname= "NULL";
					
					 xml=xml+"<num>"+appartnum+"</num>";
					 xml=xml+"<street>"+street+"</street>";
					 xml=xml+"<city>"+city+"</city>";
					 xml=xml+"<state>"+state+"</state>";
					 session.removeAttribute("RPNameSearch");
				 }
				 xml=xml+"</search_criteria>";
				 xml=xml+"</search_request>";
				 xml=xml+"</xmlgw>";		 
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
				//logger.info("Response XML_"+ xmlResponse);
				
			 
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
	      		         
		    	NodeList rowList=doc.getElementsByTagName("search_response");
		    	Node headNode =(Node) rowList.item(0);		    	
		    	rowsReturned=Integer.parseInt(readXMLNode(headNode, "rows_returned"));
		    	logger.info("Rows Returnd:"+rowsReturned);
		    	
		    	
		    	if(rowsReturned.equals("-1"))
		    	{
		    		String reasonstring=readXMLNode(headNode, "response_data");
					//logger.info("Reason Text:"+reasonstring);
		    	}
		    		nList=doc.getElementsByTagName("response_row");
		    		totalMatches=nList.getLength();
		    		//logger.info("Response_Length_response_row:"+nList.getLength());
		    		
			if(rowsReturned >0 && totalMatches > 0 )
			{
					session.removeAttribute("RPResults");
					//RealPropSearches searchdata = new RealPropSearches();
					RealPropRequestBean rpRequstbean = new RealPropRequestBean();
					// create a new transaction conn.setAutoCommit(false);
					// add search to database
					//Saving the Request Values to DB Ends Here	
					rpRequstbean.setSearchCategoryId(SEARCH_CATEGORY_ID);		
					rpRequstbean.setSearchSubCategoryId(searchSubCategoryId.shortValue()); 				
					rpRequstbean.setUserId(userId);			
					rpRequstbean.setTransactionId(transactionId);
					rpRequstbean.setMatchCount(rowsReturned.shortValue());
					rpRequstbean.setCreateDate(Calendar.getInstance().getTime());					
					rpRequstbean.setCompleted(Boolean.TRUE);
					rpRequstbean.setSearchStatusId(searchStatusId);
					rpRequstbean.setInvoiced(Boolean.TRUE);
					// Real Property fields
					rpRequstbean.setFirstName(firstname);
					rpRequstbean.setLastName(lastname);
					rpRequstbean.setAppartNum(appartnum);
					rpRequstbean.setStreet(street);
					rpRequstbean.setCity(city);
					rpRequstbean.setState(state);
					if(reference!=null)
						rpRequstbean.setReference(reference);
				
					try
					{
						requestDao.save(rpRequstbean);
					}
					catch(Exception e)
					{
						logger.info("RP_Request_Data_Save:::" + e.getMessage());
					}
					
					searchId = rpRequstbean.getUserSearchId();
					session.setAttribute("RPRequestData", rpRequstbean);
					//logger.info("RealProp_Hybrnt_ReqstSAVE:::"+searchId);			
							
							
					//logger.info("RealProp_adding results");
							
					RealPropertyXMLParser rp=new RealPropertyXMLParser();
					List<RealPropResponseBean> rpResultList = new ArrayList<RealPropResponseBean>();					
					
					for(int i=0;i<nList.getLength();i++)
					{				
						
						Node classNode = nList.item(i);				
						rp.setHouseno(readXMLNode(classNode, "houseNo"));
						rp.setStreet(readXMLNode(classNode, "street"));
						rp.setCity(readXMLNode(classNode, "city"));
						rp.setState(readXMLNode(classNode, "state"));
						rp.setPhoneno(readXMLNode(classNode, "phoneNo"));
						rp.setApn(readXMLNode(classNode, "apn"));
						rp.setFipscode(readXMLNode(classNode, "fipsCode"));
						rp.setMunicipalcode(readXMLNode(classNode, "municipalCode"));
						rp.setAccountnumber(readXMLNode(classNode, "accountNumber"));
						rp.setPrimaryowner(readXMLNode(classNode, "primaryOwner"));
						rp.setSecondaryowner(readXMLNode(classNode, "secondaryOwner"));								
						rp.setHousenosuffix(readXMLNode(classNode, "houseNoSuffix"));
						rp.setDirection(readXMLNode(classNode, "direction"));
						rp.setMode(readXMLNode(classNode, "mode"));
						rp.setAptno(readXMLNode(classNode, "aptNo"));
						rp.setZip(readXMLNode(classNode, "zip"));
						rp.setOwnerhouseno(readXMLNode(classNode, "ownerHouseNo"));
						rp.setOwnerdirection(readXMLNode(classNode, "ownerDirection"));
						rp.setOwnerstreet(readXMLNode(classNode, "ownerStreet"));
						rp.setOwnermode(readXMLNode(classNode, "ownerMode"));
						rp.setOwnercity(readXMLNode(classNode, "ownerCity"));								
						rp.setOwnerstate(readXMLNode(classNode, "ownerState"));
						rp.setOwneraptno(readXMLNode(classNode, "ownerAptNo"));
						rp.setOwnerzip(readXMLNode(classNode, "ownerZip"));
						rp.setSaleprice(readXMLNode(classNode, "salePrice"));
						rp.setDaterecorded(readXMLNode(classNode, "dateRecorded"));
						rp.setSaledate(readXMLNode(classNode, "saleDate"));
						rp.setCalculatedvalue(readXMLNode(classNode, "calculatedValue"));
						
						
							RealPropResponseBean rpResult = new RealPropResponseBean();
							  //RealPropResults result = new RealPropResults();
							  rpResult.setUser_search_id(searchId);
							  rpResult.setHouseno(rp.getHouseno());
					          rpResult.setStreet(rp.getStreet());
					          rpResult.setCity(rp.getCity());
					          rpResult.setState(rp.getState());
					          rpResult.setPhoneno(rp.getPhoneno());
					          rpResult.setApn(rp.getApn());
					          rpResult.setFipscode(rp.getFipscode());
					          rpResult.setMunicipalcode(rp.getMunicipalcode());
					          rpResult.setAccountnumber(rp.getAccountnumber());
					          rpResult.setPrimaryowner(rp.getPrimaryowner());
					          rpResult.setSecondaryowner(rp.getSecondaryowner());
					          rpResult.setHousenosuffix(rp.getHousenosuffix());
					          rpResult.setDirection(rp.getDirection());
					          rpResult.setMode(rp.getMode());
					          rpResult.setAptno(rp.getAptno());
					          rpResult.setZip(rp.getZip());
					          rpResult.setOwnerhouseno(rp.getOwnerhouseno());
					          rpResult.setOwnerdirection(rp.getOwnerdirection());
					          rpResult.setOwnerstreet(rp.getOwnerstreet());
					          rpResult.setOwnermode(rp.getOwnermode());
					          rpResult.setOwnercity(rp.getOwnercity());
					          rpResult.setOwnerstate(rp.getOwnerstate());
					          rpResult.setOwneraptno(rp.getOwneraptno());
					          rpResult.setOwnerzip(rp.getOwnerzip());
					          rpResult.setSaleprice(rp.getSaleprice());
					          rpResult.setDaterecorded(rp.getDaterecorded());
					          rpResult.setSaledate(rp.getSaledate());
					          rpResult.setCalculatedvalue(rp.getCalculatedvalue());				          
					          
					          rpResultList.add(rpResult);
					          responseDao.saveResponse(rpResult);
					         
					         
						}
						session.setAttribute("RPResults", rpResultList);
						
					} //totalMatches If Ends
					else
			         {
			        	 searchStatusId=STATUS_NO_RESULTS;
			        	 totalMatches=0;
			        	
			         }
			 
					//logger.info("Finaly_search status id: " + searchStatusId);
	
					if ((totalMatches == 0) || (rowsReturned == 0)) {
						logger.info("search successful but returned no results, continuing");
				
					}
		    }//xmlResponse IF Ends 
	    } // Try ends
		 catch (IOException e) {
			// TODO Auto-generated catch block
			 logger.info("RealProp_Exceptn_IO" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 logger.info("RealProp_Exceptn_   " + e.getMessage());
			
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
