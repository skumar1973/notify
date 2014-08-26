package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.c2pi.notify.dao.ProjectDAO;
import com.c2pi.notify.dao.UserDAO;
import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.Project;
import com.c2pi.notify.entity.User;

/**
 * @author Shailendrak
 * 
 */
public class UserManager {

	UserDAO udao = null;
	Logger logger = Logger.getLogger(UserManager.class.getName());
	User edituser=null;
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

	public User editUser(int userId) throws SQLException, ClassNotFoundException, IOException{
		udao = new UserDAO();
		edituser = udao.getedituserList(userId);
		
		return edituser;
	}
	
	/**
	 * @param projectId
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
		
	public String deleteUser(int userId) throws SQLException, IOException, ClassNotFoundException{
		String deleteUs=null;
		udao = new UserDAO();
		deleteUs = udao.userDelete(userId);
		return deleteUs;
	}
}
