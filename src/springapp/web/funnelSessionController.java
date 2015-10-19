package springapp.web;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;
import org.apache.commons.httpclient.HttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import springapp.domain.CreditCard;
import springapp.domain.User;
import springapp.web.flatrate.FRLoginForm;

@Controller

public class funnelSessionController extends springapp.web.funnel.AbstractFunnelController {
	
	// post handles checkboxes for view details selected
	@RequestMapping(value = "/funnelSessionController.do")
	public String viewDetailedResults(
			HttpSession session,
			ServletRequest request, ServletResponse response,
			ModelMap map,
			@RequestParam(value="calledPage",required=false) String calledPage,
			@RequestParam(value="authenticated",required=false) Boolean authenticated,
			@RequestParam(value="user_id",required=false) String user_id,
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="no_credit_card",required=false) String noCreditCard,
			@RequestParam(value="searchFirstName",required=false) String searchFirstName,
			@RequestParam(value="searchLastName",required=false) String searchLastName,
			@RequestParam(value="searchState",required=false) String searchState,
			@RequestParam(value="banner",required=false) Boolean banner
			)  {
	
		int userId=0;
		if(user_id != null && !user_id.equals("")){
			userId = Integer.parseInt(user_id);
		}
		
		if(banner == null) banner = false;
		if (session.getAttribute("BGlanding") != null) { session.removeAttribute("BGlanding"); }
		if(authenticated != null && authenticated ) 
		{
			session.setAttribute("authenticated", "true");
			session.setAttribute("username", username);
			session.setAttribute("userId", userId);
			User user = userManager.getUserByUserId(userId);
		
			if(noCreditCard != null)
				session.setAttribute("noCreditCard", true);
			else{				
				CreditCard cc = userManager.getCreditCard(userId);
				session.setAttribute("creditCardObj", cc); 
			}
			  // if 1, its company credit card and no need to charge
		
		} else {
			session.removeAttribute("authenticated");
			session.removeAttribute("userId");
			session.removeAttribute("creditCardObj");
			session.removeAttribute("username");
			session.removeAttribute("noCreditCard");
			
		}
		if(calledPage != null){
			if(!banner){
				return "redirect:"+calledPage;
			}
			else{
				
				try{			
					RequestDispatcher dispatcher = request.getRequestDispatcher(calledPage);
					
					if (dispatcher != null) {
						dispatcher.forward(request, response);
					}
				}catch(Exception e){
					logger.error("Exception while redirecting page from PHP:" +e);
				}
			}
		}
		return "funnel/Error";
	}
	
	@RequestMapping(value = "/FRSessionController.do")
	public String flatRateSession(ModelMap map, HttpSession session,
			@RequestParam(value="user_id",required=true) String user_id,
			@RequestParam(value="username",required=true) String username){
		
		session.removeAttribute("FRusername");
		session.removeAttribute("FRuserId");
		session.removeAttribute("FRcreditCardObj");
		session.removeAttribute("FRnoCreditCard");
		FRLoginForm.removeFRSession(session);
	
		Integer userId = Integer.parseInt(user_id);
		User user = userManager.getUserByUserId(userId);
		session.setAttribute("FRusername", user.getUsername());
		session.setAttribute("FRuserId", userId);
		if(user.getInactive() == false){
			session.setAttribute("SSusername", user.getUsername());
		}
		if(user.getNoCreditCard() == true && user.getCustomAccount() == true){
			session.setAttribute("FRnoCreditCard", true);
		}else{
			CreditCard cc = userManager.getCreditCard(user.getUserId());
			session.setAttribute("FRcreditCardObj", cc);
		}
		return "redirect:flatrate/FRMenu.do";
	}

}




