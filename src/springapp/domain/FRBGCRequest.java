package springapp.domain;

import java.io.Serializable;

public class FRBGCRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String bgcFirstName;
	private String bgcLastName;
	private String bgcDobMonth;
	private String bgcDobDay;
	private String bgcDobYear;
	private String bgcPurpose;
	private Boolean exactDob = false;
	private Boolean test = false;
	private String sType;
	
	public Boolean getExactDob() {
		return exactDob;
	}
	public void setExactDob(Boolean exactDob) {
		this.exactDob = exactDob;
	}
	public String getSType() {
		return sType;
	}
	public void setSType(Boolean exactDob) {
		if(!exactDob)
			sType = "NCSOR";
		else
			sType = "NCRIM";
	}
	public String getBgcFirstName() {
		return bgcFirstName;
	}
	public void setBgcFirstName(String bgcFirstName) {
		this.bgcFirstName = bgcFirstName;
	}
	public String getBgcLastName() {
		return bgcLastName;
	}
	public void setBgcLastName(String bgcLastName) {
		this.bgcLastName = bgcLastName;
	}
	
	public String getBgcDobMonth() {
		return bgcDobMonth;
	}
	public void setBgcDobMonth(String bgcDobMonth) {
		this.bgcDobMonth = bgcDobMonth;
	}
	public String getBgcDobDay() {
		return bgcDobDay;
	}
	public void setBgcDobDay(String bgcDobDay) {
		this.bgcDobDay = bgcDobDay;
	}
	public String getBgcDobYear() {
		return bgcDobYear;
	}
	public void setBgcDobYear(String bgcDobYear) {
		this.bgcDobYear = bgcDobYear;
	}
	public String getBgcPurpose() {
		return bgcPurpose;
	}
	public void setBgcPurpose(String bgcPurpose) {
		this.bgcPurpose = bgcPurpose;
	}
	public Boolean getTest() {
		return test;
	}
	public void setTest(Boolean test) {
		this.test = test;
	}	
	
	
}
