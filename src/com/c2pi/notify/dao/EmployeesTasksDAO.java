package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.Employees;
import com.c2pi.notify.entity.EmployeesTasks;
import com.c2pi.notify.entity.Tasks;

public class EmployeesTasksDAO {

	public String addemployeestasks(int emp_id, int task_id, String created_by)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res = 0;
		String query = "Insert Into `c2pidb`.`employees_tasks` ( `emp_id`, `task_id`, `created_by`) values(?,?,?)";
		DBConn con = new DBConn();

		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, emp_id);
			pstmt.setInt(2, task_id);
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

		return (res + "- Employee Task added.");
	}

	public ArrayList<EmployeesTasks> getemptaskslist(int emp_id)
			throws SQLException {

		ArrayList<EmployeesTasks> emptaskslist = new ArrayList<EmployeesTasks>();
		ArrayList<Employees> emplist = new ArrayList<Employees>();
		ArrayList<Tasks> taskslist = new ArrayList<Tasks>();
		Connection conn = null;
		ResultSet res = null;
		DBConn con = new DBConn();
		PreparedStatement pstmt = null;
		String query = "SELECT id, emp_id, task_id, created_dt, created_by, updated_dt, updated_by FROM `c2pidb`.`employees_tasks` where emp_id in (select  emp_id from `c2pidb`.`projects_employees` where proj_id in (select id from `c2pidb`.`projects` where mgr_id = ?))";

		try {
			conn = con.getConn();
			System.out.println("mgr id=" + emp_id);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, emp_id);
			System.out.println("query:" + query);
			res = pstmt.executeQuery();
			System.out.println("res=" + res);

			while (res.next()) {
				EmployeesTasks et = new EmployeesTasks();
				et.setId(res.getInt("id"));
				et.setEmp_id(res.getInt("emp_id"));
				et.setTask_id(res.getInt("task_id"));
				et.setCreated_dt(res.getString("created_dt"));
				et.setCreated_by(res.getString("created_by"));
				et.setUpdated_dt(res.getString("updated_dt"));
				et.setUpdated_by(res.getString("updated_by"));
				emptaskslist.add(et);

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

		return emptaskslist;
	}

	public ArrayList<Employees> getemplist(int emp_id) throws SQLException {

		ArrayList<Employees> emplist = new ArrayList<Employees>();
		Connection conn = null;
		ResultSet res = null;
		DBConn con = new DBConn();
		PreparedStatement pstmt = null;

		String empquery = "SELECT id, concat(concat(first_name,' '), last_name) first_name, last_name FROM `c2pidb`.`employees` where id in (select emp_id from `c2pidb`.`projects_employees` where proj_id in (select id from `c2pidb`.`projects` where mgr_id = ?))";

		try {
			conn = con.getConn();
			System.out.println("mgr id=" + emp_id);
			// employee list
			pstmt = conn.prepareStatement(empquery);
			pstmt.setInt(1, emp_id);
			System.out.println("pstmt" + pstmt);
			res = pstmt.executeQuery();
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

	public ArrayList<Tasks> gettaskslist(int emp_id) throws SQLException {

		ArrayList<Tasks> taskslist = new ArrayList<Tasks>();
		Connection conn = null;
		ResultSet res = null;
		DBConn con = new DBConn();
		PreparedStatement pstmt = null;
		String taskquery = "SELECT  id, name FROM `c2pidb`.`tasks`";
		try {
			conn = con.getConn();
			System.out.println("mgr id=" + emp_id);

			// tasks list
			pstmt = conn.prepareStatement(taskquery);
			System.out.println("pstmt" + pstmt);
			res = pstmt.executeQuery();
			while (res.next()) {
				Tasks task = new Tasks();
				task.setId(res.getInt("id"));
				task.setName(res.getString("name"));
				taskslist.add(task);
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

		return taskslist;
	}

}
