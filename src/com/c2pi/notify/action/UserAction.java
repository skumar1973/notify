package com.c2pi.notify.action;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.User;
import com.c2pi.notify.service.UserManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class UserAction extends ActionSupport implements SessionAware,
		Preparable, ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	ArrayList<User> userList = null;
	ArrayList<Employee> empList = null;
	User user = new User();
	
	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		String res="";
		if (sessionMap != null) sessionMap.remove("result");
		System.out.println("UserAction execute method..");
		UserManager um = new UserManager();
		res=um.AddUser(user);
		sessionMap.put("result", res);
		return "admin";
	}

	public void validate() {
		System.out.println("UserAction validate..");
		if ((user.getLoginID() == null) || (user.getLoginID().length()==0)){
			addFieldError("loginID",getText("app.user.loginID.blank"));
			UserManager um = new UserManager();	
			System.out.println("TaskManager getEmpList method ..");
			try {
				empList = um.getEmpList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("UserAction getuserlist method ...");
			try {
				userList = um.getUserList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}


	public String getUserNEmpList() throws SQLException {
		UserManager um = new UserManager();	
		System.out.println("TaskManager getEmpList method ..");
		empList = um.getEmpList();
		System.out.println("UserAction getuserlist method ...");
		userList = um.getUserList();

		return "input";

	}

	public void prepare() {
		System.out.println("UserAction prepare method...");
		
	}


	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setEmpList(ArrayList<Employee> empList) {
		this.empList = empList;
	}

	public ArrayList<Employee> getEmpList() {
		return empList;
	}

	

	@Override
	public User getModel() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("getModel Date:" + dateFormat.format(date));
		user.setUpdatedDt(dateFormat.format(date));
		user.setCreatedDt(dateFormat.format(date));
		user.setCreatedBy((String) sessionMap.get("loginID"));
		user.setUpdatedBy((String) sessionMap.get("loginID"));
		
		return user;
	}

	
}
