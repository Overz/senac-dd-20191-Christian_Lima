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

		String query = "DELETE (nome, email, senha, idNivel) FROM USUARIO"
				+ " WHERE id = '" + userVO.getNivel().getId() + "' ";
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
	 * Obt�m um usu�rio dado nome, email e senha
	 * @param nome
	 * @param email
	 * @param senha
	 * @return um usu�rio, caso email e senha estejam corretos
	 */
	public UsuarioVO consultarPorEmailESenha(String nome, String email, String senha) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		UsuarioVO user = null;
		
		
		String query = "SELECT * FROM USUARIO"
				+ " WHERE UPPER(nome) = '" + nome.toUpperCase() + "' "
				+ " AND UPPER(email) = '" + email.toUpperCase() + "' "
				+ " AND UPPER(senha) = '" + senha.toUpperCase() + "' ";
		
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				user = new UsuarioVO();
				//Constr�i um usu�rio com os dados retornados pela consulta
				user.setNome(resultado.getString("nome"));
				user.setEmail(resultado.getString("email"));
				user.setSenha(resultado.getString("senha"));

				int idNivel = resultado.getInt("idNivel");
				
				NivelDAO nivelDAO =  new NivelDAO();
				NivelVO nivel = nivelDAO.consultarPorId(idNivel);
				user.setNivel(nivel);
				return user;
			}
		} catch (SQLException e) {
			System.out.println("Falha ao Selecionar os Usuarios.\nErro: "+  e.getMessage());
			System.out.println(query);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return user;
	}

	// NECESSITA ALTERA��O, VERIFICAR NOME/EMAIL PARA RETORNAREM CORRETOS;
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