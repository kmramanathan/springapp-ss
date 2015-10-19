package springapp.web.admin.corp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.CorporateAccount;
import springapp.domain.User;
import springapp.service.user.UserManager;

@Controller
public class ListCorporateAccountsController {
	protected final Logger logger = Logger.getLogger(getClass()); 
	
	@Autowired
	private UserManager userManager;
	
	protected static final String listCorporateAccountsView = "admin/ListCorporateAccounts";
	protected static final String listCorporateAccountUsersView = "admin/ListCorporateAccountUsers";
	
	@RequestMapping("/admin/listCorporateAccounts.do")
	public ModelAndView getCorporateAccounts(ModelMap model) {
		List<CorporateAccount> list = userManager.getAllCorporateAccounts();
		return new ModelAndView(listCorporateAccountsView, "accounts", list);
	}
	
	@RequestMapping("/admin/listCorporateAccountUsers.do")
	public String getCorporateAccountUsers(
			ModelMap model,
			@RequestParam(value="accountId") Integer accountId
			) {
		CorporateAccount a = userManager.getCorporateAccountById(accountId);
		if (a == null) {
			return listCorporateAccountsView;
		}
		
		List<User> list = userManager.getCorporateAccountUsers(accountId);
		model.addAttribute("a", a);
		model.addAttribute("users", list);
		return listCorporateAccountUsersView;
	}
}