package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Home extends JFrame {

	private JPanel contentPane;
	ImageIcon perro = new ImageIcon("resources/icono.jpeg");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 417);
		contentPane = new JPanel();
		setLocationRelativeTo( null );
		setTitle("Inicio");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido al sistema de gestion de hoteles");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 18));
		lblNewLabel.setBounds(171, 32, 396, 42);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Registrarse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
				Register r = new Register();
				r.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Sitka Text", Font.BOLD, 15));
		btnNewButton.setBounds(213, 205, 130, 54);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
				Login l = new Login();
				l.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Sitka Text", Font.BOLD, 15));
		btnNewButton_1.setBounds(472, 205, 130, 54);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("¿Que desea hacer?");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_1.setBounds(322, 106, 147, 42);
		contentPane.add(lblNewLabel_1);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setForeground(Color.GRAY);
		lblNewLabel_7.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\Pablo\\Desktop\\Hotell.PNG"));
		lblNewLabel_7.setBounds(10, 25, 72, 123);
		contentPane.add(lblNewLabel_7);
	}

}
