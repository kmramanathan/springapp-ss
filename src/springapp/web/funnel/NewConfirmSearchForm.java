package springapp.web.funnel;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import net.searchsystems.limestone.bean.BGCResponseBean;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.torque.TorqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import springapp.crypt.Crypt;
import springapp.domain.AdminUserIPBean;
import springapp.domain.CreditCard;
import springapp.domain.Transaction;
import springapp.domain.User;
import springapp.domain.NSS.NssResponseBean;
import springapp.domain.corporation.CorporationSearches;
import springapp.manager.SearchException;
import springapp.manager.SpringAliasSearchManager;
import springapp.manager.SpringBGCSearchManager;
import springapp.manager.SpringBJLSearchManager;
import springapp.manager.SpringCorporationSearchManager;
import springapp.manager.SpringDDNCriminalManager;
import springapp.manager.SpringEvictionSearchManager;
import springapp.manager.SpringNSSSearchManager;
import springapp.manager.SpringPropertySearchManager;
import springapp.repository.AdminUserIPDao;
import springapp.service.billing.BillingManager;
import springapp.service.billing.BillingManager.TxnType;
import springapp.service.user.UserManagerException;
import springapp.service.user.UserManagerException.Type;
import springapp.web.funnel.DDNCriminalSearchForm.CriminalSearchFormCommand;
import springapp.web.funnel.NewAliasSearchForm.AliasSearchFormCommand;
import springapp.web.funnel.NewBJLSearchForm.BJLSearchFormCommand;
import springapp.web.funnel.NewCorpBusSearchForm.CorpBusSearchFormCommand;
import springapp.web.funnel.NewCorpIndSearchForm.CorpIndSearchFormCommand;
import springapp.web.funnel.NewEvictionBusinessSearchForm.EvictionBusinessSearchFormCommand;
import springapp.web.funnel.NewEvictionSearchForm.EvictionSearchFormCommand;
import springapp.web.funnel.NewNationSecurityForm.NationSearchFormCommand;
import springapp.web.funnel.NewSearchForm.SearchFormCommand;
import springapp.web.funnel.PropertyAddressSearchForm.RealPropAddressSearchFormCommand;
import springapp.web.funnel.PropertySearchForm.RealPropSearchFormCommand;
import springapp.web.funnel.RegisterForm.RegisterFormCommand;
//import springapp.manager.SpringSSNSearchManager;


@Controller

/**
 * Final step in running a one-shot funnel search. Shows the user the
 * search options and prompts for confirmation. 
 * 
 * Upon confirmation, this class runs the search, sets up session info, and
 * redirects to the results display page.
 */ 
public class NewConfirmSearchForm extends AbstractFunnelController {
	@Autowired
	private BillingManager billingManager;
	@Autowired
	private SpringBGCSearchManager bgcManager;
	@Autowired
	private SpringAliasSearchManager aliasSearchManager;
	@Autowired
	private SpringBJLSearchManager bjlManager;
	@Autowired
	private SpringEvictionSearchManager evictionManager;
	@Autowired
	private SpringPropertySearchManager realpropManager;
	@Autowired
	protected SpringCorporationSearchManager corpManager;
	@Autowired
	protected SpringNSSSearchManager nssManager;	
	@Autowired
	private SpringDDNCriminalManager ddnCriminalManager;
	//@Autowired
	//private SpringSSNSearchManager bgcSSNSearchManager;

	
	@Autowired
	private AdminUserIPDao userIPDao ;
	 
	protected static final Integer pageSizes[] = {10, 25, 50};
	
	private String getSearchTypeString(SearchFormCommand searchfc) {
		String searchType;
		if (searchfc.getNationwideSearch()) {
			searchType = "Criminal Search Nationwide";
		} else {
			searchType = "Criminal Search in State (" + searchfc.getBgcState() + ")";
		}
		return searchType;
	}
	
	private String getSearchTypeString(AliasSearchFormCommand searchfc) {
		String searchType;
		if (searchfc.getNationwideSearch()) {
			searchType = "US Alias Search";
		} else {
			searchType = "Single State Alias Search";
		}
		return searchType;
	}
	
 	private String getSearchTypeString(BJLSearchFormCommand bjlsfc) {
		
		String searchType;
		switch (bjlsfc.getBjlSearchType()) {
		case INDIVIDUALNAME:
			searchType = "Individual Name";
			break;
		case BUSINESSNAME:
			searchType = "Business Name";
			break;
		case SSN:
			searchType = "SSN / Tax ID";
			break;
		default:
			searchType = "Unknown";		
		}
		return searchType;
	}
	
	
	private String getSearchEvictionType(EvictionSearchFormCommand esfc)
	{
		
		String searchType = null;
		
			searchType="Eviction - Individual";	
		
		
		return searchType;
		
	}
	private String getEvictionBusinessType(EvictionBusinessSearchFormCommand ebsfc)
	{
		String searchType = null;
			searchType="Eviction - Business";
		return searchType;
	}
	//Real Property
	private String getRealPropSearchType(RealPropSearchFormCommand rpfc)
	{
		
		String searchType = null;
		
			searchType="Real Property by Name";	
		
		
		return searchType;
		
	}
	//Real Property Address search
	private String getRealPropAddressSearchType(RealPropAddressSearchFormCommand rpafc)
	{
		
		String searchType = null;
		
			searchType="Real Property by Address";	
		
		
		return searchType;
		
	}
	
	private String getCorpIndType(CorpIndSearchFormCommand cisfc)
	{
		String searchType = null;
		if(cisfc.getNationwideSearch())
		{
			searchType="Corporation Individual Nationwide";
		}
		else {
			searchType="Corporation Individual State ("+ cisfc.getState() +")";
		}
		return searchType;
	}
	private String getCorpBusType(CorpBusSearchFormCommand cbsfc)
	{
		String searchType = null;
		if(cbsfc.getNationwideSearch())
		{
			searchType="Corporation Business Nationwide";
		}
		else {
			searchType="Corporation Business State ("+cbsfc.getState()+")";
		}
		
		return searchType;
	}
	private String getNationSecurityType(NationSearchFormCommand nsfc)
	{
		String searchType;
		searchType="National Security Search";
		return searchType;
		
	}
	private User generateUser(RegisterFormCommand signupfc) {
		User u = new User();
		
		u.setUsername(signupfc.getUsername());
		u.setFirstName(signupfc.getFirstName());
		u.setLastName(signupfc.getLastName());
		u.setEmail(signupfc.getEmail());
		
        u.setPassword(DigestUtils.md5Hex(signupfc.getPassword()));        
        u.setPassQuestionId(signupfc.getSecretQuestionId().shortValue());
        u.setPassAnswer(signupfc.getSecretQuestionAnswer());        
        u.setNewsletter(signupfc.getReceiveNewsletter());  
        
		u.setNoExport(true);
		u.setNoPremium(false);
		u.setSubDirectoryUser(true);
    		
		return u;
	}
	
	private CreditCard generateCC(RegisterFormCommand signupfc) {
		CreditCard cc = new CreditCard();
		
		cc.setName(signupfc.getName());
		cc.setCompany(signupfc.getCompany());
		cc.setAddress1(signupfc.getAddress());
		cc.setPhone(signupfc.getPhone());
		cc.setCity(signupfc.getCity());
		cc.setState(signupfc.getState());
		cc.setPostalCode(signupfc.getPostalCode());
		
		logger.info("countryCode: " + signupfc.getCountryCode());
		Integer cid = userManager.getCountryIdByCode(signupfc.getCountryCode());
		logger.info("countryId: " + cid);
		cc.setCountryId(cid.shortValue());
		
		//Short ccid=224;
		//cc.setCountryId(ccid);
		
		String ccNumber = signupfc.getCcNumber();
		String last4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
		
		cc.setNumber(Crypt.encrypt(ccNumber));
		cc.setLastDigits(Short.parseShort(last4));
		
		cc.setExpMonth(signupfc.getCcExpMonth().shortValue());
		cc.setExpYear(signupfc.getCcExpYear().shortValue());
		cc.setVerified(true);
		
		return cc;
	}	
	
	//Developed by Vivek on 23 Feb 2013
	@RequestMapping(value="/funnel/newAccConfirm.do", method=RequestMethod.GET)
	public String setAccConfForm(HttpSession session, ModelMap map,
			@RequestParam(value="pageSize",required=false) Integer pageSize)
	{
		if (pageSize == null) { pageSize = 50; }
		RegisterFormCommand rfc = (RegisterFormCommand) session.getAttribute("registerFormCommand");
		
		if(rfc == null)
		{
			return landingHome;
		}
		String username = rfc.getUsername();
		String firstname = rfc.getFirstName();
		String lastname = rfc.getLastName();
		String ccNumber =rfc.getCcNumber();
		String email=rfc.getEmail();
		String ccLast4=ccNumber.substring(ccNumber.length()-4, ccNumber.length());
		map.addAttribute("ccLast4", ccLast4);
		map.addAttribute("username", username);
		map.addAttribute("lastname", lastname);
		map.addAttribute("firstname", firstname);
		map.addAttribute("email", email);
		map.addAttribute("pageSizes", pageSizes);
		map.addAttribute("pageSize", pageSize);
		return newAccConfirm;
	}
	
