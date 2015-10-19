package springapp.web.funnel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.searchsystems.limestone.BjlResults;
import net.searchsystems.limestone.BjlResultsPeer;
import net.searchsystems.limestone.BjlSearches;
import net.searchsystems.limestone.BjlSearchesPeer;
import net.searchsystems.limestone.bean.BGCAliasBean;
import net.searchsystems.limestone.bean.BGCOffenderBean;
import net.searchsystems.limestone.bean.BGCOffenseBean;
import net.searchsystems.limestone.bean.BGCRequestBean;
import net.searchsystems.limestone.bean.BGCResponseBean;

import org.apache.log4j.Logger;
import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import springapp.domain.NewBJLResults;
import springapp.domain.NewBJLSearches;
import springapp.manager.SearchException;
import springapp.manager.SpringBGCSearchManager;
import springapp.manager.SpringBJLSearchManager;
import springapp.service.user.UserManager;

@SessionAttributes("command")
@Controller
public class ResultsDisplay extends AbstractFunnelController {
	protected static final Integer pageSizes[] = {10, 25, 50};
	
	@Autowired
	private SpringBGCSearchManager bgcManager;
	
	@Autowired
	private SpringBJLSearchManager bjlManager;
	
	protected boolean verifySession(HttpSession session, Boolean test) {
		// load response
		// XXX fix this
		Integer responseId = (Integer) session.getAttribute("responseId");
		if (responseId == null) {
			if (test) { 
				session.setAttribute("responseId", 10425);
				return true; 
			} else {		
				return false;
			}
		}
				
		return true;		
	}
	
	public static class ResultsCommand {
		private String[] resultsToPrint;

		public String[] getResultsToPrint() {
			return resultsToPrint;
		}
		public void setResultsToPrint(String[] resultsToPrint) {
			this.resultsToPrint = resultsToPrint;
		}		
	}
	
