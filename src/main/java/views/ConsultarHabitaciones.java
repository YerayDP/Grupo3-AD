package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import services.DBC;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setTitle("Consulta de habitaciones");
		setLocationRelativeTo(null);
		
		
			contentPane.setLayout(null);
			

	        
	        JLabel lblNewLabel = new JLabel("Habitacion");
	        lblNewLabel.setBounds(256, 78, 130, 26);
	        lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 13));
	        contentPane.add(lblNewLabel);
	        
	        JLabel lblNewLabel_1 = new JLabel("Hotel");
	        lblNewLabel_1.setBounds(431, 78, 54, 26);
	        lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 13));
	        contentPane.add(lblNewLabel_1);
	        
	        JLabel lblNewLabel_2 = new JLabel("Tipo");
	        lblNewLabel_2.setBounds(565, 78, 54, 26);
	        lblNewLabel_2.setFont(new Font("Sitka Text", Font.BOLD, 13));
	        contentPane.add(lblNewLabel_2);
	        
	        final JDateChooser dateChooser = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
			dateChooser.setBounds(247, 179, 147, 32);
			dateChooser.getJCalendar();
			contentPane.add(dateChooser);
			
			JLabel lblNewLabel_3 = new JLabel("Seleccione una fecha");
			lblNewLabel_3.setFont(new Font("Sitka Text", Font.BOLD, 13));
			lblNewLabel_3.setBounds(32, 119, 147, 21);
			contentPane.add(lblNewLabel_3);
			
			JButton btnNewButton = new JButton("Consultar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JTable table_1 = new JTable();
					table_1.setBounds(244, 96, 386, 170);
			        contentPane.add(table_1);					
					
					DefaultTableModel modelo  = (DefaultTableModel) table_1.getModel();
			        
			        PreparedStatement ps = null;
			        ResultSet rs = null;
			        Connection con = DBC.createNewDBconnection();
			        
			        Date fecha = new java.sql.Date(dateChooser.getDate().getTime());

			        String sql = "SELECT DISTINCT HO.nombre,H.numHabitacion, H.tipo from reserva R JOIN habitaciones H JOIN hoteles HO WHERE R.fecha_entrada > '"+fecha+"' ";

			        try {
						ps = con.prepareStatement(sql);

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
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        

				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnNewButton.setBounds(32, 224, 147, 42);
			contentPane.add(btnNewButton);
			
			
		

}
}
