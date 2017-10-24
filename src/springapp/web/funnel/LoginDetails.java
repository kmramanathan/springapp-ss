package springapp.web.funnel;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springapp.domain.CreditCard;
import springapp.domain.User;
import springapp.service.NeonPasswordMD5;
import springapp.web.funnel.DDNCriminalSearchForm.CriminalSearchFormCommand;
import springapp.web.funnel.NewBJLSearchForm.BJLSearchFormCommand;
import springapp.web.funnel.NewCorpBusSearchForm.CorpBusSearchFormCommand;
import springapp.web.funnel.NewCorpIndSearchForm.CorpIndSearchFormCommand;
import springapp.web.funnel.NewNationSecurityForm.NationSearchFormCommand;
import springapp.web.funnel.NewEvictionBusinessSearchForm.EvictionBusinessSearchFormCommand;
import springapp.web.funnel.NewEvictionSearchForm.EvictionSearchFormCommand;
import springapp.web.funnel.NewSearchForm.SearchFormCommand;
import springapp.web.funnel.PropertyAddressSearchForm.RealPropAddressSearchFormCommand;
import springapp.web.funnel.PropertySearchForm.RealPropSearchFormCommand;



@Controller
public class LoginDetails extends AbstractFunnelController{
	
	protected void setSearchTypeAttribute(BJLSearchFormCommand cmd, ModelMap map) {
		switch (cmd.getBjlSearchType()) {
		case INDIVIDUALNAME:
			map.addAttribute("orderSummary", "BJL Search by Name $"+cmd.getPrice());
			break;
		case BUSINESSNAME:
			map.addAttribute("orderSummary", "BJL Search by Name and Address $"+cmd.getPrice());
			break;
		case SSN:
			map.addAttribute("orderSummary", "BJL Search by SSN $"+cmd.getPrice());
			break;
		}
	}
	//set price for eviction business search
	protected void setEvictionBusinessTypeAttribute(EvictionBusinessSearchFormCommand cmd, ModelMap map)
	{
		map.addAttribute("orderSummary", "Eviction Businees Search $"+cmd.getPrice());
		
	}
	//set price for eviction individual search
	protected void setEvictionTypeAttribute(EvictionSearchFormCommand cmd, ModelMap map)
	{
		map.addAttribute("orderSummary", "Eviction Individual Search $"+cmd.getPrice());
		
	}
	
	//set price for RealProperty individual search
	protected void setRealPropTypeAttribute(RealPropSearchFormCommand cmd, ModelMap map)
	{
		map.addAttribute("orderSummary", "RealProp Name Search $"+cmd.getPrice());
		
	}
	//set price for RealProperty Address search
	protected void setRealPropAddressTypeAttribute(RealPropAddressSearchFormCommand cmd, ModelMap map)
	{
		map.addAttribute("orderSummary", "RealPropAddress Name Search $"+cmd.getPrice());
		
	}
	
	protected void setCriminalSearchFormCommand(CriminalSearchFormCommand cmd, ModelMap map)
	{
		map.addAttribute("orderSummary", "ddnCriminal Name Search $"+cmd.getPrice());		
	}
	
	//set price for Corporation Search
	protected void setIndCorpAttribute(CorpIndSearchFormCommand cmd, ModelMap map)
	{
		if(cmd.getNationwideSearch())
		{
			
			map.addAttribute("orderSummary", "Nationwide Search $"+cmd.getPrice());
		}
		else
		{
			map.addAttribute("orderSummary", "Statewide Search $"+cmd.getPrice());
		}
		
	}
	//set price for business Corporation Search
	protected void setBusCorpAttribute(CorpBusSearchFormCommand cmd, ModelMap map)
	{
		if(cmd.getNationwideSearch())
			map.addAttribute("orderSummary", "Nationwide Search $"+cmd.getPrice());
		else
			map.addAttribute("orderSummary", "Statewide Search $"+cmd.getPrice());
	}
	protected void setBGCSearchTypeAttribute(SearchFormCommand cmd, ModelMap map) {
		
		if(cmd.getNationwideSearch())
			map.addAttribute("orderSummary", "Nationwide Search $"+cmd.getPrice());
		else
			map.addAttribute("orderSummary", "Statewide search $"+cmd.getPrice());
		
	}
	protected void setNationSecurity(NationSearchFormCommand cmd, ModelMap map)
	{
		map.addAttribute("orderSummary", "National Security Search $"+cmd.getPrice());
		
	}
	protected void setSSNSearch(NationSearchFormCommand cmd, ModelMap map)
	{
		map.addAttribute("orderSummary", "SSN Search $"+cmd.getPrice());
		
	}
	
