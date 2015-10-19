package springapp.web.user;

import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.crypt.Crypt;
import springapp.domain.CreditCard;
import springapp.domain.User;
import springapp.web.BillingInformationFormCommand;
import springapp.web.BillingInformationFormCommandValidator;
import springapp.web.funnel.PurchaseForm.PurchaseFormCommand;

@Controller
@RequestMapping("/user/updateBillingInfo.do")

public class UpdateBillingInfo extends AbstractUserController {
	protected static final TreeMap<Integer,String> cardExpirationMonths = new TreeMap<Integer,String>();
	protected static final TreeMap<Integer,String> cardExpirationYears = new TreeMap<Integer,String>();

	public void init() {
		for (int i=1; i<=12; i++) {
			cardExpirationMonths.put(i, String.valueOf(i));
		}		
		for (int i=2008; i<=2020; i++) {
			cardExpirationYears.put(i, String.valueOf(i));
		}
	}
	
	protected void setCCFromCommand(BillingInformationFormCommand cmd, CreditCard cc, Integer countryId) {
		cc.setName(cmd.getName());
		cc.setAddress1(cmd.getAddress());
		cc.setCity(cmd.getCity());
		cc.setState(cmd.getState());
		cc.setPostalCode(cmd.getPostalCode());
		cc.setPhone(cmd.getPhone());
				
		cc.setCountryId(countryId.shortValue());
				
		String ccNum = cmd.getCcNumber();
		String ccLast4 = ccNum.substring(ccNum.length() - 4, ccNum.length());
		cc.setNumber(Crypt.encrypt(ccNum));
		cc.setLastDigits(Short.parseShort(ccLast4));
		cc.setExpMonth(cmd.getCcExpMonth().shortValue());
		cc.setExpYear(cmd.getCcExpYear().shortValue());
	}
	
	protected void setDefaults(BillingInformationFormCommand cmd) {		
		if (!cmd.getI18n()) { cmd.setCountryCode("US"); }
		cmd.setAcceptAgreement(false);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm(
			HttpSession session,
			@RequestParam(value="i18n",required=false) Boolean i18n) {
		if (i18n == null) { i18n = false; }

		String username = getUsernameFromSession(session);
		User u = userManager.getUserByUsername(username);
		CreditCard cc = userManager.getCreditCard(u.getUserId());
		BillingInformationFormCommand command = new BillingInformationFormCommand();
		
		// set command vars from user info
		if (cc != null) {
			String countryCode = userManager.getCountryCodeById(Integer.valueOf(cc.getCountryId()));
			command.populateFormFieldsFromCC(cc, countryCode);			
		}
		command.setI18n(i18n);
		setDefaults(command);
		return new ModelAndView(updateBillingInfoView, "command", command);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			@ModelAttribute("command") BillingInformationFormCommand command,
			Errors errors,
			SessionStatus status) {
		new BillingInformationFormCommandValidator().validate(command, errors);
		
		// check the country code
		Integer countryId = userManager.getCountryIdByCode(command.getCountryCode());
		if (countryId == null) {
			errors.rejectValue("countryCode", "invalid-country-code", "Invalid country code.");
		}
		
		if (errors.hasErrors()) {
			logger.info("errors: " + errors.getAllErrors());
			return updateBillingInfoView;
		} else {
			// do update
			String username = getUsernameFromSession(session);
			User u = userManager.getUserByUsername(username);
			CreditCard cc = userManager.getCreditCard(u.getUserId());
			if (cc == null) {
				cc = new CreditCard();
				cc.setUserId(u.getUserId());
			}
			
			setCCFromCommand(command, cc, countryId);
			
			userManager.saveCreditCard(cc);
			
			status.setComplete();
			return "redirect:" + indexUrl;
		}	
	}
	
	// db lookups
	@ModelAttribute("secretQuestions")
	public HashMap<Integer, String> populateSecretQuestions() {
		return userManager.getPassQuestions();
	}
	
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
}