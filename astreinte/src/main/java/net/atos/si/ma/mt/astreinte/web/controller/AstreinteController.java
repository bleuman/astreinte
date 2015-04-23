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

import net.atos.si.ma.mt.astreinte.model.Astreinte;
import net.atos.si.ma.mt.astreinte.model.RechercheObject;
import net.atos.si.ma.mt.astreinte.model.Ressource;
import net.atos.si.ma.mt.astreinte.model.StatutAstreinte;
import net.atos.si.ma.mt.astreinte.service.AstreinteService;
import net.atos.si.ma.mt.auth2.AllowedRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
@Path("/astreinte")
@AllowedRoles
public class AstreinteController {

	@Autowired
	@Qualifier("astreinteServiceImpl")
	private AstreinteService astreinteService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(@Context HttpHeaders headers, Astreinte astreinte) {
		astreinteService.save(astreinte);
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void delete(@Context HttpHeaders headers, @PathParam("id") Long id) {
		Astreinte astreinte = astreinteService.find(id);
		astreinteService.delete(astreinte);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(@Context HttpHeaders headers, Astreinte astreinte) {
		astreinteService.update(astreinte);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Astreinte> listAll(@Context HttpHeaders headers) {
		List<Astreinte> list = astreinteService.listAll();
		astreinteService.process(list);
		return list;

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/check/")
	public List<Astreinte> checkChevechment(@Context HttpHeaders headers,
			Astreinte astreinte) {
		List<Astreinte> list = astreinteService.checkChevechment(astreinte);
		astreinteService.process(list);
		return list;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("findcritere")
	public List<Astreinte> listByCritere(@Context HttpHeaders headers,
			Astreinte astreinte) {
		List<Astreinte> list = astreinteService.checkChevechment(astreinte);
		astreinteService.process(list);
		return list;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/byressource/")
	public List<Astreinte> getByRessource(@Context HttpHeaders headers,
			RechercheObject rechercheObject) {
		List<Astreinte> list = astreinteService.getByRessource(
				rechercheObject.idressource, rechercheObject.idqc);
		astreinteService.process(list);
		return list;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/statutongoing/")
	public void updateAsONGOING(@Context HttpHeaders headers,
			Astreinte astreinte) {
		astreinteService.changeStaut(astreinte, StatutAstreinte.ONGOING);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/statutvalide/")
	public void updateAsVALID(@Context HttpHeaders headers,
			Astreinte astreinte) {
		astreinteService.changeStaut(astreinte, StatutAstreinte.VALID);
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/statutrejected/")
	public void updateAsREJECTED(@Context HttpHeaders headers,
			Astreinte astreinte) {
		astreinteService.changeStaut(astreinte, StatutAstreinte.REJECTED);
	}

	public AstreinteService getAstreinteService() {
		return astreinteService;
	}

	public void setAstreinteService(AstreinteService astreinteService) {
		this.astreinteService = astreinteService;
	}

}
