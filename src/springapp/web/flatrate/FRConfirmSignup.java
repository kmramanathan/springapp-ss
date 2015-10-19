package springapp.web.flatrate;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import springapp.crypt.Crypt;
import springapp.domain.CreditCard;
import springapp.domain.FRSubscription;
import springapp.domain.Rate;
import springapp.domain.Subscription;
import springapp.domain.Transaction;
import springapp.domain.User;
import springapp.repository.SubscriptionDao;
import springapp.repository.UserDao;
import springapp.service.billing.BillingManager;
import springapp.service.billing.RateManager;
import springapp.service.billing.BillingManager.TxnType;
import springapp.service.user.UserManagerException;
import springapp.service.user.UserManagerException.Type;
import springapp.web.flatrate.FRPurchaseForm.FRPurchaseFormCommand;
import springapp.web.flatrate.FRSignupForm.FRSignupFormCommand;
import springapp.web.funnel.AbstractFunnelController;

@Controller
@RequestMapping("/flatrate/FRConfirm.do")

public class FRConfirmSignup extends AbstractFunnelController {
	
	@Autowired
	private RateManager rateManager;
		
	@Autowired
	private BillingManager billingManager;
	

	private String getPaymentPlanString(FRSignupFormCommand sfc) {
		Rate r = rateManager.getRate(sfc.getRateId());
		String plan =  r.getRateName()+" / "+ r.getRateDescription()+" -- $"+ r.getRecurringPrice();
		return plan;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map
			) {		
		FRPurchaseFormCommand pfc = (FRPurchaseFormCommand) session.getAttribute("FRPurchaseFormCommand");
		FRSignupFormCommand signupfc = (FRSignupFormCommand) session.getAttribute("FRSignupFormCommand");
		
		//For SS Members. If pfc not null then member tries to create different account
		if(session.getAttribute("tempFRusername") != null && pfc == null){
			String username = (String) session.getAttribute("tempFRusername");
			User user = userManager.getUserByUsername(username);
			map.addAttribute("name", user.getFirstName() + " " + user.getLastName());			
			map.addAttribute("username", user.getUsername());
			map.addAttribute("plan", getPaymentPlanString(signupfc));
			if(session.getAttribute("tempFRnoCreditCard") != null){
				map.addAttribute("ccLast4", "No Card");
			}else{
				CreditCard cc = userManager.getCreditCard(user.getUserId());
				map.addAttribute("ccLast4", cc.getLastDigits());
			}
		}
		
		//For temp Members
		else{
			// pfc and signupfc are mandatory here
			if (pfc == null || signupfc == null) {
				return FRLandingRedir;
			}
			map.addAttribute("name", signupfc.getFirstName() + " " + signupfc.getLastName());			
			map.addAttribute("username", signupfc.getUsername());
			map.addAttribute("plan", getPaymentPlanString(signupfc));
			
			String ccNumber = pfc.getCcNumber();
			String ccLast4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
			map.addAttribute("ccLast4", ccLast4 );
		}
		
		return FRConfirm;
	}
	
	private User generateUser(FRSignupFormCommand signupfc, FRPurchaseFormCommand pfc) {
		User u = new User();
		
		u.setUsername(signupfc.getUsername());
		u.setFirstName(signupfc.getFirstName());
		u.setLastName(signupfc.getLastName());
		u.setEmail(signupfc.getEmail());
		
        u.setPassword(DigestUtils.md5Hex(signupfc.getPassword()));        
        u.setPassQuestionId(signupfc.getSecretQuestionId().shortValue());
        u.setPassAnswer(signupfc.getSecretQuestionAnswer());        
        u.setNewsletter(pfc.getReceiveNewsletter());  
        
		u.setNoExport(true);
		u.setNoPremium(false);
		u.setSubDirectoryUser(true);
		
		//All FR members are Inactive in default
		u.setInactive(true);
   		
		return u;
	}
		
