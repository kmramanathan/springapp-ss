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
public class MexicanStaticPagesController {
	
	protected static final String coverageView = "funnel/mexico/Coverage";
	protected static final String samleReportView = "funnel/mexico/SampleReport";
	protected static final String searchTipsView = "funnel/mexico/SearchTips";
	protected static final String wholePictureView = "funnel/mexico/WholePicture";
	protected static final String pricesView = "funnel/mexico/Prices";
	protected static final String errorView = "Error";
	
	@RequestMapping("/funnel/mexico/showStaticPages.do")
	public String showStaticPages(
			@RequestParam(value="page", required=true) String staticPage
			)  {
		if (staticPage.equals("coverage")) {
			return coverageView;
		} else if (staticPage.equals("samplereport")) {
			return samleReportView;
		} else if (staticPage.equals("searchtips")) {
			return searchTipsView;
		} else if (staticPage.equals("wholePicture")) {
			return wholePictureView;
		} else if (staticPage.equals("prices")) {
			return pricesView;
		} else {			
			return errorView;
		}
	}
}