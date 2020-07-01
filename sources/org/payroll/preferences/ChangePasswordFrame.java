package org.payroll.preferences;

import javax.swing.*;
import org.payroll.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class ChangePasswordFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	String username;
	String uppercase_alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String lowercase_alphabets = "abcdefghijklmnopqrstuvwxyz";
	String numbers = "0123456789";
	
	JLabel lbl_oldPassword, lbl_newPassword, lbl_repeatPassword;
	JPasswordField txt_oldPassword, txt_newPassword, txt_repeatPassword;
	JButton btn_cancel, btn_OK;
	
	public ChangePasswordFrame(String username) {
		this.username = username;
		
		initFrame();
		initComponents();
		addActionListeners();
		addComponentsToFrame();
	}
	
	void initFrame() {
		setTitle("Change Password");
		setSize(355, 145);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
	}
	
	void initComponents() {
		lbl_oldPassword = new JLabel("      Old Password: ");
		lbl_newPassword = new JLabel("     New Password: ");
		lbl_repeatPassword = new JLabel("Repeat Password: ");
		txt_oldPassword = new JPasswordField(20);
		txt_newPassword = new JPasswordField(20);
		txt_repeatPassword = new JPasswordField(20);
		btn_cancel = new JButton("Cancel");
		btn_OK = new JButton("OK");
	}
	
	void addActionListeners() {
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		btn_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Main.dbManager.verifyLoginId(username, new String(txt_oldPassword.getPassword())))
					if (Arrays.equals(txt_newPassword.getPassword(), txt_repeatPassword.getPassword()))
						if (isStrongPassword(new String(txt_newPassword.getPassword()))) {
							Main.dbManager.changePassword(username, new String(txt_newPassword.getPassword()));
							setVisible(false);
							JOptionPane.showMessageDialog(
									null,
									"Your login id's password is changed successfully",
									"Password changed",
									JOptionPane.INFORMATION_MESSAGE
								);
						} else
							JOptionPane.showMessageDialog(
									null,
									"Password not strong enough",
									"Weak Password",
									JOptionPane.ERROR_MESSAGE
								);
					else
						JOptionPane.showMessageDialog(
								null,
								"Password don't match",
								"Password Error",
								JOptionPane.ERROR_MESSAGE
							);
				else
					JOptionPane.showMessageDialog(
							null,
							"Invalid Login ID",
							"Invalid Login ID",
							JOptionPane.ERROR_MESSAGE
						);
			}
		});
	}
	
	void addComponentsToFrame() {
		add(lbl_oldPassword);
		add(txt_oldPassword);
		add(lbl_newPassword);
		add(txt_newPassword);
		add(lbl_repeatPassword);
		add(txt_repeatPassword);
		add(btn_cancel);
		add(btn_OK);
	}
	
	Boolean isStrongPassword(String password) {
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
