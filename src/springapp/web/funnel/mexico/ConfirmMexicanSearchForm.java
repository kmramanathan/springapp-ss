package springapp.web.funnel.mexico;

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
import springapp.web.funnel.mexico.PurchaseMexicanRecordForm.PurchaseMexicanRecordFormCommand;
import springapp.web.funnel.mexico.SearchMexicanRecordForm.SearchMexicanRecordFormCommand;

@Controller
@RequestMapping("/funnel/mexico/confirmMexicanSearch.do")

/**
 * Final step in running a one-shot funnel search. Shows the user the
 * search options and prompts for confirmation. 
 * 
 * Upon confirmation, this class runs the search, sets up session info, and
 * redirects to the results display page.
 */
public class ConfirmMexicanSearchForm extends springapp.web.funnel.AbstractFunnelController {
	@Autowired
	private BillingManager billingManager;	
	@Autowired
	private SpringBGCSearchManager bgcManager;
	@Autowired
	private SpringBJLSearchManager bjlManager;

	private CreditCard createCC(PurchaseMexicanRecordFormCommand pmrfc) throws SearchException {
		CreditCard cc = new CreditCard();			
		cc.setName(pmrfc.getName());
		cc.setAddress1(pmrfc.getAddress());
		cc.setCity(pmrfc.getCity());
		cc.setState(pmrfc.getState());
		cc.setPostalCode(pmrfc.getPostalCode());
		cc.setPhone(pmrfc.getPhone());
		
		String ccNum = pmrfc.getCcNumber();
		try {
			cc.setNumber(Crypt.encrypt(ccNum));
		} catch (CryptException e) {
			throw new SearchException(e);
		}
		cc.setLastDigits(Short.parseShort(ccNum.substring(ccNum.length()-4, ccNum.length())));
		cc.setExpMonth(pmrfc.getCcExpMonth().shortValue());
		cc.setExpYear(pmrfc.getCcExpYear().shortValue());
		
		return cc;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map
			) {		
		SearchMexicanRecordFormCommand searchmrfc = (SearchMexicanRecordFormCommand) session.getAttribute("searchMexicanRecordFormCommand");
		
		if (searchmrfc == null) {
			return landingMexicanRecordRedir;
		}
		
		map.addAttribute("searchName", searchmrfc.getMexicanSearchIndividualName());			
		String strRegion = "Nationwide";
		if (searchmrfc.getSearchType().equalsIgnoreCase("state")) { strRegion = searchmrfc.getMexicanSearchStates(); }
		if (searchmrfc.getSearchType().equalsIgnoreCase("region")) { strRegion = searchmrfc.getMexicanSearchRegion(); }
		map.addAttribute("searchRegion", strRegion);
		map.addAttribute("searchRecordType", searchmrfc.getSearchRecordType().substring(0,searchmrfc.getSearchRecordType().indexOf("-")));	
		map.addAttribute("searchPrice", searchmrfc.getSearchRecordType().substring(searchmrfc.getSearchRecordType().indexOf("-") + 1));
		
		String ccNumber = "";
		if (session.getAttribute("creditCardObj") != null) {
			CreditCard card = (CreditCard)session.getAttribute("creditCardObj");
			ccNumber = Crypt.decrypt(card.getNumber());
		} else {
			PurchaseMexicanRecordFormCommand pfc = (PurchaseMexicanRecordFormCommand) session.getAttribute("purchaseMexicanRecordFormCommand");
			ccNumber = pfc.getCcNumber();
		}
		String ccLast4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
		map.addAttribute("ccLast4", ccLast4 );
		
		logger.info("Confirm Mexican Search Form => Credit card number: " + ccLast4 + "; Get Request Redirect: " + confirmMexicanSearchView);
		return confirmMexicanSearchView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			SessionStatus status) {
		PurchaseMexicanRecordFormCommand pfc = (PurchaseMexicanRecordFormCommand) session.getAttribute("purchaseMexicanRecordFormCommand");
		SearchMexicanRecordFormCommand searchmrfc = (SearchMexicanRecordFormCommand) session.getAttribute("searchMexicanRecordFormCommand");
		
		if (searchmrfc == null) {
			return purchaseMexicanSearchRedir;
		}
		
						
		String price = searchmrfc.getSearchRecordType().substring(searchmrfc.getSearchRecordType().indexOf("-") + 1);
		BigDecimal amount = new BigDecimal(price);			
		String description;
		

		//changed by shahul
		short category=7;
		short subCategory=0;
		
		
		if (searchmrfc.getSearchType().equalsIgnoreCase("nation")) {
			description = "Mexico Court Record Search - Nationwide";
			subCategory=50;
			
		} else if (searchmrfc.getSearchType().equalsIgnoreCase("state")) {
			String states = "(" + searchmrfc.getMexicanSearchStates() + ")";
			description = "Mexico Court Record Search - States " + states;
			subCategory=52;
		} else { //region
			String region = "(" + searchmrfc.getMexicanSearchRegion() + ")";
			description = "Mexico Court Record Search - Region " + region;
			subCategory=54;
		}

		//dont charge if its company card
		if (session.getAttribute("noCreditCard") != null && ((String)session.getAttribute("noCreditCard")).equals("1")) {
			if (session.getAttribute("customAccount") != null && ((String)session.getAttribute("customAccount")).equals("1")) {
				return resultsMexicanRecordRedir;
			}
		}
		
		// charge card first, if fails don't bother with search
		Integer invoiceNumber = 0;
		CreditCard cc = null;
		Boolean testCC = false;
		Transaction t = null;
		String ccN = "";
		String ccLast4 =  "";
		
		if (session.getAttribute("creditCardObj") != null) {
			String username=(String)session.getAttribute("userName");
			
			//changed by shahul - getting members detail
			User u = userManager.getUserByUsername(username);
			cc = (CreditCard)session.getAttribute("creditCardObj");
			t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, invoiceNumber, category, subCategory, testCC);
		} else {
			User u = userManager.getUserByUsername(salesFunnelUser);
			
			//changed by shahul - setting email
			u.setEmail(pfc.getEmail());
			
			cc = createCC(pfc);
			testCC = pfc.getTest();
			t = billingManager.runTransactionWithCvv(u, cc, pfc.getCcAuthCode(), amount, TxnType.AUTH_CAPTURE, description, invoiceNumber, category, ++subCategory, testCC);
		}
		
		if (t.getTransactionStatusId() == BillingManager.txnApproved) {
			try {		
				
				//mexico user count
				userManager.salesUserCount();
				
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

				return resultsMexicanRecordRedir;
			} catch (SearchException te) {			
				// void charge
				if (!billingManager.voidTransaction(cc, t)) {
					// maybe send email if this fails?
					logger.error("Failed to void transaction: " + t.getTransactionId());
				//	logger.error("Username: " + u.getUsername());
					logger.error("CC Name: " + t.getCcName());
					logger.error("CC Last 4: " + t.getCcLastDigits());						
				}
	
				// send to error page
				return vwSearchError;			
			} 
		} else {
			map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
			map.addAttribute("search", true);  //used for us criminal record search, to differentiate purchase page from signup process
			map.addAttribute("isMexicanRecordSearch", true);
			return cardDeclinedMexicanSearchView;
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