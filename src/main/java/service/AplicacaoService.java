package service;

import model.entity.Aplicacao;
import model.repository.AplicacaoRepository;

public class AplicacaoService {
	
	private AplicacaoRepository aplicacaoRepository = new AplicacaoRepository();
	public Aplicacao salvarAplicacaoService(Aplicacao aplicacao) {
		return aplicacaoRepository.salvar(aplicacao) ;
		
	}
	

}
