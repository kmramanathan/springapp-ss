package springapp.service;

import org.apache.log4j.Logger;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

abstract public class NeonService implements ResourceLoaderAware {
	protected final Logger logger = Logger.getLogger(getClass());
	
    protected ResourceLoader resourceLoader;
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;		
	}
}