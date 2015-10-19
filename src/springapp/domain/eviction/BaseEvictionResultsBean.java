package springapp.domain.eviction;

import java.io.Serializable;



public class BaseEvictionResultsBean implements Serializable{
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
	
  //evictions response data
	/** case fields*/
	private String tis_sig_case;
	private String tis_vendor_code;
	private String case_number;
	private String case_sequence_number;
	private String case_year_date;
	private String book_number;
	private String page_number;
	private String unique_id;
	private String result_code;
	private String result_desc;
	private String result_date;
	private String file_date;
	private String input_by;
	private String input_date;
	private String executed_date;
	private String received_date;
	private String satisfied_date;
	private String verified_date;
	private String source_id;
	private String record_status;
	private String judgement_amount;
	private String judgement_flag;
	private String state_abbr;
	private String court_type_code;
	
	private String court_type_desc;
	private String courthouse_sub_code;
	private String county_fips_code;
	private String county_fips_desc;
	private String state_fips_code;
	private String state_fips_desc;
	private String affiliate;
	private String possession_flag;
	private String possession_flag_desc;
	private String region;
	/** evictions_defendant_liste*/
	private String tis_sig_defendant;
	private String defendant_number;
	private String d_county_fips_code;
	private String d_county_fips_desc;
	private String d_state_fips_code;
	private String d_state_fips_desc;
	private String d_ssn;
	private String d_fullname;
	private String d_firstname;
	private String d_middlename;
	private String d_lastname;
	private String d_suffix;
	private String d_alias_flag;
	private String d_alias_firstname;
	private String d_alias_middlename;
	private String d_alias_lastname;
	private String d_alias_suffix;
	private String d_birth_date;
	private String d_address_line_1;
	private String d_address_line_2;
	private String d_apartment_number;
	
	private String d_city;
	private String d_state;
	private String d_zipcode;
	private String d_phone;
	private String d_clean_area_code;
	private String d_clean_phone_number;
	
	/** evictions_plaintiff_list */
	
	private String tis_sig_plaintiff;
	private String plaintiff_type;
	private String plaintiff_desc;
	private String p_county_fips_code;
	private String p_county_fips_desc;
	private String p_state_fips_code;
	private String p_state_fips_desc;
	private String p_fullname;
	private String p_firstname;
	private String p_middlename;
	private String p_lastname;
	private String p_address_line_1;
	private String p_address_line_2;
	private String p_city;
	private String p_state;
	private String p_zipcode;
	private String p_phone_number;
	private String p_clean_area_code;
	private String p_clean_phone_number;
	
	/** courtinfo*/
	
	private String name;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	
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

		
	  public String getTis_sig_case() {
		return tis_sig_case;
	}

	public void setTis_sig_case(String tis_sig_case) {
		this.tis_sig_case = tis_sig_case;
		setModified(true);
	}

	public String getTis_vendor_code() {
		return tis_vendor_code;
	}

	public void setTis_vendor_code(String tis_vendor_code) {
		this.tis_vendor_code = tis_vendor_code;
		setModified(true);
	}

	public String getCase_number() {
		return case_number;
	}

	public void setCase_number(String case_number) {
		this.case_number = case_number;
		setModified(true);
	}

	public String getCase_sequence_number() {
		return case_sequence_number;
	}

	public void setCase_sequence_number(String case_sequence_number) {
		this.case_sequence_number = case_sequence_number;
		setModified(true);
	}

	public String getCase_year_date() {
		return case_year_date;
	}

	public void setCase_year_date(String case_year_date) {
		this.case_year_date = case_year_date;
		setModified(true);
	}

	public String getBook_number() {
		return book_number;
	}

	public void setBook_number(String book_number) {
		this.book_number = book_number;
		setModified(true);
	}

	public String getPage_number() {
		return page_number;
	}

	public void setPage_number(String page_number) {
		this.page_number = page_number;
		setModified(true);
	}

