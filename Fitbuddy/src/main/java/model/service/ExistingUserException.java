package model.service;

public class ExistingUserException extends Exception {
	private static final long serialVersionUID = 1L;

	public ExistingUserException() {
		super();
	}

//	public ExistingUserException(String arg0) {
//		super(arg0);
//	}
	public ExistingUserException(String message) {
	        super(message); // 이 부분이 메시지를 설정하는 부분입니다.
	    }
}
