package springapp.web.conversion;

import org.apache.log4j.Logger;

abstract public class AbstractConversionController {
	protected Logger logger = Logger.getLogger(getClass());
	
	protected static final int SESSION_TIMEOUT = 3600;
	
	protected static final int LOGIN_DISPLAY_DEFAULT = 0;
	protected static final int LOGIN_DISPLAY_MONTHLY = 1;
	protected static final int LOGIN_DISPLAY_ANNUAL  = 2;
	protected static final int LOGIN_DISPLAY_PREMIUM = 3;
	/*
	 * upgrades:
	 * 1) annual 48.50 to annual 29.95
	 * 2) monthly 4.95 to annual 29.95
	 * 3) premium-only to annual 29.95 (?)  
	 */

	/*
	 * web path:
	 * 1) login
	 * 2) verify billing
	 * 3) confirm
	 * 4) success
	 */
	protected static final int UPGRADE_RATE_MONTHLY = 84;
	protected static final int UPGRADE_RATE_ANNUAL  = 85;

	protected static final String loginRedir = "redirect:userLogin.do";
	protected static final String verifyBillingRedir = "redirect:verifyBilling.do";
	protected static final String confirmRedir = "redirect:confirm.do";
	protected static final String successRedir = "redirect:success.do";

	protected static final String vwUserLogin = "conversion/UserLogin";
	protected static final String vwVerifyBilling = "conversion/VerifyBilling";
	protected static final String vwConfirm = "conversion/Confirm";
	protected static final String vwSuccess = "conversion/Success";	

	protected static final String emailFromThankYou = "thank-you@searchsystems.net";
	protected static final String emailFromNoReply = "no-reply@searchsystems.net";
	protected static final String emailToAdmin = "judd@searchsystems.net";
	
	protected static final String tplUpgradeThankYou = "springapp/web/conversion/UpgradeThankYouEmailTemplate.vm";

}