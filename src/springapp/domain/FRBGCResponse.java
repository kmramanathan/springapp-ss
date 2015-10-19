package springapp.domain;

import java.io.Serializable;

public class FRBGCResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//offender detail
	private String id;
	private String lastName;
	private String firstName;
	private String middleName;
	private String dob;
	private String address;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String hairColor;
	private String eyeColor;
	private String height;
	private String weight;
	private String race;
	private String sex;
	private String county;
	private String sourceName;
	private String sourceState;
	private String offenseCode;
	
	//Alias
	private String aliases;
	
	
	//offense detail
	private String offensedescription1;
	private String offensedescription2;
	private String disposition;
	private String dispositiondate;
	private String arrestingAgency;
	private String sentenceYYYMMDDD;
	private String probationYYYMMDDD;
	private String offenseDate;
	private String plea;
	private String casenumber;
	private String fines;
	
	public FRBGCResponse() { }
	
	public FRBGCResponse(String id, String lastName, String firstName, String middleName,
			String dob, String address, String address2, String city,
			String state, String zip, String hairColor, String eyeColor,
			String height, String weight, String race, String sex,
			String county, String sourceName, String sourceState,
			String aliases, String offensedescription1,
			String offensedescription2, String disposition,
			String dispositiondate, String arrestingAgency,
			String sentenceYYYMMDDD, String probationYYYMMDDD,
			String offenseDate, String plea, String casenumber, String fines, String offenseCode) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.dob = dob;
		this.address = address;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.hairColor = hairColor;
		this.eyeColor = eyeColor;
		this.height = height;
		this.weight = weight;
		this.race = race;
		this.sex = sex;
		this.county = county;
		this.sourceName = sourceName;
		this.sourceState = sourceState;
		this.aliases = aliases;
		this.offensedescription1 = offensedescription1;
		this.offensedescription2 = offensedescription2;
		this.disposition = disposition;
		this.dispositiondate = dispositiondate;
		this.arrestingAgency = arrestingAgency;
		this.sentenceYYYMMDDD = sentenceYYYMMDDD;
		this.probationYYYMMDDD = probationYYYMMDDD;
		this.offenseDate = offenseDate;
		this.plea = plea;
		this.casenumber = casenumber;
		this.fines = fines;
		this.offenseCode = offenseCode;
	}

	
	public String getOffenseCode() {
		return offenseCode;
	}

	public void setOffenseCode(String offenseCode) {
		this.offenseCode = offenseCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
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
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getHairColor() {
		return hairColor;
	}
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	public String getEyeColor() {
		return eyeColor;
	}
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getSourceState() {
		return sourceState;
	}
	public void setSourceState(String sourceState) {
		this.sourceState = sourceState;
	}
	public String getAliases() {
		return aliases;
	}
	public void setAliases(String aliases) {
		this.aliases = aliases;
	}
	public String getOffensedescription1() {
		return offensedescription1;
	}
	public void setOffensedescription1(String offensedescription1) {
		this.offensedescription1 = offensedescription1;
	}
	public String getOffensedescription2() {
		return offensedescription2;
	}
	public void setOffensedescription2(String offensedescription2) {
		this.offensedescription2 = offensedescription2;
	}
	public String getDisposition() {
		return disposition;
	}
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	public String getDispositiondate() {
		return dispositiondate;
	}
	public void setDispositiondate(String dispositiondate) {
		this.dispositiondate = dispositiondate;
	}
	public String getArrestingAgency() {
		return arrestingAgency;
	}
	public void setArrestingAgency(String arrestingAgency) {
		this.arrestingAgency = arrestingAgency;
	}
	public String getSentenceYYYMMDDD() {
		return sentenceYYYMMDDD;
	}
	public void setSentenceYYYMMDDD(String sentenceYYYMMDDD) {
		this.sentenceYYYMMDDD = sentenceYYYMMDDD;
	}
	public String getProbationYYYMMDDD() {
		return probationYYYMMDDD;
	}
	public void setProbationYYYMMDDD(String probationYYYMMDDD) {
		this.probationYYYMMDDD = probationYYYMMDDD;
	}
	public String getOffenseDate() {
		return offenseDate;
	}
	public void setOffenseDate(String offenseDate) {
		this.offenseDate = offenseDate;
	}
	public String getPlea() {
		return plea;
	}
	public void setPlea(String plea) {
		this.plea = plea;
	}
	public String getCasenumber() {
		return casenumber;
	}
	public void setCasenumber(String casenumber) {
		this.casenumber = casenumber;
	}
	public String getFines() {
		return fines;
	}
	public void setFines(String fines) {
		this.fines = fines;
	}
	
}
