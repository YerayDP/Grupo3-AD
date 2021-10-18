package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Clientes;
import models.Empleados;
import services.DBC;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VerDatosC extends JFrame {

	private JPanel contentPane;
	static private Clientes c = new Clientes();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerDatosC frame = new VerDatosC(c);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public VerDatosC(final Clientes c) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel(c.getNombre());
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel.setBounds(168, 62, 182, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre :");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_1.setBounds(64, 62, 94, 46);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(c.getImagen()));
		lblNewLabel_2.setBounds(464, 10, 299, 280);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Apellidos :");
		lblNewLabel_3.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_3.setBounds(64, 118, 82, 32);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(c.getApellidos());
		lblNewLabel_4.setBounds(168, 114, 161, 36);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Usuario :");
		lblNewLabel_5.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_5.setBounds(52, 213, 96, 32);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Contraseña :");
		lblNewLabel_6.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_6.setBounds(52, 255, 94, 46);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Fecha :");
		lblNewLabel_7.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblNewLabel_7.setBounds(57, 160, 89, 32);
		contentPane.add(lblNewLabel_7);
		
		@SuppressWarnings("deprecation")
		JLabel lblNewLabel_8 = new JLabel(c.getFecha_nacimiento().toGMTString());
		lblNewLabel_8.setBounds(159, 160, 170, 32);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel(c.getUsername());
		lblNewLabel_9.setBounds(158, 213, 161, 32);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel(c.getPassword());
		lblNewLabel_10.setBounds(168, 255, 132, 32);
		contentPane.add(lblNewLabel_10);
		
		
		System.out.println(c.getImagen());

		
		JButton btnNewButton_5 = new JButton("volver");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Cliente cliente = new Cliente(c);
					setVisible(false);
					cliente.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		final DefaultTableModel modelo = new DefaultTableModel();
        PreparedStatement ps = null;
        ResultSet rs = null;
        final Connection con = DBC.createNewDBconnection();

        String sql = "Select id, dni, nombre, apellidos, fecha_nacimiento, imagen, username,password from users WHERE dni=?";

        ps = con.prepareStatement(sql);

        ps.setString(1,c.getDni());

        rs = ps.executeQuery();

        ResultSetMetaData rsMd = rs.getMetaData();

        int cantidadColumnas = rsMd.getColumnCount();

        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Fecha_nacimiento");
        modelo.addColumn("Imagen");
        modelo.addColumn("Username");
        modelo.addColumn("Password");

        while (rs.next()) {
            Object[] filas = new Object[cantidadColumnas];

            for (int i = 0; i < cantidadColumnas; i++) {
                filas[i] = rs.getObject(i + 1);
            }
            modelo.addRow(filas);
        }
		
		
		 final JTable table_1 = new JTable(modelo);
	        table_1.setEnabled(false);
	        table_1.setBounds(10, 11, 596, 16);
	        contentPane.add(table_1);
	        table_1.setVisible(false);
	        
		btnNewButton_5.setBounds(10, 10, 72, 33);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton = new JButton("Modificar datos");
        btnNewButton.setBounds(52, 312, 189, 55);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                
                String dni = modelo.getValueAt(0, 1).toString();
                String nombre = modelo.getValueAt(0, 2).toString();
                String apellidos = modelo.getValueAt(0, 3).toString();
                String fecha_nacimiento = modelo.getValueAt(0,4).toString();
                String imagen = modelo.getValueAt(0, 5).toString();
                String username = modelo.getValueAt(0, 6).toString();
                String password = modelo.getValueAt(0, 7).toString();
        
                String NewDNI= JOptionPane.showInputDialog(null,"Introduzca nuevo DNI",dni);
                String NewNombre= JOptionPane.showInputDialog(null,"Introduzca nuevo Nombre",nombre);
                String NewApellidos= JOptionPane.showInputDialog(null,"Introduzca nuevos Apellidos",apellidos);
                String NewFecha_nacimiento= JOptionPane.showInputDialog(null,"Introduzca nueva Fecha de nacimiento",fecha_nacimiento);
                String NewImagen= JOptionPane.showInputDialog(null,"Introduzca nueva imagen",imagen);
                String NewUsername= JOptionPane.showInputDialog(null,"Introduzca nuevo username",username);
                String NewPassword= JOptionPane.showInputDialog(null,"Introduzca nuevo password",password);
                

                String sql = "UPDATE users\r\n"
                        + "SET dni = " + NewDNI + " , nombre = '" + NewNombre + "' , apellidos = '" + NewApellidos + 
                        "' , fecha_nacimiento = '" + NewFecha_nacimiento + "' , imagen = '" + NewImagen + "' , username = '" + NewUsername + "' , password = '" + NewPassword +"' WHERE dni = '"+c.getDni()+"'" ;
            
                try {
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.execute();
                    modelo.fireTableDataChanged();
                    JTable table_2 = new JTable(modelo);
                    table_2.setBounds(50, 48, 690, 220);
                    
                    
                    contentPane.add(table_2);
                    contentPane.invalidate();
                    contentPane.validate();
                    contentPane.repaint();
                    JOptionPane.showMessageDialog(null, "Actualizado");
                    setVisible(false);
                    Cliente cs = new Cliente(c);

                    cs.setVisible(true);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                
                

            }
        });
		
}
}
