
			package net.atos.si.ma.mt.astreinte.service.impl;

					import net.atos.si.ma.mt.astreinte.dao.ManagerDAO;
					import net.atos.si.ma.mt.astreinte.model.Manager;
					import net.atos.si.ma.mt.astreinte.model.Manager;
					import net.atos.si.ma.mt.astreinte.service.ManagerService;

					import org.springframework.beans.factory.annotation.Autowired;
					import org.springframework.beans.factory.annotation.Qualifier;
					import org.springframework.stereotype.Service;

					@Service
					public class ManagerServiceImpl extends
							GenericServiceImpl<Manager, ManagerDAO> implements ManagerService {

						@Autowired
						@Qualifier("managerDAOImpl")
						@Override
						public void setDao(ManagerDAO dao) {
							super.setDao(dao);
						}

						public Manager find(long id) {
							Manager objRegister = getDao().find(id);
							return objRegister;
						}

					}

			
		