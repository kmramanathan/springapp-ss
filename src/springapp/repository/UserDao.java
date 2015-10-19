package springapp.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.searchsystems.limestone.SsAdminActions;
import net.searchsystems.limestone.SsUsersAdminActions;
import net.searchsystems.limestone.SsUsersAdminHistory;
import springapp.domain.AdminUserIPBean;
import springapp.domain.CorporateAccount;
import springapp.domain.FRSubscription;
import springapp.domain.User;
import springapp.service.user.FindUserResult;
import springapp.service.user.UserManager.QueryField;

public interface UserDao {   
    public User getUserByUserId(int userId);
    public User getUserByUsername(String username);
    public User getUserByUsername(String username, Boolean caseSensitive);
    public User getUser(User user, QueryField field);
    
    //Udhay Dec10-2014 
    public AdminUserIPBean getUserWithIP(int userId);
    
   // public AdminUserIPBean getUserByUsernameWithIP(String username, Boolean caseSensitive);
    
    public List<User> getUsersByUsernameCaseInsensitive(String username);
    
    public User getUserByEmail(String email);
    public List<User> getUsersByEmail(String email);
     /*
    public List<User> findUsers(User user, QueryField field);
    public List<User> findUsers2(User user, boolean matchAllFields);
    public List<User> findUsers3(User user, String last4digits, boolean matchAllFields);
    */
    public List<FindUserResult> findUsers(Map<String,String> fields, boolean matchAllFields);
    
    public void saveUser(User user);
    public int createUser(User user);
    public List<User> getnewCustomers();
    
    public void changeUsername(User user);
    public void changeUserPassword(User user);
    
    public HashMap<Integer, String> getPassQuestions(boolean activeOnly);
    
    public HashMap<Integer, String> getCountryISOCodesById();
    public HashMap<String, Integer> getCountryIdsByISOCode();
    public HashMap<Integer, String> getCountryISOCodesById(Boolean includeFullNames);
    
    public HashMap<String, String> getUSStates();
    public HashMap<String, String> getUSStates(boolean includeTerritories);
    
    public int deleteUser(User user);
    public int deleteUserFake(User user);
    
    public List<Map<String,Object>> getTestUsersForCleanup();
    
    // stuff for corporate accounts
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
    
    //findpeople teaser tracker
    public void updatefpBGCount();
    public void updatefpDetailCount();
    public void updatefpTeaserCount();
    public void updateFreeBGCount();
    //mexico user count
   public void updateTeaserUserCount();
   public void updateSalesUserCount();
   
   //Findpeople user request
   public void fpUserRequestImp(int userId, String product, String firstName,String lastName,String state,Number price);
   
   //Free BGR users
   public boolean getFreeBgrUserImp(int userId);
   public void setFreeBgrUserImp(int userId);
   public void modifyFreeBgrUserImp(int userId);
   
   public void setFreeFRUser(int userId, int frId,int txnId); 
   
   public List<User> getnewCustomersByDays(Integer daysToSearch); 
 
}



