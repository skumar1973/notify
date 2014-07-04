package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {

	public String getUserRole(String loginid) {
		String query = "";
		String role = "";
		Connection con = null;
		try {
			DBConn conn = new DBConn();
			con = conn.getConn();
			// System.out.println("connected with "+user_name+"password"+user_password);
			Statement stmt = con.createStatement();

			query = "SELECT u.login_id, e.first_name,e.last_name , lower(r.name) as role, u.password "
					+ "FROM c2pidb.employees e, c2pidb.users u, c2pidb.roles r, c2pidb.emp_roles er "
					+ "where u.login_id='"
					+ loginid
					+ "' "
					+ "and u.emp_id=e.id "
					+ "and u.emp_id=er.emp_id "
					+ "and er.role_id = r.id;";

			System.out.println("query =" + query);
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				
				role = res.getString("role");
			}

			System.out.println("res:" + role);
			res.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return role;
	}

	public int IsLoginCorrect(String loginid, String password) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int emp_id = 0;
		String query = "SELECT emp_id FROM `c2pidb`.`users` u where u.login_id=? and password=?";
		DBConn dbcon = new DBConn();
		try {
			con = dbcon.getConn();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, loginid);
			pstmt.setString(2, password);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				emp_id = rs.getInt("emp_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			if (dbcon != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return emp_id;
	}
}
