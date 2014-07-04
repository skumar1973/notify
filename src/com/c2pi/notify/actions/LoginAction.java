package com.c2pi.notify.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.service.LoginManager;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String loginid;
	String password;
	String type;
	Map sessionMap;

	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String execute() throws Exception {
		// id and password validation logic
		// you can validate from database
		System.out.println("loginaction execute method...");
		String role = "";
		int emp_id=0;
		sessionMap.remove("loginid");
		sessionMap.remove("role");
		sessionMap.remove("emp_id");
		sessionMap.remove("result");
		LoginManager um = new LoginManager();
		emp_id=um.IsLoginCorrect(loginid,password);
		if (emp_id >0) {
			// UserDAO user = new UserDAO();
			role = um.getUserRole(loginid);
			if ((role != null) && (role.length() != 0)) {
				sessionMap.put("loginid",loginid);
				sessionMap.put("emp_id", emp_id);
				sessionMap.put("role", role);
				sessionMap.put("result", "EmpId="+emp_id+",Role="+role);
				return role;
			}
			else {
				this.addActionError(getText("app.invalid"));
				return ERROR;
			}
		} else {
			this.addActionError(getText("app.invalid"));
			return ERROR;
		}
	}

	public void validate() {
		if ((loginid == null) || (loginid.length() == 0)) {
			this.addFieldError("loginid", getText("app.loginid.blank"));
		}
		if ((password == null) || (password.length() == 0)) {
			this.addFieldError("password", getText("app.password.blank"));
		}
	}
}