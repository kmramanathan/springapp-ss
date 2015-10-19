package springapp.web.admin.billing;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Transaction;
import springapp.service.search.OrdersManager;

@Controller
public class TransactionController {
    /*
     * fields
     */
	protected final Logger logger = Logger.getLogger(getClass());    

	@Autowired
	private OrdersManager ordersManager;

	/*
     * web methods
     */
	@RequestMapping("/orders/viewTransaction.do")
	public ModelAndView getUser(
			@RequestParam("transactionId") int transactionId				
	) throws Exception {
		Transaction t = ordersManager.getTransaction(transactionId);
		return new ModelAndView("orders/ViewTransaction", "t", t);
    }
}