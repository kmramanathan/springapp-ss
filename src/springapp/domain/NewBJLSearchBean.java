package springapp.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="new_bjl_searches")
public class NewBJLSearchBean {
	
	/** The value for the userSearchId field */
	@GeneratedValue
	@Id
    private long userSearchId;
      
    /** The value for the searchCategoryId field */
    private short searchCategoryId;
      
    /** The value for the searchSubCategoryId field */
    private short searchSubCategoryId;
      
    /** The value for the userId field */
    private int userId;
      
    /** The value for the transactionId field */
    private int transactionId;
      
    /** The value for the matchCount field */
    private short matchCount;
      
    /** The value for the createDate field */
    private Date createDate;
      
    /** The value for the completed field */
    private boolean completed;
      
    /** The value for the searchStatusId field */
    private short searchStatusId;
      
    /** The value for the name field */
    private String FirstName;
    
   /** The value for the Lastname field*/
    private  String LastName;
    
    /** The value for the MiddleName field*/
    private  String MiddleName;
  
    /** The value for the SSN field*/
    private  String SSN;
  
    /** The value for the SSNType field*/
    private  String SSNType;
  
    /** The value for the BusinessName field*/
    private  String BusinessName;
  
    /** The value for the State field*/
    private  String State;
    
    /** The value for the whoIsSearchFor field */
    private String whoIsSearchFor;
      
    /** The value for the invoiced field */
    private boolean invoiced;
      
    /** The value for the referenceCode field */
    private String referenceCode;

	public long getUserSearchId() {
		return userSearchId;
	}

	public void setUserSearchId(long userSearchId) {
		this.userSearchId = userSearchId;
	}

	public short getSearchCategoryId() {
		return searchCategoryId;
	}

	public void setSearchCategoryId(short searchCategoryId) {
		this.searchCategoryId = searchCategoryId;
	}

	public short getSearchSubCategoryId() {
		return searchSubCategoryId;
	}

	public void setSearchSubCategoryId(short searchSubCategoryId) {
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

	public short getMatchCount() {
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

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public short getSearchStatusId() {
		return searchStatusId;
	}

	public void setSearchStatusId(short searchStatusId) {
		this.searchStatusId = searchStatusId;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public String getSSNType() {
		return SSNType;
	}

	public void setSSNType(String sSNType) {
		SSNType = sSNType;
	}

	public String getBusinessName() {
		return BusinessName;
	}

	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getWhoIsSearchFor() {
		return whoIsSearchFor;
	}

	public void setWhoIsSearchFor(String whoIsSearchFor) {
		this.whoIsSearchFor = whoIsSearchFor;
	}

	public boolean isInvoiced() {
		return invoiced;
	}

	public void setInvoiced(boolean invoiced) {
		this.invoiced = invoiced;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
    
    
    
  
    
    
  

}
