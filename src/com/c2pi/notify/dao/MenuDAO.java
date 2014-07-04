package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.Menus;

public class MenuDAO {

	public String addMenu(String name, int parent_id, String desc,
			String status, String target, String created_by)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into `c2pidb`.`menus`(name, parent_id, `desc`, status, target, created_by) values(?,?,?,?,?,?)";
		String parent_id_query = "insert into `c2pidb`.`menus`(name, `desc`, status, target, created_by) values(?,?,?,?,?)";
		int res = 0;
		DBConn con = new DBConn();

		try {
			conn = con.getConn();
			if (parent_id > 0) {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setInt(2, parent_id);
				pstmt.setString(3, desc);
				pstmt.setString(4, status);
				pstmt.setString(5, target);
				pstmt.setString(6, created_by);
			} else {
				pstmt = conn.prepareStatement(parent_id_query);
				pstmt.setString(1, name);
				pstmt.setString(2, desc);
				pstmt.setString(3, status);
				pstmt.setString(4, target);
				pstmt.setString(5, created_by);
			}

			System.out.println("pstmt" + pstmt);
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return (res + "- Menu added.");

	}

	public ArrayList<Menus> getmenulist() {
		ArrayList<Menus> menulist = new ArrayList<Menus>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConn con = new DBConn();
		conn = con.getConn();
		String query = "select id, name, parent_id, `desc`, status, target, created_dt, created_by, updated_dt, updated_by from c2pidb.menus";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Menus menu = new Menus();
				menu.setId(rs.getInt("id"));
				menu.setName(rs.getString("name"));
				menu.setParent_id(rs.getInt("parent_id"));
				menu.setDesc(rs.getString("desc"));
				menu.setStatus(rs.getString("status"));
				menu.setTarget(rs.getString("target"));
				menu.setCreated_dt(rs.getString("created_dt"));
				menu.setCreated_by(rs.getString("created_by"));
				menu.setUpdated_dt(rs.getString("updated_dt"));
				menu.setUpdated_by(rs.getString("updated_by"));
				menulist.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return menulist;
	}

}