package com.c2pi.notify.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import com.c2pi.notify.common.DBConn;
import com.c2pi.notify.entity.Employee;

public class EmployeeDAO {

	/**
	 * @author Shailendrak
	 */
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private String query = "";
	private int queryResult = 0;
	private DBConn con = new DBConn();
	private ResultSet rs = null;
	private ArrayList<Employee> empList = null;

	/**
	 * @param employee
	 * @return queryResult
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String addEmployee(Employee employee) throws SQLException, ClassNotFoundException, IOException {
		query = "insert into Employees(first_name, last_name, email, designation, status, created_by, created_dt, updated_by, updated_dt ) values(?,?,?,?,?,?,?,?,?)";
//		con = new DBConn();

		
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
		if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		

		return (queryResult + "- employee added.");

	}

	/**
	 * @return empList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<Employee> getEmpList() throws SQLException, ClassNotFoundException, IOException {
		empList = new ArrayList<Employee>();
//		con = new DBConn();
		conn = con.getConn();
		query = "select id, first_name, last_name, email, designation, status, created_dt, created_by, updated_dt, updated_by from c2pidb.employees";
		
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
		
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();

		

		return empList;
	}
	public Employee getEmployeeById(int empID)
			throws SQLException, ClassNotFoundException, IOException {
		Employee emp = new Employee();
		query = "select id, first_name, last_name, email, designation, status, created_dt, created_by, updated_dt, updated_by from c2pidb.employees where id=?";
//		con=new DBConn();
		conn = con.getConn();
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, empID);
		System.out.println("pstmt:"+pstmt);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			
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
			
		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return emp;
	}
}
