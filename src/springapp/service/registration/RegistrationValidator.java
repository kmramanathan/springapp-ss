package springapp.service.registration;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import springapp.domain.CreditCard;
import springapp.domain.User;
import springapp.service.NeonValidator;

public class RegistrationValidator extends NeonValidator implements Validator {
    private static final int DEFAULT_MIN_NAME_LENGTH = 2;
    private static final int DEFAULT_MAX_NAME_LENGTH = 25;    
    
    private int minNameLength = DEFAULT_MIN_NAME_LENGTH;
    private int maxNameLength = DEFAULT_MAX_NAME_LENGTH;

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public boolean supports(Class clazz) {
        return Registration.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
    	Registration r = (Registration) obj;
    	
        if (r == null) {
            errors.rejectValue("registration", "error.not-specified", null, "Value required.");
        } else {
        	validateRequiredFields(r, errors);
        	
        	// validate objects first
        	validateRegistrationUser(r.getUser(), errors);
        	validateRegistrationCreditCard(r.getCreditCard(), errors);
        	
        	// note that password is not directly attached to user here
        	// where should we encrypt it?
        	validateString("password", r.getPassword(), errors, 7, 50, passwordRegex);
        	
        	// validate the "confirm" fields
        	// note: orthogonality suggests that confirm fields should be handled by
        	// registration rather than sub-objects. current code illustrates.
        	if (!r.getConfirmPassword().equals(r.getPassword())) {
        		errors.rejectValue("confirmPassword", "error.confirm-mismatch", "The passwords do not match.");
        	}
        	if (!r.getConfirmEmail().equals(r.getUser().getEmail())) {
        		errors.rejectValue("confirmEmail", "error.confirm-mismatch", "The emails do not match.");
        	}
        	
        	validateString("hintAnswer",  r.getHintAnswer(),  errors, 3, 50, regexBasicSpace);

        	// where do we encrypt the card #?
        	validateString("cardNumber",  r.getCardNumber(),  errors, 13, 20, regexNumeric);
        	// cvv cannot be stored with the card but we need it to charge
        	validateString("cardCvv",  r.getCardCvv(),  errors, 3, 4, regexNumeric);
        	// should be in validateCC but this might be "postal code" for intl reg
        	validateString("cardZipCode", r.getCardZipCode(), errors, 5, 9,  regexNumeric);
        }
    }
    
    protected void validateRegistrationUser(User user, Errors errors) {
    	validateString("user.firstName", user.getFirstName(), errors, 2, 25, regexBasicNoSpace);
    	validateString("user.lastName",  user.getLastName(),  errors, 2, 25, regexBasicNoSpace);
    	validateString("user.username",  user.getUsername(),  errors, 4, 25, regexBasicNoSpace);
    	validateString("user.email", user.getEmail(), errors, 10, 50, emailRegex);
    }
    
    protected void validateRegistrationCreditCard(CreditCard card, Errors errors) {
    	validateCardExpiration(card, errors);

    	validateString("creditCard.name",  card.getName(),  errors, 3, 50, regexBasicSpace);
    	validateString("creditCard.address1", card.getAddress1(),  errors, 5, 50, regexBasicSpace);
    	validateString("creditCard.address2", card.getAddress2(),  errors, 0, 50, regexBasicSpace);
    	validateString("creditCard.company",  card.getCompany(),   errors, 0, 75, regexBasicSpace);
    	validateString("creditCard.city",    card.getCity(),    errors, 0, 75, regexBasicSpace);
    	validateString("creditCard.state",   card.getState(),   errors, 2, 2,  regexLettersOnly);
    	//validateString("card.zipCode", card.getZipCode(), errors, 5, 9,  numericRegex);
    	validateString("creditCard.phone", card.getPhone(), errors, 10, 20,  regexNumeric);
    	validateString("creditCard.fax",   card.getFax(),   errors, 0,  20,  regexNumeric);
    }
    
    protected void validateRequiredFields(Registration r, Errors errors) {
    	String fields[] = {
    			// user object
    			"user.firstName", "user.lastName", "user.email", "user.username",
				// special fields
    			"confirmEmail", "password", "confirmPassword",
	    		"hintQuestion", "hintAnswer", "purchasePlan",
	    		// cc object
	    		"creditCard.name", "creditCard.expMonth", "creditCard.expYear",
	    		"creditCard.address1", "creditCard.city", "creditCard.state", "creditCard.phone",
	    		// special fields	
	    		"cardNumber", "cardCvv", "cardZipCode", 
    	};
    	for (String field : fields) {
        	ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "error.field-required", "Value is required.");    	    		
    	}
    }

    
    protected void validateCardExpiration(CreditCard card, Errors errors) {
    	short month = card.getExpMonth();
    	short year = card.getExpYear();    	
    	if ((month < 1) || (month > 12)) {
    		errors.rejectValue("card.expMonth", "error.invalid-card-exp", "Invalid card expiration date.");
    	}
    	if ((year < 2014) || (year > 2024)) {
    		errors.rejectValue("card.expYear", "error.invalid-card-exp", "Invalid card expiration date.");
    	}
    	
    	Calendar calNow = Calendar.getInstance();
    	Calendar calCard = Calendar.getInstance();
    	// assume the card expires on the 28th of the month. this is reasonable and
    	// works better than assuming the 1st.
    	calCard.set(year, month, 28);
    	// this only needs to be set once for both fields since they appear together
    	if (calCard.before(calNow)) {
    		errors.rejectValue("card.expMonth", "error.card-expired", "The credit card has already expired.");
    		errors.rejectValue("card.expYear",  "error.card-expired", "The credit card has already expired.");
    	}
    }
    
	public int getMinNameLength() {
		return minNameLength;
	}

	public void setMinNameLength(int minNameLength) {
		this.minNameLength = minNameLength;
	}

	public int getMaxNameLength() {
		return maxNameLength;
	}

	public void setMaxNameLength(int maxNameLength) {
		this.maxNameLength = maxNameLength;
	}
}