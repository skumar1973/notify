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

	RoleDAO rdao = null;

	/**
	 * @param role
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String AddRole(Role role) throws SQLException,
			ClassNotFoundException, IOException {
		String roledaores = "";
		rdao = new RoleDAO();
		roledaores = rdao.addRole(role);

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
