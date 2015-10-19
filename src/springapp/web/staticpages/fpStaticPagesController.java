package springapp.web.staticpages;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * General purpose class for display-only URLs
 * 
 * @author Ramanathan Kumarappan
 *
 */
@Controller
public class fpStaticPagesController {
	
	protected static final String samleReportView = "findpeople/SampleReport";
	protected static final String BgSample = "findpeople/BGDetailsSample";
	protected static final String wholePicture = "findpeople/WholePicture";
	protected static final String TOU = "findpeople/TermOfUse";
	protected static final String InternationalBillMsg = "findpeople/InternationalBillMsg";
	protected static final String errorView = "Error";
	
	@RequestMapping("/findpeople/showStaticPages.do")
	public String showStaticPages(
			@RequestParam(value="page", required=true) String staticPage
			)  {
		if (staticPage.equals("BGsamplereport")) {
			return BgSample;
		} else if (staticPage.equals("samplereport")) {
			return samleReportView;
		}else if (staticPage.equals("wholepicture")) {
			return wholePicture;
		}
		else if (staticPage.equals("InBillMsg")) {
			return InternationalBillMsg;
		}
		else if (staticPage.equals("TOU")) {
			return TOU;
		}
		else {			
			return errorView;
		}
	}
}