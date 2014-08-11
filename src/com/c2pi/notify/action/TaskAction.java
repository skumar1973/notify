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

import com.c2pi.notify.entity.Task;
import com.c2pi.notify.entity.TaskFrequency;
import com.c2pi.notify.service.TaskManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author Shailendrak
 * 
 */
public class TaskAction extends ActionSupport implements SessionAware,
		Preparable, ModelDriven<Task> {

	private static final long serialVersionUID = 1L;

	private ArrayList<TaskFrequency> tfList = new ArrayList<TaskFrequency>();
	private ArrayList<Task> tsList = new ArrayList<Task>();

	private Task task = new Task();
	private TaskManager tm = new TaskManager();

	Logger logger = Logger.getLogger(TaskAction.class.getName());

	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		logger.info("Task action execute");
		String retVal = "";
		sessionMap.remove("result");
		System.out.println("Task action execute");
		logger.info("checking valid login.. start..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return "login";
		} else {
			logger.info("checking valid login.. complete..");
			try {
				retVal = tm.AddTask(task);
			} catch (ClassNotFoundException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (SQLException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (IOException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			}

			sessionMap.put("result", retVal);
			return "admin";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {

		System.out.println("Task action validate");
		
		if ((task.getName() == null) || (task.getName().length() == 0)) {
			this.addFieldError("name", getText("app.task.name.blank"));			
		}
		if ((task.getFreqID() == 0) ) {
			this.addFieldError("freqID", getText("app.task.freqID.blank"));			
		}
		if ((task.getStatus() == null) || (task.getStatus().length() == 0)) {
			this.addFieldError("status", getText("app.task.status.blank"));			
		}
		getTaskNTfList();
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public String getTaskNTfList() {

		System.out.println("TaskManager getFreqId method ..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return "login";
		} else {
			try {
				tfList = tm.getTaskFreqList();

			} catch (ClassNotFoundException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (IOException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (SQLException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			}
			System.out.println("TaskManager gettaskslist method ...");
			try {
				tsList = tm.getTaskList();
			} catch (ClassNotFoundException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (SQLException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (IOException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			}

			return "input";
		}
	}

	public void prepare() {
		System.out.println("Task prepare method...");

	}

	public ArrayList<TaskFrequency> getTfList() {
		return tfList;
	}

	public void setTfList(ArrayList<TaskFrequency> tfList) {
		this.tfList = tfList;
	}

	public ArrayList<Task> getTsList() {
		return tsList;
	}

	public void setTsList(ArrayList<Task> tsList) {
		this.tsList = tsList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Task getModel() {

		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String fmtDate = dateFormat.format(date);
		String loginid = (String) sessionMap.get("loginID");
		// save
		if ((request.getParameter("id") == null)
				|| (Integer.parseInt(request.getParameter("id")) == 0)) {
			task.setCreatedDt(fmtDate);
			task.setCreatedBy(loginid);
			task.setUpdatedDt(fmtDate);
			task.setUpdatedBy(loginid);
		} else {
			// update
			task.setUpdatedDt(fmtDate);
			task.setUpdatedBy(loginid);
		}
		return task;

	}

	/**
	 * @return
	 */
	public String edit() {

		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return "login";
		} else {
			logger.info("checking valid login.. complete..");

			try {
				task = tm.getTaskById(Integer.parseInt(request
						.getParameter("id")));
				tfList = tm.getTaskFreqList();
			} catch (NumberFormatException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (ClassNotFoundException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (SQLException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (IOException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			}

			return "input";
		}
	}

	/**
	 * @return
	 */
	public String delete() {
		String res = null;

		logger.info("checking valid login.. start..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return "login";
		} else {

			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			sessionMap.remove("result");
			try {
				res = tm.deleteTask(Integer.parseInt(request.getParameter("id")));
			} catch (NumberFormatException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (ClassNotFoundException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (SQLException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (IOException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			}
			sessionMap.put("result", res);
			return "admin";
		}
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
