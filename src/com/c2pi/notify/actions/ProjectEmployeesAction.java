package com.c2pi.notify.actions;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Employees;
import com.c2pi.notify.entity.Project;
import com.c2pi.notify.entity.ProjectEmployees;
import com.c2pi.notify.service.ProjectEmployeesManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class ProjectEmployeesAction extends ActionSupport implements
		SessionAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int proj_id;
	private int emp_id;

	ArrayList<ProjectEmployees> prjemplist = null;
	ArrayList<Project> projlist = null;
	ArrayList<Employees> emplist = null;

	Map sessionMap;

	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		System.out.println("ProjectEmployees action execute");
		ProjectEmployeesManager pem = new ProjectEmployeesManager();
		String res = "";
		if (sessionMap != null) sessionMap.remove("result");
		System.out.println("res=" + res);
		res = pem.addprojectemployees(proj_id, emp_id, (String) sessionMap.get("loginid"));
		sessionMap.put("result", res);
		return "manager";
	}

	public void validate() {

		System.out.println("ProjectEmployees action validate");

	}

	public String getprjemplist() throws Exception {
		int emp_id = 0;
		System.out.println("ProjectEmployees getprjemplist method...");
		ProjectEmployeesManager pem = new ProjectEmployeesManager();
		emp_id = ((Integer) sessionMap.get("emp_id"));
		System.out.println("session emp_id=" + emp_id);
		prjemplist = pem.getprjemplist(emp_id);
		projlist = pem.getprojlist(emp_id);
		emplist = pem.getemplist();
		return "input";
	}

	public void prepare() {
		System.out.println("ProjectEmployees prepare method");
	}

	public int getProj_id() {
		return proj_id;
	}

	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public ArrayList<ProjectEmployees> getPrjemplist() {
		return prjemplist;
	}

	public void setPrjemplist(ArrayList<ProjectEmployees> prjemplist) {
		this.prjemplist = prjemplist;
	}
	
	public ArrayList<Project> getProjlist() {
		return projlist;
	}

	public void setProjlist(ArrayList<Project> projlist) {
		this.projlist = projlist;
	}

	
	public ArrayList<Employees> getEmplist() {
		return emplist;
	}

	public void setEmplist(ArrayList<Employees> emplist) {
		this.emplist = emplist;
	}
}
