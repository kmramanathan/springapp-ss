package springapp.web.flatrate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hsqldb.Collation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springapp.domain.FRBGCRequest;
import springapp.domain.FRBGCResponse;
import springapp.manager.FRBGCManager;
import springapp.web.funnel.AbstractFunnelController;
import sun.misc.FpUtils;

@Controller
public class BGCResultDisplay extends AbstractFunnelController{
	
	protected Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping(value = "/flatrate/BGCResults.do", method = RequestMethod.GET)
	public String displayResult(ModelMap map, HttpSession session){
		
	    HashMap<String, FRBGCResponse> BGCResultHash = (HashMap<String, FRBGCResponse>) session.getAttribute("BGCResultHash");
	    ArrayList<FRBGCResponse> bgcList = new ArrayList<FRBGCResponse>();
	    for(FRBGCResponse bg : BGCResultHash.values()){
	    	bgcList.add(bg);
	    }
	    map.addAttribute("bgcList", bgcList);
	    map.addAttribute("size", bgcList.size());
		return FRBGCResult;
	}
	
	@RequestMapping(value = "/flatrate/BGCResults.do", method = RequestMethod.POST)
	public String displayResultSummary(HttpServletRequest request, HttpServletResponse response,
			ModelMap map, HttpSession session,
			@RequestParam(value = "printType") String printType){
		
		ArrayList<FRBGCResponse> bgcList = new ArrayList<FRBGCResponse>();
		HashMap<String, FRBGCResponse> BGCResultHash = (HashMap<String, FRBGCResponse>) session.getAttribute("BGCResultHash");
		if(printType.equalsIgnoreCase("all")){
			for(FRBGCResponse bg : BGCResultHash.values()){
		    	bgcList.add(bg);
		    }
		}else{
			String[] bgcKey =(String[])request.getParameterValues("bgcKey");
			for(String id: bgcKey){
				bgcList.add(BGCResultHash.get(id));
			}
		}
		FRBGCRequest sfc = (FRBGCRequest) session.getAttribute("FRBGCRequest");
		map.addObject("FRBGCRequest", sfc);
		map.addAttribute("bgcList", bgcList);
	  	return FRBGCResultDetail;
	}
	
	//static lookup
	@RequestMapping(value = "/flatrate/BGCDatasource.do")
	public String displayDataSource(){
		return "flatrate/BGCDataSource";
	}
	@RequestMapping(value = "/flatrate/CaseType.do")
	public String displaycodes(){
		return "flatrate/BGCCodes";
	}
}
