package net.atos.si.ma.mt.astreinte.service.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.HttpHeaders;

import net.atos.si.ma.mt.astreinte.dao.RessourceDAO;
import net.atos.si.ma.mt.astreinte.model.LoginData;
import net.atos.si.ma.mt.astreinte.model.Ressource;
import net.atos.si.ma.mt.astreinte.service.AuthorizationException;
import net.atos.si.ma.mt.astreinte.service.RessourceService;
import net.atos.si.ma.mt.astreinte.web.controller.AllowedRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

@Service
public class RessourceServiceImpl extends
		GenericServiceImpl<Ressource, RessourceDAO> implements RessourceService {
	// private final Map<String, HashMap<String, Object>>
	// authorizationTokensStorage = new HashMap<String, HashMap<String,
	// Object>>();
	private static final String SECRET = "my secret";
	private static final String CTRPACKAGE = "net.atos.si.ma.mt.astreinte.web.controller";
	private static JWTSigner signer = new JWTSigner(SECRET);
	private static JWTVerifier verifier = new JWTVerifier(SECRET);

	@Autowired
	@Qualifier("ressourceDAOImpl")
	@Override
	public void setDao(RessourceDAO dao) {
		super.setDao(dao);
	}

	public Ressource find(long id) {
		Ressource objRegister = getDao().find(id);
		return objRegister;
	}

	public LoginData login(String login, String password) {
		LoginData loginData = new LoginData();
		loginData.statut = false;
		loginData.username = login;
		loginData.motif = "Login/Password Incorrect";

		Ressource ressource = getDao().checklogin(login, password);
		if (ressource != null) {
			// String authToken = UUID.randomUUID().toString();
			HashMap<String, Object> claims = new HashMap<String, Object>();
			claims.put(ressource.getId() + "", ressource.getRole());
			String authToken = signer.sign(claims, new JWTSigner.Options()
					.setExpirySeconds(6000).setIssuedAt(true));
			// authorizationTokensStorage.put(authToken, claims);

			loginData.token = authToken;
			loginData.statut = true;
			loginData.motif = "Success";
			loginData.id = ressource.getId();
			loginData.role = ressource.getRole();

		}
		return loginData;
	}

	private static final String BEARER = "Bearer";

	private static String[] getToken(HttpHeaders headers) {
		List<String> contents = headers
				.getRequestHeader(HttpHeaders.AUTHORIZATION);
		for (String content : contents) {
			content = content.trim();
			if (content.startsWith(BEARER)) {
				String[] parts = content.split(" ");
				if (parts != null && parts.length > 2) {
					return parts;
				}
			}
		}
		return null;
	}

	@Override
	public void logout(HttpHeaders headers) {
		String[] parts = getToken(headers);
		if (parts != null) {
			String token = parts[1];
			// authorizationTokensStorage.remove(token);
		}

	}

	@Override
	public boolean isAuthTokenValid(HttpHeaders headers) {
		String[] parts = getToken(headers);
		if (parts == null || parts.length < 1)
			return false;
		String token = parts[1];
		try {
			/*
			 * HashMap<String, Object> claims = authorizationTokensStorage
			 * .get(token); if (claims == null) return false;
			 */
			Map<String, Object> decoded = verifier.verify(token);
			/*
			 * Set<String> keys = claims.keySet(); for (String key : keys) { if
			 * (!claims.get(key).equals(decoded.get(key))) return false; }
			 */
			return true;

		} catch (InvalidKeyException | NoSuchAlgorithmException
				| IllegalStateException | SignatureException | IOException
				| JWTVerifyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean canDo(HttpHeaders headers) {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		for (StackTraceElement s : stacks) {
			String className = s.getClassName();
			String methodName = s.getMethodName();
			if (className.contains(CTRPACKAGE)) {
				System.out.println("*** stack: " + className + " --> "
						+ methodName);
				try {
					Class clazz = Class.forName(className);
					Method[] methods = clazz.getDeclaredMethods();
					for (Method meth : methods) {
						String methName = meth.getName();
						if (methName.equals(methodName)) {
							Annotation[] decalredAnno = meth
									.getDeclaredAnnotations();
							for (Annotation annotation : decalredAnno) {
								System.out.println("£££££££ "
										+ annotation.getClass()
												.getCanonicalName());
							}
							AllowedRoles annot = meth
									.getAnnotation(AllowedRoles.class);
							String role = getRole(headers);
							if (annot != null) {
								boolean connected = isAuthTokenValid(headers);
								if (annot.mustConnect() && !connected)
									return false;
								if (role == null)
									return false;
								else {
									if (annot.roles().equals("*"))
										return true;
									if (annot.roles().contains(role)) {
										return true;
									} else {
										return false;

									}
								}
							}
						}
					}

				} catch (SecurityException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return false;
	}

	@Override
	public String getRole(HttpHeaders headers) {
		String[] parts = getToken(headers);
		if (parts == null || parts.length < 1)
			return null;
		String token = parts[1];
		String idres = parts[2];
		try {

			Map<String, Object> decoded = verifier.verify(token);
			/*
			 * Set<String> keys = claims.keySet(); for (String key : keys) { if
			 * (!claims.get(key).equals(decoded.get(key))) return false; }
			 */
			return (String) decoded.get(idres);

		} catch (InvalidKeyException | NoSuchAlgorithmException
				| IllegalStateException | SignatureException | IOException
				| JWTVerifyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void canDoOrException(HttpHeaders headers)
			throws AuthorizationException {
		if (!canDo(headers))
			throw new AuthorizationException();

	}
}
