package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmpTransaction {
	
	Connection con;
	PreparedStatement ps;
	
	EmpTransaction(String name, String zipCode) {
		
		int newKey = -1;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dec2017", "root", "password");
			con.setAutoCommit(false);
			ps = con.prepareStatement("insert into employee (name, age, salary) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			ps.setInt(2, 30);
			ps.setDouble(3, 5000.0);
			int x = ps.executeUpdate();
			ResultSet autoKeys = ps.getGeneratedKeys();
			if(autoKeys.next()) {
				newKey = autoKeys.getInt(1);
			}
			System.out.println("EMP added with: " + newKey);
			autoKeys.close();
			ps.close();
			
			PreparedStatement ps2 = con.prepareStatement("insert into emp_address values (?, ?)");
			ps2.setInt(1, newKey);
			ps2.setString(2, zipCode);
			int y = ps2.executeUpdate();
			System.out.println("Emp & Address inserted!");
			con.commit();
			
			ps2.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new EmpTransaction("Tom Hanks", "60002");
	}

}
