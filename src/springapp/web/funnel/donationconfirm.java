package springapp.web.funnel;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import junit.framework.Assert;



import net.authorize.Environment;
import net.authorize.Merchant;
import net.authorize.arb.Result;
import net.authorize.arb.TransactionType;
import net.authorize.data.Address;
import net.authorize.data.Customer;
import net.authorize.data.arb.PaymentSchedule;
import net.authorize.data.arb.Subscription;
import net.authorize.data.arb.SubscriptionUnitType;
import net.authorize.data.xml.Payment;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.tomcat.util.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;


import springapp.crypt.Crypt;
import springapp.domain.User;
import springapp.domain.CreditCard;
import springapp.domain.Transaction;
import springapp.service.NeonValidator;
import springapp.service.user.CreditCardValidator;
import springapp.service.billing.BillingManager;
import springapp.service.billing.BillingManager.TxnType;
import springapp.web.funnel.NewBJLSearchForm.BJLSearchFormCommand;
import springapp.web.funnel.donationform.DonateFormCommand;
import springapp.web.funnel.donationform.DonateFormValidator;
import springapp.web.funnel.NewPurchaseForm.NewPurchaseFormCommand;
import springapp.web.funnel.NewSearchForm.SearchFormCommand;
import springapp.web.funnel.RegisterForm.RegisterFormCommand;

@Controller
public class donationconfirm extends AbstractFunnelController {
	@Autowired
	private BillingManager billingManager;
	

	private static Result<net.authorize.arb.Transaction> arb_result;
	private static net.authorize.arb.Transaction arb_transaction;
	private static final String logid="pacificir2";
	private static final String transactionkey="7jj5Rw20kNCSoGlc";
	private static final String baseUrl="https://api.authorize.net/xml/v1/request.api";
	private static final String xmlBaseUrl="https://api.authorize.net/xml/v1/request.api";
	private static final String cardPresentUrl="https://api.authorize.net/xml/v1/request.api";
	private SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
	private Date date;
	
	@RequestMapping(value = "/funnel/DConfirmation.do", method = RequestMethod.GET)
	public String donateconfirm(HttpSession session,ModelMap map){
		
		DonateFormCommand dfc=(DonateFormCommand) session.getAttribute("DonateFormCommand");
		Double amount=dfc.getDonationamount();
		String val="500.00";
		BigDecimal big=new BigDecimal(amount);
		logger.info("donationamouttest:"+big);
		map.addAttribute("donation",dfc);
		
		String ccNumber = dfc.getCcNumber();
		String ccLast4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
		map.addAttribute("ccLast4", ccLast4 );
		
		return donationconfirmview;
		
	}
	
	private CreditCard generateCCard(DonateFormCommand dfc) {
		CreditCard cc = new CreditCard();
		
		cc.setName(dfc.getName());
		cc.setCompany(dfc.getCompany());
		cc.setAddress1(dfc.getAddress());
		
		cc.setCity(dfc.getCity());
		cc.setState(dfc.getState());
		cc.setPostalCode(dfc.getPostalCode());
		
		logger.info("countryCode: " + dfc.getCountryCode());
		Integer cid = userManager.getCountryIdByCode(dfc.getCountryCode());
		logger.info("countryId: " + cid);
		cc.setCountryId(cid.shortValue());
		//Short ccid=224;
		//cc.setCountryId(ccid);
		String ccNumber = dfc.getCcNumber();
		String last4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
		cc.setNumber(Crypt.encrypt(ccNumber));
		cc.setLastDigits(Short.parseShort(last4));
		
		cc.setExpMonth(dfc.getCcExpMonth().shortValue());
		cc.setExpYear(dfc.getCcExpYear().shortValue());
		cc.setVerified(true);
		
		return cc;
	}	
	
