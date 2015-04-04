
			package net.atos.si.ma.mt.astreinte.dao.impl;

				import net.atos.si.ma.mt.astreinte.dao.StatutAstreinteDAO;
				import net.atos.si.ma.mt.astreinte.model.StatutAstreinte;
				import net.atos.si.ma.mt.astreinte.model.StatutAstreinte;

				import org.springframework.stereotype.Repository;

				@Repository
				public class StatutAstreinteDAOImpl extends GenericHibernateDAO<StatutAstreinte> implements
						StatutAstreinteDAO {

					public StatutAstreinteDAOImpl() {
						super(StatutAstreinte.class);
					}

					public StatutAstreinte find(long id) {
						StatutAstreinte registerFound = (StatutAstreinte) getCurrentSession().get(
								StatutAstreinte.class, id);

						return registerFound;
					}

				}
		
	