package com.c2pi.notify.action;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.service.EmployeeManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>,
		SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Employee> empList = null;
	private EmployeeManager empMgr = null;
	private Employee employee = new Employee();

	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		String result = "";
		System.out.println("employee action execute method..");
		// System.out.println("firstname " + firstName);
		empMgr = new EmployeeManager();

		if (sessionMap != null)
			sessionMap.remove("result");
		System.out.println("employeemanger addemployee method");
		// result = empMgr.addEmployee(firstName, lastName, email, design,
		// status,
		// (String) sessionMap.get("loginID"));
		result = empMgr.addEmployee(employee);

		sessionMap.put("result", result);

		return "admin";
	}

	@Override
	public Employee getModel() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("getModel Date:" + dateFormat.format(date));
		employee.setUpdatedDt(dateFormat.format(date));
		employee.setCreatedDt(dateFormat.format(date));
		employee.setCreatedBy((String) sessionMap.get("loginID"));
		employee.setUpdatedBy((String) sessionMap.get("loginID"));

		return employee;
	}

	public void validate() {
		System.out.println("employee action validate method..");
		if ((employee.getFirstName() == null)
				|| (employee.getFirstName().length() == 0)) {
			this.addFieldError("firstName",
					getText("app.employee.firstName.blank"));
			empMgr = new EmployeeManager();
			try {
				empList = empMgr.getEmpList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getEmployeeList() throws SQLException {
		empMgr = new EmployeeManager();
		empList = empMgr.getEmpList();
		return "input";
	}

	// public String getFirstName() {
	// return firstName;
	// }
	//
	// public void setFirstName(String firstName) {
	// this.firstName = firstName;
	// }
	//
	// public String getLastName() {
	// return lastName;
	// }
	//
	// public void setLastName(String lastName) {
	// this.lastName = lastName;
	// }
	//
	// public String getEmail() {
	// return email;
	// }
	//
	// public void setEmail(String email) {
	// this.email = email;
	// }
	//
	// public String getDesign() {
	// return design;
	// }
	//
	// public void setDesign(String design) {
	// this.design = design;
	// }
	//
	// public String getStatus() {
	// return status;
	// }
	//
	// public void setStatus(String status) {
	// this.status = status;
	// }

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
