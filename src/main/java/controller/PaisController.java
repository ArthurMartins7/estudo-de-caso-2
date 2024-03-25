package controller;



import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Pais;
import service.PaisService;

@Path("/pais")
public class PaisController {
	
	
	private PaisService novoPais = new PaisService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pais salvarPaisController(Pais pais) {
		return novoPais.salvarPaisService(pais);
		
	}
	@Path("/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pais consultarPaisPorIdController(@PathParam("id") int id) {
		return novoPais.consultarPaisPorIdService(id);
		
	}
}
