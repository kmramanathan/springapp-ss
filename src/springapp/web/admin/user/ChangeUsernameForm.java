package springapp.web.admin.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springapp.domain.User;
import springapp.service.user.UserManager;
import springapp.service.user.UserValidator;

@Controller
@RequestMapping("/admin/changeUsername.do")

public class ChangeUsernameForm {
	protected final Logger logger = Logger.getLogger(getClass());
		
	protected static final String findUserRedir = "redirect:findUser.do";
	protected static final String changeUsernameView = "admin/ChangeUsername";

	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			ModelMap map,
			@RequestParam("oldUsername") String oldUsername) {
		User oldUser = userManager.getUserByUsername(oldUsername);
		if (oldUser == null) {
			return findUserRedir;
		}
		map.addAttribute("oldUsername", oldUsername);
		return changeUsernameView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			ModelMap map,
			@RequestParam("oldUsername") String oldUsername,
			@RequestParam("newUsername") String newUsername
			) {
		map.addAttribute("oldUsername", oldUsername);
		map.addAttribute("newUsername", newUsername);			

		logger.info("old/new: " + oldUsername + "/" + newUsername);
		User oldUser = userManager.getUserByUsername(oldUsername);
		User newUser = userManager.getUserByUsername(newUsername);
		
		if (oldUser == null) {
			map.addAttribute("errorMsg", "Old user does not exist?");
			return findUserRedir;
		} else {
			if (newUser != null) {
				map.addAttribute("errorMsg", "The new username is already taken.");
				return changeUsernameView;
			}
			
			UserValidator v = new UserValidator();
			if (!v.validateUsername(newUsername)) {
				map.addAttribute("errorMsg", "The new username is invalid.");
				return changeUsernameView;				
			}

			oldUser.setUsername(newUsername);
			userManager.changeUsername(oldUser);
			map.clear();
			return "redirect:viewUser.do?username=" + newUsername;
		}
	}
}