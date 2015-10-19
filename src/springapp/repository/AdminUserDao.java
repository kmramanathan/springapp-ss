package springapp.repository;

public interface AdminUserDao {
	public boolean authenticateUser(String username, String password);
	public boolean isAllUser(String username);
}