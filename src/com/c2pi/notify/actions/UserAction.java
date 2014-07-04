package com.c2pi.notify.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Employees;
import com.c2pi.notify.entity.User;
import com.c2pi.notify.service.UserManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class UserAction extends ActionSupport implements SessionAware,
		Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login_id;
	private String password;
	private int emp_id;
	private String created_dt;
	private String created_by;
	private String updated_dt;
	private String updated_by;

	ArrayList<User> userlist = new ArrayList<User>();
	ArrayList<Employees> emplist = new ArrayList<Employees>();
	
	Map sessionMap;

	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		String res="";
		sessionMap.remove("result");
		System.out.println("UserAction execute method..");
		UserManager um = new UserManager();
		res=um.AddUser(login_id, password, emp_id, (String) sessionMap.get("loginid"));
		sessionMap.put("result", res);
		return "admin";
	}

	public void validate() {
		System.out.println("UserAction validate..");
//		 if ((name == null) || (name.length() == 0)) {
//		 this.addFieldError("name", getText("app.name.blank"));
//		 }
//		 if ((freq_id == 0) ) {
//		 this.addFieldError("freq_id", getText("app.freq_id.blank"));
//		 }
	}


	public String getuserlist() throws SQLException {
		UserManager um = new UserManager();	
		System.out.println("TaskManager getEmpList method ..");
		emplist = um.getEmpList();
		System.out.println("UserAction getuserlist method ...");
		userlist = um.getuserlist();

		return "input";

	}

	public void prepare() {
		System.out.println("UserAction prepare method...");
		
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getCreated_dt() {
		return created_dt;
	}

	public void setCreated_dt(String created_dt) {
		this.created_dt = created_dt;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getUpdated_dt() {
		return updated_dt;
	}

	public void setUpdated_dt(String updated_dt) {
		this.updated_dt = updated_dt;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public void setUserlist(ArrayList<User> userlist) {
		this.userlist = userlist;
	}

	public ArrayList<User> getUserlist() {
		return userlist;
	}

	public ArrayList<Employees> getEmplist() {
		return emplist;
	}

	public void setEmplist(ArrayList<Employees> emplist) {
		this.emplist = emplist;
	}

	
}
