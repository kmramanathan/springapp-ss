package springapp.web.admin.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Subscription;
import springapp.service.user.UserManager;

@Controller
@RequestMapping("/admin/addSubscription.do")

public class AddSubscriptionForm {
	protected final Logger logger = Logger.getLogger(getClass()); 
	
	@Autowired
	private UserManager userManager;
	
	private final String addView = "admin/AddSubscription";
	private final String listView = "admin/ListSubscriptions";
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm(
			@RequestParam("username") String username,
			ModelMap model) {
		return new ModelAndView(addView, "rate", new Subscription());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@RequestParam("username") String username,
			BindingResult result,
			SessionStatus status) {	
		//new SubscriptionValidator().validate(rate, result);
		if (result.hasErrors()) {
			return addView;			
		} else {
			//rateManager.saveSubscription(rate);
			return "redirect:" + listView;
		}
	}
}