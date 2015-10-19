package springapp.web.conversion;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import springapp.crypt.Crypt;
import springapp.domain.CreditCard;
import springapp.domain.Rate;
import springapp.domain.Subscription;
import springapp.domain.Transaction;
import springapp.domain.User;
import springapp.service.billing.BillingManager;
import springapp.service.billing.RateManager;
import springapp.service.user.UserManager;
import springapp.web.conversion.VerifyBillingForm.VerifyBillingFormCommand;

@Controller("conversionConfirmForm")
@SessionAttributes("VerifyBillingFormCommand")

public class ConfirmForm extends AbstractConversionController {
	@Autowired
	private UserManager userManager;
	@Autowired
	private RateManager rateManager;
	@Autowired
	private BillingManager billingManager;
	@Autowired
	private MailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;
	
	@RequestMapping(value = "/conversion/confirm.do", method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map
			) {
		ConversionUserSession cus = (ConversionUserSession) session.getAttribute("ConversionUserSession");
		if (cus == null || !cus.isAuthenticated()) {
			return loginRedir;
		}
		
		VerifyBillingFormCommand command = (VerifyBillingFormCommand) session.getAttribute("VerifyBillingFormCommand");
		if (command == null) {
			return verifyBillingRedir;
		}
		
		Rate r = rateManager.getRate(command.getRateId()); 
		
		String ccNumber = command.getCcNumber();
		String ccLast4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
		map.addAttribute("customerName", command.getName());
		map.addAttribute("customerUsername", cus.getUsername());
		map.addAttribute("ccLast4", ccLast4);
		map.addAttribute("paymentPlan", r.getRateDescription());
		map.addAttribute("recurring", command.getRecurringBilling());
		
		return vwConfirm;
	}
	
	@RequestMapping(value = "/conversion/confirm.do", method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("VerifyBillingFormCommand") VerifyBillingFormCommand command,
			Errors errors,
			SessionStatus status) {
		ConversionUserSession cus = (ConversionUserSession) session.getAttribute("ConversionUserSession");
		if (cus == null || !cus.isAuthenticated()) {
			return loginRedir;
		}
			
		// update card
		User u = userManager.getUserByUsername(cus.getUsername());
		CreditCard cc = userManager.getCreditCard(u.getUserId());
		if (cc == null) {
			cc = new CreditCard();
			cc.setUserId(u.getUserId());
		}
		setCCFromCommandObject(command, cc);
		userManager.saveCreditCard(cc);
		
		String signupFor = "Conversion";
		
		// add subscription
		// XXX need rate id, rebill
		Integer rateId = command.getRateId();
		Boolean rebill = command.getRecurringBilling();
		Subscription s = userManager.addSubscription(u.getUserId(), rateId, rebill );
		
		// charge card
		Transaction t = billingManager.billSubscription(u, cc, s, signupFor);
		
		if (t.getTransactionStatusId() == BillingManager.txnApproved) {
			// advance sub
			billingManager.advanceSubscription(s);
			
			// modify user account: update flags, etc
			u.setInactive(false);
			u.setNoExport(true);
			u.setNoPremium(false);
			u.setSubDirectoryUser(true);
			userManager.saveUser(u);
			
			// send email
			sendSignupThankYouEmail(cc.getName(), u.getUsername(), u.getEmail());
			
			status.setComplete();
			return successRedir;
		} else {
			errors.reject("", "The credit card was declined.");
			return vwVerifyBilling;
		}
	}
	
	@RequestMapping(value = "/conversion/success.do", method = RequestMethod.GET)
	public String showSampleReport(HttpSession session, ModelMap map)  {
		ConversionUserSession cus = (ConversionUserSession) session.getAttribute("ConversionUserSession");
		if (cus == null || !cus.isAuthenticated()) {
			return loginRedir;
		}
		
		// set fields for success page
		map.addAttribute("username", cus.getUsername());

		session.invalidate();
		
		return vwSuccess;
	}
	
	protected void setCCFromCommandObject(VerifyBillingFormCommand cmd, CreditCard cc) {
		cc.setName(cmd.getName());
		cc.setAddress1(cmd.getAddress());
		cc.setCity(cmd.getCity());
		cc.setState(cmd.getState());
		cc.setPostalCode(cmd.getPostalCode());
		cc.setPhone(cmd.getPhone());
		
		Integer i = userManager.getCountryIdByCode(cmd.getCountryCode());
		Short s = i.shortValue();
		cc.setCountryId( s );
		
		String number = cmd.getCcNumber();
		String last4 = number.substring(number.length() - 4, number.length());
		cc.setNumber(Crypt.encrypt(number));
		cc.setLastDigits(Short.parseShort(last4));
		cc.setExpMonth(cmd.getCcExpMonth().shortValue());
		cc.setExpYear(cmd.getCcExpYear().shortValue());
	}
	
	protected void sendSignupThankYouEmail(String customerName, String username, String customerEmail) {
		HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("customerName", customerName);
        map.put("username", username);
        
        String subject = "Search Systems Account Upgrade";
        
        sendEmailVelocity(map, tplUpgradeThankYou, emailFromThankYou, customerEmail, subject);
	}
	
	protected void sendEmailVelocity(HashMap<String,Object> map, String velocityTemplate, String emailFrom, String emailTo, String subject) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(emailFrom);
		msg.setTo(emailTo);
		msg.setSubject(subject);
		        
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, velocityTemplate, map);
		
		msg.setText(text);
		try {
			mailSender.send(msg);
		} catch (Exception e) {
			logger.error("Problem sending email", e);
		}		
	}
}