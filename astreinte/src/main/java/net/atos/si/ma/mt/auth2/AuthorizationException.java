package net.atos.si.ma.mt.auth2;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import net.atos.si.ma.mt.astreinte.model.ErrorBean;

public class AuthorizationException extends WebApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 201225537729837011L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public AuthorizationException(String message, int status) {

		super(Response.status(status).entity(new ErrorBean(message, status))
				.build());
		this.message = message;
	}

}
