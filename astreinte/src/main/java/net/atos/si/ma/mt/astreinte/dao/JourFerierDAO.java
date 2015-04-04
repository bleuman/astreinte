package net.atos.si.ma.mt.astreinte.dao;

import java.util.Date;

import net.atos.si.ma.mt.astreinte.model.JourFerier;

public interface JourFerierDAO extends GenericDAO<JourFerier> {

	JourFerier find(long id);

	boolean isJourFerier(Date mdate);

}
