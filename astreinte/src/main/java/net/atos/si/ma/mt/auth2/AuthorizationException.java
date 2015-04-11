package net.atos.si.ma.mt.auth2;

public class AuthorizationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 201225537729837011L;
	private String message;

	public AuthorizationException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
