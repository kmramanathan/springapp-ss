package springapp.repository.jdbc;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedSingleColumnRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import springapp.domain.Invoice;
import springapp.domain.InvoiceDetail;
import springapp.repository.InvoiceDao;

@Repository("invoiceDao")
public class JdbcInvoiceDao extends AbstractJdbcDao implements InvoiceDao {	
	protected ParameterizedRowMapper<Invoice> getInvoiceMapper() {
		return ParameterizedBeanPropertyRowMapper.newInstance(Invoice.class);
	}
	protected ParameterizedRowMapper<InvoiceDetail> getInvoiceDetailMapper() {
		return ParameterizedBeanPropertyRowMapper.newInstance(InvoiceDetail.class);
	}
	/*
	public void saveRate(Invoice invoice) {
		if (invoice.isNew()) {
			// insert rate
			invoice.setInvoiceId(this.getSeqNextVal(sqlStrings.get("seqName")));
			this.createInvoice(invoice);
			invoice.setNew(false);
			invoice.setModified(false);
		} else {
			if (invoice.isModified()) {
				// update rate
				this.updateInvoice(invoice);
				invoice.setModified(false);
			}
		}
	}
	
	protected void createInvoice(Invoice invoice) {
		SimpleJdbcInsert ins = new SimpleJdbcInsert(this.getJdbcTemplate()).withTableName(sqlStrings.get("tableName"));
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(invoice);
		ins.execute(parameters);
	}
	
	protected void updateInvoice(Invoice invoice) {
		String sql = sqlStrings.get("updateInvoice");
		SqlParameterSource params = new BeanPropertySqlParameterSource(invoice);
		getSimpleJdbcTemplate().update(sql, params);
	}
	*/
	public Integer addInvoice(Invoice invoice) {
		SimpleJdbcInsert ins = new SimpleJdbcInsert(this.getJdbcTemplate())
			.withTableName(sqlStrings.get("tableName"))
			.usingGeneratedKeyColumns("invoice_id");
		SqlParameterSource params = new BeanPropertySqlParameterSource(invoice);
		invoice.setInvoiceId(ins.executeAndReturnKey(params).intValue());
		return invoice.getInvoiceId();
	}
	
	public Integer addInvoiceDetail(InvoiceDetail invoiceDetail) {
		SimpleJdbcInsert ins = new SimpleJdbcInsert(this.getJdbcTemplate())
			.withTableName(sqlStrings.get("tableNameDetails"))
			.usingGeneratedKeyColumns("invoice_detail_id");
		SqlParameterSource params = new BeanPropertySqlParameterSource(invoiceDetail);
		invoiceDetail.setInvoiceDetailId(ins.executeAndReturnKey(params).intValue());
		return invoiceDetail.getInvoiceDetailId(); 
	}
	
	public Invoice getInvoiceByInvoiceId(int invoiceId) {
		String sql = sqlStrings.get("selectInvoiceByInvoiceIdSimple");
    	try {  
	        return getSimpleJdbcTemplate().queryForObject(sql, getInvoiceMapper(), invoiceId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
	}

	public List<Invoice> getInvoicesByUserId(int userId) {
		String sql = sqlStrings.get("selectInvoicesByUserIdSimple");
		return getSimpleJdbcTemplate().query(sql, getInvoiceMapper(), userId);
	}

	public List<InvoiceDetail> getInvoiceDetailsByInvoiceId(int invoiceId) {
		String sql = sqlStrings.get("selectInvoiceDetailsByInvoiceIdSimple");
		return getSimpleJdbcTemplate().query(sql, getInvoiceDetailMapper(), invoiceId);
	}

	public List<Invoice> getUnpaidInvoicesForUser(int userId) {
		String sql = sqlStrings.get("selectUnpaidInvoicesByUserIdSimple");
		return getSimpleJdbcTemplate().query(sql, getInvoiceMapper(), userId);
	}

	public List<InvoiceDetail> getUnpaidInvoiceDetailsForUser(int userId) {
		String sql = sqlStrings.get("selectUnpaidInvoiceDetailsByUserIdSimple");
		return getSimpleJdbcTemplate().query(sql, getInvoiceDetailMapper(), userId);
	}
	
	public List<InvoiceDetail> getUnpaidInvoiceDetailsForInvoice(int invoiceId) {
		String sql = sqlStrings.get("selectUnpaidInvoiceDetailsByInvoiceIdSimple");
		return getSimpleJdbcTemplate().query(sql, getInvoiceDetailMapper(), invoiceId);
	}	

	public List<Integer> getUserIdsWithUnpaidInvoices() {
		String sql = sqlStrings.get("getUserIdsWithUnpaidInvoices");
		return getSimpleJdbcTemplate().query(sql, ParameterizedSingleColumnRowMapper.newInstance(Integer.class));
	}

	public void updateInvoiceAmounts(int invoiceId) {		
		String sql = sqlStrings.get("updateInvoiceAmountBilled");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("invoiceId", invoiceId);
		getSimpleJdbcTemplate().update(sql, params);
	}
}