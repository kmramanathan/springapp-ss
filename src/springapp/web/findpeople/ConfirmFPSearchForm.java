package springapp.web.findpeople;

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
import springapp.web.findpeople.PurchaseFPForm.PurchaseFPFormCommand;
import springapp.web.findpeople.SearchLandingForm.SearchFPFormCommand;
import springapp.web.funnel.BJLSearchForm.BJLSearchFormCommand;
import springapp.web.funnel.PurchaseForm.PurchaseFormCommand;
import springapp.web.funnel.SearchForm.SearchFormCommand;
import springapp.domain.CreditCard;
import springapp.web.funnel.AbstractFunnelController;


/**
 * @author Shahul Dhasthagir
 *
 */

@Controller
@RequestMapping("/findpeople/confirmFPSearch.do")

/**
 * Final step in running a one-shot funnel search. Shows the user the
 * search options and prompts for confirmation. 
 * 
 * Upon confirmation, this class runs the search, sets up session info, and
 * redirects to the results display page.
 */
public class ConfirmFPSearchForm extends AbstractFunnelController {
	@Autowired
	private BillingManager billingManager;	
	
	private CreditCard createCC(PurchaseFPFormCommand pfpfc) throws SearchException {
		CreditCard cc = new CreditCard();			
		cc.setName(pfpfc.getName());
		cc.setAddress1(pfpfc.getAddress());
		cc.setCity(pfpfc.getCity());
		cc.setState(pfpfc.getState());
		cc.setPostalCode(pfpfc.getPostalCode());
		cc.setPhone(pfpfc.getPhone());
		
		Integer cid = userManager.getCountryIdByCode("US");
		cc.setCountryId(cid.shortValue());
		
		String ccNum = pfpfc.getCcNumber();
		try {
			cc.setNumber(Crypt.encrypt(ccNum));
		} catch (CryptException e) {
			throw new SearchException(e);
		}
		cc.setLastDigits(Short.parseShort(ccNum.substring(ccNum.length()-4, ccNum.length())));
		cc.setExpMonth(pfpfc.getCcExpMonth().shortValue());
		cc.setExpYear(pfpfc.getCcExpYear().shortValue());
		
		return cc; 
	} 
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map, 
			@RequestParam(value = "requestFrom", required = false) String requestFrom,
			@RequestParam(value = "resultType", required = false) String rType
			) {		
		
		String searchType= "Find People";
		String price="0";
		Boolean member=false;
		String ccNumber = "";
		String ccLast4="";
		boolean freeBGR = false;
		
		if (session.getAttribute("searchFPFormCommand") == null) {
			return "redirect:searchLanding.do?searchType=basic";
		}
		
		SearchFPFormCommand sfpfc = (SearchFPFormCommand) session.getAttribute("searchFPFormCommand");
		map.addAttribute("searchName", sfpfc.getSearchFirstName()+" "+sfpfc.getSearchLastName());	
		map.addAttribute("searchState", sfpfc.getSearchState());
		
		//Only for flat rate
		if(rType != null){
			session.setAttribute("resultType", rType);
		}		
		if(session.getAttribute("FRBGRrequest") != null){
			session.removeAttribute("FRBGRrequest");
		}
		//Identify the flat rate price up sell
		if(session.getAttribute("FRusername") != null && requestFrom != null && requestFrom.equalsIgnoreCase("flatrate")){
			session.setAttribute("FRBGRrequest", true);
		}	
		//special codes. Overwrite the price for Flat rate members
		if(session.getAttribute("FRBGRrequest") != null){
			map.addAttribute("Type", "Background Report");
			if(session.getAttribute("FRcreditCardObj") != null){
				CreditCard card = (CreditCard)session.getAttribute("FRcreditCardObj");
				ccNumber = Crypt.decrypt(card.getNumber());
				ccLast4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
			}else if(session.getAttribute("FRnoCreditCard") != null){
				ccLast4 = "No Card";
			}			
			map.addAttribute("ccLast4", ccLast4 );
			price = "9.95";
			map.addAttribute("Price", price);
			return "findpeople/Confirm";
		}	
		
		
		
		String resultType=(String)session.getAttribute("resultType");
		if(resultType.equals("BG")){
			searchType = "Background Report";
			// Getting the Free BGR users
			if(session.getAttribute("userId") != null){
				int userId = (Integer)session.getAttribute("userId");
					freeBGR = userManager.getFreeBgrUser(userId);
				if(freeBGR){
					searchType = "Free Background Search";
					session.setAttribute("freeBGR", freeBGR);
				}
			}
		}
		map.addAttribute("Type", searchType);
				
		if (session.getAttribute("noCreditCard") != null && session.getAttribute("noCreditCard").equals("1")) {
			member= true;
			ccLast4 = "No Card";
			map.addAttribute("ccLast4", ccLast4 );
		}else{
			if (session.getAttribute("creditCardObj") != null) {
					member=true;
					CreditCard card = (CreditCard)session.getAttribute("creditCardObj");
					ccNumber = Crypt.decrypt(card.getNumber());
			} else {
					PurchaseFPFormCommand pfpfc = (PurchaseFPFormCommand) session.getAttribute("purchaseFPFormCommand");
					ccNumber = pfpfc.getCcNumber();
			}
			ccLast4 = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
			map.addAttribute("ccLast4", ccLast4 );
		}
		
				
		//setting cost for search
		if(resultType.equals("one"))
		{
			price="1.75";
			if(member){
				price="1.00";
			}
		}
		else if(resultType.equals("BG"))
		{
			price="19.95";
			if(member){
				price="14.95";
			}
			
		}
		if(freeBGR){
			price = "0";
		}
		
		map.addAttribute("Price", price);
				
		logger.info("Confirm Find people Search Form => Credit card number: " + ccLast4 + "; Get Request Redirect: " + "findpeople/Confirm");
		
		return "findpeople/Confirm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			SessionStatus status,
			@RequestParam(value="Price",required=true) String Price
			) {
		PurchaseFPFormCommand pfpfc = (PurchaseFPFormCommand) session.getAttribute("purchaseFPFormCommand");
		SearchFPFormCommand sfpfc = (SearchFPFormCommand) session.getAttribute("searchFPFormCommand");
		
		logger.info("Amount to be charged"+Price);
		
		if (sfpfc == null) {
			return "redirect:purchaseFPSearch.do";
		}
				
		String username=(String)session.getAttribute("username");
		
		BigDecimal amount=new BigDecimal(Price);
		
		String description="Find people Record Search By "+sfpfc.getSearchType();
		
		// charge card first, if fails don't bother with search
		Integer invoiceNumber = 0;
		CreditCard cc = null;
		Boolean testCC = false;
		Transaction t = new Transaction();
		String ccN = "";
		String ccLast4 =  "";
		short category=9;
		short subCategory=0;
		String resultPage="";
		
		String resultType=(String)session.getAttribute("resultType");
		
		//setting description & return page
					
			if(resultType.equals("one")){
				subCategory=90;
				resultPage ="redirect:resultsFPRecords.do?resultType=one";
			}
			
			if(resultType.equals("BG")){
				subCategory=92;
				resultPage ="redirect:resultsFPRecords.do?resultType=BG";
			}		
					
				
		   // Specially coded for flat rate members - No card users
			if(session.getAttribute("FRBGRrequest") != null && session.getAttribute("FRnoCreditCard") != null){
				return resultPage;
			}
						
	       // not charging for company credit card and Free BGR
			if(session.getAttribute("noCreditCard") != null && session.getAttribute("noCreditCard").equals("1")
					|| session.getAttribute("freeBGR") != null ){
				return resultPage;
			}
			
			 // Specially coded for flat rate members	
			if(session.getAttribute("FRBGRrequest") != null){
				String FRusername = (String) session.getAttribute("FRusername");
				if(session.getAttribute("FRcreditCardObj") != null){
						User u = userManager.getUserByUsername(FRusername);
						cc = (CreditCard)session.getAttribute("FRcreditCardObj");
						subCategory = 99;	
						
						t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, invoiceNumber, category, subCategory);
					
				}
			}else if(session.getAttribute("creditCardObj") != null ) {
				User u = userManager.getUserByUsername(username);
				cc = (CreditCard)session.getAttribute("creditCardObj");
				subCategory++;
			
				t = billingManager.runTransactionNoCvv(u, cc, amount, TxnType.AUTH_CAPTURE, description, invoiceNumber, category, subCategory);
			}else{		
				User u = userManager.getUserByUsername(salesFunnelUser);
				u.setEmail(pfpfc.getEmail());
				cc = createCC(pfpfc);
				testCC = pfpfc.getTest();
						
				t = billingManager.runTransactionWithCvv(u, cc, pfpfc.getCcAuthCode(), amount, TxnType.AUTH_CAPTURE, description, invoiceNumber, category, subCategory, testCC);
			} 
		
			if (t.getTransactionStatusId() == BillingManager.txnApproved) {
				try {
					if (session.getAttribute("FRcreditCardObj") != null && session.getAttribute("FRBGRrequest") != null) {
						CreditCard card = (CreditCard)session.getAttribute("FRcreditCardObj");
						ccN = Crypt.decrypt(card.getNumber());
						ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
						User u = userManager.getUserByUsername((String) session.getAttribute("FRusername"));
						sendSearchReceiptEmail(u.getUsername(), u.getEmail(), t, description, ccLast4);
					}else if (session.getAttribute("creditCardObj") != null) {
						CreditCard card = (CreditCard)session.getAttribute("creditCardObj");
						ccN = Crypt.decrypt(card.getNumber());
						ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
						sendSearchReceiptEmail((String)session.getAttribute("username"), (String)session.getAttribute("userEmail"), t, description, ccLast4);
					}else{
						ccN = pfpfc.getCcNumber();
						ccLast4 = ccN.substring(ccN.length() - 4, ccN.length());
						sendSearchReceiptEmail(pfpfc.getName(), pfpfc.getEmail(), t, description, ccLast4);
					}
				
				session.setAttribute("transactionId", t.getTransactionId());
				session.setAttribute("searchPrice", Price);
				logger.info("Doing search for transaction id : "+t.getTransactionId());
				return resultPage;
				
				
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
			map.addAttribute("search", true);  
			map.addAttribute("isCorporateRecordSearch", true);
			return "findpeople/CardDeclined";
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