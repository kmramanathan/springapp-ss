package springapp.domain;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bgc_purpose")
public class BGCRequestBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	private int bgc_request_id;
	private int userSearchId;
	private String bgcPurpose;
	private Date dateCreated;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getBgc_request_id() {
		return bgc_request_id;
	}
	public void setBgc_request_id(int bgc_request_id) {
		this.bgc_request_id = bgc_request_id;
	}
	public int getUserSearchId() {
		return userSearchId;
	}
	public void setUserSearchId(int userSearchId) {
		this.userSearchId = userSearchId;
	}
	public String getBgcPurpose() {
		return bgcPurpose;
	}
	public void setBgcPurpose(String bgcPurpose) {
		this.bgcPurpose = bgcPurpose;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
}
