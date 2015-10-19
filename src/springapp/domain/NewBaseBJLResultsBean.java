package springapp.domain;

import java.io.Serializable;

import net.searchsystems.limestone.bean.BjlSearchesBean;

public class NewBaseBJLResultsBean implements Serializable{
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
	
	private String name_filing_state;
	private String firstname;
	private String middlename;
	private String lastname; 
	private String name_suffix;
	private String name_type;
	private String name_type_desc;
	private String ssn_taxid_flag;
	private String ssn;
	private String comment_sequence;
	private String comment_type;
	private String comment_type_desc;
	private String house_number;
	private String street_direction;
	private String street_name;
	private String street_suffix;
	private String apartment_num;
	private String city;
	private String state;
	private String zipcode;
	private String filing_state;
	private String filing_group;
	private String filing_group_desc;
	private String filing_type;
	private String filing_type_desc;
	private String filing_date;
	private String filing_number;
	private String tax_lien_type;
	private String tax_lien_type_desc;
	private String bankruptcy_type;
	private String bankruptcy_type_desc;
	private String docket_number;
	private String unlawful_detainer;
	private String initial_date;
	private String initial_amount;
	private String initial_docket;
	
	private String judgement_date;
	private String judgement_amount;
	private String judgement_docket;
	private String remove_date;
	private String remove_docket;
	private String dismissal_date;
	private String dismissal_docket;
	private String asset_amount;
	private String liability_amount;
	private String plaintiff;
	private String beneficiary;
	private String situs;
	private String trustee;
	private String county_code;
	private String county_name;
	private String county_state;
	
	private String court_code;
	private String court_desc;
	private String court_state;
	private String assets_available;
	private String perfected_date;
	private String action_state_code;
	private String action_desc;
	private String disposition_state_code;
	private String disposition_desc;
	private String amount;
	private String release_date;
	private String release_number;
	private String suit_case_number;
	private String suit_date;
	private String suit_amount;
	private String satisfaction_date;
	private String discharge_date;
	
	private String closed_date;
	private String trust_deed_number;
	private String trust_deed_date;
	private String sale_number;
	private String sale_date;
	private String cancellation_number;
	private String cancellation_date;
	private String sched_341_date;
	private String update_date;
	//Udhay Aug8 ==> New Tracers fields addedd
	private String fullname;
	private String businessname;
	private String dob;	
	private String court_address;
	private String court_city;
	private String court_zip;
	private String court_phone;
	//
	private String sched_341_time;
	private String judge;
	private String lawfirm;
	private String book;
	private String page;
	private String origdept;
	private String origcase;
	private String origbook;
	private String origpage;
	private String assoccode;
	private String actiontype;
	private String actiontypedesc;
	
		
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

