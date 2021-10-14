package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Comentarios;
import models.Empleados;

public class ComentariosS {

	public static void insert(Comentarios c) throws SQLException
	{
		
	        String sql = "INSERT INTO comentarios(id_cliente,id_hotel,comentario) VALUES(?,?,?)";

	        try (Connection conn = DBC.createNewDBconnection();
	                PreparedStatement consulta = conn.prepareStatement(sql)) {
	        		        	
	        	consulta.setInt(1, c.getId_cliente());
	        	consulta.setInt(2, c.getId_hotel());
	        	consulta.setString(3, c.getComentario());
	            consulta.executeUpdate();
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    
		
	      System.out.println("Comentario creado!");
				  
	}
	
	@SuppressWarnings("null")
	public static List<Comentarios> comentarios(Connection conexion, int hotel) throws ClassNotFoundException{
      //String sql = "SELECT * FROM users" ;
      String sql = "SELECT id_hotel,id_cliente,comentario FROM comentarios WHERE id_hotel = ?" ;

      Comentarios com = null;
      List<Comentarios> comentarios = new ArrayList<>();
      
      try {
   
    	  PreparedStatement consulta = conexion.prepareStatement(sql);
    	  
    	   // set the value
    	 	 consulta.setInt(1,hotel);
    	  
     	 //
     	 ResultSet rs  = consulta.executeQuery();
     	 // loop through the result set
     	 while (rs.next()) {
     		 
     		 com = new Comentarios(rs.getInt("id_hotel"),rs.getInt("id_cliente"),rs.getString("comentario"));
     		 comentarios.add(com);
     	 }
      } catch (SQLException e) {
     	 System.out.println(e.getMessage());
      }
      
      return comentarios;
      
	 }
	
	
	public static List<String> Hoteles(Connection conexion) throws ClassNotFoundException{
      //String sql = "SELECT * FROM users" ;
      String sql = "SELECT id_hotel FROM reserva WHERE id_cliente = 1" ;

      List<String> hoteles = new ArrayList<>();
      int contador = 0;
      
      try {
   
    	  PreparedStatement consulta = conexion.prepareStatement(sql);
    	  
     	 //
     	 ResultSet rs  = consulta.executeQuery();
     	 // loop through the result set
     	 while (rs.next()) {
     		 contador++;
     		 String a = rs.getString("id_hotel");
     		 hoteles.add(a);
     		 System.out.println(a);
     		 
     	 }
      } catch (SQLException e) {
     	 System.out.println(e.getMessage());
      }
      
      return hoteles;
      
	 }
	
}
