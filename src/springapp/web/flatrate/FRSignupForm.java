package springapp.web.flatrate;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

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

import springapp.domain.CreditCard;
import springapp.domain.Rate;
import springapp.domain.User;
import springapp.service.NeonPasswordMD5;
import springapp.service.NeonValidator;
import springapp.service.billing.BillingManager;
import springapp.service.billing.RateManager;
import springapp.service.user.UserLogin;
import springapp.service.user.UserManager;
import springapp.service.user.UserValidator;
import springapp.web.funnel.AbstractFunnelController;

@Controller
public class FRSignupForm extends AbstractFunnelController {
	
	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	protected RateManager rateManager;
	
	
	protected static final Integer[] FRFPrates = { 50, 51, 52, 53 };
	protected static final Integer[] FRBGCrates = { 54, 55, 56, 57 };
	protected static final Integer[] FRComrates = { 58, 59, 60, 61 };
	
	protected static final TreeMap<Integer,String> FRFPpaymentPlans = new TreeMap<Integer,String>();
	protected static final TreeMap<Integer,String> FRBGCpaymentPlans = new TreeMap<Integer,String>();
	protected static final TreeMap<Integer,String> FRCompaymentPlans = new TreeMap<Integer,String>();
	
	public void init() {
		super.init();
		for (int i : FRFPrates) {
			Rate rate = rateManager.getRate(i);
			if (rate == null) {
				logger.error("Startup: Could not find rate " + i);
			} else {
				FRFPpaymentPlans.put(rate.getRateId(), rate.getRateInternalDescription());
			}
		}
		for (int i : FRBGCrates) {
			Rate rate = rateManager.getRate(i);
			if (rate == null) {
				logger.error("Startup: Could not find rate " + i);
			} else {
				FRBGCpaymentPlans.put(rate.getRateId(), rate.getRateInternalDescription());
			}
		}
		for (int i : FRComrates) {
			Rate rate = rateManager.getRate(i);
			if (rate == null) {
				logger.error("Startup: Could not find rate " + i);
			} else {
				FRCompaymentPlans.put(rate.getRateId(), rate.getRateInternalDescription());
			}
		}
	}	
	
	@ModelAttribute("FRFPpaymentPlans")
	public static TreeMap<Integer, String> getFRFPpaymentPlans() {
		return FRFPpaymentPlans;
	}
	@ModelAttribute("FRBGCpaymentPlans")
	public static TreeMap<Integer, String> getFRBGCpaymentPlans() {
		return FRBGCpaymentPlans;
	}
	@ModelAttribute("FRCompaymentPlans")
	public static TreeMap<Integer, String> getFRCompaymentPlans() {
		return FRCompaymentPlans;
	}

	//static lookup
	@RequestMapping(value = "/flatrate/FRLanding.do", method = RequestMethod.GET)
	public String ShowFRLanding(ModelMap map, HttpSession session){
		return FRLandingURL;
	}
	
