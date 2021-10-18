package views;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Clientes;
import models.Empleados;
import models.Users;
import services.DBC;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cliente extends JFrame {

	private JPanel contentPane;
	static private Clientes c = new Clientes();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente(c);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Cliente( final Clientes c) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 417);
		contentPane = new JPanel();
		setLocationRelativeTo( null );
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Reservar habitacion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReservaI RI;
				RI = new ReservaI(c);
				setVisible(false);
				RI.setVisible(true);
			
			}
		});
		btnNewButton.setBounds(21, 300, 180, 56);
		contentPane.add(btnNewButton);
		
		
		
		JButton btnNewButton_3 = new JButton("Valorar hotel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					setVisible(false);
					ComentariosI v = new ComentariosI(c);
					v.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.setBounds(493, 91, 132, 56);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Historial de reservas");
		lblNewLabel.setBounds(88, 10, 272, 71);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		contentPane.add(lblNewLabel);
		
		JLabel lblComentarios = new JLabel("Comentarios");
		lblComentarios.setBounds(483, 10, 180, 71);
		lblComentarios.setFont(new Font("Tahoma", Font.BOLD, 23));
		contentPane.add(lblComentarios);
		
		JLabel lblPersonal = new JLabel("Perfil");
		lblPersonal.setBounds(673, 10, 82, 71);
		lblPersonal.setFont(new Font("Tahoma", Font.BOLD, 23));
		contentPane.add(lblPersonal);
		
		JButton btnNewButton_3_1 = new JButton("Mi perfil");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				VerDatosC v;
				try {
					v = new VerDatosC(c);
					v.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_3_1.setBounds(645, 91, 132, 56);
		contentPane.add(btnNewButton_3_1);
		
		JButton btnNewButton_3_2 = new JButton("Modificar usuario");
		btnNewButton_3_2.setBounds(645, 170, 132, 56);
		contentPane.add(btnNewButton_3_2);
		contentPane.setLayout(null);
		
		final DefaultTableModel modelo = new DefaultTableModel();
		PreparedStatement ps = null;
		ResultSet rs = null;
		final Connection con = DBC.createNewDBconnection();

		String sql = "Select H.Nombre, R.* FROM  Users U JOIN reserva R JOIN hoteles H ON H.id = R.id_hotel WHERE U.dni= '"+c.getDni()+"' AND U.id=R.id_cliente AND R.fecha_salida<(SELECT SYSDATE())";

		ps = con.prepareStatement(sql);

		rs = ps.executeQuery();

		ResultSetMetaData rsMd = rs.getMetaData();

		int cantidadColumnas = rsMd.getColumnCount();

		modelo.addColumn("id_hotel");
		modelo.addColumn("Nombre");
		modelo.addColumn("id_habitacion");
		modelo.addColumn("id_usuario");
		modelo.addColumn("fecha_entrada");
		modelo.addColumn("fecha_salida");
		

		while (rs.next()) {
			Object[] filas = new Object[cantidadColumnas];

			for (int i = 0; i < cantidadColumnas; i++) {
				filas[i] = rs.getObject(i + 1);
			}
			modelo.addRow(filas);
		}
		
		final JTable table_1 = new JTable(modelo);
		table_1.setFillsViewportHeight(true);
		table_1.setBounds(10, 67, 446, 223);
		contentPane.add(table_1);
		
		JButton btnNewButton_1 = new JButton("Cancelar reserva");
		btnNewButton_1.setBounds(262, 300, 180, 56);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ReservaCAN ai;
				try {
					ai = new ReservaCAN(c);
					setVisible(false);
					ai.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		
	
	}
}
