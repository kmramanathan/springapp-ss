package springapp.web.admin.reports;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.CorporateAccount;
import springapp.service.search.OrdersManager;
import springapp.service.user.UserManager;

@Controller
public class CorporateInvoicingController {
	protected final Logger logger = Logger.getLogger(getClass());    

	protected static final String displayCorporateInvoicingView = "/admin/reports/CorporateInvoicing";
	protected static final String displayFRInvoicingView = "/admin/reports/FRInvoicing";
	
	protected static final String fieldsBgc[] = { 
		"bgc_request_id", "username", "date_created", 
		"bgc_product_name", "bgc_product_desc", "price", "quantity_returned",
		"first_name", "last_name", "reference_code"
	};
	protected static final String fieldsNss[] = { 
		"nss_request_id", "username", "date_created", 
		"bgc_product_name", "bgc_product_desc", "price", "quantity_returned",
		"first_name", "last_name", "reference_code"
	};
	protected static final String fieldsBJL[] = { 
		"user_search_id", "username",
		"create_date", "category", "subcategory", "price", "match_count", 
		"first_name", "last_name", "business_name", "ssn","state", "reference_code"
	};
	protected static final String fieldsFP[] = { 
		"request_id", "username",
		"product", "created_date", "first_name", "last_name","state", 
		"price"
	};
	protected static final String fieldsTS[] = { 
		"user_search_id", "username",
		"create_date", "category", 
		"firstname", "lastname", "businessname", "match_count", "price", "state"
	};
	protected static final String fieldsCS[] = { 
		"user_search_id", "username",
		"create_date", "category", 
		"firstname", "lastname", "businessname", "match_count", "price", "state"
	};
	protected static final String FlatRate[] = { 
		"subscriptionid", "username","rate_name","rate_description",
		"startdate", "enddate","recurring_price", 
		"createdate"
	};

	protected static final TreeMap<Integer, String> monthsMap = new TreeMap<Integer, String>();
	protected static final TreeMap<Integer, String> yearsMap = new TreeMap<Integer, String>();
	protected static final TreeMap<Integer, String> searchTypesMap = new TreeMap<Integer, String>();
	
	public void init() {
		logger.info("calling init()");
		// set up months
		Calendar instance = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM"); 
		// XXX aiee, java.util.Calendar uses 0 based months
		for (int i=0; i<12; i++) {
			instance.set(2008, i, 1);
			monthsMap.put(i+1, sdf.format(instance.getTime()));
		}
		for (int i=2008; i<2019; i++) {
			yearsMap.put(i, String.valueOf(i));
		}
		// only 2 search types for now
		searchTypesMap.put(1, "Criminal");
		searchTypesMap.put(2, "BJL (BK, Judgment, Tax Lien)");
		searchTypesMap.put(3, "TENANT SCREENING (tenant screening/eviction searches)");
		searchTypesMap.put(4, "Corporation Searches");
		searchTypesMap.put(5, "National Security Search");
	}
	
	@Autowired
	private UserManager userManager;
	@Autowired
	private OrdersManager ordersManager;

	@RequestMapping(value="/admin/reports/FRInvoicing.do", method=RequestMethod.GET)
	public String displayFRInvoicing(ModelMap map) throws Exception {
		map.addAttribute("command", new FRInvoicingFormCommand());
		return displayFRInvoicingView;
    }

	@RequestMapping(value="/admin/reports/FRInvoicing.do", method=RequestMethod.POST)
	public ModelAndView handleFRInvoicing(
			@ModelAttribute("command") FRInvoicingFormCommand cmd,
			Errors errors,
			SessionStatus status
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(displayFRInvoicingView);
		
		// XXX determine time range
		Integer searchType = cmd.getSearchType();
		Integer month = cmd.getMonth();
		Integer year = cmd.getYear();
		Integer userId = cmd.getUserId();
		
		// get searches (crim or BJL)
		List<Map<String, Object>> searches;
		if (searchType == 1) {
			searches = ordersManager.getFRInvoiceCriminalMonth(userId, month, year);
		} else if (searchType == 2) {
			searches = ordersManager.getFRInvoiceBJLMonth(userId, month, year);
		} else if (searchType == 3) {
			searches = ordersManager.getFRInvoiceFPMonth(userId, month, year);
		} else if (searchType == 4) {
			searches = ordersManager.getFRInvoiceFlatRate(userId, month, year);
		} else {
			searches = null;
		}
		CorporateAccount corp = userManager.getCorporateAccountByUserId(cmd.getUserId());
		mav.addObject("corp", corp);
		mav.addObject("userId", cmd.getUserId());
		mav.addObject("searchType", cmd.getSearchType());
		mav.addObject("month", cmd.getMonth());
		mav.addObject("year", cmd.getYear());
		mav.addObject("searches", searches);
		mav.addObject("searchCount", searches.size());
		return mav;
	}
	
