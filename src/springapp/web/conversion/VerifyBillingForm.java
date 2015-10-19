package springapp.web.conversion;

import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import springapp.crypt.Crypt;
import springapp.domain.CreditCard;
import springapp.domain.Rate;
import springapp.domain.User;
import springapp.service.NeonValidator;
import springapp.service.billing.RateManager;
import springapp.service.user.CreditCardValidator;
import springapp.service.user.UserManager;
import springapp.web.funnel.PurchaseForm;
import springapp.web.funnel.PurchaseForm.PurchaseFormCommand;

@Controller
@RequestMapping("/conversion/verifyBilling.do")
@SessionAttributes({"command"})

public class VerifyBillingForm extends AbstractConversionController {
	protected static final TreeMap<Integer,String> cardExpirationMonths = new TreeMap<Integer,String>();
	protected static final TreeMap<Integer,String> cardExpirationYears = new TreeMap<Integer,String>();
	
	protected static final HashMap<String, Integer> countryCodesToIds = new HashMap<String, Integer>();
	
	protected static final TreeMap<Integer,String> paymentPlansPremium = new TreeMap<Integer,String>();
	protected static final TreeMap<Integer,String> paymentPlansMember  = new TreeMap<Integer,String>();
	protected static final Integer[] ratesPremium = { 84, 85 };
	protected static final Integer[] ratesMember  = { 85 };
	
	@Autowired
	private UserManager userManager;
	@Autowired
	private RateManager rateManager;
	
