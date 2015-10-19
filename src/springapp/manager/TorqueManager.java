package springapp.manager;

import org.apache.log4j.Logger;
import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;

public class TorqueManager implements ApplicationListener {
	private Logger logger = Logger.getLogger(getClass());
	
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextStartedEvent) {
			logger.info("context started");
			init();
		}
		if (event instanceof ContextRefreshedEvent) {
			logger.info("context refreshed");
			init();
		}
	}
	
	public void init() {
    	logger.info("calling startUp()");
    	try {
    		if (!Torque.isInit()) {
    			//logger.info("init torque");
    			Torque.init("torque.properties");
    			logger.info("torque init ok");
    		}
		} catch (TorqueException e) {
			logger.error("Torque init failed, continuing anyway", e);
		}
	}
}