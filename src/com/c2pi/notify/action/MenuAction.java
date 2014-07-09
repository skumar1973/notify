package com.c2pi.notify.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.c2pi.notify.entity.Menu;
import com.c2pi.notify.service.MenuManager;
import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private int parentID;
	private String desc;
	private String status;
	private String target;

	ArrayList<Menu> mnList = new ArrayList<Menu>();
	
	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		System.out.println("menu action execute method..");
		System.out.println("name " + name);
		MenuManager mm = new MenuManager();
		String res = "";
		sessionMap.remove("result");
		System.out.println("MenuManager addMenu method..");
		res = mm.addMenu(name, parentID, desc, status, target,
				(String) sessionMap.get("loginID"));
		sessionMap.put("result", res);

		return "admin";
	}

	public void validate() {
		System.out.println("menu action validate method..");
		if ((name == null) || name.length()==0){
			this.addFieldError("name", getText("app.menu.name.blank"));
			MenuManager em = new MenuManager();
			try {
				mnList = em.getMenuList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public String getMenuList() throws SQLException {
		MenuManager em = new MenuManager();
		mnList = em.getMenuList();

		return "input";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParent_id(int parentID) {
		this.parentID = parentID;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public ArrayList<Menu> getMnList() {
		return mnList;
	}

	public void setMnList(ArrayList<Menu> mnList) {
		this.mnList = mnList;
	}

}
