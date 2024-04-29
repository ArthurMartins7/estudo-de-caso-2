package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Aplicacao;
import model.entity.Pais;
import model.entity.Vacina;
import model.entity.Pessoa;
import model.repository.PaisRepository;
import model.repository.PessoaRepository;
import model.seletor.VacinaSeletor;

public class VacinaRepository implements BaseRepository<Vacina> {

	@Override
	public Vacina salvar(Vacina novaVacina) {

		String query = "INSERT INTO Vacina (nome, idPais, idPessoa, estagioDaPesquisa, dataDeInicioDaPesquisa, media) VALUES (?, ?, ?, ?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {

			pstmt.setString(1, novaVacina.getNome());
			pstmt.setInt(2, novaVacina.getPaisDeOrigem().getId());
			pstmt.setInt(3, novaVacina.getPesquisador().getIdPessoa());
			pstmt.setInt(4, novaVacina.getEstagioDaPesquisa());
			pstmt.setDate(5, Date.valueOf(novaVacina.getDataDeInicioDaPesquisa()));
			pstmt.setDouble(6, novaVacina.getMedia());

			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if (resultado.next()) {
				novaVacina.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao tentar salvar uma nova vacina!!!");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return novaVacina;
	}
	
	@Override
	public boolean excluir(int id) {
		Vacina vacina = new Vacina();
		String consulta = "DELETE FROM Vacina WHERE idVacina = " + id;
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		boolean excluiu = false;

		try {
			if (stmt.executeUpdate(consulta) == 1) {
				excluiu = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao tentar excluir uma vacina!!!");
			System.out.println(e.getMessage());
		}

		return excluiu;
	}

	@Override
	public boolean alterar(Vacina vacinaEditada) {
		boolean alterou = false;
		String query = " UPDATE vacina "
				     + " SET idPessoa=?, nome=?, idPais=?, estagioDaPesquisa=?, dataDeInicioDaPesquisa=?, media=? "
				     + " WHERE idVacina=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setInt(1, vacinaEditada.getPesquisador().getIdPessoa());
			stmt.setString(2, vacinaEditada.getNome());
			stmt.setInt(3, vacinaEditada.getPaisDeOrigem().getId());
			stmt.setInt(4, vacinaEditada.getEstagioDaPesquisa());
			stmt.setDate(5, Date.valueOf(vacinaEditada.getDataDeInicioDaPesquisa()));
			stmt.setDouble(6, vacinaEditada.getMedia());
			
			stmt.setInt(7, vacinaEditada.getId());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

	@Override
	public Vacina consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Vacina vacina = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM vacina WHERE idVacina = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			PessoaRepository pessoaRepository = new PessoaRepository();
			if(resultado.next()){
				vacina = new Vacina();
				vacina.setId(Integer.parseInt(resultado.getString("idVacina")));
				vacina.setNome(resultado.getString("nome"));
				
				PaisRepository paisRepository = new PaisRepository();
				vacina.setPaisDeOrigem(paisRepository.consultarPorId(resultado.getInt("idPais")));
				
				vacina.setEstagioDaPesquisa(resultado.getInt("estagioDaPesquisa"));
				vacina.setDataDeInicioDaPesquisa(resultado.getDate("dataDeInicioDaPesquisa").toLocalDate()); 
				Pessoa pesquisador = pessoaRepository.consultarPorId(resultado.getInt("idPessoa"));
				vacina.setPesquisador(pesquisador);
				vacina.setMedia(resultado.getDouble("media"));
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar vacina com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacina;
	}

	@Override
	public ArrayList<Vacina> consultarTodos() {
		ArrayList<Vacina> vacinas = new ArrayList<>();

		String consulta = "SELECT * FROM Vacina";
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(consulta);
			while (resultado.next()) {
				Vacina vacina = new Vacina();
				vacina.setId(resultado.getInt("idVacina"));
				vacina.setNome(resultado.getString("nome"));
				vacina.setDataDeInicioDaPesquisa(resultado.getDate("dataDeInicioDaPesquisa").toLocalDate());
				vacina.setEstagioDaPesquisa(resultado.getInt("estagioDaPesquisa"));
				PaisRepository paisRepository = new PaisRepository();
				vacina.setPaisDeOrigem(paisRepository.consultarPorId(resultado.getInt("idPais")));
				PessoaRepository pessoa = new PessoaRepository();
				vacina.setPesquisador(pessoa.consultarPorId(resultado.getInt("idPessoa")));
				vacinas.add(vacina);

			}

		} catch (SQLException e) {
			System.out.println("Erro no método consultarTodos na vacina");
			System.out.println(e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return vacinas;
	}
	
	public ArrayList<Vacina> consultarComFiltros(VacinaSeletor seletor){
		ArrayList<Vacina> vacinas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " select v.* from vacina v "
					 + " inner join pais p on v.idPais = p.idPais "
					 + " inner join pessoa pe on v.idPessoa = pe.idPessoa  ";
		
		boolean primeiro = true;
		if(seletor.getNomeVacina() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += "upper(v.nome) LIKE UPPER('%" + seletor.getNomeVacina() + "%')";
			primeiro = false;
		}
		
		if(seletor.getNomePais() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " upper(p.nome) LIKE UPPER('%" + seletor.getNomePais() + "%')";
		}
		
		try{
			resultado = stmt.executeQuery(query);
			PessoaRepository pessoaRepository = new PessoaRepository();
			while(resultado.next()){
				Vacina vacina = new Vacina();
				vacina.setId(Integer.parseInt(resultado.getString("idVacina")));
				vacina.setNome(resultado.getString("nome"));

				PaisRepository paisRepository = new PaisRepository();
				vacina.setPaisDeOrigem(paisRepository.consultarPorId(resultado.getInt("idPais")));

				vacina.setEstagioDaPesquisa(resultado.getInt("estagioDaPesquisa"));
				vacina.setDataDeInicioDaPesquisa(resultado.getDate("dataDeInicioDaPesquisa").toLocalDate()); 
				Pessoa pesquisador = pessoaRepository.consultarPorId(resultado.getInt("idPessoa"));
				vacina.setPesquisador(pesquisador);
				vacinas.add(vacina);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todas as vacinas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacinas;
	}

}
