package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Aplicacao;
import model.entity.Vacina;


public class AplicacaoRepository implements BaseRepository<Aplicacao> {

	@Override
	public Aplicacao salvar(Aplicacao novaAplicacao) {
		
		String consulta = "INSERT INTO Aplicacao (idPessoa, idVacina, dataDaAplicacao, avaliacao) VALUES (?, ?, ?, ?)";
		Connection conexao = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conexao, consulta);
		
		
		try {
			
			preencherValoresSqlInsertUpdate(pstmt, novaAplicacao);
			
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if(resultado.next()) {
				novaAplicacao.setId(resultado.getInt(1));
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao tentar salvar uma nova aplicação");
			System.out.println(e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conexao);
		}
		return novaAplicacao; 
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM aplicacao WHERE idAplicacao = " + id;
		try {
			excluiu = (stmt.executeUpdate(query) == 1);
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir aplicação");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}
	
	private void preencherValoresSqlInsertUpdate(PreparedStatement stmt, Aplicacao aplicacaoVacina) 
			throws SQLException {
		stmt.setInt(1, aplicacaoVacina.getIdPessoa());
		stmt.setInt(2, aplicacaoVacina.getVacinaAplicada().getId());
		stmt.setDate(3, Date.valueOf(aplicacaoVacina.getDataDaAplicacao()));
		stmt.setInt(4, aplicacaoVacina.getAvaliacao());
	}


	@Override
	public boolean alterar(Aplicacao aplicacaoAlterada) {
		boolean alterou = false;
		String query = " UPDATE aplicacao"
				     + " SET idPessoa=?, idVacina=?, dataDaAplicacao=?, avaliacao=? "
				     + " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			preencherValoresSqlInsertUpdate(stmt, aplicacaoAlterada);
			
			stmt.setInt(5, aplicacaoAlterada.getId());
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
	public Aplicacao consultarPorId(int id) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Aplicacao aplicacaoVacina = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM aplicacao WHERE idAplicacao = " + id;
		try{
			resultado = stmt.executeQuery(query);

			if(resultado.next()){
				aplicacaoVacina = converterParaObjeto(resultado);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar vacinação com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return aplicacaoVacina;
	}
	
	private Aplicacao converterParaObjeto(ResultSet resultado) throws SQLException {
		Aplicacao aplicacaoVacina = new Aplicacao();
		aplicacaoVacina.setId(resultado.getInt("idAplicacao"));
		aplicacaoVacina.setIdPessoa(resultado.getInt("idPessoa"));
		aplicacaoVacina.setAvaliacao(resultado.getInt("avaliacao"));
		aplicacaoVacina.setDataDaAplicacao(resultado.getDate("dataDaAplicacao").toLocalDate());
		
		VacinaRepository vacinaRepository = new VacinaRepository();
		Vacina vacinaAplicada = 
				vacinaRepository.consultarPorId(resultado.getInt("idVacina"));
		
		aplicacaoVacina.setVacinaAplicada(vacinaAplicada);
		return aplicacaoVacina;
	}
		
	@Override
	public ArrayList<Aplicacao> consultarTodos() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ArrayList<Aplicacao> aplicacoes = new ArrayList<Aplicacao>();
		ResultSet resultado = null;
		String query = " SELECT * FROM aplicacao";
		try{
			resultado = stmt.executeQuery(query);

			while(resultado.next()){
				Aplicacao aplicacaoVacina = this.converterParaObjeto(resultado);
				aplicacoes.add(aplicacaoVacina);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todas as vacinações realizadas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return aplicacoes;
	}
	
	public boolean usuarioJaTomouVacina(int id) {
		String query = "SELECT * FROM aplicacao WHERE idPessoa = " + id;
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
			System.out.println("Erro: Erro ao executar usuarioJaTomouVacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return retorno;

	}
	
	public boolean vacinaJaAplicada(int id) {
		String query = "SELECT * FROM aplicacao WHERE idVacina = " + id;
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
			System.out.println("Erro: Erro ao executar vacinaJaAplicada");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return retorno;

	}
	
	public ArrayList<Aplicacao> consultarPorIdPessoa(int idPessoa){
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ArrayList<Aplicacao> aplicacoes = new ArrayList<Aplicacao>();
		ResultSet resultado = null;
		String query = " SELECT * FROM aplicacao WHERE idPessoa = " + idPessoa;
		try{
			resultado = stmt.executeQuery(query);

			while(resultado.next()){
				Aplicacao aplicacaoVacina = this.converterParaObjeto(resultado);
				aplicacoes.add(aplicacaoVacina);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todas as vacinações realizadas na pessoa com id" + idPessoa);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return aplicacoes;
	}
	
	public ArrayList<Aplicacao> consultarPorIdVacina(int idVacina) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ArrayList<Aplicacao> aplicacoes = new ArrayList<Aplicacao>();
		ResultSet resultado = null;
		String query = " SELECT * FROM aplicacao WHERE idVacina = " + idVacina;
		try{
			resultado = stmt.executeQuery(query);

			while(resultado.next()){
				Aplicacao aplicacaoVacina = this.converterParaObjeto(resultado);
				aplicacoes.add(aplicacaoVacina);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todas as vacinações com doses da vacina " + idVacina);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return aplicacoes;
	}

	
	

}
