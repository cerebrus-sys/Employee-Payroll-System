package org.payroll.preferences;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import org.payroll.*;

public class NewLoginIdFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	String chars = " ^&\\/|`~";
	
	String uppercase_alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String lowercase_alphabets = "abcdefghijklmnopqrstuvwxyz";
	String numbers = "0123456789";
	
	JLabel lbl_username, lbl_newPassword, lbl_repeatPassword;
	JTextField txt_username;
	JPasswordField txt_newPassword, txt_repeatPassword;
	JButton btn_cancel, btn_create;
	
	public NewLoginIdFrame() {
		initFrame();
		initComponents();
		addActionListeners();
		addComponentsToFrame();
	}
	
	void initFrame() {
		setTitle("New Login ID");
		setSize(338, 152);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
	}
	
	void initComponents() {
		lbl_username = new JLabel("              Username: ");
		txt_username = new JTextField(18);
		lbl_newPassword = new JLabel("     New Password: ");
		txt_newPassword = new JPasswordField(18);
		lbl_repeatPassword = new JLabel("Repeat Password: ");
		txt_repeatPassword = new JPasswordField(18);
		btn_cancel = new JButton("Cancel");
		btn_create = new JButton("Create");
	}
	
	void addActionListeners() {
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		btn_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isUsernameValid()) {
					if (!Main.dbManager.verifyLoginId(txt_username.getText())) {
						if (Arrays.equals(txt_newPassword.getPassword(), txt_repeatPassword.getPassword())) {
							if (isStrongPassword()) {
								Main.dbManager.createLoginId(txt_username.getText(), new String(txt_newPassword.getPassword()));
								setVisible(false);
								JOptionPane.showMessageDialog(
										null,
										"New Login ID created successfully",
										"New Login ID Created",
										JOptionPane.INFORMATION_MESSAGE
									);
								dispose();
							} else
								JOptionPane.showMessageDialog(
										null,
										"Password is not strong enough",
										"Weak Password",
										JOptionPane.ERROR_MESSAGE
									);
						} else
							JOptionPane.showMessageDialog(
									null,
									"Passwords don't match",
									"Passwords are different",
									JOptionPane.ERROR_MESSAGE
								);
					} else
						JOptionPane.showMessageDialog(
								null,
								"Username Already Taken",
								"Username already taken",
								JOptionPane.ERROR_MESSAGE
							);
				} else
					JOptionPane.showMessageDialog(
							null,
							"Invalid Username. Username cannot contain these symbols: " + chars,
							"Invalid Username",
							JOptionPane.ERROR_MESSAGE
						);
			}
		});
	}
	
	void addComponentsToFrame() {
		add(lbl_username);
		add(txt_username);
		add(lbl_newPassword);
		add(txt_newPassword);
		add(lbl_repeatPassword);
		add(txt_repeatPassword);
		add(btn_cancel);
		add(btn_create);
	}
	
	Boolean isUsernameValid() {
		String username = txt_username.getText();
		
		if (username.length() < 1)
			return false;
		
		for (int i=0; i<username.length(); i++) {
			for (int j=0; j<chars.length(); j++) {
				if (username.charAt(i) == chars.charAt(j))
					return false;
			}
		}
		
		return true;
	}
	
	Boolean isStrongPassword() {
		String password = new String(txt_newPassword.getPassword());
		
		if ((password.length() > 6) &&
			(containsUppercase(password)) &&
			(containsLowercase(password)) &&
			(containsNumbers(password)))
			return true;
		
		return false;
	}
	
	Boolean containsUppercase(String password) {
		for (int i=0; i<password.length(); i++) {
			for (int j=0; j<uppercase_alphabets.length(); j++) {
				if (password.charAt(i) == uppercase_alphabets.charAt(j))
					return true;
			}
		}
		return false;
	}
	
	Boolean containsLowercase(String password) {
		for (int i=0; i<password.length(); i++) {
			for (int j=0; j<lowercase_alphabets.length(); j++) {
				if (password.charAt(i) == lowercase_alphabets.charAt(j))
					return true;
			}
		}
		return false;
	}
	
	Boolean containsNumbers(String password) {
		for (int i=0; i<password.length(); i++) {
			for (int j=0; j<numbers.length(); j++) {
				if (password.charAt(i) == numbers.charAt(j))
					return true;
			}
		}
		return false;
	}
}
