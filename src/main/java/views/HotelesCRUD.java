package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Empleados;
import models.Hoteles;
import services.DBC;
import services.HotelesS;
import services.UsersS;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class HotelesCRUD extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelesCRUD frame = new HotelesCRUD();
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
	 * @throws SQLException 
	 */
	public HotelesCRUD() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 375);
		contentPane = new JPanel();
		setLocationRelativeTo( null );
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.setBounds(550, 10, 224, 82);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				HotelesI ai = new HotelesI();
				setVisible(false);
				ai.setVisible(true);

			}
		});

		final DefaultTableModel modelo = new DefaultTableModel();
		PreparedStatement ps = null;
		ResultSet rs = null;
		final Connection con = DBC.createNewDBconnection();

		String sql = "Select * FROM hoteles";

		ps = con.prepareStatement(sql);

		rs = ps.executeQuery();

		ResultSetMetaData rsMd = rs.getMetaData();

		int cantidadColumnas = rsMd.getColumnCount();

		modelo.addColumn("ID");
		modelo.addColumn("Nombre");
		modelo.addColumn("Descripcion");
		modelo.addColumn("Ciudad");
		modelo.addColumn("Direccion");
		modelo.addColumn("Telefono");
		

		while (rs.next()) {
			Object[] filas = new Object[cantidadColumnas];

			for (int i = 0; i < cantidadColumnas; i++) {
				filas[i] = rs.getObject(i + 1);
			}
			modelo.addRow(filas);
		}
		
		

		final JTable table_1 = new JTable(modelo);
		table_1.setBounds(10, 10, 530, 315);
		contentPane.add(table_1);

		JButton btnNewButton_1 = new JButton("Borrar");
		btnNewButton_1.setBounds(550, 243, 224, 82);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final Connection conD = DBC.createNewDBconnection();

				int row = table_1.getSelectedRow();
				String cell = table_1.getModel().getValueAt(row, 0).toString();
				String sql = "DELETE FROM hoteles where id = " + cell;
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
					HotelesCRUD HUD = new HotelesCRUD();

					HUD.setVisible(true);

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}

		});

		JButton btnNewButton_2 = new JButton("Modificar");
		btnNewButton_2.setBounds(550, 127, 224, 82);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int sr = table_1.getSelectedRow();
				String nombre = modelo.getValueAt(sr, 1).toString();
				String descripcion = modelo.getValueAt(sr, 2).toString();
				String ciudad = modelo.getValueAt(sr, 3).toString();
				String direccion = modelo.getValueAt(sr, 4).toString();
				String telefono = modelo.getValueAt(sr, 5).toString();
	
		
				String NewNombre= JOptionPane.showInputDialog(null,"Introduzca nuevo nombre",nombre);
				String NewDesc= JOptionPane.showInputDialog(null,"Introduzca nueva descripcion",descripcion);
				String NewCiudad= JOptionPane.showInputDialog(null,"Introduzca nueva ciudad",ciudad);
				String NewDir= JOptionPane.showInputDialog(null,"Introduzca nueva direccion",direccion);
				String NewTel= JOptionPane.showInputDialog(null,"Introduzca nuevo telefono",telefono);
		
				

				String sql = "UPDATE hoteles \r\n "
						+ "SET direccion = NULL WHERE telefono = '"+telefono+"'"
						+ " ;UPDATE hoteles \r\n "
						+ "SET telefono = NULL WHERE nombre = '"+nombre+"'"
						+ " ;UPDATE hoteles \r\n"
						+ "SET  nombre = '" + NewNombre + "' , descripcion = '" + NewDesc + "' , ciudad = '" + NewCiudad + 
						"' , direccion = '" + NewDir + "' , telefono = '" + NewTel + "' WHERE nombre = '" + nombre + "'" ;
			
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
					HotelesCRUD HUD = new HotelesCRUD();

					HUD.setVisible(true);
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
