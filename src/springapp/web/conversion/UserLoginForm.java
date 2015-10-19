package springapp.web.conversion;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Subscription;
import springapp.domain.User;
import springapp.service.login.LoginManager;
import springapp.service.user.UserManager;

@Controller
@RequestMapping("/conversion/userLogin.do")

public class UserLoginForm extends AbstractConversionController {
	@Autowired
	private UserManager userManager;	
	@Autowired
	private LoginManager loginManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm() {
		ModelAndView mav = new ModelAndView(vwUserLogin);
		UserLoginFormCommand cmd = new UserLoginFormCommand();
		mav.addObject("command", cmd);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			@ModelAttribute("command") UserLoginFormCommand cmd,
			Errors errors,
			SessionStatus status) {	
		String username = cmd.getUsername();
		String password = cmd.getPassword();			
		
		if (!loginManager.authenticateUser(username, password, true)) {
			errors.reject("login-failed", "Login failed. Check the username and password.");
			return vwUserLogin;
		}
		
		User u = userManager.getUserByUsername(username);
		List<Subscription> subs = userManager.getSubscriptions(username);
		if (subs.size() > 0) {
			errors.reject("already-have-access", "You are already a full SearchSystems.Net member. You don't need to upgrade your account.");
			return vwUserLogin;						
		}

		ConversionUserSession cus = new ConversionUserSession();

		cus.setAuthenticated(true);
		cus.setUsername(username);
		cus.setDirectPass(u.getSubDirectoryUser());
		cus.setNoPremium(u.getNoPremium());
		if (u.getSubAmount().toString().equals("4.95")) {
			cus.setMonthly(true);
		}
		
		session.setAttribute("ConversionUserSession", cus);
		session.setMaxInactiveInterval(SESSION_TIMEOUT);
		
		status.setComplete();
		
		return verifyBillingRedir;
	}
	
	public static class UserLoginFormCommand {
		private String username;
		private String password;
		private Boolean monthly;
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
		public Boolean isMonthly() {
			return monthly;
		}
		public void setMonthly(Boolean monthly) {
			this.monthly = monthly;
		}		
	}
}