	private CreditCard generateCC(FRSignupFormCommand signupfc, FRPurchaseFormCommand pfc) {
		CreditCard cc = new CreditCard();
		
		cc.setName(pfc.getName());
		cc.setCompany(pfc.getCompany());
		cc.setAddress1(pfc.getAddress());
		cc.setPhone(pfc.getPhone());
		cc.setCity(pfc.getCity());
		cc.setState(pfc.getState());
		cc.setPostalCode(pfc.getPostalCode());
		
		logger.info("countryCode: " + pfc.getCountryCode());
		Integer cid = userManager.getCountryIdByCode(pfc.getCountryCode());
		logger.info("countryId: " + cid);
		cc.setCountryId(cid.shortValue());
		
		String ccNumber = pfc.getCcNumber();
		String last4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
		cc.setNumber(Crypt.encrypt(ccNumber));
		cc.setLastDigits(Short.parseShort(last4));
		
		cc.setExpMonth(pfc.getCcExpMonth().shortValue());
		cc.setExpYear(pfc.getCcExpYear().shortValue());
		cc.setVerified(true);
		
		return cc;
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			SessionStatus status) {
		
		FRPurchaseFormCommand pfc = (FRPurchaseFormCommand) session.getAttribute("FRPurchaseFormCommand");
		FRSignupFormCommand signupfc = (FRSignupFormCommand) session.getAttribute("FRSignupFormCommand");
	
		CreditCard cc = new CreditCard();
		User user = new User();
		Rate rate = rateManager.getRate(signupfc.getRateId());
		Transaction txn = new Transaction();
		
		boolean member = false;
		boolean memberReq = false;
		boolean ccTestMode = false;
		short category = 50;
		short subCategory = (short) rate.getInitialUnits();
		String cvv = "";
		
		if(session.getAttribute("tempFRusername") != null ){
			member = true;
		}
		
		try {			
			//For SS members
			if(member && pfc == null){
				String username = (String) session.getAttribute("tempFRusername");
				user = userManager.getUserByUsername(username);
				if(session.getAttribute("tempFRnoCreditCard") != null){
					session.removeAttribute("tempFRnoCreditCard");
					session.removeAttribute("FRSignupFormCommand");
					session.removeAttribute("tempFRusername");
					session.removeAttribute("tempFRuserId");
					map.addAttribute("username", user.getUsername());
					billingManager.addFRSubscription(user, rate, 0);
					return FRThankyou;
				}
				cc = (CreditCard)session.getAttribute("tempFRcreditCardObj");
				memberReq = true;
				ccTestMode = signupfc.getTest();
			}
			//For temp members
			else{
				// pfc is mandatory here
				if (pfc == null) {
					return FRPurchaseRedir;
				}
				// at least 1 of the other two must be present also
				if (signupfc == null) {
					return FRSignupRedir;
				}
				user = generateUser(signupfc, pfc);
				cc = generateCC(signupfc, pfc); 
				cvv = pfc.getCcAuthCode();
				ccTestMode = pfc.getTest();
			}
			
			txn = userManager.registerFRNewUserSub(user, cc, cvv, signupfc.getRateId(), memberReq, ccTestMode);
			
			status.setComplete();
			
			// send emails
			sendSignupReceiptEmail(user, cc, txn, rate);
			sendSignupThankYouEmail(user.getFirstName(), user.getUsername(), user.getEmail());
			
			// clear session info at this point
			session.removeAttribute("FRSignupFormCommand");
			session.removeAttribute("FRPurchaseFormCommand");
			session.removeAttribute("tempFRusername");
			session.removeAttribute("tempFRuserId");
			session.removeAttribute("tempFRcreditCardObj");
			
			map.addAttribute("username", user.getUsername());
			return FRThankyou;
			
		} catch (UserManagerException e) {
			// bleh, how do we figure out the reason for the ex?
			if (e.getType() == Type.CC_FAILURE) {
				map.addAttribute("cardDeclineReason", e.getMessage());
				logger.info("cardDeclineReason :" +e.getMessage());
				return FRcardDeclinedView;					
			} else {
				// XXX may need to void txn here
				logger.error("Signup failed, may need to manually void txn!");
				logger.error("Username: " + user.getUsername());
				logger.error("CC Name: " + cc.getName());
				logger.error("CC Last 4: " + cc.getLastDigits());
				logger.error(e);
				
				// check ex type to verify
				return FRvwError;
			}				
		}
	}	
	
	private void sendSignupThankYouEmail(String customerName, String username, String customerEmail) {
		HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("customerName", customerName);
        map.put("username", username);
                
        String subject = "Welcome to Search Systems";
        
        sendEmailVelocity(map, FRThankyouMail, emailFromThankYou, customerEmail, subject);
	}
	
	private void sendSignupReceiptEmail(User user, CreditCard cc, Transaction t, Rate r) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map.put("invoiceNumber", t.getTransactionId());
		map.put("description", r.getRateName()+" / "+r.getRateDescription());
		map.put("amount", t.getCost());
	
		map.put("u", user);
		map.put("cc", cc);
		map.put("countryCode", userManager.getCountryCodeById(Integer.valueOf(cc.getCountryId())));

		String subject = "Search Systems Receipt";
        sendEmailVelocity(map, FRSignupReceiptMail, emailFromNoReply, user.getEmail(), subject);		
	}
		
	   
}










