package com.c2pi.notify.actions;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Employees;
import com.c2pi.notify.entity.EmployeesTasks;
import com.c2pi.notify.entity.Tasks;
import com.c2pi.notify.service.EmployeesTasksManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class EmployeesTasksAction extends ActionSupport implements
		SessionAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int emp_id;
	private int task_id;

	ArrayList<EmployeesTasks> emptaskslist = null;
	ArrayList<Employees> emplist = null;
	ArrayList<Tasks> taskslist = null;

	Map sessionMap;

	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		System.out.println("EmployeesTasks action execute");
		EmployeesTasksManager etm = new EmployeesTasksManager();
		String res = "";
		if (sessionMap != null)
			sessionMap.remove("result");
		System.out.println("res=" + res);
		res = etm.addemployeestasks(emp_id, task_id,
				(String) sessionMap.get("loginid"));
		sessionMap.put("result", res);
		return "manager";
	}

	public void validate() {
		System.out.println("EmployeesTasks action validate");

	}

	public String getemptaskslist() throws Exception {
		int emp_id = 0;
		System.out.println("EmployeesTasks getprjemplist method...");
		EmployeesTasksManager etm = new EmployeesTasksManager();
		emp_id = ((Integer) sessionMap.get("emp_id"));
		System.out.println("session emp_id=" + emp_id);
		emptaskslist = etm.getemptaskslist(emp_id);
		emplist=etm.getemplist(emp_id);
		taskslist=etm.gettaskslist(emp_id);
		
		return "input";
	}

	public void prepare() {
		System.out.println("EmployeesTasks prepare method");
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public ArrayList<EmployeesTasks> getEmptaskslist() {
		return emptaskslist;
	}

	public void setEmptaskslist(ArrayList<EmployeesTasks> emptaskslist) {
		this.emptaskslist = emptaskslist;
	}

	public ArrayList<Employees> getEmplist() {
		return emplist;
	}

	public void setEmplist(ArrayList<Employees> emplist) {
		this.emplist = emplist;
	}

	public ArrayList<Tasks> getTaskslist() {
		return taskslist;
	}

	public void setTaskslist(ArrayList<Tasks> taskslist) {
		this.taskslist = taskslist;
	}

}
