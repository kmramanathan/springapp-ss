package springapp.domain.corporation;

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

public abstract class BaseCorporationSearches extends BaseObject{
	 /** Serial version */
    private static final long serialVersionUID = 1230102311301L;

    /** The Peer class */
    private static final CorporationSearchesPeer peer =
        new CorporationSearchesPeer();

        
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
    private String initial;
      
    /** The value for the defendantState field */
    private String state;
    private boolean invoiced;
      
        /** The value for the referenceCode field */
    private String reference;
  
            
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
                ((CorporationResults) collBjlResultss.get(i))
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
    
                  if (!ObjectUtils.equals(this.firstname, v))
              {
            this.firstname = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the lastname
     *
     * @return String
     */
    public String getLastname()
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
    
                  if (!ObjectUtils.equals(this.lastname, v))
              {
            this.lastname = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the DefendantAddress
     *
     * @return String
     */
    public String getBusinessname()
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
    
                  if (!ObjectUtils.equals(this.businessname, v))
              {
            this.businessname = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the DefendantCity
     *
     * @return String
     */
    public String getInitial()
    {
        return initial;
    }

                        
    /**
     * Set the value of DefendantCity
     *
     * @param v new value
     */
    public void setInitial(String v) 
    {
    
                  if (!ObjectUtils.equals(this.initial, v))
              {
            this.initial = v;
            setModified(true);
        }
    
          
              }
          
    /**
     * Get the DefendantState
     *
     * @return String
     */
    public String getState()
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
    
                  if (!ObjectUtils.equals(this.state, v))
              {
            this.state = v;
            setModified(true);
        }
    
          
              }
          
             
    /**
     * Get the ReferenceCode
     *
     * @return String
     */
    public String getReference()
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
    
                  if (!ObjectUtils.equals(this.reference, v))
              {
            this.reference = v;
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
     * Collection to store aggregation of collBjlResultss
     */
    protected List<CorporationResults> collBjlResultss;

    /**
     * Temporary storage of collBjlResultss to save a possible db hit in
     * the event objects are add to the collection, but the
     * complete collection is never requested.
     */
    protected void initBjlResultss()
    {
        if (collBjlResultss == null)
        {
            collBjlResultss = new ArrayList<CorporationResults>();
        }
    }

        
    /**
     * Method called to associate a BjlResults object to this object
     * through the BjlResults foreign key attribute
     *
     * @param l BjlResults
     * @throws TorqueException
     */
    public void addBjlResults(CorporationResults l) throws TorqueException
    {
        getBjlResultss().add(l);
        l.setCorporationSearches((CorporationSearches) this);
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
    public List<CorporationResults> getBjlResultss()
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
    public List<CorporationResults> getBjlResultss(Criteria criteria) throws TorqueException
    {
              if (collBjlResultss == null)
        {
            if (isNew())
            {
               collBjlResultss = new ArrayList<CorporationResults>();
            }
            else
            {
                        criteria.add(CorporationResultsPeer.USER_SEARCH_ID, getUserSearchId() );
                        collBjlResultss = CorporationResultsPeer.doSelect(criteria);
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
                            criteria.add(CorporationResultsPeer.USER_SEARCH_ID, getUserSearchId());
                            if (!lastBjlResultssCriteria.equals(criteria))
                {
                    collBjlResultss = CorporationResultsPeer.doSelect(criteria);
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
    public List<CorporationResults> getBjlResultss(Connection con) throws TorqueException
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
    public List<CorporationResults> getBjlResultss(Criteria criteria, Connection con)
            throws TorqueException
    {
              if (collBjlResultss == null)
        {
            if (isNew())
            {
               collBjlResultss = new ArrayList<CorporationResults>();
            }
            else
            {
                         criteria.add(CorporationResultsPeer.USER_SEARCH_ID, getUserSearchId());
                         collBjlResultss = CorporationResultsPeer.doSelect(criteria, con);
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
                             criteria.add(CorporationResultsPeer.USER_SEARCH_ID, getUserSearchId());
                             if (!lastBjlResultssCriteria.equals(criteria))
                 {
                     collBjlResultss = CorporationResultsPeer.doSelect(criteria, con);
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
    protected List<CorporationResults> getBjlResultssJoinBjlSearches(Criteria criteria)
        throws TorqueException
    {
                    if (collBjlResultss == null)
        {
            if (isNew())
            {
               collBjlResultss = new ArrayList<CorporationResults>();
            }
            else
            {
                              criteria.add(CorporationResultsPeer.USER_SEARCH_ID, getUserSearchId());
                              collBjlResultss = CorporationResultsPeer.doSelectJoinBjlSearches(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
                                    criteria.add(CorporationResultsPeer.USER_SEARCH_ID, getUserSearchId());
                                    if (!lastBjlResultssCriteria.equals(criteria))
            {
               // collBjlResultss = BjlResultsPeer.doSelectJoinBjlSearches(criteria);
                  collBjlResultss=BaseCorporationResultsPeer.doSelectJoinBjlSearches(criteria);
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
              fieldNames.add("firstname");
              fieldNames.add("lastname");
              fieldNames.add("businessname");
              fieldNames.add("initial");
              fieldNames.add("Invoiced");
              fieldNames.add("state");
             
              fieldNames.add("reference");
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
          if (name.equals("firstname"))
        {
                return getFirstname();
            }
          if (name.equals("lastname"))
        {
                return getLastname();
            }
          if (name.equals("businessname"))
        {
                return getBusinessname();
            }
          if (name.equals("initial"))
        {
                return getInitial();
            }
          if (name.equals("State"))
        {
                return getState();
            }
          if (name.equals("Invoiced"))
          {
                  return getInvoiced();
              }
          if (name.equals("reference"))
        {
                return getReference();
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
          if (name.equals("firstname"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setFirstname((String) value);
                      return true;
        }
          if (name.equals("lastname"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setLastname((String) value);
                      return true;
        }
          if (name.equals("businessname"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setBusinessname((String) value);
                      return true;
        }
          if (name.equals("initial"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setInitial((String) value);
                      return true;
        }
          if (name.equals("state"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setState((String) value);
                      return true;
        }
          if (name.equals("Invoiced"))
          {
                        // Object fields can be null
              if (value != null && ! Boolean.class.isInstance(value))
              {
                  throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
              }
              setInvoiced(((Boolean)value).booleanValue());
                        return true;
          }
                   if (name.equals("reference"))
        {
                      // Object fields can be null
            if (value != null && ! String.class.isInstance(value))
            {
                throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
            }
            setReference((String) value);
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
          if (name.equals(CorporationSearchesPeer.USER_SEARCH_ID))
        {
                return new Long(getUserSearchId());
            }
          if (name.equals(CorporationSearchesPeer.SEARCH_CATEGORY_ID))
        {
                return new Short(getSearchCategoryId());
            }
          if (name.equals(CorporationSearchesPeer.SEARCH_SUB_CATEGORY_ID))
        {
                return new Short(getSearchSubCategoryId());
            }
          if (name.equals(CorporationSearchesPeer.USER_ID))
        {
                return new Integer(getUserId());
            }
          if (name.equals(CorporationSearchesPeer.TRANSACTION_ID))
        {
                return new Integer(getTransactionId());
            }
          if (name.equals(CorporationSearchesPeer.MATCH_COUNT))
        {
                return new Short(getMatchCount());
            }
          if (name.equals(CorporationSearchesPeer.CREATE_DATE))
        {
                return getCreateDate();
            }
          if (name.equals(CorporationSearchesPeer.COMPLETED))
        {
                return Boolean.valueOf(getCompleted());
            }
          if (name.equals(CorporationSearchesPeer.SEARCH_STATUS_ID))
        {
                return new Short(getSearchStatusId());
            }
          if (name.equals(CorporationSearchesPeer.FIRSTNAME))
        {
                return getFirstname();
            }
          if (name.equals(CorporationSearchesPeer.LASTNAME))
        {
                return getLastname();
            }
          if (name.equals(CorporationSearchesPeer.BUSINESSNAME))
        {
                return getBusinessname();
            }
          if (name.equals(CorporationSearchesPeer.INITIAL))
        {
                return getInitial();
            }
          if (name.equals(CorporationSearchesPeer.STATE))
        {
                return getState();
            }
          if (name.equals(CorporationSearchesPeer.INVOICED))
          {
                  return Boolean.valueOf(getInvoiced());
              }
         
          if (name.equals(CorporationSearchesPeer.REFERENCE))
        {
                return getReference();
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
        if (CorporationSearchesPeer.USER_SEARCH_ID.equals(name))
        {
            return setByName("UserSearchId", value);
        }
        if (CorporationSearchesPeer.SEARCH_CATEGORY_ID.equals(name))
        {
            return setByName("SearchCategoryId", value);
        }
        if (CorporationSearchesPeer.SEARCH_SUB_CATEGORY_ID.equals(name))
        {
            return setByName("SearchSubCategoryId", value);
        }
        if (CorporationSearchesPeer.USER_ID.equals(name))
        {
            return setByName("UserId", value);
        }
        if (CorporationSearchesPeer.TRANSACTION_ID.equals(name))
        {
            return setByName("TransactionId", value);
        }
        if (CorporationSearchesPeer.MATCH_COUNT.equals(name))
        {
            return setByName("MatchCount", value);
        }
        if (CorporationSearchesPeer.CREATE_DATE.equals(name))
        {
            return setByName("CreateDate", value);
        }
        if (CorporationSearchesPeer.COMPLETED.equals(name))
        {
            return setByName("Completed", value);
        }
        if (CorporationSearchesPeer.SEARCH_STATUS_ID.equals(name))
        {
            return setByName("SearchStatusId", value);
        }
        if (CorporationSearchesPeer.FIRSTNAME.equals(name))
        {
            return setByName("firstname", value);
        }
        if (CorporationSearchesPeer.LASTNAME.equals(name))
        {
            return setByName("lastname", value);
        }
        if (CorporationSearchesPeer.BUSINESSNAME.equals(name))
        {
            return setByName("businessname", value);
        }
        if (CorporationSearchesPeer.INITIAL.equals(name))
        {
            return setByName("initial", value);
        }
        if (CorporationSearchesPeer.STATE.equals(name))
        {
            return setByName("state", value);
        }
        if (CorporationSearchesPeer.INVOICED.equals(name))
        {
            return setByName("Invoiced", value);
        }
        
        if (CorporationSearchesPeer.REFERENCE.equals(name))
        {
            return setByName("reference", value);
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
                return getFirstname();
            }
              if (pos == 10)
        {
                return getLastname();
            }
              if (pos == 11)
        {
                return getBusinessname();
            }
              if (pos == 12)
        {
                return getInitial();
            }
              if (pos == 13)
        {
                return getState();
            }
              if (pos == 14)
              {
                      return Boolean.valueOf(getInvoiced());
                  }
              if (pos == 15)
        {
                return getReference();
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
            return setByName("firstname", value);
        }
          if (position == 10)
        {
            return setByName("lastname", value);
        }
          if (position == 11)
        {
            return setByName("businessname", value);
        }
          if (position == 12)
        {
            return setByName("initial", value);
        }
          if (position == 13)
        {
            return setByName("state", value);
        }
          if (position == 14)
          {
              return setByName("Invoiced", value);
          }
          if (position == 15)
        {
            return setByName("reference", value);
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
          save(CorporationSearchesPeer.DATABASE_NAME);
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
                   // CorporationSearchesPeer.doInsert((BjlSearches) this, con);
                	CorporationSearchesPeer.doInsert((CorporationSearches) this, con);
                	
                    setNew(false);
                }
                else
                {
                  //  CorporationSearchesPeer.doUpdate((BjlSearches) this, con);
                	CorporationSearchesPeer.doUpdate((CorporationSearches)this, con);
                }
                }

                                      
                                    if (collBjlResultss != null)
            {
                for (int i = 0; i < collBjlResultss.size(); i++)
                {
                    ((CorporationResults) collBjlResultss.get(i)).save(con);
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
      public CorporationSearches copy() throws TorqueException
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
    public CorporationSearches copy(boolean deepcopy) throws TorqueException
    {
        return copyInto(new CorporationSearches(), deepcopy);
    }
      
      /**
     * Fills the copyObj with the contents of this object.
     * The associated objects are also copied and treated as new objects.
     * @param copyObj the object to fill.
     */
    protected CorporationSearches copyInto(CorporationSearches copyObj) throws TorqueException
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
      protected CorporationSearches copyInto(CorporationSearches copyObj, boolean deepcopy) throws TorqueException
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
          copyObj.setFirstname(firstname);
          copyObj.setLastname(lastname);
          copyObj.setBusinessname(businessname);
          copyObj.setInitial(initial);
         
          copyObj.setState(state);
          copyObj.setInvoiced(invoiced);
          copyObj.setReference(reference);
  
                            copyObj.setUserSearchId( 0);
                                                                                                                                                                                    
          if (deepcopy) 
        {
                                    
                            
        List<CorporationResults> vBjlResultss = getBjlResultss();
                            if (vBjlResultss != null)
        {
            for (int i = 0; i < vBjlResultss.size(); i++)
            {
                CorporationResults obj =  vBjlResultss.get(i);
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
    public CorporationSearchesPeer getPeer()
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
        return CorporationSearchesPeer.getTableMap();
    }

  
    /**
     * Creates a BjlSearchesBean with the contents of this object
       * This also creates beans for cached related objects, if they exist
       * @return a BjlSearchesBean with the contents of this object
     */
    public CorporationSearchesBean getBean()
    {
        return getBean(new IdentityMap());
    }

    /**
     * Creates a CorporationSearchesBean with the contents of this object
     * intended for internal use only
     * @param createdBeans a IdentityMap which maps objects
     *        to already created beans
     * @return a CorporationSearchesBean with the contents of this object
     */
    public CorporationSearchesBean getBean(IdentityMap createdBeans)
    {
        CorporationSearchesBean result = (CorporationSearchesBean) createdBeans.get(this);
        if (result != null ) {
            // we have already created a bean for this object, return it
            return result;
        }
        // no bean exists for this object; create a new one
        result = new CorporationSearchesBean();
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
          result.setFirstname(getFirstname());
          result.setLastname(getLastname());
          result.setBusinessname(getBusinessname());
          result.setInitial(getInitial());
          result.setState(getState());
          result.setInvoiced(getInvoiced());
          result.setReference(getReference());
  
                                                        
                    
                  if (collBjlResultss != null)
        {
            List<BaseCorporationResultsBean> relatedBeans = new ArrayList<BaseCorporationResultsBean>(collBjlResultss.size());
            for (Iterator<CorporationResults> collBjlResultssIt = collBjlResultss.iterator(); collBjlResultssIt.hasNext(); )
            {
                CorporationResults related = (CorporationResults) collBjlResultssIt.next();
                BaseCorporationResultsBean relatedBean = related.getBean(createdBeans);
                relatedBeans.add(relatedBean);
            }
            result.setCorporationResultsBeans(relatedBeans);
        }
                  
                  result.setModified(isModified());
        result.setNew(isNew());
        return result;
    }

    /**
     * Creates an instance of BjlSearches with the contents
     * of a CorporationSearchesBean.
     * This behaviour could have also been achieved using a constructor,
     * however as this class is abstract no constructors are allowed
     * @param bean the CorporationSearchesBean which contents are used to create
     *        the resulting class
     * @return an instance of BjlSearches with the contents of bean
     */
    public static CorporationSearches createBjlSearches(CorporationSearchesBean bean)
        throws TorqueException
    {
        return createBjlSearches(bean, new IdentityMap());
    }

    /**
     * Creates an instance of CorporationSearches with the contents
     * of a CorporationSearchesBean.
     * This behaviour could have also been achieved using a constructor,
     * however as this class is abstract no constructors are allowed.
     *
     * This method is intended for internal use only.
     * @param bean the CorporationSearchesBean which contents are used to create
     *        the resulting class
     * @param createdObjects a IdentityMap which maps beans
     *        to already created objects
     * @return an instance of CorporationSearches with the contents of bean
     */

    public static CorporationSearches createBjlSearches(CorporationSearchesBean bean, IdentityMap createdObjects)
        throws TorqueException
    {
    	CorporationSearches result = (CorporationSearches) createdObjects.get(bean);
        if (result != null)
        {
            // we already have an object for the bean, return it
            return result;
        }
        result = new CorporationSearches();
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
          result.setFirstname(bean.getFirstname());
          result.setLastname(bean.getLastname());
          result.setBusinessname(bean.getBusinessname());
          result.setInitial(bean.getInitial());
          result.setState(bean.getState());
          result.setInvoiced(bean.getInvoiced());
          result.setReference(bean.getReference());
  
                                                        
                    
        {
            List<BaseCorporationResultsBean> relatedBeans = bean.getCorporationResultsBeans();
            if (relatedBeans != null)
            {
                for (Iterator<BaseCorporationResultsBean> relatedBeansIt = relatedBeans.iterator(); relatedBeansIt.hasNext(); )
                {
                	BaseCorporationResultsBean relatedBean =  relatedBeansIt.next();
                    CorporationResults related = CorporationResults.createBjlResults(relatedBean, createdObjects);
                    result.addBjlResultsFromBean(related);
                }
            }
        }
                  
              result.setModified(bean.isModified());
    result.setNew(bean.isNew());
  	return result;
    }

                                                        
                    
    /**
     * Method called to associate a CorporationResults object to this object.
     * through the CorporationResults foreign key attribute
     *
     * @param toAdd CorporationResults
     */
    protected void addBjlResultsFromBean(CorporationResults toAdd)
    {
        initBjlResultss();
        collBjlResultss.add(toAdd);
    }
                    

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("CorporationSearches:\n");
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
        str.append("firstname = ")
               .append(getFirstname())
             .append("\n");
        str.append("lastname = ")
               .append(getLastname())
             .append("\n");
        str.append("businessname = ")
               .append(getBusinessname())
             .append("\n");
        str.append("initial = ")
               .append(getInitial())
             .append("\n");
        str.append("state = ")
               .append(getState())
             .append("\n");
        str.append("Invoiced = ")
        	.append(getInvoiced())
        	.append("\n");
        str.append("reference = ")
               .append(getReference())
             .append("\n");
        return(str.toString());
    }

}
