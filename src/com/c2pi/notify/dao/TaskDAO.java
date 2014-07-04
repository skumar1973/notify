package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.c2pi.notify.entity.TaskFrequencies;
import com.c2pi.notify.entity.Tasks;

public class TaskDAO {


	public String addTask(String name, String desc, String status, int freq_id,
			String created_by) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String query = "Insert Into `c2pidb`.`Tasks` (`name`,`desc`,`status`,`freq_id`,`created_by`) values(?,?,?,?,?)";
		int res = 0;
		DBConn con = new DBConn();

		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			pstmt.setString(3, status);
			pstmt.setInt(4, freq_id);
			pstmt.setString(5,created_by);
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

		return (res + "- Task added.");
	}

	public ArrayList<TaskFrequencies> getFreqId() throws SQLException {

		ArrayList<TaskFrequencies> tflist = new ArrayList<TaskFrequencies>();
		Connection conn = null;
		Statement stmt = null;
		String query = "";

		DBConn con = new DBConn();
		ResultSet rs = null;
		try {
			conn = con.getConn();
			stmt = conn.createStatement();
			query = "Select `id`,`name` from `c2pidb`.`Task_frequencies`";

			System.out.println("query:" + query);
			rs = stmt.executeQuery(query);
			System.out.println("res=" + rs);

			while (rs.next()) {
				TaskFrequencies tf = new TaskFrequencies();
				tf.setId(rs.getInt("id"));
				tf.setName(rs.getString("name"));

				tflist.add(tf);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}

		return tflist;
	}

	public ArrayList<Tasks> gettaskslist(){
		ArrayList<Tasks> tslist = new ArrayList<Tasks>();
		Connection conn = null;
		PreparedStatement pstmt =null;
		String query="SELECT `id`, `name`,`desc`,`status`, `freq_id`, `created_dt`,`created_by`, `updated_dt`,`updated_by` FROM `c2pidb`.`tasks`";
		DBConn con = new DBConn();
		ResultSet rs =null;
		conn = con.getConn();
		try {
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			System.out.println("query"+query);
			while (rs.next()){
				Tasks ts = new Tasks();
				ts.setId(rs.getInt("id"));
				ts.setName(rs.getString("name"));
				ts.setDesc(rs.getString("desc"));
				ts.setStatus(rs.getString("status"));
				ts.setFreq_id(rs.getInt("freq_id"));
				ts.setCreated_dt(rs.getString("created_dt"));
				ts.setCreated_by(rs.getString("created_by"));
				ts.setUpdated_dt(rs.getString("updated_dt"));
				ts.setUpdated_by(rs.getString("updated_by"));
				tslist.add(ts);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn !=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return tslist;
	}
}
