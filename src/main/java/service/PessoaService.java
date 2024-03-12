package service;

import model.entity.Pessoa;
import model.repository.PessoaRepository;

public class PessoaService {
	

	private PessoaRepository pessoaRepository = new PessoaRepository();
	
	public Pessoa salvar(Pessoa novaPessoa){
		return pessoaRepository.salvar(novaPessoa);
	}
}