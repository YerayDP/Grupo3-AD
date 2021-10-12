package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Empleados;
import models.Reservas;


public class ReservasS {
	
	public static void insert(Reservas r) throws SQLException
	{
		
	        String sql = "INSERT INTO reserva(id_hotel,id_cliente,fecha_entrada,fecha_salida,habitacion) VALUES(?,?,?,?,?)";

	        try (Connection conn = DBC.createNewDBconnection();
	                PreparedStatement consulta = conn.prepareStatement(sql)) {
	        		        	
	        	consulta.setInt(1, r.getId_hotel());
	        	consulta.setInt(2, r.getId_cliente());
	        	consulta.setDate(3, r.getFecha_entrada());
	        	consulta.setDate(3, r.getFecha_salida());
	        	consulta.setInt(5, r.getHabitacion());
	            consulta.executeUpdate();
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    
		
	      System.out.println("Reserva creada!");
				  
	}

	/*public Reservas select(String DNI) throws ClassNotFoundException{
		
        String sql = "SELECT DNI, nombre, telefono, pasword, deuda"
                   + "FROM reserva WHERE DNI = ?";
        
        Reservas cliente = null;
 
        try (Connection conn = DBC.createNewDBconnection();
                PreparedStatement consulta = conn.prepareStatement(sql)) {
     
       	 // set the value
       	 consulta.setString(1,DNI);
       	 //
       	 ResultSet rs  = consulta.executeQuery();
       	 // loop through the result set
       	 while (rs.next()) {
       		 
       		 Reservas reserva = new Reservas(DNI, rs.getString("nombre"),rs.getString("telefono"), rs.getString("password"),rs.getFloat("deuda"));
       		 
       	 }
        } catch (SQLException e) {
       	 System.out.println(e.getMessage());
        }
        
        return reserva;
}*/
	
}
