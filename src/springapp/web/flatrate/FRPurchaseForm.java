package springapp.web.flatrate;

import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import springapp.domain.Rate;
import springapp.service.NeonValidator;
import springapp.service.billing.RateManager;
import springapp.service.user.CreditCardValidator;
import springapp.web.flatrate.FRSignupForm.FRSignupFormCommand;
import springapp.web.funnel.AbstractFunnelController;

@Controller
public class FRPurchaseForm extends AbstractFunnelController {
	
	@RequestMapping(value="/flatrate/FRPurchase.do", method = RequestMethod.GET)
	public String setupFormSignup(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample
			) {
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
				
		FRSignupFormCommand signupfc = (FRSignupFormCommand) session.getAttribute("FRSignupFormCommand");
		
		// at least one must be set
		if (signupfc == null) {
			session.invalidate();
			return FRLandingURL;
		}
		
		// reuse existing info if its there
		FRPurchaseFormCommand cmd = (FRPurchaseFormCommand) session.getAttribute("FRPurchaseFormCommand");
		if (cmd == null) {
			cmd = new FRPurchaseFormCommand();
		} else {
			// clear CC info
			cmd.setCcNumber("");
			cmd.setCcAuthCode("");
		}
		// update these flags in case the pfc is reused		
		cmd.setTest(test);
		setDefaults(cmd);		
		if (test || sample) { 
			setSample(cmd); 
		}
		
		map.addAttribute("command", cmd);
		return FRPurchase;
	}
	
	private void setDefaults(FRPurchaseFormCommand cmd) {		
		if (!cmd.getI18n()) {
			cmd.setCountryCode("US");
		}
		cmd.setReceiveNewsletter(true);
		cmd.setAcceptAgreement(false);
	}
	
	private void setSample(FRPurchaseFormCommand cmd) {
		cmd.setName("Ramanathan Kumarappan");
		cmd.setCcNumber("4111111111111111");
		cmd.setCcAuthCode("123");
		cmd.setCcExpMonth(5);
		cmd.setCcExpYear(2010);
		cmd.setAddress("8142 Making Memories Pl");
		cmd.setCity("Las Vegas");
		cmd.setState("NV");
		cmd.setPostalCode("89131");
		cmd.setPhone1("702");
		cmd.setPhone2("545");
		cmd.setPhone3("0309");
		cmd.setCountryCode("US");
		cmd.setReceiveNewsletter(false);
		cmd.setAcceptAgreement(true);
 }
	
	@RequestMapping(value="/flatrate/FRPurchase.do", method = RequestMethod.POST)
	public String processSubmitSignup(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") FRPurchaseFormCommand command,
			Errors errors,
			SessionStatus status) {
		FRSignupFormCommand signupfc = (FRSignupFormCommand) session.getAttribute("FRSignupFormCommand");
		
		// at least one must be set
		if (signupfc == null) {
			session.invalidate();
			return FRLandingURL;
		}
		
		FRPurchaseFormValidator v = new FRPurchaseFormValidator();
		command.setPhone(command.getPhone1(), command.getPhone2(), command.getPhone3());
		v.validate(command, errors);
		if (errors.hasErrors()) {
			return FRPurchase;
		} else {
			session.setAttribute("FRPurchaseFormCommand", command);
			status.setComplete();
			return FRConfirmRedir;
		}
	}
	
	
	public static class FRPurchaseFormCommand {
		private String name;
		private String company;
		private String address;
		private String city;
		private String state;
		private String postalCode;
		private String countryCode;
		private String email;
		private String confirmEmail;
		private String phone;
		private String phone1;
		private String phone2;
		private String phone3;
		
		private String ccNumber;
		private String ccAuthCode;
		private Integer ccExpYear;
		private Integer ccExpMonth;

		private Boolean receiveNewsletter = true;
		private Boolean acceptAgreement = false;
		private Boolean i18n = false;
		private Boolean test = false;
		
		public String getPhone1() {
			return phone1;
		}
		public void setPhone1(String phone1) {
			this.phone1 = phone1;
		}
		public String getPhone2() {
			return phone2;
		}
		public void setPhone2(String phone2) {
			this.phone2 = phone2;
		}
		public String getPhone3() {
			return phone3;
		}
		public void setPhone3(String phone3) {
			this.phone3 = phone3;
		}
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
		public void setPhone(String phone1, String phone2, String phone3) {
			this.phone = phone1+phone2+phone3;
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
	
	public class FRPurchaseFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(FRPurchaseFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			FRPurchaseFormCommand cmd = (FRPurchaseFormCommand) target;
			
			validateString("name",     cmd.getName(),       errors,  5, 50, regexBasicExtended, "Card Holder Name");
			validateString("address",  cmd.getAddress(),    errors,  5, 50, regexBasicExtended, "Billing Address");
			validateString("city",     cmd.getCity(),       errors,  3, 50, regexBasicExtended, "Billing City");
			validateString("phone",    cmd.getPhone(),      errors, 10, 20, regexNumericDashSpace, "Phone");

			// because this is a hash map and not a db ILIKE, we need to worry about case			
			cmd.setCountryCode(cmd.getCountryCode().toUpperCase());
			
			// us/intl
			if (cmd.getI18n()) {
				// intl
				validateString("countryCode",  cmd.getCountryCode(), errors,  2, 2, regexBasicNoSpace, "Country Code");
				// make sure the country code exists
				Integer cid = userManager.getCountryIdByCode(cmd.getCountryCode());
				if (cid == null) {
					errors.rejectValue("countryCode", "invalid-country-code", "Invalid country code.");
				}
			} else {
				// US
				validateString("state",    cmd.getState(),      errors,  2, 2, regexBasicNoSpace, "State");
				validateString("postalCode",  cmd.getPostalCode(), errors,  5, 10, regexNumeric, "ZIP Code");
			}
			
			// validate card data
			String ccNum = cmd.getCcNumber();
			ccNum = ccNum.replace("-", "");
			ccNum = ccNum.replace(" ", "");
			cmd.setCcNumber(ccNum);
			//logger.info("ccNum fixed: " + ccNum);
			validateString("ccNumber", cmd.getCcNumber(), errors,  13,  16, regexNumericDashSpace, "Credit Card Number");

			// we don't accept discover
			if (! (ccNum.startsWith("3") || ccNum.startsWith("4") || ccNum.startsWith("5")) ) {
				errors.rejectValue("ccNumber", "cc-invalid", "Credit card must be Visa, MasterCard or American Express.");
			}
			
			validateString("ccAuthCode", cmd.getCcAuthCode(), errors,  3,  4, regexNumeric, "Authorization Code");

			validateString("ccExpMonth", cmd.getCcExpMonth(), errors,  1, 2, regexNumeric, "Expiration Month");
			validateString("ccExpYear", cmd.getCcExpYear(), errors,  4,  4, regexNumeric, "Expiration Year");

			if (!cmd.getAcceptAgreement()) {
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