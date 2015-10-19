package springapp.domain;

import java.io.Serializable;

public abstract class BJLRequestBean implements Serializable {
	
	private String user_id;
	private String password;
	private String customer_refernce;
	private String search;
	private String search_identifier;
	private String lastname;
	private String firstname;
	private String middlename;
	private String state;
	private Integer ssn;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCustomer_refernce() {
		return customer_refernce;
	}
	public void setCustomer_refernce(String customer_refernce) {
		this.customer_refernce = customer_refernce;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getSearch_identifier() {
		return search_identifier;
	}
	public void setSearch_identifier(String search_identifier) {
		this.search_identifier = search_identifier;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getSsn() {
		return ssn;
	}
	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}
	
	

}
