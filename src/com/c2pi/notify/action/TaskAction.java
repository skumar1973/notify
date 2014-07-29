package com.c2pi.notify.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.TaskFrequency;
import com.c2pi.notify.entity.Task;
import com.c2pi.notify.service.TaskManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author Shailendrak
 * 
 */
public class TaskAction extends ActionSupport implements SessionAware,
		Preparable, ModelDriven<Task> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<TaskFrequency> tfList = null;
	private ArrayList<Task> tsList = null;
	private Task task = new Task();

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
		String res = "";
		sessionMap.remove("result");
		System.out.println("Task action execute");
		TaskManager tm = new TaskManager();
		res = tm.AddTask(task);
		sessionMap.put("result", res);
		return "admin";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {
		System.out.println("Task action validate");
		System.out.println("employee action validate method..");
		if ((task.getName() == null) || (task.getName().length() == 0)) {
			this.addFieldError("name", getText("app.task.name.blank"));
			TaskManager tm = new TaskManager();
			System.out.println("TaskManager getFreqId method ..");
			try {
				tfList = tm.getTaskFreqId();
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
			System.out.println("TaskManager gettaskslist method ...");
			try {
				tsList = tm.getTaskList();
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
		}
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public String getTaskNTfList()  {
		TaskManager tm = new TaskManager();
		System.out.println("TaskManager getFreqId method ..");
		try {
			tfList = tm.getTaskFreqId();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("TaskManager gettaskslist method ...");
		try {
			tsList = tm.getTaskList();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "input";

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

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Task getModel() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String fmtDate = dateFormat.format(date);
		String loginid = (String) sessionMap.get("loginID");
		task.setUpdatedDt(fmtDate);
		task.setCreatedDt(fmtDate);
		task.setCreatedBy(loginid);
		task.setUpdatedBy(loginid);
		return task;
	}

}
