package springapp.web.funnel;

import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import springapp.domain.User;
import springapp.service.NeonValidator;
import springapp.service.user.UserManager;
import springapp.service.user.UserValidator;

@Controller
@RequestMapping("/funnel/signup.do")
@SessionAttributes({"command"})

public class SignupForm extends AbstractFunnelController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserManager userManager;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="previousPage",required=false) String previousPage,
			@RequestParam(value="flag",required=false) Boolean flag,
			@RequestParam(value="signupFor",required=false) String signupFor
			) {		
		SignupFormCommand cmd = new SignupFormCommand();
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }		
		if (test || sample) {
			setSample(cmd, test);
		}
		
		//getting called page
		if(session.getAttribute("previousPage")!=null){ session.removeAttribute("previousPage"); }
		if(previousPage!=null){
			session.setAttribute("previousPage", previousPage);
			logger.info("The previews Page is"+previousPage+"::"+(String)session.getAttribute("previousPage"));
		}
		
		//tracking the signup for
		if(session.getAttribute("signupFor")!=null){ session.removeAttribute("signupFor"); }
		if(signupFor !=null ){
			session.setAttribute("signupFor", signupFor);
			logger.info("The signup is for : "+(String)session.getAttribute("signupFor"));
		}
		
		
		map.addAttribute("command", cmd);
		return vwSignup;
	}
	
	protected void setSample(SignupFormCommand cmd, Boolean test) {
		cmd.setFirstName("Judd");
		cmd.setLastName("Bourgeois");
		cmd.setUsername("jbtest101");
		cmd.setEmail("judd@searchsystems.net");
		cmd.setConfirmEmail(cmd.getEmail());
		cmd.setPassword("Deagle50");
		cmd.setConfirmPassword(cmd.getPassword());
		cmd.setSecretQuestionId(4);
		cmd.setSecretQuestionAnswer("lausanne");
		if (test) {
			cmd.setTest(true);
		}
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") SignupFormCommand command,
			Errors errors,
			SessionStatus status) {
		SignupFormValidator v = new SignupFormValidator();
		v.validate(command, errors);
		if (errors.hasErrors()) {
			return vwSignup;
		} else {
			logger.info("setting signupfc & continuing");
			session.setAttribute("signupFormCommand", command);
			session.removeAttribute("searchFormCommand");
			status.setComplete();
			return purchaseSignupRedir;			
		}		
	}
	
	public static class SignupFormCommand {
		private String username;
		private String password;
		private String confirmPassword;		
		private String firstName;
		private String lastName;
		private String email;
		private String confirmEmail;		
		private Integer secretQuestionId;
		private String secretQuestionAnswer;
		private String bonusItem;
		private Boolean test = false;
		
		
		public String getBonusItem() {
			return bonusItem;
		}
		public void setBonusItem(String bonusItem) {
			this.bonusItem = bonusItem;
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
	
	public class SignupFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(SignupFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			SignupFormCommand cmd = (SignupFormCommand) target;
			
			validateString("username", cmd.getUsername(),   errors,  5, 25, usernameRegex, "Username");
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