package springapp.web.admin.corp;

import org.apache.log4j.Logger;

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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.CorporateAccount;
import springapp.domain.User;
import springapp.service.NeonValidator;
import springapp.service.user.UserManager;

@Controller
@SessionAttributes("corporateAccount")

public class EditCorporateAccountForm {
	protected final Logger logger = Logger.getLogger(getClass()); 

	protected static final String editCorporateAccountView = "admin/EditCorporateAccount";
	//protected static final String listCorporateAccountsView = "admin/ListCorporateAccounts";
	protected static final String listCorporateAccountsRedir = "redirect:listCorporateAccounts.do";
	protected static final String editCorporateAccountRedir = "redirect:editCorporateAccount.do";
	protected static final String listCorporateAccountUsersRedir = "redirect:listCorporateAccountUsers.do";
	
	@Autowired
	private UserManager userManager;
	    
	@RequestMapping(value="/admin/editCorporateAccount.do", method=RequestMethod.GET)
	public ModelAndView setupForm(
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="newAccount",required=false) Boolean newAccount,
			@RequestParam(value="accountId",required=false) Integer accountId,
			ModelMap map) {
		if (test == null) { test = false; }

		ModelAndView mav = new ModelAndView(editCorporateAccountView);		

		CorporateAccount acct;
		if (accountId == null) { 
			// new account
			mav.addObject("newAccount", true);
			acct = new CorporateAccount();
			if (test) { setSample(acct); }
		} else {
			// load account
			mav.addObject("newAccount", false);
			acct = userManager.getCorporateAccountById(accountId);
		}

		// set up mav
		mav.addObject("corporateAccount", acct);
		return mav;
	}
	
	@RequestMapping(value="/admin/editCorporateAccount.do", method = RequestMethod.POST)
	public String processSubmit(
			ModelMap map,
			@ModelAttribute("corporateAccount") CorporateAccount acct,
			Errors errors,
			SessionStatus status) {
		new AddCorporateAccountFormValidator().validate(acct, errors);

		if (errors.hasErrors()) {
			return editCorporateAccountView;	
		} else {
			if (acct.getCorporateAccountId() == 0) {
				// add it
				userManager.addCorporateAccount(acct);				
			} else {
				userManager.updateCorporateAccount(acct);
			}
			status.setComplete();
			return listCorporateAccountsRedir;			
		}
	}
	
	@RequestMapping(value="/admin/editCorporateAccountUsers.do")
	public String addUserToAccount(			
			WebRequest req,
			@RequestParam("mode") String mode,
			@RequestParam("accountId") Integer accountId,
			@RequestParam("username") String username
			) {
		CorporateAccount a = userManager.getCorporateAccountById(accountId);
		User u = userManager.getUserByUsername(username);
		if ((a == null) || (u == null)) {
			logger.error("corp acct not found");
			return listCorporateAccountsRedir;
		} else {
			if (mode.equals("add")) {				
				userManager.addUserToCorporateAccount(a, u);
			} else if (mode.equals("remove")) {
				userManager.removeUserFromCorporateAccount(a, u);
			} else {
				// invalid mode is a no-op
				logger.error("invalid mode");
			}
		}
		String redir = listCorporateAccountUsersRedir + "?accountId=" + a.getCorporateAccountId();
		return redir;					
	}
	
	protected static class AddCorporateAccountFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return CorporateAccount.class.equals(clazz);
		}

		public void validate(Object target, Errors errors) {
			CorporateAccount acct = (CorporateAccount) target;
		
			validateString("corporateName", acct.getCorporateName(), errors, 5, 100, regexBasicExtended);
			// XXX finish this out
		}		
	}
	
	protected void setSample(CorporateAccount acct) {
		acct.setCorporateName("Widget Pro Inc.");
		acct.setContactPerson("Judd Bourgeois");
		acct.setPhone("7025450309");
		acct.setAddress1("8142 Making Memories Pl");
		acct.setCity("Las Vegas");
		acct.setState("NV");
		acct.setZip("89131");
		acct.setAccountsAuthorized(5);
    }
}