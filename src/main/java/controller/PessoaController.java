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
import model.entity.Pessoa;
import service.PessoaService;

@Path("/pessoa")
public class PessoaController {

	private PessoaService pessoaService = new PessoaService();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa salvar(Pessoa pessoa) throws ControleVacinasException {
		return pessoaService.salvar(pessoa);
	}
	
	@DELETE
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id){
		 return pessoaService.excluir(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Pessoa> listarPessoasController() {
		return pessoaService.listarTodasAsPessoas();
		
	}
	
	@GET
	@Path("/consultar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa consultarPessoaPorIDController(@PathParam("id") int id) {
		return pessoaService.consultarPessoaPorIDService(id);
		
	}
	
}