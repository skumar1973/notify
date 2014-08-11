package com.c2pi.notify.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.common.DBConn;
import com.c2pi.notify.entity.Task;

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
		con = new DBConn();

		conn = con.getConn();
		//add
		if (task.getId()==0) {
		query = "Insert Into `c2pidb`.`Tasks` (`name`,`desc`,`status`,`freq_id`,`created_by`,`created_dt`,`Updated_by`,`Updated_dt`) values(?,?,?,?,?,?,?,?)";
		
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
		}
		//update
		else {
			query = "Update `c2pidb`.`Tasks` set `name`=?,`desc`=?,`status`=?,`freq_id`=?,`created_by`=?,`created_dt`=?,`Updated_by`=?,`Updated_dt`=? where `id`=? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, task.getName());
			pstmt.setString(2, task.getDesc());
			pstmt.setString(3, task.getStatus());
			pstmt.setInt(4, task.getFreqID());
			pstmt.setString(5, task.getCreatedBy());
			pstmt.setString(6, task.getCreatedDt());
			pstmt.setString(7, task.getUpdatedBy());
			pstmt.setString(8, task.getUpdatedDt());
			pstmt.setInt(9, task.getId());
			System.out.println("pstmt:" + pstmt);
			
		}
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
	public Task getTaskById(int tsID) throws SQLException,
			ClassNotFoundException, IOException {

		Task ts = new Task();
		con = new DBConn();

		conn = con.getConn();
		query = "SELECT `id`, `name`,`desc`,`status`, `freq_id`, `created_dt`,`created_by`, `updated_dt`,`updated_by` FROM `c2pidb`.`tasks` where id=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, tsID);
		System.out.println("pstmt:" + pstmt);
		rs = pstmt.executeQuery();
		System.out.println("res=" + rs);
		while (rs.next()) {
			ts.setId(rs.getInt("id"));
			ts.setName(rs.getString("name"));
			ts.setDesc(rs.getString("desc"));
			ts.setStatus(rs.getString("status"));
			ts.setFreqID(rs.getInt("freq_id"));
			ts.setCreatedDt(rs.getString("created_dt"));
			ts.setCreatedBy(rs.getString("created_by"));
			ts.setUpdatedDt(rs.getString("updated_dt"));
			ts.setUpdatedBy(rs.getString("updated_by"));
		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return ts;
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
		query = "SELECT t.`id`, t.`name`, t.`desc`, t.`status`, tf.`name` tf_name, t.`created_dt`, t.`created_by`, t.`updated_dt`, t.`updated_by` FROM `c2pidb`.`tasks` t LEFT OUTER JOIN `c2pidb`.`task_frequencies` tf ON t.freq_id=tf.id";
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
			ts.setFreqName(rs.getString("tf_name"));
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
	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public String deleteTask(int tsID)
			throws SQLException, ClassNotFoundException, IOException {
		int queryResult=0;
		query = "DELETE from `c2pidb`.`tasks` where id=?";
		con = new DBConn();
		conn = con.getConn();

		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, tsID);
		System.out.println("pstmt" + pstmt);

		queryResult = pstmt.executeUpdate();
		System.out.println("queryResult:" + queryResult);


		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return queryResult+"-rows deleted.";
	}

}
