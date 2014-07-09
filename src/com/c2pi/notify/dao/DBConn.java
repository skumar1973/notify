package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConn {

	private String url;
	private String user_name;
	private String user_password;
	Properties prop = new Properties();
	InputStream inputStream = null;
	String propFileName = "ApplicationResources.properties";
	Connection conn = null;
	{
		try {
			inputStream = this.getClass().getClassLoader()
					.getResourceAsStream(propFileName);
			prop.load(inputStream);
			url = prop.getProperty("app.database.URL");
			System.out.println("url:" + url);
			user_name = prop.getProperty("app.database.username");
			System.out.println("user_name:" + user_name);
			user_password = prop.getProperty("app.database.password");
			System.out.println("user_password:" + user_password);
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	public Connection getConn() {
		System.out.println("url :  " + url);
		System.out.println("user_name  :" + user_name);
		System.out.println("user_password  :" + user_password);
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
