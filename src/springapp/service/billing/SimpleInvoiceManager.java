package springapp.service.billing;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.searchsystems.limestone.BillingReportDetails;
import net.searchsystems.limestone.BillingReportDetailsPeer;
import net.searchsystems.limestone.BillingReports;
import net.searchsystems.limestone.BillingReportsPeer;
import net.searchsystems.limestone.SsTimePeriods;
import net.searchsystems.limestone.SsTransactions;
import net.searchsystems.limestone.SsTransactionsPeer;

import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springapp.domain.Invoice;
import springapp.domain.InvoiceDetail;
import springapp.domain.Rate;
import springapp.domain.Subscription;
import springapp.repository.InvoiceDao;
import springapp.repository.SubscriptionDao;
import springapp.service.NeonService;
import springapp.service.user.UserManager;

@Service("invoiceManager")
public class SimpleInvoiceManager extends NeonService implements InvoiceManager {
	@Autowired
	private UserManager userManager;
	@Autowired
	private RateManager rateManager;
	@Autowired
	private InvoiceDao invoiceDao;
	@Autowired
	private SubscriptionDao subscriptionDao;
	
	public List<BillingReports> getAllBillingReports() {
		Criteria crit = new Criteria();
		crit.addDescendingOrderByColumn(BillingReportsPeer.BILLING_REPORT_ID);
		try {
			return BillingReportsPeer.doSelect(crit);
		} catch (TorqueException e) {
			throw new InvoiceManagerException(e);
		}		
	}
	
	public BillingReports getBillingReport(int reportId) throws InvoiceManagerException {
		try {
			return BillingReportsPeer.retrieveByPK(reportId);
		} catch (NoRowsException e) {
			throw new InvoiceManagerException(e);
		} catch (TooManyRowsException e) {
			throw new InvoiceManagerException(e);
		} catch (TorqueException e) {
			throw new InvoiceManagerException(e);
		}
	}

	public List<BillingReportDetails> getBillingReportDetails(int reportId) {
		try {
			BillingReports report = BillingReportsPeer.retrieveByPK(reportId);
			return report.getBillingReportDetailss();
		} catch (NoRowsException e) {
			throw new InvoiceManagerException(e);
		} catch (TooManyRowsException e) {
			throw new InvoiceManagerException(e);
		} catch (TorqueException e) {
			throw new InvoiceManagerException(e);
		}
	}

	public List<SsTransactions> getBillingReportTransactions(int reportId) {
		try {
			//BillingReports report = BillingReportsPeer.retrieveByPK(reportId);
			Criteria c = new Criteria();
			c.add(BillingReportDetailsPeer.BILLING_REPORT_ID, reportId);
			c.addJoin(SsTransactionsPeer.TRANSACTION_ID, BillingReportDetailsPeer.TRANSACTION_ID, Criteria.INNER_JOIN);			
			return SsTransactionsPeer.doSelect(c);
		} catch (NoRowsException e) {
			throw new InvoiceManagerException(e);
		} catch (TooManyRowsException e) {
			throw new InvoiceManagerException(e);
		} catch (TorqueException e) {
			throw new InvoiceManagerException(e);
		}
	}
	
	public void deleteBillingReport(int reportId) {		
		Criteria c;		
		try {
			// clear the entries from the link table
			c = new Criteria();
			c.add(BillingReportDetailsPeer.BILLING_REPORT_ID, reportId);
			BillingReportDetailsPeer.doDelete(c);

			// now delete the report
			c = new Criteria();
			c.add(BillingReportsPeer.BILLING_REPORT_ID, reportId);
			BillingReportsPeer.doDelete(c);
		} catch (TorqueException e) {
			throw new InvoiceManagerException(e);
		}
	}
	
	@Transactional
	public Invoice runInvoiceForSubscription(int subscriptionId) {
		// get sub & rate info
		// verify that sub is due to be run
		Subscription s = subscriptionDao.getSubscription(subscriptionId, true);
		if (s == null) {
			return null;
		}
		
		Rate r = rateManager.getRate(s.getRateId());
		
		// if rate is bill in advance, we need to advance the sub dates before
		// creating an invoice. this way if the invoice fails, the sub will be
		// rolled back.
		// NOTE: this won't work if you mix Torque & Spring JDBC
		String interval;
		SsTimePeriods tp;
		BigDecimal amount;
		
		if ((r.getInitialUnits() > 0) && s.getInitDate().equals(s.getNextBillDate())) {
			// do initial bill
			tp = rateManager.getTimePeriod(r.getInitialPeriod());
			interval = r.getInitialUnits() + " " + tp.getTimePeriodName();	
			amount = r.getInitialPrice();
		} else {
			// do recurring bill
			tp = rateManager.getTimePeriod(r.getRecurringPeriod());
			interval = r.getRecurringUnits() + " " + tp.getTimePeriodName();	
			amount = r.getRecurringPrice();
		}	
		
		// advance the sub dates
		subscriptionDao.advanceSubscriptionBillDates(s, interval);
		
		// refresh the sub object
		s = subscriptionDao.getSubscriptionBySubscriptionId(subscriptionId);		
		
		// create invoice & detail
		BigDecimal bdZero = new BigDecimal("0.00");
		Calendar cal = Calendar.getInstance();
		Date dateNow = cal.getTime();
		
		Invoice i = new Invoice();		
		i.setUserId(s.getUserId());
		i.setInvoiceBillDate(dateNow);
		i.setInvoiceDueDate(dateNow);
		i.setAmountBilled(bdZero);
		i.setAmountPaid(bdZero);
		invoiceDao.addInvoice(i);

		InvoiceDetail detail = new InvoiceDetail();
		detail.setInvoiceId(i.getInvoiceId());
		detail.setItemPrice(amount);
		detail.setItemQuantity(1);
		detail.setTotalPrice(amount);
		detail.setItemName(r.getRateName());
		detail.setItemDescription(r.getRateDescription());
		detail.setDisputed(false);
		detail.setItemStartDate(s.getPrevBillDate());
		detail.setItemEndDate(s.getNextBillDate());
		detail.setAmountPaid(bdZero);		
		invoiceDao.addInvoiceDetail(detail);		
		
		// update & re-fetch before returning
		invoiceDao.updateInvoiceAmounts(i.getInvoiceId());
		return invoiceDao.getInvoiceByInvoiceId(i.getInvoiceId());
	}
}