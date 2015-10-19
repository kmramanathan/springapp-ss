package springapp.web.admin.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springapp.web.NeonController;
import springapp.service.user.UserManager;

@Controller
public class TestMapController extends NeonController {
	protected static final String vwTestMap = "/admin/test/TestMap";
	
	protected String foo;
	
	@RequestMapping("/admin/test/testMap1.do")
	public String testMap1(ModelMap model) {
		model.addAttribute("foo", 1234);
		return vwTestMap;
	}
	
	@RequestMapping("/admin/test/testMap2.do")
	public String testMap2(Map<String,Object> map) {
		map.put("foo", 1234);
		return vwTestMap;
	}
	
	@RequestMapping("/admin/test/testMap3.do")
	public ModelAndView testMap3(Map<String,Object> map) {
		map.put("foo", 1234);
		return new ModelAndView(vwTestMap, map);
	}
	
	@ModelAttribute("txnStatus")
    public HashMap<Short,String> populateTxnStatus() {
		HashMap<Short,String> map = new HashMap<Short,String>();
		map.put((short) 10, "Approved");
		map.put((short) 11, "Declined");
		map.put((short) 12, "Error");
		return map;
	}
}