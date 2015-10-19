package springapp.domain.RealProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="realprop_request")
public class RealPropRequestBean implements Serializable {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Real Property Fields*/
	@Id
	@GeneratedValue
	@Column(name="user_search_id")
	private int userSearchId;
    
    /** The value for the searchCategoryId field */
	@Column(name="search_category_id")
    private short searchCategoryId;
      
    /** The value for the searchSubCategoryId field */
	@Column(name="search_sub_category_id")
    private short searchSubCategoryId;
      
    /** The value for the userId field */
	@Column(name="user_id")
    private int userId;
      
    
	@Column(name="transaction_id")
    private int transactionId;
      
    /** The value for the matchCount field */
	@Column(name="match_count")
    private short matchCount;
      
    /** The value for the createDate field */
	@Column(name="create_date")
    private Date createDate;
      
    /** The value for the completed field */
	@Column(name="completed")
    private boolean completed;
      
    /** The value for the searchStatusId field */
	@Column(name="search_status_id")
    private short searchStatusId;
    
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
	@Column(name="appartnum")
    private String appartNum;

	@Column(name="street")
    private String street;
	
    /** The value for the defendantCity field */
	@Column(name="city")
    private String city;
      
    /** The value for the defendantState field */
	@Column(name="state")
    private String state;
      
        /** The value for the referenceCode field */
	@Column(name="reference")
    private String reference;
  


	public int getUserSearchId() {
		return userSearchId;
	}

	public void setUserSearchId(int userSearchId) {
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

	public Boolean getCompleted() 
	{
		return completed;
	}

	public void setCompleted(Boolean completed) 
	{
		this.completed = completed;
	}
	

	public short getSearchStatusId() {
		return searchStatusId;
	}

	public void setSearchStatusId(short searchStatusId) {
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


	public String getAppartNum() {
		return appartNum;
	}
	public void setAppartNum(String appartNum) {
		this.appartNum = appartNum;
	}


	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
