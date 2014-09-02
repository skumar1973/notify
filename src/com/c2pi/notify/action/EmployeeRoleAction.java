/**
 * 
 */
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
import com.c2pi.notify.entity.EmployeeRole;
import com.c2pi.notify.entity.Role;
import com.c2pi.notify.service.EmployeeRoleManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author NeerajK
 * 
 */
public class EmployeeRoleAction extends ActionSupport implements SessionAware,
		ModelDriven<EmployeeRole> {

	private static final long serialVersionUID = 1L;
	EmployeeRoleManager empRoleMgr = new EmployeeRoleManager();
	ArrayList<Employee> empIDList = new ArrayList<Employee>();
	ArrayList<Role> roleIDList = new ArrayList<Role>();
	ArrayList<EmployeeRole> empRoleList = new ArrayList<EmployeeRole>();
	EmployeeRole empRole = new EmployeeRole();

	Logger logger = Logger.getLogger(EmployeeRoleAction.class.getName());

	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {

		String result = "";

		logger.info("employee role action execute method..");

		if (sessionMap != null)
			sessionMap.remove("result");
		logger.info("Employee Role" + empRole);
		System.out.println("check valid login start..");
		logger.info("check valid login start..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return "login";
		} else {

			logger.info("calling method EmployeeRole..");
			try {
				result = empRoleMgr.addEmployeeRole(empRole);
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

		}
		sessionMap.put("result", result);

		return "admin";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public EmployeeRole getModel() {

		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String fmtDate = dateFormat.format(date);
		String loginid = (String) sessionMap.get("loginID");
		// save
		if ((request.getParameter("id") == null)
				|| (Integer.parseInt(request.getParameter("id")) == 0)) {
			empRole.setUpdatedDt(fmtDate);
			empRole.setCreatedDt(fmtDate);
			empRole.setCreatedBy(loginid);
			empRole.setUpdatedBy(loginid);
		} else {

			// update
			empRole.setUpdatedDt(fmtDate);
			empRole.setUpdatedBy(loginid);
		}

		return empRole;
	}

	public void validate() {
		logger.debug("employee action validate method..");

		if ((empRole.getEmpID() == 0)) {
			this.addFieldError("empID", getText("app.employeeRole.empID.blank"));
			empRoleMgr = new EmployeeRoleManager();
			try {
				empIDList = empRoleMgr.getEmpId();
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

		try {
			roleIDList = empRoleMgr.getRoleId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("ERROR:" + e.getMessage());
			e.printStackTrace();
			this.addActionError(getText("app.error"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("ERROR:" + e.getMessage());
			e.printStackTrace();
			this.addActionError(getText("app.error"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("ERROR:" + e.getMessage());
			e.printStackTrace();
			this.addActionError(getText("app.error"));
		}
		System.out.println("TaskManager get list of Emp role method ...");
		try {
			empRoleList = empRoleMgr.getEmpRole();
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

	}

	/**
	 * Reterive the list of emp IDs and role IDs.
	 * 
	 * @return
	 */
	public String getEmpNRole() throws Exception {
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return "login";
		} else {
			EmployeeRoleManager empRoleMgr = null;
			empRoleMgr = new EmployeeRoleManager();
			System.out
					.println("EmployeeRoleManager get list of employee IDs method ..");
			empIDList = empRoleMgr.getEmpId();
			System.out.println("TaskManager get list of role IDs method ...");
			roleIDList = empRoleMgr.getRoleId();
			System.out.println("TaskManager get list of Emp role method ...");
			empRoleList = empRoleMgr.getEmpRole();

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

				empRole = empRoleMgr.getEmployeeById(Integer.parseInt(request
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
			try {
				
				empRoleMgr = new EmployeeRoleManager();
				System.out
						.println("EmployeeRoleManager get list of employee IDs method ..");
				empIDList = empRoleMgr.getEmpId();
				System.out.println("TaskManager get list of role IDs method ...");
				roleIDList = empRoleMgr.getRoleId();
				System.out.println("TaskManager get list of Emp role method ...");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// empRoleedit.add(empRole);

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
			return ERROR;
		} else {

			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			sessionMap.remove("result");
			try {
				res = empRoleMgr.deleteEmpRole(Integer.parseInt(request
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
				this.addActionError(getText(e.getMessage()));
				return ERROR;
			} catch (IOException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText(e.getMessage()));
				return ERROR;
			}
			sessionMap.put("result", res);
			return "admin";
		}
	}

	/**
	 * @return the empIDList
	 */
	public ArrayList<Employee> getEmpIDList() {
		return empIDList;
	}

	/**
	 * @param empIDList
	 *            the empIDList to set
	 */
	public void setEmpIDList(ArrayList<Employee> empIDList) {
		this.empIDList = empIDList;
	}

	/**
	 * @return the roleIDList
	 */
	public ArrayList<Role> getRoleIDList() {
		return roleIDList;
	}

	/**
	 * @param roleIDList
	 *            the roleIDList to set
	 */
	public void setRoleIDList(ArrayList<Role> roleIDList) {
		this.roleIDList = roleIDList;
	}

	public ArrayList<EmployeeRole> getEmpRoleList() {
		return empRoleList;
	}

	public void setEmpRoleList(ArrayList<EmployeeRole> empRoleList) {
		this.empRoleList = empRoleList;
	}

	public EmployeeRole getEmpRole() {
		return empRole;
	}

	public void setEmpRole(EmployeeRole empRole) {
		this.empRole = empRole;
	}

}
