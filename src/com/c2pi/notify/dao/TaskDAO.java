package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.Task;
import com.c2pi.notify.entity.TaskFrequency;

public class TaskDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = "";
	int res = 0;
	DBConn con = null;

	public String addTask(Task task) throws SQLException {
		query = "Insert Into `c2pidb`.`Tasks` (`name`,`desc`,`status`,`freq_id`,`created_by`,`created_dt`,`Updated_by`,`Updated_dt`) values(?,?,?,?,?,?,?,?)";
		con = new DBConn();

		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return (res + "- Task added.");
	}

	public ArrayList<TaskFrequency> getTaskFreqId() throws SQLException {

		ArrayList<TaskFrequency> taskfreqList = new ArrayList<TaskFrequency>();
		TaskFrequency tf = null;
		con = new DBConn();
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return taskfreqList;
	}

	public ArrayList<Task> getTaskList() throws SQLException {
		ArrayList<Task> tsList = new ArrayList<Task>();
		Task ts = null;
		query = "SELECT `id`, `name`,`desc`,`status`, `freq_id`, `created_dt`,`created_by`, `updated_dt`,`updated_by` FROM `c2pidb`.`tasks`";
		con = new DBConn();
		conn = con.getConn();
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}

		return tsList;
	}
}
