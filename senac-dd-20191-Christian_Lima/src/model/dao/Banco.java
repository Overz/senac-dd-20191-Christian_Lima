package model.dao;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {

	/*private static final String URL = "jdbc:mysql://localhost:3306/Senac-dd-20191-Christian_Lima";
	private static final String USER = "root";
	private static final String PASS = "";
	private static Connection conexao;
	
	public static Connection getConexao() {
		if (conexao == null) {
			try {
				conexao = DriverManager.getConnection(URL,USER,PASS);
			} catch (SQLException e) {
				System.out.println("Nao foi Possivel Realizar Conexao com o Banco. Erro:" + e.getMessage());
			}
			System.out.println("Conectado.");
		}
		
		return conexao;
	}*/


private static final String DRIVER = "com.mysql.jdbc.Driver";
private static final String BANCO = "meuBanco";
private static final String CONEXAO = "jdbc:mysql://localhost:3306/" + BANCO;
private static final String USUARIO = "root";
private static final String SENHA = "";

public static Connection getConnection(){
	try {
		Connection conn = null;
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(CONEXAO, USUARIO, SENHA);
		return conn;
	} catch (ClassNotFoundException e) {
		System.out.println("Classe do Driver n�o foi encontrada.");
		return null;
	} catch (SQLException e) {
		System.out.println("Erro ao obter a Connection.");
		return null;
	}
}

public static void closeConnection(Connection conn){
	try {
		if(conn != null){
			conn.close();
		}
	} catch (SQLException e) {
		System.out.println("Problema no fechamento da conex�o.");
	}	
}

public static Statement getStatement(Connection conn){
	try {
		Statement stmt = conn.createStatement();
		return stmt;
	} catch (SQLException e) {
		System.out.println("Erro ao obter o Statement.");
		return null;
	}
}
	
public static void closeStatement(Statement stmt){
	try {
		if(stmt != null){
			stmt.close();
		}
	} catch (SQLException e) {
		System.out.println("Problema no fechamento do Statement.");
	}	
}

public static PreparedStatement getPreparedStatement(Connection conn){
	try {
		PreparedStatement stmt = null;
		return stmt;
	} catch (Exception e) {
		System.out.println("Erro ao obter o PreparedStatement.");
		return null;
	}
}

public static void closePreparedStatement(Statement stmt){
	try {
		if(stmt != null){
			stmt.close();
		}
	} catch (SQLException e) {
		System.out.println("Problema no fechamento do PreparedStatement.");
	}	
}

public static void closeResultSet(ResultSet result){
	try {
		if(result != null){
			result.close();
		}
	} catch (SQLException e) {
		System.out.println("Problema no fechamento do ResultSet");
		}
	}

}