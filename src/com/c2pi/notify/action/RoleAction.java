package com.c2pi.notify.action;

import java.io.IOException;
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

/**
 * @author Shailendrak
 * 
 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {
		System.out.println("role action validate");

		if ((role.getName() == null) || (role.getName().length() == 0)) {
			this.addFieldError("name", getText("app.role.name.blank"));
			RoleManager rm = new RoleManager();
			try {
				roleList = rm.getRoleList();
			} catch (SQLException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {

		System.out.println("role action execute");
		// System.out.println("name " + name);
		String res = "";
		sessionMap.remove("result");
		RoleManager rm = new RoleManager();
		System.out.println("role manager addrole method..");
		try {
			res = rm.AddRole(role);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sessionMap.put("result", res);
		return "admin";
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public String getRoleList() throws SQLException {

		RoleManager rm = new RoleManager();
		try {
			roleList = rm.getRoleList();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Role getModel() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String loginid = (String) sessionMap.get("loginID");
		String fmtDate = dateFormat.format(date);
		role.setCreatedBy(loginid);
		role.setCreatedDt(fmtDate);
		role.setUpdatedBy(loginid);
		role.setUpdatedDt(fmtDate);

		return role;
	}

}
