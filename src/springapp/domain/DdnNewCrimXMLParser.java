package springapp.domain;

import java.io.Serializable;

public class DdnNewCrimXMLParser implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean modified=true;
	private boolean isNew = true;
	
	//Real Property Search Response data
	/** case fields*/
	private String houseno;
	private String street;
	private String city;
	private String state;	
	private String phoneno;
	private String apn;
	private String fipscode;
	private String municipalcode;
	private String accountnumber;	
	private String primaryowner;
	private String secondaryowner;
	private String housenosuffix;	
	private String direction;
	private String mode;
	private String aptno;
	private String zip;
	private String ownerhouseno;
	private String ownerdirection;
	private String ownerstreet;
	private String ownermode;
	private String ownercity;
	private String ownerstate;
	private String owneraptno;
	private String ownerzip;
	private String saleprice;
	private String daterecorded;
	private String saledate;	
	private String calculatedvalue;
	
	
	public void setModified(boolean modified) {
		this.modified = modified;
	}
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
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
		this.apn = apn;
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
	public void setSecondaryowner(String secondaryowner)
	{
		this.secondaryowner = secondaryowner;
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
		this.ownerstreet = ownerstreet;
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
		this.saleprice = saleprice;
	}
	
	public String getDaterecorded() {
		return daterecorded;
	}
	public void setDaterecorded(String daterecorded) {
		this.daterecorded = daterecorded;
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
	