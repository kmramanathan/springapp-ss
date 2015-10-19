package springapp.web.admin.billing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.searchsystems.limestone.BillingReports;
import net.searchsystems.limestone.SsTransactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Rate;
import springapp.repository.SubscriptionDao;
import springapp.service.billing.InvoiceManager;
import springapp.service.billing.RateManager;
import springapp.web.NeonController;

@SessionAttributes("command")
@Controller
public class BillingReportsController extends NeonController {
	protected static final String vwListBillingReports = "/admin/billing/ListBillingReports";
	protected static final String vwViewBillingReport = "/admin/billing/ViewBillingReport";
	protected static final String vwListBillingReportDetails = "/admin/billing/ListBillingReportDetails";
	protected static final String vwSalesForecastSummary = "/admin/billing/SalesForecastSummary";
	protected static final String vwSalesForecastDetails = "/admin/billing/SalesForecastDetails";
	
	@Autowired
	protected InvoiceManager invoiceManager;
	@Autowired
	protected RateManager rateManager;
	@Autowired
	protected SubscriptionDao subscriptionDao;
	
	@RequestMapping("/admin/billing/listBillingReports.do")
	public ModelAndView listAllBillingReports(ModelMap model) {
		List<BillingReports> list = invoiceManager.getAllBillingReports();
		model.addAttribute("reports", list);
		model.addAttribute("rowCount", list.size());
		return new ModelAndView(vwListBillingReports);
	}
	
	@RequestMapping("/admin/billing/deleteBillingReport.do")
	public String listAllBillingReports(@RequestParam("reportId") Integer reportId) {
		invoiceManager.deleteBillingReport(reportId);
		return "redirect:listBillingReports.do";
	}
	
	@RequestMapping("/admin/billing/viewBillingReport.do")
	public ModelAndView viewBillingReport(
			ModelMap model,
			@RequestParam("reportId") Integer reportId) {
		BillingReports r = invoiceManager.getBillingReport(reportId);
		List<SsTransactions> txns = invoiceManager.getBillingReportTransactions(reportId);
		ModelAndView mav = new ModelAndView(vwViewBillingReport);
		mav.addObject("r", r);
		mav.addObject("txns", txns);
		mav.addObject("txnStatus", populateTxnStatus());
		return mav;
	}
	
	@RequestMapping("/admin/billing/listBillingReportDetails.do")
	public ModelAndView listBillingReportDetails(
			ModelMap model,
			@RequestParam("reportId") Integer reportId) {
		List<SsTransactions> list = invoiceManager.getBillingReportTransactions(reportId);
		return new ModelAndView(vwListBillingReportDetails, "txns", list);
	}
	
	//,
	@RequestMapping(value = "/admin/billing/viewSalesForecast.do", method = RequestMethod.GET)
	public ModelAndView doSalesForecastGet(
			ModelMap map,
			@RequestParam(value="daysAhead",required=false) Integer daysAhead,
			@RequestParam(value="rateId",required=false) Integer rateId,
			@RequestParam(value="deact",required=false) Boolean deactivation) {
		if (daysAhead == null) {
			daysAhead = 30;
		}
		DaysAheadCommand command = new DaysAheadCommand();
		command.setDaysAhead(daysAhead);
		map.addAttribute("command", command);
		return this.doSalesForecastImpl(map, rateId, deactivation, daysAhead);
	}
	
	@RequestMapping(value = "/admin/billing/viewSalesForecast.do", method = RequestMethod.POST)
	public ModelAndView doSalesForecastPost(
			ModelMap map,
			@RequestParam(value="rateId",required=false) Integer rateId,
			@RequestParam(value="deact",required=false) Boolean deactivation,
			@ModelAttribute("command") DaysAheadCommand command) {
		return this.doSalesForecastImpl(map, rateId, deactivation, command.getDaysAhead());
	}
	
	protected ModelAndView doSalesForecastImpl(ModelMap map, Integer rateId, Boolean deactivation, Integer daysAhead) {
		if (deactivation == null) { deactivation = false; }
		map.addAttribute("daysAhead", daysAhead);
		logger.info("days: " + daysAhead);
		
		//ModelAndView mav;
		if (rateId == null) {
			List<Map<String, Object>> ratesRebill = subscriptionDao.getSubscriptionSalesForecast(daysAhead);
			List<Map<String, Object>> ratesNoRebill = subscriptionDao.getNoRebillSubscriptionsSalesForecastSummary(daysAhead);
			ModelAndView mav = new ModelAndView(vwSalesForecastSummary);
			mav.addObject("ratesRebill", ratesRebill);
			mav.addObject("ratesNoRebill", ratesNoRebill);
			return mav;			
		} else {
			List<Map<String, Object>> subs;
			if (deactivation) {
				subs = subscriptionDao.getNoRebillSubscriptionsSalesForecastByRate(daysAhead, rateId);
			} else {
				subs = subscriptionDao.getSubscriptionsDueForBilling(daysAhead, rateId);
			}
			Integer rowCount = subs.size();			
			ModelAndView mav = new ModelAndView(vwSalesForecastDetails);
			mav.addObject("subs", subs);
			mav.addObject("rowCount", rowCount);
			return 	mav;
		}
	}
	
	public static class DaysAheadCommand {
		private Integer daysAhead;
		public Integer getDaysAhead() {
			return daysAhead;
		}
		public void setDaysAhead(Integer daysAhead) {
			this.daysAhead = daysAhead;
		}	
	}
	
	@ModelAttribute("txnStatus")
    public HashMap<Short,String> populateTxnStatus() {
		HashMap<Short,String> map = new HashMap<Short,String>();
		map.put((short) 10, "Approved");
		map.put((short) 11, "Declined");
		map.put((short) 12, "Error");
		return map;
	}
	
	@ModelAttribute("rates")
    public List<Rate> populateRates() {
		return rateManager.getAllRates();
	}
}