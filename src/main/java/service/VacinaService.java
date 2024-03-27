package service;

import java.util.ArrayList;

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
	
	public ArrayList<Vacina> listarTodasAsVacinasService() {
		return vacina.consultarTodos();
		
	}
	
	public Vacina consultarPorIdService(int id) {
		return vacina.consultarPorId(id);
		
	}

}
