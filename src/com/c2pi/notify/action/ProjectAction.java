package com.c2pi.notify.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.Project;
import com.c2pi.notify.service.ProjectManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author Shailendrak
 * 
 */
public class ProjectAction extends ActionSupport implements SessionAware,
		Preparable, ModelDriven<Project> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Project project = new Project();

	private ArrayList<Project> projList = null;
	private ArrayList<Employee> empList = null;
	ProjectManager pm = null;

	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String getProjNEmpList()  {
		pm = new ProjectManager();
		try {
			projList = pm.getProjList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			empList = pm.getEmpList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "input";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {
		if ((project.getName() == null) || (project.getName().length() == 0)) {
			addFieldError("name", getText("app.project.name.blank"));

			pm = new ProjectManager();
			try {
				try {
					projList = pm.getProjList();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				try {
					empList = pm.getEmpList();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		String res = "";
		System.out.println("project action execute");

		if (sessionMap != null)
			sessionMap.remove("result");
		pm = new ProjectManager();
		res = pm.addProject(project);
		sessionMap.put("result", res);
		return "admin";
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (sessionMap == null) {
			System.out.println("Not log in.....");
		}
	}

	public ArrayList<Project> getProjList() {
		return projList;
	}

	public void setProjList(ArrayList<Project> projList) {
		this.projList = projList;
	}

	public ArrayList<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(ArrayList<Employee> empList) {
		this.empList = empList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Project getModel() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String fmtDate = dateFormat.format(date);
		String loginid = (String) sessionMap.get("loginID");
		project.setUpdatedDt(fmtDate);
		project.setCreatedDt(fmtDate);
		project.setCreatedBy(loginid);
		project.setUpdatedBy(loginid);

		return project;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
