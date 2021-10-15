package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import models.Empleados;
import models.Hoteles;
import services.HotelesS;
import services.UsersS;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class HotelesI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelesI frame = new HotelesI();
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
	public HotelesI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 456);
		contentPane = new JPanel();
		setLocationRelativeTo( null );
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(284, 42, 143, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(284, 88, 143, 36);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(284, 134, 143, 36);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
		textField_4 = new JTextField();
		textField_4.setBounds(284, 180, 143, 36);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(284, 226, 143, 36);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 42, 250, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(10, 88, 250, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ciudad");
		lblNewLabel_2.setBounds(10, 134, 267, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Dirección");
		lblNewLabel_3.setBounds(10, 180, 250, 36);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Telefono");
		lblNewLabel_4.setBounds(10, 226, 250, 36);
		contentPane.add(lblNewLabel_4);

		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setBounds(470, 134, 159, 135);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				String nombre = textField.getText();
				String descripcion = textField_1.getText();
				String ciudad = textField_2.getText();
				String direccion = textField_4.getText();
				String telefono = textField_5.getText();

				
				Hoteles ho = new Hoteles(nombre, descripcion, ciudad, direccion, telefono);
				
				try {
					HotelesS.insert(ho);
					setVisible(false);
					HotelesCRUD H = new HotelesCRUD();
					H.setVisible(true);
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
