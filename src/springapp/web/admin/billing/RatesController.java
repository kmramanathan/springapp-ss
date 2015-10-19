package springapp.web.admin.billing;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Rate;
import springapp.service.billing.RateManager;

@Controller
public class RatesController {
	protected final Logger logger = Logger.getLogger(getClass()); 
	
	@Autowired
	private RateManager rateManager;
	
	private static final String editRateView = "admin/billing/EditRate";
	private static final String listRatesView = "admin/billing/ListRates";
	private static final String listRatesRedir = "redirect:listRates.do";
	
	@RequestMapping("/admin/billing/listRates.do")
	public ModelAndView getRates(ModelMap model) {
		List<Rate> rates = rateManager.getAllRates();
		return new ModelAndView(listRatesView, "rates", rates);
	}
	
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
	
	@ModelAttribute("billingPeriods")
    public HashMap<Integer,String> populateCardExpirationMonths() {
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		map.put(0, "none");
		map.put(1, "days");
		map.put(2, "weeks");
		map.put(3, "months");
		map.put(4, "years");
		return map;
	}
}