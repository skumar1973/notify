package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn1 {

	private String url = "jdbc:mysql://localhost:3306/c2pidb";
	private String user_name = "root";
	private String user_password = "";

	Connection conn = null;

	public Connection getConn() {

		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			conn = DriverManager.getConnection(url, user_name, user_password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}

		return conn;
	}
}
