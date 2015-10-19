package springapp.service.login;

import org.springframework.stereotype.Service;

@Service
public interface LoginManager {
	public boolean authenticateUser(String username, String password);
	public boolean authenticateUser(String username, String password, boolean allowInactive);
	public boolean authenticateAdminUser(String username, String password);
	public boolean isAllAdminUser(String username);
}