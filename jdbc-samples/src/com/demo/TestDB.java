package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestDB {
	
	Connection con;
	Statement st;
	
	TestDB() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dec2017", "root", "password");
			/*
			 * jdbc:mysql - Is the protocol to connect to MySQL DB
			 * localhost - Is the DB server IP address (Same laptop having DB and hence localhost). In real-time, it would be an
			 * IP address of the DB server
			 * 3306 - Default port number where MySQL runs
			 * dec2017 - Is the database which we created in our DB
			 * root - Is the username
			 * password - Is the password
			 */
			
			// If this connection is successfull, then "con" object must be initialized with some value. Otherwise, it will be null.
			if(con != null) {
				System.out.println("Connected to DB!");
				st = con.createStatement();
				st.execute("create table books (id int primary key auto_increment, title varchar(20), author varchar(30), price double)");
				System.out.println("Table is created!");
				int num = st.executeUpdate("insert into books (title, author, price) values ('JAVA', 'James Ghosling', 12.34)");
				num +=  st.executeUpdate("insert into books (title, author, price) values ('SOAP', 'Wrox', 9.99)");
				num +=  st.executeUpdate("insert into books (title, author, price) values ('Socket programming', 'McGraw', 15.36)");
				System.out.println(num + " rows inserted!");
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestDB();
	}

}
