package springapp.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory; 

import net.searchsystems.limestone.*; 
import org.apache.log4j.Logger;
import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
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
import springapp.domain.CriminalSSN.CriminalSSNRequestBean;
import springapp.domain.CriminalSSN.CriminalSSNResponseBean;  
import springapp.repository.CriminalSSNRequestDao;
import springapp.repository.CriminalSSNResponseDao; 
import springapp.web.funnel.NewSearchForm.SearchFormCommand;

/**
 * Provides support for querying the Criminal Records interface.
 */
@Service("bgcSSNSearchManager")
public class SpringSSNSearchManager implements ResourceLoaderAware {
	
	protected static ArrayList<BjlDataSources> bjlSources;

	public static final String bgcPostUrlTest = "https://model.backgroundchecks.com/integration/bgcdirectpost2.aspx";
	public static final String bgcPostUrlLive = "https://direct.backgroundchecks.com/integration/bgcdirectpost2.aspx";
	

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
	protected CriminalSSNRequestDao requestDao;
	@Autowired
	protected CriminalSSNResponseDao responseDao;
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
		return getCost("SSN Search", subCategory);
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
		final String bjlSearchType = "Criminal SSN";
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
	
 
	public long queryFunnel(Integer userId,  Integer transactionId, 
			 HttpSession session,SearchFormCommand sfc) {
		return this.querySSN(userId, transactionId, session,sfc);
	}
	
