package springapp.web.funnel.mexico;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import springapp.service.NeonValidator;
import springapp.service.user.CreditCardValidator;
import springapp.web.funnel.mexico.SearchMexicanRecordForm.SearchMexicanRecordFormCommand;

@Controller

public class PurchaseMexicanRecordForm extends springapp.web.funnel.AbstractFunnelController {
		
	@RequestMapping(value="/funnel/mexico/purchaseMexicanRecordSearch.do", method = RequestMethod.GET)
	public String setupFormSearch(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="i18n",required=false) Boolean i18n
			) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		if (i18n == null) { i18n = false; }
		
		if (session.getAttribute("creditCardObj") != null) {
			return "redirect:confirmMexicanSearch.do";
		}
		
		SearchMexicanRecordFormCommand searchmrfc = (SearchMexicanRecordFormCommand) session.getAttribute("searchMexicanRecordFormCommand");
		logger.info("Purchase Search Form Loading => Mexican search record form: " + searchmrfc);
		
		// at least one must be set
		if (searchmrfc == null) {
			session.invalidate();
			return landingMexicanRecordRedir;
		}
		
		// reuse existing info if its there
		PurchaseMexicanRecordFormCommand cmd = (PurchaseMexicanRecordFormCommand) session.getAttribute("purchaseMexicanRecordFormCommand");
		if (cmd == null) {
			cmd = new PurchaseMexicanRecordFormCommand();
		} else {
			// clear CC info
			cmd.setCcNumber("");
			cmd.setCcAuthCode("");
		}
		
		// update these flags in case the pfc is reused		
		cmd.setI18n(i18n);
		cmd.setTest(test);
		setDefaults(cmd);		
		if (test || sample) { 
			setSample(cmd); 
		}
		
		map.addAttribute("command", cmd);
		return purchaseMexicanView;
	}
	
	private void setDefaults(PurchaseMexicanRecordFormCommand cmd) {		
		if (!cmd.getI18n()) {
			cmd.setCountryCode("US");
		}
		cmd.setRecurringBilling(false);
		cmd.setReceiveNewsletter(true);
		cmd.setAcceptAgreement(false);
	}
	
	private void setSample(PurchaseMexicanRecordFormCommand cmd) {
		cmd.setName("Ramanathan Kumarappan");
		cmd.setCcNumber("4111111111111111");
		cmd.setCcAuthCode("123");
		cmd.setCcExpMonth(12);
		cmd.setCcExpYear(2012);
		
		cmd.setAddress("8142 Making Memories Pl");
		cmd.setCity("Las Vegas");
		cmd.setState("NV");
		cmd.setPostalCode("89131");
		cmd.setPhone("7025450309");
		cmd.setCountryCode("US");
		cmd.setEmail("ram.kumarappan@searchsystems.net");
		cmd.setConfirmEmail(cmd.getEmail());

		cmd.setReceiveNewsletter(false);
		cmd.setAcceptAgreement(true);
	}
	
	@RequestMapping(value="/funnel/mexico/purchaseMexicanRecordSearch.do", method = RequestMethod.POST)
	public String processSubmitSearch(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") PurchaseMexicanRecordFormCommand command,
			Errors errors,
			SessionStatus status) {
		SearchMexicanRecordFormCommand searchmrfc = (SearchMexicanRecordFormCommand) session.getAttribute("searchMexicanRecordFormCommand");
		logger.info("Purchase Search Form Loading => Mexican search record form - POST: " + searchmrfc);
		
		// at least one must be set
		if (searchmrfc == null) {
			session.invalidate();
			return landingMexicanRecordRedir;
		}
		
		if (session.getAttribute("creditCardObj") != null) {
			return "redirect:confirmMexicanSearch.do";
		}

		PurchaseFormValidator v = new PurchaseFormValidator();
		v.validate(command, errors);
		if (errors.hasErrors()) {
			return purchaseMexicanView;
		} else {
			session.setAttribute("purchaseMexicanRecordFormCommand", command);
			status.setComplete();
			return confirmMexicanSearchRedir;
		}
	}
	
	public static class PurchaseMexicanRecordFormCommand {
		private String name;
		private String company;
		private String address;
		private String address2;
		private String city;
		private String state;
		private String countryCode;
		private String postalCode;
		private String phone;
		private String email;
		private String confirmEmail;
		
		private String ccNumber;
		private String ccAuthCode;
		private Integer ccExpYear;
		private Integer ccExpMonth;
		
		private Boolean recurringBilling = false;
		private Boolean receiveNewsletter = true;
		private Boolean acceptAgreement = false;
		private Boolean test = false;
		private Boolean i18n = false;
		
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Boolean getTest() {
			return test;
		}
		public void setTest(Boolean test) {
			this.test = test;
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
		public Boolean getI18n() {
			return i18n;
		}
		public void setI18n(Boolean i18n) {
			this.i18n = i18n;
		}
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		public String getPostalCode() {
			return postalCode;
		}
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
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
	}
	
	public class PurchaseFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(PurchaseMexicanRecordFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			PurchaseMexicanRecordFormCommand cmd = (PurchaseMexicanRecordFormCommand) target;
			
			validateString("name",     cmd.getName(),       errors,  5, 50, regexBasicExtended, "Card Holder Name");
			logger.info("PurchaseFormValidator Inside => Name: " + cmd.getName() + ";");
			validateString("address",  cmd.getAddress(),    errors,  5, 50, regexBasicExtended, "Billing Address Line 1");
			logger.info("PurchaseFormValidator Inside => Address: " + cmd.getAddress() + ";");
			validateString("city",     cmd.getCity(),       errors,  3, 50, regexBasicExtended, "Billing City");
			logger.info("PurchaseFormValidator Inside => City: " + cmd.getCity() + ";");
			validateString("phone",    cmd.getPhone(),      errors, 10, 20, regexNumericDashSpace, "Phone");
			logger.info("PurchaseFormValidator Inside => Phone: " + cmd.getPhone() + ";");
			
			if (cmd.getConfirmEmail() != null && !(cmd.getConfirmEmail().equals(cmd.getEmail())) ) {
				errors.rejectValue("confirmEmail", "email-mismatch", "The email addresses do not match.");
			} else {
				validateString("email", cmd.getEmail(), errors,  5, 50, emailRegex, "Email");
			}
			
			cmd.setCountryCode(cmd.getCountryCode().toUpperCase());
			
			if (cmd.getI18n()) {
				// international
				validateString("countryCode",  cmd.getCountryCode(), errors,  2, 2, regexBasicNoSpace, "Country Code");
				// make sure the country code exists
				Integer cid = userManager.getCountryIdByCode(cmd.getCountryCode());
				if (cid == null) {
					errors.rejectValue("countryCode", "invalid-country-code", "Invalid country code.");
				}
			} else {
				// US
				validateString("state",    cmd.getState(),      errors,  2, 40, regexBasicNoSpace, "State");
				validateString("postalCode",  cmd.getPostalCode(), errors,  5, 10, regexNumeric, "ZIP Code");
			}
			
			// validate card data
			String ccNum = cmd.getCcNumber();
			if (ccNum != null) {
				ccNum = ccNum.replace("-", "");
				ccNum = ccNum.replace(" ", "");
				cmd.setCcNumber(ccNum);
			}
			validateString("ccNumber", cmd.getCcNumber(), errors,  13,  16, regexNumericDashSpace, "Credit Card Number");

			// we don't accept discover
			if (ccNum != null && !(ccNum.startsWith("3") || ccNum.startsWith("4") || ccNum.startsWith("5"))) {
				errors.rejectValue("ccNumber", "cc-invalid", "Credit card must be Visa, MasterCard or American Express.");
			}
			
			validateString("ccAuthCode", cmd.getCcAuthCode(), errors,  3,  4, regexNumeric, "Authorization Code");
			
			validateString("ccExpMonth", cmd.getCcExpMonth(), errors,  1, 2, regexNumeric, "Expiration Month");
			validateString("ccExpYear", cmd.getCcExpYear(), errors,  4,  4, regexNumeric, "Expiration Year");
			
			if (cmd.getAcceptAgreement() != null && !cmd.getAcceptAgreement()) {
				errors.rejectValue("acceptAgreement", "accept-agreement", "You must accept the user agreement to continue.");
			}

			// can bail out here before messing with the card
			if (errors.hasErrors()) { return; }
			
			CreditCardValidator ccv = new CreditCardValidator();
			if (cmd.getCcNumber() != null) {
				ccv.validateCardNumber(cmd.getCcNumber(), errors);
				ccv.validateCardExpiration(cmd.getCcExpMonth(), cmd.getCcExpYear(), errors, "ccExpMonth", "ccExpYear");
			}
			
		}		
	}
}