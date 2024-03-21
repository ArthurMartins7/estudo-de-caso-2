package model.entity;

import java.time.LocalDate;

public class Aplicacao {
	private int id;
	private LocalDate dataDaAplicacao;
	private int idPessoa;
	private Vacina vacinaAplicada;
	private int avaliacao;

	public Aplicacao(LocalDate dataDaAplicacao, int idPessoa, Vacina vacinaAplicada, int avaliacao) {
		super();
		this.dataDaAplicacao = dataDaAplicacao;
		this.idPessoa= idPessoa;
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

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
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
