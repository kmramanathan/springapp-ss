package springapp.domain.NSS;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nss_requests")
public class NSSRequestBean implements Serializable {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The value for the bgcRequestId field */
	@Id
	@GeneratedValue
	@Column(name="nss_request_id")
    private int bgcRequestId;
  
    /** The value for the userId field */
	@Column(name="user_id")
    private int userId;
  
    /** The value for the bgcProductId field */
	@Column(name="nss_product_id")
    private int bgcProductId;
  
    /** The value for the bgcStatusId field */
	@Column(name="nss_status_id")
    private int bgcStatusId;
  
    /** The value for the price field */
	@Column(name="price")
    private BigDecimal price;
  
    /** The value for the dateCreated field */
	@Column(name="date_created")
    private Date dateCreated;
  
    /** The value for the lastName field */
	@Column(name="last_name")
    private String lastName;
  
    /** The value for the firstName field */
	@Column(name="first_name")
    private String firstName;
    
    /** The value for the lastNameExact field */
    //private boolean lastNameExact;
  
    /** The value for the firstNameExact field */
    //private boolean firstNameExact;
    /** The value for the dobYear field */
	@Column(name="dob_year")
    private int dobYear;
      
    /** The value for the dobMonth field */
	@Column(name="dob_month")
    private int dobMonth;
      
    /** The value for the dobDay field */
	@Column(name="dob_day")
    private int dobDay;
    /** The value for the purpose of search */
	@Column(name="purpose")
    private String purpose;
    
    /** The value for the referenceCode field */
	@Column(name="reference_code")
    private String referenceCode;

	public int getBgcRequestId() {
		return bgcRequestId;
	}

	public void setBgcRequestId(int bgcRequestId) {
		this.bgcRequestId = bgcRequestId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBgcProductId() {
		return bgcProductId;
	}

	public void setBgcProductId(int bgcProductId) {
		this.bgcProductId = bgcProductId;
	}

	public int getBgcStatusId() {
		return bgcStatusId;
	}

	public void setBgcStatusId(int bgcStatusId) {
		this.bgcStatusId = bgcStatusId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

		public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public int getDobYear() {
		return dobYear;
	}

	public void setDobYear(int dobYear) {
		this.dobYear = dobYear;
	}

	public int getDobMonth() {
		return dobMonth;
	}

	public void setDobMonth(int dobMonth) {
		this.dobMonth = dobMonth;
	}

	public int getDobDay() {
		return dobDay;
	}

	public void setDobDay(int dobDay) {
		this.dobDay = dobDay;
	}

  
  
  

}
