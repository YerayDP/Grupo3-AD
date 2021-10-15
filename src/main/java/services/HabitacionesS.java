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


	public static void insert(Habitaciones ha) throws SQLException
	{
		
	        String sql = "INSERT INTO habitaciones(numHabitacion, precioNoche, tipo, extras) VALUES(?,?,?,?)";

	        try (Connection conn = DBC.createNewDBconnection();
	                PreparedStatement consulta = conn.prepareStatement(sql)) {
	        		        	
	        	consulta.setInt(1, ha.getNumHabitacion());
	        	consulta.setInt(2, ha.getPrecioNoche());
	        	consulta.setString(3, ha.getTipo());
	        	consulta.setString(4, ha.getExtras());


	            consulta.executeUpdate();
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    
		
	      System.out.println("Habitacion creada!");
				  
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
	
	
	
}
