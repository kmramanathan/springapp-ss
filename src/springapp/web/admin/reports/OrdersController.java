package springapp.web.admin.reports;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.searchsystems.limestone.BGCRequestPeer;
import net.searchsystems.limestone.BGCResponsePeer;
import net.searchsystems.limestone.BjlSearches;
import net.searchsystems.limestone.BjlSearchesPeer;

import org.apache.log4j.Logger;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
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

import springapp.domain.BGCRequest;
import springapp.domain.BGCRequestBean;
import springapp.domain.BGCResponse;
import springapp.domain.CriminalPurposesBean;
import springapp.domain.FRSubscription;
import springapp.domain.NewBJLSearches;
import springapp.domain.NewBJLSearchesPeer;
import springapp.domain.Order;
import springapp.domain.Rate;
import springapp.domain.Transaction;
import springapp.domain.User;
import springapp.domain.NSS.NSSRequestBean;
import springapp.domain.NSS.NssResponseBean;
import springapp.domain.RealProperty.RealPropRequestBean;
import springapp.domain.corporation.CorporationSearches;
import springapp.domain.corporation.CorporationSearchesPeer;
import springapp.domain.eviction.EvictionSearches;
import springapp.domain.eviction.EvictionSearchesPeer;
import springapp.repository.AdminUserDao;
import springapp.repository.BGCRequestDao;
import springapp.repository.RealPropRequestDao;
import springapp.repository.RealPropResponseDao;
import springapp.repository.SubscriptionDao;
import springapp.repository.UserDao;
import springapp.service.billing.RateManager;
import springapp.service.search.OrdersManager;
import springapp.service.search.SearchManager;
import springapp.service.user.UserManager;
import springapp.web.flatrate.BGCSearchForm;

@Controller
public class OrdersController {
	protected final Logger logger = Logger.getLogger(getClass()); 
		
	protected static final String vwShowOrders = "/admin/reports/ShowOrders";
	protected static final String freeBGRusers = "/admin/reports/freeBGRusers";
	protected static final String freeFRusers = "/admin/reports/freeFRusers";
	protected static final String vwViewTransaction = "/admin/reports/ViewTransaction";
	protected static final String dsViewTransaction = "/admin/reports/DisplayTransaction";
	protected static final String vwViewBGCRequest = "/admin/reports/ViewBGCRequest";
	protected static final String vwViewBJLRequest = "/admin/reports/ViewBJLRequest";
	protected static final String vwViewFRRequest = "/admin/reports/ViewFRRequest";
	protected static final String vwEditFRRequest = "/admin/reports/ViewFRRequest";
	protected static final String dsViewFRRequest = "/admin/reports/DisplayFRRequest";
	protected static final String vwClientShowOrders = "/admin/reports/ClientShowOrders";
	protected static final String clientIndex = "/admin/reports/ShowOrders";	
	protected static final String clientIndexPage = "/admin/reports/clientIndex";
	protected static final String vwEvictionRequest = "/admin/reports/ViewEvictionRequest";
	protected static final String vwCorpRequest = "/admin/reports/ViewCorpRequest";
	protected static final String vwNssRequest = "/admin/reports/ViewNssRequest";
	protected static final String vwRealPropRequest = "/admin/reports/ViewRealPropRequest";
	//New Customers
	protected static final String vwShowNewUsers = "/admin/reports/ShowNewUsers";
	
	@Autowired
	private OrdersManager ordersManager;
	@Autowired
	private SearchManager searchManager;
	@Autowired
	private SubscriptionDao subscriptionDao;
	@Autowired
	private RateManager rateManager;
	@Autowired
	private BGCRequestDao purposeDao;
	@Autowired
	private UserManager userManager;
	@Autowired
	protected RealPropRequestDao RPrequestDao;
	
	
	@RequestMapping(value = "/admin/reports/showFRSubscription.do", method = RequestMethod.GET)
	public ModelAndView viewFRrequest(@RequestParam("transactionId") int txnId,
			@RequestParam(value = "requestFor", required = false) String requestFor) {
		FRSubscription frsub = subscriptionDao.getSubscriptionByTxnId(txnId);
		ModelAndView mav = new ModelAndView();
		Rate rate = rateManager.getRate(frsub.getPlanid());
		if(requestFor == null){
			mav = new ModelAndView(vwViewFRRequest);
		}else{
			mav = new ModelAndView(dsViewFRRequest);
		}		
		mav.addObject("frsub", frsub);
		mav.addObject("rate", rate);
		return mav;
	}

