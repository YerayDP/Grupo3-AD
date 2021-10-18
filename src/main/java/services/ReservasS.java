package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Empleados;
import models.Reservas;


public class ReservasS {
	
	public static void insert(Reservas r, int id) throws SQLException
	{
		
	        String sql = "INSERT INTO reserva(id_hotel,id_cliente,fecha_entrada,fecha_salida,id_habitacion) VALUES(?,?,?,?,?)";

	        try (Connection conn = DBC.createNewDBconnection();
	                PreparedStatement consulta = conn.prepareStatement(sql)) {
	        		        	
	        	consulta.setInt(1, r.getId_hotel());
	        	consulta.setInt(2, id);
	        	consulta.setDate(3, r.getFecha_entrada());
	        	consulta.setDate(4, r.getFecha_salida());
	        	consulta.setInt(5, r.getId_Habitacion());
	            consulta.executeUpdate();
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    
		
	      System.out.println("Reserva creada!");
				  
	}

	public static int id_hotel(Connection conexion,String dni) throws SQLException
	{
		String sql = "SELECT id FROM hotel where dni='"+dni+"'" ;
	
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
