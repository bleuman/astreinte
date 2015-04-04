
			package net.atos.si.ma.mt.astreinte.dao.impl;

				import net.atos.si.ma.mt.astreinte.dao.ManagerDAO;
				import net.atos.si.ma.mt.astreinte.model.Manager;
				import net.atos.si.ma.mt.astreinte.model.Manager;

				import org.springframework.stereotype.Repository;

				@Repository
				public class ManagerDAOImpl extends GenericHibernateDAO<Manager> implements
						ManagerDAO {

					public ManagerDAOImpl() {
						super(Manager.class);
					}

					public Manager find(long id) {
						Manager registerFound = (Manager) getCurrentSession().get(
								Manager.class, id);

						return registerFound;
					}

				}
		
	