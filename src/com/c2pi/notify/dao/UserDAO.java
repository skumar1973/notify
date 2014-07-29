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
		query = "Insert Into `c2pidb`.`users` (`login_id`,`password`,`emp_id`,`created_by`,`created_dt`, `updated_by`, `updated_dt`) values(?,?,?,?,?,?,?)";
		con = new DBConn();

		conn = con.getConn();
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, user.getLoginID());
		pstmt.setString(2, user.getPassword());
		pstmt.setInt(3, user.getEmpID());
		pstmt.setString(4, user.getCreatedBy());
		pstmt.setString(5, user.getCreatedDt());
		pstmt.setString(6, user.getUpdatedBy());
		pstmt.setString(7, user.getUpdatedDt());
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
		query = "SELECT `id`, `login_id`,`emp_id`,`created_dt`,`created_by`, `updated_dt`,`updated_by` FROM `c2pidb`.`users`";
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
}
