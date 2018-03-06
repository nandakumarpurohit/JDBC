package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class UpdateDeleteSelect {
	
	Connection con;
	Statement st;
	
	UpdateDeleteSelect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dec2017", "root", "password");
			st = con.createStatement();
			
			// Update
			int x = st.executeUpdate("update books set price=7.77 where id=1");
			System.out.println(x + " rows updated!");
			
			int y = st.executeUpdate("delete from books where id=2");
			System.out.println(y + " rows deleted!");
			
			// How do I select data now...?  executeQuery() which returns ResultSet...
			ResultSet rs = st.executeQuery("select * from books");
			// What next? 
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			
			if(!rs.next()) {
				System.out.println("NO RECORDS...");
			}
			else {
				do {
					/*System.out.println("ID: " + rs.getInt(1));
					System.out.println("Title: " + rs.getString(2));
					System.out.println("Author: " + rs.getString(3));
					System.out.println("Price: " + rs.getDouble(4));*/
					for(int i=1;i<=count;i++) {
						System.out.println(rsmd.getColumnName(i) + ":" + rs.getString(i));
					}
					System.out.println("================");
				} while(rs.next());
				// What happens now...? In case ther are  records...
				// Use do while loop instead...
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String a[]) {
		new UpdateDeleteSelect();
	}

}
