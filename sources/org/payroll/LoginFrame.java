package org.payroll;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JLabel lbl_info, lbl_username, lbl_password;
	JButton btn_login, btn_exit;
	JTextField txt_username;
	JPasswordField txt_password;
	
	public LoginFrame() {
		initFrame();
		initComponents();
		addActionListeners();
		addComponentsToFrame();
	}
	
	void initFrame() {
		setTitle("Login");
		setSize(300, 140);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new FlowLayout());
	}
	
	void initComponents() {
		lbl_info = new JLabel("Employee Payroll System by Sanjan Geet Singh");
		lbl_username = new JLabel("Username: ");
		lbl_password = new JLabel("Password: ");
		txt_username = new JTextField(18);
		txt_password = new JPasswordField(18);
		btn_exit = new JButton("Exit");
		btn_login = new JButton("Login");
	}
	
	void addActionListeners() {
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Main.dbManager.verifyLoginId(txt_username.getText(), new String(txt_password.getPassword()))) {
					LoginSuccessful();
				} else {
					LoginFailed();
				}
			}
		});
	}
	
	void addComponentsToFrame() {
		add(lbl_info);
		add(lbl_username);
		add(txt_username);
		add(lbl_password);
		add(txt_password);
		add(btn_exit);
		add(btn_login);
	}
	
	void LoginSuccessful() {
		JOptionPane.showMessageDialog(
				null,
				"Login Successful",
				"Login Successful",
				JOptionPane.INFORMATION_MESSAGE
			);
		
		setVisible(false);
		(new MainFrame(txt_username.getText())).setVisible(true);
		dispose();
	}
	
	void LoginFailed() {
		JOptionPane.showMessageDialog(
				null,
				"Wrong username or password",
				"Login Failed",
				JOptionPane.ERROR_MESSAGE
			);
		
		txt_username.setText("");
		txt_password.setText("");
	}
}
