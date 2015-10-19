package springapp.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import springapp.service.user.UserManager;
import springapp.web.NeonController;

abstract public class AbstractUserController extends NeonController {
	
	protected static final String cancelAccountView = "/user/CancelAccount";
	protected static final String changePasswordView = "/user/ChangePassword";
	protected static final String updateBillingInfoView = "/user/UpdateBillingInfo";
	protected static final String updatePersonalInfoView = "/user/UpdatePersonalInformation";	

	protected static final int SESSION_TIMEOUT = 86400;
	protected static final String SESSION_ATTRIBUTE_NAME = "userSession";
	
	protected static final String loginView = "/user/Login";
	protected static final String loginUrl = "/user/login.do";
	protected static final String logoutUrl = "/user/logout.do";
	protected static final String indexDefault = "/user/index.do";

	protected static final String indexUrl = "/user/index.do";

	@Autowired
	protected UserManager userManager;
	
	protected String getUsernameFromSession(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute(UserLoginController.SESSION_ATTRIBUTE_NAME);
		return us.getUsername();
	}
}