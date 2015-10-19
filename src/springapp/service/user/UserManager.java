package springapp.service.user;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.searchsystems.limestone.SsUsersAdminActions;
import net.searchsystems.limestone.SsUsersAdminHistory;
import springapp.domain.AdminUserIPBean;
import springapp.domain.CorporateAccount;
import springapp.domain.CreditCard;
import springapp.domain.FRSubscription;
import springapp.domain.Invoice;
import springapp.domain.InvoiceDetail;
import springapp.domain.Payment;
import springapp.domain.Subscription;
import springapp.domain.Transaction;
import springapp.domain.User;

public interface UserManager {
	public enum QueryField {
		ID,	USERNAME, FIRST_NAME, LAST_NAME, EMAIL; 

		/*
		ID("Id"), 
		USERNAME("Username"), 
		FIRST_NAME("First Name"), 
		LAST_NAME("Last Name"),
		EMAIL("Email"); 
		private String name;
		private QueryField(String name) { this.name = name; }
		public String getName() { return name; }
		private static final HashMap<QueryField,String> searchFields = new HashMap<QueryField,String>();
		static {
			for (QueryField q : EnumSet.allOf(QueryField.class)) {
				searchFields.put(q, q.getName());
			}
		}
		*/
	};
	public User getUserByUserId(int userId);
    public User getUserByUsername(String username);
    public User getUserByUsername(String username, Boolean caseSensitive);
   
    //Udhay Dec10-2014
    public AdminUserIPBean getUserWithIP(int userId); 
    /*public AdminUserIPBean getUserByUsernameWithIP(String username, Boolean caseSensitive);
    public AdminUserIPBean getUserByUsernameWithIP(String username);
    public AdminUserIPBean getUserByUserIdWithIP(int userId);
    */
    public List<User> getUsersByUsernameCaseInsensitive(String username);
    
    public User getUserByEmail(String email);
    public List<User> getUsersByEmail(String email);
    
    public void changeUserPassword(String username, String password);
    
    public HashMap<Integer, String> getPassQuestions();
    public HashMap<Integer, String> getPassQuestions(boolean active);
    
    public Integer getCountryIdByCode(String countryCode);
    public String getCountryCodeById(Integer countryId);

    public HashMap<Integer, String> getCountryISOCodesById();
    public HashMap<Integer, String> getCountryISOCodesByIdWithName();
    public HashMap<String, Integer> getCountryIdsByCode();
    
    // default is to include states only
    public HashMap<String, String> getUSStates();
    public HashMap<String, String> getUSStates(boolean includeTerritories);
    
//    public List<User> findUsers(String searchTerm, QueryField searchField, boolean partialMatch);
//    public List<User> findUsers2(User user, boolean partialMatch, boolean matchAllFields);
//    public List<User> findUsers3(User user, String last4digits, boolean matchAllFields);
    public List<FindUserResult> findUsers(Map<String,String> fields, boolean matchAllFields);
    
    public int createUser(User user);
    public void saveUser(User user);    
    
    public CreditCard getCreditCard(int userId);
    public void saveCreditCard(CreditCard card);
    public void updateCreditCardNumber(CreditCard card, String number);
    
    public List<Subscription> getSubscriptions(String username);
    public Subscription getSubscription(int subscriptionId);

    public List<Invoice> getInvoices(String username);
    public List<InvoiceDetail> getInvoiceDetails(int invoiceId);
    public List<Payment> getPayments(String username);
    
    // txn protected method
    public int registerNewUser(User user);
    public int registerNewUser(User user, CreditCard card);
    public int registerNewUser(User user, CreditCard card, Boolean ccTestMode);
    
    public Transaction registerNewUserSub(User user, CreditCard card, String cvv, Integer rateId, Boolean rebill, String signupFor)
		throws UserManagerException;
    public Transaction registerNewUserSub(User user, CreditCard card, String cvv, Integer rateId, Boolean rebill, String signupFor, Boolean ccTestMode) 
		throws UserManagerException;  
    public Transaction registerNewUserBySearch(User user, CreditCard card, String cvv, BigDecimal amount, short category,short subCategory, Boolean ccTestMode)
    	throws UserManagerException;

    public Subscription addSubscription(int userId, int rateId, boolean rebill);
    
    public void changeUsername(User user);
    
    public int deleteUser(int userId);
    public int deleteUser(String username);
    public int deleteUser(String username, Boolean caseSensitive);
    
    public List<Map<String,Object>> getTestUsersForCleanup();
    public int cleanupTestUsers();
    
    // corporate accounts
    public CorporateAccount getCorporateAccountById(int accountId);
    public List<CorporateAccount> getAllCorporateAccounts();
    public List<User> getCorporateAccountUsers(int accountId);
    public User getCorporateAccountUserByUserId(int accountId, int userId);   
    public CorporateAccount getCorporateAccountByUserId(int userId);
    
    public void addCorporateAccount(CorporateAccount acct);
    public void updateCorporateAccount(CorporateAccount acct);
    public void deleteCorporateAccount(CorporateAccount acct);
    
    public void addUserToCorporateAccount(CorporateAccount acct, User user);
    public void removeUserFromCorporateAccount(CorporateAccount acct, User user);
    
    // user admin history
    public List<SsUsersAdminActions> getUserAdminActions();
    public List<SsUsersAdminHistory> getUserAdminHistory(int userId);
    
    //mexico user count
   public void teaserUserCount();
   public void salesUserCount();
    
    // findpeolpe teaser count
    public void fpTeaserCount();
    public void fpDetailCount();  
    public void fpBGCount();
    public void fpFreeBGCount();
    
    //XXX Fix it with new findpeople search manager
    public void fpUserRequest(int userId, String product, String firstName, String lastName, String state, Number price );
    
    
    // Registering free BGR users
    public boolean getFreeBgrUser(int userId);
    public void setFreeBgrUser(int userId);
    public void modifyFreeBgrUser(int userId);
    
    //register for free Flat rate
    public void setFreeFRUser(int userId, int frId,int txnId);
    
    
    //For Flat rate
    public Transaction registerFRNewUserSub(User user, CreditCard card, String cvv, Integer rateId, Boolean member)
		throws UserManagerException;
    public Transaction registerFRNewUserSub(User user, CreditCard card, String cvv, Integer rateId, Boolean member, Boolean ccTestMode) 
		throws UserManagerException;  
    
    public List<Map<String, Object>> getAllFRMonthlyUsers(); 
    //For New Customers
    public List<User> getnewCustomers();
	public List<User> getnewCustomersByDays(Integer daysToSearch);
    
}


