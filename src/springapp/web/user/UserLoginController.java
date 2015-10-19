package springapp.web.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.User;
import springapp.service.login.LoginManager;

@Controller
public class UserLoginController extends AbstractUserController {
	@Autowired
	private LoginManager loginManager;

	@RequestMapping(value = "/user/login.do", method = RequestMethod.GET)
	public ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView(loginView);
	}
	
	@RequestMapping(value = "/user/login.do", method = RequestMethod.POST)
	public ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (loginManager.authenticateUser(username, password)) {
			// get canoncial username (correct case)
			List<User> list = userManager.getUsersByUsernameCaseInsensitive(username);
			User u = list.get(0);
			username = u.getUsername();

			// set session
			UserSession userSession = new UserSession();
			userSession.setAuthenticated(true);
			userSession.setUsername(username);
			request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME, userSession);
			request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT);
			
			String loginForwardAction = request.getParameter("loginForwardAction");
			if (loginForwardAction == null) {
				logger.info("login ok, redir to index");
				response.sendRedirect(request.getContextPath() + indexDefault);
				return null;
			} else {
				logger.info("login ok, redir to: " + loginForwardAction);
				response.sendRedirect(loginForwardAction);
				return null;
			}	
		} else {
			return new ModelAndView(loginView, "message", "Invalid username or password.  Signon failed.");			
		}
	}	
	
	@RequestMapping("/user/logout.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute(SESSION_ATTRIBUTE_NAME);
		response.sendRedirect(request.getContextPath() + loginUrl);
		return null;
	}
}
