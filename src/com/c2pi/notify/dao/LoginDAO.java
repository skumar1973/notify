package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

	String query = "";
	String role = "";
	Connection con = null;
	DBConn conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;

	public String getUserRole(String loginID) throws SQLException {
		try {
			conn = new DBConn();
			con = conn.getConn();

			query = "SELECT u.login_id, e.first_name,e.last_name , lower(r.name) as role, u.password "
					+ "FROM c2pidb.employees e, c2pidb.users u, c2pidb.roles r, c2pidb.emp_roles er "
					+ "where u.login_id=?"
					+ "and u.emp_id=e.id "
					+ "and u.emp_id=er.emp_id " + "and er.role_id = r.id;";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, loginID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				role = rs.getString("role");
			}

			System.out.println("res:" + role);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return role;
	}

	public int IsLoginCorrect(String loginID, String password)
			throws SQLException {

		int empID = 0;
		query = "SELECT emp_id FROM `c2pidb`.`users` u where u.login_id=? and password=?";
		conn = new DBConn();
		try {
			con = conn.getConn();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, loginID);
			pstmt.setString(2, password);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				empID = rs.getInt("emp_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			if (pstmt != null)
				pstmt.close();

			if (con != null)
				con.close();
		}

		return empID;
	}
}
