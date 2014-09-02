package com.c2pi.notify.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.common.DBConn;
import com.c2pi.notify.entity.Role;

/**
 * @author Shailendrak
 * 
 */
public class RoleDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = "";
	int res = 0;
	private DBConn con = new DBConn();
	private ArrayList<Role> roleList = null;

	public String addRole(Role role) throws SQLException,
			ClassNotFoundException, IOException {
		// con = new DBConn();
		conn = con.getConn();
		if (role.getId() == 0) {
			query = "insert into roles (name,`desc`,status,created_by,created_dt,"
					+ "updated_by,updated_dt) values (?,?,?,?,?,?,?)";
		} else {
			query = "update roles set name =?,`desc`=?,status=?,created_by=?,"
					+ "created_dt=?,updated_by=?,updated_dt=? where id=?";

		}
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, role.getName());
		pstmt.setString(2, role.getDesc());
		pstmt.setString(3, role.getStatus());
		pstmt.setString(4, role.getCreatedBy());
		pstmt.setString(5, role.getCreatedDt());
		pstmt.setString(6, role.getUpdatedBy());
		pstmt.setString(7, role.getUpdatedDt());

		System.out.println("query:" + query);
		if (role.getId() != 0) {

			pstmt.setInt(8, role.getId());

		}

		res = pstmt.executeUpdate();
		System.out.println("res=" + res);

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return (res + "- role added/updated.");
	}

	public ArrayList<Role> getRoleList() throws SQLException,
			ClassNotFoundException, IOException {

		roleList = new ArrayList<Role>();
		Role role = null;
		con = new DBConn();
		conn = con.getConn();
		query = "SELECT id, name, `desc`, status, created_dt, created_by, updated_dt, updated_by FROM `c2pidb`.`roles`";

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

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return roleList;
	}

	public Role getRoleById(int roleID) throws SQLException,
			ClassNotFoundException, IOException {
		Role role = new Role();
		query = "SELECT id, name, `desc`, status, created_dt, created_by, updated_dt, updated_by FROM `c2pidb`.`roles` where id =?";
		conn = con.getConn();
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, roleID);
		System.out.println("pstmt:" + pstmt);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			role.setId(rs.getInt("id"));
			role.setName(rs.getString("name"));
			role.setDesc(rs.getString("desc"));
			role.setStatus(rs.getString("status"));
			role.setCreatedDt(rs.getString("created_dt"));
			role.setCreatedBy(rs.getString("created_by"));
			role.setUpdatedDt(rs.getString("updated_dt"));
			role.setUpdatedBy(rs.getString("updated_by"));
		}
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
		return role;

	}

	public String deleteRole(int roleID) throws SQLException,
			ClassNotFoundException, IOException {

		query = "DELETE from `c2pidb`.`roles` where id=?";
		con = new DBConn();
		conn = con.getConn();

		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, roleID);
		System.out.println("pstmt" + pstmt);

		int result = pstmt.executeUpdate();
		System.out.println("queryResult:" + result);

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return result + "-rows deleted.";
	}

}
