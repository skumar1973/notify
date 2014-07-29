package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.MenuDAO;
import com.c2pi.notify.entity.Menu;

/**
 * @author Shailendrak
 *
 */
public class MenuManager {
	
	MenuDAO mdao = null;
	
	/**
	 * @param name
	 * @param parentID
	 * @param desc
	 * @param status
	 * @param target
	 * @param createdBy
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public String addMenu(String name, int parentID, String desc,
			String status, String target, String createdBy)
			throws SQLException, ClassNotFoundException, IOException {
		mdao = new MenuDAO();
		String res = "";
		res = mdao.addMenu(name, parentID, desc, status, target, createdBy);
		return res;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public ArrayList<Menu> getMenuList() throws SQLException, ClassNotFoundException, IOException {
		ArrayList<Menu> menuList = null;
		mdao = new MenuDAO();
		menuList = mdao.getMenuList();

		return menuList;
	}
}
