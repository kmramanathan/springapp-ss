package springapp.web.search;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;


public class SearchFormValidator implements Validator {
	private static Logger logger = Logger.getLogger("Search");
	private static final String letterRegex = "^([A-Za-z]+)$";
	
	public boolean supports(Class clazz) {
		return SearchForm.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		SearchForm f = (SearchForm) obj;    	
    	validateProduct(f, errors);
    	validateSearchFields(f, errors);
	}
	
	public void validateProduct(SearchForm f, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "bgcProductId", "error.missing-product", "You must choose a product.");
		if (f.getBgcProductId() == 1) {
			errors.rejectValue("bgcProductId", "error.product-unavailable", "That product is not available.");
		}
	}
	
	public void validateSearchFields(SearchForm f, Errors errors) {
		String reqFields[] = { "firstName", "lastName" };
		for (String field : reqFields) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "error.missing-field", "Field is required.");
		}
			
		validateString("firstName", f.getFirstName(), errors, 2, 50, letterRegex);
		validateString("lastName", f.getLastName(), errors, 2, 50, letterRegex);
	}
	
	protected void validateString(String field, String value, Errors errors, int min, int max, String regex) {
		// avoid null pointer exception
		if (value != null) {
			if (value.length() < min) {
				errors.rejectValue(field, "error.field-too-short", "Input is too short (min " + min + " characters).");
			}
	    	if (value.length() > max) {
	    		errors.rejectValue(field, "error.field-too-long", "Input is too long (max " + max + " characters).");
	    	}
	    	// only check regex against non-zero strings
	    	if ((value.length() > 0) && !Pattern.matches(regex, value)) {
	    		errors.rejectValue(field, "error.invalid-chars", "Input contains invalid characters.");
	    	}
		}
    }
}