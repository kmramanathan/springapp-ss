package springapp.service.billing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import net.searchsystems.limestone.BillingReportDetails;
import net.searchsystems.limestone.BillingReports;

import org.apache.log4j.Logger;
import org.apache.torque.TorqueException;
import org.apache.velocity.app.VelocityEngine;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import springapp.domain.CreditCard;
import springapp.domain.Rate;
import springapp.domain.Subscription;
import springapp.domain.Transaction;
import springapp.domain.User;
import springapp.repository.InvoiceDao;
import springapp.repository.SubscriptionDao;
import springapp.service.user.UserManager;

/**
 * Daily subscription processing.
 * 
 * @author judd
 *
 */
@Component("DailyBillingJob")
public class DailyBillingJob {
	public static enum DeactivateReason {
		CC_FAILURE, NO_REBILL
	};
	public enum TxnStatus {
		txnOk, txnDeclined, txnError
	};
	protected final Logger logger = Logger.getLogger(getClass());
	
	protected static final boolean DAILY_JOB_CC_TEST_MODE = false;
	protected static final BigDecimal BD_ZERO = new BigDecimal("0.00");
	
	protected static final String emailFromNoReply = "no-reply@searchsystems.net";
	
	protected static final String tplBillingReport = "springapp/service/billing/BillingReport.vm";
	protected static final String tplUserReceipt = "springapp/service/billing/UserReceipt.vm";
	protected static final String tplUserDeactCCFailure = "springapp/service/billing/UserDeactCCFailure.vm";
	protected static final String tplUserDeactNoRebill = "springapp/service/billing/UserDeactNoRebill.vm";
	protected static final String tplUserDeactGeneric = "springapp/service/billing/UserDeactGeneric.vm";
	
	@Autowired
	private UserManager userManager;
	@Autowired
	private RateManager rateManager;
	@Autowired
	private BillingManager billingManager;
	@Autowired
	private VelocityEngine velocityEngine;	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private SubscriptionDao subscriptionDao;
	
