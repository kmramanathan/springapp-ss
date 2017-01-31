package springapp.web.funnel;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.searchsystems.limestone.bean.BGCAliasBean;
import net.searchsystems.limestone.bean.BGCOffenderBean;
import net.searchsystems.limestone.bean.BGCOffenseBean;
import net.searchsystems.limestone.bean.BGCRequestBean;
import net.searchsystems.limestone.bean.BGCResponseBean;

import org.apache.commons.lang.StringUtils;
import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.TorqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import springapp.domain.NewBJLResults;
import springapp.domain.CriminalSSN.CriminalSSNRequestBean;
import springapp.domain.CriminalSSN.CriminalSSNResponseBean;
import springapp.domain.CriminalSearch.CriminalResponseBean;
import springapp.domain.RealProperty.RealPropRequestBean;
import springapp.domain.RealProperty.RealPropResponseBean;
import springapp.domain.eviction.EvictionResults;
import springapp.domain.eviction.EvictionSearches;
import springapp.manager.SearchException;
import springapp.manager.SpringAliasSearchManager;
import springapp.manager.SpringBGCSearchManager;
import springapp.manager.SpringEvictionSearchManager;
import springapp.manager.SpringPropertySearchManager;
import springapp.web.funnel.NewAliasSearchForm.AliasSearchFormCommand;
import springapp.web.funnel.NewSearchForm.SearchFormCommand;

@SessionAttributes("command")
@Controller
public class NewResultsDisplay extends AbstractFunnelController {
	protected static final Integer pageSizes[] = {10, 25, 50};
	
