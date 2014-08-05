package com.c2pi.notify.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.service.EmployeeManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Shailendrak
 * 
 */
public class EmployeeAction extends ActionSupport implements
		ModelDriven<Employee>, SessionAware {

	private static final long serialVersionUID = 1L;
	private ArrayList<Employee> empList = null;
	private EmployeeManager empMgr = null;
	private Employee employee = new Employee();

	Logger logger = Logger.getLogger(EmployeeAction.class.getName());

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
		logger.info("employee action execute method..");

		empMgr = new EmployeeManager();

		if (sessionMap != null)
			sessionMap.remove("result");
		logger.info("Employee" + employee);
		System.out.println("check valid login start..");
		logger.info("check valid login start..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: check valid login..  failed..");
			this.addActionError(getText("app.error"));
			return ERROR;
		}

		logger.info("calling method addEmployee..");
		try {
			result = empMgr.addEmployee(employee);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Employee getModel() {

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String fmtDate = dateFormat.format(date);
		String loginid = (String) sessionMap.get("loginID");
		employee.setUpdatedDt(fmtDate);
		employee.setCreatedDt(fmtDate);
		employee.setCreatedBy(loginid);
		employee.setUpdatedBy(loginid);

		return employee;
	}

	public void validate() {
		logger.debug("employee action validate method..");

		if ((employee.getFirstName() == null)
				|| (employee.getFirstName().length() == 0)) {
			this.addFieldError("firstName",
					getText("app.employee.firstName.blank"));
			empMgr = new EmployeeManager();
			try {
				empList = empMgr.getEmpList();
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
	public String getEmployeeList() {
		empMgr = new EmployeeManager();
		try {
			empList = empMgr.getEmpList();
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

	public ArrayList<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(ArrayList<Employee> empList) {
		this.empList = empList;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
