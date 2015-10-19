package springapp.web.admin.user;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.User;
import springapp.service.user.UserManager;
import springapp.service.user.UserValidator;

@Controller
@RequestMapping("/admin/editUser.do")
@SessionAttributes({"command"})

/*
 * after lots of trial and error this appears to be the best way
 * to handle complex forms. the command object is a container for 
 * all the other objects. we save it in the session and we don't 
 * need to worry about other objects.
 */
public class EditUserForm {
	protected final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private UserManager userManager;

	// db lookups
	@ModelAttribute("passQuestions")
	public HashMap<Integer, String> populatePassQuestions() {
		return this.userManager.getPassQuestions(false);
	}
	
	/*
	 * GET sets up the command object and returns the mav.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm(@RequestParam("username") String username) {
		ModelAndView mav = new ModelAndView("admin/EditUser");
		EditUserFormCommand command = new EditUserFormCommand();
		command.setUser(userManager.getUserByUsername(username));
		mav.addObject("command", command);
		return mav;
	}

	/*
	 * POST needs to be careful. first we get the cmd object.
	 * validation requires nested path. BUT, we need to use push/pop
	 * because using set will break the jsp when we return.
	 * 
	 * on success, setComplete will destroy the session object. so 
	 * we can't just return a mav, we need to redirect. this is 
	 * probably a good idea anyway as we don't need to monkey with
	 * the command object anymore. but be aware of it.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			ModelMap map,
			@ModelAttribute("command") EditUserFormCommand command,
			Errors errors,
			SessionStatus status) {
		User user = command.getUser();
		UserValidator v = new UserValidator();
		errors.pushNestedPath("user");
		v.validateQuick(user, errors);
		errors.popNestedPath();
		
		if (errors.hasErrors()) {
			map.addAttribute(errors);
			return "admin/EditUser";
		} else {
			userManager.saveUser(user);
			status.setComplete();
			return "redirect:viewUser.do?username=" + user.getUsername();
		}
	}	
	
	public static class EditUserFormCommand {
		private User user;

		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}		
	}	
}