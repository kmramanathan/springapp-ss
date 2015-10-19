package springapp.domain.corporation;

import java.io.Serializable;



public class BaseCorporationResultsBean implements Serializable{
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
     * Get the ResultId
     *
     * @return long
     */
    public long getResultId ()
    {
        return resultId;
    }

    /**
     * Set the value of ResultId
     *
     * @param v new value
     */
    public void setResultId(long v)
    {
  
        this.resultId = v;
        setModified(true);

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

		
	 
	public String getFiling_number() {
		return filing_number;
	}

	public void setFiling_number(String filing_number) {
		this.filing_number = filing_number;
		setModified(true);
	}

	public String getFiling_date() {
		return filing_date;
	}

	public void setFiling_date(String filing_date) {
		this.filing_date = filing_date;
		setModified(true);
	}

	public String getFiling_state() {
		return filing_state;
	}

	public void setFiling_state(String filing_state) {
		this.filing_state = filing_state;
		setModified(true);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		setModified(true);
	}

	public String getCorporation_id() {
		return corporation_id;
	}

	public void setCorporation_id(String corporation_id) {
		this.corporation_id = corporation_id;
		setModified(true);
	}

	public String getCorporation_name() {
		return corporation_name;
	}

	public void setCorporation_name(String corporation_name) {
		this.corporation_name = corporation_name;
		setModified(true);
	}

	public String getIncorp_date() {
		return incorp_date;
	}

	public void setIncorp_date(String incorp_date) {
		this.incorp_date = incorp_date;
		setModified(true);
	}

	public String getCorporation_status() {
		return corporation_status;
	}

	public void setCorporation_status(String corporation_status) {
		this.corporation_status = corporation_status;
		setModified(true);
	}

	public String getCorporation_status_date() {
		return corporation_status_date;
	}

	public void setCorporation_status_date(String corporation_status_date) {
		this.corporation_status_date = corporation_status_date;
		setModified(true);
	}

	public String getCorporation_type() {
		return corporation_type;
	}

	public void setCorporation_type(String corporation_type) {
		this.corporation_type = corporation_type;
		setModified(true);
	}
	
	public String getBus_type() {
		return bus_type;
	}

	public void setBus_type(String bus_type) {
		this.bus_type = bus_type;
		setModified(true);
	}

	public String getOriginated_state() {
		return originated_state;
	}

	public void setOriginated_state(String originated_state) {
		this.originated_state = originated_state;
		setModified(true);
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
		setModified(true);
	}

	public String getFederal_tax_id() {
		return federal_tax_id;
	}

	public void setFederal_tax_id(String federal_tax_id) {
		this.federal_tax_id = federal_tax_id;
		setModified(true);
	}

	public String getDuration_date() {
		return duration_date;
	}

	public void setDuration_date(String duration_date) {
		this.duration_date = duration_date;
		setModified(true);
	}
	public String getCorporation_name_type_desc() {
		return corporation_name_type_desc;
	}

	public void setCorporation_name_type_desc(String corporation_name_type_desc) {
		this.corporation_name_type_desc = corporation_name_type_desc;
		setModified(true);
	}

	public String getCn_corporation_name1() {
		return cn_corporation_name1;
	}

	public void setCn_corporation_name1(String cn_corporation_name1) {
		this.cn_corporation_name1 = cn_corporation_name1;
		setModified(true);
	}

	public String getCn_effective_date() {
		return cn_effective_date;
	}

	public void setCn_effective_date(String cn_effective_date) {
		this.cn_effective_date = cn_effective_date;
		setModified(true);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setModified(true);
	}

	public String getName_type() {
		return name_type;
	}

	public void setName_type(String name_type) {
		this.name_type = name_type;
		setModified(true);
	}

	public String getCurrent_active() {
		return current_active;
	}

	public void setCurrent_active(String current_active) {
		this.current_active = current_active;
		setModified(true);
	}

	public String getNa_address1() {
		return na_address1;
	}

	public void setNa_address1(String na_address1) {
		this.na_address1 = na_address1;
		setModified(true);
	}

	public String getNa_address2() {
		return na_address2;
	}

	public void setNa_address2(String na_address2) {
		this.na_address2 = na_address2;
		setModified(true);
	}

	public String getNa_address3() {
		return na_address3;
	}

	public void setNa_address3(String na_address3) {
		this.na_address3 = na_address3;
		setModified(true);
	}

	public String getNa_city() {
		return na_city;
	}

	public void setNa_city(String na_city) {
		this.na_city = na_city;
		setModified(true);
	}

	public String getNa_state() {
		return na_state;
	}

	public void setNa_state(String na_state) {
		this.na_state = na_state;
		setModified(true);
	}

	public String getNa_zip_code() {
		return na_zip_code;
	}

	public void setNa_zip_code(String na_zip_code) {
		this.na_zip_code = na_zip_code;
		setModified(true);
	}

	public String getNa_address_type() {
		return na_address_type;
	}

	public void setNa_address_type(String na_address_type) {
		this.na_address_type = na_address_type;
		setModified(true);
	}

	public String getNa_current_address() {
		return na_current_address;
	}

	public void setNa_current_address(String na_current_address) {
		this.na_current_address = na_current_address;
		setModified(true);
	}

	public String getNa_parsed_flag() {
		return na_parsed_flag;
	}

	public void setNa_parsed_flag(String na_parsed_flag) {
		this.na_parsed_flag = na_parsed_flag;
		setModified(true);
	}

	public String getA_address1() {
		return a_address1;
	}

	public void setA_address1(String a_address1) {
		this.a_address1 = a_address1;
		setModified(true);
	}

	public String getA_address2() {
		return a_address2;
	}

	public void setA_address2(String a_address2) {
		this.a_address2 = a_address2;
		setModified(true);
	}

	public String getA_address3() {
		return a_address3;
	}

	public void setA_address3(String a_address3) {
		this.a_address3 = a_address3;
		setModified(true);
	}

	public String getA_city() {
		return a_city;
	}

	public void setA_city(String a_city) {
		this.a_city = a_city;
		setModified(true);
	}

	public String getA_state() {
		return a_state;
	}

	public void setA_state(String a_state) {
		this.a_state = a_state;
		setModified(true);
	}

	public String getA_zip_code() {
		return a_zip_code;
	}

	public void setA_zip_code(String a_zip_code) {
		this.a_zip_code = a_zip_code;
		setModified(true);
	}

	public String getA_address_type() {
		return a_address_type;
	}

	public void setA_address_type(String a_address_type) {
		this.a_address_type = a_address_type;
		setModified(true);
	}

	public String getA_current_address() {
		return a_current_address;
	}

	public void setA_current_address(String a_current_address) {
		this.a_current_address = a_current_address;
		setModified(true);
	}

	public String getA_effective_date() {
		return a_effective_date;
	}

	public void setA_effective_date(String a_effective_date) {
		this.a_effective_date = a_effective_date;
		setModified(true);
	}

	public String getA_parsed_flag() {
		return a_parsed_flag;
	}

	public void setA_parsed_flag(String a_parsed_flag) {
		this.a_parsed_flag = a_parsed_flag;
		setModified(true);
	}

	public String getInfo_title() {
		return info_title;
	}

	public void setInfo_title(String info_title) {
		this.info_title = info_title;
		setModified(true);
	}

	public String getInfo_desc() {
		return info_desc;
	}

	public void setInfo_desc(String info_desc) {
		this.info_desc = info_desc;
		setModified(true);
	}

	public String getHistory_pages() {
		return history_pages;
	}

	public void setHistory_pages(String history_pages) {
		this.history_pages = history_pages;
		setModified(true);
	}

	public String getHistory_code() {
		return history_code;
	}

	public void setHistory_code(String history_code) {
		this.history_code = history_code;
		setModified(true);
	}

	public String getHistory_desc() {
		return history_desc;
	}

	public void setHistory_desc(String history_desc) {
		this.history_desc = history_desc;
		setModified(true);
	}

	public String getEffective_date() {
		return effective_date;
	}

	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
		setModified(true);
	}

