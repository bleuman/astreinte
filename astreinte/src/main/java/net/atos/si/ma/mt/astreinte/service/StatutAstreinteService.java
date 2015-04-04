
			package net.atos.si.ma.mt.astreinte.service;

				import net.atos.si.ma.mt.astreinte.model.StatutAstreinte;

				import org.springframework.transaction.annotation.Propagation;
				import org.springframework.transaction.annotation.Transactional;

				public interface StatutAstreinteService extends GenericService<StatutAstreinte> {
					@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
					StatutAstreinte find(long id);

				}
		
	