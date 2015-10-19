package springapp.web.legal;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import springapp.service.NeonValidator;

@Controller
@RequestMapping("/legal/contactUs.do")
@SessionAttributes({"command"})

public class ContactUsForm {
	private Logger logger = Logger.getLogger(getClass());

	protected static final String contactUsView = "legal/ContactUs";
	protected static final String thankYouView = "legal/ContactUsThankYou";
	
	protected static final String contactUsEmailTemplate = "springapp/web/legal/ContactUsEmailTemplate.vm";
	
	protected static final String emailFrom = "contact-us@searchsystems.net";
	protected static final String emailTo = "seand@searchsystems.net";
	
	@Autowired
	private MailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			HttpSession session,
			ModelMap map,
			@RequestParam(value="test",required=false) Boolean test
			) {		
		ContactUsFormCommand cmd = new ContactUsFormCommand();
		if (test == null) { test = false; }
		if (test) {
			setSample(cmd);
		}
		map.addAttribute("command", cmd);
		return contactUsView;
	}
	
	protected void setSample(ContactUsFormCommand cmd) {
		cmd.setName("Judd Bourgeois");
		cmd.setEmail("judd@searchsystems.net");
		cmd.setSubject("Feedback");
		String message = "blah blah blah\n" + "foo bar baz blah blah\n" + "This is our test message";
		cmd.setMessage(message);
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			HttpSession session,
			ModelMap map,
			@ModelAttribute("command") ContactUsFormCommand command,
			Errors errors,
			SessionStatus status) {
		ContactUsFormValidator v = new ContactUsFormValidator();
		v.validate(command, errors);
		if (errors.hasErrors()) {
			logger.info("errors:" + errors.getFieldErrors());
			return contactUsView;
		} else {
			sendEmailVelocity(command);
			status.setComplete();
			return thankYouView;			
		}		
	}
	
	protected void sendEmailSimple(ContactUsFormCommand command) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(emailFrom);
		msg.setTo(emailTo);
		msg.setSubject("Email From Contact Us Form: " + command.getSubject());
		
		String text = "";
		text += "Message from: " + command.getName() + "\n";
		text += "Email: " + command.getEmail() + "\n";
		text += "Message:\n" + command.getMessage();
				
		msg.setText(text);
		try {
			mailSender.send(msg);
		} catch (Exception e) {
			logger.error("Problem sending email", e);
		}
	}
	
	protected void sendEmailVelocity(ContactUsFormCommand command) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(emailFrom);
		msg.setTo(emailTo);
		msg.setCc(command.getEmail());
		msg.setSubject("Email From Contact Us Form: " + command.getSubject());
		
		HashMap<String,String> map = new HashMap<String,String>();
        map.put("from", command.getName());
        map.put("email", command.getEmail());
        map.put("subject", command.getSubject());
        map.put("message", command.getMessage());

        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, contactUsEmailTemplate, map);
		
		msg.setText(text);
		try {
			mailSender.send(msg);
		} catch (Exception e) {
			logger.error("Problem sending email", e);
		}
	}
	
	public static class ContactUsFormCommand {
		private String name;
		private String email;
		private String subject;
		private String message;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
	
	public class ContactUsFormValidator extends NeonValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(ContactUsFormCommand.class);
		}
		
		public void validate(Object target, Errors errors) {
			ContactUsFormCommand cmd = (ContactUsFormCommand) target;
			
			validateString("name",    cmd.getName(),   errors,  3, 50, regexBasicSpace);
			validateString("email",   cmd.getEmail(),   errors,  5, 50, emailRegex);
			validateString("subject", cmd.getSubject(),   errors,  5, 50, regexBasicSpace);
			validateString("message", cmd.getMessage(),   errors,  4, 500, regexText);
		}		
	}	
}