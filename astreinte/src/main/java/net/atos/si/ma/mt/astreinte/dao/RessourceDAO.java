
			package net.atos.si.ma.mt.astreinte.dao;

import net.atos.si.ma.mt.astreinte.model.Ressource;

public interface RessourceDAO extends GenericDAO<Ressource> {

	Ressource find(long id);
	Ressource checklogin(String login,String password);

}		
