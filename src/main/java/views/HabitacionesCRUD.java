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

import models.Habitaciones;
import models.Hoteles;
import services.DBC;
import services.HabitacionesS;
import services.HotelesS;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class HabitacionesCRUD extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HabitacionesCRUD frame = new HabitacionesCRUD();
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
	public HabitacionesCRUD() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 375);
		contentPane = new JPanel();
		setLocationRelativeTo( null );
		setTitle("Administración de habitaciones");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.setBounds(550, 39, 224, 72);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				HabitacionesI ai = new HabitacionesI();
				setVisible(false);
				ai.setVisible(true);

			}
		});
		
		final DefaultTableModel modelo = new DefaultTableModel();
		PreparedStatement ps = null;
		ResultSet rs = null;
		final Connection con = DBC.createNewDBconnection();

		String sql = "Select * FROM habitaciones";

		ps = con.prepareStatement(sql);

		rs = ps.executeQuery();

		ResultSetMetaData rsMd = rs.getMetaData();

		int cantidadColumnas = rsMd.getColumnCount();

		modelo.addColumn("ID");
		modelo.addColumn("id_hotel");
		modelo.addColumn("numHabitacion");
		modelo.addColumn("precioNoche");
		modelo.addColumn("tipo");
		modelo.addColumn("extras");
		

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

		JButton btnNewButton_2 = new JButton("Borrar");
		btnNewButton_2.setBounds(550, 146, 224, 72);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final Connection conD = DBC.createNewDBconnection();

				int row = table_1.getSelectedRow();
				String cell = table_1.getModel().getValueAt(row, 0).toString();
				String sql = "DELETE FROM habitaciones where id = " + cell;
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
					HabitacionesCRUD HUD = new HabitacionesCRUD();

					HUD.setVisible(true);

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}

		});
		
		

		JButton btnNewButton_3 = new JButton("Modificar");
		btnNewButton_3.setBounds(550, 253, 224, 72);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int sr = table_1.getSelectedRow();
				String ID = modelo.getValueAt(sr, 0).toString();
				String numHabitacion = modelo.getValueAt(sr, 1).toString();
				String precioNoche = modelo.getValueAt(sr, 2).toString();
				String tipo = modelo.getValueAt(sr, 3).toString();
				String extras = modelo.getValueAt(sr, 4).toString();

		
				String NewNumHab= JOptionPane.showInputDialog(null,"Introduzca nuevo nombre",numHabitacion);
				String NewPreNoc= JOptionPane.showInputDialog(null,"Introduzca nuevo precio por noche",precioNoche);
				String NewTipo= JOptionPane.showInputDialog(null,"Introduzca nuevo tipo",tipo);
				String NewExtra= JOptionPane.showInputDialog(null,"Introduzca nuevos extras",extras);
			
				

				String sql = "UPDATE habitaciones\r\n"
						+ "SET  numHabitacion = '" + NewNumHab + "' , precioNoche = '" + NewPreNoc + "' , tipo = '" + NewTipo + 
						"' , extras = '" + NewExtra + "' WHERE ID = " +ID ;
			
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
					HabitacionesCRUD HUD = new HabitacionesCRUD();

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
