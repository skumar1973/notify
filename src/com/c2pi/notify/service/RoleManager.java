package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.RoleDAO;
import com.c2pi.notify.entity.Role;

public class RoleManager {

	RoleDAO rdao = null;
	
	public String AddRole(Role role) throws SQLException {
		String roledaores = "";
		rdao = new RoleDAO();
			roledaores = rdao.addRole(role);
	
		return roledaores;
	}

	public ArrayList<Role> getRoleList() throws SQLException {
		ArrayList<Role> roleList = null;
		rdao = new RoleDAO();
		roleList = rdao.getRoleList();
		return roleList;
	}

}
