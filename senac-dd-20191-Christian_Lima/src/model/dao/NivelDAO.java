package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.vo.NivelVO;

public class NivelDAO {

	/**
	 * Retorna um nível, dado o seu id
	 * @param idNivel
	 * @return nivel
	 */
	public NivelVO consultarPorId(int idNivel) {
		// TODO Auto-generated method stub
		//SELECT * FROM NIVEL WHERE ID = idNivel

		String query = "SELECT id FROM NIVEL WHERE id = " + idNivel;
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		NivelVO nivelVO = null;
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				nivelVO = new NivelVO();
				
				nivelVO.setId(resultado.getInt("id"));
				nivelVO.setDescricao(resultado.getString("descricao"));
				
			}
		return nivelVO;
		} catch (SQLException e) {
			System.out.println("Erro ao obter resultados da tabela Usuario junto a Nivel. Erro: " + e.getMessage());
			System.out.println(query);
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return nivelVO;
	}

}
