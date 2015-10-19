package springapp.web.user;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.User;
import springapp.service.user.UserValidator;

@Controller
@RequestMapping("/user/updatePersonalInformation.do")

public class UpdatePersonalInformation extends AbstractUserController {
	protected void setValues(User u, UpdatePersonalInformationCommand command) {
		u.setFirstName(command.getFirstName());
		u.setLastName(command.getLastName());
		u.setEmail(command.getEmail());
		u.setPassQuestionId(command.getPassQuestionId().shortValue());
		u.setPassAnswer(command.getPassAnswer());
	}
	protected void setValues2(User userNew, User userOrig) {
		userOrig.setFirstName(userNew.getFirstName());
		userOrig.setLastName(userNew.getLastName());
		userOrig.setEmail(userNew.getEmail());
		userOrig.setPassQuestionId(userNew.getPassQuestionId());
		userOrig.setPassAnswer(userNew.getPassAnswer());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm(HttpSession session) {		
		//return new ModelAndView(updatePersonalInfoView, "command", new UpdatePersonalInformationCommand());
		User u = userManager.getUserByUsername(getUsernameFromSession(session));
		return new ModelAndView(updatePersonalInfoView, "user", u);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			//@ModelAttribute("command") UpdatePersonalInformationCommand command,
			@ModelAttribute("user") User userNew,
			Errors errors,
			SessionStatus status) {
		User userOrig = userManager.getUserByUsername(getUsernameFromSession(session));
		setValues2(userNew, userOrig);
		logger.info("userOrig: " + userOrig.getUsername());
		
		// regular validate() will not work since username is not in the form
		new UserValidator().validateQuick(userOrig, errors);
		
		if (errors.hasErrors()) {
			logger.info("errors: " + errors.getAllErrors());
			return updatePersonalInfoView;
		} else {
			userManager.saveUser(userOrig);
			status.setComplete();
			return "redirect:" + indexUrl;
		}	
	}
	
	public static class UpdatePersonalInformationCommand {
		protected String firstName;
		protected String lastName;
		protected String email;
		protected Integer passQuestionId;
		protected String passAnswer;
		
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
		public Integer getPassQuestionId() {
			return passQuestionId;
		}
		public void setPassQuestionId(Integer secretQuestionId) {
			this.passQuestionId = secretQuestionId;
		}
		public String getPassAnswer() {
			return passAnswer;
		}
		public void setPassAnswer(String secretQuestionAnswer) {
			this.passAnswer = secretQuestionAnswer;
		}
	}
	
	@ModelAttribute("secretQuestions")
	public HashMap<Integer, String> populateSecretQuestions() {
		return userManager.getPassQuestions();
	}
}