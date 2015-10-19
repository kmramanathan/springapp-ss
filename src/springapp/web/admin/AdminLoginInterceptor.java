package springapp.web.admin;

import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

public class AdminLoginInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(getClass());
	private ArrayList<String> includeUrls = new ArrayList<String>();
	private HashSet<String> includeUrlsSet = new HashSet<String>();
	
	protected static final String loginUrl = "/admin/login.do";
	protected static final String loginView = "/admin/Login";
	
	public ArrayList<String> getIncludeUrls() {
		return includeUrls;
	}
	public void setIncludeUrls(ArrayList<String> includeUrls) {
		this.includeUrls = includeUrls;
	}
	
	public void afterPropertiesSet() {
		includeUrlsSet.addAll(includeUrls);
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// check for session first since its way faster
		AdminUserSession adminUserSession = (AdminUserSession) WebUtils.getSessionAttribute(request, AdminLoginController.SESSION_ATTRIBUTE);
		if ((adminUserSession != null) && (adminUserSession.isAuthenticated())) {
			return true;
		}	
		
		AdminUserSession adminClientUserSession = (AdminUserSession) WebUtils.getSessionAttribute(request, AdminLoginController.CLIENT_SESSION_ATTRIBUTE);
		if ((adminClientUserSession != null) && (adminClientUserSession.isAuthenticated())) {
			return true;
		}
		
		// check the request uri
		String path = request.getServletPath();

		// bypass if trying to login
		if (path.contains(loginUrl)) {
			return true;
		}		
		
		// check against include list
		boolean gotOne = false;
		for (String s : includeUrls) {
			if (path.contains(s)) {
				gotOne = true;
				break;
			}			
		}
		if (!gotOne) {
			return true;
		}
		logger.info("interceptor fired for: " + path);

		ModelAndView modelAndView = new ModelAndView(loginView);
		String loginForwardAction = request.getContextPath() + request.getServletPath();
		if (request.getQueryString() != null) {
			loginForwardAction += request.getQueryString(); 
		}
		logger.info("loginForwardAction: " + loginForwardAction);
		modelAndView.addObject("loginForwardAction", loginForwardAction);
		throw new ModelAndViewDefiningException(modelAndView); 
	}
}
