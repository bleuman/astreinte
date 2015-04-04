package net.atos.si.ma.mt.astreinte.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.atos.si.ma.mt.astreinte.dao.GenericDAO;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public abstract class GenericHibernateDAO<T> implements GenericDAO<T> {

	/*
	 * Esse cara é a entidade que passarmos quando extends a classe
	 * GenericHibernateDAO, observe que ele é bem generico.
	 */
	private Class<T> persistentClass;

	@Autowired
	private SessionFactory sessionFactory;

	public GenericHibernateDAO(Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	public void saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		getCurrentSession().delete(entity);

	}

	public List<T> list() {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		return criteria.list();
	}

	public List<T> listByCriteres(String query, Map<String, Object> params) {
		Query hquery = getCurrentSession().createQuery(query);
		Set<Entry<String, Object>> listParams = params.entrySet();
		for (Object object : listParams) {
			if (object instanceof String) {
				String rawkey = (String) object;
				String key = rawkey.substring(rawkey.indexOf(':') + 1,
						rawkey.length());
				String parmaName = rawkey.substring(rawkey.indexOf(':'));
				switch (key) {
				case "string":
					hquery.setString(parmaName, (String) params.get(rawkey));
					break;

				case "date":
					hquery.setDate(parmaName, (Date) params.get(rawkey));
					break;
					
				case "time":
					hquery.setTime(parmaName, (Date) params.get(rawkey));
					break;

				default:
					break;
				}
			}
		}

		return (List<T>) hquery.list();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	};

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

}