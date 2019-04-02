package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		String query = "DELETE (nome, email, senha, senhaConfirmacao, nivel) FROM USUARIO"
				+ " WHERE id = " + userVO.getNivel().getId();
		try {
			stmt.executeQuery(query);
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao excluir Usuario. Erro: " + e.getMessage());
			
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
	return false;
	}

	public UsuarioVO consultarPorLoginESenha(String email, String senha) {
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
	
	/*public List<UsuarioVO> getListar(){
		String query = "SELECT (email as E-Mail, nivel as Nível_do_Usuário) FROM USUARIOS";
		
		try {
			Connection conn = Banco.getConnection();
			Statement stmt = Banco.getStatement(conn);
			ResultSet resultado = stmt.executeQuery(query);
			
			List<UsuarioVO> listagem = new ArrayList<UsuarioVO>();
			
			while (resultado.next()) {
				UsuarioVO userVO = new UsuarioVO();
				userVO.setNome(resultado.getString("nome"));
				userVO.setNome(resultado.getString("email"));
				userVO.setNome(resultado.getString("senha"));
				userVO.setNome(resultado.getString("senhaConfirmacao"));
				userVO.setNome(resultado.getString("nivel"));
			}
			
			return listagem; 
		} catch (SQLException e) {
			System.out.println("Erro ao Listar os Usuarios. Erro: " + e.getMessage());
		} //finally {
		//	Banco.closeResultSet(resultado);
		//	Banco.closePreparedStatement(stmt);
		//	Banco.closeConnection(conn);
		//}
		
		return null;
	}*/
	
	public boolean getCadastrar(UsuarioVO userVO) {
		String query = "INSERT INTO USUARIO (nome, email, senha, senhaConfirmacao, nivel)"
				+ "FROM USUARIO";
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
	
		try {
			PreparedStatement stat;
			stat = Banco.getConnection().prepareStatement(query);
			
			stat.setString(1, userVO.getNome());
			stat.setString(2, userVO.getEmail());
			stat.setString(3, userVO.getSenha());
			stat.setString(4, userVO.getSenhaConfirmacao());
			stat.setObject(5, userVO.getNivel());
			
			stat.closeOnCompletion();
			
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao Cadastrar o Usuario. Erro: " + e.getMessage());
		} finally {
			
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return false;
		
	}
	
	
	
}
