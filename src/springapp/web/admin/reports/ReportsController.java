package springapp.web.admin.reports;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.CreditCard;
import springapp.domain.User;
import springapp.service.billing.BillingManager;
import springapp.service.user.UserManager;

@Controller
public class ReportsController {
	protected final Logger logger = Logger.getLogger(getClass());
	
	protected static final String urlListModifiedCards = "/admin/reports/listModifiedCards.do";
	
	protected static final String vwListModifiedCards = "/admin/reports/ListModifiedCards";
	protected static final String vwListExpiredCards = "/admin/reports/ListExpiredCards";
	
	@Autowired
	private UserManager userManager;
	@Autowired
	private BillingManager billingManager;

	/*
     * web methods
     */
	@RequestMapping(urlListModifiedCards)
	public ModelAndView listModifiedCards() throws Exception {
		List<Map<String,Object>> list = billingManager.getModifiedCards();
		ModelAndView mav = new ModelAndView(vwListModifiedCards);
		mav.addObject("creditCards", list);
		mav.addObject("rowCount", list.size());
		return mav;
    }

	@RequestMapping("/admin/reports/listExpiredCards.do")
	public ModelAndView listExpiredCards() throws Exception {
		List<Map<String,Object>> list = billingManager.getExpiredCards();
		ModelAndView mav = new ModelAndView(vwListExpiredCards);
		mav.addObject("creditCards", list);
		mav.addObject("rowCount", list.size());
    	return mav;
    }
	
	@RequestMapping("/admin/reports/removeModifiedCard.do")
	public String removeModifiedCard(
			@RequestParam("cc_username") String username) {
		User u = userManager.getUserByUsername(username);
		if (u != null) {
			// clear modified flag on card
			CreditCard cc = userManager.getCreditCard(u.getUserId());
			cc.setCardModified(false);
			userManager.saveCreditCard(cc);
		}
		return "redirect:" + urlListModifiedCards;
    }
}