
package springapp.domain;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.map.IdentityMap;
import org.apache.log4j.Logger;
import org.apache.torque.TorqueException;
import org.apache.torque.map.TableMap;
import org.apache.torque.om.BaseObject;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.util.Transaction;

public abstract class BJLBaseResults extends BaseObject{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1230102311301L;
	/** peer class*/
	  private static final NewBJLResultsPeer peer =
	        new NewBJLResultsPeer();
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
		
		//Udhay-Aug8 ==> New Tracers Fields
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
			if(aBjlSearches != null && !(aBjlSearches.getUserSearchId()== v))
			{
				aBjlSearches = null;
			}
		}
		public String getName_filing_state() {
			return name_filing_state;
		}
		public void setName_filing_state(String v) {
			if(this.name_filing_state != v)
			{
			this.name_filing_state = v;
			setModified(true);
			}
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String v) {
			if(this.firstname != v)
			{
			this.firstname = v;
			setModified(true);
			}
		}
		public String getMiddlename() {
			return middlename;
		}
		public void setMiddlename(String v) {
			if(this.middlename != v)
			this.middlename = v;
			setModified(true);
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String v) {
			if(this.lastname != v)
			{
			this.lastname = v;
			setModified(true);
			}
		}
		public String getName_suffix() {
			return name_suffix;
		}
		public void setName_suffix(String v) {
			if(this.name_suffix != v)
			{
			this.name_suffix = v;
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
		public String getName_type_desc() {
			return name_type_desc;
		}
		public void setName_type_desc(String v) {
			if(this.name_type_desc != v)
			{
			this.name_type_desc = v;
			setModified(true);
			}
		}
		public String getSsn_taxid_flag() {
			return ssn_taxid_flag;
		}
		public void setSsn_taxid_flag(String v) {
			if(this.ssn_taxid_flag != v)
			{
			this.ssn_taxid_flag = v;
			setModified(true);
			}
		}
		public String getSsn() {
			return ssn;
		}
		public void setSsn(String v) {
			if(this.ssn != v)
			{
			this.ssn = v;
			setModified(true);
			}
		}
		public String getComment_sequence() {
			return comment_sequence;
		}
		public void setComment_sequence(String v) {
			if(this.comment_sequence != v)
			{
			this.comment_sequence = v;
			setModified(true);
			}
		}
		public String getComment_type() {
			return comment_type;
		}
		public void setComment_type(String v) {
			if(this.comment_type != v)
			{
			this.comment_type = v;
			setModified(true);
			}
		}
		public String getComment_type_desc() {
			return comment_type_desc;
		}
		public void setComment_type_desc(String v) {
			if(this.comment_type_desc != v)
			{
			this.comment_type_desc = v;
			setModified(true);
			}
		}
		public String getHouse_number() {
			return house_number;
		}
		public void setHouse_number(String v) {
			if(this.house_number != v)
			{
			this.house_number = v;
			setModified(true);
			}
		}
		public String getStreet_direction() {
			return street_direction;
		}
		public void setStreet_direction(String v) {
			if(this.street_direction != v)
			{
			this.street_direction = v;
			setModified(true);
			}
		}
		public String getStreet_name() {
			return street_name;
		}
		public void setStreet_name(String v) {
			if(this.street_name != v)
			{
			this.street_name = v;
			setModified(true);
			}
		}
		public String getStreet_suffix() {
			return street_suffix;
		}
		public void setStreet_suffix(String v) {
			if(this.street_suffix != v)
			{
			this.street_suffix = v;
			setModified(true);
			}
		}
		public String getApartment_num() {
			return apartment_num;
		}
		public void setApartment_num(String v) {
			if(this.apartment_num != v)
			{
			this.apartment_num = v;
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
		public String getZipcode() {
			return zipcode;
		}
		public void setZipcode(String v) {
			if(this.zipcode != v)
			{
			this.zipcode = v;
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
		public String getFiling_group() {
			return filing_group;
		}
		public void setFiling_group(String v) {
			if(this.filing_group != v)
			{
			this.filing_group = v;
			setModified(true);
			}
		}
		public String getFiling_group_desc() {
			return filing_group_desc;
		}
		public void setFiling_group_desc(String v) {
			if(this.filing_group_desc != v)
			{
			this.filing_group_desc = v;
			setModified(true);
			}
		}
		public String getFiling_type() {
			return filing_type;
		}
		public void setFiling_type(String v) {
			if(this.filing_type != v)
			{
			this.filing_type = v;
			setModified(true);
			}
		}
		public String getFiling_type_desc() {
			return filing_type_desc;
		}
		public void setFiling_type_desc(String v) {
			if(this.filing_type_desc != v)
			{
			this.filing_type_desc = v;
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
		public String getTax_lien_type() {
			return tax_lien_type;
		}
		public void setTax_lien_type(String v) {
			if(this.tax_lien_type != v)
			{
			this.tax_lien_type = v;
			setModified(true);
			}
		}
		public String getTax_lien_type_desc() {
			return tax_lien_type_desc;
		}
		public void setTax_lien_type_desc(String v) {
			if(this.tax_lien_type_desc != v)
			{
			this.tax_lien_type_desc = v;
			setModified(true);
			}
		}
		public String getBankruptcy_type() {
			return bankruptcy_type;
		}
		public void setBankruptcy_type(String v) {
			if(this.bankruptcy_type != v)
			{
			this.bankruptcy_type = v;
			setModified(true);
			}
		}
		public String getBankruptcy_type_desc() {
			return bankruptcy_type_desc;
		}
		public void setBankruptcy_type_desc(String v) {
			if(this.bankruptcy_type_desc != v)
			{
			this.bankruptcy_type_desc = v;
			setModified(true);
			}
		}
		public String getDocket_number() {
			return docket_number;
		}
		public void setDocket_number(String v) {
			if(this.docket_number != v)
			{
			this.docket_number = v;
			setModified(true);
			}
		}
		public String getUnlawful_detainer() {
			return unlawful_detainer;
		}
		public void setUnlawful_detainer(String v) {
			if(this.unlawful_detainer != v)
			{
			this.unlawful_detainer = v;
			setModified(true);
			}
		}
		public String getInitial_date() {
			return initial_date;
		}
		public void setInitial_date(String v) {
			if(this.initial_date != v)
			{
			this.initial_date = v;
			setModified(true);
			}
		}
		public String getInitial_amount() {
			return initial_amount;
		}
		public void setInitial_amount(String v) {
			if(this.initial_amount != v)
			{
			this.initial_amount = v;
			setModified(true);
			}
		}
		public String getInitial_docket() {
			return initial_docket;
		}
		public void setInitial_docket(String v) {
			if(this.initial_docket != v)
			{
			this.initial_docket = v;
			setModified(true);
			}
		}
		public String getJudgement_date() {
			return judgement_date;
		}
		public void setJudgement_date(String v) {
			if(this.judgement_date != v)
			{
			this.judgement_date = v;
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
		public String getJudgement_docket() {
			return judgement_docket;
		}
		public void setJudgement_docket(String v) {
			if(this.judgement_docket != v)
			{
			this.judgement_docket = v;
			setModified(true);
			}
		}
		public String getRemove_date() {
			return remove_date;
		}
		public void setRemove_date(String v) {
			if(this.remove_date != v)
			{
			this.remove_date = v;
		
			setModified(true);
			}
		}
		public String getRemove_docket() {
			return remove_docket;
		}
		public void setRemove_docket(String v) {
			if(this.remove_docket != v)
			{
			this.remove_docket = v;
			setModified(true);
			}
		}
		public String getDismissal_date() {
			return dismissal_date;
		}
		public void setDismissal_date(String v) {
			if(this.dismissal_date != v)
			{
			this.dismissal_date = v;
			setModified(true);
			}
		}
		public String getDismissal_docket() {
			return dismissal_docket;
		}
		public void setDismissal_docket(String v) {
			if(this.dismissal_docket != v)
			{
			this.dismissal_docket = v;
			setModified(true);
			}
		}
		public String getAsset_amount() {
			return asset_amount;
		}
		public void setAsset_amount(String v) {
			if(this.asset_amount != v)
			{
			this.asset_amount = v;
			setModified(true);
			}
		}
		public String getLiability_amount() {
			return liability_amount;
		}
		public void setLiability_amount(String v) {
			if(this.liability_amount != v)
			{
			this.liability_amount = v;
			setModified(true);
			}
		}
		public String getPlaintiff() {
			return plaintiff;
		}
		public void setPlaintiff(String v) {
			if(this.plaintiff != v)
			{
			this.plaintiff = v;
			setModified(true);
			}
		}
		public String getBeneficiary() {
			return beneficiary;
		}
		public void setBeneficiary(String v) {
			if(this.beneficiary != v)
			{
			this.beneficiary = v;
			setModified(true);
			}
		}
		public String getSitus() {
			return situs;
		}
		public void setSitus(String v) {
			if(this.situs != v)
			{
			this.situs = v;
			setModified(true);
			}
		}
		public String getTrustee() {
			return trustee;
		}
		public void setTrustee(String v) {
			if(this.trustee != v)
			{
			this.trustee = v;
			setModified(true);
			}
		}
		public String getCounty_code() {
			return county_code;
		}
		public void setCounty_code(String v) {
			if(this.county_code != v)
			{
			this.county_code = v;
			setModified(true);
			}
		}
		public String getCounty_name() {
			return county_name;
		}
		public void setCounty_name(String v) {
			if(this.county_name != v)
			{
			this.county_name = v;
			setModified(true);
			}
		}
		public String getCounty_state() {
			return county_state;
		}
		public void setCounty_state(String v) {
			if(this.county_state != v)
			{
			this.county_state = v;
			setModified(true);
			}
		}
		public String getCourt_code() {
			return court_code;
		}
		public void setCourt_code(String v) {
			if(this.court_code != v)
			{
			this.court_code = v;
			setModified(true);
			}
			
		}
		public String getCourt_desc() {
			return court_desc;
		}
		public void setCourt_desc(String v) {
			if(this.court_desc != v)
			{
			this.court_desc = v;
			setModified(true);
			}
		}
		public String getCourt_state() {
			return court_state;
		}
		public void setCourt_state(String v) {
			if(this.court_state != v)
			{
			this.court_state = v;
			setModified(true);
			}
		}
		public String getAssets_available() {
			return assets_available;
		}
		public void setAssets_available(String v) {
			if(this.assets_available != v)
			{
			this.assets_available = v;
			setModified(true);
			}
		}
		public String getPerfected_date() {
			return perfected_date;
		}
		public void setPerfected_date(String v) {
			if(this.perfected_date != v)
			{
			this.perfected_date = v;
			setModified(true);
			}
		}
		public String getAction_state_code() {
			return action_state_code;
		}
		public void setAction_state_code(String v) {
			if(this.action_state_code != v)
			{
			this.action_state_code = v;
			setModified(true);
			}
		}
		public String getAction_desc() {
			return action_desc;
		}
		public void setAction_desc(String v) {
			if(this.action_desc != v)
			{
			this.action_desc = v;
			setModified(true);
			}
		}
		public String getDisposition_state_code() {
			return disposition_state_code;
		}
		public void setDisposition_state_code(String v) {
			if(this.disposition_state_code != v)
			{
			this.disposition_state_code = v;
			setModified(true);
			}
		}
		public String getDisposition_desc() {
			return disposition_desc;
		}
		public void setDisposition_desc(String v) {
			if(this.disposition_desc != v)
			{
			this.disposition_desc = v;
			setModified(true);
			}
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String v) {
			if(this.amount != v)
			{
			this.amount = v;
			setModified(true);
			}
		}
		public String getRelease_date() {
			return release_date;
		}
		public void setRelease_date(String v) {
			if(this.release_date != v)
			{
			this.release_date = v;
			setModified(true);
			}
		}
		public String getRelease_number() {
			return release_number;
		}
		public void setRelease_number(String v) {
			if(this.release_number != v)
			{
			this.release_number = v;
			setModified(true);
			}
		}
		public String getSuit_case_number() {
			return suit_case_number;
		}
		public void setSuit_case_number(String v) {
			if(this.suit_case_number != v)
			{
			this.suit_case_number = v;
			setModified(true);
			}
		}
		public String getSuit_date() {
			return suit_date;
		}
		public void setSuit_date(String v) {
			if(this.suit_date != v)
			{
			this.suit_date = v;
			setModified(true);
			}
		}
		public String getSuit_amount() {
			return suit_amount;
		}
		public void setSuit_amount(String v) {
			if(this.suit_amount != v)
			{
			this.suit_amount = v;
			setModified(true);
			}
		}
		public String getSatisfaction_date() {
			return satisfaction_date;
		}
		public void setSatisfaction_date(String v) {
			if(this.satisfaction_date != v)
			{
			this.satisfaction_date = v;
			setModified(true);
			}
		}
		public String getDischarge_date() {
			return discharge_date;
		}
		public void setDischarge_date(String v) {
			if(this.discharge_date != v)
			{
			this.discharge_date = v;
			setModified(true);
			}
		}
		public String getClosed_date() {
			return closed_date;
		}
		public void setClosed_date(String v) {
			if(this.closed_date != v)
			{
			this.closed_date = v;
			setModified(true);
			}
		}
		public String getTrust_deed_number() {
			return trust_deed_number;
		}
		public void setTrust_deed_number(String v) {
			if(this.trust_deed_number != v)
			{
			this.trust_deed_number = v;
			setModified(true);
			}
		}
		public String getTrust_deed_date() {
			return trust_deed_date;
		}
		public void setTrust_deed_date(String v) {
			if(this.trust_deed_date != v)
			{
			this.trust_deed_date = v;
			setModified(true);
			}
		}
		public String getSale_number() {
			return sale_number;
		}
		public void setSale_number(String v) {
			if(this.sale_number != v)
			{
			this.sale_number = v;
			setModified(true);
			}
		}
		public String getSale_date() {
			return sale_date;
		}
		public void setSale_date(String v) {
			if(this.sale_date != v)
			{
			this.sale_date = v;
			setModified(true);
			}
		}
		public String getCancellation_number() {
			return cancellation_number;
		}
		public void setCancellation_number(String v) {
			if(this.cancellation_number != v)
			{
			this.cancellation_number = v;
			setModified(true);
			}
		}
		public String getCancellation_date() {
			return cancellation_date;
		}
		public void setCancellation_date(String v) {
			if(this.cancellation_date != v)
			{
			this.cancellation_date = v;
			setModified(true);
			}
		}
		public String getSched_341_date() {
			
			return sched_341_date;
		}
		public void setSched_341_date(String v) {
			if(this.sched_341_date != v)
			{
			this.sched_341_date = v;
			setModified(true);
			}
		}
		public String getUpdate_date() {
			return update_date;
		}
		public void setUpdate_date(String v) {
			if(this.update_date != v)
			{
			this.update_date = v;
			setModified(true);
			}
		}
		
		//Udhay-Aug8-Tracers New Fields

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
		
		
		private NewBJLSearches aBjlSearches;
		/**
	     * Declares an association between this object and a BjlSearches object
	     *
	     * @param v BjlSearches
	     * @throws TorqueException
	     */
	    public void setBjlSearches(NewBJLSearches v) throws TorqueException
	    {
	            if (v == null)
	        {
	                          setUserSearchId( 0);
	              }
	        else
	        {
	            setUserSearchId(v.getUserSearchId());
	        }
	            aBjlSearches = v;
	    }

	    /**
	     * Returns the associated BjlSearches object.
	           * If it was not retrieved before, the object is retrieved from
	     * the database
	           *
	     * @return the associated BjlSearches object
	           * @throws TorqueException
	           */
	    public NewBJLSearches getBjlSearches()
	              throws TorqueException
	          {
	              if (aBjlSearches == null && (this.userSearchId != 0))
	        {
	                              aBjlSearches = NewBJLSearchesPeer.retrieveByPK(SimpleKey.keyFor(this.userSearchId));
	                  
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
	              return aBjlSearches;
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
	    public NewBJLSearches getBjlSearches(Connection connection)
	        throws TorqueException
	    {
	        if (aBjlSearches == null && (this.userSearchId != 0))
	        {
	                          aBjlSearches = NewBJLSearchesPeer.retrieveByPK(SimpleKey.keyFor(this.userSearchId), connection);
	              
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
	        return aBjlSearches;
	    }

	    /**
	     * Provides convenient way to set a relationship based on a
	     * ObjectKey, for example
	     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
	     *
	         */
	    public void setBjlSearchesKey(ObjectKey key) throws TorqueException
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
	              fieldNames.add("name_filing_state");
	              fieldNames.add("firstname");
	              fieldNames.add("middlename");
	              fieldNames.add("lastname");
	              fieldNames.add("name_suffix");
	              fieldNames.add("name_type");
	              fieldNames.add("name_type_desc");
	              fieldNames.add("ssn_taxid_flag");
	              fieldNames.add("ssn");
	              fieldNames.add("comment_sequence");
	              fieldNames.add("comment_type");
	              fieldNames.add("comment_type_desc");
	              fieldNames.add("house_number");
	              fieldNames.add("street_direction");
	              fieldNames.add("street_name");
	              fieldNames.add("street_suffix");
	              fieldNames.add("apartment_num");
	              fieldNames.add("city");
	              fieldNames.add("state");
	              fieldNames.add("zipcode");
	              fieldNames.add("filing_state");
	              fieldNames.add("filing_group");
	              fieldNames.add("filing_group_desc");
	              fieldNames.add("filing_type");
	              fieldNames.add("filing_type_desc");
	              fieldNames.add("filing_date");
	              fieldNames.add("filing_number");
	              fieldNames.add("tax_lien_type");
	              fieldNames.add("tax_lien_type_desc");
	              fieldNames.add("bankruptcy_type");
	              fieldNames.add("bankruptcy_type_desc");
	              fieldNames.add("docket_number");
	              fieldNames.add("unlawful_detainer");
	              fieldNames.add("initial_date");
	              fieldNames.add("initial_amount");
	              fieldNames.add("initial_docket");
	              fieldNames.add("judgement_date");
	              fieldNames.add("judgement_amount");
	              fieldNames.add("judgement_docket");
	              fieldNames.add("remove_date");
	              fieldNames.add("remove_docket");
	              fieldNames.add("dismissal_date");
	              fieldNames.add("dismissal_docket");
	              fieldNames.add("asset_amount");
	              fieldNames.add("liability_amount");
	              fieldNames.add("plaintiff");
	              fieldNames.add("beneficiary");
	              fieldNames.add("situs");
	              fieldNames.add("trustee");
	              fieldNames.add("county_code");
	              fieldNames.add("county_name");
	              fieldNames.add("county_state");
	              fieldNames.add("court_code");
	              fieldNames.add("court_desc");
	              fieldNames.add("court_state");
	              fieldNames.add("assets_available");
	              fieldNames.add("perfected_date");
	              fieldNames.add("action_state_code");
	              fieldNames.add("action_desc");
	              fieldNames.add("disposition_state_code");
	              fieldNames.add("disposition_desc");
	              fieldNames.add("amount");
	              fieldNames.add("release_date");
	              fieldNames.add("release_number");
	              fieldNames.add("suit_case_number");
	              fieldNames.add("suit_date");
	              fieldNames.add("suit_amount");
	              fieldNames.add("satisfaction_date");
	              fieldNames.add("discharge_date");
	              fieldNames.add("closed_date");
	              fieldNames.add("trust_deed_number");
	              fieldNames.add("trust_deed_date");
	              fieldNames.add("sale_number");
	              fieldNames.add("sale_date");
	              fieldNames.add("cancellation_number");
	              fieldNames.add("cancellation_date");
	              fieldNames.add("sched_341_date");
	              fieldNames.add("update_date");
	              /*new fileds*/
		            fieldNames.add("fullname");
		      		fieldNames.add("businessname");
		      		fieldNames.add("dob");		
		      		fieldNames.add("court_address");
		      		fieldNames.add("court_city");
		      		fieldNames.add("court_zip");
		      		fieldNames.add("court_phone");
		      		//
		      		fieldNames.add("sched_341_time");
		      		fieldNames.add("judge");
		      		fieldNames.add("lawfirm");
		      		fieldNames.add("book");
		      		fieldNames.add("page");
		      		fieldNames.add("origdept");
		      		fieldNames.add("origcase");
		      		fieldNames.add("origbook");
		      		fieldNames.add("origpage");
		      		fieldNames.add("assoccode");
		      		fieldNames.add("actiontype");
		      		fieldNames.add("actiontypedesc");
		      		
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
	           if (name.equals("name_filing_state"))
	         {
	                 return getName_filing_state();
	             }
	           if (name.equals("firstname"))
	         {
	                 return getFirstname();
	             }
	           if (name.equals("middlename"))
	         {
	                 return getMiddlename();
	             }
	           if (name.equals("lastname"))
	         {
	                 return getLastname();
	             }
	           if (name.equals("name_suffix"))
	         {
	                 return getName_suffix();
	             }
	           if (name.equals("name_type"))
	         {
	                 return getName_type();
	             }
	           if (name.equals("name_type_desc"))
	         {
	                 return getName_type_desc();
	             }
	           if (name.equals("ssn_taxid_flag"))
	         {
	                 return getSsn_taxid_flag();
	             }
	           if (name.equals("ssn"))
	         {
	                 return getSsn();
	             }
	           if (name.equals("comment_sequence"))
	         {
	                 return getComment_sequence();
	             }
	           if (name.equals("comment_type"))
	         {
	                 return getComment_type();
	             }
	           if (name.equals("comment_type_desc"))
	         {
	                 return getComment_type_desc();
	             }
	           if (name.equals("house_number"))
	         {
	                 return getHouse_number();
	             }
	           if (name.equals("street_direction"))
	         {
	                 return getStreet_direction();
	             }
	           if (name.equals("street_name"))
	         {
	                 return getStreet_name();
	             }
	           if (name.equals("street_suffix"))
	         {
	                 return getStreet_suffix();
	             }
	           if (name.equals("apartment_num"))
	         {
	                 return getApartment_num();
	             }
	           if (name.equals("city"))
	         {
	                 return getCity();
	             }
	           if (name.equals("state"))
	         {
	                 return getState();
	             }
	           if (name.equals("zipcode"))
	         {
	                 return getZipcode();
	             }
	           if (name.equals("filing_state"))
	         {
	                 return getFiling_state();
	             }
	           if (name.equals("filing_group"))
	         {
	                 return getFiling_group();
	             }
	           if (name.equals("filing_group_desc"))
	         {
	                 return getFiling_group_desc();
	             }
	           if (name.equals("filing_type"))
	         {
	                 return getFiling_type();
	             }
	           if (name.equals("filing_type_desc"))
	         {
	                 return getFiling_type_desc();
	             }
	           if (name.equals("filing_date"))
	         {
	                 return getFiling_date();
	             }
	           if (name.equals("filing_number"))
	         {
	                 return getFiling_number();
	             }
	           if (name.equals("tax_lien_type"))
	         {
	                 return getTax_lien_type();
	             }
	           if (name.equals("tax_lien_type_desc"))
	         {
	                 return getTax_lien_type_desc();
	             }
	           if (name.equals("bankruptcy_type"))
	         {
	                 return getBankruptcy_type();
	             }
	           if (name.equals("bankruptcy_type_desc"))
	         {
	                 return getBankruptcy_type_desc();
	             }
	           if (name.equals("docket_number"))
	         {
	                 return getDocket_number();
	             }
	           if (name.equals("unlawful_detainer"))
	         {
	                 return getUnlawful_detainer();
	             }
	           if (name.equals("initial_date"))
	         {
	                 return getInitial_date();
	             }
	           if (name.equals("initial_amount"))
	         {
	                 return getInitial_amount();
	             }
	           if (name.equals("initial_docket"))
	         {
	                 return getInitial_docket();
	             }
	           if (name.equals("judgement_date"))
	         {
	                 return getJudgement_date();
	             }
	           if (name.equals("judgement_amount"))
	         {
	                 return getJudgement_amount();
	             }
	           if (name.equals("judgement_docket"))
	         {
	                 return getJudgement_docket();
	             }
	           if (name.equals("remove_date"))
	         {
	                 return getRemove_date();
	             }
	           if (name.equals("remove_docket"))
	         {
	                 return getRemove_docket();
	             }
	           if (name.equals("dismissal_date"))
	         {
	                 return getDismissal_date();
	             }
	           if (name.equals("dismissal_docket"))
	         {
	                 return getDismissal_docket();
	             }
	           if (name.equals("asset_amount"))
	         {
	                 return getAsset_amount();
	             }
	           if(name.equals("liability_amount"))
	           {
	        	   return getLiability_amount();
	        	   
	           }
	           if(name.equals("plaintiff"))
	           {
	        	   return getPlaintiff();
	        	   
	           }
	           if(name.equals("beneficiary"))
	           {
	        	   return getBeneficiary();
	           }
	           if(name.equals("situs"))
	           {
	        	   return getSitus();
	        	   
	           }
	           if(name.equals("trustee"))
	           {
	        	   return getTrustee();
	           }
	           if(name.equals("county_code"))
	           {
	        	   return getCounty_code();
	           }
	           if(name.equals("county_name"))
	           {
	        	   return getCounty_name();
	           }
	           if(name.equals("county_state"))
	           {
	        	   return getCounty_state();
	           }
	           if(name.equals("court_code"))
	           {
	        	   return getCourt_code();
	           }
	           if(name.equals("court_desc"))
	           {
	        	   return getCourt_desc();
	           }
	           if(name.equals("court_state"))
	           {
	        	   return getCourt_state();
	           }
	           if(name.equals("assets_available"))
	           {
	        	   return getAssets_available();
	           }
	           if(name.equals("perfected_date"))
	           {
	        	   return getPerfected_date();
	           }
	           if(name.equals("action_state_code"))
	           {
	        	   return getAction_state_code();
	           }
	           if(name.equals("action_desc"))
	           {
	        	   return getAction_desc();
	           }
	           if(name.equals("disposition_state_code"))
	           {
	        	   return getDisposition_state_code();
	           }

	           if(name.equals("disposition_desc"))
	           {
	        	   return getDisposition_desc();
	           }
	           if(name.equals("amount"))
	           {
	        	   return getAmount();
	           }
	           if(name.equals("release_date"))
	           {
	        	   return getRelease_date();
	           }
	           if(name.equals("release_number"))
	           {
	        	   return getRelease_number();
	           }
	           if(name.equals("suit_case_number"))
	           {
	        	   return getSuit_case_number();
	           }
	           if(name.equals("suit_date"))
	           {
	        	   return getSuit_date();
	           }
	           if(name.equals("suit_amount"))
	           {
	        	   return getSuit_amount();
	           }
	           if(name.equals("satisfaction_date"))
	           {
	        	   return getSatisfaction_date();
	           }
	           if(name.equals("discharge_date"))
	           {
	        	   return getDischarge_date();
	           }
	           if(name.equals("closed_date"))
	           {
	        	   return getClosed_date();
	           }
	           if(name.equals("trust_deed_number"))
	           {
	        	   return getTrust_deed_number();
	           }
	           if(name.equals("trust_deed_date"))
	           {
	        	   return getTrust_deed_date();
	           }
	           if(name.equals("sale_number"))
	           {
	        	   return getSale_number();
	           }
	           if(name.equals("sale_date"))
	           {
	        	   return getSale_date();
	           }
	           if(name.equals("cancellation_number"))
	           {
	        	   return getCancellation_number();
	           }
	           if(name.equals("cancellation_date"))
	           {
	        	   return getCancellation_date();
	           }
	           if(name.equals("sched_341_date"))
	           {
	        	   return getSched_341_date();
	           }
	           if(name.equals("update_date"))
	           {
	        	   return getUpdate_date();
	           }
	           //Udhay-Aug8==> New Tracers Fields added
	           if(name.equals("fullname"))
	           {
	        	   return getFullname();
	           }
	          
	           if(name.equals("businessname"))
	           {
	        	   return getBusinessname();
	           }
	           if(name.equals("dob"))
	           {
	        	   return getDob();
	           }
	           if(name.equals("court_address"))
	           {
	        	   return getCourt_address();
	           }
	           if(name.equals("court_city"))
	           {
	        	   return getCourt_city();
	           }
	           if(name.equals("court_zip"))
	           {
	        	   return getCourt_zip();
	           }
	           if(name.equals("court_phone"))
	           {
	        	   return getCourt_phone();
	           }
	           //
	           if(name.equals("sched_341_time"))
	           {
	        	   return getSched_341_time();
	           }
	           if(name.equals("judge"))
	           {
	        	   return getJudge();
	           }
	           if(name.equals("lawfirm"))
	           {
	        	   return getLawfirm();
	           }
	           if(name.equals("book"))
	           {
	        	   return getBook();
	           }
	           if(name.equals("page"))
	           {
	        	   return getPage();
	           }
	           if(name.equals("origdept"))
	           {
	        	   return getOrigdept();
	           }
	           if(name.equals("origcase"))
	           {
	        	   return getOrigcase();
	           }
	           if(name.equals("origbook"))
	           {
	        	   return getOrigbook();
	           }
	           if(name.equals("origpage"))
	           {
	        	   return getOrigpage();
	           }
	           if(name.equals("assoccode"))
	           {
	        	   return getAssoccode();
	           }
	           if(name.equals("actiontype"))
	           {
	        	   return getActiontype();
	           }
	           if(name.equals("actiontypedesc"))
	           {
	        	   return getActiontypedesc();
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
	           if (name.equals("name_filing_state"))
	         {
	        	   if (value != null && ! String.class.isInstance(value))
	               {
	                   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	               }
	        	   setName_filing_state((String)value);
	                 return true;
	             }
	           if (name.equals("firstname"))
	         {
	        	   if (value != null && ! String.class.isInstance(value))
             {
                 throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
             }
      	   		setFirstname((String)value);
               return true;
	             }
	           if (name.equals("middlename"))
	            {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setMiddlename((String)value);
	        	   return true;
	             }
	           if (name.equals("lastname"))
	         { 
	        	   if(value != null && !String.class.isInstance(value))
      	   {
      		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
      	   }
      	   setLastname((String)value);
      	   return true;
	             }
	           if (name.equals("name_suffix"))
	         { if(value != null && !String.class.isInstance(value))
      	   {
      		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
      	   }
      	   setName_suffix((String)value);
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
	           if (name.equals("name_type_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setName_type_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("ssn_taxid_flag"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setSsn_taxid_flag((String)value);
	        	   return true;
	             }
	           if (name.equals("ssn"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setSsn((String)value);
	        	   return true;
	             }
	           if (name.equals("comment_sequence"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setComment_sequence((String)value);
	        	   return true;
	             }
	           if (name.equals("comment_type"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setComment_type((String)value);
	        	   return true;
	             }
	           if (name.equals("comment_type_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setComment_type_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("house_number"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setHouse_number((String)value);
	        	   return true;
	             }
	           if (name.equals("street_direction"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setStreet_direction((String)value);
	        	   return true;
	             }
	           if (name.equals("street_name"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setStreet_name((String)value);
	        	   return true;
	             }
	           if (name.equals("street_suffix"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setStreet_suffix((String)value);
	        	   return true;
	             }
	           if (name.equals("apartment_num"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setApartment_num((String)value);
	        	   return true;
	             }
	           if (name.equals("city"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCity((String)value);
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
	           if (name.equals("zipcode"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setZipcode((String)value);
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
	           if (name.equals("filing_group"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setFiling_group((String)value);
	        	   return true;
	             }
	           if (name.equals("filing_group_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setFiling_group_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("filing_type"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setFiling_type((String)value);
	        	   return true;
	             }
	           if (name.equals("filing_type_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setFiling_type_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("filing_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setFiling_date((String)value);
	        	   return true;
	             }
	           if (name.equals("filing_number"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setFiling_number((String)value);
	        	   return true;
	             }
	           if (name.equals("tax_lien_type"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setTax_lien_type((String)value);
	        	   return true;
	             }
	           if (name.equals("tax_lien_type_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setTax_lien_type_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("bankruptcy_type"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setBankruptcy_type((String)value);
	        	   return true;
	             }
	           if (name.equals("bankruptcy_type_desc"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setBankruptcy_type_desc((String)value);
	        	   return true;
	             }
	           if (name.equals("docket_number"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setDocket_number((String)value);
	        	   return true;
	             }
	           if (name.equals("unlawful_detainer"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setUnlawful_detainer((String)value);
	        	   return true;
	             }
	           if (name.equals("initial_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setInitial_date((String)value);
	        	   return true;
	             }
	           if (name.equals("initial_amount"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setInitial_amount((String)value);
	        	   return true;
	             }
	           if (name.equals("initial_docket"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setInitial_docket((String)value);
	        	   return true;
	             }
	           if (name.equals("judgement_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setJudgement_date((String)value);
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
	           if (name.equals("judgement_docket"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setJudgement_docket((String)value);
	        	   return true;
	             }
	           if (name.equals("remove_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setRemove_date((String)value);
	        	   return true;
	             }
	           if (name.equals("remove_docket"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setRemove_docket((String)value);
	        	   return true;
	             }
	           if (name.equals("dismissal_date"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setDismissal_date((String)value);
	        	   return true;
	             }
	           if (name.equals("dismissal_docket"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setDismissal_docket((String)value);
	        	   return true;
	             }
	           if (name.equals("asset_amount"))
	         {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setAsset_amount((String)value);
	        	   return true;
	             }
	           if(name.equals("liability_amount"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setLiability_amount((String)value);
	        	   return true;
	        	   
	           }
	           if(name.equals("plaintiff"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setPlaintiff((String)value);
	        	   return true;
	        	   
	           }
	           if(name.equals("beneficiary"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setBeneficiary((String)value);
	        	   return true;
	           }
	           if(name.equals("situs"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setSitus((String)value);
	        	   return true;
	        	   
	           }
	           if(name.equals("trustee"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setTrustee((String)value);
	        	   return true;
	           }
	           if(name.equals("county_code"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCounty_code((String)value);
	        	   return true;
	           }
	           if(name.equals("county_name"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCounty_name((String)value);
	        	   return true;
	           }
	           if(name.equals("county_state"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCounty_state((String)value);
	        	   return true;
	           }
	           if(name.equals("court_code"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCourt_code((String)value);
	        	   return true;
	           }
	           if(name.equals("court_desc"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCourt_desc((String)value);
	        	   return true;
	           }
	           if(name.equals("court_state"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCourt_state((String)value);
	        	   return true;
	           }
	           if(name.equals("assets_available"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setAssets_available((String)value);
	        	   return true;
	           }
	           if(name.equals("perfected_date"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setPerfected_date((String)value);
	        	   return true;
	           }
	           if(name.equals("action_state_code"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setAction_state_code((String)value);
	        	   return true;
	           }
	           if(name.equals("action_desc"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setAction_desc((String)value);
	        	   return true;
	           }
	           if(name.equals("disposition_state_code"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setDisposition_state_code((String)value);
	        	   return true;
	           }

	           if(name.equals("disposition_desc"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setDisposition_desc((String)value);
	        	   return true;
	           }
	           if(name.equals("amount"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setAmount((String)value);
	        	   return true;
	           }
	           if(name.equals("release_date"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setRelease_date((String)value);
	        	   return true;
	           }
	           if(name.equals("release_number"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setRelease_number((String)value);
	        	   return true;
	           }
	           if(name.equals("suit_case_number"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setSuit_case_number((String)value);
	        	   return true;
	           }
	           if(name.equals("suit_date"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setSuit_date((String)value);
	        	   return true;
	           }
	           if(name.equals("suit_amount"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setSuit_amount((String)value);
	        	   return true;
	           }
	           if(name.equals("satisfaction_date"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setSatisfaction_date((String)value);
	        	   return true;
	           }
	           if(name.equals("discharge_date"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setDischarge_date((String)value);
	        	   return true;
	           }
	           if(name.equals("closed_date"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setClosed_date((String)value);
	        	   return true;
	           }
	           if(name.equals("trust_deed_number"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setTrust_deed_number((String)value);
	        	   return true;
	           }
	           if(name.equals("trust_deed_date"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setTrust_deed_date((String)value);
	        	   return true;
	           }
	           if(name.equals("sale_number"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setSale_number((String)value);
	        	   return true;
	           }
	           if(name.equals("sale_date"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setSale_date((String)value);
	        	   return true;
	           }
	           if(name.equals("cancellation_number"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCancellation_number((String)value);
	        	   return true;
	           }
	           if(name.equals("cancellation_date"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setCancellation_date((String)value);
	        	   return true;
	           }
	           if(name.equals("sched_341_date"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setSched_341_date((String)value);
	        	   return true;
	           }
	           if(name.equals("update_date"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in setByName");
	        	   }
	        	   setUpdate_date((String)value);
	        	   return true;
	           }
	           
	           //Udhay Aug8 ==> New Tracers Fields added
	           if(name.equals("fullname"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in fullname");
	        	   }
	        	   setFullname((String)value);
	        	   return true;
	           }
	           if(name.equals("businessname"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in businessname");
	        	   }
	        	   setBusinessname((String)value);
	        	   return true;
	           }
	           if(name.equals("dob"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in dob");
	        	   }
	        	   setDob((String)value);
	        	   return true;
	           }
	           if(name.equals("court_address"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in court_address");
	        	   }
	        	   setCourt_address((String)value);
	        	   return true;
	           }
	           if(name.equals("court_city"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in court_city");
	        	   }
	        	   setCourt_city((String)value);
	        	   return true;
	           }

	           if(name.equals("court_zip"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in court_zip");
	        	   }
	        	   setCourt_zip((String)value);
	        	   return true;
	           }
	           if(name.equals("court_phone"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in court_phone");
	        	   }
	        	   setCourt_phone((String)value);
	        	   return true;
	           }
	           ///////
	           if(name.equals("sched_341_time"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in sched_341_time");
	        	   }
	        	   setSched_341_time((String)value);
	        	   return true;
	           }
	           if(name.equals("judge"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in judge");
	        	   }
	        	   setJudge((String)value);
	        	   return true;
	           }
	           if(name.equals("lawfirm"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in lawfirm");
	        	   }
	        	   setLawfirm((String)value);
	        	   return true;
	           }
	           if(name.equals("book"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in book");
	        	   }
	        	   setBook((String)value);
	        	   return true;
	           }
	           if(name.equals("page"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in page");
	        	   }
	        	   setPage((String)value);
	        	   return true;
	           }
	           if(name.equals("origdept"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in origdept");
	        	   }
	        	   setOrigdept((String)value);
	        	   return true;
	           }
	           if(name.equals("origcase"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in origcase");
	        	   }
	        	   setOrigcase((String)value);
	        	   return true;
	           }
	           if(name.equals("origbook"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in origbook");
	        	   }
	        	   setOrigbook((String)value);
	        	   return true;
	           }
	           if(name.equals("origpage"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in origpage");
	        	   }
	        	   setOrigpage((String)value);
	        	   return true;
	           }
	           if(name.equals("assoccode"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in assoccode");
	        	   }
	        	   setAssoccode((String)value);
	        	   return true;
	           }
	           if(name.equals("actiontype"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in actiontype");
	        	   }
	        	   setActiontype((String)value);
	        	   return true;
	           }
	           if(name.equals("actiontypedesc"))
	           {
	        	   if(value != null && !String.class.isInstance(value))
	        	   {
	        		   throw new IllegalArgumentException("Invalid type of object specified for value in actiontypedesc");
	        	   }
	        	   setActiontypedesc((String)value);
	        	   return true;
	           }
	           //
	          
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
	          if (name.equals(NewBJLResultsPeer.RESULT_ID))
	        {
	                return new Long(getResultId());
	            }
	          if (name.equals(NewBJLResultsPeer.USER_SEARCH_ID))
	        {
	                return new Long(getUserSearchId());
	            }
	          if (name.equals(NewBJLResultsPeer.NAME_FILING_STATE))
	        {
	                return getName_filing_state();
	            }
	          if (name.equals(NewBJLResultsPeer.FIRSTNAME))
	        {
	                return getFirstname();
	            }
	          if (name.equals(NewBJLResultsPeer.MIDDLENAME))
	        {
	                return getMiddlename();
	            }
	          if (name.equals(NewBJLResultsPeer.LASTNAME))
	        {
	                return getLastname();
	            }
	          if (name.equals(NewBJLResultsPeer.NAME_SUFFIX))
	        {
	                return getName_suffix();
	            }
	          if (name.equals(NewBJLResultsPeer.NAME_TYPE))
	        {
	                return getName_type();
	            }
	          if (name.equals(NewBJLResultsPeer.NAME_TYPE_DESC))
	        {
	                return getName_type_desc();
	            }
	          if (name.equals(NewBJLResultsPeer.SSN_TAXID_FLAG))
	        {
	                return getSsn_taxid_flag();
	            }
	          if (name.equals(NewBJLResultsPeer.SSN))
	        {
	                return getSsn();
	            }
	          if (name.equals(NewBJLResultsPeer.COMMENT_SEQUENCE))
	        {
	                return getComment_sequence();
	            }
	          if (name.equals(NewBJLResultsPeer.COMMENT_TYPE))
	        {
	                return getComment_type();
	            }
	          if (name.equals(NewBJLResultsPeer.COMMENT_TYPE_DESC))
	        {
	                return getComment_type_desc();
	            }
	          if (name.equals(NewBJLResultsPeer.HOUSE_NUMBER))
	        {
	                return getHouse_number();
	            }
	          if (name.equals(NewBJLResultsPeer.STREET_DIRECTION))
	        {
	                return getStreet_direction();
	            }
	          if (name.equals(NewBJLResultsPeer.STREET_NAME))
	        {
	                return getStreet_name();
	            }
	          if (name.equals(NewBJLResultsPeer.STREET_SUFFIX))
	        {
	                return getStreet_suffix();
	            }
	          if (name.equals(NewBJLResultsPeer.APARTMENT_NUM))
	        {
	                return getApartment_num();
	            }
	          if (name.equals(NewBJLResultsPeer.CITY))
	        {
	                return getCity();
	            }
	          if (name.equals(NewBJLResultsPeer.STATE))
	        {
	                return getState();
	            }
	          if (name.equals(NewBJLResultsPeer.ZIPCODE))
	        {
	                return getZipcode();
	            }
	          if (name.equals(NewBJLResultsPeer.FILING_STATE))
	        {
	                return getFiling_state();
	            }
	          if (name.equals(NewBJLResultsPeer.FILING_GROUP))
	        {
	                return getFiling_group();
	            }
	          if (name.equals(NewBJLResultsPeer.FILING_GROUP_DESC))
	        {
	                return getFiling_group_desc();
	            }
	          if (name.equals(NewBJLResultsPeer.FILING_TYPE))
	        {
	                return getFiling_type();
	            }
	          if (name.equals(NewBJLResultsPeer.FILING_TYPE_DESC))
	        {
	                return getFiling_type_desc();
	            }
	          if (name.equals(NewBJLResultsPeer.FILING_DATE))
	        {
	                return getFiling_date();
	            }
	          if (name.equals(NewBJLResultsPeer.FILING_NUMBER))
	        {
	                return getFiling_number();
	            }
	          if (name.equals(NewBJLResultsPeer.TAX_LIEN_TYPE))
	        {
	                return getTax_lien_type();
	            }
	          if (name.equals(NewBJLResultsPeer.TAX_LIEN_TYPE_DESC))
	        {
	                return getTax_lien_type_desc();
	            }
	          if (name.equals(NewBJLResultsPeer.BANKRUPTCY_TYPE))
	        {
	                return getBankruptcy_type();
	            }
	          if (name.equals(NewBJLResultsPeer.BANKRUPTCY_TYPE_DESC))
	        {
	                return getBankruptcy_type_desc();
	            }
	          if (name.equals(NewBJLResultsPeer.DOCKET_NUMBER))
	        {
	                return getDocket_number();
	            }
	          if (name.equals(NewBJLResultsPeer.UNLAWFULl_DETAINER))
	        {
	                return getUnlawful_detainer();
	            }
	          if (name.equals(NewBJLResultsPeer.INITIAL_DATE))
	        {
	                return getInitial_date();
	            }
	          if (name.equals(NewBJLResultsPeer.INITIAL_AMOUNT))
	        {
	                return getInitial_amount();
	            }
	          if (name.equals(NewBJLResultsPeer.INITIAL_DOCKET))
	        {
	                return getInitial_docket();
	            }
	          if (name.equals(NewBJLResultsPeer.JUDGEMENT_DATE))
	        {
	                return getJudgement_date();
	            }
	          if (name.equals(NewBJLResultsPeer.JUDGEMENT_AMOUNT))
	        {
	                return getJudgement_amount();
	            }
	          if (name.equals(NewBJLResultsPeer.JUDGEMENT_DOCKET))
	        {
	                return getJudgement_docket();
	            }
	          if (name.equals(NewBJLResultsPeer.REMOVE_DATE))
	        {
	                return getRemove_date();
	            }
	          if (name.equals(NewBJLResultsPeer.REMOVE_DOCKET))
	        {
	                return getRemove_docket();
	            }
	          if (name.equals(NewBJLResultsPeer.DISMISSAL_DATE))
	        {
	                return getDismissal_date();
	            }
	          if (name.equals(NewBJLResultsPeer.DISMISSAL_DOCKET))
	        {
	                return getDismissal_docket();
	            }
	          if (name.equals(NewBJLResultsPeer.ASSET_AMOUNT))
	        {
	                return getAsset_amount();
	            }
	          
	          if (name.equals(NewBJLResultsPeer.LIABILITY_AMOUNT))
		        {
		                return getLiability_amount();
		            }
		          if (name.equals(NewBJLResultsPeer.PLAINTIFF))
		        {
		                return getPlaintiff();
		            }
		          if (name.equals(NewBJLResultsPeer.BENEFICIARY))
		        {
		                return getBeneficiary();
		            }
		          if (name.equals(NewBJLResultsPeer.SITUS))
		        {
		                return getSitus();
		            }
		          if (name.equals(NewBJLResultsPeer.TRUSTEE))
		        {
		                return getTrustee();
		            }
		          if (name.equals(NewBJLResultsPeer.COUNTY_CODE))
		        {
		                return getCounty_code();
		            }
		          if (name.equals(NewBJLResultsPeer.COUNTY_NAME))
		        {
		                return getCounty_name();
		            }
		          if (name.equals(NewBJLResultsPeer.COUNTY_STATE))
		        {
		                return getCounty_state();
		            }
		          if (name.equals(NewBJLResultsPeer.COURT_CODE))
		        {
		                return getCourt_code();
		            }
		          if (name.equals(NewBJLResultsPeer.COURT_DESC))
		        {
		                return getCourt_desc();
		            }
		          
		          
		          
		          if (name.equals(NewBJLResultsPeer.COURT_STATE))
			        {
			                return getCourt_state();
			            }
			          if (name.equals(NewBJLResultsPeer.ASSETS_AVAILABLE))
			        {
			                return getAssets_available();
			            }
			          if (name.equals(NewBJLResultsPeer.PERFECTED_DATE))
			        {
			                return getPerfected_date();
			            }
			          if (name.equals(NewBJLResultsPeer.ACTION_STATE_CODE))
			        {
			                return getAction_state_code();
			            }
			          if (name.equals(NewBJLResultsPeer.ACTION_DESC))
			        {
			                return getAction_desc();
			            }
			          if (name.equals(NewBJLResultsPeer.DISPOSITION_STATE_CODE))
			        {
			                return getDisposition_state_code();
			            }
			          if (name.equals(NewBJLResultsPeer.DISPOSITION_DESC))
			        {
			                return getDisposition_desc();
			            }
			          
			          if (name.equals(NewBJLResultsPeer.AMOUNT))
				        {
				                return getAmount();
				            }
				          if (name.equals(NewBJLResultsPeer.RELEASE_DATE))
				        {
				                return getRelease_date();
				            }
				          if (name.equals(NewBJLResultsPeer.RELEASE_NUMBER))
				        {
				                return getRelease_number();
				            }
				          if (name.equals(NewBJLResultsPeer.SUIT_CASE_NUMBER))
				        {
				                return getSuit_case_number();
				            }
				          if (name.equals(NewBJLResultsPeer.SUIT_DATE))
				        {
				                return getSuit_date();
				            }
				          if (name.equals(NewBJLResultsPeer.SUIT_AMOUNT))
				        {
				                return getSuit_amount();
				            }
				          if (name.equals(NewBJLResultsPeer.SATISFACTION_DATE))
				        {
				                return getSatisfaction_date();
				            }
				          if (name.equals(NewBJLResultsPeer.DISCHARGE_DATE))
				        {
				                return getDischarge_date();
				            }
				          if (name.equals(NewBJLResultsPeer.CLOSED_DATE))
				        {
				                return getClosed_date();
				            }
				          if (name.equals(NewBJLResultsPeer.TRUST_DEED_NUMBER))
				        {
				                return getTrust_deed_number();
				            }
				          if (name.equals(NewBJLResultsPeer.TRUST_DEED_DATE))
					        {
					                return getTrust_deed_date();
					            }
					          
				          
				          
				          if (name.equals(NewBJLResultsPeer.SALE_NUMBER))
					        {
					                return getSale_number();
					            }
					          if (name.equals(NewBJLResultsPeer.SALE_DATE))
					        {
					                return getSale_date();
					            }
					          if (name.equals(NewBJLResultsPeer.CANCELLATION_NUMBER))
					        {
					                return getCancellation_number();
					            }
					          if (name.equals(NewBJLResultsPeer.CANCELLATION_DATE))
					        {
					                return getCancellation_date();
					            }
					          if (name.equals(NewBJLResultsPeer.SCHED_341_DATE))
					        {
					                return getSched_341_date();
					            }
					          if (name.equals(NewBJLResultsPeer.UPDATE_DATE))
					        {
					                return getUpdate_date();
					            }
				            //Udhay Aug8 ==> New Tracers Fields addedd
					        if (name.equals(NewBJLResultsPeer.FULLNAME))
					        {
					          return getFullname();
					        }
				            if (name.equals(NewBJLResultsPeer.BUSINESSNAME))
					        {
					           return getBusinessname();
					        }
				            if (name.equals(NewBJLResultsPeer.DOB))
					        {
					          return getDob();
					        }
				            if (name.equals(NewBJLResultsPeer.COURT_ADDRESS))
					        {
					          return getCourt_address();
					        }
				            if (name.equals(NewBJLResultsPeer.COURT_CITY))
					        {
					          return getCourt_city();
					        }
				            if (name.equals(NewBJLResultsPeer.COURT_ZIP))
					        {
					          return getCourt_zip();
					        }
				            if (name.equals(NewBJLResultsPeer.COURT_PHONE))
					        {
					          return getCourt_phone();
					        }
				            /*
				            */
				            if(name.equals(NewBJLResultsPeer.SCHED_341_TIME))
					           {
					        	   return getSched_341_time();
					           }
					           if(name.equals(NewBJLResultsPeer.JUDGE))
					           {
					        	   return getJudge();
					           }
					           if(name.equals(NewBJLResultsPeer.LAWFIRM))
					           {
					        	   return getLawfirm();
					           }
					           if(name.equals(NewBJLResultsPeer.BOOK))
					           {
					        	   return getBook();
					           }
					           if(name.equals(NewBJLResultsPeer.PAGE))
					           {
					        	   return getPage();
					           }
					           if(name.equals(NewBJLResultsPeer.ORIGDEPT))
					           {
					        	   return getOrigdept();
					           }
					           if(name.equals(NewBJLResultsPeer.ORIGCASE))
					           {
					        	   return getOrigcase();
					           }
					           if(name.equals(NewBJLResultsPeer.ORIGBOOK))
					           {
					        	   return getOrigbook();
					           }
					           if(name.equals(NewBJLResultsPeer.ORIGPAGE))
					           {
					        	   return getOrigpage();
					           }
					           if(name.equals(NewBJLResultsPeer.ASSOCCODE))
					           {
					        	   return getAssoccode();
					           }
					           if(name.equals(NewBJLResultsPeer.ACTIONTYPE))
					           {
					        	   return getActiontype();
					           }
					           if(name.equals(NewBJLResultsPeer.ACTIONTYPEDESC))
					           {
					        	   return getActiontypedesc();
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
	        if (NewBJLResultsPeer.RESULT_ID.equals(name))
	        {
	            return setByName("ResultId", value);
	        }
	        if (NewBJLResultsPeer.USER_SEARCH_ID.equals(name))
	        {
	            return setByName("UserSearchId", value);
	        }
	        if (NewBJLResultsPeer.NAME_FILING_STATE.equals(name))
	        {
	            return setByName("name_filing_state", value);
	        }
	        if (NewBJLResultsPeer.FIRSTNAME.equals(name))
	        {
	            return setByName("firstname", value);
	        }
	        if (NewBJLResultsPeer.MIDDLENAME.equals(name))
	        {
	            return setByName("middlename", value);
	        }
	        if (NewBJLResultsPeer.LASTNAME.equals(name))
	        {
	            return setByName("lastname", value);
	        }
	        if (NewBJLResultsPeer.NAME_SUFFIX.equals(name))
	        {
	            return setByName("name_suffix", value);
	        }
	        if (NewBJLResultsPeer.NAME_TYPE.equals(name))
	        {
	            return setByName("name_type", value);
	        }
	        if (NewBJLResultsPeer.NAME_TYPE_DESC.equals(name))
	        {
	            return setByName("name_type_desc", value);
	        }
	        if (NewBJLResultsPeer.SSN_TAXID_FLAG.equals(name))
	        {
	            return setByName("ssn_taxid_flag", value);
	        }
	        if (NewBJLResultsPeer.SSN.equals(name))
	        {
	            return setByName("ssn", value);
	        }
	        if (NewBJLResultsPeer.COMMENT_SEQUENCE.equals(name))
	        {
	            return setByName("comment_sequence", value);
	        }
	        if (NewBJLResultsPeer.COMMENT_TYPE.equals(name))
	        {
	            return setByName("comment_type", value);
	        }
	        if (NewBJLResultsPeer.COMMENT_TYPE_DESC.equals(name))
	        {
	            return setByName("comment_type_desc", value);
	        }
	        if (NewBJLResultsPeer.HOUSE_NUMBER.equals(name))
	        {
	            return setByName("house_number", value);
	        }
	        if (NewBJLResultsPeer.STREET_DIRECTION.equals(name))
	        {
	            return setByName("street_direction", value);
	        }
	        if (NewBJLResultsPeer.STREET_NAME.equals(name))
	        {
	            return setByName("street_name", value);
	        }
	        if (NewBJLResultsPeer.STREET_SUFFIX.equals(name))
	        {
	            return setByName("street_suffix", value);
	        }
	        if (NewBJLResultsPeer.APARTMENT_NUM.equals(name))
	        {
	            return setByName("apartment_num", value);
	        }
	        if (NewBJLResultsPeer.CITY.equals(name))
	        {
	            return setByName("city", value);
	        }
	        if (NewBJLResultsPeer.STATE.equals(name))
	        {
	            return setByName("state", value);
	        }
	        if (NewBJLResultsPeer.ZIPCODE.equals(name))
	        {
	            return setByName("zipcode", value);
	        }
	        if (NewBJLResultsPeer.FILING_STATE.equals(name))
	        {
	            return setByName("filing_state", value);
	        }
	        if (NewBJLResultsPeer.FILING_GROUP.equals(name))
	        {
	            return setByName("filing_group", value);
	        }
	        if (NewBJLResultsPeer.FILING_GROUP_DESC.equals(name))
	        {
	            return setByName("filing_group_desc", value);
	        }
	        if (NewBJLResultsPeer.FILING_TYPE.equals(name))
	        {
	            return setByName("filing_type", value);
	        }
	        if (NewBJLResultsPeer.FILING_TYPE_DESC.equals(name))
	        {
	            return setByName("filing_type_desc", value);
	        }
	        if (NewBJLResultsPeer.FILING_DATE.equals(name))
	        {
	            return setByName("filing_date", value);
	        }
	        if (NewBJLResultsPeer.FILING_NUMBER.equals(name))
	        {
	            return setByName("filing_number", value);
	        }
	        if (NewBJLResultsPeer.TAX_LIEN_TYPE.equals(name))
	        {
	            return setByName("tax_lien_type", value);
	        }
	        if (NewBJLResultsPeer.TAX_LIEN_TYPE_DESC.equals(name))
	        {
	            return setByName("tax_lien_type_desc", value);
	        }
	        if (NewBJLResultsPeer.BANKRUPTCY_TYPE.equals(name))
	        {
	            return setByName("bankruptcy_type", value);
	        }
	        if (NewBJLResultsPeer.BANKRUPTCY_TYPE_DESC.equals(name))
	        {
	            return setByName("bankruptcy_type_desc", value);
	        }
	        if (NewBJLResultsPeer.DOCKET_NUMBER.equals(name))
	        {
	            return setByName("docket_number", value);
	        }
	        if (NewBJLResultsPeer.UNLAWFULl_DETAINER.equals(name))
	        {
	            return setByName("unlawful_detainer", value);
	        }
	        if (NewBJLResultsPeer.INITIAL_DATE.equals(name))
	        {
	            return setByName("initial_date", value);
	        }
	        if (NewBJLResultsPeer.INITIAL_AMOUNT.equals(name))
	        {
	            return setByName("initial_amount", value);
	        }
	        if (NewBJLResultsPeer.INITIAL_DOCKET.equals(name))
	        {
	            return setByName("initial_docket", value);
	        }
	        if (NewBJLResultsPeer.JUDGEMENT_DATE.equals(name))
	        {
	            return setByName("judgement_date", value);
	        }
	        if (NewBJLResultsPeer.JUDGEMENT_AMOUNT.equals(name))
	        {
	            return setByName("judgement_amount", value);
	        }
	        if (NewBJLResultsPeer.JUDGEMENT_DOCKET.equals(name))
	        {
	            return setByName("judgement_docket", value);
	        }
	        if (NewBJLResultsPeer.REMOVE_DATE.equals(name))
	        {
	            return setByName("remove_date", value);
	        }
	        if (NewBJLResultsPeer.REMOVE_DOCKET.equals(name))
	        {
	            return setByName("remove_docket", value);
	        }
	        if (NewBJLResultsPeer.DISMISSAL_DATE.equals(name))
	        {
	            return setByName("dismissal_date", value);
	        }
	        if (NewBJLResultsPeer.DISMISSAL_DOCKET.equals(name))
	        {
	            return setByName("dismissal_docket", value);
	        }
	        if (NewBJLResultsPeer.ASSET_AMOUNT.equals(name))
	        {
	            return setByName("asset_amount", value);
	        }
	        
	        
	        
	        if (NewBJLResultsPeer.LIABILITY_AMOUNT.equals(name))
	        {
	            return setByName("liability_amount", value);
	        }
	        if (NewBJLResultsPeer.PLAINTIFF.equals(name))
	        {
	            return setByName("plaintiff", value);
	        }
	        if (NewBJLResultsPeer.BENEFICIARY.equals(name))
	        {
	            return setByName("beneficiary", value);
	        }
	        if (NewBJLResultsPeer.SITUS.equals(name))
	        {
	            return setByName("situs", value);
	        }
	        if (NewBJLResultsPeer.TRUSTEE.equals(name))
	        {
	            return setByName("trustee", value);
	        }
	        if (NewBJLResultsPeer.COUNTY_CODE.equals(name))
	        {
	            return setByName("county_code", value);
	        }
	        if (NewBJLResultsPeer.COUNTY_NAME.equals(name))
	        {
	            return setByName("county_name", value);
	        }
	        if (NewBJLResultsPeer.COUNTY_STATE.equals(name))
	        {
	            return setByName("county_state", value);
	        }
	        if (NewBJLResultsPeer.COURT_CODE.equals(name))
	        {
	            return setByName("court_code", value);
	        }
	        if (NewBJLResultsPeer.COURT_DESC.equals(name))
	        {
	            return setByName("court_desc", value);
	        }
	        if (NewBJLResultsPeer.COURT_STATE.equals(name))
	        {
	            return setByName("court_state", value);
	        }

	        
	        
	        if (NewBJLResultsPeer.ASSETS_AVAILABLE.equals(name))
	        {
	            return setByName("assets_available", value);
	        }
	        if (NewBJLResultsPeer.PERFECTED_DATE.equals(name))
	        {
	            return setByName("perfected_date", value);
	        }
	        if (NewBJLResultsPeer.ACTION_STATE_CODE.equals(name))
	        {
	            return setByName("action_state_code", value);
	        }
	        if (NewBJLResultsPeer.ACTION_DESC.equals(name))
	        {
	            return setByName("action_desc", value);
	        }
	        if (NewBJLResultsPeer.DISPOSITION_STATE_CODE.equals(name))
	        {
	            return setByName("disposition_state_code", value);
	        }
	        if (NewBJLResultsPeer.DISPOSITION_DESC.equals(name))
	        {
	            return setByName("disposition_desc", value);
	        }
	        if (NewBJLResultsPeer.AMOUNT.equals(name))
	        {
	            return setByName("amount", value);
	        }
	        if (NewBJLResultsPeer.RELEASE_DATE.equals(name))
	        {
	            return setByName("release_date", value);
	        }
	        if (NewBJLResultsPeer.RELEASE_NUMBER.equals(name))
	        {
	            return setByName("release_number", value);
	        }
	        if (NewBJLResultsPeer.SUIT_CASE_NUMBER.equals(name))
	        {
	            return setByName("suit_case_number", value);
	        }
	        if (NewBJLResultsPeer.SUIT_DATE.equals(name))
	        {
	            return setByName("suit_date", value);
	        }
	        if (NewBJLResultsPeer.SUIT_AMOUNT.equals(name))
	        {
	            return setByName("suit_amount", value);
	        }
	        if (NewBJLResultsPeer.SATISFACTION_DATE.equals(name))
	        {
	            return setByName("satisfaction_date", value);
	        }
	        if (NewBJLResultsPeer.DISCHARGE_DATE.equals(name))
	        {
	            return setByName("discharge_date", value);
	        }

	        
	        
	        if (NewBJLResultsPeer.CLOSED_DATE.equals(name))
	        {
	            return setByName("closed_date", value);
	        }
	        if (NewBJLResultsPeer.TRUST_DEED_NUMBER.equals(name))
	        {
	            return setByName("trust_deed_number", value);
	        }
	        if (NewBJLResultsPeer.TRUST_DEED_DATE.equals(name))
	        {
	            return setByName("trust_deed_date", value);
	        }
	        if (NewBJLResultsPeer.SALE_NUMBER.equals(name))
	        {
	            return setByName("sale_number", value);
	        }
	        if (NewBJLResultsPeer.SALE_DATE.equals(name))
	        {
	            return setByName("sale_date", value);
	        }
	        if (NewBJLResultsPeer.CANCELLATION_NUMBER.equals(name))
	        {
	            return setByName("cancellation_number", value);
	        }
	        if (NewBJLResultsPeer.CANCELLATION_DATE.equals(name))
	        {
	            return setByName("cancellation_date", value);
	        }
	        if (NewBJLResultsPeer.SCHED_341_DATE.equals(name))
	        {
	            return setByName("sched_341_date", value);
	        }
	        if (NewBJLResultsPeer.UPDATE_DATE.equals(name))
	        {
	            return setByName("update_date", value);
	        }
	        
	        //Udhay Aug8 ==> New Tracers fields addedd
	        
	        if (NewBJLResultsPeer.FULLNAME.equals(name))
	        {
	            return setByName("fullname", value);
	        }
	        if (NewBJLResultsPeer.BUSINESSNAME.equals(name))
	        {
	            return setByName("businessname", value);
	        }
	        if (NewBJLResultsPeer.DOB.equals(name))
	        {
	            return setByName("dob", value);
	        }
	        if (NewBJLResultsPeer.COURT_ADDRESS.equals(name))
	        {
	            return setByName("court_address", value);
	        }
	        if (NewBJLResultsPeer.COURT_CITY.equals(name))
	        {
	            return setByName("court_city", value);
	        }
	        if (NewBJLResultsPeer.COURT_ZIP.equals(name))
	        {
	            return setByName("court_zip", value);
	        }
	        if (NewBJLResultsPeer.COURT_PHONE.equals(name))
	        {
	            return setByName("court_phone", value);
	        }
	        //
	        if (NewBJLResultsPeer.SCHED_341_TIME.equals(name))
	        {
	            return setByName("sched_341_time", value);
	        }
	        if (NewBJLResultsPeer.JUDGE.equals(name))
	        {
	            return setByName("judge", value);
	        }
	        if (NewBJLResultsPeer.LAWFIRM.equals(name))
	        {
	            return setByName("lawfirm", value);
	        }
	        if (NewBJLResultsPeer.BOOK.equals(name))
	        {
	            return setByName("book", value);
	        }
	        if (NewBJLResultsPeer.PAGE.equals(name))
	        {
	            return setByName("page", value);
	        }
	        if (NewBJLResultsPeer.ORIGDEPT.equals(name))
	        {
	            return setByName("origdept", value);
	        }
	        if (NewBJLResultsPeer.ORIGCASE.equals(name))
	        {
	            return setByName("origcase", value);
	        }
	        if (NewBJLResultsPeer.ORIGBOOK.equals(name))
	        {
	            return setByName("origbook", value);
	        }
	        if (NewBJLResultsPeer.ORIGPAGE.equals(name))
	        {
	            return setByName("origpage", value);
	        }
	        if (NewBJLResultsPeer.ASSOCCODE.equals(name))
	        {
	            return setByName("assoccode", value);
	        }
	        if (NewBJLResultsPeer.ACTIONTYPE.equals(name))
	        {
	            return setByName("actiontype", value);
	        }
	        if (NewBJLResultsPeer.ACTIONTYPEDESC.equals(name))
	        {
	            return setByName("actiontypedesc", value);
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
	                return getName_filing_state();
	            }
	              if (pos == 3)
	        {
	                return getFirstname();
	            }
	              if (pos == 4)
	        {
	                return getMiddlename();
	            }
	              if (pos == 5)
	        {
	                return getLastname();
	            }
	              if (pos == 6)
	        {
	                return getName_suffix();
	            }
	              if (pos == 7)
	        {
	                return getName_type();
	            }
	              if (pos == 8)
	        {
	                return getName_type_desc();
	            }
	              if (pos == 9)
	        {
	                return getSsn_taxid_flag();
	            }
	              if (pos == 10)
	        {
	                return getSsn();
	            }
	              if (pos == 11)
	        {
	                return getComment_sequence();
	            }
	              if (pos == 12)
	        {
	                return getComment_type();
	            }
	              if (pos == 13)
	        {
	                return getComment_type_desc();
	            }
	              if (pos == 14)
	        {
	                return getHouse_number();
	            }
	              if (pos == 15)
	        {
	                return getStreet_direction();
	            }
	              if (pos == 16)
	        {
	                return getStreet_name();
	            }
	              if (pos == 17)
	        {
	                return getStreet_suffix();
	            }
	              if (pos == 18)
	        {
	                return getApartment_num();
	            }
	              if (pos == 19)
	        {
	                return getCity();
	            }
	              if (pos == 20)
	        {
	                return getState();
	            }
	              if (pos == 21)
	        {
	                return getZipcode();
	            }
	              if (pos == 22)
	        {
	                return getFiling_state();
	            }
	              if (pos == 23)
	        {
	                return getFiling_group();
	            }
	              if (pos == 24)
	        {
	                return getFiling_group_desc();
	            }
	              if (pos == 25)
	        {
	                return getFiling_type();
	            }
	              if (pos == 26)
	        {
	                return getFiling_type_desc();
	            }
	              if (pos == 27)
	        {
	                return getFiling_date();
	            }
	              if (pos == 28)
	        {
	                return getFiling_number();
	            }
	              if (pos == 29)
	        {
	                return getTax_lien_type();
	            }
	              if (pos == 30)
	        {
	                return getTax_lien_type_desc();
	            }
	              if (pos == 31)
	        {
	                return getBankruptcy_type();
	            }
	              if (pos == 32)
	        {
	                return getBankruptcy_type_desc();
	            }
	              if (pos == 33)
	        {
	                return getDocket_number();
	            }
	              if (pos == 34)
	        {
	                return getUnlawful_detainer();
	            }
	              if (pos == 35)
	        {
	                return getInitial_date();
	            }
	              if (pos == 36)
	        {
	                return getInitial_amount();
	            }
	              if (pos == 37)
	        {
	                return getInitial_docket();
	            }
	              if (pos == 38)
	        {
	                return getJudgement_date();
	            }
	              if (pos == 39)
	        {
	                return getJudgement_amount();
	            }
	              if (pos == 40)
	        {
	                return getJudgement_docket();
	            }
	              if (pos == 41)
	        {
	                return getRemove_date();
	            }
	              if (pos == 42)
	        {
	                return getRemove_docket();
	            }
	              if (pos == 43)
	        {
	                return getDismissal_date();
	            }
	              if (pos == 44)
	        {
	                return getDismissal_docket();
	            }
	              if (pos == 45)
	        {
	                return getAsset_amount();
	            }
	              if(pos == 46){
	            	  return getLiability_amount();
	              }
	              if(pos == 47){
	            	  return getPlaintiff();
	              }
	              if(pos == 48){
	            	  return getBeneficiary();
	              }
	              if(pos == 49){
	            	  return getSitus();
	              }
	              if(pos == 50){
	            	  return getTrustee();
	              }
	              if(pos == 51){
	            	  return getCourt_code();
	              }
	              
	              
	              if(pos == 52){
	            	  return getCounty_name();
	              }
	              if(pos == 53){
	            	  return getCounty_state();
	              }
	              if(pos == 54){
	            	  return getCourt_code();
	              }
	              if(pos == 55){
	            	  return getCourt_desc();
	              }
	              if(pos == 56){
	            	  return getCourt_state();
	              }
	              if(pos == 57){
	            	  return getAssets_available();
	              }
	              if(pos == 58){
	            	  return getPerfected_date();
	              }
	              if(pos == 59){
	            	  return getAction_state_code();
	              }
	              if(pos == 60){
	            	  return getAction_desc();
	              }
	              if(pos == 61){
	            	  return getDisposition_state_code();
	              }
	              if(pos == 62){
	            	  return getDisposition_desc();
	              }
	              if(pos == 63){
	            	  return getAmount();
	              }

	              
	              if(pos == 64){
	            	  return getRelease_date();
	              }
	              if(pos == 65){
	            	  return getRelease_number();
	              }
	              if(pos == 66){
	            	  return getSuit_case_number();
	              }
	              if(pos == 67){
	            	  return getSuit_date();
	              }
	              if(pos == 68){
	            	  return getSuit_amount();
	              }
	              if(pos == 69){
	            	  return getSatisfaction_date();
	              }
	              if(pos == 70){
	            	  return getDischarge_date();
	              }
	              if(pos == 71){
	            	  return getClosed_date();
	              }
	              if(pos == 72){
	            	  return getTrust_deed_number();
	              }
	              if(pos == 73){
	            	  return getTrust_deed_date();
	              }
	              if(pos == 74){
	            	  return getSale_number();
	              }
	              if(pos == 75){
	            	  return getSale_date();
	              }
	              if(pos == 76){
	            	  return getCancellation_number();
	              }
	              if(pos == 77){
	            	  return getCancellation_date();
	              }
	              if(pos == 78){
	            	  return getSched_341_date();
	              }
	              if(pos == 79){
	            	  return getUpdate_date();
	              }
	              //Udhay Aug8 ==> New fields Tracers Addedd             
	              
	              if(pos == 80){
	            	  return getFullname();
	              }
	              if(pos == 81){
	            	  return getBusinessname();
	              }
	              if(pos == 82){
	            	  return getDob();
	              }
	              if(pos == 83){
	            	  return getCourt_address();
	              }
	              if(pos == 84){
	            	  return getCourt_city();
	              }
	              if(pos == 85){
	            	  return getCourt_zip();
	              }
	              if(pos == 86){
	            	  return getCourt_phone();
	              }
	              //
	              if(pos == 87){
	            	  return getSched_341_time();
	              }
	              if(pos == 88){
	            	  return getJudge();
	              }
	              if(pos == 89){
	            	  return getLawfirm();
	              }
	              if(pos == 90){
	            	  return getBook();
	              }
	              if(pos == 91){
	            	  return getPage();
	              }
	              if(pos == 92){
	            	  return getOrigdept();
	              }
	              if(pos == 93){
	            	  return getOrigcase();
	              }
	              if(pos == 94){
	            	  return getOrigbook();
	              }
	              if(pos == 95){
	            	  return getOrigpage();
	              }
	              if(pos == 96){
	            	  return getAssoccode();
	              }
	              if(pos == 97){
	            	  return getActiontype();
	              }
	              if(pos == 98){
	            	  return getActiontypedesc();
	              }
	              return null;
	              //
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
		                return setByName("name_filing_state", value);
		            }
		              if (position == 3)
		        {
		                return setByName("firstname", value);
		            }
		              if (position == 4)
		        {
		                return setByName("middlename", value);
		            }
		              if (position == 5)
		        {
		                return setByName("lastname", value);
		            }
		              if (position == 6)
		        {
		                return setByName("name_suffix", value);
		            }
		              if (position == 7)
		        {
		                return setByName("name_type", value);
		            }
		              if (position == 8)
		        {
		                return setByName("name_type_desc", value);
		            }
		              if (position == 9)
		        {
		                return setByName("ssn_taxid_flag", value);
		            }
		              if (position == 10)
		        {
		                return setByName("ssn", value);
		            }
		              if (position == 11)
		        {
		                return setByName("comment_sequence", value);
		            }
		              if (position == 12)
		        {
		                return setByName("comment_type", value);
		            }
		              if (position == 13)
		        {
		                return setByName("comment_type_desc", value);
		            }
		              if (position == 14)
		        {
		                return setByName("house_number", value);
		            }
		              if (position == 15)
		        {
		                return setByName("street_direction", value);
		            }
		              if (position == 16)
		        {
		                return setByName("street_name", value);
		            }
		              if (position == 17)
		        {
		                return setByName("street_suffix", value);
		            }
		              if (position == 18)
		        {
		                return setByName("apartment_num", value);
		            }
		              if (position == 19)
		        {
		                return setByName("city", value);
		            }
		              if (position == 20)
		        {
		                return setByName("state", value);
		            }
		              if (position == 21)
		        {
		                return setByName("zipcode", value);
		            }
		              if (position == 22)
		        {
		                return setByName("filing_state", value);
		            }
		              if (position == 23)
		        {
		                return setByName("filing_group", value);
		            }
		              if (position == 24)
		        {
		                return setByName("filing_group_desc", value);
		            }
		              if (position == 25)
		        {
		                return setByName("filing_type", value);
		            }
		              if (position == 26)
		        {
		                return setByName("filing_type_desc", value);
		            }
		              if (position == 27)
		        {
		                return setByName("filing_date", value);
		            }
		              if (position == 28)
		        {
		                return setByName("filing_number", value);
		            }
		              if (position == 29)
		        {
		                return setByName("tax_lien_type", value);
		            }
		              if (position == 30)
		        {
		                return setByName("tax_lien_type_desc", value);
		            }
		              if (position == 31)
		        {
		                return setByName("bankruptcy_type", value);
		            }
		              if (position == 32)
		        {
		                return setByName("bankruptcy_type_desc", value);
		            }
		              if (position == 33)
		        {
		                return setByName("docket_number", value);
		            }
		              if (position == 34)
		        {
		                return setByName("unlawful_detainer", value);
		            }
		              if (position == 35)
		        {
		                return setByName("initial_date", value);
		            }
		              if (position == 36)
		        {
		                return setByName("initial_amount", value);
		            }
		              if (position == 37)
		        {
		                return setByName("initial_docket", value);
		            }
		              if (position == 38)
		        {
		                return setByName("judgement_date", value);
		            }
		              if (position == 39)
		        {
		                return setByName("judgement_amount", value);
		            }
		              if (position == 40)
		        {
		                return setByName("judgement_docket", value);
		            }
		              if (position == 41)
		        {
		                return setByName("remove_date", value);
		            }
		              if (position == 42)
		        {
		                return setByName("remove_docket", value);
		            }
		              if (position == 43)
		        {
		                return setByName("dismissal_date", value);
		            }
		              if (position == 44)
		        {
		                return setByName("dismissal_docket", value);
		            }
		              if (position == 45)
		        {
		                return setByName("asset_amount", value);
		            }
		              if(position == 46){
		            	  return setByName("liability_amount", value);
		              }
		              if(position == 47){
		            	  return setByName("plaintiff", value);
		              }
		              if(position == 48){
		            	  return setByName("beneficiary", value);
		              }
		              if(position == 49){
		            	  return setByName("situs", value);
		              }
		              if(position == 50){
		            	  return setByName("trustee", value);
		              }
		              if(position == 51){
		            	  return setByName("county_code", value);
		              }
		              
		              
		              if(position == 52){
		            	  return setByName("county_name", value);
		              }
		              if(position == 53){
		            	  return setByName("county_state", value);
		              }
		              if(position == 54){
		            	  return setByName("court_code", value);
		              }
		              if(position == 55){
		            	  return setByName("court_desc", value);
		              }
		              if(position == 56){
		            	  return setByName("court_state", value);
		              }
		              if(position == 57){
		            	  return setByName("assets_available", value);
		              }
		              if(position == 58){
		            	  return setByName("perfected_date", value);
		              }
		              if(position == 59){
		            	  return setByName("action_state_code", value);
		              }
		              if(position == 60){
		            	  return setByName("action_desc", value);
		              }
		              if(position == 61){
		            	  return setByName("disposition_state_code", value);
		              }
		              if(position == 62){
		            	  return setByName("disposition_desc", value);
		              }
		              if(position == 63){
		            	  return setByName("amount", value);
		              }

		              
		              if(position == 64){
		            	  return setByName("release_date", value);
		              }
		              if(position == 65){
		            	  return setByName("release_number", value);
		              }
		              if(position == 66){
		            	  return setByName("suit_case_number", value);
		              }
		              if(position == 67){
		            	  return setByName("suit_date", value);
		              }
		              if(position == 68){
		            	  return setByName("suit_amount", value);
		              }
		              if(position == 69){
		            	  return setByName("satisfaction_date", value);
		              }
		              if(position == 70){
		            	  return setByName("discharge_date", value);
		              }
		              if(position == 71){
		            	  return setByName("closed_date", value);
		              }
		              if(position == 72){
		            	  return setByName("trust_deed_number", value);
		              }
		              if(position == 73){
		            	  return setByName("trust_deed_date", value);
		              }
		              if(position == 74){
		            	  return setByName("sale_number", value);
		              }
		              if(position == 75){
		            	  return setByName("sale_date", value);
		              }
		              if(position == 76){
		            	  return setByName("cancellation_number", value);
		              }
		              if(position == 77){
		            	  return setByName("cancellation_date", value);
		              }
		              if(position == 78){
		            	  return setByName("sched_341_date", value);
		              }
		              if(position == 79){
		            	  return setByName("update_date", value);
		              }
		              //Udhay Aug8 ==> new Fiedls Addedd
		              if(position == 80){
		            	  return setByName("fullname", value);
		              }
		              if(position == 81){
		            	  return setByName("businessname", value);
		              }
		              if(position == 82){
		            	  return setByName("dob", value);
		              }
		              if(position == 83){
		            	  return setByName("court_address", value);
		              }
		              if(position == 84){
		            	  return setByName("court_city", value);
		              }
		              if(position == 85){
		            	  return setByName("court_zip", value);
		              }
		              if(position == 86){
		            	  return setByName("court_phone", value);
		              }
		              //
		              if(position == 87){
		            	  return setByName("sched_341_time", value);
		              }
		              if(position == 88){
		            	  return setByName("judge", value);
		              }
		              if(position == 89){
		            	  return setByName("lawfirm", value);
		              }
		              if(position == 90){
		            	  return setByName("book", value);
		              }
		              if(position == 91){
		            	  return setByName("page", value);
		              }
		              if(position == 92){
		            	  return setByName("origdept", value);
		              }
		              if(position == 93){
		            	  return setByName("origcase", value);
		              }
		              if(position == 94){
		            	  return setByName("origbook", value);
		              }
		              if(position == 95){
		            	  return setByName("origpage", value);
		              }
		              if(position == 96){
		            	  return setByName("assoccode", value);
		              }
		              if(position == 97){
		            	  return setByName("actiontype", value);
		              }
		              if(position == 98){
		            	  return setByName("actiontypedesc", value);
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
	          save(NewBJLResultsPeer.DATABASE_NAME);
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
	                    NewBJLResultsPeer.doInsert((NewBJLResults) this, con);
	                    setNew(false);
	                }
	                else
	                {
	                    NewBJLResultsPeer.doUpdate((NewBJLResults) this, con);
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
	      public NewBJLResults copy() throws TorqueException
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
	    public NewBJLResults copy(boolean deepcopy) throws TorqueException
	    {
	        return copyInto(new NewBJLResults(), deepcopy);
	    }
	      
	      /**
	     * Fills the copyObj with the contents of this object.
	     * The associated objects are also copied and treated as new objects.
	     * @param copyObj the object to fill.
	     */
	    protected NewBJLResults copyInto(NewBJLResults copyObj) throws TorqueException
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
	      protected NewBJLResults copyInto(NewBJLResults copyObj, boolean deepcopy) throws TorqueException
	      {
	          copyObj.setResultId(resultId);
	          copyObj.setUserSearchId(userSearchId);
	          copyObj.setName_filing_state(name_filing_state);
	          copyObj.setFirstname(firstname);
	          copyObj.setMiddlename(middlename);
	          copyObj.setLastname(lastname);
	          copyObj.setName_suffix(name_suffix);
	          copyObj.setName_type(name_type);
	          copyObj.setName_type_desc(name_type_desc);
	          copyObj.setSsn_taxid_flag(ssn_taxid_flag);
	          copyObj.setSsn(ssn);
	          copyObj.setComment_sequence(comment_sequence);
	          copyObj.setComment_type(comment_type);
	          copyObj.setComment_type_desc(comment_type_desc);
	          copyObj.setHouse_number(house_number);
	          copyObj.setStreet_direction(street_direction);
	          copyObj.setStreet_name(street_name);
	          copyObj.setStreet_suffix(street_suffix);
	          copyObj.setApartment_num(apartment_num);
	          copyObj.setCity(city);
	          copyObj.setState(state);
	          copyObj.setZipcode(zipcode);
	          copyObj.setFiling_state(filing_state);
	          copyObj.setFiling_group(filing_group);
	          copyObj.setFiling_group_desc(filing_group_desc);
	          copyObj.setFiling_type(filing_type);
	          copyObj.setFiling_type_desc(filing_type_desc);
	          copyObj.setFiling_date(filing_date);
	          copyObj.setFiling_number(filing_number);
	          copyObj.setTax_lien_type(tax_lien_type);
	          copyObj.setTax_lien_type_desc(tax_lien_type_desc);
	          copyObj.setBankruptcy_type(bankruptcy_type);
	          copyObj.setBankruptcy_type_desc(bankruptcy_type_desc);
	          copyObj.setDocket_number(docket_number);
	          copyObj.setUnlawful_detainer(unlawful_detainer);
	          copyObj.setInitial_date(initial_date);
	          copyObj.setInitial_amount(initial_amount);
	          copyObj.setInitial_docket(initial_docket);
	          
	          copyObj.setJudgement_date(judgement_date);
	          copyObj.setJudgement_amount(judgement_amount);
	          copyObj.setJudgement_docket(judgement_docket);
	          copyObj.setRemove_date(remove_date);
	          copyObj.setRemove_docket(remove_docket);
	          copyObj.setDismissal_date(dismissal_date);
	          copyObj.setDismissal_docket(dismissal_docket);
	          copyObj.setAsset_amount(asset_amount);
	          
	          copyObj.setLiability_amount(liability_amount);
	          copyObj.setPlaintiff(plaintiff);
	          copyObj.setBeneficiary(beneficiary);
	          copyObj.setSitus(situs);
	          copyObj.setTrustee(trustee);
	          copyObj.setCounty_code(county_code);
	          copyObj.setCounty_name(county_name);
	          copyObj.setCounty_state(county_state);
	          
	          copyObj.setCourt_code(court_code);
	          copyObj.setCourt_desc(court_desc);
	          copyObj.setCourt_state(court_state);
	          copyObj.setAssets_available(assets_available);
	          copyObj.setPerfected_date(perfected_date);
	          copyObj.setAction_state_code(action_state_code);
	          copyObj.setAction_desc(action_desc);
	          copyObj.setDisposition_state_code(disposition_state_code);
	          
	          copyObj.setDisposition_desc(disposition_desc);
	          copyObj.setAmount(amount);
	          copyObj.setRelease_date(release_date);
	          copyObj.setRelease_number(release_number);
	          copyObj.setSuit_case_number(suit_case_number);
	          copyObj.setSuit_date(suit_date);
	          copyObj.setSuit_amount(suit_amount);
	          copyObj.setSatisfaction_date(satisfaction_date);
	          
	          copyObj.setDischarge_date(discharge_date);
	          copyObj.setClosed_date(closed_date);
	          copyObj.setTrust_deed_number(trust_deed_number);
	          copyObj.setTrust_deed_date(trust_deed_date);
	          copyObj.setSale_number(sale_number);
	          copyObj.setSale_date(sale_date);
	          copyObj.setCancellation_number(cancellation_number);
	          copyObj.setCancellation_date(cancellation_date);
	          copyObj.setSched_341_date(sched_341_date);
	          copyObj.setUpdate_date(update_date);
	          
	          //Udhay-Aug8 ==>new tracers fields addedd	          
	          copyObj.setFullname(fullname);
	          copyObj.setBusinessname(businessname);
	          copyObj.setDob(dob);
	          copyObj.setCourt_address(court_address);
	          copyObj.setCourt_city(court_city);
	          copyObj.setCourt_zip(court_zip);
	          copyObj.setCourt_phone(court_phone);
	          //
	          copyObj.setSched_341_time(sched_341_time);
	          copyObj.setJudge(judge);
	          copyObj.setLawfirm(lawfirm);
	          copyObj.setBook(book);
	          copyObj.setPage(page);
	          copyObj.setOrigdept(origdept);
	          copyObj.setOrigcase(origcase);
	          copyObj.setOrigbook(origbook);
	          copyObj.setOrigpage(origpage);
	          copyObj.setAssoccode(assoccode);
	          copyObj.setActiontype(actiontype);
	          copyObj.setActiontypedesc(actiontypedesc);
	          
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
	    public NewBJLResultsPeer getPeer()
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
	        return NewBJLResultsPeer.getTableMap();
	    }

	  
	    /**
	     * Creates a BjlResultsBean with the contents of this object
	       * This also creates beans for cached related objects, if they exist
	       * @return a BjlResultsBean with the contents of this object
	     */
	    public NewBaseBJLResultsBean getBean()
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
	    public NewBaseBJLResultsBean getBean(IdentityMap createdBeans)
	    {
	    	NewBaseBJLResultsBean result = (NewBaseBJLResultsBean) createdBeans.get(this);
	        if (result != null ) {
	            // we have already created a bean for this object, return it
	            return result;
	        }
	        // no bean exists for this object; create a new one
	        result = new NewBaseBJLResultsBean();
	        createdBeans.put(this, result);

	        result.setResultId(getResultId());
	          result.setUserSearchId(getUserSearchId());
	          result.setName_filing_state(getName_filing_state());
	          result.setFirstname(getFirstname());
	          result.setMiddlename(getMiddlename());
	          result.setLastname(getLastname());
	          result.setName_suffix(getName_suffix());
	          result.setName_type(getName_type());
	          result.setName_type_desc(getName_type_desc());
	          result.setSsn_taxid_flag(getSsn_taxid_flag());
	          result.setSsn(getSsn());
	          result.setComment_sequence(getComment_sequence());
	          result.setComment_type(getComment_type());
	          result.setComment_type_desc(getComment_type_desc());
	          result.setHouse_number(getHouse_number());
	          result.setStreet_direction(getStreet_direction());
	          result.setStreet_name(getStreet_name());
	          result.setStreet_suffix(getStreet_suffix());
	          result.setApartment_num(getApartment_num());
	          result.setCity(getCity());
	          result.setState(getState());
	          result.setZipcode(getZipcode());
	          result.setFiling_state(getFiling_state());
	          result.setFiling_group(getFiling_group());
	          result.setFiling_group_desc(getFiling_group_desc());
	          result.setFiling_type(getFiling_type());
	          result.setFiling_type_desc(getFiling_type_desc());
	          result.setFiling_date(getFiling_date());
	          result.setFiling_number(getFiling_number());
	          result.setTax_lien_type(getTax_lien_type());
	          result.setTax_lien_type_desc(getTax_lien_type_desc());
	          result.setBankruptcy_type(getBankruptcy_type());
	          result.setBankruptcy_type_desc(getBankruptcy_type_desc());
	          result.setDocket_number(getDocket_number());
	          result.setUnlawful_detainer(getUnlawful_detainer());
	          result.setInitial_date(getInitial_date());
	          result.setInitial_amount(getInitial_amount());
	          result.setInitial_docket(getInitial_docket());
	         
	          result.setJudgement_date(getJudgement_date());
	          result.setJudgement_amount(getJudgement_amount());
	          result.setJudgement_docket(getJudgement_docket());
	          result.setRemove_date(getRemove_date());
	          result.setRemove_docket(getRemove_docket());
	          result.setDismissal_date(getDismissal_date());
	          result.setDismissal_docket(getDismissal_docket());
	          result.setAsset_amount(getAsset_amount());
	        
	          result.setLiability_amount(getLiability_amount());
	          result.setPlaintiff(getPlaintiff());
	          result.setBeneficiary(getBeneficiary());
	          result.setSitus(getSitus());
	          result.setTrustee(getTrustee());
	          result.setCounty_code(getCounty_code());
	          result.setCounty_name(getCounty_name());
	          result.setCounty_state(getCounty_state());
	          
	          result.setCourt_code(getCourt_code());
	          result.setCourt_desc(getCourt_desc());
	          result.setCourt_state(getCourt_state());
	          result.setAssets_available(getAssets_available());
	          result.setPerfected_date(getPerfected_date());
	          result.setAction_state_code(getAction_state_code());
	          result.setAction_desc(getAction_desc());
	          result.setDisposition_state_code(getDisposition_state_code());
	          
	          result.setDisposition_desc(getDisposition_desc());
	          result.setAmount(getAmount());
	          result.setRelease_date(getRelease_date());
	          result.setRelease_number(getRelease_number());
	          result.setSuit_case_number(getSuit_case_number());
	          result.setSuit_date(getSuit_date());
	          result.setSuit_amount(getSuit_amount());
	          result.setSatisfaction_date(getSatisfaction_date());
	          
	          result.setDischarge_date(getDischarge_date());
	          result.setClosed_date(getClosed_date());
	          result.setTrust_deed_number(getTrust_deed_number());
	          result.setTrust_deed_date(getTrust_deed_date());
	          result.setSale_number(getSale_number());
	          result.setSale_date(getSale_date());
	          result.setCancellation_number(getCancellation_number());
	          result.setCancellation_date(getCancellation_date());
	          result.setSched_341_date(getSched_341_date());
	          result.setUpdate_date(getUpdate_date());
	          //newly added fields
	          result.setFullname(getFullname());
	          result.setBusinessname(getBusinessname());
	          result.setDob(getDob());
	          result.setCourt_address(getCourt_address());
	          result.setCourt_city(getCourt_city());
	          result.setCourt_zip(getCourt_zip());
	          result.setCourt_phone(getCourt_phone());         
	          //
	          result.setSched_341_time(getSched_341_time());
	          result.setJudge(getJudge());
	          result.setLawfirm(getLawfirm());
	          result.setBook(getBook());
	          result.setPage(getPage());
	          result.setOrigdept(getOrigdept());
	          result.setOrigcase(getOrigcase());
	          result.setOrigbook(getOrigbook());
	          result.setOrigpage(getOrigpage());
	          result.setAssoccode(getAssoccode());
	          result.setActiontype(getActiontype());
	          result.setActiontypedesc(getActiontypedesc());
	          
	          
	          
	      
	                                
	        if (aBjlSearches != null)
	        {
	            NewBJLSearchesBean relatedBean = aBjlSearches.getBean(createdBeans);
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
	    public static NewBJLResults createBjlResults(NewBaseBJLResultsBean bean)
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

	    public static NewBJLResults createBjlResults(NewBaseBJLResultsBean bean, IdentityMap createdObjects)
	        throws TorqueException
	    {
	    	NewBJLResults result = (NewBJLResults) createdObjects.get(bean);
	        if (result != null)
	        {
	            // we already have an object for the bean, return it
	            return result;
	        }
	        result = new NewBJLResults();
	        createdObjects.put(bean, result);

	        result.setResultId(bean.getResultId());
	          result.setUserSearchId(bean.getUserSearchId());
	          result.setName_filing_state(bean.getName_filing_state());
	          result.setFirstname(bean.getFirstname());
	          result.setMiddlename(bean.getMiddlename());
	          result.setLastname(bean.getLastname());
	          result.setName_suffix(bean.getName_suffix());
	          result.setName_type(bean.getName_type());
	          result.setName_type_desc(bean.getName_type_desc());
	          result.setSsn_taxid_flag(bean.getSsn_taxid_flag());
	          result.setSsn(bean.getSsn());
	          result.setComment_sequence(bean.getComment_sequence());
	          result.setComment_type(bean.getComment_type());
	          result.setComment_type_desc(bean.getComment_type_desc());
	          result.setHouse_number(bean.getHouse_number());
	          result.setStreet_direction(bean.getStreet_direction());
	          result.setStreet_name(bean.getStreet_name());
	          result.setStreet_suffix(bean.getStreet_suffix());
	          result.setApartment_num(bean.getApartment_num());
	          result.setCity(bean.getCity());
	          result.setState(bean.getState());
	          result.setZipcode(bean.getZipcode());
	          result.setFiling_state(bean.getFiling_state());
	          result.setFiling_group(bean.getFiling_group());
	          result.setFiling_group_desc(bean.getFiling_group_desc());
	          result.setFiling_type(bean.getFiling_type());
	          result.setFiling_type_desc(bean.getFiling_type_desc());
	          result.setFiling_date(bean.getFiling_date());
	          result.setFiling_number(bean.getFiling_number());
	          result.setTax_lien_type(bean.getTax_lien_type());
	          result.setTax_lien_type_desc(bean.getTax_lien_type_desc());
	          result.setBankruptcy_type(bean.getBankruptcy_type());
	          result.setBankruptcy_type_desc(bean.getBankruptcy_type_desc());
	          result.setDocket_number(bean.getDocket_number());
	          result.setUnlawful_detainer(bean.getUnlawful_detainer());
	          result.setInitial_date(bean.getInitial_date());
	          result.setInitial_amount(bean.getInitial_amount());
	          result.setInitial_docket(bean.getInitial_docket());
	          
	          result.setJudgement_date(bean.getJudgement_date());
	          result.setJudgement_amount(bean.getJudgement_amount());
	          result.setJudgement_docket(bean.getJudgement_docket());
	          result.setRemove_date(bean.getRemove_date());
	          result.setRemove_docket(bean.getRemove_docket());
	          result.setDismissal_date(bean.getDismissal_date());
	          result.setDismissal_docket(bean.getDismissal_docket());
	          result.setAsset_amount(bean.getAsset_amount());
	          result.setLiability_amount(bean.getLiability_amount());
	          result.setPlaintiff(bean.getPlaintiff());
	          result.setBeneficiary(bean.getBeneficiary());
	          result.setSitus(bean.getSitus());
	          result.setTrustee(bean.getTrustee());
	          result.setCounty_code(bean.getCounty_code());
	          result.setCounty_name(bean.getCounty_name());
	          result.setCounty_state(bean.getCounty_state());
	          
	          result.setCourt_code(bean.getCourt_code());
	          result.setCourt_desc(bean.getCourt_desc());
	          result.setCourt_state(bean.getCourt_state());
	          result.setAssets_available(bean.getAssets_available());
	          result.setPerfected_date(bean.getPerfected_date());
	          result.setAction_state_code(bean.getAction_state_code());
	          result.setAction_desc(bean.getAction_desc());
	          result.setDisposition_state_code(bean.getDisposition_state_code());
	          
	          result.setDisposition_desc(bean.getDisposition_desc());
	          result.setAmount(bean.getAmount());
	          result.setRelease_date(bean.getRelease_date());
	          result.setRelease_number(bean.getRelease_number());
	          result.setSuit_case_number(bean.getSuit_case_number());
	          result.setSuit_date(bean.getSuit_date());
	          result.setSuit_amount(bean.getSuit_amount());
	          result.setSatisfaction_date(bean.getSatisfaction_date());
	          
	          result.setDischarge_date(bean.getDischarge_date());
	          result.setClosed_date(bean.getClosed_date());
	          result.setTrust_deed_number(bean.getTrust_deed_number());
	          result.setTrust_deed_date(bean.getTrust_deed_date());
	          result.setSale_number(bean.getSale_number());
	          result.setSale_date(bean.getSale_date());
	          result.setCancellation_number(bean.getCancellation_number());
	          result.setCancellation_date(bean.getCancellation_date());
	          result.setSched_341_date(bean.getSched_341_date());
	          result.setUpdate_date(bean.getUpdate_date());
	          //Udhay-Aug8 ==>New Tracers fields addedd
	          result.setFullname(bean.getFullname());
	          result.setBusinessname(bean.getBusinessname());
	          result.setDob(bean.getDob());
	          result.setCourt_address(bean.getCourt_address());
	          result.setCourt_city(bean.getCourt_city());
	          result.setCourt_zip(bean.getCourt_zip());
	          result.setCourt_phone(bean.getCourt_phone());
	          //
	          result.setSched_341_time(bean.getSched_341_time());
	          result.setJudge(bean.getJudge());
	          result.setLawfirm(bean.getLawfirm());
	          result.setBook(bean.getBook());
	          result.setPage(bean.getPage());
	          result.setOrigdept(bean.getOrigdept());
	          result.setOrigcase(bean.getOrigcase());
	          result.setOrigbook(bean.getOrigbook());
	          result.setOrigpage(bean.getOrigpage());
	          result.setAssoccode(bean.getAssoccode());
	          result.setActiontype(bean.getActiontype());
	          result.setActiontypedesc(bean.getActiontypedesc());
	          
	          {
	            NewBJLSearchesBean relatedBean = bean.getBjlSearchesBean();
	            if (relatedBean != null)
	            {
	                NewBJLSearches relatedObject = NewBJLSearches.createBjlSearches(relatedBean, createdObjects);
	                result.setBjlSearches(relatedObject);
	            }
	          }
	          result.setModified(bean.isModified());
	             
	    result.setNew(bean.isNew());
	  	return result;
	    }

	                      

	    public String toString()
	    {
	        StringBuffer str = new StringBuffer();
		        str.append("NewBJLResults:\n");
		        str.append("ResultId = ")
		               .append(getResultId())
		             .append("\n");
		        str.append("UserSearchId = ")
		               .append(getUserSearchId())
		             .append("\n");
		        str.append("name_filing_state = ")
		               .append(getName_filing_state())
		             .append("\n");
		        str.append("firstname = ")
		               .append(getFirstname())
		             .append("\n");
		        str.append("middlename = ")
		               .append(getMiddlename())
		             .append("\n");
		        str.append("lastname = ")
		               .append(getLastname())
		             .append("\n");
		        str.append("name_suffix = ")
		               .append(getName_suffix())
		             .append("\n");
		        str.append("name_type = ")
		               .append(getName_type())
		             .append("\n");
		        str.append("name_type_desc = ")
		               .append(getName_type_desc())
		             .append("\n");
		        str.append("ssn_taxid_flag = ")
		               .append(getSsn_taxid_flag())
		             .append("\n");
		        str.append("ssn = ")
		               .append(getSsn())
		             .append("\n");
		        str.append("comment_sequence = ")
		               .append(getComment_sequence())
		             .append("\n");
		        str.append("comment_type = ")
		               .append(getComment_type())
		             .append("\n");
		        str.append("comment_type_desc = ")
		               .append(getComment_type_desc())
		             .append("\n");
		        str.append("house_number = ")
		               .append(getHouse_number())
		             .append("\n");
		        str.append("street_direction = ")
		               .append(getStreet_direction())
		             .append("\n");
		        str.append("street_name = ")
		               .append(getStreet_name())
		             .append("\n");
		        str.append("street_suffix = ")
		               .append(getStreet_suffix())
		             .append("\n");
		        str.append("apartment_num = ")
		               .append(getApartment_num())
		             .append("\n");
		        str.append("city = ")
		               .append(getCity())
		             .append("\n");
		        str.append("state = ")
		               .append(getState())
		             .append("\n");
		        str.append("zipcode = ")
		               .append(getZipcode())
		             .append("\n");
		        str.append("filing_state = ")
		               .append(getFiling_state())
		             .append("\n");
		        str.append("filing_group = ")
		               .append(getFiling_group())
		             .append("\n");
		        str.append("filing_group_desc = ")
		               .append(getFiling_group_desc())
		             .append("\n");
		        str.append("filing_type = ")
		               .append(getFiling_type())
		             .append("\n");
		        str.append("filing_type_desc = ")
		               .append(getFiling_type_desc())
		             .append("\n");
		        str.append("filing_date = ")
		               .append(getFiling_date())
		             .append("\n");
		        str.append("filing_number = ")
		               .append(getFiling_number())
		             .append("\n");
		        str.append("tax_lien_type = ")
		               .append(getTax_lien_type())
		             .append("\n");
		        str.append("tax_lien_type_desc = ")
		               .append(getTax_lien_type_desc())
		             .append("\n");
		        str.append("bankruptcy_type = ")
		               .append(getBankruptcy_type())
		             .append("\n");
		        str.append("bankruptcy_type_desc = ")
		               .append(getBankruptcy_type_desc())
		             .append("\n");
		        str.append("docket_number = ")
		               .append(getDocket_number())
		             .append("\n");
		        str.append("unlawful_detainer = ")
		               .append(getUnlawful_detainer())
		             .append("\n");
		        str.append("initial_date = ")
		               .append(getInitial_date())
		             .append("\n");
		        str.append("initial_amount = ")
		               .append(getInitial_amount())
		             .append("\n");
		        str.append("initial_docket = ")
		               .append(getInitial_docket())
		             .append("\n");
		        str.append("judgement_date = ")
		               .append(getJudgement_date())
		             .append("\n");
		        str.append("judgement_amount = ")
		               .append(getJudgement_amount())
		             .append("\n");
		        str.append("judgement_docket = ")
		               .append(getJudgement_docket())
		             .append("\n");
		        str.append("remove_date = ")
		               .append(getRemove_date())
		             .append("\n");
		        str.append("remove_docket = ")
		               .append(getRemove_docket())
		             .append("\n");
		        str.append("dismissal_date = ")
		               .append(getDismissal_date())
		             .append("\n");
		        str.append("dismissal_docket = ")
		               .append(getDismissal_docket())
		             .append("\n");
		        str.append("asset_amount = ")
		               .append(getAsset_amount())
		             .append("\n");
		        str.append("liability_amount = ")
		        .append(getLiability_amount())
		             .append("\n");
	        
			        str.append("plaintiff = ")
		            .append(getPlaintiff())
		          .append("\n");
		     str.append("beneficiary = ")
		            .append(getBeneficiary())
		          .append("\n");
		     str.append("situs = ")
		            .append(getSitus())
		          .append("\n");
		     str.append("trustee = ")
		            .append(getTrustee())
		          .append("\n");
		     str.append("county_code = ")
		            .append(getCounty_code())
		          .append("\n");
		     str.append("county_name = ")
		            .append(getCounty_name())
		          .append("\n");
		     str.append("county_state = ")
		            .append(getCounty_state())
		          .append("\n");
		     str.append("court_code = ")
		            .append(getCourt_code())
		          .append("\n");
		     str.append("court_desc = ")
		            .append(getCourt_desc())
		          .append("\n");
		     str.append("court_state = ")
		            .append(getCourt_state())
		          .append("\n");
		     str.append("assets_available = ")
		            .append(getAssets_available())
		          .append("\n");
		     str.append("perfected_date = ")
		            .append(getPerfected_date())
		          .append("\n");
		     str.append("action_state_code = ")
		            .append(getAction_state_code())
		          .append("\n");
		     str.append("action_desc = ")
		     .append(getAction_desc())
		          .append("\n");
     
		     str.append("disposition_desc = ")
		     .append(getDisposition_desc())
		   .append("\n");
		str.append("amount = ")
		     .append(getAmount())
		   .append("\n");
		str.append("release_date = ")
		     .append(getRelease_date())
		   .append("\n");
		str.append("release_number = ")
		     .append(getRelease_number())
		   .append("\n");
		str.append("suit_case_number = ")
		     .append(getSuit_case_number())
		   .append("\n");
		str.append("suit_date = ")
		     .append(getSuit_date())
		   .append("\n");
		str.append("suit_amount = ")
		     .append(getSuit_amount())
		   .append("\n");
		str.append("satisfaction_date = ")
		     .append(getSatisfaction_date())
		   .append("\n");
		str.append("discharge_date = ")
		     .append(getDischarge_date())
		   .append("\n");
		str.append("closed_date = ")
		     .append(getClosed_date())
		   .append("\n");
		str.append("trust_deed_number = ")
		     .append(getTrust_deed_number())
		   .append("\n");
		str.append("trust_deed_date = ")
		     .append(getTrust_deed_date())
		   .append("\n");
		str.append("sale_number = ")
		     .append(getSale_number())
		   .append("\n");
		str.append("sale_date = ")
		.append(getSale_date())
		   .append("\n");
		str.append("cancellation_number = ")
		.append(getTrust_deed_number())
		.append("\n");
		str.append("cancellation_date = ")
		.append(getTrust_deed_date())
		.append("\n");
		str.append("sched_341_date = ")
		.append(getSale_number())
		.append("\n");
		str.append("update_date = ")
		.append(getSale_date())
		.append("\n");
		//Udhay-Aug8 ==> New Tracers Fields addedd

		str.append("fullname = ")
		.append(getFullname())
		.append("\n");

		str.append("businessname = ")
		.append(getBusinessname())
		.append("\n");

		str.append("dob = ")
		.append(getDob())
		.append("\n");

		str.append("court_address = ")
		.append(getCourt_address())
		.append("\n");

		str.append("court_city = ")
		.append(getCourt_city())
		.append("\n");

		str.append("court_zip = ")
		.append(getCourt_zip())
		.append("\n");

		str.append("sched_341_time = ")
		.append(getSched_341_time())
		.append("\n");
		//
		str.append("judge = ")
		.append(getJudge())
		.append("\n");
		
		str.append("lawfirm = ")
		.append(getLawfirm())
		.append("\n");
		str.append("book = ")
		.append(getBook())
		.append("\n");
		str.append("page = ")
		.append(getPage())
		.append("\n");
		str.append("origdept = ")
		.append(getOrigdept())
		.append("\n");
		str.append("origcase = ")
		.append(getOrigcase())
		.append("\n");
		str.append("origbook = ")
		.append(getOrigbook())
		.append("\n");
		str.append("origpage = ")
		.append(getOrigpage())
		.append("\n");
		str.append("assoccode = ")
		.append(getAssoccode())
		.append("\n");
		str.append("actiontype = ")
		.append(getActiontype())
		.append("\n");
		str.append("actiontypedesc = ")
		.append(getActiontypedesc())
		.append("\n");
		
		return(str.toString());
	    }

}
