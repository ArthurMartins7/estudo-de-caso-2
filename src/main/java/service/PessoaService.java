package service;

import java.util.ArrayList;
import java.util.List;

import Exception.ControleVacinasException;
import model.entity.Pessoa;
import model.repository.AplicacaoRepository;
import model.repository.PessoaRepository;

public class PessoaService {

	private PessoaRepository pessoaRepository = new PessoaRepository();
	private Pessoa pessoaEntity = new Pessoa();
	private AplicacaoRepository aplicacaoRepository = new AplicacaoRepository();

	public Pessoa salvar(Pessoa novaPessoa) throws ControleVacinasException {
		
		validarCamposObrigatorios(novaPessoa);
		
		if (this.pessoaRepository.cpfJaUtilizado(novaPessoa.getCpf())) {
			throw new ControleVacinasException("CPF já cadastrado");
		}

		return pessoaRepository.salvar(novaPessoa);
	}
	
	public boolean atualizar(Pessoa pessoaEditada) throws ControleVacinasException {
		validarCamposObrigatorios(pessoaEditada);
		
		//TODO porque não valido o CPF? Veremos em sala
		
		return pessoaRepository.alterar(pessoaEditada);
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
	
	private void validarCpf(Pessoa novaPessoa) throws ControleVacinasException {
		if(pessoaRepository.cpfJaUtilizado(novaPessoa.getCpf())) {
			throw new ControleVacinasException("CPF " + novaPessoa.getCpf() + " já cadastrado "); 
		}
	}
	
	private void validarCamposObrigatorios(Pessoa p) throws ControleVacinasException {
		String mensagemValidacao = "";
		if(p.getNome() == null || p.getNome().isEmpty()) {
			mensagemValidacao += " - informe o nome \n";
		}
		if(p.getDataNascimento() == null) {
			mensagemValidacao += " - informe a data de nascimento \n";
		}
		if(p.getCpf() == null || p.getCpf().isEmpty() || p.getCpf().length() != 11) {
			mensagemValidacao += " - informe o CPF";
		}
		if(p.getSexo() == null) {
			mensagemValidacao += " - informe o sexo";
		}
		if(p.getTipoDePessoa() < 1 || p.getTipoDePessoa() > 3) {
			mensagemValidacao += " - informe o tipo (entre 1 e 3)";
		}
		if(p.getPais() == null) {
			mensagemValidacao += " - informe o país de origem";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new ControleVacinasException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
		}
	}
	
	public List<Pessoa> consultarPesquisadores() {
		return this.pessoaRepository.consultarPesquisadores();
	}
}