	@RequestMapping(value = "/funnel/resultsBJL.do", method = RequestMethod.GET)
	public String getResultsBJL(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="pageSize",required=false) Integer pageSize
			)  {		
		if (test == null) { test = false; }
		if (pageSize == null) { pageSize = 50; }

		Integer userSearchId = (Integer) session.getAttribute("userSearchId");
		if (userSearchId == null) {
			return vwError;
		} 
		
		map.addAttribute("userSearchId", userSearchId);

		// get results
		try {
			NewBJLSearches search = bjlManager.getSearch(userSearchId);

			if (search.getMatchCount() == 0) {
				map.addAttribute("bjlSearch", true);
				map.addAttribute("bjlSearchName", search.getLastName());
				map.addAttribute("bjlSearchSsnTaxId", search.getSSN());
				map.addAttribute("bjlSearchState", search.getState());
				session.removeAttribute("userSearchId");
				session.removeAttribute("bjlSearchFormCommand");
				return NoBJLResult;
			}
			List<String> typeOfItem=new ArrayList<String>();
			typeOfItem.add("");
			typeOfItem.add("");
			typeOfItem.add("");
			List<NewBJLResults> results = bjlManager.getAllResults(userSearchId, typeOfItem, search.getLastName());
			
			map.addAttribute("command", new ResultsCommand());
			map.addAttribute("pageSizes", pageSizes);
			map.addAttribute("pageSize", pageSize);
			map.addAttribute("searchName", search.getLastName());
			map.addAttribute("results", results);
			map.addAttribute("resultsCount", results.size());
			
			return vwBJLResults;
			
		} catch (SearchException e) {
			// void charge?
			// XXX todo
			logger.error("Error getting results", e);

			// send to error page
			return vwError;
		}		
	}
	
	@RequestMapping(value = "/funnel/results.do", method = RequestMethod.GET)
	public String getResults(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="pageSize",required=false) Integer pageSize
			)  {		
		if (test == null) { test = false; }
		if (pageSize == null) { pageSize = 10; }
		if (!verifySession(session, test)) {
			return purchaseSearchRedir;
		}
		Integer responseId = (Integer) session.getAttribute("responseId");
		if (responseId == null) {
			return vwError;
		} else {
			map.addAttribute("responseId", responseId);
		}
		
		try {
			BGCRequestBean request;
			BGCResponseBean response;
			try {
				response = bgcManager.getResponse(responseId);
				request = bgcManager.getRequest(response.getBgcRequestId());
			} catch (NoRowsException e) {
				throw new SearchException(e);
			} catch (TooManyRowsException e) {
				throw new SearchException(e);
			} catch (TorqueException e) {
				throw new SearchException(e);
			}
			logger.info("responseId: " + response.getBgcResponseId());
			
			if (response.getQuantityReturned() == 0) {				
				map.addAttribute("firstName", request.getFirstName());
				map.addAttribute("lastName", request.getLastName());
				String searchDOB = request.getDobMonth() + "/" + request.getDobDay() + "/" + request.getDobYear();
				map.addAttribute("DOB", searchDOB);
				//session.removeAttribute("bgcRequestId");
				session.removeAttribute("responseId");
				session.removeAttribute("searchFormCommand");
				return zeroResultsView;
			}
			
			// set result info
			BGCOffenderBean[] beans;
			try {
				beans = bgcManager.getOffenders(response.getBgcResponseId());
			} catch (TorqueException e) {
				throw new SearchException(e);
			}

			ArrayList<BGCOffenderBean> offenders = new ArrayList<BGCOffenderBean>();
			for (BGCOffenderBean b : beans) {
				offenders.add(b);
			}
			
//			ArrayList<BGCOffenderBean> offenders2 = new ArrayList<BGCOffenderBean>(offenders);
//			for (int i=0; i<30; i++) {
//				offenders.addAll(offenders2);
//			}
			
			// finalize charge?
			// XXX todo
			
			// send to results page (or no results)
			
			map.addAttribute("command", new ResultsCommand());
			map.addAttribute("pageSizes", pageSizes);
			map.addAttribute("pageSize", pageSize);
			map.addAttribute("offenders", offenders);
			map.addAttribute("offendersCount", offenders.size());
			return resultsView;
			
		} catch (SearchException e) {			
			// void charge?
			// XXX todo
			logger.error("Error getting results", e);

			// send to error page
			return vwError;			
		} 
	}
	
	@RequestMapping("/funnel/resultDetailsTest.do")
	public String getResultDetailsTest(HttpSession session, ModelMap map) {		
		return getResultDetails(session, map, 47901, "1c283492519e2041cce68236ce57f03c382eb1d3", true);
	}	
			
	@RequestMapping("/funnel/resultDetails.do")
	public String getResultDetails(
			HttpSession session,
			ModelMap map,
			@RequestParam("offenderId") Integer offenderId,
			@RequestParam("hashKey") String hashKey,
			@RequestParam(value="test",required=false) Boolean test
			) {
		
		if (test == null) { test = false; }

		if (!verifySession(session, test)) {
			return purchaseSearchRedir;
		}
		
		try {
			// fetch the result first
			bgcManager.fetchOffenderDetails(offenderId);
			
			BGCOffenderBean bean = bgcManager.getOffender(offenderId, hashKey);
			map.addAttribute("o", bean);
			
			BGCAliasBean[] aliases = bgcManager.getAliases(offenderId);
			map.addAttribute("aliases", aliases);
			logger.info("aliases: " + aliases.length);
			
			BGCOffenseBean[] offenses = bgcManager.getOffenses(offenderId);
			map.addAttribute("offenses", offenses);
			logger.info("offenses: " + offenses.length);
			
			return resultDetailsView;
		} catch (Exception e) {
			return vwError;
		}
	}
	
	@RequestMapping(value ="/funnel/resultDetailsBJL.do", method = RequestMethod.GET)
	public String getResultDetailsBJL(
			HttpSession session,
			ModelMap map,
			@RequestParam("resultId") Integer resultId,
			@RequestParam(value="test",required=false) Boolean test
			) {
		
		if (test == null) { test = false; }

		// XXX fix this
//		if (!verifySession(session, test)) {
//			return purchaseRedir;
//		}
		
		try {
			// fetch the result first
			NewBJLResults result = bjlManager.getResult(resultId);
			map.addAttribute("result", result);			
			return vwBJLResultDetails;
		} catch (Exception e) {
			return vwError;
		}
	}
	
	@RequestMapping(value ="/funnel/resultDetailsBJL.do", method = RequestMethod.POST)
	public String getAllResultDetailsBJL(
			HttpSession session,
			ModelMap map,
			@RequestParam("resultIds") Integer[] resultIds,
			@RequestParam(value="test",required=false) Boolean test
			) {
		
		if (test == null) { test = false; }

		// XXX fix this
//		if (!verifySession(session, test)) {
//			return purchaseRedir;
//		}
		
		try {
			// fetch the result first
			ArrayList<NewBJLResults> BJLList = new ArrayList<NewBJLResults>();
			NewBJLResults result = new NewBJLResults();
			for(Integer i : resultIds){
				result = bjlManager.getResult(i);
				BJLList.add(result);
			}
			map.addAttribute("BJLList", BJLList);			
			return vwBJLResultDetails;
		} catch (Exception e) {
			return vwError;
		}
	}
	
	// post handles checkboxes for print selected
	@RequestMapping(value = "/funnel/printResults.do", method = RequestMethod.POST)
	public String printResults(
			WebRequest request,
			HttpSession session,
			ModelMap map,
			ResultsCommand command
			)  {
		//request.getParameter("");
		Map params = request.getParameterMap();
//		for (Object o : params.keySet()) {
//			logger.info("o: " + o + ":" + params.get(o));
//		}
		Integer responseId = (Integer) session.getAttribute("responseId");
		
		// this will be returned to the view
		ArrayList<BGCOffenderBean> offenders = new ArrayList<BGCOffenderBean>();
		
		// list to fetch, we will create this at the same time
		ArrayList<Integer> offToFetch = new ArrayList<Integer>();
		
		try {
			// best way to do this is probably to get all responses so there's
			// only 1 db call. then if we need to do only checked, we can easily 
			// fetch those out.
			BGCOffenderBean[] offendersAll = bgcManager.getOffenders(responseId);

			if (params.get("printChecked") == null) {
				// print all
				logger.info("print all for: " + responseId);
				for (BGCOffenderBean o : offendersAll) {
					offenders.add(o);
					offToFetch.add(o.getBgcOffenderId());
				}
			} else {
				// print checked
				logger.info("print checked for: " + responseId);
				HashSet<Integer> checked = new HashSet<Integer>();
				String results[] = command.getResultsToPrint();
				
				for (String s : results) {
					//logger.info("s: " + s);
					checked.add(Integer.parseInt(s));
				}
				
				for (BGCOffenderBean o : offendersAll) {
					if (checked.contains(o.getBgcOffenderId())) {
						offenders.add(o);
						offToFetch.add(o.getBgcOffenderId());
					}
				}
			}
			
			// make sure all the offenders are fetched	
			bgcManager.fetchOffenderDetails(offToFetch);

			// ok, we have offenders, now we need to get the other info
			HashMap<Integer,BGCAliasBean[]> aliasesMap = new HashMap<Integer,BGCAliasBean[]>();
			for (BGCOffenderBean o : offenders) {
				Integer id = o.getBgcOffenderId();
				aliasesMap.put(id, bgcManager.getAliases(id));
			}
			
			HashMap<Integer,BGCOffenseBean[]> offensesMap = new HashMap<Integer,BGCOffenseBean[]>();
			for (BGCOffenderBean o : offenders) {
				Integer id = o.getBgcOffenderId();
				offensesMap.put(id, bgcManager.getOffenses(id));
			}
			
			// add everything to the map
			map.addAttribute("offenders", offenders);
			map.addAttribute("aliasesMap", aliasesMap);
			map.addAttribute("offensesMap", offensesMap);
		
			return printResultsView;
			
		} catch (Exception e) {
			return vwError;
		}
	}
}