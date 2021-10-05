package GestionHoteles.Grupo3_AD;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Unit test for simple App.
 */
public class DBConn 
{
	private static String dbhost = "jdbc:mysql://localhost:3306/student";
	private static String username = "root";
	private static String password = "1234";
	private static Connection conn;
	
	@SuppressWarnings("finally")
	public static Connection createNewDBconnection() {
		try  {	
			conn = DriverManager.getConnection(
					dbhost, username, password);	
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return conn;	
		}		
	}
	
}
