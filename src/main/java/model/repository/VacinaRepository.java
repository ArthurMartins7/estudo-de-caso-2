package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Vacina;

public class VacinaRepository implements BaseRepository<Vacina> {

	@Override
	public Vacina salvar(Vacina novaVacina) {

		String query = "INSERT INTO Vacina (nome, pais_origem, idPesquisador, estagio, dataDeInicoDaPesquisa) VALUES (?, ?, ?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {

			pstmt.setString(1, novaVacina.getNome());
			pstmt.setString(2, novaVacina.getPaisDeOrigem());
			pstmt.setInt(3, novaVacina.getPesquisador().getIdPessoa());
			pstmt.setInt(4, novaVacina.getEstagioDaPesquisa());
			pstmt.setDate(5, Date.valueOf(novaVacina.getDataDeInicioDaPesquisa()));

			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if (resultado.next()) {
				novaVacina.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao tentar salvar uma nova vacina!!!");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeResultSet(null);
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return novaVacina;
	}

	@Override
	public boolean excluir(int id) {
		Vacina vacina = new Vacina();
		String consulta = "DELETE FROM Vacina WHERE id = " + id;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Vacina> consultarTodos() {
		ArrayList<Vacina> vacinas = new ArrayList<>();
		Vacina vacina = new Vacina();
		String consulta = "SELECT v.id AS id_vacina, v.nome AS nome_vacina, v.pais_origem AS pais_origem_vacina,\r\n"
				+ "       v.estagio AS estagio_vacina, v.dataDeInicoDaPesquisa AS data_inicio_pesquisa_vacina,\r\n"
				+ "       p.id AS id_pesquisador, p.nome AS nome_pesquisador, p.cpf AS cpf_pesquisador,\r\n"
				+ "       p.dataNascimento AS data_nascimento_pesquisador, p.tipoDePessoa AS tipo_pessoa_pesquisador,\r\n"
				+ "       p.sexo AS sexo_pesquisador\r\n"
				+ "FROM Vacina v\r\n"
				+ "INNER JOIN Pessoa p ON v.idPesquisador = p.id;\r\n"
				+ "";
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		
		try {
			while (resultado.next()) {
				
				vacina.setId(resultado.getInt("id"));
				vacina.setNome(resultado.getString("nome"));
				vacina.setDataDeInicioDaPesquisa(resultado.getDate("dataDeInicioDaPesquisa").toLocalDate());
				vacina.setEstagioDaPesquisa(resultado.getInt("estagio"));
				vacina.setPaisDeOrigem(resultado.getString("pais_origem"));
				//vacina.setPesquisador();
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
