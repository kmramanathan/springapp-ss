package springapp.web.funnel;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class BjlXMLbean {
	
	
	private String search_identifier; 
	private String customer_refernece;
	private String search;
	private String rows_returned;
	
	
	
	
	
	ArrayList bjlNameList=new ArrayList();
	ArrayList bjlAddressList=new ArrayList();
	ArrayList bjlFilingList=new ArrayList();
	
	private bjlNameObj nameObj;
	private bjlAddressObj addressObj;
	private bjlFilingObj filingObj;
	
	public String getSearch_identifier() {
		return search_identifier;
	}
	public void setSearch_identifier(String search_identifier) {
		this.search_identifier = search_identifier;
	}
	public String getCustomer_refernece() {
		return customer_refernece;
	}
	public void setCustomer_refernece(String customer_refernece) {
		this.customer_refernece = customer_refernece;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getRows_returned() {
		return rows_returned;
	}
	public void setRows_returned(String rows_returned) {
		this.rows_returned = rows_returned;
	}
	
	public ArrayList getBjlNameList() {
		return bjlNameList;
	}
	public void setBjlNameList(ArrayList bjlNameList) {
		this.bjlNameList = bjlNameList;
	}
	public ArrayList getBjlAddressList() {
		return bjlAddressList;
	}
	public void setBjlAddressList(ArrayList bjlAddressList) {
		this.bjlAddressList = bjlAddressList;
	}
	public ArrayList getBjlFilingList() {
		return bjlFilingList;
	}
	public void setBjlFilingList(ArrayList bjlFilingList) {
		this.bjlFilingList = bjlFilingList;
	}
	public bjlNameObj getNameObj() {
		return nameObj;
	}
	public void setNameObj(bjlNameObj nameObj) {
		this.nameObj = nameObj;
	}
	public bjlAddressObj getAddressObj() {
		return addressObj;
	}
	public void setAddressObj(bjlAddressObj addressObj) {
		this.addressObj = addressObj;
	}
	public bjlFilingObj getFilingObj() {
		return filingObj;
	}
	public void setFilingObj(bjlFilingObj filingObj) {
		this.filingObj = filingObj;
	}
	public static class bjlNameObj 
	{
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
	public bjlNameObj(String name_filing_state,String firstname,String middlename,String lastname,String name_suffix,String name_type,
			String name_type_desc,String ssn_taxid_flag,String ssn,String comment_sequence,String comment_type,String comment_type_desc)
	{
		this.name_filing_state = name_filing_state;
		this.firstname= firstname;
		this.middlename= middlename;
		this.lastname = lastname;
		this.name_suffix = name_suffix;
		this.name_type=name_type;
		this.name_type_desc = name_type_desc;
		this.ssn_taxid_flag = ssn_taxid_flag;
		this.ssn = ssn;
		this.comment_sequence = comment_sequence;
		this.comment_type = comment_type;
		this.comment_type_desc = comment_type_desc;
		
		}
	public String getName_filing_state() {
		return name_filing_state;
	}
	public void setName_filing_state(String name_filing_state) {
		this.name_filing_state = name_filing_state;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getName_suffix() {
		return name_suffix;
	}
	public void setName_suffix(String name_suffix) {
		this.name_suffix = name_suffix;
	}
	public String getName_type() {
		return name_type;
	}
	public void setName_type(String name_type) {
		this.name_type = name_type;
	}
	public String getName_type_desc() {
		return name_type_desc;
	}
	public void setName_type_desc(String name_type_desc) {
		this.name_type_desc = name_type_desc;
	}
	public String getSsn_taxid_flag() {
		return ssn_taxid_flag;
	}
	public void setSsn_taxid_flag(String ssn_taxid_flag) {
		this.ssn_taxid_flag = ssn_taxid_flag;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getComment_sequence() {
		return comment_sequence;
	}
	public void setComment_sequence(String comment_sequence) {
		this.comment_sequence = comment_sequence;
	}
	public String getComment_type() {
		return comment_type;
	}
	public void setComment_type(String comment_type) {
		this.comment_type = comment_type;
	}
	public String getComment_type_desc() {
		return comment_type_desc;
	}
	public void setComment_type_desc(String comment_type_desc) {
		this.comment_type_desc = comment_type_desc;
	}
	
	}
	public static class bjlAddressObj
	{
		private String house_number;
		private String street_direction;
		private String street_name;
		private String street_suffix;
		private String apartment_num;
		private String city;
		private String state;
		private String zipcode;
		
		
		public bjlAddressObj(String house_number,
		String street_direction,
		String street_name,
		String street_suffix,
		String apartment_num,
		String city,
		String state,
		String zipcode
		)
		{
			this.house_number = house_number;
			this.street_direction=street_direction;
			this.street_name = street_name;
			this.street_suffix =street_suffix;
			this.apartment_num = apartment_num;
			this.city = city;
			this.state = state;
			this.zipcode = zipcode;
				}
		public String getHouse_number() {
			return house_number;
		}
		public void setHouse_number(String house_number) {
			this.house_number = house_number;
		}
		public String getStreet_direction() {
			return street_direction;
		}
		public void setStreet_direction(String street_direction) {
			this.street_direction = street_direction;
		}
		public String getStreet_name() {
			return street_name;
		}
		public void setStreet_name(String street_name) {
			this.street_name = street_name;
		}
		public String getStreet_suffix() {
			return street_suffix;
		}
		public void setStreet_suffix(String street_suffix) {
			this.street_suffix = street_suffix;
		}
		public String getApartment_num() {
			return apartment_num;
		}
		public void setApartment_num(String apartment_num) {
			this.apartment_num = apartment_num;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getZipcode() {
			return zipcode;
		}
		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}
		
	}
	public static class bjlFilingObj
	{
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
		
		public bjlFilingObj(String filing_state,String filing_group,String filing_group_desc,
		String filing_type,String filing_type_desc,String filing_date,String filing_number,
		String tax_lien_type,String tax_lien_type_desc,String bankruptcy_type,
		String bankruptcy_type_desc,String docket_number,String unlawful_detainer,
		String initial_date,String initial_amount,String initial_docket,String judgement_date,
		String judgement_amount,String judgement_docket,String remove_date,String remove_docket,
		String dismissal_date,String dismissal_docket,String asset_amount,String liability_amount,
		String plaintiff,String beneficiary,String situs,String trustee,String county_code,
		String county_name,String county_state,String court_code,String court_desc,
		String court_state,String assets_available,String perfected_date,String action_state_code,
		String action_desc,String disposition_state_code,String disposition_desc,
		String amount,String release_date,String release_number,String suit_case_number,
		String suit_date,String suit_amount,String satisfaction_date,String discharge_date,String closed_date,
		String trust_deed_number,String trust_deed_date,String sale_number,String sale_date,
		String cancellation_number,String cancellation_date,String sched_341_date,String update_date)
		{
			this.filing_state= filing_state ;
			this.filing_group = filing_group;
			this.filing_group_desc = filing_group_desc;
			this.filing_type = filing_type;
			this.filing_type_desc = filing_type_desc;
			this.filing_date = filing_date;
			this.filing_number = filing_number;
			this.tax_lien_type = tax_lien_type;
			this.tax_lien_type_desc= tax_lien_type_desc;
			this.bankruptcy_type = bankruptcy_type;
			this.bankruptcy_type_desc = bankruptcy_type_desc;
			this.docket_number = docket_number;
			this.unlawful_detainer = unlawful_detainer;
			this.initial_date = initial_date;
			this.initial_amount = initial_amount;
			this.initial_docket = initial_docket;
			
			this.judgement_date = judgement_date;
			this.judgement_amount = judgement_amount;
			this.judgement_docket = judgement_docket;
			this.remove_date = remove_date;
			this.remove_docket = remove_docket;
			this.dismissal_date = dismissal_date;
			this.dismissal_docket =dismissal_docket;
			this.asset_amount = asset_amount;
			this.liability_amount = liability_amount;
			this.plaintiff = plaintiff;
			this.beneficiary = beneficiary;
			this.situs = situs;
			this.trustee = trustee;
			this.county_code = county_code;
			this.county_name = county_name;
			this.county_state = county_state;
			
			this.court_code =court_code;
			this.court_desc = court_desc;
			this.court_state = court_state;
			this.assets_available = assets_available;
			this.perfected_date = perfected_date;
			this.action_state_code = action_state_code;
			this.action_desc = action_desc;
			this.disposition_state_code = disposition_state_code;
			this.disposition_desc = disposition_desc;
			this.amount = amount;
			this.release_date =release_date;
			this.release_number = release_number;
			this.suit_case_number = suit_case_number;
			this.suit_date = suit_date;
			this.suit_amount = suit_amount;
			this.satisfaction_date = satisfaction_date;
			this.discharge_date = discharge_date;
			
			this.closed_date = closed_date;
			this.trust_deed_number = trust_deed_number;
			this.trust_deed_date = trust_deed_date;
			this.sale_number = sale_number;
			this.sale_date = sale_date;
			this.cancellation_number = cancellation_number;
			this.cancellation_date = cancellation_date;
			this.sched_341_date = sched_341_date;
			this.update_date = update_date;
			}

		public String getFiling_state() {
			return filing_state;
		}

		public void setFiling_state(String filing_state) {
			this.filing_state = filing_state;
		}

		public String getFiling_group() {
			return filing_group;
		}

		public void setFiling_group(String filing_group) {
			this.filing_group = filing_group;
		}

		public String getFiling_group_desc() {
			return filing_group_desc;
		}

		public void setFiling_group_desc(String filing_group_desc) {
			this.filing_group_desc = filing_group_desc;
		}

		public String getFiling_type() {
			return filing_type;
		}

		public void setFiling_type(String filing_type) {
			this.filing_type = filing_type;
		}

		public String getFiling_type_desc() {
			return filing_type_desc;
		}

		public void setFiling_type_desc(String filing_type_desc) {
			this.filing_type_desc = filing_type_desc;
		}

		public String getFiling_date() {
			return filing_date;
		}

		public void setFiling_date(String filing_date) {
			this.filing_date = filing_date;
		}

		public String getFiling_number() {
			return filing_number;
		}

		public void setFiling_number(String filing_number) {
			this.filing_number = filing_number;
		}

		public String getTax_lien_type() {
			return tax_lien_type;
		}

		public void setTax_lien_type(String tax_lien_type) {
			this.tax_lien_type = tax_lien_type;
		}

		public String getTax_lien_type_desc() {
			return tax_lien_type_desc;
		}

		public void setTax_lien_type_desc(String tax_lien_type_desc) {
			this.tax_lien_type_desc = tax_lien_type_desc;
		}

		public String getBankruptcy_type() {
			return bankruptcy_type;
		}

		public void setBankruptcy_type(String bankruptcy_type) {
			this.bankruptcy_type = bankruptcy_type;
		}

		public String getBankruptcy_type_desc() {
			return bankruptcy_type_desc;
		}

		public void setBankruptcy_type_desc(String bankruptcy_type_desc) {
			this.bankruptcy_type_desc = bankruptcy_type_desc;
		}

		public String getDocket_number() {
			return docket_number;
		}

		public void setDocket_number(String docket_number) {
			this.docket_number = docket_number;
		}

		public String getUnlawful_detainer() {
			return unlawful_detainer;
		}

		public void setUnlawful_detainer(String unlawful_detainer) {
			this.unlawful_detainer = unlawful_detainer;
		}

		public String getInitial_date() {
			return initial_date;
		}

		public void setInitial_date(String initial_date) {
			this.initial_date = initial_date;
		}

		public String getInitial_amount() {
			return initial_amount;
		}

		public void setInitial_amount(String initial_amount) {
			this.initial_amount = initial_amount;
		}

		public String getInitial_docket() {
			return initial_docket;
		}

		public void setInitial_docket(String initial_docket) {
			this.initial_docket = initial_docket;
		}

		public String getJudgement_date() {
			return judgement_date;
		}

		public void setJudgement_date(String judgement_date) {
			this.judgement_date = judgement_date;
		}

		public String getJudgement_amount() {
			return judgement_amount;
		}

		public void setJudgement_amount(String judgement_amount) {
			this.judgement_amount = judgement_amount;
		}

		public String getJudgement_docket() {
			return judgement_docket;
		}

		public void setJudgement_docket(String judgement_docket) {
			this.judgement_docket = judgement_docket;
		}

		public String getRemove_date() {
			return remove_date;
		}

		public void setRemove_date(String remove_date) {
			this.remove_date = remove_date;
		}

		public String getRemove_docket() {
			return remove_docket;
		}

		public void setRemove_docket(String remove_docket) {
			this.remove_docket = remove_docket;
		}

		public String getDismissal_date() {
			return dismissal_date;
		}

		public void setDismissal_date(String dismissal_date) {
			this.dismissal_date = dismissal_date;
		}

		public String getDismissal_docket() {
			return dismissal_docket;
		}

		public void setDismissal_docket(String dismissal_docket) {
			this.dismissal_docket = dismissal_docket;
		}

		public String getAsset_amount() {
			return asset_amount;
		}

		public void setAsset_amount(String asset_amount) {
			this.asset_amount = asset_amount;
		}

		public String getLiability_amount() {
			return liability_amount;
		}

		public void setLiability_amount(String liability_amount) {
			this.liability_amount = liability_amount;
		}

		public String getPlaintiff() {
			return plaintiff;
		}

		public void setPlaintiff(String plaintiff) {
			this.plaintiff = plaintiff;
		}

		public String getBeneficiary() {
			return beneficiary;
		}

		public void setBeneficiary(String beneficiary) {
			this.beneficiary = beneficiary;
		}

		public String getSitus() {
			return situs;
		}

		public void setSitus(String situs) {
			this.situs = situs;
		}

		public String getTrustee() {
			return trustee;
		}

		public void setTrustee(String trustee) {
			this.trustee = trustee;
		}

		public String getCounty_code() {
			return county_code;
		}

		public void setCounty_code(String county_code) {
			this.county_code = county_code;
		}

		public String getCounty_name() {
			return county_name;
		}

		public void setCounty_name(String county_name) {
			this.county_name = county_name;
		}

		public String getCounty_state() {
			return county_state;
		}

		public void setCounty_state(String county_state) {
			this.county_state = county_state;
		}

		public String getCourt_code() {
			return court_code;
		}

		public void setCourt_code(String court_code) {
			this.court_code = court_code;
		}

		public String getCourt_desc() {
			return court_desc;
		}

		public void setCourt_desc(String court_desc) {
			this.court_desc = court_desc;
		}

		public String getCourt_state() {
			return court_state;
		}

		public void setCourt_state(String court_state) {
			this.court_state = court_state;
		}

		public String getAssets_available() {
			return assets_available;
		}

		public void setAssets_available(String assets_available) {
			this.assets_available = assets_available;
		}

		public String getPerfected_date() {
			return perfected_date;
		}

		public void setPerfected_date(String perfected_date) {
			this.perfected_date = perfected_date;
		}

		public String getAction_state_code() {
			return action_state_code;
		}

		public void setAction_state_code(String action_state_code) {
			this.action_state_code = action_state_code;
		}

		public String getAction_desc() {
			return action_desc;
		}

		public void setAction_desc(String action_desc) {
			this.action_desc = action_desc;
		}

		public String getDisposition_state_code() {
			return disposition_state_code;
		}

		public void setDisposition_state_code(String disposition_state_code) {
			this.disposition_state_code = disposition_state_code;
		}

		public String getDisposition_desc() {
			return disposition_desc;
		}

		public void setDisposition_desc(String disposition_desc) {
			this.disposition_desc = disposition_desc;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getRelease_date() {
			return release_date;
		}

		public void setRelease_date(String release_date) {
			this.release_date = release_date;
		}

		public String getRelease_number() {
			return release_number;
		}

		public void setRelease_number(String release_number) {
			this.release_number = release_number;
		}

		public String getSuit_case_number() {
			return suit_case_number;
		}

		public void setSuit_case_number(String suit_case_number) {
			this.suit_case_number = suit_case_number;
		}

		public String getSuit_date() {
			return suit_date;
		}

		public void setSuit_date(String suit_date) {
			this.suit_date = suit_date;
		}

		public String getSuit_amount() {
			return suit_amount;
		}

		public void setSuit_amount(String suit_amount) {
			this.suit_amount = suit_amount;
		}

		public String getSatisfaction_date() {
			return satisfaction_date;
		}

		public void setSatisfaction_date(String satisfaction_date) {
			this.satisfaction_date = satisfaction_date;
		}

		public String getDischarge_date() {
			return discharge_date;
		}

		public void setDischarge_date(String discharge_date) {
			this.discharge_date = discharge_date;
		}

		public String getClosed_date() {
			return closed_date;
		}

		public void setClosed_date(String closed_date) {
			this.closed_date = closed_date;
		}

		public String getTrust_deed_number() {
			return trust_deed_number;
		}

		public void setTrust_deed_number(String trust_deed_number) {
			this.trust_deed_number = trust_deed_number;
		}

		public String getTrust_deed_date() {
			return trust_deed_date;
		}

		public void setTrust_deed_date(String trust_deed_date) {
			this.trust_deed_date = trust_deed_date;
		}

		public String getSale_number() {
			return sale_number;
		}

		public void setSale_number(String sale_number) {
			this.sale_number = sale_number;
		}

		public String getSale_date() {
			return sale_date;
		}

		public void setSale_date(String sale_date) {
			this.sale_date = sale_date;
		}

		public String getCancellation_number() {
			return cancellation_number;
		}

		public void setCancellation_number(String cancellation_number) {
			this.cancellation_number = cancellation_number;
		}

		public String getCancellation_date() {
			return cancellation_date;
		}

		public void setCancellation_date(String cancellation_date) {
			this.cancellation_date = cancellation_date;
		}

		public String getSched_341_date() {
			return sched_341_date;
		}

		public void setSched_341_date(String sched_341_date) {
			this.sched_341_date = sched_341_date;
		}

		public String getUpdate_date() {
			return update_date;
		}

		public void setUpdate_date(String update_date) {
			this.update_date = update_date;
		}
		
		
	}
}
