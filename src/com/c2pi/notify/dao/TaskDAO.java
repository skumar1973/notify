package com.c2pi.notify.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.common.DBConn;
import com.c2pi.notify.entity.Task;
import com.c2pi.notify.entity.TaskFrequency;

/**
 * @author Shailendrak
 * 
 */
public class TaskDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = "";
	int res = 0;
	DBConn con = null;

	/**
	 * @param task
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String addTask(Task task) throws SQLException,
			ClassNotFoundException, IOException {
		query = "Insert Into `c2pidb`.`Tasks` (`name`,`desc`,`status`,`freq_id`,`created_by`,`created_dt`,`Updated_by`,`Updated_dt`) values(?,?,?,?,?,?,?,?)";
		con = new DBConn();

		conn = con.getConn();
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, task.getName());
		pstmt.setString(2, task.getDesc());
		pstmt.setString(3, task.getStatus());
		pstmt.setInt(4, task.getFreqID());
		pstmt.setString(5, task.getCreatedBy());
		pstmt.setString(6, task.getCreatedDt());
		pstmt.setString(7, task.getUpdatedBy());
		pstmt.setString(8, task.getUpdatedDt());
		System.out.println("pstmt:" + pstmt);
		res = pstmt.executeUpdate();
		System.out.println("res=" + res);

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return (res + "- Task added.");
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<TaskFrequency> getTaskFreqId() throws SQLException,
			ClassNotFoundException, IOException {

		ArrayList<TaskFrequency> taskfreqList = new ArrayList<TaskFrequency>();
		TaskFrequency tf = null;
		con = new DBConn();

		conn = con.getConn();
		query = "Select `id`,`name` from `c2pidb`.`Task_frequencies`";
		pstmt = conn.prepareStatement(query);
		System.out.println("pstmt:" + pstmt);
		rs = pstmt.executeQuery();
		System.out.println("res=" + rs);
		while (rs.next()) {
			tf = new TaskFrequency();
			tf.setId(rs.getInt("id"));
			tf.setName(rs.getString("name"));
			taskfreqList.add(tf);
		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return taskfreqList;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<Task> getTaskList() throws SQLException,
			ClassNotFoundException, IOException {
		ArrayList<Task> tsList = new ArrayList<Task>();
		Task ts = null;
		query = "SELECT `id`, `name`,`desc`,`status`, `freq_id`, `created_dt`,`created_by`, `updated_dt`,`updated_by` FROM `c2pidb`.`tasks`";
		con = new DBConn();
		conn = con.getConn();

		pstmt = conn.prepareStatement(query);
		rs = pstmt.executeQuery();
		System.out.println("query" + query);
		while (rs.next()) {
			ts = new Task();
			ts.setId(rs.getInt("id"));
			ts.setName(rs.getString("name"));
			ts.setDesc(rs.getString("desc"));
			ts.setStatus(rs.getString("status"));
			ts.setFreqID(rs.getInt("freq_id"));
			ts.setCreatedDt(rs.getString("created_dt"));
			ts.setCreatedBy(rs.getString("created_by"));
			ts.setUpdatedDt(rs.getString("updated_dt"));
			ts.setUpdatedBy(rs.getString("updated_by"));
			tsList.add(ts);
		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return tsList;
	}
}
