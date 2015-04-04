package net.atos.si.ma.mt.astreinte.dao.impl;

import java.util.Date;
import java.util.List;

import net.atos.si.ma.mt.astreinte.dao.JourFerierDAO;
import net.atos.si.ma.mt.astreinte.model.JourFerier;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JourFerierDAOImpl extends GenericHibernateDAO<JourFerier>
		implements JourFerierDAO {

	public JourFerierDAOImpl() {
		super(JourFerier.class);
	}

	public JourFerier find(long id) {
		JourFerier registerFound = (JourFerier) getCurrentSession().get(
				JourFerier.class, id);

		return registerFound;
	}

	public boolean isJourFerier(Date mdate) {
		Query tquery = getCurrentSession().createQuery(
				"from JourFerier where dateJour=:dateJour");
		List list = tquery.setDate("dateJour", mdate).list();
		if (list == null || list.isEmpty())
			return false;
		else
			return true;
	}
}
