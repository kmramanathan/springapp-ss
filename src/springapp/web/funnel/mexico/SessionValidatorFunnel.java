package springapp.web.funnel.mexico;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import springapp.domain.CreditCard;

/**
 * @author Ram Kumarappan
 *
 */

@Controller

public class SessionValidatorFunnel extends springapp.web.funnel.AbstractFunnelController {
	
	// post handles checkboxes for view details selected
	@RequestMapping(value = "/funnel/mexico/sessionValidatorFunnel.do", method = RequestMethod.POST)
	public String viewDetailedResults(
			WebRequest request,
			HttpSession session,
			ModelMap map
			)  {
		Map params = request.getParameterMap();
		for (Object o : params.keySet()) {
			logger.info("SessionValidatorFunnel => Parameters: " + o + ":" + params.get(o));
		}
		
		logger.info("SessionValidatorFunnel=> isAuthenticated=" + params.get("isAuthenticated"));
		logger.info("SessionValidatorFunnel=> isAuthenticated=" + request.getParameter("isAuthenticated"));
		logger.info("SessionValidatorFunnel=> userId=" + params.get("userId"));
		logger.info("SessionValidatorFunnel=> userId=" + request.getParameter("userId"));
		
		String userId = (String)request.getParameter("userId");
		if(request.getParameter("isAuthenticated") != null && request.getParameter("isAuthenticated").equals("1")) {
			session.setAttribute("isAuthenticated", 1);
			session.setAttribute("userId", userId);
			springapp.domain.User user = userManager.getUserByUserId(Integer.parseInt(userId));
			session.setAttribute("userName", user.getUsername());
			logger.info("SessionValidatorFunnel=> userName=" + user.getUsername());
			session.setAttribute("userEmail", user.getEmail());
			logger.info("SessionValidatorFunnel=> userEmail=" + user.getEmail());
			CreditCard cc = userManager.getCreditCard(Integer.parseInt(userId));
			logger.info("SessionValidatorFunnel => CreditCard: " + cc);
			if (cc != null) {
				session.setAttribute("creditCardObj", cc);
			}
			session.setAttribute("noCreditCard", request.getParameter("noCreditCard"));  // if 1, its company credit card and no need to charge
			session.setAttribute("customAccount", request.getParameter("customAccount"));
		} else {
			session.removeAttribute("isAuthenticated");
			session.removeAttribute("userId");
			session.removeAttribute("creditCardObj");
			session.removeAttribute("userName");
			session.removeAttribute("userEmail");
		}
		
		return "redirect:searchMexicanRecord.do" + (request.getParameter("searchType") != null ? "?searchType=region": "");
	}
}
