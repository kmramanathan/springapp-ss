package springapp.web.search;

public class SearchForm {
	int userId;
	String firstName;
	String lastName;
	boolean firstNameExact;
	boolean lastNameExact;
	int dobMonth;
	int dobDay;
	int dobYear;
	int dobYearRange;
	boolean matchFuzzyDates;
	boolean matchMissingDates;
	int bgcProductId;
	boolean isDpassUser;
	String crimJurisdiction;
	String crimPurpose;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public boolean isFirstNameExact() {
		return firstNameExact;
	}
	public void setFirstNameExact(boolean firstNameExact) {
		this.firstNameExact = firstNameExact;
	}
	public boolean isLastNameExact() {
		return lastNameExact;
	}
	public void setLastNameExact(boolean lastNameExact) {
		this.lastNameExact = lastNameExact;
	}
	public int getDobMonth() {
		return dobMonth;
	}
	public void setDobMonth(int dobMonth) {
		this.dobMonth = dobMonth;
	}
	public int getDobDay() {
		return dobDay;
	}
	public void setDobDay(int dobDay) {
		this.dobDay = dobDay;
	}
	public int getDobYear() {
		return dobYear;
	}
	public void setDobYear(int dobYear) {
		this.dobYear = dobYear;
	}
	public int getDobYearRange() {
		return dobYearRange;
	}
	public void setDobYearRange(int dobYearRange) {
		this.dobYearRange = dobYearRange;
	}
	public boolean isMatchFuzzyDates() {
		return matchFuzzyDates;
	}
	public void setMatchFuzzyDates(boolean matchFuzzyDates) {
		this.matchFuzzyDates = matchFuzzyDates;
	}
	public boolean isMatchMissingDates() {
		return matchMissingDates;
	}
	public void setMatchMissingDates(boolean matchMissingDates) {
		this.matchMissingDates = matchMissingDates;
	}
	public int getBgcProductId() {
		return bgcProductId;
	}
	public void setBgcProductId(int bgcProductId) {
		this.bgcProductId = bgcProductId;
	}
	public boolean isDpassUser() {
		return isDpassUser;
	}
	public void setDpassUser(boolean isDpassUser) {
		this.isDpassUser = isDpassUser;
	}
	public String getCrimJurisdiction() {
		return crimJurisdiction;
	}
	public void setCrimJurisdiction(String crimJurisdiction) {
		this.crimJurisdiction = crimJurisdiction;
	}
	public String getCrimPurpose() {
		return crimPurpose;
	}
	public void setCrimPurpose(String crimPurpose) {
		this.crimPurpose = crimPurpose;
	}

}