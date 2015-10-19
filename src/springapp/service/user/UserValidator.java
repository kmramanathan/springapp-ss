package springapp.service.user;

import java.util.regex.Pattern;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import org.apache.log4j.Logger;

import springapp.domain.User;
import springapp.service.NeonValidator;

public class UserValidator extends NeonValidator implements Validator {
    private int DEFAULT_MIN_LENGTH = 2;
    private int DEFAULT_MAX_LENGTH = 8;
    private int minLength = DEFAULT_MIN_LENGTH;
    private int maxLength = DEFAULT_MAX_LENGTH;

    protected final Logger logger = Logger.getLogger(getClass());

    public boolean supports(Class clazz) {
        return User.class.equals(clazz);
    }

    /* 
     * should we attempt to validate fields that are presumably read-only?
     * it could be a point of failure if code should get out of sync. 
     * a junit test to catch that would be a good idea.
     */
    public void validate(Object obj, Errors errors) {
    	User user = (User) obj;

    	// we do not validate whether the username is unique. could that be useful here?
    	// not sure we want to get the data layer involved at this point.
    	validateString("username",  user.getUsername(),  errors, 4, 25, usernameRegex);
    	
        // let this method do its work
        validateQuick(obj, errors);
    }
    
    /*
     * we will go ahead and write a validator that assumes only certain fields
     * have been changed. if a customer can edit the field, it should go here.
     */
    public void validateQuick(Object obj, Errors errors) {
    	User user = (User) obj;
    	
    	validateString("firstName", user.getFirstName(), errors, 2, 25, regexBasicExtended);
    	validateString("lastName",  user.getLastName(),  errors, 2, 25, regexBasicExtended);
    	validateString("email", user.getEmail(), errors, 10, 50, emailRegex);
    	
    	validateString("passAnswer", user.getPassAnswer(), errors, 2, 50, regexBasicExtended);
    }
    
    /*
     *  how do we handle passwords?
     *  it might be best to treat it as an opaque security token within this
     *  object and create a separate mechanism for password changes.
     *  
     *  for now, we'll just do it here and provide a convenience method for this.
     *  note that this only uses the password and not the entire user object.
     */
    public void validatePassword(String passwordClear, Errors errors) {
    	validateString("password", passwordClear, errors, 6, 20, passwordRegex, "Password");    	
    }

    // put any username checking here
    // expose it, useful to web tier
    public Boolean validateUsername(String username) {
    	if (Pattern.matches(usernameRegex, username)) {
    		return true;
    	}
    	return false;
    }
    
	public synchronized int getMinLength() {
		return minLength;
	}

	public synchronized void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public synchronized int getMaxLength() {
		return maxLength;
	}

	public synchronized void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
}