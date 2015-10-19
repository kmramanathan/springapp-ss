package springapp.web.admin.user;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.User;
import springapp.service.NeonValidator;
import springapp.service.user.UserManager;

@Controller
@RequestMapping("/admin/disableUser.do")

public class DisableUserForm {
	protected final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm() {
		DisableUserFormCommand c = new DisableUserFormCommand();
		return new ModelAndView("admin/DisableUser", "DisableUser", c);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("DisableUser") DisableUserFormCommand c,
			Errors errors,
			SessionStatus status) {
		User u = userManager.getUserByUsername(c.getUsername(), false);
		if (u == null) {
			errors.rejectValue("username", "no-such-user", "User not found.");
		}
		new DisableUserFormValidator().validate(c, errors);
		
		if (errors.hasErrors()) {
			return "admin/DisableUser";
		} else {
			// XXX send email
			
			u.setDisabled(c.isDisabled());
			u.setInactive(c.isInactive());
			u.setDisabledReason(c.getReason());
			u.setDisabledDate(new Date());
			userManager.saveUser(u);
			status.setComplete();
			return "redirect:editUser.do?username=" + u.getUsername();
		}
	}
	
	protected static class DisableUserFormCommand {
		private String username;
		private boolean disabled;
		private boolean inactive;
		private String reason;

		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public boolean isDisabled() {
			return disabled;
		}
		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}
		public boolean isInactive() {
			return inactive;
		}
		public void setInactive(boolean inactive) {
			this.inactive = inactive;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}		
	}
	
	protected static class DisableUserFormValidator extends NeonValidator {
		private final int DEFAULT_MIN_LENGTH = 3;
	    private final int DEFAULT_MAX_LENGTH = 50;
	    
		public boolean supports(Class clazz) {
			return DisableUserFormCommand.class.equals(clazz);
		}

		public void validate(Object target, Errors errors) {
			DisableUserFormCommand c = (DisableUserFormCommand) target;
			validateString("username", c.getUsername(), errors, DEFAULT_MIN_LENGTH, DEFAULT_MAX_LENGTH, usernameRegex);
			//validateString("reason",   c.getUsername(), errors, 0, DEFAULT_MAX_LENGTH, basicRegexSpace);
		}		
	}
}