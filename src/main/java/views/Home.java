package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Home extends JFrame {

	private JPanel contentPane;
	ImageIcon icono = new ImageIcon("resources/icon.jpg");

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("H");
		lblNewLabel_7.setBackground(Color.WHITE);
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\Pablo\\eclipse-workspace\\Grupo3_AD\\resources\\icon.jpg"));
		lblNewLabel_7.setBounds(10, 20, 132, 180);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(97, 187, 107, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Registro");
		lblNewLabel_1.setBounds(314, 173, 141, 37);
		contentPane.add(lblNewLabel_1);
	}
}
