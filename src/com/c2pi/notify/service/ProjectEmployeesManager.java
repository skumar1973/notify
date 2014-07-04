package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.ProjectEmployeesDAO;
import com.c2pi.notify.entity.Employees;
import com.c2pi.notify.entity.Project;
import com.c2pi.notify.entity.ProjectEmployees;

public class ProjectEmployeesManager {
	
	public String addprojectemployees(int proj_id, int emp_id, String created_by){
		String res="";	
		ProjectEmployeesDAO pedao = new ProjectEmployeesDAO();
		
		try {
			res=pedao.addprojectemployees(proj_id, emp_id, created_by);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public ArrayList<Project> getprojlist(int emp_id) {

		ArrayList<Project> projlist = null;
		ProjectEmployeesDAO pedao = new ProjectEmployeesDAO();
		System.out.println("emp_id="+emp_id);
		try {
			projlist = pedao.getprojlist(emp_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return projlist;
	}		
	
	public ArrayList<Employees> getemplist() {

		ArrayList<Employees> emplist = null;
		ProjectEmployeesDAO pedao = new ProjectEmployeesDAO();

		try {
			emplist = pedao.getemplist();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emplist;
	}		
	
	public ArrayList<ProjectEmployees> getprjemplist(int emp_id) {

		ArrayList<ProjectEmployees> prjemplist = null;
		ProjectEmployeesDAO pedao = new ProjectEmployeesDAO();
		System.out.println("emp_id="+emp_id);
		try {
			prjemplist = pedao.getprjemplist(emp_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prjemplist;
	}		
}
