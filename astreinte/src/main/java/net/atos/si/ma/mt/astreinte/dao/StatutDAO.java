
			package net.atos.si.ma.mt.astreinte.dao;

import net.atos.si.ma.mt.astreinte.model.Statut;

public interface StatutDAO extends GenericDAO<Statut> {

	Statut find(long id);

}		
