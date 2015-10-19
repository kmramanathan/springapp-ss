package springapp.web.funnel;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import net.searchsystems.limestone.bean.BGCResponseBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

@Controller

/**
 * Final step in running a one-shot funnel search. Shows the user the
 * search options and prompts for confirmation. 
 * 
 * Upon confirmation, this class runs the search, sets up session info, and
 * redirects to the results display page.
 */
public class ConfirmSearchForm extends AbstractFunnelController {
	@Autowired
	private BillingManager billingManager;	
	@Autowired
	private SpringBGCSearchManager bgcManager;
	@Autowired
	private SpringBJLSearchManager bjlManager;

	private String getSearchTypeString(SearchFormCommand searchfc) {
		String searchType;
		if (searchfc.getNationwideSearch()) {
			searchType = "Nationwide";
		} else {
			searchType = "State (" + searchfc.getBgcState() + ")";
		}
		return searchType;
	}

	private String getSearchTypeString(BJLSearchFormCommand bjlsfc) {
		logger.info("search type: " + bjlsfc.getBjlSearchType());
		String searchType;
		switch (bjlsfc.getBjlSearchType()) {
		case NAME:
			searchType = "Name";
			break;
		case ADDRESS:
			searchType = "Name and State, City or ZIP Code";
			break;
		case SSN:
			searchType = "SSN / Tax ID";
			break;
		default:
			searchType = "Unknown";		
		}
		return searchType;
	}
	
	private CreditCard createCC(PurchaseFormCommand pfc) throws SearchException {
		CreditCard cc = new CreditCard();			
		cc.setName(pfc.getName());
		cc.setAddress1(pfc.getAddress());
		cc.setCity(pfc.getCity());
		cc.setState(pfc.getState());
		cc.setPostalCode(pfc.getPostalCode());
		cc.setPhone(pfc.getPhone());
		
		logger.info("countryCode: " + pfc.getCountryCode());
		Integer cid = userManager.getCountryIdByCode(pfc.getCountryCode());
		logger.info("countryId: " + cid);
		cc.setCountryId(cid.shortValue());
		
		String ccNum = pfc.getCcNumber();
		try {
			cc.setNumber(Crypt.encrypt(ccNum));
		} catch (CryptException e) {
			throw new SearchException(e);
		}
		cc.setLastDigits(Short.parseShort(ccNum.substring(ccNum.length()-4, ccNum.length())));
		cc.setExpMonth(pfc.getCcExpMonth().shortValue());
		cc.setExpYear(pfc.getCcExpYear().shortValue());
		
		return cc;
	}
	
	@RequestMapping(value = "/funnel/confirmSearch.do", method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map
			) {		
		PurchaseFormCommand pfc = (PurchaseFormCommand) session.getAttribute("purchaseFormCommand");

		SearchFormCommand searchfc = (SearchFormCommand) session.getAttribute("searchFormCommand");
		BJLSearchFormCommand bjlsfc = (BJLSearchFormCommand) session.getAttribute("bjlSearchFormCommand");
		
		// pfc is mandatory here
		if (pfc == null) {
			return landingRedir;
		}
		// at least 1 of the other 2 must be present also
		if ((searchfc == null) && (bjlsfc == null)) {
			return landingRedir;
		}
		
		// now determine what to do based on which other form is present	
		map.addAttribute("search", true);
		
		String ccNumber = pfc.getCcNumber();
		String ccLast4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
		map.addAttribute("ccLast4", ccLast4 );
		
		// determine which type of search: crim or BJL
		if (bjlsfc != null) {				
			// add BJL fields
			map.addAttribute("searchPrice", bjlsfc.getPrice());
			map.addAttribute("searchName", bjlsfc.getBjlName());
			map.addAttribute("searchState", bjlsfc.getBjlDefendantState());
			map.addAttribute("searchBJLType", bjlsfc.getBjlCaseType());
			map.addAttribute("searchSsnTax", bjlsfc.getBjlSsnTaxId());
		
			return BJLconfirm;
			
		} else {
			// add crim fields
			map.addAttribute("searchCriminal", true);
			map.addAttribute("searchPrice", searchfc.getPrice());			
			map.addAttribute("searchType", getSearchTypeString(searchfc));			
			
			String searchName = searchfc.getBgcFirstName() + " " + searchfc.getBgcLastName();
			map.addAttribute("searchName", searchName);
			
			String searchDOB = searchfc.getBgcDobMonth() + "/" + searchfc.getBgcDobDay() + "/" + searchfc.getBgcDobYear();
			
			//added by Ram - 16 APRIL 2009
			if (searchfc.getBgcDobRange())
				searchDOB = searchfc.getBgcDobRangeBaseYear() + " (within " + searchfc.getBgcDobRangeFuzz() + " years)";
			
			map.addAttribute("searchDOB", searchDOB);				
		}
		
		return confirmView;
	}
	
