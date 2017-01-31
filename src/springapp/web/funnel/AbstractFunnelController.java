package springapp.web.funnel;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import springapp.service.user.UserManager;

/**
 * Base class for all sales funnel functionality. Defines frequently used objects,
 * static values, commonly used helper methods (email, etc) and backing data for
 * model attributes used in forms. 
 * 
 * @author vivek
 *
 */
 public abstract class AbstractFunnelController {
	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	protected UserManager userManager;
	@Autowired
	protected MailSender mailSender;
	@Autowired
	protected VelocityEngine velocityEngine;
	@Autowired
	protected JavaMailSender javaMailSender;
	
	protected static final int SESSION_TIMEOUT = 3600;
	protected static final String salesFunnelUser = "SalesFunnelUser";
	protected static final Integer bgcTestProductId = 1;
	
	static final int TEST_BJL_RESULT_ID = 65411;
	static final int TEST_CRIMINAL_RESULT_ID = 1764;

	/*
	 * web paths:
	 * 0) landing, value prop
	 * 1) service (crim search, bjl search, or signup)
	 * 2) purchase (enter billing info)
	 * 3) confirm
	 * 4) success (welcome or results)
	 */
	
	// various redirs
	protected static final String purchaseSignupRedir = "redirect:purchaseSignup.do";
	protected static final String purchaseSearchRedir = "redirect:purchaseSearch.do";
	
	//For new BJL
	protected static final String BJLValuProp = "funnel/BJLOption";
	protected static final String BJLconfirm = "funnel/BJLconfirm";
	protected static final String NoBJLResult = "funnel/BJLnoResult";
	
	protected static final String newBJLconfirm = "funnel/NewBJLconfirm";
	protected static final String newNoBJLResult = "funnel/NewBJLnoResult";
	
	protected static final String showLogin = "funnel/Login";
	protected static final String redirLogin = "redirect:login.do";
	//Upgraded by vivek 
	protected static final String shownewLogin = "funnel/NewLogin";
	protected static final String redirnewLogin = "redirect:newLogin.do";
	
	protected static final String showRegister = "funnel/Register";
	protected static final String redirRegister = "redirect:register.do";
	// Business urls by vivek 21-03-2012
	protected static final String shownewRegister = "funnel/NewRegister";
	protected static final String redirnewRegister = "redirect:NewRegister.do";
	// Business urls by vivek 21-03-2012
	protected static final String showBusinessRegister = "funnel/BusinessForm";
	protected static final String redirBusinessRegister = "redirect:BusinessRegister.do";
	
	protected static final String showPurchase = "funnel/NewPurchase";
	protected static final String redirPurchase = "redirect:purchase.do";
	// crrminal search upgraded by vivek 22-03-2012
	protected static final String showPurchaseTest = "funnel/NewPurchaseTest";
	protected static final String redirPurchaseTest = "redirect:purchasetest.do";

	protected static final String searchRedir = "redirect:search.do";
	protected static final String searchCampaignRedir = "redirect:searchCampaign.do";
	protected static final String searchBJLRedir = "redirect:searchBJL.do";

	protected static final String confirmSignupRedir = "redirect:confirmSignup.do";
	protected static final String confirmSearchRedir = "redirect:confirmSearch.do";
	protected static final String newconfirmSearchRedir = "redirect:newConfirmSearch.do";
	protected static final String newregisterRedir = "redirect:register.do";
	//changed by vivek
	protected static final String newconfirmSearchRedirTest = "redirect:newConfirmSearchTest.do";
	
	protected static final String successRedir = "redirect:success.do";
	protected static final String welcomeRedir = "redirect:welcome.do";
	protected static final String resultsRedir = "redirect:results.do";
	protected static final String resultsBjlRedir = "redirect:resultsBJL.do";
	protected static final String newresultsRedir = "redirect:newResults.do";
	protected static final String newAliasResultsRedir = "redirect:newAliasResults.do";
	protected static final String newresultsBjlRedir = "redirect:newResultsBJL.do";
	protected static final String evictionresultsRedir = "redirect:evictionResults.do";
	protected static final String completeSignupRedir = "redirect:completeSignup.do";
	// eviction urls
    protected static final String NoEvictionResult = "funnel/EvictionNoResult";// 29 may 2012
	
	protected static final String newEvictionconfirm = "funnel/NewEvictionConfirm";
	// Eviction views
	protected static final String vwEvictionBusiness="funnel/NewEvictionBusinessSearch";
	protected static final String vwEvictionSearch="funnel/NewEvictionSearch";
	protected static final String EvictionResultDetails = "funnel/EvictionResultDetails";
	
	// Real Property views
	// Udhay Jun3 - 2014 changes
	protected static final String vwRealPropAddress="funnel/PropertyAddressSearch";
	protected static final String vwRealPropSearch="funnel/PropertySearch";
	protected static final String RealPropResultDetails = "funnel/PropertyResultDetails";
    protected static final String NoResultRealProp = "funnel/PropertyNoResult";	
	protected static final String newRealPropconfirm = "funnel/PropertySearchConfirm";
	protected static final String newvwReralPropRedir = "redirect:realpropSearch.do";
	protected static final String newvwReralPropAddressRedir = "redirect:realpropAddressSearch.do";
	protected static final String newRealPropresultsRedir = "redirect:realPropResults.do";
	//end RealProp
	
	//DDN Criminal search implement 
	//
	protected static final String vwDdnNewCrimSearch="funnel/DdnNewCrimSearch";
	//
	protected static final String vwDdnCriminalSearch="funnel/DdnCriminalSearch";
	protected static final String newvwCrminalRedir = "redirect:criminalSearch.do";
	protected static final String DDNResultDetails = "funnel/DDNResultDetails";
	protected static final String newDDNResultDetails = "redirect:ddnResults.do";
	//Test mail -udhay	
	protected static final String vwSampleMail="funnel/zTestMail";
	protected static final String newvwSampleMail = "redirect:testmail.do";
	
    //protected static final String landingRedir = "redirect:landing.html";
	//vivek changes//
	protected static final String landingHome = "redirect:https://www.searchsystems.net/springapp/premium";//https://premium.searchsystems.net/secure/search.php
	//protected static final String logoutRedirect = "redirect:http://publicrecords.searchsystems.net/logout-new.php";
	protected static final String logoutRedirect = "redirect:../../premium";
	protected static final String logoutnewRedirect = "redirect:http://publicrecords.searchsystems.net/logout-new.php";
	//Aug8-2014 - Udhay
	protected static final String landingPremium = "redirect:https://www.searchsystems.net/springapp/premium";
	protected static final String landingTestPremium = "redirect:https://dev.searchsystems.net/springapp/premium";
		
	protected static final String landingRedir = "redirect:funnel/landing.html";
	// form views
	protected static final String vwSearch = "funnel/Search";
	protected static final String vwSearchBGCFull = "funnel/SearchBGCFull";
	protected static final String newvwSearchBGCFull = "funnel/NewSearchBGCFull";
	
	//new Criminal upgrade pages 
	// form views
	
	//protected static final String vwSearchBGCFull = "funnel/SearchBGCFull";
	protected static final String newvwSearchBusiness = "funnel/NewBusinessSearch";
	protected static final String newvwSearchRedir = "redirect:newSearch.do";
	protected static final String newvwbJLSearchRedir = "redirect:newSearchBJL.do";
	protected static final String newvwEvictionRedir = "redirect:eviction-records.do";
	protected static final String newvwEvictionBusinessRedir = "redirect:eviction-records-business.do";
	
	//For alias search
	protected static final String newvwAliasSearch = "funnel/NewAliasSearch";
	protected static final String newvwAliasSearchRedir = "redirect:aliasSearch.do";
	
	//New Criminal Search using DDN   -- Sep-20-2014
	protected static final String newvwDdnSearch = "funnel/DdnCriminalSearch";
	protected static final String newvwDdnSearchRedir = "redirect:ddnSearch.do";
	
	protected static final String newvwSearchBGCPersonal="funnel/NewPersonalSearchForm";
	
	protected static final String vwBJLSearch = "funnel/BJLSearch";
	protected static final String vwBJLResults = "funnel/BJLResults";
	protected static final String vwBJLResultDetails = "funnel/BJLResultDetails";
	
	protected static final String newvwBJLSearch = "funnel/NewBJLSearch";
	protected static final String newViewBJL="funnel/NewBJLSearchTest";//vivek
	protected static final String newvwBJLResults = "funnel/NewBJLResults";
	protected static final String newvwBJLResultDetails = "funnel/NewBJLResultDetails";
	
	// Added form views for Mexican funnel 
	protected static final String landingMexicanRecordRedir = "redirect:searchMexicanRecord.do?type=nation";
	protected static final String purchaseMexicanSearchRedir= "redirect:purchaseMexicanRecordSearch.do";
	protected static final String vwSearchMexicanRecordFull = "funnel/mexico/SearchMexicanRecord";
	protected static final String vwSearchMexicanRecordByState = "funnel/mexico/SearchMexicanRecordByState";
	protected static final String vwSearchMexicanRecordByRegion = "funnel/mexico/SearchMexicanRecordByRegion";
	protected static final String vwSearchMexicanRecordTeaserResult = "funnel/mexico/TeaserResult";
	protected static final String vwSearchMexicanRecordNoTeaserResult = "funnel/mexico/TeaserResultZeroCount";
	protected static final String purchaseMexicanView = "funnel/mexico/Purchase";
	protected static final String confirmMexicanSearchView = "funnel/mexico/ConfirmSearch";
	protected static final String confirmMexicanSearchRedir = "redirect:confirmMexicanSearch.do"; 
	protected static final String cardDeclinedMexicanSearchView = "funnel/mexico/CardDeclined";
	protected static final String resultsMexicanRecordRedir = "redirect:resultsMexicanRecords.do";
	protected static final String vwMexicanRecordResults = "funnel/mexico/ResultsMexicanRecord";
	protected static final String vwMexicanCommonError = "funnel/mexico/Error";
	protected static final String vwMexicanRecordDetails = "funnel/mexico/ViewDetailsMexicanRecord";
	
	protected static final String vwSignup = "funnel/Signup";
	
	protected static final String vwError = "funnel/Error";
	protected static final String newvwError = "funnel/NewError";
	protected static final String vwSearchError = "funnel/SearchError";
	protected static final String newvwSearchError = "funnel/NewSearchError";

	protected static final String purchaseView = "funnel/Purchase";
	protected static final String zeroResultsView = "funnel/ZeroResults";
	protected static final String newzeroResultsView = "funnel/NewZeroResults";
	protected static final String confirmView = "funnel/Confirm";
	protected static final String newconfirmView = "funnel/NewConfirm";
	//upgraded by vivek 22-03-2012
	protected static final String newcriminalconfirmView = "funnel/NewCriminalConfirm";


	protected static final String cardDeclinedView = "funnel/CardDeclined";
	protected static final String newcardDeclinedView = "funnel/NewCardDeclined";
	protected static final String resultsView = "funnel/Results";
	protected static final String newresultsView = "funnel/NewResults";
	protected static final String resultDetailsView = "funnel/ResultDetails";
	protected static final String printResultsView = "funnel/PrintResults";
	protected static final String newresultDetailsView = "funnel/NewResultDetails";
	protected static final String newprintResultsView = "funnel/NewPrintResults";
	
	// static views
	protected static final String welcomeView = "funnel/Welcome";
	protected static final String ourDatabasesView = "funnel/OurDatabases";
	protected static final String valuePropositionView = "funnel/ValueProposition";
	protected static final String sampleReportView = "funnel/SampleReport";
	protected static final String subscribeReportView = "funnel/Subscribe";
	protected static final String criminalRecordsGuideView = "funnel/CriminalRecordsGuide";

	// email stuff
	protected static final String emailFromThankYou = "thank-you@searchsystems.net";
	protected static final String emailFromNoReply = "no-reply@searchsystems.net";
	protected static final String emailToAdmin = "ram.dvk@gmail.com";
	protected static final String emailFromSkipTrace = "skip-trace@searchsystems.net";
	
	protected static final String tplThankYou = "springapp/web/funnel/ThankYouEmailTemplate.vm";
	protected static final String tplAdminProblem = "springapp/web/funnel/AdminProblemEmailTemplate.vm";
	protected static final String tplSearchException = "springapp/web/funnel/SearchExceptionEmailTemplate.vm";
	protected static final String tplSearchReceipt = "springapp/web/funnel/SearchReceiptEmailTemplate.vm";
	protected static final String tplSignupReceipt = "springapp/web/funnel/SignupReceiptEmailTemplate.vm";
	protected static final String skipTraceOrder = "springapp/web/skiptrace/orderTemplate.vm";


	// static lookups, useful across classes
	protected static final TreeMap<Integer,String> dobMonths = new TreeMap<Integer,String>();
	protected static final TreeMap<Integer,String> dobDays = new TreeMap<Integer,String>();
	protected static final TreeMap<Integer,String> dobYears = new TreeMap<Integer,String>();
	protected static final HashMap<String,String> usStates = new HashMap<String,String>();
	protected static final HashMap<Integer,String> countryISOCodesById = new HashMap<Integer,String>();
	protected static final TreeMap<Integer,String> cardExpirationMonths = new TreeMap<Integer,String>();
	protected static final TreeMap<Integer,String> cardExpirationYears = new TreeMap<Integer,String>();
	protected static final HashMap<String,String> directoryState = new HashMap<String,String>();
	protected static final TreeMap<Double,String> donationamount = new TreeMap<Double,String>();
	protected static final HashMap<Integer,String> donatefrequency = new HashMap<Integer,String>();
	protected static final TreeMap<String, String> usdirectorystate=new TreeMap<String, String>();
	protected static final TreeMap<String, String> bjlnumberabove=new TreeMap<String, String>();
	
	//Flat rate URLs
	protected static final  String FRLoginURL = "flatrate/FRLogin";
	protected static final  String FRLandingURL = "flatrate/FRLanding";
	protected static final  String FRSignup = "flatrate/FRSignup";
	protected static final  String FRPurchase = "flatrate/FRPurchase";
	protected static final  String FRConfirm = "flatrate/FRConfirm";
	protected static final  String FRThankyou = "flatrate/FRThankyou";
	protected static final  String FRMenuURL = "flatrate/FRMenu";
	protected static final  String FRPlanExpiry = "flatrate/FRExpired";
	protected static final  String FRcardDeclinedView = "flatrate/FRCardDeclined";
	protected static final  String FRvwError = "flatrate/FRError";
    		//redir
	protected static final  String FRLandingRedir = "redirect:FRLanding.do";
	protected static final  String FRSignupRedir = "redirect:FRSignup.do";
	protected static final  String FRPurchaseRedir = "redirect:FRPurchase.do";
	protected static final  String FRConfirmRedir = "redirect:FRConfirm.do";
	protected static final  String FRMenuRedir = "redirect:FRMenu.do";
	protected static final  String FRLoginRedir = "redirect:FRLogin.do";
	//FR FP
	protected static final String FRFPlandingUrl = "flatrate/FPSearchPage";
	protected static final String FRFPdelayUrl = "flatrate/FPSearchDelay";
	protected static final String FRFPnoResultUrl = "flatrate/FPNoResults";
	protected static final String FRFPresultUrl = "flatrate/FPResults";
	protected static final String FRFPBGResultRedir = "redirect:/findpeople/confirmFPSearch.do";
	protected static final String FRFPlandingRedir = "redirect:FPsearchLanding.do";
	//FR BGC
	protected static final String FRBGCLandingURL = "flatrate/BGCSearchPage";
	protected static final String FRBGCDelayURL = "flatrate/BGCSearchDelay";
	protected static final String FRBGCResultRedir = "redirect:BGCResults.do";
	protected static final String FRBGCLandingRedir = "redirect:BGCsearchLanding.do";
	protected static final String FRBGCNoResult = "flatrate/BGCNoResult";
	protected static final String FRBGCResultDetail = "flatrate/BGCResultDetail";
	protected static final String FRBGCResult = "flatrate/BGCResult";
	//FR Email
	protected static final String FRThankyouMail = "springapp/web/flatrate/emailTemplates/ThankYouEmailTemplate.vm";
	protected static final String FRSignupReceiptMail = "springapp/web/flatrate/emailTemplates/SignupReceiptEmailTemplate.vm";
	protected static final String FRExpMail = "springapp/web/flatrate/emailTemplates/FRExpEmailTemplate.vm"; 
	// Doantion funnel 
	protected static final String donateview="funnel/Donation";
	protected static final String donationconfirmredir="redirect:/funnel/DConfirmation.do";
	protected static final String donationconfirmview="funnel/Donationconfirm";
	protected static final String donationerror="funnel/DonationError";
	//Corporation Funnel
	protected static final String corpIndView="funnel/NewCorpIndSearch";
	protected static final String corpBusView="funnel/NewCorpBusSearch";
	protected static final String corpDelayView="funnel/CorpSearchDelay";
	protected static final String corpIndViewRedir="redirect:corp-ind-search.do";
	protected static final String corpBusViewRedir="redirect:corp-bus-search.do";
	protected static final String corpConfirmView="funnel/NewCorpConfirm";
	protected static final String corpresultsRedir = "redirect:CorpResults.do";
	protected static final String corpNoResult="funnel/CorpNoResult";
	protected static final String corpResultDetails="funnel/CorpResultDetails";
	protected static final String corpTooManyResults="funnel/TooManyResults";
	
	//New Account Verification
	protected static final String newconfirmAccRedir = "redirect:newAccConfirm.do";
	protected static final String newAccConfirm="funnel/NewAccConfirm";
	protected static final String premiumdatabaseRedir="redirect:premium";
	// National Security Views
	protected static final String nationSecurityView="funnel/NewNationSecurity";
	protected static final String nationSecureViewRedir="redirect:national-security-search.do";
	protected static final String nationalresultsRedir = "redirect:NssResults.do";
	protected static final String nsszeroResultsView = "funnel/NssZeroResults";
	protected static final String nssresultsView = "funnel/NssResults";
	protected static final String nssresultDetailsView = "funnel/NssResultDetails";
	protected static final String nssprintResultsView = "funnel/NssPrintResults";
	protected static final String nssconfirmView = "funnel/NssConfirm";
	protected static final String nssSearchDelayView="funnel/NssSearchDelay";
	// set up all the maps
	public void init() {
		populateDobMonths();
		populateDobDays();
		populateDobYears();
		populateUSStates();
		populateCountryCodes();
		populateCardExpirationMonths();
		populateCardExpirationYears();
		populateDirectoryState();
		populateDonationamount();
		populatedonatefrequency();
		populateAllUSState();
		populateBjlNumberTypes();
		
	}
	
	

	// map populators
	protected final void populateCardExpirationMonths() {
		for (int i=1; i<=12; i++) {
			cardExpirationMonths.put(i, String.valueOf(i));
		}		
	}
	protected final void populateCardExpirationYears() {
		for (int i=2014; i<=2024; i++) {
			cardExpirationYears.put(i, String.valueOf(i));
		}		
	}
	protected final void populateUSStates() {
		usStates.putAll(userManager.getUSStates());
	}
	
	protected final void populateDonationamount(){
		donationamount.put(2.00, "$2.00");
		donationamount.put(5.00, "$5.00");
		donationamount.put(10.00, "$10.00");
		donationamount.put(15.00, "$15.00");
		donationamount.put(25.00, "$25.00");
		donationamount.put(50.00, "$50.00");
		donationamount.put(100.00, "$100.00");
		donationamount.put(200.00, "$200.00");
		donationamount.put(500.00, "$500.00");
		donationamount.put(1000.00, "$1000.00");
	}
	
	protected final void populatedonatefrequency(){
		donatefrequency.put(1, "One-Time Donation");
		donatefrequency.put(12, "Recurring-Monthly Donation");
	}
	
	protected final void populateDirectoryState() {
		directoryState.put("all", "-_U_S__Nationwide");
		directoryState.put("AK","Alaska");
        directoryState.put("AL","Alabama");
        directoryState.put("AR","Arkansas");
        directoryState.put("AZ","Arizona");
        directoryState.put("CA","California");
        directoryState.put("CO","Colorado");
        directoryState.put("CT","Connecticut");
        directoryState.put("DC","District_of_Columbia");
        directoryState.put("DE","Delaware");
        directoryState.put("FL","Florida");
        directoryState.put("GA","Georgia");
        directoryState.put("HI","Hawaii");
        directoryState.put("IA","Iowa");
        directoryState.put("ID","Idaho");
        directoryState.put("IL","Illinois");
        directoryState.put("IN","Indiana");
        directoryState.put("KS","Kansas");
        directoryState.put("KY","Kentucky");
        directoryState.put("LA","Louisiana");
        directoryState.put("MA","Massachusetts");
        directoryState.put("MD","Maryland");
        directoryState.put("ME","Maine");
        directoryState.put("MI","Michigan");
        directoryState.put("MN","Minnesota");
        directoryState.put("MO","Missouri");
        directoryState.put("MS","Mississippi");
        directoryState.put("MT","Montana");
        directoryState.put("NC","North_Carolina");
        directoryState.put("ND","North_Dakota");
        directoryState.put("NE","Nebraska");
        directoryState.put("NH","New_Hampshire");
        directoryState.put("NJ","New_Jersey");
        directoryState.put("NM","New_Mexico");
        directoryState.put("NV","Nevada");
        directoryState.put("NY","New_York");
        directoryState.put("OH","Ohio");
        directoryState.put("OK","Oklahoma");
        directoryState.put("OR","Oregon");
        directoryState.put("PA","Pennsylvania");
        directoryState.put("RI","Rhode_Island");
        directoryState.put("SC","South_Carolina");
        directoryState.put("SD","South_Dakota");
        directoryState.put("TN","Tennessee");
        directoryState.put("TX","Texas");
        directoryState.put("UT","Utah");
        directoryState.put("VA","Virginia");
        directoryState.put("VT","Vermont");
        directoryState.put("WA","Washington");
        directoryState.put("WI","Wisconsin");
        directoryState.put("WV","West_Virginia");
        directoryState.put("WY","Wyoming");
	}
protected final void populateAllUSState() {
	
	
    usdirectorystate.put("AL","Alabama");
    usdirectorystate.put("AK","Alaska");
    usdirectorystate.put("AR","Arkansas");
    usdirectorystate.put("AZ","Arizona");
    usdirectorystate.put("CA","California");
    usdirectorystate.put("CO","Colorado");
    usdirectorystate.put("CT","Connecticut");
    usdirectorystate.put("DC","District_of_Columbia");
    usdirectorystate.put("DE","Delaware");
    usdirectorystate.put("FL","Florida");
    usdirectorystate.put("GA","Georgia");
    usdirectorystate.put("HI","Hawaii");
    usdirectorystate.put("IA","Iowa");
    usdirectorystate.put("ID","Idaho");
    usdirectorystate.put("IL","Illinois");
    usdirectorystate.put("IN","Indiana");
    usdirectorystate.put("KS","Kansas");
    usdirectorystate.put("KY","Kentucky");
    usdirectorystate.put("LA","Louisiana");
    usdirectorystate.put("MA","Massachusetts");
    usdirectorystate.put("MD","Maryland");
    usdirectorystate.put("ME","Maine");
    usdirectorystate.put("MI","Michigan");
    usdirectorystate.put("MN","Minnesota");
    usdirectorystate.put("MO","Missouri");
    usdirectorystate.put("MS","Mississippi");
    usdirectorystate.put("MT","Montana");
    usdirectorystate.put("NC","North_Carolina");
    usdirectorystate.put("ND","North_Dakota");
    usdirectorystate.put("NE","Nebraska");
    usdirectorystate.put("NH","New_Hampshire");
    usdirectorystate.put("NJ","New_Jersey");
    usdirectorystate.put("NM","New_Mexico");
    usdirectorystate.put("NV","Nevada");
    usdirectorystate.put("NY","New_York");
    usdirectorystate.put("OH","Ohio");
    usdirectorystate.put("OK","Oklahoma");
    usdirectorystate.put("OR","Oregon");
    usdirectorystate.put("PA","Pennsylvania");
    usdirectorystate.put("RI","Rhode_Island");
    usdirectorystate.put("SC","South_Carolina");
    usdirectorystate.put("SD","South_Dakota");
    usdirectorystate.put("TN","Tennessee");
    usdirectorystate.put("TX","Texas");
    usdirectorystate.put("UT","Utah");
    usdirectorystate.put("VA","Virginia");
    usdirectorystate.put("VT","Vermont");
    usdirectorystate.put("WA","Washington");
    usdirectorystate.put("WI","Wisconsin");
    usdirectorystate.put("WV","West_Virginia");
    usdirectorystate.put("WY","Wyoming");
    usdirectorystate.put("us","National");
    usdirectorystate.put("all","International");
		
	}
	protected final void populateCountryCodes() {
		countryISOCodesById.putAll(userManager.getCountryISOCodesById());
	}
	protected final void populateDobMonths() {
		for (int i=1; i<=12; i++) {
			dobMonths.put(i, String.valueOf(i));
		}
	}
	protected final void populateDobDays() {
		for (int i=1; i<=31; i++) {
			dobDays.put(i, String.valueOf(i));
		}
	}
	protected final void populateDobYears() {
		for (int i=1900; i<=1996; i++) {
			dobYears.put(i, String.valueOf(i));
		}
	}
	protected final void populateBjlNumberTypes() {
		bjlnumberabove.put("SSN", "SSN(Social Security Number)");
		bjlnumberabove.put("Tax ID", "EIN(Tax ID Number)");
		
	}
	
	// map getters	
	
	@ModelAttribute("dobMonths")
	public final TreeMap<Integer,String> getDobMonths() {
		return dobMonths;
	}
	@ModelAttribute("dobDays")
	public final TreeMap<Integer,String> getDobDays() {
		return dobDays;
	}
	@ModelAttribute("dobYears")
	public final TreeMap<Integer,String> getDobYears() {
		return dobYears;
	}
	@ModelAttribute("usStates")
	public final HashMap<String, String> getStates() {
		return userManager.getUSStates();
	}	
	@ModelAttribute("directoryStates")
	public final HashMap<String, String> getDirectoryState() {
		return directoryState;
	}	
	@ModelAttribute("countryCodes")
	public final HashMap<Integer, String> getCountryCodes() {
		return countryISOCodesById;
	}
	@ModelAttribute("cardExpirationMonths")
	public final TreeMap<Integer, String> getCardExpirationMonths() {
		return cardExpirationMonths;
	}
	@ModelAttribute("cardExpirationYears")
	public final TreeMap<Integer, String> getCardExpirationYears() {
		return cardExpirationYears;
	}
	@ModelAttribute("amountList")
	public final TreeMap<Double,String> getdonationamount(){
		return donationamount;
	}
	@ModelAttribute("frequencyList")
	public final HashMap<Integer,String> getdonationfrequencylist(){
		return donatefrequency;
		
	}
	@ModelAttribute("USStatelist")
	public final TreeMap<String, String> getUSStateList(){
		return usdirectorystate;
		
	}
	@ModelAttribute("bjlNumberAbove")
	public final TreeMap<String, String> getNumberAbove()
	{
		return bjlnumberabove;
	}
	/**
	 * General purpose email function
	 * 
	 * @param map
	 * @param velocityTemplate
	 * @param emailFrom
	 * @param emailTo
	 * @param subject
	 */
	protected final void sendEmailVelocity(HashMap<String,Object> map, String velocityTemplate, String emailFrom, String emailTo, String subject) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(emailFrom);
		msg.setTo(emailTo);
		msg.setSubject(subject);
		 
	     String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, velocityTemplate, map);
    
		msg.setText(text);
		try {
			logger.info("sender Started to send");
			mailSender.send(msg); 
			logger.info("sender completed sending");
		} 
		catch (Exception e) 
		{
			logger.error("Problem sending email", e);
		}		
	}	
	protected final void sendEmailVelocity(HashMap<String,Object> map, String velocityTemplate, String emailFrom, String emailTo[], String subject) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(emailFrom);
		msg.setTo(emailTo);
		msg.setSubject(subject);
		
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, velocityTemplate, map);
    	
		msg.setText(text);
		try {
			mailSender.send(msg);			
		} catch (Exception e) {
			logger.error("Problem sending email", e);
		}		
	}	
	
	//Sending mail with html
	protected final void sendHtmlEmailVelocity(HashMap<String,Object> map, String velocityTemplate, String emailFrom, String[] emailTo, String subject) {
		try{
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom(emailFrom);
			helper.setTo(emailTo);
			
			StringWriter html = new StringWriter();
			VelocityEngineUtils.mergeTemplate( velocityEngine, velocityTemplate, map, html);
			html.close();
			    
			helper.setText(html.toString(), true);			    
			helper.setSubject(subject);			
			javaMailSender.send(message);
			
		}catch (Exception e) {
			logger.error("Exception while sending mail :"+e.getLocalizedMessage());
		}
		
	}	
	
	// this might be more useful if we could include a map
	protected final void sendAdminProblemEmail(String message) {
		HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("message", message);
        
        String subject = "FUNNEL: Admin Problem";
        
        sendEmailVelocity(map, tplThankYou, emailFromNoReply, emailToAdmin, subject);
	}
	
	// this might be more useful if we could include a map
	protected final void sendSearchExceptionEmail(String userName, String customerName, int transactionId, Short ccLast4) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("ccName", (customerName != null && customerName.trim().length() > 0 ? customerName : "Tim Koster"));
		map.put("transactionId", transactionId);
		map.put("ccLast4", ccLast4);
		
        String subject = "Search Exception: An error occured in premium search";
        
        sendEmailVelocity(map, tplSearchException, emailFromNoReply, emailToAdmin, subject);
	}
}