	public String getUnique_id() {
		return unique_id;
	}

	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
		setModified(true);
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
		setModified(true);
	}

	public String getResult_desc() {
		return result_desc;
	}

	public void setResult_desc(String result_desc) {
		this.result_desc = result_desc;
		setModified(true);
	}

	public String getResult_date() {
		return result_date;
	}

	public void setResult_date(String result_date) {
		this.result_date = result_date;
		setModified(true);
	}

	public String getFile_date() {
		return file_date;
	}

	public void setFile_date(String file_date) {
		this.file_date = file_date;
		setModified(true);
	}

	public String getInput_by() {
		return input_by;
	}

	public void setInput_by(String input_by) {
		this.input_by = input_by;
		setModified(true);
	}

	public String getInput_date() {
		return input_date;
	}

	public void setInput_date(String input_date) {
		this.input_date = input_date;
		setModified(true);
	}

	public String getExecuted_date() {
		return executed_date;
	}

	public void setExecuted_date(String executed_date) {
		this.executed_date = executed_date;
		setModified(true);
	}

	public String getReceived_date() {
		return received_date;
	}

	public void setReceived_date(String received_date) {
		this.received_date = received_date;
		setModified(true);
	}

	public String getSatisfied_date() {
		return satisfied_date;
	}

	public void setSatisfied_date(String satisfied_date) {
		this.satisfied_date = satisfied_date;
		setModified(true);
	}

	public String getVerified_date() {
		return verified_date;
	}

	public void setVerified_date(String verified_date) {
		this.verified_date = verified_date;
		setModified(true);
	}

	public String getSource_id() {
		return source_id;
	}

	public void setSource_id(String source_id) {
		this.source_id = source_id;
		setModified(true);
	}

	public String getRecord_status() {
		return record_status;
	}

	public void setRecord_status(String record_status) {
		this.record_status = record_status;
		setModified(true);
	}

	public String getJudgement_amount() {
		return judgement_amount;
	}

	public void setJudgement_amount(String judgement_amount) {
		this.judgement_amount = judgement_amount;
		setModified(true);
	}

	public String getJudgement_flag() {
		return judgement_flag;
	}

	public void setJudgement_flag(String judgement_flag) {
		this.judgement_flag = judgement_flag;
		setModified(true);
	}

	public String getState_abbr() {
		return state_abbr;
	}

	public void setState_abbr(String state_abbr) {
		this.state_abbr = state_abbr;
		setModified(true);
	}

	public String getCourt_type_code() {
		return court_type_code;
	}

	public void setCourt_type_code(String court_type_code) {
		this.court_type_code = court_type_code;
		setModified(true);
	}

	public String getCourt_type_desc() {
		return court_type_desc;
	}

	public void setCourt_type_desc(String court_type_desc) {
		this.court_type_desc = court_type_desc;
		setModified(true);
	}

	public String getCourthouse_sub_code() {
		return courthouse_sub_code;
	}

	public void setCourthouse_sub_code(String courthouse_sub_code) {
		this.courthouse_sub_code = courthouse_sub_code;
		setModified(true);
	}

	public String getCounty_fips_code() {
		return county_fips_code;
	}

	public void setCounty_fips_code(String county_fips_code) {
		this.county_fips_code = county_fips_code;
		setModified(true);
	}

	public String getCounty_fips_desc() {
		return county_fips_desc;
	}

	public void setCounty_fips_desc(String county_fips_desc) {
		this.county_fips_desc = county_fips_desc;
		setModified(true);
	}

	public String getState_fips_code() {
		return state_fips_code;
	}

	public void setState_fips_code(String state_fips_code) {
		this.state_fips_code = state_fips_code;
		setModified(true);
	}

	public String getState_fips_desc() {
		return state_fips_desc;
	}

	public void setState_fips_desc(String state_fips_desc) {
		this.state_fips_desc = state_fips_desc;
		setModified(true);
	}

	public String getAffiliate() {
		return affiliate;
	}

	public void setAffiliate(String affiliate) {
		this.affiliate = affiliate;
		setModified(true);
	}

	public String getPossession_flag() {
		return possession_flag;
	}

	public void setPossession_flag(String possession_flag) {
		this.possession_flag = possession_flag;
		setModified(true);
	}

	public String getPossession_flag_desc() {
		return possession_flag_desc;
	}

	public void setPossession_flag_desc(String possession_flag_desc) {
		this.possession_flag_desc = possession_flag_desc;
		setModified(true);
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
		setModified(true);
	}

	public String getTis_sig_defendant() {
		return tis_sig_defendant;
	}

	public void setTis_sig_defendant(String tis_sig_defendant) {
		this.tis_sig_defendant = tis_sig_defendant;
		setModified(true);
	}

	public String getDefendant_number() {
		return defendant_number;
	}

	public void setDefendant_number(String defendant_number) {
		this.defendant_number = defendant_number;
		setModified(true);
	}

	public String getD_county_fips_code() {
		return d_county_fips_code;
	}

	public void setD_county_fips_code(String d_county_fips_code) {
		this.d_county_fips_code = d_county_fips_code;
		setModified(true);
	}

	public String getD_county_fips_desc() {
		return d_county_fips_desc;
	}

	public void setD_county_fips_desc(String d_county_fips_desc) {
		this.d_county_fips_desc = d_county_fips_desc;
		setModified(true);
	}

	public String getD_state_fips_code() {
		return d_state_fips_code;
	}

	public void setD_state_fips_code(String d_state_fips_code) {
		this.d_state_fips_code = d_state_fips_code;
		setModified(true);
	}

	public String getD_state_fips_desc() {
		return d_state_fips_desc;
	}

	public void setD_state_fips_desc(String d_state_fips_desc) {
		this.d_state_fips_desc = d_state_fips_desc;
		setModified(true);
	}

	public String getD_ssn() {
		return d_ssn;
	}

	public void setD_ssn(String d_ssn) {
		this.d_ssn = d_ssn;
		setModified(true);
	}

	public String getD_fullname() {
		return d_fullname;
	}

	public void setD_fullname(String d_fullname) {
		this.d_fullname = d_fullname;
		setModified(true);
	}

	public String getD_firstname() {
		return d_firstname;
	}

	public void setD_firstname(String d_firstname) {
		this.d_firstname = d_firstname;
		setModified(true);
	}

	public String getD_middlename() {
		return d_middlename;
	}

	public void setD_middlename(String d_middlename) {
		this.d_middlename = d_middlename;
		setModified(true);
	}

	public String getD_lastname() {
		return d_lastname;
	}

	public void setD_lastname(String d_lastname) {
		this.d_lastname = d_lastname;
		setModified(true);
	}

	public String getD_suffix() {
		return d_suffix;
	}

	public void setD_suffix(String d_suffix) {
		this.d_suffix = d_suffix;
		setModified(true);
	}

	public String getD_alias_flag() {
		return d_alias_flag;
	}

	public void setD_alias_flag(String d_alias_flag) {
		this.d_alias_flag = d_alias_flag;
	}

	public String getD_alias_firstname() {
		return d_alias_firstname;
	}

	public void setD_alias_firstname(String d_alias_firstname) {
		this.d_alias_firstname = d_alias_firstname;
		setModified(true);
	}

	public String getD_alias_middlename() {
		return d_alias_middlename;
	}

	public void setD_alias_middlename(String d_alias_middlename) {
		this.d_alias_middlename = d_alias_middlename;
		setModified(true);
	}

	public String getD_alias_lastname() {
		return d_alias_lastname;
	}

	public void setD_alias_lastname(String d_alias_lastname) {
		this.d_alias_lastname = d_alias_lastname;
		setModified(true);
	}

	public String getD_alias_suffix() {
		return d_alias_suffix;
	}

	public void setD_alias_suffix(String d_alias_suffix) {
		this.d_alias_suffix = d_alias_suffix;
		setModified(true);
	}

	public String getD_birth_date() {
		return d_birth_date;
	}

	public void setD_birth_date(String d_birth_date) {
		this.d_birth_date = d_birth_date;
		setModified(true);
	}

	public String getD_address_line_1() {
		return d_address_line_1;
	}

	public void setD_address_line_1(String d_address_line_1) {
		this.d_address_line_1 = d_address_line_1;
		setModified(true);
	}

	public String getD_address_line_2() {
		return d_address_line_2;
	}

	public void setD_address_line_2(String d_address_line_2) {
		this.d_address_line_2 = d_address_line_2;
		setModified(true);
	}

	public String getD_apartment_number() {
		return d_apartment_number;
	}

	public void setD_apartment_number(String d_apartment_number) {
		this.d_apartment_number = d_apartment_number;
		setModified(true);
	}

	public String getD_city() {
		return d_city;
	}

	public void setD_city(String d_city) {
		this.d_city = d_city;
		setModified(true);
	}

	public String getD_state() {
		return d_state;
	}

	public void setD_state(String d_state) {
		this.d_state = d_state;
		setModified(true);
	}

	public String getD_zipcode() {
		return d_zipcode;
	}

	public void setD_zipcode(String d_zipcode) {
		this.d_zipcode = d_zipcode;
		setModified(true);
	}

	public String getD_phone() {
		return d_phone;
	}

	public void setD_phone(String d_phone) {
		this.d_phone = d_phone;
		setModified(true);
	}

	public String getD_clean_area_code() {
		return d_clean_area_code;
	}

	public void setD_clean_area_code(String d_clean_area_code) {
		this.d_clean_area_code = d_clean_area_code;
		setModified(true);
	}

	public String getD_clean_phone_number() {
		return d_clean_phone_number;
	}

	public void setD_clean_phone_number(String d_clean_phone_number) {
		this.d_clean_phone_number = d_clean_phone_number;
		setModified(true);
	}

	public String getTis_sig_plaintiff() {
		return tis_sig_plaintiff;
	}

	public void setTis_sig_plaintiff(String tis_sig_plaintiff) {
		this.tis_sig_plaintiff = tis_sig_plaintiff;
		setModified(true);
	}

	public String getPlaintiff_type() {
		return plaintiff_type;
	}

	public void setPlaintiff_type(String plaintiff_type) {
		this.plaintiff_type = plaintiff_type;
		setModified(true);
	}

	public String getPlaintiff_desc() {
		return plaintiff_desc;
	}

	public void setPlaintiff_desc(String plaintiff_desc) {
		this.plaintiff_desc = plaintiff_desc;
		setModified(true);
	}

	public String getP_county_fips_code() {
		return p_county_fips_code;
	}

	public void setP_county_fips_code(String p_county_fips_code) {
		this.p_county_fips_code = p_county_fips_code;
		setModified(true);
	}

	public String getP_county_fips_desc() {
		return p_county_fips_desc;
	}

	public void setP_county_fips_desc(String p_county_fips_desc) {
		this.p_county_fips_desc = p_county_fips_desc;
		setModified(true);
	}

	public String getP_state_fips_code() {
		return p_state_fips_code;
	}

	public void setP_state_fips_code(String p_state_fips_code) {
		this.p_state_fips_code = p_state_fips_code;
		setModified(true);
	}

	public String getP_state_fips_desc() {
		return p_state_fips_desc;
	}

	public void setP_state_fips_desc(String p_state_fips_desc) {
		this.p_state_fips_desc = p_state_fips_desc;
		setModified(true);
	}

	public String getP_fullname() {
		return p_fullname;
	}

	public void setP_fullname(String p_fullname) {
		this.p_fullname = p_fullname;
		setModified(true);
	}

	public String getP_firstname() {
		return p_firstname;
	}

	public void setP_firstname(String p_firstname) {
		this.p_firstname = p_firstname;
		setModified(true);
	}

	public String getP_middlename() {
		return p_middlename;
	}

	public void setP_middlename(String p_middlename) {
		this.p_middlename = p_middlename;
		setModified(true);
	}

	public String getP_lastname() {
		return p_lastname;
	}

	public void setP_lastname(String p_lastname) {
		this.p_lastname = p_lastname;
		setModified(true);
	}

	public String getP_address_line_1() {
		return p_address_line_1;
	}

	public void setP_address_line_1(String p_address_line_1) {
		this.p_address_line_1 = p_address_line_1;
		setModified(true);
	}

	public String getP_address_line_2() {
		return p_address_line_2;
	}

	public void setP_address_line_2(String p_address_line_2) {
		this.p_address_line_2 = p_address_line_2;
		setModified(true);
	}

	public String getP_city() {
		return p_city;
	}

	public void setP_city(String p_city) {
		this.p_city = p_city;
		setModified(true);
	}

	public String getP_state() {
		return p_state;
	}

	public void setP_state(String p_state) {
		this.p_state = p_state;
		setModified(true);
	}

	public String getP_zipcode() {
		return p_zipcode;
	}

	public void setP_zipcode(String p_zipcode) {
		this.p_zipcode = p_zipcode;
		setModified(true);
	}

	public String getP_phone_number() {
		return p_phone_number;
	}

	public void setP_phone_number(String p_phone_number) {
		this.p_phone_number = p_phone_number;
		setModified(true);
	}

	public String getP_clean_area_code() {
		return p_clean_area_code;
	}

	public void setP_clean_area_code(String p_clean_area_code) {
		this.p_clean_area_code = p_clean_area_code;
		setModified(true);
	}

	public String getP_clean_phone_number() {
		return p_clean_phone_number;
	}

	public void setP_clean_phone_number(String p_clean_phone_number) {
		this.p_clean_phone_number = p_clean_phone_number;
		setModified(true);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setModified(true);
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
		setModified(true);
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
		setModified(true);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
		setModified(true);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		setModified(true);
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
		setModified(true);
	}


	private EvictionSearchesBean aBjlSearchesBean;

	    /**
	     * sets an associated BjlSearchesBean object
	     *
	     * @param v BjlSearchesBean
	     */
	    public void setBjlSearchesBean(EvictionSearchesBean v)
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
	    public EvictionSearchesBean getBjlSearchesBean()
	    {
	        return aBjlSearchesBean;
	    }


	

}
