package springapp.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import springapp.domain.BJLSearch;
import springapp.domain.Order;
import springapp.repository.OrderDao;
import springapp.service.search.OrdersManager;

@Repository("orderDao")
public class JdbcOrderDao extends AbstractJdbcDao implements OrderDao {  
	protected final int defaultDaysToSearch = 7;
    
	/*
	 * query functions
	 */
	public List<Order> getOrders() {
        return getOrdersDays(defaultDaysToSearch);
    } 
	public List<Order> getOrdersDays(int daysToSearch) {
		logger.info("Getting orders: " + daysToSearch);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("daysToSearch", Integer.toString(daysToSearch));
		//params.addValue("daysToSearch", daysToSearch);
        List<Order> orders = getSimpleJdbcTemplate().query(sqlStrings.get("sqlSelect"), new OrderMapper(), params);
        return orders;
    }
	public List<Order> getOrdersLimit(int daysToSearch, String username) {
		logger.info("Getting orders limit: " + daysToSearch + ":" + username);
		MapSqlParameterSource params = new MapSqlParameterSource();
		// bleh, this is a hack
		Date cutoffDate = new Date(java.lang.System.currentTimeMillis() - (86400 * 1000 * daysToSearch));
		//params.addValue("daysToSearch", daysToSearch);		
		params.addValue("cutoffDate", cutoffDate);
		params.addValue("username", username);
		this.getSimpleJdbcTemplate().getNamedParameterJdbcOperations();
        List<Order> orders = getSimpleJdbcTemplate().query(sqlStrings.get("sqlSelectOrdersLimit"), new OrderMapper(), params);
        return orders;
	}
	
	public List<Order> getOrdersFilter(HashMap<OrdersManager.QueryField, Object> map, int daysToSearch) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = "SELECT * FROM show_transactions WHERE true ";
		
		if (map.containsKey(OrdersManager.QueryField.USERNAME)) {
			sql += " AND username ILIKE :username ";
			params.addValue("username", map.get(OrdersManager.QueryField.USERNAME));
		}
		if (map.containsKey(OrdersManager.QueryField.EMAIL)) {
			sql += " AND email ILIKE :email ";
			params.addValue("email", map.get(OrdersManager.QueryField.EMAIL));
		}
		if (map.containsKey(OrdersManager.QueryField.LAST4)) {
			sql += " AND cc_last_digits = :last4 ";
			params.addValue("last4", map.get(OrdersManager.QueryField.LAST4));
		}
		if (map.containsKey(OrdersManager.QueryField.TRANSACTION)) {
			sql += " AND transaction_id = :transaction ";
			params.addValue("transaction", map.get(OrdersManager.QueryField.TRANSACTION));
		}
		// note difference in param handling here
		if (map.containsKey(OrdersManager.QueryField.AMOUNT)) {
			//BigDecimal amount = (BigDecimal) map.get(OrdersManager.QueryField.AMOUNT);			
			//sql += " AND cost = ' " + amount.toPlainString() + "' ";
			sql += " AND cost = :amount ";
			params.addValue("amount", map.get(OrdersManager.QueryField.AMOUNT).toString());
		}
		
		// bail out if someone tries to kill us with too many days and no filters.
		// actually we just silently set it to 7. the form will show an error message
		// anyway but this is good protection.
		if (map.size() == 0 && daysToSearch > 7) {
			logger.info("oops! no filters! resetting days to 7!");
			daysToSearch = 7;
		}
		Calendar cDate = Calendar.getInstance();
		cDate.setTimeInMillis(java.lang.System.currentTimeMillis());
		cDate.add(Calendar.DATE, -daysToSearch);
		sql += " AND create_date > :cutoffDate ";
		params.addValue("cutoffDate", cDate.getTime());		
		
		sql += " ORDER BY create_date DESC ";
		
