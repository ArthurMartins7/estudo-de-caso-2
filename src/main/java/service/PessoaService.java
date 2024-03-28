package service;

import java.util.ArrayList;

import Exception.ControleVacinasException;
import model.entity.Pessoa;
import model.repository.AplicacaoRepository;
import model.repository.PessoaRepository;

public class PessoaService {

	private PessoaRepository pessoaRepository = new PessoaRepository();
	private Pessoa pessoaEntity = new Pessoa();
	private AplicacaoRepository aplicacaoRepository = new AplicacaoRepository();

	public Pessoa salvar(Pessoa novaPessoa) throws ControleVacinasException {
		if (this.pessoaRepository.cpfJaUtilizado(novaPessoa.getCpf())) {
			throw new ControleVacinasException("CPF já cadastrado");
		}

		return pessoaRepository.salvar(novaPessoa);
	}

	public boolean excluir(int id) throws ControleVacinasException {
		// TODO pode excluir jogador que já possui partidas?
		if (this.aplicacaoRepository.usuarioJaTomouVacina(id)) {
			throw new ControleVacinasException("Não é possível excluir essa pessoa porque ela já tomou uma vacina!!!");
		}

		return pessoaRepository.excluir(id);
	}

	public ArrayList<Pessoa> listarTodasAsPessoas() {
		return pessoaRepository.consultarTodos();

	}

	public Pessoa consultarPessoaPorIDService(int id) {
		return pessoaRepository.consultarPorId(id);

	}
	

}