	@RequestMapping(value="/funnel/newAccConfirm.do", method=RequestMethod.POST)
	public String postAccConfForm(HttpSession session, ModelMap map, SessionStatus status, HttpServletRequest request)
	{
		RegisterFormCommand rfc = (RegisterFormCommand) session.getAttribute("registerFormCommand");
		
		if(rfc == null)
		{
			return landingHome;
		}
		boolean member = false;
		if(session.getAttribute("username") != null)
		{
			member=true;
		}
		User u=new User();
		CreditCard cc=new CreditCard();
		//Checking for fraud user using Email 
		String strEmail = rfc.getEmail();
		String[] mailDomain;			 
		String delimiter = "@";
		mailDomain = strEmail.split(delimiter);
		if(mailDomain!=null && mailDomain.length > 0)
		{
			
			if (mailDomain[1].equals("gmail.com") || mailDomain[1].equals("yahoo.com") || mailDomain[1].equals("hotmail.com") || mailDomain[1].equals("ymail.com") || mailDomain[1].equals("live.com"))
			{
				//u.setInactive(true);				
			}
		}	
		
		if(rfc != null)
		{
			
			u=generateUser(rfc);
			cc=generateCC(rfc);
		}
		String sUserFullName = (u.getFirstName() + u.getLastName()).replaceAll(" ","").toUpperCase();
		String sUserCCName = (cc.getName().replaceAll(" ","")).toUpperCase();
		
			logger.info("User Name && CC name: "+ sUserFullName +"& CrdCard Name-->"+ sUserCCName);
		
		if(sUserCCName.equals(sUserFullName))
			u.setInactive(false);
		else
		{
			u.setInactive(true);
		}
		
		int userId=userManager.registerNewUser(u, cc, rfc.getTest());				 
		Transaction t=new Transaction();
		int invoiceNumber = 101;
		String description = "Credit Card Verification";
		
		short category=3;
		short subCategory=10;
		BigDecimal amount=new BigDecimal(1.00);
		String sReturnPage;
		//Udhay on Jul 26 2014
		//Below mathod before we implementing VOID transaction. 
		//Our new Auth_Only transaction with Void method is implemented below.
		
		//t = billingManager.runTransactionWithCvv(u, cc, rfc.getCcAuthCode(), amount, TxnType.AUTH_ONLY, description, invoiceNumber, category, subCategory, true);
		AdminUserIPBean userIP = new AdminUserIPBean();
		String ipAddress = getClientIpAddr(request);		   
		
		if(mailDomain!=null && mailDomain.length > 0)
		{			
			if (mailDomain[1].equals("gmail.com") || mailDomain[1].equals("yahoo.com") || mailDomain[1].equals("hotmail.com") || mailDomain[1].equals("ymail.com") || mailDomain[1].equals("live.com"))
			{		
				logger.info("User Mail Domain Name: "+ mailDomain[1]+"IF chck" + sUserCCName.equals(sUserFullName));			
				
			   if (!sUserCCName.equals(sUserFullName) && ipAddress != null) 
			   {  
				    logger.info("USer IP Address_Regstr_Page:-->Uid:"+ userId +"uIP:"+ ipAddress);
				    userIP.setUserId(userId);
					userIP.setUsersIP(ipAddress);
					userIPDao.save(userIP);
					map.addAttribute("accountDeclined", "GYHotmails");
					//map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
					map.addAttribute("search", true);
					return newcardDeclinedView;
			   }
			
				
			}
		}
		t = billingManager.verifyCardWithCvv(u, cc, rfc.getCcAuthCode(), amount);
		if(t.getTransactionStatusId() == billingManager.txnApproved )
		{
			//session.setAttribute("username", rfc.getUsername());
			
			if (ipAddress != null) 
			   {  
				   logger.info("USer IP Address_Regstr_Page:-->:"+ ipAddress);
				    userIP.setUserId(userId);
					userIP.setUsersIP(ipAddress);
					userIPDao.save(userIP);
			   }
			logger.info("User Id: "+userId);
			logger.info("Credit Card Verification: "+t.getBankResponseReasonText());
			logger.info("NSF_NewAccnt_BnkTransactnCODE::::#### "+ t.getBankTransactionCode());			
		}
		else 
		{
			map.addAttribute("cardDeclineReason", t.getBankResponseReasonText()); 
			//int userId = userManager.registerNewUser(u, cc, rfc.getTest());
			if (ipAddress != null) 
			   {  
				   logger.info("USer IP Address_RegstrPg_CrdDecld:-->:"+ ipAddress);
				    userIP.setUserId(userId);
					userIP.setUsersIP(ipAddress);
					userIPDao.save(userIP);
			   }
			return newcardDeclinedView;
		}
		
		User regUser=userManager.getUserByUsername(u.getUsername());
		CreditCard regCC=userManager.getCreditCard(regUser.getUserId());
		//logger.info("reg CC user id:"+regCC.getUserId());
		session.setAttribute("creditCardObj", regCC);
		session.setAttribute("authenticated", "true");
		session.setAttribute("username", regUser.getUsername());
		session.setAttribute("userId", regUser.getUserId());
		session.setAttribute("userEmail", regUser.getEmail());
		
		
		sReturnPage = ManageSearchandRedirect(session,map,status);
		return sReturnPage ;
		//return premiumdatabaseRedir;
		
	}
	