	@RequestMapping(value = "/admin/reports/freeBGRusers.do", method = RequestMethod.GET)
	public ModelAndView viewFBGRU() {
		List<Map<String, Object>> BGRusers = ordersManager.getFBGRusers();
		List<Map<String, Object>> tracker = ordersManager.getTracker();
		
		ModelAndView mav = new ModelAndView(freeBGRusers);
		mav.addObject("BGRusers", BGRusers);
		mav.addObject("tracker", tracker);
		mav.addObject("userCount", BGRusers.size());
		mav.addObject("command", new FormCommandObject());
		return mav;
	}
	
	@RequestMapping(value = "/admin/reports/freeBGRusers.do", method = RequestMethod.POST)
	public ModelAndView processUserSubmit(
			@ModelAttribute("command") FormCommandObject command,
			Errors errors,
			SessionStatus status			
			) {
		
		String username = command.getUsername();
		if (username == null) {
			errors.reject("no-filters", "You must Enter Username.");
			
		}
		List<Map<String, Object>> BGRusers = ordersManager.getFBGRbyuser(username);
		List<Map<String, Object>> tracker = ordersManager.getTracker();
		
		ModelAndView mav = new ModelAndView(freeBGRusers);
		mav.addObject("BGRusers", BGRusers);
		mav.addObject("tracker", tracker);
		mav.addObject("userCount", BGRusers.size());
		mav.addObject("command", command);
		return mav;
	}
	
	@RequestMapping(value = "/admin/reports/freeFRusers.do", method = RequestMethod.GET)
	public ModelAndView viewFRU() {
		List<Map<String, Object>> FRusers = ordersManager.getFreeFRusers();
		ModelAndView mav = new ModelAndView(freeFRusers);
		mav.addObject("FRusers", FRusers);
		mav.addObject("userCount", FRusers.size());
		mav.addObject("command", new FormCommandObject());
		return mav;
	}
	
