package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.vo.UsuarioVO;

public class UsuarioDAO {

	
	public int cadastrar(UsuarioVO usuario) {
		int resultado = 0;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getPreparedStatement(conn);
		ResultSet resultado = null;
		
		String query = "SELECT * FROM USUARIO u WHERE u.email = emailAdm AND u.senha = senha";
		
		try {
			resultado = stmt.executeQuery(query);
		/*	if (resultado.next()) {
				return true;
			}*/
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que "
					+ "verifica existência de Usuario por Nome e Email. Erro:"
					+ e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return resultado;
	}

	public int excluir(UsuarioVO userVO) {
		// TODO Auto-generated method stub
		
	}

	public UsuarioVO consultarPorLoginESenha(String login, String senha) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
