package net.atos.si.ma.mt.astreinte.service;

import java.util.List;

import net.atos.si.ma.mt.astreinte.model.Ticket;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TicketService extends GenericService<Ticket> {
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Ticket find(long id);
	
	List<Ticket> recherche(String qc);

}
