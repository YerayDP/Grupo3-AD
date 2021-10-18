package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import models.Clientes;
import models.Comentarios;
import models.Empleados;
import services.ComentariosS;
import services.DBC;
import services.UsersS;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class ComentariosI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	private JButton btnNewButton;
	static private Clientes cli = new Clientes();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComentariosI frame = new ComentariosI(cli);
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
	public ComentariosI(final Clientes cli) throws ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 417);
		contentPane = new JPanel();
		setLocationRelativeTo( null );
		setTitle("Insertar comentarios");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Hotel");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_1.setBounds(57, 119, 123, 38);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Comentario");
		lblNewLabel_2.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_2.setBounds(57, 193, 123, 38);
		contentPane.add(lblNewLabel_2);
		
		final JTextArea textField_2 = new JTextArea();
		textField_2.setBounds(190, 167, 245, 90);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		
		List<String> hoteles = ComentariosS.Hoteles(DBC.createNewDBconnection(),cli.getDni());
		for (String hotel : hoteles) {
			comboBox.addItem(hotel);
			
		}
		comboBox.setBounds(190, 120, 133, 32);
		contentPane.add(comboBox);
		
		
		
		btnNewButton = new JButton("Valorar");
		btnNewButton.setBounds(570, 71, 123, 132);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String hotel = (String) comboBox.getSelectedItem();
				
				try {
					int id_hotel = ComentariosS.id_hotel(DBC.createNewDBconnection(), hotel);
					Comentarios c = new Comentarios();
					c.setId_hotel(id_hotel);
					c.setComentario(textField_2.getText());
					try {
						int id = ComentariosS.id(DBC.createNewDBconnection(), cli.getDni());
						ComentariosS.insert(c,id);
						setVisible(false);
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
		
		JButton btnNewButton_5 = new JButton("volver");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Cliente cliente = new Cliente(cli);
					setVisible(false);
					cliente.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_5.setBounds(10, 10, 72, 33);
		contentPane.add(btnNewButton_5);
		
	}
}
