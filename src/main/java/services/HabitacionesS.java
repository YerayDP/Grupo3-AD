package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import models.Habitaciones;
import models.Hoteles;

public class HabitacionesS {

	public static void insert() throws SQLException
	{
		
	        String sql = "INSERT INTO habitaciones (numHabitaciones,precioNoche,tipo,extras) VALUES(?,?,?,?)";

	        try (Connection conn = DBC.createNewDBconnection();
	                PreparedStatement consulta = conn.prepareStatement(sql)) {
	        	
	        	// create a sql date object so we can use it in our INSERT statement
	            Calendar calendar = Calendar.getInstance();
	            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
	        	
	        	consulta.setInt(1, 2);
	        	consulta.setInt(2, 144);
	        	consulta.setString(3, "Suite");
	        	consulta.setString(4, "Frigorifico");
	        	
	            consulta.executeUpdate();
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    
		
	      System.out.println("Usuario creado!");
				  
	}
	
	
	
	public static void update() throws ClassNotFoundException {
        String sql = "UPDATE habitaciones SET numHabitacion=?, precioNoche=?, tipo=?, extras=? WHERE numHabitacion=?";
        
     // create a sql date object so we can use it in our INSERT statement
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        try (Connection conn = DBC.createNewDBconnection();
                PreparedStatement consulta = conn.prepareStatement(sql)) {
        	
        	consulta.setInt(1, 2);
        	consulta.setInt(2, 144);
        	consulta.setString(3, "Suite");
        	consulta.setString(4, "Frigorifico");
        	
            
            consulta.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("---");
    }
	
	public static List<Habitaciones> select(Connection conexion) throws ClassNotFoundException{
	      String sql = "SELECT * FROM habitaciones" ;

	      Habitaciones ha = null;
	      List<Habitaciones> habitaciones = new ArrayList<>();
	      
	      try {
	   
	    	  PreparedStatement consulta = conexion.prepareStatement(sql);
	    	  
	     	 //
	     	 ResultSet rs  = consulta.executeQuery();
	     	 // loop through the result set
	     	 while (rs.next()) {
	     		 
	     		ha = new Habitaciones(rs.getInt("numHabitacion"), rs.getInt("precioNoche"), rs.getString("tipo"), rs.getString("extras"));
	     		habitaciones.add(ha);
	     	 }
	      } catch (SQLException e) {
	     	 System.out.println(e.getMessage());
	      }
	      
	      return habitaciones;
	      
		 }
	
	public static void delete() throws ClassNotFoundException {
        String sql = "DELETE FROM users WHERE telefono = ?";

        try (Connection conn = DBC.createNewDBconnection();
                PreparedStatement consulta = conn.prepareStatement(sql)) {

            // set the corresponding parameter
        	consulta.setInt(1, 2);
            // execute the delete statement
        	consulta.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
}
