
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

				import net.atos.si.ma.mt.astreinte.model.Manager;
				import net.atos.si.ma.mt.astreinte.service.ManagerService;

				import org.springframework.beans.factory.annotation.Autowired;
				import org.springframework.beans.factory.annotation.Qualifier;
				import org.springframework.stereotype.Controller;

				@Controller
				@Path("/manager")
				public class ManagerController {

					@Autowired
					@Qualifier("managerServiceImpl")
					private ManagerService managerService;
					
					@POST
					@Consumes(MediaType.APPLICATION_JSON)
					public void save(Manager manager){
						managerService.save(manager);
					}
					
					@DELETE
					@Consumes(MediaType.APPLICATION_JSON)
					@Path("/{id}")
					public void delete(@PathParam("id")Long id){
						Manager manager = managerService.find(id);
						managerService.delete(manager);
					}
					
					@PUT
					@Consumes(MediaType.APPLICATION_JSON)
					public void update(Manager manager){
						managerService.update(manager);
					}
					
					@GET
					@Produces(MediaType.APPLICATION_JSON)
					public List<Manager> listAll(){
						
						return managerService.listAll();
								
					}

					public ManagerService getManagerService() {
						return managerService;
					}

					public void setManagerService(ManagerService managerService) {
						this.managerService = managerService;
					}
				}

			
		