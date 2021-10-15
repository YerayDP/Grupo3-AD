package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import services.DBC;
import javax.swing.JLabel;
import java.awt.Font;

public class ConsultarHabitaciones extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarHabitaciones frame = new ConsultarHabitaciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultarHabitaciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Consulta de habitaciones");
		setLocationRelativeTo(null);
		
		
		try {					
			
			JTable table_1 = new JTable();
	        table_1.setBounds(153, 104, 483, 170);
	        contentPane.add(table_1);
	        
	        JLabel lblNewLabel = new JLabel("Habitacion");
	        lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 13));
	        lblNewLabel.setBounds(190, 78, 130, 26);
	        contentPane.add(lblNewLabel);
	        
	        JLabel lblNewLabel_1 = new JLabel("Hotel");
	        lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 13));
	        lblNewLabel_1.setBounds(360, 78, 54, 26);
	        contentPane.add(lblNewLabel_1);
	        
	        JLabel lblNewLabel_2 = new JLabel("Tipo");
	        lblNewLabel_2.setFont(new Font("Sitka Text", Font.BOLD, 13));
	        lblNewLabel_2.setBounds(522, 78, 54, 26);
	        contentPane.add(lblNewLabel_2);
			
			DefaultTableModel modelo  = (DefaultTableModel) table_1.getModel();
	        
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        Connection con = DBC.createNewDBconnection();


	        String sql = "SELECT DISTINCT H.numHabitacion, HO.nombre, H.tipo from reserva R JOIN habitaciones H JOIN hoteles HO WHERE fecha_entrada<=(SELECT SYSDATE()) AND fecha_salida>=(SELECT SYSDATE())";

	        ps = con.prepareStatement(sql);
	        
	        //ps.setInt(1,Integer.valueOf(hotel));

	        rs = ps.executeQuery();

	        ResultSetMetaData rsMd = rs.getMetaData();

	        int cantidadColumnas = rsMd.getColumnCount();

	        modelo.addColumn("");
	        modelo.addColumn("");
	        modelo.addColumn("");

	        while(rs.next())
	        {
	            Object[] filas = new Object[cantidadColumnas];

	            for (int i = 0; i < cantidadColumnas; i++) {
	                filas[i] = rs.getObject(i+1);
	            }
	            modelo.addRow(filas);
	        }
		
	} catch (NumberFormatException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
}
