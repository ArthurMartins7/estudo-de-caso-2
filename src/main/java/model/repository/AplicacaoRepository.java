package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Aplicacao;

public class AplicacaoRepository implements BaseRepository<Aplicacao> {

	@Override
	public Aplicacao salvar(Aplicacao novaAplicacao) {
		
		String consulta = "INSERT INTO Aplicacao (idPessoa, idVacina, dataAplicacao, avaliacao) VALUES (?, ?, ?, ?)";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Aplicacao> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
