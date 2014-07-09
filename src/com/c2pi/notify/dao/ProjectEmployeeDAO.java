package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.Project;
import com.c2pi.notify.entity.ProjectEmployee;

public class ProjectEmployeeDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	int res = 0;
	String query = "";
	DBConn con = null;
	ResultSet rs = null;

	public String addProjectEmployee(ProjectEmployee projEmp)
			throws SQLException {
		query = "Insert Into `c2pidb`.`projects_employees` ( `proj_id`, `emp_id`, `created_by`,`created_dt`,`updated_by`,`updated_dt`) values(?,?,?,?,?,?)";
		con = new DBConn();

		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projEmp.getProjID());
			pstmt.setInt(2, projEmp.getEmpID());
			pstmt.setString(3, projEmp.getCreatedBy());
			pstmt.setString(4, projEmp.getCreatedDt());
			pstmt.setString(5, projEmp.getUpdatedBy());
			pstmt.setString(6, projEmp.getUpdatedDt());			
			System.out.println("pstmt:" + pstmt);
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

	public ArrayList<ProjectEmployee> getProjEmpList(int emp_id)
			throws SQLException {

		ArrayList<ProjectEmployee> projEmpList = new ArrayList<ProjectEmployee>();
		ProjectEmployee pe = null;
		con = new DBConn();

		query = "SELECT pe.id, pe.proj_id, pe.emp_id, pe.created_dt, pe.created_by, pe.updated_dt, pe.updated_by "
				+ "FROM `c2pidb`.`projects`  pr, `c2pidb`.`projects_employees` pe "
				+ "WHERE mgr_id=? and pr.id=pe.proj_id";
		try {
			conn = con.getConn();
			System.out.println("mgr id=" + emp_id);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, emp_id);
			System.out.println("pstmt:" + pstmt);
			rs = pstmt.executeQuery();
			System.out.println("res=" + rs);

			while (rs.next()) {
				pe = new ProjectEmployee();
				pe.setId(rs.getInt("id"));
				pe.setProjID(rs.getInt("proj_id"));
				pe.setEmpID(rs.getInt("emp_id"));
				pe.setCreatedDt(rs.getString("created_dt"));
				pe.setCreatedBy(rs.getString("created_by"));
				pe.setUpdatedDt(rs.getString("updated_dt"));
				pe.setUpdatedBy(rs.getString("updated_by"));

				projEmpList.add(pe);

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

		return projEmpList;
	}

	public ArrayList<Employee> getEmpList() throws SQLException {

		ArrayList<Employee> empList = new ArrayList<Employee>();
		con = new DBConn();
		query = "SELECT id, concat(concat(first_name,' '), last_name) as `first_name`, last_name FROM `c2pidb`.`employees` where id not in (select emp_id from `c2pidb`.`projects_employees` ) ";
		try {
			conn = con.getConn();

			pstmt = conn.prepareStatement(query);
			System.out.println("query:" + query);
			rs = pstmt.executeQuery();
			System.out.println("res=" + rs);

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
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

	public ArrayList<Project> getProjList(int empID) throws SQLException {

		ArrayList<Project> projList = new ArrayList<Project>();
		Project emp = null;
		con = new DBConn();

		query = "SELECT id, name FROM `c2pidb`.`projects` where mgr_id =? ";
		try {
			conn = con.getConn();

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empID);
			System.out.println("query:" + query);
			rs = pstmt.executeQuery();
			System.out.println("res=" + rs);

			while (rs.next()) {
				emp = new Project();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));

				projList.add(emp);

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

		return projList;
	}
}
