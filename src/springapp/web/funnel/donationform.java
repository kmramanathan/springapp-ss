package springapp.web.funnel;

import java.math.BigDecimal;

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
import springapp.web.funnel.NewBJLSearchForm.BJLSearchFormCommand;
import springapp.web.funnel.donationform.DonateFormCommand;
import springapp.web.funnel.donationform.DonateFormValidator;
import springapp.web.funnel.NewPurchaseForm.NewPurchaseFormCommand;
import springapp.web.funnel.NewSearchForm.SearchFormCommand;

@Controller
public class donationform extends AbstractFunnelController {
	
	@RequestMapping(value = "/funnel/Donation.do", method = RequestMethod.GET)
	public String enterdonationform(HttpSession session,ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample,
			@RequestParam(value="i18n",required=false) Boolean i18n)
		
		{
		if (test == null) { test = false; }
		if (sample == null) { sample = false; }
		if (i18n == null) { i18n = false; }
		
		// reuse existing info if its there
		DonateFormCommand cmd = (DonateFormCommand) session.getAttribute("DonateFormCommand");
		if (cmd == null) {
			cmd = new DonateFormCommand();
		} else {
			// clear CC info
		//	cmd.setCcNumber("");
		//	cmd.setCcAuthCode("");
		}
		
		cmd.setI18n(i18n);
		
		// update these flags in case the pfc is reused		
		//cmd.setSignup(false);	
		//cmd.setSearch(true);
		
		cmd.setI18n(i18n);
		cmd.setTest(test);
		setDefaults(cmd);		
		if (test || sample) { 
			setSample(cmd); 
		}
		
		map.addAttribute("command", cmd);
		return donateview;
		
	}
	
	private void setDefaults(DonateFormCommand cmd) {		
		if (!cmd.getI18n()) {
			cmd.setCountryCode("US");
		}
	}
	
	private void setSample(DonateFormCommand cmd) {
		cmd.setName("Ramanathan Kumarappan");
		cmd.setCcNumber("4111111111111111");
		cmd.setCcAuthCode("123");
		cmd.setCcExpMonth(5);
		cmd.setCcExpYear(2012);
		//cmd.setDonationamount(500.00);
		cmd.setFrequency("1");
		cmd.setAddress("8142 Making Memories Pl");
		cmd.setCity("Las Vegas");
		cmd.setState("NV");
		cmd.setPostalCode("89131");
		cmd.setCountryCode("US");
		cmd.setReceiveNewsletter(false);
		cmd.setAcceptAgreement(true);				
	}
	
	
	@RequestMapping(value="/funnel/Donation.do", method = RequestMethod.POST)
	public String DonateSubmitSearch(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") DonateFormCommand command,
			Errors errors,
			SessionStatus status) {
		SearchFormCommand searchfc = (SearchFormCommand) session.getAttribute("searchFormCommand");
		BJLSearchFormCommand bjlsfc = (BJLSearchFormCommand) session.getAttribute("bjlSearchFormCommand");
		
		// at least one must be set
		//if ((searchfc == null) && (bjlsfc == null)) {
		//	session.invalidate();
		//	return landingHome;
		//}

		DonateFormValidator v = new DonateFormValidator();
		DonateFormCommand dd=new DonateFormCommand();
		
		
		//logger.info(dd.getPricevalue());
		v.validate(command, errors);
		if (errors.hasErrors()) {
			logger.info("problem");
			
			
			return donateview;
		} else {
			session.setAttribute("DonateFormCommand", command);
			status.setComplete();
			
			
			return donationconfirmredir;
		}
	}
	public static class DonateFormCommand {
		private double	donationamount;
		private String frequency;
		private String name;
		private String company;
		private String address;
		private String city;
		private String state;
		private String postalCode;
		private String countryCode;
		private String email;
		private Boolean receiveNewsletter = true;		
		private String ccNumber;
		private String ccAuthCode;
		private Integer ccExpYear;
		private Integer ccExpMonth;
		private Boolean acceptAgreement = false;
		private Boolean i18n = false;
		private Boolean test = false;
		
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
		public Boolean getReceiveNewsletter() {
			return receiveNewsletter;
		}
		public void setReceiveNewsletter(Boolean receiveNewsletter) {
			this.receiveNewsletter = receiveNewsletter;
		}
		
		public double getDonationamount() {
			return donationamount;
		}
		public void setDonationamount(double donationamount) {
			this.donationamount = donationamount;
		}
		public String getFrequency() {
			return frequency;
		}
		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}
		
		public Boolean getAcceptAgreement() {
			return acceptAgreement;
		}
		public void setAcceptAgreement(Boolean acceptAgreement) {
			this.acceptAgreement = acceptAgreement;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getCcNumber() {
			return ccNumber;
		}
		public void setCcNumber(String ccNumber) {
			this.ccNumber = ccNumber;
		}
		public String getCcAuthCode() {
			return ccAuthCode;
		}
		public void setCcAuthCode(String ccAuthCode) {
			this.ccAuthCode = ccAuthCode;
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
		
	}
	
	public class DonateFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(DonateFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			DonateFormCommand cmd = (DonateFormCommand) target;
			validateString("name",cmd.getName(),errors,5,50,regexBasicExtended,"Card Holder Name");
			validateString("address",cmd.getAddress(),errors,5,50,regexBasicExtended,"Billing Address");
			validateString("city",cmd.getCity(),errors,3,50, regexBasicExtended,"Billing City");
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
			
			if(cmd.getDonationamount() == 0)
			{
			errors.rejectValue("donationamount", "can't be zero", "Please select your donation amount");	
			}
			
			
			String ccNum = cmd.getCcNumber();
			ccNum = ccNum.replace("-", "");
			ccNum = ccNum.replace(" ", "");
			cmd.setCcNumber(ccNum);
			logger.info("ccNum fixed: " + ccNum);
			validateString("ccNumber", cmd.getCcNumber(), errors,  13,  16, regexNumericDashSpace, "Credit Card Number");
			
			// we don't accept discover
			if (! (ccNum.startsWith("3") || ccNum.startsWith("4") || ccNum.startsWith("5")) ) {
				errors.rejectValue("ccNumber", "cc-invalid", "Credit card must be Visa, MasterCard or American Express.");
			}
			
			validateString("ccAuthCode", cmd.getCcAuthCode(), errors,  3,  4, regexNumeric, "Authorization Code");

			validateString("ccExpMonth", cmd.getCcExpMonth(), errors,  1, 2, regexNumeric, "Expiration Month");
			validateString("ccExpYear", cmd.getCcExpYear(), errors,  4,  4, regexNumeric, "Expiration Year");
			
			if (errors.hasErrors()) { return; }
			
			CreditCardValidator ccv = new CreditCardValidator();
			if (cmd.getCcNumber() != null) {
				ccv.validateCardNumber(cmd.getCcNumber(), errors);
				ccv.validateCardExpiration(cmd.getCcExpMonth(), cmd.getCcExpYear(), errors, "ccExpMonth", "ccExpYear");
			}
			
		}
		
	}

}
