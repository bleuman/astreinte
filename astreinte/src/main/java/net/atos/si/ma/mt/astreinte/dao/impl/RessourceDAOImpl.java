package net.atos.si.ma.mt.astreinte.dao.impl;

import java.util.List;

import net.atos.si.ma.mt.astreinte.dao.RessourceDAO;
import net.atos.si.ma.mt.astreinte.model.Ressource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional 
public class RessourceDAOImpl extends GenericHibernateDAO<Ressource> implements
		RessourceDAO {

	public RessourceDAOImpl() {
		super(Ressource.class);
	}

	public Ressource find(long id) {
		Ressource registerFound = (Ressource) getCurrentSession().get(
				Ressource.class, id);

		return registerFound;
	}

	public boolean checklogin(String login, String password) {
		List<Ressource> result = getCurrentSession()
				.createQuery("from Ressource Where login=:login")
				.setString("login", login).list();
		if (result == null || result.isEmpty())
			return false;
		else {

			Ressource registerFound = (Ressource) result.get(0);
			if (registerFound != null
					&& registerFound.getPassword().equals(password))
				return true;
			else

				return false;
		}
	}
}
