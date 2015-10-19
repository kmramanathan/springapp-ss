package springapp.web.funnel;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import springapp.service.NeonValidator;
import springapp.web.funnel.PropertySearchForm.RealPropSearchFormCommand;

@Controller

public class zTestmail extends AbstractFunnelController {
	
	public void init() {
		
	}
	
	@RequestMapping(value = "/funnel/testmail.do", method = RequestMethod.GET)
	public String setupEvictionFormCampaign(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test,
			@RequestParam(value="sample",required=false) Boolean sample) {	
		// check campaign id here
		samplemail cmd = new samplemail();
		map.addAttribute("command", cmd);
		return vwSampleMail;		
	}	
	
	@RequestMapping(value = "/funnel/testmail.do", method = RequestMethod.POST)
	public String processEvictionSubmitFull(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") samplemail sMail,
			Errors errors,
			SessionStatus status) 
	{
		
		String subject = "Search Systems Sample Email";
        sendEmailVelocity(map, tplSearchReceipt, emailFromNoReply, sMail.getToEmail(), subject);		
		return vwSampleMail;
		
	}
	
	public static class samplemail
	{
		
		private String toEmail;
		
		
		public String getToEmail()
		{
			return toEmail;
		}
		public void setToEmail(String toEmail)
		{
			this.toEmail = toEmail;
		}
	
	
	}
}



