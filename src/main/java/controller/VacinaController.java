package controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Vacina;
import service.VacinaService;

@Path("/vacina")
public class VacinaController {

	private VacinaService vacinaService = new VacinaService();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Vacina salvarVacinaController(Vacina vacina) {
		return vacinaService.salvarVacinaService(vacina);

	}
	
	@DELETE 
	@Path("/{id}")
	public boolean excluirVacinaController(@PathParam("id")int id) {
		return vacinaService.excluirVacinaService(id);
	}

}
