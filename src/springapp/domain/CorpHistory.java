package springapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name="corp_history")
public class CorpHistory {
/** corp_history*/
	@Id
	@GeneratedValue
	private long id;
	private long user_search_id;;
	private String filing_number;
	private String info_title;
	private String info_desc;
	private String history_pages;
	private String history_code;
	private String history_desc;
	private String effective_date;
	private String history_num;
	private String history_name;
	private String locator_num;
	
	
	
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
	
	public String getInfo_title() {
		return info_title;
	}
	public void setInfo_title(String info_title) {
		this.info_title = info_title;
	}
	public String getInfo_desc() {
		return info_desc;
	}
	public void setInfo_desc(String info_desc) {
		this.info_desc = info_desc;
	}
	public String getHistory_pages() {
		return history_pages;
	}
	public void setHistory_pages(String history_pages) {
		this.history_pages = history_pages;
	}
	public String getHistory_code() {
		return history_code;
	}
	public void setHistory_code(String history_code) {
		this.history_code = history_code;
	}
	public String getHistory_desc() {
		return history_desc;
	}
	public void setHistory_desc(String history_desc) {
		this.history_desc = history_desc;
	}
	public String getEffective_date() {
		return effective_date;
	}
	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
	}
	public String getHistory_num() {
		return history_num;
	}
	public void setHistory_num(String history_num) {
		this.history_num = history_num;
	}
	public String getHistory_name() {
		return history_name;
	}
	public void setHistory_name(String history_name) {
		this.history_name = history_name;
	}
	public String getLocator_num() {
		return locator_num;
	}
	public void setLocator_num(String locator_num) {
		this.locator_num = locator_num;
	}
	
	

}
