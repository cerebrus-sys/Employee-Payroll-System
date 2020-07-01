package org.payroll.departments;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import org.payroll.*;

public class DeleteDepartmentFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	ArrayList<String> departments = Main.dbManager.getListOfDepartments();
	
	JLabel lbl_dep_name;
	JComboBox<String> combobox;
	JButton btn_cancel, btn_delete;
	
	public DeleteDepartmentFrame() {
		initFrame();
		initComponents();
		addActionListeners();
		addComponentsToFrame();
	}
	
	void initFrame() {
		setTitle("Delete Department");
		setSize(330, 120);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
	}
	
	void initComponents() {
		lbl_dep_name = new JLabel("Department Name: ");
		combobox = new JComboBox<String>(departments.toArray(new String[departments.size()]));
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
				Main.dbManager.deleteDepartment(combobox.getSelectedItem().toString());
				setVisible(false);
				dispose();
			}
		});
	}
	
	void addComponentsToFrame() {
		add(lbl_dep_name);
		add(combobox);
		add(btn_cancel);
		add(btn_delete);
	}
}
