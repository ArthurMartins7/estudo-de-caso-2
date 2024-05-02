package controller;

import java.util.ArrayList;
import java.util.List;

import Exception.ControleVacinasException;
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
import model.seletor.VacinaSeletor;
import service.VacinaService;

@Path("/vacina")
public class VacinaController {

	private VacinaService vacinaService = new VacinaService();
	
	@POST
	@Path("/filtro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vacina> consultarComFiltros(VacinaSeletor seletor){
		 return vacinaService.consultarComFiltros(seletor);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacina salvarVacina(Vacina vacina) {
		return vacinaService.salvarVacinaService(vacina);

	}
	

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean atualizar(Vacina vacinaEditada){
		 return vacinaService.atualizar(vacinaEditada);
	}
	
	@DELETE 
	@Path("/excluir/{id}")
	public boolean excluirVacina(@PathParam("id")int id) throws ControleVacinasException {
		return vacinaService.excluirVacinaService(id);
	}
	
	
	@GET
	@Path("/{id}")
	public Vacina consultarVacinaPorID(@PathParam("id")int id) {
		return vacinaService.consultarPorIdService(id);
		
	}
	

	@GET
	@Path("/todas")
	public ArrayList<Vacina> listarTodasAsVacinas() {
		return vacinaService.listarTodasAsVacinasService();
		
	}

}
