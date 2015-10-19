package springapp.service.billing;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springapp.service.user.UserManager;

@Component
public class TestJob {
	protected final Logger logger = Logger.getLogger(getClass());

	@Autowired(required=true)
	UserManager userManager;
	
	public void init() {
		logger.info("um: " + userManager);
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("um: " + userManager);
	};
}