package springapp.manager;
 
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Log4jConfigurer;
 
/**
 * A simple Spring Bean that allows log configuration to be managed in the Application Context
 *
 * Originally by Daniel Rijkhof (daniel.rijkhof@gmail.com)
 */
public class Log4jDirectConfigurer implements InitializingBean {
    private String location;
    private long refreshInterval;
    
    // not sure if this makes sense here
    protected Logger logger = Logger.getLogger(getClass());
 
    @Autowired
    private ApplicationContext context;

    public void afterPropertiesSet() throws Exception {
        if (location == null) {
            return;
        }
 
        if (refreshInterval == 0) {
            Log4jConfigurer.initLogging(location);
        } else {
            Log4jConfigurer.initLogging(location, refreshInterval);
        }
        
//        Context initCtx = new InitialContext();
//        Context envCtx = (Context) initCtx.lookup("java:comp/env");
//
//        // Look up our data source
//		DataSource ds = (DataSource) envCtx.lookup("jdbc/EmployeeDB");
//
//		// Allocate and use a connection from the pool
//		Connection conn = ds.getConnection();
//		// ... use this connection to access the database ...
//		conn.close();


        logger.info("context: " + context.getId());
    }
 
    // Attribute injectors
    public void setLocation(String location) {
        this.location = location;
    }
 
    public void setRefreshInterval(long refreshInterval){
        this.refreshInterval = refreshInterval;
    }
}