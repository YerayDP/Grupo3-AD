package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Empleados;
import models.Hoteles;
import services.DBC;
import services.HotelesS;
import services.UsersS;

import javax.swing.JButton;
import javax.swing.JList;

public class HotelesCRUD extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelesCRUD frame = new HotelesCRUD();
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
	public HotelesCRUD() throws ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.setBounds(550, 39, 224, 72);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				HotelesI ai = new HotelesI();
				setVisible(false);
				ai.setVisible(true);

			}
		});

		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(550, 146, 224, 72);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(550, 253, 224, 72);
		contentPane.add(btnNewButton_3);

		String a = "";
		List<Hoteles> f = HotelesS.select(DBC.createNewDBconnection());
		
		for (int i = 0; i < f.size(); i++) 
		{
			a += f.toString()+"\n";
		}
		
		TextArea textArea = new TextArea(a);
		textArea.setBounds(10, 39, 534, 286);
		contentPane.add(textArea);
	}
}
