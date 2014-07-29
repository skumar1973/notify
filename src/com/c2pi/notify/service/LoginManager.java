package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;

import com.c2pi.notify.dao.LoginDAO;

/**
 * @author Shailendrak
 *
 */
public class LoginManager {
	
	LoginDAO udao = null;
	/**
	 * @param loginID
	 * @param password
	 * @return emp id
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */

	public int IsLoginCorrect(String loginID, String password) throws SQLException, ClassNotFoundException, IOException{
		
		int emp_id=0;
		udao = new LoginDAO();
		emp_id=udao.IsLoginCorrect(loginID, password);
		
		return emp_id;
	}
	
	/**
	 * @param loginID
	 * @return role
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public String getUserRole(String loginID) throws SQLException, ClassNotFoundException, IOException{
		String res="";
		udao = new LoginDAO();
		res=udao.getUserRole(loginID);
		return res;
	}
}
