package springapp.web.corporate;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import net.searchsystems.limestone.bean.BGCResponseBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;


import springapp.crypt.Crypt;
import springapp.crypt.CryptException;
import springapp.domain.CreditCard;
import springapp.domain.Transaction;
import springapp.domain.User;
import springapp.manager.SearchException;
import springapp.manager.SpringBGCSearchManager;
import springapp.manager.SpringBJLSearchManager;
import springapp.service.billing.BillingManager;
import springapp.service.billing.RateManager;
import springapp.service.billing.BillingManager.TxnType;
import springapp.web.funnel.BJLSearchForm.BJLSearchFormCommand;
import springapp.web.funnel.PurchaseForm.PurchaseFormCommand;
import springapp.web.funnel.SearchForm.SearchFormCommand;
import springapp.domain.CreditCard;
import springapp.web.funnel.AbstractFunnelController;


import springapp.web.corporate.CorporateSearchLandingForm.SearchCorporateRecordFormCommand;
import springapp.web.corporate.PurchaseCorporateRecordForm.PurchaseCorporateRecordFormCommand;

/**
 * @author Ram Kumarappan
 *
 */

@Controller
@RequestMapping("/corporate/confirmCorporateSearch.do")

/**
 * Final step in running a one-shot funnel search. Shows the user the
 * search options and prompts for confirmation. 
 * 
 * Upon confirmation, this class runs the search, sets up session info, and
 * redirects to the results display page.
 */
public class ConfirmCorporateSearchForm extends AbstractFunnelController {
	@Autowired
	private BillingManager billingManager;	
	@Autowired
	private SpringBGCSearchManager bgcManager;
	@Autowired
	private SpringBJLSearchManager bjlManager;

