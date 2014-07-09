package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.EmployeeTaskDAO;
import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.EmployeeTask;
import com.c2pi.notify.entity.Task;

public class EmployeeTaskManager {

	EmployeeTaskDAO etdao = null;

	public String addEmployeeTask(int empID, int taskID, String createdBy)
			throws SQLException {
		String res = "";
		etdao = new EmployeeTaskDAO();
		res = etdao.addEmployeeTask(empID, taskID, createdBy);
		return res;
	}

	public ArrayList<EmployeeTask> getEmpTaskList(int empID)
			throws SQLException {
		ArrayList<EmployeeTask> empTaskList = null;
		etdao = new EmployeeTaskDAO();
		System.out.println("emp_id=" + empID);
		empTaskList = etdao.getEmpTaskList(empID);
		return empTaskList;
	}

	public ArrayList<Employee> getEmpList(int empID) throws SQLException {
		ArrayList<Employee> empList = null;
		etdao = new EmployeeTaskDAO();
		System.out.println("emp_id=" + empID);
		empList = etdao.getEmpList(empID);
		return empList;
	}

	public ArrayList<Task> getTaskList(int empID) throws SQLException {
		ArrayList<Task> taskList = null;
		etdao = new EmployeeTaskDAO();
		System.out.println("emp_id=" + empID);
		taskList = etdao.getTaskList(empID);
		return taskList;
	}
}
