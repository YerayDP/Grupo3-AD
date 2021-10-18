package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Empleados;
import models.Habitaciones;
import services.DBC;
import services.HabitacionesS;
import services.UsersS;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.TextArea;
import javax.swing.JScrollPane;

public class Admin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Admin() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 417);
		setLocationRelativeTo( null );
		setTitle("Administración de empleados");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Empleados");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel.setBounds(353, 10, 101, 53);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.setBounds(108, 278, 148, 77);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				AdminI ai = new AdminI();
				setVisible(false);
				ai.setVisible(true);

			}
		});

		final DefaultTableModel modelo = new DefaultTableModel();
		PreparedStatement ps = null;
		ResultSet rs = null;
		final Connection con = DBC.createNewDBconnection();

		String sql = "Select id, dni, nombre, apellidos, fecha_nacimiento, poblacion, username,password from users where poblacion is not null";

		ps = con.prepareStatement(sql);

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
		table_1.setBounds(50, 48, 690, 220);
		contentPane.add(table_1);

		JButton btnNewButton_1 = new JButton("Borrar");
		btnNewButton_1.setBounds(335, 278, 148, 77);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final Connection conD = DBC.createNewDBconnection();

				int row = table_1.getSelectedRow();
				String cell = table_1.getModel().getValueAt(row, 0).toString();
				String sql = "DELETE FROM users where id = " + cell;
				try {
					PreparedStatement pst = conD.prepareStatement(sql);
					pst.execute();
					modelo.fireTableDataChanged();
					JTable table_2 = new JTable(modelo);
					table_2.setBounds(50, 48, 690, 220);
					
					
					contentPane.add(table_2);
					contentPane.invalidate();
					contentPane.validate();
					contentPane.repaint();
					JOptionPane.showMessageDialog(null, "Borrado");
					setVisible(false);
					Admin main = new Admin();

					main.setVisible(true);

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}

		});

		JButton btnNewButton_2 = new JButton("Modificar");
		btnNewButton_2.setBounds(554, 278, 148, 77);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int sr = table_1.getSelectedRow();
				String id = modelo.getValueAt(sr, 0).toString();
				String dni = modelo.getValueAt(sr, 1).toString();
				String nombre = modelo.getValueAt(sr, 2).toString();
				String apellidos = modelo.getValueAt(sr, 3).toString();
				String fecha_nacimiento = modelo.getValueAt(sr, 4).toString();
				String poblacion = modelo.getValueAt(sr, 5).toString();
				String username = modelo.getValueAt(sr, 6).toString();
				String password = modelo.getValueAt(sr, 7).toString();
		
				String NewId= JOptionPane.showInputDialog(null,"Introduzca nuevo ID",id);
				String NewDNI= JOptionPane.showInputDialog(null,"Introduzca nuevo DNI",dni);
				String NewNombre= JOptionPane.showInputDialog(null,"Introduzca nuevo Nombre",nombre);
				String NewApellidos= JOptionPane.showInputDialog(null,"Introduzca nuevos Apellidos",apellidos);
				String NewFecha_nacimiento= JOptionPane.showInputDialog(null,"Introduzca nueva Fecha de nacimiento",fecha_nacimiento);
				String NewPoblacion= JOptionPane.showInputDialog(null,"Introduzca nueva poblacion",poblacion);
				String NewUsername= JOptionPane.showInputDialog(null,"Introduzca nuevo username",username);
				String NewPassword= JOptionPane.showInputDialog(null,"Introduzca nuevo password",password);
				

				String sql = "UPDATE users\r\n"
						+ "SET ID = " + NewId + " , dni = " + NewDNI + " , nombre = '" + NewNombre + "' , apellidos = '" + NewApellidos + 
						"' , fecha_nacimiento = '" + NewFecha_nacimiento + "' , poblacion = '" + NewPoblacion + "' , username = '" + NewUsername + "' , password = '" + NewPassword +"'" ;
			
				try {
					PreparedStatement pst = con.prepareStatement(sql);
					pst.execute();
					modelo.fireTableDataChanged();
					JTable table_2 = new JTable(modelo);
					table_2.setBounds(50, 48, 690, 220);
					
					
					contentPane.add(table_2);
					contentPane.invalidate();
					contentPane.validate();
					contentPane.repaint();
					JOptionPane.showMessageDialog(null, "Actualizado");
					setVisible(false);
					Admin main = new Admin();

					main.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				

			}
		});

	}
}
