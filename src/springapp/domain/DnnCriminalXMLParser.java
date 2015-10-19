package springapp.domain;

import java.io.Serializable;

public class DnnCriminalXMLParser implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private boolean modified=true;
	private boolean isNew = true;
	
	//DnnCriminalXMLParser Search Response data
	/** case fields*/
	private String FirstName;
	private String LastName;
	private String MiddleName;
	private String DOB;	
	private String Sex;
	private String Address;
	private String VerifiedBy;
	private String Offense;
	private String CaseNum;	
	private String FileDate;
	private String Disposition;
	private String DispositionDate;
	private String OffenseCounty;
	private String SourceofRecord;
	private String Citation;	
	private String OffnsCount;
	private String CourtNumber;
	private String OffnsDLState;
	private String CaseType;
	private String CourtAddress;
	private String CourtName;
	private String ownerLastName;
	private String FacilityCounty;
	//
	private String ownerMiddleName;
	private String ownerDOB;
	private String ownerOffnsDLState;
	private String ownerCaseType;
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
	
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String LastName) {
		this.LastName = LastName;
	}
	
	public String getMiddleName() {
		return MiddleName;
	}
	public void setMiddleName(String MiddleName) {
		this.MiddleName = MiddleName;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String DOB) {
		this.DOB = DOB;
	}
	
	public String getSex() {
		return Sex;
	}
	public void setSex(String Sex) {
		this.Sex = Sex;
	}
	
	public String getAddress() {
		return Address;
	}
	public void setAddress(String Address) {
		this.Address = Address;
	}
		
	public String getVerifiedBy() {
		return VerifiedBy;
	}
	public void setVerifiedBy(String VerifiedBy) {
		this.VerifiedBy = VerifiedBy;
	}
	public String getOffense() {
		return Offense;
	}
	public void setOffense(String Offense) {
		this.Offense = Offense;
	}
	
	public String getCaseNum() {
		return CaseNum;
	}
	public void setCaseNum(String CaseNum) {
		this.CaseNum = CaseNum;
	}	
	
	public String getFileDate() {
		return FileDate;
	}
	public void setFileDate(String FileDate) {
		this.FileDate = FileDate;
	}
	
	public String getDisposition() {
		return Disposition;
	}
	public void setDisposition(String Disposition)
	{
		this.Disposition = Disposition;
	}
	
	public String getDispositionDate() {
		return DispositionDate;
	}
	public void setDispositionDate(String DispositionDate)
	{
		this.DispositionDate = DispositionDate;
	}

	public String getOffenseCounty() {
		return OffenseCounty;
	}
	public void setOffenseCounty(String OffenseCounty) {
		this.OffenseCounty = OffenseCounty;
	}
	public String getSourceofRecord() {
		return SourceofRecord;
	}
	public void setSourceofRecord(String SourceofRecord) {
		this.SourceofRecord = SourceofRecord;
	}
	//
	public String getCitation() {
		return Citation;
	}
	public void setCitation(String Citation) {
		this.Citation = Citation;
	}
	
	public String getOffnsCount() {
		return OffnsCount;
	}
	public void setOffnsCount(String OffnsCount) {
		this.OffnsCount = OffnsCount;
	}
	public String getCourtNumber() {
		return CourtNumber;
	}
	public void setCourtNumber(String CourtNumber) {
		this.CourtNumber = CourtNumber;
	}
	public String getOffnsDLState() {
		return OffnsDLState;
	}
	public void setOffnsDLState(String OffnsDLState) {
		this.OffnsDLState = OffnsDLState;
	}
	
	public String getCaseType() {
		return CaseType;
	}
	public void setCaseType(String CaseType) {
		this.CaseType = CaseType;
	}
	public String getCourtAddress() {
		return CourtAddress;
	}
	public void setCourtAddress(String CourtAddress) {
		this.CourtAddress = CourtAddress;
	}
	public String getCourtName() {
		return CourtName;
	}
	public void setCourtName(String CourtName) {
		this.CourtName = CourtName;
	}
	public String getOwnerLastName() {
		return ownerLastName;
	}
	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}
	public String getFacilityCounty() {
		return FacilityCounty;
	}
	public void setFacilityCounty(String FacilityCounty) {
		this.FacilityCounty = FacilityCounty;
	}
	public String getOwnerMiddleName() {
		return ownerMiddleName;
	}
	public void setOwnerMiddleName(String ownerMiddleName) {
		this.ownerMiddleName = ownerMiddleName;
	}
	public String getOwnerDOB() {
		return ownerDOB;
	}
	
	public void setOwnerDOB(String ownerDOB) {
		this.ownerDOB = ownerDOB;
	}
	public String getOwnerOffnsDLState() {
		return ownerOffnsDLState;
	}
	public void setOwnerOffnsDLState(String ownerOffnsDLState) {
		this.ownerOffnsDLState = ownerOffnsDLState;
	}
	public String getOwnerCaseType() {
		return ownerCaseType;
	}
	public void setOwnerCaseType(String ownerCaseType) {
		this.ownerCaseType = ownerCaseType;
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
	