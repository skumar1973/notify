package com.c2pi.notify.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.EmployeeTask;
import com.c2pi.notify.entity.Task;
import com.c2pi.notify.service.EmployeeTaskManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class EmployeeTaskAction extends ActionSupport implements
		SessionAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int empID;
	private int taskID;

	ArrayList<EmployeeTask> empTaskList = null;
	ArrayList<Employee> empList = null;
	ArrayList<Task> taskList = null;
	
	EmployeeTaskManager etm = null;
	
	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		System.out.println("EmployeesTasks action execute");
		etm = new EmployeeTaskManager();
		String res = "";
		if (sessionMap != null)
			sessionMap.remove("result");
		System.out.println("res=" + res);
		res = etm.addEmployeeTask(empID, taskID, (String) sessionMap.get("loginID"));
		sessionMap.put("result", res);
		return "manager";
	}

	public void validate() {
		System.out.println("EmployeesTasks action validate");
		if (( empID == 0)) {
			this.addFieldError("empID", getText("app.employeeTask.empID.blank"));
			int empID = 0;
			System.out.println("EmployeesTasks getprjemplist method...");
			etm = new EmployeeTaskManager();
			empID = ((Integer) sessionMap.get("empID"));
			System.out.println("session emp_id=" + empID);
			try {
				empTaskList = etm.getEmpTaskList(empID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				empList=etm.getEmpList(empID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				taskList=etm.getTaskList(empID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getEmpNTaskNEmpTaskList() throws Exception {
		int empID = 0;
		System.out.println("EmployeesTasks getprjemplist method...");
		etm = new EmployeeTaskManager();
		empID = ((Integer) sessionMap.get("empID"));
		System.out.println("session emp_id=" + empID);
		empTaskList = etm.getEmpTaskList(empID);
		empList=etm.getEmpList(empID);
		taskList=etm.getTaskList(empID);
		
		return "input";
	}

	public void prepare() {
		System.out.println("EmployeesTasks prepare method");
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public ArrayList<EmployeeTask> getEmpTaskList() {
		return empTaskList;
	}

	public void setEmpTaskList(ArrayList<EmployeeTask> empTaskList) {
		this.empTaskList = empTaskList;
	}

	public ArrayList<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(ArrayList<Employee> empList) {
		this.empList = empList;
	}

	public ArrayList<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}


}
