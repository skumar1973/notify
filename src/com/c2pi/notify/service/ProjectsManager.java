package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.ProjectDAO;
import com.c2pi.notify.entity.Project;

public class ProjectsManager {

	public String projectAdd(String name, String desc, int mgr_id ,String status,
			String created_by) throws SQLException {
//		/String addVal=null;
		ArrayList<Project> prlist = null;
		ProjectDAO projectdao = new ProjectDAO();
		String addVal=projectdao.addProject(name, desc, mgr_id,status, created_by);
		return addVal;
	}
	
	public ArrayList<Project> Show() throws SQLException {

		ArrayList<Project> prlist = null;
		ProjectDAO projectdao = new ProjectDAO();
		prlist=projectdao.showProject();
		return prlist;
	}
}
