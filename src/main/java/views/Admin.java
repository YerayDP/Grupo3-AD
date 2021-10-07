package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Empleados;
import services.DBC;
import services.UsersS;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.TextArea;

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
	 * @throws ClassNotFoundException 
	 */
	public Admin() throws ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 417);
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
		
		JButton btnNewButton_1 = new JButton("Borrar");
		btnNewButton_1.setBounds(335, 278, 148, 77);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Modificar");
		btnNewButton_2.setBounds(554, 278, 148, 77);
		contentPane.add(btnNewButton_2);
		
		String a = "";
		List<Empleados> f = UsersS.select(DBC.createNewDBconnection());
		
		for (int i = 0; i < f.size(); i++) 
		{
			a += f.toString()+"\n";
		}
		
		TextArea textArea = new TextArea(a);
		textArea.setBounds(172, 69, 440, 150);
		contentPane.add(textArea);
	}
}