	public String getHistory_num() {
		return history_num;
	}

	public void setHistory_num(String history_num) {
		this.history_num = history_num;
		setModified(true);
	}

	public String getHistory_name() {
		return history_name;
	}

	public void setHistory_name(String history_name) {
		this.history_name = history_name;
		setModified(true);
	}

	public String getLocator_num() {
		return locator_num;
	}

	public void setLocator_num(String locator_num) {
		this.locator_num = locator_num;
		setModified(true);
	}

	public CorporationSearchesBean getABjlSearchesBean() {
		return aBjlSearchesBean;
	}

	public void setABjlSearchesBean(CorporationSearchesBean bjlSearchesBean) {
		aBjlSearchesBean = bjlSearchesBean;
	}



	private CorporationSearchesBean aBjlSearchesBean;

	    /**
	     * sets an associated BjlSearchesBean object
	     *
	     * @param v BjlSearchesBean
	     */
	    public void setBjlSearchesBean(CorporationSearchesBean v)
	    {
	            if (v == null)
	        {
	                          setUserSearchId( 0);
	              }
	        else
	        {
	            setUserSearchId(v.getUserSearchId());
	        }
	            aBjlSearchesBean = v;
	    }

	                        
	    /**
	     * Get the associated BjlSearchesBean object
	     *
	     * @return the associated BjlSearchesBean object
	     */
	    public CorporationSearchesBean getBjlSearchesBean()
	    {
	        return aBjlSearchesBean;
	    }


	

}
