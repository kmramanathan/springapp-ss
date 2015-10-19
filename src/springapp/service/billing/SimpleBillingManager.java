package springapp.service.billing;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.searchsystems.limestone.SsTimePeriods;

import org.apache.commons.httpclient.CircularRedirectException;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import springapp.crypt.Crypt;
import springapp.domain.CreditCard;
import springapp.domain.FRSubscription;
import springapp.domain.Rate;
import springapp.domain.Subscription;
import springapp.domain.Transaction;
import springapp.domain.User;
import springapp.repository.CreditCardDao;
import springapp.repository.RateDao;
import springapp.repository.SubscriptionDao;
import springapp.repository.TransactionDao;
import springapp.service.NeonService;
import springapp.service.user.UserManager;
import springapp.web.funnel.donationform.DonateFormCommand;

@Service("billingManager")
public class SimpleBillingManager extends NeonService implements BillingManager {
	@Autowired
	private TransactionDao transactionDao;
	@Autowired
	private CreditCardDao creditCardDao;
	@Autowired
	private SubscriptionDao subscriptionDao;
	@Autowired
	private RateDao rateDao;
	
	@Autowired
	private UserManager userManager;	
	
	private static final boolean authorizeNetTestMode = false;
	
	private static final String authorizeNetUrl       = "https://secure.authorize.net/gateway/transact.dll";
	private static final String authorizeNetLogin     = "pacificir2";
	private static final String authorizeNetPassword  = "7jj5Rw20kNCSoGlc";
	private static final String authorizeNetDelimChar = "#";
	
	// this is hardcoded in the DB
	private static final int internalCountryCodeUS = 224;

	public List<Map<String,Object>> getModifiedCards() {
		return creditCardDao.getModifiedCards();
	}
	public List<Map<String,Object>> getExpiredCards() { 
		return creditCardDao.getExpiredCards();
	}
	
	public Transaction getTransaction(int transactionId) {
		return transactionDao.getTransaction(transactionId);
	}
	
	/**
	 * Run a generic transaction. This will run the txn first, then store it in
	 * the database. A DB failure will thus need to be voided.
	 * 
	 * The return value is the DB's primary key (transaction_id). This is NOT the same
	 * as the "transaction ID" returned from the processor (bank_transaction_code).
	 * 
	 * TODO: error handling. The transaction can succeed but the DB store could fail,
	 * so we need to either void the txn or notify someone to fix it. The only way
	 * around this is to do all txns in 2 stages (auth then capture).
	 */

	// facade methods
	//changed by shahul - added two extra arg for all Transaction method
	@Transactional
	public Transaction runTransactionNoCvv(User user, CreditCard creditCard, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, short category, short subCategory) {
		return runTransactionAuthorizeNetImpl(user, creditCard, "", amount, txnType, description, invoiceNumber, category, subCategory, authorizeNetTestMode);
	}
	@Transactional
	public Transaction runTransactionNoCvv(User user, CreditCard creditCard, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, short category, short subCategory, boolean testMode) {
		return runTransactionAuthorizeNetImpl(user, creditCard, "", amount, txnType, description, invoiceNumber, category, subCategory, testMode);
	}
	
	public Transaction runTransactionWithCvv(User user, CreditCard creditCard, String cvv, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, short category, short subCategory) {
		return runTransactionAuthorizeNetImpl(user, creditCard, cvv, amount, txnType, description, invoiceNumber, category, subCategory, authorizeNetTestMode);
	}
	public Transaction runTransactionWithCvv(User user, CreditCard creditCard, String cvv, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, short category, short subCategory, boolean testMode) {
		return runTransactionAuthorizeNetImpl(user, creditCard, cvv, amount, txnType, description, invoiceNumber, category, subCategory, testMode);
	}
	
	/* March 12 2014 - Udhay  
	  For implementing Credit card validation on Register page.*/
	public Transaction runCreditCardValidation(User user, CreditCard creditCard, String cvv, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, short category, short subCategory) {
		return runValidateCreditCard(user, creditCard, cvv, amount, txnType, description, invoiceNumber, category, subCategory, authorizeNetTestMode);
	}
	
