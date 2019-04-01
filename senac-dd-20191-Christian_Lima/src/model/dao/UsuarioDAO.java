package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.vo.UsuarioVO;

public class UsuarioDAO {

	
	public boolean cadastrar(UsuarioVO usuario) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getPreparedStatement(conn);
		ResultSet resultado = null;
		boolean result = false;
		
		String query = "INSERT INTO USUARIO (nome, email, senha, senhaConfirmacao, nivel)"
						+ "VALUES ('" + usuario.getNome() +"','" + usuario.getEmail()
						+ "' ,'" + usuario.getSenha() + "', '" + usuario.getNivel() + ")";
		
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				result = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que "
					+ "Cadastra existência de Usuario. Erro:" + e.getMessage());
			result = false;
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return result;
	}

	public boolean excluir(UsuarioVO userVO) {
		
		//TODO 
	return false;
	}

	public UsuarioVO consultarPorLoginESenha(String login, String senha) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		UsuarioVO user = new UsuarioVO();
		
		String query = "SELECT (email as E-Mail, nivel as Nível_do_Usuário) FROM USUARIOS";
		
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				user = (UsuarioVO) resultado;
			}
		} catch (SQLException e) {
			System.out.println("Falha ao Selecionar os Usuarios. Erro: "+ e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return user;
	}
	
	
	
}
