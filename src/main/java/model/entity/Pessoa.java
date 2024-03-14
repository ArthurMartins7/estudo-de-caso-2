package model.entity;

import java.time.LocalDate;

public class Pessoa {

	private int idPessoa;
	private String nome;
	private LocalDate dataNascimento;
	private String sexo;
	private String cpf;
	private String tipoDePessoa;

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

	public String getTipoDePessoa() {
		return tipoDePessoa;
	}

	public void setTipoDePessoa(String tipoDePessoa) {
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
