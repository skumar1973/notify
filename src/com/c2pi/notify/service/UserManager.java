package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.TaskFreqDAO;
import com.c2pi.notify.dao.UserDAO;
import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.User;

public class UserManager {

	UserDAO udao = null;

	public String AddUser(User user) throws SQLException {
		String udaores = "";
		udao = new UserDAO();
		System.out.println("UserManager addUser method...");
		udaores = udao.addUser(user);
		return udaores;
	}

	public ArrayList<User> getUserList() throws SQLException {
		ArrayList<User> userList = null;
		udao = new UserDAO();
		userList = udao.getUserList();
		return userList;

	}

	public ArrayList<Employee> getEmpList() throws SQLException {
		udao = new UserDAO();
		ArrayList<Employee> empList = null;
		empList = udao.getEmpList();
		return empList;

	}
}
