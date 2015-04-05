package net.atos.si.ma.mt.astreinte.dao.impl;

import java.util.List;

import net.atos.si.ma.mt.astreinte.dao.AstreinteDAO;
import net.atos.si.ma.mt.astreinte.model.Astreinte;
import net.atos.si.ma.mt.astreinte.model.Ressource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AstreinteDAOImpl extends GenericHibernateDAO<Astreinte> implements
		AstreinteDAO {

	public AstreinteDAOImpl() {
		super(Astreinte.class);
	}

	public Astreinte find(long id) {
		Astreinte registerFound = (Astreinte) getCurrentSession().get(
				Astreinte.class, id);

		return registerFound;
	}

	@Transactional
	public List<Astreinte> checkChevechment(Astreinte astreinte) {
		@SuppressWarnings("unchecked")
		List<Astreinte> list = getCurrentSession()
				.createQuery(
						"select * from astreinte where dateastreinte='2015-03-03' and "
								+ "( hdebut BETWEEN :hdebut and '12:00:00' or"
								+ " hfin BETWEEN :hdebut and :hfin  or "
								+ ":hdebut BETWEEN hdebut and hfin or "
								+ ":hfin BETWEEN hdebut and hfin )")
				.setTime("hdebut", astreinte.getHdebut())
				.setTime("hfin", astreinte.getHfin()).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Astreinte> getByRessource(Ressource ressource) {
		List<Astreinte> list = getCurrentSession()
				.createQuery("from Astreinte where ressource.id=:res")
				.setLong("res", ressource.getId()).list();
		return list;
	}

}
