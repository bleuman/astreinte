
			package net.atos.si.ma.mt.astreinte.dao.impl;

				import net.atos.si.ma.mt.astreinte.dao.StatutDAO;
				import net.atos.si.ma.mt.astreinte.model.Statut;
				import net.atos.si.ma.mt.astreinte.model.Statut;

				import org.springframework.stereotype.Repository;

				@Repository
				public class StatutDAOImpl extends GenericHibernateDAO<Statut> implements
						StatutDAO {

					public StatutDAOImpl() {
						super(Statut.class);
					}

					public Statut find(long id) {
						Statut registerFound = (Statut) getCurrentSession().get(
								Statut.class, id);

						return registerFound;
					}

				}
		
	