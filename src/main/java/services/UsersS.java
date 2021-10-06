package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class UsersS {

	
	
	public static void insert() throws SQLException
	{
		
	        String sql = "INSERT INTO users(dni,nombre,apellidos,fecha_nacimiento,poblacion,rol,username,password) VALUES(?,?,?,?,?,?,?,?)";

	        try (Connection conn = DBC.createNewDBconnection();
	                PreparedStatement consulta = conn.prepareStatement(sql)) {
	        	
	        	// create a sql date object so we can use it in our INSERT statement
	            Calendar calendar = Calendar.getInstance();
	            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
	        	
	        	consulta.setString(1, "49560848B");
	        	consulta.setString(2, "Pablo");
	        	consulta.setString(3, "Delgado");
	        	consulta.setDate(4, startDate);
	        	consulta.setString(5, "San Fernando");
	        	consulta.setString(6, "empleado");
	  	      	consulta.setString(7, "pablo");
	  	      	consulta.setString(8, "pablo");
	            consulta.executeUpdate();
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    
		
	      System.out.println("Usuario creado!");
				  
	}
	
}
