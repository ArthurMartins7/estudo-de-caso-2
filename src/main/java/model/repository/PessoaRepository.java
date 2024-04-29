package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		String query = "DELETE FROM Pessoa where idPessoa = " + id;
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
	public boolean alterar(Pessoa pessoaEditada) {
		boolean alterou = false;
		String query = " UPDATE Pessoa "
				     + " SET nome=?, cpf=?, sexo=?, idPais=?, "
				     + " dataNascimento=?, tipoDePessoa=? "
				     + " WHERE idPessoa=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setString(1, pessoaEditada.getNome());
			stmt.setString(2, pessoaEditada.getCpf());
			stmt.setString(3, pessoaEditada.getSexo());
			stmt.setInt(4, pessoaEditada.getPais().getId());
			stmt.setDate(5, Date.valueOf(pessoaEditada.getDataNascimento()));
			stmt.setInt(6, pessoaEditada.getTipoDePessoa());
			
			stmt.setInt(7, pessoaEditada.getIdPessoa());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	
	}

	@Override
	public Pessoa consultarPorId(int id) {
		String consulta = "SELECT * FROM Pessoa WHERE idPessoa = " + id;
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
			System.out.println("Erro ao tentar consultar pessoa com o id: " + id);
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
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM pessoa";
		
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Pessoa pessoa = construirDoResultSet(resultado);
				pessoas.add(pessoa);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todas as pessoas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoas;
	}
	 
	private Pessoa construirDoResultSet(ResultSet resultado) throws SQLException {
		Pessoa pessoa = new Pessoa();
		pessoa.setIdPessoa(resultado.getInt("idPessoa"));
		pessoa.setNome(resultado.getString("nome"));
		pessoa.setCpf(resultado.getString("cpf"));
		pessoa.setSexo(resultado.getString("sexo"));
		pessoa.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate()); 
		pessoa.setTipoDePessoa(resultado.getInt("tipoDePessoa"));
		
		PaisRepository paisRepository = new PaisRepository();
		pessoa.setPais(paisRepository.consultarPorId(resultado.getInt("idPais")));
		
		return pessoa;
	}
	
	public List<Pessoa> consultarPesquisadores() {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM pessoa WHERE tipoDePessoa = " + Pessoa.PESQUISADOR;
		
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Pessoa pessoa = construirDoResultSet(resultado);
				pessoas.add(pessoa);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todos os pesquisadores");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoas;
	}

}
