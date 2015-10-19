package springapp.web.funnel;


import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;

import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;


import springapp.domain.NSS.NSSOffenderBean;
import springapp.domain.NSS.NSSRequestBean;
import springapp.domain.NSS.NssOffenseBean;
import springapp.domain.NSS.NssOffenseSupplementBean;
import springapp.domain.NSS.NssResponseBean;

import springapp.manager.SearchException;

import springapp.manager.SpringNSSSearchManager;
import springapp.repository.NssResponseDao;


@SessionAttributes("command")
@Controller
public class NssResultsDisplay extends AbstractFunnelController {
	protected static final Integer pageSizes[] = {10, 25, 50};
	
	@Autowired
	private SpringNSSSearchManager nssManager;
	@Autowired
	private NssResponseDao respDao;
	
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
	
	protected static final String fieldOffenders[]=
		{"fullName","dateOfBirth","recordOffenderId",
		"street","city",  "state", "postalCode",
		"gender","race",  "county", "country", 
		"heightFeet", "heightInches", "weight",  "eyeColor", "hairColor",
		"provider", "recordState"
		
	};
	public static class ResultsCommand {
		private String[] resultsToPrint;

		public String[] getResultsToPrint() {
			return resultsToPrint;
		}
		public void setResultsToPrint(String[] resultsToPrint) {
			this.resultsToPrint = resultsToPrint;
		}		
	}
	
	
	@RequestMapping(value = "/funnel/NssResults.do", method = RequestMethod.GET)
	public String getResults(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="pageSize",required=false) Integer pageSize,
			@RequestParam(value="download", required= false) Boolean download,
			HttpServletResponse rHttpServletResponse
			)  {		
		
		
		if (test == null) { test = false; }
		if (pageSize == null) { pageSize = 10; }
		if(download == null){download= false;}
		
		if (!verifySession(session, test)) {
			return landingHome;
		}
		Integer responseId = (Integer) session.getAttribute("responseId");
		if (responseId == null) {
			return newvwError;
		} else {
			map.addAttribute("responseId", responseId);
		}
		
		try {
			NSSRequestBean request;
			NssResponseBean response;
			try {
				response = nssManager.getResponse(responseId);
				request = nssManager.getRequest(response.getNssRequestId());
			
			} catch (Exception e) {
				throw new SearchException(e);
			}
			logger.info("responseId: " + response.getNssResponseId());
			
			if (response.getQuantityReturned() == 0) {				
				map.addAttribute("firstName", request.getFirstName());
				map.addAttribute("lastName", request.getLastName());
				
				
				if(request.getDobMonth() != 0 && request.getDobYear() != 0)
				{
				
				String searchDOB = request.getDobMonth() + "/" + request.getDobDay() + "/" + request.getDobYear();
				map.addAttribute("DOB", searchDOB);
				}
				//session.removeAttribute("bgcRequestId");
				session.removeAttribute("responseId");
				
				
				return nsszeroResultsView;
			}
			
			// set result info
			NSSOffenderBean[] beans;
			try {
				beans = nssManager.getOffenders(response.getNssResponseId());
				
			} catch (Exception e) {
				throw new SearchException(e);
			}

			ArrayList<NSSOffenderBean> offenders = new ArrayList<NSSOffenderBean>();
			for (NSSOffenderBean b : beans) {
				
					offenders.add(b);
				
			}
			
			
			if(download == true)
			{
				rHttpServletResponse.setContentType("text/plain");
				rHttpServletResponse.setHeader("Content-Disposition", "attachment;filename="+request.getLastName()+"+"+request.getFirstName()+".txt");
				
				List<NssOffenseBean> offList= new ArrayList<NssOffenseBean>() ;
				List<NssOffenseSupplementBean> supList=new ArrayList<NssOffenseSupplementBean>();
				try{
					PrintWriter pw=rHttpServletResponse.getWriter();
					int i=0;
					pw.println("National Security Search - Result Details");
					pw.println("*****************************************");
					for (NSSOffenderBean list : offenders) {
						int id=list.getNssOffenderId();
						offList=respDao.getOffenseList(id);
						supList=respDao.getSupplementList(id);
						if(offList.size() > 0)
						{
							i=i+1;
							pw.println("Record #"+i);
							pw.println();
							pw.println("Offender Info");
							pw.println("*************");
							generateOffendersTextFormat(pw,list);
						}
						if(offList.size() > 0)
						{
							pw.println("Offenses");
							pw.println("********");
							genrateOffenseTextFormat(pw,offList);
						}
						if(supList.size() > 0)
						{
							pw.println("Offense Supplements");
							pw.println("*******************");
							generateSupplemtsTextFormat(pw, supList);
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
					logger.error("Error: While downloading the text format:"+e);
				}
				return null;
			}
//			ArrayList<BGCOffenderBean> offenders2 = new ArrayList<BGCOffenderBean>(offenders);
//			for (int i=0; i<30; i++) {
//				offenders.addAll(offenders2);
//			}
			
			// finalize charge?
			// XXX todo
			
			// send to results page (or no results)
			if(offenders.size() == 0)
			{
				map.addAttribute("firstName", request.getFirstName());
				map.addAttribute("lastName", request.getLastName());
				
				
				if(request.getDobMonth() != 0 && request.getDobYear() != 0)
				{
					String searchDOB = request.getDobMonth() + "/" + request.getDobDay() + "/" + request.getDobYear();
					map.addAttribute("DOB", searchDOB);
				}
				
				//session.removeAttribute("bgcRequestId");
				session.removeAttribute("responseId");
				
				
				return nsszeroResultsView;
			}
			map.addAttribute("command", new ResultsCommand());
			map.addAttribute("pageSizes", pageSizes);
			map.addAttribute("pageSize", pageSize);
			map.addAttribute("offenders", offenders);
			map.addAttribute("offendersCount", offenders.size());
			
			
			
			return nssresultsView;
			
		} catch (SearchException e) {			
			// void charge?
			// XXX todo
			logger.error("Error getting results", e);

			// send to error page
			return newvwError;			
		} 
	}
	
				
	

	private void generateSupplemtsTextFormat(PrintWriter pw,
			List<NssOffenseSupplementBean> supList) {
		// TODO Auto-generated method stub
		int j=1;
		
		for (NssOffenseSupplementBean nsb : supList) {
			pw.println("");
			pw.print(j+" "+nsb.getDisplayTitle()+"\t\t"+nsb.getDisplayValue());
			pw.println("");
			j++;
		}
		pw.println("");
	}



	private void generateOffendersTextFormat(PrintWriter pw,
			NSSOffenderBean list) {
		// TODO Auto-generated method stub
		String address, city, state, postal, gender, race, county, country, height, weight, eye, hair, provider, rstate;
		//address
		if(list.getStreet().isEmpty())
		{
			address="---";
		}else {
			address=list.getStreet();
		}
		
		//city
		if(list.getCity().isEmpty())
		{
			city="---";
		}else {
			city=list.getCity();
		}
			
		//county
		if(list.getCounty().isEmpty())
		{
			county="---";
		}else {
			county=list.getCounty();
		}
		//country
		if(list.getCountry().isEmpty())
		{
			country="---";
		}else {
			country=list.getCountry();
		}
		//gender
		if(list.getGender().isEmpty())
				{
					gender="---";
		}else {
					gender=list.getGender();
			}
		//Race
		if(list.getRace().isEmpty())
		{
			race="---";
		}else {
				race=list.getRace();
			}
		//state
		if(list.getState().isEmpty())
			{
				state="---";
		}else {
				state=list.getState();
				}
		//postal
		if(list.getPostalCode().isEmpty())
			{
				postal="---";
		}else {
			postal=list.getPostalCode();
				}
		//height
		if(list.getHeightFeet().isEmpty())
			{
				height="---";
		}else {
			height=list.getHeightFeet();
				}
		//weight
		if(list.getWeight().isEmpty())
			{
				weight="---";
		}else {
				weight=list.getWeight();
				}
		//eye color
		if(list.getEyeColor().isEmpty())
			{
				eye="---";
		}else {
				eye=list.getEyeColor();
				}
		//hair color
		if(list.getHairColor().isEmpty())
			{
				hair="---";
		}else {
				hair=list.getHairColor();
				}
		//Provider
		if(list.getProvider().isEmpty())
			{
				provider="---";
		}else {
			provider=list.getProvider();
				}
		//Record State
		if(list.getRecordState().isEmpty())
			{
				rstate="---";
		}else {
			rstate=list.getRecordState();
				}
		
		pw.println("FullName\t\t\t\tDOB\t\tID");
		pw.println(list.getFullName()+"\t\t\t"+list.getDateOfBirth()+"\t"+list.getRecordOffenderId());
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

	private void genrateOffenseTextFormat(PrintWriter pw,
			List<NssOffenseBean> offList) {
		
	for (NssOffenseBean nobList : offList) {
		
		
		if(!nobList.getDescription().isEmpty())
		{
			pw.println("Description");
			pw.println(nobList.getDescription());
		}
		if(!nobList.getStatute().equals(""))
		{
			pw.println("Statute");
			pw.println(nobList.getStatute());

		}
		if(!nobList.getDegreeOfOffense().equals(""))
		{
			pw.println("Degree Of Offense");
			pw.println(nobList.getDegreeOfOffense());

		}
		if(!nobList.getOffenseDate().equals(""))
		{
			pw.println("Offense Date");
			pw.println(nobList.getOffenseDate());

		}
		if(!nobList.getArrestDate().equals(""))
		{
			pw.println("Arrest Date");
			pw.println(nobList.getArrestDate());

		}
		if(!nobList.getArrestingAgency().equals(""))
		{
			pw.println("Arresting Agency");
			pw.println(nobList.getArrestingAgency());

		}
		if(!nobList.getDisposition().equals(""))
		{
		pw.println("Disposition");pw.println(nobList.getDisposition());

		}
		if(!nobList.getDispositionDate().equals(""))
		{
		pw.println("Disposition Date");pw.println(nobList.getDispositionDate());

		}
		if(!nobList.getSentence().equals(""))
		{
		pw.println("Sentence");pw.println(nobList.getSentence());

		}
		if(!nobList.getSentenceDate().equals(""))
		{
		pw.println("Sentence Date");pw.println(nobList.getSentenceDate());

		}
		if(!nobList.getConfinement().equals(""))
		{
		pw.println("Confinement");pw.println(nobList.getConfinement());

		}
		if(!nobList.getProbation().equals(""))
		{
		pw.println("Probation");pw.println(nobList.getProbation());

		}
		if(!nobList.getFine().equals(""))
		{
		pw.println("Fine");
		pw.println(nobList.getFine());

		}
		if(!nobList.getPlea().equals(""))
		{
		pw.println("Plea");
		pw.println(nobList.getPlea());

		}
		
	}
	pw.println("");
	}

	@RequestMapping("/funnel/NssResultDetails.do")
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
		
		try {
			// fetch the result first
			nssManager.fetchOffenderDetails(offenderId);
			
			NSSOffenderBean bean = nssManager.getOffender(offenderId, hashKey);
			map.addAttribute("o", bean);
			
			
			
			NssOffenseBean[] offenses = nssManager.getOffenses(offenderId);
			map.addAttribute("offenses", offenses);
			logger.info("offenses: " + offenses.length);
			for (NssOffenseBean nssOffenseBean : offenses) {
				NssOffenseSupplementBean[] supplements = nssManager.getOffenseSupplements(nssOffenseBean.getNssOffenseId());
				map.addAttribute("supplements", supplements);
			}
			
			return nssresultDetailsView;
		} catch (Exception e) {
			return newvwError;
		}
	}
		// post handles checkboxes for print selected
	@RequestMapping(value = "/funnel/NssPrintResults.do", method = RequestMethod.POST)
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
		ArrayList<NSSOffenderBean> offenders = new ArrayList<NSSOffenderBean>();
		
		// list to fetch, we will create this at the same time
		ArrayList<Integer> offToFetch = new ArrayList<Integer>();
		
		try {
			// best way to do this is probably to get all responses so there's
			// only 1 db call. then if we need to do only checked, we can easily 
			// fetch those out.
			NSSOffenderBean[] offendersAll = nssManager.getOffenders(responseId);

			if (params.get("printChecked") == null) {
				// print all
				logger.info("print all for: " + responseId);
				for (NSSOffenderBean o : offendersAll) {
					offenders.add(o);
					offToFetch.add(o.getNssOffenderId());
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
				
				for (NSSOffenderBean o : offendersAll) {
					if (checked.contains(o.getNssOffenderId())) {
						offenders.add(o);
						offToFetch.add(o.getNssOffenderId());
					}
				}
			}
			
			// make sure all the offenders are fetched	
			nssManager.fetchOffenderDetails(offToFetch);

			// ok, we have offenders, now we need to get the other info
		
			
			
			HashMap<Integer,NssOffenseBean[]> offensesMap = new HashMap<Integer,NssOffenseBean[]>();
			for (NSSOffenderBean o : offenders) {
				Integer id = o.getNssOffenderId();
				offensesMap.put(id, nssManager.getOffenses(id));
				
				
			}
			
			HashMap<Integer, NssOffenseSupplementBean[]> supplementMap = new HashMap<Integer, NssOffenseSupplementBean[]>();
			for (NSSOffenderBean o: offenders) {
				
				Integer id=o.getNssOffenderId();
				
				supplementMap.put(id, nssManager.getOffenseSupplements(id));
			}
			
			// add everything to the map
			map.addAttribute("offenders", offenders);
			
			map.addAttribute("offensesMap", offensesMap);
			map.addAttribute("supplementMap",supplementMap);
			
			
			return nssprintResultsView;
			
		} catch (Exception e) {
			return newvwError;
		}
		
	}
		
	
	
}