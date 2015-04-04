package net.atos.si.ma.mt.astreinte.dao;

import java.util.List;

import net.atos.si.ma.mt.astreinte.model.Astreinte;

public interface AstreinteDAO extends GenericDAO<Astreinte> {

	Astreinte find(long id);

	public List<Astreinte> checkChevechment(Astreinte astreinte);

}
