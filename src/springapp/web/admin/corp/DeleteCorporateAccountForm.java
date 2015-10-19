package springapp.web.admin.corp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springapp.domain.CorporateAccount;
import springapp.service.user.UserManager;

@Controller
@RequestMapping("/admin/deleteCorporateAccount.do")

public class DeleteCorporateAccountForm {
	protected final Logger logger = Logger.getLogger(getClass());

	protected static final String deleteView = "admin/DeleteCorporateAccount";
	protected static final String listRedir = "redirect:listCorporateAccounts.do";
	
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			ModelMap map,
			@RequestParam("accountId") Integer accountId
			) {
		CorporateAccount acct = userManager.getCorporateAccountById(accountId);
		if (acct == null) {
			//map.addAttribute("errorMsg", "The account could not be found.");
			return listRedir;
		}
		//map.addAttribute("accountId", accountId);
		map.addAttribute("acct", acct);
		return deleteView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			ModelMap map,
			@RequestParam("accountId") Integer accountId,
			@RequestParam("confirm") String confirm
			) {
		CorporateAccount acct = userManager.getCorporateAccountById(accountId);
		if (acct == null) {
			map.addAttribute("errorMsg", "The account could not be found.");
			return deleteView;
		} else {
			map.addAttribute("acct", acct);
			if (!confirm.equals("confirm")) {
				map.addAttribute("errorMsg", "You must type confirm to continue.");
				return deleteView;			
			} else if (acct.getAccountsInuse() > 0) {
				map.addAttribute("errorMsg", "The account still has users linked to it.");
				return deleteView;						
			} else {
				userManager.deleteCorporateAccount(acct);
				return listRedir;
			}
		}
	}
}