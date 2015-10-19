package springapp.web.user;

public class UserSession {
	private String username;
	private boolean directPass;
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
	public boolean isDirectPass() {
		return directPass;
	}
	public void setDirectPass(boolean directPass) {
		this.directPass = directPass;
	}
}
