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

	@Override
	public Pessoa salvar(Pessoa novaPessoa) {
		String query = "INSERT INTO Pessoa (nome, dataNascimento, sexo, cpf) VALUES (?, ?, ?, ?)";
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
		pstmt.setLong(4, novaPessoa.getCpf());
	}

	@Override
	public boolean excluir(int id) {
		String query = "DELETE FROM Pessoa where idpessoa = " + id;
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
		// TODO Auto-generated method stub
		return null;
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
			while(resultado.next()) {
				Pessoa pessoa = new Pessoa();
				
				pessoa.setIdPessoa(Integer.parseInt(resultado.getString("idpessoa")));
				pessoa.setNome(resultado.getString("nome"));
				pessoa.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
				pessoa.setSexo(resultado.getString("sexo"));
				pessoa.setCpf(resultado.getLong("cpf"));
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
		
	String query = "SELECT * FROM pessoa";
	Connection conn = Banco.getConnection();
	Statement stmt = Banco.getStatement(conn);
	
	ArrayList<Pessoa> pessoas = new ArrayList<>();
	
	ResultSet resultado = null;
	
	try {
		resultado = stmt.executeQuery(query);
		while (resultado.next()) {
			
			Pessoa pessoa = new Pessoa();
			
			pessoa.setIdPessoa(Integer.parseInt(resultado.getString("ID")));
			
			
		}
	

		return null;
	}
	*/
	
}