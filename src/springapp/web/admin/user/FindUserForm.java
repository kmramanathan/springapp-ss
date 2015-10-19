package springapp.web.admin.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.User;
import springapp.repository.SubscriptionDao;
import springapp.service.NeonValidator;
import springapp.service.user.FindUserResult;
import springapp.service.user.UserManager;

@Controller
@RequestMapping("/admin/findUser.do")

public class FindUserForm {
	protected final Logger logger = Logger.getLogger(getClass()); 
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private SubscriptionDao subscriptionDao;
	/*
	@Autowired
	public FindUserForm(UserManager userManager) {
		this.userManager = userManager;
	}
	*/
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm() {
		return new ModelAndView("admin/FindUser", "findUser", new FindUserFormCommand());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processSubmit(
			@ModelAttribute("findUser") FindUserFormCommand findUser,
			Errors errors,
			SessionStatus status) {	
		new FindUserFormValidator().validate(findUser, errors);
		if (errors.hasErrors()) {
			return new ModelAndView("admin/FindUser", "findUser", findUser);
		}
		User user = new User();
		boolean partialMatch = findUser.isPartialMatch();
		
		HashMap<String,String> fields = new HashMap<String,String>();
		fields.put("username",  makeSqlMatchString(findUser.getUsername(), partialMatch));
		fields.put("firstName", makeSqlMatchString(findUser.getFirstName(), partialMatch));
		fields.put("lastName",  makeSqlMatchString(findUser.getLastName(), partialMatch));
		fields.put("email",     makeSqlMatchString(findUser.getEmail(), partialMatch));
		fields.put("last4",     makeSqlMatchString(findUser.getLast4(), partialMatch));
		fields.put("address",   makeSqlMatchString(findUser.getAddress(), partialMatch));
		fields.put("phone",     makeSqlMatchString(findUser.getPhone(), partialMatch));
		fields.put("billingName", makeBillingNameString(findUser.getFirstName(), findUser.getLastName()));
		
		
		// check to make sure at least one of the fields is in use
		// more appropriate for validator?
		int fieldsInUse = 0;
		for (String s : fields.keySet()) {
			if (fields.get(s).length() > 0) {
				fieldsInUse++;
			}
		}
		if (fieldsInUse == 0) {
			// reject username rather than whole form since errors:* is not on view
			errors.rejectValue("username", "one-field-required", "At least one field is required.");
			return new ModelAndView("admin/FindUser", "findUser", findUser);
		}
		
		List<FindUserResult> resultTempList = userManager.findUsers(fields, findUser.isMatchAllFields());
		List<FindUserResult> resultList = new ArrayList<FindUserResult>();
		
		for(FindUserResult f :resultTempList){
			 User fuser = userManager.getUserByUsername(f.getUsername(), false);
			 f.setFlatrate(subscriptionDao.isActiveFRUser(fuser.getUserId()));
			 resultList.add(f);
		}
		
    	ModelAndView mav = new ModelAndView("admin/FindUser");
    	mav.addObject("findUser", findUser);
    	mav.addObject("resultList", resultList);
    	mav.addObject("count", resultList.size());
    	return mav;
	}
	
	protected String makeSqlMatchString(String value, boolean partialMatch) {
		// if not a partial, just return the string
		if (!partialMatch) { return value; }
		
		// only set % for non-empty values
		if (value.equals("")) {
			return value;
		} else {
			return "%" + value + "%";
		}
	}
	
	protected String makeBillingNameString(String firstName, String lastName) {
		String billingName = "";
		if (!firstName.equals("")) {
			billingName += "%" + firstName + "%";
		}
		if (!lastName.equals("")) {
			billingName += "%" + lastName + "%";
		}
		return billingName;
	}
	
	public static class FindUserFormCommand {
		// search fields
		private String username;
		private String firstName;
		private String lastName;
		private String email;
		private String last4;
		private String address;
		private String phone;		

		// options
		private boolean partialMatch;
		private boolean matchAllFields;
		
		// return values
		private int count;		
		private ArrayList<User> userList;
		private boolean results = false;

		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public boolean isPartialMatch() {
			return partialMatch;
		}
		public void setPartialMatch(boolean partialMatch) {
			this.partialMatch = partialMatch;
		}		
		public boolean isMatchAllFields() {
			return matchAllFields;
		}
		public void setMatchAllFields(boolean matchAllFields) {
			this.matchAllFields = matchAllFields;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public ArrayList<User> getUserList() {
			return userList;
		}
		public void setUserList(ArrayList<User> userList) {
			this.userList = userList;
		}
		public boolean isResults() {
			return results;
		}
		public void setResults(boolean results) {
			this.results = results;
		}
		public String getLast4() {
			return last4;
		}
		public void setLast4(String last4) {
			this.last4 = last4;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}		
	}
	
	
	public static class FindUserFormValidator extends NeonValidator implements Validator {
	    private final int DEFAULT_MIN_LENGTH = 3;
	    private final int DEFAULT_MAX_LENGTH = 25;
	    private int minLength = DEFAULT_MIN_LENGTH;
	    private int maxLength = DEFAULT_MAX_LENGTH;
	    private int maxEmailLength = 50;
	    private int maxAddressLength = 50;

	    protected final Logger logger = Logger.getLogger(getClass());

	    public boolean supports(Class clazz) {
	        return FindUserFormCommand.class.equals(clazz);
	    }

	    public void validate(Object obj, Errors errors) {
	    	FindUserFormCommand f = (FindUserFormCommand) obj;
	        if (f == null) {
	            errors.rejectValue("searchTerm", "error.not-specified", null, "Value required.");
	            return;
	        }

	        if (f.isPartialMatch()) {
	        	minLength = 1;
	        } else {
	        	minLength = 0;
	        }	        

	        validateStringEmptyOk("username",  f.getUsername(),  errors, minLength, maxLength, usernameRegex);
	        validateStringEmptyOk("firstName", f.getFirstName(), errors, minLength, maxLength, regexBasicSpace);
	        validateStringEmptyOk("lastName",  f.getLastName(),  errors, minLength, maxLength, regexBasicSpace);
	        validateStringEmptyOk("email", f.getEmail(), errors, minLength, maxEmailLength, emailRegexRelaxed);
	        
	        validateStringEmptyOk("last4", f.getLast4(), errors, 4, 4, regexNumeric);
	        
	        validateStringEmptyOk("address",  f.getAddress(),  errors, minLength, maxAddressLength, regexBasicSpace);
	        validateStringEmptyOk("phone",  f.getPhone(),  errors, minLength, maxLength, regexBasicSpace);

	    }

		public int getMinLength() {
			return minLength;
		}
		public void setMinLength(int minLength) {
			this.minLength = minLength;
		}
		public int getMaxLength() {
			return maxLength;
		}
		public void setMaxLength(int maxLength) {
			this.maxLength = maxLength;
		}
	}	
}