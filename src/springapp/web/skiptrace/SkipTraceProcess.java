package springapp.web.skiptrace;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springapp.service.NeonValidator;
import springapp.web.funnel.AbstractFunnelController;

@Controller
public class SkipTraceProcess extends AbstractFunnelController{
	
	private final String STLandingPage = "skiptrace/Landing";
	private final String STOrderPage = "skiptrace/OrderForm";	
	private final String STThankPage = "skiptrace/ThankYou";
	
	@RequestMapping(value = "/skiptrace/Landing.do", method = RequestMethod.GET)
	public String ShowLandingPage(ModelMap map) {
		return STLandingPage;		
	}
	
	@RequestMapping(value = "/skiptrace/OrderForm.do", method = RequestMethod.GET)
	public String ShowOrderPage(ModelMap map, HttpSession session) {
		
		STOrderFormCommand stof = new STOrderFormCommand();
		map.addAttribute("command", stof);
		return STOrderPage;		
	}
	
	@RequestMapping(value = "/skiptrace/OrderForm.do", method = RequestMethod.POST)
	public String ProcessOrderPage(ModelMap map, HttpSession session,
			@ModelAttribute("command") STOrderFormCommand stoc, Errors errors) {		
		
		STOrderFormValidator v = new STOrderFormValidator();
		v.validate(stoc, errors);
		if(errors.hasErrors()){
			map.addAttribute("command", stoc);
			return STOrderPage;
		}
			
		//sending mail
		map.addAttribute("skiptrace", stoc);		
		
		String[] emails = {"webmaster@searchsystems.net", "seand@searchsystems.net", "contact@searchsystems.net"};
		sendHtmlEmailVelocity(map, skipTraceOrder, emailFromSkipTrace, emails , "Skip Trace Order from "+stoc.getEmail());
		
		return STThankPage;		
	}
	
	public static class STOrderFormCommand{
		private String reason;
		private String firstName;
		private String middleName;
		private String lastName;
		private String dob;
		private String lastAddress;
		private boolean specialPerson = false;
		private String ssn;
		private String city;
		private String spouseName;
		private String state;
		private String zip;
		private String addressDate;
		private String contactName;
		private String company;
		private String phone;
		private String email;
		private String confirmMail;
		
				
		public boolean isSpecialPerson() {
			return specialPerson;
		}
		public void setSpecialPerson(boolean specialPerson) {
			this.specialPerson = specialPerson;
		}
		public String getConfirmMail() {
			return confirmMail;
		}
		public void setConfirmMail(String confirmMail) {
			this.confirmMail = confirmMail;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getMiddleName() {
			return middleName;
		}
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getLastAddress() {
			return lastAddress;
		}
		public void setLastAddress(String lastAddress) {
			this.lastAddress = lastAddress;
		}
		public String getSsn() {
			return ssn;
		}
		public void setSsn(String ssn) {
			this.ssn = ssn;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getSpouseName() {
			return spouseName;
		}
		public void setSpouseName(String spouseName) {
			this.spouseName = spouseName;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
		public String getAddressDate() {
			return addressDate;
		}
		public void setAddressDate(String addressDate) {
			this.addressDate = addressDate;
		}
		public String getContactName() {
			return contactName;
		}
		public void setContactName(String contactName) {
			this.contactName = contactName;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}		
		
	}
	
	public class STOrderFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(STOrderFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			STOrderFormCommand cmd = (STOrderFormCommand) target;

			validateStringEmptyMsg("reason", cmd.getReason() , errors,  3, 500, regexText, "Reason", "Please input your Reason for Search.");
			validateStringEmptyMsg("firstName",  cmd.getFirstName(), errors,  2, 50, regexBasicSpace, "First Name", "Please input the First Name of your Subject.");
			validateStringEmptyMsg("lastName",   cmd.getLastName(), errors,  2, 50, regexBasicSpace, "Last Name", "Please input the Last Name of your Subject.");			
			validateStringEmptyMsg("dob", cmd.getDob() , errors,  1, 20, regexText, "DOB", "Please input the DOB of your Subject.");
			validateStringEmptyMsg("contactName", cmd.getContactName() , errors,  3, 50, regexBasicSpace, "Contact Name", "Please input your Contact Name.");
			validateStringEmptyMsg("phone",  cmd.getPhone(), errors,  3, 50, regexText, "Phone","Please input your Phone number.");
			validateStringEmptyMsg("email",   cmd.getEmail(), errors,  3, 50, emailRegex, "Email", "Please input your email address.");
			validateStringEmptyMsg("confirmMail",   cmd.getConfirmMail(), errors,  3, 50, emailRegex, "Confirm Email", "Please confirm your email address.");	
			if(!cmd.getEmail().equals(cmd.getConfirmMail())){
				errors.rejectValue("confirmMail", "error.invalid-number", "Email addresses do not match.");
			}
			
			validateStringEmptyOk("middleName",   cmd.getMiddleName(), errors,  1, 2, regexBasicNoSpace, "Middle Name");
			validateStringEmptyOk("lastAddress",   cmd.getLastAddress(), errors,  1, 100, regexText, "Last Address");
			validateStringEmptyOk("ssn",   cmd.getSsn(), errors,  9, 9, regexNumeric, "SSN");
			validateStringEmptyOk("city",   cmd.getCity(), errors,  1, 50, regexText, "City");
			validateStringEmptyOk("spouseName",   cmd.getSpouseName(), errors,  1, 50, regexBasicSpace, "SpouseName");
			validateStringEmptyOk("state",   cmd.getState(), errors,  1, 3, regexText, "State");
			validateStringEmptyOk("zip",   cmd.getZip(), errors,  1, 20, regexText, "Zip");
			validateStringEmptyOk("addressDate",   cmd.getAddressDate(), errors,  1, 20, regexText, "Address date");
			validateStringEmptyOk("company",   cmd.getCompany(), errors,  1, 50, regexText, "company");			
			
			}		
	}
	
}
