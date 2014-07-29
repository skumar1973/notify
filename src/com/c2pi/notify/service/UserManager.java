package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.c2pi.notify.dao.UserDAO;
import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.User;

/**
 * @author Shailendrak
 * 
 */
public class UserManager {

	UserDAO udao = null;
	Logger logger = Logger.getLogger(UserManager.class.getName());

	/**
	 * @param user
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String AddUser(User user) throws SQLException,
			ClassNotFoundException, IOException {
		String udaores = "";
		udao = new UserDAO();
		logger.info("UserManager addUser method...");
		udaores = udao.addUser(user);
		return udaores;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<User> getUserList() throws SQLException,
			ClassNotFoundException, IOException {
		ArrayList<User> userList = null;
		udao = new UserDAO();
		userList = udao.getUserList();
		return userList;

	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<Employee> getEmpList() throws SQLException,
			ClassNotFoundException, IOException {
		udao = new UserDAO();
		ArrayList<Employee> empList = null;
		empList = udao.getEmpList();
		return empList;

	}
}
