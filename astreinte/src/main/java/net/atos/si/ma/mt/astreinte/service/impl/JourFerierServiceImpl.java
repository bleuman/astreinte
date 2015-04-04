
			package net.atos.si.ma.mt.astreinte.service.impl;

					import net.atos.si.ma.mt.astreinte.dao.JourFerierDAO;
					import net.atos.si.ma.mt.astreinte.model.JourFerier;
					import net.atos.si.ma.mt.astreinte.model.JourFerier;
					import net.atos.si.ma.mt.astreinte.service.JourFerierService;

					import org.springframework.beans.factory.annotation.Autowired;
					import org.springframework.beans.factory.annotation.Qualifier;
					import org.springframework.stereotype.Service;

					@Service
					public class JourFerierServiceImpl extends
							GenericServiceImpl<JourFerier, JourFerierDAO> implements JourFerierService {

						@Autowired
						@Qualifier("jourFerierDAOImpl")
						@Override
						public void setDao(JourFerierDAO dao) {
							super.setDao(dao);
						}

						public JourFerier find(long id) {
							JourFerier objRegister = getDao().find(id);
							return objRegister;
						}

					}

			
		