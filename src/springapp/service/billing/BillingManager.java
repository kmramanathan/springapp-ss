package springapp.service.billing;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import springapp.domain.CreditCard;
import springapp.domain.FRSubscription;
import springapp.domain.Rate;
import springapp.domain.Subscription;
import springapp.domain.Transaction;
import springapp.domain.User;

public interface BillingManager {
	// note: these are authorize.net's names for types
	public enum TxnType {
		AUTH_CAPTURE, AUTH_ONLY, CAPTURE_ONLY, CREDIT, PRIOR_AUTH_CAPTURE, VOID
	};
	
	public static final int txnApproved = 10;
	public static final int txnDeclined = 11;
	public static final int txnError = 12;
	
	public Transaction getTransaction(int transactionId);
	
	//changed by shahul - need for add category
	public Transaction runTransactionNoCvv(User user, CreditCard creditCard, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, short category, short subCategory);
	public Transaction runTransactionNoCvv(User user, CreditCard creditCard, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, short category, short subCategory, boolean testMode);

	public Transaction runTransactionWithCvv(User user, CreditCard creditCard, String cvv, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, short category, short subCategory);
	public Transaction runTransactionWithCvv(User user, CreditCard creditCard, String cvv, BigDecimal amount, TxnType txnType, String description, Integer invoiceNumber, short category, short subCategory, boolean testMode);
	
	public Transaction verifyCardWithCvv(User user, CreditCard creditCard, String cvv, BigDecimal amount);
	public boolean voidTransaction(CreditCard cc, Transaction origTxn);
	/*
	public void addInvoice(Invoice i);
	public void addInvoiceDetail(Invoice i, InvoiceDetail id);
	public void addInvoiceDetail(int invoiceId, InvoiceDetail id);
	
	public void addPayment(Payment p);
	
	public void applyPayment(Payment p);
	public void applyPaymentsForUser(User u);
	*/
	
	public List<Map<String,Object>> getModifiedCards();
	public List<Map<String,Object>> getExpiredCards();
	
	public void advanceSubscription(Subscription s);
	
	public Transaction billSubscription(User u, CreditCard cc, Subscription s, String signupFor);	
	public Transaction billSubscription(User u, CreditCard cc, Subscription s, String signupFor, Boolean ccTestMode);
	
	public FRSubscription addFRSubscription(User user, Rate rate, int txnId);
	
}