package springapp.service.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.searchsystems.limestone.BGCRequest;
import net.searchsystems.limestone.SsCreditCardsPeer;
import net.searchsystems.limestone.SsSubscriptionsPeer;
import net.searchsystems.limestone.SsUsersAdminActions;
import net.searchsystems.limestone.SsUsersAdminActionsPeer;
import net.searchsystems.limestone.SsUsersAdminHistory;
import net.searchsystems.limestone.SsUsersAdminHistoryPeer;
import net.searchsystems.limestone.SsUsersPeer;

import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springapp.crypt.Crypt;
import springapp.domain.AdminUserIPBean;
import springapp.domain.CorporateAccount;
import springapp.domain.CreditCard;
import springapp.domain.FRSubscription;
import springapp.domain.Invoice;
import springapp.domain.InvoiceDetail;
import springapp.domain.Payment;
import springapp.domain.Rate;
import springapp.domain.Subscription;
import springapp.domain.Transaction;
import springapp.domain.User;
import springapp.repository.CreditCardDao;
import springapp.repository.InvoiceDao;
import springapp.repository.PaymentDao;
import springapp.repository.RateDao;
import springapp.repository.SubscriptionDao;
import springapp.repository.UserDao;
import springapp.service.NeonPasswordMD5;
import springapp.service.NeonService;
import springapp.service.billing.BillingManager;
import springapp.service.billing.BillingManager.TxnType;
import springapp.service.search.SearchManager;
import springapp.service.user.UserManagerException.Type;

@Service("userManager")
public class SimpleUserManager extends NeonService implements UserManager {

	@Autowired
	private UserDao userDao;
	@Autowired
    private CreditCardDao creditCardDao;
	@Autowired
    private SubscriptionDao subscriptionDao;
	@Autowired
	private RateDao rateDao;
	@Autowired
	private InvoiceDao invoiceDao;
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private BillingManager billingManager;
	@Autowired
	private SearchManager searchManager;
	
	private static final HashMap<String, Integer> countryIdsByISOCode = new HashMap<String, Integer>();
	private static final HashMap<Integer, String> countryISOCodesById = new HashMap<Integer, String>();
	private static final HashMap<Integer, String> countryISOCodesByIdWithName = new HashMap<Integer, String>();

	public void init() {
		countryIdsByISOCode.putAll(userDao.getCountryIdsByISOCode());
		countryISOCodesById.putAll(userDao.getCountryISOCodesById());
		countryISOCodesByIdWithName.putAll(userDao.getCountryISOCodesById(true));
		logger.info("in UM.init(): " + countryIdsByISOCode.size() + ":" + countryISOCodesById.size());
		userDao.getUserByUserId(9694);
	}
    
