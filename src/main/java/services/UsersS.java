package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	
	
	public static void update() throws ClassNotFoundException {
        String sql = "UPDATE users SET nombre=?, apellidos=?, fecha_nacimiento=?, poblacion=?, username=?, password=? WHERE DNI=?";
        
     // create a sql date object so we can use it in our INSERT statement
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        try (Connection conn = DBC.createNewDBconnection();
                PreparedStatement consulta = conn.prepareStatement(sql)) {
        	
        	consulta.setString(1, "Pablo");
            consulta.setString(2, "Delgado Merino");
            consulta.setDate(3, startDate);
            consulta.setString(4, "San Fernando");
            consulta.setString(5, "Pableiro");
            consulta.setString(6, "pablo");
            consulta.setString(7, "49560848B");
        	
            
            consulta.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("---");
    }
	
	public static void select() throws ClassNotFoundException{
		
        String sql = "SELECT * FROM users";
 
        try (Connection conn = DBC.createNewDBconnection();
                PreparedStatement consulta = conn.prepareStatement(sql)){
     
       	 //
       	 ResultSet rs  = consulta.executeQuery();
       	 // loop through the result set
       	 while (rs.next()) {
       		 
       		System.out.println(rs);
       	 }
        } catch (SQLException e) {
       	 System.out.println(e.getMessage());
        }
        
}
	
}
