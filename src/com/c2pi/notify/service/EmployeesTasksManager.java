package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.EmployeesTasksDAO;
import com.c2pi.notify.entity.Employees;
import com.c2pi.notify.entity.EmployeesTasks;
import com.c2pi.notify.entity.Tasks;

public class EmployeesTasksManager {
	
	public String addemployeestasks(int emp_id, int task_id, String created_by){
		String res="";	
		EmployeesTasksDAO etdao = new EmployeesTasksDAO();
		
		try {
			res=etdao.addemployeestasks(emp_id, task_id, created_by);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public ArrayList<EmployeesTasks> getemptaskslist(int emp_id) {

		ArrayList<EmployeesTasks> emptaskslist = null;
		EmployeesTasksDAO etdao = new EmployeesTasksDAO();
		System.out.println("emp_id="+emp_id);
		try {
			emptaskslist = etdao.getemptaskslist(emp_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emptaskslist;
	}
	
	public ArrayList<Employees> getemplist(int emp_id) {

		ArrayList<Employees> emplist = null;
		EmployeesTasksDAO etdao = new EmployeesTasksDAO();
		System.out.println("emp_id="+emp_id);
		try {
			emplist = etdao.getemplist(emp_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emplist;
	}

	public ArrayList<Tasks> gettaskslist(int emp_id) {

		ArrayList<Tasks> taskslist = null;
		EmployeesTasksDAO etdao = new EmployeesTasksDAO();
		System.out.println("emp_id="+emp_id);
		try {
			taskslist = etdao.gettaskslist(emp_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return taskslist;
	}
}
