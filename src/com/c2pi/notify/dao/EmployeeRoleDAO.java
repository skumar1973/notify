package com.c2pi.notify.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.common.DBConn;
import com.c2pi.notify.entity.EmployeeRole;
import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.Role;

public class EmployeeRoleDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private DBConn con = null;
	private String query = "";
	private int queryResult = 0;
	private ResultSet rs = null;
	private Role role = null;
	private EmployeeRole empRole = null;

	/**
	 * 
	 * @param empID
	 * @param roleID
	 * @param createdBy
	 * @return res
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public String addEmployeeRole(EmployeeRole empRole)
			throws SQLException, ClassNotFoundException, IOException {
		query = "Insert Into `c2pidb`.`emp_roles` (`emp_id`,`role_id`,`created_by`, `created_dt`, `updated_by`, `updated_dt`) values(?,?,?,?,?,?)";
		con = new DBConn();

			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empRole.getEmpID());
			pstmt.setInt(2, empRole.getRoleID());
			pstmt.setString(3, empRole.getCreatedBy());
			pstmt.setString(4, empRole.getCreatedDt());
			pstmt.setString(5, empRole.getUpdatedBy());
			pstmt.setString(6, empRole.getUpdatedDt());
			System.out.println("pstmt:" + pstmt);
			queryResult = pstmt.executeUpdate();
			System.out.println("res=" + queryResult);

			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		
		return (queryResult + "- Employee role added.");
	}

	/**
	 * @return List of employee IDs
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public ArrayList<Employee> getEmpId() throws SQLException, ClassNotFoundException, IOException {
		ArrayList<Employee> empIDList = null;
		query = "Select `id`,concat(concat(`first_name`,' '),`last_name`) first_name, last_name from `c2pidb`.`employees`";
		empIDList = new ArrayList<Employee>();
		con = new DBConn();

			conn = con.getConn();
			System.out.println("query" + query);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));

				empIDList.add(emp);
			}

			if (pstmt != null)
				pstmt.close();

			if (conn != null)
				conn.close();


		return empIDList;
	}

	/**
	 * @return List of role IDs
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public ArrayList<Role> getRoleId() throws SQLException, ClassNotFoundException, IOException {
		ArrayList<Role> roleIDList = null;
		roleIDList = new ArrayList<Role>();
		query = "SELECT `id`, `name` FROM `c2pidb`.`roles`";
		con = new DBConn();
		
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			System.out.println("pstmt:" + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				role = new Role();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				roleIDList.add(role);
			}

			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		

		return roleIDList;
	}

	/**
	 * @return List of Employee role
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public ArrayList<EmployeeRole> getEmpRole() throws SQLException, ClassNotFoundException, IOException {
		ArrayList<EmployeeRole> empRoleList = null;
		empRoleList = new ArrayList<EmployeeRole>();
		query = "SELECT `id`, `emp_id`, `role_id`, `created_dt`, `created_by`, `updated_dt`, `updated_by` FROM `c2pidb`.`emp_roles`";
		con = new DBConn();

			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			System.out.println("pstmt: " + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				empRole = new EmployeeRole();
				empRole.setId(rs.getInt("id"));
				empRole.setEmpID(rs.getInt("emp_id"));
				empRole.setRoleID(rs.getInt("role_id"));
				empRole.setCreatedDt(rs.getString("created_dt"));
				empRole.setCreatedBy(rs.getString("created_by"));
				empRole.setUpdatedDt(rs.getString("updated_dt"));
				empRole.setUpdatedBy(rs.getString("updated_by"));
				empRoleList.add(empRole);
			}

			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		
		return empRoleList;
	}
}
