package net.atos.si.ma.mt.astreinte.web.controller;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.atos.si.ma.mt.astreinte.model.Astreinte;
import net.atos.si.ma.mt.astreinte.model.Ticket;
import net.atos.si.ma.mt.astreinte.service.AstreinteService;
import net.atos.si.ma.mt.astreinte.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
@Path("/ticket")
public class TicketController {

	@Autowired
	@Qualifier("ticketServiceImpl")
	private TicketService ticketService;

	@Autowired
	@Qualifier("astreinteServiceImpl")
	private AstreinteService astreinteService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(Ticket ticket) {
		ticketService.save(ticket);
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		Ticket ticket = ticketService.find(id);
		ticketService.delete(ticket);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Ticket ticket) {
		ticketService.update(ticket);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ticket> listAll() {
		List<Ticket> tickets=ticketService.listAll();
		for (Ticket ticket : tickets) {
			Set<Astreinte> astreintes = ticket.getAstreintes();
			for (Astreinte astreinte : astreintes) {
				astreinteService.process(astreinte);
			}
			
		}
		return tickets;

	}

	public TicketService getTicketService() {
		return ticketService;
	}

	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}
}
