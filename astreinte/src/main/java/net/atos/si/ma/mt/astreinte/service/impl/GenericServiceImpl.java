package net.atos.si.ma.mt.astreinte.service.impl;

import java.util.List;
import java.util.Map;

import net.atos.si.ma.mt.astreinte.dao.GenericDAO;
import net.atos.si.ma.mt.astreinte.service.GenericService;

import org.springframework.stereotype.Service;

@Service
public class GenericServiceImpl<T, DAO extends GenericDAO<T>> implements
		GenericService<T> {

	private DAO dao;

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	public DAO getDao() {
		return dao;
	}

	public void save(T entity) {
		dao.saveOrUpdate(entity);

	}

	public void update(T entity) {
		dao.saveOrUpdate(entity);

	}

	public void delete(T entity) {
		dao.delete(entity);

	}

	public List<T> listAll() {
		List<T> list = dao.list();
		return list;
	}

	public List<T> listByCriteres(String query, String[] keys, Object[] values) {
		List<T> list = dao.listByCriteres(query, keys,values);
		return list;
	}

}
