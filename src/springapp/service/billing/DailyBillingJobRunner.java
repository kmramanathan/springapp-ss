package springapp.service.billing;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class DailyBillingJobRunner extends QuartzJobBean {
	private static final String APPLICATION_CONTEXT_KEY = "applicationContext";
	private static final String JOB_BEAN_NAME_KEY = "DailyBillingJob";

	protected final Logger logger = Logger.getLogger(getClass());
	
	@Override
	protected final void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		SchedulerContext schedulerContext = null;
		try {
			schedulerContext = jobExecutionContext.getScheduler().getContext();
		} catch (SchedulerException e) {
			throw new JobExecutionException("Failure accessing scheduler context", e);
		}
		
		ApplicationContext appContext = (ApplicationContext) schedulerContext.get(APPLICATION_CONTEXT_KEY);
		logger.info("context: " + appContext);
		
		String hostName = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("neon.host.name");		
		logger.info("hostName: " + hostName);
		
		if (hostName.equals("app1")) {
			DailyBillingJob job = (DailyBillingJob) appContext.getBean(JOB_BEAN_NAME_KEY);
			job.execute(jobExecutionContext);
		}
	}
}