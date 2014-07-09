package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.MenuDAO;
import com.c2pi.notify.entity.Menu;

public class MenuManager {
	
	MenuDAO mdao = null;
	
	public String addMenu(String name, int parentID, String desc,
			String status, String target, String createdBy)
			throws SQLException {
		mdao = new MenuDAO();
		String res = "";
		res = mdao.addMenu(name, parentID, desc, status, target, createdBy);
		return res;
	}

	public ArrayList<Menu> getMenuList() throws SQLException {
		ArrayList<Menu> menuList = null;
		mdao = new MenuDAO();
		menuList = mdao.getMenuList();

		return menuList;
	}
}
