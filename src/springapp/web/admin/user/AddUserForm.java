package springapp.web.admin.user;

import java.util.HashMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.crypt.Crypt;
import springapp.domain.CreditCard;
import springapp.domain.Rate;
import springapp.domain.Transaction;
import springapp.domain.User;
import springapp.service.NeonPasswordMD5;
import springapp.service.NeonValidator;
import springapp.service.billing.BillingManager;
import springapp.service.billing.RateManager;
import springapp.service.user.CreditCardValidator;
import springapp.service.user.UserManager;
import springapp.service.user.UserValidator;
import springapp.web.admin.AbstractAdminController;

@Controller
@RequestMapping("/admin/addUser.do")

public class AddUserForm extends AbstractAdminController {
	protected final Logger logger = Logger.getLogger(getClass()); 
	
	@Autowired
	private UserManager userManager;	
	@Autowired
	private RateManager rateManager;
	
	protected static final TreeMap<Integer,String> paymentPlans = new TreeMap<Integer,String>();
	protected static final Integer[] rates = { 84, 85, 86 };
	
	public void init() {
		for (int i : rates) {
			Rate rate = rateManager.getRate(i);
			if (rate == null) {
				logger.error("Startup: Could not find rate " + i);
			} else {
				paymentPlans.put(rate.getRateId(), rate.getRateName());
			}
		}
	}
	
	// db lookups
	@ModelAttribute("passQuestions")
	public HashMap<Integer, String> populatePassQuestions() {
		return this.userManager.getPassQuestions();
	}	
	@ModelAttribute("usStates")
	public HashMap<String, String> populateUSStates() {
		return this.userManager.getUSStates();
	}	
	@ModelAttribute("paymentPlans")
	public TreeMap<Integer,String> populatePaymentPlans() {
		return paymentPlans;
	}	
    
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm(
			@RequestParam(value="userOutsideUS",required=false) Boolean userOutsideUS,
			@RequestParam(value="test",required=false) Boolean test,
			ModelMap model) {
		if (userOutsideUS == null) { userOutsideUS = false; }
		if (test == null) { test = false; }
		ModelAndView mav = new ModelAndView("admin/AddUser");		
		AddUserFormCommand cmd = new AddUserFormCommand();
		// remove this when live
		if (test) {
			setSampleAddUser(cmd);
		}
		// set up mav
		mav.addObject("command", cmd);
		cmd.setUserOutsideUS(userOutsideUS);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			ModelMap map,
			@ModelAttribute("command") AddUserFormCommand cmd,
			Errors errors,
			SessionStatus status) {
		new AddUserFormValidator().validate(cmd, errors);
				
		User userExists = userManager.getUserByUsername(cmd.getUser().getUsername());
        if (userExists != null) {
        	errors.rejectValue("user.username", "username-already-taken", "The username has already been taken. Please choose another.");        	
        }
        
        // bailout here if necessary
		if (errors.hasErrors()) {
			return "admin/AddUser";	
		} 
		
			
		// add the user
		User user = cmd.getUser();			
		user.setPassword(NeonPasswordMD5.encryptPassword(cmd.getPassword()));
		user.setNoPremium(false);
        user.setNoExport(true);
        user.setInactive(true);
        user.setConfirmed(true);
    	user.setSubDirectoryUser(true); 
    	if(user.getCustomAccount() || !cmd.getValidateCC()){
			user.setNoCreditCard(true);
		}
    	
    	map.addAttribute("username", user.getUsername());		
		
    	if (cmd.getValidateCC()) { 
    		// add the credit card
			// user manager will handle this
			//cc.setUserId(u.getUserId());
			CreditCard cc = cmd.getCreditCard();
			String ccNumber = cmd.getCcNumber();
			String last4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
			cc.setNumber(Crypt.encrypt(ccNumber));
			cc.setLastDigits(Short.parseShort(last4));
			cc.setVerified(true);
			
			String signupFor = "Adding User";
			// register the user (uses db txn)
			// charge here then set active if ok
			Transaction t = userManager.registerNewUserSub(user, cc, cmd.getCcCvv(), cmd.getRateId(), cmd.getRebill(),signupFor);
			logger.info("registered new user: " + t.getUserId());
						
			// runTxn returns void, how do we know it worked?
			if (t.getTransactionStatusId() == BillingManager.txnApproved) {
				user.setInactive(false);
				userManager.saveUser(user);
				map.addAttribute("ccResult", "The credit card was approved. The user has been activated.");
			} else {
				map.addAttribute("ccResult", "The credit card could not be authorized. The user has been added but not activated.");
			}
			status.setComplete();
			return "admin/AddUserComplete";
		
    	} else {
    		// add without cc
    		user.setInactive(false);
    		userManager.registerNewUser(user);    		
    		userManager.addSubscription(user.getUserId(), cmd.getRateId(), cmd.getRebill());
    		
			map.addAttribute("ccResult", "Credit card validation was disabled. The user has been activated.");
			status.setComplete();
			return "admin/AddUserComplete";
    	}
	}
	
