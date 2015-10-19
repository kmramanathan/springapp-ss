package springapp.web.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springapp.service.login.LoginManager;
import springapp.web.NeonController;

@Controller
public class AdminLoginController extends NeonController {
	protected static final int SESSION_TIMEOUT = 86400;
	protected static final String SESSION_ATTRIBUTE = "adminUserSession";
	protected static final String CLIENT_SESSION_ATTRIBUTE = "adminClientUserSession";
	
	protected static final String loginView = "/admin/Login";
	protected static final String loginUrl = "/admin/login.do";
	protected static final String logoutUrl = "/admin/logout.do";
	protected static final String indexDefault = "/admin/index.do";
	protected static final String clientHome = "/admin/reports/clientIndex.do";

	@Autowired
	private LoginManager loginManager;

	@RequestMapping(value = "/admin/login.do", method = RequestMethod.GET)
	public ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView(loginView);
	}
	
	@RequestMapping(value = "/admin/login.do", method = RequestMethod.POST)
	public ModelAndView doPost(HttpServletRequest request, HttpServletResponse response, HttpSession session) 
																						throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (loginManager.authenticateAdminUser(username, password)) {			
			AdminUserSession adminUserSession = new AdminUserSession();
			adminUserSession.setAuthenticated(true);
			adminUserSession.setUsername(username);			
			//checking the role
			if(!loginManager.isAllAdminUser(username)){
				request.getSession().setAttribute(CLIENT_SESSION_ATTRIBUTE, adminUserSession);
				request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT);
				response.sendRedirect(request.getContextPath() + clientHome);
				return null;
			}
			
			//continue with searchsystems admin 
			request.getSession().setAttribute(SESSION_ATTRIBUTE, adminUserSession);
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
	
	@RequestMapping("/admin/logout.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		request.getSession().removeAttribute(SESSION_ATTRIBUTE);
		session.removeAttribute(CLIENT_SESSION_ATTRIBUTE);
		session.removeAttribute("client");
		session.removeAttribute("searchsystems");
		response.sendRedirect(request.getContextPath() + loginUrl);
		return null;
	}
}
