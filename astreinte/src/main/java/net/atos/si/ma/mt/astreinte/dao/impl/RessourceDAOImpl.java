package net.atos.si.ma.mt.astreinte.dao.impl;

import java.util.List;

import net.atos.si.ma.mt.astreinte.dao.RessourceDAO;
import net.atos.si.ma.mt.astreinte.model.Ressource;
import net.atos.si.ma.mt.auth2.LoginService;
import net.atos.si.ma.mt.auth2.Principale;

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

	@Override
	public Principale checkLogin(String login, String password) {
		List<Ressource> result = getCurrentSession()
				.createQuery("from Ressource Where login=:login")
				.setString("login", login).list();
		if (result == null || result.isEmpty())
			return null;
		else {

			Ressource registerFound = (Ressource) result.get(0);
			if (registerFound != null
					&& registerFound.getPassword().equals(password)){
				Principale principale = new Principale(registerFound.getLogin(),registerFound.getRole(),null,registerFound.getId());
				
				return principale;
			}
			else

				return null;
		}
	}
}