	public static class AddUserFormCommand {
		private User user;
		private CreditCard creditCard;
		
		// needs to be separate, pass is encrypted for user object
		private String password;
		private String confirmPassword;
		
		// cc fields
		private String ccNumber;
		private String ccCvv;
		
		// billing fields
		private Boolean userOutsideUS;
		
		private Integer rateId;
		private Boolean rebill;
		private Boolean validateCC = true;

		public AddUserFormCommand() {
	    	user = new User();
	    	creditCard = new CreditCard();
	    }
		
	    public Integer getRateId() {
			return rateId;
		}
		public void setRateId(Integer rateId) {
			this.rateId = rateId;
		}
		public Boolean getRebill() {
			return rebill;
		}
		public void setRebill(Boolean rebill) {
			this.rebill = rebill;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public CreditCard getCreditCard() {
			return creditCard;
		}
		public void setCreditCard(CreditCard cc) {
			this.creditCard = cc;
		}
		// other fields: pass/confirm pass, rate plan (object?)
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getConfirmPassword() {
			return confirmPassword;
		}
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
		public String getCcNumber() {
			return ccNumber;
		}
		public void setCcNumber(String ccNumber) {
			this.ccNumber = ccNumber;
		}
		public String getCcCvv() {
			return ccCvv;
		}
		public void setCcCvv(String ccCvv) {
			this.ccCvv = ccCvv;
		}
		public Boolean getUserOutsideUS() {
			return userOutsideUS;
		}
		public void setUserOutsideUS(Boolean userOutsideUS) {
			this.userOutsideUS = userOutsideUS;
		}
		public Boolean getValidateCC() {
			return validateCC;
		}
		public void setValidateCC(Boolean validateCC) {
			this.validateCC = validateCC;
		}	
	}
	
	protected static class AddUserFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return AddUserFormCommand.class.equals(clazz);
		}

		public void validate(Object target, Errors errors) {
			AddUserFormCommand cmd = (AddUserFormCommand) target;

			// validate other fields
			if (!cmd.getPassword().equals(cmd.getConfirmPassword())) {
				errors.rejectValue("password", "password-mismatch", "The password values do not match.");
				errors.rejectValue("confirmPassword", "password-mismatch", "The password values do not match.");
			}
			
			// validate user
			errors.pushNestedPath("user");
			UserValidator v = new UserValidator();
			v.validate(cmd.getUser(), errors);
			// this doesn't work right, password on the user object is null at this point
			// we need a separate password strength checker (not validator?)
			// v.validatePassword(cmd.getPassword(), errors);			
			errors.popNestedPath();
			
			// validate cc
			if (cmd.getValidateCC()) {
				
				errors.pushNestedPath("creditCard");
				new CreditCardValidator().validate(cmd.getCreditCard(), errors);
				errors.popNestedPath();

				// validate ccNumber, ccCvv separately
				this.validateString("ccNumber", cmd.getCcNumber(), errors, 13, 16, regexNumeric);
				this.validateString("ccCvv",    cmd.getCcCvv(),    errors,  3,  4, regexNumeric);
			}			
			
			// XXX need to validate the lookup fields
			// probably sufficient to make sure they aren't "-" or "Please select"
			// instead of some full validation routine. 
			// msg "Invalid data" is returned when default choice is used.
		}		
	}
	
	protected void setSampleAddUser(AddUserFormCommand cmd) {
    	User user = new User();
    	user.setFirstName("Ramanathan");
    	user.setLastName("Kumarappan");
    	user.setEmail("ram@searchsystems.net");    	
    	user.setUsername("ramtest001");
    	cmd.setUser(user);

    	// extra fields for user
    	String password = "ram2go"; 
    	cmd.setPassword(password);
    	cmd.setConfirmPassword(password);
    	
    	user.setPassQuestionId((short) 4);
    	user.setPassAnswer("valliammai");
    	
    	CreditCard c = new CreditCard();
    	c.setName("Ramanathan Kumarappan");    	
    	c.setAddress1("8142 Making Memories Pl");
    	c.setCity("Las Vegas");
    	c.setState("NV");
    	c.setPostalCode("89131");
    	c.setPhone("7025450309");
    	c.setExpMonth((short) 1);
    	c.setExpYear((short) 2012);
    	cmd.setCreditCard(c);

    	// extra fields for cc
    	cmd.setCcNumber("4111111111111111");
    	cmd.setCcCvv("123");
    	cmd.setRateId(84);
    }
}