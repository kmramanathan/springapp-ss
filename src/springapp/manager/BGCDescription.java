package springapp.manager;

public class BGCDescription {
	protected String jurisdiction;
	protected String provider;
	protected String description;
	
	public synchronized String getJurisdiction() {
		return jurisdiction;
	}
	public synchronized void setJurisdiction(String code) {
		this.jurisdiction = code;
	}
	public synchronized String getDescription() {
		return description;
	}
	public synchronized void setDescription(String description) {
		this.description = description;
	}
	public synchronized String getProvider() {
		return provider;
	}
	public synchronized void setProvider(String provider) {
		this.provider = provider;
	}
	
}
	