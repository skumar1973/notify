package com.c2pi.notify.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.c2pi.notify.common.DBConn;
import com.c2pi.notify.entity.Menu;
import com.c2pi.notify.entity.Role;
import com.c2pi.notify.entity.RoleMenu;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class RoleMenuDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt1 = null;
	private DBConn con = null;
	private String query = "";
	private int queryResult = 0;
	private ResultSet rs = null;

	Logger logger = Logger.getLogger(RoleMenuDAO.class.getName());
	private RoleMenu roleMenu = null;

	/**
	 * @return List of Employee role
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<RoleMenu> getRoleMenu() throws SQLException,
			ClassNotFoundException, IOException {
		ArrayList<RoleMenu> roleMenuList = null;
		roleMenuList = new ArrayList<RoleMenu>();
		query = "SELECT rm.`id`, rm.`role_id`, rm.`menu_id`, rm.`created_dt`, rm.`created_by`, rm.`updated_dt`, rm.`updated_by`, r.`name` as rname, m.`name` as mname FROM `c2pidb`.`roles_menus` rm, `c2pidb`.`roles` r, `c2pidb`.`menus` m where rm.role_id = r.id and rm.menu_id = m.id ";
		con = new DBConn();

		conn = con.getConn();
		pstmt = conn.prepareStatement(query);
		System.out.println("pstmt: " + pstmt);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			roleMenu = new RoleMenu();
			roleMenu.setId(rs.getInt("id"));
			roleMenu.setRoleID(rs.getInt("role_id"));
			roleMenu.setMenuID(rs.getInt("menu_id"));
			roleMenu.setCreatedDt(rs.getString("created_dt"));
			roleMenu.setCreatedBy(rs.getString("created_by"));
			roleMenu.setUpdatedDt(rs.getString("updated_dt"));
			roleMenu.setUpdatedBy(rs.getString("updated_by"));
			roleMenu.setRoleName(rs.getString("rname"));
			roleMenu.setMenuName(rs.getString("mname"));
			roleMenuList.add(roleMenu);
		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return roleMenuList;
	}

	public String saveNUpdRoleMenu(RoleMenu rolemenu) throws SQLException,
			ClassNotFoundException, IOException,
			MySQLIntegrityConstraintViolationException {
		con = new DBConn();
		conn = con.getConn();

		if ((rolemenu.getId() == 0)) {
			query = "Insert Into `c2pidb`.`roles_menus` (`role_id`,`menu_id`,`created_dt`,`created_by`,`updated_dt`,`updated_by`) values (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rolemenu.getRoleID());
			pstmt.setInt(2, rolemenu.getMenuID());

			pstmt.setString(3, rolemenu.getCreatedDt());
			pstmt.setString(4, rolemenu.getCreatedBy());
			pstmt.setString(5, rolemenu.getUpdatedDt());
			pstmt.setString(6, rolemenu.getUpdatedBy());
		} else {
			query = "Update `c2pidb`.`roles_menus` set `role_id`=?,`menu_id`=?,`created_dt`=?,`created_by`=?,`updated_dt`=?,`updated_by`=? where `id`=? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rolemenu.getRoleID());
			pstmt.setInt(2, rolemenu.getMenuID());

			pstmt.setString(3, rolemenu.getCreatedDt());
			pstmt.setString(4, rolemenu.getCreatedBy());
			pstmt.setString(5, rolemenu.getUpdatedDt());
			pstmt.setString(6, rolemenu.getUpdatedBy());
			pstmt.setInt(7, rolemenu.getId());
		}
		logger.info("query:" + query);
		System.out.println("query:" + query);
		queryResult = pstmt.executeUpdate();
		System.out.println("res=" + queryResult);

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return (queryResult + "- Role Menu saved/updated.");
	}

	public RoleMenu getRoleMenuById(int rmID) throws SQLException,
			ClassNotFoundException, IOException {
		RoleMenu rm = new RoleMenu();
		query = "SELECT `id`, `role_id`, `menu_id`, `created_dt`, `created_by`, `updated_dt`, `updated_by` FROM `c2pidb`.`roles_menus` where id=?";

		con = new DBConn();
		conn = con.getConn();

		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, rmID);
		System.out.println("pstmt" + pstmt);
		rs = pstmt.executeQuery();
		System.out.println("rs" + rs);
		while (rs.next()) {
			rm.setId(rs.getInt("id"));
			rm.setRoleID(rs.getInt("role_id"));
			rm.setMenuID(rs.getInt("menu_id"));
			rm.setCreatedDt(rs.getString("created_dt"));
			rm.setCreatedBy(rs.getString("created_by"));
			rm.setUpdatedDt(rs.getString("updated_dt"));
			rm.setUpdatedBy(rs.getString("updated_by"));
		}

		if (pstmt1 != null)
			pstmt1.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return rm;
	}

	public String deleteRM(int rmID) throws SQLException,
			ClassNotFoundException, IOException {

		query = "DELETE from `c2pidb`.`roles_menus` where id=?";
		con = new DBConn();
		conn = con.getConn();

		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, rmID);
		System.out.println("pstmt" + pstmt);

		queryResult = pstmt.executeUpdate();
		System.out.println("queryResult:" + queryResult);

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return queryResult + "-rows deleted.";
	}

	public ArrayList<Role> getRoleId() throws SQLException,
			ClassNotFoundException, IOException {
		ArrayList<Role> roleList = null;
		query = "SELECT `id`, `name`, `desc`,`status`, `created_dt`, `created_by`, `updated_dt`, `updated_by` FROM `c2pidb`.`roles`";
		roleList = new ArrayList<Role>();
		con = new DBConn();

		conn = con.getConn();
		System.out.println("query" + query);
		pstmt = conn.prepareStatement(query);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Role role = new Role();
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

	public ArrayList<Menu> getMenuId() throws SQLException,
			ClassNotFoundException, IOException {
		ArrayList<Menu> menuList = null;
		menuList = new ArrayList<Menu>();

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