	public void init() {
		logger.info("actual job init");
		// access context here
	}

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.info("job started");
		runDailyBilling(false);
		logger.info("job finished");
	}
		
	protected void runDailyBilling(boolean testMode) throws JobExecutionException {
		BillingReports report = new BillingReports();
		report.setDateStarted(Calendar.getInstance().getTime());
		
		TreeMap<Integer,TxnStatus> txns = new TreeMap<Integer,TxnStatus>();
		TreeMap<TxnStatus,BigDecimal> amounts = new TreeMap<TxnStatus,BigDecimal>();
		
		ArrayList<Integer> txnOk = new ArrayList<Integer>();
		ArrayList<Integer> txnDecline = new ArrayList<Integer>();
		ArrayList<Integer> txnFail = new ArrayList<Integer>();
		BigDecimal amountOk = new BigDecimal("0.00");
		BigDecimal amountDecline = new BigDecimal("0.00");
		BigDecimal amountFail = new BigDecimal("0.00");

		// Deactivate any subs where rebill is set to false
		// should this process send emails?
		List<Integer> subsNoRebill = subscriptionDao.getInactiveSubIdsForNoRebill();
		for (Integer subId : subsNoRebill) {
			Subscription s = subscriptionDao.getSubscriptionBySubscriptionId(subId);
			User u = userManager.getUserByUserId(s.getUserId());
			if (testMode) {
				logger.info("TEST: deact user/sub: " + u.getUserId() + "/" + s.getSubscriptionId());
			} else {
				deactivateUser(u, s, DeactivateReason.NO_REBILL, testMode);
			}
		}
		logger.info("Non-rebilling subs deactivated: " + subsNoRebill.size());
		report.setSubsDeactivated(subsNoRebill.size());
		
		// 1) Find subscriptions whose bill date is due (next_bill <= now)
		List<Integer> subs = subscriptionDao.getSubscriptionIdsDueForBilling();
		
		if (subs.size() == 0) {
			logger.info("No subs to bill, bailing out");			
		} else {
			// 2) Bill/advance, or record failure
			logger.info("Subs to bill: " + subs.size());			
			report.setSubsProcessed(subs.size());
			for (Integer subId : subs) {
				Subscription sub = subscriptionDao.getSubscriptionBySubscriptionId(subId);
				Rate rate = rateManager.getRate(sub.getRateId());
				Integer userId = sub.getUserId();
				
				User user = userManager.getUserByUserId(userId);				
				CreditCard cc = userManager.getCreditCard(userId);
				
				// if the recurring price is zero, just advance the dates and continue
				if (rate.getRecurringPrice().compareTo(BD_ZERO) == 0) {
					if (testMode) {
						logger.info("TEST: ZERO PRICE: user/sub/date: " + user.getUserId() + "/" + sub.getSubscriptionId() + "/" + sub.getNextBillDate());
					} else {
						logger.info("ZERO PRICE: user/sub/date: " + user.getUserId() + "/" + sub.getSubscriptionId() + "/" + sub.getNextBillDate());
						billingManager.advanceSubscription(sub);						
					}
					continue;
				}
				
				// LAST TEST MODE BAILOUT
				// for test, just log the info and continue, we dont want a billing
				// report with this data
				if (testMode) {
					logger.info("TEST: bill user/sub/date: " + user.getUserId() + "/" + sub.getSubscriptionId() + "/" + sub.getNextBillDate());
					continue;
				}
				
				// does the CC exist? this will fail if cc is null
				Transaction t;
				String signupFor = "DBJ";
				if (cc == null) {
					logger.info("NO CC ON FILE: user/sub: " + user.getUserId() + "/" + sub.getSubscriptionId());
					t = new Transaction();
					t.setTransactionStatusId((short) BillingManager.txnError);
				} else {
					t = billingManager.billSubscription(user, cc, sub, signupFor ,DAILY_JOB_CC_TEST_MODE);
				}
				
				// 3) handle billing result
				if (t.getTransactionStatusId() == BillingManager.txnApproved) {
					logger.info("Billing OK for sub " + subId + " : txn " + t.getTransactionId());
					
					// 1a) advance sub
					billingManager.advanceSubscription(sub);

					// 1b) send email
					HashMap<String,Object> map = new HashMap<String,Object>();
					map.put("invoiceNumber", t.getTransactionId());
					map.put("description", rate.getRateDescription());
					map.put("amount", t.getCost());
					map.put("customerName", user.getFirstName() + " " + user.getLastName());
					map.put("customerEmail", user.getEmail());
					sendReceiptEmail(map, user.getEmail(), t);
					
					// 1c) update counts
					//txns.put(t.getTransactionId(), TxnStatus.txnOk);
					txnOk.add(t.getTransactionId());
					amountOk = amountOk.add(new BigDecimal(t.getCost()));

				} else if (t.getTransactionStatusId() == BillingManager.txnDeclined) {
					logger.info("Billing declined for sub " + subId);

					// 2a) deactivate user (this sends email also)
					deactivateUser(user, sub, DeactivateReason.CC_FAILURE, testMode);
					
					// 2b) update counts
					//txns.put(t.getTransactionId(), TxnStatus.txnDeclined);
					txnDecline.add(t.getTransactionId());
					amountDecline = amountDecline.add(new BigDecimal(t.getCost()));
					
				} else {
					logger.error("Billing FAILED for sub " + subId);

					// XXX we may need to report this as a problem somehow,
					// but some errors like "card expired" should be considered 
					// declines instead.
					// 3a) deactivate user (this sends email also)
					deactivateUser(user, sub, DeactivateReason.CC_FAILURE, testMode);
					
					// 3b) update counts
					//txns.put(t.getTransactionId(), TxnStatus.txnError);
					txnFail.add(t.getTransactionId());
					amountFail = amountFail.add(new BigDecimal(t.getCost()));
				}
			}
		}

		amounts.put(TxnStatus.txnOk, amountOk);
		amounts.put(TxnStatus.txnDeclined, amountDecline);
		amounts.put(TxnStatus.txnError, amountFail);
		if (testMode) {
			logger.info("TEST: report: ok/decline: " + txnOk.size() + "/" + txnDecline.size());
		} else {
			saveBillingReport(report, txnOk, txnDecline, txnFail, amounts);
		}
		
		logger.info("All done. Sleeping...");
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			// ignore it
		}
	}
	
	protected void saveBillingReport(BillingReports report, 
			ArrayList<Integer> txnOk,
			ArrayList<Integer> txnDecline,
			ArrayList<Integer> txnFail,
			TreeMap<TxnStatus, BigDecimal> amounts) {
		
		// 4) Prepare report from counts
		report.setDateFinished(Calendar.getInstance().getTime());
		report.setTxnOk(txnOk.size());
		report.setTxnDecline(txnDecline.size());
		report.setTxnFail(txnFail.size());
		report.setAmountOk(amounts.get(TxnStatus.txnOk));
		report.setAmountDecline(amounts.get(TxnStatus.txnDeclined));
		report.setAmountFail(amounts.get(TxnStatus.txnError));
		
		try {
			report.save();			
		} catch (TorqueException e) {
			// XXX this is a failure
			logger.error("Failed to save billing report header", e);
		}
	
		// 5) Save report details
		ArrayList<Integer> txnAll = new ArrayList<Integer>();
		txnAll.addAll(txnOk);
		txnAll.addAll(txnDecline);
		txnAll.addAll(txnFail);

		for (Integer i : txnAll) {
			try {
				BillingReportDetails d = new BillingReportDetails();
				d.setBillingReportId(report.getBillingReportId());
				d.setTransactionId(i);
				d.save();
			} catch (TorqueException e) {
				logger.error("Failed to save billing report detail", e);
			}
		}			

	}
	
	protected void deactivateUser(User u, Subscription s, DeactivateReason reason, boolean testMode) {
		if (testMode) {
			logger.info("TEST: deact user/sub: " + u.getUserId() + "/" + s.getSubscriptionId());		
			return;
		}
		
		// set active to false for user & sub
		u.setInactive(true);
		userManager.saveUser(u);
		
		s.setActive(false);
		subscriptionDao.updateSubscriptionActive(s);
		
		// send email to user
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("u", u);
		sendDeactivationEmail(map, u.getEmail(), reason);
	}

	protected void sendReportEmail(HashMap<String,Object> map, String emailTo) {
		String tmpl = tplBillingReport;
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(emailFromNoReply);
		msg.setSubject("Search Systems Billing Report");
		msg.setTo(emailTo);
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tmpl, map);
		msg.setText(text);
		try {
			mailSender.send(msg);
		} catch (Exception e) {
			logger.error("Problem sending email", e);
		}
	}
	
	protected void sendReceiptEmail(HashMap<String,Object> map, String emailTo, Transaction t) {
		String tmpl = tplUserReceipt;
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(emailFromNoReply);
		msg.setSubject("Search Systems Receipt");
		msg.setTo(emailTo);
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tmpl, map);
		msg.setText(text);
		try {
			logger.info("Sent receipt to: " + emailTo);
			mailSender.send(msg);
		} catch (Exception e) {
			logger.error("Problem sending email", e);
		}
	}
	
	protected void sendDeactivationEmail(HashMap<String,Object> map, String emailTo, DeactivateReason reason) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(emailFromNoReply);
		msg.setSubject("Search Systems Account Notice");
		msg.setTo(emailTo);
		
		String tmpl;
		switch (reason) {
		case CC_FAILURE:
			tmpl = tplUserDeactCCFailure;
			break;
		case NO_REBILL:
			tmpl = tplUserDeactNoRebill;
			break;
		default:
			tmpl = tplUserDeactGeneric;
			break;
		}
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tmpl, map);
		
		msg.setText(text);
		try {
			mailSender.send(msg);
			logger.info("Sent deact notice to: " + emailTo);
		} catch (Exception e) {
			logger.error("Problem sending email", e);
		}		
	}
}