	/**
	 * Verify a card (i.e., run an AUTH_ONLY transaction against it).
	 * 
	 * @param user
	 * @param creditCard
	 * @param cvv
	 * @param amount
	 * @return status
	 */
	public Transaction verifyCardWithCvv(User user, CreditCard creditCard, String cvv, BigDecimal amount) {
		int invoiceNumber = 101;
		String description = "Card Verification";
		
		short category=0;
		short subCategory=0;
		
		/* March 12 2014 - Udhay
		 * For implementing Credit card validation on Register page.*/
		
		return runCreditCardValidation(user, creditCard, cvv, amount, TxnType.AUTH_ONLY, description, invoiceNumber, category, subCategory);
		
	}
	
	// implementation
	protected Transaction runTransactionAuthorizeNetImpl(User user, CreditCard card, String cvv, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, short category, short subCategory, boolean testMode) {
		/*
		 * 1) set up request for authorize.net
		 */
		//logger.info("card: " + card.getLastDigits());
		HashMap<String,String> map = buildMapForSaleTxn(user, card, cvv, amount, txnType, description, invoiceNumber, testMode);
		NameValuePair[] reqData = buildAuthorizeNetTxnReqDataFromMap(map);
		
		/*
		 * 2) build the http request
		 */
		HttpClient client = setupHttpClient();
		
		/*
		 * 3) send the req to the processor
		 */
		String responseData[] = sendHttpReqToAuthorizeNet(client, reqData);
		
		/*
		 * 4) parse the info returned from the processor
		 * this generic method calls the processor specific method
		 */
		Transaction t = parseAuthorizeNetResponse(responseData, user, card, amount, category, subCategory); 
		
		/*
		 * 5) store the response in the db
		 */
		transactionDao.saveTransaction(t);
		
		if (t.getTransactionStatusId() != txnApproved) 
		{
			logger.error("_txn " + t.getTransactionId() + " not approved: " + t.getBankResponseReasonText());			
		}		
		return t;
	}
	protected Transaction runValidateCreditCard(User user, CreditCard card, String cvv, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, short category, short subCategory, boolean testMode) {
		/*
		 * 1) set up request for authorize.net
		 */
		//logger.info("card: " + card.getLastDigits());
		HashMap<String,String> map = buildMapForSaleTxn(user, card, cvv, amount, txnType, description, invoiceNumber, testMode);
		NameValuePair[] reqData = buildAuthorizeNetTxnReqDataFromMap(map);
		
		/*
		 * 2) build the http request
		 */
		HttpClient client = setupHttpClient();
		
		/*
		 * 3) send the req to the processor
		 */
		String responseData[] = sendHttpReqToAuthorizeNet(client, reqData);
		
		/*
		 * 4) parse the info returned from the processor
		 * this generic method calls the processor specific method
		 */
		
		Transaction t = parseAuthorizeNetResponse(responseData, user, card, amount, category, subCategory); 
			
		/*
		 * 5) store the response in the db
		 */
		if (t.getTransactionStatusId() == txnApproved) 
		{
			boolean bVoidTransctn = voidTransaction(card, t);
			if(bVoidTransctn)
				logger.info("Void Transaction_NwCfnrSrch:: Success");
			else
				logger.info("Void Transction_NwCfnrSrch:False");
		}
		if (t.getTransactionStatusId() != txnApproved) 
		{
			logger.error("ValidateCC_NotApprvd:: " + t.getTransactionId() + " not approved: " + t.getBankResponseReasonText());			
		}
		return t;
	}
	public boolean voidTransaction(CreditCard cc, Transaction origTxn) {
		return voidTransactionAuthorizeNetImpl(cc, origTxn);
	}
	