	public void init() {
		for (int i=1; i<=12; i++) {
			cardExpirationMonths.put(i, String.valueOf(i));
		}		
		for (int i=2008; i<=2020; i++) {
			cardExpirationYears.put(i, String.valueOf(i));
		}
		countryCodesToIds.putAll(userManager.getCountryIdsByCode());
		
		for (int i : ratesMember) {
			Rate rate = rateManager.getRate(i);
			if (rate == null) {
				logger.error("Startup: Could not find rate " + i);
			} else {
				paymentPlansMember.put(rate.getRateId(), rate.getRateName());
			}
		}
		
		for (int i : ratesPremium) {
			Rate rate = rateManager.getRate(i);
			if (rate == null) {
				logger.error("Startup: Could not find rate " + i);
			} else {
				paymentPlansPremium.put(rate.getRateId(), rate.getRateName());
			}
		}

	}
	
//	protected boolean verifySession(HttpSession session) {
//		ConversionUserSession cus = (ConversionUserSession) session.getAttribute("ConversionUserSession");
//		if (cus == null || !cus.isAuthenticated()) {
//			return false;
//		}
//		return true;
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="i18n",required=false) Boolean i18n
			) {
		if (i18n == null) { i18n = false; }
		
		ConversionUserSession cus = (ConversionUserSession) session.getAttribute("ConversionUserSession");
		if (cus == null || !cus.isAuthenticated()) {
			return loginRedir;
		}
		
		map.addAttribute("directPass", cus.isDirectPass());
		map.addAttribute("monthly", cus.isMonthly());
		
		// determine if user is US or INTL
		VerifyBillingFormCommand cmd = new VerifyBillingFormCommand();

		User u = userManager.getUserByUsername(cus.getUsername());
		CreditCard cc = userManager.getCreditCard(u.getUserId());
		if (cc == null) {
			cc = new CreditCard();
			cc.setCountryId((short) 224);
		} else {
			// populate form fields from database
			setFormFields(cmd, cc);
		}
		
		cmd.setI18n(i18n);

		map.addAttribute("command", cmd);
		return vwVerifyBilling;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") VerifyBillingFormCommand command,
			Errors errors,
			SessionStatus status) {
		
		// verify session
		ConversionUserSession cus = (ConversionUserSession) session.getAttribute("ConversionUserSession");
		if (cus == null || !cus.isAuthenticated()) {
			logger.info("cus: " + cus);
			return loginRedir;
		}
				
		// validate form
		VerifyBillingFormValidator v = new VerifyBillingFormValidator();
		v.validate(command, errors);
		if (errors.hasErrors()) {
			for (Object o : errors.getGlobalErrors()) {
				ObjectError oe = (ObjectError) o;
				logger.error("error: " + oe.toString());
			}
			for (Object o : errors.getFieldErrors()) {
				FieldError fe = (FieldError) o;
				logger.error("error: " + fe.toString());
			}
			return vwVerifyBilling;
		}			

		// everything is good, show confirm
		session.setAttribute("VerifyBillingFormCommand", command);
		status.setComplete();
		return confirmRedir;
	}
	
	protected void setFormFields(VerifyBillingFormCommand cmd, CreditCard cc) {
		cmd.setName(cc.getName());
		cmd.setCcNumber(Crypt.decrypt(cc.getNumber()));
		cmd.setCcExpMonth(Integer.valueOf(cc.getExpMonth()));
		cmd.setCcExpYear(Integer.valueOf(cc.getExpYear()));
		
		cmd.setAddress(cc.getAddress1());
		cmd.setCity(cc.getCity());
		cmd.setState(cc.getState());
		cmd.setPostalCode(cc.getPostalCode());
		cmd.setPhone(cc.getPhone());
		cmd.setCountryCode(userManager.getCountryCodeById(Integer.valueOf(cc.getCountryId())));

		cmd.setAcceptAgreement(false);
	}
	
	public static class VerifyBillingFormCommand extends PurchaseForm.PurchaseFormCommand {	
		
	}
	
	public static class VerifyBillingFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(PurchaseFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			PurchaseFormCommand cmd = (PurchaseFormCommand) target;
			
			validateString("name",     cmd.getName(),       errors,  5, 50, regexBasicExtended, "Card Holder Name");
			validateString("address",  cmd.getAddress(),    errors,  5, 50, regexBasicExtended, "Billing Address");
			validateString("city",     cmd.getCity(),       errors,  3, 50, regexBasicExtended, "Billing City");
			validateString("phone",    cmd.getPhone(),      errors, 10, 20, regexNumeric, "Phone");

			// only validate email for search
			if (cmd.getSearch()) {
				if (!cmd.getConfirmEmail().equals(cmd.getEmail())) {
					errors.rejectValue("confirmEmail", "email-mismatch", "The email addresses do not match.");
				} else {
					validateString("email",     cmd.getEmail(),     errors,  5, 50, emailRegex, "Email");
				}
			}
			
			// because this is a hash map and not a db ILIKE, we need to worry about case			
			cmd.setCountryCode(cmd.getCountryCode().toUpperCase());
			
			// us/intl
			if (cmd.getI18n()) {
				// intl
				validateString("countryCode",  cmd.getCountryCode(), errors,  2, 2, regexBasicNoSpace, "Country Code");
				// make sure the country code exists
				Integer cid = countryCodesToIds.get(cmd.getCountryCode()); 
				//userManager.getCountryIdByCode(cmd.getCountryCode());
				
				if (cid == null) {
					errors.rejectValue("countryCode", "invalid-country-code", "Invalid country code.");
				}
			} else {
				// US
				validateString("state",    cmd.getState(),      errors,  2, 2, regexBasicNoSpace, "State");
				validateString("postalCode",  cmd.getPostalCode(), errors,  5, 10, regexNumeric, "ZIP Code");
			}
			
			// validate card data
			validateString("ccNumber", cmd.getCcNumber(), errors,  13,  16, regexNumeric, "Credit Card Number");
			validateString("ccAuthCode", cmd.getCcAuthCode(), errors,  3,  4, regexNumeric, "Authorization Code");

			validateString("ccExpMonth", cmd.getCcExpMonth(), errors,  1, 2, regexNumeric, "Expiration Month");
			validateString("ccExpYear", cmd.getCcExpYear(), errors,  4,  4, regexNumeric, "Expiration Year");

			if (!cmd.getAcceptAgreement()) {
				errors.rejectValue("acceptAgreement", "accept-agreement", "You must accept the user agreement to continue.");
			}

			// can bail out here before messing with the card
			if (errors.hasErrors()) { return; }
			
			CreditCardValidator ccv = new CreditCardValidator();
			if (cmd.getCcNumber() != null) {
				ccv.validateCardNumber(cmd.getCcNumber(), errors);
				ccv.validateCardExpiration(cmd.getCcExpMonth(), cmd.getCcExpYear(), errors, "ccExpMonth", "ccExpYear");
			}			
		}		
	}	
	
	// db lookups
	@ModelAttribute("countryCodes")
	public HashMap<Integer, String> populateCountryCodes() {
		return userManager.getCountryISOCodesById();
	}
	
	@ModelAttribute("states")
	public HashMap<String, String> populateStates() {
		return userManager.getUSStates(true);
	}
	
	// static lookups
	@ModelAttribute("cardExpirationMonths")
    public TreeMap<Integer, String> populateCardExpirationMonths() {
		return cardExpirationMonths;
	}

	@ModelAttribute("cardExpirationYears")
    public TreeMap<Integer, String> populateCardExpirationYears() {
		return cardExpirationYears;
	}
	
	@ModelAttribute("paymentPlansPremium")
    public TreeMap<Integer, String> populatePaymentPlansPremium() {		
		return paymentPlansPremium;
	}
	
	@ModelAttribute("paymentPlansMember")
	public TreeMap<Integer, String> populatePaymentPlansMember() {		
		return paymentPlansMember;
	}
}