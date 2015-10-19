package springapp.web.funnel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import springapp.domain.CorpAddress;
import springapp.domain.CorpHistory;
import springapp.domain.CorpNameInfo;
import springapp.domain.CorpResponses;
import springapp.domain.CorpXMLParser;
import springapp.domain.corporation.CorporationResults;
import springapp.domain.corporation.CorporationSearches;
import springapp.manager.SpringCorporationSearchManager;
import springapp.repository.CorpDao;
import springapp.web.funnel.NewCorpIndSearchForm.CorpIndSearchFormCommand;

@Controller
public class CorpResultsDisplay extends AbstractFunnelController {
	@Autowired
	protected SpringCorporationSearchManager corpManager;
	@Autowired
	protected CorpDao corpDao;
	
	@RequestMapping(value="/funnel/CorpResults.do")
	public String submitCorpResults(HttpSession session, ModelMap map)
	{
		Integer UserSearchId = (Integer) session.getAttribute("userSearchId");
		logger.info("usersearchId:"+UserSearchId);
		if(UserSearchId == null)
		{
			return vwError;
		}
		map.addAttribute("UserSearchId", UserSearchId);
		try{
			CorporationSearches corpSearchResults=corpManager.getSearch(UserSearchId);
			if(corpSearchResults.getMatchCount() == 0)
			{
				map.addAttribute("corpSearch", true);
				map.addAttribute("corpName", corpSearchResults.getLastname()+" "+corpSearchResults.getFirstname());
				map.addAttribute("corpBName", corpSearchResults.getBusinessname());
				map.addAttribute("corpState", corpSearchResults.getState());
				map.addAttribute("corpRefer", corpSearchResults.getReference());
				session.removeAttribute("UserSearchId");
				session.removeAttribute("corpIndSearchFormCommand");
				return corpNoResult;
				}
			
			List<CorpResponses> corpResponse=corpDao.getAllCorpResponses(UserSearchId);
			List<CorpNameInfo> corpNameList=null;
			List<CorpNameInfo> nameList=new ArrayList<CorpNameInfo>();
			List<CorpAddress> corpAddrList=null;
			List<CorpAddress> addrList=new ArrayList<CorpAddress>();
			List<CorpHistory> corpHistoryList=null;
			List<CorpHistory> histList=new ArrayList<CorpHistory>();
			for (Iterator iterator = corpResponse.iterator(); iterator
					.hasNext();) {
				CorpResponses cr = (CorpResponses) iterator.next();
			
				corpNameList= corpDao.getNameAddressList(UserSearchId, cr.getFiling_number().toUpperCase());
				nameList.addAll(corpNameList);
				corpAddrList=corpDao.getCorpAddressList(UserSearchId, cr.getFiling_number().toUpperCase());
				addrList.addAll(corpAddrList);
				corpHistoryList=corpDao.getCorpHistoryList(UserSearchId, cr.getFiling_number().toUpperCase());
				histList.addAll(corpHistoryList);
			}
			
			map.addAttribute("corpResList", corpResponse);
			map.addAttribute("corpResSize", corpResponse.size());
			map.addAttribute("nameList", nameList);
			map.addAttribute("nameSize", nameList.size());
			map.addAttribute("addrList", addrList);
			map.addAttribute("addrSize", addrList.size());
			map.addAttribute("historyList", histList);
			map.addAttribute("historySize", histList.size());
			
			map.addAttribute("corpName", corpSearchResults.getLastname()+" "+corpSearchResults.getFirstname());
			map.addAttribute("corpBName", corpSearchResults.getBusinessname());
			map.addAttribute("corpState", corpSearchResults.getState());
			map.addAttribute("corpRefer", corpSearchResults.getReference());
			session.removeAttribute("UserSearchId");
			return corpResultDetails;
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("error:"+e);
			return newvwError;
		}
		
	}

}
