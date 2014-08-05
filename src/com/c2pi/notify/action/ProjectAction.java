package com.c2pi.notify.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.Project;
import com.c2pi.notify.service.ProjectManager;
import com.opensymphony.xwork2.ActionContext;
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

	private Project project = new Project();

	private ArrayList<Project> projList = new ArrayList<Project>();
	private ArrayList<Employee> empList = new ArrayList<Employee>();
	private ProjectManager pm = null;
//	private Project editproj=null;

	Logger logger = Logger.getLogger(ProjectAction.class.getName());
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
			logger.error("ERROR-" + e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error("ERROR-" + e.getMessage());
		} catch (IOException e) {
			logger.error("ERROR-" + e.getMessage());
		}
		try {
			empList = pm.getEmpList();
		} catch (SQLException e) {
			logger.error("ERROR-" + e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error("ERROR-" + e.getMessage());
		} catch (IOException e) {
			logger.error("ERROR-" + e.getMessage());
		}

		return "input";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	/* (non-Javadoc)
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
					logger.error("ERROR-" + e.getMessage());
				}
			} catch (SQLException e) {
				logger.error("ERROR-" + e.getMessage());
			} catch (ClassNotFoundException e) {
				logger.error("ERROR-" + e.getMessage());
			}
			try {
				try {
					empList = pm.getEmpList();
				} catch (IOException e) {
					logger.error("ERROR-" + e.getMessage());
				}
			} catch (SQLException e) {
				logger.error("ERROR-" + e.getMessage());
			} catch (ClassNotFoundException e) {
				logger.error("ERROR-" + e.getMessage());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		logger.info("Project action execute");
		sessionMap.remove("result");
		String res = "";
		logger.debug("Project execute method...");
		logger.info("checking valid login.. start..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return ERROR;
		}else{
			logger.info("checking valid login.. complete..");
			pm = new ProjectManager();
			res = pm.addProject(project);
			sessionMap.put("result", res);
			return "admin";
		}
		
		//System.out.println("project action execute");

			
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#editProject()
	 */
	
	public String editProject() throws Exception {
		logger.debug("Edit Project execute method...");
		String res = "";
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		
		
			logger.info("checking valid login.. complete..");
			pm = new ProjectManager();
			project = pm.editProject(Integer.parseInt(request
					.getParameter("id")));
			sessionMap.put("result", res);
			return "input";
		
		}
	
	/**
	 * deleteProject method
	 * @return
	 * @throws Exception
	 */
	public String deleteProject() throws Exception {
		logger.info("checking valid login.. start..");
		
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.error"));
			return ERROR;
		} else {
		logger.debug("Called deleteProject method...");
		String res = "";
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		sessionMap.remove("result");
		pm = new ProjectManager();
		res=pm.deleteProj(Integer.parseInt(request.getParameter("id")));
		sessionMap.put("result", res);
		return "admin";
		}
		
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
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String fmtDate = dateFormat.format(date);
		String loginid = (String) sessionMap.get("loginID");
		// save
		if ((request.getParameter("id")==null)||(Integer.parseInt(request.getParameter("id")) == 0)) {
			project.setUpdatedDt(fmtDate);
			project.setCreatedDt(fmtDate);
			project.setCreatedBy(loginid);
			project.setUpdatedBy(loginid);
		}else{
		//update
			project.setUpdatedDt(fmtDate);
			project.setUpdatedBy(loginid);
		}
		

		return project;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
