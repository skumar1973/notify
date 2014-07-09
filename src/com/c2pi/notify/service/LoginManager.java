package com.c2pi.notify.service;

import java.sql.SQLException;

import com.c2pi.notify.dao.LoginDAO;

public class LoginManager {
	LoginDAO udao = null;
	public int IsLoginCorrect(String loginID, String password) throws SQLException{
		int emp_id=0;
		udao = new LoginDAO();
		emp_id=udao.IsLoginCorrect(loginID, password);
		return emp_id;
	}
	public String getUserRole(String loginID) throws SQLException{
		String res="";
		udao = new LoginDAO();
		res=udao.getUserRole(loginID);
		return res;
	}
}
