package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.ProjectDAO;
import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.Project;

public class ProjectManager {
	ProjectDAO pdao = null;

	public String addProject(Project project) throws SQLException {
		pdao = new ProjectDAO();
		String addVal = pdao.addProject(project);
		return addVal;
	}

	public ArrayList<Project> getProjList() throws SQLException {

		ArrayList<Project> projList = null;
		pdao = new ProjectDAO();
		projList = pdao.getProjList();
		return projList;
	}

	public ArrayList<Employee> getEmpList() throws SQLException {

		ArrayList<Employee> empList = null;
		pdao = new ProjectDAO();
		empList = pdao.getMgrList();
		return empList;
	}
}
