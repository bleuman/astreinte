package net.atos.si.ma.mt.astreinte.web.controller;

import java.util.Date;
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
import net.atos.si.ma.mt.astreinte.model.Manager;
import net.atos.si.ma.mt.astreinte.model.StatutAstreinte;
import net.atos.si.ma.mt.astreinte.service.AstreinteService;
import net.atos.si.ma.mt.astreinte.service.ManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
@Path("/init")
public class InitController {

	@Autowired
	@Qualifier("astreinteServiceImpl")
	private AstreinteService astreinteService;
	
	@Autowired
	@Qualifier("managerServiceImpl")
	private ManagerService managerService;
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Astreinte> initDB(){
//		Manager manager = new Manager();
//		manager.setDas("xxxxx");
//		manager.setRole("CP");
//		managerService.save(manager);
		
		Astreinte astreinte = new Astreinte();
		StatutAstreinte statutAstreinte = new StatutAstreinte();
		statutAstreinte.setId(1);
		astreinte.setStatutAstreinte(statutAstreinte);
		astreinte.setDateAstreinte(new Date());
		//astreinte.setHdebut(new Date());
		//astreinte.setHfin(new Date());
		
		
		astreinteService.save(astreinte);
		
		return astreinteService.listAll();
				
	}

	public AstreinteService getAstreinteService() {
		return astreinteService;
	}

	public void setAstreinteService(AstreinteService astreinteService) {
		this.astreinteService = astreinteService;
	}
}
