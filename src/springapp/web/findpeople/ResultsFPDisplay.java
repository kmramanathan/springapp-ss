package springapp.web.findpeople;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


import javax.servlet.http.HttpSession;

import net.searchsystems.limestone.BGCOffenseSupplement;
import net.searchsystems.limestone.bean.BGCAliasBean;
import net.searchsystems.limestone.bean.BGCOffenderBean;
import net.searchsystems.limestone.bean.BGCOffenseBean;
import net.searchsystems.limestone.bean.BGCOffenseSupplementBean;
import net.searchsystems.limestone.bean.BGCResponseBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;


import springapp.domain.Transaction;
import springapp.domain.User;
import springapp.manager.SearchException;
import springapp.manager.SpringBGCSearchManager;
import springapp.service.NeonValidator;
import springapp.web.findpeople.BGxmlManager.OccupantData;
import springapp.web.findpeople.SearchLandingForm.SearchFPFormCommand;
import springapp.web.findpeople.xmlParser.fpDetail;
import springapp.web.funnel.AbstractFunnelController;
import springapp.web.funnel.ResultsDisplay.ResultsCommand;
import springapp.web.funnel.SearchForm.SearchFormCommand;
import springapp.web.funnel.mexico.SearchMexicanRecordForm.SearchMexicanRecordFormCommand;
import springapp.web.funnel.mexico.SearchMexicanRecordForm.SearchMexicanRecordFormValidator;

import org.w3c.dom.*;



@Controller
public class ResultsFPDisplay extends AbstractFunnelController{
	
	@Autowired
	private SpringBGCSearchManager bgcManager;
	
