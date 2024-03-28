package controller;

import Exception.ControleVacinasException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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
	
	public Aplicacao salvarAplicacaoController(Aplicacao novaAplicacao) throws ControleVacinasException {
		return aplicacaoService.salvarAplicacaoService(novaAplicacao);
		
	}
	
	
	
	
	

}
