package springapp.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.service.user.UserValidator;

@Controller
@RequestMapping("/user/changePassword.do")

public class ChangePassword extends AbstractUserController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm() {
		return new ModelAndView(changePasswordView, "command",  new ChangePasswordCommand());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			@ModelAttribute("command") ChangePasswordCommand command,
			Errors errors,
			SessionStatus status) {
		String password = command.getPassword();
		String confirmPassword = command.getConfirmPassword();
		if (!password.equals(confirmPassword)) {
			errors.reject("password-mismatch", "Passwords do not match.");
		} else {
			new UserValidator().validatePassword(password, errors);
		}
		
		if (errors.hasErrors()) {
			return changePasswordView;
		} else {
			UserSession us = (UserSession) session.getAttribute(UserLoginController.SESSION_ATTRIBUTE_NAME);
			String username = us.getUsername();
			userManager.changeUserPassword(username, password);
			status.setComplete();
			return "redirect:" + indexUrl;
		}	
	}
	
	public static class ChangePasswordCommand {
		private String password;
		private String confirmPassword;
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
	}
}