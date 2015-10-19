package springapp.web.admin.user;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.AdminUserIPBean;
import springapp.domain.Invoice;
import springapp.domain.InvoiceDetail;
import springapp.domain.Payment;
import springapp.domain.Rate;
import springapp.domain.Subscription;
import springapp.domain.User;
import springapp.service.billing.RateManager;
import springapp.service.user.UserManager;

@Controller
public class UserAdminController {
    /*
     * fields
     */
	protected final Logger logger = Logger.getLogger(getClass());    

	@Autowired
	private UserManager userManager;
	@Autowired
	private RateManager rateManager;
	
	/*
     * web methods
     */
	
	@RequestMapping("/admin/getUser.do")
	public ModelAndView getUser(HttpServletRequest request,
    		HttpServletResponse response, 
    		Object command) throws Exception {
    	User user = userManager.getUserByUsername(request.getParameter("username"));
    	return new ModelAndView("admin/ViewUser", "user", user);
    }
    //Udhay modified Dec10-2014
	@RequestMapping("/admin/viewUser.do")	
    public ModelAndView viewUser(
    		@RequestParam(value="username",required=false) String username,
    		@RequestParam(value="userId",required=false) Integer userId) throws Exception {
		
		User user;
		//String sUsrIP=null;
		if (username != null) {
			user = userManager.getUserByUsername(username);		
			//logger.info("User IP userManager: "+ user.getUserId());	
			AdminUserIPBean sUsrIP  = userManager.getUserWithIP(user.getUserId());
			if(sUsrIP!=null)
				user.setCancelComment(sUsrIP.getUsersIP());
			//logger.info("User IP addrs: getCancelComment()"+ sUsrIP.getUsersIP());		
		} else if (userId != null) {
			user = userManager.getUserByUserId(userId);
			
		} else {
			user = new User();
		}
    	return new ModelAndView("admin/ViewUser", "user", user); 
    }
	
	
	@RequestMapping("/admin/viewUser2.do")	
    public ModelAndView viewUser2(@RequestParam("username") String username) throws Exception {
		User user = userManager.getUserByUsername(username);
    	return new ModelAndView("admin/ViewUser2", "user", user);
	}
	
	@RequestMapping("/admin/viewSubscriptions.do")	
    public ModelAndView viewSubscriptions(
    		ModelMap map,
    		@RequestParam("username") String username) throws Exception {
		map.addAttribute("username", username);
		List<Subscription> list = userManager.getSubscriptions(username);		
    	return new ModelAndView("admin/ViewSubscriptions", "subscriptions", list);
    } 
	
	@RequestMapping("/admin/viewInvoices.do")	
    public ModelAndView viewInvoices(@RequestParam("username") String username) throws Exception {
		List<Invoice> list = userManager.getInvoices(username);
    	return new ModelAndView("admin/ViewInvoices", "invoices", list);
    }
	
	@RequestMapping("/admin/viewInvoiceDetails.do")	
    public ModelAndView viewInvoiceDetails(
    		ModelMap map,
    		@RequestParam("invoiceId") int invoiceId) throws Exception {
		List<InvoiceDetail> list = userManager.getInvoiceDetails(invoiceId);
		map.addAttribute("invoiceId", invoiceId);
    	return new ModelAndView("admin/ViewInvoiceDetails", "invoiceDetails", list);
    }
	
	@RequestMapping("/admin/viewPayments.do")	
    public ModelAndView viewPayments(@RequestParam("username") String username) throws Exception {
		List<Payment> list = userManager.getPayments(username);
    	return new ModelAndView("admin/ViewPayments", "payments", list);
    }
	
	@ModelAttribute("rateNames")
    public HashMap<Integer,String> populateRates() {
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		for (Rate r : rateManager.getAllRates()) {
			map.put(r.getRateId(), r.getRateName());
		}
		return map;
	}

}