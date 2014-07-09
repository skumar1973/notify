package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.ProjectEmployeeDAO;
import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.Project;
import com.c2pi.notify.entity.ProjectEmployee;

public class ProjectEmployeeManager {
	ProjectEmployeeDAO pedao = null;

	public String addProjectEmployee(ProjectEmployee projEmp)
			throws SQLException {
		String res = "";
		pedao = new ProjectEmployeeDAO();
		res = pedao.addProjectEmployee(projEmp);

		return res;
	}

	public ArrayList<Project> getProjectList(int empID) {

		ArrayList<Project> projList = null;
		pedao = new ProjectEmployeeDAO();
		System.out.println("emp_id=" + empID);
		try {
			projList = pedao.getProjList(empID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return projList;
	}

	public ArrayList<Employee> getEmpList() throws SQLException {

		ArrayList<Employee> empList = null;
		pedao = new ProjectEmployeeDAO();
		empList = pedao.getEmpList();
		return empList;
	}

	public ArrayList<ProjectEmployee> getProjEmpList(int empID) throws SQLException {

		ArrayList<ProjectEmployee> projEmpList = null;
		pedao = new ProjectEmployeeDAO();
		System.out.println("emp_id=" + empID);
		projEmpList = pedao.getProjEmpList(empID);
		
		return projEmpList;
	}
}
