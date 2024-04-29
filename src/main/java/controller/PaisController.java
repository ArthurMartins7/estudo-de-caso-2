package controller;



import java.util.ArrayList;

import Exception.ControleVacinasException;
import jakarta.validation.constraints.AssertFalse.List;
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
	
	
	private PaisService paisService = new PaisService();
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pais salvarPaisController(Pais pais) throws ControleVacinasException{
		return paisService.salvarPaisService(pais);
		
	}
	
	@Path("/{id}")
	@GET
	public Pais consultarPaisPorIdController(@PathParam("id") int id) {
		return paisService.consultarPaisPorIdService(id);
		
	}
	
	@Path("/todos")
	@GET
	public ArrayList<Pais> consultarTodos() {
		return paisService.consultarTodos();
	}
}
