package springapp.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class FRSubscription implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int subscriptionid;
	private int userid;
	private int transactionid;
	private int planid;
	private int plantypeid;
	private Timestamp startdate;
	private Timestamp prevdate;
	private Timestamp nextdate;
	private Timestamp enddate;
	private int status;
	private int availablesearches;
	private int findpeoplesearches;
	private int criminalsearches;
	private Timestamp createdate;
	private int daysavailable;

	
	public int getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}
	public int getDaysavailable() {
		return daysavailable;
	}
	public void setDaysavailable(int daysavailable) {
		this.daysavailable = daysavailable;
	}
	public Timestamp getPrevdate() {
		return prevdate;
	}
	public void setPrevdate(Timestamp prevdate) {
		this.prevdate = prevdate;
	}
	public Timestamp getNextdate() {
		return nextdate;
	}
	public void setNextdate(Timestamp nextdate) {
		this.nextdate = nextdate;
	}
	public int getFindpeoplesearches() {
		return findpeoplesearches;
	}
	public void setFindpeoplesearches(int findpeoplesearches) {
		this.findpeoplesearches = findpeoplesearches;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public int getSubscriptionid() {
		return subscriptionid;
	}
	public void setSubscriptionid(int subscriptionid) {
		this.subscriptionid = subscriptionid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getPlanid() {
		return planid;
	}
	public void setPlanid(int planid) {
		this.planid = planid;
	}
	public int getPlantypeid() {
		return plantypeid;
	}
	public void setPlantypeid(int plantypeid) {
		this.plantypeid = plantypeid;
	}
	public Timestamp getStartdate() {
		return startdate;
	}
	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}
	public Timestamp getEnddate() {
		return enddate;
	}
	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAvailablesearches() {
		return availablesearches;
	}
	public void setAvailablesearches(int availablesearches) {
		this.availablesearches = availablesearches;
	}

	public int getCriminalsearches() {
		return criminalsearches;
	}
	public void setCriminalsearches(int criminalsearches) {
		this.criminalsearches = criminalsearches;
	}
	
	
}
