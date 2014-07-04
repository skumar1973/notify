package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.TaskFreqDAO;
import com.c2pi.notify.dao.UserDAO;
import com.c2pi.notify.entity.Employees;
import com.c2pi.notify.entity.User;

public class UserManager {
 

	
	public String AddUser(String login_id, String password, int emp_id, String created_by){
		String udaores="";
		UserDAO userdao= new UserDAO();
		try {			
			System.out.println("UserManager addUser method...");
			udaores=userdao.addUser(login_id, password, emp_id, created_by);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return udaores;
	}
	public ArrayList<User> getuserlist(){
		ArrayList<User> userlist = null;
		UserDAO userdao= new UserDAO();
		userlist=userdao.getuserlist();
		return userlist;
		
	}
	
	public ArrayList<Employees> getEmpList() throws SQLException{
		UserDAO ud = new UserDAO();
		ArrayList<Employees> emplist = null;
		emplist=ud.getEmpList();
		return emplist;
		
	}
}
