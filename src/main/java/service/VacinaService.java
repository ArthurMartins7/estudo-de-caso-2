package service;

import model.entity.Vacina;
import model.repository.VacinaRepository;

public class VacinaService {
	
	VacinaRepository vacina = new VacinaRepository();
	
	public Vacina salvarVacinaService(Vacina novaVacina) {
		return vacina.salvar(novaVacina);
		
	}
	
	public boolean excluirVacinaService(int id) {
		return vacina.excluir(id);
		
	}

}
