package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.Empleados;

public class DBC {
	
	private static String dbhost = "jdbc:mysql://localhost:3306/gestionhoteles";
	private static String username = "root";
	private static String password = "";
	private static Connection conn;
	

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		//createNewDBconnection();
		//UsersS.insert();
		//UsersS.select();
		//UsersS.selectct(createNewDBconnection());
		//ComentariosS.Hoteles(createNewDBconnection());
		
	  }

	
	
	@SuppressWarnings("finally")
	public static Connection createNewDBconnection() {
		try  {	
			conn = DriverManager.getConnection(
					dbhost, username, password);
			System.out.println("Connected");
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return conn;	
		}		
	}
	
	
	
}
