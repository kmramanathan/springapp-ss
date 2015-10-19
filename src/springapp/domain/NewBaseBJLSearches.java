package springapp.domain;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.map.IdentityMap;
import org.apache.commons.lang.ObjectUtils;
import org.apache.torque.TorqueException;
import org.apache.torque.map.TableMap;
import org.apache.torque.om.BaseObject;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;

public abstract class NewBaseBJLSearches extends BaseObject{
	 /** Serial version */
    private static final long serialVersionUID = 1230102311301L;

    /** The Peer class */
    private static final NewBJLSearchesPeer peer =
        new NewBJLSearchesPeer();

        
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
      
    /** The value for the First name field */
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
  
            
    /**
     * Get the UserSearchId
     *
     * @return long
     */
    public long getUserSearchId()
    {
        return userSearchId;
    }

                                              
    /**
     * Set the value of UserSearchId
     *
     * @param v new value
     */
    public void setUserSearchId(long v) throws TorqueException
    {
    
                  if (this.userSearchId != v)
              {
            this.userSearchId = v;
            setModified(true);
        }
    
          
                                  
                  // update associated BjlResults
        if (collBjlResultss != null)
        {
            for (int i = 0; i < collBjlResultss.size(); i++)
            {
                ((NewBJLResults) collBjlResultss.get(i))
                        .setUserSearchId(v);
            }
        }
                                }
          
    /**
     * Get the SearchCategoryId
     *
     * @return short
     */
    public short getSearchCategoryId()
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
    
