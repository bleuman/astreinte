package net.atos.si.ma.mt.astreinte.service.impl;

import java.io.IOException;
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
import net.atos.si.ma.mt.astreinte.service.RessourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

@Service
public class RessourceServiceImpl extends
		GenericServiceImpl<Ressource, RessourceDAO> implements RessourceService {
	//private final Map<String, HashMap<String, Object>> authorizationTokensStorage = new HashMap<String, HashMap<String, Object>>();
	private static final String SECRET = "my secret";
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
			claims.put(ressource.getId() + "", ressource.getLogin());
			String authToken = signer.sign(claims, new JWTSigner.Options()
					.setExpirySeconds(60).setIssuedAt(true));
			//authorizationTokensStorage.put(authToken, claims);

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
			//authorizationTokensStorage.remove(token);
		}

	}

	@Override
	public boolean isAuthTokenValid(HttpHeaders headers) {
		String[] parts = getToken(headers);
		if (parts == null || parts.length<1) 
			return false;
		String token = parts[1];
		try {
		/*	HashMap<String, Object> claims = authorizationTokensStorage					.get(token);
			if (claims == null)
				return false;*/
			Map<String, Object> decoded = verifier.verify(token);
			/*Set<String> keys = claims.keySet();
			for (String key : keys) {
				if (!claims.get(key).equals(decoded.get(key)))
					return false;
			}*/
			return true;

		} catch (InvalidKeyException | NoSuchAlgorithmException
				| IllegalStateException | SignatureException | IOException
				| JWTVerifyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}
