package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Comentarios;
import services.ComentariosS;
import services.DBC;
import services.HotelesS;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Consultar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consultar frame = new Consultar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 */
	public Consultar() throws ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 417);
		contentPane = new JPanel();
		setLocationRelativeTo( null );
		setTitle("Consulta de hospedaje");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Hotel");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel.setBounds(56, 20, 72, 33);
		contentPane.add(lblNewLabel);
		
		final JComboBox<Integer> comboBox = new JComboBox<Integer>();
		
		List<Integer> hoteles = ComentariosS.TodosHoteles(DBC.createNewDBconnection());
		for (Integer hotel : hoteles) {
			comboBox.addItem(hotel);
			
		}
		comboBox.setBounds(10, 63, 121, 21);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(7, 118, 121, 33);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String hotel = comboBox.getSelectedItem().toString();
				
				try {
					List<Comentarios> cs = ComentariosS.comentarios(DBC.createNewDBconnection(), Integer.valueOf (hotel));

						
						
						JTable table_1 = new JTable();
				        table_1.setBounds(152, 64, 483, 170);
				        contentPane.add(table_1);
						
						DefaultTableModel modelo  = (DefaultTableModel) table_1.getModel();
				        
				        PreparedStatement ps = null;
				        ResultSet rs = null;
				        Connection con = DBC.createNewDBconnection();


				        String sql = "Select id_cliente from reserva where id_hotel=? AND fecha_salida>='2021-09-12'";

				        ps = con.prepareStatement(sql);
				        
				        ps.setInt(1,Integer.valueOf(hotel));

				        rs = ps.executeQuery();

				        ResultSetMetaData rsMd = rs.getMetaData();

				        int cantidadColumnas = rsMd.getColumnCount();

				        modelo.addColumn("Dni");
				        modelo.addColumn("Dni");


				        while(rs.next())
				        {
				            Object[] filas = new Object[cantidadColumnas];

				            for (int i = 0; i < cantidadColumnas; i++) {
				                filas[i] = rs.getObject(i+1);
				            }
				            modelo.addRow(filas);
				        }
					
				} catch (NumberFormatException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
}
