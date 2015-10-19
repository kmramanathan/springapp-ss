package springapp.web.conversion;

import java.io.Serializable;

public class ConversionUserSession implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private boolean authenticated;
	private boolean monthly;
	private boolean directPass;
	private boolean noPremium;

	public boolean isNoPremium() {
		return noPremium;
	}
	public void setNoPremium(boolean noPremium) {
		this.noPremium = noPremium;
	}
	public boolean isAuthenticated() {
		return authenticated;
	}
	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isMonthly() {
		return monthly;
	}
	public void setMonthly(Boolean monthly) {
		this.monthly = monthly;
	}
	public boolean isDirectPass() {
		return directPass;
	}
	public void setDirectPass(Boolean directPass) {
		this.directPass = directPass;
	}
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	public void setMonthly(boolean monthly) {
		this.monthly = monthly;
	}
	public void setDirectPass(boolean directPass) {
		this.directPass = directPass;
	}
	
}
