package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.MenuDAO;
import com.c2pi.notify.dao.RoleDAO;
import com.c2pi.notify.dao.RoleMenuDAO;
import com.c2pi.notify.entity.Menu;
import com.c2pi.notify.entity.Role;
import com.c2pi.notify.entity.RoleMenu;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class RoleMenuManager {

	private ArrayList<RoleMenu> roleMenuList = null;
	private ArrayList<Role> roleList = null;
	private ArrayList<Menu> menuList = null;
	private RoleMenuDAO rmDAO = new RoleMenuDAO();
	private RoleMenu rm = null;
	private String rmdaores = "";

	public void getList() throws SQLException, ClassNotFoundException,
			IOException {

		rmDAO = new RoleMenuDAO();
		roleMenuList = rmDAO.getRoleMenu();

		RoleDAO rDAO = new RoleDAO();
		roleList = rDAO.getRoleList();

		MenuDAO mDAO = new MenuDAO();
		menuList = mDAO.getMenuList();

	}

	public RoleMenu getRoleMenuById(int rmID) throws SQLException,
			ClassNotFoundException, IOException {
		System.out.println("Role Menu list method..");
		rm = rmDAO.getRoleMenuById(rmID);
		return rm;
	}

	public String savenupdRoleMenu(RoleMenu rolemenu) throws SQLException,
			ClassNotFoundException, IOException,
			MySQLIntegrityConstraintViolationException {
		String RoleMenuResult = "";
		RoleMenuDAO rmDAO = new RoleMenuDAO();
		RoleMenuResult = rmDAO.saveNUpdRoleMenu(rolemenu);
		return RoleMenuResult;
	}

	public String deleteRM(int rmID) throws SQLException,
			ClassNotFoundException, IOException {
		System.out.println("RoleMenuManager deleteRM method..");
		rmdaores = rmDAO.deleteRM(rmID);
		System.out.println("tfdaores" + rmdaores);

		return rmdaores;

	}

	public ArrayList<Role> getRoleId() throws SQLException,
			ClassNotFoundException, IOException {
		rmDAO = new RoleMenuDAO();
		roleList = rmDAO.getRoleId();
		return roleList;
	}

	public ArrayList<Menu> getMenuId() throws SQLException,
			ClassNotFoundException, IOException {
		rmDAO = new RoleMenuDAO();
		menuList = rmDAO.getMenuId();
		return menuList;
	}

	public ArrayList<RoleMenu> getRoleMenuList() {
		return roleMenuList;
	}

	public ArrayList<Role> getRoleList() {
		return roleList;
	}

	public ArrayList<Menu> getMenuList() {
		return menuList;
	}

}
