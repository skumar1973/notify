package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.ProjectDAO;
import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.Project;

/**
 * @author Shailendrak
 * 
 */
public class ProjectManager {
	ProjectDAO pdao = null;
	Project editproject=null;
	Project deleteProj= null;

	/**
	 * @param project
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String addProject(Project project) throws SQLException,
			ClassNotFoundException, IOException {
		pdao = new ProjectDAO();
		String addVal = pdao.addProject(project);
		return addVal;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<Project> getProjList() throws SQLException,
			ClassNotFoundException, IOException {

		ArrayList<Project> projList = null;
		pdao = new ProjectDAO();
		projList = pdao.getProjList();
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
		pdao = new ProjectDAO();
		empList = pdao.getMgrList();
		return empList;
	}
	
	public Project editProject(int projectId) throws SQLException, ClassNotFoundException, IOException{
		pdao = new ProjectDAO();
		editproject = pdao.geteditprojectList(projectId);
		
		return editproject;
	}
	
	/**
	 * @param projectId
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
		
	public String deleteProj(int projectId) throws SQLException, IOException, ClassNotFoundException{
		String deletePj=null;
		pdao = new ProjectDAO();
		deletePj = pdao.projectDelete(projectId);
		return deletePj;
	}
	
}
