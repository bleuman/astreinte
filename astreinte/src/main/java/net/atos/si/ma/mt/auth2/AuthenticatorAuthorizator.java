package net.atos.si.ma.mt.auth2;

import javax.ws.rs.core.HttpHeaders;

import org.aspectj.lang.ProceedingJoinPoint;

public interface AuthenticatorAuthorizator {
	Object canDo (ProceedingJoinPoint proceedingJoinPoint ) throws AuthorizationException;
	Principale login(String login, String password) throws AuthorizationException;
	
	Principale getPrincipaleFormToken(HttpHeaders headers) throws AuthorizationException;
	
}
