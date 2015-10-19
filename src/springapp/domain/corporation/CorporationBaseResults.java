package springapp.domain.corporation;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections.map.IdentityMap;
import org.apache.torque.TorqueException;
import org.apache.torque.map.TableMap;
import org.apache.torque.om.BaseObject;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.util.Transaction;

public abstract class CorporationBaseResults extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1230102311301L;
	/** peer class*/
	  private static final CorporationResultsPeer peer =
	        new CorporationResultsPeer();
	  /** The value for the resultId field */
	    private long resultId;
	      
	    /** The value for the userSearchId field */
	    private long userSearchId;
	    //Corporations response data
		/** Sos Corp fields*/
		private String filing_number;
		private String filing_date;
		private String filing_state;
		private String state;
		private String corporation_id;
		private String corporation_name;
		private String incorp_date;
		private String corporation_status;
		private String corporation_status_date;
		private String corporation_type;
		private String bus_type;
		private String originated_state;
		private String county;
		private String federal_tax_id;
		private String duration_date;
		
		/** corporation_name_information */
		private String corporation_name_type_desc;
		private String cn_corporation_name1;
		private String cn_effective_date;
		
		/** corporation name_information */
		private String name;
		private String name_type;
		private String current_active;
		
		/** corporation name_address */
		private String na_address1;
		private String na_address2;
		private String na_address3;
		private String na_city;
		private String na_state;
		private String na_zip_code;
		private String na_address_type;
		private String na_current_address;
		private String na_parsed_flag;
		
		/** Corporation address */
		
		private String a_address1;
		private String a_address2;
		private String a_address3;
		private String a_city;
		private String a_state;
		private String a_zip_code;
		private String a_address_type;
		private String a_current_address;
		private String a_effective_date;
		private String a_parsed_flag;
		
		/** Corporation other_info */
		private String info_title;
		private String info_desc;
		
		/** corp_history*/
		
		private String history_pages;
		private String history_code;
		private String history_desc;
		private String effective_date;
		private String history_num;
		private String history_name;
		private String locator_num;
		
		public long getResultId() {
			return resultId;
		}
		public void setResultId(long v) {
			if(this.resultId != v)
			{
			this.resultId = v;
			setModified(true);
			}
		}
		/** search id **/
		public long getUserSearchId() {
			return userSearchId;
		}
		public void setUserSearchId(long v)throws TorqueException {
			if(this.userSearchId != v)
			{
			this.userSearchId = v;
			setModified(true);
			}
			if(aCorporationSearches != null && !(aCorporationSearches.getUserSearchId()== v))
			{
				aCorporationSearches = null;
			}
		}
		
		
		
		private CorporationSearches aCorporationSearches;
		/**
	     * Declares an association between this object and a BjlSearches object
	     *
	     * @param v CorporationSearches
	     * @throws TorqueException
	     */
	    public void setCorporationSearches(CorporationSearches v) throws TorqueException
	    {
	            if (v == null)
	        {
	                          setUserSearchId( 0);
	              }
	        else
	        {
	            setUserSearchId(v.getUserSearchId());
	        }
	            aCorporationSearches = v;
	    }

	    
	   
		
		public String getFiling_number() {
			return filing_number;
		}
		public void setFiling_number(String v) {
			if(this.filing_number != v)
			{
				this.filing_number = v;
				setModified(true);
			}
		}
		public String getFiling_date() {
			return filing_date;
		}
		public void setFiling_date(String v) {
			if(this.filing_date != v)
			{
			this.filing_date = v;
			setModified(true);
			}
		}
		public String getFiling_state() {
			return filing_state;
		}
		public void setFiling_state(String v) {
			if(this.filing_state != v)
			{
			this.filing_state = v;
			setModified(true);
			}
		}
		public String getState() {
			return state;
		}
		public void setState(String v) {
			if(this.state != v)
			{
				this.state = v;	
				setModified(true);
			}
			
		}
		public String getCorporation_id() {
			return corporation_id;
		}
		public void setCorporation_id(String v) {
			if(this.corporation_id != v)
			{
			this.corporation_id = v;
			setModified(true);
			}
		}
		public String getCorporation_name() {
			return corporation_name;
		}
		public void setCorporation_name(String v) {
			if(this.corporation_name != v)
			{
			this.corporation_name = v;
			setModified(true);
			}
		}
		public String getIncorp_date() {
			return incorp_date;
		}
		public void setIncorp_date(String v) {
			if(this.incorp_date != v)
			{
			this.incorp_date = v;
			setModified(true);
			}
		}
		public String getCorporation_status() {
			return corporation_status;
		}
		public void setCorporation_status(String v) {
			if(this.corporation_status != v)
			{
			this.corporation_status = v;
			setModified(true);
			}
		}
		public String getCorporation_status_date() {
			return corporation_status_date;
		}
		public void setCorporation_status_date(String v) {
			if(this.corporation_status_date != v)
			{
			this.corporation_status_date = v;
			setModified(true);
			}
		}
		public String getCorporation_type() {
			return corporation_type;
		}
		public void setCorporation_type(String v) {
			if(this.corporation_type != v)
			{
			this.corporation_type = v;
			setModified(true);
			}
		}
		
		public String getBus_type() {
			return bus_type;
		}
		public void setBus_type(String v) {
			if(this.bus_type != v)
			{
			this.bus_type = v;
			setModified(true);
			}
		}
		public String getOriginated_state() {
			return originated_state;
		}
		public void setOriginated_state(String v) {
			if(this.originated_state != v)
			{
			this.originated_state = v;
			setModified(true);
			}
		}
		public String getCounty() {
			return county;
		}
		public void setCounty(String v) {
			if(this.county != v)
			{
			this.county = v;
			setModified(true);
			}
		}
		public String getFederal_tax_id() {
			return federal_tax_id;
		}
		public void setFederal_tax_id(String v) {
			if(this.federal_tax_id != v)
			{
			this.federal_tax_id = v;
			setModified(true);
			}
		}
		public String getDuration_date() {
			return duration_date;
		}
		public void setDuration_date(String v) {
			if(this.duration_date != v)
			{
			this.duration_date = v;
			setModified(true);
			}
		}
		
		public String getCorporation_name_type_desc() {
			return corporation_name_type_desc;
		}
		public void setCorporation_name_type_desc(String v) {
			if(this.corporation_name_type_desc != v)
			{
			this.corporation_name_type_desc = v;
			setModified(true);
			}
		}
		public String getCn_corporation_name1() {
			return cn_corporation_name1;
		}
		public void setCn_corporation_name1(String v) {
			if(this.cn_corporation_name1 != v)
			{
			this.cn_corporation_name1 = v;
			setModified(true);
			}
		}
		public String getCn_effective_date() {
			return cn_effective_date;
		}
		public void setCn_effective_date(String v) {
			if(this.cn_effective_date != v)
			{
			this.cn_effective_date = v;
			setModified(true);			}
		}
		public String getName() {
			return name;
		}
		public void setName(String v) {
			if(this.name != v)
			{
			this.name = v;
			setModified(true);
			}
		}
		public String getName_type() {
			return name_type;
		}
		public void setName_type(String v) {
			if(this.name_type != v)
			{
			this.name_type = v;
			setModified(true);
			}
		}
		public String getCurrent_active() {
			return current_active;
		}
		public void setCurrent_active(String v) {
			if(this.current_active != v)
			{
			this.current_active = v;
			setModified(true);
			}
		}
		public String getNa_address1() {
			return na_address1;
		}
		public void setNa_address1(String v) {
			if(this.na_address1 != v)
			{
			this.na_address1 = v;
			setModified(true);
			}
		}
		public String getNa_address2() {
			return na_address2;
		}
		public void setNa_address2(String v) {
			if(this.na_address2 != v)
			{
			this.na_address2 = v;
			setModified(true);
			}
		}
		public String getNa_address3() {
			return na_address3;
		}
		public void setNa_address3(String v) {
			if(this.na_address3 != v)
			{
			this.na_address3 = v;
			setModified(true);
			}
		}
		public String getNa_city() {
			return na_city;
		}
		public void setNa_city(String v) {
			if(this.na_city != v)
			{
			this.na_city = v;
			setModified(true);
			}
		}
		public String getNa_state() {
			return na_state;
		}
		public void setNa_state(String v) {
			if(this.na_state != v)
			{
			this.na_state = v;
			setModified(true);
			}
		}
		public String getNa_zip_code() {
			return na_zip_code;
		}
		public void setNa_zip_code(String v) {
			if(this.na_zip_code != v)
			{
			this.na_zip_code = v;
			setModified(true);
			}
		}
		public String getNa_address_type() {
			return na_address_type;
		}
		public void setNa_address_type(String v) {
			if(this.na_address_type != v)
			{
			this.na_address_type = v;
			setModified(true);
			}
		}
		public String getNa_current_address() {
			return na_current_address;
		}
		public void setNa_current_address(String v) {
			if(this.na_current_address != v)
			{
			this.na_current_address = v;
			setModified(true);
			}
		}
		public String getNa_parsed_flag() {
			return na_parsed_flag;
		}
		public void setNa_parsed_flag(String v) {
			if(this.na_parsed_flag != v)
			{
			this.na_parsed_flag = v;
			setModified(true);
			}
		}
		public String getA_address1() {
			return a_address1;
		}
		public void setA_address1(String v) {
			if(this.a_address1 != v)
			{
			this.a_address1 = v;
			setModified(true);
			}
		}
		public String getA_address2() {
			return a_address2;
		}
		public void setA_address2(String v) {
			if(this.a_address2 != v)
			{
			this.a_address2 = v;
			setModified(true);
			}
		}
		public String getA_address3() {
			return a_address3;
		}
		public void setA_address3(String v) {
			if(this.a_address3 != v)
			{
			this.a_address3 = v;
			setModified(true);
			}
		}
		public String getA_city() {
			return a_city;
		}
		public void setA_city(String v) {
			if(this.a_city != v)
			{
			this.a_city = v;
			setModified(true);
			}
		}
		public String getA_state() {
			return a_state;
		}
		public void setA_state(String v) {
			if(this.a_state != v)
			{
			this.a_state = v;
			setModified(true);
			}
		}
		public String getA_zip_code() {
			return a_zip_code;
		}
		public void setA_zip_code(String v) {
			if(this.a_zip_code != v)
			{
			this.a_zip_code = v;
			setModified(true);
			}
		}
		public String getA_address_type() {
			return a_address_type;
		}
		public void setA_address_type(String v) {
			if(this.a_address_type != v)
			{
			this.a_address_type = v;
			setModified(true);
			}
		}
		public String getA_current_address() {
			return a_current_address;
		}
		public void setA_current_address(String v) {
			if(this.a_current_address != v)
			{
			this.a_current_address = v;
			setModified(true);
			}
		}
		public String getA_effective_date() {
			return a_effective_date;
		}
		public void setA_effective_date(String v) {
			if(this.a_effective_date != v)
			{
			this.a_effective_date = v;
			setModified(true);
			}
		}
		public String getA_parsed_flag() {
			return a_parsed_flag;
		}
		public void setA_parsed_flag(String v) {
			if(this.a_parsed_flag != v)
			{
			this.a_parsed_flag = v;
			setModified(true);
			}
		}
		public String getInfo_title() {
			return info_title;
		}
		public void setInfo_title(String v) {
			if(this.info_title != v)
			{
			this.info_title = v;
			setModified(true);
			}
		}
		public String getInfo_desc() {
			return info_desc;
		}
		public void setInfo_desc(String v) {
			if(this.info_desc != v)
			{
			this.info_desc = v;
			setModified(true);
			}
		}
		public String getHistory_pages() {
			return history_pages;
		}
		public void setHistory_pages(String v) {
			if(this.history_pages != v)
			{
			this.history_pages = v;
			setModified(true);
			}
		}
		public String getHistory_code() {
			return history_code;
		}
		public void setHistory_code(String v) {
			if(this.history_code != v)
			{
			this.history_code = v;
			setModified(true);
			}
		}
		public String getHistory_desc() {
			return history_desc;
		}
		public void setHistory_desc(String v) {
			if(this.history_desc != v)
			{
			this.history_desc = v;
			setModified(true);
			}
		}
		public String getEffective_date() {
			return effective_date;
		}
		public void setEffective_date(String v) {
			if(this.effective_date != v)
			{
			this.effective_date = v;
			setModified(true);
			}
		}
		public String getHistory_num() {
			return history_num;
		}
		public void setHistory_num(String v) {
			if(this.history_num != v)
			{
			this.history_num = v;
			setModified(true);
			}
		}
		public String getHistory_name() {
			return history_name;
		}
		public void setHistory_name(String v) {
			if(this.history_name != v)
			{
			this.history_name = v;
			setModified(true);
			}
		}
		public String getLocator_num() {
			return locator_num;
		}
		public void setLocator_num(String v) {
			if(this.locator_num != v)
			{
			this.locator_num = v;
			setModified(true);
			}
		}
		public CorporationSearches getACorporationSearches() {
			return aCorporationSearches;
		}
		public void setACorporationSearches(CorporationSearches corporationSearches) {
			aCorporationSearches = corporationSearches;
		}
		public boolean isAlreadyInSave() {
			return alreadyInSave;
		}
		public void setAlreadyInSave(boolean alreadyInSave) {
			this.alreadyInSave = alreadyInSave;
		}
		/**
	     * Returns the associated CorporationSearches object.
	           * If it was not retrieved before, the object is retrieved from
	     * the database
	           *
	     * @return the associated CorporationSearches object
	           * @throws TorqueException
	           */
	    public CorporationSearches getCorporationSearches()
	              throws TorqueException
	          {
	              if (aCorporationSearches == null && (this.userSearchId != 0))
	        {
	            	  aCorporationSearches = CorporationSearchesPeer.retrieveByPK(SimpleKey.keyFor(this.userSearchId));
	                  
	            /* The following can be used instead of the line above to
	               guarantee the related object contains a reference
	               to this object, but this level of coupling
	               may be undesirable in many circumstances.
	               As it can lead to a db query with many results that may
	               never be used.
	               BjlSearches obj = BjlSearchesPeer.retrieveByPK(this.userSearchId);
	               obj.add${pCollName}(this);
	            */
	        }
	              return aCorporationSearches;
	    }
	    /**
	     * Return the associated BjlSearches object
	     * If it was not retrieved before, the object is retrieved from
	     * the database using the passed connection
	     *
	     * @param connection the connection used to retrieve the associated object
	     *        from the database, if it was not retrieved before
	     * @return the associated BjlSearches object
	     * @throws TorqueException
	     */
	    public CorporationSearches getCorporationSearches(Connection connection)
	        throws TorqueException
	    {
	        if (aCorporationSearches == null && (this.userSearchId != 0))
	        {
	        	aCorporationSearches = CorporationSearchesPeer.retrieveByPK(SimpleKey.keyFor(this.userSearchId), connection);
	              
	            /* The following can be used instead of the line above to
	               guarantee the related object contains a reference
	               to this object, but this level of coupling
	               may be undesirable in many circumstances.
	               As it can lead to a db query with many results that may
	               never be used.
	               BjlSearches obj = BjlSearchesPeer.retrieveByPK(this.userSearchId, connection);
	               obj.add${pCollName}(this);
	            */
	        }
	        return aCorporationSearches;
	    }

	    /**
	     * Provides convenient way to set a relationship based on a
	     * ObjectKey, for example
	     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
	     *
	         */
	    public void setCorporationSearchesKey(ObjectKey key) throws TorqueException
	    {
	      
	                        setUserSearchId(((NumberKey) key).longValue());
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
	            fieldNames.add("ResultId");
	            fieldNames.add("UserSearchId");
	          	fieldNames.add("filing_number");
	        	fieldNames.add("filing_date");
	        	fieldNames.add("filing_state");
	        	fieldNames.add("state");
	        	fieldNames.add("corporation_id");
	        	fieldNames.add("corporation_name");
	        	fieldNames.add("incorp_date");
	        	fieldNames.add("corporation_status");
	        	fieldNames.add("corporation_status_date");
	        	fieldNames.add("corporation_type");
	        	fieldNames.add("bus_type");
	        	fieldNames.add("originated_state");
	        	fieldNames.add("county");
	        	fieldNames.add("federal_tax_id");
	        	fieldNames.add("duration_date");
	        	fieldNames.add("date");
	        	fieldNames.add("description");
	        	fieldNames.add("corporation_name_type_desc");
	        	fieldNames.add("cn_corporation_name1");
	        	fieldNames.add("cn_effective_date");
	        	fieldNames.add("name");
	        	fieldNames.add("name_type");
	        	fieldNames.add("current_active");
	        	fieldNames.add("na_address1");
	        	fieldNames.add("na_address2");
	        	fieldNames.add("na_address3");
	        	fieldNames.add("na_city");
	        	fieldNames.add("na_state");
	        	fieldNames.add("na_zip_code");
	        	fieldNames.add("na_address_type");
	        	fieldNames.add("na_current_address");
	        	fieldNames.add("na_parsed_flag");
	        	fieldNames.add("a_address1");
	        	fieldNames.add("a_address2");
	        	fieldNames.add("a_address3");
	        	fieldNames.add("a_city");
	        	fieldNames.add("a_state");
	        	fieldNames.add("a_zip_code");
	        	fieldNames.add("a_address_type");
	        	fieldNames.add("a_current_address");
	        	fieldNames.add("a_effective_date");
	        	fieldNames.add("a_parsed_flag");
	        	fieldNames.add("info_title");
	        	fieldNames.add("info_desc");
	        	fieldNames.add("history_pages");
	        	fieldNames.add("history_code");
	        	fieldNames.add("history_desc");
	        	fieldNames.add("effective_date");
	        	fieldNames.add("history_num");
	        	fieldNames.add("history_name");
	        	fieldNames.add("locator_num");          
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
	    	 if (name.equals("ResultId"))
	         {
	                 return new Long(getResultId());
	             }
	           if (name.equals("UserSearchId"))
	         {
	                 return new Long(getUserSearchId());
	             }
	           if (name.equals("filing_number"))
	         {
	                 return getFiling_number();
	             }
	           if (name.equals("filing_date"))
	         {
	                 return getFiling_date();
	             }
	           if (name.equals("filing_state"))
	         {
	                 return getFiling_state();
	             }
	           if (name.equals("state"))
	         {
	                 return getState();
	             }
	           if (name.equals("corporation_id"))
	         {
	                 return getCorporation_id();
	             }
	           if (name.equals("corporation_name"))
	         {
	                 return getCorporation_name();
	             }
	           if (name.equals("incorp_date"))
	         {
	                 return getIncorp_date();
	             }
	           if (name.equals("corporation_status"))
	         {
	                 return getCorporation_status();
	             }
	           if (name.equals("corporation_status_date"))
	         {
	                 return getCorporation_status_date();
	             }
	           if (name.equals("corporation_type"))
	         {
	                 return getCorporation_type();
	             }
	           if (name.equals("bus_type"))
		         {
		                 return getBus_type();
		             }
	           if (name.equals("originated_state"))
	         {
	                 return getOriginated_state();
	             }
	           if (name.equals("county"))
	         {
	                 return getCounty();
	             }
	           if (name.equals("federal_tax_id"))
	         {
	                 return getFederal_tax_id();
	             }
	           if (name.equals("duration_date"))
	         {
	                 return getDuration_date();
	             }
	         
	           if (name.equals("corporation_name_type_desc"))
	         {
	                 return getCorporation_name_type_desc();
	             }
	           if (name.equals("cn_corporation_name1"))
	         {
	                 return getCn_corporation_name1();
	             }
	           if (name.equals("cn_effective_date"))
	         {
	                 return getCn_effective_date();
	             }
	           if (name.equals("name"))
	         {
	                 return getName();
	             }
	           if (name.equals("name_type"))
	         {
	                 return getName_type();
	             }
	           if (name.equals("current_active"))
	         {
	                 return getCurrent_active();
	             }
	           if (name.equals("na_address1"))
	         {
	                 return getNa_address1();
	             }
	           if (name.equals("na_address2"))
	         {
	                 return getNa_address2();
	             }
	           if (name.equals("na_address3"))
	         {
	                 return getNa_address3();
	             }
	           if (name.equals("na_city"))
	         {
	                 return getNa_city();
	             }
	           if (name.equals("na_state"))
	         {
	                 return getNa_state();
	             }
	           if (name.equals("na_zip_code"))
	         {
	                 return getNa_zip_code();
	             }
	           if (name.equals("na_address_type"))
	         {
	                 return getNa_address_type();
	             }
	           if (name.equals("na_current_address"))
	         {
	                 return getNa_current_address();
	             }
	           if (name.equals("na_parsed_flag"))
	         {
	                 return getNa_parsed_flag();
	             }
	           if (name.equals("a_address1"))
	         {
	                 return getA_address1();
	             }
	           if (name.equals("a_address2"))
	         {
	                 return getA_address2();
	             }
	           if (name.equals("a_address3"))
	         {
	                 return getA_address3();
	             }
	           if (name.equals("a_city"))
	         {
	                 return getA_city();
	             }
	           if (name.equals("a_state"))
	         {
	                 return getA_state();
	             }
	           if (name.equals("a_zip_code"))
	         {
	                 return getA_zip_code();
	             }
	           if (name.equals("a_address_type"))
	         {
	                 return getA_address_type();
	             }
	           if (name.equals("a_current_address"))
	         {
	                 return getA_current_address();
	             }
	           if (name.equals("a_effective_date"))
	         {
	                 return getA_effective_date();
	             }
	           if (name.equals("a_parsed_flag"))
	         {
	                 return getA_parsed_flag();
	             }
	           if (name.equals("info_title"))
	         {
	                 return getInfo_title();
	             }
	           if (name.equals("info_desc"))
	         {
	                 return getInfo_desc();
	             }
	           if (name.equals("history_pages"))
	         {
	                 return getHistory_pages();
	             }
	           if(name.equals("history_code"))
	           {
	        	   return getHistory_code();
	        	   
	           }
	           if(name.equals("history_desc"))
	           {
	        	   return getHistory_desc();
	        	   
	           }
	           if(name.equals("effective_date"))
	           {
	        	   return getEffective_date();
	           }
	           if(name.equals("history_num"))
	           {
	        	   return getHistory_num();
	        	   
	           }
	           if(name.equals("history_name"))
	           {
	        	   return getHistory_name();
	           }
	           if(name.equals("locator_num"))
	           {
	        	   return getLocator_num();
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
	    
	    public boolean setByName(String name,Object value)throws TorqueException, IllegalArgumentException
	    {
	    	 if (name.equals("ResultId"))
	         {
	    		  if (value == null || ! (Long.class.isInstance(value)))
	              {
	                  throw new IllegalArgumentException("setByName: value parameter was null or not a Long object.");
	              }
	              setResultId(((Long) value).longValue());
	                        return true;
	             }
	           if (name.equals("UserSearchId"))
	         {        if (value == null || ! (Long.class.isInstance(value)))
	            {
	                throw new IllegalArgumentException("setByName: value parameter was null or not a Long object.");
	            }
	            setUserSearchId(((Long) value).longValue());
	                      return true;
	             }
	           if (name.equals("filing_number"))
	         {
	        	   if (value != null && ! String.class.isInstance(value))
	               {
	                   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	               }
	        	   setFiling_number((String)value);
	                 return true;
	             }
	           if (name.equals("filing_date"))
	         {
	        	   if (value != null && ! String.class.isInstance(value))
             {
                 throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
             }
      	   		setFiling_date((String)value);
               return true;
	             }
	           if (name.equals("filing_state"))
	            {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setFiling_state((String)value);
	        	   return true;
	             }
	           if (name.equals("state"))
	         { 
	        	   if(value != null && !String.class.isInstance(value))
      	   {
      		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
      	   }
      	   setState((String)value);
      	   return true;
	             }
	           if (name.equals("corporation_id"))
	         { if(value != null && !String.class.isInstance(value))
      	   {
      		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
      	   }
      	   setCorporation_id((String)value);
      	   return true;
	             }
	           if (name.equals("corporation_name"))
	             {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCorporation_name((String)value);
	        	   return true;
	             }
	           if (name.equals("incorp_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setIncorp_date((String)value);
	        	   return true;
	             }
	           if (name.equals("corporation_status"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCorporation_status((String)value);
	        	   return true;
	             }
	           if (name.equals("corporation_status_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCorporation_status_date((String)value);
	        	   return true;
	             }
	           if (name.equals("corporation_type"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCorporation_type((String)value);
	        	   return true;
	             }
	           if (name.equals("bus_type"))
		         {
		        	   if(value != null && !String.class.isInstance(value))
		        	   {
		        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
		        	   }
		        	   setBus_type((String)value);
		        	   return true;
		             }
	           if (name.equals("originated_state"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setOriginated_state((String)value);
	        	   return true;
	             }
	           if (name.equals("county"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCounty((String)value);
	        	   return true;
	             }
	           if (name.equals("federal_tax_id"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setFederal_tax_id((String)value);
	        	   return true;
	             }
	           if (name.equals("duration_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setDuration_date((String)value);
	        	   return true;
	             }
	          
	           if (name.equals("corporation_name_type_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCorporation_name_type_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("cn_corporation_name1"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCn_corporation_name1((String)value);
	        	   return true;
	             }
	           if (name.equals("cn_effective_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCn_effective_date((String)value);
	        	   return true;
	             }
	           if (name.equals("name"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setName((String)value);
	        	   return true;
	             }
	           if (name.equals("name_type"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setName_type((String)value);
	        	   return true;
	             }
	           if (name.equals("current_active"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCurrent_active((String)value);
	        	   return true;
	             }
	           if (name.equals("na_address1"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setNa_address1((String)value);
	        	   return true;
	             }
	           if (name.equals("na_address2"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setNa_address2((String)value);
	        	   return true;
	             }
	           if (name.equals("na_address3"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setNa_address3((String)value);
	        	   return true;
	             }
	           if (name.equals("na_city"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setNa_city((String)value);
	        	   return true;
	             }
	           if (name.equals("na_state"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setNa_state((String)value);
	        	   return true;
	             }
	           if (name.equals("na_zip_code"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setNa_zip_code((String)value);
	        	   return true;
	             }
	           if (name.equals("na_address_type"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setNa_address_type((String)value);
	        	   return true;
	             }
	           if (name.equals("na_current_address"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setNa_current_address((String)value);
	        	   return true;
	             }
	           if (name.equals("na_parsed_flag"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setNa_parsed_flag((String)value);
	        	   return true;
	             }
	           if (name.equals("a_address1"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setA_address1((String)value);
	        	   return true;
	             }
	           if (name.equals("a_address2"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setA_address2((String)value);
	        	   return true;
	             }
	           if (name.equals("a_address3"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setA_address3((String)value);
	        	   return true;
	             }
	           if (name.equals("a_city"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setA_city((String)value);
	        	   return true;
	             }
	           if (name.equals("a_state"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setA_state((String)value);
	        	   return true;
	             }
	           if (name.equals("a_zip_code"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setA_zip_code((String)value);
	        	   return true;
	             }
	           if (name.equals("a_address_type"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setA_address_type((String)value);
	        	   return true;
	             }
	           if (name.equals("a_current_address"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setA_current_address((String)value);
	        	   return true;
	             }
	           if (name.equals("a_effective_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setA_effective_date((String)value);
	        	   return true;
	             }
	           if (name.equals("a_parsed_flag"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setA_parsed_flag((String)value);
	        	   return true;
	             }
	           if (name.equals("info_title"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setInfo_title((String)value);
	        	   return true;
	             }
	           if (name.equals("info_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setInfo_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("history_pages"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setHistory_pages((String)value);
	        	   return true;
	             }
	           if(name.equals("history_code"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setHistory_code((String)value);
	        	   return true;
	        	   
	           }
	           if(name.equals("history_desc"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setHistory_desc((String)value);
	        	   return true;
	        	   
	           }
	           if(name.equals("effective_date"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setEffective_date((String)value);
	        	   return true;
	           }
	           if(name.equals("history_num"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setHistory_num((String)value);
	        	   return true;
	        	   
	           }
	           if(name.equals("history_name"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setHistory_name((String)value);
	        	   return true;
	           }
	           if(name.equals("locator_num"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setLocator_num((String)value);
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
	          if (name.equals(CorporationResultsPeer.RESULT_ID))
	        {
	                return new Long(getResultId());
	            }
	          if (name.equals(CorporationResultsPeer.USER_SEARCH_ID))
	        {
	                return new Long(getUserSearchId());
	            }
	          if (name.equals(CorporationResultsPeer.FILING_NUMBER))
	        {
	                return getFiling_number();
	            }
	          if (name.equals(CorporationResultsPeer.FILING_DATE))
	        {
	                return getFiling_date();
	            }
	          if (name.equals(CorporationResultsPeer.FILING_STATE))
	        {
	                return getFiling_state();
	            }
	          if (name.equals(CorporationResultsPeer.STATE))
	        {
	                return getState();
	            }
	          if (name.equals(CorporationResultsPeer.CORPORATION_ID))
	        {
	                return getCorporation_id();
	            }
	          if (name.equals(CorporationResultsPeer.CORPORATION_NAME))
	        {
	                return getCorporation_name();
	            }
	          if (name.equals(CorporationResultsPeer.INCORP_DATE))
	        {
	                return getIncorp_date();
	            }
	          if (name.equals(CorporationResultsPeer.CORPORATION_STATUS))
	        {
	                return getCorporation_status();
	            }
	          if (name.equals(CorporationResultsPeer.CORPORATION_STATUS_DATE))
	        {
	                return getCorporation_status_date();
	            }
	          if (name.equals(CorporationResultsPeer.CORPORATION_TYPE))
	        {
	                return getCorporation_type();
	            }
	          if (name.equals(CorporationResultsPeer.BUS_TYPE))
		        {
		                return getBus_type();
		            }
	          if (name.equals(CorporationResultsPeer.ORIGINATED_STATE))
	        {
	                return getOriginated_state();
	            }
	          if (name.equals(CorporationResultsPeer.COUNTY))
	        {
	                return getCounty();
	            }
	          if (name.equals(CorporationResultsPeer.FEDERAL_TAX_ID))
	        {
	                return getFederal_tax_id();
	            }
	          if (name.equals(CorporationResultsPeer.DURATION_DATE))
	        {
	                return getDuration_date();
	            }
	         
	          if (name.equals(CorporationResultsPeer.CORPORATION_NAME_TYPE_DESC))
	        {
	                return getCorporation_name_type_desc();
	            }
	          if (name.equals(CorporationResultsPeer.CN_CORPORATION_NAME1))
	        {
	                return getCn_corporation_name1();
	            }
	         
	          if (name.equals(CorporationResultsPeer.CN_EFFECTIVE_DATE))
	        {
	                return getCn_effective_date();
	            }
	          if (name.equals(CorporationResultsPeer.NAME))
	        {
	                return getName();
	            }
	          if (name.equals(CorporationResultsPeer.NAME_TYPE))
		        {
		                return getName_type();
		            }
	          if (name.equals(CorporationResultsPeer.CURRENT_ACTIVE))
	        {
	                return getCurrent_active();
	            }
	          if (name.equals(CorporationResultsPeer.NA_ADDRESS1))
	        {
	                return getNa_address1();
	            }
	          if (name.equals(CorporationResultsPeer.NA_ADDRESS2))
	        {
	                return getNa_address2();
	            }
	          if (name.equals(CorporationResultsPeer.NA_ADDRESS3))
	        {
	                return getNa_address3();
	            }
	          if (name.equals(CorporationResultsPeer.NA_CITY))
	        {
	                return getNa_city();
	            }
	          if (name.equals(CorporationResultsPeer.NA_STATE))
	        {
	                return getNa_state();
	            }
	          if (name.equals(CorporationResultsPeer.NA_ZIP_CODE))
	        {
	                return getNa_zip_code();
	            }
	          if (name.equals(CorporationResultsPeer.NA_ADDRESS_TYPE))
	        {
	                return getNa_address_type();
	            }
	          if (name.equals(CorporationResultsPeer.NA_CURRENT_ADDRESS))
	        {
	                return getNa_current_address();
	            }
	          if (name.equals(CorporationResultsPeer.NA_PARSED_FLAG))
	        {
	                return getNa_parsed_flag();
	            }
	          if (name.equals(CorporationResultsPeer.A_ADDRESS1))
		        {
		                return getA_address1();
		            }
		          if (name.equals(CorporationResultsPeer.A_ADDRESS2))
		        {
		                return getA_address2();
		            }
		          if (name.equals(CorporationResultsPeer.A_ADDRESS3))
		        {
		                return getA_address3();
		            }
		          if (name.equals(CorporationResultsPeer.A_CITY))
		        {
		                return getA_city();
		            }
		          if (name.equals(CorporationResultsPeer.A_STATE))
		        {
		                return getA_state();
		            }
		          if (name.equals(CorporationResultsPeer.A_ZIP_CODE))
		        {
		                return getA_zip_code();
		            }
		          if (name.equals(CorporationResultsPeer.A_ADDRESS_TYPE))
		        {
		                return getA_address_type();
		            }
		          if (name.equals(CorporationResultsPeer.A_CURRENT_ADDRESS))
		        {
		                return getA_current_address();
		            }
		          if (name.equals(CorporationResultsPeer.A_EFFECTIVE_DATE))
			        {
			                return getA_effective_date();
			            }
		          if (name.equals(CorporationResultsPeer.A_PARSED_FLAG))
		        {
		                return getA_parsed_flag();
		            }
		         
	          if (name.equals(CorporationResultsPeer.INFO_TITLE))
	        {
	                return getInfo_title();
	            }
	          if (name.equals(CorporationResultsPeer.INFO_DESC))
	        {
	                return getInfo_desc();
	            }
	          if (name.equals(CorporationResultsPeer.HISTORY_PAGES))
	        {
	                return getHistory_pages();
	            }
	          if (name.equals(CorporationResultsPeer.HISTORY_CODE))
	        {
	                return getHistory_code();
	            }
	          if (name.equals(CorporationResultsPeer.HISTORY_DESC))
	        {
	                return getHistory_desc();
	            }
	          if (name.equals(CorporationResultsPeer.EFFECTIVE_DATE))
	        {
	                return getEffective_date();
	            }
	          if (name.equals(CorporationResultsPeer.HISTORY_NUM))
	        {
	                return getHistory_num();
	                
	            }
	          if (name.equals(CorporationResultsPeer.HISTORY_NAME))
	        {
	                return getHistory_name();
	            }
	          if (name.equals(CorporationResultsPeer.LOCATOR_NUM))
	        {
	                return getLocator_num();
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
	        if (CorporationResultsPeer.RESULT_ID.equals(name))
	        {
	            return setByName("ResultId", value);
	        }
	        if (CorporationResultsPeer.USER_SEARCH_ID.equals(name))
	        {
	            return setByName("UserSearchId", value);
	        }
	        if (CorporationResultsPeer.FILING_NUMBER.equals(name))
	        {
	                return setByName("filing_number", value);
	            }
	          if (CorporationResultsPeer.FILING_DATE.equals(name))
	        {
	                return setByName("filing_date", value);
	            }
	          if (CorporationResultsPeer.FILING_STATE.equals(name))
	        {
	                return setByName("filing_state", value);
	            }
	          if (CorporationResultsPeer.STATE.equals(name))
	        {
	                return setByName("state", value);
	            }
	          if (CorporationResultsPeer.CORPORATION_ID.equals(name))
	        {
	                return setByName("corporation_id", value);
	            }
	          if (CorporationResultsPeer.CORPORATION_NAME.equals(name))
	        {
	                return setByName("corporation_name", value);
	            }
	          if (CorporationResultsPeer.INCORP_DATE.equals(name))
	        {
	                return setByName("incorp_date", value);
	            }
	          if (CorporationResultsPeer.CORPORATION_STATUS.equals(name))
	        {
	                return setByName("corporation_status", value);
	            }
	          if (CorporationResultsPeer.CORPORATION_STATUS_DATE.equals(name))
	        {
	                return setByName("corporation_status_date", value);
	            }
	          if (CorporationResultsPeer.CORPORATION_TYPE.equals(name))
	        {
	                return setByName("corporation_type", value);
	            }
	          if (CorporationResultsPeer.BUS_TYPE.equals(name))
		        {
		                return setByName("bus_type", value);
		            }
	          if (CorporationResultsPeer.ORIGINATED_STATE.equals(name))
	        {
	                return setByName("originated_state", value);
	            }
	          if (CorporationResultsPeer.COUNTY.equals(name))
	        {
	                return setByName("county", value);
	            }
	          if (CorporationResultsPeer.FEDERAL_TAX_ID.equals(name))
	        {
	                return setByName("federal_tax_id", value);
	            }
	          if (CorporationResultsPeer.DURATION_DATE.equals(name))
	        {
	                return setByName("duration_date", value);
	            }
	          
	          if (CorporationResultsPeer.CORPORATION_NAME_TYPE_DESC.equals(name))
	        {
	                return setByName("corporation_name_type_desc", value);
	            }
	          if (CorporationResultsPeer.CN_CORPORATION_NAME1.equals(name))
	        {
	                return setByName("cn_corporation_name1", value);
	            }
	         
	          if (CorporationResultsPeer.CN_EFFECTIVE_DATE.equals(name))
	        {
	                return setByName("cn_effective_date", value);
	            }
	          if (CorporationResultsPeer.NAME.equals(name))
	        {
	                return setByName("name", value);
	            }
	          if (CorporationResultsPeer.NAME_TYPE.equals(name))
		        {
		                return setByName("name_type", value);
		            }
	          if (CorporationResultsPeer.CURRENT_ACTIVE.equals(name))
	        {
	                return setByName("current_active", value);
	            }
	          if (CorporationResultsPeer.NA_ADDRESS1.equals(name))
	        {
	                return setByName("na_address1", value);
	            }
	          if (CorporationResultsPeer.NA_ADDRESS2.equals(name))
	        {
	                return setByName("na_address2", value);
	            }
	          if (CorporationResultsPeer.NA_ADDRESS3.equals(name))
	        {
	                return setByName("na_address3", value);
	            }
	          if (CorporationResultsPeer.NA_CITY.equals(name))
	        {
	                return setByName("na_city", value);
	            }
	          if (CorporationResultsPeer.NA_STATE.equals(name))
	        {
	                return setByName("na_state", value);
	            }
	          if (CorporationResultsPeer.NA_ZIP_CODE.equals(name))
	        {
	                return setByName("na_zip_code", value);
	            }
	          if (CorporationResultsPeer.NA_ADDRESS_TYPE.equals(name))
	        {
	                return setByName("na_address_type", value);
	            }
	          if (CorporationResultsPeer.NA_CURRENT_ADDRESS.equals(name))
	        {
	                return setByName("na_current_address", value);
	            }
	          if (CorporationResultsPeer.NA_PARSED_FLAG.equals(name))
	        {
	                return setByName("na_parsed_flag", value);
	            }
	          if (CorporationResultsPeer.A_ADDRESS1.equals(name))
		        {
		                return setByName("a_address1", value);
		            }
		          if (CorporationResultsPeer.A_ADDRESS2.equals(name))
		        {
		                return setByName("a_address2", value);
		            }
		          if (CorporationResultsPeer.A_ADDRESS3.equals(name))
		        {
		                return setByName("a_address3", value);
		            }
		          if (CorporationResultsPeer.A_CITY.equals(name))
		        {
		                return setByName("a_city", value);
		            }
		          if (CorporationResultsPeer.A_STATE.equals(name))
		        {
		                return setByName("a_state", value);
		            }
		          if (CorporationResultsPeer.A_ZIP_CODE.equals(name))
		        {
		                return setByName("a_zip_code", value);
		            }
		          if (CorporationResultsPeer.A_ADDRESS_TYPE.equals(name))
		        {
		                return setByName("A_address_type", value);
		            }
		          if (CorporationResultsPeer.A_CURRENT_ADDRESS.equals(name))
		        {
		                return setByName("a_current_address", value);
		            }
		          if (CorporationResultsPeer.A_EFFECTIVE_DATE.equals(name))
			        {
			                return setByName("a_effective_date", value);
			            }
		          if (CorporationResultsPeer.A_PARSED_FLAG.equals(name))
		        {
		                return setByName("a_parsed_flag", value);
		            }
		         
	          if (CorporationResultsPeer.INFO_TITLE.equals(name))
	        {
	                return setByName("info_title", value);
	            }
	          if (CorporationResultsPeer.INFO_DESC.equals(name))
	        {
	                return setByName("info_desc", value);
	            }
	          if (CorporationResultsPeer.HISTORY_PAGES.equals(name))
	        {
	                return setByName("history_pages", value);
	            }
	          if (CorporationResultsPeer.HISTORY_CODE.equals(name))
	        {
	                return setByName("history_code", value);
	            }
	          if (CorporationResultsPeer.HISTORY_DESC.equals(name))
	        {
	                return setByName("history_desc", value);
	            }
	          if (CorporationResultsPeer.EFFECTIVE_DATE.equals(name))
	        {
	                return setByName("effective_date", value);
	            }
	          if (CorporationResultsPeer.HISTORY_NUM.equals(name))
	        {
	                return setByName("history_num", value);
	                
	            }
	          if (CorporationResultsPeer.HISTORY_NAME.equals(name))
	        {
	                return setByName("history_name", value);
	            }
	          if (CorporationResultsPeer.LOCATOR_NUM.equals(name))
	        {
	                return setByName("locator_num", value);
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
	                return new Long(getResultId());
	            }
	              if (pos == 1)
	        {
	                return new Long(getUserSearchId());
	            }
	              if (pos == 2)
	        {
	                return getFiling_number();
	            }
	              if (pos == 3)
	        {
	                return getFiling_date();
	            }
	              if (pos == 4)
	        {
	                return getFiling_state();
	            }
	              if (pos == 5)
	        {
	                return getState();
	            }
	              if (pos == 6)
	        {
	                return getCorporation_id();
	            }
	              if (pos == 7)
	        {
	                return getCorporation_name();
	            }
	              if (pos == 8)
	        {
	                return getIncorp_date();
	            }
	              if (pos == 9)
	        {
	                return getCorporation_status();
	            }
	              if (pos == 10)
	        {
	                return getCorporation_status_date();
	            }
	              if (pos == 11)
	        {
	                return getCorporation_type();
	            }
	              if (pos == 12)
	  	        {
	  	                return getBus_type();
	  	            }
	              if (pos == 13)
	        {
	                return getOriginated_state();
	            }
	              if (pos == 14)
	        {
	                return getCounty();
	            }
	              if (pos == 15)
	        {
	                return getFederal_tax_id();
	            }
	              if (pos == 16)
	        {
	                return getDuration_date();
	            }
	            
	              if (pos == 17)
	        {
	                return getCorporation_name_type_desc();
	            }
	              if (pos == 18)
	        {
	                return getCn_corporation_name1();
	            }
	              if (pos == 19)
	        {
	                return getCn_effective_date();
	            }
	              if (pos == 20)
	        {
	                return getName();
	            }
	              if (pos == 21)
	        {
	                return getName_type();
	            }
	              if (pos == 22)
	        {
	                return getCurrent_active();
	            }
	              if (pos == 23)
	        {
	                return getNa_address1();
	            }
	              if (pos == 24)
	        {
	                return getNa_address2();
	            }
	              if (pos == 25)
	        {
	                return getNa_address3();
	            }
	              if (pos == 26)
	        {
	                return getNa_city();
	            }
	              if (pos == 27)
	        {
	                return getNa_state();
	            }
	              if (pos == 28)
	        {
	                return getNa_zip_code();
	            }
	              if (pos == 29)
	        {
	                return getNa_address_type();
	            }
	              if (pos == 30)
	        {
	                return getNa_current_address();
	            }
	              if (pos == 31)
	        {
	                return getNa_parsed_flag();
	            }
	              if (pos == 32)
	        {
	                return getA_address1();
	            }
	              if (pos == 33)
	        {
	                return getA_address2();
	            }
	              if (pos == 34)
	        {
	                return getA_address3();
	            }
	              if (pos == 35)
	        {
	                return getA_city();
	            }
	              if (pos == 36)
	        {
	                return getA_state();
	            }
	              if (pos == 37)
	        {
	                return getA_zip_code();
	            }
	              if (pos == 38)
	        {
	                return getA_address_type();
	            }
	              if (pos == 39)
	        {
	                return getA_current_address();
	            }
	              if (pos == 40)
	        {
	                return getA_effective_date();
	            }
	              if (pos == 41)
	        {
	                return getA_parsed_flag();
	            }
	              if (pos == 42)
	        {
	                return getInfo_title();
	            }
	              if (pos == 43)
	        {
	                return getInfo_desc();
	            }
	              if (pos == 44)
	        {
	                return getHistory_pages();
	            }
	              if(pos == 45){
	            	  return getHistory_code();
	              }
	              if(pos == 46){
	            	  return getHistory_desc();
	              }
	              if(pos == 47){
	            	  return getEffective_date();
	              }
	              if(pos == 48){
	            	  return getHistory_num();
	              }
	              if(pos == 49){
	            	  return getHistory_name();
	              }
	              if(pos == 50){
	            	  return getLocator_num();
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
	            return setByName("ResultId", value);
	        }
	          if (position == 1)
	        {
	            return setByName("UserSearchId", value);
	        }
		              if (position == 2)
		        {
		            	  return setByName("filing_number", value);
		            }
		              if (position == 3)
		        {
		            	  return setByName("filing_date", value);
		            }
		              if (position == 4)
		        {
		            	  return setByName("filing_state", value);
		            }
		              if (position == 5)
		        {
		            	  return setByName("state", value);
		            }
		              if (position == 6)
		        {
		            	  return setByName("corporation_id", value);
		            }
		              if (position == 7)
		        {
		            	  return setByName("corporation_name", value);
		            }
		              if (position == 8)
		        {
		            	  return setByName("incorp_date", value);
		            }
		              if (position == 9)
		        {
		            	  return setByName("corporation_status", value);
		            }
		              if (position == 10)
		        {
		            	  return setByName("corporation_status_date", value);
		            }
		              if (position == 11)
		        {
		            	  return setByName("corporation_type", value);
		            }
		              if (position == 12)
				        {
				           return setByName("bus_type", value);
				          }
		              if (position == 13)
		        {
		            	  return setByName("originated_state", value);
		            }
		              if (position == 14)
		        {
		            	  return setByName("county", value);
		            }
		              if (position == 15)
		        {
		            	  return setByName("federal_tax_id", value);
		            }
		              if (position == 16)
		        {
		            	  return setByName("duration_date", value);
		            }
		             
		              if (position == 17)
		        {
		            	  return setByName("corporation_name_type_desc", value);
		            }
		              if (position == 18)
		        {
		            	  return setByName("cn_corporation_name1", value);
		            }
		              if (position == 19)
		        {
		            	  return setByName("cn_effective_date", value);
		            }
		              if (position == 20)
		        {
		            	  return setByName("name", value);
		            }
		              if (position == 21)
		        {
		            	  return setByName("name_type", value);
		            }
		              if (position == 22)
		        {
		            	  return setByName("current_active", value);
		            }
		              if (position == 23)
		        {
		            	  return setByName("na_address1", value);
		            }
		              if (position == 24)
		        {
		            	  return setByName("na_address2", value);
		            }
		              if (position == 25)
		        {
		            	  return setByName("na_address3", value);
		            }
		              if (position == 26)
		        {
		            	  return setByName("na_city", value);
		            }
		              if (position == 27)
		        {
		            	  return setByName("na_state", value);
		            }
		              if (position == 28)
		        {
		            	  return setByName("na_zip_code", value);
		            }
		              if (position == 29)
		        {
		            	  return setByName("na_address_type", value);
		            }
		              if (position == 30)
		        {
		            	  return setByName("na_current_address", value);
		            }
		              if (position == 31)
		        {
		            	  return setByName("na_parsed_flag", value);
		            }
		              if (position == 32)
		        {
		            	  return setByName("a_address1", value);
		            }
		              if (position == 33)
		        {
		            	  return setByName("a_address2", value);
		            }
		              if (position == 34)
		        {
		            	  return setByName("a_address3", value);
		            }
		              if (position == 35)
		        {
		            	  return setByName("a_city", value);
		            }
		              if (position == 36)
		        {
		            	  return setByName("a_state", value);
		            }
		              if (position == 37)
		        {
		            	  return setByName("a_zip_code", value);
		            }
		              if (position == 38)
		        {
		            	  return setByName("a_address_type", value);
		            }
		              if (position == 39)
		        {
		            	  return setByName("a_current_address", value);
		            }
		              if (position == 40)
		        {
		            	  return setByName("a_effective_date", value);
		            }
		              if (position == 41)
		        {
		            	  return setByName("a_parsed_flag", value);
		            }
		              if (position == 42)
		        {
		            	  return setByName("info_title", value);
		            }
		              if (position == 43)
		        {
		            	  return setByName("info_desc", value);
		            }
		              if (position == 44)
		        {
		            	  return setByName("history_pages", value);
		            }
		              if(position == 45){
		            	  return setByName("history_code", value);
		              }
		              if(position == 46){
		            	  return setByName("history_desc", value);
		              }
		              
		              if(position == 47){
		            	  return setByName("effective_date", value);
		              }
		              if(position == 48){
		            	  return setByName("history_num", value);
		              }
		              if(position == 49){
		            	  return setByName("history_name", value);
		              }
		              
		              
		              if(position == 50){
		            	  return setByName("locator_num", value);
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
	          save(CorporationResultsPeer.DATABASE_NAME);
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
	                    CorporationResultsPeer.doInsert((CorporationResults) this, con);
	                    setNew(false);
	                }
	                else
	                {
	                    CorporationResultsPeer.doUpdate((CorporationResults) this, con);
	                }
	                }

	                      alreadyInSave = false;
	        }
	      }

	                  
	      /**
	     * Set the PrimaryKey using ObjectKey.
	     *
	     * @param key resultId ObjectKey
	     */
	    public void setPrimaryKey(ObjectKey key)
	        
	    {
	            setResultId(((NumberKey) key).longValue());
	        }

	    /**
	     * Set the PrimaryKey using a String.
	     *
	     * @param key
	     */
	    public void setPrimaryKey(String key) 
	    {
	            setResultId(Long.parseLong(key));
	        }

	  
	    /**
	     * returns an id that differentiates this object from others
	     * of its class.
	     */
	    public ObjectKey getPrimaryKey()
	    {
	          return SimpleKey.keyFor(getResultId());
	      }
	 

	    /**
	     * Makes a copy of this object.
	     * It creates a new object filling in the simple attributes.
	       * It then fills all the association collections and sets the
	     * related objects to isNew=true.
	       */
	      public CorporationResults copy() throws TorqueException
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
	    public CorporationResults copy(boolean deepcopy) throws TorqueException
	    {
	        return copyInto(new CorporationResults(), deepcopy);
	    }
	      
	      /**
	     * Fills the copyObj with the contents of this object.
	     * The associated objects are also copied and treated as new objects.
	     * @param copyObj the object to fill.
	     */
	    protected CorporationResults copyInto(CorporationResults copyObj) throws TorqueException
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
	      protected CorporationResults copyInto(CorporationResults copyObj, boolean deepcopy) throws TorqueException
	      {
	          copyObj.setResultId(resultId);
	          copyObj.setUserSearchId(userSearchId);
	          copyObj.setFiling_number(filing_number);
	          copyObj.setFiling_date(filing_date);
	          copyObj.setFiling_state(filing_state);
	          copyObj.setState(state);
	          copyObj.setCorporation_id(corporation_id);
	          copyObj.setCorporation_name(corporation_name);
	          copyObj.setIncorp_date(incorp_date);
	          copyObj.setCorporation_status(corporation_status);
	          copyObj.setCorporation_status_date(corporation_status_date);
	          copyObj.setCorporation_type(corporation_type);
	          copyObj.setBus_type(bus_type);
	          copyObj.setOriginated_state(originated_state);
	          copyObj.setCounty(county);
	          copyObj.setFederal_tax_id(federal_tax_id);
	          copyObj.setDuration_date(duration_date);
	          copyObj.setCorporation_name_type_desc(corporation_name_type_desc);
	          copyObj.setCn_corporation_name1(cn_corporation_name1);
	          copyObj.setCn_effective_date(cn_effective_date);
	          copyObj.setName(name);
	          copyObj.setName_type(name_type);
	          copyObj.setCurrent_active(current_active);
	          copyObj.setNa_address1(na_address1);
	          copyObj.setNa_address2(na_address2);
	          copyObj.setNa_address3(na_address3);
	          copyObj.setNa_city(na_city);
	          copyObj.setNa_state(na_state);
	          copyObj.setNa_zip_code(na_zip_code);
	          copyObj.setNa_address_type(na_address_type);
	          copyObj.setNa_current_address(na_current_address);
	          copyObj.setNa_parsed_flag(na_parsed_flag);
	          copyObj.setA_address1(a_address1);
	          copyObj.setA_address2(a_address2);
	          copyObj.setA_address3(a_address3);
	          copyObj.setA_city(a_city);
	          copyObj.setA_state(a_state);
	          copyObj.setA_zip_code(a_zip_code);
	          copyObj.setA_address_type(a_address_type);
	          copyObj.setA_current_address(a_current_address);
	          copyObj.setA_effective_date(a_effective_date);
	          copyObj.setNa_parsed_flag(a_parsed_flag);
	          copyObj.setInfo_title(info_title);
	          copyObj.setInfo_desc(info_desc);
	          copyObj.setHistory_pages(history_pages);
	          copyObj.setHistory_code(history_code);
	          copyObj.setHistory_desc(history_desc);
	          copyObj.setEffective_date(effective_date);
	          copyObj.setHistory_num(history_num);
	          copyObj.setHistory_name(history_name);
	          copyObj.setLocator_num(locator_num);
	          	                            copyObj.setResultId( 0);
	                                                                                                                                                                                                                                                                                          
	          if (deepcopy) 
	        {
	            }
	          return copyObj;
	    }

	    /**
	     * returns a peer instance associated with this om.  Since Peer classes
	     * are not to have any instance attributes, this method returns the
	     * same instance for all member of this class. The method could therefore
	     * be static, but this would prevent one from overriding the behavior.
	     */
	    public CorporationResultsPeer getPeer()
	    {
	        return peer;
	    	//return pee
	    }

	    /**
	     * Retrieves the TableMap object related to this Table data without
	     * compiler warnings of using getPeer().getTableMap().
	     *
	     * @return The associated TableMap object.
	     */
	    public TableMap getTableMap() throws TorqueException
	    {
	        return CorporationResultsPeer.getTableMap();
	    }

	  
	    /**
	     * Creates a BjlResultsBean with the contents of this object
	       * This also creates beans for cached related objects, if they exist
	       * @return a BjlResultsBean with the contents of this object
	     */
	    public BaseCorporationResultsBean getBean()
	    {
	        return getBean(new IdentityMap());
	    }

	    /**
	     * Creates a BjlResultsBean with the contents of this object
	     * intended for internal use only
	     * @param createdBeans a IdentityMap which maps objects
	     *        to already created beans
	     * @return a BjlResultsBean with the contents of this object
	     */
	    public BaseCorporationResultsBean getBean(IdentityMap createdBeans)
	    {
	    	BaseCorporationResultsBean result = (BaseCorporationResultsBean) createdBeans.get(this);
	        if (result != null ) {
	            // we have already created a bean for this object, return it
	            return result;
	        }
	        // no bean exists for this object; create a new one
	        result = new BaseCorporationResultsBean();
	        createdBeans.put(this, result);

	        result.setResultId(getResultId());
	          result.setUserSearchId(getUserSearchId());
				
	          result.setFiling_number(getFiling_number());
	          result.setFiling_date(getFiling_date());
	          result.setFiling_state(getFiling_state());
	          result.setState(getState());
	          result.setCorporation_id(getCorporation_id());
	          result.setCorporation_name(getCorporation_name());
	          result.setIncorp_date(getIncorp_date());
	          result.setCorporation_status(getCorporation_status());
	          result.setCorporation_status_date(getCorporation_status_date());
	          result.setCorporation_type(getCorporation_type());
	          result.setBus_type(getBus_type());
	          result.setOriginated_state(getOriginated_state());
	          result.setCounty(getCounty());
	          result.setFederal_tax_id(getFederal_tax_id());
	          result.setDuration_date(getDuration_date());
	          result.setCorporation_name_type_desc(getCorporation_name_type_desc());
	          result.setCn_corporation_name1(getCn_corporation_name1());
	          result.setCn_effective_date(getCn_effective_date());
	          result.setName(getName());
	          result.setName_type(getName_type());
	          result.setCurrent_active(getCurrent_active());
	          result.setNa_address1(getNa_address1());
	          result.setNa_address2(getNa_address2());
	          result.setNa_address3(getNa_address3());
	          result.setNa_city(getNa_city());
	          result.setNa_state(getNa_state());
	          result.setNa_zip_code(getNa_zip_code());
	          result.setNa_address_type(getNa_address_type());
	          result.setNa_current_address(getNa_current_address());
	          result.setNa_parsed_flag(getNa_parsed_flag());
	          result.setA_address1(getA_address1());
	          result.setA_address2(getA_address2());
	          result.setA_address3(getA_address3());
	          result.setA_city(getA_city());
	          result.setA_state(getA_state());
	          result.setA_zip_code(getA_zip_code());
	          result.setA_address_type(getA_address_type());
	          result.setA_current_address(getA_current_address());
	          result.setA_effective_date(getA_effective_date());
	          result.setNa_parsed_flag(getA_parsed_flag());
	          result.setInfo_title(getInfo_title());
	          result.setInfo_desc(getInfo_desc());
	          result.setHistory_pages(getHistory_pages());
	          result.setHistory_code(getHistory_code());
	          result.setHistory_desc(getHistory_desc());
	          result.setEffective_date(getEffective_date());
	          result.setHistory_num(getHistory_num());
	          result.setHistory_name(getHistory_name());
	          result.setLocator_num(getLocator_num());
	                    
	        
	      
	                                
	              if (aCorporationSearches != null)
	        {
	            CorporationSearchesBean relatedBean = aCorporationSearches.getBean(createdBeans);
	            result.setBjlSearchesBean(relatedBean);
	        }
	              result.setModified(isModified());
	        result.setNew(isNew());
	        return result;
	    }

	    /**
	     * Creates an instance of BjlResults with the contents
	     * of a NewBaseBJLResultsBean.
	     * This behaviour could have also been achieved using a constructor,
	     * however as this class is abstract no constructors are allowed
	     * @param bean the NewBaseBJLResultsBean which contents are used to create
	     *        the resulting class
	     * @return an instance of BjlResults with the contents of bean
	     */
	    public static CorporationResults createBjlResults(BaseCorporationResultsBean bean)
	        throws TorqueException
	    {
	        return createBjlResults(bean, new IdentityMap());
	    }

	    /**
	     * Creates an instance of BjlResults with the contents
	     * of a NewBaseBJLResultsBean.
	     * This behaviour could have also been achieved using a constructor,
	     * however as this class is abstract no constructors are allowed.
	     *
	     * This method is intended for internal use only.
	     * @param bean the NewBaseBJLResultsBean which contents are used to create
	     *        the resulting class
	     * @param createdObjects a IdentityMap which maps beans
	     *        to already created objects
	     * @return an instance of BjlResults with the contents of bean
	     */

	    public static CorporationResults createBjlResults(BaseCorporationResultsBean bean, IdentityMap createdObjects)
	        throws TorqueException
	    {
	    	CorporationResults result = (CorporationResults) createdObjects.get(bean);
	        if (result != null)
	        {
	            // we already have an object for the bean, return it
	            return result;
	        }
	        result = new CorporationResults();
	        createdObjects.put(bean, result);

	          result.setResultId(bean.getResultId());
	          result.setUserSearchId(bean.getUserSearchId());
	          result.setFiling_number(bean.getFiling_number());
	          result.setFiling_date(bean.getFiling_date());
	          result.setFiling_state(bean.getFiling_state());
	          result.setState(bean.getState());
	          result.setCorporation_id(bean.getCorporation_id());
	          result.setCorporation_name(bean.getCorporation_name());
	          result.setIncorp_date(bean.getIncorp_date());
	          result.setCorporation_status(bean.getCorporation_status());
	          result.setCorporation_status_date(bean.getCorporation_status_date());
	          result.setCorporation_type(bean.getCorporation_type());
	          result.setBus_type(bean.getCorporation_type());
	          result.setOriginated_state(bean.getOriginated_state());
	          result.setCounty(bean.getCounty());
	          result.setFederal_tax_id(bean.getFederal_tax_id());
	          result.setDuration_date(bean.getDuration_date());
	          result.setCorporation_name_type_desc(bean.getCorporation_name_type_desc());
	          result.setCn_corporation_name1(bean.getCn_corporation_name1());
	          result.setCn_effective_date(bean.getCn_effective_date());
	          result.setName(bean.getName());
	          result.setName_type(bean.getName_type());
	          result.setCurrent_active(bean.getCurrent_active());
	          result.setNa_address1(bean.getNa_address1());
	          result.setNa_address2(bean.getNa_address2());
	          result.setNa_address3(bean.getNa_address3());
	          result.setNa_city(bean.getNa_city());
	          result.setNa_state(bean.getNa_state());
	          result.setNa_zip_code(bean.getNa_zip_code());
	          result.setNa_address_type(bean.getNa_address_type());
	          result.setNa_current_address(bean.getNa_current_address());
	          result.setNa_parsed_flag(bean.getNa_parsed_flag());
	          result.setA_address1(bean.getA_address1());
	          result.setA_address2(bean.getA_address2());
	          result.setA_address3(bean.getA_address3());
	          result.setA_city(bean.getA_city());
	          result.setA_state(bean.getA_state());
	          result.setA_zip_code(bean.getA_zip_code());
	          result.setA_address_type(bean.getA_address_type());
	          result.setA_current_address(bean.getA_current_address());
	          result.setA_effective_date(bean.getA_effective_date());
	          result.setNa_parsed_flag(bean.getA_parsed_flag());
	          result.setInfo_title(bean.getInfo_title());
	          result.setInfo_desc(bean.getInfo_desc());
	          result.setHistory_pages(bean.getHistory_pages());
	          result.setHistory_code(bean.getHistory_code());
	          result.setHistory_desc(bean.getHistory_desc());
	          result.setEffective_date(bean.getEffective_date());
	          result.setHistory_num(bean.getHistory_num());
	          result.setHistory_name(bean.getHistory_name());
	          result.setLocator_num(bean.getLocator_num());
	                    
	        
	      
	                                
	              {
	            CorporationSearchesBean relatedBean = bean.getBjlSearchesBean();
	            if (relatedBean != null)
	            {
	                CorporationSearches relatedObject = CorporationSearches.createBjlSearches(relatedBean, createdObjects);
	            	//CorporationSearches relatedObject = CorporationSearches.createBjlSearches(bean, createdObjects);
	                //result.setBjlSearches(relatedObject);
	                result.setCorporationSearches(relatedObject);
	            }
	        }
	          result.setModified(bean.isModified());
	             
	    result.setNew(bean.isNew());
	  	return result;
	    }

	                      

	    public String toString()
	    {
	        StringBuffer str = new StringBuffer();
	        str.append("CorporationResults:\n");
	        str.append("ResultId = ")
	               .append(getResultId())
	             .append("\n");
	        str.append("UserSearchId = ")
	               .append(getUserSearchId())
	             .append("\n");
	        str.append("filing_number = ")
	               .append(getFiling_number())
	             .append("\n");
	        str.append("filing_date = ")
	               .append(getFiling_date())
	             .append("\n");
	        str.append("filing_state = ")
	               .append(getFiling_number())
	             .append("\n");
	        str.append("state = ")
	               .append(getState())
	             .append("\n");
	        str.append("corporation_id = ")
	               .append(getCorporation_id())
	             .append("\n");
	        str.append("corporation_name = ")
	               .append(getCorporation_name())
	             .append("\n");
	        str.append("incorp_date = ")
	               .append(getIncorp_date())
	             .append("\n");
	        str.append("corporation_status = ")
	               .append(getCorporation_status())
	             .append("\n");
	        str.append("corporation_status_date = ")
	               .append(getCorporation_status_date())
	             .append("\n");
	        str.append("corporation_type = ")
	               .append(getCorporation_type())
	             .append("\n");
	        str.append("bus_type = ")
            		.append(getBus_type())
            		.append("\n");
	        str.append("originated_state = ")
	               .append(getOriginated_state())
	             .append("\n");
	        str.append("county = ")
	               .append(getCounty())
	             .append("\n");
	        str.append("federal_tax_id = ")
	               .append(getFederal_tax_id())
	             .append("\n");
	        str.append("duration_date = ")
	               .append(getDuration_date())
	             .append("\n");
	       str.append("corporation_name_type_desc = ")
	               .append(getCorporation_name_type_desc())
	             .append("\n");
	        str.append("cn_corporation_name1 = ")
	               .append(getCn_corporation_name1())
	             .append("\n");
	        str.append("cn_effective_date = ")
	               .append(getCn_effective_date())
	             .append("\n");
	        str.append("name = ")
	               .append(getName())
	             .append("\n");
	        str.append("name_type = ")
	               .append(getName_type())
	             .append("\n");
	        str.append("current_active = ")
	               .append(getCurrent_active())
	             .append("\n");
	        str.append("na_address1 = ")
	               .append(getNa_address1())
	             .append("\n");
	        str.append("na_address2 = ")
	               .append(getNa_address2())
	             .append("\n");
	        str.append("na_address3 = ")
	               .append(getNa_address3())
	             .append("\n");
	        str.append("na_city = ")
	               .append(getNa_city())
	             .append("\n");
	        str.append("na_state = ")
	               .append(getNa_state())
	             .append("\n");
	        str.append("na_zip_code = ")
	               .append(getNa_zip_code())
	             .append("\n");
	        str.append("na_address_type = ")
	               .append(getNa_address_type())
	             .append("\n");
	        str.append("na_current_address = ")
	               .append(getNa_current_address())
	             .append("\n");
	        str.append("na_parsed_flag = ")
	               .append(getNa_parsed_flag())
	             .append("\n");
	        str.append("a_address1 = ")
            	.append(getA_address1())
            	.append("\n");
	        str.append("a_address2 = ")
            	.append(getA_address2())
            	.append("\n");
	        str.append("a_address3 = ")
            	.append(getA_address3())
            	.append("\n");
	        str.append("a_city = ")
            	.append(getA_city())
            	.append("\n");
	        str.append("a_state = ")
            	.append(getA_state())
            	.append("\n");
	        str.append("a_zip_code = ")
            	.append(getA_zip_code())
            	.append("\n");
	        str.append("a_address_type = ")
            	.append(getNa_address_type())
            	.append("\n");
	        str.append("a_current_address = ")
            	.append(getA_current_address())
            	.append("\n");
	        str.append("a_effective_date = ")
        		.append(getA_effective_date())
        		.append("\n");
	        str.append("a_parsed_flag = ")
            	.append(getA_parsed_flag())
            	.append("\n");
	        str.append("info_title = ")
	               .append(getInfo_title())
	             .append("\n");
	        str.append("info_desc = ")
	               .append(getInfo_desc())
	             .append("\n");
	        str.append("history_pages = ")
	               .append(getHistory_pages())
	             .append("\n");
	        str.append("history_code = ")
	               .append(getHistory_code())
	             .append("\n");
	        str.append("history_desc = ")
	               .append(getHistory_desc())
	             .append("\n");
	        str.append("effective_date = ")
	               .append(getEffective_date())
	             .append("\n");
	        str.append("history_num = ")
	               .append(getHistory_num())
	             .append("\n");
	        str.append("history_name = ")
	               .append(getHistory_name())
	             .append("\n");
	        str.append("locator_num = ")
	               .append(getLocator_num())
	             .append("\n");
	        
	        return(str.toString());
	    }

}