	protected boolean voidTransactionAuthorizeNetImpl(CreditCard cc, Transaction origTxn) {
		/*
		 * differences here:
		 * 1) all we really need is the orig txn. that contains all the user/card
		 *    info as well as the bank txn code which we need to void.
		 * 2) we need to update our old txn rather than store a new txn.
		 * 3)  
		 */
		User user = userManager.getUserByUserId(origTxn.getUserId());
		
		/*
		 * 1) set up request for authorize.net
		 */
		HashMap<String,String> map = new HashMap<String,String>();
		addAuthorizeNetFixedFields(map);
		setAdditionalTxnInfo(map, TxnType.VOID, cc, origTxn);
		NameValuePair[] reqData = buildAuthorizeNetTxnReqDataFromMap(map);

		/*
		 * 2) build the http request
		 */
		HttpClient client = setupHttpClient();
		
		/*
		 * 3) send the req to the processor
		 */
		String responseData[] = sendHttpReqToAuthorizeNet(client, reqData);
		
		/*
		 * 4) parse the info returned from the processor
		 * note that we will not be saving this txn.
		 */
		Transaction voidTxn = new Transaction();
		parseAuthorizeNetResponse(origTxn, responseData);
		//parseAuthorizeNetResponse(voidTxn, responseData);
		if (origTxn.getTransactionStatusId() == txnApproved) {
			/*
			 * 5) update the orig txn and save it
			 */
			
			//origTxn.setCost("0.01");
			//origTxn.setBankResponseReasonText(voidTxn.getBankResponseReasonText());
			
			transactionDao.saveTransaction(origTxn);			
			return true;
		} else {
			// void failure, log it?
			logger.error("Void failure_Lne:261::" + origTxn.getTransactionId() + ": " + origTxn.getBankResponseReasonText());
			return false;
		}		
	}
	
	protected HttpClient setupHttpClient() {
		HttpClient client = new HttpClient();
		HttpClientParams params = client.getParams();

		// set client params
		params.setParameter(HttpClientParams.MAX_REDIRECTS, new Integer(15));
		params.setParameter(HttpClientParams.SO_TIMEOUT, new Integer(15 * 1000));
		
		// initial connection timeout
		// separate from overall socket timeout above
		client.getHttpConnectionManager().getParams().setConnectionTimeout(15 * 1000);		
		
		return client;
	}
	
	protected boolean checkForTestCard(CreditCard card) {
		String ccNum = Crypt.decrypt(card.getNumber());
		if (ccNum.equals("5432123454321234") && card.getName().equals("Ramanathan Kumarappan")) {
			return true;
		}
		return false;
	}

	// convert map into array for httpclient
	protected NameValuePair[] buildAuthorizeNetTxnReqDataFromMap(HashMap<String,String> map) {
		// dump these all into the list
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();		
		for (String s : map.keySet()) {		
			list.add(new NameValuePair(s, map.get(s)));
		}
		
		// convert list back into array		
		return list.toArray(new NameValuePair[0]);
	}
		
	// build map from various objects
	// the map is an intermediate step between db & httpclient
	protected HashMap<String,String> buildMapForSaleTxn(User user, CreditCard card, String cvv, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, boolean testMode) {		
		String[] name = card.getName().split(" ");
		String firstName = name[0];
		String lastName = name[name.length - 1];
		
		HashMap<String,String> map = new HashMap<String,String>();

		// check for test req
		if (testMode || checkForTestCard(card)) {
			map.put("x_test_request", "TRUE");
		}
		
		// set basic fields
		addAuthorizeNetFixedFields(map);
		
		// set txn type
		setAdditionalTxnInfo(map, txnType, card, null);
		
		// variable required fields (request dependent)		
		map.put("x_amount",      amount.toPlainString());
		map.put("x_card_num",    Crypt.decrypt(card.getNumber()));
		map.put("x_exp_date",    makeExpDate6(card));
		
		// variable optional fields (request dependent)
		map.put("x_first_name",  firstName);
		map.put("x_last_name",   lastName);		
		map.put("x_cust_id",     String.valueOf(user.getUserId()));
		map.put("x_description", description);
		
		// XXX fix these		
		map.put("x_card_code",   cvv);
		map.put("x_invoice_num", invoiceNumber.toString());		
		
		// XXX fix this hard code BS
		// avs breaks for int'l cards apparently
		if (card.getCountryId() == internalCountryCodeUS) {
			map.put("x_country",     "US");			

			map.put("x_company",     card.getCompany());
			map.put("x_address",     card.getAddress1());
			logger.info("x_address is :"+card.getAddress1());
			map.put("x_city",        card.getCity());
			map.put("x_state",       card.getState());
			map.put("x_zip",         card.getPostalCode());
			map.put("x_phone",       card.getPhone());					
			map.put("x_fax",         card.getFax());
			
		} else {
			HashMap<Integer, String> countryCodes = userManager.getCountryISOCodesById();
			String code = countryCodes.get(card.getCountryId());
			map.put("x_country", code);
		}
		
		return map;
	}

