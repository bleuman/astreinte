package net.atos.si.ma.mt.astreinte.service;

import net.atos.si.ma.mt.astreinte.model.Ressource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface RessourceService extends GenericService<Ressource> {
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Ressource find(long id);

	boolean checklogin(String login, String password);
}
