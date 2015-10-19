package springapp.domain.CriminalSSN;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="criminal_ssn_request")
public class CriminalSSNRequestBean implements Serializable {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Real Property Fields*/
	@Id
	@GeneratedValue
	@Column(name="user_search_id")
	private long userSearchId;
    
    /** The value for the searchCategoryId field */
	@Column(name="search_category_id")
    private int searchCategoryId;
      
    /** The value for the searchSubCategoryId field */
	@Column(name="search_sub_category_id")
    private int searchSubCategoryId;
      
    /** The value for the userId field */
	@Column(name="user_id")
    private int userId;
      
    
	@Column(name="transaction_id")
    private int transactionId;
      
    /** The value for the matchCount field */
	@Column(name="match_count")
    private int matchCount;
      
    /** The value for the createDate field */
	@Column(name="create_date")
    private Date createDate;
      
    /** The value for the completed field */
	@Column(name="completed")
    private boolean completed;
      
    /** The value for the searchStatusId field */
	@Column(name="search_status_id")
    private int searchStatusId;
    
    /** The value for the searchStatusId field */
	@Column(name="invoiced")
    private Boolean invoiced;
    
    /** The value for the name field */
	@Column(name="firstname")
    private String firstName;
      
    /** The value for the defendant field */
	@Column(name="lastname")
    private String lastName;
      
    /** The value for the defendantAddress field */
	@Column(name="middlename")
    private String middlename;

    /** The value for the defendantState field */
	@Column(name="state")
    private String state;
      

	@Column(name="dob")
    private String dob;
	
    /** The value for the defendantCity field */
	@Column(name="ssn")
    private String ssn;
      
	
        /** The value for the referenceCode field */
	@Column(name="reference")
    private String reference;
  


	public long getUserSearchId() {
		return userSearchId;
	}

	public void setUserSearchId(long userSearchId) {
		this.userSearchId = userSearchId;
	}
	

	public int getSearchCategoryId() {
		return searchCategoryId;
	}

	public void setSearchCategoryId(short searchCategoryId) {
		this.searchCategoryId = searchCategoryId;
	}


	public int getSearchSubCategoryId() {
		return searchSubCategoryId;
	}

	public void setSearchSubCategoryId(int searchSubCategoryId) {
		this.searchSubCategoryId = searchSubCategoryId;
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
		

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
	public int getMatchCount() {
		return matchCount;
	}

	public void setMatchCount(short matchCount) {
		this.matchCount = matchCount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Boolean getCompleted() 
	{
		return completed;
	}

	public void setCompleted(Boolean completed) 
	{
		this.completed = completed;
	}
	

	public int getSearchStatusId() {
		return searchStatusId;
	}

	public void setSearchStatusId(int searchStatusId) {
		this.searchStatusId = searchStatusId;
	}
	
	public Boolean getInvoiced() 
	{
		return invoiced;
	}

	public void setInvoiced(Boolean invoiced) 
	{
		this.invoiced = invoiced;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	


	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}


	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

  

}
