package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Exception.ControleVacinasException;
import model.entity.Aplicacao;
import model.entity.Pessoa;
import model.entity.Vacina;
import model.repository.AplicacaoRepository;
import model.repository.PessoaRepository;
import model.repository.VacinaRepository;

public class AplicacaoService {
	
	private static final int NOTA_MAXIMA = 5;
	
	private AplicacaoRepository aplicacaoRepository = new AplicacaoRepository();
	
	public Aplicacao salvarAplicacaoService(Aplicacao aplicacao) throws ControleVacinasException {
			validarDadosVacinacao(aplicacao);
			atualizarMediaVacina(aplicacao);
			validarAcessoPessoaParaReceberVacina(aplicacao);
		
		return aplicacaoRepository.salvar(aplicacao) ;
		
	}
	
	public boolean atualizar(Aplicacao vacinacaoEditada) throws ControleVacinasException {
		validarDadosVacinacao(vacinacaoEditada);
		atualizarMediaVacina(vacinacaoEditada);
		validarAcessoPessoaParaReceberVacina(vacinacaoEditada);
		
		return aplicacaoRepository.alterar(vacinacaoEditada);
	}
	
	public boolean excluir(int id ) {
		return aplicacaoRepository.excluir(id);
	}

	public Aplicacao consultarPorId(int id) {
		return aplicacaoRepository.consultarPorId(id);
	}

	public List<Aplicacao> consultarTodas() {
		return aplicacaoRepository.consultarTodos();
	}

	public List<Aplicacao> consultarPorIdPessoa(int idPessoa) {
		return aplicacaoRepository.consultarPorIdPessoa(idPessoa);
				
	}
	
	public List<Aplicacao> consultarPorIdVacina(int idVacina) {
		return aplicacaoRepository.consultarPorIdVacina(idVacina);
				
	}
	
	private void validarAcessoPessoaParaReceberVacina(Aplicacao vacinacao) throws ControleVacinasException {
		Vacina vac = vacinacao.getVacinaAplicada();
		Pessoa paciente = new PessoaRepository().consultarPorId(vacinacao.getIdPessoa());
		
		boolean podeReceberDose = false;
		if(vac.getEstagioDaPesquisa() == Vacina.ESTAGIO_INICIAL && paciente.getTipoDePessoa() == Pessoa.PESQUISADOR) {
			podeReceberDose = true;
		}
		
		if(vac.getEstagioDaPesquisa() == Vacina.ESTAGIO_TESTES && 
				(paciente.getTipoDePessoa() == Pessoa.PESQUISADOR || paciente.getTipoDePessoa() == Pessoa.VOLUNTARIO)) {
			podeReceberDose = true;
		}
		
		if(vac.getEstagioDaPesquisa() == Vacina.ESTAGIO_APLICACAO_MASSA) {
			podeReceberDose = true;
		}
		
		if(!podeReceberDose) {
			throw new ControleVacinasException("Usuário sem permissão para receber a vacina");
		}
	}
	
	private void atualizarMediaVacina(Aplicacao novaVacinacao) {
		ArrayList<Aplicacao> dosesAplicadas = aplicacaoRepository.consultarPorIdVacina(novaVacinacao.getVacinaAplicada().getId());
		
		double novaMediaVacina = 0;
		double somatorioNotasDaVacina = 0;
		
		for(Aplicacao dose: dosesAplicadas) {
			somatorioNotasDaVacina += dose.getAvaliacao();
		}
		
		novaMediaVacina = (somatorioNotasDaVacina + novaVacinacao.getAvaliacao()) / (dosesAplicadas.size() + 1);
		
		VacinaRepository vacinaRepository = new VacinaRepository();
		Vacina vacinaAplicada = vacinaRepository.consultarPorId(novaVacinacao.getVacinaAplicada().getId());
		vacinaAplicada.setMedia(novaMediaVacina);
		
		vacinaRepository.alterar(vacinaAplicada);
		
		novaVacinacao.setVacinaAplicada(vacinaAplicada);
	}
	
	private void validarDadosVacinacao(Aplicacao novaVacinacao) throws ControleVacinasException {
		if(novaVacinacao.getIdPessoa() == 0 
				|| novaVacinacao.getVacinaAplicada() == null
				|| novaVacinacao.getVacinaAplicada().getId() == 0) {
			throw new ControleVacinasException("Informe a o id da pessoa e a vacina da aplicação");
		}
		
		novaVacinacao.setDataDaAplicacao(LocalDate.now());
		
		if(novaVacinacao.getAvaliacao() == 0) {
			novaVacinacao.setAvaliacao(NOTA_MAXIMA);
		}
	}
}
