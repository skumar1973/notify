package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.EmployeeTask;
import com.c2pi.notify.entity.Task;

public class EmployeeTaskDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private int queryResult = 0;
	private String query = "";
	private DBConn con = null;
	private ResultSet rs = null;

	public String addEmployeeTask(int empID, int taskID, String createdBy)
			throws SQLException {
		query = "Insert Into `c2pidb`.`employees_tasks` ( `emp_id`, `task_id`, `created_by`) values(?,?,?)";
		con = new DBConn();

		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empID);
			pstmt.setInt(2, taskID);
			pstmt.setString(3, createdBy);
			System.out.println("query:" + query);
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

		return (queryResult + "- Employee Task added.");
	}

	public ArrayList<EmployeeTask> getEmpTaskList(int empID)
			throws SQLException {

		ArrayList<EmployeeTask> empTaskList = new ArrayList<EmployeeTask>();
		con = new DBConn();

		String query = "SELECT id, emp_id, task_id, created_dt, created_by, updated_dt, updated_by FROM `c2pidb`.`employees_tasks` where emp_id in (select  emp_id from `c2pidb`.`projects_employees` where proj_id in (select id from `c2pidb`.`projects` where mgr_id = ?))";

		try {
			conn = con.getConn();
			System.out.println("mgr id=" + empID);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empID);
			System.out.println("query:" + query);
			rs = pstmt.executeQuery();
			System.out.println("res=" + rs);

			while (rs.next()) {
				EmployeeTask et = new EmployeeTask();
				et.setId(rs.getInt("id"));
				et.setEmpID(rs.getInt("emp_id"));
				et.setTaskID(rs.getInt("task_id"));
				et.setCreatedDt(rs.getString("created_dt"));
				et.setCreatedBy(rs.getString("created_by"));
				et.setUpdatedDt(rs.getString("updated_dt"));
				et.setUpdatedBy(rs.getString("updated_by"));
				empTaskList.add(et);

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

		return empTaskList;
	}

	public ArrayList<Employee> getEmpList(int empID) throws SQLException {

		ArrayList<Employee> empList = new ArrayList<Employee>();
		con = new DBConn();

		query = "SELECT id, concat(concat(first_name,' '), last_name) first_name, last_name FROM `c2pidb`.`employees` where id in (select emp_id from `c2pidb`.`projects_employees` where proj_id in (select id from `c2pidb`.`projects` where mgr_id = ?))";

		try {
			conn = con.getConn();
			System.out.println("mgr id=" + empID);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empID);
			System.out.println("pstmt" + pstmt);
			rs = pstmt.executeQuery();
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

	public ArrayList<Task> getTaskList(int empID) throws SQLException {

		ArrayList<Task> taskList = new ArrayList<Task>();
		con = new DBConn();
		query = "SELECT  id, name FROM `c2pidb`.`tasks`";
		try {
			conn = con.getConn();
			System.out.println("mgr id=" + empID);
			pstmt = conn.prepareStatement(query);
			System.out.println("pstmt" + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Task task = new Task();
				task.setId(rs.getInt("id"));
				task.setName(rs.getString("name"));
				taskList.add(task);
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

		return taskList;
	}

}
