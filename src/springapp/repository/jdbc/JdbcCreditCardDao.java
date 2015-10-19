package springapp.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import springapp.domain.CreditCard;
import springapp.repository.CreditCardDao;

@Repository("creditCardDao")
public class JdbcCreditCardDao extends AbstractJdbcDao implements CreditCardDao {     
	@Autowired
	protected DataSource dataSource;

    public CreditCard getCreditCard(int userId) {		
		try {    		
	        CreditCard card = getSimpleJdbcTemplate().queryForObject(sqlStrings.get("sqlSelect"), new CreditCardMapper(), userId);
			return card;
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
    }
    
    public List<Map<String, Object>> getCreditCardsForUser(int userId) {    	
        List<Map<String, Object>> card = getSimpleJdbcTemplate().queryForList(sqlStrings.get("sqlSelect"), new CreditCardMapper()        );
		return card;
    }

	public void saveCreditCard(CreditCard card) {
		if (card.isNew()) {
			// no sequence for this table
			insertCreditCard(card);
			card.setNew(false);
			card.setModified(false);
		} else {
			if (card.isModified()) {
				// update rate
				updateCreditCard(card);
				card.setModified(false);
			}
		}		
	}
	
	public List<Map<String,Object>> getModifiedCards() {
		return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getModifiedCards"));		
	}
	
	public List<Map<String,Object>> getExpiredCards() {
		Map<String,Integer> map = new HashMap<String,Integer>();
		Calendar now = Calendar.getInstance();
		map.put("expYear", now.get(Calendar.YEAR));
		map.put("expMonth", now.get(Calendar.MONTH));		
		SqlParameterSource params = new MapSqlParameterSource(map);
		return getSimpleJdbcTemplate().queryForList(sqlStrings.get("getExpiredCards"), params);		
	}
	
	protected void updateCreditCard(CreditCard card) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(card);        
		int count = getSimpleJdbcTemplate().update(sqlStrings.get("sqlUpdate"), params);        
	}
	
	protected void insertCreditCard(CreditCard card) {
		SimpleJdbcInsert insertCreditCard = new SimpleJdbcInsert(this.getJdbcTemplate())
			.withTableName("ss_credit_cards")
			.usingColumns("user_id", "country_id", 
					"name", "company", "address1", "address2", "city", "state", "postal_code",					
					"phone", "fax", "number", "exp_month", "exp_year", "last_digits",
					"verified");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(card);
        insertCreditCard.execute(parameters);
	}	
    
    private static class CreditCardMapper implements ParameterizedRowMapper<CreditCard> {
        public CreditCard mapRow(ResultSet rs, int rowNum) throws SQLException {
        	CreditCard card = new CreditCard();
        	card.setNew(false);
        	card.setModified(false);
            
        	card.setUserId(rs.getInt("user_id"));
            card.setCountryId(rs.getShort("country_id"));
            
            card.setName(rs.getString("name"));
            card.setCompany(rs.getString("company"));
            card.setAddress1(rs.getString("address1"));
            card.setAddress2(rs.getString("address2"));
            card.setCity(rs.getString("city"));
            card.setState(rs.getString("state"));
            card.setPostalCode(rs.getString("postal_code"));
            card.setPhone(rs.getString("phone"));
            card.setFax(rs.getString("fax"));
            
            // XXX this is still encrypted here
            card.setNumber(rs.getBytes("number"));
            card.setExpMonth(rs.getShort("exp_month"));
            card.setExpYear(rs.getShort("exp_year"));
            card.setLastDigits(rs.getShort("last_digits"));
            
            card.setVerified(rs.getBoolean("verified"));
            // XXX pay close attention to this field
            card.setCardModified(rs.getBoolean("modified"));
            card.setSuspended(rs.getBoolean("suspended"));
            
        	return card;
        }
    }

}