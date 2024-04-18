package model.entity;

import java.time.LocalDate;

public class Vacina {

	public static final int ESTAGIO_INICIAL = 1;
	public static final int ESTAGIO_TESTES = 2;
	public static final int ESTAGIO_APLICACAO_MASSA = 3;

	private int id;
	private String nome;
	private Pais paisDeOrigem;
	private int estagioDaPesquisa;
	private LocalDate dataDeInicioDaPesquisa;
	private double media;
	private Pessoa pesquisador;

	public Vacina(int id, String nome, Pais paisDeOrigem, int estagioDaPesquisa, LocalDate dataDeInicioDaPesquisa,
			double media, Pessoa pesquisador) {
		super();
		this.id = id;
		this.nome = nome;
		this.paisDeOrigem = paisDeOrigem;
		this.estagioDaPesquisa = estagioDaPesquisa;
		this.dataDeInicioDaPesquisa = dataDeInicioDaPesquisa;
		this.media = media;
		this.pesquisador = pesquisador;

	}

	public Vacina() {
		super();

	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
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
