package springapp.repository.jdbc;

import java.util.List;

import net.searchsystems.limestone.SsTimePeriods;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import springapp.domain.Rate;
import springapp.repository.RateDao;

@Repository("rateDao")
public class JdbcRateDao extends AbstractJdbcDao implements RateDao {
	protected ParameterizedRowMapper<Rate> getRateMapper() {
		return ParameterizedBeanPropertyRowMapper.newInstance(Rate.class);
	}

	protected ParameterizedRowMapper<SsTimePeriods> getMapperTimePeriod() {
		return ParameterizedBeanPropertyRowMapper.newInstance(SsTimePeriods.class);
	}

	public List<Rate> getAllRates() {
    	return getAllRates(false);
    }
	
	public List<Rate> getAllRates(boolean includeInactive) {
		String sql;
		if (includeInactive) {
			sql = sqlStrings.get("selectAllRates");
		} else {
			sql = sqlStrings.get("selectActiveRates");
		}
		return getSimpleJdbcTemplate().query(sql, getRateMapper());
	}

	public Rate getRateByRateId(int rateId) {
		String sql = sqlStrings.get("selectRateByRateIdSimple");
    	try {  
    		
	        return getSimpleJdbcTemplate().queryForObject(sql, getRateMapper(), rateId);
	       
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
	}

	public void saveRate(Rate rate) {
		if (rate.isNew()) {
			// insert rate
			rate.setRateId(this.getSeqNextVal(sqlStrings.get("seqName")));
			this.createRate(rate);
			rate.setNew(false);
		} else {
			if (rate.isModified()) {
				// update rate
				this.updateRate(rate);
				rate.setModified(false);
			}
		}
	}
	
	protected void createRate(Rate rate) {
		SimpleJdbcInsert insertRate = new SimpleJdbcInsert(this.getJdbcTemplate()).withTableName(sqlStrings.get("tableName"));
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(rate);
		insertRate.execute(parameters);
	}
	
	protected void updateRate(Rate rate) {
		String sql = sqlStrings.get("updateRate");
		SqlParameterSource params = new BeanPropertySqlParameterSource(rate);
		getSimpleJdbcTemplate().update(sql, params);
	}
	
	public List<SsTimePeriods> getAllTimePeriods() {
		String sql = sqlStrings.get("selectAllTimePeriods");
		return getSimpleJdbcTemplate().query(sql, getMapperTimePeriod());
	}
	public SsTimePeriods getTimePeriod(Integer timePeriodId) {
		String sql = sqlStrings.get("selectTimePeriod");
		return getSimpleJdbcTemplate().queryForObject(sql, getMapperTimePeriod(), timePeriodId);
	}
    /*
	private static class RateMapper extends ParameterizedBeanPropertyRowMapper<Rate> {
        public Rate mapRow(ResultSet rs, int rowNum) throws SQLException {
            Rate rate = new Rate();
            
            rate.setModified(false);
            rate.setNew(false);
            
            rate.setRateId(rs.getInt("rate_id"));
            rate.setRateName(rs.getString("rate_name"));
            rate.setRateDescription(rs.getString("rate_description"));
            rate.setRateInternalDescription(rs.getString("rate_description"));
            
            rate.setInitialPeriod(rs.getInt("initial_period"));
            rate.setInitialPrice(rs.getBigDecimal("initial_price"));
            rate.setInitialUnits(rs.getInt("initial_units"));
            rate.setRecurringPeriod(rs.getInt("recurring_period"));
            rate.setRecurringPrice(rs.getBigDecimal("recurring_price"));
            rate.setRecurringUnits(rs.getInt("recurring_units"));
            
            rate.setActive(rs.getBoolean("active"));
            rate.setAllowNew(rs.getBoolean("allow_new"));
            rate.setRequireApproval(rs.getBoolean("require_approval"));
            rate.setBillInAdvance(rs.getBoolean("bill_in_advance"));
            
            return rate;
        }
    }   
    */
}