package com.c2pi.notify.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.c2pi.notify.common.DBConn;

/**
 * @author Shailendrak
 * 
 */
public class LoginDAO {

	String query = "";
	String role = "";
	Connection con = null;
	DBConn conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Logger logger = Logger.getLogger(LoginDAO.class.getName());

	/**
	 * @param loginID
	 * @return role
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public String getUserRole(String loginID) throws SQLException, ClassNotFoundException, IOException {

		conn = new DBConn();
		con = conn.getConn();

		query = "SELECT u.login_id, e.first_name,e.last_name , lower(r.name) as role, u.password "
				+ "FROM c2pidb.employees e, c2pidb.users u, c2pidb.roles r, c2pidb.emp_roles er "
				+ "where u.login_id=?"
				+ "and u.emp_id=e.id "
				+ "and u.emp_id=er.emp_id " + "and er.role_id = r.id;";

		pstmt = con.prepareStatement(query);
		pstmt.setString(1, loginID);
		logger.debug(pstmt);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			role = rs.getString("role");
		}

		logger.info("role:" + role);
		
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();

		return role;
	}

	/**
	 * @param loginID
	 * @param password
	 * @return emp ID
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public int IsLoginCorrect(String loginID, String password)
			throws SQLException, ClassNotFoundException, IOException {

		int empID = 0;
		query = "SELECT emp_id FROM `c2pidb`.`users` u where u.login_id=? and password=?";
		conn = new DBConn();

		con = conn.getConn();
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, loginID);
		pstmt.setString(2, password);
		System.out.println(pstmt);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			empID = rs.getInt("emp_id");
		}
		logger.info(empID);
		if (pstmt != null)
			pstmt.close();

		if (con != null)
			con.close();

		return empID;
	}
}
