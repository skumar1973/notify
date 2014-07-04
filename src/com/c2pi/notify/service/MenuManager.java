package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.MenuDAO;
import com.c2pi.notify.entity.Menus;

public class MenuManager {

	public String addMenu(String name, int parent_id, String desc,
			String status, String target, String created_by)
			throws SQLException {
		MenuDAO mdao = new MenuDAO();
		String res = "";
		res = mdao.addMenu(name, parent_id, desc, status, target, created_by);
		return res;
	}

	public ArrayList<Menus> getmenulist() {
		ArrayList<Menus> menulist = null;
		MenuDAO mdao = new MenuDAO();
		menulist = mdao.getmenulist();

		return menulist;
	}
}