	@RequestMapping(value = "/admin/reports/freeFRusers.do", method = RequestMethod.POST)
	public ModelAndView processFRUserSubmit(
			@ModelAttribute("command") FormCommandObject command,
			Errors errors,
			SessionStatus status			
			) {
		
		String username = command.getUsername();
		if (username == null) {
			errors.reject("no-filters", "You must Enter Username.");
			
		}
		List<Map<String, Object>> FRusers = ordersManager.getFreeFRbyuser(username);
			
		ModelAndView mav = new ModelAndView(freeFRusers);
		mav.addObject("FRusers", FRusers);
		mav.addObject("userCount", FRusers.size());
		mav.addObject("command", command);
		return mav;
	}	
	
	
	//New users report
	@RequestMapping(value = "/admin/reports/newUsers.do", method = RequestMethod.GET)
	public ModelAndView setupNewUser() 
	{
		List<Order> orders = ordersManager.getOrders();
		List<User> customers=userManager.getnewCustomers();
		ModelAndView mav = new ModelAndView(vwShowNewUsers);
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) 
		{
			User user = (User) iterator.next();
			logger.info("name:"+user.getUserId()+"user id:"+user.getUsername()+"create date:"+user.getCreateDate());
		}
		mav.addObject("customers",customers);
		mav.addObject("orders", orders);
		mav.addObject("orderCount", customers.size());
		mav.addObject("command", new FormCommandObject());
		return mav;
	}
	
	@RequestMapping(value = "/admin/reports/newUsers.do", method = RequestMethod.POST)
	public ModelAndView newUserSubmit(
			@ModelAttribute("command") FormCommandObject command,
			Errors errors,
			SessionStatus status,
			HttpSession session) {
		List<Order> orders;
		List<User> customers=userManager.getnewCustomersByDays(command.getDaysToSearch());
		
		HashMap<OrdersManager.QueryField, Object> map = new HashMap<OrdersManager.QueryField, Object>();
		/*		
		addFieldMaybe(map, OrdersManager.QueryField.USERNAME, command.getUsername(), command.getPartialMatch());
		addFieldMaybe(map, OrdersManager.QueryField.EMAIL, command.getEmail(), command.getPartialMatch());
		addFieldMaybe(map, OrdersManager.QueryField.LAST4, command.getLast4());
		addFieldMaybe(map, OrdersManager.QueryField.TRANSACTION, command.getTransaction());
		addFieldMaybe(map, OrdersManager.QueryField.AMOUNT, command.getAmount());
		
		// bail out if someone tries to kill us with too many days and no filters
		if (map.size() == 0 && command.getDaysToSearch() > 7) {
			errors.reject("no-filters", "You must use at least 1 filter field. Resetting to 7 days.");
			command.setDaysToSearch(7);
		}
			
		*/
		orders = ordersManager.getOrdersFilter(map, command.getDaysToSearch());
		ModelAndView mav = new ModelAndView(vwShowNewUsers);
		
		mav.addObject("customers",customers);
		mav.addObject("command", command);
    	mav.addObject("orders", orders);
    	mav.addObject("orderCount", customers.size());    	
    	//logger.info("Oder Count" +  customers.size());
    	
    	return mav;
	}
	
	
	@RequestMapping(value = "/admin/reports/orders.do", method = RequestMethod.GET)
	public ModelAndView setupForm() {
		List<Order> orders = ordersManager.getOrders();
		List<User> customers=userManager.getnewCustomers();
		ModelAndView mav = new ModelAndView(vwShowOrders);
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			logger.info("name:"+user.getUserId()+"user id:"+user.getUsername()+"create date:"+user.getCreateDate());
		}
		mav.addObject("customers",customers);
		mav.addObject("orders", orders);
		mav.addObject("orderCount", orders.size());
		mav.addObject("command", new FormCommandObject());
		return mav;
	}
	
	@RequestMapping(value = "/admin/reports/orders.do", method = RequestMethod.POST)
	public ModelAndView processSubmit(
			@ModelAttribute("command") FormCommandObject command,
			Errors errors,
			SessionStatus status,
			HttpSession session) {
		List<Order> orders;
		HashMap<OrdersManager.QueryField, Object> map = new HashMap<OrdersManager.QueryField, Object>();
				
		addFieldMaybe(map, OrdersManager.QueryField.USERNAME, command.getUsername(), command.getPartialMatch());
		addFieldMaybe(map, OrdersManager.QueryField.EMAIL, command.getEmail(), command.getPartialMatch());
		addFieldMaybe(map, OrdersManager.QueryField.LAST4, command.getLast4());
		addFieldMaybe(map, OrdersManager.QueryField.TRANSACTION, command.getTransaction());
		addFieldMaybe(map, OrdersManager.QueryField.AMOUNT, command.getAmount());
		
		// bail out if someone tries to kill us with too many days and no filters
		if (map.size() == 0 && command.getDaysToSearch() > 7) {
			errors.reject("no-filters", "You must use at least 1 filter field. Resetting to 7 days.");
			command.setDaysToSearch(7);
		}
		orders = ordersManager.getOrdersFilter(map, command.getDaysToSearch());	
		ModelAndView mav = new ModelAndView(vwShowOrders);
		
		mav.addObject("command", command);
    	mav.addObject("orders", orders);
    	mav.addObject("orderCount", orders.size());
    	return mav;
	}	
	
	
	//For Client View
	@RequestMapping(value = "/admin/reports/clientIndex.do", method = RequestMethod.GET)
	public ModelAndView setupClientindex() {
		ModelAndView mav = new ModelAndView(clientIndexPage);
		return mav;
	}
	
	@RequestMapping(value = "/admin/reports/viewOrders.do", method = RequestMethod.GET)
	public ModelAndView setupClientForm() {
		List<Order> orders = ordersManager.getOrders();
		ModelAndView mav = new ModelAndView(vwClientShowOrders);
		mav.addObject("orders", orders);
		mav.addObject("orderCount", orders.size());
		mav.addObject("command", new FormCommandObject());
		return mav;
	}
	
	@RequestMapping(value = "/admin/reports/viewOrders.do", method = RequestMethod.POST)
	public ModelAndView processClientSubmit(
			@ModelAttribute("command") FormCommandObject command,
			Errors errors,
			SessionStatus status) {
		List<Order> orders;
		HashMap<OrdersManager.QueryField, Object> map = new HashMap<OrdersManager.QueryField, Object>();
				
		addFieldMaybe(map, OrdersManager.QueryField.USERNAME, command.getUsername(), command.getPartialMatch());
		addFieldMaybe(map, OrdersManager.QueryField.EMAIL, command.getEmail(), command.getPartialMatch());
		addFieldMaybe(map, OrdersManager.QueryField.LAST4, command.getLast4());
		addFieldMaybe(map, OrdersManager.QueryField.TRANSACTION, command.getTransaction());
		addFieldMaybe(map, OrdersManager.QueryField.AMOUNT, command.getAmount());
		
		// bail out if someone tries to kill us with too many days and no filters
		if (map.size() == 0 && command.getDaysToSearch() > 7) {
			errors.reject("no-filters", "You must use at least 1 filter field. Resetting to 7 days.");
			command.setDaysToSearch(7);
		}
		orders = ordersManager.getOrdersFilter(map, command.getDaysToSearch());			
		
		ModelAndView mav = new ModelAndView(vwClientShowOrders);
		mav.addObject("command", command);
    	mav.addObject("orders", orders);
    	mav.addObject("orderCount", orders.size());
    	return mav;
	}	
	
	@RequestMapping(value = "/admin/reports/viewTransaction.do", method = RequestMethod.GET)
	public ModelAndView viewTransaction(@RequestParam("transactionId") int transactionId,
			@RequestParam(value = "requestFor", required = false) String requestFor) {
		Transaction t = ordersManager.getTransaction(transactionId);
		ModelAndView mav = new ModelAndView();
		if(requestFor == null){
			mav = new ModelAndView(vwViewTransaction);	
		}else{
			mav = new ModelAndView(dsViewTransaction);
		}
		mav.addObject("t", t);
		return mav;
	}
	
	@RequestMapping(value = "/admin/reports/viewBGCRequest.do", method = RequestMethod.GET)
	public ModelAndView viewBGCRequest(@RequestParam("transactionId") int transactionId) {
		
		/*BGCRequest req = searchManager.getBGCRequest(transactionId);
		BGCResponse resp = searchManager.getBGCResponseForRequest(transactionId);		
		ModelAndView mav = new ModelAndView(vwViewBGCRequest);
		mav.addObject("req", req);
		mav.addObject("resp", resp);
		return mav;*/
		
		ModelAndView mav = new ModelAndView(vwViewBGCRequest);
		Criteria c = new Criteria();
		Criteria c1 = new Criteria();
		List<net.searchsystems.limestone.BGCRequest> reqL;
		List<net.searchsystems.limestone.BGCResponse> resL;
		List<BGCRequestBean> reqp;
		List<CriminalPurposesBean> reqpurpose;
		try {
			//c.add(BGCResponsePeer.TRANSACTION_ID, transactionId);
			c.add(BGCResponsePeer.BGC_REQUEST_ID, transactionId);
			resL = BGCResponsePeer.doSelect(c);
			net.searchsystems.limestone.BGCResponse res = resL.get(0);
			
			c1.add(BGCRequestPeer.BGC_REQUEST_ID, res.getBgcRequestId());
			reqL = BGCRequestPeer.doSelect(c1);
			net.searchsystems.limestone.BGCRequest req = reqL.get(0);
			reqp=purposeDao.getPurposeResult(res.getBgcRequestId());
			reqpurpose=purposeDao.getPurposeMatch(reqp.get(0).getBgcPurpose());
			mav.addObject("purpose", reqpurpose.get(0));
			mav.addObject("resp", res);
			mav.addObject("req", req);
		} catch (TorqueException e) {
			logger.error("Error fetching BGC request and response: " + transactionId, e);
		}		
		return mav;
		
	}
	
	@RequestMapping(value = "/admin/reports/viewBJLRequest.do", method = RequestMethod.GET)
	public ModelAndView viewBJL(@RequestParam("transactionId") int transactionId) {
		ModelAndView mav = new ModelAndView(vwViewBJLRequest);
		Criteria c = new Criteria();
		//List<BjlSearches> list;
		List<NewBJLSearches> list;
		try {
			//c.add(BjlSearchesPeer.TRANSACTION_ID, transactionId);
			c.add(NewBJLSearchesPeer.TRANSACTION_ID, transactionId);// changed by vivek on 21-02-2012
			//list = BjlSearchesPeer.doSelect(c);
			list=NewBJLSearchesPeer.doSelect(c);// changed by vivek on 21-02-2012
			mav.addObject("req", list.get(0));
		} catch (TorqueException e) {
			logger.error("Error fetching BJL request: " + transactionId, e);
		}		
		return mav;
	}
	//admin report of eviction track searches // developed by vivek 30-may-2012
	@RequestMapping(value="/admin/reports/ViewEvictionRequest.do", method=RequestMethod.GET)
	public ModelAndView viewEviction(@RequestParam("transactionId") int transactionId)
	{
		ModelAndView mav=new ModelAndView(vwEvictionRequest);
		Criteria cr=new Criteria();
		
		List<EvictionSearches> evList;
		try 
		{
		cr.add(EvictionSearchesPeer.TRANSACTION_ID, transactionId);
		
			evList=EvictionSearchesPeer.doSelect(cr);
			mav.addObject("req", evList.get(0));
		} catch (TorqueException e) 
		{
			// TODO Auto-generated catch block
			logger.error("Error fetching Eviction request: " + transactionId, e);
			
		}
		return mav;
	}
	//admin report for corporation track searches // developed by vivek 10-feb-2013
	@RequestMapping(value="/admin/reports/ViewCorpRequest.do", method=RequestMethod.GET)
	public ModelAndView viewCorp(@RequestParam("transactionId") int transactionId)
	{
		ModelAndView mav=new ModelAndView(vwCorpRequest);
		Criteria cr=new Criteria();
		
		List<CorporationSearches> corpList;
		try {
		cr.add(CorporationSearchesPeer.TRANSACTION_ID, transactionId);
		
			corpList=CorporationSearchesPeer.doSelect(cr);
			mav.addObject("req", corpList.get(0));
		} catch (TorqueException e) {
			// TODO Auto-generated catch block
			logger.error("Error fetching Corporation request: " + transactionId, e);
			
		}
		return mav;
	}
	/**
	 * Admin reports for National Security Search
	 * @return
	 */
	@RequestMapping(value="/admin/reports/ViewNSSRequest.do", method=RequestMethod.GET)
	public ModelAndView viewNssRequest(@RequestParam("transactionId") int transactionId)
	{
		ModelAndView mav=new ModelAndView(vwNssRequest);
		List<CriminalPurposesBean> purposebean;
		try {
			List<NssResponseBean> res=searchManager.getNssResponse(transactionId);
			List<NSSRequestBean> req=searchManager.getNssRequest(res.get(0).getNssRequestId());
			purposebean=purposeDao.getPurposeMatch(req.get(0).getPurpose());
			
			mav.addObject("req",req.get(0));
			mav.addObject("resp", res.get(0));
			mav.addObject("purpose",purposebean.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Error fetching National Security request: "+transactionId, e);
		}
		return mav;
		
	}
	
	/**
	 * Admin reports for Real Property Search
	 * @return
	 */
	@RequestMapping(value="/admin/reports/ViewRealPropRequest.do", method=RequestMethod.GET)
	public ModelAndView ViewRealPropRequest(@RequestParam("transactionId") int transactionId)
	{
		ModelAndView mav=new ModelAndView(vwRealPropRequest);
		List<CriminalPurposesBean> purposebean;
		try {
			List<RealPropRequestBean> req=RPrequestDao.getRequestByTransID(transactionId);		
			//logger.error("RealProperty_LIST::@@: "+req);
			mav.addObject("req",req.get(0));
		
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Error fetching Real Property request: "+transactionId);
		}
		
		
		return mav;
		
	}
	
	protected void addFieldMaybe(HashMap<OrdersManager.QueryField, Object> map, OrdersManager.QueryField key, Object value) {
		this.addFieldMaybe(map, key, value, false);
	}
	protected void addFieldMaybe(HashMap<OrdersManager.QueryField, Object> map, OrdersManager.QueryField key, Object value, Boolean partialMatch) {
		if (value == null || value.equals("")) {
			return;
		} else {
			if (partialMatch) {
				map.put(key, "%" + value + "%");
			} else {
				map.put(key, value);
			}
		}
	}
	
	
	protected static class FormCommandObject {
		private String username;
		private Integer daysToSearch;
		private Integer last4;
		private String email;
		private Integer transaction;
		private BigDecimal amount;
		private Boolean partialMatch = false;
		
		public Boolean getPartialMatch() {
			return partialMatch;
		}
		public void setPartialMatch(Boolean partialMatch) {
			this.partialMatch = partialMatch;
		}
		public Integer getLast4() {
			return last4;
		}
		public void setLast4(Integer last4) {
			this.last4 = last4;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Integer getTransaction() {
			return transaction;
		}
		public void setTransaction(Integer transaction) {
			this.transaction = transaction;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public Integer getDaysToSearch() {
			return daysToSearch;
		}
		public void setDaysToSearch(Integer daysToSearch) {
			this.daysToSearch = daysToSearch;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}		
	}
}