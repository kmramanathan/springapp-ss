package springapp.domain.NSS;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nss_offenses")
public class NssOffenseBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The value for the bgcOffenseId field */
	@Id
	@GeneratedValue
	@Column(name="nss_offense_id")
    private int nssOffenseId;
  
    /** The value for the bgcOffenderId field */
	@Column(name="nss_offender_id")
    private int nssOffenderId;
  
    /** The value for the hashKey field */
	@Column(name="hash_key")
    private String hashKey;
  
    /** The value for the description field */
	@Column(name="description")
    private String description;
  
    /** The value for the disposition field */
	@Column(name="disposition")
    private String disposition;
  
    /** The value for the degreeOfOffense field */
	@Column(name="degree_of_offense")
    private String degreeOfOffense;
  
    /** The value for the sentence field */
	@Column(name="sentence")
    private String sentence;
  
    /** The value for the probation field */
	@Column(name="probation")
    private String probation;
  
    /** The value for the confinement field */
	@Column(name="confinement")
    private String confinement;
  
    /** The value for the arrestingAgency field */
	@Column(name="arresting_agency")
    private String arrestingAgency;
  
    /** The value for the originatingAgency field */
	@Column(name="originating_agency")
    private String originatingAgency;
  
    /** The value for the jurisdiction field */
	@Column(name="jurisdiction")
    private String jurisdiction;
  
    /** The value for the statute field */
	@Column(name="statute")
    private String statute;
  
    /** The value for the plea field */
	@Column(name="plea")
    private String plea;
  
    /** The value for the courtDecision field */
	@Column(name="court_decision")
    private String courtDecision;
  
    /** The value for the courtCosts field */
	@Column(name="court_costs")
    private String courtCosts;
  
    /** The value for the fine field */
	@Column(name="fine")
    private String fine;
  
    /** The value for the offenseDate field */
	@Column(name="offense_date")
    private String offenseDate;
  
    /** The value for the arrestDate field */
	@Column(name="arrest_date")
    private String arrestDate;
  
    /** The value for the sentenceDate field */
	@Column(name="sentence_date")
    private String sentenceDate;
  
    /** The value for the dispositionDate field */
	@Column(name="disposition_date")
    private String dispositionDate;
  
    /** The value for the fileDate field */
	@Column(name="file_date")
    private String fileDate;
  
   


	public int getNssOffenseId() {
		return nssOffenseId;
	}

	public void setNssOffenseId(int nssOffenseId) {
		this.nssOffenseId = nssOffenseId;
	}

	public int getNssOffenderId() {
		return nssOffenderId;
	}

	public void setNssOffenderId(int nssOffenderId) {
		this.nssOffenderId = nssOffenderId;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisposition() {
		return disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public String getDegreeOfOffense() {
		return degreeOfOffense;
	}

	public void setDegreeOfOffense(String degreeOfOffense) {
		this.degreeOfOffense = degreeOfOffense;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getProbation() {
		return probation;
	}

	public void setProbation(String probation) {
		this.probation = probation;
	}

	public String getConfinement() {
		return confinement;
	}

	public void setConfinement(String confinement) {
		this.confinement = confinement;
	}

	public String getArrestingAgency() {
		return arrestingAgency;
	}

	public void setArrestingAgency(String arrestingAgency) {
		this.arrestingAgency = arrestingAgency;
	}

	public String getOriginatingAgency() {
		return originatingAgency;
	}

	public void setOriginatingAgency(String originatingAgency) {
		this.originatingAgency = originatingAgency;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getStatute() {
		return statute;
	}

	public void setStatute(String statute) {
		this.statute = statute;
	}

	public String getPlea() {
		return plea;
	}

	public void setPlea(String plea) {
		this.plea = plea;
	}

	public String getCourtDecision() {
		return courtDecision;
	}

	public void setCourtDecision(String courtDecision) {
		this.courtDecision = courtDecision;
	}

	public String getCourtCosts() {
		return courtCosts;
	}

	public void setCourtCosts(String courtCosts) {
		this.courtCosts = courtCosts;
	}

	public String getFine() {
		return fine;
	}

	public void setFine(String fine) {
		this.fine = fine;
	}

	public String getOffenseDate() {
		return offenseDate;
	}

	public void setOffenseDate(String offenseDate) {
		this.offenseDate = offenseDate;
	}

	public String getArrestDate() {
		return arrestDate;
	}

	public void setArrestDate(String arrestDate) {
		this.arrestDate = arrestDate;
	}

	public String getSentenceDate() {
		return sentenceDate;
	}

	public void setSentenceDate(String sentenceDate) {
		this.sentenceDate = sentenceDate;
	}

	public String getDispositionDate() {
		return dispositionDate;
	}

	public void setDispositionDate(String dispositionDate) {
		this.dispositionDate = dispositionDate;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	


}
