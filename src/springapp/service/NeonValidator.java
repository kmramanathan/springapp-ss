package springapp.service;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

abstract public class NeonValidator implements Validator {
	protected final Logger logger = Logger.getLogger(getClass());
	
	// java requires double backslashes in regex, be careful
    protected static final String regexBasicNoSpace = "^([-A-Za-z0-9]+)$";
    protected static final String regexBasicSpace   = "^([-A-Za-z0-9 ]+)$";
    protected static final String regexBasicSpaceAmpersand   = "^([-A-Za-z0-9 &]+)$";
    protected static final String regexBasicExtended = "^([-A-Za-z0-9 .,'#]+)$";
    protected static final String regexText = "^([-A-Za-z0-9 \r\n\"'`!?/@#$%^&*()_.,+-]+)$";
    protected static final String regexLettersOnly       = "^([A-Za-z]+)$";
    protected static final String regexNumeric      = "^([0-9]+)$";
    protected static final String regexNumericDashSpace = "^([-0-9 ]+)$";
    
    //newly added
    protected static final String regexLettersWithSpace       = "^([A-Za-z ]+)$";
    
    // Date format : mm/dd/yyyy checking
    protected static final String regexDOB= "^((0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)[0-9][0-9])$";

    // specific regex for username checking
    protected static final String usernameRegex   = "^([A-Za-z0-9]+)([-_A-Za-z0-9]+)$";
    
    // regex allows: alphanumeric, any of 0-9 shifted, a few others (period, comma, plus, minus)
    // reminder: the char class for the regex below ends at +-]. 
    // the trailing +)$ is a separate part!
    protected static final String passwordRegex     = "^([A-Za-z0-9!@#$%^&*()_.,+-]+)$";
    
    // use this when a full email address (a@b.com) is expected
    protected static final String emailRegex        = "^([^@\\s]+)@((?:[-a-zA-Z0-9]+\\.)+[a-zA-Z]{2,})$";
    
    // use this for a full or partial email address
    //protected static final String emailRegexRelaxed = "^([^@\\s]?)@?((?:[-a-z0-9]+\\.)?)$";    
    // bleh, might need to just do this
    protected static final String emailRegexRelaxed = "^([A-Za-z0-9@_.+-]+)$";

    //protected static final String qfn = "'" + field + "'";
    

    /**
     * Validate an Integer. Sets fieldDescription = fieldName.
     * 
     * @see #validateInteger(String, Integer, Errors, int, int, String, String)
     */
    protected void validateInteger(String field, Integer value, Errors errors, int min, int max) {
    	this.validateInteger(field, value, errors, min, max, field);    	
    }
    
    /**
     * Validate an Integer.
     * 
     * @param field Name of the field in the form context
     * @param value Value to be validate
     * @param errors Errors object for the form
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @param fieldDescription Description of field ("cleaner" name to be used in messages)
     */
    protected void validateInteger(String field, Integer value, Errors errors, int min, int max, String fieldDescription) {
    	Object[] errorArgs;
    	String qfn = "'" + field + "'";
    	if (value == null) {
    		errors.rejectValue(field, "error.invalid-number", "Invalid number for field " + qfn + ".");
    	}
    	if (value < min) {
    		errorArgs = new Object[] { fieldDescription, min };
    		errors.rejectValue(field, "error.integer-too-low", errorArgs, "Value is too low.");
    	}
    	if (value > max) {
    		errorArgs = new Object[] { fieldDescription, max };
    		errors.rejectValue(field, "error.integer-too-high", errorArgs, "Value is too high.");    		
    	}
    }
    

    /**
     * Validate a String value which contains an Integer.. Sets fieldDescription = fieldName.
     * 
     * @see #validateString(String, Integer, Errors, int, int, String, String)
     */
    protected void validateString(String field, Integer value, Errors errors, int min, int max, String regex) {
    	this.validateString(field, value, errors, min, max, regex, field);    	
    }
    
    /**
     * Validate an String which contains an Integer. This checks for null 
     * then passes value.toString() on to the main method.
     *  
     * @param field
     * @param value
     * @param errors
     * @param min
     * @param max
     * @param regex
     */
    protected void validateString(String field, Integer value, Errors errors, int min, int max, String regex, String fieldDescription) {
    	String qfn = "'" + field + "'";
    	if (value == null) {
    		errors.rejectValue(field, "error.invalid-number", "Invalid number for field " + qfn + ".");
    		return;
    	}
    	this.validateString(field, value.toString(), errors, min, max, regex, field);    	
    }