	@Autowired
	private SpringBGCSearchManager bgcManager;
	@Autowired
	private SpringAliasSearchManager aliasSearchManager;
	@Autowired
	private SpringEvictionSearchManager evictionManager;	
	@Autowired
	private SpringPropertySearchManager realpropSearchManager;
	
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
	
	
	@RequestMapping(value = "/funnel/newResults.do", method = RequestMethod.GET)
	public String getResults(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="pageSize",required=false) Integer pageSize,
			@RequestParam(value="", required=false) Boolean download,
			HttpServletResponse hresponse
			)  {		
		
		
		if (test == null) { test = false; }
		if (pageSize == null) { pageSize = 10; }
		if(download == null){download=false;}
		if (!verifySession(session, test))
		{
			return landingHome;
		}
		Integer responseId = (Integer) session.getAttribute("responseId");
		if (responseId == null) {
			return newvwError;
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
			
			if (response.getQuantityReturned() == 0) 
			{				
				map.addAttribute("firstName", request.getFirstName());
				map.addAttribute("lastName", request.getLastName());
				SearchFormCommand sfc = (SearchFormCommand) session.getAttribute("searchFormCommand");
				
				if(sfc != null)
				{
				map.addAttribute("location", sfc.getBgcState());
				}
				
				String searchDOB = request.getDobMonth() + "/" + request.getDobDay() + "/" + request.getDobYear();
				map.addAttribute("DOB", searchDOB);
				map.addAttribute("aliasSearchFlag", false);
				//session.removeAttribute("bgcRequestId");
				session.removeAttribute("responseId");
				session.removeAttribute("searchFormCommand");
				
				return newzeroResultsView;
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
				if(!b.getProvider().equalsIgnoreCase("P.R.I.O.R.S.")){
					offenders.add(b);
				}
			}
			
//			ArrayList<BGCOffenderBean> offenders2 = new ArrayList<BGCOffenderBean>(offenders);
//			for (int i=0; i<30; i++) {
//				offenders.addAll(offenders2);
//			}
			
			// finalize charge?
			// XXX todo
			// generate the download text format file by client 
			//when they click the download link on the result page
			if(download == true)
			{
				hresponse.setContentType("text/plain");
				hresponse.setHeader("Content-Disposition", "attachment;filename="+request.getLastName()+"+"+request.getFirstName()+".txt");
				BGCOffenseBean[] bgcOffenseList=null;
				BGCAliasBean[] bgcAliasList=null;
				try
				{
					PrintWriter pw=hresponse.getWriter();
					int i=0;
					pw.println("Criminal Records Search - Result Details");
					pw.println("*****************************************");
					for (BGCOffenderBean bean : offenders) {
						int id=bean.getBgcOffenderId();
						bgcOffenseList=bgcManager.getOffenses(id);
						bgcAliasList = bgcManager.getAliases(id);
						if(bgcOffenseList.length > 0)
						{
							i=i+1;
							pw.println("Record #"+i);
							pw.println();
							pw.println("Offender Info");
							pw.println("*************");
							generateOffendersTextFormat(pw,bean);
						}
						if(bgcOffenseList.length > 0 && bgcAliasList.length > 0)
						{
							pw.println("Aliases");
							pw.println("*******************");
							generateAliasTextFormat(pw, bgcAliasList);
						}
						if(bgcOffenseList.length > 0)
						{
							pw.println("Offenses");
							pw.println("********");
							genrateOffenseTextFormat(pw,bgcOffenseList);
						}
						
					}
					
					pw.println("");
					pw.println("DISCLAIMER");
					pw.println("**********");
					pw.println("Search Systems provides no warranty of any type as to the accuracy of this information, and any reliance on this information is solely ");
					pw.println("at your own risk and responsibility. All information retrieved from or through SearchSystems.net must be utilized in accordance with the");
					pw.println("User Agreement and all applicable state and federal laws.");
					pw.println("");
					pw.println("Copyright © 1997-2013 Search Systems, Inc. All rights reserved.");
					pw.close();
				}
				catch (Exception e) {
					// TODO: handle exception
					logger.error("Error: While writing on the file :"+e);
				}
			}
			// send to results page (or no results)
			if(offenders.size() == 0)
			{
				map.addAttribute("firstName", request.getFirstName());
				map.addAttribute("lastName", request.getLastName());
				SearchFormCommand sfc = (SearchFormCommand) session.getAttribute("searchFormCommand");
				
				if(sfc != null)
				{
				map.addAttribute("location", sfc.getBgcState());
				}
				
				String searchDOB = request.getDobMonth() + "/" + request.getDobDay() + "/" + request.getDobYear();
				map.addAttribute("DOB", searchDOB);
				map.addAttribute("aliasSearchFlag", false);
				//session.removeAttribute("bgcRequestId");
				session.removeAttribute("responseId");
				session.removeAttribute("searchFormCommand");
				
				return newzeroResultsView;
			}
			map.addAttribute("command", new ResultsCommand());
			map.addAttribute("pageSizes", pageSizes);
			map.addAttribute("pageSize", pageSize);
			map.addAttribute("offenders", offenders);
			map.addAttribute("offendersCount", offenders.size());
			
			SearchFormCommand sfc = (SearchFormCommand) session.getAttribute("searchFormCommand");
			
			if(sfc != null)
			{
			map.addAttribute("searchState", sfc.getBgcState());
			}
			
			map.addAttribute("aliasSearchFlag", false);
			
			//session.removeAttribute("searchFormCommand");
			
			return newresultsView;
			
		} catch (SearchException e) {			
			// void charge?
			// XXX todo
			logger.error("Error getting results", e);

			// send to error page
			return newvwError;			
		} 
	}
	
	
	@RequestMapping(value = "/funnel/newAliasResults.do", method = RequestMethod.GET)
	public String getResultsForAlias(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="pageSize",required=false) Integer pageSize,
			@RequestParam(value="", required=false) Boolean download,
			HttpServletResponse hresponse
			)  {		
		
		logger.info("Inside  newAliasResults - Download flag - " + download);
		if (test == null) { test = false; }
		if (pageSize == null) { pageSize = 10; }
		if(download == null){download=false;}
		if (!verifySession(session, test))
		{
			return landingHome;
		}
		Integer responseId = (Integer) session.getAttribute("responseId");
		if (responseId == null) {
			return newvwError;
		} else {
			map.addAttribute("responseId", responseId);
		}
		
		try {
			BGCRequestBean request;
			BGCResponseBean response;
			try {
				response = aliasSearchManager.getResponse(responseId);
				request = aliasSearchManager.getRequest(response.getBgcRequestId());
			} catch (NoRowsException e) {
				throw new SearchException(e);
			} catch (TooManyRowsException e) {
				throw new SearchException(e);
			} catch (TorqueException e) {
				throw new SearchException(e);
			}
			logger.info("responseId: " + response.getBgcResponseId());
			
			if (response.getQuantityReturned() == 0) 
			{				
				map.addAttribute("firstName", request.getFirstName());
				map.addAttribute("lastName", request.getLastName());
				AliasSearchFormCommand sfc = (AliasSearchFormCommand) session.getAttribute("aliasSearchFormCommand");
				
				if(sfc != null)
				{
				map.addAttribute("location", sfc.getBgcState());
				}
				
				String searchDOB = request.getDobMonth() + "/" + request.getDobDay() + "/" + request.getDobYear();
				map.addAttribute("DOB", searchDOB);
				
				map.addAttribute("aliasSearchFlag", true);
				String ssnStr = String.valueOf(request.getDobYearRange());
				ssnStr = (ssnStr.length() < 9 ? String.format("%0" + (9 - ssnStr.length()) + "d%s", 0 , 
						ssnStr) : ssnStr);
				map.addAttribute("aliasSSN", ssnStr);
				
				//session.removeAttribute("bgcRequestId");
				session.removeAttribute("responseId");
				session.removeAttribute("aliasSearchFormCommand");
				
				return newzeroResultsView;
			}
			
			// set result info
			BGCOffenderBean[] beans;
			try {
				beans = aliasSearchManager.getOffenders(response.getBgcResponseId());
				logger.info("Offenders Length: " + beans.length);
			} catch (TorqueException e) {
				throw new SearchException(e);
			}

			ArrayList<BGCOffenderBean> offenders = new ArrayList<BGCOffenderBean>();
			for (BGCOffenderBean b : beans) {
				if(!b.getProvider().equalsIgnoreCase("P.R.I.O.R.S.")){
					offenders.add(b);
				}
			}
			logger.info("Offenders size: " + offenders.size());
//			ArrayList<BGCOffenderBean> offenders2 = new ArrayList<BGCOffenderBean>(offenders);
//			for (int i=0; i<30; i++) {
//				offenders.addAll(offenders2);
//			}
			
			// finalize charge?
			// XXX todo
			// generate the download text format file by client 
			//when they click the download link on the result page
			if(download == true)
			{
				hresponse.setContentType("text/plain");
				hresponse.setHeader("Content-Disposition", "attachment;filename="+request.getLastName()+"+"+request.getFirstName()+".txt");
				BGCOffenseBean[] bgcOffenseList=null;
				BGCAliasBean[] bgcAliasList=null;
				try
				{
					PrintWriter pw=hresponse.getWriter();
					int i=0;
					pw.println("Alias Search - Result Details");
					pw.println("*****************************************");
					for (BGCOffenderBean bean : offenders) {
						int id=bean.getBgcOffenderId();
						bgcOffenseList=aliasSearchManager.getOffenses(id);
						bgcAliasList = aliasSearchManager.getAliases(id);
						if(bgcOffenseList.length > 0)
						{
							i=i+1;
							pw.println("Record #"+i);
							pw.println();
							pw.println("Offender Info");
							pw.println("*************");
							generateOffendersTextFormat(pw,bean);
						}
						if(bgcOffenseList.length > 0 && bgcAliasList.length > 0)
						{
							pw.println("Aliases");
							pw.println("*******************");
							generateAliasTextFormat(pw, bgcAliasList);
						}
						if(bgcOffenseList.length > 0)
						{
							pw.println("Offenses");
							pw.println("********");
							genrateOffenseTextFormat(pw,bgcOffenseList);
						}
						
					}
					
					pw.println("");
					pw.println("DISCLAIMER");
					pw.println("**********");
					pw.println("Search Systems provides no warranty of any type as to the accuracy of this information, and any reliance on this information is solely ");
					pw.println("at your own risk and responsibility. All information retrieved from or through SearchSystems.net must be utilized in accordance with the");
					pw.println("User Agreement and all applicable state and federal laws.");
					pw.println("");
					pw.println("Copyright © 1997-2013 Search Systems, Inc. All rights reserved.");
					pw.close();
				}
				catch (Exception e) {
					// TODO: handle exception
					logger.error("Error: While writing on the file :"+e);
					e.printStackTrace();
				}
			}
			// send to results page (or no results)
			if(offenders.size() == 0)
			{
				map.addAttribute("firstName", request.getFirstName());
				map.addAttribute("lastName", request.getLastName());
				AliasSearchFormCommand sfc = (AliasSearchFormCommand) session.getAttribute("aliasSearchFormCommand");
				
				if(sfc != null)
				{
				map.addAttribute("location", sfc.getBgcState());
				}
				
				String searchDOB = request.getDobMonth() + "/" + request.getDobDay() + "/" + request.getDobYear();
				map.addAttribute("DOB", searchDOB);
				
				map.addAttribute("aliasSearchFlag", true);
				map.addAttribute("aliasSSN", sfc.getBgcSsn());
				
				//session.removeAttribute("bgcRequestId");
				session.removeAttribute("responseId");
				session.removeAttribute("aliasSearchFormCommand");
				
				return newzeroResultsView;
			}
			map.addAttribute("command", new ResultsCommand());
			map.addAttribute("pageSizes", pageSizes);
			map.addAttribute("pageSize", pageSize);
			map.addAttribute("offenders", offenders);
			map.addAttribute("offendersCount", offenders.size());
			
			AliasSearchFormCommand sfc = (AliasSearchFormCommand) session.getAttribute("aliasSearchFormCommand");
			
			if(sfc != null)
			{
			map.addAttribute("searchState", sfc.getBgcState());
			}
			
			map.addAttribute("aliasSearchFlag", true);
			map.addAttribute("aliasSSN", sfc.getBgcSsn());
			
			//session.removeAttribute("aliasSearchFormCommand");
			
			return newresultsView;
			
		} catch (SearchException e) {			
			// void charge?
			// XXX todo
			logger.error("Error getting results", e);

			// send to error page
			return newvwError;			
		} 
	}
	
	
	
				
	private void generateAliasTextFormat(PrintWriter pw,
			BGCAliasBean[] bgcAliasList) {
		// TODO Auto-generated method stub
		int j=1;
		
		for (BGCAliasBean bgcAliasBean : bgcAliasList) {
			
			pw.println(j+"\tFirst Name\t\tLast Name\t\tMiddle Name\t\tSuffix");
			if(bgcAliasBean.getFirstName().isEmpty())
			{
				pw.print("---");
			}
			else {
				pw.print("\t"+bgcAliasBean.getFirstName()+"\t\t");
			}
			if(bgcAliasBean.getLastName().isEmpty())
			{
				pw.print("---");
			}
			else {
				pw.print(bgcAliasBean.getLastName()+"\t\t\t");
				
			}
			if(bgcAliasBean.getMiddleName().isEmpty())
			{
				pw.print("---");
			}
			else {
				pw.print(bgcAliasBean.getMiddleName()+"\t\t\t");
			}
			if(bgcAliasBean.getSuffix().isEmpty())
			{
				pw.print("---");
			}
			else {
				pw.print(bgcAliasBean.getSuffix());
			}
			pw.println("");
			j++;
		}
		pw.println("");
	}


	private void genrateOffenseTextFormat(PrintWriter pw,
			BGCOffenseBean[] bgcOffenseList) {
		for (BGCOffenseBean bgcOffenseBean : bgcOffenseList) {
			if(!StringUtils.isEmpty(bgcOffenseBean.getDescription()))      //if(!bgcOffenseBean.getDescription().isEmpty())
			{
				pw.println("Description");
				pw.println(bgcOffenseBean.getDescription());
			}
			if(!StringUtils.isEmpty(bgcOffenseBean.getStatute()))
			{
				pw.println("Statute");
				pw.println(bgcOffenseBean.getStatute());

			}
			if(!StringUtils.isEmpty(bgcOffenseBean.getDegreeOfOffense()))
			{
				pw.println("Degree Of Offense");
				pw.println(bgcOffenseBean.getDegreeOfOffense());

			}
			if(!StringUtils.isEmpty(bgcOffenseBean.getOffenseDate()))
			{
				pw.println("Offense Date");
				pw.println(bgcOffenseBean.getOffenseDate());

			}
			if(!StringUtils.isEmpty(bgcOffenseBean.getArrestDate()))
			{
				pw.println("Arrest Date");
				pw.println(bgcOffenseBean.getArrestDate());

			}
			if(!StringUtils.isEmpty(bgcOffenseBean.getArrestingAgency()))
			{
				pw.println("Arresting Agency");
				pw.println(bgcOffenseBean.getArrestingAgency());

			}
			if(!StringUtils.isEmpty(bgcOffenseBean.getDisposition()))
			{
			pw.println("Disposition");pw.println(bgcOffenseBean.getDisposition());

			}
			if(!StringUtils.isEmpty(bgcOffenseBean.getDispositionDate()))
			{
			pw.println("Disposition Date");pw.println(bgcOffenseBean.getDispositionDate());

			}
			if(!StringUtils.isEmpty(bgcOffenseBean.getSentence()))
			{
			pw.println("Sentence");pw.println(bgcOffenseBean.getSentence());

			}
			if(!StringUtils.isEmpty(bgcOffenseBean.getSentenceDate()))
			{
			pw.println("Sentence Date");pw.println(bgcOffenseBean.getSentenceDate());

			}
			if(!StringUtils.isEmpty(bgcOffenseBean.getConfinement()))
			{
			pw.println("Confinement");pw.println(bgcOffenseBean.getConfinement());

			}
			if(!StringUtils.isEmpty(bgcOffenseBean.getProbation()))
			{
			pw.println("Probation");pw.println(bgcOffenseBean.getProbation());

			}
			if(!StringUtils.isEmpty(bgcOffenseBean.getFine()))
			{
			pw.println("Fine");
			pw.println(bgcOffenseBean.getFine());

			}
			if(!StringUtils.isEmpty(bgcOffenseBean.getPlea()))
			{
			pw.println("Plea");
			pw.println(bgcOffenseBean.getPlea());

			}

			
		}
		pw.println("");
	}


	private void generateOffendersTextFormat(PrintWriter pw,
			BGCOffenderBean bean) {
		String address, city, state, postal, gender, race, county, country, height, weight, eye, hair, provider, rstate;
		//address
		if(StringUtils.isEmpty(bean.getStreet()))        //if(bean.getStreet().isEmpty())
		{
			address="---";
		}else {
			address=bean.getStreet();
		}
		
		//city
		if(StringUtils.isEmpty(bean.getCity()))
		{
			city="---";
		}else {
			city=bean.getCity();
		}
		
		logger.info("isEmpty(bean.getCounty()) - " + StringUtils.isEmpty(bean.getCounty()));
		//county
		if(StringUtils.isEmpty(bean.getCounty()))
		{
			county="---";
		}else {
			county=bean.getCounty();
		}
		//country
		if(StringUtils.isEmpty(bean.getCountry()))
		{
			country="---";
		}else {
			country=bean.getCountry();
		}
		//gender
		if(StringUtils.isEmpty(bean.getGender()))
				{
					gender="---";
		}else {
					gender=bean.getGender();
			}
		//Race
		if(StringUtils.isEmpty(bean.getRace()))
		{
			race="---";
		}else {
				race=bean.getRace();
			}
		//state
		if(StringUtils.isEmpty(bean.getState()))
			{
				state="---";
		}else {
				state=bean.getState();
				}
		//postal
		if(StringUtils.isEmpty(bean.getPostalCode()))
			{
				postal="---";
		}else {
			postal=bean.getPostalCode();
				}
		//height
		if(StringUtils.isEmpty(bean.getHeightFeet()))
			{
				height="---";
		}else {
			height=bean.getHeightFeet();
				}
		//weight
		if(StringUtils.isEmpty(bean.getWeight()))
			{
				weight="---";
		}else {
				weight=bean.getWeight();
				}
		//eye color
		if(StringUtils.isEmpty(bean.getEyeColor()))
			{
				eye="---";
		}else {
				eye=bean.getEyeColor();
				}
		//hair color
		if(StringUtils.isEmpty(bean.getHairColor()))
			{
				hair="---";
		}else {
				hair=bean.getHairColor();
				}
		//Provider
		if(StringUtils.isEmpty(bean.getProvider()))
			{
				provider="---";
		}else {
			provider=bean.getProvider();
				}
		//Record State
		if(StringUtils.isEmpty(bean.getRecordState()))
			{
				rstate="---";
		}else {
			rstate=bean.getRecordState();
				}
		
		pw.println("FullName\t\t\t\tDOB\t\tID");
		pw.println(bean.getFullName()+"\t\t\t"+bean.getDateOfBirth()+"\t"+bean.getRecordOffenderId());
		pw.println("Address\t\t\t\tCity\t\tState\tPostal Code");
		pw.println(address+"\t\t"+city+"\t"+state+"\t"+postal);
		pw.println("Gender\t\t\t\tRace\t\tCounty\t\tCountry");
		pw.println(gender+"\t\t\t\t"+race+"\t\t"+county+"\t\t"+country);
		pw.println("Height\t\t\t\tWeight\t\tEye Color\tHair Color");
		pw.println(height+"\t\t\t\t"+weight+"\t\t"+eye+"\t\t"+hair);
		pw.println("Record Source\t\t\t\t\tRecord state");
		pw.println(provider+"\t\t"+rstate);
		pw.println("");

		
	}


	@RequestMapping("/funnel/newResultDetails.do")
	public String getResultDetails(
			HttpSession session,
			ModelMap map,
			@RequestParam("offenderId") Integer offenderId,
			@RequestParam("hashKey") String hashKey,
			@RequestParam(value="test",required=false) Boolean test
			) {
		
		if (test == null) { test = false; }

		if (!verifySession(session, test)) {
			return landingHome;
		}
		
		try 
		{
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
			
			return newresultDetailsView;
		} 
		catch (Exception e) 
		{
			return newvwError;
		}
	}
	
		// post handles checkboxes for print selected
	@RequestMapping(value = "/funnel/newPrintResults.do", method = RequestMethod.POST)
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
		
			return newprintResultsView;
			
		} catch (Exception e) {
			return newvwError;
		}
	}
	
	@RequestMapping(value="/funnel/evictionResults.do")
	public String submitEvictionData(HttpSession session,ModelMap map)
	{
		Integer UserSearchId=(Integer) session.getAttribute("userSearchId");
		logger.info("user search id: "+UserSearchId);
		if(UserSearchId == null)
		{
			
			return vwError;
		}
		map.addAttribute("UserSearchId", UserSearchId);
		try
		{
		EvictionSearches searchResult=evictionManager.getSearch(UserSearchId);
		if(searchResult.getMatchCount() == 0)
		{
			map.addAttribute("evictionSearch", true);
			map.addAttribute("evictionName", searchResult.getFirstname()+ " " +searchResult.getLastname());
			map.addAttribute("evictionBName", searchResult.getBusinessname());
			map.addAttribute("evictionState", searchResult.getState());
			map.addAttribute("evictionCity", searchResult.getCity());
			map.addAttribute("evictionrefer", searchResult.getReference());
			session.removeAttribute("UserSearchId");
			session.removeAttribute("evictionSearchFormCommand");
			session.removeAttribute("evictionBusinessSearchFormCommand");
			return NoEvictionResult;
			
		}
			List<EvictionResults> eList=evictionManager.getAllResults(UserSearchId);
			if(eList != null && eList.size() > 0)
			{
				map.addAttribute("eList", eList);
				map.addAttribute("eListsize", eList.size());
				
			}
			map.addAttribute("evictionName", searchResult.getFirstname()+ " " +searchResult.getLastname());
			map.addAttribute("evictionBName", searchResult.getBusinessname());
			map.addAttribute("evictionState", searchResult.getState());
			map.addAttribute("evictionCity", searchResult.getCity());
			map.addAttribute("evictionrefer", searchResult.getReference());
			session.removeAttribute("UserSearchId");
	
			return EvictionResultDetails;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("error:"+e);
			return newvwError;
		}
		
		
	}
	
	
	//New Criminal SSN search 
		@RequestMapping(value="/funnel/newSSNResults.do", method = RequestMethod.GET)
		public String submitCriminalSSNData(HttpSession session,ModelMap map,
				@RequestParam(value="downloadall", required = false) Boolean downloadAll,
				HttpServletResponse rHttpServletResponse)
		{
			 
			if(downloadAll == null)
			{
				//logger.info("SSN_Result_DownloadALL Null----: "+downloadAll);
				downloadAll = false;// || downloadAll == null
			}
			Integer UserSearchId=(Integer) session.getAttribute("userSearchId");			
		try
			{
					//			
					SearchFormCommand searchData = (SearchFormCommand) session.getAttribute("searchFormCommand");
					
					List<CriminalSSNResponseBean> eList = new ArrayList<CriminalSSNResponseBean>();	
					
					if(session.getAttribute("SSNResults")!= null)
					{
						logger.info("SSreslt sessn chck 722");
						eList = (List<CriminalSSNResponseBean>) session.getAttribute("SSNResults");
						logger.info("SSN result elist-=>count--> " +eList.size());
					}
					if(eList != null && eList.size() > 0)
					{
						map.addAttribute("eList", eList);
						map.addAttribute("eListsize", eList.size());					
					}
					if(eList.size() == 0 && session.getAttribute("searchFormCommand")!=null)
					{
						logger.info("SSN_searchFormCommand Null 730----: ");
						
						map.addAttribute("ssnSearchName", searchData.getBgcFirstName()+""+ searchData.getBgcLastName());
						map.addAttribute("ssnState", searchData.getBgcState());
						String sDOB= searchData.getBgcDobMonth()+"/"+ searchData.getBgcDobDay() +"/"+searchData.getBgcDobYear();
						map.addAttribute("ssnDOB", sDOB);
						//map.addAttribute("ssnNumber", searchData.getBgcSsn());
						return newzeroResultsView;
					}
					String searchName =searchData.getBgcFirstName()+ " " +searchData.getBgcLastName() ;
					if(downloadAll == true)
					{
						logger.info("SSN_Result_DownloadALL---- IF: "+downloadAll);
						DownloadRecords(session,rHttpServletResponse,searchName);
					}	
					
					if(eList.size() > 0)
					{
						logger.info("SSN_Result_null-> 750: ");
						SearchFormCommand ssnSearchData = (SearchFormCommand) session.getAttribute("searchFormCommand");
						map.addAttribute("ssnSearchName", ssnSearchData.getBgcFirstName()+""+ ssnSearchData.getBgcLastName());
						map.addAttribute("ssnState", ssnSearchData.getBgcState());
						String sDOB= ssnSearchData.getBgcDobMonth()+"/"+ ssnSearchData.getBgcDobDay() +"/"+ssnSearchData.getBgcDobYear();
						map.addAttribute("ssnDOB", sDOB);
						//map.addAttribute("ssnNumber", ssnSearchData.getBgcSsn());
					}
					return newresultDetailsView;
				
			}
			catch (Exception e) {
				// TODO: handle exception
				logger.error("Criminal SSN_792->tDetails__error:"+e);
				return newvwError;
			}
			
			
		}
		
	
	
	//Real Property search
	@RequestMapping(value="/funnel/realPropResults.do", method = RequestMethod.GET)
	public String submitRealPropData(HttpSession session,ModelMap map,
			@RequestParam(value="downloadall", required = false) Boolean downloadAll,
			HttpServletResponse rHttpServletResponse)
	{
		logger.info("_realProp_Result_DownloadALL on GET----: "+downloadAll);
		if(downloadAll == null)
		{
			logger.info("_realProp_Result_DownloadALL Null----: "+downloadAll);
			downloadAll = false;// || downloadAll == null
		}
		Integer UserSearchId=(Integer) session.getAttribute("userSearchId");
		//logger.info("_realProp_Result_user search id: "+UserSearchId);
		//if(UserSearchId == null)
		{
			
			//return vwError;
		}
		//map.addAttribute("UserSearchId", UserSearchId);
		try
			{			
			//RealPropSearches searchResult= realpropSearchManager.getSearch(UserSearchId);			
			RealPropRequestBean searchResult=(RealPropRequestBean) session.getAttribute("RPRequestData");
			if(searchResult !=null && searchResult.getMatchCount() == 0)
			{ 
				map.addAttribute("realPropSearch", true);
				map.addAttribute("realPropName", searchResult.getFirstName()+ " " +searchResult.getLastName());				
				map.addAttribute("realPropAppartnum", searchResult.getAppartNum());
				map.addAttribute("realPropStreet", searchResult.getStreet());
				map.addAttribute("realPropState", searchResult.getState());
				map.addAttribute("realPropCity", searchResult.getCity());
				map.addAttribute("realProprefer", searchResult.getReference());
				session.removeAttribute("UserSearchId");
				session.removeAttribute("RealPropSearchFormCommand");				
				return NoResultRealProp;				
			}
			
			List<RealPropResponseBean> eList = new ArrayList<RealPropResponseBean>();		
			if(session.getAttribute("RPResults")!= null)
			{
				eList = (List<RealPropResponseBean>) session.getAttribute("RPResults");
			}
			if(eList != null && eList.size() > 0)
			{
				map.addAttribute("eList", eList);
				map.addAttribute("eListsize", eList.size());					
			}
			String searchName =searchResult.getFirstName()+ " " +searchResult.getLastName() ;
			if(downloadAll == true)
			{
				logger.info("_realProp_Result_DownloadALL---- IF: "+downloadAll);
				DownloadRecords(session,rHttpServletResponse,searchName);
			}
				//logger.info("B4 Searchresutls_RP: "+ searchResult !=null);
				if(searchResult !=null)
				{
					if(session.getAttribute("RPNameSearch") != null)
					{
						map.addAttribute("realPropName", searchResult.getFirstName()+ " " +searchResult.getLastName());
						logger.info("Name_Sesson");
					}
					else
						map.addAttribute("realPropName","");
					if(session.getAttribute("RPAdresSearch") != null)
					{
						logger.info("Address_Sesson");
						map.addAttribute("realPropAppartnum", searchResult.getAppartNum());
						map.addAttribute("realPropStreet", searchResult.getStreet());						
						map.addAttribute("realPropCity", searchResult.getCity());					
					}
					else
					{
						map.addAttribute("realPropAppartnum", "");
						map.addAttribute("realPropStreet", "");						
						map.addAttribute("realPropCity", "");
					}
					
					map.addAttribute("realPropState", searchResult.getState());
					map.addAttribute("realProprefer", searchResult.getReference());									
					session.removeAttribute("UserSearchId");
				}
				
				return RealPropResultDetails;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("RealPropResultDetails__error:"+e);
			return newvwError;
		}
		
		
	}
	
	private void DownloadRecords(HttpSession session,HttpServletResponse rHttpServletResponse,String searchName) {
		
		// TODO Auto-generated method stub		 
	    //logger.info("AllResultz:"+ session.getAttribute("ckckedResultID"));
	    List<RealPropResponseBean> selectdResults=null;
		List<RealPropResponseBean> proprtyList=null;
		String bName="Real Property";
		
		if(!bName.isEmpty())
			selectdResults = (List<RealPropResponseBean>) session.getAttribute("RPResults");
		
		//if(download == true)
		{
			rHttpServletResponse.setContentType("text/plain");
			rHttpServletResponse.setHeader("Content-Disposition", "attachment;filename=RealProp_"+ searchName.replace(' ', '_')+".txt");
			
			try
			{
				PrintWriter pw=rHttpServletResponse.getWriter();
				int i=0;
				pw.println(" Real Property Search - Result Details");
				pw.println("*************************************************");
				
				//Map<String,List<RealPropResponseBean>> bankruptcyMap = GetRealPropResultsMap(proprtyList);
				
				for (Iterator iterator = selectdResults.iterator(); iterator.hasNext();) 
				{					
					RealPropResponseBean newPropertyResults = (RealPropResponseBean) iterator.next();
					//List<RealPropResponseBean> tmpBnkrptcyList = newPropertyResults;
					RealPropResponseBean tmpBnkrptcy = newPropertyResults;
					PrintBJLRecords(pw, i++, "Bankruptcy Info" ,tmpBnkrptcy,"", "");					
					pw.println("");
				}
				
				pw.println("");
				pw.println("DISCLAIMER");
				pw.println("**********");
				pw.println("Search Systems provides no warranty of any type as to the accuracy of this information, and any reliance on this information is solely ");
				pw.println("at your own risk and responsibility. All information retrieved from or through SearchSystems.net must be utilized in accordance with the");
				pw.println("User Agreement and all applicable state and federal laws.");
				pw.println("");
				pw.println("Copyright © 1997-2014 Search Systems, Inc. All rights reserved.");
				pw.close();
			}
			catch (Exception e) 
			{
				// TODO: handle exception
				logger.error("Error: While downloading the text format:"+e);
			}

		}
		
		
	}
	
	private Map<String,List<RealPropResponseBean>> GetRealPropResultsMap(List<RealPropResponseBean> bjlList)
	{
		Map<String,List<RealPropResponseBean>> rpListMap = new Hashtable<String,List<RealPropResponseBean>>();					 
		for (Iterator<RealPropResponseBean> iterator = bjlList.iterator(); iterator.hasNext();) 
		{
			RealPropResponseBean newRelPropSelctResults =  iterator.next();							
			/*if(rpListMap.containsKey(newRelPropSelctResults.getDocket_number()))
			{
				rpListMap.get(newRelPropSelctResults.getDocket_number()).add(newRelPropSelctResults);						
			}
			else
			{
				List<RealPropResponseBean> tmpBJLResultList = new ArrayList<RealPropResponseBean>();
				tmpBJLResultList.add(newRelPropSelctResults);
				rpListMap.put(newRelPropSelctResults.getDocket_number(), tmpBJLResultList);
			}*/
		}
		return rpListMap;
	}
	
	
	private void PrintBJLRecords(PrintWriter pw,int i,String title,RealPropResponseBean list,String recordsFrom,String fillingGroup)
	{		
		i=i+1;
		pw.println("Record #"+i);
		pw.println("-------------------------------------------------------------------------");
		pw.println("Owner Information" );
		pw.println("*************************");
		pw.println("Primary Owner:\t\t" + list.getPrimaryowner());
		pw.println("Secondary Owner:\t" + list.getSecondaryowner());
	    if(list.getOwneraptno() !=null || list.getOwnerhouseno()!=null)
	    	pw.println("Address:\t\t" + list.getOwneraptno() +" " + list.getOwnerhouseno()+ " "+list.getOwnerstreet() );
		pw.println("Mode:\t\t\t" + list.getMode());
		pw.println("Sale Price:\t\t" + list.getSaleprice());
		pw.println("Date Recorded:\t\t" + PrintDate(list.getDaterecorded()));
		pw.println("Sale Date:\t\t" + PrintDate(list.getSaledate()));
		pw.println("Calculated Value:" + list.getCalculatedvalue());
		pw.println("\t\t");	
		pw.println("PROPERTY INFORMATION" );
		pw.println("*************************");
		//pw.println("Docket:\t\t\t" + list.getDocket_number());
		if(list.getHouseno() !=null && list.getStreet() !=null)
			pw.println("House Address:\t\t"+ list.getHouseno() + " " + list.getStreet());
		else 
			pw.println("House Address:\t\t"+ list.getCity() + " " + list.getState() +" " + list.getZip());
		pw.println("             :\t\t"+ list.getCity() + " " + list.getState() +" " + list.getZip());
		pw.println("Phone number:\t\t" + list.getPhoneno());
		pw.println("Apn:\t\t\t" + list.getApn());//:
		pw.println("Flips Code:\t\t" + list.getFipscode());
		pw.println("Municipal Code:\t\t" + list.getMunicipalcode());
		if(list.getAccountnumber() !=null)
			pw.println("Account Number:\t\t" + list.getAccountnumber());
		pw.println("\t\t");	
		/*pw.println(":\t\t" + list.);
		pw.println(":\t\t" + list.);
		pw.println(":\t\t" + list.);*/
		
	}
	

	private String PrintDate(String date)
	{
		if (date.isEmpty())
			return "\t\t";
		if(date.length()> 7)
		{
			return date.substring(4, 6)+"/"+ date.substring(6, 8)+"/"+ date.substring(0, 4);
		}
		return "\t\t";
	}
	
	//CriminaL DDN data bind
	@RequestMapping(value="/funnel/ddnResults.do", method = RequestMethod.GET)
	public String submitDDNCriminal(HttpSession session,ModelMap map,
			@RequestParam(value="downloadall", required = false) Boolean downloadAll,
			HttpServletResponse rHttpServletResponse)
	{
		String sResponse="";
		//logger.info("_realProp_Result_DownloadALL on GET----: "+downloadAll);
		if(downloadAll == null)
		{
			logger.info("_DDN_Result_DownloadALL Null----: "+downloadAll);
			downloadAll = false;// || downloadAll == null
		}
		
		List<CriminalResponseBean> eList = new ArrayList<CriminalResponseBean>();		
		if(session.getAttribute("DDNResults")!= null)
		{
			eList = (List<CriminalResponseBean>) session.getAttribute("DDNResults");
			logger.info("DDN  criminal List Count----: "+ eList.size());
			sResponse = session.getAttribute("DDNResults").toString();
		}
		if(eList != null && eList.size() > 0)
		{
			map.addAttribute("eList", eList);
			map.addAttribute("eListsize", eList.size());	
			//logger.info("DDN  criminal List ResltDetaisl----: "+ eList.indexOf(1));
		}		
		Integer UserSearchId=(Integer) session.getAttribute("userSearchId");
		
		map.addAttribute("ddnResponseXML", sResponse);
				
		return DDNResultDetails;
		
		
	}
	
	
	
	
	
}