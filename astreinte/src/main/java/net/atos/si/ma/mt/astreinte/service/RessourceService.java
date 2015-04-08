package net.atos.si.ma.mt.astreinte.service;

import javax.ws.rs.core.HttpHeaders;

import net.atos.si.ma.mt.astreinte.model.LoginData;
import net.atos.si.ma.mt.astreinte.model.Ressource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface RessourceService extends GenericService<Ressource> {
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Ressource find(long id);

	LoginData login(String login, String password);

	void logout(HttpHeaders headers);

	boolean isAuthTokenValid(HttpHeaders headers);
}
