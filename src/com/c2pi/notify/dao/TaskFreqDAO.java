package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.TaskFrequency;

public class TaskFreqDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	String query = "";
	int queryResult = 0;
	DBConn con = null;
	ResultSet rs = null;

//	public String addTaskFreq(String name, String desc, String status,
//			String created_by) throws SQLException {
	public String addTaskFreq(TaskFrequency taskFreq) throws SQLException {	
		query = "Insert Into `c2pidb`.`Task_Frequencies` (`name`,`desc`,`status`,`created_dt`,`created_by`,`updated_dt`,`updated_by`) values (?,?,?,?,?,?,?)";
		con = new DBConn();
		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, taskFreq.getName());
			pstmt.setString(2, taskFreq.getDesc());
			pstmt.setString(3, taskFreq.getStatus());
			pstmt.setString(4, taskFreq.getCreatedDt());
			pstmt.setString(5, taskFreq.getCreatedBy());
			pstmt.setString(6, taskFreq.getUpdatedDt());
			pstmt.setString(7, taskFreq.getUpdatedBy());
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

		return (queryResult + "- Task Freq added.");
	}

	public ArrayList<TaskFrequency> getTaskFreqList() throws SQLException {
		ArrayList<TaskFrequency> tfList = new ArrayList<TaskFrequency>();
		query = "select id, name, `desc`, status, created_dt, created_by, updated_dt, updated_by from `c2pidb`.`task_frequencies`";
		con = new DBConn();
		conn = con.getConn();
		try {

			pstmt = conn.prepareStatement(query);
			System.out.println("pstmt" + pstmt);

			rs = pstmt.executeQuery();
			System.out.println("rs" + rs);
			while (rs.next()) {
				TaskFrequency tf = new TaskFrequency();
				tf.setId(rs.getInt("id"));
				tf.setName(rs.getString("name"));
				tf.setDesc(rs.getString("desc"));
				tf.setStatus(rs.getString("status"));
				tf.setCreatedDt(rs.getString("created_dt"));
				tf.setCreatedBy(rs.getString("created_by"));
				tf.setUpdatedDt(rs.getString("updated_dt"));
				tf.setUpdatedBy(rs.getString("updated_by"));
				tfList.add(tf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return tfList;
	}
}
