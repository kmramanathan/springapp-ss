package springapp.service.billing;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import springapp.domain.FRSubscription;
import springapp.domain.Rate;
import springapp.domain.User;
import springapp.repository.SubscriptionDao;
import springapp.web.funnel.AbstractFunnelController;

public class FRJobRunner extends AbstractFunnelController {
	
	@Autowired
	protected SubscriptionDao subscriptionDao;
	
	@Autowired
	protected RateManager rateManager;
		
	protected Logger logger = Logger.getLogger(getClass());

	//This method will be execute for each 15 min
	public void FlatRateJobs(){
		
		try{			
			logger.info("Calling Flat Rate job runner");
			//First find expires and send mail 
			List<FRSubscription> expList = subscriptionDao.updateFROnExpireSubs();
			
			if(expList != null && !expList.isEmpty()){
				for(FRSubscription s :expList){
					User user = userManager.getUserByUserId(s.getUserid());
					Rate rate = rateManager.getRate(s.getPlanid());
					String plan =  rate.getRateName()+" / "+ rate.getRateDescription();
					SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
					
					sendExpireThankYouEmail(user.getUsername(), plan, user.getEmail(), 
							formatDate.format(s.getStartdate()), formatDate.format(s.getEnddate()), 
							String.valueOf(rate.getRecurringPrice().floatValue()), !user.getInactive());					
				}
			}
			
			//Then deals with daily expires. Refresh all daily expired subscriptions
			List<FRSubscription> daillyExpList = subscriptionDao.getFRDailyExpireSubs();
			
			if(daillyExpList != null && !daillyExpList.isEmpty()){
				for(FRSubscription s : daillyExpList){
					FRSubscription frsub = setDateIntervels(s);
					Rate rate = rateManager.getRate(frsub.getPlanid());
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
					
					subscriptionDao.updateFRSubscriptionActive(frsub);
				}
			}
			
		}catch (Exception e) {
			logger.info("Exception in FR daily job :" +e);
		}
		
	}
	
	private void sendExpireThankYouEmail(String username, String plan, String customerEmail, 
			String stDate, String endDate, String amount, boolean member) {
		
		HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("username", username);
        map.put("plan", plan);
        map.put("amount", amount);
        map.put("startDate", stDate);
        map.put("endDate", endDate);
        map.put("member", member);
        
        String subject = "THANK YOU / EXPIRATION NOTICE EMAIL";
        
        sendEmailVelocity(map, FRExpMail, emailFromThankYou, customerEmail, subject);
	}
	
	public FRSubscription setDateIntervels(FRSubscription s){
		
	 	Timestamp prevDate =  s.getPrevdate();
		Timestamp nxtDate = s.getNextdate();
		long oneDay = 1 * 24 * 60 * 60 * 1000;
		
		s.setPrevdate(s.getNextdate());
		Timestamp nextDate = new Timestamp(nxtDate.getTime()+oneDay);
		s.setNextdate(nextDate);
		int days = s.getDaysavailable()-1;
		s.setDaysavailable(days);		
		return s;
	}

}
