package springapp.service.user;

import org.springframework.core.NestedRuntimeException;

public class UserManagerException extends NestedRuntimeException {
	private static final long serialVersionUID = 1L;
	
	public static enum Type {
		CC_FAILURE,
		UNKNOWN
	};
	
	private Type type;

	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public UserManagerException(String msg, Type type) {
		super(msg);
		this.type = type;		
	}
	public UserManagerException(String msg, Type type, Throwable cause) {
		super(msg, cause);
		this.type = type;
	}
}
