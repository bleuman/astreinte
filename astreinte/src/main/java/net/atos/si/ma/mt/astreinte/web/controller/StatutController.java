
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

				import net.atos.si.ma.mt.astreinte.model.Statut;
				import net.atos.si.ma.mt.astreinte.service.StatutService;

				import org.springframework.beans.factory.annotation.Autowired;
				import org.springframework.beans.factory.annotation.Qualifier;
				import org.springframework.stereotype.Controller;

				@Controller
				@Path("/statut")
				public class StatutController {

					@Autowired
					@Qualifier("statutServiceImpl")
					private StatutService statutService;
					
					@POST
					@Consumes(MediaType.APPLICATION_JSON)
					public void save(Statut statut){
						statutService.save(statut);
					}
					
					@DELETE
					@Consumes(MediaType.APPLICATION_JSON)
					@Path("/{id}")
					public void delete(@PathParam("id")Long id){
						Statut statut = statutService.find(id);
						statutService.delete(statut);
					}
					
					@PUT
					@Consumes(MediaType.APPLICATION_JSON)
					public void update(Statut statut){
						statutService.update(statut);
					}
					
					@GET
					@Produces(MediaType.APPLICATION_JSON)
					public List<Statut> listAll(){
						
						return statutService.listAll();
								
					}

					public StatutService getStatutService() {
						return statutService;
					}

					public void setStatutService(StatutService statutService) {
						this.statutService = statutService;
					}
				}

			
		