	@RequestMapping(value = "/funnel/login.do", method = RequestMethod.GET)
	public String showLoginForm(ModelMap map, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "searchType", required = false) String searchType)
	{
		String referrer = request.getHeader("referer");		
		session.setAttribute("referrer", referrer);
		if(session.getAttribute("bjlSearchFormCommand") == null && session.getAttribute("searchFormCommand") == null  && session.getAttribute("evictionSearchFormCommand") == null && session.getAttribute("evictionBusinessSearchFormCommand") == null && session.getAttribute("corpIndSearchFormCommand") == null && session.getAttribute("corpBusSearchFormCommand") == null && session.getAttribute("nationSearchFormCommand") == null){
			return showLogin;
		}
		
		if(searchType == null)
			return showLogin;
		if(searchType.equalsIgnoreCase("bjl")){
			BJLSearchFormCommand bjlSfc = (BJLSearchFormCommand) session.getAttribute("bjlSearchFormCommand");
			setSearchTypeAttribute(bjlSfc, map);
		}
		
		else if (searchType.equalsIgnoreCase("bgc")) {
			SearchFormCommand sfc=(SearchFormCommand) session.getAttribute("searchFormCommand");
			setBGCSearchTypeAttribute(sfc, map);
		  }
		else if (searchType.equalsIgnoreCase("bgcSSN")) {
			SearchFormCommand sfc=(SearchFormCommand) session.getAttribute("searchFormCommand");
			setBGCSearchTypeAttribute(sfc, map);
		  }
		else if(searchType.equalsIgnoreCase("nation"))
		{
			NationSearchFormCommand nsfc=(NationSearchFormCommand) session.getAttribute("nationSearchFormCommand");
			setNationSecurity(nsfc, map);
		}
		else if(searchType.equalsIgnoreCase("eviction"))
		{
			EvictionSearchFormCommand esfc= (EvictionSearchFormCommand) session.getAttribute("evictionSearchFormCommand");
			setEvictionTypeAttribute(esfc, map);
		}
		else if (searchType.equalsIgnoreCase("evicBusiness")) {
			EvictionBusinessSearchFormCommand ebsfc=(EvictionBusinessSearchFormCommand) session.getAttribute("evictionBusinessSearchFormCommand");
			setEvictionBusinessTypeAttribute(ebsfc, map);
		}
		else if (searchType.equalsIgnoreCase("corporation")) {
			CorpIndSearchFormCommand cisfc = (CorpIndSearchFormCommand) session.getAttribute("corpIndSearchFormCommand");
			
			setIndCorpAttribute(cisfc, map);
		}
		else if (searchType.equalsIgnoreCase("corpbus")) {
			CorpBusSearchFormCommand cbsfc=(CorpBusSearchFormCommand) session.getAttribute("corpBusSearchFormCommand");
			setBusCorpAttribute(cbsfc, map);
		}
		else if(searchType.equalsIgnoreCase("realprop"))
		{
			RealPropSearchFormCommand rpsf= (RealPropSearchFormCommand) session.getAttribute("realPropSearchFormCommand");
			setRealPropTypeAttribute(rpsf, map);
		}
		else if(searchType.equalsIgnoreCase("realpropAddress"))
		{
			RealPropAddressSearchFormCommand rpafc= (RealPropAddressSearchFormCommand) session.getAttribute("RealPropAddressSearchFormCommand");
			setRealPropAddressTypeAttribute(rpafc, map);
		}
		else if(searchType.equalsIgnoreCase("ddnCriminal"))
		{
			CriminalSearchFormCommand cirmsf= (CriminalSearchFormCommand) session.getAttribute("CriminalSearchFormCommand");
			setCriminalSearchFormCommand(cirmsf, map);
		}
		session.setAttribute("searchtypes", searchType);
		return showLogin;
	}
	
	
	@RequestMapping(value = "/funnel/login.do", method = RequestMethod.POST)
	public String showLoginForm(ModelMap map, HttpSession session,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "orderSummary", required = false) String searchType){		
		
		
		User user = userManager.getUserByUsername(username, false);		
		if(!authenticateUser(map, user, password))
		{
			map.addAttribute("orderSummary", searchType);
			return showLogin;
		}	
		
		String referrerURL=(String)session.getAttribute("referrer");
		//logger.info("RealPRop AddresLogIF:::"+ session.getAttribute("RealPropAddressSearchFormCommand") == null );
		logger.info("referer:"+referrerURL);

		
		ArrayList<String> alist=new ArrayList<String>();
		
		alist.add("https://www.searchsystems.net/springapp/funnel/newSearch.do");
		alist.add("https://dev.searchsystems.net/springapp/funnel/newSearch.do");
		
		ArrayList<String> alist0=new ArrayList<String>();
		
		alist0.add("https://www.searchsystems.net/springapp/funnel/aliasSearch.do");
		alist0.add("https://dev.searchsystems.net/springapp/funnel/aliasSearch.do");
		
		ArrayList<String> alist1=new ArrayList<String>();
		
		alist1.add("http://localhost:8080/springapp/funnel/newSearchBJL.do");
		alist1.add("http://207.178.157.123:8080/springapp/funnel/newSearchBJL.do");
		alist1.add("https://www.searchsystems.net/springapp/funnel/newSearchBJL.do");
		alist1.add("http://dev.searchsystems.net/springapp/funnel/newSearchBJL.do");
		
		ArrayList<String> alist2=new ArrayList<String>();
		
		alist2.add("http://localhost:8080/springapp/funnel/eviction-records.do");
		alist2.add("http://207.178.157.123:8080/springapp/funnel/eviction-records.do");
		alist2.add("https://www.searchsystems.net/springapp/funnel/eviction-records.do");
		alist2.add("https://dev.searchsystems.net/springapp/funnel/eviction-records.do");
		
		ArrayList<String> alist3=new ArrayList<String>();
		
		alist3.add("http://localhost:8080/springapp/funnel/eviction-records-business.do");
		alist3.add("https://www.searchsystems.net/springapp/funnel/eviction-records-business.do");
		alist3.add("http://207.178.157.123:8080/springapp/funnel/eviction-records-business.do");
		alist3.add("https://dev.searchsystems.net/springapp/funnel/eviction-records-business.do");
		
		ArrayList<String> alist4= new ArrayList<String>();
		
		alist4.add("https://www.searchsystems.net/springapp/funnel/corp-ind-search.do");
		alist4.add("https://dev.searchsystems.net/springapp/funnel/corp-ind-search.do");
		
		ArrayList<String> alist5= new ArrayList<String>();
		
		alist5.add("https://www.searchsystems.net/springapp/funnel/corp-bus-search.do");
		alist5.add("https://dev.searchsystems.net/springapp/funnel/corp-bus-search.do");
		
		ArrayList<String> alist6= new ArrayList<String>();
		
		alist6.add("https://www.searchsystems.net/springapp/funnel/national-security-search.do");
		alist6.add("https://dev.searchsystems.net/springapp/funnel/national-security-search.do");
		
		//Real Prop indivudal search
		ArrayList<String> alist7= new ArrayList<String>();
		
		alist7.add("http://localhost:8080/springapp/funnel/realpropSearch.do");
		alist7.add("https://www.searchsystems.net/springapp/funnel/realpropSearch.do");
		alist7.add("http://207.178.157.123:8080/springapp/funnel/realpropSearch.do");
		alist7.add("http://dev.searchsystems.net/springapp/funnel/realpropSearch.do");
		
		//Real Prop Address search
		ArrayList<String> alist8= new ArrayList<String>();	
		
		alist8.add("http://localhost:8080/springapp/funnel/realpropAddressSearch.do");
		alist8.add("https://www.searchsystems.net/springapp/funnel/realpropAddressSearch.do");
		alist8.add("http://207.178.157.123:8080/springapp/funnel/realpropAddressSearch.do");
		alist8.add("http://dev.searchsystems.net/springapp/funnel/realpropAddressSearch.do");
		
		//Criminal Search using DDN search
		ArrayList<String> alist9= new ArrayList<String>();
		alist9.add("http://localhost:8080/springapp/funnel/criminalSearch.do");
		alist9.add("https://www.searchsystems.net/springapp/funnel/criminalSearch.do");
		alist9.add("http://207.178.157.123:8080/springapp/funnel/criminalSearch.do");
		alist9.add("http://dev.searchsystems.net/springapp/funnel/criminalSearch.do");
				
		ArrayList<String> alist10=new ArrayList<String>();
		
		alist10.add("http://207.178.157.123:8080/springapp/funnel/newSSNSearch.do");
		alist10.add("https://www.searchsystems.net/springapp/funnel/newSSNSearch.do");
		alist10.add("http://localhost:8080/springapp/funnel/newSSNSearch.do");
		alist10.add("http://dev.searchsystems.net/springapp/funnel/newSSNSearch.do");
		
				
		String sTestPremium = "http://dev.searchsystems.net/springapp/premium";
		String sLivePremium = "https://www.searchsystems.net/springapp/premium";		
		if(referrerURL.equals(sTestPremium) || referrerURL.equals(sLivePremium))
		{
			logger.info("LoginRedict_Premium:::::"+referrerURL);
			session.setAttribute("authenticated", "true");
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userEmail", user.getEmail());		
				
			if (user.getNoCreditCard())			
				session.setAttribute("noCreditCard", true); 
			else
			{
				CreditCard cc = userManager.getCreditCard(user.getUserId());
				session.setAttribute("creditCardObj", cc);  
			}
			session.removeAttribute("referrer");
			if(referrerURL.equals(sLivePremium))
				return landingPremium;
			else if(referrerURL.equals(sTestPremium))
				return landingTestPremium;
		}
		
		for (Iterator iterator = alist.iterator(); iterator.hasNext();) 
		{
			String string = (String) iterator.next();
			logger.info("Iterator: alist: " + string);
				
			if(session.getAttribute("searchFormCommand") == null && referrerURL.equals(string))
			{
				logger.info("Iterator: " + referrerURL);
				session.setAttribute("authenticated", "true");
				session.setAttribute("username", user.getUsername());
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("userEmail", user.getEmail());		
					
				if (user.getNoCreditCard())			
					session.setAttribute("noCreditCard", true); 
				else
				{
					CreditCard cc = userManager.getCreditCard(user.getUserId());
					session.setAttribute("creditCardObj", cc);  
				}
				session.removeAttribute("referrer");
				return newvwSearchRedir;
			}
		}
		
		for (Iterator iterator = alist0.iterator(); iterator.hasNext();) 
		{
			String string = (String) iterator.next();
			logger.info("Iterator: alist0: " + string);
				
			if(session.getAttribute("aliasSearchFormCommand") == null && referrerURL.equals(string))
			{
				logger.info("Iterator: " + referrerURL);
				session.setAttribute("authenticated", "true");
				session.setAttribute("username", user.getUsername());
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("userEmail", user.getEmail());		
					
				if (user.getNoCreditCard())			
					session.setAttribute("noCreditCard", true); 
				else
				{
					CreditCard cc = userManager.getCreditCard(user.getUserId());
					session.setAttribute("creditCardObj", cc);  
				}
				session.removeAttribute("referrer");
				logger.info("Before redirect - URL: " + newvwAliasSearchRedir);
				return newvwAliasSearchRedir;
			}
		}
		
		for (Iterator iterator = alist1.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
		
		if(session.getAttribute("bjlSearchFormCommand") == null && referrerURL.equals(string))		
		{
			//logger.info("BJL Search and Refer URL"+referrerURL);
			session.setAttribute("authenticated", "true");
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userEmail", user.getEmail());		
				
			if (user.getNoCreditCard())			
				session.setAttribute("noCreditCard", true); 
			else{
				CreditCard cc = userManager.getCreditCard(user.getUserId());
				session.setAttribute("creditCardObj", cc);  
			}
			session.removeAttribute("referrer");
			return newvwbJLSearchRedir;
		}
	}
		for (Iterator iterator = alist2.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
		 if(session.getAttribute("evictionSearchFormCommand") == null && referrerURL.equals(string))
		
		{
			//logger.info("Eviction Search and Refer URL"+referrerURL);
			session.setAttribute("authenticated", "true");
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userEmail", user.getEmail());		
				
			if (user.getNoCreditCard())			
				session.setAttribute("noCreditCard", true); 
			else
			{
				CreditCard cc = userManager.getCreditCard(user.getUserId());
				session.setAttribute("creditCardObj", cc);  
			}
			session.removeAttribute("referrer");
			return newvwEvictionRedir;
		}
		}
		
		for (Iterator iterator = alist3.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
		if(session.getAttribute("evictionBusinessSearchFormCommand") == null && referrerURL.equals(string))
		{
			//logger.info("Eviction Business Search and Refer URL"+referrerURL);
			session.setAttribute("authenticated", "true");
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userEmail", user.getEmail());		
				
			if (user.getNoCreditCard())			
				session.setAttribute("noCreditCard", true); 
			else{
				CreditCard cc = userManager.getCreditCard(user.getUserId());
				session.setAttribute("creditCardObj", cc);  
			}
			session.removeAttribute("referrer");
			return newvwEvictionBusinessRedir;
		}
		}
		
		for (Iterator iterator = alist4.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
		if(session.getAttribute("corpIndSearchFormCommand") == null && referrerURL.equals(string))
		{
			//logger.info("Eviction Business Search and Refer URL"+referrerURL);
			session.setAttribute("authenticated", "true");
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userEmail", user.getEmail());		
				
			if (user.getNoCreditCard())			
				session.setAttribute("noCreditCard", true); 
			else{
				CreditCard cc = userManager.getCreditCard(user.getUserId());
				session.setAttribute("creditCardObj", cc);  
			}
			session.removeAttribute("referrer");
			return corpIndViewRedir;
		}
		}
		
		for (Iterator iterator = alist5.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
		if(session.getAttribute("corpBusSearchFormCommand") == null && referrerURL.equals(string))
		{
			
			session.setAttribute("authenticated", "true");
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userEmail", user.getEmail());		
				
			if (user.getNoCreditCard())			
				session.setAttribute("noCreditCard", true); 
			else{
				CreditCard cc = userManager.getCreditCard(user.getUserId());
				session.setAttribute("creditCardObj", cc);  
			}
			session.removeAttribute("referrer");
			return corpBusViewRedir;
		}
		}
		
		for (Iterator iterator = alist6.iterator(); iterator.hasNext();)
		{			
			String string = (String) iterator.next();
			//logger.info("NationSS_#####::::"+string+ "reference_URL####:::"+ referrerURL );
			if(session.getAttribute("nationSearchFormCommand") == null && referrerURL.equals(string))
			{				
				session.setAttribute("authenticated", "true");
				session.setAttribute("username", user.getUsername());
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("userEmail", user.getEmail());		
					
				if (user.getNoCreditCard())			
					session.setAttribute("noCreditCard", true); 
				else{
					CreditCard cc = userManager.getCreditCard(user.getUserId());
					session.setAttribute("creditCardObj", cc);  
				}
				session.removeAttribute("referrer");
				return nationSecureViewRedir;
			}
		}
		
		//For RealProp Name search
		for (Iterator iterator = alist7.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			
			if(session.getAttribute("RealPropSearchFormCommand") == null && referrerURL.equals(string))
			{			
				//logger.info("RealPROP=###########::::::RealPropSearchFormCommand:::NULL::"+referrerURL);
				session.setAttribute("authenticated", "true");
				session.setAttribute("username", user.getUsername());
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("userEmail", user.getEmail());		
					
				if (user.getNoCreditCard())			
					session.setAttribute("noCreditCard", true); 
				else{
					CreditCard cc = userManager.getCreditCard(user.getUserId());
					session.setAttribute("creditCardObj", cc);  
				}
				session.removeAttribute("referrer");
				return newvwReralPropRedir;
			}
		}
		
		//address search 
		for (Iterator iterator = alist8.iterator(); iterator.hasNext();) 
		{
				String string = (String) iterator.next();
				
			if(session.getAttribute("RealPropAddressSearchFormCommand") == null && referrerURL.equals(string))
			{		
				
				session.setAttribute("authenticated", "true");
				session.setAttribute("username", user.getUsername());
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("userEmail", user.getEmail());		
					
				if (user.getNoCreditCard())			
					session.setAttribute("noCreditCard", true); 
				else{
					
					CreditCard cc = userManager.getCreditCard(user.getUserId());
					session.setAttribute("creditCardObj", cc);  
				}
				session.removeAttribute("referrer");
				return newvwReralPropAddressRedir;
			}
		}
		
		//For RealProp Name search
		for (Iterator iterator = alist9.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			
			if(session.getAttribute("CriminalSearchFormCommand") == null && referrerURL.equals(string))
			{			
				logger.info("Criminal DDN=###########::::::CriminalSearchFormCommand:::NULL::"+referrerURL);
				session.setAttribute("authenticated", "true");
				session.setAttribute("username", user.getUsername());
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("userEmail", user.getEmail());		
					
				if (user.getNoCreditCard())			
					session.setAttribute("noCreditCard", true); 
				else{
					CreditCard cc = userManager.getCreditCard(user.getUserId());
					session.setAttribute("creditCardObj", cc);  
				}
				session.removeAttribute("referrer");
				return newvwCrminalRedir;
			}
		}
		
		session.removeAttribute("referrer");
		session.setAttribute("authenticated", "true");
		session.setAttribute("username", user.getUsername());
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("userEmail", user.getEmail());		
		
		if (user.getNoCreditCard())			
			session.setAttribute("noCreditCard", true); 
		else{
			CreditCard cc = userManager.getCreditCard(user.getUserId());
			session.setAttribute("creditCardObj", cc);  
		}// if 1, its company credit card and no need to charge
					
		return newconfirmSearchRedir;
	}
	
	public boolean authenticateUser(ModelMap map, User user, String password){
		
		if(user == null){
			map.addAttribute("errorMsg", "User does not exist");
			return false;
		}else if(!user.getPassword().equals(NeonPasswordMD5.encryptPassword(password))){
			map.addAttribute("errorMsg", "username and password does not match");
			return false;
		}else if(user.getDisabled()){
			map.addAttribute("errorMsg", "user is disabled");
			return false;
		}else if(user.getInactive()){
			map.addAttribute("errorMsg", "user is inactive");
			return false;
		}	
		
		return true;
		
	}
	
	@RequestMapping(value = "/funnel/logout.do", method = RequestMethod.GET)
	public String logoutUser(HttpSession session){
		session.removeAttribute("noCreditCard");
		session.removeAttribute("creditCardObj");
		session.removeAttribute("authenticated");
		session.removeAttribute("username");
		session.removeAttribute("userId");
		session.removeAttribute("userEmail");
		session.invalidate();
		logger.info("Before logout - URL=> " + logoutNewRedirect);
		//session.removeAttribute("referrer");
		return logoutNewRedirect;
		
	}
	
	

}
