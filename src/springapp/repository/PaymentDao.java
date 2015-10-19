package springapp.repository;

import java.util.List;

import springapp.domain.Payment;
import springapp.domain.PaymentDetail;

public interface PaymentDao {
    public Payment getPaymentByPaymentId(int invoiceId);
    public List<Payment> getPaymentsByUserId(int userId);
    
    public List<PaymentDetail> getPaymentDetailsByPaymentId(int invoiceId);
    
    public List<Payment> getUnappliedPaymentsForUser(int userId);
    public List<Integer> findUsersForApplyPayments();
}