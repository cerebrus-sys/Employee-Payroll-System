package org.payroll.preferences;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.payroll.*;

public class DeleteLoginIdFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JLabel lbl_username, lbl_password;
	JTextField txt_username;
	JPasswordField txt_password;
	JButton btn_cancel, btn_delete;
	
	public DeleteLoginIdFrame() {
		initFrame();
		initComponents();
		addActionListeners();
		addComponentsToFrame();
	}
	
	void initFrame() {
		setTitle("Delete Login ID");
		setSize(300, 115);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
	}
	
	void initComponents() {
		lbl_username = new JLabel("Username: ");
		txt_username = new JTextField(18);
		lbl_password = new JLabel("Password: ");
		txt_password = new JPasswordField(18);
		btn_cancel = new JButton("Cancel");
		btn_delete = new JButton("Delete");
	}
	
	void addActionListeners() {
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Main.dbManager.verifyLoginId(txt_username.getText(), new String(txt_password.getPassword()))) {
					Main.dbManager.deleteLoginId(txt_username.getText());
					setVisible(false);
					JOptionPane.showMessageDialog(
							null,
							"Login ID deleted successfully",
							"Deletion Successful",
							JOptionPane.INFORMATION_MESSAGE
						);
					dispose();
				} else
					JOptionPane.showMessageDialog(
							null,
							"Wrong username or password",
							"Deletion Failed",
							JOptionPane.ERROR_MESSAGE
						);
			}
		});
	}
	
	void addComponentsToFrame() {
		add(lbl_username);
		add(txt_username);
		add(lbl_password);
		add(txt_password);
		add(btn_cancel);
		add(btn_delete);
	}
}
