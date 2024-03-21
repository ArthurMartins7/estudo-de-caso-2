package model.entity;

import java.time.LocalDate;

public class Vacina {

	private int id;
	private String nome;
	private Pais paisDeOrigem;
	private int estagioDaPesquisa;
	private LocalDate dataDeInicioDaPesquisa;
	private Pessoa pesquisador;

	public Vacina() {
		super();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pais getPaisDeOrigem() {
		return paisDeOrigem;
	}

	public void setPaisDeOrigem(Pais paisDeOrigem) {
		this.paisDeOrigem = paisDeOrigem;
	}

	public int getEstagioDaPesquisa() {
		return estagioDaPesquisa;
	}

	public void setEstagioDaPesquisa(int estagioDaPesquisa) {
		this.estagioDaPesquisa = estagioDaPesquisa;
	}

	public LocalDate getDataDeInicioDaPesquisa() {
		return dataDeInicioDaPesquisa;
	}

	public void setDataDeInicioDaPesquisa(LocalDate dataDeInicioDaPesquisa) {
		this.dataDeInicioDaPesquisa = dataDeInicioDaPesquisa;
	}

	public Pessoa getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pessoa pesquisador) {
		this.pesquisador = pesquisador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
