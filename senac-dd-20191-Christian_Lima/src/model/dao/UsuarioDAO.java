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
	 * Cadastra um novo usu�rio
	 * @param userVO
	 * @return true caso cadastrou, false caso contr�rio
	 */
	public boolean cadastrar(UsuarioVO userVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean result = false;

		String query = "INSERT INTO USUARIO (nome, email, senha, idNivel)"
				+ "VALUES ('" + userVO.getNome() +"','" + userVO.getEmail()
				+ "' ,'" + userVO.getSenha() + "', '" + userVO.getNivel().getId() + "')";

		try {
			int resultado = stmt.executeUpdate(query);
			if (resultado == 1) {
				result = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que "
					+ "Cadastra exist�ncia de Usuario. Erro:" + e.getMessage());
			result = false;
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return result;
	}

	/**
	 * M�todo que excluiu usuario do banco, retorna True caso conseguir, False caso contrario.
	 * @param userVO
	 * @return true/false
	 */
	public boolean excluir(UsuarioVO userVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		String query = "DELETE FROM USUARIO WHERE ID = " + userVO.getId();
		try {
			stmt.executeLargeUpdate(query);
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao excluir Usuario.");
			System.out.println("Erro: " + e.getMessage());
			System.out.println(query);

		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}

	/**
	 * Obt�m um usu�rio dado nome, email e senha
	 * @param email
	 * @param senha
	 * @return usu�rio, caso email e senha estejam corretos
	 */
	public UsuarioVO consultarPorEmailESenha(String email, String senha) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		String query = "SELECT * FROM USUARIO"
				+ " WHERE UPPER(email) = '" + email.toUpperCase() + "' "
				+ " AND UPPER(senha) = '" + senha.toUpperCase() + "' ";

		try {
			UsuarioVO userVO = null;
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				userVO = new UsuarioVO();
				//Constr�i um usu�rio com os dados retornados pela consulta
				userVO.setEmail(resultado.getString("email"));
				userVO.setSenha(resultado.getString("senha"));
				userVO.setNome(resultado.getString("nome"));
				userVO.setId(resultado.getInt("id"));

				int idNivel = resultado.getInt("idNivel");
				
				NivelDAO nivelDAO =  new NivelDAO();
				NivelVO nivel = nivelDAO.consultarPorId(idNivel);
				
				userVO.setNivel(nivel);
				return userVO;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao Consultar Permiss�o do Usuario Logado");
			System.out.println("Erro: " +  e.getMessage());
			System.out.println(query);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return null;
	}

	/**
	 * M�todo que retorna um ArrayList com Todos os Usuarios existentes no banco.
	 * @return ArrayList com Todos usuarios;
	 */
	public ArrayList<UsuarioVO> consultarTodos() {
		String query = "SELECT * FROM USUARIO";

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		UsuarioVO userVO = null;
		ArrayList<UsuarioVO> listagem = new ArrayList<UsuarioVO>();
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				userVO = new UsuarioVO();
				userVO.setId(resultado.getInt("id"));
				userVO.setNome(resultado.getString("nome"));
				userVO.setEmail(resultado.getString("email"));
				userVO.setSenha(resultado.getString("senha"));
				int idNivel = resultado.getInt("idNivel");

				NivelDAO nivelDAO = new NivelDAO();
				NivelVO nivel = nivelDAO.consultarPorId(idNivel);
				userVO.setNivel(nivel);
				
				listagem.add(userVO);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao Listar TODOS os Usuarios. Erro: " + e.getMessage());
			System.out.println(query);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return listagem;
	}

	/**
	 * M�todo que dado um NOME, buscar o Usuario no banco.
	 * @param nome
	 * @return ArrayList de Usuario Completo
	 */
	public ArrayList<UsuarioVO> consultarPorNomeDAO(String nome) {
		String query = "SELECT * FROM USUARIO WHERE UPPER(nome) LIKE '%" + nome.toUpperCase() + "%'";

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		UsuarioVO userVO = null;
		ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				userVO = new UsuarioVO();
				userVO.setNome(resultado.getString("nome"));
				userVO.setEmail(resultado.getString("email"));
				userVO.setSenha(resultado.getString("senha"));
				int idNivel = resultado.getInt("id");
				NivelDAO nivelDAO = new NivelDAO();
				NivelVO nivelVO = nivelDAO.consultarPorId(idNivel);
				
				usuarios.add(userVO);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar por NOME no banco.");
					System.out.println("Erro: " + e.getMessage());
			System.out.println(query);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return usuarios;
	}

	/**
	 * M�todo que dado um NIVEl, buscar o usuario no banco.
	 * @param nivelSelecionado NivelVO
	 * @return ArrayList de Usuarios por Nivel
	 */
	public ArrayList<UsuarioVO> consultarPorNivelDAO(NivelVO nivelSelecionado) {
		String query = "SELECT * FROM NIVEL WHERE id = " + nivelSelecionado;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		UsuarioVO userVO = null;
		ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				userVO = new UsuarioVO();
				userVO.setNome(resultado.getString("nome"));
				userVO.setEmail(resultado.getString("email"));
				userVO.setSenha(resultado.getString("senha"));
				int idNivel = resultado.getInt("id");
				NivelDAO nivelDAO = new NivelDAO();
				NivelVO nivelVO = nivelDAO.consultarPorId(idNivel);
				
				usuarios.add(userVO);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar por Nivel no banco.");
			System.out.println("Erro: " + e.getMessage());
			System.out.println(query);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return usuarios;
	}
}