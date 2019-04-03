package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.NivelVO;
import model.vo.UsuarioVO;

public class UsuarioDAO {

	/**
	 * Cadastra um novo usuário
	 * @param usuario
	 * @return true caso cadastrou, false caso contrário
	 */
	public boolean cadastrar(UsuarioVO usuario) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean result = false;

		String query = "INSERT INTO USUARIO (nome, email, senha, idNivel)"
				+ "VALUES ('" + usuario.getNome() +"','" + usuario.getEmail()
				+ "' ,'" + usuario.getSenha() + "', '" + usuario.getNivel().getId() + "')";

		try {
			int resultado = stmt.executeUpdate(query);
			if (resultado == 1) {
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

	/**
	 * Método que excluiu usuario do banco, retorna True caso conseguir, False caso contrario.
	 * @param userVO
	 * @return true/false
	 */
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

	/**
	 * Obtém um usuário dado email e senha
	 * @param email
	 * @param senha
	 * @return um usuário, caso email e senha estejam corretos
	 */
	public UsuarioVO consultarPorEmailESenha(String email, String senha) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		UsuarioVO user = null;

		String query = "SELECT * FROM USUARIO "
				+ " WHERE email = " + email 
				+ " AND senha = " + senha; 

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				user = new UsuarioVO();
				//Constrói um usuário com os dados retornados pela consulta
				user.setNome(resultado.getString("nome"));
				user.setEmail(resultado.getString("email"));
				user.setSenha(resultado.getString("senha"));

				int idNivel = resultado.getInt("idNivel");

				NivelDAO nivelDAO = new NivelDAO();
				//TODO implementar o NivelDAO!
				NivelVO nivel = nivelDAO.consultarPorId(idNivel);
				user.setNivel(nivel);
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

	public UsuarioVO consultar(String email, int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		//TODO implementar 
		
		return null;
	}

	// NECESSITA ALTERAÇÃO, VERIFICAR NOME/EMAIL PARA RETORNAREM CORRETOS;
	public List<UsuarioVO> consultarTodos(String nome, String email){
		String query = "SELECT * FROM USUARIOS";

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		try {
			resultado = stmt.executeQuery(query);
			List<UsuarioVO> listagem = new ArrayList<UsuarioVO>();

			while (resultado.next()) {
				UsuarioVO userVO = new UsuarioVO();
				userVO.setNome(resultado.getString("nome"));
				userVO.setNome(resultado.getString("email"));
				userVO.setNome(resultado.getString("senha"));
				int idNivel = resultado.getInt("idNivel");

				NivelDAO nivelDAO = new NivelDAO();
				//TODO implementar o NivelDAO!
				NivelVO nivel = nivelDAO.consultarPorId(idNivel);
				userVO.setNivel(nivel);
			}

			return listagem; 
		} catch (SQLException e) {
			System.out.println("Erro ao Listar os Usuarios. Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return null;
	}
}