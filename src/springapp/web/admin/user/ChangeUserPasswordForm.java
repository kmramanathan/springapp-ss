package springapp.web.admin.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.service.user.UserManager;
import springapp.service.user.UserValidator;

@Controller
@RequestMapping("/admin/changeUserPassword.do")

public class ChangeUserPasswordForm {
	protected final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm(@RequestParam("username") String username) {
		ChangeUserPassword c = new ChangeUserPassword();
		c.setUsername(username);
		return new ModelAndView("admin/ChangeUserPassword", "ChangeUserPassword", c);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			//ModelMap map,
			@ModelAttribute("ChangeUserPassword") ChangeUserPassword c,
			Errors errors,
			SessionStatus status) {		
		if (!c.getPassword().equals(c.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "password-mismatch", "Passwords do not match.");
		} else {
			new UserValidator().validatePassword(c.getPassword(), errors);
		}
		
		if (errors.hasErrors()) {
			//map.addAttribute(errors);
			// clear passwords on error
			//c.setPassword("");
			//c.setConfirmPassword("");
			//map.addAttribute("ChangeUserPassword", c);
			return "admin/ChangeUserPassword";
		} else {
			userManager.changeUserPassword(c.getUsername(), c.getPassword());
			status.setComplete();
			return "redirect:editUser.do?username=" + c.getUsername();
		}
	}
	
	public static class ChangeUserPassword {
		private String username;
		private String password;
		private String confirmPassword;	

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
	}
}