                  if (this.searchCategoryId != v)
              {
            this.searchCategoryId = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the SearchSubCategoryId
     *
     * @return short
     */
    public short getSearchSubCategoryId()
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
    
                  if (this.searchSubCategoryId != v)
              {
            this.searchSubCategoryId = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the UserId
     *
     * @return int
     */
    public int getUserId()
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
    
                  if (this.userId != v)
              {
            this.userId = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the TransactionId
     *
     * @return int
     */
    public int getTransactionId()
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
    
                  if (this.transactionId != v)
              {
            this.transactionId = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the MatchCount
     *
     * @return short
     */
    public short getMatchCount()
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
    
                  if (this.matchCount != v)
              {
            this.matchCount = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the CreateDate
     *
     * @return Date
     */
    public Date getCreateDate()
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
    
                  if (!ObjectUtils.equals(this.createDate, v))
              {
            this.createDate = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the Completed
     *
     * @return boolean
     */
    public boolean getCompleted()
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
    
                  if (this.completed != v)
              {
            this.completed = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the SearchStatusId
     *
     * @return short
     */
    public short getSearchStatusId()
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
    
                  if (this.searchStatusId != v)
              {
            this.searchStatusId = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the Name
     *
     * @return String
     */
    public String getFirstName()
    {
        return FirstName;
    }

                        
    /**
     * Set the value of Name
     *
     * @param v new value
     */
    public void setFirstName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.FirstName, v))
              {
            this.FirstName = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the Defendant
     *
     * @return String
     */
    public String getLastName()
    {
        return LastName;
    }

                        
    /**
     * Set the value of Defendant
     *
     * @param v new value
     */
    public void setLastName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.LastName, v))
              {
            this.LastName = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the DefendantAddress
     *
     * @return String
     */
    public String getMiddleName()
    {
        return MiddleName;
    }

                        
    /**
     * Set the value of DefendantAddress
     *
     * @param v new value
     */
    public void setMiddleName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.MiddleName, v))
              {
            this.MiddleName = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the DefendantCity
     *
     * @return String
     */
    public String getSSN()
    {
        return SSN;
    }

                        
    /**
     * Set the value of SSN
     *
     * @param v new value
     */
    public void setSSN(String v) 
    {
    
                  if (!ObjectUtils.equals(this.SSN, v))
              {
            this.SSN = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the DefendantState
     *
     * @return String
     */
    public String getSSNType()
    {
        return SSNType;
    }

                        
    /**
     * Set the value of SSNType
     *
     * @param v new value
     */
    public void setSSNType(String v) 
    {
    
                  if (!ObjectUtils.equals(this.SSNType, v))
              {
            this.SSNType = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the BusinessName
     *
     * @return String
     */
    public String getBusinessName()
    {
        return BusinessName;
    }

                        
    /**
     * Set the value of BusinessName
     *
     * @param v new value
     */
    public void setBusinessName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.BusinessName, v))
              {
            this.BusinessName = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the STATE
     *
     * @return String
     */
    public String getState()
    {
        return State;
    }

                        
    /**
     * Set the value of STATE
     *
     * @param v new value
     */
    public void setState(String v) 
    {
    
                  if (!ObjectUtils.equals(this.State, v))
              {
            this.State = v;
            setModified(true);
        }
    
          
              }
          
            
    /**
     * Get the WhoIsSearchFor
     *
     * @return String
     */
    public String getWhoIsSearchFor()
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
    
                  if (!ObjectUtils.equals(this.whoIsSearchFor, v))
              {
            this.whoIsSearchFor = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the Invoiced
     *
     * @return boolean
     */
    public boolean getInvoiced()
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
    
                  if (this.invoiced != v)
              {
            this.invoiced = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the ReferenceCode
     *
     * @return String
     */
    public String getReferenceCode()
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
    
                  if (!ObjectUtils.equals(this.referenceCode, v))
              {
            this.referenceCode = v;
            setModified(true);
        }
    
          
              }
  
         
                                
            
          /**
     * Collection to store aggregation of collBjlResultss
     */
    protected List<NewBJLResults> collBjlResultss;

    /**
     * Temporary storage of collBjlResultss to save a possible db hit in
     * the event objects are add to the collection, but the
     * complete collection is never requested.
     */
    protected void initBjlResultss()
    {
        if (collBjlResultss == null)
        {
            collBjlResultss = new ArrayList<NewBJLResults>();
        }
    }

        
    /**
     * Method called to associate a BjlResults object to this object
     * through the BjlResults foreign key attribute
     *
     * @param l BjlResults
     * @throws TorqueException
     */
    public void addBjlResults(NewBJLResults l) throws TorqueException
    {
        getBjlResultss().add(l);
        l.setBjlSearches((NewBJLSearches) this);
    }

    /**
     * The criteria used to select the current contents of collBjlResultss
     */
    private Criteria lastBjlResultssCriteria = null;
      
    /**
                   * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getBjlResultss(new Criteria())
                   *
     * @return the collection of associated objects
           * @throws TorqueException
           */
    public List<NewBJLResults> getBjlResultss()
              throws TorqueException
          {
                      if (collBjlResultss == null)
        {
            collBjlResultss = getBjlResultss(new Criteria(10));
        }
                return collBjlResultss;
          }

    /**
           * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this BjlSearches has previously
           * been saved, it will retrieve related BjlResultss from storage.
     * If this BjlSearches is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     *
     * @throws TorqueException
     */
    public List<NewBJLResults> getBjlResultss(Criteria criteria) throws TorqueException
    {
              if (collBjlResultss == null)
        {
            if (isNew())
            {
               collBjlResultss = new ArrayList<NewBJLResults>();
            }
            else
            {
                        criteria.add(NewBJLResultsPeer.USER_SEARCH_ID, getUserSearchId() );
                        collBjlResultss = NewBJLResultsPeer.doSelect(criteria);
            }
        }
        else
        {
            // criteria has no effect for a new object
            if (!isNew())
            {
                // the following code is to determine if a new query is
                // called for.  If the criteria is the same as the last
                // one, just return the collection.
                            criteria.add(NewBJLResultsPeer.USER_SEARCH_ID, getUserSearchId());
                            if (!lastBjlResultssCriteria.equals(criteria))
                {
                    collBjlResultss = NewBJLResultsPeer.doSelect(criteria);
                }
            }
        }
        lastBjlResultssCriteria = criteria;

        return collBjlResultss;
          }

    /**
           * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getBjlResultss(new Criteria(),Connection)
           * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List<NewBJLResults> getBjlResultss(Connection con) throws TorqueException
    {
              if (collBjlResultss == null)
        {
            collBjlResultss = getBjlResultss(new Criteria(10), con);
        }
        return collBjlResultss;
          }

    /**
           * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this BjlSearches has previously
           * been saved, it will retrieve related BjlResultss from storage.
     * If this BjlSearches is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List<NewBJLResults> getBjlResultss(Criteria criteria, Connection con)
            throws TorqueException
    {
              if (collBjlResultss == null)
        {
            if (isNew())
            {
               collBjlResultss = new ArrayList<NewBJLResults>();
            }
            else
            {
                         criteria.add(NewBJLResultsPeer.USER_SEARCH_ID, getUserSearchId());
                         collBjlResultss = NewBJLResultsPeer.doSelect(criteria, con);
             }
         }
         else
         {
             // criteria has no effect for a new object
             if (!isNew())
             {
                 // the following code is to determine if a new query is
                 // called for.  If the criteria is the same as the last
                 // one, just return the collection.
                             criteria.add(NewBJLResultsPeer.USER_SEARCH_ID, getUserSearchId());
                             if (!lastBjlResultssCriteria.equals(criteria))
                 {
                     collBjlResultss = NewBJLResultsPeer.doSelect(criteria, con);
                 }
             }
         }
         lastBjlResultssCriteria = criteria;

         return collBjlResultss;
           }

                  
              
                    
                              
                                
                                                              
                                        
                    
                    
          
    /**
                 * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this BjlSearches is new, it will return
                 * an empty collection; or if this BjlSearches has previously
     * been saved, it will retrieve related BjlResultss from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in BjlSearches.
     */
    protected List<NewBJLResults> getBjlResultssJoinBjlSearches(Criteria criteria)
        throws TorqueException
    {
                    if (collBjlResultss == null)
        {
            if (isNew())
            {
               collBjlResultss = new ArrayList<NewBJLResults>();
            }
            else
            {
                              criteria.add(NewBJLResultsPeer.USER_SEARCH_ID, getUserSearchId());
                              collBjlResultss = NewBJLResultsPeer.doSelectJoinBjlSearches(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
                                    criteria.add(NewBJLResultsPeer.USER_SEARCH_ID, getUserSearchId());
                                    if (!lastBjlResultssCriteria.equals(criteria))
            {
               // collBjlResultss = BjlResultsPeer.doSelectJoinBjlSearches(criteria);
                  collBjlResultss=NewBaseBJLResultsPeer.doSelectJoinBjlSearches(criteria);
            }
        }
        lastBjlResultssCriteria = criteria;

        return collBjlResultss;
                }
                            


          
    private static List<String> fieldNames = null;

    /**
     * Generate a list of field names.
     *
     * @return a list of field names
     */
    public static synchronized List<String> getFieldNames()
    {
        if (fieldNames == null)
        {
            fieldNames = new ArrayList<String>();
              fieldNames.add("UserSearchId");
              fieldNames.add("SearchCategoryId");
              fieldNames.add("SearchSubCategoryId");
              fieldNames.add("UserId");
              fieldNames.add("TransactionId");
              fieldNames.add("MatchCount");
              fieldNames.add("CreateDate");
              fieldNames.add("Completed");
              fieldNames.add("SearchStatusId");
              fieldNames.add("FirstName");
              fieldNames.add("LastName");
              fieldNames.add("MiddleName");
              fieldNames.add("BusinessName");
              fieldNames.add("State");
              fieldNames.add("SSNType");
              fieldNames.add("SSN");
              fieldNames.add("WhoIsSearchFor");
              fieldNames.add("Invoiced");
              fieldNames.add("ReferenceCode");
              fieldNames = Collections.unmodifiableList(fieldNames);
        }
        return fieldNames;
    }

    /**
     * Retrieves a field from the object by field (Java) name passed in as a String.
     *
     * @param name field name
     * @return value
     */
    public Object getByName(String name)
    {
          if (name.equals("UserSearchId"))
        {
                return new Long(getUserSearchId());
            }
          if (name.equals("SearchCategoryId"))
        {
                return new Short(getSearchCategoryId());
            }
          if (name.equals("SearchSubCategoryId"))
        {
                return new Short(getSearchSubCategoryId());
            }
          if (name.equals("UserId"))
        {
                return new Integer(getUserId());
            }
          if (name.equals("TransactionId"))
        {
                return new Integer(getTransactionId());
            }
          if (name.equals("MatchCount"))
        {
                return new Short(getMatchCount());
            }
          if (name.equals("CreateDate"))
        {
                return getCreateDate();
            }
          if (name.equals("Completed"))
        {
                return Boolean.valueOf(getCompleted());
            }
          if (name.equals("SearchStatusId"))
        {
                return new Short(getSearchStatusId());
            }
          if (name.equals("FirstName"))
        {
                return getFirstName();
            }
          if (name.equals("LastName"))
        {
                return getLastName();
            }
          if (name.equals("MiddleName"))
        {
                return getMiddleName();
            }
          if (name.equals("BusinessName"))
          {
                  return getBusinessName();
              }
            if (name.equals("State"))
          {
                  return getState();
              }
         
          if (name.equals("SSNType"))
        {
                return getSSNType();
            }
          if (name.equals("SSN"))
          {
                  return getSSN();
              }
          
          if (name.equals("WhoIsSearchFor"))
        {
                return getWhoIsSearchFor();
            }
          if (name.equals("Invoiced"))
        {
                return Boolean.valueOf(getInvoiced());
            }
          if (name.equals("ReferenceCode"))
        {
                return getReferenceCode();
            }
          return null;
    }

    /**
     * Set a field in the object by field (Java) name.
     *
     * @param name field name
     * @param value field value
     * @return True if value was set, false if not (invalid name / protected field).
     * @throws IllegalArgumentException if object type of value does not match field object type.
     * @throws TorqueException If a problem occures with the set[Field] method.
     */
    public boolean setByName(String name, Object value )
        throws TorqueException, IllegalArgumentException
    {
          if (name.equals("UserSearchId"))
        {
                      if (value == null || ! (Long.class.isInstance(value)))
            {
                throw new IllegalArgumentException("setByName: value parameter was null or not a Long object.");
            }
            setUserSearchId(((Long) value).longValue());
                      return true;
        }
          if (name.equals("SearchCategoryId"))
        {
                      if (value == null || ! (Short.class.isInstance(value)))
            {
                throw new IllegalArgumentException("setByName: value parameter was null or not a Short object.");
            }
            setSearchCategoryId(((Short)value).shortValue());
                      return true;
        }
          if (name.equals("SearchSubCategoryId"))
        {
                      if (value == null || ! (Short.class.isInstance(value)))
            {
                throw new IllegalArgumentException("setByName: value parameter was null or not a Short object.");
            }
            setSearchSubCategoryId(((Short)value).shortValue());
                      return true;
        }
          if (name.equals("UserId"))
        {
                      if (value == null || ! (Integer.class.isInstance(value)))
            {
                throw new IllegalArgumentException("setByName: value parameter was null or not an Integer object.");
            }
            setUserId(((Integer) value).intValue());
                      return true;
        }
          if (name.equals("TransactionId"))
        {
                      if (value == null || ! (Integer.class.isInstance(value)))
            {
                throw new IllegalArgumentException("setByName: value parameter was null or not an Integer object.");
            }
            setTransactionId(((Integer) value).intValue());
                      return true;
        }
          if (name.equals("MatchCount"))
        {
                      if (value == null || ! (Short.class.isInstance(value)))
            {
                throw new IllegalArgumentException("setByName: value parameter was null or not a Short object.");
            }
            setMatchCount(((Short)value).shortValue());
                      return true;
        }
          if (name.equals("CreateDate"))
        {
                      // Object fields can be null
            if (value != null && ! Date.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setCreateDate((Date) value);
                      return true;
        }
          if (name.equals("Completed"))
        {
                      if (value == null || ! (Boolean.class.isInstance(value)))
            {
                throw new IllegalArgumentException("setByName: value parameter was null or not a Boolean object.");
            }
            setCompleted(((Boolean)value).booleanValue());
                      return true;
        }
          if (name.equals("SearchStatusId"))
        {
                      if (value == null || ! (Short.class.isInstance(value)))
            {
                throw new IllegalArgumentException("setByName: value parameter was null or not a Short object.");
            }
            setSearchStatusId(((Short)value).shortValue());
                      return true;
        }
          if (name.equals("FirstName"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setFirstName((String) value);
                      return true;
        }
          if (name.equals("LastName"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setLastName((String) value);
                      return true;
        }
          if (name.equals("MiddleName"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
           setMiddleName((String) value);
                      return true;
        }
          if (name.equals("SSN"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setSSN((String) value);
                      return true;
        }
          if (name.equals("SSNType"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
           setSSNType((String) value);
                      return true;
        }
          if (name.equals("BusinessName"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setBusinessName((String) value);
                      return true;
        }
          if (name.equals("State"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setState((String) value);
                      return true;
        }
                    if (name.equals("WhoIsSearchFor"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setWhoIsSearchFor((String) value);
                      return true;
        }
          if (name.equals("Invoiced"))
        {
                      if (value == null || ! (Boolean.class.isInstance(value)))
            {
                throw new IllegalArgumentException("setByName: value parameter was null or not a Boolean object.");
            }
            setInvoiced(((Boolean)value).booleanValue());
                      return true;
        }
          if (name.equals("ReferenceCode"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setReferenceCode((String) value);
                      return true;
        }
          return false;
    }

    /**
     * Retrieves a field from the object by name passed in
     * as a String.  The String must be one of the static
     * Strings defined in this Class' Peer.
     *
     * @param name peer name
     * @return value
     */
    public Object getByPeerName(String name)
    {
          if (name.equals(NewBJLSearchesPeer.USER_SEARCH_ID))
        {
                return new Long(getUserSearchId());
            }
          if (name.equals(NewBJLSearchesPeer.SEARCH_CATEGORY_ID))
        {
                return new Short(getSearchCategoryId());
            }
          if (name.equals(NewBJLSearchesPeer.SEARCH_SUB_CATEGORY_ID))
        {
                return new Short(getSearchSubCategoryId());
            }
          if (name.equals(NewBJLSearchesPeer.USER_ID))
        {
                return new Integer(getUserId());
            }
          if (name.equals(NewBJLSearchesPeer.TRANSACTION_ID))
        {
                return new Integer(getTransactionId());
            }
          if (name.equals(NewBJLSearchesPeer.MATCH_COUNT))
        {
                return new Short(getMatchCount());
            }
          if (name.equals(NewBJLSearchesPeer.CREATE_DATE))
        {
                return getCreateDate();
            }
          if (name.equals(NewBJLSearchesPeer.COMPLETED))
        {
                return Boolean.valueOf(getCompleted());
            }
          if (name.equals(NewBJLSearchesPeer.SEARCH_STATUS_ID))
        {
                return new Short(getSearchStatusId());
            }
          if (name.equals(NewBJLSearchesPeer.FIRST_NAME))
        {
                return getFirstName();
            }
          if (name.equals(NewBJLSearchesPeer.LAST_NAME))
        {
                return getLastName();
            }
          if (name.equals(NewBJLSearchesPeer.MIDDLE_NAME))
        {
                return getMiddleName();
            }
          if (name.equals(NewBJLSearchesPeer.BUSINESS_NAME))
          {
                  return getBusinessName();
              }
            if (name.equals(NewBJLSearchesPeer.STATE))
          {
                  return getState();
              }
        
          if (name.equals(NewBJLSearchesPeer.SSN_TYPE))
        {
                return getSSNType();
            }
          if (name.equals(NewBJLSearchesPeer.SSN))
          {
                  return getSSN();
              }
         
          if (name.equals(NewBJLSearchesPeer.WHO_IS_SEARCH_FOR))
        {
                return getWhoIsSearchFor();
            }
          if (name.equals(NewBJLSearchesPeer.INVOICED))
        {
                return Boolean.valueOf(getInvoiced());
            }
          if (name.equals(NewBJLSearchesPeer.REFERENCE_CODE))
        {
                return getReferenceCode();
            }
          return null;
    }

    /**
     * Set field values by Peer Field Name
     *
     * @param name field name
     * @param value field value
     * @return True if value was set, false if not (invalid name / protected field).
     * @throws IllegalArgumentException if object type of value does not match field object type.
     * @throws TorqueException If a problem occures with the set[Field] method.
     */
    public boolean setByPeerName(String name, Object value)
        throws TorqueException, IllegalArgumentException
    {
        if (NewBJLSearchesPeer.USER_SEARCH_ID.equals(name))
        {
            return setByName("UserSearchId", value);
        }
        if (NewBJLSearchesPeer.SEARCH_CATEGORY_ID.equals(name))
        {
            return setByName("SearchCategoryId", value);
        }
        if (NewBJLSearchesPeer.SEARCH_SUB_CATEGORY_ID.equals(name))
        {
            return setByName("SearchSubCategoryId", value);
        }
        if (NewBJLSearchesPeer.USER_ID.equals(name))
        {
            return setByName("UserId", value);
        }
        if (NewBJLSearchesPeer.TRANSACTION_ID.equals(name))
        {
            return setByName("TransactionId", value);
        }
        if (NewBJLSearchesPeer.MATCH_COUNT.equals(name))
        {
            return setByName("MatchCount", value);
        }
        if (NewBJLSearchesPeer.CREATE_DATE.equals(name))
        {
            return setByName("CreateDate", value);
        }
        if (NewBJLSearchesPeer.COMPLETED.equals(name))
        {
            return setByName("Completed", value);
        }
        if (NewBJLSearchesPeer.SEARCH_STATUS_ID.equals(name))
        {
            return setByName("SearchStatusId", value);
        }
        if (NewBJLSearchesPeer.FIRST_NAME.equals(name))
        {
            return setByName("FirstName", value);
        }
        if (NewBJLSearchesPeer.LAST_NAME.equals(name))
        {
            return setByName("LastName", value);
        }
        if (NewBJLSearchesPeer.MIDDLE_NAME.equals(name))
        {
            return setByName("MiddleName", value);
        }
        if (NewBJLSearchesPeer.BUSINESS_NAME.equals(name))
        {
            return setByName("BusinessName", value);
        }
        if (NewBJLSearchesPeer.STATE.equals(name))
        {
            return setByName("State", value);
        }
       
        if (NewBJLSearchesPeer.SSN_TYPE.equals(name))
        {
            return setByName("SSN_Type", value);
        }
        if (NewBJLSearchesPeer.SSN.equals(name))
        {
            return setByName("SSN", value);
        }
        
        if (NewBJLSearchesPeer.WHO_IS_SEARCH_FOR.equals(name))
        {
            return setByName("WhoIsSearchFor", value);
        }
        if (NewBJLSearchesPeer.INVOICED.equals(name))
        {
            return setByName("Invoiced", value);
        }
        if (NewBJLSearchesPeer.REFERENCE_CODE.equals(name))
        {
            return setByName("ReferenceCode", value);
        }
          return false;
    }

    /**
     * Retrieves a field from the object by Position as specified
     * in the xml schema.  Zero-based.
     *
     * @param pos position in xml schema
     * @return value
     */
    public Object getByPosition(int pos)
    {
            if (pos == 0)
        {
                return new Long(getUserSearchId());
            }
              if (pos == 1)
        {
                return new Short(getSearchCategoryId());
            }
              if (pos == 2)
        {
                return new Short(getSearchSubCategoryId());
            }
              if (pos == 3)
        {
                return new Integer(getUserId());
            }
              if (pos == 4)
        {
                return new Integer(getTransactionId());
            }
              if (pos == 5)
        {
                return new Short(getMatchCount());
            }
              if (pos == 6)
        {
                return getCreateDate();
            }
              if (pos == 7)
        {
                return Boolean.valueOf(getCompleted());
            }
              if (pos == 8)
        {
                return new Short(getSearchStatusId());
            }
              if (pos == 9)
        {
                return getFirstName();
            }
              if (pos == 10)
        {
                return getLastName();
            }
              if (pos == 11)
        {
                return getMiddleName();
            }
            if (pos == 12)
        {
               return getBusinessName();
            }
               
             if (pos == 13)
        {
               return getState();
            }
              
              if (pos == 14)
              {
                 return getSSNType();
            
              }
              if (pos == 15)
              {
                      return getSSN();
                  }
             if (pos == 16)
            {
                return getWhoIsSearchFor();
            }
              if (pos == 17)
              {
                return Boolean.valueOf(getInvoiced());
            }
              if (pos == 18)
        {
                return getReferenceCode();
            }
              return null;
    }

    /**
     * Set field values by its position (zero based) in the XML schema.
     *
     * @param position The field position
     * @param value field value
     * @return True if value was set, false if not (invalid position / protected field).
     * @throws IllegalArgumentException if object type of value does not match field object type.
     * @throws TorqueException If a problem occures with the set[Field] method.
     */
    public boolean setByPosition(int position, Object value)
        throws TorqueException, IllegalArgumentException
    {
        if (position == 0)
        {
            return setByName("UserSearchId", value);
        }
          if (position == 1)
        {
            return setByName("SearchCategoryId", value);
        }
          if (position == 2)
        {
            return setByName("SearchSubCategoryId", value);
        }
          if (position == 3)
        {
            return setByName("UserId", value);
        }
          if (position == 4)
        {
            return setByName("TransactionId", value);
        }
          if (position == 5)
        {
            return setByName("MatchCount", value);
        }
          if (position == 6)
        {
            return setByName("CreateDate", value);
        }
          if (position == 7)
        {
            return setByName("Completed", value);
        }
          if (position == 8)
        {
            return setByName("SearchStatusId", value);
        }
          if (position == 9)
        {
            return setByName("FirstName", value);
        }
          if (position == 10)
        {
            return setByName("LastName", value);
        }
          if (position == 11)
        {
            return setByName("MiddleName", value);
        }
          if (position == 12)
          {
              return setByName("BusinessName", value);
          }
            if (position == 13)
          {
              return setByName("State", value);
          }
          
          if (position == 14)
        {
            return setByName("SSNType", value);
        }
          if (position == 15)
          {
              return setByName("SSN", value);
          }
         
          if (position == 16)
        {
            return setByName("WhoIsSearchFor", value);
        }
          if (position == 17)
        {
            return setByName("Invoiced", value);
        }
          if (position == 18)
        {
            return setByName("ReferenceCode", value);
        }
              return false;
    }
     
    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @throws TorqueException
     */
    public void save() throws TorqueException
    {
          save(NewBJLSearchesPeer.DATABASE_NAME);
      }

    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
       * Note: this code is here because the method body is
     * auto-generated conditionally and therefore needs to be
     * in this file instead of in the super class, BaseObject.
       *
     * @param dbName
     * @throws TorqueException
     */
    public void save(String dbName) throws TorqueException
    {
        Connection con = null;
          try
        {
            con = Transaction.begin(dbName);
            save(con);
            Transaction.commit(con);
        }
        catch(TorqueException e)
        {
            Transaction.safeRollback(con);
            throw e;
        }
      }

      /** flag to prevent endless save loop, if this object is referenced
        by another object which falls in this transaction. */
    private boolean alreadyInSave = false;
      /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.  This method
     * is meant to be used as part of a transaction, otherwise use
     * the save() method and the connection details will be handled
     * internally
     *
     * @param con
     * @throws TorqueException
     */
    public void save(Connection con) throws TorqueException
    {
          if (!alreadyInSave)
        {
            alreadyInSave = true;


  
            // If this object has been modified, then save it to the database.
            if (isModified())
            {
                if (isNew())
                {
                   // NewBJLSearchesPeer.doInsert((BjlSearches) this, con);
                	NewBJLSearchesPeer.doInsert((NewBJLSearches) this, con);
                	
                    setNew(false);
                }
                else
                {
                  //  NewBJLSearchesPeer.doUpdate((BjlSearches) this, con);
                	NewBJLSearchesPeer.doUpdate((NewBJLSearches)this, con);
                }
                }

                                      
                                    if (collBjlResultss != null)
            {
                for (int i = 0; i < collBjlResultss.size(); i++)
                {
                    ((NewBJLResults) collBjlResultss.get(i)).save(con);
                }
            }
                                  alreadyInSave = false;
        }
      }

                        
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param key userSearchId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        throws TorqueException
    {
            setUserSearchId(((NumberKey) key).longValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) throws TorqueException
    {
            setUserSearchId(Long.parseLong(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getUserSearchId());
      }
 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
       * It then fills all the association collections and sets the
     * related objects to isNew=true.
       */
      public NewBJLSearches copy() throws TorqueException
    {
            return copy(true);
        }
  
          /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
     * If the parameter deepcopy is true, it then fills all the
     * association collections and sets the related objects to
     * isNew=true.
     *
     * @param deepcopy whether to copy the associated objects.
     */
    public NewBJLSearches copy(boolean deepcopy) throws TorqueException
    {
        return copyInto(new NewBJLSearches(), deepcopy);
    }
      
      /**
     * Fills the copyObj with the contents of this object.
     * The associated objects are also copied and treated as new objects.
     * @param copyObj the object to fill.
     */
    protected NewBJLSearches copyInto(NewBJLSearches copyObj) throws TorqueException
    {
        return copyInto(copyObj, true);
    }
      
    /**
     * Fills the copyObj with the contents of this object.
       * If deepcopy is true, The associated objects are also copied 
     * and treated as new objects.
       * @param copyObj the object to fill.
       * @param deepcopy whether the associated objects should be copied.
       */
      protected NewBJLSearches copyInto(NewBJLSearches copyObj, boolean deepcopy) throws TorqueException
      {
          copyObj.setUserSearchId(userSearchId);
          copyObj.setSearchCategoryId(searchCategoryId);
          copyObj.setSearchSubCategoryId(searchSubCategoryId);
          copyObj.setUserId(userId);
          copyObj.setTransactionId(transactionId);
          copyObj.setMatchCount(matchCount);
          copyObj.setCreateDate(createDate);
          copyObj.setCompleted(completed);
          copyObj.setSearchStatusId(searchStatusId);
          copyObj.setFirstName(FirstName);
          copyObj.setLastName(LastName);
          copyObj.setMiddleName(MiddleName);
          copyObj.setBusinessName(BusinessName);
          copyObj.setState(State);
          copyObj.setSSNType(SSNType);
          copyObj.setSSN(SSN);
          copyObj.setWhoIsSearchFor(whoIsSearchFor);
          copyObj.setInvoiced(invoiced);
          copyObj.setReferenceCode(referenceCode);
  
                            copyObj.setUserSearchId( 0);
                                                                                                                                                                                    
          if (deepcopy) 
        {
                                    
                            
        List<NewBJLResults> vBjlResultss = getBjlResultss();
                            if (vBjlResultss != null)
        {
            for (int i = 0; i < vBjlResultss.size(); i++)
            {
                NewBJLResults obj =  vBjlResultss.get(i);
                copyObj.addBjlResults(obj.copy());
            }
        }
        else
        {
            copyObj.collBjlResultss = null;
        }
                          }
          return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public NewBJLSearchesPeer getPeer()
    {
        return peer;
    }

    /**
     * Retrieves the TableMap object related to this Table data without
     * compiler warnings of using getPeer().getTableMap().
     *
     * @return The associated TableMap object.
     */
    public TableMap getTableMap() throws TorqueException
    {
        return NewBJLSearchesPeer.getTableMap();
    }

  
    /**
     * Creates a BjlSearchesBean with the contents of this object
       * This also creates beans for cached related objects, if they exist
       * @return a BjlSearchesBean with the contents of this object
     */
    public NewBJLSearchesBean getBean()
    {
        return getBean(new IdentityMap());
    }

    /**
     * Creates a NewBJLSearchesBean with the contents of this object
     * intended for internal use only
     * @param createdBeans a IdentityMap which maps objects
     *        to already created beans
     * @return a NewBJLSearchesBean with the contents of this object
     */
    public NewBJLSearchesBean getBean(IdentityMap createdBeans)
    {
        NewBJLSearchesBean result = (NewBJLSearchesBean) createdBeans.get(this);
        if (result != null ) {
            // we have already created a bean for this object, return it
            return result;
        }
        // no bean exists for this object; create a new one
        result = new NewBJLSearchesBean();
        createdBeans.put(this, result);

          result.setUserSearchId(getUserSearchId());
          result.setSearchCategoryId(getSearchCategoryId());
          result.setSearchSubCategoryId(getSearchSubCategoryId());
          result.setUserId(getUserId());
          result.setTransactionId(getTransactionId());
          result.setMatchCount(getMatchCount());
          result.setCreateDate(getCreateDate());
          result.setCompleted(getCompleted());
          result.setSearchStatusId(getSearchStatusId());
          result.setFirstName(getFirstName());
          result.setLastName(getLastName());
          result.setMiddleName(getMiddleName());
          result.setBusinessName(getBusinessName());
          result.setState(getState());
          result.setSSNType(getSSNType());
          result.setSSN(getSSN());
          result.setWhoIsSearchFor(getWhoIsSearchFor());
          result.setInvoiced(getInvoiced());
          result.setReferenceCode(getReferenceCode());
  
                                                        
                    
                  if (collBjlResultss != null)
        {
            List<NewBaseBJLResultsBean> relatedBeans = new ArrayList<NewBaseBJLResultsBean>(collBjlResultss.size());
            for (Iterator<NewBJLResults> collBjlResultssIt = collBjlResultss.iterator(); collBjlResultssIt.hasNext(); )
            {
                NewBJLResults related = (NewBJLResults) collBjlResultssIt.next();
                NewBaseBJLResultsBean relatedBean = related.getBean(createdBeans);
                relatedBeans.add(relatedBean);
            }
            result.setBjlResultsBeans(relatedBeans);
        }
                  
                  result.setModified(isModified());
        result.setNew(isNew());
        return result;
    }

    /**
     * Creates an instance of BjlSearches with the contents
     * of a NewBJLSearchesBean.
     * This behaviour could have also been achieved using a constructor,
     * however as this class is abstract no constructors are allowed
     * @param bean the NewBJLSearchesBean which contents are used to create
     *        the resulting class
     * @return an instance of BjlSearches with the contents of bean
     */
    public static NewBJLSearches createBjlSearches(NewBJLSearchesBean bean)
        throws TorqueException
    {
        return createBjlSearches(bean, new IdentityMap());
    }

    /**
     * Creates an instance of NewBJLSearches with the contents
     * of a NewBJLSearchesBean.
     * This behaviour could have also been achieved using a constructor,
     * however as this class is abstract no constructors are allowed.
     *
     * This method is intended for internal use only.
     * @param bean the NewBJLSearchesBean which contents are used to create
     *        the resulting class
     * @param createdObjects a IdentityMap which maps beans
     *        to already created objects
     * @return an instance of NewBJLSearches with the contents of bean
     */

    public static NewBJLSearches createBjlSearches(NewBJLSearchesBean bean, IdentityMap createdObjects)
        throws TorqueException
    {
    	NewBJLSearches result = (NewBJLSearches) createdObjects.get(bean);
        if (result != null)
        {
            // we already have an object for the bean, return it
            return result;
        }
        result = new NewBJLSearches();
        createdObjects.put(bean, result);

          result.setUserSearchId(bean.getUserSearchId());
          result.setSearchCategoryId(bean.getSearchCategoryId());
          result.setSearchSubCategoryId(bean.getSearchSubCategoryId());
          result.setUserId(bean.getUserId());
          result.setTransactionId(bean.getTransactionId());
          result.setMatchCount(bean.getMatchCount());
          result.setCreateDate(bean.getCreateDate());
          result.setCompleted(bean.getCompleted());
          result.setSearchStatusId(bean.getSearchStatusId());
          result.setFirstName(bean.getFirstName());
          result.setLastName(bean.getLastName());
          result.setMiddleName(bean.getMiddleName());
          result.setBusinessName(bean.getBusinessName());
          result.setState(bean.getState());
          result.setSSNType(bean.getSSNType());
          result.setSSN(bean.getSSN());
          result.setWhoIsSearchFor(bean.getWhoIsSearchFor());
          result.setInvoiced(bean.getInvoiced());
          result.setReferenceCode(bean.getReferenceCode());
  
                                                        
                    
        {
            List<NewBaseBJLResultsBean> relatedBeans = bean.getBjlResultsBeans();
            if (relatedBeans != null)
            {
                for (Iterator<NewBaseBJLResultsBean> relatedBeansIt = relatedBeans.iterator(); relatedBeansIt.hasNext(); )
                {
                    NewBaseBJLResultsBean relatedBean =  relatedBeansIt.next();
                    NewBJLResults related = NewBJLResults.createBjlResults(relatedBean, createdObjects);
                    result.addBjlResultsFromBean(related);
                }
            }
        }
                  
              result.setModified(bean.isModified());
    result.setNew(bean.isNew());
  	return result;
    }

                                                        
                    
    /**
     * Method called to associate a NewBJLResults object to this object.
     * through the NewBJLResults foreign key attribute
     *
     * @param toAdd NewBJLResults
     */
    protected void addBjlResultsFromBean(NewBJLResults toAdd)
    {
        initBjlResultss();
        collBjlResultss.add(toAdd);
    }
                    

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("NewBJLSearches:\n");
        str.append("UserSearchId = ")
               .append(getUserSearchId())
             .append("\n");
        str.append("SearchCategoryId = ")
               .append(getSearchCategoryId())
             .append("\n");
        str.append("SearchSubCategoryId = ")
               .append(getSearchSubCategoryId())
             .append("\n");
        str.append("UserId = ")
               .append(getUserId())
             .append("\n");
        str.append("TransactionId = ")
               .append(getTransactionId())
             .append("\n");
        str.append("MatchCount = ")
               .append(getMatchCount())
             .append("\n");
        str.append("CreateDate = ")
               .append(getCreateDate())
             .append("\n");
        str.append("Completed = ")
               .append(getCompleted())
             .append("\n");
        str.append("SearchStatusId = ")
               .append(getSearchStatusId())
             .append("\n");
        str.append("FirstName = ")
               .append(getFirstName())
             .append("\n");
        str.append("LastName = ")
               .append(getLastName())
             .append("\n");
        str.append("MiddleName = ")
               .append(getMiddleName())
             .append("\n");
        str.append("BusinessName = ")
    	   .append(getBusinessName())
    	   .append("\n");
       str.append("State = ")
           .append(getState())
         .append("\n");
        
       str.append("SSNType = ")
               .append(getSSNType())
             .append("\n");
       str.append("SSN = ")
          .append(getSSN())
          .append("\n");
        
        str.append("WhoIsSearchFor = ")
               .append(getWhoIsSearchFor())
             .append("\n");
        str.append("Invoiced = ")
               .append(getInvoiced())
             .append("\n");
        str.append("ReferenceCode = ")
               .append(getReferenceCode())
             .append("\n");
        return(str.toString());
    }

}
