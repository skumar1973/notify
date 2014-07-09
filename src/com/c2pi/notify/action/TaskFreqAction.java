package com.c2pi.notify.action;


import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.TaskFrequency;
import com.c2pi.notify.service.TaskFreqManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class TaskFreqAction extends ActionSupport implements SessionAware, ModelDriven<TaskFrequency>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<TaskFrequency> tfList = null;
	private TaskFrequency taskFreq = new TaskFrequency();
	private TaskFreqManager tfManager = null;
	
	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		System.out.println("TaskFreq action execute");

		sessionMap.remove("result");
		String res = null;
		tfManager = new TaskFreqManager();
		res= tfManager.AddTaskFreq(taskFreq);
		
		sessionMap.put("result", res);

		return "admin";
	}

	@Override
	public TaskFrequency getModel() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("getModel Date:"+dateFormat.format(date));		
		taskFreq.setUpdatedDt(dateFormat.format(date));
		taskFreq.setCreatedDt(dateFormat.format(date));
		taskFreq.setCreatedBy((String) sessionMap.get("loginID"));
		taskFreq.setUpdatedBy((String) sessionMap.get("loginID")); 
		return taskFreq;
	}


	public void validate() {
		System.out.println("TaskFreq action validate");
		 if ((taskFreq.getName() == null) || (taskFreq.getName().length() == 0)) {
		 this.addFieldError("name", getText("app.taskfreq.name.blank"));
		
		 tfManager = new TaskFreqManager();
			try {
				tfList = tfManager.getTaskFreqList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}

	public void prepare() {
		System.out.println("TaskFreq action prepare method");
		
	}

	public String getTaskFreqList() throws SQLException {
		System.out.println("taskfreq action gettflist method ...");
		tfManager = new TaskFreqManager();
		tfList = tfManager.getTaskFreqList();

		return "input";
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
