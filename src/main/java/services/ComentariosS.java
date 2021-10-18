package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Clientes;
import models.Comentarios;
import models.Empleados;

public class ComentariosS {

	public static void insert(Comentarios c,int id) throws SQLException
	{
		
	        String sql = "INSERT INTO comentarios(id_cliente,id_hotel,comentario) VALUES(?,?,?)";

	        try (Connection conn = DBC.createNewDBconnection();
	                PreparedStatement consulta = conn.prepareStatement(sql)) {
	        		        	
	        	consulta.setInt(1, id);
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
	
	
	public static List<String> Hoteles(Connection conexion, String dni) throws ClassNotFoundException{
		
      String sql = "SELECT DISTINCT H.nombre FROM reserva R JOIN users U JOIN hoteles H WHERE U.dni = '"+dni+"' AND H.id=R.id_hotel " ;

      List<String> hoteles = new ArrayList<>();
      int contador = 0;
      
      try {
   
    	  PreparedStatement consulta = conexion.prepareStatement(sql);
    	  
     	 //
     	 ResultSet rs  = consulta.executeQuery();
     	 // loop through the result set
     	 while (rs.next()) {
     		 contador++;
     		 String a = rs.getString("nombre");
     		 hoteles.add(a);
     		 System.out.println(a);
     		 
     	 }
      } catch (SQLException e) {
     	 System.out.println(e.getMessage());
      }
      
      return hoteles;
      
	 }
	
	public static List<Integer> TodosHoteles(Connection conexion) throws ClassNotFoundException{
        String sql = "SELECT id FROM hoteles" ;

        List<Integer> hoteles = new ArrayList<>();
        int contador = 0;

        try {

            PreparedStatement consulta = conexion.prepareStatement(sql);

            //
            ResultSet rs  = consulta.executeQuery();
            // loop through the result set
            while (rs.next()) {
                contador++;
                int a = rs.getInt("id");
                hoteles.add(a);
                System.out.println(a);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return hoteles;

       }
	
	
	public static int id(Connection conexion,String dni) throws SQLException
	{
		String sql = "SELECT DISTINCT id FROM users where dni='"+dni+"'" ;
	
		int id = 0;
		PreparedStatement consulta = conexion.prepareStatement(sql);

        //
        ResultSet rs  = consulta.executeQuery();
        // loop through the result set
        while (rs.next()) {
            id = rs.getInt("id");

            System.out.println(id);
	}
		return id;
}
	
	
	
	public static int id_hotel(Connection conexion,String nombre) throws SQLException
	{
		String sql = "SELECT DISTINCT id FROM hoteles WHERE nombre='"+nombre+"'" ;
	
		int id = 0;
		PreparedStatement consulta = conexion.prepareStatement(sql);

        //
        ResultSet rs  = consulta.executeQuery();
        // loop through the result set
        while (rs.next()) {
            id = rs.getInt("id");

            System.out.println(id);
	}
		return id;
}
	
	
	
}
