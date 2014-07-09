package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.Menu;

public class MenuDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	String query = "";
	String parentIDQuery = "";
	int queryResult = 0;
	DBConn con = null;
	ResultSet rs = null;

	public String addMenu(String name, int parentID, String desc,
			String status, String target, String createdBy) throws SQLException {
		query = "insert into `c2pidb`.`menus`(name, parent_id, `desc`, status, target, created_by) values(?,?,?,?,?,?)";
		parentIDQuery = "insert into `c2pidb`.`menus`(name, `desc`, status, target, created_by) values(?,?,?,?,?)";
		con = new DBConn();

		try {
			conn = con.getConn();
			if (parentID > 0) {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setInt(2, parentID);
				pstmt.setString(3, desc);
				pstmt.setString(4, status);
				pstmt.setString(5, target);
				pstmt.setString(6, createdBy);
			} else {
				pstmt = conn.prepareStatement(parentIDQuery);
				pstmt.setString(1, name);
				pstmt.setString(2, desc);
				pstmt.setString(3, status);
				pstmt.setString(4, target);
				pstmt.setString(5, createdBy);
			}

			System.out.println("pstmt" + pstmt);
			queryResult = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return (queryResult + "- Menu added.");

	}

	public ArrayList<Menu> getMenuList() throws SQLException {
		ArrayList<Menu> menuList = new ArrayList<Menu>();

		con = new DBConn();
		conn = con.getConn();
		query = "select id, name, ifnull(parent_id,0) parent_id,  `desc`, status, target, created_dt, created_by, updated_dt, updated_by from `c2pidb`.`menus`";
		try {
			pstmt = conn.prepareStatement(query);
			System.out.println("pstmt:"+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setName(rs.getString("name"));
				menu.setParentID(rs.getInt("parent_id"));
				menu.setDesc(rs.getString("desc"));
				menu.setStatus(rs.getString("status"));
				menu.setTarget(rs.getString("target"));
				menu.setCreatedDt(rs.getString("created_dt"));
				menu.setCreatedBy(rs.getString("created_by"));
				menu.setUpdatedDt(rs.getString("updated_dt"));
				menu.setUpdatedBy(rs.getString("updated_by"));
				menuList.add(menu);
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

		return menuList;
	}

}