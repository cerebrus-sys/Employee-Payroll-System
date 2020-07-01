package org.payroll;

// Loader Class
public class Main {
	
	public static DatabaseManager dbManager;	// shared database manager
	
	public static void main(String[] args) {
		dbManager = new DatabaseManager("");
		// If "the path to database file" is empty, a temporary in-memory database is opened.
		
		(new LoginFrame()).setVisible(true);
	}
}
