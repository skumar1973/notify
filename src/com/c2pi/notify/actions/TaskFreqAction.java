package com.c2pi.notify.actions;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.TaskFrequencies;
import com.c2pi.notify.service.TaskFreqManager;
import com.opensymphony.xwork2.ActionSupport;

public class TaskFreqAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String desc;
	private String status;
	ArrayList<TaskFrequencies> tflist = new ArrayList<TaskFrequencies>();

	Map sessionMap;

	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		System.out.println("TaskFreq action execute");

		sessionMap.remove("result");
		String res = null;
		TaskFreqManager taskfreqmanager = new TaskFreqManager();
		res = taskfreqmanager.AddTaskFreq(name, desc, status,
				(String) sessionMap.get("loginid"));
		sessionMap.put("result", res);

		return "admin";
	}

	public void validate() {
		System.out.println("TaskFreq action validate");

		// if ((name == null) || (name.length() == 0)) {
		// this.addFieldError("name", getText("app.name.blank"));
		// }
		//

	}

	public void prepare() {
		System.out.println("TaskFreq action prepare method");
	}

	public String gettflist() {
		System.out.println("taskfreq action gettflist method ...");
		TaskFreqManager tfm = new TaskFreqManager();
		tflist = tfm.gettflist();

		return "input";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<TaskFrequencies> getTflist() {
		return tflist;
	}

	public void setTflist(ArrayList<TaskFrequencies> tflist) {
		this.tflist = tflist;
	}

}
