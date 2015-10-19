package springapp.repository;

import java.util.List;
import java.util.Map;

import springapp.domain.FRSubscription;
import springapp.domain.Subscription;

public interface SubscriptionDao {   
    public Subscription getSubscriptionBySubscriptionId(int subscriptionId);
    public Subscription getSubscription(int subscriptionId, boolean checkNextBill);
    
    public List<Subscription> getSubscriptionsByUserId(int userId);
    public List<Subscription> getSubscriptionsByUsername(String username);
    
    // XXX this is not really a safe method to use. we don't want to 
    // allow generic updates on these fields. create methods to perform
    // specific sub tasks.
    // public void saveSubscription(Subscription sub); 
    
    public void createSubscription(Subscription sub);    
    public void advanceSubscriptionBillDates(Subscription s, String interval);
    public void updateSubscriptionActive(Subscription s);
    
    // full sub info for display
    public List<Map<String, Object>> getSubscriptionsDueForBilling();
    public List<Map<String, Object>> getSubscriptionsDueForBilling(int daysFromNow);
    public List<Map<String, Object>> getSubscriptionsDueForBilling(int daysFromNow, Integer rateId);
    
    // summary by rates
    public List<Map<String, Object>> getSubscriptionSalesForecast(int daysFromNow);
    
    // just sub id for batch
    public List<Integer> getSubscriptionIdsDueForBilling();
    public List<Integer> getSubscriptionIdsDueForBilling(int daysFromNow);
    
    // get subs that will be deactivated because rebill = false
    public List<Integer> getInactiveSubIdsForNoRebill();
    public List<Integer> getInactiveSubIdsForNoRebill(int daysFromNow);
    
    public List<Map<String, Object>> getNoRebillSubscriptionsSalesForecastSummary(int daysFromNow);
    public List<Map<String, Object>> getNoRebillSubscriptionsSalesForecastByRate(int daysFromNow, Integer rateId);
    
    // make those subs inactive
    public int setInactiveForNoRebill();
    
    //For Flat rate
    public List<FRSubscription> getAllFRSubscriptionsByUserId(int userId);
    public FRSubscription getSubscriptionByTxnId(int txnId);
    public FRSubscription getFRSubscriptionBysubId(int subId);
    public FRSubscription createFRSubscription(FRSubscription frsub);
    public void deleteFRSubscription(int subId);
    public List<FRSubscription> getValidFRSubscriptions(int userId);
    public List<FRSubscription> getActiveFRSubscriptions(int userId);
    public List<FRSubscription> getPipeFRSubscriptions(int userId);
    public boolean isFRUser(int userId);
    public boolean isActiveFRUser(int userId);
    public boolean isExpired(int subId);
    public void updateFRSubscription(FRSubscription frsub);
    public void updateFRSubscriptionActive(FRSubscription frsub);
    public void updateFRSubsPipeToActive(int subId, int days);
    public int updateFPSearchCount(int subId);
    public int updateBGCSearchCount(int subId);
    public int getPlanTypeIdBySubId(int subId);
    public List<FRSubscription> updateFROnExpireSubs();    
    public List<FRSubscription> getFRDailyExpireSubs(); 
    public List<Map<String, Object>> getAllFRMonthlyUsers();
            
       
}