    // our methods
    public User getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }
    public User getUserByUsername(String username) {
        return getUserByUsername(username, true);
    }
    public User getUserByUsername(String username, Boolean caseSensitive) {
        return userDao.getUserByUsername(username, caseSensitive);
    } 
    
    //Udhay Dec10-2014 
    public AdminUserIPBean getUserWithIP(int userId) {
    	
        return userDao.getUserWithIP(userId);
    } 
   /* public AdminUserIPBean getUserByUsernameWithIP(String username) {
        return getUserByUsernameWithIP(username, true);
    }
    public AdminUserIPBean getUserByUsernameWithIP(String username, Boolean caseSensitive) {
        return userDao.getUserByUsernameWithIP(username, caseSensitive);
    }
    
    */
    
    public List<User> getUsersByUsernameCaseInsensitive(String username) {
    	return userDao.getUsersByUsernameCaseInsensitive(username);
    }
    
    public User getUserByEmail(String email) {
    	return userDao.getUserByEmail(email);
    }
    public List<User> getUsersByEmail(String email) {
    	return userDao.getUsersByEmail(email);
    }
    
	/*
	 * there are 2 approaches to this: 
	 * 1) use a switch/case so that we only set the appropriate field. this is
	 * faster but couples things a bit too much to UserDao.QueryField.
	 * 2) set all fields to the search term and pass it on. we know the query
	 * field so the Dao will figure it out. but this could break if the term
	 * is too big for the field. on the other hand we may have a validator available.
	 */
    /*
    public List<User> findUsers(String searchTerm, QueryField searchField, boolean partialMatch) {
    	User user = new User();
    	if (partialMatch) {
    		searchTerm = "%" + searchTerm + "%";
    	}
    	switch (searchField) {
        case USERNAME:
    		user.setUsername(searchTerm);
        	break;
        case FIRST_NAME:
    		user.setFirstName(searchTerm);
        	break;
        case LAST_NAME:
    		user.setLastName(searchTerm);
        	break;
        case EMAIL:
    		user.setEmail(searchTerm);
        	break;
        default:
        	throw new DataRetrievalFailureException("invalid query field");
        }
    	return userDao.findUsers(user, searchField);
    }
    
    // need to handle AND/OR for fields
    public List<User> findUsers2(User user, boolean partialMatch, boolean matchAllFields) {
//    	if (partialMatch) {
//    		searchTerm = "%" + searchTerm + "%";
//    	}
    	return userDao.findUsers2(user, matchAllFields);
    }
    
    public List<User> findUsers3(User user, String last4digits, boolean matchAllFields) {
    	return userDao.findUsers3(user, last4digits, matchAllFields);
    }
    */
    
    public List<FindUserResult> findUsers(Map<String,String> fields, boolean matchAllFields) {
    	return userDao.findUsers(fields, matchAllFields);
    }
    
    public void saveUser(User user) {
    	userDao.saveUser(user);
    }

	/*
	 * password is in clear text until here. we encrypt it to go into sql
	 * which allows us to pass around User objects without needing to worry
	 * about passwords in the clear.
	 */
	public void changeUserPassword(String username, String password) {
		User u = getUserByUsername(username);
		u.setPassword(NeonPasswordMD5.encryptPassword(password));
		userDao.changeUserPassword(u);
	}
    
	public void changeUsername(User user) {
		userDao.changeUsername(user);
	}
	
	public HashMap<Integer, String> getPassQuestions() {
		return this.getPassQuestions(true);
	}
    public HashMap<Integer, String> getPassQuestions(boolean activeOnly) {
    	return userDao.getPassQuestions(activeOnly);
    }
    
	
    public Integer getCountryIdByCode(String countryCode) {
    	return countryIdsByISOCode.get(countryCode);
    }
    public String getCountryCodeById(Integer countryId) {
    	return countryISOCodesById.get(countryId);
    }
    
    public HashMap<Integer, String> getCountryISOCodesById() {
    	return countryISOCodesById;
    }
    public HashMap<Integer, String> getCountryISOCodesByIdWithName() {
    	return countryISOCodesByIdWithName;
    }
    public HashMap<String, Integer> getCountryIdsByCode() {
    	return countryIdsByISOCode;
    }
    
    public HashMap<String, String> getUSStates() {
    	return getUSStates(false);
    }
    public HashMap<String, String> getUSStates(boolean includeTerritories) {
    	return userDao.getUSStates(includeTerritories);
    }
    
    // returns userId
    public int createUser(User user) {
    	return userDao.createUser(user);
    }

    public CreditCard getCreditCard(int userId) {
    	return creditCardDao.getCreditCard(userId);
    }
    
    public void saveCreditCard(CreditCard card) {
    	creditCardDao.saveCreditCard(card);
    }
    
    public void updateCreditCardNumber(CreditCard card, String number) {
    	card.setNumber(Crypt.encrypt(number));
		int ccLen = number.length();
		String last4 = number.substring(ccLen - 4, ccLen);
		card.setLastDigits(Short.parseShort(last4));
    	saveCreditCard(card);
    }
    
    public List<Subscription> getSubscriptions(String username) {
    	return subscriptionDao.getSubscriptionsByUsername(username);
    }
    public Subscription getSubscription(int subscriptionId) {
    	return subscriptionDao.getSubscriptionBySubscriptionId(subscriptionId);
    }

    // various methods to add a new user
    @Transactional
    public int registerNewUser(User user) {
    	int userId = userDao.createUser(user);
    	return userId;
    }
    
    @Transactional
    public int registerNewUser(User user, CreditCard card) {
    	return this.registerNewUser(user, card, false);
    }
    
    @Transactional
    public int registerNewUser(User user, CreditCard card, Boolean ccTestMode) {
    	int userId = userDao.createUser(user);
    	card.setUserId(userId);
    	creditCardDao.saveCreditCard(card);    	
    	return userId;
    }    
    
    @Transactional
    public Transaction registerNewUserSub(User user, CreditCard card, String cvv, Integer rateId, Boolean rebill, String signupFor) {
    	return this.registerNewUserSub(user, card, cvv, rateId, rebill, signupFor, false);
    }
    
    //@Transactional
    public Transaction registerNewUserBySearch(User user, CreditCard card, String cvv, BigDecimal amount,short category,short subCategory, Boolean ccTestMode) 
	throws UserManagerException {
	
    	int userId = this.registerNewUser(user, card);
    	Transaction t = billingManager.runTransactionWithCvv(user, card, cvv, amount, TxnType.AUTH_CAPTURE, "Register by Search", userId, category, subCategory, ccTestMode);
	
	logger.info("Transaction id:"+t.getTransactionId());//getTransactionStatusId()
	if (t.getTransactionStatusId() == BillingManager.txnApproved) {
		logger.info("reached sucess");		
	} else if (t.getTransactionStatusId() == BillingManager.txnDeclined) {
		logger.info("reached failed");
		throw new UserManagerException(t.getBankResponseReasonText(), Type.CC_FAILURE);			
	} else {
		logger.info("reached unknown");
		throw new UserManagerException("Unknown", Type.UNKNOWN);
	}

	return t;
}
    
    @Transactional
    public Transaction registerNewUserSub(User user, CreditCard card, String cvv, Integer rateId, Boolean rebill, String signupFor, Boolean ccTestMode) 
		throws UserManagerException {
    	int userId = this.registerNewUser(user, card);
    	Rate r = rateDao.getRateByRateId(rateId); 
    	Subscription s = addSubscription(userId, rateId, rebill);
    	logger.info("reached for billing");
    	// do initial billing
		Transaction t = billingManager.billSubscription(user, card, s, signupFor, ccTestMode);	
		logger.info("Transaction id:"+t.getTransactionStatusId());
		if (t.getTransactionStatusId() == BillingManager.txnApproved) {
			logger.info("reached sucess");
			billingManager.advanceSubscription(s);
		} else if (t.getTransactionStatusId() == BillingManager.txnDeclined) {
			logger.info("reached failed");
			throw new UserManagerException(t.getBankResponseReasonText(), Type.CC_FAILURE);			
		} else {
			logger.info("reached unknown");
			throw new UserManagerException("Unknown", Type.UNKNOWN);
		}

		return t;
    }
    
    
    
    @Transactional
	public Subscription addSubscription(int userId, int rateId, boolean rebill) {
		User u = userDao.getUserByUserId(userId);
		Rate r = rateDao.getRateByRateId(rateId);
		if (u == null || r == null) {
			return null;
		} else {
			Subscription s = new Subscription();
			s.setUserId(userId);
			s.setRateId(rateId);
			Date now = new Date();
			s.setInitDate(now);
			s.setPrevBillDate(now);
			s.setNextBillDate(now);
			s.setActive(true);
			s.setRebill(rebill);
			subscriptionDao.createSubscription(s);
			return s;
		}
	}

	public List<Invoice> getInvoices(String username) {
		User u = getUserByUsername(username);
		return invoiceDao.getInvoicesByUserId(u.getUserId());
	}
	public List<InvoiceDetail> getInvoiceDetails(int invoiceId) {
		return invoiceDao.getInvoiceDetailsByInvoiceId(invoiceId);
	}
	public List<Payment> getPayments(String username) {
		User u = getUserByUsername(username);
		return paymentDao.getPaymentsByUserId(u.getUserId());
	}
	
