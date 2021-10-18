package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import models.Clientes;
import models.Users;
import services.UsersS;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private String ruta = null;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 417);
		contentPane = new JPanel();
		setLocationRelativeTo( null );
		setTitle("Register");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Dni");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel.setBounds(50, 45, 33, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_1.setBounds(28, 78, 89, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_2.setBounds(15, 115, 68, 48);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha de nacimiento");
		lblNewLabel_3.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_3.setBounds(15, 174, 153, 28);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Imagen");
		lblNewLabel_4.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_4.setBounds(253, 45, 68, 26);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Username");
		lblNewLabel_5.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_5.setBounds(15, 213, 86, 31);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Password");
		lblNewLabel_6.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_6.setBounds(15, 255, 86, 31);
		contentPane.add(lblNewLabel_6);
		
		
		textField = new JTextField();
		textField.setBounds(93, 48, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(93, 76, 96, 31);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(93, 120, 134, 35);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(93, 255, 96, 31);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(93, 213, 96, 28);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		final JDateChooser dateChooser = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dateChooser.setBounds(178, 176, 86, 20);
		dateChooser.getJCalendar();
		contentPane.add(dateChooser);

		
		JFileChooser chooser = new JFileChooser();
		chooser.setBounds(331, 45, 86, 97);
		contentPane.add(chooser);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		Component parent = null;
		int returnVal = chooser.showSaveDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			 
			 
			 // obtener ruta
			String selectPath = chooser.getSelectedFile().getPath();
			 System.out.println ("El directorio que elija es:" + selectPath);
			 ruta = selectPath;
			}
		

		JButton btnNewButton = new JButton("Registrarse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

				String dni = textField.getText();
				String nombre = textField_1.getText();
				String apellidos = textField_2.getText();
				Date fn = new java.sql.Date(dateChooser.getDate().getTime());
				String username = textField_4.getText();
				String password = textField_3.getText();
				
				Clientes c = new Clientes(dni, nombre, apellidos,"cliente",username,password,fn ,ruta);
				c.setImagen(ruta);
				try {
					UsersS.insertC(c);
					Cliente cli = new Cliente(c);
					Login l = new Login();
					setVisible(false);
					l.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(331, 178, 164, 97);
		contentPane.add(btnNewButton);
		
			}
	}
