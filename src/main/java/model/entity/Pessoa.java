package model.entity;

import java.time.LocalDate;

public class Pessoa {
	
	public static final int PESQUISADOR = 1;
	public static final int VOLUNTARIO = 2;
	public static final int PUBLICO_GERAL = 3;

	private int idPessoa;
	private String nome;
	private LocalDate dataNascimento;
	private String sexo;
	private String cpf;
	private int tipoDePessoa;
	private Pais pais;
	
	

	public Pessoa(int idPessoa, String nome, LocalDate dataNascimento, String sexo, String cpf, int tipoDePessoa,
			Pais pais) {
		super();
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.tipoDePessoa = tipoDePessoa;
		this.pais = pais;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getNome() {
		return nome;
	}

	public Pessoa() {
		super();

	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getTipoDePessoa() {
		return tipoDePessoa;
	}

	public void setTipoDePessoa( int tipoDePessoa) {
		this.tipoDePessoa = tipoDePessoa;
	}

	@Override
	public String toString() {
		return "Nome: " + nome 
				+ "Data de nascimento: " + dataNascimento 
				+ "Sexo: " + sexo 
				+ "CPF: " + cpf
				+ "Tipo de Pessoa: " + tipoDePessoa;
	}

	
	
}
