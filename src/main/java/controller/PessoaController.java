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
import model.seletor.PessoaSeletor;
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
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean atualizar(Pessoa pessoaEditada) throws ControleVacinasException{
		 return pessoaService.atualizar(pessoaEditada);
	}
	
	@DELETE
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id) throws ControleVacinasException{
		 return pessoaService.excluir(id);
	}
	
	@GET
	@Path("/todas")
	public ArrayList<Pessoa> listarTodasPessoas() {
		return pessoaService.listarTodasAsPessoas();
		
	}
	
	@GET
	@Path("/{id}")
	public Pessoa consultarPessoaPorId(@PathParam("id") int id) {
		return pessoaService.consultarPessoaPorIDService(id);
		
	}
	
	@GET
	@Path("/pesquisadores")
	public List<Pessoa> consultarTodosPesquisadores() {
		return pessoaService.consultarPesquisadores();
	}
	
	@POST
	@Path("/filtro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList <Pessoa> consultarComSeletor (PessoaSeletor seletor) {
		return this.pessoaService.consultarComSeletor(seletor);
		
	}
	
}