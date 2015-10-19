package springapp.domain.RealProperty;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="realprop_response")
public class RealPropResponseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** Real Property Fields*/
	@Id
	@GeneratedValue
	@Column(name="result_id")
	private long result_id;
    
    /** The value for the user_search_id field */
	@Column(name="user_search_id")
    private long user_search_id;
      
    /** The value for the houseno field */
	@Column(name="houseno")
    private String houseno;      

	@Column(name="street")
    private String street;
	
    /** The value for the defendantCity field */
	@Column(name="city")
    private String city;
      
    /** The value for the defendantState field */
	@Column(name="state")
    private String state;
    
    /** The value for the name field */
	@Column(name="phoneno")
    private String phoneno;
      
    /** The value for the defendant field */
	@Column(name="apn")
    private String apn;
      
    /** The value for the defendantAddress field */
	@Column(name="fipscode")
    private String fipscode;
	
	@Column(name="municipalcode")
    private String municipalcode;
	
	@Column(name="accountnumber")
    private String accountnumber;
	
	@Column(name="primaryowner")
    private String primaryowner;
	
	@Column(name="secondaryowner")
    private String secondaryowner;
	
	@Column(name="housenosuffix")
    private String housenosuffix;
	
	@Column(name="direction")
    private String direction;
	
	@Column(name="mode")
    private String mode;
  
	@Column(name="aptno")
    private String aptno;
  
	
	@Column(name="zip")
    private String zip;
  
	@Column(name="ownerhouseno")
    private String ownerhouseno;
  
	@Column(name="ownerdirection")
    private String ownerdirection;
  
	@Column(name="ownerstreet")
    private String ownerstreet;
  
	@Column(name="ownermode")
    private String ownermode;
  
	@Column(name="ownercity")
    private String ownercity;
  
	@Column(name="ownerstate")
    private String ownerstate;
	
	@Column(name="owneraptno")
    private String owneraptno;
	
	@Column(name="ownerzip")
    private String ownerzip;
	
	@Column(name="saleprice")
    private String saleprice;

	@Column(name="daterecorded")
    private String daterecorded;
	
	@Column(name="saledate")
    private String saledate;
	
	@Column(name="calculatedvalue")
    private String calculatedvalue;
	
	
	
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
		

	public String getHouseno() {
		return houseno;
	}

	public void setHouseno(String houseno) {
		this.houseno = houseno;
	}


	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	
	public String getApn() {
		return apn;
	}
	public void setApn(String apn) {
		this.apn =apn ;
	}
	
	public String getFipscode() {
		return fipscode;
	}
	public void setFipscode(String fipscode) {
		this.fipscode = fipscode;
	}

	
	public String getMunicipalcode() {
		return municipalcode;
	}
	public void setMunicipalcode(String municipalcode) {
		this.municipalcode = municipalcode;
	}

	
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	
	public String getPrimaryowner() {
		return primaryowner;
	}
	public void setPrimaryowner(String primaryowner) {
		this.primaryowner = primaryowner;
	}

	
	public String getSecondaryowner() {
		return secondaryowner;
	}
	public void setSecondaryowner(String secondaryowner) {
		this.secondaryowner= secondaryowner;
	}

	public String getHousenosuffix() {
		return housenosuffix;
	}
	public void setHousenosuffix(String housenosuffix) {
		this.housenosuffix = housenosuffix;
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getAptno() {
		return aptno;
	}
	public void setAptno(String aptno) {
		this.aptno = aptno;
	}
	

	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	

	public String getOwnerhouseno() {
		return ownerhouseno;
	}
	public void setOwnerhouseno(String ownerhouseno) {
		this.ownerhouseno = ownerhouseno;
	}
	

	public String getOwnerdirection() {
		return ownerdirection;
	}
	public void setOwnerdirection(String ownerdirection) {
		this.ownerdirection = ownerdirection;
	}
	

	public String getOwnerstreet() {
		return ownerstreet;
	}
	public void setOwnerstreet(String ownerstreet) {
		this.ownerstreet =ownerstreet ;
	}


	public String getOwnermode() {
		return ownermode;
	}
	public void setOwnermode(String ownermode) {
		this.ownermode = ownermode;
	}

	public String getOwnercity() {
		return ownercity;
	}
	public void setOwnercity(String ownercity) {
		this.ownercity = ownercity;
	}

	public String getOwnerstate() {
		return ownerstate;
	}
	public void setOwnerstate(String ownerstate) {
		this.ownerstate = ownerstate;
	}

	public String getOwneraptno() {
		return owneraptno;
	}
	public void setOwneraptno(String owneraptno) {
		this.owneraptno = owneraptno;
	}


	public String getOwnerzip() {
		return ownerzip;
	}
	public void setOwnerzip(String ownerzip) {
		this.ownerzip = ownerzip;
	}
	

	public String getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(String saleprice) {
		this.saleprice =saleprice ;
	}
  

	public String getDaterecorded() {
		return daterecorded;
	}
	public void setDaterecorded(String daterecorded) {
		this.daterecorded =daterecorded ;
	}
	

	public String getSaledate() {
		return saledate;
	}
	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}
	

	public String getCalculatedvalue() {
		return calculatedvalue;
	}
	public void setCalculatedvalue(String calculatedvalue) {
		this.calculatedvalue = calculatedvalue;
	}

}