	/**
	 * Query the RealProperty interface and store the results in the database.
	 * 
	 * @throws SearchException
	 *             If the SOAP request failed for any reason.
	 */
	public long querySSN(Integer userId, Integer transactionId,
			HttpSession session,SearchFormCommand sfc) throws SearchException {
		/*
		 * step 1: set up the httppost request
		 * 
		 */
       // by Udhay	
		
		 long searchId = 0;
		 int searchStatusId=0;
         Integer totalMatches = 0;
         Integer rowsReturned = 0;
         StringBuffer sb=new StringBuffer();
		 
         try 
         {
		
			String userID = "searchsystems";
			String password = "J@$0n11";
			String lastname=sfc.getBgcLastName();
			String firstname=sfc.getBgcFirstName();
			String middlename=sfc.getBgcMiddleInitial();
			String dob= sfc.getBgcDobMonth()+"/"+sfc.getBgcDobDay()+"/"+sfc.getBgcDobYear(); logger.info("dob log Yearrr"+sfc.getBgcDobYear());
			String ssn=sfc.getBgcSsn(); 
			
			 String xml="";
					 xml="<BGC version='4.5'>  <login>";				
					 xml=xml+"<user>"+userID+"</user>";
					 xml=xml+"<password>"+password+"</password><account>10009807</account></login>";
					 xml=xml+"<product>    <USAliasSearch version='1'><order>";
				 	 xml=xml+"<firstName>"+firstname+"</firstName>";
					 xml=xml+"<middleName>"+ middlename +"</middleName>";
					 xml=xml+"<lastName>"+lastname+"</lastName>"; 
					 xml=xml+"<DOB><month>"+ sfc.getBgcDobMonth() +"</month><day>"+sfc.getBgcDobDay() +"</day><year>"+sfc.getBgcDobYear()+"</year></DOB>";
					 xml=xml+"<SSN>"+ssn+"</SSN>";
					 xml=xml+"<certifications><jurisdiction>"+sfc.getBgcState()+"</jurisdiction></certifications></order>";
					 xml=xml+" <custom><options><doValidation>YES</doValidation><checkDMI>YES</checkDMI>";
					 xml=xml+"<includeSources>NO</includeSources><checkDMI>YES</checkDMI>";
					 xml=xml+"<includeSources>NO</includeSources> </options>";
					 xml=xml+"</custom></USAliasSearch></product></BGC>";					 
			
					 CriminalSSNRequestBean ssnReqstBean = new CriminalSSNRequestBean() ;
					 searchId = ssnReqstBean.getUserSearchId();
					 
					logger.info("SSN-->request xml"+xml);			 	    
			 		File fXmlFile = new File("C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/springapp/src/xmlToRead/USAlias.xml");
			    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			    	Document doc = dBuilder.parse(fXmlFile);
			    	doc.getDocumentElement().normalize(); 
			    	 
				 	/*
				 	HttpClient client=new HttpClient();
					PostMethod post=new PostMethod(bgcPostUrlTest);
				 	post.setParameter("request", xml);
				 	
				 	int returncode=client.executeMethod(post);
					logger.info("Return Code:"+returncode);
			        BufferedReader  br=new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));
			        String readline=null;
			        while((readline=br.readLine()) != null)
			         {
			        	sb.append(readline+"\n"); 
			         }
			         */
			    	CriminalSSNRequestBean ssnRequstBean = new CriminalSSNRequestBean();
			    	
			    	ssnReqstBean.setSearchCategoryId(SEARCH_CATEGORY_ID);		
					ssnReqstBean.setSearchSubCategoryId(201); 				
					ssnReqstBean.setUserId(userId);			
					ssnReqstBean.setTransactionId(transactionId);
					ssnReqstBean.setMatchCount(rowsReturned.shortValue());
					ssnReqstBean.setCreateDate(Calendar.getInstance().getTime());					
					ssnReqstBean.setCompleted(Boolean.TRUE);
					ssnReqstBean.setSearchStatusId(searchStatusId);
					ssnReqstBean.setInvoiced(Boolean.TRUE);
					
			    	ssnReqstBean.setFirstName(firstname);
			    	ssnReqstBean.setLastName(lastname);
			    	ssnReqstBean.setMiddlename(middlename);
			    	ssnReqstBean.setState(sfc.getBgcState());
			    	ssnReqstBean.setDob(dob);
			    	ssnReqstBean.setSsn(ssn);
			    	requestDao.save(ssnReqstBean);
			    	
			    	logger.info("XML DOC info --> "+ doc.getDocumentElement().getNodeName());
			    	Element docEle = doc.getDocumentElement();			    	
			    	NodeList nodes = doc.getElementsByTagName("traceDetail");
			    	
			    	logger.info("XML Nodes sizee --> "+ nodes.getLength());
			    	
			    	List<CriminalSSNResponseBean> lstSSNResult = new ArrayList<CriminalSSNResponseBean>();
			    	
			    	
			    	for(int i = 0 ; i < nodes.getLength();i++) 
					{
			    		CriminalSSNResponseBean sRespons = new CriminalSSNResponseBean();
						//Node node = nodList.item(i);
						Element element = (Element) nodes.item(i);
						
						NodeList firstName = element.getElementsByTagName("firstName");
						Element eleFname = (Element) firstName.item(0);
						sRespons.setFirstname(eleFname.getFirstChild().getNodeValue());
						//logger.info("Employee Fname : "    + eleFname.getFirstChild().getNodeValue());
						
						NodeList middleName = element.getElementsByTagName("middleName");
						Element eleMidname = (Element) middleName.item(0);
						if(eleMidname.getFirstChild()!=null)
						{
							sRespons.setMiddlename(eleMidname.getFirstChild().getNodeValue());						
							//logger.info("Employee middlenam : " + eleMidname.getFirstChild().getNodeValue());
						}
						NodeList lastName = element.getElementsByTagName("lastName");
						Element eleLname = (Element) lastName.item(0);
						sRespons.setLastname(eleLname.getFirstChild().getNodeValue());
						//
						NodeList strtNum = element.getElementsByTagName("streetNumber");
						Element eleStrtNum = (Element) strtNum.item(0);
						if(eleStrtNum.getFirstChild()!=null)
						{
							sRespons.setStreetnumber(eleStrtNum.getFirstChild().getNodeValue());
						}
						//
						NodeList strtName = element.getElementsByTagName("streetName");
						Element eleStrtNam = (Element) strtName.item(0);
						if(eleStrtNam.getFirstChild()!=null)
						{
							sRespons.setStreetname(eleStrtNam.getFirstChild().getNodeValue());
						}	
						//
						NodeList strtcity = element.getElementsByTagName("city");
						Element eleCity = (Element) strtcity.item(0);
						if(eleCity.getFirstChild()!=null)
						{
							sRespons.setCity(eleCity.getFirstChild().getNodeValue());
						}
						NodeList strtstate = element.getElementsByTagName("state");
						Element elestate = (Element) strtstate.item(0);
						if(elestate.getFirstChild()!=null)
						{
							sRespons.setState(elestate.getFirstChild().getNodeValue());
						}
						NodeList strtcounty = element.getElementsByTagName("county");
						Element eleCounty = (Element) strtcounty.item(0);
						if(eleCounty.getFirstChild()!=null)
						{
							sRespons.setCounty(eleCounty.getFirstChild().getNodeValue());
						}
						NodeList postalCode = element.getElementsByTagName("postalCode");
						Element elePostalCode = (Element) postalCode.item(0);
						if(elePostalCode.getFirstChild()!=null)
						{
							sRespons.setPostalcode(elePostalCode.getFirstChild().getNodeValue());
						}
						NodeList phoneInfo = element.getElementsByTagName("phoneInfo");
						Element elePhoneInfo = (Element) phoneInfo.item(0);
						if(elePhoneInfo.getFirstChild()!=null)
						{
							sRespons.setPhoneinfo(elePhoneInfo.getFirstChild().getNodeValue());
						}
						
						/*
						//offender
						NodeList nodOffender = doc.getElementsByTagName("offender");
				    	for(int j = 0 ; j < nodOffender.getLength();j++) 
						{
				    		Element eOffndr = (Element) nodes.item(i);							
							NodeList firstName = element.getElementsByTagName("firstName");
							Element eleFname = (Element) firstName.item(0);
							sRespons.setFirstname(eleFname.getFirstChild().getNodeValue());
							logger.info("Employee Fname : "    + eleFname.getFirstChild().getNodeValue());
						}
						
						*/

						
						lstSSNResult.add(sRespons);
						//responseDao.saveResponse(sRespons);
					}
			    	 logger.info("List count"+lstSSNResult.size());
			    	session.setAttribute("SSNResults", lstSSNResult);					
						
	         /*
	 		 *  step 3: parse response
	 		 *  
	 		 */		
		    
	    } // Try ends
         catch(Exception ex)
         {
        	 logger.info("352--> exptn err:;"+ ex.getMessage()+"st"+ ex.getStackTrace() +"caus"+ ex.getMessage());
         }
         
		return searchId;
		
	}
	


	
}