package springapp.domain;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String email;
	private int invoiceNumber;
	private int searchCategoryId;
	private String cost;
	private String status;
	private String type;
	private String subtype;
	private Date date;
	
	public int getSearchCategoryId() {
		return searchCategoryId;
	}
	public void setSearchCategoryId(int searchCategoryId) {
		this.searchCategoryId = searchCategoryId;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public synchronized String getUsername() {
		return username;
	}
	public synchronized void setUsername(String username) {
		this.username = username;
	}
	public synchronized String getEmail() {
		return email;
	}
	public synchronized void setEmail(String email) {
		this.email = email;
	}
	public synchronized int getInvoiceNumber() {
		return invoiceNumber;
	}
	public synchronized void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public synchronized String getCost() {
		return cost;
	}
	public synchronized void setCost(String cost) {
		this.cost = cost;
	}
	public synchronized String getStatus() {
		return status;
	}
	public synchronized void setStatus(String status) {
		this.status = status;
	}
	public synchronized String getType() {
		return type;
	}
	public synchronized void setType(String type) {
		this.type = type;
	}
	public synchronized Date getDate() {
		return date;
	}
	public synchronized void setDate(Date date) {
		this.date = date;
	}
}