	@RequestMapping(value = "/funnel/confirmSearch.do", method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			SessionStatus status) {
		PurchaseFormCommand pfc = (PurchaseFormCommand) session.getAttribute("purchaseFormCommand");

		SearchFormCommand searchfc = (SearchFormCommand) session.getAttribute("searchFormCommand");
		BJLSearchFormCommand bjlsfc = (BJLSearchFormCommand) session.getAttribute("bjlSearchFormCommand");
		
		// pfc is mandatory here
		if (pfc == null) {
			return purchaseSearchRedir;
		}
		// at least 1 of the other two must be present also
		if ((searchfc == null) && (bjlsfc == null)) {
			return purchaseSearchRedir;
		}
		
		// now determine what to do based on which other form is present

		//changed by shahul - setting category
		short category = 0;
		short subCategory=0;
		
		
		// required for all funnel searches
		User u = userManager.getUserByUsername(salesFunnelUser);			
		Boolean testCC = pfc.getTest();			
		
		// determine which type of search: crim or BJL
		if (bjlsfc != null) {
			// do BJL
			category = 11;
			BigDecimal amount = bjlsfc.getPrice();			
			String description = "Bankruptcy, Judgment, Tax Lien - " + bjlsfc.getBjlSearchType();

			// charge card first, if fails don't bother with search
			Integer invoiceNumber = 0;
			
			//setting email
			u.setEmail(pfc.getEmail());
			
			CreditCard cc = createCC(pfc);
			
			//setting description in admin
			String searchType = bjlsfc.getBjlSearchType().toString();
			
			if(searchType.equalsIgnoreCase("NAME")){
				subCategory = 110;
			}else if(searchType.equalsIgnoreCase("ADDRESS")){
				subCategory = 111;
			}else{
				subCategory = 112;
			}

			Transaction t = billingManager.runTransactionWithCvv(u, cc, pfc.getCcAuthCode(), amount, TxnType.AUTH_CAPTURE, description, invoiceNumber, category, subCategory, testCC);
			if (t.getTransactionStatusId() == BillingManager.txnApproved) {
				try {				
					int userSearchId = runBJLSearch(session, map, status, bjlsfc, t);
					String ccN = pfc.getCcNumber();					
					String ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
					sendSearchReceiptEmail(pfc.getName(), pfc.getEmail(), t, description, ccLast4);

					// req id is set in runSearch()
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", bjlsfc.getPrice());
					return resultsBjlRedir;
					
				} catch (SearchException te) {			
					// void charge
					if (!billingManager.voidTransaction(cc, t)) {
						// maybe send email if this fails?
						logger.error("Failed to void transaction: " + t.getTransactionId());
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + t.getCcName());
						logger.error("CC Last 4: " + t.getCcLastDigits());
					}
		
					// send to error page
					return vwSearchError;			
				} finally {
					session.removeAttribute("bjlSearchFormCommand");
				}
			} else {
				map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
				map.addAttribute("search", true);
				return cardDeclinedView;
			}   
	
			// end of BJL
			
		} else {
			// do criminal
	
			//changed by shahul - setting category for criminal search
			category=2;
			
			BigDecimal amount = searchfc.getPrice();			
			String description;
			if (searchfc.getNationwideSearch()) {
				description = "Criminal Record Search - Nationwide";
				subCategory=30;
				
			} else {
				String state = "(" + searchfc.getBgcState() + ")";
				description = "Criminal Record Search - State " + state;
				subCategory=31;
			}
			
			
			// charge card first, if fails don't bother with search
			Integer invoiceNumber = 0;
			
			//changed by shahul - setting email
			u.setEmail(pfc.getEmail());
			
			CreditCard cc = createCC(pfc);
			Transaction t = billingManager.runTransactionWithCvv(u, cc, pfc.getCcAuthCode(), amount, TxnType.AUTH_CAPTURE, description, invoiceNumber, category, subCategory, testCC);
			
			if (t.getTransactionStatusId() == BillingManager.txnApproved) {
				try {				
					int responseId = runCriminalSearch(session, map, status, searchfc, t);

					String ccN = pfc.getCcNumber();					
					String ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
					sendSearchReceiptEmail(pfc.getName(), pfc.getEmail(), t, description, ccLast4);

					// req id is set in runSearch()
					session.setAttribute("responseId", responseId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", searchfc.getPrice());

					return resultsRedir;
				} catch (SearchException te) {			
					// void charge
					if (!billingManager.voidTransaction(cc, t)) {
						// maybe send email if this fails?
						logger.error("Failed to void transaction: " + t.getTransactionId());
						logger.error("Username: " + u.getUsername());
						logger.error("CC Name: " + t.getCcName());
						logger.error("CC Last 4: " + t.getCcLastDigits());						
					}
		
					// send to error page
					return vwSearchError;			
				} finally {
					session.removeAttribute("searchFormCommand");
					session.setAttribute("campaign", searchfc.getCampaignId());
				}
			} else {
				map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
				map.addAttribute("search", true);
				return cardDeclinedView;
			}
			
			// end of criminal
		}
		

	}
	
