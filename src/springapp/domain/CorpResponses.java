package springapp.domain;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="corp_responses")
public class CorpResponses {
	 //Corporations response data
	/** Sos Corp fields*/
	@Id
	@GeneratedValue
	@Column(name="result_id")
	private long result_id;
	@Column(name="user_search_id")
	private long user_search_id;
	@Column(name="filing_number")
	private String filing_number;
	@Column(name="filing_date")
	private String filing_date;
	@Column(name="filing_state")
	private String filing_state;
	@Column(name="state")
	private String state;
	@Column(name="corporation_id")
	private String corporation_id;
	@Column(name="corporation_name")
	private String corporation_name;
	@Column(name="incorp_date")
	private String incorp_date;
	@Column(name="corporation_status")
	private String corporation_status;
	@Column(name="corporation_status_date")
	private String corporation_status_date;
	@Column(name="corporation_type")
	private String corporation_type;
	@Column(name="bus_type")
	private String bus_type;
	@Column(name="originated_state")
	private String originated_state;
	@Column(name="county")
	private String county;
	@Column(name="federal_tax_id")
	private String federal_tax_id;
	@Column(name="duration_date")
	private String duration_date;
	
	/** corporation_name_information */
	@Column(name="corporation_name_type_desc")
	private String corporation_name_type_desc;
	@Column(name="cn_corporation_name1")
	private String cn_corporation_name1;
	@Column(name="cn_effective_date")
	private String cn_effective_date;

	

	
	
	
	public long getResult_id() {
		return result_id;
	}
	public void setResult_id(long result_id) {
		this.result_id = result_id;
	}
	public long getUser_search_id() {
		return user_search_id;
	}
	public void setUser_search_id(long user_search_id) {
		this.user_search_id = user_search_id;
	}
	public String getFiling_number() {
		return filing_number;
	}
	public void setFiling_number(String filing_number) {
		this.filing_number = filing_number;
	}
	public String getFiling_date() {
		return filing_date;
	}
	public void setFiling_date(String filing_date) {
		this.filing_date = filing_date;
	}
	public String getFiling_state() {
		return filing_state;
	}
	public void setFiling_state(String filing_state) {
		this.filing_state = filing_state;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCorporation_id() {
		return corporation_id;
	}
	public void setCorporation_id(String corporation_id) {
		this.corporation_id = corporation_id;
	}
	public String getCorporation_name() {
		return corporation_name;
	}
	public void setCorporation_name(String corporation_name) {
		this.corporation_name = corporation_name;
	}
	public String getIncorp_date() {
		return incorp_date;
	}
	public void setIncorp_date(String incorp_date) {
		this.incorp_date = incorp_date;
	}
	public String getCorporation_status() {
		return corporation_status;
	}
	public void setCorporation_status(String corporation_status) {
		this.corporation_status = corporation_status;
	}
	public String getCorporation_status_date() {
		return corporation_status_date;
	}
	public void setCorporation_status_date(String corporation_status_date) {
		this.corporation_status_date = corporation_status_date;
	}
	public String getCorporation_type() {
		return corporation_type;
	}
	public void setCorporation_type(String corporation_type) {
		this.corporation_type = corporation_type;
	}
	public String getBus_type() {
		return bus_type;
	}
	public void setBus_type(String bus_type) {
		this.bus_type = bus_type;
	}
	public String getOriginated_state() {
		return originated_state;
	}
	public void setOriginated_state(String originated_state) {
		this.originated_state = originated_state;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getFederal_tax_id() {
		return federal_tax_id;
	}
	public void setFederal_tax_id(String federal_tax_id) {
		this.federal_tax_id = federal_tax_id;
	}
	public String getDuration_date() {
		return duration_date;
	}
	public void setDuration_date(String duration_date) {
		this.duration_date = duration_date;
	}
	public String getCorporation_name_type_desc() {
		return corporation_name_type_desc;
	}
	public void setCorporation_name_type_desc(String corporation_name_type_desc) {
		this.corporation_name_type_desc = corporation_name_type_desc;
	}
	public String getCn_corporation_name1() {
		return cn_corporation_name1;
	}
	public void setCn_corporation_name1(String cn_corporation_name1) {
		this.cn_corporation_name1 = cn_corporation_name1;
	}
	public String getCn_effective_date() {
		return cn_effective_date;
	}
	public void setCn_effective_date(String cn_effective_date) {
		this.cn_effective_date = cn_effective_date;
	}
	
	
	
	
	
	
}
