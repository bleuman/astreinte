package net.atos.si.ma.mt.astreinte.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.core.HttpHeaders;

import net.atos.si.ma.mt.astreinte.dao.RessourceDAO;
import net.atos.si.ma.mt.astreinte.model.Ressource;
import net.atos.si.ma.mt.astreinte.service.RessourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RessourceServiceImpl extends
		GenericServiceImpl<Ressource, RessourceDAO> implements RessourceService {
	private final Map<String, String> authorizationTokensStorage = new HashMap<String, String>();

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

	public String login(String login, String password) {
		if (getDao().checklogin(login, password)) {
			String authToken = UUID.randomUUID().toString();
			authorizationTokensStorage.put(authToken, login);
			return authToken;
		}
		return null;
	}

	private static final String BEARER = "Bearer";

	private static String getToken(HttpHeaders headers) {
		List<String> contents = headers
				.getRequestHeader(HttpHeaders.AUTHORIZATION);
		for (String content : contents) {
			content = content.trim();
			if (content.startsWith(BEARER)) {
				String[] parts = content.split(" ");
				if (parts != null && parts.length > 1) {
					return parts[1];
				}
			}
		}
		return null;
	}

	@Override
	public void logout(HttpHeaders headers) {
		String token = getToken(headers);
		authorizationTokensStorage.remove(token);

	}

	@Override
	public boolean isAuthTokenValid(HttpHeaders headers) {
		String token = getToken(headers);
		if (authorizationTokensStorage.containsKey(token))
			return true;
		else
			return false;
	}

}