	//XXX Fix this. Temp., getting BJL member search from Signup
	@RequestMapping(value = "/funnel/BJLMemberSearch.do", method = RequestMethod.GET)
	public String memberBJLSubmit(
			HttpSession session,
			ModelMap map,
			SessionStatus status) {
		
		BJLSearchFormCommand bjlsfc = (BJLSearchFormCommand) session.getAttribute("bjlSearchFormCommand");

		// at least 1 of the other two must be present also
		if (bjlsfc == null) {
			return searchBJLRedir;
		}
		
		//setting category
		short category = 11;
		short subCategory=0;
				
		BigDecimal amount=new BigDecimal("5");
		
		String description = "Bankruptcy, Judgment, Tax Lien - " + bjlsfc.getBjlSearchType();
		
		// charge card first, if fails don't bother with search
		Integer invoiceNumber = 0;
		CreditCard cc = null;
		Transaction t = new Transaction();
		String ccN = "";
		String ccLast4 =  "";
		
		String username=(String)session.getAttribute("username");
		if(username == null){
			return searchBJLRedir;
		}
		
		//Setting admin description
		String searchType = bjlsfc.getBjlSearchType().toString();
		if(searchType.equalsIgnoreCase("NAME")){
			subCategory = 113;
		}else if(searchType.equalsIgnoreCase("ADDRESS")){
			subCategory = 114;
		}else{
			subCategory = 115;
		}
		
		// charge card first, if fails don't bother with search
				
		if (session.getAttribute("creditCardObj") != null) {
			
			User u = userManager.getUserByUsername(username);
			cc = (CreditCard)session.getAttribute("creditCardObj");
				
			t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, invoiceNumber, category, subCategory);
		} 		
		if (t.getTransactionStatusId() == BillingManager.txnApproved) {
				try {				
					int userSearchId = runBJLSearch(session, map, status, bjlsfc, t);
					CreditCard card = (CreditCard)session.getAttribute("creditCardObj");
					ccN = Crypt.decrypt(card.getNumber());
					ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
					sendSearchReceiptEmail((String)session.getAttribute("username"), (String)session.getAttribute("userEmail"), t, description, ccLast4);

					// req id is set in runSearch()
					session.setAttribute("userSearchId", userSearchId);
					session.setAttribute("transactionId", t.getTransactionId());
					session.setAttribute("searchPrice", bjlsfc.getPrice());
					return resultsBjlRedir;
					
				} catch (SearchException te) {			
					// void charge
					if (!billingManager.voidTransaction(cc, t)) {
						// maybe send email if this fails?
						logger.error("Failed to void transaction: " + t.getTransactionId());
						logger.error("Username: " + session.getAttribute("username"));
						logger.error("CC Name: " + t.getCcName());
						logger.error("CC Last 4: " + t.getCcLastDigits());
					}
		
					// send to error page
					return vwSearchError;			
				} finally {
					session.removeAttribute("bjlSearchFormCommand");
				}
			} else {
				map.addAttribute("cardDeclineReason", t.getBankResponseReasonText());
				map.addAttribute("search", true);
				return cardDeclinedView;
			}   
		
