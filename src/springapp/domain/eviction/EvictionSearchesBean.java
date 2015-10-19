package springapp.domain.eviction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class EvictionSearchesBean implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * whether the bean or its underlying object has changed
     * since last reading from the database
     */
    private boolean modified = true;

    /**
     * false if the underlying object has been read from the database,
     * true otherwise
     */
    private boolean isNew = true;

  
    /** The value for the userSearchId field */
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
    private String firstname;
  
    /** The value for the defendant field */
    private String lastname;
  
    /** The value for the defendantAddress field */
    private String businessname;
  
    /** The value for the defendantCity field */
    private String city;
  
    /** The value for the defendantState field */
    private String state;
  
    private boolean invoiced;
  
    /** The value for the referenceCode field */
    private String reference;


    /**
     * sets whether the bean exists in the database
     */
    public void setNew(boolean isNew)
    {
        this.isNew = isNew;
    }

    /**
     * returns whether the bean exists in the database
     */
    public boolean isNew()
    {
        return this.isNew;
    }

    /**
     * sets whether the bean or the object it was created from
     * was modified since the object was last read from the database
     */
    public void setModified(boolean isModified)
    {
        this.modified = isModified;
    }

    /**
     * returns whether the bean or the object it was created from
     * was modified since the object was last read from the database
     */
    public boolean isModified()
    {
        return this.modified;
    }


    /**
     * Get the UserSearchId
     *
     * @return long
     */
    public long getUserSearchId ()
    {
        return userSearchId;
    }

    /**
     * Set the value of UserSearchId
     *
     * @param v new value
     */
    public void setUserSearchId(long v)
    {
  
        this.userSearchId = v;
        setModified(true);

    }

    /**
     * Get the SearchCategoryId
     *
     * @return short
     */
    public short getSearchCategoryId ()
    {
        return searchCategoryId;
    }

    /**
     * Set the value of SearchCategoryId
     *
     * @param v new value
     */
    public void setSearchCategoryId(short v)
    {
  
        this.searchCategoryId = v;
        setModified(true);

    }

    /**
     * Get the SearchSubCategoryId
     *
     * @return short
     */
    public short getSearchSubCategoryId ()
    {
        return searchSubCategoryId;
    }

    /**
     * Set the value of SearchSubCategoryId
     *
     * @param v new value
     */
    public void setSearchSubCategoryId(short v)
    {
  
        this.searchSubCategoryId = v;
        setModified(true);

    }

    /**
     * Get the UserId
     *
     * @return int
     */
    public int getUserId ()
    {
        return userId;
    }

    /**
     * Set the value of UserId
     *
     * @param v new value
     */
    public void setUserId(int v)
    {
  
        this.userId = v;
        setModified(true);

    }

    /**
     * Get the TransactionId
     *
     * @return int
     */
    public int getTransactionId ()
    {
        return transactionId;
    }

    /**
     * Set the value of TransactionId
     *
     * @param v new value
     */
    public void setTransactionId(int v)
    {
  
        this.transactionId = v;
        setModified(true);

    }

    /**
     * Get the MatchCount
     *
     * @return short
     */
    public short getMatchCount ()
    {
        return matchCount;
    }

    /**
     * Set the value of MatchCount
     *
     * @param v new value
     */
    public void setMatchCount(short v)
    {
  
        this.matchCount = v;
        setModified(true);

    }

    /**
     * Get the CreateDate
     *
     * @return Date
     */
    public Date getCreateDate ()
    {
        return createDate;
    }

    /**
     * Set the value of CreateDate
     *
     * @param v new value
     */
    public void setCreateDate(Date v)
    {
  
        this.createDate = v;
        setModified(true);

    }

    /**
     * Get the Completed
     *
     * @return boolean
     */
    public boolean getCompleted ()
    {
        return completed;
    }

    /**
     * Set the value of Completed
     *
     * @param v new value
     */
    public void setCompleted(boolean v)
    {
  
        this.completed = v;
        setModified(true);

    }

    /**
     * Get the SearchStatusId
     *
     * @return short
     */
    public short getSearchStatusId ()
    {
        return searchStatusId;
    }

    /**
     * Set the value of SearchStatusId
     *
     * @param v new value
     */
    public void setSearchStatusId(short v)
    {
  
        this.searchStatusId = v;
        setModified(true);

    }

    /**
     * Get the Name
     *
     * @return String
     */
    public String getFirstname()
    {
        return firstname;
    }

    /**
     * Set the value of Name
     *
     * @param v new value
     */
    public void setFirstname(String v)
    {
  
        this.firstname = v;
        setModified(true);

    }

    /**
     * Get the Defendant
     *
     * @return String
     */
    public String getLastname ()
    {
        return lastname;
    }

    /**
     * Set the value of Defendant
     *
     * @param v new value
     */
    public void setLastname(String v)
    {
  
        this.lastname = v;
        setModified(true);

    }

    /**
     * Get the DefendantAddress
     *
     * @return String
     */
    public String getBusinessname ()
    {
        return businessname;
    }

    /**
     * Set the value of DefendantAddress
     *
     * @param v new value
     */
    public void setBusinessname(String v)
    {
  
        this.businessname = v;
        setModified(true);

    }

    /**
     * Get the DefendantCity
     *
     * @return String
     */
    public String getCity ()
    {
        return city;
    }

    /**
     * Set the value of DefendantCity
     *
     * @param v new value
     */
    public void setCity(String v)
    {
  
        this.city = v;
        setModified(true);

    }

    /**
     * Get the DefendantState
     *
     * @return String
     */
    public String getState ()
    {
        return state;
    }

    /**
     * Set the value of DefendantState
     *
     * @param v new value
     */
    public void setState(String v)
    {
  
        this.state = v;
        setModified(true);

    }

    /**
     * Get the Invoiced
     *
     * @return boolean
     */
    public boolean getInvoiced ()
    {
        return invoiced;
    }

    /**
     * Set the value of Invoiced
     *
     * @param v new value
     */
    public void setInvoiced(boolean v)
    {
  
        this.invoiced = v;
        setModified(true);

    }


    
    /**
     * Get the ReferenceCode
     *
     * @return String
     */
    public String getReference ()
    {
        return reference;
    }

    /**
     * Set the value of ReferenceCode
     *
     * @param v new value
     */
    public void setReference(String v)
    {
  
        this.reference = v;
        setModified(true);

    }

      

                                      
                
    /**
     * Collection to store aggregation of collBjlResultsBeans
     */
    protected List<BaseEvictionResultsBean> collEvictionResultsBeans;

    /**
     * Returns the collection of BjlResultsBeans
     */
    public List<BaseEvictionResultsBean> getEvictionResultsBeans()
    {
        return collEvictionResultsBeans;
    }

    /**
     * Sets the collection of BjlResultsBeans to the specified value
     */
    public void setEvictionResultsBeans(List<BaseEvictionResultsBean> list)
    {
        if (list == null)
        {
        	collEvictionResultsBeans = null;
        }
        else
        {
        	collEvictionResultsBeans = new ArrayList<BaseEvictionResultsBean>(list);
        }
    }

	


}
