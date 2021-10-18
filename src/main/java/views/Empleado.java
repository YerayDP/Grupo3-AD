package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import models.Comentarios;
import models.Empleados;
import services.ComentariosS;
import services.DBC;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Empleado extends JFrame {

	private JPanel contentPane;
	static private Empleados emp = new Empleados();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Empleado frame = new Empleado(emp);
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
	public Empleado(final Empleados emp) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 456);
		contentPane = new JPanel();
		setLocationRelativeTo( null );
		setTitle("Empleado");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnNewButton = new JButton("Ver valoraciones");
		btnNewButton.setBounds(40, 322, 141, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Consultar habitaciones libres ");
		btnNewButton_1.setBounds(285, 310, 214, 64);
		contentPane.add(btnNewButton_1);
		
		final JDateChooser date = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		date.setBounds(509, 322, 127, 27);
		date.getJCalendar();
		contentPane.add(date);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					Date fecha = new java.sql.Date(date.getDate().getTime());
					HabLibres hl = new HabLibres(fecha);
					setVisible(false);
					hl.setVisible(true);
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
							
			}
		});
		

		
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					Valoraciones v = new Valoraciones(emp);
					setVisible(false);
					v.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
							
			}
		});
		
		
		
		
		
		final DefaultTableModel modelo = new DefaultTableModel();
		PreparedStatement ps = null;
		ResultSet rs = null;
		final Connection con = DBC.createNewDBconnection();

		String sql = "Select id, dni, nombre, apellidos, fecha_nacimiento, poblacion, username,password from users WHERE dni=?";

		ps = con.prepareStatement(sql);
		
		ps.setString(1,emp.getDni());

		rs = ps.executeQuery();

		ResultSetMetaData rsMd = rs.getMetaData();

		int cantidadColumnas = rsMd.getColumnCount();

		modelo.addColumn("ID");
		modelo.addColumn("DNI");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellidos");
		modelo.addColumn("Fecha_nacimiento");
		modelo.addColumn("Poblacion");
		modelo.addColumn("Username");
		modelo.addColumn("Password");

		while (rs.next()) {
			Object[] filas = new Object[cantidadColumnas];

			for (int i = 0; i < cantidadColumnas; i++) {
				filas[i] = rs.getObject(i + 1);
			}
			modelo.addRow(filas);
		}
		
		

		final JTable table_1 = new JTable(modelo);
		table_1.setBounds(40, 48, 596, 16);
		contentPane.add(table_1);
		
		
		JLabel lblNewLabel_1 = new JLabel("Introduzca fecha");
		lblNewLabel_1.setBounds(519, 360, 107, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Administracion habitaciones");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HabitacionesCRUD hc = new HabitacionesCRUD(emp);
					setVisible(false);
					hc.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(55, 132, 160, 40);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Administracion de hoteles");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HotelesCRUD hoc = new HotelesCRUD(emp);
					setVisible(false);
					hoc.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(302, 132, 160, 40);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Ver clientes hospedados");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Consultar co = new Consultar();
					setVisible(false);
					co.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(48, 217, 167, 40);
		contentPane.add(btnNewButton_4);
		
		
	}
}
