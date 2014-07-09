package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.c2pi.notify.entity.Employee;

public class EmployeeDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private String query = "";
	private int queryResult = 0;
	private DBConn con = null;
	private ResultSet rs = null;
	private ArrayList<Employee> empList = null;

	public String addEmployee(Employee employee) throws SQLException {
		query = "insert into Employees(first_name, last_name, email, designation, status, created_by, created_dt, updated_by, updated_dt ) values(?,?,?,?,?,?,?,?,?)";
		con = new DBConn();

		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getLastName());
			pstmt.setString(3, employee.getEmail());
			pstmt.setString(4, employee.getDesignation());
			pstmt.setString(5, employee.getStatus());
			pstmt.setString(6, employee.getCreatedBy());
			pstmt.setString(7, employee.getCreatedDt());
			pstmt.setString(8, employee.getUpdatedBy());
			pstmt.setString(9, employee.getUpdatedDt());
			System.out.println("pstmt" + pstmt);
			queryResult = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return (queryResult + "- employee added.");

	}

	public ArrayList<Employee> getEmpList() throws SQLException {
		empList = new ArrayList<Employee>();
		con = new DBConn();
		conn = con.getConn();
		query = "select id, first_name, last_name, email, designation, status, created_dt, created_by, updated_dt, updated_by from c2pidb.employees";
		try {
			pstmt = conn.prepareStatement(query);
			System.out.println("pstmt:"+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setDesignation(rs.getString("designation"));
				emp.setStatus(rs.getString("status"));
				emp.setCreatedDt(rs.getString("created_dt"));
				emp.setCreatedBy(rs.getString("created_by"));
				emp.setUpdatedBy(rs.getString("updated_dt"));
				emp.setUpdatedBy(rs.getString("updated_by"));
				empList.add(emp);
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

		return empList;
	}
}
