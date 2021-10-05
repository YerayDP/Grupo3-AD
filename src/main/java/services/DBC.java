package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBC {
	
	private static String dbhost = "jdbc:mysql://localhost:3306/gestionhoteles";
	private static String username = "root";
	private static String password = "";
	private static Connection conn;
	

	
	public static void main(String[] args) {
		
		createNewDBconnection();		
	}
	
	
	
	@SuppressWarnings("finally")
	public static Connection createNewDBconnection() {
		try  {	
			conn = DriverManager.getConnection(
					dbhost, "root", "");
			System.out.println("Connected");
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return conn;	
		}		
	}
}
