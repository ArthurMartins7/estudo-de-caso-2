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
			AplicacaoRepository aplicacao = new AplicacaoRepository();
			pstmt.setDouble(6, aplicacao.calcularMedia(novaVacina.getId()));

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
	public boolean alterar(Vacina entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vacina consultarPorId(int id) {
		Vacina vacina = new Vacina();
		String consulta = "SELECT * FROM Vacina WHERE idVacina = " + id;
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(consulta);
			if (resultado.next()) {
				vacina.setId(resultado.getInt("idVacina"));
				PaisRepository pais = new PaisRepository();
				vacina.setPaisDeOrigem(pais.consultarPorId(resultado.getInt("idPais")));
				vacina.setNome(resultado.getString("nome"));
				vacina.setEstagioDaPesquisa(resultado.getInt("estagioDaPesquisa"));
				vacina.setDataDeInicioDaPesquisa(resultado.getDate("dataAplicacao").toLocalDate());
				PessoaRepository pessoa = new PessoaRepository();

			}

		} catch (SQLException e) {
			System.out.println("Erro ao tentar o método consultarPorId na vacina");
			System.out.println(e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
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
				vacina.setEstagioDaPesquisa(resultado.getInt("estagio"));
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

}
