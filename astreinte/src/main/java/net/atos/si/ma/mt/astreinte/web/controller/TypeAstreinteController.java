
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

				import net.atos.si.ma.mt.astreinte.model.TypeAstreinte;
				import net.atos.si.ma.mt.astreinte.service.TypeAstreinteService;

				import org.springframework.beans.factory.annotation.Autowired;
				import org.springframework.beans.factory.annotation.Qualifier;
				import org.springframework.stereotype.Controller;

				@Controller
				@Path("/typeAstreinte")
				public class TypeAstreinteController {

					@Autowired
					@Qualifier("typeAstreinteServiceImpl")
					private TypeAstreinteService typeAstreinteService;
					
					@POST
					@Consumes(MediaType.APPLICATION_JSON)
					public void save(TypeAstreinte typeAstreinte){
						typeAstreinteService.save(typeAstreinte);
					}
					
					@DELETE
					@Consumes(MediaType.APPLICATION_JSON)
					@Path("/{id}")
					public void delete(@PathParam("id")Long id){
						TypeAstreinte typeAstreinte = typeAstreinteService.find(id);
						typeAstreinteService.delete(typeAstreinte);
					}
					
					@PUT
					@Consumes(MediaType.APPLICATION_JSON)
					public void update(TypeAstreinte typeAstreinte){
						typeAstreinteService.update(typeAstreinte);
					}
					
					@GET
					@Produces(MediaType.APPLICATION_JSON)
					public List<TypeAstreinte> listAll(){
						
						return typeAstreinteService.listAll();
								
					}

					public TypeAstreinteService getTypeAstreinteService() {
						return typeAstreinteService;
					}

					public void setTypeAstreinteService(TypeAstreinteService typeAstreinteService) {
						this.typeAstreinteService = typeAstreinteService;
					}
				}

			
		