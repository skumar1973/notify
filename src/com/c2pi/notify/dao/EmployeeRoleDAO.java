package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.EmployeeRole;
import com.c2pi.notify.entity.Employees;
import com.c2pi.notify.entity.Roles;

public class EmployeeRoleDAO {

	/**
	 * 
	 * @param emp_id
	 * @param role_id
	 * @param created_by
	 * @return res
	 * @throws SQLException
	 */
	public String addEmployeeRole(int emp_id, int role_id, String created_by) throws SQLException {
		String query = "Insert Into `c2pidb`.`emp_roles` (`emp_id`,`role_id`,`created_by`) values(?,?,?)";
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt =null;
		DBConn con = new DBConn();
		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, emp_id);
			pstmt.setInt(2, role_id);
			pstmt.setString(3,created_by);
			System.out.println("query:" + query);
			System.out.println("pstmt:"+pstmt);
			res = pstmt.executeUpdate();
			System.out.println("res=" + res);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return (res + "- Employee role added.");
	}

	/**
	 * @return List of employee IDs
	 * @throws SQLException
	 */
	public ArrayList<Employees> getEmpId() {
		String query = "Select `id`,concat(concat(`first_name`,' '),`last_name`) first_name, last_name from `c2pidb`.`employees`";
		ArrayList<Employees> empIDList = new ArrayList<Employees>();
		Connection conn = null;
		PreparedStatement pstmt =null;
		DBConn con = new DBConn();
		ResultSet rs =null;
		try {
			conn = con.getConn();
			System.out.println("query"+query);
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				Employees emp = new Employees();
				emp.setId(rs.getInt("id"));
				emp.setFirst_name(rs.getString("first_name"));
				emp.setLast_name(rs.getString("last_name"));

				empIDList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn !=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return empIDList;
	}

	/**
	 * @return List of role IDs
	 * @throws SQLException
	 */
	public ArrayList<Roles> getRoleId(){
		ArrayList<Roles> roleIDList = new ArrayList<Roles>();
		String query="SELECT `id`, `name` FROM `c2pidb`.`roles`";
		Connection conn = null;
		PreparedStatement pstmt =null;
		DBConn con = new DBConn();
		ResultSet rs =null;
		try {
			conn = con.getConn();
			System.out.println("query"+query);
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while (rs.next()){
				Roles role = new Roles();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				
				roleIDList.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn !=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return roleIDList;
	}
	/**
	 * @return List of Employee role 
	 * @throws SQLException
	 */
	public ArrayList<EmployeeRole> getEmpRole(){
		ArrayList<EmployeeRole> emproleList = new ArrayList<EmployeeRole>();
		String query="SELECT `id`, `emp_id`, `role_id`, `created_dt`, `created_by`, `updated_dt`, `updated_by` FROM `c2pidb`.`emp_roles`";
		Connection conn = null;
		PreparedStatement pstmt =null;
		DBConn con = new DBConn();
		ResultSet rs =null;
		try {
			conn = con.getConn();
			System.out.println("query: "+query);
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while (rs.next()){
				EmployeeRole emprole = new EmployeeRole();
				emprole.setId(rs.getInt("id"));
				emprole.setEmp_id(rs.getInt("emp_id"));
				emprole.setRole_id(rs.getInt("role_id"));
				emprole.setCreated_dt(rs.getString("created_dt"));
				emprole.setCreated_by(rs.getString("created_by"));
				emprole.setUpdated_dt(rs.getString("updated_dt"));
				emprole.setUpdated_by(rs.getString("updated_by"));
				emproleList.add(emprole);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn !=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return emproleList;
	}
}
