package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.Employees;
import com.c2pi.notify.entity.Project;
import com.c2pi.notify.entity.ProjectEmployees;

public class ProjectEmployeesDAO {

	public String addprojectemployees(int proj_id, int emp_id, String created_by)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res = 0;
		String query = "Insert Into `c2pidb`.`projects_employees` ( `proj_id`, `emp_id`, `created_by`) values(?,?,?)";
		DBConn con = new DBConn();

		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, proj_id);
			pstmt.setInt(2, emp_id);
			pstmt.setString(3, created_by);
			System.out.println("query:" + query);
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

		return (res + "- Project Employee added.");
	}

	public ArrayList<ProjectEmployees> getprjemplist(int emp_id)
			throws SQLException {

		ArrayList<ProjectEmployees> prjemplist = new ArrayList<ProjectEmployees>();
		Connection conn = null;
		ResultSet res = null;
		DBConn con = new DBConn();
		PreparedStatement pstmt = null;
		String query = "SELECT pe.id, pe.proj_id, pe.emp_id, pe.created_dt, pe.created_by, pe.updated_dt, pe.updated_by "
				+ "FROM `c2pidb`.`projects`  pr, `c2pidb`.`projects_employees` pe "
				+ "WHERE mgr_id=? and pr.id=pe.proj_id";
		try {
			conn = con.getConn();
			System.out.println("mgr id=" + emp_id);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, emp_id);
			System.out.println("query:" + query);
			res = pstmt.executeQuery();
			System.out.println("res=" + res);

			while (res.next()) {
				ProjectEmployees pe = new ProjectEmployees();
				pe.setId(res.getInt("id"));
				pe.setProj_id(res.getInt("proj_id"));
				pe.setEmp_id(res.getInt("emp_id"));
				pe.setCreated_dt(res.getString("created_dt"));
				pe.setCreated_by(res.getString("created_by"));
				pe.setUpdated_dt(res.getString("updated_dt"));
				pe.setUpdated_by(res.getString("updated_by"));

				prjemplist.add(pe);

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

		return prjemplist;
	}
	
	public ArrayList<Employees> getemplist()
			throws SQLException {

		ArrayList<Employees> emplist = new ArrayList<Employees>();
		Connection conn = null;
		ResultSet res = null;
		DBConn con = new DBConn();
		PreparedStatement pstmt = null;
		String query = "SELECT id, concat(concat(first_name,' '), last_name) as `first_name`, last_name FROM `c2pidb`.`employees` where id not in (select emp_id from `c2pidb`.`projects_employees` ) ";
		try {
			conn = con.getConn();
		
			pstmt = conn.prepareStatement(query);
			System.out.println("query:" + query);
			res = pstmt.executeQuery();
			System.out.println("res=" + res);

			while (res.next()) {
				Employees emp = new Employees();
				emp.setId(res.getInt("id"));
				emp.setFirst_name(res.getString("first_name"));
				emp.setLast_name(res.getString("last_name"));
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
	
	public ArrayList<Project> getprojlist(int emp_id)
			throws SQLException {

		ArrayList<Project> projlist = new ArrayList<Project>();
		Connection conn = null;
		ResultSet res = null;
		DBConn con = new DBConn();
		PreparedStatement pstmt = null;
		String query = "SELECT id, name FROM `c2pidb`.`projects` where mgr_id =? ";
		try {
			conn = con.getConn();
		
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, emp_id);
			System.out.println("query:" + query);
			res = pstmt.executeQuery();
			System.out.println("res=" + res);

			while (res.next()) {
				Project emp = new Project();
				emp.setId(res.getInt("id"));
				emp.setName(res.getString("name"));
				
				projlist.add(emp);

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

		return projlist;
	}
}
