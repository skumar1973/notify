package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.Employees;
import com.c2pi.notify.entity.User;

public class UserDAO {

	public String addUser(String login_id, String password, int emp_id,
			String created_by) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String query = "Insert Into `c2pidb`.`users` (`login_id`,`password`,`emp_id`,`created_by`) values(?,?,?,?)";
		int res = 0;
		DBConn con = new DBConn();

		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, login_id);
			pstmt.setString(2, password);
			pstmt.setInt(3, emp_id);
			pstmt.setString(4, created_by);
			System.out.println("query:" + query);
			System.out.println("pstmt" + pstmt);
			res = pstmt.executeUpdate();
			System.out.println("res=" + res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return (res + "- User added.");
	}

	public ArrayList<Employees> getEmpList() throws SQLException {

		ArrayList<Employees> emplist = new ArrayList<Employees>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "Select `id`, concat(concat(`first_name`,' '),`last_name`) name from `c2pidb`.`Employees`";

		DBConn con = new DBConn();
		ResultSet rs = null;
		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);

			System.out.println("pstmt:" + pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Employees emp = new Employees();
				emp.setId(rs.getInt("id"));
				emp.setFirst_name(rs.getString("name"));
				System.out.println(rs.getString("name"));
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return emplist;
	}

	public ArrayList<User> getuserlist() {
		ArrayList<User> userlist = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "SELECT `id`, `login_id`,`emp_id`,`created_dt`,`created_by`, `updated_dt`,`updated_by` FROM `c2pidb`.`users`";
		DBConn con = new DBConn();
		ResultSet rs = null;
		conn = con.getConn();
		try {
			pstmt = conn.prepareStatement(query);
			System.out.println("pstmt" + pstmt);
			rs = pstmt.executeQuery();
			System.out.println("query" + query);
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin_id(rs.getString("login_id"));
				user.setEmp_id(rs.getInt("emp_id"));
				user.setCreated_dt(rs.getString("created_dt"));
				user.setCreated_by(rs.getString("created_by"));
				user.setUpdated_dt(rs.getString("updated_dt"));
				user.setUpdated_by(rs.getString("updated_by"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return userlist;
	}
}
