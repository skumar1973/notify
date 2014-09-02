package com.c2pi.notify.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.common.DBConn;
import com.c2pi.notify.entity.Menu;

/**
 * @author Shailendrak
 * 
 */
public class MenuDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	String query = "";
	String parentIDQuery = "";
	int queryResult = 0;
	DBConn con = null;
	ResultSet rs = null;

	/**
	 * @param name
	 * @param parentID
	 * @param desc
	 * @param status
	 * @param target
	 * @param createdBy
	 * @return queryResult
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	/*
	 * public String addMenu(String name, int parentID, String desc, String
	 * status, String target, String createdBy) throws SQLException,
	 * ClassNotFoundException, IOException { query =
	 * "insert into `c2pidb`.`menus`(name, parent_id, `desc`, status, target, created_by) values(?,?,?,?,?,?)"
	 * ; parentIDQuery =
	 * "insert into `c2pidb`.`menus`(name, `desc`, status, target, created_by) values(?,?,?,?,?)"
	 * ; con = new DBConn();
	 * 
	 * 
	 * conn = con.getConn(); if (parentID > 0) { pstmt =
	 * conn.prepareStatement(query); pstmt.setString(1, name); pstmt.setInt(2,
	 * parentID); pstmt.setString(3, desc); pstmt.setString(4, status);
	 * pstmt.setString(5, target); pstmt.setString(6, createdBy); } else { pstmt
	 * = conn.prepareStatement(parentIDQuery); pstmt.setString(1, name);
	 * pstmt.setString(2, desc); pstmt.setString(3, status); pstmt.setString(4,
	 * target); pstmt.setString(5, createdBy); }
	 * 
	 * System.out.println("pstmt" + pstmt); queryResult = pstmt.executeUpdate();
	 * 
	 * 
	 * if (pstmt != null) pstmt.close(); if (conn != null) conn.close();
	 * 
	 * 
	 * return (queryResult + "- Menu added."); }
	 */
	public String addMenu(Menu menu) throws SQLException,
			ClassNotFoundException, IOException {
		con = new DBConn();

		conn = con.getConn();

		if (menu.getId() == 0) {
			query = "insert into `c2pidb`.`menus`(name, parent_id, `desc`, status, target, created_by, created_dt, updated_dt, updated_by) values(?,?,?,?,?,?,?,?,?)";
			 parentIDQuery =
			"insert into `c2pidb`.`menus`(name, `desc`, status, target, created_by) values(?,?,?,?,?)";

		} else {
			query = "update `c2pidb`.`menus` set name=?,parent_id=?,`desc`=?,status=?,target=?,created_by=?,created_dt=?, updated_dt=?, updated_by=? where id=?";
		}

		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, menu.getName());
		pstmt.setInt(2, menu.getParentID());
		pstmt.setString(3, menu.getDesc());
		pstmt.setString(4, menu.getStatus());
		pstmt.setString(5, menu.getTarget());
		pstmt.setString(6, menu.getCreatedBy());
		pstmt.setString(7, menu.getCreatedDt());
		pstmt.setString(8, menu.getUpdatedDt());
		pstmt.setString(9, menu.getUpdatedBy());
		/*
		 * else { pstmt = conn.prepareStatement(parentIDQuery);
		 * pstmt.setString(1, menu.getName()); pstmt.setString(3,
		 * menu.getDesc()); pstmt.setString(4, menu.getStatus());
		 * pstmt.setString(5, menu.getTarget()); pstmt.setString(6,
		 * menu.getCreatedBy());
		 */
		if (menu.getId() != 0) {

			pstmt.setInt(10, menu.getId());

		}

		System.out.println("pstmt" + pstmt);
		queryResult = pstmt.executeUpdate();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return (queryResult + "- Menu added.");
	}

	public Menu getMenuById(int menuID) throws SQLException,
			ClassNotFoundException, IOException {
		Menu menu = new Menu();
		con = new DBConn();
		conn = con.getConn();
		query = "select id, name, ifnull(parent_id,0) parent_id,  `desc`, status, target, created_dt, created_by, updated_dt, updated_by from `c2pidb`.`menus` where id =?";

		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, menuID);
		System.out.println("pstmt:" + pstmt);
		rs = pstmt.executeQuery();
		while (rs.next()) {
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
		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return menu;
	}

	public String deleteMenu(int menuID) throws SQLException,
			ClassNotFoundException, IOException {

		query = "DELETE from `c2pidb`.`menus` where id=?";
		con = new DBConn();
		conn = con.getConn();

		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, menuID);
		System.out.println("pstmt" + pstmt);

		int result = pstmt.executeUpdate();
		System.out.println("queryResult:" + result);

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return result + "-rows deleted.";
	}

	/**
	 * @return menuList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<Menu> getMenuList() throws SQLException,
			ClassNotFoundException, IOException {
		ArrayList<Menu> menuList = new ArrayList<Menu>();

		con = new DBConn();
		conn = con.getConn();
		query = "select id, name, ifnull(parent_id,0) parent_id,  `desc`, status, target, created_dt, created_by, updated_dt, updated_by from `c2pidb`.`menus`";

		pstmt = conn.prepareStatement(query);
		System.out.println("pstmt:" + pstmt);
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

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return menuList;
	}

}