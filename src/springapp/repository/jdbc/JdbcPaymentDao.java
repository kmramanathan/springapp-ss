package springapp.repository.jdbc;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedSingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import springapp.domain.Payment;
import springapp.domain.PaymentDetail;
import springapp.repository.PaymentDao;

@Repository("paymentDao")
public class JdbcPaymentDao extends AbstractJdbcDao implements PaymentDao {	
	protected ParameterizedRowMapper<Payment> getPaymentMapper() {
		return ParameterizedBeanPropertyRowMapper.newInstance(Payment.class);
	}
	protected ParameterizedRowMapper<PaymentDetail> getPaymentDetailMapper() {
		return ParameterizedBeanPropertyRowMapper.newInstance(PaymentDetail.class);
	}
	
	public Payment getPaymentByPaymentId(int invoiceId) {
		String sql = sqlStrings.get("selectPaymentByPaymentIdSimple");
    	try {  
	        return getSimpleJdbcTemplate().queryForObject(sql, getPaymentMapper(), invoiceId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
	}

	public List<Payment> getPaymentsByUserId(int userId) {
		String sql = sqlStrings.get("selectPaymentsByUserIdSimple");
		return getSimpleJdbcTemplate().query(sql, getPaymentMapper(), userId);
	}

	public List<PaymentDetail> getPaymentDetailsByPaymentId(int invoiceId) {
		String sql = sqlStrings.get("selectPaymentDetailsByPaymentIdSimple");
		return getSimpleJdbcTemplate().query(sql, getPaymentDetailMapper(), invoiceId);
	}
	
	public List<Payment> getUnappliedPaymentsForUser(int userId) {
		String sql = sqlStrings.get("getUnappliedPaymentsForUser");
		return getSimpleJdbcTemplate().query(sql, getPaymentMapper(), userId);
	}
	
	/**
	 * Get users who have unpaid invoices and unapplied payments.
	 * 
	 * @return list of user ids
	 * @throws SQLException
	 * 
	 * move to repository
	 */
	public List<Integer> findUsersForApplyPayments() {
		String sql = sqlStrings.get("findUsersForApplyPayments");
		return getSimpleJdbcTemplate().query(sql, ParameterizedSingleColumnRowMapper.newInstance(Integer.class));
	}
}