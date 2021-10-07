package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import models.Empleados;
import models.Users;

public class UsersS {

	
	
	public static void insert(Empleados user) throws SQLException
	{
		
	        String sql = "INSERT INTO users(dni,nombre,apellidos,fecha_nacimiento,poblacion,rol,username,password) VALUES(?,?,?,?,?,?,?,?)";

	        try (Connection conn = DBC.createNewDBconnection();
	                PreparedStatement consulta = conn.prepareStatement(sql)) {
	        		        	
	        	consulta.setString(1, user.getDni());
	        	consulta.setString(2, user.getNombre());
	        	consulta.setString(3, user.getApellidos());
	        	consulta.setDate(4, user.getFecha_nacimiento());
	        	consulta.setString(5, user.getPoblacion());
	        	consulta.setString(6, user.getRol());
	  	      	consulta.setString(7, user.getUsername());
	  	      	consulta.setString(8, user.getPassword());
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
	
	public static void delete() throws ClassNotFoundException {
        String sql = "DELETE FROM users WHERE DNI = ?";

        try (Connection conn = DBC.createNewDBconnection();
                PreparedStatement consulta = conn.prepareStatement(sql)) {

            // set the corresponding parameter
        	consulta.setString(1, "49560848B");
            // execute the delete statement
        	consulta.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	/*public static void select() throws ClassNotFoundException{
	
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
    
}*/
	
	@SuppressWarnings("null")
	public static void select()
	{
		DBC.createNewDBconnection();
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		
		try{
            ps=con.prepareStatement("select * from users");

            rs=ps.executeQuery();
            while(rs.next())
            {
                System.out.println(rs.getString(1)); //here you can get data, the '1' indicates column number based on your query

            }

      }
      catch(Exception e)
      {
          System.out.println("Error in getData"+e);
      }
		    

	}
	
	@SuppressWarnings("null")
	public static List<Empleados> select(Connection conexion) throws ClassNotFoundException{
      String sql = "SELECT * FROM users" ;

      Empleados emp = null;
      List<Empleados> empleados = new ArrayList<>();
      
      try {
   
    	  PreparedStatement consulta = conexion.prepareStatement(sql);
    	  
     	 //
     	 ResultSet rs  = consulta.executeQuery();
     	 // loop through the result set
     	 while (rs.next()) {
     		 
     		 emp = new Empleados(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("rol"), rs.getString("poblacion"), rs.getString("username"), rs.getString("password"), null);
     		 empleados.add(emp);
     	 }
      } catch (SQLException e) {
     	 System.out.println(e.getMessage());
      }
      
      return empleados;
      
	 }
	
	@SuppressWarnings("null")
	public static void selectct(Connection conexion) throws ClassNotFoundException{
      String sql = "SELECT * FROM users" ;

      Empleados emp = null;
      List<Empleados> empleados = new ArrayList<>();
      
      try {
   
    	  PreparedStatement consulta = conexion.prepareStatement(sql);
    	  
     	 //
     	 ResultSet rs  = consulta.executeQuery();
     	 // loop through the result set
     	 while (rs.next()) {
     		 
     		 emp = new Empleados(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("rol"), rs.getString("poblacion"), rs.getString("username"), rs.getString("password"), null);
     		 empleados.add(emp);
     	 }
      } catch (SQLException e) {
     	 System.out.println(e.getMessage());
      }
      
      for (Empleados empleados2 : empleados)
      {
    	  System.out.println(empleados2.getNombre());
      }
      
	 }
	
}
