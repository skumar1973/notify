package com.c2pi.notify.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.c2pi.notify.common.DBConn;
import com.c2pi.notify.entity.EmployeesTaskNotification;
import com.c2pi.notify.entity.Task;

/**
 * @author Shailendrak
 * 
 */
public class NotifyTaskDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	String query = "";
	DBConn conn = null;
	ResultSet rs = null;

	/**
	 * @param emp_id
	 * @param emptask
	 * @param period_date
	 * @param created_by
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String addNotifyTask(int emp_id, Integer[] emptask,
			Date period_date, String created_by) throws SQLException,
			ClassNotFoundException, IOException {
		query = "Insert Into `c2pidb`.`employees_task_notification` ( `emp_id`, `task_id`, `period_date`, `created_by`) values (?,?,?,?)";
		int res = 0, tres = 0;
		int cnt = 0;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sd.format(period_date));
		conn = new DBConn();

		con = conn.getConn();
		pstmt = con.prepareStatement(query);
		System.out.println(DateFormat.getDateInstance().format(period_date));
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

		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();

		return (tres + "- Notify Task added.");
	}

	public ArrayList<EmployeesTaskNotification> getETNList(String loginID)
			throws SQLException, ClassNotFoundException, IOException {

		ArrayList<EmployeesTaskNotification> etnList = new ArrayList<EmployeesTaskNotification>();
		EmployeesTaskNotification etn = null;
		conn = new DBConn();
		query = "SELECT etn.id, etn.emp_id, etn.task_id, etn.period_date, etn.created_by, etn.created_dt, etn.updated_by, etn.updated_dt "
				+ "FROM `c2pidb`.`employees_task_notification` etn , c2pidb.tasks t "
				+ "where etn.emp_id in (select u.emp_id from `c2pidb`.`users` u where u.login_id=?) "
				+ "and etn.task_id = t.id " + " order by period_date desc";

		con = conn.getConn();
		pstmt = con.prepareStatement(query);
		System.out.println("login id" + loginID);
		pstmt.setString(1, loginID);

		System.out.println("pstmt:" + pstmt);
		rs = pstmt.executeQuery();
		System.out.println("res=" + rs);

		while (rs.next()) {
			etn = new EmployeesTaskNotification();
			etn.setId(rs.getInt("id"));
			etn.setEmpID(rs.getInt("emp_id"));
			etn.setTaskID(rs.getInt("task_id"));
			etn.setPeriodDate(rs.getString("period_date"));
			etn.setCreatedBy(rs.getString("created_by"));
			etn.setCreatedDt(rs.getString("created_dt"));
			etn.setUpdatedBy(rs.getString("updated_by"));
			etn.setUpdatedDt(rs.getString("updated_dt"));
			etnList.add(etn);

		}

		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();

		return etnList;
	}

	/**
	 * @param loginID
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<Task> getEmpTaskList(String loginID) throws SQLException,
			ClassNotFoundException, IOException {

		Task task = null;
		ArrayList<Task> empTaskList = new ArrayList<Task>();
		conn = new DBConn();
		query = "SELECT  t.id, t.name "
				+ "FROM `c2pidb`.`employees_tasks` et, `c2pidb`.`tasks` t "
				+ "WHERE et.task_id = t.id and et.emp_id "
				+ "in (select u.emp_id from `c2pidb`.`users` u where u.login_id=?) ";

		con = conn.getConn();
		pstmt = con.prepareStatement(query);
		System.out.println("login id" + loginID);
		pstmt.setString(1, loginID);

		System.out.println("pstmt:" + pstmt);
		rs = pstmt.executeQuery();
		System.out.println("res=" + rs);

		while (rs.next()) {
			task = new Task();
			System.out.println(rs.getString("name"));
			task.setId(rs.getInt("id"));
			task.setName(rs.getString("name"));
			empTaskList.add(task);
		}

		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();

		return empTaskList;
	}

	/**
	 * @param empTask
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String getTaskName(Integer[] empTask) throws SQLException,
			ClassNotFoundException, IOException {
		String taskName = "";
		if (empTask.length == 0) {
			taskName = "";
		} else {
			String query = "select name from c2pidb.tasks where id in(";
			int cnt = 0;
			while (cnt < empTask.length) {
				query = query + empTask[cnt];
				if (cnt == (empTask.length - 1))
					query = query + ")";
				else
					query = query + ",";
				cnt = cnt + 1;

			}
			conn = new DBConn();
			con = conn.getConn();

			System.out.println(pstmt);
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				taskName = taskName + rs.getString("name") + ",";
			}
			System.out.println("taskname=" + taskName);

			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}

		return taskName;
	}

	/**
	 * @param empID
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String getMgrEmail(int empID) throws SQLException,
			ClassNotFoundException, IOException {

		String email = "";
		if (empID != 0) {

			String query = "select email from `c2pidb`.`employees` e where id in ("
					+ " select mgr_id from `c2pidb`.`projects` p"
					+ " where id in ("
					+ "SELECT proj_id FROM `c2pidb`.`projects_employees`  pe "
					+ " where pe.emp_id=?	))";
			conn = new DBConn();
			con = conn.getConn();

			System.out.println(pstmt);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, empID);
			System.out.println("pstmt:" + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				email = rs.getString("email");
			}
			System.out.println("email=" + email);

			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}

		return email;

	}
	public ArrayList<String> getDateList() throws ClassNotFoundException, SQLException, IOException{
		ArrayList<String> dateList= new ArrayList<String>();
		conn = new DBConn();
		con = conn.getConn();
		String query = "SELECT "+
				" date_format(adddate(curdate(), INTERVAL 1-DAYOFWEEK(curdate()) DAY), '%Y-%m-%d') WeekStart1, "+ 
				" date_format(adddate(curdate(), INTERVAL 2-DAYOFWEEK(curdate()) DAY), '%Y-%m-%d') WeekStart2, "+
				" date_format(adddate(curdate(), INTERVAL 3-DAYOFWEEK(curdate()) DAY), '%Y-%m-%d') WeekStart3, "+ 
				" date_format(adddate(curdate(), INTERVAL 4-DAYOFWEEK(curdate()) DAY), '%Y-%m-%d') WeekStart4, "+
				" date_format(adddate(curdate(), INTERVAL 5-DAYOFWEEK(curdate()) DAY), '%Y-%m-%d') WeekStart5, "+
				" date_format(adddate(curdate(), INTERVAL 6-DAYOFWEEK(curdate()) DAY), '%Y-%m-%d') WeekStart6, "+ 
				" date_format(adddate(curdate(), INTERVAL 7-DAYOFWEEK(curdate()) DAY), '%Y-%m-%d') WeekStart7";
		
		pstmt = con.prepareStatement(query);
		rs=pstmt.executeQuery();
		rs.next();
		dateList.add(rs.getString("WeekStart1"));
		dateList.add(rs.getString("WeekStart2"));
		dateList.add(rs.getString("WeekStart3"));
		dateList.add(rs.getString("WeekStart4"));
		dateList.add(rs.getString("WeekStart5"));
		dateList.add(rs.getString("WeekStart6"));
		dateList.add(rs.getString("WeekStart7"));
		int i=0;	
		while (i < dateList.size())
		{
			System.out.println("i="+dateList.get(i));
			i=i+1;
		}
		if (pstmt !=null) pstmt.close();
		if (con != null) con.close();
		return dateList;
	}
}
