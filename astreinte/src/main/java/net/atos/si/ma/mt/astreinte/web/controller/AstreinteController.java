package net.atos.si.ma.mt.astreinte.web.controller;

import java.util.List;

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
import net.atos.si.ma.mt.astreinte.model.Ressource;
import net.atos.si.ma.mt.astreinte.service.AstreinteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
@Path("/astreinte")
public class AstreinteController {

	@Autowired
	@Qualifier("astreinteServiceImpl")
	private AstreinteService astreinteService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(Astreinte astreinte) {
		astreinteService.save(astreinte);
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		Astreinte astreinte = astreinteService.find(id);
		astreinteService.delete(astreinte);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Astreinte astreinte) {
		astreinteService.update(astreinte);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Astreinte> listAll() {
		List<Astreinte> list = astreinteService.listAll();
		astreinteService.process(list);
		return list;

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("check")
	public List<Astreinte> checkChevechment(Astreinte astreinte) {
		return astreinteService.checkChevechment(astreinte);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("findcritere")
	public List<Astreinte> listByCritere(Astreinte astreinte) {
		return astreinteService.checkChevechment(astreinte);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("byressource")
	public List<Astreinte> getByRessource(Ressource ressource) {
		return astreinteService.getByRessource(ressource);
	}

	public AstreinteService getAstreinteService() {
		return astreinteService;
	}

	public void setAstreinteService(AstreinteService astreinteService) {
		this.astreinteService = astreinteService;
	}
}
