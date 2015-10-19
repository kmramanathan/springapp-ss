package springapp.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedSingleColumnRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springapp.domain.FRSubscription;
import springapp.domain.Subscription;
import springapp.repository.SubscriptionDao;

@Repository("subscriptionDao")
public class JdbcSubscriptionDao extends AbstractJdbcDao implements SubscriptionDao {
	protected ParameterizedRowMapper<Subscription> getMapper() {
		return ParameterizedBeanPropertyRowMapper.newInstance(Subscription.class);
	}
	
	protected ParameterizedRowMapper<FRSubscription> getFRMapper() {
		return ParameterizedBeanPropertyRowMapper.newInstance(FRSubscription.class);
	}

	public Subscription getSubscriptionBySubscriptionId(int subscriptionId) {
		String sql = sqlStrings.get("selectSubscriptionBySubscriptionId");
    	try {  
	        return getSimpleJdbcTemplate().queryForObject(sql, getMapper(), subscriptionId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
	}
	
	// simple check to see if the sub is actually due
	// should be useful to avoid messing with Java dates
	public Subscription getSubscription(int subscriptionId, boolean checkNextBill) {
		String sql;
		if (checkNextBill) {
			sql = sqlStrings.get("selectSubscriptionBySubscriptionIdCheckNextBill");
		} else {
			sql = sqlStrings.get("selectSubscriptionBySubscriptionId");
		}
		
    	try {  
	        return getSimpleJdbcTemplate().queryForObject(sql, getMapper(), subscriptionId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
	}

	public List<Subscription> getSubscriptionsByUserId(int userId) {
		String sql = sqlStrings.get("selectSubscriptionsByUserId"); 
		return getSimpleJdbcTemplate().query(sql, getMapper(), userId);
	}
	
	public List<Subscription> getSubscriptionsByUsername(String username) {
		String sql = sqlStrings.get("selectSubscriptionsByUsername"); 
		return getSimpleJdbcTemplate().query(sql, getMapper(), username);
	}
	
	// XXX this is bad, see interface description
//	public void saveSubscription(Subscription s) {
//		if (s.isNew()) {
//			// insert rate
//			s.setSubscriptionId(this.getSeqNextVal(sqlStrings.get("seqName")));
//			createSubscription(s);
//			s.setNew(false);
//			s.setModified(false);
//		} else {
//			if (s.isModified()) {
//				// update rate
//				updateSubscription(s);
//				s.setModified(false);
//			}
//		}
//	}
	
	@Transactional
	public void createSubscription(Subscription s) {
		SimpleJdbcInsert insertSubscription = new SimpleJdbcInsert(this.getJdbcTemplate())
			.withTableName(sqlStrings.get("tableName"));
		s.setSubscriptionId(this.getSeqNextVal(sqlStrings.get("seqName")));
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(s);
		insertSubscription.execute(parameters);
	}
	
	/* this doesn't really work right */	
//	protected void updateSubscription(Subscription s) {
//		String sql = sqlStrings.get("updateSubscription");
//		SqlParameterSource params = new BeanPropertySqlParameterSource(s);
//		getSimpleJdbcTemplate().update(sql, params);
//	}

	@Transactional
	public void updateSubscriptionActive(Subscription s) {
		String sql = sqlStrings.get("updateSubscriptionActive");
		SqlParameterSource params = new BeanPropertySqlParameterSource(s);
		getSimpleJdbcTemplate().update(sql, params);
	}

	@Transactional
	public void advanceSubscriptionBillDates(Subscription s, String interval) {
		String sql = sqlStrings.get("advanceSubscriptionBillDates");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("subscriptionId", s.getSubscriptionId());
		params.addValue("interval", interval);
		getSimpleJdbcTemplate().update(sql, params);	
	}
	
	// this includes various info related to subs
	// used for display pages
	public List<Map<String, Object>> getSubscriptionsDueForBilling() {
		return this.getSubscriptionsDueForBilling(0);
	}	
	public List<Map<String, Object>> getSubscriptionsDueForBilling(int daysFromNow) {
		String sql = sqlStrings.get("getSubscriptionsDueForBilling");
		String interval = daysFromNow + " days";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("interval", interval);
		return getSimpleJdbcTemplate().queryForList(sql, params);
	}
	public List<Map<String, Object>> getSubscriptionsDueForBilling(int daysFromNow, Integer rateId) {		
		String sql = sqlStrings.get("getSubscriptionsDueForBillingByRate");
		String interval = daysFromNow + " days";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("interval", interval);
		params.addValue("rateId", rateId);
		return getSimpleJdbcTemplate().queryForList(sql, params);
	}
	
	// summary data (rates & totals only)
	public List<Map<String, Object>> getSubscriptionSalesForecast(int daysFromNow) {
		String sql = sqlStrings.get("getSubscriptionSalesForecast");
		String interval = daysFromNow + " days";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("interval", interval);
		return getSimpleJdbcTemplate().queryForList(sql, params);
	}
	
	// this one just returns the sub ids
	// used for batch processes
	public List<Integer> getSubscriptionIdsDueForBilling() {
		return getSubscriptionIdsDueForBilling(0);
	}
	public List<Integer> getSubscriptionIdsDueForBilling(int daysFromNow) {
		String sql = sqlStrings.get("getSubscriptionIdsDueForBilling");
		String interval = daysFromNow + " days";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("interval", interval);
		return getSimpleJdbcTemplate().query(sql, ParameterizedSingleColumnRowMapper.newInstance(Integer.class), params);
	}
	
	// get list of subs that will be deactivated because rebill is false
	// this is used to send emails
	public List<Integer> getInactiveSubIdsForNoRebill() {
		return this.getInactiveSubIdsForNoRebill(0);
	}
	
	public List<Integer> getInactiveSubIdsForNoRebill(int daysFromNow) {
		String sql = sqlStrings.get("getInactiveSubIdsForNoRebill");
		String interval = daysFromNow + " days";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("interval", interval);
		return getSimpleJdbcTemplate().query(sql, ParameterizedSingleColumnRowMapper.newInstance(Integer.class), params);		
	}
	
	public List<Map<String, Object>> getNoRebillSubscriptionsSalesForecastSummary(int daysFromNow) {
		String sql = sqlStrings.get("getNoRebillSubscriptionsSalesForecastSummary");
		String interval = daysFromNow + " days";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("interval", interval);
		return getSimpleJdbcTemplate().queryForList(sql, params);		
	}
	public List<Map<String, Object>> getNoRebillSubscriptionsSalesForecastByRate(int daysFromNow, Integer rateId) {
		String sql = sqlStrings.get("getNoRebillSubscriptionsSalesForecastByRate");
		String interval = daysFromNow + " days";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("interval", interval);
		params.addValue("rateId", rateId);
		return getSimpleJdbcTemplate().queryForList(sql, params);		
	}
	
	// set subs inactive if due for rebill but rebill flag is false
	// we probably don't want to use this since it just hits them all
	// without notice
	@Transactional
	public int setInactiveForNoRebill() {
		String sql = sqlStrings.get("setInactiveForNoRebill");
		return getSimpleJdbcTemplate().update(sql);		
	}

	private static class FRSubscriptionMapper implements ParameterizedRowMapper<FRSubscription>{
		public FRSubscription mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			FRSubscription frsub = new FRSubscription();
			frsub.setSubscriptionid(rs.getInt("subscriptionid"));
			frsub.setUserid(rs.getInt("userid"));
			frsub.setPlanid(rs.getInt("planid"));
			frsub.setTransactionid(rs.getInt("transactionid"));
			frsub.setPlantypeid(rs.getInt("plantypeid"));
			frsub.setStartdate(rs.getTimestamp("startdate"));
			frsub.setPrevdate(rs.getTimestamp("prevdate"));
			frsub.setNextdate(rs.getTimestamp("nextdate"));
			frsub.setEnddate(rs.getTimestamp("enddate"));
			frsub.setAvailablesearches(rs.getInt("availablesearches"));
			frsub.setFindpeoplesearches(rs.getInt("findpeoplesearches"));
			frsub.setCriminalsearches(rs.getInt("criminalsearches"));
			frsub.setStatus(rs.getInt("status"));
			frsub.setDaysavailable(rs.getInt("daysavailable"));
			frsub.setCreatedate(rs.getTimestamp("createdate"));
		
			return frsub;
		}
		
	}

	public List<FRSubscription> getAllFRSubscriptionsByUserId(int userId) {
		String sql = sqlStrings.get("selectFRSubscriptionsByUserId"); 
		return getSimpleJdbcTemplate().query(sql, getFRMapper(), userId);
	}
	
	@Transactional
	public FRSubscription createFRSubscription(FRSubscription frsub) {
		java.util.Date today = new java.util.Date();
	    Timestamp createDate =  new java.sql.Timestamp(today.getTime());
		SimpleJdbcInsert insertSubscription = new SimpleJdbcInsert(this.getJdbcTemplate())
			.withTableName(sqlStrings.get("FRtableName"));
		frsub.setSubscriptionid(this.getSeqNextVal(sqlStrings.get("FRseqName")));
		frsub.setCreatedate(createDate);
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(frsub);
		int update = insertSubscription.execute(parameters);
		if(update == 1){
			return frsub;
		}
		return null;
	}

   public void deleteFRSubscription(int subId) {
	    String sql = sqlStrings.get("deleteFRSubscription");
		getSimpleJdbcTemplate().update(sql, subId);
	}

   public List<FRSubscription> getValidFRSubscriptions(int userId) {
	   String sql = sqlStrings.get("selectValidFRSubsByUserId"); 
	   return getSimpleJdbcTemplate().query(sql, getFRMapper(), userId);
	  
   }

   public boolean isFRUser(int userId) {
	   String sql = sqlStrings.get("isFRUser"); 
	   int count = this.getSimpleJdbcTemplate().queryForInt(sql, userId);
	   logger.info("count : "+count);
	   if(count >0)
		     return true;
	   else
		   return false;

   }
   public boolean isActiveFRUser(int userId) {
	   String sql = sqlStrings.get("isActiveFRUser"); 
	   int count = this.getSimpleJdbcTemplate().queryForInt(sql, userId);
	   logger.info("count : "+count);
	   if(count >0)
		     return true;
	   else
		   return false;

   }
   public List<FRSubscription> getActiveFRSubscriptions(int userId) {
	   String sql = sqlStrings.get("selectActiveFRSubsByUserId"); 
	   return getSimpleJdbcTemplate().query(sql, getFRMapper(), userId, userId);
   }
   public List<FRSubscription> getPipeFRSubscriptions(int userId) {
	   String sql = sqlStrings.get("selectPipeFRSubsByUserId"); 
	   return getSimpleJdbcTemplate().query(sql, getFRMapper(), userId, userId);
   }

   public void updateFRSubscriptionActive(FRSubscription frsub) {
	    String sql = sqlStrings.get("updateFRSubscriptionActive");
		SqlParameterSource params = new BeanPropertySqlParameterSource(frsub);
		this.getSimpleJdbcTemplate().update(sql, params);
		    
   }
   
   public int updateFPSearchCount(int subId) {
	   String sql = sqlStrings.get("updateFPAvailableSearch"); 
	   String sql2 = sqlStrings.get("selectFPAvailableSearch");
	   getSimpleJdbcTemplate().update(sql, subId);
	   return getSimpleJdbcTemplate().queryForInt(sql2, subId);
   }
   
   public int updateBGCSearchCount(int subId) {
	   String sql = sqlStrings.get("updateBGCAvailableSearch"); 
	   String sql2 = sqlStrings.get("selectBGCAvailableSearch");
	   getSimpleJdbcTemplate().update(sql, subId);
	   return getSimpleJdbcTemplate().queryForInt(sql2, subId);
   }
   
   public int getPlanTypeIdBySubId(int subId) {
	   String sql = sqlStrings.get("selectPlanTypeIdBySubId");
	   return getSimpleJdbcTemplate().queryForInt(sql, subId);
   }

   public boolean isExpired(int subId) {
	   String sql = sqlStrings.get("isFRExpired");
	   int status = getSimpleJdbcTemplate().queryForInt(sql, subId);
	   if(status == 3)
		   return true;
	   else
		   return false;
   }

   public List<FRSubscription> updateFROnExpireSubs() {
	   String sql = sqlStrings.get("selectOnExpireySubscriptions");
	   String sql2 = sqlStrings.get("updateOnExpireySubscriptions"); 
	   List<FRSubscription> frList = getSimpleJdbcTemplate().query(sql, getFRMapper());
	   getSimpleJdbcTemplate().update(sql2);
	   return frList;
   }

   public List<FRSubscription> getFRDailyExpireSubs() {
	   String sql = sqlStrings.get("selectFRDailyExpireySubscriptions");
	   List<FRSubscription> frList = getSimpleJdbcTemplate().query(sql, getFRMapper());
	   return frList;
   }
   
   public FRSubscription getSubscriptionByTxnId(int txnId) {
	   String sql = sqlStrings.get("selectFRSubscriptionByTxnId");
	   return getSimpleJdbcTemplate().queryForObject(sql, getFRMapper(), txnId);
   }

   public void updateFRSubsPipeToActive(int subId, int days) {
	    String sql = sqlStrings.get("updateFRSubsPipeToActive");
		MapSqlParameterSource params = new MapSqlParameterSource();
		String validDays = days+ " day";
		params.addValue("validDays", validDays);
		params.addValue("subId", subId);
		this.getSimpleJdbcTemplate().update(sql, params);
	}

	public List<Map<String, Object>> getAllFRMonthlyUsers() {
		String sql = sqlStrings.get("selectFRMonthlySubscriptions");
		List<Map<String, Object>> userList = getSimpleJdbcTemplate().queryForList(sql, 0);
		return userList;
	}
	public FRSubscription getFRSubscriptionBysubId(int subId) {
		 String sql = sqlStrings.get("selectFRSubscriptionBySubId");
		 return getSimpleJdbcTemplate().queryForObject(sql, getFRMapper(), subId);
	}
	public void updateFRSubscription(FRSubscription frsub) {
		 String sql = sqlStrings.get("updateFRSubscriptionActive");
		 SqlParameterSource params = new BeanPropertySqlParameterSource(frsub);
		 this.getSimpleJdbcTemplate().update(sql, params);
	}

  
}




