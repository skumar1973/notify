package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.TaskDAO;
import com.c2pi.notify.entity.TaskFrequencies;
import com.c2pi.notify.entity.Tasks;

public class TaskManager {

	String name;
	String desc;
	String status;
	int freq_id;
	String created_by;
	
	public String AddTask(String name, String desc, String status, int freq_id, String created_by){
		String taskdaores="";
		TaskDAO taskdao= new TaskDAO();
		try {			
			System.out.println("Taskdao addtask method...");
			taskdaores=taskdao.addTask(name, desc, status, freq_id, created_by);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskdaores;
	}


	public ArrayList<TaskFrequencies> getFreqId() {

		ArrayList<TaskFrequencies> tflist = null;

		TaskDAO tfdao = new TaskDAO();
		try {
			tflist = tfdao.getFreqId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tflist;
	}
	
	public ArrayList<Tasks> gettaskslist(){
		
		ArrayList<Tasks> tslist=null;
		TaskDAO tdao= new TaskDAO();
		tslist=tdao.gettaskslist();
		return tslist;
		
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

	
}
