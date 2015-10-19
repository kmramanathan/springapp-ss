package springapp.web.flatrate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.CreditCard;
import springapp.domain.FRSubscription;
import springapp.domain.User;
import springapp.repository.SubscriptionDao;
import springapp.service.NeonPasswordMD5;
import springapp.service.billing.BillingManager;
import springapp.service.billing.RateManager;
import springapp.service.user.UserLogin;
import springapp.service.user.UserManager;
import springapp.web.funnel.AbstractFunnelController;

@Controller
public class FRLoginForm extends AbstractFunnelController{

	@Autowired
	protected SubscriptionDao subscriptionDao;
		
	protected Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping(value = "/flatrate/FRLogin.do", method = RequestMethod.GET)
	public String showLoginForm(ModelMap map){
		LoginFormCommand lfc = new LoginFormCommand();
		map.addAttribute("command", lfc);
		return FRLoginURL;
	}
	
	@RequestMapping(value = "/flatrate/FRLogin.do", method = RequestMethod.POST)
	public String getLoginForm(ModelMap map, HttpSession session,
			@ModelAttribute("command") LoginFormCommand lfc, Errors errors
			){
		if(session.isNew()){ //if its a new session
			   session.setMaxInactiveInterval(36000);
			}
		if(lfc.getUsername().isEmpty() || lfc.getPassword().isEmpty()){
			errors.reject("username", "Username or Password can not be empty");
			return FRLoginURL;
		}
		User user = userManager.getUserByUsername(lfc.getUsername(), false);
		UserLogin.authenticateFRMember(user, errors, lfc.getPassword(), session);
		if(errors.hasErrors()){
			return FRLoginURL;
		}
		if(!subscriptionDao.isFRUser(user.getUserId())){
			errors.reject("username", "You are not a flat rate member. Please sign up for Flat Rate upgrade on your account");
			return FRLoginURL;
		}
		
		logger.info("getting sublist for userid : "+user.getUserId());
		List<FRSubscription> frSubList = subscriptionDao.getValidFRSubscriptions(user.getUserId());
		logger.info("size of sub list : "+frSubList.size());
		if(frSubList.isEmpty()){
			if(!user.getInactive()){
				map.addAttribute("member", true);
			}
			return FRPlanExpiry; 
		}
		
		if(!user.getInactive()){
			session.setAttribute("SSusername", user.getUsername());
		}
		session.setAttribute("FRusername", user.getUsername());
		session.setAttribute("FRuserId", new Integer(user.getUserId()));
		if(user.getNoCreditCard() == true){
			session.setAttribute("FRnoCreditCard", true);
		}else{
			CreditCard cc = userManager.getCreditCard(user.getUserId());
			session.setAttribute("FRcreditCardObj", cc);
		}
		
		return FRMenuRedir;
	}
	
	@RequestMapping(value = "/flatrate/FRMenu.do", method = RequestMethod.GET)
	public String showFRMenu(ModelMap map, HttpSession session){
			 
		if(session.getAttribute("FRusername") == null || session.getAttribute("FRuserId") == null){
			return FRLoginRedir;
		}
		String FRusername = (String) session.getAttribute("FRusername");
		int FRuserId = (Integer) session.getAttribute("FRuserId");
				
		//need to refresh the session every time user comes to menu
		removeFRSession(session);
			 
		//Finding active and pipe line subscriptions
		List<FRSubscription> activeList = subscriptionDao.getActiveFRSubscriptions(FRuserId);
		List<FRSubscription> pipeList = subscriptionDao.getPipeFRSubscriptions(FRuserId);
		
		HashMap<Integer, FRSubscription> activeMap = new HashMap<Integer, FRSubscription>();
	
		// this condition will happen when account expire after user logged in
		if(activeList.isEmpty() && pipeList.isEmpty()){ 
			if(session.getAttribute("SSusername") != null){
				map.addAttribute("member", true);
			}
			removeFRSession(session);
			return FRPlanExpiry;
		}else if(activeList.isEmpty() && !pipeList.isEmpty()){
			//activate the plan from pipleLine. 
			for(FRSubscription s : pipeList){
				subscriptionDao.updateFRSubsPipeToActive(s.getSubscriptionid(), s.getDaysavailable());
				activeList.add(s);
			}
		}else{
			// pipe may have different plan type compare to active plan. if so active that too
			//XXX do it using query
			for(FRSubscription a : activeList){
				activeMap.put(a.getPlantypeid(), a);
			}
			for(FRSubscription p : pipeList){
				if(!activeMap.containsKey(p.getPlantypeid())){
					subscriptionDao.updateFRSubsPipeToActive(p.getSubscriptionid(), p.getDaysavailable());
					activeList.add(p);
				}
			}
			
		}
		
		initializeFRSession(session, activeList);
		
		map.addAttribute("FRusername", FRusername);
		map.addAttribute("size", activeList.size());
		map.addAttribute("activeList", activeList);
		return FRMenuURL;
	}
	
