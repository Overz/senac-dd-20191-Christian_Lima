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
	 * @param userVO
	 * @return true caso cadastrou, false caso contrário
	 */
	public boolean cadastrar(UsuarioVO userVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean result = false;

		String query = "INSERT INTO USUARIO (nome, email, senha, idNivel)"
				+ "VALUES ('" + userVO.getNome() +"','" + userVO.getEmail()
				+ "' ,'" + userVO.getSenha() + "', '" + userVO.getNivelVO().getId() + "')";

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
	 * Obtém um usuário dado nome, email e senha
	 * @param email
	 * @param senha
	 * @return usuário, caso email e senha estejam corretos
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
				UsuarioVO usuario = obterUsuarioDoResultSet(resultado);
				return usuario;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao Consultar Permissão do Usuario Logado");
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
	 * Cria um novo usuário a partir do ResultSet
	 * @param resultado
	 * @return
	 */
	private UsuarioVO obterUsuarioDoResultSet(ResultSet resultado) {
		UsuarioVO userVO = null;
		try {
			//Constrói um usuário com os dados retornados pela consulta
			userVO = new UsuarioVO();
			userVO.setId(resultado.getInt("id"));
			userVO.setEmail(resultado.getString("email"));
			userVO.setSenha(resultado.getString("senha"));
			userVO.setNome(resultado.getString("nome"));
			
			int idNivel = resultado.getInt("idNivel");
			
			NivelDAO nivelDAO =  new NivelDAO();
			NivelVO nivel = nivelDAO.consultarPorId(idNivel);
			
			userVO.setNivelVO(nivel);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userVO;
	}

	/**
	 * Método que retorna um ArrayList com Todos os Usuarios existentes no banco.
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
				UsuarioVO usuario = obterUsuarioDoResultSet(resultado);
				
				listagem.add(usuario);
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
	 * Método que dado um NOME, buscar o Usuario no banco.
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
				UsuarioVO usuario = obterUsuarioDoResultSet(resultado);
				usuarios.add(usuario);
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
	 * Método que dado um NIVEl, buscar o usuario no banco.
	 * @param nivelSelecionado NivelVO
	 * @return ArrayList de Usuarios por Nivel
	 */
	public ArrayList<UsuarioVO> consultarPorNivelDAO(NivelVO nivelSelecionado) {
		String query = "SELECT * FROM usuario u"
				+ " WHERE u.idNivel = " + nivelSelecionado.getId();
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				UsuarioVO usuario = obterUsuarioDoResultSet(resultado);
				usuarios.add(usuario);
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