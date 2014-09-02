package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.RoleDAO;
import com.c2pi.notify.entity.Role;

/**
 * @author Shailendrak
 * 
 */
public class RoleManager {

	private Role role = new Role();
	private ArrayList<Role> roleList = null;
	RoleDAO rdao = new RoleDAO();
	private String roledaores = "";

	/**
	 * @param role
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public String addRole(Role role) throws SQLException,
			ClassNotFoundException, IOException {
		// String roledaores = "";
		rdao = new RoleDAO();
		roledaores = rdao.addRole(role);

		return roledaores;
	}

	public Role getRoleById(int roleID) throws SQLException,
			ClassNotFoundException, IOException {
		System.out.println("Role get list method..");
		rdao = new RoleDAO();
		role = rdao.getRoleById(roleID);
		System.out.println("RoleList" + roleList);
		return role;

	}

	public String deleteRole(int roleID) throws SQLException,
			ClassNotFoundException, IOException {
		System.out.println("RoleMenuManager deleteRM method..");
		roledaores = rdao.deleteRole(roleID);
		System.out.println("roledaores" + roledaores);

		return roledaores;

	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<Role> getRoleList() throws SQLException,
			ClassNotFoundException, IOException {
		ArrayList<Role> roleList = null;
		rdao = new RoleDAO();
		roleList = rdao.getRoleList();
		return roleList;
	}

}
