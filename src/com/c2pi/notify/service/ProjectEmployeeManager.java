package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.ProjectEmployeeDAO;
import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.Project;
import com.c2pi.notify.entity.ProjectEmployee;

/**
 * @author Shailendrak
 * 
 */
public class ProjectEmployeeManager {
	ProjectEmployeeDAO pedao = null;

	/**
	 * @param projEmp
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String addProjectEmployee(ProjectEmployee projEmp)
			throws SQLException, ClassNotFoundException, IOException {
		String res = "";
		pedao = new ProjectEmployeeDAO();
		res = pedao.addProjectEmployee(projEmp);

		return res;
	}

	/**
	 * @param empID
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 */
	public ArrayList<Project> getProjectList(int empID)
			throws ClassNotFoundException, SQLException, IOException {

		ArrayList<Project> projList = null;
		pedao = new ProjectEmployeeDAO();
		System.out.println("emp_id=" + empID);

		projList = pedao.getProjList(empID);

		return projList;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<Employee> getEmpList() throws SQLException,
			ClassNotFoundException, IOException {

		ArrayList<Employee> empList = null;
		pedao = new ProjectEmployeeDAO();
		empList = pedao.getEmpList();
		return empList;
	}

	/**
	 * @param empID
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<ProjectEmployee> getProjEmpList(int empID)
			throws SQLException, ClassNotFoundException, IOException {

		ArrayList<ProjectEmployee> projEmpList = null;
		pedao = new ProjectEmployeeDAO();
		System.out.println("emp_id=" + empID);
		projEmpList = pedao.getProjEmpList(empID);

		return projEmpList;
	}
}
