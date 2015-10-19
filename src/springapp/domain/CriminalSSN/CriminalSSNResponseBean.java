package springapp.domain.CriminalSSN;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="criminal_ssn_response")
public class CriminalSSNResponseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** criminal_ssn_response Fields*/
	@Id
	@GeneratedValue
	@Column(name="result_id")
	private long result_id;
    
    /** The value for the user_search_id field */
	@Column(name="user_search_id")
    private long user_search_id;
      
    /** The value for the firstname field */
	@Column(name="firstname")
    private String firstname;      

	@Column(name="lastname")
    private String lastname;
	      
    /** The value for the defendant field */
	@Column(name="middlename")
    private String middlename;
      
	/** The value for the streetnumber field */
	@Column(name="streetnumber")
    private String streetnumber;	
	
    /** The value for the defendantAddress field */
	@Column(name="streetname")
    private String streetname;	

    /** The value for the defendantCity field */
	@Column(name="city")
    private String city;
      
    /** The value for the defendantState field */
	@Column(name="state")
    private String state;
    
    /** The value for the name field */
	@Column(name="county")
    private String county;
	

	@Column(name="postalcode")
    private String postalcode;
	
	@Column(name="datefirstseen")
    private String datefirstseen;
	
	@Column(name="datelastseen")
    private String datelastseen;
	
	@Column(name="phoneinfo")
    private String phoneinfo;
	
	@Column(name="aliasnames")
    private String aliasnames;
	/*******************************************************/
	
	@Column(name="housenosuffix")
    private String housenosuffix;
	
	@Column(name="direction")
    private String direction;
	
	@Column(name="mode")
    private String mode;
  
	@Column(name="aptno")
    private String aptno;
  	
  
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
		

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename =middlename ;
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

	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}	
	
	public String getStreetnumber() {
		return streetnumber;
	}
	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
	}
	
	public String getStreetname() {
		return streetname;
	}
	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	
	public String getDatefirstseen() {
		return datefirstseen;
	}
	public void setDatefirstseen(String datefirstseen) {
		this.datefirstseen = datefirstseen;
	}

	
	public String getDatelastseen() {
		return datelastseen;
	}
	public void setDatelastseen(String datelastseen) {
		this.datelastseen = datelastseen;
	}
	
	public String getPhoneinfo() {
		return phoneinfo;
	}
	public void setPhoneinfo(String phoneinfo) {
		this.phoneinfo = phoneinfo;
	}
	
	public String getAliasnames() {
		return aliasnames;
	}
	public void setAliasnames(String aliasnames) {
		this.aliasnames= aliasnames;
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
	

	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
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