    /**
     * Validate a String value. regex is unused if String.length() == 0.
     * min > 0 will reject a String that is entirely whitespace. 
     * 
     * @param field Name of the field in the form context
     * @param value Value to be validate
     * @param errors Errors object for the form
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @param regex Regex to validate input against
     * @param fieldDescription Description of field ("cleaner" name to be used in messages)
     */
    protected void validateString(String field, String value, Errors errors, int min, int max, String regex, String fieldDescription) {
    	// reject if empty (but only if min > 0)
    	Object[] errorArgs;    	
    	if (min > 0) {
    		errorArgs = new Object[] { fieldDescription };
    		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "error.field-required", errorArgs);
    		ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "error.field-required-nicer", errorArgs, "A required field is missing.");
        	// return immediately here because we know it will fail again
    		if (errors.hasFieldErrors(field)) {
    			return;
    		}
    		
    	}

    	// check length
    	if (value.length() > max) {
    		errorArgs = new Object[] { fieldDescription, max };
    		errors.rejectValue(field, "error.field-too-long", errorArgs, "Input is too long (max " + max + " characters) for " + fieldDescription + ".");
    	}

    	if (value.length() < min) {
    		errorArgs = new Object[] { fieldDescription, min };
    		errors.rejectValue(field, "error.field-too-short", errorArgs, "Input is too short (min " + min + " characters) for " + fieldDescription + ".");
    	}
    	
    	// only check regex if the value to be checked has length > 0.
    	// min string length has already been checked above so the regex doesn't 
    	// need to worry about it. however, the regex would fail on an empty string,
    	// so assuming that is permitted above we can skip the regex check here.
    	if ((value.length() > 0) && !Pattern.matches(regex, value)) {
    		logger.info("invalid string for :field: " + field + " :regex: " + regex + " :value: " + value);
    		errorArgs = new Object[] { fieldDescription };
    		String errorMsg;
    		if (field.equals("email") || field.equals("confirmMail")) {
    			errorMsg = "The email address is invalid.";
    			errors.rejectValue(field, "error.invalid-email", errorArgs, errorMsg);
    		}else if(field.equals("searchDob")){
    			errorMsg = "The DOB is invalid";
    			errors.rejectValue(field, "error.invalid-DOB", errorArgs, errorMsg);
    		}else {
    			errorMsg = "Input contains invalid characters for " + fieldDescription + ".";
    			errors.rejectValue(field, "error.invalid-chars", errorArgs, errorMsg);    			
    		}
    	}
    }
    
    /**
     * Validate a String value. Sets fieldDescription = fieldName.
     * 
     * @see #validateString(String, Integer, Errors, int, int, String, String)
     */
    protected void validateString(String field, String value, Errors errors, int min, int max, String regex) {
    	this.validateString(field, value, errors, min, max, regex, field);
    }
    
    // validate an email address
//    protected void validateEmail(String field, String value, Errors errors, String description) {
//    	this.validateString(field, value, errors, min, max, regex, field);
//    }
    
    
    /**
     * Validate a String value. regex is unused if String.length() == 0.
     * min > 0 will reject a String that is entirely whitespace.
     * <p/>
     * 
     * Variant of validateString(). Specifically allows the string to be empty or null
     * but it will be verified if it exists. Allows blank fields to be ignored while 
     * enforcing a minimum length if used. Subtle difference between this and 
     * minLength = 0 above.
     * 
     * @param field Name of the field in the form context
     * @param value Value to be validate
     * @param errors Errors object for the form
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @param regex Regex to validate input against
     * @param fieldDescription Description of field ("cleaner" name to be used in messages)
     */
    protected void validateStringEmptyOk(String field, String value, Errors errors, int min, int max, String regex, String fieldDescription) {
    	if (value == null || value.equals("")) {
    		return;
    	}
    	this.validateString(field, value, errors, min, max, regex, fieldDescription);
    }

    /**
     * Validate a String value. Sets fieldDescription = fieldName.
     * 
     * @see #validateStringEmptyOk(String, Integer, Errors, int, int, String, String)
     */
    protected void validateStringEmptyOk(String field, String value, Errors errors, int min, int max, String regex) {
    	if (value == null || value.equals("")) {
    		return;
    	}
    	this.validateString(field, value, errors, min, max, regex);
    }
    
    protected void validateStringEmptyMsg(String field, String value, Errors errors, int min, int max, String regex, String fieldDescription, String emptyMsg) {
    	// reject if empty (but only if min > 0)
    	Object[] errorArgs;    	
    	if (min > 0) {
    		errorArgs = new Object[] { emptyMsg };
    		ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "error.defined-msg", errorArgs);
        	// return immediately here because we know it will fail again
    		if (errors.hasFieldErrors(field)) {
    			return;
    		}
    		
    	}
    	this.validateStringEmptyOk(field, value, errors, min, max, regex, fieldDescription);
    }

}