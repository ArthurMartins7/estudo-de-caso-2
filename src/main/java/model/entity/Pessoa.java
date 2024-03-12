package model.entity;

import java.time.LocalDate;

public class Pessoa {

	private int idPessoa;
	private String nome;
	private LocalDate dataNascimento;
	private char sexo;
	private long cpf;

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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Nome: " + nome 
				+ "Data de nascimento: " + dataNascimento 
				+ "Sexo: " + sexo 
				+ "CPF: " + cpf;
	}

	
	
}
