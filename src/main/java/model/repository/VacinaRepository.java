package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

}
