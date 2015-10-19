package springapp.web;

import springapp.crypt.Crypt;
import springapp.domain.CreditCard;

/**
 * Form command object for billing information.
 * 
 * @author judd
 *
 */
public class BillingInformationFormCommand {
	private String name;
	private String company;
	private String address;
	private String city;
	private String state;
	private String postalCode;
	private String countryCode;
	private String phone;
	private String email;
	private String confirmEmail;

	private String ccNumber;
	private String ccAuthCode;
	private Integer ccExpYear;
	private Integer ccExpMonth;

	private Integer paymentPlan;

	private Boolean recurringBilling = true;
	private Boolean receiveNewsletter = true;
	private Boolean acceptAgreement = false;
	private Boolean i18n = false;
	private Boolean test = false;
	private Boolean search = false;
	private Boolean signup = false;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPaymentPlan() {
		return paymentPlan;
	}
	public void setPaymentPlan(Integer paymentPlan) {
		this.paymentPlan = paymentPlan;
	}
	public Boolean getTest() {
		return test;
	}
	public void setTest(Boolean test) {
		this.test = test;
	}
	public Boolean getI18n() {
		return i18n;
	}
	public void setI18n(Boolean i18n) {
		this.i18n = i18n;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public Integer getCcExpYear() {
		return ccExpYear;
	}
	public void setCcExpYear(Integer ccExpYear) {
		this.ccExpYear = ccExpYear;
	}
	public Integer getCcExpMonth() {
		return ccExpMonth;
	}
	public void setCcExpMonth(Integer ccExpMonth) {
		this.ccExpMonth = ccExpMonth;
	}
	public String getCcAuthCode() {
		return ccAuthCode;
	}
	public void setCcAuthCode(String ccAuthCode) {
		this.ccAuthCode = ccAuthCode;
	}
	public Boolean getReceiveNewsletter() {
		return receiveNewsletter;
	}
	public void setReceiveNewsletter(Boolean receiveNewsletter) {
		this.receiveNewsletter = receiveNewsletter;
	}
	public Boolean getAcceptAgreement() {
		return acceptAgreement;
	}
	public void setAcceptAgreement(Boolean acceptAgreement) {
		this.acceptAgreement = acceptAgreement;
	}
	public Boolean getRecurringBilling() {
		return recurringBilling;
	}
	public void setRecurringBilling(Boolean recurringBilling) {
		this.recurringBilling = recurringBilling;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getConfirmEmail() {
		return confirmEmail;
	}
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
	public Boolean getSearch() {
		return search;
	}
	public void setSearch(Boolean search) {
		this.search = search;
	}
	public Boolean getSignup() {
		return signup;
	}
	public void setSignup(Boolean signup) {
		this.signup = signup;
	}
	
	public void populateFormFieldsFromCC(CreditCard cc, String countryCode) {
		setName(cc.getName());
		setAddress(cc.getAddress1());
		setCity(cc.getCity());
		setState(cc.getState());
		setPostalCode(cc.getPostalCode());
		setCountryCode(countryCode);
		setPhone(cc.getPhone());
		
		setCcNumber(Crypt.decrypt(cc.getNumber()));
		setCcExpMonth((int) cc.getExpMonth());
		setCcExpYear((int) cc.getExpYear());
	}
}