package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pessoa;

public class PessoaRepository implements BaseRepository<Pessoa> {

	public boolean cpfJaUtilizado(String cpf) {
		String query = "SELECT * FROM pessoa WHERE cpf = " + cpf;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;

		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				retorno = true;
			}

		} catch (SQLException erro) {
			System.out.println("Erro: Erro ao executar cpfJaUtilizado");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return retorno;

	}

	@Override
	public Pessoa salvar(Pessoa novaPessoa) {
		String query = "INSERT INTO Pessoa (nome, dataNascimento, sexo, cpf, tipoDePessoa, idPais) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			// TODO este bloco repete-se no alterar().... refatorar!
			preencherParametrosParaInsertOuUpdate(pstmt, novaPessoa);

			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novaPessoa.setIdPessoa(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar nova Pesssoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novaPessoa;
	}

	private void preencherParametrosParaInsertOuUpdate(PreparedStatement pstmt, Pessoa novaPessoa) throws SQLException {
		pstmt.setString(1, novaPessoa.getNome());
		pstmt.setDate(2, Date.valueOf(novaPessoa.getDataNascimento()));
		pstmt.setString(3, novaPessoa.getSexo());
		pstmt.setString(4, novaPessoa.getCpf());
		pstmt.setInt(5, novaPessoa.getTipoDePessoa());
		pstmt.setInt(6, novaPessoa.getPais().getId());
	}

	@Override
	public boolean excluir(int id) {
		String query = "DELETE FROM Pessoa where id = " + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;

		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro: não foi possível deletar a pessoa!!!");
			System.out.println("Erro" + erro.getMessage());

		} finally {

			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return excluiu;
	}

	@Override
	public boolean alterar(Pessoa entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pessoa consultarPorId(int id) {
		String consulta = "SELECT * FROM Pessoa WHERE idPessoa" + id;
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		Pessoa pessoa = new Pessoa();

		try {
			resultado = stmt.executeQuery(consulta);

			if (resultado.next()) {
				pessoa.setIdPessoa(resultado.getInt("idPessoa"));
				pessoa.setCpf(resultado.getString("cpf"));
				pessoa.setNome(resultado.getString("nome"));
				pessoa.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
				pessoa.setTipoDePessoa(resultado.getInt("tipoDePessoa"));
				pessoa.setSexo(resultado.getString("sexo"));
				PaisRepository pais = new PaisRepository();
				pessoa.setPais(pais.consultarPorId(resultado.getInt("idPais")));

			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar o método: ConsultarPorId");
			System.out.println(e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return pessoa;
	}

	@Override
	public ArrayList<Pessoa> consultarTodos() {

		ArrayList<Pessoa> pessoas = new ArrayList<>();
		String query = "SELECT * FROM Pessoa;";

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Pessoa pessoa = new Pessoa();

				pessoa.setIdPessoa(Integer.parseInt(resultado.getString("idPessoa")));
				pessoa.setNome(resultado.getString("nome"));
				pessoa.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
				// SE FOR CHAR DE 1, É SÓ USAR O MÉTODO CHHARAT E PEGAR O INDICE 0
				pessoa.setSexo(resultado.getString("sexo"));
				pessoa.setCpf(resultado.getString("cpf"));
				pessoa.setTipoDePessoa(resultado.getInt("tipoDePessoa"));
				pessoas.add(pessoa);
			}
		} catch (SQLException erro) {
			System.out.println("Erro: não foi possível listar todas pessoas.");
			System.out.println("Erro: " + erro.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return pessoas;

	}
	/*
	 * 
	 * String query = "SELECT * FROM pessoa"; Connection conn =
	 * Banco.getConnection(); Statement stmt = Banco.getStatement(conn);
	 * 
	 * ArrayList<Pessoa> pessoas = new ArrayList<>();
	 * 
	 * ResultSet resultado = null;
	 * 
	 * try { resultado = stmt.executeQuery(query); while (resultado.next()) {
	 * 
	 * Pessoa pessoa = new Pessoa();
	 * 
	 * pessoa.setIdPessoa(Integer.parseInt(resultado.getString("ID")));
	 * 
	 * 
	 * }
	 * 
	 * 
	 * return null; }
	 */

}
