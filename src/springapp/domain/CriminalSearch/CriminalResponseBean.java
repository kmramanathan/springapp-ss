package springapp.domain.CriminalSearch;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="criminal_response")
public class CriminalResponseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** DDN Property Fields*/
	@Id
	@GeneratedValue
	@Column(name="result_id")
	private long result_id;
    
    /** The value for the user_search_id field */
	@Column(name="user_search_id")
    private long user_search_id;
    
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Column(name="MiddleName")
	private String middleName;
	
	@Column(name="DOB")
	private String DOB;
	
	@Column(name="Sex")
	private String sex;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="VerifiedBy")
	private String verifiedBy;
	
	@Column(name="Offense")
	private String offense;
	
	@Column(name="CaseNum")
	private String caseNum;	
	
	@Column(name="FileDate")
	private String fileDate;
	
	@Column(name="Disposition")
	private String disposition;
	
	@Column(name="DispositionDate")
	private String dispositionDate;
	
	@Column(name="OffenseCounty")
	private String offenseCounty;	

	@Column(name="SourceofRecord")
	private String sourceofRecord;
	
	//private String ;
	@Column(name="Citation")
	private String citation;	
	
	@Column(name="OffnsCount")
	private String offnsCount;
	
	@Column(name="CourtNumber")
	private String courtNumber;
	
	@Column(name="OffnsDLState")
	private String offnsDLState;
	
	@Column(name="CaseType")
	private String caseType;
	
	@Column(name="CourtAddress")
	private String courtAddress;
	
	@Column(name="CourtName")
	private String courtName;
	
	@Column(name="FacilityCounty")
	private String facilityCounty;
	
	@Column(name="MatchType")
	private String matchType;
	
	
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String DOB) {
		this.DOB = DOB;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
		
	public String getVerifiedBy() {
		return verifiedBy;
	}
	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}
	public String getOffense() {
		return offense;
	}
	public void setOffense(String offense) {
		this.offense = offense;
	}
	
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}	
	
	public String getFileDate() {
		return fileDate;
	}
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
	
	public String getDisposition() {
		return disposition;
	}
	public void setDisposition(String disposition)
	{
		this.disposition = disposition;
	}
	public String getDispositionDate() {
		return dispositionDate;
	}
	public void setDispositionDate(String dispositionDate)
	{
		this.dispositionDate = dispositionDate;
	}
	

	public String getOffenseCounty() {
		return offenseCounty;
	}
	public void setOffenseCounty(String offenseCounty) {
		this.offenseCounty = offenseCounty;
	}
	
	public String getSourceofRecord() {
		return sourceofRecord;
	}
	public void setSourceofRecord(String sourceofRecord) {
		this.sourceofRecord = sourceofRecord;
	}
	
	public String getCitation() {
		return citation;
	}
	public void setCitation(String citation) {
		this.citation = citation;
	}
	
	public String getOffnsCount() {
		return offnsCount;
	}
	public void setOffnsCount(String offnsCount) {
		this.offnsCount = offnsCount;
	}
	public String getCourtNumber() {
		return courtNumber;
	}
	public void setCourtNumber(String courtNumber) {
		this.courtNumber = courtNumber;
	}
	public String getOffnsDLState() {
		return offnsDLState;
	}
	public void setOffnsDLState(String offnsDLState) {
		this.offnsDLState = offnsDLState;
	}
	
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getCourtAddress() {
		return courtAddress;
	}
	public void setCourtAddress(String courtAddress) {
		this.courtAddress = courtAddress;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	 
	public String getFacilityCounty() {
		return facilityCounty;
	}
	public void setFacilityCounty(String facilityCounty) {
		this.facilityCounty = facilityCounty;
	}
		
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
		

}
