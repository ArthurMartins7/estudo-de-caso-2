package controller;

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
import model.entity.Aplicacao;
import service.AplicacaoService;

@Path ("/aplicacao")
public class AplicacaoController {
	
	private AplicacaoService aplicacaoService = new AplicacaoService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Aplicacao salvarAplicacao(Aplicacao novaAplicacao) throws ControleVacinasException {
		return aplicacaoService.salvarAplicacaoService(novaAplicacao);
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean atualizar(Aplicacao vacinacaoEditada) throws ControleVacinasException{
		 return aplicacaoService.atualizar(vacinacaoEditada);
	}
	
	
	@DELETE
	@Path("/{id}")
	public boolean excluirAplicacao(@PathParam("id") int id) {
		return aplicacaoService.excluir(id);
	}
	
	@GET
	@Path("/{id}")
	public Aplicacao consultarPorId(@PathParam("id") int id) {
		return aplicacaoService.consultarPorId(id);
	}
	
	@GET
	@Path("/todas")
	public List<Aplicacao> consultarTodasAplicacoes() {
		return aplicacaoService.consultarTodas();
	}
	
	@GET
	@Path("pessoa/{idPessoa}")
	public List<Aplicacao> consultarAplicacaoPorIdPessoa(@PathParam("idPessoa") int idPessoa) {
		return aplicacaoService.consultarPorIdPessoa(idPessoa);
	}
	
	@GET
	@Path("vacina/{id}")
	public List<Aplicacao> consultarAplicacaoPorIdVacina(@PathParam("id") int idVacina) {
		return aplicacaoService.consultarPorIdVacina(idVacina);
	}
	

}
