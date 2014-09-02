package com.c2pi.notify.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Role;
import com.c2pi.notify.service.RoleManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RoleAction extends ActionSupport implements ModelDriven<Role>,
		SessionAware {

	private static final long serialVersionUID = 1L;
	private ArrayList<Role> roleList = new ArrayList<Role>();
	private RoleManager rmMgr = new RoleManager();
	private Role role = new Role();

	Logger logger = Logger.getLogger(RoleAction.class.getName());

	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {

		String result = "";
		logger.info("role action execute method..");

		if (sessionMap != null)
			sessionMap.remove("result");
		logger.info("Role" + role);
		System.out.println("check valid login start..");
		logger.info("check valid login start..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return "login";
		} else {

			logger.info("calling method addRole..");
			try {
				result = rmMgr.addRole(role);
			} catch (SQLException e) {
				logger.error("ERROR:" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (ClassNotFoundException e) {
				logger.error("ERROR:" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (IOException e) {
				logger.error("ERROR:" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			}

			sessionMap.put("result", result);

			return "admin";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Role getModel() {

		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String fmtDate = dateFormat.format(date);
		String loginid = (String) sessionMap.get("loginID");
		// save
		if ((request.getParameter("id") == null)
				|| (Integer.parseInt(request.getParameter("id")) == 0)) {
			role.setCreatedDt(fmtDate);
			role.setCreatedBy(loginid);
			role.setUpdatedDt(fmtDate);
			role.setUpdatedBy(loginid);
		} else {
			// update
			role.setUpdatedDt(fmtDate);
			role.setUpdatedBy(loginid);
		}
		return role;
	}

	public void validate() {
		logger.debug("role action validate method..");

		if ((role.getName() == null) || (role.getName().length() == 0)) {
			this.addFieldError("Name", getText("app.role.Name.blank"));
			rmMgr = new RoleManager();
			try {
				roleList = rmMgr.getRoleList();
			} catch (SQLException e) {
				logger.error("ERROR:" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));

			} catch (ClassNotFoundException e) {
				logger.error("ERROR:" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
			} catch (IOException e) {
				logger.error("ERROR:" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
			}
		}
	}

	/**
	 * @return input
	 * @throws SQLException
	 */
	public String getAllRoleList() {

		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return "login";
		} else {

			try {
				roleList = rmMgr.getRoleList();
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				this.addFieldError("firstName", getText("app.error"));
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				this.addFieldError("firstName", getText("app.error"));
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				this.addFieldError("firstName", getText("app.error"));
			}
			return "input";
		}
	}

	public String edit() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return "login";
		} else {
			logger.info("checking valid login.. complete..");

			try {
				role = rmMgr.getRoleById(Integer.parseInt(request
						.getParameter("id")));
			} catch (NumberFormatException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (ClassNotFoundException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (SQLException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (IOException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			}

			return "input";
		}
	}

	public String delete() {
		String res = null;

		logger.info("checking valid login.. start..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return "login";
		} else {

			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			sessionMap.remove("result");
			try {
				res = rmMgr.deleteRole(Integer.parseInt(request
						.getParameter("id")));
			} catch (NumberFormatException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (ClassNotFoundException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (SQLException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (IOException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			}
			sessionMap.put("result", res);
			return "admin";
		}
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setRoleList(ArrayList<Role> roleList) {
		this.roleList = roleList;
	}

	public ArrayList<Role> getRoleList() {
		return roleList;
	}

}
