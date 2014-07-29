package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.EmployeeTaskDAO;
import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.EmployeeTask;
import com.c2pi.notify.entity.Task;

/**
 * @author Shailendrak
 *
 */
public class EmployeeTaskManager {

	EmployeeTaskDAO etdao = null;

	/**
	 * @param empID
	 * @param taskID
	 * @param createdBy
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public String addEmployeeTask(int empID, int taskID, String createdBy)
			throws SQLException, ClassNotFoundException, IOException {
		String res = "";
		etdao = new EmployeeTaskDAO();
		res = etdao.addEmployeeTask(empID, taskID, createdBy);
		return res;
	}

	/**
	 * @param empID
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<EmployeeTask> getEmpTaskList(int empID)
			throws SQLException, ClassNotFoundException, IOException {
		ArrayList<EmployeeTask> empTaskList = null;
		etdao = new EmployeeTaskDAO();
		System.out.println("emp_id=" + empID);
		empTaskList = etdao.getEmpTaskList(empID);
		return empTaskList;
	}

	/**
	 * @param empID
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public ArrayList<Employee> getEmpList(int empID) throws SQLException, ClassNotFoundException, IOException {
		ArrayList<Employee> empList = null;
		etdao = new EmployeeTaskDAO();
		System.out.println("emp_id=" + empID);
		empList = etdao.getEmpList(empID);
		return empList;
	}

	/**
	 * @param empID
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public ArrayList<Task> getTaskList(int empID) throws SQLException, ClassNotFoundException, IOException {
		ArrayList<Task> taskList = null;
		etdao = new EmployeeTaskDAO();
		System.out.println("emp_id=" + empID);
		taskList = etdao.getTaskList(empID);
		return taskList;
	}
}
