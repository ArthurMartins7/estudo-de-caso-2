package service;

import java.util.ArrayList;

import Exception.ControleVacinasException;
import model.entity.Pessoa;
import model.repository.PessoaRepository;

public class PessoaService {

	private PessoaRepository pessoaRepository = new PessoaRepository();
	
	public Pessoa salvar(Pessoa novaPessoa) throws ControleVacinasException{
		if(this.pessoaRepository.cpfJaUtilizado(novaPessoa.getCpf())) {
			throw new ControleVacinasException("CPF já cadastrado");
		}
		
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
