package springapp.service.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.domain.Order;
import springapp.domain.Transaction;
import springapp.repository.OrderDao;
import springapp.repository.TransactionDao;

@Service
public class SimpleOrdersManager implements OrdersManager {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private TransactionDao transactionDao;
	
	public List<Order> getOrders() {
		return orderDao.getOrders();
	}
	public List<Order> getOrdersDays(int daysToSearch) {
		return orderDao.getOrdersDays(daysToSearch);
	}
	public List<Order> getOrdersLimit(int daysToSearch, String username) {
		return orderDao.getOrdersLimit(daysToSearch, username);
	}	
	public List<Order> getOrdersFilter(HashMap<QueryField, Object> map, int daysToSearch) {
		return orderDao.getOrdersFilter(map, daysToSearch);
	}
	
	public Transaction getTransaction(int transactionId) {
		return transactionDao.getTransaction(transactionId);
	}
	
	public List<Map<String,Object>> getCorporateInvoiceCriminalMonth(Integer corporateAccountId, Integer month, Integer year) {
		return orderDao.getCorporateInvoiceCriminalMonth(corporateAccountId, month, year);
	}
	
	public List<Map<String,Object>> getCorporateInvoiceBJLMonth(Integer corporateAccountId, Integer month, Integer year) {
		return orderDao.getCorporateInvoiceBJLMonth(corporateAccountId, month, year);
	}
	public List<Map<String, Object>> getCorporateInvoiceFPMonth(
			Integer corporateAccountId, Integer month, Integer year) {
		return orderDao.getCorporateInvoiceFPMonth(corporateAccountId, month, year);
	}
	public List<Map<String, Object>> getFBGRbyuser(String username) {
		return orderDao.getFBGRbyuser(username);	
	}
	public List<Map<String, Object>> getFBGRusers() {
		return orderDao.getFBGRusers();
	}
	public List<Map<String, Object>> getTracker() {
		return orderDao.getTracker();
	}
	public List<Map<String, Object>> getCorporateInvoiceFlatRate(
			Integer corporateAccountId, Integer month, Integer year) {
		return orderDao.getCorporateInvoiceFlatRate(corporateAccountId, month, year);
	}
	
	public List<Map<String, Object>> getFRInvoiceBJLMonth(Integer userId,
			Integer month, Integer year) {
		return orderDao.getFRInvoiceBJLMonth(userId, month, year);
	}
	
	public List<Map<String, Object>> getFRInvoiceCriminalMonth(Integer userId,
			Integer month, Integer year) {
		return orderDao.getFRInvoiceCriminalMonth(userId, month, year);
	}
	public List<Map<String, Object>> getFRInvoiceFPMonth(Integer userId,
			Integer month, Integer year) {
		return orderDao.getFRInvoiceFPMonth(userId, month, year);
	}
	public List<Map<String, Object>> getFRInvoiceFlatRate(Integer userId,
			Integer month, Integer year) {
		return orderDao.getFRInvoiceFlatRate(userId, month, year);
	}

	public List<Map<String, Object>> getFreeFRusers() {
		return orderDao.getFreeFRusers();
	}
	public List<Map<String, Object>> getFreeFRbyuser(String username) {
		return orderDao.getFreeFRbyuser(username);	
	}
	
	public List<Map<String, Object>> getCorporateInvoiceTSMonth(
			Integer corporateAccountId, Integer month, Integer year) {
		// TODO Auto-generated method stub
		return orderDao.getCorporateInvoiceTSMonth(corporateAccountId, month, year);
	}
	public List<Map<String, Object>> getCorporateInvoiceCSMonth(
			Integer corporateAccountId, Integer month, Integer year) {
		// TODO Auto-generated method stub
		return orderDao.getCorporateInvoiceCSMonth(corporateAccountId, month, year);
	}
	@Override
	public List<Map<String, Object>> getCorporateInvoiceNSSMonth(
			Integer corporateAccountId, Integer month, Integer year) {
		// TODO Auto-generated method stub
		return orderDao.getCorporateInvoiceNSSMonth(corporateAccountId, month, year);
	}
}