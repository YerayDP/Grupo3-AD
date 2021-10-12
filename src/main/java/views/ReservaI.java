package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import models.Empleados;
import models.Reservas;
import services.ReservasS;
import services.UsersS;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ReservaI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservaI frame = new ReservaI();
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
	public ReservaI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		final JDateChooser dateChooser = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dateChooser.setBounds(247, 179, 147, 32);
		dateChooser.getJCalendar();
		contentPane.add(dateChooser);
		
		final JDateChooser dateChooser2 = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		
		dateChooser2.setBounds(247, 136, 147, 32);
		dateChooser2.getJCalendar();
		contentPane.add(dateChooser2);;
		
		
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(247, 284, 147, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Fecha_inicio:");
		lblNewLabel.setBounds(164, 179, 73, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblFechafin = new JLabel("Fecha_fin:");
		lblFechafin.setBounds(164, 136, 59, 32);
		contentPane.add(lblFechafin);
		
		JLabel lblHotel = new JLabel("Hotel:");
		lblHotel.setBounds(164, 50, 38, 32);
		contentPane.add(lblHotel);
		
		JLabel lblHabitacin = new JLabel("Habitación:");
		lblHabitacin.setBounds(164, 93, 59, 32);
		contentPane.add(lblHabitacin);
		
		textField_1 = new JTextField();
		textField_1.setBounds(247, 93, 147, 32);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(247, 50, 147, 32);
		contentPane.add(textField_2);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				int hotel = Integer.parseInt(textField_1.getText());
				Date fecha_inicio = new java.sql.Date(dateChooser.getDate().getTime());
				Date fecha_fin = new java.sql.Date(dateChooser.getDate().getTime());
				int habitacion = Integer.parseInt(textField_2.getText());

				Reservas res = new Reservas(1,hotel,habitacion,fecha_inicio,fecha_fin);

				try {
					ReservasS.insert(res);
					setVisible(false);
					//
					//Cliente
					//
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
							
			}
		});
	}
}
