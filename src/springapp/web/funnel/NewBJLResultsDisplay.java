package springapp.web.funnel;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import java.util.Iterator;
import java.util.List;


import javax.jws.WebResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.apache.commons.lang.ArrayUtils;
//import org.apache.jasper.tagplugins.jstl.core.ForEach; 
import org.apache.velocity.runtime.directive.Foreach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sun.xml.internal.ws.wsdl.writer.UsingAddressing;

import springapp.domain.NewBJLResults;

import springapp.domain.NewBJLSearches;

import springapp.manager.SearchException;

import springapp.manager.SpringBJLSearchManager;

@SessionAttributes("command")
@Controller
public class NewBJLResultsDisplay extends AbstractFunnelController {
	protected static final Integer pageSizes[] = {10, 25, 50};
	    
	
	@Autowired
	private SpringBJLSearchManager bjlManager;
	
	public static class ResultsCommand {
		private String[] resultsToPrint;

		public String[] getResultsToPrint() {
			return resultsToPrint;
		}
		public void setResultsToPrint(String[] resultsToPrint) {
			this.resultsToPrint = resultsToPrint;
		}		
	}
	
	@RequestMapping(value = "/funnel/newResultsBJL.do", method = RequestMethod.GET)
	public String getResultsBJL(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="pageSize",required=false) Integer pageSize,
			@RequestParam(value="download", required = false) Boolean download,
			@RequestParam(value="downloadall", required = false) Boolean downloadAll,
			HttpServletResponse rHttpServletResponse)  
	{		
		if (test == null) { test = false; }
		if (pageSize == null) { pageSize = 50; }
		if(download == null)
		{
			download = false;
		}
		if(downloadAll == null)
		{
			downloadAll = false;// || downloadAll == null
		}
		Integer userSearchId = (Integer) session.getAttribute("userSearchId");
		if (userSearchId == null) {
			return vwError;
		} 
		
		map.addAttribute("userSearchId", userSearchId);

		// get results
		try {
			
			NewBJLSearches search= bjlManager.getSearch(userSearchId);
            
			if (search.getMatchCount() == 0) 
			{
				map.addAttribute("bjlSearch", true);
				map.addAttribute("bjlSearchName", search.getFirstName()+" "+search.getLastName());
				map.addAttribute("bjlBusinessName", search.getBusinessName());
				map.addAttribute("bjlSearchSsnTaxId", search.getSSN());
				map.addAttribute("bjlSearchState", search.getState());
				
				session.removeAttribute("userSearchId");
				session.removeAttribute("bjlSearchFormCommand");
				return newNoBJLResult;
			}
			List<String> typeOfItem=new ArrayList<String>();
			typeOfItem.add("BANKRUPTCY");
			typeOfItem.add("JUDGEMENT");
			typeOfItem.add("TAX LIEN");
			typeOfItem.add("CIVIL JUDGMENT");
			List<NewBJLResults> results=new ArrayList<NewBJLResults>();
			String searchName= search.getFirstName()+" "+search.getLastName();		
			
			
			if(search.getFirstName() != null && search.getLastName() != null && !search.getLastName().trim().equals(""))
			{
				searchName=search.getFirstName()+" "+search.getLastName();
				
				results = bjlManager.getAllResults(userSearchId,typeOfItem,search.getLastName().toUpperCase(),search.getFirstName().toUpperCase());
				//logger.info("results size:"+results.size());				
				
			}
			if (search.getBusinessName() != null && !search.getBusinessName().trim().equals("")) 
			{
				searchName=search.getBusinessName();
				results = bjlManager.getAllResults(userSearchId,typeOfItem,search.getBusinessName().toUpperCase());
				//logger.info("Business Check:"+ results);
			}
			
			
			if(search.getSSN() != null && search.getSSN().length() > 0)
			{
				searchName=search.getSSN();
				results = bjlManager.getAllSSNResults(userSearchId,typeOfItem,search.getSSN());
				//logger.info("results size ssn:"+results.size());
			}
			
	    //results = bjlManager.getAllResults(userSearchId,typeOfItem,search.getLastName().toUpperCase(),search.getFirstName().toUpperCase());
		//logger.info("results size:"+results.size());
		 Map<String,NewBJLResults> objMap = new Hashtable<String,NewBJLResults>();
		String tempArr = "";
		for (Iterator iterator = results.iterator(); iterator.hasNext();) {
			NewBJLResults newBJLResults = (NewBJLResults) iterator.next();
			//if(newBJLResults.getName_type()== "D")
			{
				if(objMap.containsKey(newBJLResults.getDocket_number()))
				{
					NewBJLResults tmpResults = objMap.get(newBJLResults.getDocket_number());
					//tempArr = tempArr+newBJLResults.getDocket_number()+"@";
					String tmpName = newBJLResults.getFirstname()+" "+newBJLResults.getLastname();
					if(tmpName != tmpResults.getFirstname())
					{
						tmpResults.setFirstname(tmpResults.getFirstname()+","+tmpName);
						
					}				
				}
				else
				{					
						newBJLResults.setFirstname(newBJLResults.getFirstname()+" "+newBJLResults.getLastname());
						objMap.put(newBJLResults.getDocket_number(), newBJLResults);}
				}				
		}
		
		Iterator tmpobj = objMap.entrySet().iterator();
		results.clear();
		while (tmpobj.hasNext()) 
		{
			Map.Entry  obj = (Map.Entry) tmpobj.next();			
			NewBJLResults objNewBJLResults = (NewBJLResults) obj.getValue();
			results.add(objNewBJLResults);
			tempArr = tempArr+objNewBJLResults.getDocket_number()+"@";
		}
		session.setAttribute("downloadAllRecds", tempArr);
		//Sorting Summery page results
		Comparator<NewBJLResults> sortType=new FilingGroupSort();		
		Collections.sort(results, sortType);
		sortType=new FilingGroupSort();
			
		   //logger.trace("Trace res Size: "+results.size());
			/*List<NewBJLResults> resList=new ArrayList<NewBJLResults>();
			NewBJLResults summaryResult=new NewBJLResults();
			
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				summaryResult = (NewBJLResults) iterator.next();
				if((!summaryResult.getCity().isEmpty()) && (!summaryResult.getState().isEmpty()))
				{
					resList.add(summaryResult);
					
				}
			}
			*/
			//logger.info("size:"+resList.size());
						
	    //String[] docket_no =tempArr.split("@"); 
	    String[] secltdResultID = (String[]) session.getAttribute("ckckedResultID") ;
	   if(download==true || downloadAll ==true)
	   {
		   if(downloadAll ==true )
		   {
			   String sTmpIds= (String) session.getAttribute("downloadAllRecds");
			   secltdResultID = sTmpIds.split("@") ;			  
		   }
		   DownloadRecords(secltdResultID,userSearchId,rHttpServletResponse,searchName);
	   }
		
			if (results.size() == 0) {
				map.addAttribute("bjlSearch", true);
				map.addAttribute("bjlSearchName", search.getFirstName()+" "+search.getLastName());
				map.addAttribute("bjlBusinessName", search.getBusinessName());
				map.addAttribute("bjlSearchSsnTaxId", search.getSSN());
				map.addAttribute("bjlSearchState", search.getState());
				session.removeAttribute("userSearchId");
				session.removeAttribute("bjlSearchFormCommand");
				return newNoBJLResult;
			}
			map.addAttribute("command", new ResultsCommand());
			map.addAttribute("pageSizes", pageSizes);
			map.addAttribute("pageSize", pageSize);
			map.addAttribute("searchName", searchName);
			map.addAttribute("results", results);
			map.addAttribute("resultsCount", results.size());
			map.addAttribute("UsID", userSearchId);
			
			session.setAttribute("userId", userSearchId);
			//logger.info("userSearchId in get Method:"+ userSearchId);		
			return newvwBJLResults;
			
		} catch (SearchException e) {
			// void charge?
			// XXX todo 
			logger.error("Error getting results", e); 

			// send to error page
			return newvwError;
		}	
	}
	
	private void DownloadRecords(String[] secltdResultID, Integer userSearchId,
			HttpServletResponse rHttpServletResponse,String searchName) {
		// TODO Auto-generated method stub
		 
	    //logger.info("AllResultz:"+ session.getAttribute("ckckedResultID"));
	    List<NewBJLResults> selectdResults=null;
		List<NewBJLResults> bankrupticList=null;
		List<NewBJLResults> jList=null;
		List<NewBJLResults> taxList=null;
		String bName="BANKRUPTCY";
		String taxName="TAX LIEN";
		List<String> judgeName=new ArrayList<String>();
		judgeName.add("JUDGEMENT");
		judgeName.add("CIVIL JUDGMENT");
		
		//Passing Selected records resultID from session array
		//if(download== true)
			//secltdResultID = (String[]) session.getAttribute("ckckedResultID") ;
		 
		if(!bName.isEmpty())
			selectdResults = bankrupticList = bjlManager.getBankruptyResult(secltdResultID,userSearchId, bName);
		if(judgeName.size()>0)
			selectdResults = jList = bjlManager.getJudgementResults(secltdResultID,userSearchId,judgeName);
		if(!taxName.isEmpty())
			selectdResults = taxList = bjlManager.getTaxliensResults(secltdResultID,userSearchId, taxName);		
		
		//logger.info("Detlszz UsrSrchID :"+userSearchId);
		
		
	// || downloadAll== true
		//if(download == true)
		{
			rHttpServletResponse.setContentType("text/plain");
			rHttpServletResponse.setHeader("Content-Disposition", "attachment;filename=BJL_"+ searchName.replace(' ', '_')+".txt");
			
			try
			{
				PrintWriter pw=rHttpServletResponse.getWriter();
				int i=0;
				pw.println("Bankruptcy, Judgment & Tax Lien  - Result Details");
				pw.println("*************************************************");
				
				Map<String,List<NewBJLResults>> bankruptcyMap = GetBJLResultsMap(bankrupticList);
				Map<String,List<NewBJLResults>> judgementMap = GetBJLResultsMap(jList);
				Map<String,List<NewBJLResults>> taxlineMap = GetBJLResultsMap(taxList);				
				
				
				
				for(String key : bankruptcyMap.keySet())
				{	
					String sRecrdsFrm ="";
					List<NewBJLResults> tmpBnkrptcyList = bankruptcyMap.get(key);
					NewBJLResults tmpBnkrptcy = tmpBnkrptcyList.get(0);
					
					if(!tmpBnkrptcy.getCourt_desc().isEmpty())
					{
						//sRecrdsFrm
						if(!tmpBnkrptcy.getCourt_city().isEmpty())
							sRecrdsFrm = tmpBnkrptcy.getCourt_desc() +", "+tmpBnkrptcy.getCourt_city();
						if(!tmpBnkrptcy.getCourt_state().isEmpty())
						{
							sRecrdsFrm = tmpBnkrptcy.getCourt_desc() +", "+tmpBnkrptcy.getCourt_city()+", "+tmpBnkrptcy.getCourt_state();
							if(!tmpBnkrptcy.getCourt_zip().isEmpty())
								sRecrdsFrm = tmpBnkrptcy.getCourt_desc() +", "+tmpBnkrptcy.getCourt_city()+", "+tmpBnkrptcy.getCourt_state()+", "+tmpBnkrptcy.getCourt_zip();
						}
						else
							sRecrdsFrm = tmpBnkrptcy.getCourt_desc()+", "+ (tmpBnkrptcy.getFiling_state().isEmpty() ?tmpBnkrptcy.getState() : tmpBnkrptcy.getFiling_state() );
					}
					else
						sRecrdsFrm = tmpBnkrptcy.getState();
					
					sRecrdsFrm = !tmpBnkrptcy.getCourt_desc().isEmpty() ? tmpBnkrptcy.getCourt_desc() +", "+tmpBnkrptcy.getState() : tmpBnkrptcy.getState() ;
					PrintBJLRecords(pw, i++, "Bankruptcy Info" ,tmpBnkrptcy,sRecrdsFrm,tmpBnkrptcy.getBankruptcy_type_desc());
					pw.println("Trustee:\t\t" + tmpBnkrptcy.getTrustee());
					String Assets = tmpBnkrptcy.getAssets_available();
					if(!Assets.isEmpty())
					{
						Assets= Assets.equals("Y") ? "Yes" : "No";						
					}
					pw.println("Assets Avail:\t\t" + Assets);
					PrintBJLRecordsGroup(pw, tmpBnkrptcyList);
					pw.println("");
				}
				
				for(String key : judgementMap.keySet())
				{
					List<NewBJLResults> tmpJudgementList = judgementMap.get(key);
					NewBJLResults tmpJudgement = tmpJudgementList.get(0);
					String sRecrdsFrom =tmpJudgement.getCourt_desc(); 
					
					if(!tmpJudgement.getCourt_desc().isEmpty())
					{
						//sRecrdsFrom
						if(!tmpJudgement.getCourt_city().isEmpty())
							sRecrdsFrom = tmpJudgement.getCourt_desc() +", "+tmpJudgement.getCourt_city();
						if(!tmpJudgement.getCourt_state().isEmpty())
						{
							sRecrdsFrom = tmpJudgement.getCourt_desc() +", "+tmpJudgement.getCourt_city()+", "+tmpJudgement.getCourt_state();
							if(!tmpJudgement.getCourt_zip().isEmpty())
								sRecrdsFrom = tmpJudgement.getCourt_desc() +", "+tmpJudgement.getCourt_city()+", "+tmpJudgement.getCourt_state()+", "+tmpJudgement.getCourt_zip();
						}
						else
							sRecrdsFrom = tmpJudgement.getCourt_desc()+", "+ (tmpJudgement.getFiling_state().isEmpty() ?tmpJudgement.getState() : tmpJudgement.getFiling_state() );
					}
					else
						sRecrdsFrom = tmpJudgement.getState();
					
					/*if(!tmpJudgement.getCourt_desc().isEmpty())
						sRecrdsFrom = tmpJudgement.getCourt_desc()+", "+ (tmpJudgement.getFiling_state().isEmpty() ?tmpJudgement.getState() : tmpJudgement.getFiling_state() );
					else
						sRecrdsFrom = (tmpJudgement.getFiling_state().isEmpty() ? tmpJudgement.getState() : tmpJudgement.getFiling_state() );
					*/
					PrintBJLRecords(pw, i++, "Judgment Info" ,tmpJudgement,sRecrdsFrom,tmpJudgement.getFiling_group_desc());
					pw.println("Liability Amt:\t\t" + "$" + (!tmpJudgement.getLiability_amount().isEmpty() ? tmpJudgement.getLiability_amount().replaceAll("(?<!\\d)0+(?=\\d+)", "") : "0") );
					PrintBJLRecordsGroup(pw, tmpJudgementList);
					pw.println("");
				}
				
				for(String key : taxlineMap.keySet())
				{
					List<NewBJLResults> tmptxlnList = taxlineMap.get(key);
					NewBJLResults tmpTxLn = tmptxlnList.get(0);
					String sRecrdsFrom =tmpTxLn.getCourt_desc(); 
					
					if(!tmpTxLn.getCourt_desc().isEmpty())
					{
						//sRecrdsFrom
						if(!tmpTxLn.getCourt_city().isEmpty())
							sRecrdsFrom = tmpTxLn.getCourt_desc() +", "+tmpTxLn.getCourt_city();
						if(!tmpTxLn.getCourt_state().isEmpty())
						{
							sRecrdsFrom = tmpTxLn.getCourt_desc() +", "+tmpTxLn.getCourt_city()+", "+tmpTxLn.getCourt_state();
							if(!tmpTxLn.getCourt_zip().isEmpty())
								sRecrdsFrom = tmpTxLn.getCourt_desc() +", "+tmpTxLn.getCourt_city()+", "+tmpTxLn.getCourt_state()+", "+tmpTxLn.getCourt_zip();
						}
						else
							sRecrdsFrom = tmpTxLn.getCourt_desc()+", "+ (tmpTxLn.getFiling_state().isEmpty() ?tmpTxLn.getState() : tmpTxLn.getFiling_state() );
					}
					else
						sRecrdsFrom = tmpTxLn.getState();
					
					
					/*if(!tmpTxLn.getCourt_desc().isEmpty())
						sRecrdsFrom = tmpTxLn.getCourt_desc()+", "+ (tmpTxLn.getFiling_state().isEmpty() ?tmpTxLn.getState() : tmpTxLn.getFiling_state() );
					else
						sRecrdsFrom = (tmpTxLn.getFiling_state().isEmpty() ? tmpTxLn.getState() : tmpTxLn.getFiling_state() );
					*/
					PrintBJLRecords(pw, i++, "Tax Lien Info" ,tmpTxLn,sRecrdsFrom,tmpTxLn.getFiling_group_desc());
					pw.println("Liability Amt:\t\t" + "$" + (! tmpTxLn.getLiability_amount().isEmpty() ? tmpTxLn.getLiability_amount().replaceAll("(?<!\\d)0+(?=\\d+)", "") : "0"));
					PrintBJLRecordsGroup(pw, tmptxlnList);
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
			//return null;  
			
		}
		
		
	}

	private Map<String,List<NewBJLResults>> GetBJLResultsMap(List<NewBJLResults> bjlList)
	{
		Map<String,List<NewBJLResults>> bjlListMap = new Hashtable<String,List<NewBJLResults>>();					 
		for (Iterator<NewBJLResults> iterator = bjlList.iterator(); iterator.hasNext();) 
		{
			NewBJLResults newBJLSelctResults =  iterator.next();							
			if(bjlListMap.containsKey(newBJLSelctResults.getDocket_number()))
			{
				bjlListMap.get(newBJLSelctResults.getDocket_number()).add(newBJLSelctResults);						
			}
			else
			{
				List<NewBJLResults> tmpBJLResultList = new ArrayList<NewBJLResults>();
				tmpBJLResultList.add(newBJLSelctResults);
				bjlListMap.put(newBJLSelctResults.getDocket_number(), tmpBJLResultList);
			}
		}
		return bjlListMap;
	}
	
		
	private void PrintBJLRecords(PrintWriter pw,int i,String title,NewBJLResults list,String recordsFrom,String fillingGroup)
	{		
		i=i+1;
		pw.println("Record #"+i);
		pw.println("-------------------------------------------------------------------------");
		pw.println(title);
		pw.println("***************");
		pw.println("Docket:\t\t\t" + list.getDocket_number());
		pw.println("Records from:\t\t"+ recordsFrom);
		pw.println("Filing type:\t\t" + fillingGroup);//Bankruptcy Info
		if(!list.getFiling_date().isEmpty())
			pw.println("Filing date:\t\t" + PrintDate(list.getFiling_date())) ;
		if(!title.equals("Judgment Info"))
		{
			pw.println("Initial date:\t\t"+ PrintDate(list.getInitial_date()));
			if(!title.equals("Tax Lien Info"))
			{
				if(!list.getClosed_date().isEmpty())
					pw.println("Closed date:\t\t" + PrintDate(list.getClosed_date()));
				if(!list.getUpdate_date().isEmpty())
					pw.println("Update date:\t\t" + PrintDate(list.getUpdate_date()));
				if(!list.getDischarge_date().isEmpty())
					pw.println("Discharge date:\t\t" + PrintDate(list.getDischarge_date()));
			}
		}
	}
	
	private void PrintBJLRecordsGroup(PrintWriter pw,List<NewBJLResults> bjlList)
	{	
		for (NewBJLResults list : bjlList)
		{			
			if(!list.getName_type_desc().equals("Plaintiff")) //Debtor
			{
				if(list.getName_type_desc().equals("Debtor"))
					pw.println(list.getName_type_desc()+":\t\t\t" + list.getFullname());//list.getLastname()+" "+  list.getFirstname()
				else if(list.getName_type_desc().equals("Debtor Attorney"))
					pw.println(list.getName_type_desc()+":\t" +  list.getFullname());//list.getLastname()+" "+  list.getFirstname()
				else
					pw.println(list.getName_type_desc()+":\t\t" + list.getFullname());//list.getLastname()+" "+  list.getFirstname()
				
				if(!list.getBusinessname().isEmpty())
					pw.println("BUSINESS:\t\t" +list.getBusinessname());
				if(!list.getSsn().isEmpty())
					pw.println("SSN/EIN:\t\t" +list.getSsn());
				if(!list.getStreet_name().isEmpty() || !list.getCity().isEmpty() || !list.getState().isEmpty() || !list.getZipcode().isEmpty())
				{
					pw.println("Address:\t\t" +list.getHouse_number()+" "+list.getStreet_direction() +" "+list.getStreet_name()+" "+list.getStreet_suffix()+" "+list.getApartment_num());
					pw.println("\t\t\t" + (list.getCity().isEmpty()? "": list.getCity()+",")  +list.getState()+" "+list.getZipcode());
				}
			}
			else
				pw.println(list.getName_type_desc()+":\t\t" + list.getFullname() );//list.getFirstname()+" "+  list.getMiddlename()+" "+  list.getLastname()
			
		}		
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
    	
	@RequestMapping(value ="/funnel/newResultDetailsBJL.do", method = RequestMethod.GET)
	public String getResultDetailsBJL(
			HttpSession session,
			ModelMap map,
			@RequestParam("resultId") String resultId,
			@RequestParam("userid") Integer userSearchId, 
			@RequestParam(value="test",required=false) Boolean test
			) {
		
		if (test == null) { test = false; }

		// XXX fix this
//		if (!verifySession(session, test)) {
//			return landingHome;
//		}
		Integer userId=(Integer) session.getAttribute("userId");
		if(userId != null)
		{
			//System.out.println("Post User Search Id: "+userId);
		}
		try {
			// fetch the result first
			//BjlResults result = bjlManager.getResult(resultId);
			//NewBJLResults result = bjlManager.getResult(resultId);
			//map.addAttribute("result", result);			
			return newvwBJLResultDetails;
		} catch (Exception e) {
			return newvwError;
		}
	}
	
	@RequestMapping(value ="/funnel/newResultDetailsBJL.do", method = RequestMethod.POST)
	public String getAllResultDetailsBJL(
			HttpSession session,
			ModelMap map,
			HttpServletRequest req,
			@RequestParam("resultIds") String[] resultIds,
			@RequestParam("userid") Integer userSearchId,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="viewall",required=false) Boolean viewAll
			) {
		
		if (test == null ) 
		{ 
			test = false; 
			//downloadAll =false;//|| downloadAll == null
		}
		if (viewAll == null ) 
		{ 
			viewAll = false;
		}
		
		// XXX fix this
//		if (!verifySession(session, test)) {
//			return purchaseRedir;
//		}	
		if(viewAll ==true )
		 {
		   String sTmpIds= (String) session.getAttribute("downloadAllRecds");
		   resultIds = sTmpIds.split("@") ;	
		   logger.info("USP_DownlodALLRecds:"+resultIds);
		 }
		session.setAttribute("ckckedResultID", resultIds);
		
		Integer userId=(Integer) session.getAttribute("userId");
		//logger.info("sessin id: "+userId);
		String bName="BANKRUPTCY";
		String taxName="TAX LIEN";
		List<String> judgeName=new ArrayList<String>();
		judgeName.add("JUDGEMENT");
		judgeName.add("CIVIL JUDGMENT");
		
		try {
			// fetch the result first
			List<NewBJLResults> bankrupticList=null;
			List<NewBJLResults> jList=null;
			List<NewBJLResults> taxList=null;
			//resultIds=0
			  
			//int[] arr= {1,0};//ArrayUtils.toPrimitive(null, 0);
			bankrupticList=bjlManager.getBankruptyResult(resultIds,userId, bName);
			jList=bjlManager.getJudgementResults(resultIds,userId,judgeName);
			taxList=bjlManager.getTaxliensResults(resultIds,userId, taxName);
			
			//Comparator<NewBJLResults> sort = new resultSort();
			Comparator<NewBJLResults> sortType=new FilingResultSort();
			//Collections.sort(bankrupticList, sort);
			Collections.sort(bankrupticList, sortType);
			//sort = new resultSort();
			sortType=new FilingResultSort();
			//Collections.sort(jList, sort);
			Collections.sort(jList, sortType);
			//sort = new resultSort();
			sortType=new FilingResultSort();
			//Collections.sort(taxList, sort);
			Collections.sort(taxList, sortType);
			
			if(bankrupticList.size() > 0)
			{
			map.addAttribute("bankCourt", bankrupticList.get(0).getCourt_desc());
			map.addAttribute("bankState", bankrupticList.get(0).getState());
			}
			if(jList.size() > 0 )
			{
			map.addAttribute("judgeCourt", jList.get(0).getCourt_desc());
			map.addAttribute("judgeState", jList.get(0).getState());
			}
			if(taxList.size() > 0)
			{
			map.addAttribute("taxCourt", taxList.get(0).getCourt_desc());
			map.addAttribute("taxState", taxList.get(0).getState());
			}
			
		   	map.addAttribute("bankList", bankrupticList);
			map.addAttribute("bankSize", bankrupticList.size());
			map.addAttribute("JudgeList", jList);
			map.addAttribute("judgeSize", jList.size());
			map.addAttribute("TaxlienList",taxList);
			map.addAttribute("taxSize", taxList.size());
			
			return newvwBJLResultDetails;
		} catch (Exception e) {
			return newvwError;
		}
	}
	

	//Sorting Result for the srceen display	
	public class resultSort implements Comparator<NewBJLResults>{
		@Override
		public int compare(NewBJLResults o1, NewBJLResults o2) {
			return o1.getDocket_number().compareToIgnoreCase(o2.getDocket_number());
			
		}
		
	}
	public class FilingResultSort implements Comparator<NewBJLResults>{
		@Override
		public int compare(NewBJLResults o1, NewBJLResults o2) {
			return o1.getFiling_type_desc().compareToIgnoreCase(o2.getFiling_type_desc());
			
		}
		
	}
	public class FilingGroupSort implements Comparator<NewBJLResults>{
		@Override
		public int compare(NewBJLResults o1, NewBJLResults o2) {
			return o1.getFiling_group().compareToIgnoreCase(o2.getFiling_group());
			
		}
		
	}


	
}