		logger.info("Getting orders with filters: " + sql);
		logger.info("cutoffDate: " + cDate.getTime());
        List<Order> orders = getSimpleJdbcTemplate().query(sql, new OrderMapper(), params);
        return orders;
    }
	
	public List<Order> getBGCSearches(int daysToSearch, String username) {
		logger.info("Getting orders limit: " + daysToSearch + ":" + username);
		MapSqlParameterSource params = new MapSqlParameterSource();
		// bleh, this is a hack
		Date cutoffDate = new Date(java.lang.System.currentTimeMillis() - (86400 * 1000 * daysToSearch));
		//params.addValue("daysToSearch", daysToSearch);		
		params.addValue("cutoffDate", cutoffDate);
		params.addValue("username", username);
		this.getSimpleJdbcTemplate().getNamedParameterJdbcOperations();
        List<Order> orders = getSimpleJdbcTemplate().query(sqlStrings.get("getTransactions"), new OrderMapper(), params);
        return orders;
	}
	
	public BJLSearch getBJLSearch(int transactionId) {
		// basic row mapper
		//return getSimpleJdbcTemplate().queryForObject(sqlStrings.get("getBJLSearch"), new BJLSearchMapper(), transactionId);

		// this requires named jdbc template
		//return getSimpleJdbcTemplate().queryForObject(sqlStrings.get("getBJLSearch"), new BeanPropertyRowMapper(BJLSearch.class), transactionId);
		
		// test with just class
		return getSimpleJdbcTemplate().queryForObject(sqlStrings.get("getBJLSearch"), BJLSearch.class, transactionId);
	}
    
	public List<Map<String,Object>> getCorporateInvoiceCriminalMonth(Integer corporateAccountId, Integer month, Integer year) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("corporateAccountId", corporateAccountId);
		params.addValue("month", month);
		params.addValue("year", year);
        return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getCorporateInvoiceCriminalMonth"), params);
	}
	public List<Map<String,Object>> getCorporateInvoiceTSMonth(Integer corporateAccountId, Integer month, Integer year) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("corporateAccountId", corporateAccountId);
		params.addValue("month", month);
		params.addValue("year", year);
        return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getCorporateInvoiceTSMonth"), params);
	}
	
	public List<Map<String, Object>> getCorporateInvoiceCSMonth(
			Integer corporateAccountId, Integer month, Integer year) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("corporateAccountId", corporateAccountId);
		params.addValue("month", month);
		params.addValue("year", year);
		return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getCorporateInvoiceCSMonth"), params);
	}
	public List<Map<String,Object>> getCorporateInvoiceBJLMonth(Integer corporateAccountId, Integer month, Integer year) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("corporateAccountId", corporateAccountId);
		params.addValue("month", month);
		params.addValue("year", year);
        return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getCorporateInvoiceBJLMonth"), params);
	}

	@Override
	public List<Map<String, Object>> getCorporateInvoiceNSSMonth(
			Integer corporateAccountId, Integer month, Integer year) {
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("corporateAccountId", corporateAccountId);
		params.addValue("month", month);
		params.addValue("year", year);
		
		return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getCorporateInvoiceNSSMonth"), params);
	}
    private static class OrderMapper implements ParameterizedRowMapper<Order> {
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Order order = new Order();
        	order.setUsername(rs.getString("username"));
        	order.setEmail(rs.getString("email"));
        	order.setInvoiceNumber(rs.getInt("transaction_id"));
        	order.setSearchCategoryId(rs.getInt("search_category_id"));
        	order.setCost(rs.getString("cost"));
        	order.setStatus(rs.getString("status"));
        	order.setType(rs.getString("category_title"));
        	order.setSubtype(rs.getString("subcategory_title"));        	
        	order.setDate(rs.getTimestamp("create_date"));
        	return order;        	
        }
    }
    
	/*    
    private static class BJLSearchMapper implements ParameterizedRowMapper<BJLSearch> {
        public BJLSearch mapRow(ResultSet rs, int rowNum) throws SQLException {
        	BJLSearch s = new BJLSearch();
        	
        	s.setUserSearchId(rs.getString("user_search_id"));
        	s.setSearchCategoryId(rs.getString("search_category_id");
        	s.setSearchSubCategoryId(rs.getString("search_sub_category_id"));
        	s.setUserId(rs.getString("user_id"));
        	s.setTransactionId(rs.getString("transaction_id"));
        	s.setMatchCount(rs.getString("match_count"));
        	s.setCreateDate(rs.getString("create_date"));
        	s.setCompleted(rs.getString("completed"));
        	s.setSearchStatusId(rs.getString("username"));
        	s.setInvoiced(rs.getString("username"));
        	s.setName(rs.getString("username"));
        	s.setDefendant(rs.getString("username"));
        	s.setDefendantAddress(rs.getString("username"));
        	s.setDefendantCity(rs.getString("username"));
        	s.setDefendantState(rs.getString("username"));
        	s.setDefendantZipCode(rs.getString("username"));
        	s.setDefendantSsnTaxId(rs.getString("username"));
        	s.setPlaintiff(rs.getString("username"));
        	s.setAttorney(rs.getString("username"));
        	s.setCaseNumber(rs.getString("username"));
        	s.setFilingType(rs.getString("username"));
        	s.setStartingFilingDate(rs.getString("username"));
        	s.setEndingFilingDate(rs.getString("username"));
        	s.setStartingLiabilityAmount(rs.getString("username"));
        	s.setEndingLiabilityAmount(rs.getString("username"));
        	s.setCourtIdCode(rs.getString("username"));
        	s.setSearchType(rs.getString("username"));
        	s.setWhoIsSearchFor(rs.getString("username"));
        	s.setReferenceCode(rs.getString("username"));
        	
        	return s;          	      	
        }
   	}	 
	 */

	
	public List<Map<String, Object>> getCorporateInvoiceFPMonth(
			Integer corporateAccountId, Integer month, Integer year) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("corporateAccountId", corporateAccountId);
		params.addValue("month", month);
		params.addValue("year", year);
        return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getCorporateInvoiceFPMonth"), params);
	}
	public List<Map<String, Object>> getFreeFRusers() {
		return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getFreeFRusers"));
	}
	public List<Map<String, Object>> getFreeFRbyuser(String username) {
	    return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getFreeFRbyUser"), username);
	}
	public List<Map<String, Object>> getFBGRbyuser(String username) {
	    return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getFreeBGRbyUser"), username);
	}
	public List<Map<String, Object>> getFBGRusers() {
		return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getFreeBGRusers"));
	}
	public List<Map<String, Object>> getTracker() {
		return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getTracker"));
	}
	public List<Map<String, Object>> getCorporateInvoiceFlatRate(
			Integer corporateAccountId, Integer month, Integer year) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("corporateAccountId", corporateAccountId);
		params.addValue("month", month);
		params.addValue("year", year);
        return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getCorporateInvoiceFlatRate"), params);
	}

	//Flat Rate Invoice
	public List<Map<String,Object>> getFRInvoiceCriminalMonth(Integer userId, Integer month, Integer year) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		params.addValue("month", month);
		params.addValue("year", year);
        return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getFRInvoiceCriminalMonth"), params);
	}
	
	public List<Map<String,Object>> getFRInvoiceBJLMonth(Integer userId, Integer month, Integer year) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		params.addValue("month", month);
		params.addValue("year", year);
        return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getFRInvoiceBJLMonth"), params);
	}
	public List<Map<String, Object>> getFRInvoiceFPMonth(
			Integer userId, Integer month, Integer year) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		params.addValue("month", month);
		params.addValue("year", year);
        return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getFRInvoiceFPMonth"), params);
	}
	public List<Map<String, Object>> getFRInvoiceFlatRate(
			Integer userId, Integer month, Integer year) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		params.addValue("month", month);
		params.addValue("year", year);
        return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getFRInvoiceFlatRate"), params);
	}
	
	
}