			// end of BJL
 	}

	protected int runBJLSearch(HttpSession session, ModelMap map, SessionStatus status, 
			BJLSearchFormCommand bjlsfc, Transaction t) 
	throws SearchException {
		User u = userManager.getUserByUsername(salesFunnelUser);

		// check for test
		if (bjlsfc.getTest()) {
			return TEST_BJL_RESULT_ID;
		}
		
		// run query
		long result = bjlManager.queryFunnel(u.getUserId(), t.getTransactionId(), 
				bjlsfc.getBjlName(), bjlsfc.getBjlSsnTaxId(), 
				bjlsfc.getBjlDefendantCity(), bjlsfc.getBjlDefendantState(), bjlsfc.getBjlDefendantZip(),
				bjlsfc.getBjlNameType(),bjlsfc.getBjlDefendantState(), bjlsfc.getBjlCaseType(), null);
		return (int) result;
	}
	
	protected int runCriminalSearch(HttpSession session, ModelMap map, SessionStatus status, 
			 SearchFormCommand sfc, Transaction t) 
	throws SearchException {
		User u = userManager.getUserByUsername(salesFunnelUser);
		
		if (sfc.getTest()) {
			return TEST_CRIMINAL_RESULT_ID;
		}
		
		String jurisdiction;
		if (sfc.getNationwideSearch()) {
			jurisdiction = "Nationwide";
		} else {
			jurisdiction = sfc.getBgcState();
		}

		if (sfc.getBgcDobRange()) {
			sfc.setBgcDobYear(sfc.getBgcDobRangeBaseYear());
		}
		
		// XXX fix product id
		
		// run search
		int requestId = bgcManager.prepareSearch(u.getUserId(), 
				sfc.getBgcFirstName(), sfc.getBgcMiddleInitial(), sfc.getBgcLastName(), 
				sfc.getBgcFirstNameExact(), sfc.getBgcLastNameExact(), 
				sfc.getBgcDobMonth(), sfc.getBgcDobDay(), sfc.getBgcDobYear(), 
				sfc.getBgcDobRangeFuzz(), sfc.getBgcDobRange(), sfc.getBgcMatchMissingDates(), 
				bgcTestProductId, false, jurisdiction, sfc.getBgcPurpose(), "");
		logger.info("requestId: " + requestId);
		//session.setAttribute("bgcRequestId", requestId);
					
		BGCResponseBean response = bgcManager.runSearch(requestId);
		int responseId = response.getBgcResponseId();
		logger.info("responseId: " + responseId);

		// record the txn id
		bgcManager.setTransactionId(responseId, (int) t.getTransactionId());

		// finalize charge?
		// XXX todo
		
		//return resultsView;
		return responseId;
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