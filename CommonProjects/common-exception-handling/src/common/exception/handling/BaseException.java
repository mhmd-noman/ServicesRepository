package common.exception.handling;

public class BaseException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public BaseException() {
		super();
	}

	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(Throwable reason) {
		super(reason);
	}
	
	public BaseException(String message, Throwable reason) {
		super(message, reason);
	}
	
	@Override
	public String getMessage() {
		String msg = super.getMessage();
		if (null != msg) {
			return msg;
		} else {
			return getClass().getName();
		}
	}
}
