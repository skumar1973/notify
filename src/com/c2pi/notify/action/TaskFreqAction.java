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

import com.c2pi.notify.entity.TaskFrequency;
import com.c2pi.notify.service.TaskFreqManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author Shailendrak
 * 
 */
public class TaskFreqAction extends ActionSupport implements SessionAware,
		ModelDriven<TaskFrequency>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<TaskFrequency> tfList = new ArrayList<TaskFrequency>();
	private TaskFrequency taskFreq = new TaskFrequency();
	private TaskFreqManager tfManager = new TaskFreqManager();

	Logger logger = Logger.getLogger(TaskFreqAction.class.getName());

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
	public String execute() {
		System.out.println("TaskFreq action execute");
		logger.info("TaskFreq action execute");
		sessionMap.remove("result");
		String res = null;

		logger.info("checking valid login.. start..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.error"));
			return ERROR;
		} else {
			logger.info("checking valid login.. complete..");

			try {
				res = tfManager.AddTaskFreq(taskFreq);
			} catch (ClassNotFoundException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();this.addActionError(getText("app.error"));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public TaskFrequency getModel() {

		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String fmtDate = dateFormat.format(date);
		String loginid = (String) sessionMap.get("loginID");
		// save
		if ((request.getParameter("id")==null)||(Integer.parseInt(request.getParameter("id")) == 0)) {
			taskFreq.setCreatedDt(fmtDate);
			taskFreq.setCreatedBy(loginid);
			taskFreq.setUpdatedDt(fmtDate);
			taskFreq.setUpdatedBy(loginid);
		} else {
			// update
			taskFreq.setUpdatedDt(fmtDate);
			taskFreq.setUpdatedBy(loginid);
		}
		return taskFreq;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {
		System.out.println("TaskFreq action validate");
		if ((taskFreq.getName() == null) || (taskFreq.getName().length() == 0)) {
			System.out.println("taskFreq.getName()="+taskFreq.getName());
			this.addFieldError("name", getText("app.taskfreq.name.blank"));
//			try {
//				tfList = tfManager.getTaskFreqList();
//			} catch (SQLException e) {
//				logger.error("ERROR:" + e.getMessage());
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				logger.error("ERROR-" + e.getMessage());
//				e.printStackTrace();
//			} catch (IOException e) {
//				logger.error("ERROR:" + e.getMessage());
//				e.printStackTrace();
//			}
		}
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() {
		System.out.println("TaskFreq action prepare method");

	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public String getTaskFreqList() {
		System.out.println("taskfreq action gettflist method ...");

		try {
			tfList = tfManager.getTaskFreqList();
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

		return "input";
	}

	/**
	 * @return
	 */
	public String edit() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		try {
			taskFreq = tfManager.getTaskFreqById(Integer.parseInt(request
					.getParameter("id")));
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

	/**
	 * @return
	 */
	public String delete() {
		String res=null;

		logger.info("checking valid login.. start..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.error"));
			return ERROR;
		} else {
		
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		sessionMap.remove("result");
		try {
			res=tfManager.deleteTF(Integer.parseInt(request.getParameter("id")));
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

	public ArrayList<TaskFrequency> getTfList() {
		return tfList;
	}

	public void setTfList(ArrayList<TaskFrequency> tfList) {
		this.tfList = tfList;
	}

	public TaskFrequency getTaskFreq() {
		return taskFreq;
	}

	public void setTaskFreq(TaskFrequency taskFreq) {
		this.taskFreq = taskFreq;
	}
}
