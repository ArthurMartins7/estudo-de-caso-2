package service;

import java.util.ArrayList;

import model.entity.Pessoa;
import model.repository.PessoaRepository;

public class PessoaService {
	

	private PessoaRepository pessoaRepository = new PessoaRepository();
	
	public Pessoa salvar(Pessoa novaPessoa){
		return pessoaRepository.salvar(novaPessoa);
	}
	
	public boolean excluir(int id) {
		//TODO pode excluir jogador que já possui partidas?
		return pessoaRepository.excluir(id);
	}
	
	public ArrayList<Pessoa> listarTodasAsPessoas() {
		
		return pessoaRepository.consultarTodos();
		
	}
}
