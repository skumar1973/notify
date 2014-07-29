package com.c2pi.notify.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBConn {

	private String url;
	private String userName;
	private String userPassword;
	Properties prop = new Properties();
	InputStream inputStream = null;
	String propFileName = "ApplicationResources.properties";

	Logger logger = Logger.getLogger(DBConn.class.getName());

	Connection conn = null;

	public Connection getConn() throws ClassNotFoundException, SQLException,
			IOException {
		inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(propFileName);
		prop.load(inputStream);
		url = prop.getProperty("app.database.URL");
		userName = prop.getProperty("app.database.username");
		userPassword = prop.getProperty("app.database.password");
		logger.debug("url:" + url);
		logger.debug("user_name:" + userName);
		logger.debug("user_password:" + userPassword);
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, userName, userPassword);
		return conn;
	}
}