	protected Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping(value = "/findpeople/resultsFPRecords.do", method = RequestMethod.GET)
	public String getResults(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="key",required=false) String key,
			@RequestParam(value="resultType",required=true) String resultType,
			@RequestParam(value="memberRequest",required=false) Boolean memberRequest,
			@RequestParam(value="signupFor",required=false) String signupFor
			)  {		
		
		logger.info("We are in Findpeople result page");
		
		if(key == null){key = ""; }
		if(memberRequest == null){ memberRequest = false; }	
	    
		boolean member=false;
		String username=(String)session.getAttribute("username");
		if(username != null){
			member=true;
		}
				
		if(resultType.equals("one"))
		{
			logger.info("WE reached fpdetail one ");
			Hashtable fpResult = null;
			SearchFPFormCommand sfpfc = (SearchFPFormCommand) session.getAttribute("searchFPFormCommand");
				
			try
			{
				userManager.fpDetailCount(); // increasing fp counter
				
				//XXX monthly billing
				if(session.getAttribute("userId")!=null){
					Integer userId = (Integer)session.getAttribute("userId");
					Number price = 1.00;
					String state = sfpfc.getSearchState();
					if(state.equals("")){
						state="All states";
					}
					userManager.fpUserRequest(userId, "Find People Details", sfpfc.getSearchFirstName(),
							sfpfc.getSearchLastName(),state, price);
				}
				//running fp detail search
				fpResult = xmlParser.getSearchResult(sfpfc,"FP","one");
				if(fpResult != null ){
					logger.info("Size of the fpDetail result :"+fpResult.size());
					session.setAttribute("fpResult", fpResult);
				}
				return "redirect:searchLanding.do?resultType=one";
				
			}
			catch(Exception e)
			{
				logger.info("Find people - Exception Getting Result for one id :" +e.toString());
				return "findpeople/Error";
			}
			  
		}
		
		if(resultType.equals("oneKey")){
			logger.info("inside result one :: Key "+key);
			session.setAttribute("key", key);
			if(!member){
				return "findpeople/ValueProp";
			}
			return "redirect:purchaseFPSearch.do?resultType=one";
		}
		
		if(resultType.equals("BG")){
			SearchFPFormCommand sfpfc= (SearchFPFormCommand) session.getAttribute("searchFPFormCommand");
			String id = (String) session.getAttribute("key");
			logger.info("Searching Bg Result for Key " + id +" LastName :"+sfpfc.getSearchLastName());
			
			userManager.fpBGCount(); // increasing BG counter
			

			if(signupFor != null && signupFor.equalsIgnoreCase("findpeople")){
				//Free BGR Search user's modification
				if(session.getAttribute("userId")!=null){
						Integer userId = (Integer)session.getAttribute("userId");
						userManager.modifyFreeBgrUser(userId);
				}
				//Just increasing count
				userManager.fpFreeBGCount();
								
			}else{
					//XXX monthly billing and modifying free account
					if(session.getAttribute("FRBGRrequest") != null &&	session.getAttribute("FRuserId") != null){
						session.removeAttribute("FRBGRrequest");
						Integer userId = (Integer)session.getAttribute("FRuserId");
						Number price = 9.95;
						String state = sfpfc.getSearchState();
						if(state.equals("")){
								state="All states";
						}
						userManager.fpUserRequest(userId, "Background Details", sfpfc.getSearchFirstName(),
								sfpfc.getSearchLastName(),state, price);
					}else if(session.getAttribute("userId")!=null){
						Integer userId = (Integer)session.getAttribute("userId");
						//Removing Free BGR User
						if(session.getAttribute("freeBGR") != null){
							userManager.modifyFreeBgrUser(userId);
							session.removeAttribute("freeBGR");
							}
						Number price = 14.95;
						String state = sfpfc.getSearchState();
						if(state.equals("")){
								state="All states";
						}
						userManager.fpUserRequest(userId, "Background Details", sfpfc.getSearchFirstName(),
								sfpfc.getSearchLastName(),state, price);
					}
			}
		
			//running BG search
			Hashtable BGResult = xmlParser.getSearchResult(sfpfc, "BG", id);
			logger.info(" returned Hash table from BGxmlParser :"+BGResult);
			
			if(BGResult == null){
				return "findpeople/Error";
			}
			session.setAttribute("BGResult", BGResult);
			try
			{
				// Run criminal Search
				logger.info("Searching BGC record inside FP");
				BGxmlManager bgResult = (BGxmlManager) BGResult.get("BGResult");
				int responseId = runCriminalSearch(session, map, bgResult);
				logger.info("responseId: " + responseId);
			
				//Fetching the results
				ArrayList<BGCOffenderBean> offenders = new ArrayList<BGCOffenderBean>();
				BGCOffenderBean[] offendersAll = bgcManager.getOffenders(responseId);
				
				//getting offender Info
				for (BGCOffenderBean o : offendersAll) {
					offenders.add(o);
				}
				
				//setting the BGC result to session - Beacuse we need it for pdf also
				session.setAttribute("BgcOffenders", offenders);

			}catch(Exception e)	{
				logger.info("Error occured in BGC search inside FindPeople");
				logger.error(e);
			}
			
			
			return "redirect:searchLanding.do?resultType=BG";
		}
		return "findpeople/Error";
			
	}
	
	
	protected int runCriminalSearch(HttpSession session, ModelMap map, BGxmlManager bgResult) throws SearchException {
		User u = userManager.getUserByUsername(salesFunnelUser);
		String jurisdiction;
		jurisdiction = bgResult.getProRpt().getState();
		Boolean fullDOB = true;
		String searchDob = "";
		
		//Validating DOB
		Integer year = 0;
		Integer month = 0;
		Integer date = 0;
		
		String BgcDob = bgResult.getProRpt().getDOB();
		logger.info(" DOB from BG Report :"+BgcDob);
		String m, d;
		if(!BgcDob.equals(""))
		{
			String[] DOBToken = BgcDob.split("/");
			month = Integer.parseInt(DOBToken[0]);
			date = Integer.parseInt(DOBToken[1]);
			year = Integer.parseInt(DOBToken[2]);
			
			searchDob = month+"/"+date+"/"+year;
			
			if(month == 0 || date == 0) {
				searchDob = "00/00"+"/"+year;
				fullDOB = false;
			}
		}
		else if(session.getAttribute("BgcYob") != null){
			String yob =(String) session.getAttribute("BgcYob");
			year = Integer.parseInt(yob);
			searchDob = "00/00"+"/"+year;
			fullDOB = false;
		}
		logger.info("Final DOB for BGC Search :"+searchDob);
		
		//setting dob for search
		session.setAttribute("searchDob", searchDob);
		
		int requestId;
		// run search
		if(fullDOB)
		{
			requestId = bgcManager.prepareSearch(u.getUserId(), 
				bgResult.getProRpt().getFirstName(), "", bgResult.getProRpt().getLastName(), 
				month, date, year, 0 , bgcTestProductId, false, jurisdiction, "Personal", "");
		}
		else{
			requestId = bgcManager.prepareSearch(u.getUserId(), 
					bgResult.getProRpt().getFirstName(), bgResult.getProRpt().getMiddleName(), bgResult.getProRpt().getLastName(), 
					0, 0, year, 3, bgcTestProductId, false, jurisdiction, "Personal", "");
		}
		logger.info("requestId: " + requestId);
		BGCResponseBean response = bgcManager.runSearch(requestId, session);
		int responseId = response.getBgcResponseId();
		logger.info("responseId: " + responseId);
		return responseId;
	}
	
	// post handles checkboxes for print selected
	@RequestMapping(value = "/findpeople/bgcResults.do", method = RequestMethod.POST)
	public String printResults(
			WebRequest request,
			HttpSession session,
			ModelMap map,
			@RequestParam(value="printType",required=false) String printType
				){
			Map params = request.getParameterMap();
			String[] printChecked =(String[])params.get("printChecked");
			
			Integer responseId = (Integer) session.getAttribute("responseId");
			
			ArrayList<BGCOffenderBean> offenders = new ArrayList<BGCOffenderBean>();
			ArrayList<Integer> offToFetch = new ArrayList<Integer>();
		
		try {
			offenders = (ArrayList)session.getAttribute("BgcOffenders");

			if (printType.equals("all")) {
				// print all
				logger.info("print all for: " + responseId);
				for (BGCOffenderBean o : offenders) {
						offToFetch.add(o.getBgcOffenderId());
				}
			} else {
				// print checked
				logger.info("print checked for: " + responseId);
				HashSet<Integer> checked = new HashSet<Integer>();
					for (String s : printChecked) {
					checked.add(Integer.parseInt(s));
				} 
				
				for (BGCOffenderBean o : offenders) {
					if (checked.contains(o.getBgcOffenderId())) {
							offToFetch.add(o.getBgcOffenderId());
					}
				}
			}

			// make sure all the offenders are fetched	
			bgcManager.fetchOffenderDetails(offToFetch);
			
			//getting Alias info
			HashMap<Integer,BGCAliasBean[]> aliasesMap = new HashMap<Integer,BGCAliasBean[]>();
			for (BGCOffenderBean o : offenders) {
				Integer offenderId =o.getBgcOffenderId();
				aliasesMap.put(offenderId, bgcManager.getAliases(offenderId));
			}
			//getting Offenses Detail
			HashMap<Integer,BGCOffenseBean[]> offensesMap = new HashMap<Integer,BGCOffenseBean[]>();
			for (BGCOffenderBean o : offenders) {
				Integer offenderId =o.getBgcOffenderId();
				offensesMap.put(offenderId, bgcManager.getOffenses(offenderId));
			}
							
			//setting the BGC result to session - Beacuse we need it for pdf also
			session.setAttribute("offToFetch", offToFetch);
			session.setAttribute("BgcAliases", aliasesMap);
			session.setAttribute("BgcOffenses", offensesMap);
			
			return "findpeople/BGCinFP";
			
		} catch (Exception e) {
			logger.info("Error occured while printing BGC result in findpeople for response id :"+responseId);
			return "findpeople/Error";
		}
	}
	
	
	@RequestMapping(value = "/findpeople/resultsFPRecords.do", method = RequestMethod.POST)
	public String viewDetailedResults(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="memberRequest",required=false) Boolean memberRequest,
			@RequestParam(value="resultType",required=true) String resultType,
			@RequestParam(value="key",required=false) String key
			)  {

		try {
							
				if(memberRequest==null){ memberRequest=false; }
				if(key != null){
					session.setAttribute("key", key);
				}
				
				logger.info("Result Type ::"+resultType);
				logger.info("Key Value is ::"+key);
								
				if(resultType.equals("BG"))
				{
					String BGrePage="";
					if(memberRequest==true)	{
						BGrePage = "redirect:/funnel/signup.do?signupFor=findpeople&previousPage=/findpeople/resultsFPRecords.do?resultType=BG";
					}
					else{
						BGrePage = "redirect:purchaseFPSearch.do?resultType=BG";
					}
					session.setAttribute("BGrePage", BGrePage);
				
					return "findpeople/BgcInput";
				}
				if(resultType.equals("FreeBgPage")){
					return "findpeople/FreeBgPage";
				}
			    return "findpeople/searchLanding.do";
		} catch (Exception e) {
			return "findpeople/Error";
		}
	}
	
	//To get and validate the Yob and Purpose
	@RequestMapping(value = "/findpeople/BgcInput.do", method = RequestMethod.POST)
	public  String getBGCYob(
			HttpSession session,
			@RequestParam(value="BgcPurpose",required=false) String BgcPurpose,
			@RequestParam(value="BgcYob",required=false) String BgcYob
			
			)  {
		logger.info("BGC search purpose : "+BgcPurpose);
		logger.info("BGC search DOB : "+BgcYob);   	
		
		if(BgcYob != null){
			session.setAttribute("BgcYob", BgcYob);
		}	
		
		logger.info(" Dob is " + session.getAttribute("BgcYob"));
		
		String BGrePage = (String)session.getAttribute("BGrePage");
		return BGrePage;
	}
	
	public String readXMLNode(Node firstNode, String strTagName){
		String strQueryResult = "";
		Element firstElement = (Element)firstNode;
		NodeList queryResultList = firstElement.getElementsByTagName(strTagName);
		Element firstqueryResultElement = (Element)queryResultList.item(0);
		if(firstqueryResultElement!=null){
			NodeList textqueryResultList = firstqueryResultElement.getChildNodes();
			Node lNode = ((Node)textqueryResultList.item(0));			
			if(lNode!=null){
				strQueryResult = lNode.getNodeValue().trim();
			}
		}
		return (strQueryResult);
	}
}