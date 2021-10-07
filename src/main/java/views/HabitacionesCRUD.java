package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JList;

public class HabitacionesCRUD extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HabitacionesCRUD frame = new HabitacionesCRUD();
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
	public HabitacionesCRUD() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.setBounds(550, 39, 224, 72);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(550, 146, 224, 72);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(550, 253, 224, 72);
		contentPane.add(btnNewButton_3);
		
		JList list = new JList();
		list.setBounds(10, 39, 495, 286);
		contentPane.add(list);
	}
}
