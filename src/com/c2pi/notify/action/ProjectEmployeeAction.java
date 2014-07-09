package com.c2pi.notify.action;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.Project;
import com.c2pi.notify.entity.ProjectEmployee;
import com.c2pi.notify.service.ProjectEmployeeManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ProjectEmployeeAction extends ActionSupport implements
		SessionAware, Preparable, ModelDriven<ProjectEmployee> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<ProjectEmployee> projEmpList = null;
	ArrayList<Project> projList = null;
	ArrayList<Employee> empList = null;
	ProjectEmployeeManager pem = null;
	ProjectEmployee projEmp = new ProjectEmployee();
	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		System.out.println("ProjectEmployees action execute");
		pem = new ProjectEmployeeManager();
		String res = "";
		if (sessionMap != null) sessionMap.remove("result");
		System.out.println("res=" + res);
		res = pem.addProjectEmployee(projEmp);
		sessionMap.put("result", res);
		return "manager";
	}

	public void validate() {
		
		System.out.println("ProjectEmployees action validate");
		if ((projEmp.getProjID()==0)){
			addFieldError("projID",getText("app.projectEmployee.projID.blank"));
			int empID = 0;
			System.out.println("ProjectEmployees getprjemplist method...");
			pem = new ProjectEmployeeManager();
			empID = ((Integer) sessionMap.get("empID"));
			System.out.println("session emp_id=" + empID);
			try {
				projEmpList = pem.getProjEmpList(empID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			projList = pem.getProjectList(empID);
			try {
				empList = pem.getEmpList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getProjNEmpNProjEmpList() throws Exception {
		int empID = 0;
		System.out.println("ProjectEmployees getprjemplist method...");
		pem = new ProjectEmployeeManager();
		empID = ((Integer) sessionMap.get("empID"));
		System.out.println("session emp_id=" + empID);
		projEmpList = pem.getProjEmpList(empID);
		projList = pem.getProjectList(empID);
		empList = pem.getEmpList();
		
		return "input";
	}

	public void prepare() {
		System.out.println("ProjectEmployees prepare method");
	}



	public ArrayList<Project> getProjList() {
		return projList;
	}

	public void setProjList(ArrayList<Project> projList) {
		this.projList = projList;
	}

	public ArrayList<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(ArrayList<Employee> empList) {
		this.empList = empList;
	}

	public ArrayList<ProjectEmployee> getProjEmpList() {
		return projEmpList;
	}

	public void setProjEmpList(ArrayList<ProjectEmployee> projEmpList) {
		this.projEmpList = projEmpList;
	}

	@Override
	public ProjectEmployee getModel() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("getModel Date:" + dateFormat.format(date));
		projEmp.setUpdatedDt(dateFormat.format(date));
		projEmp.setCreatedDt(dateFormat.format(date));
		projEmp.setCreatedBy((String) sessionMap.get("loginID"));
		projEmp.setUpdatedBy((String) sessionMap.get("loginID"));
		
		return projEmp;
	}


}
