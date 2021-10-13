package views;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Register extends JFrame {
	public static void main(String[] args) {
		new Register();
	}
	public Register() {
		setTitle("Register");
		setSize(480, 240);
		setResizable(false);
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
		panel.setLayout(new GridLayout(4, 2, 10, 10));
		panel.add(new JLabel("Username:"));
		panel.add(new JTextField(15));
		panel.add(new JLabel("Password:"));
		panel.add(new JPasswordField());
		JButton register = new JButton("Register");
		register.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(register);
		add(panel);
	    setVisible(true);
	}
}
