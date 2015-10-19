// +---------------------------------------------------------------------------+
// | Copyright (c) 2004 Search Systems. All rights reserved.                   |
// |                                                                           |
// | Author: Sean Kerr <skerr@searchsystems.net>                               |
// +---------------------------------------------------------------------------+

package springapp.crypt;

import org.springframework.core.NestedRuntimeException;

public class CryptException extends NestedRuntimeException {
	private static final long serialVersionUID = 1L;

	public CryptException(Throwable thrown) {
		super("Crypt exception", thrown);
	}
}
