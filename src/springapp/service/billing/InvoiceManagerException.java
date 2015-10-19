package springapp.service.billing;

import org.springframework.core.NestedRuntimeException;

public class InvoiceManagerException extends NestedRuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvoiceManagerException(Throwable cause) {
		super("Unknown", cause);
	}
	public InvoiceManagerException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