	@RequestMapping(value = "/flatrate/FRLogout.do", method = RequestMethod.GET)
	public String logoutFR(ModelMap map, HttpSession session){
		session.removeAttribute("SSusername");
		session.removeAttribute("FRusername");
		session.removeAttribute("FRuserId");
		removeFRSession(session);
		return FRLoginRedir;
	}
	
	public static class LoginFormCommand{
		private String username;
		private String password;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	}
	
/*	public FRSubscription setDateIntervels(FRSubscription s){
		
		java.util.Date today = new java.util.Date();
		Timestamp startDate =  new java.sql.Timestamp(today.getTime());
		Timestamp endDate = new java.sql.Timestamp(today.getTime());
	 	Timestamp prevDate =  new java.sql.Timestamp(today.getTime());
		Timestamp nextDate = new java.sql.Timestamp(today.getTime());
		long oneDay = 1 * 24 * 60 * 60 * 1000;
		
		s.setStatus(1);
		s.setStartdate(startDate);
		s.setPrevdate(prevDate);
		nextDate.setTime(prevDate.getTime()+oneDay);
		s.setNextdate(nextDate);
		endDate.setTime(startDate.getTime()+(oneDay*s.getDaysavailable()));
		s.setEnddate((endDate));
		
		return s;
	}
*/	
	public void initializeFRSession(HttpSession session, List<FRSubscription> activeList){
		if(session.isNew()){ //if its a new session
			   session.setMaxInactiveInterval(36000);
			}
		for(FRSubscription sub : activeList){
			if(sub.getPlantypeid() == 1 && sub.getFindpeoplesearches() >0){
				session.setAttribute("FPSearch", true);
				session.setAttribute("FPSearchAvailable", new Integer(sub.getFindpeoplesearches()));
				session.setAttribute("FPSubId", sub.getSubscriptionid());
			}else if(sub.getPlantypeid() == 2 && sub.getCriminalsearches()>0){
				session.setAttribute("BGCSearch", true);
				session.setAttribute("BGCSearchAvailable", new Integer(sub.getCriminalsearches()));
				session.setAttribute("BGCSubId", sub.getSubscriptionid());
			}else if(sub.getPlantypeid() == 3 && sub.getAvailablesearches()>0){
				session.setAttribute("ComSearch", true);
				if(sub.getCriminalsearches() > 0){
					session.setAttribute("BGCComSearch", true);
					session.setAttribute("BGCComSearchAvailable", new Integer(sub.getCriminalsearches()));
				}
				if(sub.getFindpeoplesearches() > 0){
					session.setAttribute("FPComSearch", true);
					session.setAttribute("FPComSearchAvailable", new Integer(sub.getFindpeoplesearches()));
				}
				session.setAttribute("ComSubId", sub.getSubscriptionid());
			}
		}
	}
	
	public static void removeFRSession(HttpSession session){
		session.removeAttribute("FPSearch");
		session.removeAttribute("FPSearchAvailable");
		session.removeAttribute("FPSubId");
		session.removeAttribute("BGCSearch");
		session.removeAttribute("BGCSearchAvailable");
		session.removeAttribute("BGCSubId");
		session.removeAttribute("ComSearch");
		session.removeAttribute("FPComSearch");
		session.removeAttribute("BGCComSearch");
		session.removeAttribute("ComSearchAvailable");
		session.removeAttribute("BGCComSearchAvailable");
		session.removeAttribute("FPComSearchAvailable");
		session.removeAttribute("ComSubId");
		
	}
	
}