	private CreditCard createCC(PurchaseCorporateRecordFormCommand pcrfc) throws SearchException {
		CreditCard cc = new CreditCard();			
		cc.setName(pcrfc.getName());
		cc.setAddress1(pcrfc.getAddress());
		cc.setCity(pcrfc.getCity());
		cc.setState(pcrfc.getState());
		cc.setPostalCode(pcrfc.getPostalCode());
		cc.setPhone(pcrfc.getPhone());
		
		String ccNum = pcrfc.getCcNumber();
		try {
			cc.setNumber(Crypt.encrypt(ccNum));
		} catch (CryptException e) {
			throw new SearchException(e);
		}
		cc.setLastDigits(Short.parseShort(ccNum.substring(ccNum.length()-4, ccNum.length())));
		cc.setExpMonth(pcrfc.getCcExpMonth().shortValue());
		cc.setExpYear(pcrfc.getCcExpYear().shortValue());
		
		return cc; 
	} 
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map
			) {		
		SearchCorporateRecordFormCommand searchcrfc = (SearchCorporateRecordFormCommand) session.getAttribute("searchCorporateRecordFormCommand");
		
		if (searchcrfc == null) {
			return "redirect:corporateSearchLanding.do?type=company";
		}
		
		map.addAttribute("searchCity", searchcrfc.getCorporateSearchCity());	
		map.addAttribute("searchState", searchcrfc.getCorporateSearchCity());
		String strCompanyName="";
		String strOfficerName="";
		String strAddress="";

		if (searchcrfc.getSearchType().equalsIgnoreCase("company")) { 
			strCompanyName= searchcrfc.getCorporateSearchCompanyName();
			map.addAttribute("searchName", strCompanyName);
			}
		if (searchcrfc.getSearchType().equalsIgnoreCase("officer")) {
			strOfficerName = searchcrfc.getCorporateSearchOfficerName(); 
			map.addAttribute("searchName", strOfficerName);
			}
		if (searchcrfc.getSearchType().equalsIgnoreCase("address")) {
			strAddress = searchcrfc.getCorporateSearchAddress(); 
			map.addAttribute("searchName", strAddress);
			}
		
		String ccNumber = "";
		if (session.getAttribute("creditCardObj") != null) {
			CreditCard card = (CreditCard)session.getAttribute("creditCardObj");
			ccNumber = Crypt.decrypt(card.getNumber());
		} else {
			PurchaseCorporateRecordFormCommand pfc = (PurchaseCorporateRecordFormCommand) session.getAttribute("purchaseCorporateRecordFormCommand");
			ccNumber = pfc.getCcNumber();
		}
		String ccLast4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
		map.addAttribute("ccLast4", ccLast4 );
		
		logger.info("Confirm Corporate Search Form => Credit card number: " + ccLast4 + "; Get Request Redirect: " + "corporate/corporateSearchConfirm");
		
		return "corporate/corporateSearchConfirm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			SessionStatus status) {
		PurchaseCorporateRecordFormCommand pfc = (PurchaseCorporateRecordFormCommand) session.getAttribute("purchaseCorporateRecordFormCommand");
		SearchCorporateRecordFormCommand searchcrfc = (SearchCorporateRecordFormCommand) session.getAttribute("searchCorporateRecordFormCommand");
		
		if (searchcrfc == null) {
			return "redirect:purchaseCorporateRecordSearch.do";
		}
		
		
		String username=(String)session.getAttribute("username");
		String price="7";
		if(username != null)
		{
			price="5";
		}
		BigDecimal amount=new BigDecimal(price);
		
		
		String description="Corporate Record Search By "+searchcrfc.getSearchType();
		
		// charge card first, if fails don't bother with search
		Integer invoiceNumber = 0;
		CreditCard cc = null;
		Boolean testCC = false;
		Transaction t = new Transaction();
		String ccN = "";
		String ccLast4 =  "";
		
		short category=7;
		short subCategory=50;
		
		
		if (session.getAttribute("creditCardObj") != null) {
			User u = userManager.getUserByUsername(username);
			cc = (CreditCard)session.getAttribute("creditCardObj");
			
			t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, invoiceNumber, category, subCategory, testCC);
		} else {
			User u = userManager.getUserByUsername("SalesFunnelUser");
			logger.info("Email id inside confirm before set in user ::"+u.getEmail());
			logger.info("Email id inside confirm before set in pfc ::"+pfc.getEmail());
			u.setEmail(pfc.getEmail());
			logger.info("Email id inside confirm after set ::"+u.getEmail());
			cc = createCC(pfc);
			testCC = pfc.getTest();
						
			t = billingManager.runTransactionWithCvv(u, cc, pfc.getCcAuthCode(), amount, TxnType.AUTH_CAPTURE, description, invoiceNumber, category, subCategory, testCC);
		}
		
		if (t.getTransactionStatusId() == BillingManager.txnApproved) {
			try {				
				if (session.getAttribute("creditCardObj") != null) {
					CreditCard card = (CreditCard)session.getAttribute("creditCardObj");
					ccN = Crypt.decrypt(card.getNumber());
					ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
					sendSearchReceiptEmail((String)session.getAttribute("userName"), (String)session.getAttribute("userEmail"), t, description, ccLast4);
				} else {
					ccN = pfc.getCcNumber();
					ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
					sendSearchReceiptEmail(pfc.getName(), pfc.getEmail(), t, description, ccLast4);
				}
				
				session.setAttribute("transactionId", t.getTransactionId());
				session.setAttribute("searchPrice", price);

				return "redirect:resultsCorporateRecords.do";
			} catch (SearchException te) {			
				// void charge
				if (!billingManager.voidTransaction(cc, t)) {
					// maybe send email if this fails?
					logger.error("Failed to void transaction: " + t.getTransactionId());
					//logger.error("Username: " + u.getUsername());
					logger.error("CC Name: " + t.getCcName());
					logger.error("CC Last 4: " + t.getCcLastDigits());						
				}
	
				// send to error page
				return vwSearchError;			
			} 
		} else {
			map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
			map.addAttribute("search", true);  //used for us criminal record search, to differentiate purchase page from signup process
			map.addAttribute("isCorporateRecordSearch", true);
			return "corporate/CardDeclined";
		}
	}
	
	protected void sendSearchReceiptEmail (String customerName, String customerEmail, Transaction t, String description, String ccLast4) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		// add to map: search type, price, status, results, query fields?
		map.put("invoiceNumber", t.getTransactionId());
		map.put("description", description);
		map.put("amount", t.getCost());
		
		map.put("customerName", customerName);
		map.put("customerEmail", customerEmail);
		map.put("ccLast4", ccLast4);

		String subject = "Search Systems Receipt";
        sendEmailVelocity(map, tplSearchReceipt, emailFromNoReply, customerEmail, subject);		
	}
 
}