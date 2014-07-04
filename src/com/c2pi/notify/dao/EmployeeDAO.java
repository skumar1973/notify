package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.c2pi.notify.entity.Employees;

public class EmployeeDAO {

	public String addEmployee(String firstname, String lastname, String email,
			String design, String status, String created_by)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into Employees(first_name, last_name, email, designation, status, created_by) values(?,?,?,?,?,?)";
		int res = 0;
		DBConn con = new DBConn();

		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, firstname);
			pstmt.setString(2, lastname);
			pstmt.setString(3, email);
			pstmt.setString(4, design);
			pstmt.setString(5, status);
			pstmt.setString(6, created_by);
			System.out.println("pstmt"+pstmt);
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return (res + "- employee added.");

	}
	public ArrayList<Employees> getemplist(){
		ArrayList<Employees> emplist = new ArrayList<Employees>();
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		DBConn con = new DBConn();
		conn=con.getConn();
		String query="select id, first_name, last_name, email, designation, status, created_dt, created_by, updated_dt, updated_by from c2pidb.employees";
		try {
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while (rs.next()){
				Employees emp = new Employees();
				emp.setId(rs.getInt("id"));
				emp.setFirst_name(rs.getString("first_name"));
				emp.setLast_name(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setDesignation(rs.getString("designation"));
				emp.setStatus(rs.getString("status"));
				emp.setCreated_dt(rs.getString("created_dt"));
				emp.setCreated_by(rs.getString("created_by"));
				emp.setUpdated_dt(rs.getString("updated_dt"));
				emp.setUpdated_by(rs.getString("updated_by"));
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {if (pstmt!=null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return emplist;
	}
}
