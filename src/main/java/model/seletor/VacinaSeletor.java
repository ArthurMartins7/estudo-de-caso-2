package model.seletor;

import java.time.LocalDate;

public class VacinaSeletor {
	
	private String nomePais;
	private String nomePesquisador;
	private String nomeVacina;
	private LocalDate dataInicioPesquisa;
	private LocalDate dataFinalPesquisa;
	
	public VacinaSeletor() {
		
	}
	
	
	
	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}



	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}



	public LocalDate getDataFinalPesquisa() {
		return dataFinalPesquisa;
	}



	public void setDataFinalPesquisa(LocalDate dataFinalPesquisa) {
		this.dataFinalPesquisa = dataFinalPesquisa;
	}



	public String getNomePais() {
		return nomePais;
	}
	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}
	public String getNomePesquisador() {
		return nomePesquisador;
	}
	public void setNomePesquisador(String nomePesquisador) {
		this.nomePesquisador = nomePesquisador;
	}
	public String getNomeVacina() {
		return nomeVacina;
	}
	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}
}
