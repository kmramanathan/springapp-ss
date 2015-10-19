package springapp.web.user;

import org.springframework.stereotype.Controller;

/**
 * General purpose class for display-only URLs
 * 
 * @author judd
 *
 */
@Controller
public class UserDisplay extends AbstractUserController {
	protected void init() {		
	}
	/*
	@RequestMapping("/user/valueProposition.do")
	public String showValueProposition(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="nationwide",required=false) Boolean nationwide
			)  {
		if (nationwide == null) { nationwide = false; }
		map.addAttribute("nationwide", nationwide);
		session.removeAttribute("searchFormCommand");
		session.removeAttribute("signupFormCommand");
		return valuePropositionView;
	}
	
	@RequestMapping("/user/criminalRecordsGuide.do")
	public String showCriminalRecordsGuideView()  {
		return criminalRecordsGuideView;
	}
	*/
}