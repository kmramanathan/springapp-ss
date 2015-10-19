package springapp.web.funnel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springapp.manager.BGCDescription;
import springapp.manager.SpringBGCSearchManager;


/**
 * General purpose class for display-only URLs
 * 
 * @author judd
 *
 */
@Controller
public class FunnelDisplay extends AbstractFunnelController {
	@Autowired
	private SpringBGCSearchManager bgcManager;
	private static ArrayList<String> states;

	public void init() {		
		HashMap<String, ArrayList<BGCDescription>> descAll = bgcManager.getAllBGCDescriptions();
		//logger.info("descAll: " + descAll.size());
		Set<String> statesSet = descAll.keySet();		
		states = new ArrayList<String>(statesSet);
		states.remove("United States");
		states.remove("International");
		Collections.sort(states);
	
	}
	
	@RequestMapping("/funnel/reset.do")
	public String reset(HttpSession session) {
		session.invalidate();
		return landingRedir;
	}
	
	@RequestMapping("/funnel/searchBJL.do")
	public String dummyBJLLanding() {
		return "redirect:newSearchBJL.do";
	}
	
	@RequestMapping("/funnel/ourDatabases.do")
	public String showDatabases(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="view",required=false) String view,
			@RequestParam(value="state",required=false) String state
			)  {
		if (view == null) { view = "state"; }
		if (state == null) { state = "AZ"; }
		BGCDescription[] desc;
		if (view.equals("state")) {
			desc = bgcManager.getBGCJurisdictionInfo(state);
									
		} else {
			desc = bgcManager.getBGCJurisdictionInfo(view);
		}
		//logger.info("states: " + states.size());
		//logger.info("desc: " + desc.length);
		map.addAttribute("states", states);
		map.addAttribute("desc", desc);
		map.addAttribute("view", view);
		return ourDatabasesView;
	}
	
	
	@RequestMapping("/funnel/valueProposition.do")
	public String showValueProposition(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="nationwide",required=false) Boolean nationwide
			)  {
		if (nationwide == null) { nationwide = false; }
		map.addAttribute("nationwide", nationwide);
		session.removeAttribute("searchFormCommand");
		session.removeAttribute("signupFormCommand");
		return valuePropositionView;
	}
	
	@RequestMapping("/funnel/sampleReport.do")
	public String showSampleReport()  {
		return sampleReportView;
	}
	
	@RequestMapping("/funnel/welcome.do")
	public String showWelcome(
			HttpSession session,
			ModelMap map, @RequestParam(value="bonusType") String bonusType)  {
		map.addAttribute("username", session.getAttribute("username"));
		map.addAttribute("bonusType", bonusType);
		return welcomeView;
	}
	
	@RequestMapping("/funnel/subscribe.do")
	public String showSubscribe(
			ModelMap map,
			@RequestParam(value="linkid",required=false) String linkid)  {
		map.addAttribute("linkid", linkid);
		return subscribeReportView;
	}
	
	@RequestMapping("/funnel/criminalRecordsGuide.do")
	public String showCriminalRecordsGuideView()  {
		return criminalRecordsGuideView;
	}
}