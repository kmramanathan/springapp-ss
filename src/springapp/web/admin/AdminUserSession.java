package springapp.web.admin;

import java.io.Serializable;

public class AdminUserSession implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private boolean authenticated;
	
	public synchronized boolean isAuthenticated() {
		return authenticated;
	}
	public synchronized void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
		
}
