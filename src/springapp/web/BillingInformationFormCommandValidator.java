package springapp.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import springapp.service.NeonValidator;
import springapp.service.user.CreditCardValidator;
import springapp.web.funnel.PurchaseForm.PurchaseFormCommand;

public class BillingInformationFormCommandValidator extends NeonValidator implements Validator {
	public boolean supports(Class clazz) {
		return clazz.equals(BillingInformationFormCommand.class);
	}
	
	public void validate(Object target, Errors errors) {
		BillingInformationFormCommand cmd = (BillingInformationFormCommand) target;
		
		validateString("name",     cmd.getName(),       errors,  5, 50, regexBasicSpace, "Card Holder Name");
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
			// XXX fix this
//				Integer cid = userManager.getCountryIdByCode(cmd.getCountryCode());
//				if (cid == null) {
//					errors.rejectValue("countryCode", "invalid-country-code", "Invalid country code.");
//				}
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