	public String getName_filing_state() {
		return name_filing_state;
	}
	public void setName_filing_state(String name_filing_state) {
		this.name_filing_state = name_filing_state;
		setModified(true);
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
		setModified(true);
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
		setModified(true);
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
		setModified(true);
	}
	public String getName_suffix() {
		return name_suffix;
	}
	public void setName_suffix(String name_suffix) {
		this.name_suffix = name_suffix;
		setModified(true);
	}
	public String getName_type() {
		return name_type;
	}
	public void setName_type(String name_type) {
		this.name_type = name_type;
		setModified(true);
	}
	public String getName_type_desc() {
		return name_type_desc;
	}
	public void setName_type_desc(String name_type_desc) {
		this.name_type_desc = name_type_desc;
		setModified(true);
	}
	public String getSsn_taxid_flag() {
		return ssn_taxid_flag;
	}
	public void setSsn_taxid_flag(String ssn_taxid_flag) {
		this.ssn_taxid_flag = ssn_taxid_flag;
		setModified(true);
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
		setModified(true);
	}
	public String getComment_sequence() {
		return comment_sequence;
	}
	public void setComment_sequence(String comment_sequence) {
		this.comment_sequence = comment_sequence;
		setModified(true);
	}
	public String getComment_type() {
		return comment_type;
	}
	public void setComment_type(String comment_type) {
		this.comment_type = comment_type;
		setModified(true);
	}
	public String getComment_type_desc() {
		return comment_type_desc;
	}
	public void setComment_type_desc(String comment_type_desc) {
		this.comment_type_desc = comment_type_desc;
		setModified(true);
	}
	public String getHouse_number() {
		return house_number;
	}
	public void setHouse_number(String house_number) {
		this.house_number = house_number;
		setModified(true);
	}
	public String getStreet_direction() {
		return street_direction;
	}
	public void setStreet_direction(String street_direction) {
		this.street_direction = street_direction;
		setModified(true);
	}
	public String getStreet_name() {
		return street_name;
	}
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
		setModified(true);
	}
	public String getStreet_suffix() {
		return street_suffix;
	}
	public void setStreet_suffix(String street_suffix) {
		this.street_suffix = street_suffix;
		setModified(true);
	}
	public String getApartment_num() {
		return apartment_num;
	}
	public void setApartment_num(String apartment_num) {
		this.apartment_num = apartment_num;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
		setModified(true);
	}
	public String getFiling_state() {
		return filing_state;
	}
	public void setFiling_state(String filing_state) {
		this.filing_state = filing_state;
		setModified(true);
	}
	public String getFiling_group() {
		return filing_group;
	}
	public void setFiling_group(String filing_group) {
		this.filing_group = filing_group;
		setModified(true);
	}
	public String getFiling_group_desc() {
		return filing_group_desc;
	}
	public void setFiling_group_desc(String filing_group_desc) {
		this.filing_group_desc = filing_group_desc;
		setModified(true);
	}
	public String getFiling_type() {
		return filing_type;
	}
	public void setFiling_type(String filing_type) {
		this.filing_type = filing_type;
		setModified(true);
	}
	public String getFiling_type_desc() {
		return filing_type_desc;
	}
	public void setFiling_type_desc(String filing_type_desc) {
		this.filing_type_desc = filing_type_desc;
		setModified(true);
	}
	public String getFiling_date() {
		return filing_date;
	}
	public void setFiling_date(String filing_date) {
		this.filing_date = filing_date;
		setModified(true);
	}
	public String getFiling_number() {
		return filing_number;
	}
	public void setFiling_number(String filing_number) {
		this.filing_number = filing_number;
		setModified(true);
	}
	public String getTax_lien_type() {
		return tax_lien_type;
	}
	public void setTax_lien_type(String tax_lien_type) {
		this.tax_lien_type = tax_lien_type;
		setModified(true);
	}
	public String getTax_lien_type_desc() {
		return tax_lien_type_desc;
	}
	public void setTax_lien_type_desc(String tax_lien_type_desc) {
		this.tax_lien_type_desc = tax_lien_type_desc;
		setModified(true);
	}
	public String getBankruptcy_type() {
		return bankruptcy_type;
	}
	public void setBankruptcy_type(String bankruptcy_type) {
		this.bankruptcy_type = bankruptcy_type;
		setModified(true);
	}
	public String getBankruptcy_type_desc() {
		return bankruptcy_type_desc;
	}
	public void setBankruptcy_type_desc(String bankruptcy_type_desc) {
		this.bankruptcy_type_desc = bankruptcy_type_desc;
		setModified(true);
	}
	public String getDocket_number() {
		return docket_number;
	}
	public void setDocket_number(String docket_number) {
		this.docket_number = docket_number;
		setModified(true);
	}
	public String getUnlawful_detainer() {
		return unlawful_detainer;
	}
	public void setUnlawful_detainer(String unlawful_detainer) {
		this.unlawful_detainer = unlawful_detainer;
		setModified(true);
	}
	public String getInitial_date() {
		return initial_date;
	}
	public void setInitial_date(String initial_date) {
		this.initial_date = initial_date;
		setModified(true);
	}
	public String getInitial_amount() {
		return initial_amount;
	}
	public void setInitial_amount(String initial_amount) {
		this.initial_amount = initial_amount;
		setModified(true);
	}
	public String getInitial_docket() {
		return initial_docket;
	}
	public void setInitial_docket(String initial_docket) {
		this.initial_docket = initial_docket;
		setModified(true);
	}
	public String getJudgement_date() {
		return judgement_date;
	}
	public void setJudgement_date(String judgement_date) {
		this.judgement_date = judgement_date;
		setModified(true);
	}
	public String getJudgement_amount() {
		return judgement_amount;
	}
	public void setJudgement_amount(String judgement_amount) {
		this.judgement_amount = judgement_amount;
		setModified(true);
	}
	public String getJudgement_docket() {
		return judgement_docket;
	}
	public void setJudgement_docket(String judgement_docket) {
		this.judgement_docket = judgement_docket;
		setModified(true);
	}
	public String getRemove_date() {
		return remove_date;
	}
	public void setRemove_date(String remove_date) {
		this.remove_date = remove_date;
		setModified(true);
	}
	public String getRemove_docket() {
		return remove_docket;
	}
	public void setRemove_docket(String remove_docket) {
		this.remove_docket = remove_docket;
		setModified(true);
	}
	public String getDismissal_date() {
		return dismissal_date;
	}
	public void setDismissal_date(String dismissal_date) {
		this.dismissal_date = dismissal_date;
		setModified(true);
	}
	public String getDismissal_docket() {
		return dismissal_docket;
	}
	public void setDismissal_docket(String dismissal_docket) {
		this.dismissal_docket = dismissal_docket;
		setModified(true);
	}
	public String getAsset_amount() {
		return asset_amount;
	}
	public void setAsset_amount(String asset_amount) {
		this.asset_amount = asset_amount;
		setModified(true);
	}
	public String getLiability_amount() {
		return liability_amount;
	}
	public void setLiability_amount(String liability_amount) {
		this.liability_amount = liability_amount;
		setModified(true);
	}
	public String getPlaintiff() {
		return plaintiff;
	}
	public void setPlaintiff(String plaintiff) {
		this.plaintiff = plaintiff;
		setModified(true);
	}
	public String getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
		setModified(true);
	}
	public String getSitus() {
		return situs;
	}
	public void setSitus(String situs) {
		this.situs = situs;
		setModified(true);
	}
	public String getTrustee() {
		return trustee;
	}
	public void setTrustee(String trustee) {
		this.trustee = trustee;
		setModified(true);
	}
	public String getCounty_code() {
		return county_code;
	}
	public void setCounty_code(String county_code) {
		this.county_code = county_code;
		setModified(true);
	}
	public String getCounty_name() {
		return county_name;
	}
	public void setCounty_name(String county_name) {
		this.county_name = county_name;
		setModified(true);
	}
	public String getCounty_state() {
		return county_state;
	}
	public void setCounty_state(String county_state) {
		this.county_state = county_state;
		setModified(true);
	}
	public String getCourt_code() {
		return court_code;
	}
	public void setCourt_code(String court_code) {
		this.court_code = court_code;
		setModified(true);
	}
	public String getCourt_desc() {
		return court_desc;
	}
	public void setCourt_desc(String court_desc) {
		this.court_desc = court_desc;
		setModified(true);
	}
	public String getCourt_state() {
		return court_state;
	}
	public void setCourt_state(String court_state) {
		this.court_state = court_state;
		setModified(true);
	}
	public String getAssets_available() {
		return assets_available;
	}
	public void setAssets_available(String assets_available) {
		this.assets_available = assets_available;
		setModified(true);
	}
	public String getPerfected_date() {
		return perfected_date;
	}
	public void setPerfected_date(String perfected_date) {
		this.perfected_date = perfected_date;
		setModified(true);
	}
	public String getAction_state_code() {
		return action_state_code;
	}
	public void setAction_state_code(String action_state_code) {
		this.action_state_code = action_state_code;
		setModified(true);
	}
	public String getAction_desc() {
		return action_desc;
	}
	public void setAction_desc(String action_desc) {
		this.action_desc = action_desc;
		setModified(true);
	}
	public String getDisposition_state_code() {
		return disposition_state_code;
	}
	public void setDisposition_state_code(String disposition_state_code) {
		this.disposition_state_code = disposition_state_code;
		setModified(true);
	}
	public String getDisposition_desc() {
		return disposition_desc;
	}
	public void setDisposition_desc(String disposition_desc) {
		this.disposition_desc = disposition_desc;
		setModified(true);
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
		setModified(true);
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
		setModified(true);
	}
	public String getRelease_number() {
		return release_number;
	}
	public void setRelease_number(String release_number) {
		this.release_number = release_number;
		setModified(true);
	}
	public String getSuit_case_number() {
		return suit_case_number;
	}
	public void setSuit_case_number(String suit_case_number) {
		this.suit_case_number = suit_case_number;
		setModified(true);
	}
	public String getSuit_date() {
		return suit_date;
	}
	public void setSuit_date(String suit_date) {
		this.suit_date = suit_date;
		setModified(true);
	}
	public String getSuit_amount() {
		return suit_amount;
	}
	public void setSuit_amount(String suit_amount) {
		this.suit_amount = suit_amount;
		setModified(true);
	}
	public String getSatisfaction_date() {
		return satisfaction_date;
	}
	public void setSatisfaction_date(String satisfaction_date) {
		this.satisfaction_date = satisfaction_date;
		setModified(true);
	}
	public String getDischarge_date() {
		return discharge_date;
	}
	public void setDischarge_date(String discharge_date) {
		this.discharge_date = discharge_date;
		setModified(true);
	}
	public String getClosed_date() {
		return closed_date;
	}
	public void setClosed_date(String closed_date) {
		this.closed_date = closed_date;
		setModified(true);
	}
	public String getTrust_deed_number() {
		return trust_deed_number;
	}
	public void setTrust_deed_number(String trust_deed_number) {
		this.trust_deed_number = trust_deed_number;
		setModified(true);
	}
	public String getTrust_deed_date() {
		return trust_deed_date;
	}
	public void setTrust_deed_date(String trust_deed_date) {
		this.trust_deed_date = trust_deed_date;
		setModified(true);
	}
	public String getSale_number() {
		return sale_number;
	}
	public void setSale_number(String sale_number) {
		this.sale_number = sale_number;
		setModified(true);
	}
	public String getSale_date() {
		return sale_date;
	}
	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
		setModified(true);
	}
	public String getCancellation_number() {
		return cancellation_number;
	}
	public void setCancellation_number(String cancellation_number) {
		this.cancellation_number = cancellation_number;
		setModified(true);
	}
	public String getCancellation_date() {
		return cancellation_date;
	}
	public void setCancellation_date(String cancellation_date) {
		this.cancellation_date = cancellation_date;
		setModified(true);
	}
	public String getSched_341_date() {
		return sched_341_date;
	}
	public void setSched_341_date(String sched_341_date) {
		this.sched_341_date = sched_341_date;
		setModified(true);
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
		setModified(true);
	}
	
	//Udhay-Aug8 ==> New Tracers fields added
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		if(this.fullname != fullname)
		{
		this.fullname =fullname ;
		setModified(true);
		}
	}

	public String getBusinessname() {
		return businessname;
	}
	public void setBusinessname(String businessname) {
		if(this.businessname != businessname)
		{
		this.businessname = businessname;
		setModified(true);
		}
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		if(this.dob !=dob )
		{
		this.dob = dob;
		setModified(true);
		}
	}
	public String getCourt_address() {
		return court_address;
	}
	public void setCourt_address(String court_address) {
		if(this.court_address != court_address)
		{
		this.court_address = court_address;
		setModified(true);
		}
	}
	public String getCourt_city() {
		return court_city;
	}
	public void setCourt_city(String court_city) {
		if(this.court_city !=court_city )
		{
		this.court_city =court_city ;
		setModified(true);
		}
	}
	
	public String getCourt_zip() {
		return court_zip;
	}
	public void setCourt_zip(String court_zip) {
		if(this.court_zip != court_zip)
		{
		this.court_zip = court_zip;
		setModified(true);
		}
	}
	
	public String getCourt_phone() {
		return court_phone;
	}
	public void setCourt_phone(String court_phone) {
		if(this.court_phone !=court_phone )
		{
		this.court_phone = court_phone;
		setModified(true);
		}
	}
	
	//
	public String getSched_341_time() {
		return sched_341_time;
	}
	public void setSched_341_time(String sched_341_time) {
		if(this.sched_341_time !=sched_341_time )
		{
		this.sched_341_time = sched_341_time;
		setModified(true);
		}
	}
	
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		if(this.judge !=judge )
		{
		this.judge = judge;
		setModified(true);
		}
	}
	
	public String getLawfirm() {
		return lawfirm;
	}
	public void setLawfirm(String lawfirm) {
		if(this.lawfirm !=lawfirm )
		{
		this.lawfirm = lawfirm;
		setModified(true);
		}
	}
	
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		if(this.book !=book )
		{
		this.book = book;
		setModified(true);
		}
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		if(this.page !=page )
		{
		this.page = page;
		setModified(true);
		}
	}
	public String getOrigdept() {
		return origdept;
	}
	public void setOrigdept(String origdept) {
		if(this.origdept !=origdept )
		{
		this.origdept = origdept;
		setModified(true);
		}
	}
	
	public String getOrigcase() {
		return origcase;
	}
	public void setOrigcase(String origcase) {
		if(this.origcase !=origcase )
		{
		this.origcase = origcase;
		setModified(true);
		}
	}
	
	
	public String getOrigbook() {
		return origbook;
	}
	public void setOrigbook(String origbook) {
		if(this.origbook !=origbook )
		{
		this.origbook = origbook;
		setModified(true);
		}
	}
	
	public String getOrigpage() {
		return origpage;
	}
	public void setOrigpage(String origpage) {
		if(this.origpage !=origpage )
		{
		this.origpage = origpage;
		setModified(true);
		}
	}
	
	public String getAssoccode() {
		return assoccode;
	}
	public void setAssoccode(String assoccode) {
		if(this.assoccode !=assoccode )
		{
		this.assoccode = assoccode;
		setModified(true);
		}
	}
	
	public String getActiontype() {
		return actiontype;
	}
	public void setActiontype(String actiontype) {
		if(this.actiontype !=actiontype )
		{
		this.actiontype = actiontype;
		setModified(true);
		}
	}
	
	public String getActiontypedesc() {
		return actiontypedesc;
	}
	public void setActiontypedesc(String actiontypedesc) {
		if(this.actiontypedesc !=actiontypedesc )
		{
		this.actiontypedesc = actiontypedesc;
		setModified(true);
		}
	}
	
	
	  private NewBJLSearchesBean aBjlSearchesBean;

	    /**
	     * sets an associated BjlSearchesBean object
	     *
	     * @param v BjlSearchesBean
	     */
	    public void setBjlSearchesBean(NewBJLSearchesBean v)
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
	    public NewBJLSearchesBean getBjlSearchesBean()
	    {
	        return aBjlSearchesBean;
	    }


	

}