	//Getting CLient IP Address
	public static String getClientIpAddr(HttpServletRequest request) {  
		    String ip = request.getHeader("X-Forwarded-For");  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("Proxy-Client-IP");  
		    }  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("WL-Proxy-Client-IP");  
		    }  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
		    }  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("HTTP_X_FORWARDED");  
		    }  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");  
		    }  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("HTTP_CLIENT_IP");  
		    }  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("HTTP_FORWARDED_FOR");  
		    }  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("HTTP_FORWARDED");  
		    }  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("HTTP_VIA");  
		    }  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("REMOTE_ADDR");  
		    }  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getRemoteAddr();  
		    }  
		    return ip;  
		}
	
	@RequestMapping(value = "/funnel/newConfirmSearch.do", method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map
			) {		
		
				
		//added by vivek 21-03-2012
		SearchFormCommand personalfc =(SearchFormCommand) session.getAttribute("searchFormCommand");
		AliasSearchFormCommand aliasfc =(AliasSearchFormCommand) session.getAttribute("aliasSearchFormCommand");
		BJLSearchFormCommand bjlsfc = (BJLSearchFormCommand) session.getAttribute("bjlSearchFormCommand");
		RegisterFormCommand rfc = (RegisterFormCommand) session.getAttribute("registerFormCommand");
		
		//eviction  programmed by vivek 26 may 2012
		EvictionSearchFormCommand esfc=(EvictionSearchFormCommand) session.getAttribute("evictionSearchFormCommand");
		EvictionBusinessSearchFormCommand ebsfc=(EvictionBusinessSearchFormCommand) session.getAttribute("evictionBusinessSearchFormCommand");
		CorpIndSearchFormCommand cisfc = (CorpIndSearchFormCommand) session.getAttribute("corpIndSearchFormCommand");
		CorpBusSearchFormCommand cbsfc = (CorpBusSearchFormCommand) session.getAttribute("corpBusSearchFormCommand");
		NationSearchFormCommand nsfc= (NationSearchFormCommand) session.getAttribute("nationSearchFormCommand");
		//Real Property Search Udhay Jul 3 -2014
		RealPropSearchFormCommand rpfc=(RealPropSearchFormCommand) session.getAttribute("RealPropSearchFormCommand");
		RealPropAddressSearchFormCommand rpafc=(RealPropAddressSearchFormCommand) session.getAttribute("RealPropAddressSearchFormCommand");		
		CriminalSearchFormCommand crimfc=(CriminalSearchFormCommand) session.getAttribute("CriminalSearchFormCommand");
		
		// pfc is mandatory here
		if (rfc == null && session.getAttribute("username") == null ) {
			return landingHome;
		}
		// at least 1 of the other 2 must be present also
		if ((bjlsfc == null) && (personalfc == null)&& (aliasfc == null) && (rpfc == null)&& (rpafc == null) && (esfc == null) && (ebsfc == null) && (cisfc == null) && (cbsfc == null) && (nsfc == null)&& (crimfc == null)) {
			return landingHome;
		}
		
		// now determine what to do based on which other form is present	
		map.addAttribute("search", true);
		//logger.info("NewConfm_Session_CCObjt@@::"+session.getAttribute("creditCardObj") != null);
		if(session.getAttribute("creditCardObj") != null){
			
			//getting updated Credit card
			String username = (String) session.getAttribute("username");
			User user = userManager.getUserByUsername(username);			
			CreditCard cc = userManager.getCreditCard(user.getUserId());
			logger.info("NewConfrm_GET::USERID@@:"+ user.getUserId());
			session.setAttribute("creditCardObj", cc);
			
			map.addAttribute("ccLast4", cc.getLastDigits());
		}else if(session.getAttribute("noCreditCard") != null){
			map.addAttribute("ccLast4", "No Card" );
		}else{
			String ccNumber = rfc.getCcNumber();
			String ccLast4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
			map.addAttribute("ccLast4", ccLast4 );
		}
		
		// determine which type of search: crim or BJL
		if (bjlsfc != null) {				
			// add BJL fields
			
			map.addAttribute("searchPrice", bjlsfc.getPrice());
			if(bjlsfc.getBjllastname() != null)
			{
			map.addAttribute("searchName", bjlsfc.getBjlfirstname()+" "+bjlsfc.getBjllastname());
			//map.addAttribute("searchBJLFName", bjlsfc.getBjlfirstname());
			//map.addAttribute("searchBJLLName", bjlsfc.getBjllastname());
			}
			
			if(bjlsfc.getBjlBusinessName() != null)
			{
			map.addAttribute("searchName", bjlsfc.getBjlBusinessName());
			//map.addAttribute("searchBussName", bjlsfc.getBjlBusinessName());
			}
			map.addAttribute("searchState", bjlsfc.getBjlstate());
			map.addAttribute("searchBJLType", getSearchTypeString(bjlsfc));
			map.addAttribute("searchSsnTax", bjlsfc.getBjlssn());
		
			return newBJLconfirm;
			
		}		//add RelProp - Udhay Jul 3-2014
		else if(rpfc != null)
		{
			map.addAttribute("searchRealProp", true);
			map.addAttribute("searchPrice", rpfc.getPrice());
			map.addAttribute("searchName", rpfc.getFirstname()+" "+rpfc.getMiddlename()+" " +rpfc.getLastname());
			map.addAttribute("searchState", rpfc.getState());
			map.addAttribute("searchCity", rpfc.getCity());
			map.addAttribute("searchRefer", rpfc.getReference());
			map.addAttribute("searchRealPropType", getRealPropSearchType(rpfc));
			
			return newRealPropconfirm;
		}
		else if(rpafc != null)
		{
			map.addAttribute("searchRealPropAddress", true);
			map.addAttribute("searchPrice", rpafc.getPrice());
			map.addAttribute("searchAppartnum", rpafc.getAppartnum());
			map.addAttribute("searchStreet", rpafc.getStreet());
			map.addAttribute("searchCity", rpafc.getCity());
			map.addAttribute("searchState", rpafc.getState());
			map.addAttribute("searchRefer", rpafc.getReference());
			map.addAttribute("searchRealPropAddressSearchType", getRealPropAddressSearchType(rpafc));
			
			return newRealPropconfirm;
		}
		else if(esfc != null)
		{
			map.addAttribute("searchEviction", true);
			map.addAttribute("searchPrice", esfc.getPrice());
			map.addAttribute("searchName", esfc.getFirstname()+" "+esfc.getLastname());
			map.addAttribute("searchState", esfc.getState());
			map.addAttribute("searchCity", esfc.getCity());
			map.addAttribute("searchRefer", esfc.getReference());
			map.addAttribute("searchEvictionType", getSearchEvictionType(esfc));
			
			return newEvictionconfirm;
		}
		else if(ebsfc != null)
		{
			map.addAttribute("searchEBusiness", true);
			map.addAttribute("searchPrice", ebsfc.getPrice());
			map.addAttribute("searchName", ebsfc.getBusinessname());
			map.addAttribute("searchState", ebsfc.getState());
			map.addAttribute("searchCity", ebsfc.getCity());
			map.addAttribute("searchRefer", ebsfc.getReference());
			map.addAttribute("searchEvictionType", getEvictionBusinessType(ebsfc));
			return newEvictionconfirm;
			
		}
		else if (cisfc != null) {
			map.addAttribute("searchCorpInd", true);
			map.addAttribute("searchPrice", cisfc.getPrice());
			map.addAttribute("searchFName", cisfc.getFirstname());
			map.addAttribute("searchLName", cisfc.getLastname());
			map.addAttribute("searchState", cisfc.getState());
			map.addAttribute("searchRefer", cisfc.getReference());
			map.addAttribute("searchCorpType", getCorpIndType(cisfc));
			map.addAttribute("Nationwide", cisfc.getNationwideSearch());
			return corpConfirmView;
			
		}
		else if (cbsfc != null) {
			
			map.addAttribute("searchCorpBus", true);
			map.addAttribute("searchPrice", cbsfc.getPrice());
			map.addAttribute("searchBName", cbsfc.getBusinessname());
			map.addAttribute("searchState", cbsfc.getState());
			map.addAttribute("searchRefer", cbsfc.getReference());
			map.addAttribute("searchCorpType", getCorpBusType(cbsfc));
			map.addAttribute("Nationwide", cbsfc.getNationwideSearch());
			return corpConfirmView;
			
		}
		else if(nsfc != null) // added by vivek on 18/7/2013
		{
			map.addAttribute("searchNationalSecurity", true);
			map.addAttribute("searchPrice", nsfc.getPrice());
			map.addAttribute("searchName", nsfc.getNssFirstName()+" "+nsfc.getNssLastName());
			map.addAttribute("searchNationalType", getNationSecurityType(nsfc));
			return nssconfirmView;
		}
		else if(crimfc != null)
		{
			map.addAttribute("searchCriminal", true);
			map.addAttribute("searchPrice", crimfc.getPrice());
			map.addAttribute("searchName", crimfc.getFirstname()+" "+crimfc.getMiddlename()+" " +crimfc.getLastname());
			map.addAttribute("searchState", crimfc.getState());
			//map.addAttribute("searchCity", crimfc.getCity());
			//map.addAttribute("searchRefer", crimfc.getReference());			
			logger.info("Criminal_Search_: Get search INFO");
			return newRealPropconfirm;
		}
		else if (personalfc != null) {
			//upgrade by vivek 21--2011
			
			map.addAttribute("searchCriminal", true);
			map.addAttribute("searchPrice", personalfc.getPrice());
			map.addAttribute("searchType", getSearchTypeString(personalfc));
			String searchName=personalfc.getBgcFirstName() + " " +personalfc.getBgcLastName();
			map.addAttribute("searchName", searchName);
			String state=personalfc.getBgcState();
			String searchDOB="";
			String searchSSN="";
			boolean msg=false;
				
			if(personalfc.getBgcDobRange())
			{
				if(state.equals("CA") || state.equals("IN") || state.equals("MO") || state.equals("HI") || state.equals("KS") || state.equals("MS") || state.equals("ND") || state.equals("NJ") || state.equals("NV") || state.equals("PA") || state.equals("RI") || state.equals("UT"))
				{
					searchDOB = personalfc.getBgcDobRangeBaseYear() + " (within " + personalfc.getBgcDobRangeFuzz() + " years)";
					msg=true;
				}
				else 
				{
					searchDOB = personalfc.getBgcDobRangeBaseYear() + " (within " + personalfc.getBgcDobRangeFuzz() + " years)";
				}
			}
			else
			{
				searchDOB = personalfc.getBgcDobMonth() + "/" +personalfc.getBgcDobDay() +"/"+ personalfc.getBgcDobYear();
			}
			map.addAttribute("msg", msg);
			map.addAttribute("searchDOB", searchDOB);
			map.addAttribute("NationWide", personalfc.getNationwideSearch());
		}
		else if (aliasfc != null) {
			map.addAttribute("searchCriminal", true);
			map.addAttribute("searchPrice", aliasfc.getPrice());
			map.addAttribute("searchType", getSearchTypeString(aliasfc));
			String searchName=aliasfc.getBgcFirstName() + " " +aliasfc.getBgcLastName();
			map.addAttribute("searchName", searchName);
			//String state=aliasfc.getBgcState();
			String searchDOB=aliasfc.getBgcDobMonth() + "/" +aliasfc.getBgcDobDay() +"/"+ aliasfc.getBgcDobYear();
			String searchSSN=aliasfc.getBgcSsn();
			map.addAttribute("searchSSN", searchSSN);
			map.addAttribute("searchDOB", searchDOB);
			map.addAttribute("NationWide", aliasfc.getNationwideSearch());
		}
		
		return newconfirmView;
		
	}
	
	@RequestMapping(value = "/funnel/newConfirmSearch.do", method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			SessionStatus status) 
	{
		//crimfc.getFirstname(),crimfc.getLastname(),crimfc.getDob(), crimfc.getSsn(),crimfc.getState()
		 
		CriminalSearchFormCommand crimfc=(CriminalSearchFormCommand) session.getAttribute("CriminalSearchFormCommand");
		if(crimfc!=null)
		{
			if(crimfc.getFirstname() !=null && crimfc.getLastname() !=null )
			{
				if(crimfc.getFirstname().equals("Charles") && crimfc.getLastname().equals("Souza") || crimfc.getDob().equals("5/28/1959"))
				{
					//logger.info("NewSearchForm_lin:>>122-->" + crimfc.getDob());
					return newzeroResultsView;
				}
			}
			
		}
		
		String sReturnPage;
		sReturnPage = ManageSearchandRedirect(session,map,status);
		return sReturnPage ;
		
	}
	
	private String ManageSearchandRedirect(HttpSession session, ModelMap map,
			SessionStatus status) {
		// TODO Auto-generated method stub
		boolean member = false;
		if(session.getAttribute("username") != null )
		{
			member = true;
		}
		//NewPurchaseFormCommand pfc =  new NewPurchaseFormCommand();
		RegisterFormCommand rfc = new RegisterFormCommand();
		// upgraded by vivek for crimial searches 22-03-2012
		
		if(!member)
		{
			//pfc = (NewPurchaseFormCommand) session.getAttribute("newPurchaseFormCommand");	
			rfc = (RegisterFormCommand) session.getAttribute("registerFormCommand");
			// upgraded by vivek for crimial searches 21-03-2012
			
		}
		
		// upgraded by vivek for crimial searches 21-03-2012
		
		SearchFormCommand personalfc = (SearchFormCommand) session.getAttribute("searchFormCommand");
		AliasSearchFormCommand aliasfc =(AliasSearchFormCommand) session.getAttribute("aliasSearchFormCommand");
		BJLSearchFormCommand bjlsfc = (BJLSearchFormCommand) session.getAttribute("bjlSearchFormCommand");
		//eviction  programmed by vivek 26 may 2012
		EvictionSearchFormCommand esfc=(EvictionSearchFormCommand) session.getAttribute("evictionSearchFormCommand");
		EvictionBusinessSearchFormCommand ebsfc=(EvictionBusinessSearchFormCommand) session.getAttribute("evictionBusinessSearchFormCommand");
		CorpIndSearchFormCommand cisfc = (CorpIndSearchFormCommand) session.getAttribute("corpIndSearchFormCommand");
		CorpBusSearchFormCommand cbsfc = (CorpBusSearchFormCommand) session.getAttribute("corpBusSearchFormCommand");
		NationSearchFormCommand nsfc = (NationSearchFormCommand) session.getAttribute("nationSearchFormCommand");
		//Real Property Search Udhay Jul 3 -2014
		RealPropSearchFormCommand rpfc=(RealPropSearchFormCommand) session.getAttribute("RealPropSearchFormCommand");
		RealPropAddressSearchFormCommand rpafc=(RealPropAddressSearchFormCommand) session.getAttribute("RealPropAddressSearchFormCommand");
		
		CriminalSearchFormCommand crimfc=(CriminalSearchFormCommand) session.getAttribute("CriminalSearchFormCommand");		
		
		if((rfc == null) && !member)
		{
			return redirRegister;
		}		
		// rfc is mandatory here
		if (rfc == null && !member) 
		{
			return landingHome;
		}
		// at least 1 of the other two must be present also
		if ((bjlsfc == null) && (personalfc == null) && (aliasfc == null) && (rpfc == null) && (rpafc == null)  && (esfc == null) && (ebsfc == null) && (cisfc == null) && (cbsfc == null) && (nsfc == null) && (crimfc == null)) 
		{
			return landingHome;
		}
				
		// now determine what to do based on which other form is present
		
		short category = 0;
		short subCategory=0;
		
		
		// required for all funnel searches
		User u = new User();
		if(member)
		{
			String username = (String) session.getAttribute("username");
			u = userManager.getUserByUsername(username);
		}else{
			if(rfc != null)
			{
				u = generateUser(rfc);			
			}
			
		}
		//Getting customer Name to Send Welcome mail for New customer. -Feb18-2014 Udhay
		String customerName = u.getFirstName() + " " + u.getLastName();
		int userId = 0 ;
		//Monthly invoice for NO Card Users
		if(session.getAttribute("noCreditCard") != null)
		{
			Transaction t = new Transaction();
			t.setTransactionId(0);
			
			if (personalfc != null) 
			{
				
				try
				{		
					//Doing Criminal Personal Searches changed by vivek 21-03-2010
					int responseId = runCriminalSearch(session, map, status, personalfc, t, u);

					// req id is set in runSearch()
					session.setAttribute("responseId", responseId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", personalfc.getPrice());
					
					//if(personalfc.getBgcSsn()!=null)
						//return newSSNresultsRedir;
					//else
						return newresultsRedir;
					
				} 
				catch (SearchException te) 
				{			
					logger.error("Crminl runCriminlSerch:" + te);					
					return newvwSearchError;			
				} 
				
			}
			else if(aliasfc != null)
			{
				//Doing bjl Search				
				try {				
					logger.error("Alias Search -  Search about to be done: for noCreditCard");
					int responseId = runAliasSearch(session, map, status, aliasfc, t, u);
					
					// req id is set in runSearch()
					session.setAttribute("responseId", responseId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", aliasfc.getPrice());
					
					return newAliasResultsRedir;
				} catch (SearchException te) 
				{		
					logger.error(te);
					logger.error("Alias Search Error for monthly User : " + u.getUsername() + ":" + u.getUserId());
					return newvwSearchError;			
				} 
			}
			else if(bjlsfc != null)
			{
				//Doing bjl Search				
				try {				
					int userSearchId = runBJLSearch(session, map, status, bjlsfc, t, u);
					
					// req id is set in runSearch()
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", bjlsfc.getPrice());
					return newresultsBjlRedir;
					
					
				} catch (SearchException te) 
				{		
					logger.error(te);
					logger.error("BJL Search Error for monthly User : "+u.getUsername() +":"+u.getUserId());
					return newvwSearchError;			
				} 
			}
			else if (esfc != null) 
			{
				//Doing Eviction Individual Search
				try
				{
					int userSearchId = runEvictionSearch(session, map, status, esfc, t, u);
					//req id is set in runsearch
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", esfc.getPrice());
					return evictionresultsRedir;
				}
				catch (SearchException se) 
				{
					
					logger.error(se);
					logger.error("Eviction Search Error for monthly user"+ u.getUsername() +":"+u.getUserId());
					return newvwSearchError;
				}
				
			}
			else if (rpfc != null) 
			{
				//Doing Real Property Name and Address Search
				logger.info("Real Property Monthly Members ");
				try
				{
					int userSearchId = runRealPropertySearch(session, map, status, rpfc, t, u);
					
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", rpfc.getPrice());
					return newRealPropresultsRedir;
				}
				catch (SearchException se) 
				{
					
					logger.error(se);
					logger.error("Real Property Name  Search Error for monthly user"+ u.getUsername() +":"+u.getUserId());
					return newvwSearchError;
				}
				
			}
			else if (rpafc != null) 
			{
				//Doing Real Property Name and Address Search
				logger.info("Real Property Monthly Members ");
				try
				{
					int userSearchId = runRealPropertyAddressSearch(session, map, status, rpafc, t, u);
					
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", rpafc.getPrice());
					return newRealPropresultsRedir;
				}
				catch (SearchException se) 
				{
					
					logger.error(se);
					logger.error("Real Property Name  Search Error for monthly user"+ u.getUsername() +":"+u.getUserId());
					return newvwSearchError;
				}
				
			}
			else if (ebsfc != null) {
				
				//Doing Eviction Business Search
				try
				{
					int userSearchId = runEvictionSearch(session, map, status, ebsfc, t, u);
					//req id is set in runsearch
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", ebsfc.getPrice());
					return evictionresultsRedir;
				}
				catch (SearchException se) {
					
					logger.error(se);
					logger.error("Eviction Business Search Error for monthly user"+ u.getUsername() +":"+u.getUserId());
					return newvwSearchError;
				}
				
			}
			else if (cisfc != null) {
				
				try
				{
					int userSearchId = runCorpSearch(session, map, status, cisfc, t, u);
					//logger.info(getClass() + "::indsearch:userId = " + userSearchId);
					if (userSearchId == -1)
					{
						return corpTooManyResults;
					}
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", cisfc.getPrice());
					return corpresultsRedir;
				}
				catch (SearchException se) 
				{
					// TODO: handle exception
					logger.error(se);
					logger.error("Coporation Individual Search Error for monthly user"+u.getUsername()+" : "+u.getUserId());
					return newvwSearchError;
				}
				
			}
			else if (cbsfc != null) {

				try{
					int userSearchId = runCorpSearch(session, map, status, cbsfc, t, u);
					//logger.info(getClass() + "::bussearch:userId = " + userSearchId);
					if (userSearchId == -1)
					{
						return corpTooManyResults;
					}
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", cbsfc.getPrice());
					return corpresultsRedir;
				}
				catch (SearchException se) {
					// TODO: handle exception
					logger.error(se);
					logger.error("Coporation Business Search Error for monthly user"+u.getUsername()+" : "+u.getUserId());
					return newvwSearchError;
				}
				
			}
			else if (nsfc != null) {
				try{
					int responseId = runNationalSecuritySearch(session, map, status, nsfc, t, u);
					session.setAttribute("responseId", responseId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", nsfc.getPrice());
					return nationalresultsRedir;
					
				}
				catch (SearchException e) {
					// TODO: handle exception
					logger.error(e);
					logger.error("National Security Search Error for monthly user"+u.getUsername()+" : "+u.getUserId());
					return newvwSearchError;
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.info(" JAXB National Security Search Error for monthly user"+u.getUsername());
				}
				
			}//ddn Criminal search
			else if (crimfc != null) 
			{
				//Doing Real Property Name and Address Search
				logger.info("Crminal DDN __");
				try
				{
					int userSearchId = runDDNCriminalSearch(session, map, status, crimfc, t, u);
					logger.info("Crminal DDN __UseIDDDD::::" + userSearchId);
					
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", crimfc.getPrice());
					return newDDNResultDetails;
				}
				catch (SearchException se) 
				{					
					logger.error(se);
					logger.error("DDNCriminal Search  Search Error for monthly user"+ u.getUsername() +":"+u.getUserId());
					return newvwSearchError;
				}				
			}
		}

		
		//Doing normal searches for members		
		CreditCard cc = new CreditCard();
		//logger.info("RP_Addres to_GetCC_Info:::Name" +session.getAttribute("creditCardObj") != null);
		if(session.getAttribute("creditCardObj") != null)
		{
			cc = (CreditCard) session.getAttribute("creditCardObj");
		}
		else
		{
			if(rfc != null)
			{
				cc = generateCC(rfc);
			}
		
		}
		Boolean testCC = rfc.getTest();			
		
		// determine which type of search: crim or BJL
		if (bjlsfc != null) 
		{
			// do BJL
			
			category = 11;
			BigDecimal amount = bjlsfc.getPrice();			
			String description = "Bankruptcy, Judgment, Tax Lien - " + bjlsfc.getBjlSearchType();

			// charge card first, if fails don't bother with search
			Integer invoiceNumber = 0;
			
			//setting email
						
						
			//setting description in admin
			String searchType = bjlsfc.getBjlSearchType().toString();
			
			if(searchType.equalsIgnoreCase("INDIVIDUALNAME")){
				subCategory = 113;
			}else if(searchType.equalsIgnoreCase("BUSINESSNAME")){
				subCategory = 114;
			}else{
				subCategory = 115;
			}

			Transaction t = new Transaction();
			if(member)
			{
				
				//XXX change to test mode charge by vivek 19-01-2012
				//t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory, true);
				//XXX change to real charge by vivek 19-01-2012 -10-01-2014
				t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory);
			}
			else
			{
				try
				{
					t = userManager.registerNewUserBySearch(u, cc, rfc.getCcAuthCode(), amount, category, subCategory, rfc.getTest());
					
					User regUser = userManager.getUserByUsername(u.getUsername());
					CreditCard regcc = userManager.getCreditCard(regUser.getUserId());
					
					session.setAttribute("creditCardObj", regcc);
					session.setAttribute("authenticated", "true");
					session.setAttribute("username", regUser.getUsername());
					session.setAttribute("userId", regUser.getUserId());
					session.setAttribute("userEmail", regUser.getEmail());
					
				}
				catch (UserManagerException e) 
				{					
					// bleh, how do we figure out the reason for the ex?
					if (e.getType() == Type.CC_FAILURE) 
					{
						map.addAttribute("cardDeclineReason", e.getMessage());
						return newcardDeclinedView;					
					} 
					else 
					{
						// XXX may need to void txn here
						logger.error("Registeration failed, may need to manually void txn!");
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + cc.getName());
						logger.error("CC Last 4: " + cc.getLastDigits());
						logger.error(e);
						
						// check ex type to verify
						return newcardDeclinedView;
					}				
				}			
			}
			
			
			if (t.getTransactionStatusId() == BillingManager.txnApproved) {
				try {				
					int userSearchId = runBJLSearch(session, map, status, bjlsfc, t, u);
					
					if(member)
						sendSearchReceiptEmail(u.getFirstName(), u.getEmail(), t, description, String.valueOf(cc.getLastDigits()));
					else
					{	
						String ccN = rfc.getCcNumber();				
						String ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
						sendSearchReceiptEmail(rfc.getName(), rfc.getEmail(), t, description, ccLast4);
					}

					// req id is set in runSearch()
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", bjlsfc.getPrice());
					return newresultsBjlRedir;
					} 
					catch (SearchException te) {			
					// void charge
					if (!billingManager.voidTransaction(cc, t)) {
						// maybe send email if this fails?
						logger.error("Failed to void transaction: " + t.getTransactionId());
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + t.getCcName());
						logger.error("CC Last 4: " + t.getCcLastDigits());
					}
		
					// send to error page
					return newvwSearchError;			
				}
			} else {
				map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
				map.addAttribute("search", true);
				return newcardDeclinedView;
			}   
	
			// end of BJL
			
		}//Real Property Search starts
		else if(rpfc != null ||  rpafc != null)
		{
			// do Real Property Name searches	
			logger.info("Real Property Searching for member");
			category=66;
			BigDecimal amount= BigDecimal.ZERO;
			if(rpfc != null)
				amount = rpfc.getPrice();
			else if(rpafc != null)
				amount = rpafc.getPrice();
			session.setAttribute("searchPrice", amount);
			String description = null;
			
			description=(rpfc!=null ? "Real property Name Search" : "Real Property Address Search");
			subCategory=606;
						
			// charge card first, if fails don't bother with search and register
			Transaction t = new Transaction();
			
			if(member)
			{
				//XXX change test mode udhay
				//t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory,true);
				//XXX change live mode
				t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory);
				logger.info("TransID"+t.getTransactionId());
			}
			//Real Property
			else
			{				
				try
				{
					t = userManager.registerNewUserBySearch(u, cc, rfc.getCcAuthCode(), amount, category, subCategory, rfc.getTest());
					
					User regUser = userManager.getUserByUsername(u.getUsername());
					CreditCard regcc = userManager.getCreditCard(regUser.getUserId());
					
					session.setAttribute("creditCardObj", regcc);
					session.setAttribute("authenticated", "true");
					session.setAttribute("username", regUser.getUsername());
					session.setAttribute("userId", regUser.getUserId());
					session.setAttribute("userEmail", regUser.getEmail());
					
				}
				catch (UserManagerException e) 
				{
					// bleh, how do we figure out the reason for the ex?
					if (e.getType() == Type.CC_FAILURE) 
					{
						map.addAttribute("cardDeclineReason", e.getMessage());
						return newcardDeclinedView;					
					} else {
						// XXX may need to void txn here
						logger.error("Registeration failed, may need to manually void txn!");
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + cc.getName());
						logger.error("CC Last 4: " + cc.getLastDigits());
						logger.error(e);
						
						// check ex type to verify
						return newcardDeclinedView;
					}				
				}			
			}
			
			if (t.getTransactionStatusId() == BillingManager.txnApproved) 
			{
				try {	
					int userSearchId=0;
					if(rpfc !=null)
						userSearchId = runRealPropertySearch(session, map, status, rpfc, t, u);
					if(rpafc !=null)
						userSearchId = runRealPropertyAddressSearch(session, map, status, rpafc, t, u);
					if(member)
						sendSearchReceiptEmail(u.getFirstName(), u.getEmail(), t, description, String.valueOf(cc.getLastDigits()));
					else{	
						String ccN = rfc.getCcNumber();					
						String ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
						logger.info("description aftetr"+description);
						logger.info("pfc.getName() aftetr"+rfc.getName());
						logger.info("rfc.getEmail() aftetr"+rfc.getEmail());
						logger.info("t aftetr"+t);
						logger.info("ccLast4 aftetr"+ccLast4);
						sendSearchReceiptEmail(rfc.getName(), rfc.getEmail(), t, description, ccLast4);
					}

					// req id is set in runSearch()
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					//session.setAttribute("searchPrice", rpfc.getPrice());

					return newRealPropresultsRedir;
				} catch (SearchException te) 
				{			
					// void charge
					if (!billingManager.voidTransaction(cc, t)) {
						// maybe send email if this fails?
						logger.error("Failed to void transaction: " + t.getTransactionId());
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + t.getCcName());
						logger.error("CC Last 4: " + t.getCcLastDigits());						
					}
		
					// send to error page
					return newvwSearchError;			
				} 
			} else 
			{
				map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
				map.addAttribute("search", true);
				return newcardDeclinedView;
			}
			
			// end of Real Property Name searches
		}	
		
		else if(esfc != null){
			// do eviction individual searches
	
			logger.info("Eviction Inividual Searching for member");
			category=33;
			
			
			BigDecimal amount = esfc.getPrice();			
			String description = null;
			
			description="Eviction Individual Search"+esfc.getState();
			subCategory=901;
			
			
			logger.info("description:"+description);
			
			// charge card first, if fails don't bother with search and register
			Transaction t = new Transaction();
			
			if(member)
			{
				//XXX change test mode
				//t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory,true);
				//XXX change live mode
				t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory);
			}
			else
			{
				
				try
				{
					t = userManager.registerNewUserBySearch(u, cc, rfc.getCcAuthCode(), amount, category, subCategory, rfc.getTest());
					
					User regUser = userManager.getUserByUsername(u.getUsername());
					CreditCard regcc = userManager.getCreditCard(regUser.getUserId());
					
					session.setAttribute("creditCardObj", regcc);
					session.setAttribute("authenticated", "true");
					session.setAttribute("username", regUser.getUsername());
					session.setAttribute("userId", regUser.getUserId());
					session.setAttribute("userEmail", regUser.getEmail());
					
				}
				catch (UserManagerException e) 
				{
					// bleh, how do we figure out the reason for the ex?
					if (e.getType() == Type.CC_FAILURE) 
					{
						map.addAttribute("cardDeclineReason", e.getMessage());
						return newcardDeclinedView;					
					} else {
						// XXX may need to void txn here
						logger.error("Registeration failed, may need to manually void txn!");
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + cc.getName());
						logger.error("CC Last 4: " + cc.getLastDigits());
						logger.error(e);
						
						// check ex type to verify
						return newcardDeclinedView;
					}				
				}			
			}
			
			if (t.getTransactionStatusId() == BillingManager.txnApproved) {
				try {				
					int userSearchId = runEvictionSearch(session, map, status, esfc, t, u);

					if(member)
						sendSearchReceiptEmail(u.getFirstName(), u.getEmail(), t, description, String.valueOf(cc.getLastDigits()));
					else{	
						String ccN = rfc.getCcNumber();					
						String ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
						logger.info("description aftetr"+description);
						logger.info("pfc.getName() aftetr"+rfc.getName());
						logger.info("rfc.getEmail() aftetr"+rfc.getEmail());
						logger.info("t aftetr"+t);
						logger.info("ccLast4 aftetr"+ccLast4);
						sendSearchReceiptEmail(rfc.getName(), rfc.getEmail(), t, description, ccLast4);
					}

					// req id is set in runSearch()
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", esfc.getPrice());

					return evictionresultsRedir;
				} catch (SearchException te) {			
					// void charge
					if (!billingManager.voidTransaction(cc, t)) {
						// maybe send email if this fails?
						logger.error("Failed to void transaction: " + t.getTransactionId());
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + t.getCcName());
						logger.error("CC Last 4: " + t.getCcLastDigits());						
					}
		
					// send to error page
					return newvwSearchError;			
				} 
			} else {
				map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
				map.addAttribute("search", true);
				return newcardDeclinedView;
			}
			
			// end of eviction individual searches
			}
		
		else if(ebsfc != null){
			// do eviction individual searches
	
			logger.info("Eviction Business Searching for member");
			category=33;
			
			
			BigDecimal amount = ebsfc.getPrice();			
			String description = null;
			
			description="Eviction Business Search"+ebsfc.getState();
			subCategory=902;
			
			
			logger.info("description:"+description);
			
			// charge card first, if fails don't bother with search and register
			Transaction t = new Transaction();
			
			if(member)
			{
				//XXX change test mode
				//t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory,true);
				//XXX change live mode
				t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory);
			}
			else
			{				
				try
				{
					t = userManager.registerNewUserBySearch(u, cc, rfc.getCcAuthCode(), amount, category, subCategory, rfc.getTest());
					
					User regUser = userManager.getUserByUsername(u.getUsername());
					CreditCard regcc = userManager.getCreditCard(regUser.getUserId());
					
					session.setAttribute("creditCardObj", regcc);
					session.setAttribute("authenticated", "true");
					session.setAttribute("username", regUser.getUsername());
					session.setAttribute("userId", regUser.getUserId());
					session.setAttribute("userEmail", regUser.getEmail());
					
				}catch (UserManagerException e) {
					// bleh, how do we figure out the reason for the ex?
					if (e.getType() == Type.CC_FAILURE) {
						map.addAttribute("cardDeclineReason", e.getMessage());
						return newcardDeclinedView;					
					} else {
						// XXX may need to void txn here
						logger.error("Registeration failed, may need to manually void txn!");
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + cc.getName());
						logger.error("CC Last 4: " + cc.getLastDigits());
						logger.error(e);
						
						// check ex type to verify
						return newcardDeclinedView;
					}				
				}			
			}
			
			if (t.getTransactionStatusId() == BillingManager.txnApproved) {
				try {				
					int userSearchId = runEvictionSearch(session, map, status, ebsfc, t, u);

					if(member)
						sendSearchReceiptEmail(u.getFirstName(), u.getEmail(), t, description, String.valueOf(cc.getLastDigits()));
					else{	
						String ccN = rfc.getCcNumber();					
						String ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
						logger.info("description aftetr"+description);
						logger.info("pfc.getName() aftetr"+rfc.getName());
						logger.info("rfc.getEmail() aftetr"+rfc.getEmail());
						logger.info("t aftetr"+t);
						logger.info("ccLast4 aftetr"+ccLast4);
						sendSearchReceiptEmail(rfc.getName(), rfc.getEmail(), t, description, ccLast4);
					}

					// req id is set in runSearch()
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", ebsfc.getPrice());

					return evictionresultsRedir;
				} catch (SearchException te) {			
					// void charge
					if (!billingManager.voidTransaction(cc, t)) {
						// maybe send email if this fails?
						logger.error("Failed to void transaction: " + t.getTransactionId());
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + t.getCcName());
						logger.error("CC Last 4: " + t.getCcLastDigits());						
					}
		
					// send to error page
					return newvwSearchError;			
				} 
			} else {
				map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
				map.addAttribute("search", true);
				return newcardDeclinedView;
			}
			
			// end of eviction Business searches
			}
		
		else if (cisfc != null) {
			//Begin Corporation Individual Search
			category=38;
			subCategory=908;
			BigDecimal amount=cisfc.getPrice();
			String description="";
			if(cisfc.getNationwideSearch())
			{
				description="Corporation Individual Search - Nationwide";
			}
			else {
				description="Corporation Individual Search - "+cisfc.getState();
			}
			
			Transaction t=new Transaction();
			
			int userSearchId = runCorpSearch(session, map, status, cisfc, t, u);
			logger.info(getClass() + "::indsearch-creditcard:userId = " + userSearchId);
			if (userSearchId == -1)
			{
				return corpTooManyResults;
			}
			if(member)
			{
				//Test Mode
				//t=billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory,true);
				//Live Mode
				t=billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory);
			}
			else 
			{
				try
				{
					t= userManager.registerNewUserBySearch(u, cc, rfc.getCcAuthCode(), amount, category, subCategory, rfc.getTest());
					User regUser = userManager.getUserByUsername(u.getUsername());
					CreditCard regcc = userManager.getCreditCard(regUser.getUserId());
				
					session.setAttribute("creditCardObj", regcc);
					session.setAttribute("authenticated", "true");
					session.setAttribute("username", regUser.getUsername());
					session.setAttribute("userId", regUser.getUserId());
					session.setAttribute("userEmail", regUser.getEmail());
				}
				catch (UserManagerException e) {
					// bleh, how do we figure out the reason for the ex?
					if (e.getType() == Type.CC_FAILURE) {
						map.addAttribute("cardDeclineReason", e.getMessage());
						return newcardDeclinedView;					
					} else {
						// XXX may need to void txn here
						logger.error("Registeration failed, may need to manually void txn!");
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + cc.getName());
						logger.error("CC Last 4: " + cc.getLastDigits());
						logger.error(e);
						
						// check ex type to verify
						return newcardDeclinedView;
					}		
				}
			}
			if (t.getTransactionStatusId() == BillingManager.txnApproved) {
				try {				
					
					if(userSearchId != -1 && userSearchId != 0)
					{
						CorporationSearches corpObj=new CorporationSearches();
						corpObj=corpManager.getSearch(u.getUsername(), userSearchId);
						
						if(corpObj.getUserSearchId() == userSearchId)
						{
							corpObj.setTransactionId(t.getTransactionId());
							corpObj.save();
						}
						
					}

					if(member)
						sendSearchReceiptEmail(u.getFirstName(), u.getEmail(), t, description, String.valueOf(cc.getLastDigits()));
					else{	
						String ccN = rfc.getCcNumber();					
						String ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
						logger.info("description aftetr"+description);
						logger.info("pfc.getName() aftetr"+rfc.getName());
						logger.info("rfc.getEmail() aftetr"+rfc.getEmail());
						logger.info("t aftetr"+t);
						logger.info("ccLast4 aftetr"+ccLast4);
						sendSearchReceiptEmail(rfc.getName(), rfc.getEmail(), t, description, ccLast4);
					}

					// req id is set in runSearch()
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", cisfc.getPrice());

					return corpresultsRedir;
				}
				catch (TorqueException e) {
					// TODO: handle exception
					logger.info("data updated into db"+e);
					return newvwSearchError;
				}
				catch (SearchException te) {			
					// void charge
					if (!billingManager.voidTransaction(cc, t)) {
						// maybe send email if this fails?
						logger.error("Failed to void transaction: " + t.getTransactionId());
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + t.getCcName());
						logger.error("CC Last 4: " + t.getCcLastDigits());						
					}
					
		
					// send to error page
					return newvwSearchError;			
				} 
			} else {
				map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
				map.addAttribute("search", true);
				return newcardDeclinedView;
			}

			//End of Corporation Ind Search
		}
		
		else if (cbsfc != null) {

			//Begin Corporation Business Search
			category=38;
			subCategory=909;
			BigDecimal amount=cbsfc.getPrice();
			String description="";
			if(cbsfc.getNationwideSearch())
			{
				description="Corporation Business Search - Nationwide";
			}
			else {
				description="Corporation Business Search - "+cbsfc.getState();
			}
				
			Transaction t=new Transaction();
			//t.setTransactionId(0);
			int userSearchId = runCorpSearch(session, map, status, cbsfc, t, u);
			//logger.info(getClass() + "::bussearch-creditcard:userId = " + userSearchId);
			if (userSearchId == -1)
			{
				return corpTooManyResults;
			}
			if(member)
			{
				//Test Mode
				//t=billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory,true);
				//Live Mode
				t=billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory);
			}
			else 
			{
				try
				{
					t= userManager.registerNewUserBySearch(u, cc, rfc.getCcAuthCode(), amount, category, subCategory, rfc.getTest());
					User regUser = userManager.getUserByUsername(u.getUsername());
					CreditCard regcc = userManager.getCreditCard(regUser.getUserId());
					
					session.setAttribute("creditCardObj", regcc);
					session.setAttribute("authenticated", "true");
					session.setAttribute("username", regUser.getUsername());
					session.setAttribute("userId", regUser.getUserId());
					session.setAttribute("userEmail", regUser.getEmail());
				}
				catch (UserManagerException e) 
				{
					// bleh, how do we figure out the reason for the ex?
					if (e.getType() == Type.CC_FAILURE) {
						map.addAttribute("cardDeclineReason", e.getMessage());
						return newcardDeclinedView;					
					} else {
						// XXX may need to void txn here
						logger.error("Registeration failed, may need to manually void txn!");
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + cc.getName());
						logger.error("CC Last 4: " + cc.getLastDigits());
						logger.error(e);
						
						// check ex type to verify
						return newcardDeclinedView;
					}		
				}
			}
			if (t.getTransactionStatusId() == BillingManager.txnApproved) {
				try {
					if(userSearchId != -1 && userSearchId != 0)
					{
						CorporationSearches corpObj=new CorporationSearches();
						corpObj=corpManager.getSearch(u.getUsername(), userSearchId);
						if(corpObj.getUserSearchId() == userSearchId)
						{
							corpObj.setTransactionId(t.getTransactionId());
							corpObj.save();
						}
					}
					if(member)
						sendSearchReceiptEmail(u.getFirstName(), u.getEmail(), t, description, String.valueOf(cc.getLastDigits()));
					else{	
						String ccN = rfc.getCcNumber();					
						String ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
						logger.info("description aftetr"+description);
						logger.info("pfc.getName() aftetr"+rfc.getName());
						logger.info("rfc.getEmail() aftetr"+rfc.getEmail());
						logger.info("t aftetr"+t);
						logger.info("ccLast4 aftetr"+ccLast4);
						sendSearchReceiptEmail(rfc.getName(), rfc.getEmail(), t, description, ccLast4);
					}

					// req id is set in runSearch()
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", cbsfc.getPrice());

					return corpresultsRedir;
				}
				catch (TorqueException e) {
					// TODO: handle exception
					logger.info("not updated into transaction id:"+e);
					return newvwSearchError;
				}
				catch (SearchException te) {			
					// void charge
					if (!billingManager.voidTransaction(cc, t)) {
						// maybe send email if this fails?
						logger.error("Failed to void transaction: " + t.getTransactionId());
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + t.getCcName());
						logger.error("CC Last 4: " + t.getCcLastDigits());						
					}
		
					// send to error page
					return newvwSearchError;			
				} 
			} else {
				map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
				map.addAttribute("search", true);
				return newcardDeclinedView;
			}

			//End of Corporation Bus Search
		
			
		}

		/**
		 * Begin National Security Search for Member
		 */
		
		else if (nsfc != null) 
		{
			
			category=59;
			subCategory=999;
			BigDecimal amount=nsfc.getPrice();
			String description="National Security Search";
			Transaction t=new Transaction();
			if(member)
			{
				//test mode
				//t=billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory,true);
				//Live mode
				t=billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory);
			}
			else 
			{
				try
				{
					t = userManager.registerNewUserBySearch(u, cc, rfc.getCcAuthCode(), amount, category, subCategory, rfc.getTest());
					
					User regUser = userManager.getUserByUsername(u.getUsername());
					CreditCard regcc = userManager.getCreditCard(regUser.getUserId());
					
					session.setAttribute("creditCardObj", regcc);
					session.setAttribute("authenticated", "true");
					session.setAttribute("username", regUser.getUsername());
					session.setAttribute("userId", regUser.getUserId());
					session.setAttribute("userEmail", regUser.getEmail());
					
				}
				catch (UserManagerException e) 
				{
					// bleh, how do we figure out the reason for the ex?
					if (e.getType() == Type.CC_FAILURE) {
						map.addAttribute("cardDeclineReason", e.getMessage());
						return newcardDeclinedView;					
					} else {
						// XXX may need to void txn here
						logger.error("Registeration failed, may need to manually void txn!");
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + cc.getName());
						logger.error("CC Last 4: " + cc.getLastDigits());
						logger.error(e);
						
						// check ex type to verify
						return newcardDeclinedView;
					}				
				}			
			}
			if (t.getTransactionStatusId() == BillingManager.txnApproved) {
				try {				
					int responseId = runNationalSecuritySearch(session, map, status, nsfc, t, u);

					if(member)
						sendSearchReceiptEmail(u.getFirstName(), u.getEmail(), t, description, String.valueOf(cc.getLastDigits()));
					else{	
						String ccN = rfc.getCcNumber();					
						String ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
						sendSearchReceiptEmail(rfc.getName(), rfc.getEmail(), t, description, ccLast4);
					}

					// req id is set in runSearch()
					session.setAttribute("responseId", responseId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", nsfc.getPrice());

					return nationalresultsRedir;
				} catch (SearchException te) {			
					// void charge
					if (!billingManager.voidTransaction(cc, t)) {
						// maybe send email if this fails?
						logger.error("Failed to void transaction: " + t.getTransactionId());
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + t.getCcName());
						logger.error("CC Last 4: " + t.getCcLastDigits());						
					}
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
					// send to error page
					return newvwSearchError;			
				
			} else {
				map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
				map.addAttribute("search", true);
				return newcardDeclinedView;
			}

			
			
		} //DDN Criminal search for members -start
		else if(crimfc != null)
		{
				
			logger.info("DDN_CriminalSrch for member_1");
			category=2;
			BigDecimal amount= BigDecimal.ZERO;
			if(crimfc != null)
				amount = crimfc.getPrice();
			session.setAttribute("searchPrice", amount);
			String description = null;
			
			description="DDN Criminal Search";
			subCategory=6;
						
			// charge card first, if fails don't bother with search and register
			Transaction t = new Transaction();
			
			if(member)
			{
				
				
				logger.info("DDN_CriminalSrch for member::(membrs)"+cc.getNumber());
				//XXX change test mode udhay
				t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory,true);
				//XXX change live mode
				//t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory);
				
				
				
				logger.info("TransID"+t.getTransactionId());
			}
			//DDN Criminal
			else
			{				
				try
				{
					t = userManager.registerNewUserBySearch(u, cc, rfc.getCcAuthCode(), amount, category, subCategory, rfc.getTest());
					
					User regUser = userManager.getUserByUsername(u.getUsername());
					CreditCard regcc = userManager.getCreditCard(regUser.getUserId());
					
					session.setAttribute("creditCardObj", regcc);
					session.setAttribute("authenticated", "true");
					session.setAttribute("username", regUser.getUsername());
					session.setAttribute("userId", regUser.getUserId());
					session.setAttribute("userEmail", regUser.getEmail());
					
				}
				catch (UserManagerException e) 
				{
					// bleh, how do we figure out the reason for the ex?
					if (e.getType() == Type.CC_FAILURE) 
					{
						map.addAttribute("cardDeclineReason", e.getMessage());
						return newcardDeclinedView;					
					} else {
						// XXX may need to void txn here
						logger.error("Registeration failed, may need to manually void txn!");
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + cc.getName());
						logger.error("CC Last 4: " + cc.getLastDigits());
						logger.error(e);
						
						// check ex type to verify
						return newcardDeclinedView;
					}				
				}			
			}
			
			if (t.getTransactionStatusId() == BillingManager.txnApproved) 
			{
				try {	
					int userSearchId=0;					
					logger.info("Crminal DDN_Norml_Members::::" + userSearchId);					
					if(crimfc !=null)
						userSearchId = runDDNCriminalSearch(session, map, status, crimfc, t, u);					
					if(member)
						sendSearchReceiptEmail(u.getFirstName(), u.getEmail(), t, description, String.valueOf(cc.getLastDigits()));
					else{	
						String ccN = rfc.getCcNumber();					
						String ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
						
						sendSearchReceiptEmail(rfc.getName(), rfc.getEmail(), t, description, ccLast4);
					}

					// req id is set in runSearch()
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", crimfc.getPrice());

					return newDDNResultDetails;
				} catch (SearchException te) 
				{			
					// void charge
					if (!billingManager.voidTransaction(cc, t)) {
						// maybe send email if this fails?
						logger.error("Failed to void transaction: " + t.getTransactionId());
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + t.getCcName());
						logger.error("CC Last 4: " + t.getCcLastDigits());						
					}
		
					// send to error page
					return newvwSearchError;			
				} 
			} else 
			{
				map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
				map.addAttribute("search", true);
				return newcardDeclinedView;
			}
			
			// end of Criminal member search ends
		}
		
		else if(aliasfc != null) {

			// do criminal  searches
	
			
			category=30;
			subCategory=301;
			
			BigDecimal amount = aliasfc.getPrice();			
			String description="";
			
			
			if (aliasfc.getNationwideSearch()) {
				description = "Alias US Search - Nationwide";				
			} else {
				String state = "(" + aliasfc.getBgcState() + ")";
				description = "Alias Single State Search - State " + state;				
			}
			
			// charge card first, if fails don't bother with search and register
			Transaction t = new Transaction();
			
			if(member){
				//XXX change test mode
				//t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory,true);
				//XXX change live mode
				t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory);
			}
			else
			{
				try
				{
					t = userManager.registerNewUserBySearch(u, cc, rfc.getCcAuthCode(), amount, category, subCategory, rfc.getTest());							
					
					User regUser = userManager.getUserByUsername(u.getUsername());
					CreditCard regcc = userManager.getCreditCard(regUser.getUserId());
					
					session.setAttribute("creditCardObj", regcc);
					session.setAttribute("authenticated", "true");
					session.setAttribute("username", regUser.getUsername());
					session.setAttribute("userId", regUser.getUserId());
					session.setAttribute("userEmail", regUser.getEmail());
					
				}catch (UserManagerException e) {
					// bleh, how do we figure out the reason for the ex?
					if (e.getType() == Type.CC_FAILURE) {
						map.addAttribute("cardDeclineReason", e.getMessage());
						return newcardDeclinedView;					
					} else {
						// XXX may need to void txn here
						logger.error("Registeration failed, may need to manually void txn!");
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + cc.getName());
						logger.error("CC Last 4: " + cc.getLastDigits());
						logger.error(e);
						
						// check ex type to verify
						return newcardDeclinedView;
					}				
				}			
			}
			
			if (t.getTransactionStatusId() == BillingManager.txnApproved) {
				try 
				{				
					//logger.error("onSubmt:Status@: "+ status );
					logger.info("Alias Search Confirmed: Transaction approved: Search starts here");
					int responseId = runAliasSearch(session, map, status, aliasfc, t, u);
					logger.info("Alias Search Confirmed: Search Ends here: ResponseId: " + responseId);
					
					if(member)
						sendSearchReceiptEmail(u.getFirstName(), u.getEmail(), t, description, String.valueOf(cc.getLastDigits()));
					else
					{	
						String ccN = rfc.getCcNumber();					
						String ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
						sendSearchReceiptEmail(rfc.getName(), rfc.getEmail(), t, description, ccLast4);
					}
					logger.info("Alias Search Confirmed: Transaction approved: Email sent to customer");

					// req id is set in runSearch()
					session.setAttribute("responseId", responseId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", aliasfc.getPrice());

					return newAliasResultsRedir;
				} catch (Exception te) {			
					// void charge
					if (!billingManager.voidTransaction(cc, t)) {
						// maybe send email if this fails?
						logger.error("Failed to void transaction: " + t.getTransactionId());
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + t.getCcName());
						logger.error("CC Last 4: " + t.getCcLastDigits());						
					}
		
					// send to error page
					return newvwSearchError;			
				} 
			} else {
				map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
				map.addAttribute("search", true);
				return newcardDeclinedView;
			}
			
			// end of alias  searches
		
		}
		
		/**
		 * End National Security Search for Member
		 */
				else {
					// do criminal  searches
			
					
					category=30;
					subCategory=301;
					
					BigDecimal amount = personalfc.getPrice();			
					String description="";
					
					
					if (personalfc.getNationwideSearch()) {
						description = "BGC Criminal Search - Nationwide";				
					} else {
						String state = "(" + personalfc.getBgcState() + ")";
						description = "BGC Criminal Search - State " + state;				
					}
					
					// charge card first, if fails don't bother with search and register
					Transaction t = new Transaction();
					
					if(member){
						//XXX change test mode
						//t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory,true);
						//XXX change live mode
						t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, u.getUserId(), category, subCategory);
					}
					else
					{
						try
						{
							t = userManager.registerNewUserBySearch(u, cc, rfc.getCcAuthCode(), amount, category, subCategory, rfc.getTest());							
							
							User regUser = userManager.getUserByUsername(u.getUsername());
							CreditCard regcc = userManager.getCreditCard(regUser.getUserId());
							
							session.setAttribute("creditCardObj", regcc);
							session.setAttribute("authenticated", "true");
							session.setAttribute("username", regUser.getUsername());
							session.setAttribute("userId", regUser.getUserId());
							session.setAttribute("userEmail", regUser.getEmail());
							
						}catch (UserManagerException e) {
							// bleh, how do we figure out the reason for the ex?
							if (e.getType() == Type.CC_FAILURE) {
								map.addAttribute("cardDeclineReason", e.getMessage());
								return newcardDeclinedView;					
							} else {
								// XXX may need to void txn here
								logger.error("Registeration failed, may need to manually void txn!");
								logger.error("Username: " + u.getUsername());
								logger.error("CC Name: " + cc.getName());
								logger.error("CC Last 4: " + cc.getLastDigits());
								logger.error(e);
								
								// check ex type to verify
								return newcardDeclinedView;
							}				
						}			
					}
					
					if (t.getTransactionStatusId() == BillingManager.txnApproved) {
						try 
						{				
							//logger.error("onSubmt:Status@: "+ status );
							logger.info("NewSearchForm_lin Bil Confrmd Seach-->1985");
							int responseId = runCriminalSearch(session, map, status, personalfc, t, u);

							if(member)
								sendSearchReceiptEmail(u.getFirstName(), u.getEmail(), t, description, String.valueOf(cc.getLastDigits()));
							else
							{	
								String ccN = rfc.getCcNumber();					
								String ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
								sendSearchReceiptEmail(rfc.getName(), rfc.getEmail(), t, description, ccLast4);
							}

							// req id is set in runSearch()
							session.setAttribute("responseId", responseId);
							session.setAttribute("transactionId", t.getTransactionId());
							session.setAttribute("searchPrice", personalfc.getPrice());

							return newresultsRedir;
						} catch (SearchException te) {			
							// void charge
							if (!billingManager.voidTransaction(cc, t)) {
								// maybe send email if this fails?
								logger.error("Failed to void transaction: " + t.getTransactionId());
								logger.error("Username: " + u.getUsername());
								logger.error("CC Name: " + t.getCcName());
								logger.error("CC Last 4: " + t.getCcLastDigits());						
							}
				
							// send to error page
							return newvwSearchError;			
						} 
					} else {
						map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
						map.addAttribute("search", true);
						return newcardDeclinedView;
					}
					
					// end of criminal  searches
				}
	}
	//BJL Search
	protected int runBJLSearch(HttpSession session, ModelMap map, SessionStatus status, 
			BJLSearchFormCommand bjlsfc, Transaction t, User u) 
	throws SearchException {
		// check for test
		if (bjlsfc.getTest()) {
			return TEST_BJL_RESULT_ID;
		}
		
		// run query
		long result = bjlManager.queryFunnel(u.getUserId(), t.getTransactionId(), 
				bjlsfc.getBjllastname(), bjlsfc.getBjlfirstname(),bjlsfc.getBjlmiddlename(),
				bjlsfc.getBjlstate(), bjlsfc.getBjlssn(), bjlsfc.getBjlssntype(),
				bjlsfc.getBjlBusinessName(), bjlsfc.getBjlReference(), bjlsfc.getBjlCaseType());
		
		return (int) result;
		
	}
	
	// Begin Criminal Search 21-03-2010
	protected int runCriminalSearch(HttpSession session, ModelMap map, SessionStatus status, 
			 SearchFormCommand sfc, Transaction t, User u) 
	throws SearchException 
	{
		
		if (sfc.getTest()) 
		{
			return TEST_CRIMINAL_RESULT_ID;
		}
		
		int productId = 4;
		String jurisdiction;
		if (sfc.getNationwideSearch()) 
		{			
			jurisdiction = "Nationwide";
		} else 
		{
			productId = 5;
			jurisdiction = sfc.getBgcState();
		}	

		sfc.setBgcDobYear(sfc.getBgcDobRangeBaseYear());
		int requestId=0;
		long ssnRequestId=0;
		int responseId=0;
		//bgcSSNManager
		/*if(sfc.getBgcSsn()!=null)
		{
			logger.info("NewCfrm lin 2078 SSN if");
			ssnRequestId = bgcSSNSearchManager.queryFunnel(u.getUserId(),t.getTransactionId(),session,sfc); 
			responseId= (int) ssnRequestId;
			logger.info("NwCfrm ssnRequestId 2081+ssn"+ ssnRequestId);
		}*/
		//else
		{
		requestId = bgcManager.prepareSearch(u.getUserId(), 
				sfc.getBgcFirstName(), sfc.getBgcMiddleInitial(), sfc.getBgcLastName(), 
				sfc.getBgcFirstNameExact(), sfc.getBgcLastNameExact(), 
				sfc.getBgcDobMonth(), sfc.getBgcDobDay(), sfc.getBgcDobYear(), 
				sfc.getBgcDobRangeFuzz(), sfc.getBgcDobRange(), sfc.getBgcMatchMissingDates(), 
				productId, false, jurisdiction, sfc.getBgcPurpose(), sfc.getBgcReferenceCode());
		

		BGCResponseBean response = bgcManager.runSearch(requestId);
		responseId = response.getBgcResponseId();
		logger.info("responseId:--> " + responseId);

		// record the txn id
		bgcManager.setTransactionId(responseId, (int) t.getTransactionId());
				
		}
		

		// finalize charge?
		// XXX todo
		
		//return resultsView;
		return responseId;
	}// End Criminal  Searches 
	
	// Begin Alias Search
		protected int runAliasSearch(HttpSession session, ModelMap map, SessionStatus status, 
				 AliasSearchFormCommand sfc, Transaction t, User u) 
		throws SearchException 
		{
			
			if (sfc.getTest()) 
			{
				return TEST_CRIMINAL_RESULT_ID;
			}
			
			int productId = 7;
			String jurisdiction;
			if (sfc.getNationwideSearch()) 
			{			
				jurisdiction = "Nationwide";
			} else 
			{
				productId = 8;
				jurisdiction = sfc.getBgcState();
			}	

			//sfc.setBgcDobYear(sfc.getBgcDobRangeBaseYear());
			int requestId=0;
			long ssnRequestId=0;
			int responseId=0;
			//bgcSSNManager
			/*if(sfc.getBgcSsn()!=null)
			{
				logger.info("NewCfrm lin 2078 SSN if");
				ssnRequestId = bgcSSNSearchManager.queryFunnel(u.getUserId(),t.getTransactionId(),session,sfc); 
				responseId= (int) ssnRequestId;
				logger.info("NwCfrm ssnRequestId 2081+ssn"+ ssnRequestId);
			}*/
			//else
			{
			requestId = aliasSearchManager.prepareSearch(u.getUserId(), 
					sfc.getBgcFirstName(), sfc.getBgcMiddleInitial(), sfc.getBgcLastName(), 
					sfc.getBgcFirstNameExact(), sfc.getBgcLastNameExact(), 
					sfc.getBgcDobMonth(), sfc.getBgcDobDay(), sfc.getBgcDobYear(), Integer.parseInt(sfc.getBgcSsn()),
					productId, false, jurisdiction, sfc.getBgcPurpose(), sfc.getBgcReferenceCode());
			
			logger.info("The request id for alias search is: " + requestId);
			
			BGCResponseBean response = aliasSearchManager.runSearch(requestId);
			responseId = response.getBgcResponseId();
			logger.info("responseId:--> " + responseId);

			// record the txn id
			aliasSearchManager.setTransactionId(responseId, (int) t.getTransactionId());
					
			}
			

			// finalize charge?
			// XXX todo
			
			//return resultsView;
			return responseId;
		}// End Criminal  Searches 
	
	//Begin Real Property Name search
	
	//Run Real Property 
	protected int runRealPropertySearch(HttpSession session, ModelMap map, SessionStatus status, 
			RealPropSearchFormCommand rpfc, Transaction t, User u) 
	throws SearchException {
		// check for test
		//logger.error("runRealPropertySearch_tst " + rpfc.getTest());
		if (rpfc.getTest()) {			
			return TEST_BJL_RESULT_ID;
		}
		int searchsubcategory=606;
		//logger.error("runRealProp_TransactionIDddDD::::" + t.getTransactionId() );
		// run query
		long result = realpropManager.queryFunnel(u.getUserId(), searchsubcategory,t.getTransactionId(), 
				rpfc.getFirstname(),rpfc.getLastname(), null,rpfc.getAppartnum(),rpfc.getStreet(),rpfc.getCity(),rpfc.getState(),rpfc.getReference(),session);
		
		session.removeAttribute("RPNameSearch");
		return (int) result;
		
	}
	
	//Run Real Property Address Search
	protected int runRealPropertyAddressSearch(HttpSession session, ModelMap map, SessionStatus status, 
				RealPropAddressSearchFormCommand rpafc, Transaction t, User u) 
		throws SearchException {
			
			if (rpafc.getTest()) {			
				return TEST_BJL_RESULT_ID;
			}
			int searchsubcategory=606;
			
			// run query
			long result = realpropManager.queryFunnel(u.getUserId(), searchsubcategory,t.getTransactionId(), 
					rpafc.getFirstname(),rpafc.getLastname(), null,rpafc.getAppartnum(),rpafc.getStreet(),rpafc.getCity(),rpafc.getState(),
					rpafc.getReference(),session);
			session.removeAttribute("RPNameSearch");
			return (int) result;
			
		}
		
	
	//Begin Eviction individual search or Business Seaerch
	protected int runEvictionSearch(HttpSession session, ModelMap map, SessionStatus status, 
			EvictionSearchFormCommand esfc, Transaction t, User u) 
	throws SearchException {
		// check for test
		if (esfc.getTest()) {
			return TEST_BJL_RESULT_ID;
		}
		int searchsubcategory=901;
		// run query
		long result = evictionManager.queryFunnel(u.getUserId(), searchsubcategory,t.getTransactionId(), 
				esfc.getFirstname(),esfc.getLastname(), null,esfc.getState(),esfc.getCity(),esfc.getReference());
		
		return (int) result;
		
	}
	// End of Eviction Individual Search
	
	//Begin Eviction  Business Seaerch
	protected int runEvictionSearch(HttpSession session, ModelMap map, SessionStatus status, 
			EvictionBusinessSearchFormCommand ebsfc, Transaction t, User u) 
	throws SearchException {
		// check for test
		if (ebsfc.getTest()) {
			return TEST_BJL_RESULT_ID;
		}
		int searchsubcategory=902;
		// run query
		long result = evictionManager.queryFunnel(u.getUserId(), searchsubcategory, t.getTransactionId(), 
				null, null, ebsfc.getBusinessname(),ebsfc.getState(),ebsfc.getCity(),ebsfc.getReference());
		
		return (int) result;
		
	}// End of Eviction Business Search
	// Begin Coporation Individual Searche
	protected int runCorpSearch(HttpSession session, ModelMap map, SessionStatus status, CorpIndSearchFormCommand cisfc, Transaction t, User u) throws SearchException
	{
		if(cisfc.getTest())
		{
			return TEST_BJL_RESULT_ID;
		}
		int searchsubcategory=908;
		//run query
		long result = corpManager.queryCorpFunnel(u.getUserId(), searchsubcategory, t.getTransactionId(), cisfc.getFirstname(), cisfc.getLastname(), null, cisfc.getState(), cisfc.getMiddleinitial(), cisfc.getReference());
		
		return (int) result;
		
	}
	// End Corporation Ind Search
	
	// Begin Coporation Business Search
	protected int runCorpSearch(HttpSession session, ModelMap map, SessionStatus status, CorpBusSearchFormCommand cbsfc, Transaction t, User u) throws SearchException
	{
		if(cbsfc.getTest())
		{
			return TEST_BJL_RESULT_ID;
		}
		int searchsubcategory=909;
		//run query
		long result = corpManager.queryCorpFunnel(u.getUserId(), searchsubcategory, t.getTransactionId(), null, null, cbsfc.getBusinessname(), cbsfc.getState(), null, cbsfc.getReference());
		
		return (int) result;
		
	}
	// End Corporation Business Search
	
	//Begin national security search
	protected int runNationalSecuritySearch(HttpSession session, ModelMap map, SessionStatus status, NationSearchFormCommand nsfc, Transaction t, User u) throws SearchException, JAXBException
	{
		if(nsfc.getTest())
		{
		  return TEST_CRIMINAL_RESULT_ID;
		}
		int nssProductId=6;
		// run query 
		int requestId=nssManager.prepareSearch(u.getUserId(), nsfc.getNssFirstName(), nsfc.getNssLastName(), nsfc.getNssDobMonth(), nsfc.getNssDobDay(), nsfc.getNssDobYear(), nssProductId, false, nsfc.getNssPurpose(), nsfc.getNssReferenceCode());
		
		logger.info("Request Id:"+requestId);
		NssResponseBean response = nssManager.runSearch(requestId);
		int responseId=response.getNssResponseId();
		logger.info("responseId:"+responseId);
		
		nssManager.setTransactionId(responseId, (int) t.getTransactionId());
		return responseId;
		
	}
	
	
	//Run DDN Criminal Search 
		protected int runDDNCriminalSearch(HttpSession session, ModelMap map, SessionStatus status, 
				CriminalSearchFormCommand crimfc, Transaction t, User u) 
		throws SearchException 
		{
			// check for test
			logger.error("On Method__runDDNCriminalSearch::::");
			if (crimfc.getTest()) {			
				//return TEST_BJL_RESULT_ID;
			}
			int searchsubcategory=6;
			//logger.error("runDDNCriminalSearch::::" + crimfc.getFirstname() + " nna"+ crimfc.getLastname() +" dob"+ crimfc.getDob() +"ssn"+  crimfc.getSsn() +"state"+crimfc.getState() );
			// run query
			long result = ddnCriminalManager.queryFunnel(u.getUserId(), searchsubcategory,t.getTransactionId(), 
					crimfc.getFirstname(),crimfc.getLastname(),crimfc.getDob(), crimfc.getSsn(),crimfc.getState(), session);
			
			//session.removeAttribute("RPNameSearch");
			return (int) result;
			
		}
		
			
	protected void sendSearchReceiptEmail (String customerName, String customerEmail, Transaction t, String description, String ccLast4) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		// add to map: search type, price, status, results, query fields?
		map.put("invoiceNumber", t.getTransactionId());
		map.put("description", description);
		map.put("amount", t.getCost());
		
		map.put("customerName", customerName);
		map.put("customerEmail", customerEmail);
		map.put("ccLast4", ccLast4);

		String subject = "Search Systems Receipt";
        sendEmailVelocity(map, tplSearchReceipt, emailFromNoReply, customerEmail, subject);		
	}
		
}