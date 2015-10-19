package springapp.web.registration;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import springapp.crypt.Crypt;
import springapp.domain.CreditCard;
import springapp.domain.User;
import springapp.service.registration.Registration;
import springapp.service.user.UserManager;

public class RegistrationFormController extends SimpleFormController {
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    private UserManager userManager;

    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors) throws Exception {
        Registration reg = (Registration) command;
        
        // most values for these are already set in the jsp
        // anything set here requires special handling
        User user = reg.getUser();
        user.setPassword(DigestUtils.md5Hex(reg.getPassword()));
        user.setPassQuestionId((short) reg.getHintQuestion());
        user.setPassAnswer(reg.getHintAnswer());        
        user.setNewsletter(reg.isReceiveNewsletter());       
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        
        switch (reg.getPurchasePlan()) {
	        case 1:
	        	// 4.95
	        	user.setSubAmount(new BigDecimal("4.95"));
	        	user.setSubInterval((short) 1);
	        	user.setSubIntervalUnit("Month");
	        	user.setSubBillPeriod((short) 36);
	        	
	        	cal.add(Calendar.MONTH, 1);	        	   
	        	user.setSubStartDate(sdf.format(cal.getTime()));
	        	break;
	        	
	        case 2:
	        	// 48.50
	        	user.setSubAmount(new BigDecimal("48.50"));
	        	user.setSubInterval((short) 365);
	        	user.setSubIntervalUnit("Days");
	        	user.setSubBillPeriod((short) 3);
	        	
	        	cal.add(Calendar.YEAR, 1);     	
	        	user.setSubStartDate(sdf.format(cal.getTime()));
	        	break;
	        	
        	default:
        		throw new Exception("Invalid purchase plan.");
        }
        
        CreditCard card = reg.getCreditCard();  
        card.setPostalCode(reg.getCardZipCode());
        card.setNumber(Crypt.encrypt(reg.getCardNumber()));
        int length = reg.getCardNumber().length();
        String last4 = reg.getCardNumber().substring(length - 4);       
        card.setLastDigits(Short.parseShort(last4));        
        // hack
        card.setCardBrandId((short) 1);
        card.setCountryId((short) 224);        

        // see if the username already exists
        User userExists = userManager.getUserByUsername(user.getUsername());
        if (userExists != null) {
        	errors.reject("errors.username", "The username has already been taken. Please choose another.");
        	return showForm(request, response, errors);        	
        }
        
        try {
        	int userId = userManager.registerNewUser(user, card);
        	logger.info("Registered ok: user id " + userId);
        } catch (DataAccessException e) {
        	errors.reject("errors.database", "A database error occurred. Please try again later.");
        	logger.info("db error", e);
        	return showForm(request, response, errors);
        }

        // set the model object and return
        return new ModelAndView(getSuccessView(), "registration", reg);
    }

    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
    	Registration r = new Registration();
    	this.setSampleRegistration(r);        
        return r;
    }
    
    protected ModelAndView handleInvalidSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BindException errors = getErrorsForNewForm(request);
		errors.reject("duplicateFormSubmission", "Duplicate form submission");
		return showForm(request, response, errors);
	}
    
    protected void setSampleRegistration(Registration r) {
    	User user = new User();
    	user.setFirstName("Judd");
    	user.setLastName("Bourgeois");
    	user.setEmail("judd@searchsystems.net");    	
    	user.setUsername("jbtest001");
    	r.setUser(user);

    	// extra fields for user
    	r.setConfirmEmail(user.getEmail());    	
    	r.setPassword("Deagle50");
    	r.setConfirmPassword(r.getPassword());
    	r.setHintQuestion(4);
    	r.setHintAnswer("lausanne");
    	r.setPurchasePlan(1);
    	r.setReceiveNewsletter(false);
    	
    	CreditCard c = new CreditCard();
    	c.setName("Judd Bourgeois");    	
    	c.setExpMonth((short) 1);
    	c.setExpYear((short) 2012);
    	c.setAddress1("486 Siskin Pl");
    	c.setCity("Simi Valley");
    	c.setState("CA");
    	c.setPhone("8055206934");
    	r.setCreditCard(c);

    	// extra fields for cc
    	r.setCardNumber("371714428711003");
    	r.setCardCvv("9025");
    	r.setCardZipCode("93065");    	
    }

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}    
}