	@RequestMapping(value = "/flatrate/FRSignup.do", method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="planType",required=false) String planType
			) {		
		
		//removing all session related to FR
		session.removeAttribute("SSusername");
		session.removeAttribute("FRusername");
		session.removeAttribute("FRuserId");
		
		session.removeAttribute("tempFRusername");
		session.removeAttribute("tempFRuserId");
		session.removeAttribute("tempFRnoCreditCard");
		session.removeAttribute("tempFRcreditCardObj");
				
		FRLoginForm.removeFRSession(session);
		
		FRSignupFormCommand cmd = (FRSignupFormCommand) session.getAttribute("FRSignupFormCommand");
		if(cmd == null){
			cmd = new FRSignupFormCommand();
		}
		if(planType == null){ planType = "all"; }
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }		
		if (test || sample) {
			setSample(cmd, test);
		}
		cmd.setPlanType(planType);		
		map.addAttribute("command", cmd);
		return FRSignup;
	}
	
	protected void setSample(FRSignupFormCommand cmd, Boolean test) {
		cmd.setFirstName("Shahul");
		cmd.setLastName("Dhasthagir");
		cmd.setUsername("ShahulTesting");
		cmd.setEmail("shahul@adhithiyasystems.net");
		cmd.setConfirmEmail(cmd.getEmail());
		cmd.setPassword("test");
		cmd.setConfirmPassword(cmd.getPassword());
		cmd.setSecretQuestionId(4);
		cmd.setSecretQuestionAnswer("shah");
		if (test) {
			cmd.setTest(true);
		}
	}	
	
	@RequestMapping(value = "/flatrate/FRSignup.do", method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") FRSignupFormCommand command,
			Errors errors, SessionStatus status,
			@RequestParam(value = "memberLogin", required = false) String memberLogin) {
		
		if(session.getAttribute("FRSignupFormCommand") != null){
			session.removeAttribute("FRSignupFormCommand");
		}
		
		session.setAttribute("FRSignupFormCommand", command);
			
		//signup for SS members
		if(memberLogin != null){
			if(session.getAttribute("FRPurchaseFormCommand") != null){ session.removeAttribute("FRPurchaseFormCommand"); }
			if(command.getRateId() == null){
				errors.rejectValue("rateId", "plan-not-seleted", "Please Choose Your Plan  to continue");
			}
			if(command.getSsUsername().isEmpty() || command.getSsPassword().isEmpty()){
				errors.rejectValue("ssUsername", "Username-empty", "Username or Password can not be empty");
			}
			if (errors.hasErrors()) {
				map.addAttribute("command", command);
				return FRSignup;
			}else{
				User user = userManager.getUserByUsername(command.getSsUsername(), false);
				UserLogin.authenticateSsMember(user, errors, command.getSsPassword(), session);
				if(errors.hasErrors()){
					map.addAttribute("command", command);
					return FRSignup;
				}else{
					// Reason for creating tempFR sessions is, even inactive users can access this signup.
					// So if we keep the session as 'username' he can access other services as a member if he click
					// this form and go away
					
					session.setAttribute("tempFRusername", user.getUsername());
					session.setAttribute("tempFRuserId", user.getUserId());
					if(user.getNoCreditCard() == true){
						session.setAttribute("tempFRnoCreditCard", true);
					}else{
						CreditCard cc = userManager.getCreditCard(user.getUserId());
						session.setAttribute("tempFRcreditCardObj", cc);
					}
					return FRConfirmRedir;
				}
			}
		  }	
		
		
		
		//temp members
		FRSignupFormValidator v = new FRSignupFormValidator();
		v.validate(command, errors);
		if (errors.hasErrors()) {
			map.addAttribute("command", command);
			return FRSignup;
		} else {
			logger.info("setting FR signupfc & continuing");
			status.setComplete();
			return FRPurchaseRedir;			
		}		
	}
	
	public static class FRSignupFormCommand {
		private Integer rateId;
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
		private String planType;
				
		//only for SS members
		private String ssUsername;
		private String ssPassword;
		
		
		
		public String getPlanType() {
			return planType;
		}
		public void setPlanType(String planType) {
			this.planType = planType;
		}
		public String getSsUsername() {
			return ssUsername;
		}
		public void setSsUsername(String ssUsername) {
			this.ssUsername = ssUsername;
		}
		public String getSsPassword() {
			return ssPassword;
		}
		public void setSsPassword(String ssPassword) {
			this.ssPassword = ssPassword;
		}
		public Integer getRateId() {
			return rateId;
		}
		public void setRateId(Integer rateId) {
			this.rateId = rateId;
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
	}
	
	public class FRSignupFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(FRSignupFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			FRSignupFormCommand cmd = (FRSignupFormCommand) target;
			if(cmd.getRateId() == null){
				errors.rejectValue("rateId", "plan-not-seleted", "Please Choose Your Plan  to continue");
			}
			validateString("username", cmd.getUsername(),   errors,  5, 25, usernameRegex, "Username");
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
			} else {
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
		}		
	}
	
	// db lookups
	@ModelAttribute("secretQuestions")
	public HashMap<Integer, String> populateSecretQuestions() {
		return userManager.getPassQuestions();
	}	
	
}
