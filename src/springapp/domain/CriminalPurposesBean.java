package springapp.domain;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="criminal_purposes")
public class CriminalPurposesBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int purpose_id;
	private String title;
	private String description;
	
	public int getPurpose_id() {
		return purpose_id;
	}
	public void setPurpose_id(int purpose_id) {
		this.purpose_id = purpose_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}
