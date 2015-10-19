package springapp.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springapp.domain.AdminUserIPBean;
import springapp.domain.CorporateAccount;
import springapp.domain.FRSubscription;
import springapp.domain.User;
import springapp.repository.UserDao;
import springapp.service.user.FindUserResult;
import springapp.service.user.UserManager;

@Repository("userDao")
public class JdbcUserDao extends AbstractJdbcDao implements UserDao {
	protected ParameterizedRowMapper<User> getUserMapper() {
		//return ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		return new UserMapper();
	}
	
	protected ParameterizedRowMapper<AdminUserIPBean> getUserIPMapper() 
	{
		//return ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		return new UserIPMapper();
	}
   
	public User getUserByUserId(int userId) 
	{
    	String sql = sqlStrings.get("selectUserByUserIdSimple");
    	try {    		
	        return getSimpleJdbcTemplate().queryForObject(sql, getUserMapper(), userId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
    }
        
    public User getUserByUsername(String username) {
    	
    	return getUserByUsername(username, true);
    }
    
    // case-insensitive match for checking on signup 
    public List<User> getUsersByUsernameCaseInsensitive(String username) {
    	String sql = sqlStrings.get("selectUserByUsernameSimpleIlike");   	
    	return getSimpleJdbcTemplate().query(sql, getUserMapper(), username);    	
    }
    
    public User getUserByUsername(String username, Boolean caseSensitive) {
    	String sql;
    	if (caseSensitive) {
        	sql = sqlStrings.get("selectUserByUsernameSimple");    		
    	} else {
    		sql = sqlStrings.get("selectUserByUsernameSimpleIlike");
    	}
    	
    	try {    		
            return getSimpleJdbcTemplate().queryForObject(sql, getUserMapper(), username);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
    }
  
  //Udhay Dec10-2014   
    @Override 
    public AdminUserIPBean getUserWithIP(int userId) {
    	String sql = sqlStrings.get("selectUserIPByUserId");
    	try {    		
    		logger.info("User IP addrs SQLL: UsrID->:" + userId +" SqlQury:->>"+ sql);	
	        return getSimpleJdbcTemplate().queryForObject(sql, getUserIPMapper(), userId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
    }    

    /*
    public AdminUserIPBean getUserByUsernameWithIP(String username, Boolean caseSensitive) {
    	String sql;
    	if (caseSensitive) {
        	sql = sqlStrings.get("selectUserByUsernameSimple");    		
    	} else {
    		sql = sqlStrings.get("selectUserByUsernameSimpleIlike");
    	}
    	
    	try {    		
            return getSimpleJdbcTemplate().queryForObject(sql, getViewUserMapper(), username);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
    }
    public AdminUserIPBean getUserByUserIdWithIP(int userId) {
    	String sql = sqlStrings.get("selectUserByUserIdSimple");
    	try {    		
	        return getSimpleJdbcTemplate().queryForObject(sql, getViewUserMapper(), userId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
    }
    */
    //Ends
    
    // note: this will throw ex if multiple users!
    public User getUserByEmail(String email) {
    	String sql = sqlStrings.get("selectUserByEmailSimple");
    	try {    		
	        return getSimpleJdbcTemplate().queryForObject(sql, getUserMapper(), email);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
    }
    
  
    // safer version of above
    public List<User> getUsersByEmail(String email) {
    	String sql = sqlStrings.get("selectUserByEmailSimple");   	
    	return getSimpleJdbcTemplate().query(sql, getUserMapper(), email);    	
    }
    
    // not sure how useful this method really is
    public User getUser(User user, UserManager.QueryField field) {
        logger.info("Getting user!");               
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        
        String sql;
        switch (field) {
        case ID:
        	sql = sqlStrings.get("selectUserByUserId");
        	break;
        case USERNAME:
        	sql = sqlStrings.get("selectUserByUsername");
        	break;
        case FIRST_NAME:
        	sql = sqlStrings.get("selectUserByFirstName");
        	break;
        case LAST_NAME:
        	sql = sqlStrings.get("selectUserByLastName");
        	break;
        default:
        	throw new DataRetrievalFailureException("invalid query field");
        }
        
        try {
        	return getSimpleJdbcTemplate().queryForObject(sql, getUserMapper(), params);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
    }

    // note: this uses a different mapper 
    // note: we need to return a different object than just "User" here if we 
    // want to include address, phone, etc.
    public List<FindUserResult> findUsers(Map<String, String> fields, boolean matchAllFields) {
    	SqlParameterSource params = new MapSqlParameterSource(fields);
    	String sql = buildFindUsersSql(fields, matchAllFields);    	
    	return getSimpleJdbcTemplate().query(sql, new UserMapperFindUserResult(), params);    	
    }

    protected String buildFindUsersSql(Map<String, String> fields, boolean matchAllFields) {
    	final String conj;
        
    	// tricky stuff to handle the use of multiple possible parameters
    	// and different conjunctions
    	
    	String sql = sqlStrings.get("findUsersGeneric");
    	    	
        if (matchAllFields) {
    		conj = " AND ";
    		sql += " WHERE true ";
    	} else {
    		conj = " OR ";
    		sql += " WHERE false ";
    	}
        
        if (fields.get("username").length() > 0) {
        	sql += conj + " U.username ILIKE :username ";
        }
        if (fields.get("firstName").length() > 0) {
        	sql += conj + " ( U.first_name ILIKE :firstName OR CC.name ILIKE :billingName )";
        }
        if (fields.get("lastName").length() > 0) {
        	sql += conj + " ( U.last_name ILIKE :lastName OR CC.name ILIKE :billingName ) ";
        }
        if (fields.get("email").length() > 0) {
        	sql += conj + " U.email ILIKE :email ";
        }
        if (fields.get("last4").length() > 0) { 
        	// bleh, need to fix the db
        	// see: http://noc1.searchsystems.net/bugzilla/show_bug.cgi?id=12
        	String last4 = fields.get("last4");        	
        	if (last4.substring(0, 1).equals("0")) {
        		last4 = last4.substring(1);
        	}
        	//logger.info("last 4: last4");
        	sql += conj + " CAST(CC.last_digits as varchar) ILIKE '" + last4 + "'";
        }
        if (fields.get("address").length() > 0) {
        	sql += conj + " CC.address1 ILIKE :address ";
        }
        if (fields.get("phone").length() > 0) {
        	sql += conj + " CC.phone ILIKE :phone ";
        }
                
        return sql;
    }
    
    @Transactional
    public void saveUser(User user) {
		if (user.isNew()) {
			// insert rate
			user.setUserId(this.getSeqNextVal(sqlStrings.get("seqName")));
			this.insertUser(user);
			logger.info("insert user id"+user.getUserId());
			user.setNew(false);
			user.setModified(false);
		} else {
			if (user.isModified()) {
				// update rate
				this.updateUser(user);
				user.setModified(false);
			}
		}
	}
    
    @Transactional
    public int createUser(User user) {
    	saveUser(user);
    	return user.getUserId();
    }
    
    protected void updateUser(User user) {
		logger.info("Saving user: " + user.getUserId());		
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		int count = getSimpleJdbcTemplate().update(sqlStrings.get("updateUser"), params);        
        logger.info("Rows affected: " + count);
	}
    
    @Transactional
    public void changeUsername(User user) {
		logger.info("Changing username for user: " + user.getUserId());	
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		int count = getSimpleJdbcTemplate().update(sqlStrings.get("changeUsername"), params);        
        logger.info("Rows affected: " + count);
	}
    
    @Transactional
    public void changeUserPassword(User user) {
		logger.info("Changing password for user: " + user.getUserId());	
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		int count = getSimpleJdbcTemplate().update(sqlStrings.get("changeUserPassword"), params);        
        logger.info("Rows affected: " + count);
	}
    
    @Transactional
    public void changeFlags(User user) {
		logger.info("Changing flags for user: " + user.getUserId());	
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		int count = getSimpleJdbcTemplate().update(sqlStrings.get("changeFlags"), params);        
        logger.info("Rows affected: " + count);
	}
    
    public HashMap<Integer, String> getPassQuestions(boolean activeOnly) {
    	LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();    	
    	String sql;
    	List<Map<String,Object>> list;
    	if (activeOnly) {
    		sql = sqlStrings.get("getPassQuestionsActive");
    		list = getSimpleJdbcTemplate().queryForList(sql);
    	} else {
    		sql = sqlStrings.get("getPassQuestionsAll");
    		list = getSimpleJdbcTemplate().queryForList(sql);    		
    	}
    	for (Map<String,Object> m : list) {    		
    		Integer id = (Integer) m.get("pass_question_id");
    		String s = (String) m.get("question");
    		//logger.info("adding: " + id + ":" + s);
    		map.put(id, s);
    	}
    	return map;
    }

    public HashMap<Integer, String> getCountryISOCodesById() {
    	return getCountryISOCodesById(false);
    }
    
    public HashMap<Integer, String> getCountryISOCodesById(Boolean includeFullNames) {
    	LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
    	String sql = sqlStrings.get("getCountryCodes");
    	List<Map<String,Object>> list = getSimpleJdbcTemplate().queryForList(sql);
    	for (Map<String,Object> m : list) {    		
    		Integer id = (Integer) m.get("country_id");
    		String description = (String) m.get("code"); 
    		if (includeFullNames) {
    			description += " - " + (String) m.get("title");
    		}
    		
    		map.put(id, description);
    	}
    	return map;
    }    
    public HashMap<String, Integer> getCountryIdsByISOCode() {
    	LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
    	String sql = sqlStrings.get("getCountryCodes");
    	List<Map<String,Object>> list = getSimpleJdbcTemplate().queryForList(sql);
    	for (Map<String,Object> m : list) {    		
    		Integer id = (Integer) m.get("country_id");
    		String code = (String) m.get("code");
    		map.put(code, id);
    	}
    	return map;
    }
    
    public HashMap<String, String> getUSStates() {
    	return getUSStates(false);
    }
    public HashMap<String, String> getUSStates(boolean includeTerritories) {
    	LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
    	String sql;
    	if (includeTerritories) {
    		sql = sqlStrings.get("getUSStatesAndTerritories");
    	} else {
    		sql = sqlStrings.get("getUSStatesOnly");
    	}
    	List<Map<String,Object>> list = getSimpleJdbcTemplate().queryForList(sql);
    	for (Map<String,Object> m : list) {
    		String description = (String) m.get("code") + " - " + (String) m.get("name");
    		map.put((String) m.get("code"), description);
    	}
    	return map;
    }

	// short & simple
	// should work with hsql & postgres
    protected void insertUser(User user) {
    	SimpleJdbcInsert insertUser = new SimpleJdbcInsert(this.getJdbcTemplate())
			.withTableName("ss_users")
			.usingColumns("user_id", "first_name", "last_name", "username", "password",					
					"email", "pass_question_id", "pass_answer","inactive" ,
					"sub_interval", "sub_interval_unit", "sub_bill_period", "sub_start_date",
					"sub_directory_user", "no_credit_card",
					"no_export", "no_premium", "custom_account", "confirmed");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
        insertUser.execute(parameters);
	}
	
    
	public int createUserPsql(User user) {
		int userId = this.getSeqNextVal("ss_users_seq");
		user.setUserId(userId);		
		logger.info("Adding new user with id: " + userId);

		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(this.getJdbcTemplate());		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user.getUserId());
		params.put("first_name", user.getFirstName());
		params.put("last_name", user.getLastName());
		params.put("username", user.getUsername());
        int rowcount = insertActor.withTableName("ss_users").execute(params);
        logger.info("Added new user, rowcount: " + rowcount);
		return userId;
	}
	
	public int createUserHsqlSimple(User user) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(this.getJdbcTemplate());
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("first_name", user.getFirstName());
		params.put("last_name", user.getLastName());
		params.put("username", user.getUsername());
        int rowcount = insertActor.withTableName("users").execute(params);
        logger.info("Added new user, rowcount: " + rowcount);
		
		String sql = "call identity()";
		int userId = this.getJdbcTemplate().queryForInt(sql);
			
		logger.info("Adding new user with id: " + userId);
		return userId;
	}
	
	public int createUserHsql(User user) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("first_name", user.getFirstName());
		params.put("last_name", user.getLastName());
		params.put("username", user.getUsername());		
        
		String sql = "insert into users (first_name, last_name, username) " +
			"values " +
			"(:first_name, :last_name, :username) ";		
		int count = getSimpleJdbcTemplate().update(sql, params);
		
		sql = "call identity()";
		int userId = this.getJdbcTemplate().queryForInt(sql);
			
		logger.info("Adding new user with id: " + userId);
		return userId;
	}
	
	@Transactional
	public int deleteUser(User user) {
		logger.info("Deleting user: " + user.getUserId());		
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        int count;
		count = getSimpleJdbcTemplate().update(sqlStrings.get("deleteUserTransactions"), params);        
        logger.info("Txns deleted: " + count);
        count = getSimpleJdbcTemplate().update(sqlStrings.get("deleteUserSubscriptions"), params);        
        logger.info("Subs deleted: " + count);
		count = getSimpleJdbcTemplate().update(sqlStrings.get("deleteUserCreditCards"), params);        
        logger.info("CCs deleted: " + count);
		count = getSimpleJdbcTemplate().update(sqlStrings.get("deleteUser"), params);        
        logger.info("Users deleted: " + count); 
        return count;
	}
	
	// this will fail since it doesn't clean up the other stuff
	@Transactional
	public int deleteUserFake(User user) {
		logger.info("Deleting user: " + user.getUserId());		
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        int count;
		count = getSimpleJdbcTemplate().update(sqlStrings.get("deleteUser"), params);        
        logger.info("Users deleted: " + count); 
        return count;
	}
	
	public List<Map<String,Object>> getTestUsersForCleanup() {
		String sql = sqlStrings.get("getTestUsersForCleanup");
    	try {    		
	        return getSimpleJdbcTemplate().queryForList(sql);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
	}
	
	// corporate account stuff (uses ss_companies)
	public CorporateAccount getCorporateAccountById(int accountId) {
    	String sql = sqlStrings.get("getCorporateAccountById");
    	try {    		
	        return getSimpleJdbcTemplate().queryForObject(sql, getCorporateAccountMapper(), accountId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
	}
	
	public List<CorporateAccount> getAllCorporateAccounts() {
    	String sql = sqlStrings.get("getAllCorporateAccounts");
    	try {    		
	        return getSimpleJdbcTemplate().query(sql, getCorporateAccountMapper());
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
    }
	
	public CorporateAccount getCorporateAccountByUserId(int userId) {
		String sql = sqlStrings.get("getCorporateAccountByUserId");
    	try {    		
	        return getSimpleJdbcTemplate().queryForObject(sql, getCorporateAccountMapper(), userId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
	}

    public List<User> getCorporateAccountUsers(int accountId) {
    	String sql = sqlStrings.get("getCorporateAccountUsers");
    	try {    		
	        return getSimpleJdbcTemplate().query(sql, getUserMapper(), accountId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
    }
    
    public User getCorporateAccountUserByUserId(int accountId, int userId) {
    	String sql = sqlStrings.get("getCorporateAccountUserByUserId");
    	try {    		
	        return getSimpleJdbcTemplate().queryForObject(sql, getUserMapper(), accountId, userId);
        } catch (EmptyResultDataAccessException e) {        	
        	return null;
        }
    }
    
    @Transactional
    public void addUserToCorporateAccount(CorporateAccount acct, User user) {
		SimpleJdbcInsert insertUser = new SimpleJdbcInsert(this.getJdbcTemplate())
			.withTableName("ss_corporate_account_users")
			.usingColumns("user_id", "corporate_account_id");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", user.getUserId());
		parameters.addValue("corporateAccountId", acct.getCorporateAccountId());
	    insertUser.execute(parameters);
    }
    
    @Transactional
    public void addCorporateAccount(CorporateAccount acct) {
		SimpleJdbcInsert insertUser = new SimpleJdbcInsert(this.getJdbcTemplate())
			.withTableName("ss_corporate_accounts")
			.usingColumns("corporate_name", "contact_person", "phone", "fax",
					"address1", "address2", "city", "state", "zip", 
					"accounts_authorized", "accounts_inuse");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(acct);
	    insertUser.execute(parameters);
    }
    
    @Transactional
    public void updateCorporateAccount(CorporateAccount acct) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(acct);
		int count = getSimpleJdbcTemplate().update(sqlStrings.get("updateCorporateAccount"), params);        
        logger.info("Rows affected: " + count);
	}
    
    @Transactional
    public void deleteCorporateAccount(CorporateAccount acct) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(acct);
		int count = getSimpleJdbcTemplate().update(sqlStrings.get("deleteCorporateAccount"), params);        
        logger.info("Rows affected: " + count);
	}
    
    @Transactional
    public void removeUserFromCorporateAccount(CorporateAccount acct, User user) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", user.getUserId());
		parameters.addValue("corporateAccountId", acct.getCorporateAccountId());
		int count = getSimpleJdbcTemplate().update(sqlStrings.get("removeUserFromCorporateAccount"), parameters);        
        logger.info("Rows affected: " + count);
	}

    protected ParameterizedRowMapper<CorporateAccount> getCorporateAccountMapper() {
		return ParameterizedBeanPropertyRowMapper.newInstance(CorporateAccount.class);
	}
    
    private static class UserMapper implements ParameterizedRowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setModified(false);
            user.setNew(false);
            
            user.setUserId(rs.getInt("user_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            // XXX does using short break edit user? edit user uses integer,
            // maybe it is rejecting this      
            user.setPassQuestionId(rs.getShort("pass_question_id"));
            user.setPassAnswer(rs.getString("pass_answer"));
            
            user.setDisabled(rs.getBoolean("disabled"));
            user.setDisabledReason(rs.getString("disabled_reason"));
            user.setDisabledDate(rs.getTimestamp("disabled_date"));   
            user.setConfirmed(rs.getBoolean("confirmed"));
            user.setInactive(rs.getBoolean("inactive"));
            user.setCreateDate(rs.getTimestamp("create_date")); // changed by vivek 17-aug-2012
            user.setNewsletter(rs.getBoolean("newsletter"));

            user.setSubAmount(rs.getBigDecimal("sub_amount"));
            user.setSubDirectoryUser(rs.getBoolean("sub_directory_user"));            
            
            user.setNoPremium(rs.getBoolean("no_premium"));
            user.setCustomAccount(rs.getBoolean("custom_account"));
            user.setNoCreditCard(rs.getBoolean("no_credit_card"));
            user.setArgonUser(rs.getBoolean("argon_user"));
            user.setInternalUser(rs.getBoolean("internal_user"));            
            
            return user;
        }
    }
    
    //Udhay- Dec10-2014
    private static class UserIPMapper implements ParameterizedRowMapper<AdminUserIPBean> {
        public AdminUserIPBean mapRow(ResultSet rs, int rowNum) throws SQLException {
        	AdminUserIPBean user = new AdminUserIPBean();
            user.setUserId(rs.getInt("user_id"));
            user.setUsersIP(rs.getString("users_ip"));
            return user;
        }
    }
    
    
    private static class UserMapperCC extends UserMapper implements ParameterizedRowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = super.mapRow(rs, rowNum);
            user.setSubCcLastDigits(String.valueOf(rs.getShort("last_digits")));            
            return user;
        }
    }
    
    private static class UserMapperFindUserResult implements ParameterizedRowMapper<FindUserResult> {
        public FindUserResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        	FindUserResult r = new FindUserResult();
        	r.setUsername(rs.getString("username"));
        	r.setFirstName(rs.getString("first_name"));
        	r.setLastName(rs.getString("last_name"));
        	r.setEmail(rs.getString("email"));
        	r.setLast4(String.valueOf(rs.getShort("last_digits")));
        	r.setBillingName(rs.getString("billing_name"));
        	r.setAddress(rs.getString("address1"));
        	r.setPhone(rs.getString("phone"));  
        	r.setDirectPass(rs.getBoolean("sub_directory_user"));
        	r.setDisabled(rs.getBoolean("disabled"));
        	r.setInactive(rs.getBoolean("inactive"));
        	r.setExported(rs.getBoolean("sub_exported"));
        	r.setSuspended(rs.getBoolean("suspended"));
        	return r;        	
        }
    }

	//findpeople Teaser tracker
	public void updatefpBGCount() {
		String sql= sqlStrings.get("updateBGSalesCount");
    	try {    		
    		getSimpleJdbcTemplate().update(sql);
	        
        } catch (Exception e) {        	
        	logger.error("Findpeople BG Counter Failed :"+e.toString());
        }
			
	}
	public void updatefpDetailCount() {
		String sql= sqlStrings.get("updateFPSalesCount");
			
    	try {    		
    		getSimpleJdbcTemplate().update(sql);
	               
        } catch (Exception e) {        	
        	logger.error("Findpeople FP Counter Failed :"+e.toString());
        }
	
	}
	public void updatefpTeaserCount() {
		String sql= sqlStrings.get("updateFPTeaserCount");
			
    	try {    		
    		getSimpleJdbcTemplate().update(sql);
    
        } catch (Exception e) {        	
        	logger.error("Findpeople Teaser Counter Failed :"+e.toString());
        }		
	}
	public void updateFreeBGCount() {
		String sql= sqlStrings.get("updateFreeBGCount");
			
    	try {    		
    		getSimpleJdbcTemplate().update(sql);
        } catch (Exception e) {        	
        	logger.error("Findpeople FP Counter Failed :"+e.toString());
        }
		
	}
	

	//mexico user count
   
	public void updateSalesUserCount() {
		
		String sql = sqlStrings.get("selectSalesCount");
		String sql2= sqlStrings.get("updateSalesCount");
			
    	try {    		
    		logger.info("Entered inside jdbcDAO-sales");
	        int count=this.getSimpleJdbcTemplate().queryForInt(sql);
	        logger.info("Sales Teaser count from DB is ::"+count);
	        getSimpleJdbcTemplate().update(sql2, ++count);
	        logger.info("count"+count);
	        
        } catch (Exception e) {        	
        	logger.error(e.toString());
        }
		
	}
	public void updateTeaserUserCount() {
		String sql = sqlStrings.get("selectTeaserCount");
		String sql2= sqlStrings.get("updateTeaserCount");
			
    	try {    		
    		logger.info("Entered inside jdbcDAO");
	        int count=this.getSimpleJdbcTemplate().queryForInt(sql);
	        logger.info("Teaser count from DB is ::"+count);
	        getSimpleJdbcTemplate().update(sql2, ++count);
	        logger.info("the count"+count);
	        
        } catch (Exception e) {        	
        	logger.error(e.toString());
        }
		
	}


 //monthly billling for findpeople
	public void fpUserRequestImp(int userId, String product, String firstName,
			String lastName, String state, Number price) {
		
		try{
				logger.info("inserting FP request");
				SimpleJdbcInsert insertFPRequest = new SimpleJdbcInsert(this.getJdbcTemplate())
					.withTableName("fp_request")
					.usingColumns("user_id", "product", "first_name", "last_name","state", "price" );
		
				MapSqlParameterSource parameters = new MapSqlParameterSource();
				parameters.addValue("userId",userId);
				parameters.addValue("product",product);
				parameters.addValue("firstName",firstName);
				parameters.addValue("lastName",lastName);
				parameters.addValue("state",state);
				parameters.addValue("price",price);
		
				insertFPRequest.execute(parameters);
				
		}catch(Exception e){
			logger.error("Error while inserting FP request : "+e.toString());
		}
		
	}

	//Free BGR users Validations	
	public boolean getFreeBgrUserImp(int userId) {
		String sql = sqlStrings.get("selectFreeBgrUser");
		try {    		
    		logger.info("Entered inside jdbcDAO");
    		int freeBgr=this.getSimpleJdbcTemplate().queryForInt(sql, userId);
    		logger.info("Free BGR for user id :"+userId+" is :"+freeBgr);
    		if(freeBgr == 1){
    			return true;
    		}
	        
        } catch (Exception e) {        	
        	logger.error(e.toString());
        }
		return false;
	}
	
	public void setFreeBgrUserImp(int userId) {
		try{
			int freeBgr = 1;
			logger.info("inserting Free BGR request");
			SimpleJdbcInsert insertFPRequest = new SimpleJdbcInsert(this.getJdbcTemplate())
				.withTableName("free_bgr_users")
				.usingColumns("user_id", "free_bgr");
	
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("userId",userId);
			parameters.addValue("freeBgr",freeBgr);
			insertFPRequest.execute(parameters);
			
			}catch(Exception e){
				logger.error("Error while inserting FP request : "+e.toString());
			}
	} 
	
	public void modifyFreeBgrUserImp(int userId){
			String sql= sqlStrings.get("modifyFreeBgrUser");
			int freeBgr = 0;
			try {    		
					getSimpleJdbcTemplate().update(sql, freeBgr, userId);
					logger.info("Free BGR for user id :"+userId+" is changed to :0");
	        	} catch (Exception e) {        	
	        		logger.error(e.toString());
	        	}
	}

	public void setFreeFRUser(int userId, int frId, int txnId) {
		try{
			logger.info("inserting Free FR request :"+frId);
			SimpleJdbcInsert insertFRRequest = new SimpleJdbcInsert(this.getJdbcTemplate())
				.withTableName("free_fr_users")
				.usingColumns("user_id", "frsubscriptionid", "transaction_id");
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("userId",userId);
			parameters.addValue("frsubscriptionid",frId);
			parameters.addValue("transaction_id",txnId);
			insertFRRequest.execute(parameters);
			
			}catch(Exception e){
				logger.error("Error while inserting Free FR request : "+e.toString());
			}
	}

	
	public List<User> getnewCustomers() 
	{
		
		String sql=sqlStrings.get("getNewCustomerByDate");
		//logger.info("query"+sql);
		return getSimpleJdbcTemplate().query(sql, getUserMapper());
	}
	
	@Override
	public List<User> getnewCustomersByDays(Integer daysToSearch) 
	{		
		String sql="";
		if(daysToSearch > 0 && daysToSearch == 7)		
			sql=sqlStrings.get("getNewCustomerBy7Days");
		else if (daysToSearch == 30)		
			sql=sqlStrings.get("getNewCustomerBy30Days");
		else 
			 sql=sqlStrings.get("getNewCustomerByDate");
		//String sqlSelect= "SELECT * FROM ss_users WHERE create_date > (current_timestamp - interval "+daysToSearch+" ' days') ORDER BY create_date DESC		LIMIT 100";
		logger.info("query_For_NewUsers::-->>"+ sql);
		return getSimpleJdbcTemplate().query(sql, getUserMapper());
		
		
	}

}