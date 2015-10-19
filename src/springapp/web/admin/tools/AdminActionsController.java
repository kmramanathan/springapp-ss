package springapp.web.admin.tools;

import java.util.List;

import net.searchsystems.limestone.SsUsersAdminActions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springapp.web.admin.AbstractAdminController;

@Controller
public class AdminActionsController extends AbstractAdminController {
	private static final String vwListAdminActions = "/admin/tools/ListAdminActions";
	
	@RequestMapping("/admin/tools/listAdminActions.do")
	public ModelAndView listActions(ModelMap model) {
		List<SsUsersAdminActions> actions = userManager.getUserAdminActions();
		return new ModelAndView(vwListAdminActions, "actions", actions);
	}
	
	/*
	@RequestMapping(value="/admin/billing/editRate.do", method=RequestMethod.GET)
	public ModelAndView setupForm(
			@RequestParam(value="rateId",required=false) Integer rateId,
			ModelMap map) {
		ModelAndView mav = new ModelAndView(editRateView);		

		Rate rate;
		if (rateId == null) { 
			// new account
			mav.addObject("newRate", true);
			rate = new Rate();
		} else {
			// load account
			mav.addObject("newRate", false);
			rate = rateManager.getRate(rateId);
		}

		// set up mav
		mav.addObject("rate", rate);
		return mav;
	}
	*/
	
	/*
	@RequestMapping(value="/admin/billing/editRate.do", method = RequestMethod.POST)
	public String processSubmit(
			ModelMap map,
			@ModelAttribute("rate") Rate rate,
			Errors errors,
			SessionStatus status) {
		// XXX validate rate

		if (errors.hasErrors()) {
			logger.info("errors: " + errors.getFieldErrors());
			return editRateView;	
		} else {
			rateManager.saveRate(rate);				
			status.setComplete();
			return listRatesRedir;			
		}
	}
	
	@RequestMapping(value="/admin/billing/cloneRate.do", method = RequestMethod.GET)
	public String cloneRate(
			@RequestParam(value="rateId") Integer rateId,
			ModelMap map) {
		Rate rate = rateManager.getRate(rateId);
		if (rate != null) { 
			rate.setRateId(0);
			rateManager.saveRate(rate);
			logger.info("cloned rate id: " + rate.getRateId());
		}
		return listRatesRedir;	
	}
	*/

}