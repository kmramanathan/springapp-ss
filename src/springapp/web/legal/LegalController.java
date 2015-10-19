package springapp.web.legal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * General purpose class for display-only URLs
 * 
 * @author judd
 *
 */
@Controller
public class LegalController {
	private Logger logger = Logger.getLogger(getClass());
	
	protected static final String agreementMembersView = "legal/AgreementMembers";
	protected static final String agreementFunnelView = "legal/AgreementFunnel";
	protected static final String agreementMexicanFunnelView = "legal/MexicanAgreementFunnel";
	protected static final String privacyPolicyView = "legal/PrivacyPolicy";
	protected static final String disclaimerView = "legal/Disclaimer";
	
	protected static final String errorView = "Error";
	
	@RequestMapping("/legal/showAgreement.do")
	public String showAgreement(
			@RequestParam(value="version") String version
			)  {
		if (version.equals("members")) {
			return agreementMembersView;
		} else if (version.equals("funnel")) {
			return agreementFunnelView;
		} else if (version.equals("mexicanfunnel")) {
			return agreementMexicanFunnelView;
		} else if (version.equals("privacy")) {
			return privacyPolicyView;
		} else if (version.equals("disclaimer")) {
			return disclaimerView;
		} else {			
			return errorView;
		}
	}
}