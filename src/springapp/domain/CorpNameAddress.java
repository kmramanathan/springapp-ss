package springapp.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


public class CorpNameAddress {
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
