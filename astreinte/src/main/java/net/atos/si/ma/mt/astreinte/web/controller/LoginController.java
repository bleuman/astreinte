package net.atos.si.ma.mt.astreinte.web.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import net.atos.si.ma.mt.astreinte.model.LoginData;
import net.atos.si.ma.mt.astreinte.model.LoginObject;
import net.atos.si.ma.mt.auth2.AuthenticatorAuthorizator;
import net.atos.si.ma.mt.auth2.AuthorizationException;
import net.atos.si.ma.mt.auth2.Principale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
@Path("/login")
public class LoginController {
	@Autowired
	@Qualifier("authenticatorAuthorizatorImpl")
	private AuthenticatorAuthorizator authenticatorAuthorizator;

	@POST
	@Path("/authenticate/")
	@Produces(MediaType.APPLICATION_JSON)
	public Principale checklogin(@Context HttpHeaders headers,
			LoginObject loginObject) throws AuthorizationException {
		return authenticatorAuthorizator.login(loginObject.login,
				loginObject.password);
	}

	@GET
	@Path("/logout/")
	@Produces(MediaType.APPLICATION_JSON)
	public void logout(@Context HttpHeaders headers) {

	}

	@GET
	@Path("/isauth/")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean isauth(@Context HttpHeaders headers) {
		return true;
	}
}
