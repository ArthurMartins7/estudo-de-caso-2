package service;

import java.util.ArrayList;
import java.util.List;

import Exception.ControleVacinasException;
import model.entity.Vacina;
import model.repository.AplicacaoRepository;
import model.repository.VacinaRepository;
import model.seletor.VacinaSeletor;

public class VacinaService {
	
	VacinaRepository vacina = new VacinaRepository();
	AplicacaoRepository aplicacaoRepository = new AplicacaoRepository();
	
	public Vacina salvarVacinaService(Vacina novaVacina) {
		return vacina.salvar(novaVacina);
		
	}
	
	public boolean excluirVacinaService(int id) throws ControleVacinasException {
		if (this.aplicacaoRepository.vacinaJaAplicada(id)) {
			throw new ControleVacinasException("NÃO É POSSÍVEL EXCLUIR PORQUE ESSA VACINA JÁ FOI APLICADA!!!");
		}
		return vacina.excluir(id);
		
	}
	
	public ArrayList<Vacina> listarTodasAsVacinasService() {
		return vacina.consultarTodos();
		
	}
	
	public Vacina consultarPorIdService(int id) {
		return vacina.consultarPorId(id);
		
	}
	
	public boolean atualizar(Vacina vacinaEditada) {
		return vacina.alterar(vacinaEditada);
	}

	public List<Vacina> consultarComFiltros(VacinaSeletor seletor) {
		return vacina.consultarComFiltros(seletor);
	}
}
