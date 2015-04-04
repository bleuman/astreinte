
			package net.atos.si.ma.mt.astreinte.service.impl;

					import net.atos.si.ma.mt.astreinte.dao.StatutAstreinteDAO;
					import net.atos.si.ma.mt.astreinte.model.StatutAstreinte;
					import net.atos.si.ma.mt.astreinte.model.StatutAstreinte;
					import net.atos.si.ma.mt.astreinte.service.StatutAstreinteService;

					import org.springframework.beans.factory.annotation.Autowired;
					import org.springframework.beans.factory.annotation.Qualifier;
					import org.springframework.stereotype.Service;

					@Service
					public class StatutAstreinteServiceImpl extends
							GenericServiceImpl<StatutAstreinte, StatutAstreinteDAO> implements StatutAstreinteService {

						@Autowired
						@Qualifier("statutAstreinteDAOImpl")
						@Override
						public void setDao(StatutAstreinteDAO dao) {
							super.setDao(dao);
						}

						public StatutAstreinte find(long id) {
							StatutAstreinte objRegister = getDao().find(id);
							return objRegister;
						}

					}

			
		