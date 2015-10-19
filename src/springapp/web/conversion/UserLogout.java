package springapp.web.conversion;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/conversion/userLogout.do")

public class UserLogout {
	private Logger logger = Logger.getLogger(getClass());
	
	private final String loginUrl = "/conversion/userLogin.do";
	
	@RequestMapping(method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:" + loginUrl;
	}
}
