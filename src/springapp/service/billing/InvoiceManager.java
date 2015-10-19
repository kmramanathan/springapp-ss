package springapp.service.billing;

import java.util.List;

import springapp.domain.Invoice;

import net.searchsystems.limestone.BillingReportDetails;
import net.searchsystems.limestone.BillingReports;
import net.searchsystems.limestone.SsTransactions;

public interface InvoiceManager {
	public List<BillingReports> getAllBillingReports();
	public BillingReports getBillingReport(int reportId);
	public List<BillingReportDetails> getBillingReportDetails(int reportId);
	public List<SsTransactions> getBillingReportTransactions(int reportId);
	
	public void deleteBillingReport(int reportId);
	
	public Invoice runInvoiceForSubscription(int subscriptionId);
}