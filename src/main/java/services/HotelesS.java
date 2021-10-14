package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import models.Empleados;
import models.Hoteles;

public class HotelesS {

	
		
		public static void insert(Hoteles ho) throws SQLException
		{
			
		        String sql = "INSERT INTO hoteles(nombre, descripcion, ciudad, direccion, telefono) VALUES(?,?,?,?,?)";

		        try (Connection conn = DBC.createNewDBconnection();
		                PreparedStatement consulta = conn.prepareStatement(sql)) {
		        		        	
		        	consulta.setString(1, ho.getNombre());
		        	consulta.setString(2, ho.getDescripcion());
		        	consulta.setString(3, ho.getCiudad());
		        	consulta.setString(4, ho.getDireccion());
		        	consulta.setString(5, ho.getTelefono());

		            consulta.executeUpdate();
		            
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		    
			
		      System.out.println("Hotel creado!");
					  
		}

	
	
	
	public static void update() throws ClassNotFoundException {
        String sql = "UPDATE hoteles SET nombre=?, descripcion=?, ciudad=?, direccion=?, telefono=? WHERE telefono=?\"";
        
     // create a sql date object so we can use it in our INSERT statement
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        try (Connection conn = DBC.createNewDBconnection();
                PreparedStatement consulta = conn.prepareStatement(sql)) {
        	
        	consulta.setString(1, "Hotel Royalti");
        	consulta.setString(2, "4 estrellas a pie de playa");
        	consulta.setString(3, "San Fernando");
        	consulta.setString(4, "Calle Reyes");
        	consulta.setString(5, "900900900");
        	
            
            consulta.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("---");
    }
	
	public static List<Hoteles> select(Connection conexion) throws ClassNotFoundException{
	      String sql = "SELECT * FROM hoteles" ;

	      Hoteles ho = null;
	      List<Hoteles> hoteles = new ArrayList<>();
	      
	      try {
	   
	    	  PreparedStatement consulta = conexion.prepareStatement(sql);
	    	  
	     	 //
	     	 ResultSet rs  = consulta.executeQuery();
	     	 // loop through the result set
	     	 while (rs.next()) {
	     		 
	     		ho = new Hoteles(rs.getString("nombre"), rs.getString("descripcion"), rs.getString("ciudad"), rs.getString("direccion"), rs.getString("telefono"));
	     		hoteles.add(ho);
	     	 }
	      } catch (SQLException e) {
	     	 System.out.println(e.getMessage());
	      }
	      
	      return hoteles;
	      
		 }
	
	public static void delete() throws ClassNotFoundException {
        String sql = "DELETE FROM hoteles WHERE telefono = ?";

        try (Connection conn = DBC.createNewDBconnection();
                PreparedStatement consulta = conn.prepareStatement(sql)) {

            // set the corresponding parameter
        	consulta.setString(1, "900900900");
            // execute the delete statement
        	consulta.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public static List<Integer> ids(Connection conexion) throws ClassNotFoundException{
	      String sql = "SELECT id FROM hoteles" ;

	      List<Integer> ids = new ArrayList<>();
	      
	      try {
	   
	    	  PreparedStatement consulta = conexion.prepareStatement(sql);
	    	  
	     	 //
	     	 ResultSet rs  = consulta.executeQuery();
	     	 // loop through the result set
	     	 while (rs.next()) {
	     		 
	     		int a = rs.getInt("id");
	     		ids.add(a);
	     	 }
	      } catch (SQLException e) {
	     	 System.out.println(e.getMessage());
	      }
	      
	      return ids;
	      
		 }
	
}