	@RequestMapping(value="/admin/reports/corporateInvoicing.do", method=RequestMethod.GET)
	public String displayCorporateInvoicing(ModelMap map) throws Exception {
		map.addAttribute("command", new CorporateInvoicingFormCommand());
		return displayCorporateInvoicingView;
    }

	@RequestMapping(value="/admin/reports/corporateInvoicing.do", method=RequestMethod.POST)
	public ModelAndView handleCorporateInvoicing(
			@ModelAttribute("command") CorporateInvoicingFormCommand cmd,
			Errors errors,
			SessionStatus status
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(displayCorporateInvoicingView);
		
		// XXX determine time range
		Integer searchType = cmd.getSearchType();
		Integer month = cmd.getMonth();
		Integer year = cmd.getYear();
		Integer accountId = cmd.getAccountId();
		
		// get searches (crim, national security, corporation, tanent screening or BJL)
		List<Map<String, Object>> searches;
		if (searchType == 1) {
			searches = ordersManager.getCorporateInvoiceCriminalMonth(accountId, month, year);
		} else if (searchType == 2) {
			searches = ordersManager.getCorporateInvoiceBJLMonth(accountId, month, year);
		} else if (searchType == 3) {
			searches = ordersManager.getCorporateInvoiceTSMonth(accountId, month, year);
		} else if (searchType == 4) {
			searches = ordersManager.getCorporateInvoiceCSMonth(accountId, month, year);
		} 
		else if (searchType == 5) {
			searches=ordersManager.getCorporateInvoiceNSSMonth(accountId, month, year);
		}else {
			searches = null;
		}
		mav.addObject("accountId", cmd.getAccountId());
		mav.addObject("searchType", cmd.getSearchType());
		mav.addObject("month", cmd.getMonth());
		mav.addObject("year", cmd.getYear());
		mav.addObject("searches", searches);
		mav.addObject("searchCount", searches.size());
		return mav;
	}
	
	@RequestMapping(value="/admin/reports/corporateInvoicingDownload.do", method=RequestMethod.GET)
	public void downloadCorporateInvoicing(			
			HttpServletResponse response,
			@RequestParam("accountId") Integer accountId,
			@RequestParam("month") Integer month,
			@RequestParam("year") Integer year,
			@RequestParam("searchType") Integer searchType
			) throws Exception {
		response.setHeader("Content-disposition", "attachment; filename=report.csv");
		PrintWriter p = response.getWriter();

		List<Map<String, Object>> searches;
		if (searchType == 1) {
			p.print(printHeaderCriminal());
			searches = ordersManager.getCorporateInvoiceCriminalMonth(accountId, month, year);
			printRows(p, searches, fieldsBgc);
		} else if (searchType == 2) {
			p.print(printHeaderBJL());
			searches = ordersManager.getCorporateInvoiceBJLMonth(accountId, month, year);
			printRows(p, searches, fieldsBJL);
		} else if (searchType == 3) {
			p.print(printHeaderTS());
			searches = ordersManager.getCorporateInvoiceTSMonth(accountId, month, year);
			printRows(p, searches, fieldsTS);
		}else if (searchType == 4) {
			p.print(printHeaderCS());
			searches = ordersManager.getCorporateInvoiceCSMonth(accountId, month, year);
			printRows(p, searches, fieldsCS);
		} 
		else if (searchType == 5) {
			p.print(printHeaderNSS());
			searches=ordersManager.getCorporateInvoiceNSSMonth(accountId, month, year);
			printRows(p, searches, fieldsNss);
		}
		else {
			searches = null;
		}
	}
	
	@RequestMapping(value="/admin/reports/FRInvoicingDownload.do", method=RequestMethod.GET)
	public void downloadFRInvoicing(			
			HttpServletResponse response,
			@RequestParam("userId") Integer userId,
			@RequestParam("month") Integer month,
			@RequestParam("year") Integer year,
			@RequestParam("searchType") Integer searchType
			) throws Exception {
		response.setHeader("Content-disposition", "attachment; filename=report.csv");
		PrintWriter p = response.getWriter();

		List<Map<String, Object>> searches;
		if (searchType == 1) {
			p.print(printHeaderCriminal());
			searches = ordersManager.getFRInvoiceCriminalMonth(userId, month, year);
			printRows(p, searches, fieldsBgc);
		} else if (searchType == 2) {
			p.print(printHeaderBJL());
			searches = ordersManager.getFRInvoiceBJLMonth(userId, month, year);
			printRows(p, searches, fieldsBJL);
		} else if (searchType == 3) {
			p.print(printHeaderFP());
			searches = ordersManager.getFRInvoiceFPMonth(userId, month, year);
			printRows(p, searches, fieldsFP);
		} else if (searchType == 4) {
			p.print(printHeaderflatRate());
			searches = ordersManager.getFRInvoiceFlatRate(userId, month, year);
			printRows(p, searches, FlatRate);
		} else {
			searches = null;
		}
	}
	
	
	protected String printHeaderCriminal() {
		String s = new String();
		for (String f : fieldsBgc) {
			s += f + ",";
		}
		s += "\n";
		return s;
	}
	
