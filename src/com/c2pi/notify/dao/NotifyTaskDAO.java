package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.c2pi.notify.entity.EmployeesTaskNotification;
import com.c2pi.notify.entity.Tasks;

public class NotifyTaskDAO {

	public String addnotifytask(int emp_id, Integer[] emptask,
			Date period_date, String created_by) throws SQLException {
		int res = 0, tres = 0;
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "Insert Into `c2pidb`.`employees_task_notification` ( `emp_id`, `task_id`, `period_date`, `created_by`) values (?,?,?,?)";

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sd.format(period_date));
		DBConn con = new DBConn();
		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			System.out
					.println(DateFormat.getDateInstance().format(period_date));
			while (cnt < emptask.length) {

				System.out.println("[" + cnt + "]=" + emptask[cnt]);
				pstmt.setInt(1, emp_id);
				pstmt.setInt(2, emptask[cnt]);
				pstmt.setString(3, sd.format(period_date));
				pstmt.setString(4, created_by);
				res = pstmt.executeUpdate();
				tres = tres + res;
				cnt = cnt + 1;
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

		return (tres + "- Notify Task added.");
	}

	public ArrayList<EmployeesTaskNotification> getetnlist(String login_id)
			throws SQLException {

		ArrayList<EmployeesTaskNotification> etnlist = new ArrayList<EmployeesTaskNotification>();

		ResultSet res = null;
		DBConn con = new DBConn();
		Connection conn = null;
		String query = "SELECT etn.id, etn.emp_id, etn.task_id, etn.period_date, etn.created_by, etn.created_dt, etn.updated_by, etn.updated_dt "
				+ "FROM `c2pidb`.`employees_task_notification` etn , c2pidb.tasks t "
				+ "where etn.emp_id in (select u.emp_id from `c2pidb`.`users` u where u.login_id=?) "
				+ "and etn.task_id = t.id " + " order by period_date desc";

		PreparedStatement pstmt = null;

		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			System.out.println("login id" + login_id);
			pstmt.setString(1, login_id);

			System.out.println("pstmt:" + pstmt);
			res = pstmt.executeQuery();
			System.out.println("res=" + res);

			while (res.next()) {
				EmployeesTaskNotification etn = new EmployeesTaskNotification();
				etn.setId(res.getInt("id"));
				etn.setEmp_id(res.getInt("emp_id"));
				etn.setTask_id(res.getInt("task_id"));
				etn.setPeriod_date(res.getString("period_date"));
				etn.setCreated_by(res.getString("created_by"));
				etn.setCreated_dt(res.getString("created_dt"));
				etn.setUpdated_by(res.getString("updated_by"));
				etn.setUpdated_dt(res.getString("updated_dt"));
				etnlist.add(etn);

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

		return etnlist;
	}

	public ArrayList<Tasks> getemptasklist(String login_id) throws SQLException {

		ArrayList<Tasks> emptasklist = new ArrayList<Tasks>();

		ResultSet res = null;
		DBConn con = new DBConn();
		Connection conn = null;
		String query = "SELECT  t.id, t.name "
				+ "FROM `c2pidb`.`employees_tasks` et, `c2pidb`.`tasks` t "
				+ "WHERE et.task_id = t.id and et.emp_id "
				+ "in (select u.emp_id from `c2pidb`.`users` u where u.login_id=?) ";
		PreparedStatement pstmt = null;

		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			System.out.println("login id" + login_id);
			pstmt.setString(1, login_id);

			System.out.println("pstmt:" + pstmt);
			res = pstmt.executeQuery();
			System.out.println("res=" + res);

			while (res.next()) {
				Tasks task = new Tasks();
				System.out.println(res.getString("name"));
				task.setId(res.getInt("id"));
				task.setName(res.getString("name"));
				emptasklist.add(task);
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

		return emptasklist;
	}

	public String getTaskName(Integer[] emptask) {
		String taskname = "";
		if (emptask.length == 0) {

		} else {
			String query = "select name from c2pidb.tasks where id in(";
			int cnt = 0;
			while (cnt < emptask.length) {
				query = query + emptask[cnt];
				if (cnt == (emptask.length - 1))
					query = query + ")";
				else
					query = query + ",";
				cnt = cnt + 1;

			}
			DBConn conn = new DBConn();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			Connection con=conn.getConn();
			try {
				System.out.println(pstmt);
				pstmt=con.prepareStatement(query);
				rs=pstmt.executeQuery();
				while (rs.next()){
					taskname=taskname+" , "+rs.getString("name");
				}
				System.out.println("taskname="+taskname);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (pstmt !=null)
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (con != null)
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			
		}

		return taskname;
	}
}
