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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import models.Empleados;
import services.DBC;

public class HabLibres extends JFrame {

	private JPanel contentPane;
	private static Date date;
	private static Empleados emp = new Empleados();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HabLibres frame = new HabLibres(date);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param dateChooser 
	 * @throws SQLException 
	 */
	public HabLibres(Date fecha) throws SQLException {
		System.out.println(fecha);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 417);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final DefaultTableModel modelo = new DefaultTableModel();
		PreparedStatement ps = null;
		ResultSet rs = null;
		final Connection con = DBC.createNewDBconnection();

		String sql = "SELECT DISTINCT HO.nombre,H.NUMHabitacion FROM habitaciones H, hoteles HO WHERE H.ID NOT IN (SELECT DISTINCT id_habitacion FROM reserva WHERE '" +fecha+ "' BETWEEN fecha_entrada AND fecha_salida) AND HO.id=H.id_hotel";

		ps = con.prepareStatement(sql);

		rs = ps.executeQuery();

		ResultSetMetaData rsMd = rs.getMetaData();

		int cantidadColumnas = rsMd.getColumnCount();

		modelo.addColumn("");
		modelo.addColumn("");


		while (rs.next()) {
			Object[] filas = new Object[cantidadColumnas];

			for (int i = 0; i < cantidadColumnas; i++) {
				filas[i] = rs.getObject(i + 1);
			}
			modelo.addRow(filas);
		}
		
		

		final JTable table_1 = new JTable(modelo);
		table_1.setBounds(45, 53, 690, 258);
		contentPane.add(table_1);
		
		JButton btnNewButton_1 = new JButton("volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empleado emple;
				try {
					emple = new Empleado(emp);
					setVisible(false);
					emple.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.setBounds(10, 10, 72, 33);
		contentPane.add(btnNewButton_1);

		
	}

}
