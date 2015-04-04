
			package net.atos.si.ma.mt.astreinte.service.impl;

					import net.atos.si.ma.mt.astreinte.dao.StatutDAO;
					import net.atos.si.ma.mt.astreinte.model.Statut;
					import net.atos.si.ma.mt.astreinte.model.Statut;
					import net.atos.si.ma.mt.astreinte.service.StatutService;

					import org.springframework.beans.factory.annotation.Autowired;
					import org.springframework.beans.factory.annotation.Qualifier;
					import org.springframework.stereotype.Service;

					@Service
					public class StatutServiceImpl extends
							GenericServiceImpl<Statut, StatutDAO> implements StatutService {

						@Autowired
						@Qualifier("statutDAOImpl")
						@Override
						public void setDao(StatutDAO dao) {
							super.setDao(dao);
						}

						public Statut find(long id) {
							Statut objRegister = getDao().find(id);
							return objRegister;
						}

					}

			
		