package springapp.domain.NSS;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nss_responses")
public class NssResponseBean implements Serializable {
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The value for the bgcResponseId field */
	@Id
	@GeneratedValue
	@Column(name="nss_response_id")
    private int nssResponseId;
  
    /** The value for the bgcRequestId field */
	@Column(name="nss_request_id")
    private int nssRequestId;
  
   
  
    /** The value for the quantityFound field */
	@Column(name="quantity_found")
    private int quantityFound;
  
    /** The value for the quantityReturned field */
	@Column(name="quantity_returned")
    private int quantityReturned;
  
    /** The value for the dateCreated field */
	@Column(name="date_created")
    private Date dateCreated;
	/** The value for the hashKey field */
	@Column(name="hash_key")
	private String hashKey;
    /** The value for the transactionId field */
	@Column(name="transaction_id")
    private int transactionId;

	public int getNssResponseId() {
		return nssResponseId;
	}

	public void setNssResponseId(int nssResponseId) {
		this.nssResponseId = nssResponseId;
	}

	public int getNssRequestId() {
		return nssRequestId;
	}

	public void setNssRequestId(int nssRequestId) {
		this.nssRequestId = nssRequestId;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public int getQuantityFound() {
		return quantityFound;
	}

	public void setQuantityFound(int quantityFound) {
		this.quantityFound = quantityFound;
	}

	public int getQuantityReturned() {
		return quantityReturned;
	}

	public void setQuantityReturned(int quantityReturned) {
		this.quantityReturned = quantityReturned;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}


    
    

}
