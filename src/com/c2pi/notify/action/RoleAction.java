package com.c2pi.notify.action;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Role;
import com.c2pi.notify.service.RoleManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class RoleAction extends ActionSupport implements SessionAware,
		ModelDriven<Role>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	ArrayList<Role> roleList = new ArrayList<Role>();
	Role role = new Role();
	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public void validate() {
		System.out.println("role action validate");

		if ((role.getName() == null) || (role.getName().length() == 0)) {
			this.addFieldError("name", getText("app.role.name.blank"));
			RoleManager rm = new RoleManager();
			try {
				roleList = rm.getRoleList();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

	}

	public String execute() throws Exception {

		System.out.println("role action execute");
		// System.out.println("name " + name);
		String res = "";
		sessionMap.remove("result");
		RoleManager rm = new RoleManager();
		System.out.println("role manager addrole method..");
		res = rm.AddRole(role);
		sessionMap.put("result", res);
		return "admin";
	}

	public String getRoleList() throws SQLException {

		RoleManager rm = new RoleManager();
		roleList = rm.getRoleList();

		return "input";
	}

	public void prepare() {
		System.out.println("role action prepare method..");
	}

	public ArrayList<Role> getRolelist() {
		return roleList;
	}

	public void setRolelist(ArrayList<Role> roleList) {
		this.roleList = roleList;
	}

	@Override
	public Role getModel() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		role.setCreatedBy((String) sessionMap.get("loginID"));
		role.setCreatedDt(dateFormat.format(date));
		role.setUpdatedBy((String) sessionMap.get("loginID"));
		role.setUpdatedDt(dateFormat.format(date));

		return role;
	}

}
