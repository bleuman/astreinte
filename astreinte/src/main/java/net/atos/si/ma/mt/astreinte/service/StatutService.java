
			package net.atos.si.ma.mt.astreinte.service;

				import net.atos.si.ma.mt.astreinte.model.Statut;

				import org.springframework.transaction.annotation.Propagation;
				import org.springframework.transaction.annotation.Transactional;

				public interface StatutService extends GenericService<Statut> {
					@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
					Statut find(long id);

				}
		
	