	// build 6 digit expiration date for use below
	protected String makeExpDate6(CreditCard card) {
		String sExpDate6 = "";
		if (card.getExpMonth() < 10) {
			sExpDate6 += "0";
		}
		sExpDate6 += card.getExpMonth();
		sExpDate6 += card.getExpYear();
		return sExpDate6;
	}

	/**
	 * Add fields which are required on EVERY Authorize.Net txn.
	 * 
	 * @param map Map to add fields to
	 */
	protected void addAuthorizeNetFixedFields(HashMap<String,String> map) {
		// fixed required fields (same on every txn)		
		map.put("x_login",            authorizeNetLogin);
		map.put("x_tran_key",         authorizeNetPassword);
		map.put("x_version",          "3.1");
		map.put("x_relay_response",   "FALSE");
		map.put("x_delim_data",       "TRUE");
		map.put("x_delim_char",       authorizeNetDelimChar);

		// fixed optional fields (same on every txn)
		map.put("x_method",           "CC");
		map.put("x_duplicate_window", "0");
	}
	
	/**
	 * Set additional fields based on the transaction type. 
	 *  
	 * @param map Field map
	 * @param txnType Transaction Type
	 * @param cc Credit card
	 * @param origTxn Original transaction (used for void/credit/prior auth, null for sales)
	 */
	protected void setAdditionalTxnInfo(HashMap<String,String> map, TxnType txnType, CreditCard cc, Transaction origTxn) {
		switch (txnType) {
		case AUTH_CAPTURE:
			map.put("x_type", "AUTH_CAPTURE");
			break;
		
		case AUTH_ONLY:
			map.put("x_type", "AUTH_ONLY");
			break;
		
		case VOID:
			map.put("x_type", "VOID");
			map.put("x_trans_id", origTxn.getBankTransactionCode());
//			map.put("x_amount",   origTxn.getCost());
//			map.put("x_card_num", Crypt.decrypt(cc.getNumber()));
//			map.put("x_exp_date", makeExpDate6(cc));
			logger.info("Void__X_TYPE@:VOid and x_trans_id iss::" +origTxn.getBankTransactionCode());
			break;

		case CREDIT:
			map.put("x_type", "CREDIT");
			map.put("x_trans_id", origTxn.getBankTransactionCode());
			// sigh.. this uses short
			//map.put("x_card_num", origTxn.getCcLastDigits());
			break;
		
		case PRIOR_AUTH_CAPTURE:
			map.put("x_type", "PRIOR_AUTH_CAPTURE");
			map.put("x_trans_id", origTxn.getBankTransactionCode());
			break;
		
		case CAPTURE_ONLY:
			// 6 digit approval code
			map.put("x_type", "CAPTURE_ONLY");
			map.put("x_auth_code", origTxn.getBankApprovalCode());
			break;
		}
	}
	
	/*
	 * httpclient can throw a variety of exceptions so we handle those
	 * here. if response data is null, there was a failure.
	 */
	private String[] sendHttpReqToAuthorizeNet(HttpClient client, NameValuePair[] reqData) {
		PostMethod method = new PostMethod(authorizeNetUrl);			
		method.setRequestBody(reqData);
		
		String responseData[] = null;
		try {
			//logger.debug("Attempting to run transaction");
			
			int status = client.executeMethod(method);
		
			String responseBody = method.getResponseBodyAsString();
			//logger.info(responseBody);

			responseData = responseBody.split(authorizeNetDelimChar, -1);
			//logger.info("responseData size: " + responseData.length);
		} catch (IllegalArgumentException e) {
			logger.error("HttpClient runtime exception", e);
		} catch (URIException e) {
			logger.error("Invalid URI", e);
		} catch (NullPointerException e) {
			logger.error("Null pointer (URI is null?)", e);
		} catch (CircularRedirectException e) {
			logger.error("Circular redirect", e);
		} catch (HttpException e) {
			logger.error("Fatal protocol violation", e);
		} catch (ConnectTimeoutException e) {
			logger.error("Timeout: connect", e);
		} catch (InterruptedIOException e) {
			logger.error("Timeout: interrupt", e);
		} catch (IOException e) {
			logger.error("Fatal transport error", e);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}	
		return responseData;
	}
	
