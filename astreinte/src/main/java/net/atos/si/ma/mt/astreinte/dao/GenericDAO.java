package net.atos.si.ma.mt.astreinte.dao;

import java.util.List;
import java.util.Map;

public interface GenericDAO<T> {

	void saveOrUpdate(T entity);
	void delete(T entity); 
	List<T> list();
	List<T> listByCriteres(String query,Map<String,Object> params);
	
}
