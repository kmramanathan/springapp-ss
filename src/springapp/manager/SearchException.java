/*
 * Pacific Information Resources Inc. dba Search Systems
 * Copyright (C) 2006-2007
 * All rights reserved.
 */
package springapp.manager;

import org.springframework.core.NestedRuntimeException;

/**
 * Checker exception class.
 *
 * @author  judd
 */
public class SearchException extends NestedRuntimeException {
	private static final long serialVersionUID = 1L;

	public SearchException(String msg) {
		super(msg);
	}
	
	public SearchException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public SearchException(Throwable t) {
		super(SearchException.class.getName(), t);
	}
}
