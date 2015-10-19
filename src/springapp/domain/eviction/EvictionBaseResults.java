package springapp.domain.eviction;

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

public abstract class EvictionBaseResults extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1230102311301L;
	/** peer class*/
	  private static final EvictionResultsPeer peer =
	        new EvictionResultsPeer();
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
			if(aEvictionSearches != null && !(aEvictionSearches.getUserSearchId()== v))
			{
				aEvictionSearches = null;
			}
		}
		
		
		
		private EvictionSearches aEvictionSearches;
		/**
	     * Declares an association between this object and a BjlSearches object
	     *
	     * @param v EvictionSearches
	     * @throws TorqueException
	     */
	    public void setEvictionSearches(EvictionSearches v) throws TorqueException
	    {
	            if (v == null)
	        {
	                          setUserSearchId( 0);
	              }
	        else
	        {
	            setUserSearchId(v.getUserSearchId());
	        }
	            aEvictionSearches = v;
	    }

	    
	    public String getTis_sig_case() {
			return tis_sig_case;
		}
		public void setTis_sig_case(String tis_sig_case) {
			if(this.tis_sig_case != tis_sig_case)
			{
			this.tis_sig_case = tis_sig_case;
			setModified(true);
			}
			
		}
		public String getTis_vendor_code() {
			return tis_vendor_code;
		}
		public void setTis_vendor_code(String tis_vendor_code) {
			if(this.tis_vendor_code != tis_vendor_code)
			{
			this.tis_vendor_code = tis_vendor_code;
			setModified(true);
			}
		}
		public String getCase_number() {
			return case_number;
		}
		public void setCase_number(String case_number) {
			if(this.case_number != case_number)
			{
			this.case_number = case_number;
			setModified(true);
			}
		}
		public String getCase_sequence_number() {
			return case_sequence_number;
		}
		public void setCase_sequence_number(String v) {
			if(this.case_sequence_number != v)
			{
			this.case_sequence_number = v;
			setModified(true);
			}
		}
		public String getCase_year_date() {
			return case_year_date;
		}
		public void setCase_year_date(String v) {
			if(this.case_year_date != v)
			{
			this.case_year_date = v;
			setModified(true);
			}
		}
		public String getBook_number() {
			return book_number;
		}
		public void setBook_number(String v) {
			if(this.book_number != v)
			{
			this.book_number = v;
			setModified(true);
			}
		}
		public String getPage_number() {
			return page_number;
		}
		public void setPage_number(String v) {
			if(this.page_number != v)
			{
			this.page_number = v;
			setModified(true);
			}
		}
		public String getUnique_id() {
			return unique_id;
		}
		public void setUnique_id(String v) {
			if(this.unique_id != v)
			{
			this.unique_id = v;
			setModified(true);
			}
		}
		public String getResult_code() {
			return result_code;
		}
		public void setResult_code(String v) {
			if(this.result_code != v)
			{
			this.result_code = v;
			setModified(true);
			}
		}
		public String getResult_desc() {
			return result_desc;
		}
		public void setResult_desc(String v) {
			if(this.result_desc != v)
			{
			this.result_desc = v;
			setModified(true);
			}
		}
		public String getResult_date() {
			return result_date;
		}
		public void setResult_date(String v) {
			if(this.result_date != v)
			{
			this.result_date = v;
			setModified(true);
			}
		}
		public String getFile_date() {
			return file_date;
		}
		public void setFile_date(String v) {
			if(this.file_date != v)
			{
			this.file_date = v;
			setModified(true);
			}
		}
		public String getInput_by() {
			return input_by;
		}
		public void setInput_by(String v) {
			if(this.input_by != v)
			{
			this.input_by = v;
			setModified(true);
			}
		}
		public String getInput_date() {
			return input_date;
		}
		public void setInput_date(String v) {
			if(this.input_date != v)
			{
			this.input_date = v;
			setModified(true);
			}
		}
		public String getExecuted_date() {
			return executed_date;
		}
		public void setExecuted_date(String v) {
			if(this.executed_date != v)
			{
			this.executed_date = v;
			setModified(true);
			}
		}
		public String getReceived_date() {
			return received_date;
		}
		public void setReceived_date(String v) {
			if(this.received_date != v)
			{
			this.received_date = v;
			setModified(true);
			}
		}
		public String getSatisfied_date() {
			return satisfied_date;
		}
		public void setSatisfied_date(String v) {
			if(this.satisfied_date != v)
			{
			this.satisfied_date = v;
			setModified(true);
			}
		}
		public String getVerified_date() {
			return verified_date;
		}
		public void setVerified_date(String v) {
			if(this.verified_date != v)
			{
			this.verified_date = v;
			setModified(true);
			}
		}
		public String getSource_id() {
			return source_id;
		}
		public void setSource_id(String v) {
			if(this.source_id != v)
			{
			this.source_id = v;
			setModified(true);
			}
		}
		public String getRecord_status() {
			return record_status;
		}
		public void setRecord_status(String v) {
			if(this.record_status != v)
			{
			this.record_status = v;
			setModified(true);
			}
		}
		public String getJudgement_amount() {
			return judgement_amount;
		}
		public void setJudgement_amount(String v) {
			if(this.judgement_amount != v)
			{
			this.judgement_amount = v;
			setModified(true);
			}
		}
		public String getJudgement_flag() {
			return judgement_flag;
		}
		public void setJudgement_flag(String v) {
			if(this.judgement_flag != v)
			{
			this.judgement_flag = v;
			setModified(true);
			}
		}
		public String getState_abbr() {
			return state_abbr;
		}
		public void setState_abbr(String v) {
			if(this.state_abbr != v)
			{
			this.state_abbr = v;
			setModified(true);
			}
		}
		public String getCourt_type_code() {
			return court_type_code;
		}
		public void setCourt_type_code(String v) {
			if(this.court_type_code != v)
			{
			this.court_type_code = v;
			setModified(true);
			}
		}
		public String getCourt_type_desc() {
			return court_type_desc;
		}
		public void setCourt_type_desc(String v) {
			if(this.court_type_desc != v)
			{
			this.court_type_desc = v;
			setModified(true);
			}
		}
		public String getCourthouse_sub_code() {
			return courthouse_sub_code;
		}
		public void setCourthouse_sub_code(String v) {
			if(this.courthouse_sub_code != v)
			{
			this.courthouse_sub_code = v;
			setModified(true);
			}
		}
		public String getCounty_fips_code() {
			return county_fips_code;
		}
		public void setCounty_fips_code(String v) {
			if(this.county_fips_code != v)
			{
			this.county_fips_code = v;
			setModified(true);
			}
		}
		public String getCounty_fips_desc() {
			return county_fips_desc;
		}
		public void setCounty_fips_desc(String v) {
			if(this.county_fips_desc != v)
			{
			this.county_fips_desc = v;
			setModified(true);
			}
		}
		public String getState_fips_code() {
			return state_fips_code;
		}
		public void setState_fips_code(String v) {
			if(this.state_fips_code != v)
			{
			this.state_fips_code = v;
			setModified(true);
			}
		}
		public String getState_fips_desc() {
			return state_fips_desc;
		}
		public void setState_fips_desc(String v) {
			if(this.state_fips_desc != v)
			{
			this.state_fips_desc = v;
			setModified(true);
			}
		}
		public String getAffiliate() {
			return affiliate;
		}
		public void setAffiliate(String v) {
			if(this.affiliate != v)
			{
			this.affiliate = v;
			setModified(true);
			}
		}
		public String getPossession_flag() {
			return possession_flag;
		}
		public void setPossession_flag(String v) {
			if(this.possession_flag != v)
			{
			this.possession_flag = v;
			setModified(true);
			}
		}
		public String getPossession_flag_desc() {
			return possession_flag_desc;
		}
		public void setPossession_flag_desc(String v) {
			if(this.possession_flag_desc != v)
			{
			this.possession_flag_desc = v;
			setModified(true);
			}
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String v) {
			if(this.region != v)
			{
			this.region = v;
			setModified(true);
			}
		}
		public String getTis_sig_defendant() {
			return tis_sig_defendant;
		}
		public void setTis_sig_defendant(String v) {
			if(this.tis_sig_defendant != v)
			{
			this.tis_sig_defendant = v;
			setModified(true);
			}
		}
		public String getDefendant_number() {
			return defendant_number;
		}
		public void setDefendant_number(String v) {
			if(this.defendant_number != v)
			{
			this.defendant_number = v;
			setModified(true);
			}
		}
		public String getD_county_fips_code() {
			return d_county_fips_code;
		}
		public void setD_county_fips_code(String v) {
			if(this.d_county_fips_code != v)
			{
			this.d_county_fips_code = v;
			setModified(true);
			}
		}
		public String getD_county_fips_desc() {
			return d_county_fips_desc;
		}
		public void setD_county_fips_desc(String v) {
			if(this.d_county_fips_desc != v)
			{
			this.d_county_fips_desc = v;
			setModified(true);
			}
		}
		public String getD_state_fips_code() {
			return d_state_fips_code;
		}
		public void setD_state_fips_code(String v) {
			if(this.d_state_fips_code != v)
			{
				this.d_state_fips_code = v;
				setModified(true);
			}
			
		}
		public String getD_state_fips_desc() {
			return d_state_fips_desc;
		}
		public void setD_state_fips_desc(String v) {
			if(this.d_state_fips_desc != v)
			{
			this.d_state_fips_desc = v;
			setModified(true);
			}
		}
		public String getD_ssn() {
			return d_ssn;
		}
		public void setD_ssn(String v) {
			if(this.d_ssn != v)
			{
			this.d_ssn = v;
			setModified(true);
			}
		}
		public String getD_fullname() {
			return d_fullname;
		}
		public void setD_fullname(String v) {
			if(this.d_fullname != v)
			{
			this.d_fullname = v;
			setModified(true);
			}
		}
		public String getD_firstname() {
			return d_firstname;
		}
		public void setD_firstname(String v) {
			if(this.d_firstname != v)
			{
			this.d_firstname = v;
			setModified(true);
			}
		}
		public String getD_middlename() {
			return d_middlename;
		}
		public void setD_middlename(String v) {
			if(this.d_middlename != v)
			{
			this.d_middlename = v;
			setModified(true);
			}
		}
		public String getD_lastname() {
			return d_lastname;
		}
		public void setD_lastname(String v) {
			if(this.d_lastname != v)
			{
			this.d_lastname = v;
			setModified(true);
			}
		}
		public String getD_suffix() {
			return d_suffix;
		}
		public void setD_suffix(String v) {
			if(this.d_suffix != v)
			{
			this.d_suffix = v;
			setModified(true);
			}
		}
		public String getD_alias_flag() {
			return d_alias_flag;
		}
		public void setD_alias_flag(String v) {
			if(this.d_alias_flag != v)
			{
			this.d_alias_flag = v;
			setModified(true);
			}
		}
		public String getD_alias_firstname() {
			return d_alias_firstname;
		}
		public void setD_alias_firstname(String v) {
			if(this.d_alias_firstname != v)
			{
			this.d_alias_firstname = v;
			setModified(true);
			}
		}
		public String getD_alias_middlename() {
			return d_alias_middlename;
		}
		public void setD_alias_middlename(String v) {
			if(this.d_alias_middlename != v)
			{
			this.d_alias_middlename = v;
			setModified(true);
			}
		}
		public String getD_alias_lastname() {
			return d_alias_lastname;
		}
		public void setD_alias_lastname(String v) {
			if(this.d_alias_lastname != v)
			{
				this.d_alias_lastname = v;
				setModified(true);
			}
			
			
		}
		public String getD_alias_suffix() {
			return d_alias_suffix;
		}
		public void setD_alias_suffix(String v) {
			if(this.d_alias_suffix != v)
			{
				this.d_alias_suffix = v;
				setModified(true);
			}
			
		}
		public String getD_birth_date() {
			return d_birth_date;
		}
		public void setD_birth_date(String v) {
			if(this.d_birth_date != v)
			{
			this.d_birth_date = v;
			setModified(true);
			}
		}
		public String getD_address_line_1() {
			return d_address_line_1;
		}
		public void setD_address_line_1(String v) {
			if(this.d_address_line_1 != v)
			{
			this.d_address_line_1 = v;
			setModified(true);
			}
		}
		public String getD_address_line_2() {
			return d_address_line_2;
		}
		public void setD_address_line_2(String v) {
			if(this.d_address_line_2 != v)
			{
			this.d_address_line_2 = v;
			setModified(true);
			}
		}
		public String getD_apartment_number() {
			return d_apartment_number;
		}
		public void setD_apartment_number(String v) {
			if(this.d_apartment_number != v)
			{
			this.d_apartment_number = v;
			setModified(true);
			}
		}
		public String getD_city() {
			return d_city;
		}
		public void setD_city(String v) {
			if(this.d_city != v)
			{
			this.d_city = v;
			setModified(true);
			}
		}
		public String getD_state() {
			return d_state;
		}
		public void setD_state(String v) {
			if(this.d_state != v)
			{
			this.d_state = v;
			setModified(true);
			}
		}
		public String getD_zipcode() {
			return d_zipcode;
		}
		public void setD_zipcode(String v) {
			if(this.d_zipcode != v)
			{
			this.d_zipcode = v;
			setModified(true);
			}
		}
		public String getD_phone() {
			return d_phone;
		}
		public void setD_phone(String v) {
			if(this.d_phone != v)
			{
			this.d_phone = v;
			setModified(true);
			}
		}
		public String getD_clean_area_code() {
			return d_clean_area_code;
		}
		public void setD_clean_area_code(String v) {
			if(this.d_clean_area_code != v)
			{
			this.d_clean_area_code = v;
			setModified(true);
			}
		}
		public String getD_clean_phone_number() {
			return d_clean_phone_number;
		}
		public void setD_clean_phone_number(String v) {
			if(this.d_clean_phone_number != v)
			{
			this.d_clean_phone_number = v;
			setModified(true);
			}
		}
		public String getTis_sig_plaintiff() {
			return tis_sig_plaintiff;
		}
		public void setTis_sig_plaintiff(String v) {
			if(this.tis_sig_plaintiff != v)
			{
			this.tis_sig_plaintiff = v;
			setModified(true);
			}
		}
		public String getPlaintiff_type() {
			return plaintiff_type;
		}
		public void setPlaintiff_type(String v) {
			if(this.plaintiff_type != v)
			{
			this.plaintiff_type = v;
			setModified(true);
			}
		}
		public String getPlaintiff_desc() {
			return plaintiff_desc;
		}
		public void setPlaintiff_desc(String v) {
			if(this.plaintiff_desc != v)
			{
			this.plaintiff_desc = v;
			setModified(true);
			}
		}
		public String getP_county_fips_code() {
			return p_county_fips_code;
		}
		public void setP_county_fips_code(String v) {
			if(this.p_county_fips_code != v)
			{
			this.p_county_fips_code = v;
			setModified(true);
			}
		}
		public String getP_county_fips_desc() {
			return p_county_fips_desc;
		}
		public void setP_county_fips_desc(String v) {
			if(this.p_county_fips_desc != v)
			{
			this.p_county_fips_desc = v;
			setModified(true);
			}
		}
		public String getP_state_fips_code() {
			return p_state_fips_code;
		}
		public void setP_state_fips_code(String v) {
			if(this.p_state_fips_code != v)
			{
			this.p_state_fips_code = v;
			setModified(true);
			}
		}
		public String getP_state_fips_desc() {
			return p_state_fips_desc;
		}
		public void setP_state_fips_desc(String v) {
			if(this.p_state_fips_desc != v)
			{
			this.p_state_fips_desc = v;
			setModified(true);
			}
		}
		public String getP_fullname() {
			return p_fullname;
		}
		public void setP_fullname(String v) {
			if(this.p_fullname != v)
			{
			this.p_fullname = v;
			setModified(true);
			}
		}
		public String getP_firstname() {
			return p_firstname;
		}
		public void setP_firstname(String v) {
			if(this.p_firstname != v)
			{
			this.p_firstname = v;
			setModified(true);
			}
		}
		public String getP_middlename() {
			return p_middlename;
		}
		public void setP_middlename(String v) {
			if(this.p_middlename != v)
			{
			this.p_middlename = v;
			setModified(true);
			}
		}
		public String getP_lastname() {
			return p_lastname;
		}
		public void setP_lastname(String v) {
			if(this.p_lastname != v){
				this.p_lastname = v;
				setModified(true);
			}
			
		}
		public String getP_address_line_1() {
			return p_address_line_1;
		}
		public void setP_address_line_1(String v) {
			if(this.p_address_line_1 != v)
			{
			this.p_address_line_1 = v;
			setModified(true);
			}
		}
		public String getP_address_line_2() {
			return p_address_line_2;
		}
		public void setP_address_line_2(String v) {
			if(this.p_address_line_2 != v)
			{
			this.p_address_line_2 = v;
			setModified(true);
			}
		}
		public String getP_city() {
			return p_city;
		}
		public void setP_city(String v) {
			if(this.p_city != v)
			{
			this.p_city = v;
			setModified(true);
			}
		}
		public String getP_state() {
			return p_state;
		}
		public void setP_state(String v) {
			if(this.p_state != v)
			{
			this.p_state = v;
			setModified(true);
			}
		}
		public String getP_zipcode() {
			return p_zipcode;
		}
		public void setP_zipcode(String v) {
			if(this.p_zipcode != v)
			{
			this.p_zipcode = v;
			setModified(true);
			}
		}
		public String getP_phone_number() {
			return p_phone_number;
		}
		public void setP_phone_number(String v) {
			if(this.p_phone_number != v)
			{
			this.p_phone_number = v;
			setModified(true);
			}
		}
		public String getP_clean_area_code() {
			return p_clean_area_code;
		}
		public void setP_clean_area_code(String v) {
			if(this.p_clean_area_code != v)
			{
			this.p_clean_area_code = v;
			setModified(true);
			}
		}
		public String getP_clean_phone_number() {
			return p_clean_phone_number;
		}
		public void setP_clean_phone_number(String v) {
			if(this.p_clean_phone_number != v)
			{
			this.p_clean_phone_number = v;
			setModified(true);
			}
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
		public String getAddress1() {
			return address1;
		}
		public void setAddress1(String v) {
			if(this.address1 != v)
			{
			this.address1 = v;
			setModified(true);
			}
		}
		public String getAddress2() {
			return address2;
		}
		public void setAddress2(String v) {
			if(this.address2 != v)
			{
			this.address2 = v;
			setModified(true);
			}
		}
		public String getCity() {
			return city;
		}
		public void setCity(String v) {
			if(this.city != v)
			{
			this.city = v;
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
		public String getZip() {
			return zip;
		}
		public void setZip(String v) {
			if(this.zip != v)
			{
			this.zip = v;
			setModified(true);
			}
		}
		
		/**
	     * Returns the associated EvictionSearches object.
	           * If it was not retrieved before, the object is retrieved from
	     * the database
	           *
	     * @return the associated EvictionSearches object
	           * @throws TorqueException
	           */
	    public EvictionSearches getEvictionSearches()
	              throws TorqueException
	          {
	              if (aEvictionSearches == null && (this.userSearchId != 0))
	        {
	            	  aEvictionSearches = EvictionSearchesPeer.retrieveByPK(SimpleKey.keyFor(this.userSearchId));
	                  
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
	              return aEvictionSearches;
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
	    public EvictionSearches getEvictionSearches(Connection connection)
	        throws TorqueException
	    {
	        if (aEvictionSearches == null && (this.userSearchId != 0))
	        {
	        	aEvictionSearches = EvictionSearchesPeer.retrieveByPK(SimpleKey.keyFor(this.userSearchId), connection);
	              
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
	        return aEvictionSearches;
	    }

	    /**
	     * Provides convenient way to set a relationship based on a
	     * ObjectKey, for example
	     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
	     *
	         */
	    public void setEvictionSearchesKey(ObjectKey key) throws TorqueException
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
	              fieldNames.add("tis_sig_case");
	              fieldNames.add("tis_vendor_code");
	              fieldNames.add("case_number");
	              fieldNames.add("case_sequence_number");
	              fieldNames.add("case_year_date");
	              fieldNames.add("book_number");
	              fieldNames.add("page_number");
	              fieldNames.add("unique_id");
	              fieldNames.add("result_code");
	              fieldNames.add("result_desc");
	              fieldNames.add("result_date");
	              fieldNames.add("file_date");
	              fieldNames.add("input_by");
	              fieldNames.add("input_date");
	              fieldNames.add("executed_date");
	              fieldNames.add("received_date");
	              fieldNames.add("satisfied_date");
	              fieldNames.add("verified_date");
	              fieldNames.add("source_id");
	              fieldNames.add("record_status");
	              fieldNames.add("judgement_amount");
	              fieldNames.add("judgement_flag");
	              fieldNames.add("state_abbr");
	              fieldNames.add("court_type_code");
	              fieldNames.add("court_type_desc");
	              fieldNames.add("courthouse_sub_code");
	              fieldNames.add("county_fips_code");
	              fieldNames.add("county_fips_desc");
	              fieldNames.add("state_fips_code");
	              fieldNames.add("state_fips_desc");
	              fieldNames.add("affiliate");
	              fieldNames.add("possession_flag");
	              fieldNames.add("possession_flag_desc");
	              fieldNames.add("region");
	              fieldNames.add("tis_sig_defendant");
	              fieldNames.add("defendant_number");
	              fieldNames.add("d_county_fips_code");
	              fieldNames.add("d_county_fips_desc");
	              fieldNames.add("d_state_fips_code");
	              fieldNames.add("d_state_fips_desc");
	              fieldNames.add("d_ssn");
	              fieldNames.add("d_fullname");
	              fieldNames.add("d_firstname");
	              fieldNames.add("d_middlename");
	              fieldNames.add("d_lastname");
	              fieldNames.add("d_suffix");
	              fieldNames.add("d_alias_flag");
	              fieldNames.add("d_alias_firstname");
	              fieldNames.add("d_alias_middlename");
	              fieldNames.add("d_alias_lastname");
	              fieldNames.add("d_alias_suffix");
	              fieldNames.add("d_birth_date");
	              fieldNames.add("d_address_line_1");
	              fieldNames.add("d_address_line_2");
	              fieldNames.add("d_apartment_number");
	              fieldNames.add("d_city");
	              fieldNames.add("d_state");
	              fieldNames.add("d_zipcode");
	              fieldNames.add("d_phone");
	              fieldNames.add("d_clean_area_code");
	              fieldNames.add("d_clean_phone_number");
	              fieldNames.add("tis_sig_plaintiff");
	              fieldNames.add("plaintiff_type");
	              fieldNames.add("plaintiff_desc");
	              fieldNames.add("p_county_fips_code");
	              fieldNames.add("p_county_fips_desc");
	              fieldNames.add("p_state_fips_code");
	              fieldNames.add("p_state_fips_desc");
	              fieldNames.add("p_fullname");
	              fieldNames.add("p_firstname");
	              fieldNames.add("p_middlename");
	              fieldNames.add("p_lastname");
	              fieldNames.add("p_address_line_1");
	              fieldNames.add("p_address_line_2");
	              fieldNames.add("p_city");
	              fieldNames.add("p_state");
	              fieldNames.add("p_zipcode");
	              fieldNames.add("p_phone_number");
	              
	              fieldNames.add("p_clean_area_code");
	              fieldNames.add("p_clean_phone_number");
	              fieldNames.add("name");
	              fieldNames.add("address1");
	              fieldNames.add("address2");
	              fieldNames.add("city");
	              fieldNames.add("state");
	              fieldNames.add("zip");
	              
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
	           if (name.equals("tis_sig_case"))
	         {
	                 return getTis_sig_case();
	             }
	           if (name.equals("tis_vendor_code"))
	         {
	                 return getTis_vendor_code();
	             }
	           if (name.equals("case_number"))
	         {
	                 return getCase_number();
	             }
	           if (name.equals("case_sequence_number"))
	         {
	                 return getCase_sequence_number();
	             }
	           if (name.equals("case_year_date"))
	         {
	                 return getCase_year_date();
	             }
	           if (name.equals("book_number"))
	         {
	                 return getBook_number();
	             }
	           if (name.equals("page_number"))
	         {
	                 return getPage_number();
	             }
	           if (name.equals("unique_id"))
	         {
	                 return getUnique_id();
	             }
	           if (name.equals("result_code"))
	         {
	                 return getResult_code();
	             }
	           if (name.equals("result_desc"))
	         {
	                 return getResult_desc();
	             }
	           if (name.equals("result_date"))
	         {
	                 return getResult_date();
	             }
	           if (name.equals("file_date"))
	         {
	                 return getFile_date();
	             }
	           if (name.equals("input_by"))
	         {
	                 return getInput_by();
	             }
	           if (name.equals("input_date"))
	         {
	                 return getInput_date();
	             }
	           if (name.equals("executed_date"))
	         {
	                 return getExecuted_date();
	             }
	           if (name.equals("received_date"))
	         {
	                 return getReceived_date();
	             }
	           if (name.equals("satisfied_date"))
	         {
	                 return getSatisfied_date();
	             }
	           if (name.equals("verified_date"))
	         {
	                 return getVerified_date();
	             }
	           if (name.equals("source_id"))
	         {
	                 return getSource_id();
	             }
	           if (name.equals("record_status"))
	         {
	                 return getRecord_status();
	             }
	           if (name.equals("judgement_amount"))
	         {
	                 return getJudgement_amount();
	             }
	           if (name.equals("judgement_flag"))
	         {
	                 return getJudgement_flag();
	             }
	           if (name.equals("state_abbr"))
	         {
	                 return getState_abbr();
	             }
	           if (name.equals("court_type_code"))
	         {
	                 return getCourt_type_code();
	             }
	           if (name.equals("court_type_desc"))
	         {
	                 return getCourt_type_desc();
	             }
	           if (name.equals("courthouse_sub_code"))
	         {
	                 return getCourthouse_sub_code();
	             }
	           if (name.equals("county_fips_code"))
	         {
	                 return getCounty_fips_code();
	             }
	           if (name.equals("county_fips_desc"))
	         {
	                 return getCounty_fips_desc();
	             }
	           if (name.equals("state_fips_code"))
	         {
	                 return getState_fips_code();
	             }
	           if (name.equals("state_fips_desc"))
	         {
	                 return getState_fips_desc();
	             }
	           if (name.equals("affiliate"))
	         {
	                 return getAffiliate();
	             }
	           if (name.equals("possession_flag"))
	         {
	                 return getPossession_flag();
	             }
	           if (name.equals("possession_flag_desc"))
	         {
	                 return getPossession_flag_desc();
	             }
	           if (name.equals("region"))
	         {
	                 return getRegion();
	             }
	           if (name.equals("tis_sig_defendant"))
	         {
	                 return getTis_sig_defendant();
	             }
	           if (name.equals("defendant_number"))
	         {
	                 return getDefendant_number();
	             }
	           if (name.equals("d_county_fips_code"))
	         {
	                 return getD_county_fips_code();
	             }
	           if (name.equals("d_county_fips_desc"))
	         {
	                 return getD_county_fips_desc();
	             }
	           if (name.equals("d_state_fips_code"))
	         {
	                 return getD_state_fips_code();
	             }
	           if (name.equals("d_state_fips_desc"))
	         {
	                 return getD_state_fips_desc();
	             }
	           if (name.equals("d_ssn"))
	         {
	                 return getD_ssn();
	             }
	           if (name.equals("d_fullname"))
	         {
	                 return getD_fullname();
	             }
	           if (name.equals("d_firstname"))
	         {
	                 return getD_firstname();
	             }
	           if (name.equals("d_middlename"))
	         {
	                 return getD_middlename();
	             }
	           if(name.equals("d_lastname"))
	           {
	        	   return getD_lastname();
	        	   
	           }
	           if(name.equals("d_suffix"))
	           {
	        	   return getD_suffix();
	        	   
	           }
	           if(name.equals("d_alias_flag"))
	           {
	        	   return getD_alias_flag();
	           }
	           if(name.equals("d_alias_firstname"))
	           {
	        	   return getD_alias_firstname();
	        	   
	           }
	           if(name.equals("d_alias_middlename"))
	           {
	        	   return getD_alias_middlename();
	           }
	           if(name.equals("d_alias_lastname"))
	           {
	        	   return getD_alias_lastname();
	           }
	           if(name.equals("d_alias_suffix"))
	           {
	        	   return getD_alias_suffix();
	           }
	           if(name.equals("d_birth_date"))
	           {
	        	   return getD_birth_date();
	           }
	           if(name.equals("d_address_line_1"))
	           {
	        	   return getD_address_line_1();
	           }
	           if(name.equals("d_address_line_2"))
	           {
	        	   return getD_address_line_2();
	           }
	           if(name.equals("d_apartment_number"))
	           {
	        	   return getD_apartment_number();
	           }
	           if(name.equals("d_city"))
	           {
	        	   return getD_city();
	           }
	           if(name.equals("d_state"))
	           {
	        	   return getD_state();
	           }
	           if(name.equals("d_zipcode"))
	           {
	        	   return getD_zipcode();
	           }
	           if(name.equals("d_phone"))
	           {
	        	   return getD_phone();
	           }
	           if(name.equals("d_clean_area_code"))
	           {
	        	   return getD_clean_area_code();
	           }

	           if(name.equals("d_clean_phone_number"))
	           {
	        	   return getD_clean_phone_number();
	           }
	           if(name.equals("tis_sig_plaintiff"))
	           {
	        	   return getTis_sig_plaintiff();
	           }
	           if(name.equals("plaintiff_type"))
	           {
	        	   return getPlaintiff_type();
	           }
	           if(name.equals("plaintiff_desc"))
	           {
	        	   return getPlaintiff_desc();
	           }
	           if(name.equals("p_county_fips_code"))
	           {
	        	   return getP_county_fips_code();
	           }
	           if(name.equals("p_county_fips_desc"))
	           {
	        	   return getP_county_fips_desc();
	           }
	           if(name.equals("p_state_fips_code"))
	           {
	        	   return getP_state_fips_code();
	           }
	           if(name.equals("p_state_fips_desc"))
	           {
	        	   return getP_state_fips_desc();
	           }
	           if(name.equals("p_fullname"))
	           {
	        	   return getP_fullname();
	           }
	           if(name.equals("p_firstname"))
	           {
	        	   return getP_firstname();
	           }
	           if(name.equals("p_middlename"))
	           {
	        	   return getP_middlename();
	           }
	           if(name.equals("p_lastname"))
	           {
	        	   return getP_lastname();
	           }
	           if(name.equals("p_address_line_1"))
	           {
	        	   return getP_address_line_1();
	           }
	           if(name.equals("p_address_line_2"))
	           {
	        	   return getP_address_line_2();
	           }
	           if(name.equals("p_city"))
	           {
	        	   return getP_city();
	           }
	           if(name.equals("p_state"))
	           {
	        	   return getP_state();
	           }
	           if(name.equals("p_zipcode"))
	           {
	        	   return getP_zipcode();
	           }
	           if(name.equals("p_phone_number"))
	           {
	        	   return getP_phone_number();
	           }
	           
	           if(name.equals("p_clean_area_code"))
	           {
	        	   return getP_clean_area_code();
	           }
	           if(name.equals("p_clean_phone_number"))
	           {
	        	   return getP_clean_phone_number();
	           }
	           if(name.equals("name"))
	           {
	        	   return getName();
	           }
	           if(name.equals("address1"))
	           {
	        	   return getAddress1();
	           }
	           if(name.equals("address2"))
	           {
	        	   return getAddress2();
	           }
	           if(name.equals("city"))
	           {
	        	   return getCity();
	           }
	           if(name.equals("state"))
	           {
	        	   return getState();
	           }
	           if(name.equals("zip"))
	           {
	        	   return getZip();
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
	           if (name.equals("tis_sig_case"))
	         {
	        	   if (value != null && ! String.class.isInstance(value))
	               {
	                   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	               }
	        	   setTis_sig_case((String)value);
	                 return true;
	             }
	           if (name.equals("tis_vendor_code"))
	         {
	        	   if (value != null && ! String.class.isInstance(value))
             {
                 throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
             }
      	   		setTis_vendor_code((String)value);
               return true;
	             }
	           if (name.equals("case_number"))
	            {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCase_number((String)value);
	        	   return true;
	             }
	           if (name.equals("case_sequence_number"))
	         { 
	        	   if(value != null && !String.class.isInstance(value))
      	   {
      		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
      	   }
      	   setCase_sequence_number((String)value);
      	   return true;
	             }
	           if (name.equals("case_year_date"))
	         { if(value != null && !String.class.isInstance(value))
      	   {
      		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
      	   }
      	   setCase_year_date((String)value);
      	   return true;
	             }
	           if (name.equals("book_number"))
	             {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setBook_number((String)value);
	        	   return true;
	             }
	           if (name.equals("page_number"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setPage_number((String)value);
	        	   return true;
	             }
	           if (name.equals("unique_id"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setUnique_id((String)value);
	        	   return true;
	             }
	           if (name.equals("result_code"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setResult_code((String)value);
	        	   return true;
	             }
	           if (name.equals("result_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setResult_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("result_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setResult_date((String)value);
	        	   return true;
	             }
	           if (name.equals("file_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setFile_date((String)value);
	        	   return true;
	             }
	           if (name.equals("input_by"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setInput_by((String)value);
	        	   return true;
	             }
	           if (name.equals("input_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setInput_date((String)value);
	        	   return true;
	             }
	           if (name.equals("executed_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setExecuted_date((String)value);
	        	   return true;
	             }
	           if (name.equals("received_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setReceived_date((String)value);
	        	   return true;
	             }
	           if (name.equals("satisfied_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setSatisfied_date((String)value);
	        	   return true;
	             }
	           if (name.equals("verified_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setVerified_date((String)value);
	        	   return true;
	             }
	           if (name.equals("source_id"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setSource_id((String)value);
	        	   return true;
	             }
	           if (name.equals("record_status"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setRecord_status((String)value);
	        	   return true;
	             }
	           if (name.equals("judgement_amount"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setJudgement_amount((String)value);
	        	   return true;
	             }
	           if (name.equals("judgement_flag"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setJudgement_flag((String)value);
	        	   return true;
	             }
	           if (name.equals("state_abbr"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setState_abbr((String)value);
	        	   return true;
	             }
	           if (name.equals("court_type_code"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCourt_type_code((String)value);
	        	   return true;
	             }
	           if (name.equals("court_type_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCourt_type_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("courthouse_sub_code"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCourthouse_sub_code((String)value);
	        	   return true;
	             }
	           if (name.equals("county_fips_code"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCounty_fips_code((String)value);
	        	   return true;
	             }
	           if (name.equals("county_fips_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCounty_fips_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("state_fips_code"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setState_fips_code((String)value);
	        	   return true;
	             }
	           if (name.equals("state_fips_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setState_fips_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("affiliate"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setAffiliate((String)value);
	        	   return true;
	             }
	           if (name.equals("possession_flag"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setPossession_flag((String)value);
	        	   return true;
	             }
	           if (name.equals("possession_flag_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setPossession_flag_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("region"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setRegion((String)value);
	        	   return true;
	             }
	           if (name.equals("tis_sig_defendant"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setTis_sig_defendant((String)value);
	        	   return true;
	             }
	           if (name.equals("defendant_number"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setDefendant_number((String)value);
	        	   return true;
	             }
	           if (name.equals("d_county_fips_code"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_county_fips_code((String)value);
	        	   return true;
	             }
	           if (name.equals("d_county_fips_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_county_fips_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("d_state_fips_code"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_state_fips_code((String)value);
	        	   return true;
	             }
	           if (name.equals("d_state_fips_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_state_fips_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("d_ssn"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_ssn((String)value);
	        	   return true;
	             }
	           if (name.equals("d_fullname"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_fullname((String)value);
	        	   return true;
	             }
	           if (name.equals("d_firstname"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_firstname((String)value);
	        	   return true;
	             }
	           if (name.equals("d_middlename"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_middlename((String)value);
	        	   return true;
	             }
	           if(name.equals("d_lastname"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_lastname((String)value);
	        	   return true;
	        	   
	           }
	           if(name.equals("d_suffix"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_suffix((String)value);
	        	   return true;
	        	   
	           }
	           if(name.equals("d_alias_flag"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_alias_flag((String)value);
	        	   return true;
	           }
	           if(name.equals("d_alias_firstname"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_alias_firstname((String)value);
	        	   return true;
	        	   
	           }
	           if(name.equals("d_alias_middlename"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_alias_middlename((String)value);
	        	   return true;
	           }
	           if(name.equals("d_alias_lastname"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_alias_lastname((String)value);
	        	   return true;
	           }
	           if(name.equals("d_alias_suffix"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_alias_suffix((String)value);
	        	   return true;
	           }
	           if(name.equals("d_birth_date"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_birth_date((String)value);
	        	   return true;
	           }
	           if(name.equals("d_address_line_1"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_address_line_1((String)value);
	        	   return true;
	           }
	           if(name.equals("d_address_line_2"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_address_line_2((String)value);
	        	   return true;
	           }
	           if(name.equals("d_apartment_number"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_apartment_number((String)value);
	        	   return true;
	           }
	           if(name.equals("d_city"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_city((String)value);
	        	   return true;
	           }
	           if(name.equals("d_state"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_state((String)value);
	        	   return true;
	           }
	           if(name.equals("d_zipcode"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_zipcode((String)value);
	        	   return true;
	           }
	           if(name.equals("d_phone"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_phone((String)value);
	        	   return true;
	           }
	           if(name.equals("d_clean_area_code"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_clean_area_code((String)value);
	        	   return true;
	           }

	           if(name.equals("d_clean_phone_number"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setD_clean_phone_number((String)value);
	        	   return true;
	           }
	           if(name.equals("tis_sig_plaintiff"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setTis_sig_plaintiff((String)value);
	        	   return true;
	           }
	           if(name.equals("plaintiff_type"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setPlaintiff_type((String)value);
	        	   return true;
	           }
	           if(name.equals("plaintiff_desc"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setPlaintiff_desc((String)value);
	        	   return true;
	           }
	           if(name.equals("p_county_fips_code"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_county_fips_code((String)value);
	        	   return true;
	           }
	           if(name.equals("p_county_fips_desc"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_county_fips_desc((String)value);
	        	   return true;
	           }
	           if(name.equals("p_state_fips_code"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_state_fips_code((String)value);
	        	   return true;
	           }
	           if(name.equals("p_state_fips_desc"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_state_fips_desc((String)value);
	        	   return true;
	           }
	           if(name.equals("p_fullname"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_fullname((String)value);
	        	   return true;
	           }
	           if(name.equals("p_firstname"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_firstname((String)value);
	        	   return true;
	           }
	           if(name.equals("p_middlename"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_middlename((String)value);
	        	   return true;
	           }
	           if(name.equals("p_lastname"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_lastname((String)value);
	        	   return true;
	           }
	           if(name.equals("p_address_line_1"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_address_line_1((String)value);
	        	   return true;
	           }
	           if(name.equals("p_address_line_2"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_address_line_2((String)value);
	        	   return true;
	           }
	           if(name.equals("p_city"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_city((String)value);
	        	   return true;
	           }
	           if(name.equals("p_state"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_state((String)value);
	        	   return true;
	           }
	           if(name.equals("p_zipcode"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_zipcode((String)value);
	        	   return true;
	           }
	           if(name.equals("p_phone_number"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_phone_number((String)value);
	        	   return true;
	           }
	           
	           if(name.equals("p_clean_area_code"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_clean_area_code((String)value);
	        	   return true;
	           }
	           if(name.equals("p_clean_phone_number"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setP_clean_phone_number((String)value);
	        	   return true;
	           }
	           if(name.equals("name"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setName((String)value);
	        	   return true;
	           }
	           if(name.equals("address1"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setAddress1((String)value);
	        	   return true;
	           }
	           if(name.equals("address2"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setAddress2((String)value);
	        	   return true;
	           }
	           if(name.equals("city"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCity((String)value);
	        	   return true;
	           }
	           if(name.equals("state"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setState((String)value);
	        	   return true;
	           }
	           if(name.equals("zip"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setZip((String)value);
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
	          if (name.equals(EvictionResultsPeer.RESULT_ID))
	        {
	                return new Long(getResultId());
	            }
	          if (name.equals(EvictionResultsPeer.USER_SEARCH_ID))
	        {
	                return new Long(getUserSearchId());
	            }
	          if (name.equals(EvictionResultsPeer.TIS_SIG_CASE))
	        {
	                return getTis_sig_case();
	            }
	          if (name.equals(EvictionResultsPeer.TIS_VENDOR_CODE))
	        {
	                return getTis_vendor_code();
	            }
	          if (name.equals(EvictionResultsPeer.CASE_NUMBER))
	        {
	                return getCase_number();
	            }
	          if (name.equals(EvictionResultsPeer.CASE_SEQUENCE_NUMBER))
	        {
	                return getCase_sequence_number();
	            }
	          if (name.equals(EvictionResultsPeer.CASE_YEAR_DATE))
	        {
	                return getCase_year_date();
	            }
	          if (name.equals(EvictionResultsPeer.BOOK_NUMBER))
	        {
	                return getBook_number();
	            }
	          if (name.equals(EvictionResultsPeer.PAGE_NUMBER))
	        {
	                return getPage_number();
	            }
	          if (name.equals(EvictionResultsPeer.UNIQUE_ID))
	        {
	                return getUnique_id();
	            }
	          if (name.equals(EvictionResultsPeer.RESULT_CODE))
	        {
	                return getResult_code();
	            }
	          if (name.equals(EvictionResultsPeer.RESULT_DESC))
	        {
	                return getResult_desc();
	            }
	          if (name.equals(EvictionResultsPeer.RESULT_DATE))
	        {
	                return getResult_date();
	            }
	          if (name.equals(EvictionResultsPeer.FILE_DATE))
	        {
	                return getFile_date();
	            }
	          if (name.equals(EvictionResultsPeer.INPUT_BY))
	        {
	                return getInput_by();
	            }
	          if (name.equals(EvictionResultsPeer.INPUT_DATE))
	        {
	                return getInput_date();
	            }
	          if (name.equals(EvictionResultsPeer.EXECUTED_DATE))
	        {
	                return getExecuted_date();
	            }
	          if (name.equals(EvictionResultsPeer.RECEIVED_DATE))
	        {
	                return getReceived_date();
	            }
	          if (name.equals(EvictionResultsPeer.SATISFIED_DATE))
	        {
	                return getSatisfied_date();
	            }
	          if (name.equals(EvictionResultsPeer.VERIFIED_DATE))
	        {
	                return getVerified_date();
	            }
	         
	          if (name.equals(EvictionResultsPeer.SOURCE_ID))
	        {
	                return getSource_id();
	            }
	          if (name.equals(EvictionResultsPeer.RECORD_STATUS))
	        {
	                return getRecord_status();
	            }
	          if (name.equals(EvictionResultsPeer.JUDGEMENT_AMOUNT))
		        {
		                return getJudgement_amount();
		            }
	          if (name.equals(EvictionResultsPeer.JUDGEMENT_FLAG))
	        {
	                return getJudgement_flag();
	            }
	          if (name.equals(EvictionResultsPeer.STATE_ABBR))
	        {
	                return getState_abbr();
	            }
	          if (name.equals(EvictionResultsPeer.COURT_TYPE_CODE))
	        {
	                return getCourt_type_code();
	            }
	          if (name.equals(EvictionResultsPeer.COURT_TYPE_DESC))
	        {
	                return getCourt_type_desc();
	            }
	          if (name.equals(EvictionResultsPeer.COURTHOUSE_SUB_CODE))
	        {
	                return getCourthouse_sub_code();
	            }
	          if (name.equals(EvictionResultsPeer.COUNTY_FIPS_CODE))
	        {
	                return getCounty_fips_code();
	            }
	          if (name.equals(EvictionResultsPeer.COUNTY_FIPS_DESC))
	        {
	                return getCounty_fips_desc();
	            }
	          if (name.equals(EvictionResultsPeer.STATE_FIPS_CODE))
	        {
	                return getState_fips_code();
	            }
	          if (name.equals(EvictionResultsPeer.STATE_FIPS_DESC))
	        {
	                return getState_fips_desc();
	            }
	          if (name.equals(EvictionResultsPeer.AFFILIATE))
	        {
	                return getAffiliate();
	            }
	          if (name.equals(EvictionResultsPeer.POSSESSION_FLAG))
	        {
	                return getPossession_flag();
	            }
	          if (name.equals(EvictionResultsPeer.POSSESSION_FLAG_DESC))
	        {
	                return getPossession_flag_desc();
	            }
	          if (name.equals(EvictionResultsPeer.REGION))
	        {
	                return getRegion();
	            }
	          if (name.equals(EvictionResultsPeer.TIS_SIG_DEFENDANT))
	        {
	                return getTis_sig_defendant();
	            }
	          if (name.equals(EvictionResultsPeer.DEFENDANT_NUMBER))
	        {
	                return getDefendant_number();
	            }
	          if (name.equals(EvictionResultsPeer.D_COUNTY_FIPS_CODE))
	        {
	                return getD_county_fips_code();
	            }
	          if (name.equals(EvictionResultsPeer.D_COUNTY_FIPS_DESC))
	        {
	                return getD_county_fips_desc();
	                
	            }
	          if (name.equals(EvictionResultsPeer.D_STATE_FIPS_CODE))
	        {
	                return getD_state_fips_code();
	            }
	          if (name.equals(EvictionResultsPeer.D_STATE_FIPS_DESC))
	        {
	                return getD_state_fips_desc();
	            }
	          if (name.equals(EvictionResultsPeer.D_SSN))
	        {
	                return getD_ssn();
	            }
	          if (name.equals(EvictionResultsPeer.D_FULLNAME))
	        {
	                return getD_fullname();
	            }
	          if (name.equals(EvictionResultsPeer.D_FIRSTNAME))
	        {
	                return getD_firstname();
	            }
	          if (name.equals(EvictionResultsPeer.D_MIDDLENAME))
	        {
	                return getD_middlename();
	            }
	          
	          if (name.equals(EvictionResultsPeer.D_LASTNAME))
		        {
		                return getD_lastname();
		            }
		          if (name.equals(EvictionResultsPeer.D_SUFFIX))
		        {
		                return getD_suffix();
		            }
		          if (name.equals(EvictionResultsPeer.D_ALIAS_FLAG))
		        {
		                return getD_alias_flag();
		            }
		          if (name.equals(EvictionResultsPeer.D_ALIAS_FIRSTNAME))
		        {
		                return getD_alias_firstname();
		            }
		          if (name.equals(EvictionResultsPeer.D_ALIAS_MIDDLENAME))
		        {
		                return getD_alias_middlename();
		            }
		          if (name.equals(EvictionResultsPeer.D_ALIAS_LASTNAME))
		        {
		                return getD_alias_lastname();
		            }
		          if (name.equals(EvictionResultsPeer.D_ALIAS_SUFFIX))
		        {
		                return getD_alias_suffix();
		            }
		          if (name.equals(EvictionResultsPeer.D_BIRTH_DATE))
		        {
		                return getD_birth_date();
		            }
		          if (name.equals(EvictionResultsPeer.D_ADDRESS_LINE_1))
		        {
		                return getD_address_line_1();
		            }
		          if (name.equals(EvictionResultsPeer.D_ADDRESS_LINE_2))
		        {
		                return getD_address_line_2();
		            }
		          
		          
		          
		          if (name.equals(EvictionResultsPeer.D_APARTMENT_NUMBER))
			        {
			                return getD_apartment_number();
			            }
			          if (name.equals(EvictionResultsPeer.D_CITY))
			        {
			                return getD_city();
			            }
			          if (name.equals(EvictionResultsPeer.D_STATE))
			        {
			                return getD_state();
			            }
			          if (name.equals(EvictionResultsPeer.D_ZIPCODE))
			        {
			                return getD_zipcode();
			            }
			          if (name.equals(EvictionResultsPeer.D_PHONE))
			        {
			                return getD_phone();
			            }
			          if (name.equals(EvictionResultsPeer.D_CLEAN_AREA_CODE))
			        {
			                return getD_clean_area_code();
			            }
			          if (name.equals(EvictionResultsPeer.D_CLEAN_PHONE_NUMBER))
			        {
			                return getD_clean_phone_number();
			            }
			          
			          if (name.equals(EvictionResultsPeer.TIS_SIG_PLAINTIFF))
				        {
				                return getTis_sig_plaintiff();
				            }
				          if (name.equals(EvictionResultsPeer.PLAINTIFF_TYPE))
				        {
				                return getPlaintiff_type();
				            }
				          if (name.equals(EvictionResultsPeer.PLAINTIFF_DESC))
				        {
				                return getPlaintiff_desc();
				            }
				          if (name.equals(EvictionResultsPeer.P_COUNTY_FIPS_CODE))
				        {
				                return getP_county_fips_code();
				            }
				          if (name.equals(EvictionResultsPeer.P_COUNTY_FIPS_DESC))
				        {
				                return getP_county_fips_desc();
				            }
				          if (name.equals(EvictionResultsPeer.P_STATE_FIPS_CODE))
				        {
				                return getP_state_fips_code();
				            }
				          if (name.equals(EvictionResultsPeer.P_STATE_FIPS_DESC))
				        {
				                return getP_state_fips_desc();
				            }
				          if (name.equals(EvictionResultsPeer.P_FULLNAME))
				        {
				                return getP_fullname();
				            }
				          if (name.equals(EvictionResultsPeer.P_FIRSTNAME))
				        {
				                return getP_firstname();
				            }
				          if (name.equals(EvictionResultsPeer.P_MIDDLENAME))
				        {
				                return getP_middlename();
				            }
				          if (name.equals(EvictionResultsPeer.P_LASTNAME))
					        {
					                return getP_lastname();
					            }
					          
				          
				          
				          if (name.equals(EvictionResultsPeer.P_ADDRESS_LINE_1))
					        {
					                return getAddress1();
					            }
					          if (name.equals(EvictionResultsPeer.P_ADDRESS_LINE_2))
					        {
					                return getAddress2();
					            }
					          if (name.equals(EvictionResultsPeer.P_CITY))
					        {
					                return getP_city();
					            }
					          if (name.equals(EvictionResultsPeer.P_STATE))
					        {
					                return getP_state();
					            }
					          if (name.equals(EvictionResultsPeer.P_ZIPCODE))
					        {
					                return getP_zipcode();
					            }
					          if (name.equals(EvictionResultsPeer.P_PHONE_NUMBER))
					        {
					                return getP_phone_number();
					            }
					          
					          if (name.equals(EvictionResultsPeer.P_CLEAN_AREA_CODE))
						        {
						                return getP_clean_area_code();
						            }
					          if (name.equals(EvictionResultsPeer.P_CLEAN_PHONE_NUMBER))
						        {
						                return getP_clean_phone_number();
						            }
					          if (name.equals(EvictionResultsPeer.NAME))
						        {
						                return getName();
						            }
					          if (name.equals(EvictionResultsPeer.ADDRESS1))
						        {
						                return getAddress1();
						            }
					          if (name.equals(EvictionResultsPeer.ADDRESS2))
						        {
						                return getAddress2();
						            }
					          if (name.equals(EvictionResultsPeer.CITY))
						        {
						                return getCity();
						            }
					          if (name.equals(EvictionResultsPeer.STATE))
						        {
						                return getState();
						            }
					          if (name.equals(EvictionResultsPeer.ZIP))
						        {
						                return getZip();
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
	        if (EvictionResultsPeer.RESULT_ID.equals(name))
	        {
	            return setByName("ResultId", value);
	        }
	        if (EvictionResultsPeer.USER_SEARCH_ID.equals(name))
	        {
	            return setByName("UserSearchId", value);
	        }
	        if (EvictionResultsPeer.TIS_SIG_CASE.equals(name))
	        {
	            return setByName("tis_sig_case", value);
	        }
	        if (EvictionResultsPeer.TIS_VENDOR_CODE.equals(name))
	        {
	            return setByName("tis_vendor_code", value);
	        }
	        if (EvictionResultsPeer.CASE_NUMBER.equals(name))
	        {
	            return setByName("case_number", value);
	        }
	        if (EvictionResultsPeer.CASE_SEQUENCE_NUMBER.equals(name))
	        {
	            return setByName("case_sequence_number", value);
	        }
	        if (EvictionResultsPeer.CASE_YEAR_DATE.equals(name))
	        {
	            return setByName("case_year_date", value);
	        }
	        if (EvictionResultsPeer.BOOK_NUMBER.equals(name))
	        {
	            return setByName("book_number", value);
	        }
	        if (EvictionResultsPeer.PAGE_NUMBER.equals(name))
	        {
	            return setByName("page_number", value);
	        }
	        if (EvictionResultsPeer.UNIQUE_ID.equals(name))
	        {
	            return setByName("unique_id", value);
	        }
	        if (EvictionResultsPeer.RESULT_CODE.equals(name))
	        {
	            return setByName("result_code", value);
	        }
	        if (EvictionResultsPeer.RESULT_DESC.equals(name))
	        {
	            return setByName("result_desc", value);
	        }
	        if (EvictionResultsPeer.RESULT_DATE.equals(name))
	        {
	            return setByName("result_date", value);
	        }
	        if (EvictionResultsPeer.FILE_DATE.equals(name))
	        {
	            return setByName("file_date", value);
	        }
	        if (EvictionResultsPeer.INPUT_BY.equals(name))
	        {
	            return setByName("input_by", value);
	        }
	        if (EvictionResultsPeer.INPUT_DATE.equals(name))
	        {
	            return setByName("input_date", value);
	        }
	        if (EvictionResultsPeer.EXECUTED_DATE.equals(name))
	        {
	            return setByName("executed_date", value);
	        }
	        if (EvictionResultsPeer.RECEIVED_DATE.equals(name))
	        {
	            return setByName("received_date", value);
	        }
	        if (EvictionResultsPeer.SATISFIED_DATE.equals(name))
	        {
	            return setByName("satisfied_date", value);
	        }
	        if (EvictionResultsPeer.VERIFIED_DATE.equals(name))
	        {
	            return setByName("verified_date", value);
	        }
	        if (EvictionResultsPeer.SOURCE_ID.equals(name))
	        {
	            return setByName("source_id", value);
	        }
	        if (EvictionResultsPeer.RECORD_STATUS.equals(name))
	        {
	            return setByName("record_status", value);
	        }
	        if (EvictionResultsPeer.JUDGEMENT_AMOUNT.equals(name))
	        {
	            return setByName("judgement_amount", value);
	        }
	        if (EvictionResultsPeer.JUDGEMENT_FLAG.equals(name))
	        {
	            return setByName("judgement_flag", value);
	        }
	        if (EvictionResultsPeer.STATE_ABBR.equals(name))
	        {
	            return setByName("state_abbr", value);
	        }
	        if (EvictionResultsPeer.COURT_TYPE_CODE.equals(name))
	        {
	            return setByName("court_type_code", value);
	        }
	        if (EvictionResultsPeer.COURT_TYPE_DESC.equals(name))
	        {
	            return setByName("court_type_desc", value);
	        }
	        if (EvictionResultsPeer.COURTHOUSE_SUB_CODE.equals(name))
	        {
	            return setByName("courthouse_sub_code", value);
	        }
	        if (EvictionResultsPeer.COUNTY_FIPS_CODE.equals(name))
	        {
	            return setByName("county_fips_code", value);
	        }
	        if (EvictionResultsPeer.COUNTY_FIPS_DESC.equals(name))
	        {
	            return setByName("county_fips_desc", value);
	        }
	        if (EvictionResultsPeer.STATE_FIPS_CODE.equals(name))
	        {
	            return setByName("state_fips_code", value);
	        }
	        if (EvictionResultsPeer.STATE_FIPS_DESC.equals(name))
	        {
	            return setByName("state_fips_desc", value);
	        }
	        if (EvictionResultsPeer.AFFILIATE.equals(name))
	        {
	            return setByName("affiliate", value);
	        }
	        if (EvictionResultsPeer.POSSESSION_FLAG.equals(name))
	        {
	            return setByName("possession_flag", value);
	        }
	        if (EvictionResultsPeer.POSSESSION_FLAG_DESC.equals(name))
	        {
	            return setByName("possession_flag_desc", value);
	        }
	        if (EvictionResultsPeer.REGION.equals(name))
	        {
	            return setByName("region", value);
	        }
	        //evictions_defendant_list
	        if (EvictionResultsPeer.TIS_SIG_DEFENDANT.equals(name))
	        {
	            return setByName("tis_sig_defendant", value);
	        }
	        if (EvictionResultsPeer.DEFENDANT_NUMBER.equals(name))
	        {
	            return setByName("defendant_number", value);
	        }
	        if (EvictionResultsPeer.D_COUNTY_FIPS_CODE.equals(name))
	        {
	            return setByName("d_county_fips_code", value);
	        }
	        if (EvictionResultsPeer.D_COUNTY_FIPS_DESC.equals(name))
	        {
	            return setByName("d_county_fips_desc", value);
	        }
	        if (EvictionResultsPeer.D_STATE_FIPS_CODE.equals(name))
	        {
	            return setByName("d_state_fips_code", value);
	        }
	        if (EvictionResultsPeer.D_STATE_FIPS_DESC.equals(name))
	        {
	            return setByName("d_state_fips_desc", value);
	        }
	        if (EvictionResultsPeer.D_SSN.equals(name))
	        {
	            return setByName("d_ssn", value);
	        }
	        if (EvictionResultsPeer.D_FULLNAME.equals(name))
	        {
	            return setByName("d_fullname", value);
	        }
	        if (EvictionResultsPeer.D_FIRSTNAME.equals(name))
	        {
	            return setByName("d_firstname", value);
	        }
	        if (EvictionResultsPeer.D_MIDDLENAME.equals(name))
	        {
	            return setByName("d_middlename", value);
	        }
	        
	        
	        
	        if (EvictionResultsPeer.D_LASTNAME.equals(name))
	        {
	            return setByName("d_lastname", value);
	        }
	        if (EvictionResultsPeer.D_SUFFIX.equals(name))
	        {
	            return setByName("d_suffix", value);
	        }
	        if (EvictionResultsPeer.D_ALIAS_FLAG.equals(name))
	        {
	            return setByName("d_alias_flag", value);
	        }
	        if (EvictionResultsPeer.D_ALIAS_FIRSTNAME.equals(name))
	        {
	            return setByName("d_alias_firstname", value);
	        }
	        if (EvictionResultsPeer.D_ALIAS_MIDDLENAME.equals(name))
	        {
	            return setByName("d_alias_middlename", value);
	        }
	        if (EvictionResultsPeer.D_ALIAS_LASTNAME.equals(name))
	        {
	            return setByName("d_alias_lastname", value);
	        }
	        if (EvictionResultsPeer.D_ALIAS_SUFFIX.equals(name))
	        {
	            return setByName("d_alias_suffix", value);
	        }
	        if (EvictionResultsPeer.D_BIRTH_DATE.equals(name))
	        {
	            return setByName("d_birth_date", value);
	        }
	        if (EvictionResultsPeer.D_ADDRESS_LINE_1.equals(name))
	        {
	            return setByName("d_address_line_1", value);
	        }
	        if (EvictionResultsPeer.D_ADDRESS_LINE_2.equals(name))
	        {
	            return setByName("d_address_line_2", value);
	        }
	        if (EvictionResultsPeer.D_APARTMENT_NUMBER.equals(name))
	        {
	            return setByName("d_apartment_number", value);
	        }
	        if (EvictionResultsPeer.D_CITY.equals(name))
	        {
	            return setByName("d_city", value);
	        }

	        
	        
	        if (EvictionResultsPeer.D_STATE.equals(name))
	        {
	            return setByName("d_state", value);
	        }
	        if (EvictionResultsPeer.D_ZIPCODE.equals(name))
	        {
	            return setByName("d_zipcode", value);
	        }
	        if (EvictionResultsPeer.D_PHONE.equals(name))
	        {
	            return setByName("d_phone", value);
	        }
	        if (EvictionResultsPeer.D_CLEAN_AREA_CODE.equals(name))
	        {
	            return setByName("d_clean_area_code", value);
	        }
	        if (EvictionResultsPeer.D_CLEAN_PHONE_NUMBER.equals(name))
	        {
	            return setByName("d_clean_phone_number", value);
	        }
	        if (EvictionResultsPeer.TIS_SIG_PLAINTIFF.equals(name))
	        {
	            return setByName("tis_sig_plaintiff", value);
	        }
	        if (EvictionResultsPeer.PLAINTIFF_TYPE.equals(name))
	        {
	            return setByName("plaintiff_type", value);
	        }
	        if (EvictionResultsPeer.PLAINTIFF_DESC.equals(name))
	        {
	            return setByName("plaintiff_desc", value);
	        }
	        if (EvictionResultsPeer.P_COUNTY_FIPS_CODE.equals(name))
	        {
	            return setByName("p_county_fips_code", value);
	        }
	        if (EvictionResultsPeer.P_COUNTY_FIPS_DESC.equals(name))
	        {
	            return setByName("p_county_fips_desc", value);
	        }
	        if (EvictionResultsPeer.P_STATE_FIPS_CODE.equals(name))
	        {
	            return setByName("p_state_fips_code", value);
	        }
	        if (EvictionResultsPeer.P_STATE_FIPS_DESC.equals(name))
	        {
	            return setByName("p_state_fips_desc", value);
	        }
	        if (EvictionResultsPeer.P_FULLNAME.equals(name))
	        {
	            return setByName("p_fullname", value);
	        }
	        if (EvictionResultsPeer.P_FIRSTNAME.equals(name))
	        {
	            return setByName("p_firstname", value);
	        }

	        
	        
	        if (EvictionResultsPeer.P_MIDDLENAME.equals(name))
	        {
	            return setByName("p_middlename", value);
	        }
	        if (EvictionResultsPeer.P_LASTNAME.equals(name))
	        {
	            return setByName("p_lastname", value);
	        }
	        if (EvictionResultsPeer.P_ADDRESS_LINE_1.equals(name))
	        {
	            return setByName("p_address_line_1", value);
	        }
	        if (EvictionResultsPeer.P_ADDRESS_LINE_2.equals(name))
	        {
	            return setByName("p_address_line_2", value);
	        }
	        if (EvictionResultsPeer.P_CITY.equals(name))
	        {
	            return setByName("p_city", value);
	        }
	        if (EvictionResultsPeer.P_STATE.equals(name))
	        {
	            return setByName("p_state", value);
	        }
	        if (EvictionResultsPeer.P_ZIPCODE.equals(name))
	        {
	            return setByName("p_zipcode", value);
	        }
	        if (EvictionResultsPeer.P_PHONE_NUMBER.equals(name))
	        {
	            return setByName("p_phone_number", value);
	        }
	        if (EvictionResultsPeer.P_CLEAN_AREA_CODE.equals(name))
	        {
	            return setByName("p_clean_area_code", value);
	        }
	        
	        if (EvictionResultsPeer.P_CLEAN_PHONE_NUMBER.equals(name))
	        {
	            return setByName("p_clean_phone_number", value);
	        }
	        if (EvictionResultsPeer.NAME.equals(name))
	        {
	            return setByName("name", value);
	        }
	        if (EvictionResultsPeer.ADDRESS1.equals(name))
	        {
	            return setByName("address1", value);
	        }
	        if (EvictionResultsPeer.ADDRESS2.equals(name))
	        {
	            return setByName("address2", value);
	        }
	        if (EvictionResultsPeer.CITY.equals(name))
	        {
	            return setByName("city", value);
	        }
	        if (EvictionResultsPeer.STATE.equals(name))
	        {
	            return setByName("state", value);
	        }
	        if (EvictionResultsPeer.ZIP.equals(name))
	        {
	            return setByName("zip", value);
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
	                return getTis_sig_case();
	            }
	              if (pos == 3)
	        {
	                return getTis_vendor_code();
	            }
	              if (pos == 4)
	        {
	                return getCase_number();
	            }
	              if (pos == 5)
	        {
	                return getCase_sequence_number();
	            }
	              if (pos == 6)
	        {
	                return getCase_year_date();
	            }
	              if (pos == 7)
	        {
	                return getBook_number();
	            }
	              if (pos == 8)
	        {
	                return getPage_number();
	            }
	              if (pos == 9)
	        {
	                return getUnique_id();
	            }
	              if (pos == 10)
	        {
	                return getResult_code();
	            }
	              if (pos == 11)
	        {
	                return getResult_desc();
	            }
	              if (pos == 12)
	        {
	                return getResult_date();
	            }
	              if (pos == 13)
	        {
	                return getFile_date();
	            }
	              if (pos == 14)
	        {
	                return getInput_by();
	            }
	              if (pos == 15)
	        {
	                return getInput_date();
	            }
	              if (pos == 16)
	        {
	                return getExecuted_date();
	            }
	              if (pos == 17)
	        {
	                return getReceived_date();
	            }
	              if (pos == 18)
	        {
	                return getSatisfied_date();
	            }
	              if (pos == 19)
	        {
	                return getVerified_date();
	            }
	              if (pos == 20)
	        {
	                return getSource_id();
	            }
	              if (pos == 21)
	        {
	                return getRecord_status();
	            }
	              if (pos == 22)
	        {
	                return getJudgement_amount();
	            }
	              if (pos == 23)
	        {
	                return getJudgement_flag();
	            }
	              if (pos == 24)
	        {
	                return getState_abbr();
	            }
	              if (pos == 25)
	        {
	                return getCourt_type_code();
	            }
	              if (pos == 26)
	        {
	                return getCourt_type_desc();
	            }
	              if (pos == 27)
	        {
	                return getCourthouse_sub_code();
	            }
	              if (pos == 28)
	        {
	                return getCounty_fips_code();
	            }
	              if (pos == 29)
	        {
	                return getCounty_fips_desc();
	            }
	              if (pos == 30)
	        {
	                return getState_fips_code();
	            }
	              if (pos == 31)
	        {
	                return getState_fips_desc();
	            }
	              if (pos == 32)
	        {
	                return getAffiliate();
	            }
	              if (pos == 33)
	        {
	                return getPossession_flag();
	            }
	              if (pos == 34)
	        {
	                return getPossession_flag_desc();
	            }
	              if (pos == 35)
	        {
	                return getRegion();
	            }
	              if (pos == 36)
	        {
	                return getTis_sig_defendant();
	            }
	              if (pos == 37)
	        {
	                return getDefendant_number();
	            }
	              if (pos == 38)
	        {
	                return getD_county_fips_code();
	            }
	              if (pos == 39)
	        {
	                return getD_county_fips_desc();
	            }
	              if (pos == 40)
	        {
	                return getD_state_fips_code();
	            }
	              if (pos == 41)
	        {
	                return getD_state_fips_desc();
	            }
	              if (pos == 42)
	        {
	                return getD_ssn();
	            }
	              if (pos == 43)
	        {
	                return getD_fullname();
	            }
	              if (pos == 44)
	        {
	                return getD_firstname();
	            }
	              if (pos == 45)
	        {
	                return getD_middlename();
	            }
	              if(pos == 46){
	            	  return getD_lastname();
	              }
	              if(pos == 47){
	            	  return getD_suffix();
	              }
	              if(pos == 48){
	            	  return getD_alias_flag();
	              }
	              if(pos == 49){
	            	  return getD_alias_firstname();
	              }
	              if(pos == 50){
	            	  return getD_alias_middlename();
	              }
	              if(pos == 51){
	            	  return getD_alias_lastname();
	              }
	              
	              
	              if(pos == 52){
	            	  return getD_alias_suffix();
	              }
	              if(pos == 53){
	            	  return getD_birth_date();
	              }
	              if(pos == 54){
	            	  return getD_address_line_1();
	              }
	              if(pos == 55){
	            	  return getD_address_line_2();
	              }
	              if(pos == 56){
	            	  return getD_apartment_number();
	              }
	              if(pos == 57){
	            	  return getD_city();
	              }
	              if(pos == 58){
	            	  return getD_state();
	              }
	              if(pos == 59){
	            	  return getD_zipcode();
	              }
	              if(pos == 60){
	            	  return getD_phone();
	              }
	              if(pos == 61){
	            	  return getD_clean_area_code();
	              }
	              if(pos == 62){
	            	  return getD_clean_phone_number();
	              }
	              if(pos == 63){
	            	  return getTis_sig_plaintiff();
	              }

	              
	              if(pos == 64){
	            	  return getPlaintiff_type();
	              }
	              if(pos == 65){
	            	  return getPlaintiff_desc();
	              }
	              if(pos == 66){
	            	  return getP_county_fips_code();
	              }
	              if(pos == 67){
	            	  return getP_county_fips_desc();
	              }
	              if(pos == 68){
	            	  return getP_state_fips_code();
	              }
	              if(pos == 69){
	            	  return getP_state_fips_desc();
	              }
	              if(pos == 70){
	            	  return getP_fullname();
	              }
	              if(pos == 71){
	            	  return getP_firstname();
	              }
	              if(pos == 72){
	            	  return getP_middlename();
	              }
	              if(pos == 73){
	            	  return getP_lastname();
	              }
	              if(pos == 74){
	            	  return getP_address_line_1();
	              }
	              if(pos == 75){
	            	  return getP_address_line_2();
	              }
	              if(pos == 76){
	            	  return getP_city();
	              }
	              if(pos == 77){
	            	  return getP_state();
	              }
	              if(pos == 78){
	            	  return getP_zipcode();
	              }
	              if(pos == 79){
	            	  return getP_phone_number();
	              }
	              
	              if(pos == 80){
	            	  return getP_clean_area_code();
	              }
	              if(pos == 81){
	            	  return getP_clean_phone_number();
	              }
	              if(pos == 82){
	            	  return getName();
	              }
	              if(pos == 83){
	            	  return getAddress1();
	              }
	              if(pos == 84){
	            	  return getAddress2();
	              }
	              if(pos == 85){
	            	  return getCity();
	              }
	              if(pos == 86){
	            	  return getState();
	              }
	              if(pos == 87){
	            	  return getZip();
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
		                return setByName("tis_sig_case", value);
		            }
		              if (position == 3)
		        {
		                return setByName("tis_vendor_code", value);
		            }
		              if (position == 4)
		        {
		                return setByName("case_number", value);
		            }
		              if (position == 5)
		        {
		                return setByName("case_sequence_number", value);
		            }
		              if (position == 6)
		        {
		                return setByName("case_year_date", value);
		            }
		              if (position == 7)
		        {
		                return setByName("book_number", value);
		            }
		              if (position == 8)
		        {
		                return setByName("page_number", value);
		            }
		              if (position == 9)
		        {
		                return setByName("unique_id", value);
		            }
		              if (position == 10)
		        {
		                return setByName("result_code", value);
		            }
		              if (position == 11)
		        {
		                return setByName("result_desc", value);
		            }
		              if (position == 12)
		        {
		                return setByName("result_date", value);
		            }
		              if (position == 13)
		        {
		                return setByName("file_date", value);
		            }
		              if (position == 14)
		        {
		                return setByName("input_by", value);
		            }
		              if (position == 15)
		        {
		                return setByName("input_date", value);
		            }
		              if (position == 16)
		        {
		                return setByName("executed_date", value);
		            }
		              if (position == 17)
		        {
		                return setByName("received_date", value);
		            }
		              if (position == 18)
		        {
		                return setByName("satisfied_date", value);
		            }
		              if (position == 19)
		        {
		                return setByName("verified_date", value);
		            }
		              if (position == 20)
		        {
		                return setByName("source_id", value);
		            }
		              if (position == 21)
		        {
		                return setByName("record_status", value);
		            }
		              if (position == 22)
		        {
		                return setByName("judgement_amount", value);
		            }
		              if (position == 23)
		        {
		                return setByName("judgement_flag", value);
		            }
		              if (position == 24)
		        {
		                return setByName("state_abbr", value);
		            }
		              if (position == 25)
		        {
		                return setByName("court_type_code", value);
		            }
		              if (position == 26)
		        {
		                return setByName("court_type_desc", value);
		            }
		              if (position == 27)
		        {
		                return setByName("courthouse_sub_code", value);
		            }
		              if (position == 28)
		        {
		                return setByName("county_fips_code", value);
		            }
		              if (position == 29)
		        {
		                return setByName("county_fips_desc", value);
		            }
		              if (position == 30)
		        {
		                return setByName("state_fips_code", value);
		            }
		              if (position == 31)
		        {
		                return setByName("state_fips_desc", value);
		            }
		              if (position == 32)
		        {
		                return setByName("affiliate", value);
		            }
		              if (position == 33)
		        {
		                return setByName("possession_flag", value);
		            }
		              if (position == 34)
		        {
		                return setByName("possession_flag_desc", value);
		            }
		              if (position == 35)
		        {
		                return setByName("region", value);
		            }
		              if (position == 36)
		        {
		                return setByName("tis_sig_defendant", value);
		            }
		              if (position == 37)
		        {
		                return setByName("defendant_number", value);
		            }
		              if (position == 38)
		        {
		                return setByName("d_county_fips_code", value);
		            }
		              if (position == 39)
		        {
		                return setByName("d_county_fips_desc", value);
		            }
		              if (position == 40)
		        {
		                return setByName("d_state_fips_code", value);
		            }
		              if (position == 41)
		        {
		                return setByName("d_state_fips_desc", value);
		            }
		              if (position == 42)
		        {
		                return setByName("d_ssn", value);
		            }
		              if (position == 43)
		        {
		                return setByName("d_fullname", value);
		            }
		              if (position == 44)
		        {
		                return setByName("d_firstname", value);
		            }
		              if (position == 45)
		        {
		                return setByName("d_middlename", value);
		            }
		              if(position == 46){
		            	  return setByName("d_lastname", value);
		              }
		              if(position == 47){
		            	  return setByName("d_suffix", value);
		              }
		              if(position == 48){
		            	  return setByName("d_alias_flag", value);
		              }
		              if(position == 49){
		            	  return setByName("d_alias_firstname", value);
		              }
		              if(position == 50){
		            	  return setByName("d_alias_middlename", value);
		              }
		              if(position == 51){
		            	  return setByName("d_alias_lastname", value);
		              }
		              
		              
		              if(position == 52){
		            	  return setByName("d_alias_suffix", value);
		              }
		              if(position == 53){
		            	  return setByName("d_birth_date", value);
		              }
		              if(position == 54){
		            	  return setByName("d_address_line_1", value);
		              }
		              if(position == 55){
		            	  return setByName("d_address_line_2", value);
		              }
		              if(position == 56){
		            	  return setByName("d_apartment_number", value);
		              }
		              if(position == 57){
		            	  return setByName("d_city", value);
		              }
		              if(position == 58){
		            	  return setByName("d_state", value);
		              }
		              if(position == 59){
		            	  return setByName("d_zipcode", value);
		              }
		              if(position == 60){
		            	  return setByName("d_phone", value);
		              }
		              if(position == 61){
		            	  return setByName("d_clean_area_code", value);
		              }
		              if(position == 62){
		            	  return setByName("d_clean_phone_number", value);
		              }
		              if(position == 63){
		            	  return setByName("tis_sig_plaintiff", value);
		              }

		              
		              if(position == 64){
		            	  return setByName("plaintiff_type", value);
		              }
		              if(position == 65){
		            	  return setByName("plaintiff_desc", value);
		              }
		              if(position == 66){
		            	  return setByName("p_county_fips_code", value);
		              }
		              if(position == 67){
		            	  return setByName("p_county_fips_desc", value);
		              }
		              if(position == 68){
		            	  return setByName("p_state_fips_code", value);
		              }
		              if(position == 69){
		            	  return setByName("p_state_fips_desc", value);
		              }
		              if(position == 70){
		            	  return setByName("p_fullname", value);
		              }
		              if(position == 71){
		            	  return setByName("p_firstname", value);
		              }
		              if(position == 72){
		            	  return setByName("p_middlename", value);
		              }
		              if(position == 73){
		            	  return setByName("p_lastname", value);
		              }
		              if(position == 74){
		            	  return setByName("p_address_line_1", value);
		              }
		              if(position == 75){
		            	  return setByName("p_address_line_2", value);
		              }
		              if(position == 76){
		            	  return setByName("p_city", value);
		              }
		              if(position == 77){
		            	  return setByName("p_state", value);
		              }
		              if(position == 78){
		            	  return setByName("p_zipcode", value);
		              }
		              if(position == 79){
		            	  return setByName("p_phone_number", value);
		              }
		              
		              if(position == 80){
		            	  return setByName("p_clean_area_code", value);
		              }
		              if(position == 81){
		            	  return setByName("p_clean_phone_number", value);
		              }
		              if(position == 82){
		            	  return setByName("name", value);
		              }
		              if(position == 83){
		            	  return setByName("address1", value);
		              }
		              if(position == 84){
		            	  return setByName("address2", value);
		              }
		              if(position == 85){
		            	  return setByName("city", value);
		              }
		              if(position == 86){
		            	  return setByName("state", value);
		              }
		              if(position == 87){
		            	  return setByName("zip", value);
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
	          save(EvictionResultsPeer.DATABASE_NAME);
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
	                    EvictionResultsPeer.doInsert((EvictionResults) this, con);
	                    setNew(false);
	                }
	                else
	                {
	                    EvictionResultsPeer.doUpdate((EvictionResults) this, con);
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
	      public EvictionResults copy() throws TorqueException
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
	    public EvictionResults copy(boolean deepcopy) throws TorqueException
	    {
	        return copyInto(new EvictionResults(), deepcopy);
	    }
	      
	      /**
	     * Fills the copyObj with the contents of this object.
	     * The associated objects are also copied and treated as new objects.
	     * @param copyObj the object to fill.
	     */
	    protected EvictionResults copyInto(EvictionResults copyObj) throws TorqueException
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
	      protected EvictionResults copyInto(EvictionResults copyObj, boolean deepcopy) throws TorqueException
	      {
	          copyObj.setResultId(resultId);
	          copyObj.setUserSearchId(userSearchId);
	          copyObj.setTis_sig_case(tis_sig_case);
	          copyObj.setTis_vendor_code(tis_vendor_code);
	          copyObj.setCase_number(case_number);
	          copyObj.setCase_sequence_number(case_sequence_number);
	          copyObj.setCase_year_date(case_year_date);
	          copyObj.setBook_number(book_number);
	          copyObj.setPage_number(page_number);
	          copyObj.setUnique_id(unique_id);
	          copyObj.setResult_code(result_code);
	          copyObj.setResult_desc(result_desc);
	          copyObj.setResult_date(result_date);
	          copyObj.setFile_date(file_date);
	          copyObj.setInput_by(input_by);
	          copyObj.setInput_date(input_date);
	          copyObj.setExecuted_date(executed_date);
	          copyObj.setReceived_date(received_date);
	          copyObj.setSatisfied_date(satisfied_date);
	          copyObj.setVerified_date(verified_date);
	          copyObj.setSource_id(source_id);
	          copyObj.setRecord_status(record_status);
	          copyObj.setJudgement_amount(judgement_amount);
	          copyObj.setJudgement_flag(judgement_flag);
	          copyObj.setState_abbr(state_abbr);
	          copyObj.setCourt_type_code(court_type_code);
	          copyObj.setCourt_type_desc(court_type_desc);
	          copyObj.setCourthouse_sub_code(courthouse_sub_code);
	          copyObj.setCounty_fips_code(county_fips_code);
	          copyObj.setCounty_fips_desc(county_fips_desc);
	          copyObj.setState_fips_code(state_fips_code);
	          copyObj.setState_fips_desc(state_fips_desc);
	          copyObj.setAffiliate(affiliate);
	          copyObj.setPossession_flag(possession_flag);
	          copyObj.setPossession_flag_desc(possession_flag_desc);
	          copyObj.setRegion(region);
	          copyObj.setTis_sig_defendant(tis_sig_defendant);
	          copyObj.setDefendant_number(defendant_number);
	          copyObj.setD_county_fips_code(d_county_fips_code);
	          copyObj.setD_county_fips_desc(d_county_fips_desc);
	          copyObj.setD_state_fips_code(d_state_fips_code);
	          copyObj.setD_state_fips_desc(d_state_fips_desc);
	          copyObj.setD_ssn(d_ssn);
	          copyObj.setD_fullname(d_fullname);
	          copyObj.setD_firstname(d_firstname);
	          copyObj.setD_middlename(d_middlename);
	          copyObj.setD_lastname(d_lastname);
	          copyObj.setD_suffix(d_suffix);
	          copyObj.setD_alias_flag(d_alias_flag);
	          copyObj.setD_alias_firstname(d_alias_firstname);
	          copyObj.setD_alias_middlename(d_alias_middlename);
	          copyObj.setD_alias_lastname(d_alias_lastname);
	          copyObj.setD_alias_suffix(d_alias_suffix);
	          copyObj.setD_birth_date(d_birth_date);
	          copyObj.setD_address_line_1(d_address_line_1);
	          copyObj.setD_address_line_2(d_address_line_2);
	          copyObj.setD_apartment_number(d_apartment_number);
	          copyObj.setD_city(d_city);
	          copyObj.setD_state(d_state);
	          copyObj.setD_phone(d_phone);
	          copyObj.setD_zipcode(d_zipcode);
	          copyObj.setD_clean_area_code(d_clean_area_code);
	          copyObj.setD_clean_phone_number(d_clean_phone_number);
	          copyObj.setTis_sig_plaintiff(tis_sig_plaintiff);
	          copyObj.setPlaintiff_type(plaintiff_type);
	          copyObj.setPlaintiff_desc(plaintiff_desc);
	          copyObj.setP_county_fips_code(p_county_fips_code);
	          copyObj.setP_county_fips_desc(p_county_fips_desc);
	          copyObj.setP_state_fips_code(p_state_fips_code);
	          copyObj.setP_state_fips_desc(p_state_fips_desc);
	          copyObj.setP_fullname(p_fullname);
	          copyObj.setP_firstname(p_firstname);
	          copyObj.setP_middlename(p_middlename);
	          copyObj.setP_lastname(p_lastname);
	          copyObj.setP_address_line_1(p_address_line_1);
	          copyObj.setP_address_line_2(p_address_line_2);
	          copyObj.setP_city(p_city);
	          copyObj.setP_state(p_state);
	          copyObj.setP_phone_number(p_phone_number);
	          copyObj.setP_zipcode(p_zipcode);
	          copyObj.setP_clean_area_code(p_clean_area_code);
	          copyObj.setP_clean_phone_number(p_clean_phone_number);
	          copyObj.setName(name);
	          copyObj.setAddress1(address1);
	          copyObj.setAddress2(address2);
	          copyObj.setCity(city);
	          copyObj.setState(state);
	          copyObj.setZip(zip);
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
	    public EvictionResultsPeer getPeer()
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
	        return EvictionResultsPeer.getTableMap();
	    }

	  
	    /**
	     * Creates a BjlResultsBean with the contents of this object
	       * This also creates beans for cached related objects, if they exist
	       * @return a BjlResultsBean with the contents of this object
	     */
	    public BaseEvictionResultsBean getBean()
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
	    public BaseEvictionResultsBean getBean(IdentityMap createdBeans)
	    {
	    	BaseEvictionResultsBean result = (BaseEvictionResultsBean) createdBeans.get(this);
	        if (result != null ) {
	            // we have already created a bean for this object, return it
	            return result;
	        }
	        // no bean exists for this object; create a new one
	        result = new BaseEvictionResultsBean();
	        createdBeans.put(this, result);

	        result.setResultId(getResultId());
	          result.setUserSearchId(getUserSearchId());
				
	          result.setTis_sig_case(getTis_sig_case());
	          result.setTis_vendor_code(getTis_vendor_code());
	          result.setCase_number(getCase_number());
	          result.setCase_sequence_number(getCase_sequence_number());
	          result.setCase_year_date(getCase_year_date());
	          result.setBook_number(getBook_number());
	          result.setPage_number(getPage_number());
	          result.setUnique_id(getUnique_id());
	          result.setResult_code(getResult_code());
	          result.setResult_desc(getResult_desc());
	          result.setResult_date(getResult_date());
	          result.setFile_date(getFile_date());
	          result.setInput_by(getInput_by());
	          result.setInput_date(getInput_date());
	          result.setExecuted_date(getExecuted_date());
	          result.setReceived_date(getReceived_date());
	          result.setSatisfied_date(getSatisfied_date());
	          result.setVerified_date(getVerified_date());
	          result.setSource_id(getSource_id());
	          result.setRecord_status(getRecord_status());
	          result.setJudgement_amount(getJudgement_amount());
	          result.setJudgement_flag(getJudgement_flag());
	          result.setState_abbr(getState_abbr());
	          result.setCourt_type_code(getCourt_type_code());
	          result.setCourt_type_desc(getCourt_type_desc());
	          result.setCourthouse_sub_code(getCourthouse_sub_code());
	          result.setCounty_fips_code(getCounty_fips_code());
	          result.setCounty_fips_desc(getCounty_fips_desc());
	          result.setState_fips_code(getState_fips_code());
	          result.setState_fips_desc(getState_fips_desc());
	          result.setAffiliate(getAffiliate());
	          result.setPossession_flag(getPossession_flag());
	          result.setPossession_flag_desc(getPossession_flag_desc());
	          result.setRegion(getRegion());
	          result.setTis_sig_defendant(getTis_sig_defendant());
	          result.setDefendant_number(getDefendant_number());
	          result.setD_county_fips_code(getD_county_fips_code());
	          result.setD_county_fips_desc(getD_county_fips_desc());
	          result.setD_state_fips_code(getD_state_fips_code());
	          result.setD_state_fips_desc(getD_state_fips_desc());
	          result.setD_ssn(getD_ssn());
	          result.setD_fullname(getD_fullname());
	          result.setD_firstname(getD_firstname());
	          result.setD_middlename(getD_middlename());
	          result.setD_lastname(getD_lastname());
	          result.setD_suffix(getD_suffix());
	          result.setD_alias_flag(getD_alias_flag());
	          result.setD_alias_firstname(getD_alias_firstname());
	          result.setD_alias_middlename(getD_alias_middlename());
	          result.setD_alias_lastname(getD_alias_lastname());
	          result.setD_alias_suffix(getD_alias_suffix());
	          result.setD_birth_date(getD_birth_date());
	          result.setD_address_line_1(getD_address_line_1());
	          result.setD_address_line_2(getD_address_line_2());
	          result.setD_apartment_number(getD_apartment_number());
	          result.setD_city(getD_city());
	          result.setD_state(getD_state());
	          result.setD_phone(getD_phone());
	          result.setD_zipcode(getD_zipcode());
	          result.setD_clean_area_code(getD_clean_area_code());
	          result.setD_clean_phone_number(getD_clean_phone_number());
	          result.setTis_sig_plaintiff(getTis_sig_plaintiff());
	          result.setPlaintiff_type(getPlaintiff_type());
	          result.setPlaintiff_desc(getPlaintiff_desc());
	          result.setP_county_fips_code(getP_county_fips_code());
	          result.setP_county_fips_desc(getP_county_fips_desc());
	          result.setP_state_fips_code(getP_state_fips_code());
	          result.setP_state_fips_desc(getP_state_fips_desc());
	          result.setP_fullname(getP_fullname());
	          result.setP_firstname(getP_firstname());
	          result.setP_middlename(getP_middlename());
	          result.setP_lastname(getP_lastname());
	          result.setP_address_line_1(getP_address_line_1());
	          result.setP_address_line_2(getP_address_line_2());
	          result.setP_city(getP_city());
	          result.setP_state(getP_state());
	          result.setP_phone_number(getP_phone_number());
	          result.setP_zipcode(getP_zipcode());
	          result.setP_clean_area_code(getP_clean_area_code());
	          result.setP_clean_phone_number(getP_clean_phone_number());
	          result.setName(getName());
	          result.setAddress1(getAddress1());
	          result.setAddress2(getAddress2());
	          result.setCity(getCity());
	          result.setState(getState());
	          result.setZip(getZip());
	      
	      
	                                
	        if (aEvictionSearches != null)
	        {
	            EvictionSearchesBean relatedBean = aEvictionSearches.getBean(createdBeans);
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
	    public static EvictionResults createBjlResults(BaseEvictionResultsBean bean)
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

	    public static EvictionResults createBjlResults(BaseEvictionResultsBean bean, IdentityMap createdObjects)
	        throws TorqueException
	    {
	    	EvictionResults result = (EvictionResults) createdObjects.get(bean);
	        if (result != null)
	        {
	            // we already have an object for the bean, return it
	            return result;
	        }
	        result = new EvictionResults();
	        createdObjects.put(bean, result);

	          result.setResultId(bean.getResultId());
	          result.setUserSearchId(bean.getUserSearchId());
			  result.setTis_sig_case(bean.getTis_sig_case());
	          result.setTis_vendor_code(bean.getTis_vendor_code());
	          result.setCase_number(bean.getCase_number());
	          result.setCase_sequence_number(bean.getCase_sequence_number());
	          result.setCase_year_date(bean.getCase_year_date());
	          result.setBook_number(bean.getBook_number());
	          result.setPage_number(bean.getPage_number());
	          result.setUnique_id(bean.getUnique_id());
	          result.setResult_code(bean.getResult_code());
	          result.setResult_desc(bean.getResult_desc());
	          result.setResult_date(bean.getResult_date());
	          result.setFile_date(bean.getFile_date());
	          result.setInput_by(bean.getInput_by());
	          result.setInput_date(bean.getInput_date());
	          result.setExecuted_date(bean.getExecuted_date());
	          result.setReceived_date(bean.getReceived_date());
	          result.setSatisfied_date(bean.getSatisfied_date());
	          result.setVerified_date(bean.getVerified_date());
	          result.setSource_id(bean.getSource_id());
	          result.setRecord_status(bean.getRecord_status());
	          result.setJudgement_amount(bean.getJudgement_amount());
	          result.setJudgement_flag(bean.getJudgement_flag());
	          result.setState_abbr(bean.getState_abbr());
	          result.setCourt_type_code(bean.getCourt_type_code());
	          result.setCourt_type_desc(bean.getCourt_type_desc());
	          result.setCourthouse_sub_code(bean.getCourthouse_sub_code());
	          result.setCounty_fips_code(bean.getCounty_fips_code());
	          result.setCounty_fips_desc(bean.getCounty_fips_desc());
	          result.setState_fips_code(bean.getState_fips_code());
	          result.setState_fips_desc(bean.getState_fips_desc());
	          result.setAffiliate(bean.getAffiliate());
	          result.setPossession_flag(bean.getPossession_flag());
	          result.setPossession_flag_desc(bean.getPossession_flag_desc());
	          result.setRegion(bean.getRegion());
	          result.setTis_sig_defendant(bean.getTis_sig_defendant());
	          result.setDefendant_number(bean.getDefendant_number());
	          result.setD_county_fips_code(bean.getD_county_fips_code());
	          result.setD_county_fips_desc(bean.getD_county_fips_desc());
	          result.setD_state_fips_code(bean.getD_state_fips_code());
	          result.setD_state_fips_desc(bean.getD_state_fips_desc());
	          result.setD_ssn(bean.getD_ssn());
	          result.setD_fullname(bean.getD_fullname());
	          result.setD_firstname(bean.getD_firstname());
	          result.setD_middlename(bean.getD_middlename());
	          result.setD_lastname(bean.getD_lastname());
	          result.setD_suffix(bean.getD_suffix());
	          result.setD_alias_flag(bean.getD_alias_flag());
	          result.setD_alias_firstname(bean.getD_alias_firstname());
	          result.setD_alias_middlename(bean.getD_alias_middlename());
	          result.setD_alias_lastname(bean.getD_alias_lastname());
	          result.setD_alias_suffix(bean.getD_alias_suffix());
	          result.setD_birth_date(bean.getD_birth_date());
	          result.setD_address_line_1(bean.getD_address_line_1());
	          result.setD_address_line_2(bean.getD_address_line_2());
	          result.setD_apartment_number(bean.getD_apartment_number());
	          result.setD_city(bean.getD_city());
	          result.setD_state(bean.getD_state());
	          result.setD_phone(bean.getD_phone());
	          result.setD_zipcode(bean.getD_zipcode());
	          result.setD_clean_area_code(bean.getD_clean_area_code());
	          result.setD_clean_phone_number(bean.getD_clean_phone_number());
	          result.setTis_sig_plaintiff(bean.getTis_sig_plaintiff());
	          result.setPlaintiff_type(bean.getPlaintiff_type());
	          result.setPlaintiff_desc(bean.getPlaintiff_desc());
	          result.setP_county_fips_code(bean.getP_county_fips_code());
	          result.setP_county_fips_desc(bean.getP_county_fips_desc());
	          result.setP_state_fips_code(bean.getP_state_fips_code());
	          result.setP_state_fips_desc(bean.getP_state_fips_desc());
	          result.setP_fullname(bean.getP_fullname());
	          result.setP_firstname(bean.getP_firstname());
	          result.setP_middlename(bean.getP_middlename());
	          result.setP_lastname(bean.getP_lastname());
	          result.setP_address_line_1(bean.getP_address_line_1());
	          result.setP_address_line_2(bean.getP_address_line_2());
	          result.setP_city(bean.getP_city());
	          result.setP_state(bean.getP_state());
	          result.setP_phone_number(bean.getP_phone_number());
	          result.setP_zipcode(bean.getP_zipcode());
	          result.setP_clean_area_code(bean.getP_clean_area_code());
	          result.setP_clean_phone_number(bean.getP_clean_phone_number());
	          result.setName(bean.getName());
	          result.setAddress1(bean.getAddress1());
	          result.setAddress2(bean.getAddress2());
	          result.setCity(bean.getCity());
	          result.setState(bean.getState());
	          result.setZip(bean.getZip());
	  
	                    
	        
	      
	                                
	              {
	            EvictionSearchesBean relatedBean = bean.getBjlSearchesBean();
	            if (relatedBean != null)
	            {
	                EvictionSearches relatedObject = EvictionSearches.createBjlSearches(relatedBean, createdObjects);
	            	//EvictionSearches relatedObject = EvictionSearches.createBjlSearches(bean, createdObjects);
	                //result.setBjlSearches(relatedObject);
	                result.setEvictionSearches(relatedObject);
	            }
	        }
	          result.setModified(bean.isModified());
	             
	    result.setNew(bean.isNew());
	  	return result;
	    }

	                      

	    public String toString()
	    {
	        StringBuffer str = new StringBuffer();
	        str.append("EvictionResults:\n");
	        str.append("ResultId = ")
	               .append(getResultId())
	             .append("\n");
	        str.append("UserSearchId = ")
	               .append(getUserSearchId())
	             .append("\n");
	        str.append("tis_sig_case = ")
	               .append(getTis_sig_case())
	             .append("\n");
	        str.append("tis_vendor_code = ")
	               .append(getTis_vendor_code())
	             .append("\n");
	        str.append("case_number = ")
	               .append(getCase_number())
	             .append("\n");
	        str.append("case_sequence_number = ")
	               .append(getCase_sequence_number())
	             .append("\n");
	        str.append("case_year_date = ")
	               .append(getCase_year_date())
	             .append("\n");
	        str.append("book_number = ")
	               .append(getBook_number())
	             .append("\n");
	        str.append("page_number = ")
	               .append(getPage_number())
	             .append("\n");
	        str.append("unique_id = ")
	               .append(getUnique_id())
	             .append("\n");
	        str.append("result_code = ")
	               .append(getResult_code())
	             .append("\n");
	        str.append("result_desc = ")
	               .append(getResult_desc())
	             .append("\n");
	        str.append("result_date = ")
	               .append(getResult_date())
	             .append("\n");
	        str.append("file_date = ")
	               .append(getFile_date())
	             .append("\n");
	        str.append("input_by = ")
	               .append(getInput_by())
	             .append("\n");
	        str.append("input_date = ")
	               .append(getInput_date())
	             .append("\n");
	        str.append("executed_date = ")
	               .append(getExecuted_date())
	             .append("\n");
	        str.append("received_date = ")
	               .append(getReceived_date())
	             .append("\n");
	        str.append("satisfied_date = ")
	               .append(getSatisfied_date())
	             .append("\n");
	        str.append("verified_date = ")
	               .append(getVerified_date())
	             .append("\n");
	        str.append("source_id = ")
	               .append(getSource_id())
	             .append("\n");
	        str.append("record_status = ")
	               .append(getRecord_status())
	             .append("\n");
	        str.append("judgement_amount = ")
	               .append(getJudgement_amount())
	             .append("\n");
	        str.append("judgement_flag = ")
	               .append(getJudgement_flag())
	             .append("\n");
	        str.append("state_abbr = ")
	               .append(getState_abbr())
	             .append("\n");
	        str.append("court_type_code = ")
	               .append(getCourt_type_code())
	             .append("\n");
	        str.append("court_type_desc = ")
	               .append(getCourt_type_desc())
	             .append("\n");
	        str.append("courthouse_sub_code = ")
	               .append(getCourthouse_sub_code())
	             .append("\n");
	        str.append("county_fips_code = ")
	               .append(getCounty_fips_code())
	             .append("\n");
	        str.append("county_fips_desc = ")
	               .append(getCounty_fips_desc())
	             .append("\n");
	        str.append("state_fips_code = ")
	               .append(getState_fips_code())
	             .append("\n");
	        str.append("state_fips_desc = ")
	               .append(getState_fips_desc())
	             .append("\n");
	        str.append("affiliate = ")
	               .append(getAffiliate())
	             .append("\n");
	        str.append("possession_flag = ")
	               .append(getPossession_flag())
	             .append("\n");
	        str.append("possession_flag_desc = ")
	               .append(getPossession_flag_desc())
	             .append("\n");
	        str.append("region = ")
	               .append(getRegion())
	             .append("\n");
	        str.append("tis_sig_defendant = ")
	               .append(getTis_sig_defendant())
	             .append("\n");
	        str.append("defendant_number = ")
	               .append(getDefendant_number())
	             .append("\n");
	        str.append("d_county_fips_code = ")
	               .append(getD_county_fips_code())
	             .append("\n");
	        str.append("d_county_fips_desc = ")
	               .append(getD_county_fips_desc())
	             .append("\n");
	        str.append("d_state_fips_code = ")
	               .append(getD_state_fips_code())
	             .append("\n");
	        str.append("d_state_fips_desc = ")
	               .append(getD_state_fips_desc())
	             .append("\n");
	        str.append("d_ssn = ")
	               .append(getD_ssn())
	             .append("\n");
	        str.append("d_fullname = ")
	               .append(getD_fullname())
	             .append("\n");
	        str.append("d_firstname = ")
	               .append(getD_firstname())
	             .append("\n");
	        str.append("d_middlename = ")
	               .append(getD_middlename())
	             .append("\n");
	        str.append("d_lastname = ")
	        .append(getD_lastname())
	             .append("\n");
	        
	        str.append("d_suffix = ")
            .append(getD_suffix())
          .append("\n");
     str.append("d_alias_flag = ")
            .append(getD_alias_flag())
          .append("\n");
     str.append("d_alias_firstname = ")
            .append(getD_alias_firstname())
          .append("\n");
     str.append("d_alias_lastname = ")
            .append(getD_alias_lastname())
          .append("\n");
     str.append("d_alias_suffix = ")
            .append(getD_alias_suffix())
          .append("\n");
     str.append("d_birth_date = ")
            .append(getD_birth_date())
          .append("\n");
     str.append("d_address_line_1 = ")
            .append(getD_address_line_1())
          .append("\n");
     str.append("d_address_line_2 = ")
            .append(getD_address_line_2())
          .append("\n");
     str.append("d_apartment_number = ")
            .append(getD_apartment_number())
          .append("\n");
     str.append("d_city = ")
            .append(getD_city())
          .append("\n");
     str.append("d_state = ")
            .append(getD_state())
          .append("\n");
     str.append("d_zipcode = ")
            .append(getD_zipcode())
          .append("\n");
     str.append("d_phone = ")
            .append(getD_phone())
          .append("\n");
     str.append("d_clean_area_code = ")
     .append(getD_clean_area_code())
          .append("\n");
     
     str.append("d_clean_phone_number = ")
     .append(getD_clean_phone_number())
   .append("\n");
str.append("tis_sig_plaintiff = ")
     .append(getTis_sig_plaintiff())
   .append("\n");
str.append("plaintiff_type = ")
     .append(getPlaintiff_type())
   .append("\n");
str.append("plaintiff_desc = ")
     .append(getPlaintiff_desc())
   .append("\n");
str.append("p_county_fips_code = ")
     .append(getP_county_fips_code())
   .append("\n");
str.append("p_county_fips_desc = ")
     .append(getP_county_fips_desc())
   .append("\n");
str.append("p_state_fips_code = ")
     .append(getP_state_fips_code())
   .append("\n");
str.append("p_state_fips_desc = ")
     .append(getP_state_fips_desc())
   .append("\n");
str.append("p_fullname = ")
     .append(getP_fullname())
   .append("\n");
str.append("p_firstname = ")
     .append(getP_firstname())
   .append("\n");
str.append("p_middlename = ")
     .append(getP_middlename())
   .append("\n");
str.append("p_lastname = ")
     .append(getP_lastname())
   .append("\n");
str.append("p_address_line_1 = ")
     .append(getP_address_line_1())
   .append("\n");
str.append("p_address_line_2 = ")
.append(getP_address_line_2())
   .append("\n");
str.append("p_city = ")
.append(getP_city())
.append("\n");
str.append("p_state = ")
.append(getP_state())
.append("\n");
str.append("p_zipcode = ")
.append(getP_zipcode())
.append("\n");
str.append("p_phone_number = ")
.append(getP_phone_number())
.append("\n");
str.append("p_clean_area_code = ")
.append(getP_clean_area_code())
.append("\n");
str.append("p_clean_phone_number = ")
.append(getP_clean_phone_number())
.append("\n");
str.append("name = ")
.append(getName())
.append("\n");
str.append("address1 = ")
.append(getAddress1())
.append("\n");
str.append("address2 = ")
.append(getAddress2())
.append("\n");
str.append("city = ")
.append(getCity())
.append("\n");
str.append("state = ")
.append(getState())
.append("\n");
str.append("zip = ")
.append(getZip())
.append("\n");

	        return(str.toString());
	    }

}
