package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.TaskFrequencies;

public class TaskFreqDAO {

	public String addTaskFreq(String name, String desc, String status,
			String created_by) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "Insert Into `c2pidb`.`Task_Frequencies` (`name`,`desc`,`status`,`created_by`) values (?,?,?,?)";

		int res = 0;
		DBConn con = new DBConn();

		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			pstmt.setString(3, status);
			pstmt.setString(4, created_by);
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

		return (res + "- Task Freq added.");
	}

	public ArrayList<TaskFrequencies> gettflist() {
		ArrayList<TaskFrequencies> tflist = new ArrayList<TaskFrequencies>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select id, name, `desc`, status, created_dt, created_by, updated_dt, updated_by from `c2pidb`.`task_frequencies`";
		DBConn con = new DBConn();
		conn = con.getConn();
		try {

			pstmt = conn.prepareStatement(query);
			System.out.println("pstmt" + pstmt);

			rs = pstmt.executeQuery();
			System.out.println("rs" + rs);
			while (rs.next()) {
				TaskFrequencies tf = new TaskFrequencies();
				tf.setId(rs.getInt("id"));
				tf.setName(rs.getString("name"));
				tf.setDesc(rs.getString("desc"));
				tf.setStatus(rs.getString("status"));
				tf.setCreated_dt(rs.getString("created_dt"));
				tf.setCreated_by(rs.getString("created_by"));
				tf.setUpdated_dt(rs.getString("updated_dt"));
				tf.setUpdated_by(rs.getString("updated_by"));
				tflist.add(tf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return tflist;
	}
}
