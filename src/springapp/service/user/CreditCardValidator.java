package springapp.service.user;

import java.util.Calendar;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import springapp.domain.CreditCard;
import springapp.service.NeonValidator;

public class CreditCardValidator extends NeonValidator implements Validator {
    public static final int DEFAULT_MIN_LENGTH = 13;
    public static final int DEFAULT_MAX_LENGTH = 16;
    
	public boolean supports(Class clazz) {
		return CreditCard.class.equals(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		CreditCard cc = (CreditCard) target;
		
		// number is encrypted here, maybe we need a separate validator for the 
		// cc number update service
		//this.validateString("number", cc.getNumber(), errors, minLength, maxLength, numericRegex);
		
		validateString("name",  cc.getName(),  errors, 3, 50, regexBasicExtended);
    	validateString("address1", cc.getAddress1(),  errors, 5, 50, regexBasicExtended);
    	validateString("address2", cc.getAddress2(),  errors, 0, 50, regexBasicExtended);
    	validateString("company",  cc.getCompany(),   errors, 0, 75, regexBasicExtended);
    	validateString("city",    cc.getCity(),    errors, 0, 75, regexBasicExtended);
    	validateString("state",   cc.getState(),   errors, 2, 2,  regexLettersOnly);
    	validateString("postalCode", cc.getPostalCode(), errors, 5, 9,  regexBasicSpace);
    	validateString("phone", cc.getPhone(), errors, 10, 20,  regexNumeric);
    	validateString("fax",   cc.getFax(),   errors, 0,  20,  regexNumeric);
		
		this.validateCardExpiration(Integer.valueOf(cc.getExpMonth()), Integer.valueOf(cc.getExpYear()), errors, "expMonth", "expYear");
	}
	
	// convenience method, this might be better on the form handler because
	// this uses reject instead of rejectValue
	public void validateCardNumber(String number, Errors errors) {
		if (number.length() < DEFAULT_MIN_LENGTH) {
			errors.reject("too-short", "Credit card number is too short.");
		}
		if (number.length() > DEFAULT_MAX_LENGTH) {
			errors.reject("too-long", "Credit card number is too long.");
		}		
	}
    
	public void validateCardExpiration(Integer expMonth, Integer expYear, Errors errors, String monthFieldName, String yearFieldName) { 	
		if ((expMonth == null) || (expMonth < 1) || (expMonth > 12)) {
    		errors.rejectValue(monthFieldName, "error.invalid-card-exp", "Invalid card expiration month.");
    	}
    	if ((expYear == null) || (expYear < 2008) || (expYear > 2020)) {
    		errors.rejectValue(yearFieldName, "error.invalid-card-exp", "Invalid card expiration year.");
    	}    	
    	if (errors.hasErrors()) { return; }
    	
		Calendar now = Calendar.getInstance();
		int nowYear = now.get(Calendar.YEAR);
		int nowMonth = now.get(Calendar.MONTH);
		
		if (expYear < nowYear) {
			errors.rejectValue(yearFieldName, "card-expired", "Card has expired (check year).");
		} 
		
		if (expYear == nowYear && expMonth < nowMonth) {
    		errors.rejectValue(monthFieldName, "error.card-expired", "Card has expired (check month & year).");
		}
    }
}