package net.atos.si.ma.mt.astreinte.service;

import java.util.List;

import net.atos.si.ma.mt.astreinte.model.Astreinte;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface AstreinteService extends GenericService<Astreinte> {
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Astreinte find(long id);

	void process(List<Astreinte> astreintes);

	void process(Astreinte astreinte);
	
	public List<Astreinte> checkChevechment(Astreinte astreinte) ;
}
