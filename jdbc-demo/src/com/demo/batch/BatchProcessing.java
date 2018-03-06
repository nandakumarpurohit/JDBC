package com.demo.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BatchProcessing {
	
	Connection con;
	Statement st;
	
	BatchProcessing() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dec2017","root","password");
			st = con.createStatement();
			con.setAutoCommit(false);
			String query1 = "insert into employee (name, age, salary) values ('ABC', 25, 1234.5)";
			String query2 = "insert into employee (name, age, salary) values (28, 2345.6)";
			String query3 = "insert into employee (name, age, salary) values ('GHI', 31, 3456.7)";
			st.addBatch(query1);
			st.addBatch(query2);
			st.addBatch(query3);
			st.executeBatch();
			con.commit();
			System.out.println("Executed!!");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BatchProcessing();
	}

}
