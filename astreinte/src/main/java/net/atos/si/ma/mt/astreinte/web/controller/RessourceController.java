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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import net.atos.si.ma.mt.astreinte.model.Ressource;
import net.atos.si.ma.mt.astreinte.service.RessourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
@Path("/ressource")
public class RessourceController {

	@Autowired
	@Qualifier("ressourceServiceImpl")
	private RessourceService ressourceService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(Ressource ressource) {
		ressourceService.save(ressource);
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		Ressource ressource = ressourceService.find(id);
		ressourceService.delete(ressource);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Ressource ressource) {
		ressourceService.update(ressource);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ressource> listAll(@Context HttpHeaders headers) {
		return ressourceService.listAll();

	}

	

	public RessourceService getRessourceService() {
		return ressourceService;
	}

	public void setRessourceService(RessourceService ressourceService) {
		this.ressourceService = ressourceService;
	}
}
