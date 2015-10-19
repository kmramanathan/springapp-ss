package springapp.web.admin.user;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.service.user.UserValidator;

@Controller
public class ValidateUsernameForm {
	protected final Logger logger = Logger.getLogger(getClass());

	@RequestMapping("/admin/validateUsername.do")
	public ModelAndView setupForm(@RequestParam(value="username",required=false) String username) {
		ModelMap map = new ModelMap();
		if (username != null) {
			UserValidator v = new UserValidator();
			map.addAttribute("username", username);
			map.addAttribute("valid", v.validateUsername(username));
		}
		return new ModelAndView("admin/ValidateUsername", map);
	}
}