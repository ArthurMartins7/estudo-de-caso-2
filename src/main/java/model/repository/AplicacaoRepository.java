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
		ResultSet resultado = null;
		
		
		try {
			pstmt.getGeneratedKeys();
			
			pstmt.setInt(1, novaAplicacao.getIdPessoa());
			pstmt.setInt(2, novaAplicacao.getVacinaAplicada().getId());
			pstmt.setDate(3, Date.valueOf(novaAplicacao.getDataDaAplicacao()));
			pstmt.setInt(4, novaAplicacao.getAvaliacao());
			
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao tentar salvar uma nova aplicação");
			System.out.println(e.getMessage());
		}
		return novaAplicacao; 
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Aplicacao entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Aplicacao consultarPorId(int id) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Aplicacao aplicacaoVacina = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM aplicacao WHERE id = " + id;
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
		aplicacaoVacina.setAvaliacao(resultado.getInt("AVALIACAO"));
		aplicacaoVacina.setDataDaAplicacao(resultado.getDate("dataDaAplicacao").toLocalDate());
		
		VacinaRepository vacinaRepository = new VacinaRepository();
		Vacina vacinaAplicada = 
				vacinaRepository.consultarPorId(resultado.getInt("idVacina"));
		
		aplicacaoVacina.setVacinaAplicada(vacinaAplicada);
		return aplicacaoVacina;
	}
		
	@Override
	public ArrayList<Aplicacao> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
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
	
	public double calcularMedia(int id) {
		String consulta = "select avaliacao from aplicacao where idVacina = " + id;
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		int avaliacao = 0;
		double total = 0;
		double media = 0;
				
		try {
			resultado = stmt.executeQuery(consulta);
			if (resultado.next()) {
				avaliacao += resultado.getDouble("avaliacao");
				total++;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o método CalcularMedia");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
		if(total > 0) {
			media = avaliacao / total;
		}
					
		return media;
		
	}
	
	public boolean verificarTipoDePessoa(int id) throws SQLException {
		String query = "SELECT * FROM aplicacao WHERE idVaci = " + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;

		ResultSet resultado = null;
		resultado = stmt.executeQuery(query);
		if (resultado.next()) {
			retorno = true;
		}
		
		return retorno;
	}
}
