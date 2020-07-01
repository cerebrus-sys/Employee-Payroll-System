package org.payroll.departments;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.payroll.*;

public class NewDepartmentFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JLabel lbl_dep_name, lbl_basic_salary, lbl_da, lbl_hra, lbl_pf;
	JTextField txt_dep_name, txt_basic_salary, txt_da, txt_hra, txt_pf;
	JButton btn_cancel, btn_create;
	
	public NewDepartmentFrame() {
		initFrame();
		initComponents();
		configureComponents();
		addActionListeners();
		addComponentsToFrame();
	}
	
	void initFrame() {
		setTitle("New Department");
		setSize(333, 193);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
	}
	
	void initComponents() {
		lbl_dep_name = new JLabel("Department Name: ");
		lbl_basic_salary = new JLabel("           Basic Salary: ");
		lbl_da = new JLabel("                          DA%: ");
		lbl_hra = new JLabel("                       HRA%: ");
		lbl_pf = new JLabel("                          PF%: ");
		txt_dep_name = new JTextField(18);
		txt_basic_salary = new JTextField(18);
		txt_da = new JTextField(18);
		txt_hra = new JTextField(18);
		txt_pf = new JTextField(18);
		btn_cancel = new JButton("Cancel");
		btn_create = new JButton("Create");
	}
	
	void configureComponents() {
		txt_da.setText("10");
		txt_hra.setText("14");
		txt_pf.setText("8");
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
				try {
					String dep_name = txt_dep_name.getText();
					int basic_salary = Integer.parseInt(txt_basic_salary.getText());
					int da = Integer.parseInt(txt_da.getText());
					int hra = Integer.parseInt(txt_hra.getText());
					int pf = Integer.parseInt(txt_pf.getText());
					
					if (!Main.dbManager.existsDepartment(dep_name)) {
						Main.dbManager.newDepartment(dep_name, basic_salary, da, hra, pf);
						setVisible(false);
						JOptionPane.showMessageDialog(
								null,
								"Created Department successfully",
								"Department created",
								JOptionPane.INFORMATION_MESSAGE
							);
						dispose();
					} else {
						JOptionPane.showMessageDialog(
								null,
								"Department already exists",
								"Department already exists",
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
		add(lbl_dep_name);
		add(txt_dep_name);
		add(lbl_basic_salary);
		add(txt_basic_salary);
		add(lbl_da);
		add(txt_da);
		add(lbl_hra);
		add(txt_hra);
		add(lbl_pf);
		add(txt_pf);
		add(btn_cancel);
		add(btn_create);
	}
}
