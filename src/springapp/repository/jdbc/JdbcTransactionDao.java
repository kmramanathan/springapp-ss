package springapp.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import springapp.domain.Transaction;
import springapp.repository.TransactionDao;

@Repository("transactionDao")
public class JdbcTransactionDao extends AbstractJdbcDao implements TransactionDao { 
    public Transaction getTransaction(int transactionId) {    	
        Transaction txn = getSimpleJdbcTemplate().queryForObject(
        	sqlStrings.get("sqlSelect"),
			new TransactionMapper(),
			new Object[] { Integer.valueOf(transactionId) }
        );
		return txn;
    }

	public void saveTransaction(Transaction t) {
		if (t.isNew()) {
			// insert rate
			t.setTransactionId(this.getSeqNextVal(sqlStrings.get("seqName")));
			this.insertTransaction(t);
			t.setNew(false);
			t.setModified(false);
		} else {
			if (t.isModified()) {
				// update txn
				this.updateTransaction(t);
				t.setModified(false);
			}
		}
	}

	protected void updateTransaction(Transaction txn) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(txn);      
		int count = getSimpleJdbcTemplate().update(sqlStrings.get("sqlUpdate"), params);        
	}
	
	protected void insertTransaction(Transaction txn) {
		SimpleJdbcInsert insertTransaction = new SimpleJdbcInsert(this.getJdbcTemplate())
			.withTableName("ss_transactions")
			.usingColumns("transaction_id", "search_category_id", "search_sub_category_id", "transaction_status_id",
					"user_id", "cost", "email", 
					"cc_name", "cc_address1", "cc_address2", "cc_city", "cc_state", "cc_postal_code",
					"cc_phone", "cc_last_digits", "cc_exp_month", "cc_exp_year",
					"bank_response_code", "bank_response_reason_code", "bank_response_reason_text",
					"bank_response_subcode", "bank_approval_code", "bank_avs_result_code",
					"bank_transaction_code", "bank_cavv_response_code", "bank_cvv_response_code"
			);
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(txn);
        insertTransaction.execute(parameters);
	}
    
    private static class TransactionMapper implements ParameterizedRowMapper<Transaction> {
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Transaction t = new Transaction();
        	
        	t.setTransactionId(rs.getInt("transaction_id"));
        	t.setSearchCategoryId(rs.getShort("search_category_id"));
        	t.setSearchSubCategoryId(rs.getShort("search_sub_category_id"));
        	t.setTransactionStatusId(rs.getShort("transaction_status_id"));
        	t.setUserId(rs.getInt("user_id"));
        	t.setCost(rs.getString("cost"));
        	t.setEmail(rs.getString("email"));
        	t.setCcName(rs.getString("cc_name"));
        	t.setCcAddress1(rs.getString("cc_address1"));
        	t.setCcAddress2(rs.getString("cc_address2"));
        	t.setCcCity(rs.getString("cc_city"));
        	t.setCcState(rs.getString("cc_state"));
        	t.setCcPostalCode(rs.getString("cc_postal_code"));
        	t.setCcPhone(rs.getString("cc_phone"));
        	t.setCcLastDigits(rs.getShort("cc_last_digits"));
        	t.setCcExpMonth(rs.getShort("cc_exp_month"));
        	t.setCcExpYear(rs.getShort("cc_exp_year"));
        	t.setCreateDate(rs.getTimestamp("create_date"));
        	
        	t.setBankResponseCode(rs.getString("bank_response_code"));
        	t.setBankResponseSubcode(rs.getString("bank_response_reason_code"));
        	t.setBankResponseReasonCode(rs.getString("bank_response_reason_code"));
        	t.setBankResponseReasonText(rs.getString("bank_response_reason_text"));
        	
        	t.setBankApprovalCode(rs.getString("bank_approval_code"));
        	t.setBankAvsResultCode(rs.getString("bank_avs_result_code"));
        	t.setBankTransactionCode(rs.getString("bank_transaction_code"));
        	t.setBankCavvResponseCode(rs.getString("bank_cavv_response_code"));
        	t.setBankCvvResponseCode(rs.getString("bank_cvv_response_code"));

        	return t;
        }
    }
}