	/*
	 * parse response data from above
	 */	
	private Transaction parseAuthorizeNetResponse(String data[], User user, CreditCard card, BigDecimal amount, short category, short subCategory) {
		Transaction t = new Transaction();
		
		// set these first
		t.setUserId(user.getUserId());
		t.setCost(amount.toPlainString());
		t.setCcName(card.getName());
		t.setCcLastDigits(card.getLastDigits());
		t.setCcExpMonth(card.getExpMonth());
		t.setCcExpYear(card.getExpYear());

		// do the rest of it
		parseAuthorizeNetResponse(t, data);
		
		//changed by shahul - setting email
		if(t.getEmail().isEmpty())
		{
			t.setEmail(user.getEmail());
		}
		
		//changed by shahul - setting category
		t.setSearchCategoryId(category);
		t.setSearchSubCategoryId(subCategory);
		return t;
	}
	
	private void parseAuthorizeNetResponse(Transaction t, String data[]) {				
		// set fields from response
		t.setBankResponseCode       (data[0]);
		t.setBankResponseSubcode    (data[1]);
		t.setBankResponseReasonCode (data[2]);
		t.setBankResponseReasonText (data[3]);
		t.setBankApprovalCode       (data[4]);
		t.setBankAvsResultCode      (data[5]);
		t.setBankTransactionCode      (data[6]);

		// XXX fix this
		// i think these are all just echoed back from the processor so we
		// can set them in the main method above
		/*
		t.setInvoiceNum        (data[7]);
		t.setDescription       (data[8]);
		t.setTransactionAmount (new BigDecimal(data[9]));		
		t.setTransactionType   (data[11]);
		*/
		
		// we store first/last names on users but full names on cards & txns.
		//t.setCcName (data[13] + " " + data[14]);

		// company, country & fax are on the card but not txn,
		// are they sent to authorize.net but not echoed?
		
		//t.setCcCompany   (data[15]);		
		
		// XXX how to handle this?
		// address2 is on the card & txn, but it doesn't appear to be used or echoed by authorize.net
		t.setCcAddress1   (data[16]);
		t.setCcAddress2   ("");		
		
		t.setCcCity      (data[17]);
		t.setCcState     (data[18]);		
		t.setCcPostalCode       (data[19]);
		
		//t.setCcCountry   (data[20]);
		t.setCcPhone     (data[21]);
		//t.setFax       (data[22]);
		t.setEmail     (data[23]);

		// XXX are some of these fields only associated with the card?
//		if (globalTestMode) {
//			t.setCcCompany   (data[15]);		
//			t.setCcCountry   (data[20]);
//			t.setFax       (data[22]);			
//		}
		
		//t.setMd5                (data[37]);
		t.setBankCvvResponseCode    (data[38]);
		t.setBankCavvResponseCode   (data[39]);
		
		// set our own status id & log the txn		
		short dataTxnStatus = Short.parseShort(t.getBankResponseCode());
		short txnStatusId;
		String logmsg = t.getTransactionId() + " : " + t.getCost() + ":" + t.getBankResponseReasonText();
		switch (dataTxnStatus) {
		case 1:
			txnStatusId = txnApproved;
			logger.info("sbm_CC transaction approved_Lne565: " + logmsg);
			break;
		case 2:
			txnStatusId = txnDeclined;
			logger.info("sbm_CC transaction declined_Lne565: " + logmsg);
			break;
		default:
			txnStatusId = txnError;
			logger.info("sbm_CC transaction error_Lne565: " + logmsg);
			break;
		}
		t.setTransactionStatusId(txnStatusId);
	}
	
