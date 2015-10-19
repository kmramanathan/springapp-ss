package springapp.repository.jdbc;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public abstract class AbstractJdbcDao extends SimpleJdbcDaoSupport implements ResourceLoaderAware {
	// everyone needs a logger
	protected final Log logger = LogFactory.getLog(getClass());
    
    // setters (do we need getters?)
    protected ResourceLoader resourceLoader;
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;		
	}	

    /*
     * to implement @Repository while extending SJDS above, we must declare a 
     * data source, and we must also override checkDaoConfig().
     */
    @Autowired
    protected DataSource dataSource;

	// member fields populated from xml strings file
	protected String sqlStringsFile;
    protected Map<String,String> sqlStrings;
    
    @Override
    protected final void checkDaoConfig() {    	
    	if (this.getJdbcTemplate() == null) {
    		if (this.dataSource == null) {
    			throw new IllegalArgumentException("'dataSource' or 'jdbcTemplate' is required");        		
        	} else {
        		this.setJdbcTemplate(createJdbcTemplate(dataSource));
        	}
		}
    }   
    
    // load sql strings here
    @PostConstruct
    public void init() {
    	if (sqlStringsFile == null) {
    		sqlStringsFile = "classpath:" + this.getClass().getName().replace('.', '/') + "Strings.xml";
    	}
   		
		try {
	    	Resource res = resourceLoader.getResource(sqlStringsFile);    		
			sqlStrings = loadSqlStrings(res);
		} catch (Exception e) {
			logger.error("loadSqlStrings failed: " + sqlStringsFile);
			return;
		}
		
		// strings file should define at least table & sequence
		// log an error if we can't find either of those
		if ((sqlStrings.get("tableName") == null) || (sqlStrings.get("seqName") == null)) {
			logger.error("loadSqlStrings ok but table/sequence name not found");
		}
    }
    
    // methods to load sql strings from xml file
    protected HashMap<String,String> loadSqlStrings(Resource res) throws IOException {
    	return this.loadSqlStrings(res.getURL());
    }
    
    protected HashMap<String,String> loadSqlStrings(URL url) {
		HashMap<String,String> queryStrings = null;
		try {
			queryStrings = new HashMap<String, String>();
			
			XMLConfiguration config = new XMLConfiguration();
			config.setDelimiterParsingDisabled(true);
			config.load(url);			
			
			List<?> list = config.configurationsAt("string");
			if (list != null) {
				logger.debug("size: " + list.size());
				for (Iterator<?> i = list.iterator(); i.hasNext();) {
					HierarchicalConfiguration sub = (HierarchicalConfiguration) i.next();
					String queryName = sub.getString("[@name]");
					String queryText = sub.getString("");

					queryStrings.put(queryName, queryText);
					//logger.info("string: " + queryName + ":" + queryText);
				}
			}			
		} catch (ConfigurationException e) {
			logger.error("Failed to load sql strings!");
		}
		return queryStrings;
	}
    
    /*
     * public methods
     */

    // Postgres only: get the next value in a sequence
    public int getSeqNextVal(String seqName) {
        return this.getJdbcTemplate().queryForInt("select nextval('" + seqName + "')");
    }
}