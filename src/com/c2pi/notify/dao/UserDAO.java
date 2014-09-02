package com.c2pi.notify.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.c2pi.notify.common.DBConn;
import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.User;

/**
 * @author Shailendrak
 * 
 */
public class UserDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;

	String query = "";
	int res = 0;
	DBConn con = null;
	ResultSet rs = null;
	User edituser = null;
	int queryResult = 0;
	Logger logger = Logger.getLogger(UserDAO.class.getName());

	/**
	 * @param user
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String addUser(User user) throws SQLException,
			ClassNotFoundException, IOException {
		con = new DBConn();

		conn = con.getConn();
		if (user.getId() == 0) {
			query = "Insert Into `c2pidb`.`users` (`login_id`,`password`,`emp_id`,`created_by`,`created_dt`, `updated_by`, `updated_dt`) values(?,?,?,?,?,?,?)";
			
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, user.getLoginID());
		pstmt.setString(2, user.getPassword());
		pstmt.setInt(3, user.getEmpID());
		pstmt.setString(4, user.getCreatedBy());
		pstmt.setString(5, user.getCreatedDt());
		pstmt.setString(6, user.getUpdatedBy());
		pstmt.setString(7, user.getUpdatedDt());
			
		
		}else {
			query = "update `c2pidb`.`users` set `login_id`=?,`password`=?,`emp_id`=?,`updated_by`=?,`updated_dt`=? where id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getLoginID());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getEmpID());
			pstmt.setString(4, user.getUpdatedBy());
			pstmt.setString(5, user.getUpdatedDt());
			pstmt.setInt(6, user.getId());
		}
		logger.info("pstmt" + pstmt);
		res = pstmt.executeUpdate();
		logger.info("res=" + res);

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return (res + "- User added.");
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<Employee> getEmpList() throws SQLException,
			ClassNotFoundException, IOException {
		Employee emp = null;
		ArrayList<Employee> empList = new ArrayList<Employee>();
		query = "Select `id`, concat(concat(`first_name`,' '),`last_name`) name from `c2pidb`.`Employees`";

		con = new DBConn();

		conn = con.getConn();
		pstmt = conn.prepareStatement(query);

		logger.info("pstmt:" + pstmt);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			emp = new Employee();
			emp.setId(rs.getInt("id"));
			emp.setFirstName(rs.getString("name"));
			logger.info(rs.getString("name"));
			empList.add(emp);
		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return empList;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<User> getUserList() throws SQLException,
			ClassNotFoundException, IOException {
		ArrayList<User> userList = new ArrayList<User>();
		query = "SELECT u.`id`, `login_id`,`emp_id`,CONCAT(first_name,' ',last_name) AS empName,u.`created_dt`,u.`created_by`, u.`updated_dt`,u.`updated_by`" +
				" FROM `c2pidb`.`users` AS u,`c2pidb`.`employees` AS e WHERE u.emp_id=e.id ";
		User user = null;
		con = new DBConn();
		conn = con.getConn();

		pstmt = conn.prepareStatement(query);
		logger.info("pstmt" + pstmt);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setLoginID(rs.getString("login_id"));
			user.setEmpID(rs.getInt("emp_id"));
			user.setEmpName(rs.getString("empName"));
			user.setCreatedDt(rs.getString("created_dt"));
			user.setCreatedBy(rs.getString("created_by"));
			user.setUpdatedDt(rs.getString("updated_dt"));
			user.setUpdatedBy(rs.getString("updated_by"));
			userList.add(user);
		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return userList;
	}

	public User getedituserList(int userId) throws SQLException,
			ClassNotFoundException, IOException {
		// ArrayList<Project> projList = new ArrayList<Project>();
		con = new DBConn();

		conn = con.getConn();
		query = "SELECT `id`, `login_id`,`emp_id`,`created_dt`,`created_by`, `updated_dt`,`updated_by` FROM `c2pidb`.`users` where id=?";
		System.out.println("query" + query);
		pstmt = conn.prepareStatement(query);
		logger.info(pstmt);
		pstmt.setInt(1, userId);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			edituser = new User();
			edituser.setId(rs.getInt("id"));
			edituser.setLoginID(rs.getString("login_id"));
			edituser.setEmpID(rs.getInt("emp_id"));
			edituser.setCreatedDt(rs.getString("created_dt"));
			logger.info("created_dt= " + rs.getString("created_dt"));
			edituser.setCreatedBy(rs.getString("created_by"));
			logger.info("created_by= " + rs.getString("created_by"));
			edituser.setUpdatedDt(rs.getString("updated_dt"));
			logger.info("updated_dt= " + rs.getString("updated_dt"));
			edituser.setUpdatedBy(rs.getString("updated_by"));
			logger.info("updated_by= " + rs.getString("updated_by"));

		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return edituser;
	}

	public String userDelete(int projectId) throws SQLException, IOException,
			ClassNotFoundException {
		con = new DBConn();

		query = "DELETE from `c2pidb`.`users` where id=?";
		con = new DBConn();
		conn = con.getConn();

		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, projectId);
		logger.info("query:" + query);
		System.out.println("pstmt= " + pstmt);
		queryResult = pstmt.executeUpdate();
		System.out.println("queryResult:" + queryResult);

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
		return queryResult + "-rows deleted.";
	}
}