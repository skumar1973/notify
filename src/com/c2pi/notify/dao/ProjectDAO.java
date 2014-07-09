package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.Project;

public class ProjectDAO {

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String query = "";
	int queryResult = 0;
	DBConn con = null;

	public String addProject(Project project) throws SQLException {
		con = new DBConn();
		try {
			conn = con.getConn();
			query = "Insert Into `c2pidb`.`projects` (`name`, `desc`, `mgr_id`, `status`,"
					+ "`created_by`,`created_dt`,`updated_by`,`Updated_dt`) " + "values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, project.getName());
			pstmt.setString(2, project.getDesc());
			pstmt.setInt(3, project.getMgrID());
			pstmt.setString(4, project.getStatus());
			pstmt.setString(5, project.getCreatedBy());
			pstmt.setString(6, project.getCreatedDt());
			pstmt.setString(7, project.getUpdatedBy());
			pstmt.setString(8, project.getUpdatedDt());
			
			System.out.println("pstmt:" + pstmt);
			queryResult = pstmt.executeUpdate();
			System.out.println("res=" + queryResult);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return (queryResult + "- project added.");
	}

	public ArrayList<Project> getProjList() throws SQLException {
		ArrayList<Project> projList = new ArrayList<Project>();
		Project ps = null;
		con = new DBConn();
		try {
			conn = con.getConn();
			query = "SELECT `id`, `name`,`desc`, `mgr_id`,`status`, `created_by` FROM `c2pidb`.`projects`";
			System.out.println("pstmt" + pstmt);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ps = new Project();
				ps.setId(rs.getInt("id"));
				ps.setName(rs.getString("name"));
				ps.setDesc(rs.getString("desc"));
				ps.setMgrID(rs.getInt("mgr_id"));
				ps.setStatus(rs.getString("status"));
				ps.setCreatedBy(rs.getString("created_by"));
				projList.add(ps);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return projList;
	}

	public ArrayList<Employee> getMgrList() throws SQLException {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		Employee emp = null;
		con = new DBConn();
		try {
			conn = con.getConn();
			query = "SELECT e.`id`, concat(concat(`first_name`,' '), last_name) first_name FROM `c2pidb`.`employees` e, `c2pidb`.`emp_roles` er where e.id = er.emp_id and role_id in (select id from c2pidb.roles r where r.name='MANAGER')";
			pstmt = conn.prepareStatement(query);
			System.out.println("pstmt:" + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setFirstName(rs.getString("first_name"));
				empList.add(emp);
			}
		} catch (Exception e) {
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
