package springapp.web.admin.user;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.crypt.Crypt;
import springapp.domain.CreditCard;
import springapp.domain.Transaction;
import springapp.domain.User;
import springapp.service.NeonValidator;
import springapp.service.billing.BillingManager;
import springapp.service.billing.BillingManager.TxnType;
import springapp.service.user.CreditCardValidator;
import springapp.service.user.UserManager;

@Controller
@RequestMapping("/admin/reactivateUser.do")
@SessionAttributes({"command"})

public class ReactivateUserForm {
	protected final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private BillingManager billingManager;
	
	// db lookups
	@ModelAttribute("countryCodes")
	public HashMap<Integer, String> populateCountryCodes() {
		return userManager.getCountryISOCodesById();
	}
	// static lookups
	private static final Integer[] cardExpirationMonths = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	@ModelAttribute("cardExpirationMonths")
    public Integer[] populateCardExpirationMonths() {		
		return cardExpirationMonths;
	}
    private static final Integer[] cardExpirationYears = {2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020};
	@ModelAttribute("cardExpirationYears")
    public Integer[] populateCardExpirationYears() {		
		return cardExpirationYears;
	}
	
	/*
	 * sequence for reactivation:
	 * 1) make sure user is inact/disab
	 * 2) prompt for billing update
	 * 3) charge card & set active (check flags?)
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm(@RequestParam("username") String username) {
		// don't allow this if user is active
		User user = userManager.getUserByUsername(username);
		if (!(user.getDisabled() || user.getInactive())) {
			return new ModelAndView("admin/ReactivateUserInvalid", "username", username);
		}
		
		ReactivateUserFormCommand command = new ReactivateUserFormCommand();
		command.setUsername(username);
		CreditCard cc = userManager.getCreditCard(user.getUserId());
		command.setCreditCard(cc);
		command.setCardNumber(Crypt.decrypt(cc.getNumber()));
		
		return new ModelAndView("admin/ReactivateUser", "command", command);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			ModelMap map,
			@ModelAttribute("command") ReactivateUserFormCommand command,
			Errors errors,
			SessionStatus status) {
		logger.info("username: " + command.getUsername());
		User u = userManager.getUserByUsername(command.getUsername());
		if (u == null) {
			errors.rejectValue("username", "no-such-user", "User not found.");
		}
		new ReactivateUserFormValidator().validate(command, errors);
		
		if (errors.hasErrors()) {
			return "admin/ReactivateUser";
		} else {
			CreditCard cc = command.getCreditCard();
			// update card
			String ccNumber = command.getCardNumber();
			cc.setNumber(Crypt.encrypt(ccNumber));
			int ccLen = ccNumber.length();
			String last4 = command.getCardNumber().substring(ccLen - 4, ccLen);
			cc.setLastDigits(Short.parseShort(last4));
			
			cc.setSuspended(false);
			cc.setVerified(true);
			cc.setCardModified(true);
			
			userManager.saveCreditCard(cc);
			
			// figure out amount
			BigDecimal amount = u.getSubAmount();
			logger.info("amount: " + amount);
			
			//changed by shahul
			short category = 80;
			short subCategory=80;
			
			// charge card
			Integer invoiceNumber = u.getUserId();
			Transaction t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, "Account Reactivation", invoiceNumber, category, subCategory);			
			switch (t.getTransactionStatusId()) {
			case BillingManager.txnApproved:
				// set active
				u.setInactive(false);
				u.setDisabled(false);
				userManager.saveUser(u);
				map.addAttribute("cardApprovalCode", t.getBankApprovalCode());
				map.addAttribute("amount", t.getCost());
				break;
			case BillingManager.txnDeclined:
				map.addAttribute("cardDeclined", true);
				map.addAttribute("cardDeclinedReason", t.getBankResponseReasonText());
				break;
			case BillingManager.txnError:
				map.addAttribute("cardError", true);
				map.addAttribute("cardErrorReason", t.getBankResponseReasonText());
				break;
			}
			
			// use a completion/status view here?
			// the req is "complete" in a sense
			map.addAttribute("username", u.getUsername());
			status.setComplete();

			return "admin/ReactivateUserComplete";
		}
	}
	
	public static class ReactivateUserFormCommand {
		private String username;
		private CreditCard creditCard;
		private String cardNumber;

		public String getCardNumber() {
			return cardNumber;
		}
		public void setCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public CreditCard getCreditCard() {
			return creditCard;
		}
		public void setCreditCard(CreditCard creditCard) {
			this.creditCard = creditCard;
		}		
	}
	
	protected static class ReactivateUserFormValidator extends NeonValidator {
		private final int DEFAULT_MIN_LENGTH = 3;
	    private final int DEFAULT_MAX_LENGTH = 50;
	    
		public boolean supports(Class clazz) {
			return ReactivateUserFormCommand.class.equals(clazz);
		}

		public void validate(Object target, Errors errors) {
			ReactivateUserFormCommand c = (ReactivateUserFormCommand) target;
			validateString("username", c.getUsername(), errors, DEFAULT_MIN_LENGTH, DEFAULT_MAX_LENGTH, usernameRegex);
			
			errors.pushNestedPath("creditCard");
			new CreditCardValidator().validate(c.getCreditCard(), errors);
			errors.popNestedPath();
		}		
	}
}