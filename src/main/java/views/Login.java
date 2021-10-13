package views;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Login extends JFrame {
	public static void main(String[] args) {
		new Login();
	}
	public Login() {
		setTitle("Login");
		setSize(480, 240);
		setResizable(false);
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
		panel.setLayout(new GridLayout(4, 2, 10, 10));
		panel.add(new JLabel("Username:"));
		panel.add(new JTextField(15));
		panel.add(new JLabel("Password:"));
		panel.add(new JPasswordField());
		JButton login = new JButton("Login");
		login.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(login);
		add(panel);
	    setVisible(true);
	}
}
