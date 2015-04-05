package net.atos.si.ma.mt.astreinte.dao;

import java.util.List;

import net.atos.si.ma.mt.astreinte.model.Astreinte;
import net.atos.si.ma.mt.astreinte.model.Ressource;

public interface AstreinteDAO extends GenericDAO<Astreinte> {

	Astreinte find(long id);

	List<Astreinte> checkChevechment(Astreinte astreinte);

	List<Astreinte> getByRessource(Ressource ressource);

}
