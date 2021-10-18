package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import models.Clientes;
import models.Reservas;
import services.ComentariosS;
import services.DBC;
import services.ReservasS;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ReservaI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	static private Clientes c = new Clientes();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservaI frame = new ReservaI(c);
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
	public ReservaI(final Clientes c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 385);
		contentPane = new JPanel();
		setLocationRelativeTo( null );
		setTitle("Realizar reserva");
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
		
		
		
		JButton btnNewButton = new JButton("Realizar Reserva");
		btnNewButton.setBounds(247, 284, 147, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Fecha_inicio:");
		lblNewLabel.setBounds(164, 136, 73, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblFechafin = new JLabel("Fecha_fin:");
		lblFechafin.setBounds(164, 179, 59, 32);
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
				int id2;
				try {
					id2 = ComentariosS.id(DBC.createNewDBconnection(), c.getDni());
					int hotel = Integer.parseInt(textField_1.getText());
					Date fecha_inicio = new java.sql.Date(dateChooser2.getDate().getTime());
					Date fecha_fin = new java.sql.Date(dateChooser.getDate().getTime());
					int habitacion = Integer.parseInt(textField_2.getText());

					Reservas res = new Reservas(hotel,id2,habitacion,fecha_inicio,fecha_fin);
					try {
						int id = ComentariosS.id(DBC.createNewDBconnection(), c.getDni());
						ReservasS.insert(res,id);
						setVisible(false);
						Cliente cl = new Cliente(c);
						cl.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				

				
				
							
			}
		});
	}
}
