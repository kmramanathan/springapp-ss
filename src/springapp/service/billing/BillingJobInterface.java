package springapp.service.billing;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface BillingJobInterface {
      public void init(JobExecutionContext jobExecutionContext) throws JobExecutionException;
      public void execute() throws JobExecutionException;
      public void destroy();
}