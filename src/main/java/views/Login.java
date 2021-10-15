package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import models.Empleados;
import models.Users;
import services.DBC;
import services.UsersS;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 417);
		contentPane = new JPanel();
		setLocationRelativeTo( null );
		setTitle("Login");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(359, 118, 192, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(313, 183, 195, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Introduzca su Nombre de usuario : ");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel.setBounds(35, 118, 270, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Introduzca su contraseña : ");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_1.setBounds(35, 180, 210, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Inicio de sesión");
		lblNewLabel_2.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_2.setBounds(291, 23, 149, 48);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Inicio de sesión");
		btnNewButton.setBounds(290, 272, 150, 62);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					List<Empleados> users = UsersS.selectt(DBC.createNewDBconnection());
					
					boolean registrado;
					
					for (Empleados em : users) {
						
						if(em.getUsername().equals(textField.getText()) && em.getPassword().equals(textField_1.getText()) && em.getRol().equals("empleado"))
						{
							registrado=false;
							Empleado emp = new Empleado(em);
							setVisible(false);
							emp.setVisible(true);
						}
						else if(em.getUsername().equals(textField.getText()) && em.getPassword().equals(textField_1.getText()) && em.getRol().equals("cliente"))
						{
							registrado=false;
							Cliente c = new Cliente();
							setVisible(false);
							c.setVisible(true);
						}
						
					}
					
					if(registrado=false)
					{
					Home h = new Home();
					setVisible(false);
					h.setVisible(true);
					}
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
}
