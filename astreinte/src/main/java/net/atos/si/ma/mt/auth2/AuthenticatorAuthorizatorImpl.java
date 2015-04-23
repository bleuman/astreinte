package net.atos.si.ma.mt.auth2;

import java.io.IOException;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.util.GenericSignature.ClassSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

@Aspect
@Service
public class AuthenticatorAuthorizatorImpl implements AuthenticatorAuthorizator {
	private static final String BEARER = "Bearer";
	private static final String PRINCIPAL = "principale";
	private static final String DEFAULTSECRET = "0XMPO090909XXPXOXOPXP&10";
	private JWTSigner signer = null;
	private JWTVerifier verifier = null;
	@Autowired
	@Qualifier("loginServiceImpl")
	private LoginService loginService;
	private int expire = 3000;

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public AuthenticatorAuthorizatorImpl(String secret) {
		signer = new JWTSigner(secret);
		verifier = new JWTVerifier(secret);

	}

	public AuthenticatorAuthorizatorImpl() {
		signer = new JWTSigner(DEFAULTSECRET);
		verifier = new JWTVerifier(DEFAULTSECRET);

	}

	@Around("execution(* net.atos.si.ma.mt.astreinte.web.controller.AstreinteController.*(..)) || @annotation(net.atos.si.ma.mt.auth2.AllowedRoles)")
	public Object canDo(ProceedingJoinPoint proceedingJoinPoint)
			throws AuthorizationException {
		Object value = null;
		Object[] args = proceedingJoinPoint.getArgs();
		for (Object object : args) {
			if (object instanceof HttpHeaders) {
				HttpHeaders headers = (HttpHeaders) object;
				Principale principale = getPrincipaleFormToken(headers);
				if (principale != null) {
					String role = principale.getRole();
					if (role != null && !role.trim().equals("")) {
						MethodSignature signature = (MethodSignature) proceedingJoinPoint
								.getSignature();
					
						Method method = signature.getMethod();
						String classRoles = "*";
						String roles = "*";
						Class clazz=method.getDeclaringClass();
						AllowedRoles allowedRolesClass = clazz.getClass().getAnnotation(AllowedRoles.class);
						AllowedRoles allowedRoles = method
								.getAnnotation(AllowedRoles.class);
						if (allowedRolesClass != null)
							classRoles = allowedRolesClass.roles();
						if (allowedRoles != null)
							roles = allowedRoles.roles();

						if (!(roles.equals("*") || roles.contains(role) || (roles
								.equals("*") && (classRoles.equals("*") || classRoles
								.contains(role))))) {
							throw new AuthorizationException(
									"Acces no authorised",
									Response.Status.NO_CONTENT.getStatusCode());
						}
					}
				} else {
					throw new AuthorizationException("User no connected",
							Response.Status.PRECONDITION_FAILED.getStatusCode());
				}
				break;
			}
		}
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			if (e instanceof AuthorizationException) {
				AuthorizationException authe = (AuthorizationException) e;
				throw authe;
			} else
				throw new AuthorizationException(e.getMessage(),
						Response.Status.PRECONDITION_FAILED.getStatusCode());
		}
		return value;
	}

	public Principale login(String login, String password)
			throws AuthorizationException {
		try {
			Principale principale = loginService.checkLogin(login, password);
			if (principale == null) {
				throw new AuthorizationException("Login incorect",
						Response.Status.FORBIDDEN.getStatusCode());
			} else {
				HashMap<String, Object> claims = new HashMap<String, Object>();
				claims.put(PRINCIPAL, principale);
				String authToken = signer.sign(claims, new JWTSigner.Options()
						.setExpirySeconds(expire).setIssuedAt(true));
				principale.setToken(authToken);
				return principale;

			}
		} catch (Exception e) {
			throw new AuthorizationException(e.getMessage(),
					Response.Status.FORBIDDEN.getStatusCode());
		}

	}

	public Principale getPrincipaleFormToken(HttpHeaders headers)
			throws AuthorizationException {
		try {
			List<String> contents = headers
					.getRequestHeader(HttpHeaders.AUTHORIZATION);
			for (String content : contents) {
				content = content.trim();
				if (content.startsWith(BEARER)) {
					String[] parts = content.split(" ");
					if (parts != null && parts.length > 1) {
						String token = parts[1];
						Map<String, Object> decoded = verifier.verify(token);
						Object pricipalObject = decoded.get(PRINCIPAL);
						if (pricipalObject instanceof Map<?, ?>) {
							Map<?, ?> principaleMap = (Map<?, ?>) pricipalObject;
							Principale principale = new Principale();
							principale.setLogin((String) principaleMap
									.get("login"));
							principale.setRole((String) principaleMap
									.get("role"));

							return principale;
						} else {
							return null;
						}

					}
				}
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| IllegalStateException | SignatureException | IOException
				| JWTVerifyException e) {
			throw new AuthorizationException(e.getMessage(),
					Response.Status.PRECONDITION_FAILED.getStatusCode());
		}
		return null;
	}

}
