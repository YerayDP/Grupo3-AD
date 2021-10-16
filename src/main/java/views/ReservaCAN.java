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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Clientes;
import services.DBC;

public class ReservaCAN extends JFrame {

	private JPanel contentPane;
	static private Clientes c = new Clientes();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservaCAN frame = new ReservaCAN(c);
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
	public ReservaCAN(Clientes c) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 359);
		setLocationRelativeTo( null );
		setTitle("Cancelación de reserva");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		final DefaultTableModel modelo = new DefaultTableModel();
		PreparedStatement ps = null;
		ResultSet rs = null;
		final Connection con = DBC.createNewDBconnection();

		String sql = "SELECT H.Nombre, R.*,U.dni FROM reserva R JOIN users U JOIN hoteles H ON H.id = R.id_hotel WHERE U.dni='"+c.getDni()+"' AND R.fecha_entrada>(SELECT SYSDATE() + 2)";

		ps = con.prepareStatement(sql);

		rs = ps.executeQuery();

		ResultSetMetaData rsMd = rs.getMetaData();

		int cantidadColumnas = rsMd.getColumnCount();

		modelo.addColumn("id_hotel");
		modelo.addColumn("Nombre");
		modelo.addColumn("id_habitacion");
		modelo.addColumn("id_usuario");
		modelo.addColumn("fecha_entrada");
		modelo.addColumn("fecha_salida");
		modelo.addColumn("fecha_salida");
		

		while (rs.next()) {
			Object[] filas = new Object[cantidadColumnas];

			for (int i = 0; i < cantidadColumnas; i++) {
				filas[i] = rs.getObject(i + 1);
			}
			modelo.addRow(filas);
		}
		contentPane.setLayout(null);
		
		final JTable table_1 = new JTable(modelo);
		table_1.setFillsViewportHeight(true);
		table_1.setBounds(10, 11, 446, 234);
		contentPane.add(table_1);
		
		JButton Cancelar = new JButton("Cancelar");
		Cancelar.setBounds(143, 256, 172, 53);
		contentPane.add(Cancelar);
		Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				final Connection conD = DBC.createNewDBconnection();

				int row = table_1.getSelectedRow();
				String cell = table_1.getModel().getValueAt(row, 4).toString();
					
					String sql = "DELETE FROM reserva WHERE fecha_entrada = '"+cell+"'";
					
					String today=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					System.out.println(cell.getClass().getSimpleName());
					
		            
					try {
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

						Date date1 = format.parse(cell);
						Date date2 = format.parse(today);
			           System.out.println(date1.before(date2));
			           
			            
						try {
							PreparedStatement pst = conD.prepareStatement(sql);
							pst.execute();
							modelo.fireTableDataChanged();
							JTable table_2 = new JTable(modelo);
							table_2.setBounds(10, 67, 446, 223);
							
							
							contentPane.add(table_2);
							contentPane.invalidate();
							contentPane.validate();
							contentPane.repaint();
							JOptionPane.showMessageDialog(null, "Borrado");
							setVisible(false);

						} catch (Exception e2) {
							// TODO: handle exception
						}
					} catch (ParseException e1) {
						System.out.println("Muy pronto");
						e1.printStackTrace();
					}
					
					

				}
				
		});
		
	}
}
