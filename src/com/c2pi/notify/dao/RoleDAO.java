package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.Role;

public class RoleDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = "";
	int res = 0;
	DBConn con = null;

	public String addRole(Role role) throws SQLException {
		query = "Insert Into `c2pidb`.`Roles` (`name`,`desc`,`status`,`created_by`,`created_dt`,`updated_by`,`updated_dt`) values (?,?,?,?,?,?,?)";
		con = new DBConn();

		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, role.getName());
			pstmt.setString(2, role.getDesc());
			pstmt.setString(3, role.getStatus());
			pstmt.setString(4, role.getCreatedBy());
			pstmt.setString(5, role.getCreatedDt());
			pstmt.setString(6, role.getUpdatedBy());
			pstmt.setString(7, role.getUpdatedDt());
			
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

		return (res + "- role added.");
	}

	public ArrayList<Role> getRoleList() throws SQLException {

		ArrayList<Role> roleList = new ArrayList<Role>();
		Role role = null;
		con = new DBConn();
		conn = con.getConn();
		query = "SELECT id, name, `desc`, status, created_dt, created_by, updated_dt, updated_by FROM `c2pidb`.`roles`";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			System.out.println("query" + query);
			while (rs.next()) {
				role = new Role();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setDesc(rs.getString("desc"));
				role.setStatus(rs.getString("status"));
				role.setCreatedDt(rs.getString("created_dt"));
				role.setCreatedBy(rs.getString("created_by"));
				role.setUpdatedDt(rs.getString("updated_dt"));
				role.setUpdatedBy(rs.getString("updated_by"));
				roleList.add(role);
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
		return roleList;
	}
}
