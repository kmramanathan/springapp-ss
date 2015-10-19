package springapp.service.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.domain.User;
import springapp.repository.AdminUserDao;
import springapp.repository.UserDao;
import springapp.service.NeonPasswordMD5;
import springapp.service.NeonService;
import springapp.service.user.UserManager;

@Service("loginManager")
public class SimpleLoginManager extends NeonService implements LoginManager {
	@Autowired
	UserManager userManager;
		
	@Autowired
	AdminUserDao adminUserDao;

	public boolean authenticateUser(String username, String password) {
		return authenticateUser(username, password, false);
	}
	
	public boolean authenticateUser(String username, String password, boolean allowInactive) {
		List<User> list = userManager.getUsersByUsernameCaseInsensitive(username);
		if (list.size() != 1) {
			return false;
		}
		User user = list.get(0);
		if (user == null) {
			return false;
		}
		if (user.getDisabled()) {
			return false;
		}
		if (user.getInactive() && !allowInactive) {
			return false;
		}
		if (user.getPassword().equals(NeonPasswordMD5.encryptPassword(password))) {
			return true;
		}
		return false;
	}

	public boolean authenticateAdminUser(String username, String password) {
		return adminUserDao.authenticateUser(username, NeonPasswordMD5.encryptPassword(password));
	}
	public boolean isAllAdminUser(String username) {
		return adminUserDao.isAllUser(username);
	}
}