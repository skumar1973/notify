package com.c2pi.notify.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.c2pi.notify.common.DBConn;
import com.c2pi.notify.entity.TaskFrequency;

/**
 * @author Shailendrak
 * 
 */
public class TaskFreqDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	String query = "";
	int queryResult = 0;
	DBConn con = null;
	ResultSet rs = null;
	
	Logger logger = Logger.getLogger(TaskFreqDAO.class.getName());
	
	/**
	 * @param taskFreq
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public String addTaskFreq(TaskFrequency taskFreq) throws SQLException,
			ClassNotFoundException, IOException {
		con = new DBConn();
		conn = con.getConn();

		if ((taskFreq.getId() == 0)) {
			query = "Insert Into `c2pidb`.`Task_Frequencies` (`name`,`desc`,`status`,`created_dt`,`created_by`,`updated_dt`,`updated_by`) values (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, taskFreq.getName());
			pstmt.setString(2, taskFreq.getDesc());
			pstmt.setString(3, taskFreq.getStatus());
			pstmt.setString(4, taskFreq.getCreatedDt());
			pstmt.setString(5, taskFreq.getCreatedBy());
			pstmt.setString(6, taskFreq.getUpdatedDt());
			pstmt.setString(7, taskFreq.getUpdatedBy());
		} else {
			query = "Update `c2pidb`.`Task_Frequencies` set `name`=?,`desc`=?,`status`=?,`created_dt`=?,`created_by`=?,`updated_dt`=?,`updated_by`=? where `id`=? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, taskFreq.getName());
			pstmt.setString(2, taskFreq.getDesc());
			pstmt.setString(3, taskFreq.getStatus());
			pstmt.setString(4, taskFreq.getCreatedDt());
			pstmt.setString(5, taskFreq.getCreatedBy());
			pstmt.setString(6, taskFreq.getUpdatedDt());
			pstmt.setString(7, taskFreq.getUpdatedBy());
			pstmt.setInt(8, taskFreq.getId());
		}
		logger.info("query:"+query);
		System.out.println("query:" + query);
		queryResult = pstmt.executeUpdate();
		System.out.println("res=" + queryResult);

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return (queryResult + "- Task Freq saved/updated.");
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<TaskFrequency> getTaskFreqList() throws SQLException,
			ClassNotFoundException, IOException {
		ArrayList<TaskFrequency> tfList = new ArrayList<TaskFrequency>();
		query = "select id, name, `desc`, status, created_dt, created_by, updated_dt, updated_by from `c2pidb`.`task_frequencies`";
		con = new DBConn();
		conn = con.getConn();

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

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return tfList;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public TaskFrequency getTaskFreqById(int tfID)
			throws SQLException, ClassNotFoundException, IOException {
		TaskFrequency tf = new TaskFrequency();
		query = "select id, name, `desc`, status, created_dt, created_by, updated_dt, updated_by from `c2pidb`.`task_frequencies` where id=?";
		con = new DBConn();
		conn = con.getConn();

		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, tfID);
		System.out.println("pstmt" + pstmt);

		rs = pstmt.executeQuery();
		System.out.println("rs" + rs);
		while (rs.next()) {
			tf.setId(rs.getInt("id"));
			tf.setName(rs.getString("name"));
			tf.setDesc(rs.getString("desc"));
			tf.setStatus(rs.getString("status"));
			tf.setCreatedDt(rs.getString("created_dt"));
			tf.setCreatedBy(rs.getString("created_by"));
			tf.setUpdatedDt(rs.getString("updated_dt"));
			tf.setUpdatedBy(rs.getString("updated_by"));
			
		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return tf;
	}
	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public String deleteTF(int tfID)
			throws SQLException, ClassNotFoundException, IOException {
		
		query = "DELETE from `c2pidb`.`task_frequencies` where id=?";
		con = new DBConn();
		conn = con.getConn();

		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, tfID);
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
