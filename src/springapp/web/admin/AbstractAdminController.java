package springapp.web.admin;

import java.util.HashMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import springapp.service.user.UserManager;
import springapp.web.NeonController;

abstract public class AbstractAdminController extends NeonController {
	
	protected static final String viewEditCreditCard = "/admin/EditCreditCard";
	protected static final String viewEditCreditCardInvalid = "/admin/EditCreditCardInvalid";
	
	protected static final String viewDeleteUser = "/admin/DeleteUser";

	protected static final String viewCleanupTestUsers = "/admin/DeleteUser";

	protected static final int SESSION_TIMEOUT = 86400;
	protected static final String SESSION_ATTRIBUTE_NAME = "userSession";
	
	protected static final String viewLogin = "/admin/Login";

	protected static final String loginUrl = "/admin/login.do";
	protected static final String logoutUrl = "/admin/logout.do";
	protected static final String indexDefault = "/admin/index.do";

	@Autowired
	protected UserManager userManager;

	protected static final TreeMap<Integer,String> cardExpirationMonths = new TreeMap<Integer,String>();
	protected static final TreeMap<Integer,String> cardExpirationYears = new TreeMap<Integer,String>();
	protected static final HashMap<Integer,String> countryCodes = new HashMap<Integer,String>();

	public void init() {
		for (int i=1; i<=12; i++) {
			cardExpirationMonths.put(i, String.valueOf(i));
		}		
		for (int i=2008; i<=2020; i++) {
			cardExpirationYears.put(i, String.valueOf(i));
		}
		countryCodes.putAll(userManager.getCountryISOCodesById());
	}
	
	// static lookups
	@ModelAttribute("countryCodes")
	public final HashMap<Integer, String> populateCountryCodes() {
		return countryCodes;
	}	
	@ModelAttribute("cardExpirationMonths")
    public final TreeMap<Integer,String> populateCardExpirationMonths() {		
		return cardExpirationMonths;
	}
	@ModelAttribute("cardExpirationYears")
    public final TreeMap<Integer,String> populateCardExpirationYears() {		
		return cardExpirationYears;
	}
}