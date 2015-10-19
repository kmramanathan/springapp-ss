package springapp.web.funnel;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import springapp.crypt.Crypt;
import springapp.domain.CreditCard;
import springapp.domain.FRSubscription;
import springapp.domain.Rate;
import springapp.domain.Transaction;
import springapp.domain.User;
import springapp.service.billing.BillingManager;
import springapp.service.billing.RateManager;
import springapp.service.user.UserManagerException;
import springapp.service.user.UserManagerException.Type;
import springapp.web.funnel.PurchaseForm.PurchaseFormCommand;
import springapp.web.funnel.SignupForm.SignupFormCommand;

@Controller
@RequestMapping("/funnel/confirmSignup.do")

/**
 * Final step in running a new user signup. Shows the user the signup options 
 * and prompts for confirmation. 
 * 
 * Upon confirmation, this class bills the credit card, registers the new user
 * and sends "welcome" and "receipt" emails to the user.
 */
public class ConfirmSignupForm extends AbstractFunnelController {
	
	@Autowired
	private RateManager rateManager;
	
	@Autowired
	private BillingManager billingManager;
	

	private String getPaymentPlanString(PurchaseFormCommand pfc) {
		Rate r = rateManager.getRate(pfc.getRateId());
		String plan = r.getRateDescription();
		return plan;
	}
	private String getRecurringString(PurchaseFormCommand pfc) {
		String recurring;
		if (pfc.getRecurringBilling()) {
			recurring = "Yes (Automatic)";
		} else {
			recurring = "No";
		}
		return recurring;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map
			) {		
		PurchaseFormCommand pfc = (PurchaseFormCommand) session.getAttribute("purchaseFormCommand");
		SignupFormCommand signupfc = (SignupFormCommand) session.getAttribute("signupFormCommand");
		
		// pfc is mandatory here
		if (pfc == null) {
			return landingRedir;
		}
		// at least 1 of the other three must be present also
		if (signupfc == null) {
			return landingRedir;
		}
		
		// now determine what to do based on which other form is present
		// do signup

		// XXX set attrs
		map.addAttribute("signup", true);
		map.addAttribute("customerName", signupfc.getFirstName() + " " + signupfc.getLastName());			
		map.addAttribute("customerUsername", signupfc.getUsername());
		map.addAttribute("paymentPlan", getPaymentPlanString(pfc));
		map.addAttribute("recurring", getRecurringString(pfc));		
		
		String ccNumber = pfc.getCcNumber();
		String ccLast4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
		map.addAttribute("ccLast4", ccLast4 );
		
		return confirmView;
	}
	
	private User generateUser(SignupFormCommand signupfc, PurchaseFormCommand pfc) {
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
    		
		return u;
	}
		
	private CreditCard generateCC(SignupFormCommand signupfc, PurchaseFormCommand pfc) {
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
		PurchaseFormCommand pfc = (PurchaseFormCommand) session.getAttribute("purchaseFormCommand");
		SignupFormCommand signupfc = (SignupFormCommand) session.getAttribute("signupFormCommand");
		
		// pfc is mandatory here
		if (pfc == null) {
			return purchaseSignupRedir;
		}
		// at least 1 of the other two must be present also
		if (signupfc == null) {
			return purchaseSignupRedir;
		}
		
		// now determine what to do based on which other form is present

		// do signup
		User u = generateUser(signupfc, pfc);
		CreditCard cc = generateCC(signupfc, pfc); 
		try {
			logger.info("pfc test: " + pfc.getTest());
			
			String signupFor = "";
			if(session.getAttribute("signupFor") != null ){
				signupFor = (String) session.getAttribute("signupFor");
			}
			
			// this will create everything & charge CC
			Transaction t = userManager.registerNewUserSub(u, cc, pfc.getCcAuthCode(), pfc.getRateId(), pfc.getRecurringBilling(), signupFor, pfc.getTest());
			
			// XXX exception is thrown but register is not rolled back?
			
			status.setComplete();

			Rate r = rateManager.getRate(pfc.getRateId());
			
			// send emails
			String customerName = u.getFirstName() + " " + u.getLastName();
							
			sendSignupThankYouEmail(customerName, u.getUsername(), u.getPassword(), u.getEmail(), signupfc.getBonusItem());				
			sendSignupReceiptEmail(u, cc, t, r, pfc.getRecurringBilling());
			
			// XXX need to improve this & add map fields
			session.setAttribute("username", u.getUsername());
			session.setAttribute("paymentPlan", pfc.getRateId());
			session.setAttribute("userId", u.getUserId());
			session.setAttribute("purchasePrice", t.getCost());
			session.setAttribute("creditCardObj",cc);
			
			// clear session info at this point
			session.removeAttribute("signupFormCommand");
			
			boolean signFlag = false;
			if(session.getAttribute("signupFor") != null){
				signupFor = (String)session.getAttribute("signupFor"); 
				if(signupFor.equalsIgnoreCase("findpeople")){
					signFlag = true;
				}
			}
			
			//Bonus Item for users
			if(signupfc.getBonusItem().equalsIgnoreCase("fbr")){
				userManager.setFreeBgrUser(u.getUserId());
			}else{
				//creating Rate for oneday findpeople
				Rate frRate = rateManager.getRate(50);
				FRSubscription frsub = billingManager.addFRSubscription(u, frRate, t.getTransactionId());
				logger.info("Fr id : "+frsub.getSubscriptionid());
				userManager.setFreeFRUser(u.getUserId(), frsub.getSubscriptionid(), t.getTransactionId());
			}					
			
			
			//changed by shahul - required for redirect to called page
			String previousPage=(String)session.getAttribute("previousPage");
			if(previousPage!=null){
				session.removeAttribute("previousPage");
				if(signFlag){
					return "redirect:"+previousPage+"&signupFor="+signupFor;
				}
				return "redirect:"+previousPage;
			}
		
			return welcomeRedir+"?bonusType="+signupfc.getBonusItem();
			
		} catch (UserManagerException e) {
			// bleh, how do we figure out the reason for the ex?
			if (e.getType() == Type.CC_FAILURE) {
				map.addAttribute("cardDeclineReason", e.getMessage());
				return cardDeclinedView;					
			} else {
				// XXX may need to void txn here
				logger.error("Signup failed, may need to manually void txn!");
				logger.error("Username: " + u.getUsername());
				logger.error("CC Name: " + cc.getName());
				logger.error("CC Last 4: " + cc.getLastDigits());
				logger.error(e);
				
				// check ex type to verify
				return vwError;
			}				
		}
	}	
	
	private void sendSignupThankYouEmail(String customerName, String username, String password, String customerEmail, String bonusItem) {
		HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("customerName", customerName);
        map.put("username", username);
        map.put("password", password);
        map.put("bonusType", bonusItem);
        
        String subject = "Welcome to Search Systems";
        
        sendEmailVelocity(map, tplThankYou, emailFromThankYou, customerEmail, subject);
	}
	
	private void sendSignupReceiptEmail(User user, CreditCard cc, Transaction t, Rate r, Boolean recurring) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		String rc = "No";
		if (recurring) { rc = "Yes"; }
		map.put("invoiceNumber", t.getTransactionId());
		map.put("description", r.getRateDescription());
		map.put("amount", t.getCost());
		map.put("recurring", rc);
		
		map.put("u", user);
		map.put("cc", cc);
		map.put("countryCode", userManager.getCountryCodeById(Integer.valueOf(cc.getCountryId())));

		String subject = "Search Systems Receipt";
        sendEmailVelocity(map, tplSignupReceipt, emailFromNoReply, user.getEmail(), subject);		
	}
}