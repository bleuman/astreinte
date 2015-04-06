package net.atos.si.ma.mt.astreinte.dao.impl;

import java.util.List;

import net.atos.si.ma.mt.astreinte.dao.AstreinteDAO;
import net.atos.si.ma.mt.astreinte.model.Astreinte;
import net.atos.si.ma.mt.astreinte.model.Ressource;

import org.hibernate.Query;
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
						"from Astreinte where dateAstreinte=:dateAstreinte and "
								+ "( hdebut BETWEEN :hdebut and :hfin or"
								+ " hfin BETWEEN :hdebut and :hfin  or "
								+ ":hdebut BETWEEN hdebut and hfin or "
								+ ":hfin BETWEEN hdebut and hfin )")
				.setDate("dateAstreinte", astreinte.getDateAstreinte())
				.setTime("hdebut", astreinte.getHdebut())
				.setTime("hfin", astreinte.getHfin()).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Astreinte> getByRessource(long idressource, long idqc) {
		String squery = "from Astreinte where 1=1 ";
		if (idqc >= 0) {
			squery = squery + " and ticket.id=:idqc";
		}
		if (idressource >= 0) {
			squery = squery + " and ressource.id=:idressource";
		}
		Query query = getCurrentSession().createQuery(squery);
		if (idqc >= 0) {
			query.setLong("idqc", idqc);
		}
		if (idressource >= 0) {
			query.setLong("idressource", idressource);
		}

		return query.list();
	}
}
