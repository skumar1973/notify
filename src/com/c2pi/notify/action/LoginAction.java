package com.c2pi.notify.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.service.LoginManager;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {

	/**
	 * @author Shailendrak
	 */
	private static final long serialVersionUID = 1L;
	private String loginID;
	private String password;
	
	Logger logger = Logger.getLogger(LoginAction.class.getName());
	
	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}


	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		
		logger.debug("loginaction execute method...");
		String role = "";
		int empID=0;
		sessionMap.remove("loginID");
		sessionMap.remove("role");
		sessionMap.remove("empID");
		sessionMap.remove("result");
		LoginManager um = new LoginManager();
		try {
			empID=um.IsLoginCorrect(loginID,password);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			logger.error("ERROR:"+e.getMessage());
			e.printStackTrace();
			this.addActionError(getText("app.error"));
			return ERROR;
		} catch (ClassNotFoundException e) {
			logger.error("ERROR:"+e.getMessage());
			e.printStackTrace();
			this.addActionError(getText("app.error"));
			return ERROR;
		} catch (IOException e) {
			logger.error("ERROR:"+e.getMessage());
			e.printStackTrace();
			this.addActionError(getText("app.error"));
			return ERROR;
		}
		logger.info("empId:"+empID);
		if (empID >0) {

			try {
				role = um.getUserRole(loginID);
			} catch (SQLException e) {
				logger.error("ERROR:"+e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (ClassNotFoundException e) {
				logger.error("ERROR:"+e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (IOException e) {
				logger.error("ERROR:"+e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			}
			if ((role != null) && (role.length() != 0)) {
				sessionMap.put("loginID", loginID);
				sessionMap.put("empID", empID);
				sessionMap.put("role", role);
				sessionMap.put("result", "Your Employee Id is " + empID);
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