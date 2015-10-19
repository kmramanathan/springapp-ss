package springapp.web.user;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.User;

@Controller
@RequestMapping("/user/cancelAccount.do")

public class CancelAccount extends AbstractUserController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm() {
		return new ModelAndView(cancelAccountView, "command",  new CancelAccountCommand());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			@ModelAttribute("command") CancelAccountCommand command,
			Errors errors,
			SessionStatus status) {
		UserSession us = (UserSession) session.getAttribute(UserLoginController.SESSION_ATTRIBUTE_NAME);
		String username = us.getUsername();
		User u = userManager.getUserByUsername(username);
		u.setInactive(true);
		u.setDisabledDate(new Date());
		u.setDisabledReason(command.getCancelComment());
		userManager.saveUser(u);
		
		logger.info("cancel: " + username + " : " + command.getCancelComment());
		status.setComplete();
		//return logoutView;
		return "redirect:" + logoutUrl;
	}
	
	public static class CancelAccountCommand {
		private String cancelComment;
		public String getCancelComment() {
			return cancelComment;
		}
		public void setCancelComment(String cancelComment) {
			this.cancelComment = cancelComment;
		}
	}
}