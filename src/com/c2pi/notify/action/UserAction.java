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

import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.User;
import com.c2pi.notify.service.UserManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author Shailendrak
 * 
 */
public class UserAction extends ActionSupport implements SessionAware,
		Preparable, ModelDriven<User> {

	private static final long serialVersionUID = 1L;

	private User user = new User();

	private ArrayList<User> userList = new ArrayList<User>();
	private ArrayList<Employee> empList = new ArrayList<Employee>();
	private UserManager um = null;

	Map<String, Object> sessionMap;
	Logger logger = Logger.getLogger(UserAction.class.getName());

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

		logger.debug("check valid login start..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.debug("ERROR: check valid login..  failed..");
			this.addActionError(getText("app.invalid"));
			return "login";
		} else {
			String res = "";
			if (sessionMap != null)
				sessionMap.remove("result");

			UserManager um = new UserManager();
			logger.info("UserAction execute method..");
			try {
				res = um.AddUser(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				addActionError(e.getMessage());
				return "input";
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				addActionError(e.getMessage());
				return "input";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				addActionError(e.getMessage());
				// addFieldError("loginID", getText("app.user.loginID.blank"))
				return "input";
			}
			sessionMap.put("result", res);

			return "admin";
		}
	}

	public void validate() {
		logger.info("UserAction validate..");
		if ((user.getLoginID() == null) || (user.getLoginID().length() == 0)) {
			addFieldError("loginID", getText("app.user.loginID.blank"));
	/*		UserManager um = new UserManager();
			logger.info("TaskManager getEmpList method ..");
			try {
				empList = um.getEmpList();
			} catch (SQLException e) {
				logger.error("Error - " + e.getErrorCode());
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("UserAction getuserlist method ...");
			try {
				userList = um.getUserList();
			} catch (SQLException e) {
				logger.error("Error - " + e.getErrorCode());
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

		}
	}

	public String getUserNEmpList() {
		System.out.println("check valid login start..");

		logger.debug("check valid login start..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.debug("ERROR: check valid login..  failed..");
			this.addActionError(getText("app.invalid"));
			return "login";
		} else {
			UserManager um = new UserManager();
			logger.info("TaskManager getEmpList method ..");
			try {
				empList = um.getEmpList();
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
			logger.info("UserAction getuserlist method ...");
			try {
				userList = um.getUserList();
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

			return "input";
		}
	}

	/** User Edit and delete function define **/
	public String userEdit() {
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.error"));
			return "login";
		} else {
		logger.debug("Edit Project execute method...");
		String res = "";
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		logger.info("checking valid login.. complete..");
		try {
			um = new UserManager();
			empList = um.getEmpList();
			user = um.editUser(Integer.parseInt(request.getParameter("id")));
			sessionMap.put("result", res);
		} catch (Exception e) {
			logger.info("editUser1=  " + e.getMessage());
		}
		return "input";
		}
	}

	public String deleteUser() throws Exception {
		logger.info("checking valid login.. start..");

		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.error"));
			return "login";
		} else {
			logger.debug("Delete User execute method...");
			String res = "";
			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			sessionMap.remove("result");
			um = new UserManager();
			res = um.deleteUser(Integer.parseInt(request.getParameter("id")));
			sessionMap.put("result", res);
			return "admin";
		}

	}

	public void prepare() {
		logger.info("UserAction prepare method...");

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

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fmtDate = dateFormat.format(date);
		String loginid = (String) sessionMap.get("loginID");
		user.setUpdatedDt(fmtDate);
		user.setCreatedDt(fmtDate);
		user.setCreatedBy(loginid);
		user.setUpdatedBy(loginid);

		return user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
