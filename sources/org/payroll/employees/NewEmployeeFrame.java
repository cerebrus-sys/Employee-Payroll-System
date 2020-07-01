package org.payroll.employees;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.payroll.*;
import java.util.*;

public class NewEmployeeFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	ArrayList<String> departments = Main.dbManager.getListOfDepartments();
	
	JLabel lbl_fn, lbl_ln, lbl_em, lbl_department;
	JTextField txt_fn, txt_ln, txt_em;
	JComboBox<String> txt_department;
	JButton btn_cancel, btn_create;
	
	public NewEmployeeFrame() {
		initFrame();
		initComponents();
		configureComponents();
		addActionListeners();
		addComponentsToFrame();
	}
	
	void initFrame() {
		setTitle("New Employee");
		setSize(320, 170);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
	}
	
	void initComponents() {
		lbl_fn = new JLabel			("First Name: ");
		lbl_ln = new JLabel			("Last Name: ");
		lbl_em = new JLabel			("          Email: ");
		lbl_department = new JLabel	("      Department: ");
		txt_fn = new JTextField(18);
		txt_ln = new JTextField(18);
		txt_em = new JTextField(18);
		txt_department = new JComboBox<String>(departments.toArray(new String[departments.size()]));
		btn_cancel = new JButton("Cancel");
		btn_create = new JButton("Create");
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
		
		btn_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.dbManager.createEmployee(txt_fn.getText(), txt_ln.getText(), txt_em.getText(), txt_department.getSelectedItem().toString());
				setVisible(false);
				JOptionPane.showMessageDialog(
						null,
						"New Employee Added",
						"New Employee Added",
						JOptionPane.INFORMATION_MESSAGE
					);
				dispose();
			}
		});
	}
	
	void addComponentsToFrame() {
		add(lbl_fn);
		add(txt_fn);
		add(lbl_ln);
		add(txt_ln);
		add(lbl_em);
		add(txt_em);
		add(lbl_department);
		add(txt_department);
		add(btn_cancel);
		add(btn_create);
	}
}
