package com.c2pi.notify.actions;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.c2pi.notify.entity.Menus;
import com.c2pi.notify.service.MenuManager;
import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private int parent_id;
	private String desc;
	private String status;
	private String target;

	ArrayList<Menus> menulist = new ArrayList<Menus>();

	Map sessionMap;

	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		System.out.println("menu action execute method..");
		System.out.println("name " + name);
		MenuManager mm = new MenuManager();
		String res = "";
		sessionMap.remove("result");
		System.out.println("MenuManager addMenu method..");
		res = mm.addMenu(name, parent_id, desc, status, target,
				(String) sessionMap.get("loginid"));
		sessionMap.put("result", res);

		return "admin";
	}

	public void validate() {
		System.out.println("menu action validate method..");
		// if ((firstname == null) || (firstname.length() == 0)) {
		// this.addFieldError("firstname", getText("First Name is Required"));
		// }
		// if ((lastname == null) || (lastname.length() == 0)) {
		// this.addFieldError("lastname",
		// getText("app.employee.lastname.blank"));
		// }

	}

	public String getmenulist() {
		MenuManager em = new MenuManager();
		menulist = em.getmenulist();

		return "input";
	}

	public ArrayList<Menus> getMenulist() {
		return menulist;
	}

	public void setMenulist(ArrayList<Menus> menulist) {
		this.menulist = menulist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
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

}