	private User generateUser() {
		User u = new User();
		
		u.setUsername("Donation");
		u.setFirstName("Sean");
		u.setLastName("Dickenson");
		u.setEmail("donation@searchsystems.net");
		
        u.setPassword(DigestUtils.md5Hex("December13"));        
        
        u.setPassAnswer("Austin");        
        u.setNewsletter(true);  
        
		u.setNoExport(true);
		u.setNoPremium(false);
		u.setSubDirectoryUser(true);
    		
		return u;
	}
	
	
	@RequestMapping(value = "/funnel/DConfirmation.do", method = RequestMethod.POST)
	public String stringsubmit(
			HttpSession session,
			ModelMap map
			){
		DonateFormCommand dfc=(DonateFormCommand) session.getAttribute("DonateFormCommand");
		String fname="";
		String lname="";
		String str=dfc.getName();
		StringTokenizer token=new StringTokenizer(str," ");	
		while (token.hasMoreTokens()) {
			fname=token.nextToken();
			lname=token.nextToken();
			
		}
		
		String check="1";
		if(dfc.getFrequency().equals(check))
		{
			logger.info("One time Donation");
		CreditCard card=new CreditCard();
		Double amount=dfc.getDonationamount();
		BigDecimal big=new BigDecimal(amount);
		card=generateCCard(dfc);
		User u;
		u=generateUser();
		Transaction t=new Transaction();
		short category = 0;
		short subCategory=0;
		t=billingManager.runTransactionWithCvv(u,card,dfc.getCcAuthCode(),big,TxnType.AUTH_CAPTURE,"Donation",0,category,subCategory);
		//public Transaction runTransactionWithCvv(User user, CreditCard creditCard, String cvv, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, short category, short subCategory, boolean testMode);
		logger.info(t.getBankResponseCode()+"responsecode");
		logger.info(t.getBankResponseReasonCode()+"responsereasoncode");
		logger.info(t.getBankResponseReasonText()+"responsereasontext");
		logger.info(t.getBankTransactionCode()+"transactioncode");
		logger.info(t.getTransactionStatusId()+"transactionID");
		map.addAttribute("donation", dfc);
		if(t.getTransactionStatusId() != billingManager.txnApproved){
			return donationerror;
			//return "funnel/DonationRecipt";
			
		}
		else{
		
			
			return "funnel/DonationRecipt";
			
		}
		}
		else
		{
		
			logger.info("Recurring Monthly Donation");
			CreditCard card=new CreditCard();
			Double amount=dfc.getDonationamount();
			BigDecimal big=new BigDecimal(amount);
			card=generateCCard(dfc);
			User u;
			u=generateUser();
			Transaction t=new Transaction();
			short category = 0;
			short subCategory=0;
			t=billingManager.runTransactionWithCvv(u,card,dfc.getCcAuthCode(),big,TxnType.AUTH_CAPTURE,"Donation",0,category,subCategory);
			//public Transaction runTransactionWithCvv(User user, CreditCard creditCard, String cvv, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, short category, short subCategory, boolean testMode);
			logger.info(t.getBankResponseCode()+"responsecode");
			logger.info(t.getBankResponseReasonCode()+"responsereasoncode");
			logger.info(t.getBankResponseReasonText()+"responsereasontext");
			logger.info(t.getBankTransactionCode()+"transactioncode");
			logger.info(t.getTransactionStatusId()+"transactionID");
			try{
				
				
				date=simple.parse("2010-07-25");
				logger.info("Parse Date Format:"+date);
				}
				catch (ParseException e) {
					logger.info("Exception parse date:"+e);
				}
			String currentdate=simple.format(new Date());
			
			Environment environment=Environment.createEnvironment(baseUrl, xmlBaseUrl, cardPresentUrl);
			Merchant merchant=Merchant.createMerchant(environment, logid, transactionkey);
			TransactionType transactionType=TransactionType.CREATE_SUBSCRIPTION;
			//subscription unit types
			
			// payment schedule
			PaymentSchedule arb_pay_schedule=PaymentSchedule.createPaymentSchedule();
			arb_pay_schedule.setIntervalLength(1);
			arb_pay_schedule.setStartDate(currentdate);
			arb_pay_schedule.setSubscriptionUnit(SubscriptionUnitType.MONTHS);
			arb_pay_schedule.setTotalOccurrences(11);
			
			//credit card details to ARB
			net.authorize.data.creditcard.CreditCard credit_card=net.authorize.data.creditcard.CreditCard.createCreditCard();
			credit_card.setCreditCardNumber(dfc.getCcNumber());
			credit_card.setExpirationDate(dfc.getCcExpYear()+"-"+dfc.getCcExpMonth());
			
			Payment arb_pay=Payment.createPayment(credit_card);
			
			//bill info about customer
			net.authorize.data.xml.Address bill_info=net.authorize.data.xml.Address.createAddress();
			bill_info.setFirstName(fname);
			bill_info.setLastName(lname);
			net.authorize.data.xml.Customer customer=net.authorize.data.xml.Customer.createCustomer();
			customer.setBillTo(bill_info);
			//subscription
			Subscription subscription=Subscription.createSubscription();
			subscription.setAmount(big);
			subscription.setCustomer(customer);
			subscription.setPayment(arb_pay);
			subscription.setSchedule(arb_pay_schedule);
			subscription.setName("Donation subscription");
			net.authorize.arb.Transaction arbtransaction=net.authorize.arb.Transaction.createTransaction(merchant, transactionType, subscription);
			logger.info("Request XML:"+arbtransaction.getCurrentRequest().dump());
			//logger.info("response XML:"+arbtransaction.getCurrentResponse().dump());
			logger.info("Transction type:"+arbtransaction.getTransactionType().getValue());
			arb_transaction=merchant.createARBTransaction(transactionType.CREATE_SUBSCRIPTION, subscription);
			arb_result=(Result<net.authorize.arb.Transaction>)merchant.postTransaction(arb_transaction);
			//net.authorize.Result<net.authorize.arb.Transaction> arb_result =(net.authorize.Result<net.authorize.arb.Transaction>)merchant.postTransaction(arb_transaction);
		   
		     map.addAttribute("donation", dfc);
		    
			if(arb_result != null)
			{
		    logger.info("Result code:"+arb_result.getResultCode());
		   
		    logger.info("Result Subscription Id"+arb_result.getResultSubscriptionId());
		    //logger.info("info:"+arb_result.getTransaction());
		    logger.info("subscription status:"+arb_result.getSubscriptionStatus());	
			}
			
			
			if(arb_result.isOk() || t.getTransactionStatusId() == billingManager.txnApproved)
			{
				return "funnel/DonationRecipt";
			}
			
			else{
				return donationerror;
			}
			
		}
		
		
	}
	
	
}
