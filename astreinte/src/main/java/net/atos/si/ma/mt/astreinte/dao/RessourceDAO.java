package net.atos.si.ma.mt.astreinte.dao;

import net.atos.si.ma.mt.astreinte.model.Ressource;
import net.atos.si.ma.mt.auth2.Principale;

public interface RessourceDAO extends GenericDAO<Ressource> {

	Ressource find(long id);

	Principale checkLogin(String login, String password);
}
