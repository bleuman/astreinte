
			package net.atos.si.ma.mt.astreinte.dao;

import net.atos.si.ma.mt.astreinte.model.Ticket;

public interface TicketDAO extends GenericDAO<Ticket> {

	Ticket find(long id);

}		
