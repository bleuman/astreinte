
			package net.atos.si.ma.mt.astreinte.service;

				import net.atos.si.ma.mt.astreinte.model.Manager;

				import org.springframework.transaction.annotation.Propagation;
				import org.springframework.transaction.annotation.Transactional;

				public interface ManagerService extends GenericService<Manager> {
					@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
					Manager find(long id);

				}
		
	