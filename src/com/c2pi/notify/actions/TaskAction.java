package com.c2pi.notify.actions;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.TaskFrequencies;
import com.c2pi.notify.entity.Tasks;
import com.c2pi.notify.service.TaskManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class TaskAction extends ActionSupport implements SessionAware,
		Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String desc;
	private String status;
	private int freq_id = 0;
	private String created_by;

	ArrayList<TaskFrequencies> tflist = new ArrayList<TaskFrequencies>();
	ArrayList<Tasks> tslist = new ArrayList<Tasks>();
	
	Map sessionMap;

	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		String res="";
		sessionMap.remove("result");
		System.out.println("Task action execute");
		TaskManager tm = new TaskManager();
		res=tm.AddTask(name, desc, status, freq_id, (String) sessionMap.get("loginid"));
		sessionMap.put("result", res);
		return "admin";
	}

	public void validate() {
		System.out.println("Task action validate");
//		 if ((name == null) || (name.length() == 0)) {
//		 this.addFieldError("name", getText("app.name.blank"));
//		 }
//		 if ((freq_id == 0) ) {
//		 this.addFieldError("freq_id", getText("app.freq_id.blank"));
//		 }
	}

	public String getFreqId() {
		TaskManager tm = new TaskManager();	
		System.out.println("TaskManager getFreqId method ..");
		tflist = tm.getFreqId();
		System.out.println("TaskManager gettaskslist method ...");
		tslist = tm.gettaskslist();

		return "input";

	}

	public void prepare() {
		System.out.println("Task prepare method...");
		
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

	public int getFreq_id() {
		return freq_id;
	}

	public void setFreq_id(int freq_id) {
		this.freq_id = freq_id;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public ArrayList<TaskFrequencies> getTflist() {
		return tflist;
	}

	public void setTflist(ArrayList<TaskFrequencies> tflist) {
		this.tflist = tflist;
	}

	public ArrayList<Tasks> getTslist() {
		return tslist;
	}

	public void setTslist(ArrayList<Tasks> tslist) {
		this.tslist = tslist;
	}

}
