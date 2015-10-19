package springapp.repository;

import java.util.List;

import springapp.domain.Invoice;
import springapp.domain.InvoiceDetail;

public interface InvoiceDao {
    public Invoice getInvoiceByInvoiceId(int invoiceId);
    public List<Invoice> getInvoicesByUserId(int userId);
    
    public List<InvoiceDetail> getInvoiceDetailsByInvoiceId(int invoiceId);
    
    public List<Invoice> getUnpaidInvoicesForUser(int userId);
    public List<InvoiceDetail> getUnpaidInvoiceDetailsForUser(int userId);	
    public List<InvoiceDetail> getUnpaidInvoiceDetailsForInvoice(int invoiceId);
    
	// convenience method to avoid creating a bunch of user objects
    public List<Integer> getUserIdsWithUnpaidInvoices();
    
    public Integer addInvoice(Invoice invoice);
    public Integer addInvoiceDetail(InvoiceDetail invoiceDetail);
    
    public void updateInvoiceAmounts(int invoiceId);
}