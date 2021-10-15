package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Comentarios;
import services.ComentariosS;
import services.DBC;
import services.HotelesS;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class Valoraciones extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Valoraciones frame = new Valoraciones();
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
	public Valoraciones() throws ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JComboBox<Integer> comboBox = new JComboBox<Integer>();
		
		List<Integer> ids = HotelesS.ids(DBC.createNewDBconnection());
		for (Integer id : ids) {
			comboBox.addItem(id);
			
		}
		comboBox.setBounds(20, 100, 121, 21);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Elija un hotel");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 10, 131, 68);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(20, 147, 121, 53);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					JLabel lblNewLabel_1 = new JLabel("ID Usuario");
					lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 15));
					lblNewLabel_1.setBounds(171, 25, 131, 38);
					contentPane.add(lblNewLabel_1);
					
					JLabel lblNewLabel_2 = new JLabel("Comentario");
					lblNewLabel_2.setFont(new Font("Sitka Text", Font.BOLD, 15));
					lblNewLabel_2.setBounds(491, 25, 152, 38);
					contentPane.add(lblNewLabel_2);
					String hotel = comboBox.getSelectedItem().toString();
					
					try {
						List<Comentarios> cs = ComentariosS.comentarios(DBC.createNewDBconnection(), Integer.valueOf (hotel));
						for (Comentarios c : cs) {
							System.out.println(c);
							
							JTable table_1 = new JTable();
					        table_1.setBounds(152, 64, 483, 170);
					        contentPane.add(table_1);
							
							DefaultTableModel modelo  = (DefaultTableModel) table_1.getModel();
					        
					        PreparedStatement ps = null;
					        ResultSet rs = null;
					        Connection con = DBC.createNewDBconnection();
					        
					        //String dni = modelo.getValueAt(table_1.getSelectedRow(), 0).toString();

					        String sql = "Select id_cliente,comentario from comentarios where id_hotel=?";

					        ps = con.prepareStatement(sql);
					        
					        ps.setInt(1,Integer.valueOf(hotel));

					        rs = ps.executeQuery();

					        ResultSetMetaData rsMd = rs.getMetaData();

					        int cantidadColumnas = rsMd.getColumnCount();

					        modelo.addColumn("Dni");
					        modelo.addColumn("Dni");


					        while(rs.next())
					        {
					            Object[] filas = new Object[cantidadColumnas];

					            for (int i = 0; i < cantidadColumnas; i++) {
					                filas[i] = rs.getObject(i+1);
					            }
					            modelo.addRow(filas);
					        }
						}
					} catch (NumberFormatException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
							
			}
		});
	}
}
