
			package net.atos.si.ma.mt.astreinte.dao.impl;

				import net.atos.si.ma.mt.astreinte.dao.TypeAstreinteDAO;
				import net.atos.si.ma.mt.astreinte.model.TypeAstreinte;
				import net.atos.si.ma.mt.astreinte.model.TypeAstreinte;

				import org.springframework.stereotype.Repository;

				@Repository
				public class TypeAstreinteDAOImpl extends GenericHibernateDAO<TypeAstreinte> implements
						TypeAstreinteDAO {

					public TypeAstreinteDAOImpl() {
						super(TypeAstreinte.class);
					}

					public TypeAstreinte find(long id) {
						TypeAstreinte registerFound = (TypeAstreinte) getCurrentSession().get(
								TypeAstreinte.class, id);

						return registerFound;
					}

				}
		
	