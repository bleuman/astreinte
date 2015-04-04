
			package net.atos.si.ma.mt.astreinte.service;

				import net.atos.si.ma.mt.astreinte.model.TypeAstreinte;

				import org.springframework.transaction.annotation.Propagation;
				import org.springframework.transaction.annotation.Transactional;

				public interface TypeAstreinteService extends GenericService<TypeAstreinte> {
					@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
					TypeAstreinte find(long id);

				}
		
	