	protected String printHeaderBJL() {
		String s = new String();
		for (String f : fieldsBJL) {
			s += f + ",";
		}
		s += "\n";
		return s;
	}
	
	protected String printHeaderFP() {
		String s = new String();
		for (String f : fieldsFP) {
			s += f + ",";
		}
		s += "\n";
		return s;
	}
	protected String printHeaderTS() {
		String s = new String();
		for (String f : fieldsTS) {
			s += f + ",";
		}
		s += "\n";
		return s;
	}
	protected String printHeaderCS() {
		String s = new String();
		for (String f : fieldsCS) {
			s += f + ",";
		}
		s += "\n";
		return s;
	}
	
	protected String printHeaderNSS() {
		String s = new String();
		for (String f : fieldsNss) {
			s += f + ",";
		}
		s += "\n";
		return s;
	}
	
	protected String printHeaderflatRate() {
		String s = new String();
		for (String f : FlatRate) {
			s += f + ",";
		}
		s += "\n";
		return s;
	}
	
	protected void printRows(PrintWriter p, List<Map<String, Object>> rows, String fields[]) {
		for (Map<String, Object> map : rows) {			
			for (String f : fields) {
				Object o = map.get(f);
				// just print empty string instead of "null"
				String s;
				if (o == null) {
					s = "";
				} else {
					s = o.toString();
				}
				p.print('"' + s + '"' + ",");
			}
			p.print("\n");
		}
	}	
		
	@ModelAttribute("corporateAccounts")
	public TreeMap<Integer, String> populateCorporateAccounts() {
		// set up accounts
		TreeMap<Integer, String> map = new TreeMap<Integer, String>(); 
		List<CorporateAccount> list = userManager.getAllCorporateAccounts();
		for (CorporateAccount a : list) {
			map.put(a.getCorporateAccountId(), a.getCorporateName());
		}
		return map;
	}
	
	@ModelAttribute("FRMonthlyAccounts")
	public TreeMap<String, String> populateFRMonthlyAccounts() {
		// set up accounts
		TreeMap<String, String> map = new TreeMap<String, String>(); 
		List<Map<String, Object>> userList = userManager.getAllFRMonthlyUsers();
		for(Map<String, Object> m : userList ){
			map.put(m.get("userid").toString(),m.get("username").toString());
		}
		return map;
	}
	
	@ModelAttribute("months")
	public TreeMap<Integer, String> populateMonths() {
		return monthsMap;
	}
	@ModelAttribute("years")
	public TreeMap<Integer, String> populateYears() {
		return yearsMap;
	}
	@ModelAttribute("searchTypes")
	public TreeMap<Integer, String> populateSearchTypesMap() {
		return searchTypesMap;
	}
	
	public static class CorporateInvoicingFormCommand {
		private Integer accountId;
		private Integer month;
		private Integer year;
		private Integer searchType;

		public Integer getSearchType() {
			return searchType;
		}
		public void setSearchType(Integer searchType) {
			this.searchType = searchType;
		}
		public Integer getAccountId() {
			return accountId;
		}
		public void setAccountId(Integer accountId) {
			this.accountId = accountId;
		}
		public Integer getMonth() {
			return month;
		}
		public void setMonth(Integer month) {
			this.month = month;
		}
		public Integer getYear() {
			return year;
		}
		public void setYear(Integer year) {
			this.year = year;
		}		
	}
	
	public static class FRInvoicingFormCommand {
		private Integer userId;
		private Integer month;
		private Integer year;
		private Integer searchType;

		public Integer getSearchType() {
			return searchType;
		}
		public void setSearchType(Integer searchType) {
			this.searchType = searchType;
		}
		
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public Integer getMonth() {
			return month;
		}
		public void setMonth(Integer month) {
			this.month = month;
		}
		public Integer getYear() {
			return year;
		}
		public void setYear(Integer year) {
			this.year = year;
		}		
	}
}