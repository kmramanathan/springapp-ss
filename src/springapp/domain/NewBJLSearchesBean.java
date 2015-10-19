package springapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class NewBJLSearchesBean implements Serializable{
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
    private String FirstName;
  
    /** The value for the defendant field */
    private String LastName;
  
    /** The value for the defendantAddress field */
    private String BusinessName;
  
    /** The value for the defendantCity field */
    private String SSN;
  
    /** The value for the defendantState field */
    private String State;
  
    /** The value for the defendantZipCode field */
    private String MiddleName;
  
    /** The value for the defendantSsnTaxId field */
    private String SSNType;
    /** The value for the whoIsSearchFor field */
    private String whoIsSearchFor;
  
    /** The value for the invoiced field */
    private boolean invoiced;
  
    /** The value for the referenceCode field */
    private String referenceCode;


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

      
    public short getSearchStatusId() {
		return searchStatusId;
	}

	public void setSearchStatusId(short searchStatusId) {
		this.searchStatusId = searchStatusId;
		 setModified(true);
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
		 setModified(true);
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
		 setModified(true);
	}

	public String getBusinessName() {
		return BusinessName;
	}

	public void setBusinessName(String businessName) {
		BusinessName = businessName;
		 setModified(true);
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
		 setModified(true);
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
		 setModified(true);
	}

	public String getMiddleName() {
		return MiddleName;
	}
	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}
	public String getSSNType() {
		return SSNType;
	}

	public void setSSNType(String sSNType) {
		SSNType = sSNType;
		 setModified(true);
	}

	/**
     * Get the WhoIsSearchFor
     *
     * @return String
     */
    public String getWhoIsSearchFor ()
    {
        return whoIsSearchFor;
    }

    /**
     * Set the value of WhoIsSearchFor
     *
     * @param v new value
     */
    public void setWhoIsSearchFor(String v)
    {
  
        this.whoIsSearchFor = v;
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
    public String getReferenceCode ()
    {
        return referenceCode;
    }

    /**
     * Set the value of ReferenceCode
     *
     * @param v new value
     */
    public void setReferenceCode(String v)
    {
  
        this.referenceCode = v;
        setModified(true);

    }

      

                                      
                
    /**
     * Collection to store aggregation of collBjlResultsBeans
     */
    protected List<NewBaseBJLResultsBean> collBjlResultsBeans;

    /**
     * Returns the collection of BjlResultsBeans
     */
    public List<NewBaseBJLResultsBean> getBjlResultsBeans()
    {
        return collBjlResultsBeans;
    }

    /**
     * Sets the collection of BjlResultsBeans to the specified value
     */
    public void setBjlResultsBeans(List<NewBaseBJLResultsBean> list)
    {
        if (list == null)
        {
            collBjlResultsBeans = null;
        }
        else
        {
            collBjlResultsBeans = new ArrayList<NewBaseBJLResultsBean>(list);
        }
    }


}
