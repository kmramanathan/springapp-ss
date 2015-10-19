package springapp.service.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import springapp.domain.Order;
import springapp.domain.Transaction;

public interface OrdersManager {
	public enum QueryField {
		USERNAME, EMAIL, TRANSACTION, LAST4, AMOUNT;
	}
	
	public List<Order> getOrders();
	public List<Order> getOrdersDays(int daysToSearch);
	public List<Order> getOrdersLimit(int daysToSearch, String username);
	public List<Order> getOrdersFilter(HashMap<QueryField, Object> map, int daysToSearch);
	
	public Transaction getTransaction(int transactionId);
	
	public List<Map<String,Object>> getCorporateInvoiceCriminalMonth(Integer corporateAccountId, Integer month, Integer year);	
	public List<Map<String,Object>> getCorporateInvoiceBJLMonth(Integer corporateAccountId, Integer month, Integer year);
	public List<Map<String,Object>> getCorporateInvoiceFPMonth(Integer corporateAccountId, Integer month, Integer year);
	//added by vivek on 2012/05/09
	public List<Map<String,Object>> getCorporateInvoiceTSMonth(Integer corporateAccountId, Integer month, Integer year);
	//added by vivek on 2012/08/21
	public List<Map<String,Object>> getCorporateInvoiceCSMonth(Integer corporateAccountId, Integer month, Integer year);
	//added by vivek on 30/08/2013
	public List<Map<String,Object>> getCorporateInvoiceNSSMonth(Integer corporateAccountId, Integer month, Integer year);
	public List<Map<String,Object>> getCorporateInvoiceFlatRate(Integer corporateAccountId, Integer month, Integer year);
	
	//Free BGR users
	public List<Map<String,Object>> getFBGRusers();
	public List<Map<String,Object>> getTracker();
	public List<Map<String,Object>> getFBGRbyuser(String username);
	
	//Free FR Users
	public List<Map<String,Object>> getFreeFRusers();
	public List<Map<String,Object>> getFreeFRbyuser(String username);
	
	//Flat Rate Invoice
	public List<Map<String,Object>> getFRInvoiceCriminalMonth(Integer userId, Integer month, Integer year);	
	public List<Map<String,Object>> getFRInvoiceBJLMonth(Integer userId, Integer month, Integer year);
	public List<Map<String,Object>> getFRInvoiceFPMonth(Integer userId, Integer month, Integer year);
	public List<Map<String,Object>> getFRInvoiceFlatRate(Integer userId, Integer month, Integer year);
	
}