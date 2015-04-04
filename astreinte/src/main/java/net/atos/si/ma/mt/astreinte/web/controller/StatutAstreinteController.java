
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

				import net.atos.si.ma.mt.astreinte.model.StatutAstreinte;
				import net.atos.si.ma.mt.astreinte.service.StatutAstreinteService;

				import org.springframework.beans.factory.annotation.Autowired;
				import org.springframework.beans.factory.annotation.Qualifier;
				import org.springframework.stereotype.Controller;

				@Controller
				@Path("/statutAstreinte")
				public class StatutAstreinteController {

					@Autowired
					@Qualifier("statutAstreinteServiceImpl")
					private StatutAstreinteService statutAstreinteService;
					
					@POST
					@Consumes(MediaType.APPLICATION_JSON)
					public void save(StatutAstreinte statutAstreinte){
						statutAstreinteService.save(statutAstreinte);
					}
					
					@DELETE
					@Consumes(MediaType.APPLICATION_JSON)
					@Path("/{id}")
					public void delete(@PathParam("id")Long id){
						StatutAstreinte statutAstreinte = statutAstreinteService.find(id);
						statutAstreinteService.delete(statutAstreinte);
					}
					
					@PUT
					@Consumes(MediaType.APPLICATION_JSON)
					public void update(StatutAstreinte statutAstreinte){
						statutAstreinteService.update(statutAstreinte);
					}
					
					@GET
					@Produces(MediaType.APPLICATION_JSON)
					public List<StatutAstreinte> listAll(){
						
						return statutAstreinteService.listAll();
								
					}

					public StatutAstreinteService getStatutAstreinteService() {
						return statutAstreinteService;
					}

					public void setStatutAstreinteService(StatutAstreinteService statutAstreinteService) {
						this.statutAstreinteService = statutAstreinteService;
					}
				}

			
		