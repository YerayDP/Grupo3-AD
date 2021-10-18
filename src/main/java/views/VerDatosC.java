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

import models.Clientes;
import models.Empleados;
import services.DBC;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class VerDatosC extends JFrame {

	private JPanel contentPane;
	static private Clientes c = new Clientes();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerDatosC frame = new VerDatosC(c);
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
	public VerDatosC(Clientes c) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel(c.getNombre());
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel.setBounds(168, 62, 182, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre :");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_1.setBounds(64, 62, 94, 46);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Pablo\\Desktop\\yo2.PNG"));
		lblNewLabel_2.setBounds(464, 10, 299, 280);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Apellidos :");
		lblNewLabel_3.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_3.setBounds(64, 118, 82, 32);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(c.getApellidos());
		lblNewLabel_4.setBounds(168, 114, 161, 36);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Usuario :");
		lblNewLabel_5.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_5.setBounds(52, 213, 96, 32);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Contraseña :");
		lblNewLabel_6.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_6.setBounds(52, 255, 94, 46);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Fecha :");
		lblNewLabel_7.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_7.setBounds(57, 160, 89, 32);
		contentPane.add(lblNewLabel_7);
		
		@SuppressWarnings("deprecation")
		JLabel lblNewLabel_8 = new JLabel(c.getFecha_nacimiento().toGMTString());
		lblNewLabel_8.setBounds(159, 160, 170, 32);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel(c.getUsername());
		lblNewLabel_9.setBounds(158, 213, 161, 32);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel(c.getPassword());
		lblNewLabel_10.setBounds(168, 255, 132, 32);
		contentPane.add(lblNewLabel_10);
		
		
		System.out.println(c.getImagen());

}
}