	@Transactional
	public Transaction billSubscription(User u, CreditCard cc, Subscription s, String signupFor) {
		return this.billSubscription(u, cc, s, signupFor, false);
	}
	
	@Transactional
	public Transaction billSubscription(User u, CreditCard cc, Subscription s, String signupFor, Boolean ccTestMode) {
		// get rate info
		Rate r = rateDao.getRateByRateId(s.getRateId());
		BigDecimal amount;
		String description = r.getRateDescription();

		short category=40;
		short subCategory=41;
		
		// note that initial bill is meaningless if initial units == 0
		if ((r.getInitialUnits() > 0) && s.getInitDate().equals(s.getNextBillDate())) {
			// do initial bill
			amount = r.getInitialPrice();
			description += " (Initial Bill)";
		} else {			
			// do recurring bill
			amount = r.getRecurringPrice();
			description += " (Recurring Bill)";
		}
		
		//Displays description in admin page
		if(signupFor != null){
			if(signupFor.equalsIgnoreCase("findpeople")){
				subCategory = 42;
			}
			else if(signupFor.equalsIgnoreCase("DBJ")){
				category = 20;
				BigDecimal monthly = new BigDecimal("9.95");
				if(amount.equals(monthly)){
					subCategory = 201;
				}else{
					subCategory = 202;
				}
			}
		}
		
		logger.info("Amount to be charge "+amount+" For user name : "+u.getUsername()+" with cat, subcat : "+category+","+subCategory);
		// charge card
		Integer invoiceNumber = u.getUserId();
		Transaction t = runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, invoiceNumber, category, subCategory, ccTestMode);		
		return t;
		}
	
	// note that initial bill is meaningless if initial units == 0
	public void advanceSubscription(Subscription s) {
		// get rate info
		Rate r = rateDao.getRateByRateId(s.getRateId());
		String interval;
		SsTimePeriods tp;
		
		if ((r.getInitialUnits() > 0) && s.getInitDate().equals(s.getNextBillDate())) {
			// do initial bill
			tp = rateDao.getTimePeriod(r.getInitialPeriod());
			interval = r.getInitialUnits() + " " + tp.getTimePeriodName();	
		} else {
			// do recurring bill
			tp = rateDao.getTimePeriod(r.getRecurringPeriod());
			interval = r.getRecurringUnits() + " " + tp.getTimePeriodName();	
		}		

		// build interval string
		subscriptionDao.advanceSubscriptionBillDates(s, interval);
	}
	
	@Transactional
	public FRSubscription addFRSubscription(User user, Rate rate, int txnid) {
		FRSubscription frsub = new FRSubscription();
		frsub.setPlanid(rate.getRateId()); 
		frsub.setPlantypeid(rate.getRecurringUnits()); // 1. findpeople , 2. criminal , 3. combined
		frsub.setStatus(0); // 0 - pipeline , 1 - active , 2 - expired
		frsub.setUserid(user.getUserId());
		frsub.setDaysavailable(rate.getRecurringPeriod());
		frsub.setTransactionid(txnid);
		if(frsub.getPlantypeid() == 3){
			frsub.setCriminalsearches(rate.getInitialPeriod());
			frsub.setFindpeoplesearches(rate.getInitialPeriod());
			frsub.setAvailablesearches(frsub.getFindpeoplesearches()+frsub.getCriminalsearches());
		}else if(frsub.getPlantypeid() == 2){
			frsub.setAvailablesearches(rate.getInitialPeriod());
			frsub.setCriminalsearches(rate.getInitialPeriod());
			frsub.setFindpeoplesearches(0);				
		}else{
			frsub.setAvailablesearches(rate.getInitialPeriod());
			frsub.setCriminalsearches(0);
			frsub.setFindpeoplesearches(rate.getInitialPeriod());				
		}
		//initially plan will be in pipe line
		frsub.setEnddate(null);
		frsub.setStartdate(null);
		frsub.setPrevdate(null);
		frsub.setNextdate(null);
		
		
		frsub = subscriptionDao.createFRSubscription(frsub);
	return frsub;
	}
}