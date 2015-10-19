package springapp.web.funnel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import springapp.crypt.Crypt;
import springapp.domain.CreditCard;
import springapp.domain.Transaction;
import springapp.domain.User;
import springapp.service.NeonValidator;
import springapp.service.billing.BillingManager;
import springapp.service.billing.BillingManager.TxnType;
import springapp.service.user.CreditCardValidator;
import springapp.service.user.UserManager;
import springapp.service.user.UserValidator;
import springapp.service.user.UserManagerException;
import springapp.web.funnel.NewBJLSearchForm.BJLSearchFormCommand;
import springapp.web.funnel.NewCorpBusSearchForm.CorpBusSearchFormCommand;
import springapp.web.funnel.NewCorpIndSearchForm.CorpIndSearchFormCommand;
import springapp.web.funnel.NewEvictionBusinessSearchForm.EvictionBusinessSearchFormCommand;
import springapp.web.funnel.NewEvictionSearchForm.EvictionSearchFormCommand;
import springapp.web.funnel.NewNationSecurityForm.NationSearchFormCommand;
import springapp.web.funnel.NewSearchForm.SearchFormCommand;
//
//import springapp.repository.CreditCardDao;
//import springapp.repository.UserDao;

@Controller
public class RegisterForm extends AbstractFunnelController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserManager userManager;
	@Autowired
	private BillingManager billingManager;
	
	@RequestMapping(value="/funnel/register.do", method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="i18n",required=false) Boolean i18n			
			) {		
		
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		if (i18n == null) { i18n = false; }
		
		SearchFormCommand searchfc = (SearchFormCommand) session.getAttribute("searchFormCommand");
		BJLSearchFormCommand bjlsfc = (BJLSearchFormCommand) session.getAttribute("bjlSearchFormCommand");
		EvictionSearchFormCommand esfc= (EvictionSearchFormCommand) session.getAttribute("evictionSearchFormCommand");
		EvictionBusinessSearchFormCommand ebsfc= (EvictionBusinessSearchFormCommand) session.getAttribute("evictionBusinessSearchFormCommand");
		CorpIndSearchFormCommand cisfc = (CorpIndSearchFormCommand)session.getAttribute("corpIndSearchFormCommand");
		CorpBusSearchFormCommand cbsfc = (CorpBusSearchFormCommand)session.getAttribute("corpBusSearchFormCommand");
		NationSearchFormCommand nsfc= (NationSearchFormCommand) session.getAttribute("nationSearchFormCommand");
		
		// at least one must be set
		logger.info("searchfc:"+searchfc);
		//if ((searchfc == null) && (bjlsfc == null) && (esfc == null) && (ebsfc == null) && (cisfc == null) && (cbsfc == null)) {
			//session.invalidate();
			//return landingHome;
		//}
		RegisterFormCommand cmd = (RegisterFormCommand) session.getAttribute("registerFormCommand");
		
		if (cmd == null) {
			cmd = new RegisterFormCommand();
		} else {
			// clear CC info
			cmd.setCcNumber("");
			cmd.setCcAuthCode("");
			
		}
		// update these flags in case the pfc is reused		
		cmd.setSignup(false);	
		cmd.setSearch(true);
		
		cmd.setI18n(i18n);
		cmd.setTest(test);
		setDefaults(cmd);
		if (test || sample) {
			setSample(cmd, test);
		}
		
		map.addAttribute("command", cmd);
		
		return showRegister;
	}
	private void setDefaults(RegisterFormCommand cmd) {		
		if (!cmd.getI18n()) {
			cmd.setCountryCode("US");
		}
		cmd.setRecurringBilling(false);
		cmd.setReceiveNewsletter(true);
		cmd.setAcceptAgreement(false);
		cmd.setCCAuthErr("dynamic txt");

	}
	protected void setSample(RegisterFormCommand cmd, Boolean test) {
		cmd.setFirstName("Vivek");
		cmd.setLastName("Kaliyaperumal");
		cmd.setUsername("VivekTesting");
		cmd.setEmail("vivek@adhithiyasystems.net");
		cmd.setConfirmEmail(cmd.getEmail());
		cmd.setPassword("ucando");
		cmd.setConfirmPassword(cmd.getPassword());
		cmd.setSecretQuestionId(4);
		cmd.setSecretQuestionAnswer("cuddalore");
		//Billing Info combined with register
		cmd.setName("Ramanathan Kumarappan");
		cmd.setCcNumber("4111111111111111");
		cmd.setCcAuthCode("123");
		cmd.setCcExpMonth(5);
		cmd.setCcExpYear(2018);
		
		cmd.setAddress("8142 Making Memories Pl");
		cmd.setCity("Las Vegas");
		cmd.setState("NV");
		cmd.setPostalCode("89131");
		cmd.setPhone("7025450309");
		cmd.setCountryCode("US");
		cmd.setReceiveNewsletter(false);
		cmd.setAcceptAgreement(true);
		if (test) {
			cmd.setTest(true);
		}
	}	
	
	@RequestMapping(value="/funnel/register.do", method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") RegisterFormCommand command,
			Errors errors,
			SessionStatus status ) 
	{
		SearchFormCommand searchfc = (SearchFormCommand) session.getAttribute("searchFormCommand");
		BJLSearchFormCommand bjlsfc = (BJLSearchFormCommand) session.getAttribute("bjlSearchFormCommand");
		EvictionSearchFormCommand esfc= (EvictionSearchFormCommand) session.getAttribute("evictionSearchFormCommand");
		EvictionBusinessSearchFormCommand ebsfc= (EvictionBusinessSearchFormCommand) session.getAttribute("evictionBusinessSearchFormCommand");
		CorpIndSearchFormCommand cisfc = (CorpIndSearchFormCommand)session.getAttribute("corpIndSearchFormCommand");
		CorpBusSearchFormCommand cbsfc = (CorpBusSearchFormCommand)session.getAttribute("corpBusSearchFormCommand");
		NationSearchFormCommand nsfc= (NationSearchFormCommand) session.getAttribute("nationSearchFormCommand");
		RegisterFormValidator v = new RegisterFormValidator();
		//logger.info("B4_CCVal_start: On POST");
		v.validate(command, errors);
		
		
			
		if (errors.hasErrors()) {
			logger.info("paswrd:"+ command.getPassword());
			command.setPassword(command.getPassword());

			return showRegister;
		}
		else if ((searchfc == null) && (bjlsfc == null) && (esfc == null) && (ebsfc == null) && (cisfc == null) && (cbsfc == null) && (nsfc == null)) {
			//session.invalidate();
			session.setAttribute("registerFormCommand", command);
			status.setComplete();
			return newconfirmAccRedir;
		}
		else {
			logger.info("setting Resiger Obj & Billing Obj continuing");
			session.setAttribute("registerFormCommand", command);
			status.setComplete();
			return newconfirmSearchRedir;			
		}		
	}
	
	
	// Register Form by Vivek on 09-10-2012
	public static class RegisterFormCommand {
		private String username;
		private String password;
		private String confirmPassword;		
		private String firstName;
		private String lastName;
		private String email;
		private String confirmEmail;		
		private Integer secretQuestionId;
		private String secretQuestionAnswer;
		private Boolean test = false;
		
		// Billing Info by Vivek
		private String name;
		private String company;
		private String address;
		private String city;
		private String state;
		private String postalCode;
		private String countryCode;
		private String phone;
				
		private String ccNumber;
		private String ccAuthCode;
		private Integer ccExpYear;
		private Integer ccExpMonth;
		
		private Boolean recurringBilling = false;
		private Boolean receiveNewsletter = true;
		private Boolean acceptAgreement = false;
		private Boolean i18n = false;
		private Boolean search = false;
		private Boolean signup = false;

		private String ccAuthErr;
		

		public String getCCAuthErr() {
			return ccAuthErr;
		}
		public void setCCAuthErr(String ccAuthErr) {
			this.ccAuthErr = ccAuthErr;
		}
		
		public Boolean getTest() {
			return test;
		}
		public void setTest(Boolean test) {
			this.test = test;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
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
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getConfirmEmail() {
			return confirmEmail;
		}
		public void setConfirmEmail(String confirmEmail) {
			this.confirmEmail = confirmEmail;
		}
		public Integer getSecretQuestionId() {
			return secretQuestionId;
		}
		public void setSecretQuestionId(Integer secretQuestionId) {
			this.secretQuestionId = secretQuestionId;
		}
		public String getSecretQuestionAnswer() {
			return secretQuestionAnswer;
		}
		public void setSecretQuestionAnswer(String secretQuestionAnswer) {
			this.secretQuestionAnswer = secretQuestionAnswer;
		}	
		// Billing info 
		
		public Boolean getI18n() {
			return i18n;
		}
		public void setI18n(Boolean i18n) {
			this.i18n = i18n;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getPostalCode() {
			return postalCode;
		}
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getCcNumber() {
			return ccNumber;
		}
		public void setCcNumber(String ccNumber) {
			this.ccNumber = ccNumber;
		}
		public Integer getCcExpYear() {
			return ccExpYear;
		}
		public void setCcExpYear(Integer ccExpYear) {
			this.ccExpYear = ccExpYear;
		}
		public Integer getCcExpMonth() {
			return ccExpMonth;
		}
		public void setCcExpMonth(Integer ccExpMonth) {
			this.ccExpMonth = ccExpMonth;
		}
		public String getCcAuthCode() {
			return ccAuthCode;
		}
		public void setCcAuthCode(String ccAuthCode) {
			this.ccAuthCode = ccAuthCode;
		}
		public Boolean getReceiveNewsletter() {
			return receiveNewsletter;
		}
		public void setReceiveNewsletter(Boolean receiveNewsletter) {
			this.receiveNewsletter = receiveNewsletter;
		}
		public Boolean getAcceptAgreement() {
			return acceptAgreement;
		}
		public void setAcceptAgreement(Boolean acceptAgreement) {
			this.acceptAgreement = acceptAgreement;
		}
		public Boolean getRecurringBilling() {
			return recurringBilling;
		}
		public void setRecurringBilling(Boolean recurringBilling) {
			this.recurringBilling = recurringBilling;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public Boolean getSearch() {
			return search;
		}
		public void setSearch(Boolean search) {
			this.search = search;
		}
		public Boolean getSignup() {
			return signup;
		}
		public void setSignup(Boolean signup) {
			this.signup = signup;
		}
		
	}
	
	
	public class RegisterFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(RegisterFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			RegisterFormCommand cmd = (RegisterFormCommand) target;
			
			validateString("username", cmd.getUsername(),   errors,  5, 25, usernameRegex, "Username");
			validateString("name",     cmd.getName(),       errors,  5, 50, regexBasicExtended, "Card Holder Name");
			validateString("address",  cmd.getAddress(),    errors,  5, 50, regexBasicExtended, "Billing Address");
			validateString("city",     cmd.getCity(),       errors,  3, 50, regexBasicExtended, "Billing City");
			validateString("phone",    cmd.getPhone(),      errors, 10, 20, regexNumericDashSpace, "Phone");
			
			
			// because this is a hash map and not a db ILIKE, we need to worry about case			
			cmd.setCountryCode(cmd.getCountryCode().toUpperCase());

//			User u = userManager.getUserByUsername(cmd.getUsername());
//			if (u != null) {
//				errors.rejectValue("username", "username-in-use", "The username is already in use. Please choose another.");
//			}
			List<User> users = userManager.getUsersByUsernameCaseInsensitive(cmd.getUsername());
			if (users.size() > 0) {
				errors.rejectValue("username", "username-in-use", "The username is already in use. Please choose another.");
			}

			if (cmd.getTest() && cmd.getPassword().equals("")) {
				cmd.setPassword("Deagle50");
				cmd.setConfirmPassword("Deagle50");
			}
			
			if (!cmd.getConfirmPassword().equals(cmd.getPassword())) {
				errors.rejectValue("confirmPassword", "password-mismatch", "The passwords do not match.");
			} else {
				UserValidator uv = new UserValidator();
				uv.validatePassword(cmd.getPassword(), errors);				
			}			
			
			
			
			if (!cmd.getConfirmEmail().equals(cmd.getEmail())) {
				errors.rejectValue("confirmEmail", "email-mismatch", "The email addresses do not match.");
			} 
			else 
			{				
				validateString("email",     cmd.getEmail(),     errors,  5, 50, emailRegex, "Email");
			}
			
			// check for duplicate email
			if (!errors.hasFieldErrors("email")) {
				if (!cmd.getEmail().endsWith("@searchsystems.net")) {
					List<User> usersByEmail = userManager.getUsersByEmail(cmd.getEmail());
					if (usersByEmail.size() > 0) {
						errors.rejectValue("email", "email-in-use", "The email address is in use. Do you already have an account with us?");
					}
				}
			}
				
			
			
			
			validateString("firstName", cmd.getFirstName(), errors,  2, 50, regexBasicExtended, "First Name");
			validateString("lastName",  cmd.getLastName(),  errors,  2, 50, regexBasicExtended, "Last Name");
			validateString("secretQuestionAnswer", cmd.getSecretQuestionAnswer(), errors, 3, 50, regexBasicExtended, "Secret Question Answer");
			
			// us/intl
			if (cmd.getI18n()) {
				// intl
				validateString("countryCode",  cmd.getCountryCode(), errors,  2, 2, regexBasicNoSpace, "Country Code");
				// make sure the country code exists
				Integer cid = userManager.getCountryIdByCode(cmd.getCountryCode());
				if (cid == null) {
					errors.rejectValue("countryCode", "invalid-country-code", "Invalid country code.");
				}
			} else {
				// US
				validateString("state",    cmd.getState(),      errors,  2, 2, regexBasicNoSpace, "State");
				validateString("postalCode",  cmd.getPostalCode(), errors,  5, 10, regexNumeric, "ZIP Code");
			}

			// validate card data
			String ccNum = cmd.getCcNumber();
			ccNum = ccNum.replace("-", "");
			ccNum = ccNum.replace(" ", "");
			cmd.setCcNumber(ccNum);
			//logger.info("ccNum fixed: " + ccNum);
			validateString("ccNumber", cmd.getCcNumber(), errors,  13,  16, regexNumericDashSpace, "Credit Card Number");

			
			
			// we don't accept discover
			if (! (ccNum.startsWith("3") || ccNum.startsWith("4") || ccNum.startsWith("5")) ) {
				errors.rejectValue("ccNumber", "cc-invalid", "Credit card must be Visa, MasterCard or American Express.");				
			}
			
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
	

	private CreditCard generateCC(RegisterFormCommand signupfc) {
		CreditCard cc = new CreditCard();
		
		cc.setName(signupfc.getName());
		cc.setCompany(signupfc.getCompany());
		cc.setAddress1(signupfc.getAddress());
		cc.setPhone(signupfc.getPhone());
		cc.setCity(signupfc.getCity());
		cc.setState(signupfc.getState());
		cc.setPostalCode(signupfc.getPostalCode());
		
		//logger.info("countryCode: " + signupfc.getCountryCode());
		Integer cid = userManager.getCountryIdByCode(signupfc.getCountryCode());
		//logger.info("countryId: " + cid);
		cc.setCountryId(cid.shortValue());
		//Short ccid=224;
		//cc.setCountryId(ccid);
		
		String ccNumber = signupfc.getCcNumber();
		String last4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
		cc.setNumber(Crypt.encrypt(ccNumber));
		cc.setLastDigits(Short.parseShort(last4));
		
		cc.setExpMonth(signupfc.getCcExpMonth().shortValue());
		cc.setExpYear(signupfc.getCcExpYear().shortValue());
		cc.setVerified(true);
		
		return cc;
	}	
	
	
	// db lookups
	@ModelAttribute("secretQuestions")
	public HashMap<Integer, String> populateSecretQuestions() {
		return userManager.getPassQuestions();
	}	
}