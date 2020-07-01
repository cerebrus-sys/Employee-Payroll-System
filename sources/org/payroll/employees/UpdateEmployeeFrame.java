package org.payroll.employees;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.payroll.*;
import java.util.*;

public class UpdateEmployeeFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	ArrayList<String> departments = Main.dbManager.getListOfDepartments();
	
	JLabel lbl_id, lbl_fn, lbl_ln, lbl_em, lbl_department;
	JTextField txt_id, txt_fn, txt_ln, txt_em;
	JComboBox<String> txt_department;
	JButton btn_cancel, btn_update;
	
	public UpdateEmployeeFrame() {
		initFrame();
		initComponents();
		configureComponents();
		addActionListeners();
		addComponentsToFrame();
	}
	
	void initFrame() {
		setTitle("Update Employee");
		setSize(320, 195);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
	}
	
	void initComponents() {
		lbl_id = new JLabel			("                ID: ");
		lbl_fn = new JLabel			("First Name: ");
		lbl_ln = new JLabel			("Last Name: ");
		lbl_em = new JLabel			("          Email: ");
		lbl_department = new JLabel	("      Department: ");
		txt_id = new JTextField(18);
		txt_fn = new JTextField(18);
		txt_ln = new JTextField(18);
		txt_em = new JTextField(18);
		txt_department = new JComboBox<String>(departments.toArray(new String[departments.size()]));
		btn_cancel = new JButton("Cancel");
		btn_update = new JButton("Update");
	}
	
	void configureComponents() {
	}
	
	void addActionListeners() {
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(txt_id.getText());
					String fn = txt_fn.getText();
					String ln = txt_ln.getText();
					String email = txt_em.getText();
					String department = txt_department.getSelectedItem().toString();
					
					if (Main.dbManager.existsEmployeeID(id)) {
						Main.dbManager.updateEmployee(id, fn, ln, email, department);
						setVisible(false);
						JOptionPane.showMessageDialog(
								null,
								"ID Updated Successfully",
								"Employee Updated",
								JOptionPane.INFORMATION_MESSAGE
							);
						dispose();
					} else {
						JOptionPane.showMessageDialog(
								null,
								"ID doesn't exist.",
								"Error",
								JOptionPane.ERROR_MESSAGE
							);
					}
				} catch (NumberFormatException e1) {
					System.err.println(e1.getMessage());
				}
			}
		});
	}
	
	void addComponentsToFrame() {
		add(lbl_id);
		add(txt_id);
		add(lbl_fn);
		add(txt_fn);
		add(lbl_ln);
		add(txt_ln);
		add(lbl_em);
		add(txt_em);
		add(lbl_department);
		add(txt_department);
		add(btn_cancel);
		add(btn_update);
	}
}
