
			package net.atos.si.ma.mt.astreinte.service;

				import net.atos.si.ma.mt.astreinte.model.JourFerier;

				import org.springframework.transaction.annotation.Propagation;
				import org.springframework.transaction.annotation.Transactional;

				public interface JourFerierService extends GenericService<JourFerier> {
					@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
					JourFerier find(long id);

				}
		
	