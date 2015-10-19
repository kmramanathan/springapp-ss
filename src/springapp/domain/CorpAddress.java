package springapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name="corp_address")
public class CorpAddress {
/** Corporation address */
	
	@Id
	@GeneratedValue
	private long id;
	private long user_search_id;
	private String filing_number;
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
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getA_address1() {
		return a_address1;
	}
	public void setA_address1(String a_address1) {
		this.a_address1 = a_address1;
	}
	public String getA_address2() {
		return a_address2;
	}
	public void setA_address2(String a_address2) {
		this.a_address2 = a_address2;
	}
	public String getA_address3() {
		return a_address3;
	}
	public void setA_address3(String a_address3) {
		this.a_address3 = a_address3;
	}
	public String getA_city() {
		return a_city;
	}
	public void setA_city(String a_city) {
		this.a_city = a_city;
	}
	public String getA_state() {
		return a_state;
	}
	public void setA_state(String a_state) {
		this.a_state = a_state;
	}
	public String getA_zip_code() {
		return a_zip_code;
	}
	public void setA_zip_code(String a_zip_code) {
		this.a_zip_code = a_zip_code;
	}
	public String getA_address_type() {
		return a_address_type;
	}
	public void setA_address_type(String a_address_type) {
		this.a_address_type = a_address_type;
	}
	public String getA_current_address() {
		return a_current_address;
	}
	public void setA_current_address(String a_current_address) {
		this.a_current_address = a_current_address;
	}
	public String getA_effective_date() {
		return a_effective_date;
	}
	public void setA_effective_date(String a_effective_date) {
		this.a_effective_date = a_effective_date;
	}
	public String getA_parsed_flag() {
		return a_parsed_flag;
	}
	public void setA_parsed_flag(String a_parsed_flag) {
		this.a_parsed_flag = a_parsed_flag;
	}
	
	

}
