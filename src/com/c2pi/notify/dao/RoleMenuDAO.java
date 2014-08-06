package com.c2pi.notify.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.c2pi.notify.common.DBConn;
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
		query = "SELECT `id`, `role_id`, `menu_id`, `created_dt`, `created_by`, `updated_dt`, `updated_by` FROM `c2pidb`.`roles_menus`";
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
			roleMenuList.add(roleMenu);
		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return roleMenuList;
	}

	public String saveNUpdRoleMenu(RoleMenu rolemenu) throws SQLException,
			ClassNotFoundException, IOException, MySQLIntegrityConstraintViolationException {
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
	
	public RoleMenu getRoleMenuById(int rmID)
			throws SQLException, ClassNotFoundException, IOException {
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
		
		if(pstmt1!=null)
			pstmt1.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return rm;
	}
	
	public String deleteRM(int rmID)
			throws SQLException, ClassNotFoundException, IOException {
		
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

		return queryResult+"-rows deleted.";
	}

}
