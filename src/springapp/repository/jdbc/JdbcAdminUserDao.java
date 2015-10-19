package springapp.repository.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import springapp.repository.AdminUserDao;

@Repository("adminUserDao")
public class JdbcAdminUserDao extends AbstractJdbcDao implements AdminUserDao {     
	@Autowired
	protected DataSource dataSource;

	public boolean authenticateUser(String username, String password) {
		String sql = "SELECT 1 FROM ss_admin_users WHERE username = ? AND password = ?";
		try {  
	        int result = getSimpleJdbcTemplate().queryForInt(sql, username, password);
	        if (result == 1) {
	        	return true;
	        }
        } catch (EmptyResultDataAccessException e) {        	
        	return false;
        }
        // fall thru
        return false;
	}

	public boolean isAllUser(String username) {
		String sql = "SELECT count(*) FROM ss_admin_users WHERE username = ? and role = 'all'";
		int result = getSimpleJdbcTemplate().queryForInt(sql, username);
		logger.info("returns : "+result);
	        if (result == 0) {
	        	return false;
	        }
           return true;
	}
}