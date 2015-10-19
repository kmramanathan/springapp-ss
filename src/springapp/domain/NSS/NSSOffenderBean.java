package springapp.domain.NSS;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="nss_offenders")
public class NSSOffenderBean {
	
	 /** The value for the bgcOffenderId field */
	@Id
	@GeneratedValue
	@Column(name="nss_offender_id")
    private int nssOffenderId;
  
    /** The value for the bgcResponseId field */
	@Column(name="nss_response_id")
    private int nssResponseId;
  
    /** The value for the hashKey field */
	@Column(name="hash_key")
    private String hashKey;
  
    /** The value for the fullName field */
	@Column(name="full_name")
    private String fullName;
  
    /** The value for the gender field */
	@Column(name="gender")
    private String gender;
  
    /** The value for the race field */
	@Column(name="race")
    private String race;
  
    /** The value for the heightFeet field */
	@Column(name="height_feet")
    private String heightFeet;
  
    /** The value for the heightInches field */
	@Column(name="height_inches")
    private String heightInches;
  
    /** The value for the weight field */
	@Column(name="weight")
    private String weight;
  
    /** The value for the hairColor field */
	@Column(name="hair_color")
    private String hairColor;
  
    /** The value for the eyeColor field */
	@Column(name="eye_color")
    private String eyeColor;
  
    /** The value for the dateOfBirth field */
	@Column(name="date_of_birth")
    private String dateOfBirth;
  
    /** The value for the dobType field */
	@Column(name="dob_type")
    private int dobType;
  
    /** The value for the imageUrl field */
	@Column(name="image_url")
    private String imageUrl;
  
    /** The value for the city field */
	@Column(name="city")
    private String city;
  
    /** The value for the street field */
	@Column(name="street")
    private String street;
  
    /** The value for the postalCode field */
	@Column(name="postal_code")
    private String postalCode;
  
    /** The value for the state field */
	@Column(name="state")
    private String state;
  
    /** The value for the county field */
	@Column(name="county")
    private String county;
  
    /** The value for the country field */
	@Column(name="country")
    private String country;
  
    /** The value for the provider field */
	@Column(name="provider")
    private String provider;
  
    /** The value for the jurisdiction field */
	@Column(name="jurisdiction")
    private String jurisdiction;
  
    /** The value for the realNameMatch field */
	 @Column(name="real_name_match")
    private String realNameMatch;
  
    /** The value for the dobMatch field */
    @Column(name="dob_match")
    private String dobMatch;
  
    /** The value for the akaMatch field */
    @Column(name="aka_match")
    private String akaMatch;
  
    /** The value for the recordOffenderId field */
    @Column(name="record_offender_id")
    private String recordOffenderId;
  
    /** The value for the recordState field */
    @Column(name="record_state")
    private String recordState;
  
    /** The value for the recordSource field */
    @Column(name="record_source")
    private String recordSource;
  
    /** The value for the recordSecureKey field */
    @Column(name="record_secure_key")
    private String recordSecureKey;
  
    /** The value for the hasDetail field */
    @Column(name="has_detail")
    private boolean hasDetail;



	public int getNssOffenderId() {
		return nssOffenderId;
	}

	public void setNssOffenderId(int v) {
		if(this.nssOffenderId != v)
		{
			this.nssOffenderId = v;
		}
		
	}

	public int getNssResponseId() {
		return nssResponseId;
	}

	public void setNssResponseId(int v) {
		if(this.nssResponseId != v)
		{
			this.nssResponseId = v;
		}
		
	}

	

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		if(this.hashKey != hashKey)
			{
			this.hashKey = hashKey;
			}
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String v) {
		if(this.fullName != v)
		{
		this.fullName = v;
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String v) {
		if(this.gender != v){
			this.gender = v;
		}
		
	}

	public String getRace() {
		return race;
	}

	public void setRace(String v) {
		if(this.race != v)
		{
			this.race = v;
		}
		
	}

	public String getHeightFeet() {
		return heightFeet;
	}

	public void setHeightFeet(String v) {
		if(this.heightFeet != v)
		{
		this.heightFeet = v;
		}
	}

	public String getHeightInches() {
		return heightInches;
	}

	public void setHeightInches(String v) {
		if(this.heightInches != v)
		{
		this.heightInches = v;
		}
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String v) {
		if(this.weight != v)
		{
		this.weight = v;
		}
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String v) {
		if(this.hairColor != v)
		{
		this.hairColor = v;
		}
	}

	public String getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(String v) {
		if(this.eyeColor != v)
		{
		this.eyeColor = v;
		}
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String v) {
		if(this.dateOfBirth != v)
		{
		this.dateOfBirth = v;
		}
	}

	public int getDobType() {
		return dobType;
	}

	public void setDobType(int v) {
		if(this.dobType != v)
		{
		this.dobType = v;
		}
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String v) {
		if(this.imageUrl != v)
		{
		this.imageUrl = v;
		}
	}

	public String getCity() {
		return city;
	}

	public void setCity(String v) {
		if(this.city != v)
		{
		this.city = v;
		}
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String v) {
		if(this.street != v)
		{
		this.street = v;
		}
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String v) {
		if(this.postalCode != v)
		{
		this.postalCode = v;
		}
	}

	public String getState() {
		return state;
	}

	public void setState(String v) {
		if(this.state != v)
		{
		this.state = v;
		}
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String v) {
		if(this.county != v)
		{
			this.county = v;
		}
		
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String v) {
		if(this.country != v)
		{
		this.country = v;
		}
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String v) {
		if(this.provider != v)
		{
		this.provider = v;
		}
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String v) {
		if(this.jurisdiction != v)
		{
		this.jurisdiction = v;
		}
	}

	public String getRealNameMatch() {
		return realNameMatch;
	}

	public void setRealNameMatch(String v) {
		if(this.realNameMatch != v){
			this.realNameMatch = v;
		}
		
	}

	public String getDobMatch() {
		return dobMatch;
	}

	public void setDobMatch(String v) {
		if(this.dobMatch != v)
		{
		this.dobMatch = v;
		}
	}

	public String getAkaMatch() {
		return akaMatch;
	}

	public void setAkaMatch(String v) {
		if(this.akaMatch != v)
		{
		this.akaMatch = v;
		}
	}

	public String getRecordOffenderId() {
		return recordOffenderId;
	}

	public void setRecordOffenderId(String v) {
		if(this.recordOffenderId != v)
		{
		this.recordOffenderId = v;
		}
	}

	public String getRecordState() {
		return recordState;
	}

	public void setRecordState(String v) {
		if(this.recordState != v){
			this.recordState = v;
		}
		
	}

	public String getRecordSource() {
		return recordSource;
	}

	public void setRecordSource(String v) {
		if(this.recordSource != v)
		{
		this.recordSource = v;
		}
	}

	public String getRecordSecureKey() {
		return recordSecureKey;
	}

	public void setRecordSecureKey(String v) {
		if(this.recordSecureKey != v)
		{
		this.recordSecureKey = v;
		}
	}

	public boolean isHasDetail() {
		return hasDetail;
	}

	public void setHasDetail(boolean v) {
		if(this.hasDetail != v)
		{
		this.hasDetail = v;
		}
	}


    
    
}
