package service;

import Exception.ControleVacinasException;
import model.entity.Aplicacao;
import model.entity.Pessoa;
import model.entity.Vacina;
import model.repository.AplicacaoRepository;
import model.repository.PessoaRepository;

public class AplicacaoService {
	
	private AplicacaoRepository aplicacaoRepository = new AplicacaoRepository();
	
	public Aplicacao salvarAplicacaoService(Aplicacao aplicacao) throws ControleVacinasException {
		PessoaRepository pessoa = new PessoaRepository();
		Pessoa pessoaEntity = null;
		Vacina vacina = new Vacina();
			pessoaEntity = pessoa.consultarPorId(aplicacao.getId());
			
			if(pessoaEntity.getTipoDePessoa() == 2  && vacina.getEstagioDaPesquisa() == 1 ) {
				throw new ControleVacinasException("Tipo de pessoa voluntária (2) não pode receber vacina em teste inicial (1)");
				
			} else if (pessoaEntity.getTipoDePessoa() == 3 && vacina.getEstagioDaPesquisa() == 1 && vacina.getEstagioDaPesquisa() == 2) {
				throw new ControleVacinasException("Tipo de pessoa publico geral (3) não pode receber vacina inicial (1) e vacina em testes (2) ");
			}
		
		return aplicacaoRepository.salvar(aplicacao) ;
		
	}
	

	

}