//	@Transactional
	public int deleteUser(int userId) {
		// delete: transactions, searches, cc, user
		User u = userDao.getUserByUserId(userId);
		if (u == null) {
			return 0;
		} else {
			//userDao.
			//u.setDisabledReason("test-" + System.currentTimeMillis());
			return userDao.deleteUser(u);
			//throw new UserManagerException("Test failure mode", Type.UNKNOWN);
			/*
			List<BGCRequest> searches = searchManager.getSearchesForUser(u.getUserId());
			for (BGCRequest r : searches) {
				searchManager.deleteSearch(r.getBgcRequestId());
			}
			logger.info("Searches deleted: " + searches.size());
			try {
				Criteria crit = new Criteria();
				crit.add(SsCreditCardsPeer.USER_ID, u.getUserId());
				SsCreditCardsPeer.doDelete(crit);
				
				crit.clear();
				crit.add(SsSubscriptionsPeer.USER_ID, u.getUserId());
				SsSubscriptionsPeer.doDelete(crit);
				
				crit.clear();
				crit.add(SsUsersPeer.USER_ID, u.getUserId());
				SsUsersPeer.doDelete(crit);
				
				throw new UserManagerException("Test failure mode", Type.UNKNOWN);
			} catch (TorqueException e) {
				logger.error("Delete user failed", e);
				throw new UserManagerException("Delete user failed", Type.UNKNOWN, e);
			}
			*/		
		}
	}
	
	@Transactional
	public int deleteUser(String username) {
		return deleteUser(username, true);
	}
	
	@Transactional
    public int deleteUser(String username, Boolean caseSensitive) {
		User u = getUserByUsername(username, caseSensitive);
		if (u == null) {
			return 0;
		} else {
			return deleteUser(u.getUserId());
		}
    }
    
    public List<Map<String,Object>> getTestUsersForCleanup() {
    	return userDao.getTestUsersForCleanup();
    }
    
    public int cleanupTestUsers() {
    	List<Map<String, Object>> users = getTestUsersForCleanup();
    	logger.info("users to clean: " + users.size());
    	int deleted = 0;
    	for (Map<String, Object> map : users) {
    		String username = (String) map.get("username");
    		deleted += deleteUser(username);
    	}
    	logger.info("users deleted: " + deleted);
    	return deleted;
    }

   // corporate accounts
    public CorporateAccount getCorporateAccountById(int accountId) {
    	return userDao.getCorporateAccountById(accountId);
    }
    public List<CorporateAccount> getAllCorporateAccounts() {
    	return userDao.getAllCorporateAccounts();
    }
    public List<User> getCorporateAccountUsers(int accountId) {
    	return userDao.getCorporateAccountUsers(accountId);
    }
    public User getCorporateAccountUserByUserId(int accountId, int userId) {
    	return userDao.getCorporateAccountUserByUserId(accountId, userId);
    }
	public CorporateAccount getCorporateAccountByUserId(int userId) {
		return userDao.getCorporateAccountByUserId(userId);
	}    
    public void addCorporateAccount(CorporateAccount acct) {
    	userDao.addCorporateAccount(acct);
    }
    public void updateCorporateAccount(CorporateAccount acct) {
    	userDao.updateCorporateAccount(acct);
    }
    public void deleteCorporateAccount(CorporateAccount acct) {
    	userDao.deleteCorporateAccount(acct);
    }
    
    // this will no-op if appropriate
    public void addUserToCorporateAccount(CorporateAccount acct, User user) {
    	Integer auth = acct.getAccountsAuthorized();
    	Integer inuse = acct.getAccountsInuse();
    	if (inuse >= auth) {
    		// XXX do something here
    		return;
    	}
    	
    	User u = this.getCorporateAccountUserByUserId(acct.getCorporateAccountId(), user.getUserId());
    	if (u == null) {
    		userDao.addUserToCorporateAccount(acct, user);
        	acct.setAccountsInuse(inuse + 1);
        	userDao.updateCorporateAccount(acct);
    	}
    }
    
    // this does not need to no-op, delete 0 rows is ok
    public void removeUserFromCorporateAccount(CorporateAccount acct, User user) {
    	Integer inuse = acct.getAccountsInuse();
		userDao.removeUserFromCorporateAccount(acct, user);
		acct.setAccountsInuse(inuse - 1);
    	userDao.updateCorporateAccount(acct);
    }
    
    // user admin history
    public List<SsUsersAdminActions> getUserAdminActions() {
    	try {
			return SsUsersAdminActionsPeer.doSelect(new Criteria());
		} catch (TorqueException e) {
			throw new UserManagerException("", Type.UNKNOWN, e);
		}
    }
    
    public List<SsUsersAdminHistory> getUserAdminHistory(int userId) {
    	try {
    		Criteria c = new Criteria();
    		c.add(SsUsersAdminHistoryPeer.USER_ID, userId);
			return SsUsersAdminHistoryPeer.doSelect(c);
		} catch (TorqueException e) {
			throw new UserManagerException("", Type.UNKNOWN, e);
		}
    }

    //find People Counter
	
	public void fpBGCount() {
		userDao.updatefpBGCount();
		
	}
	public void fpDetailCount() {
		userDao.updatefpDetailCount();
		
	}
	public void fpTeaserCount() {
		userDao.updatefpTeaserCount();
		
	}
	public void fpFreeBGCount() {
		userDao.updateFreeBGCount();
		
	}


    //mexico user count

	public void salesUserCount() {
		userDao.updateSalesUserCount();
	}
	
    
	public void teaserUserCount() {
		userDao.updateTeaserUserCount();		
	}
	

	//Findpeople user request
	public void fpUserRequest(int userId, String product, String firstName,
			String lastName, String state, Number price) {
		userDao.fpUserRequestImp(userId, product, firstName,lastName, state, price);
	}

	
	//Free BGR user
	public boolean getFreeBgrUser(int userId) {
		return userDao.getFreeBgrUserImp(userId);
	}
	public void setFreeBgrUser(int userId) {
		userDao.setFreeBgrUserImp(userId);
	} 
	public void modifyFreeBgrUser(int userId){
		userDao.modifyFreeBgrUserImp(userId);
	}

	@Transactional
	public Transaction registerFRNewUserSub(User user, CreditCard card,	String cvv, Integer rateId, Boolean member)
		throws UserManagerException {
		return this.registerFRNewUserSub(user, card, cvv, rateId, member, false);
	}

	@Transactional
	public Transaction registerFRNewUserSub(User user, CreditCard card,	String cvv, Integer rateId, Boolean member, Boolean ccTestMode)
		throws UserManagerException {
		
		Rate rate = rateDao.getRateByRateId(rateId);
		Transaction t = new Transaction();
		short category = 50;
		short subCategory = (short) rate.getInitialUnits();
		int userId = 0;
		
		if(!member){
			userId = this.registerNewUser(user, card);
			t = billingManager.runTransactionWithCvv(user, card, cvv, rate.getRecurringPrice(), TxnType.AUTH_CAPTURE, rate.getRateDescription(), userId, category, subCategory, ccTestMode);
		}else{
			userId = user.getUserId();
			t = billingManager.runTransactionNoCvv(user, card, rate.getRecurringPrice(), TxnType.AUTH_CAPTURE, rate.getRateDescription(), user.getUserId(), category, subCategory, ccTestMode);
		}
    	
		if (t.getTransactionStatusId() == BillingManager.txnApproved) {
			billingManager.addFRSubscription(user, rate, t.getTransactionId());
		} else if (t.getTransactionStatusId() == BillingManager.txnDeclined) {
			throw new UserManagerException(t.getBankResponseReasonText(), Type.CC_FAILURE);			
		} else {
			throw new UserManagerException("Unknown", Type.UNKNOWN);
		}

		return t;
	}

	public List<Map<String, Object>> getAllFRMonthlyUsers() {
		return subscriptionDao.getAllFRMonthlyUsers();
	}

	public void setFreeFRUser(int userId, int frId,int txnId) {
		userDao.setFreeFRUser(userId, frId, txnId);
	}


	public List<User> getnewCustomers() {
		
		return userDao.getnewCustomers();
	}
	public List<User> getnewCustomersByDays(Integer daysToSearch) {
		
		return userDao.getnewCustomersByDays(daysToSearch);
	}
		
    //public void addUserAdminAction(SsUsersAdminHistory item);
    //public void addUserAdminHistoryItem(SsUsersAdminHistory item);

}