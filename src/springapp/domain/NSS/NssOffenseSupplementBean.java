package springapp.domain.NSS;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nss_offense_supplements")
public class NssOffenseSupplementBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** The value for the bgcOffenseSupplementId field */
	@Id
	@GeneratedValue
	@Column(name="nss_offense_supplement_id")
    private int nssOffenseSupplementId;
  
    /** The value for the bgcOffenseId field */
	@Column(name="nss_offense_id")
    private int nssOffenseId;
  
    /** The value for the hashKey field */
	@Column(name="hash_key")
    private String hashKey;
  
    /** The value for the displayTitle field */
	@Column(name="display_title")
    private String displayTitle;
  
    /** The value for the displayValue field */
	@Column(name="display_value")
    private String displayValue;

	

	public int getNssOffenseSupplementId() {
		return nssOffenseSupplementId;
	}

	public void setNssOffenseSupplementId(int nssOffenseSupplementId) {
		this.nssOffenseSupplementId = nssOffenseSupplementId;
	}

	public int getNssOffenseId() {
		return nssOffenseId;
	}

	public void setNssOffenseId(int nssOffenseId) {
		this.nssOffenseId = nssOffenseId;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public String getDisplayTitle() {
		return displayTitle;
	}

	public void setDisplayTitle(String displayTitle) {
		this.displayTitle = displayTitle;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}
    
    
    

}
