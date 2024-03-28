package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.entity.Pais;

public class PaisRepository {

	public Pais salvarPaisRepository(Pais pais) {

		String consulta = "INSERT INTO Pais (nome, sigla) values (?, ?)";
		Connection conexao = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conexao, consulta);

		try {

			pstmt.setString(1, pais.getNome());
			pstmt.setString(2, pais.getSigla());

			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if (resultado.next()) {
				pais.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao salvar um pais em paisRepository");
			System.out.println(e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conexao);

		}
		return pais;

	}

	public Pais consultarPorId(int id) {
		String consulta = "SELECT * FROM Pais WHERE idPais = " + id;
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
	
		Pais pais = new Pais();
		try {
			resultado = stmt.executeQuery(consulta);
			if (resultado.next()) {
				
				pais.setId(resultado.getInt("idPais"));
				pais.setNome(resultado.getString("nome"));
				pais.setSigla(resultado.getString("sigla"));

			}

		} catch (Exception e) {
			System.out.println("Erro ao consultar um novo pais");
			System.out.println(e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);

		}

		return pais;

	}

}
