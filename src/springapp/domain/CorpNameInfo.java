package springapp.domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="corp_name_address")
public class CorpNameInfo {
	
	/** corporation name_information */
	@Id
	@GeneratedValue
	private long id;
	private long user_search_id;
	private String filing_number;
	private String name;
	private String name_type;
	private String current_active;
	
	private String na_address1;
	private String na_address2;
	private String na_address3;
	private String na_city;
	private String na_state;
	private String na_zip_code;
	private String na_address_type;
	private String na_current_address;
	private String na_parsed_flag;
	

	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName_type() {
		return name_type;
	}
	public void setName_type(String name_type) {
		this.name_type = name_type;
	}
	public String getCurrent_active() {
		return current_active;
	}
	public void setCurrent_active(String current_active) {
		this.current_active = current_active;
	}
	
	public String getNa_address1() {
		return na_address1;
	}
	public void setNa_address1(String na_address1) {
		this.na_address1 = na_address1;
	}
	public String getNa_address2() {
		return na_address2;
	}
	public void setNa_address2(String na_address2) {
		this.na_address2 = na_address2;
	}
	public String getNa_address3() {
		return na_address3;
	}
	public void setNa_address3(String na_address3) {
		this.na_address3 = na_address3;
	}
	public String getNa_city() {
		return na_city;
	}
	public void setNa_city(String na_city) {
		this.na_city = na_city;
	}
	public String getNa_state() {
		return na_state;
	}
	public void setNa_state(String na_state) {
		this.na_state = na_state;
	}
	public String getNa_zip_code() {
		return na_zip_code;
	}
	public void setNa_zip_code(String na_zip_code) {
		this.na_zip_code = na_zip_code;
	}
	public String getNa_address_type() {
		return na_address_type;
	}
	public void setNa_address_type(String na_address_type) {
		this.na_address_type = na_address_type;
	}
	public String getNa_current_address() {
		return na_current_address;
	}
	public void setNa_current_address(String na_current_address) {
		this.na_current_address = na_current_address;
	}
	public String getNa_parsed_flag() {
		return na_parsed_flag;
	}
	public void setNa_parsed_flag(String na_parsed_flag) {
		this.na_parsed_flag = na_parsed_flag;
	}
	
	
	
	

}
