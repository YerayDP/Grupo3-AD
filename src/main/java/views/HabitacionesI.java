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
import models.Habitaciones;
import services.HabitacionesS;
import services.UsersS;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class HabitacionesI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_6;
	private JTextField textField_3;
	static private Empleados emp = new Empleados();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HabitacionesI frame = new HabitacionesI(emp);
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
	public HabitacionesI(final Empleados emp) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 300);
		contentPane = new JPanel();
		setLocationRelativeTo( null );
		setTitle("Insertar habitación");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(284, 65, 143, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(284, 111, 143, 36);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(284, 157, 143, 36);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(284, 203, 143, 36);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Numero de habitacion");
		lblNewLabel.setBounds(10, 65, 250, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Precio por noche");
		lblNewLabel_1.setBounds(10, 111, 250, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo de habiatcion");
		lblNewLabel_2.setBounds(10, 157, 267, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_6 = new JLabel("Extras");
		lblNewLabel_6.setBounds(10, 203, 250, 36);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setBounds(472, 65, 159, 135);
		contentPane.add(btnNewButton);
		
		JLabel lblIdHotel = new JLabel("ID Hotel");
		lblIdHotel.setBounds(10, 18, 250, 36);
		contentPane.add(lblIdHotel);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(284, 18, 143, 36);
		contentPane.add(textField_3);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				int id_hotel = Integer.parseInt(textField_3.getText());
				int numHabitaciones = Integer.parseInt(textField.getText());
				int precioNoche = Integer.parseInt(textField_1.getText());
				String tipo = textField_2.getText();
				String extras = textField_6.getText();
				
				Habitaciones hab = new Habitaciones(id_hotel,numHabitaciones, precioNoche, tipo, extras);
				
				try {
					HabitacionesS.insert(hab);
					setVisible(false);
					HabitacionesCRUD HUD = new HabitacionesCRUD(emp);
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
		
		JButton btnNewButton_1 = new JButton("volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Empleado empleado = new Empleado(emp);
					setVisible(false);
					empleado.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(20, 30, 72, 33);
		contentPane.add(btnNewButton_1);
		
	}
}
