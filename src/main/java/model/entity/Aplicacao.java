package model.entity;

import java.time.LocalDate;

public class Aplicacao {

	private LocalDate dataDaAplicacao;
	private Pessoa pessoaAplicada;
	private Vacina vacinaAplicada;
	private int avaliacao;

	public Aplicacao(LocalDate dataDaAplicacao, Pessoa pessoaAplicada, Vacina vacinaAplicada, int avaliacao) {
		super();
		this.dataDaAplicacao = dataDaAplicacao;
		this.pessoaAplicada = pessoaAplicada;
		this.vacinaAplicada = vacinaAplicada;
		this.avaliacao = avaliacao;
	}

	public Aplicacao() {

	}

	public LocalDate getDataDaAplicacao() {
		return dataDaAplicacao;
	}

	public void setDataDaAplicacao(LocalDate dataDaAplicacao) {
		this.dataDaAplicacao = dataDaAplicacao;
	}

	public Pessoa getPessoaAplicada() {
		return pessoaAplicada;
	}

	public void setPessoaAplicada(Pessoa pessoaAplicada) {
		this.pessoaAplicada = pessoaAplicada;
	}

	public Vacina getVacinaAplicada() {
		return vacinaAplicada;
	}

	public void setVacinaAplicada(Vacina vacinaAplicada) {
		this.vacinaAplicada = vacinaAplicada;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

}
