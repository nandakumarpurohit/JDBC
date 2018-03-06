package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreparedDemo {
	
	PreparedDemo(String bookTitle, int bookId) {
		
		Connection con;
		PreparedStatement ps;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dec2017", "root", "password");
			ps = con.prepareStatement("update books set title=? where id=?");
			ps.setString(1, bookTitle);
			ps.setInt(2, bookId);
			int x = ps.executeUpdate();
			System.out.println(x + " row updated!");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PreparedDemo(args[0], Integer.parseInt(args[1]));
	}

}
