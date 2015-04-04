
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

				import net.atos.si.ma.mt.astreinte.model.JourFerier;
				import net.atos.si.ma.mt.astreinte.service.JourFerierService;

				import org.springframework.beans.factory.annotation.Autowired;
				import org.springframework.beans.factory.annotation.Qualifier;
				import org.springframework.stereotype.Controller;

				@Controller
				@Path("/jourFerier")
				public class JourFerierController {

					@Autowired
					@Qualifier("jourFerierServiceImpl")
					private JourFerierService jourFerierService;
					
					@POST
					@Consumes(MediaType.APPLICATION_JSON)
					public void save(JourFerier jourFerier){
						jourFerierService.save(jourFerier);
					}
					
					@DELETE
					@Consumes(MediaType.APPLICATION_JSON)
					@Path("/{id}")
					public void delete(@PathParam("id")Long id){
						JourFerier jourFerier = jourFerierService.find(id);
						jourFerierService.delete(jourFerier);
					}
					
					@PUT
					@Consumes(MediaType.APPLICATION_JSON)
					public void update(JourFerier jourFerier){
						jourFerierService.update(jourFerier);
					}
					
					@GET
					@Produces(MediaType.APPLICATION_JSON)
					public List<JourFerier> listAll(){
						
						return jourFerierService.listAll();
								
					}

					public JourFerierService getJourFerierService() {
						return jourFerierService;
					}

					public void setJourFerierService(JourFerierService jourFerierService) {
						this.jourFerierService = jourFerierService;
					}
				}

			
		