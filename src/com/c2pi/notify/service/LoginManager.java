package com.c2pi.notify.service;

import com.c2pi.notify.dao.LoginDAO;

public class LoginManager {

	public int IsLoginCorrect(String loginid, String password){
		int emp_id=0;
		LoginDAO udao = new LoginDAO();
		emp_id=udao.IsLoginCorrect(loginid, password);
		return emp_id;
	}
	public String getUserRole(String loginid){
		String res="";
		LoginDAO udao = new LoginDAO();
		res=udao.getUserRole(loginid);
		return res;
	}
}
