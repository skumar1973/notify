package com.c2pi.notify.actions;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.c2pi.notify.entity.Employees;
import com.c2pi.notify.service.EmployeesManager;
import com.opensymphony.xwork2.ActionSupport;

public class EmployeeAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String firstname;
	private String lastname;
	private String email;
	private String design;
	private String status;

	ArrayList<Employees> emplist = new ArrayList<Employees>();

	Map sessionMap;

	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		System.out.println("employee action execute method..");
		System.out.println("firstname " + firstname);
		EmployeesManager em = new EmployeesManager();
		String res = "";
		sessionMap.remove("result");
		System.out.println("employeemanger addemployee method");
		res = em.addemployee(firstname, lastname, email, design, status,
				(String) sessionMap.get("loginid"));
		sessionMap.put("result", res);
		return "admin";
	}

	public void validate() {
		System.out.println("employee action validate method..");
		// if ((firstname == null) || (firstname.length() == 0)) {
		// this.addFieldError("firstname", getText("First Name is Required"));
		// }
		// if ((lastname == null) || (lastname.length() == 0)) {
		// this.addFieldError("lastname",
		// getText("app.employee.lastname.blank"));
		// }

	}

	public String getemplist() {
		EmployeesManager em = new EmployeesManager();
		emplist = em.getemplist();
		return "input";
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<Employees> getEmplist() {
		return emplist;
	}

	public void setEmplist(ArrayList<Employees> emplist) {
		this.emplist = emplist;
	}

}
