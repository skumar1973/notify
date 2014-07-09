package com.c2pi.notify.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.service.LoginManager;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String loginID;
	String password;
//	String type;
	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}


	public String execute() throws Exception {
		// id and password validation logic
		// you can validate from database
		System.out.println("loginaction execute method...");
		String role = "";
		int empID=0;
		sessionMap.remove("loginID");
		sessionMap.remove("role");
		sessionMap.remove("empID");
		sessionMap.remove("result");
		LoginManager um = new LoginManager();
		empID=um.IsLoginCorrect(loginID,password);
		if (empID >0) {

			role = um.getUserRole(loginID);
			if ((role != null) && (role.length() != 0)) {
				sessionMap.put("loginID",loginID);
				sessionMap.put("empID", empID);
				sessionMap.put("role", role);
				sessionMap.put("result", "Your Employee Id is "+empID);
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
		if ((loginID == null) || (loginID.length() == 0)) {
			this.addFieldError("loginid", getText("app.loginid.blank"));
		}
		if ((password == null) || (password.length() == 0)) {
			this.addFieldError("password", getText("app.password.blank"));
		}
	}


	public String getLoginID() {
		return loginID;
	}


	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}