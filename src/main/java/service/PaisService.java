package service;

import model.entity.Pais;
import model.repository.PaisRepository;

public class PaisService {
	
	private PaisRepository novoPais = new PaisRepository();
	
	public Pais salvarPaisService(Pais pais) {
		return novoPais.salvarPaisRepository(pais);
		
	}
	
	public Pais consultarPaisPorIdService(int id) {
		return novoPais.consultarPorId(id);
		
	}

}
