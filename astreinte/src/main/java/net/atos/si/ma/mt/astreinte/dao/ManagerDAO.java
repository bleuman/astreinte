
			package net.atos.si.ma.mt.astreinte.dao;

import net.atos.si.ma.mt.astreinte.model.Manager;

public interface ManagerDAO extends GenericDAO<Manager> {

	Manager find(long id);

}		
