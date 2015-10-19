package springapp.web.test;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;

import springapp.service.user.UserManager;

public abstract class AbstractTestController {
protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	protected UserManager userManager;
	@Autowired
	protected MailSender mailSender;
	@Autowired
	protected VelocityEngine velocityEngine;
	@Autowired
	protected JavaMailSender javaMailSender;
	
	protected static final int SESSION_TIMEOUT = 3600;
	protected static final String salesFunnelUser = "SalesFunnelUser";
	protected static final Integer bgcTestProductId = 1;
	
	static final int TEST_BJL_RESULT_ID = 65411;
	static final int TEST_CRIMINAL_RESULT_ID = 1764;
	//web paths
	
	protected static final String showBusinessReg="test/BusinessForm";
	protected static final String showpurchaseRedir="redirect:BusinessnewRegister.do";

}
