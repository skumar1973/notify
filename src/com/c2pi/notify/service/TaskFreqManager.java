package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.TaskFreqDAO;
import com.c2pi.notify.entity.TaskFrequency;

public class TaskFreqManager {
	
	TaskFreqDAO tfdao = null;
	
//	public String AddTaskFreq(String name, String desc, String status, String createdBy) {
	public String AddTaskFreq(TaskFrequency taskFreq) {		
		String tfdaores = "";

		tfdao = new TaskFreqDAO();
		try {
			System.out.println("TaskFreq dao addTaskFreq method...");
//			tfdaores = tfdao.addTaskFreq(name, desc, status,
//					createdBy);
			tfdaores = tfdao.addTaskFreq(taskFreq);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tfdaores;
	}

	public ArrayList<TaskFrequency> getTaskFreqList() throws SQLException {
		System.out.println("TaskFreqManager gettflist method..");
		ArrayList<TaskFrequency> tfList = null;
		tfdao = new TaskFreqDAO();

		tfList = tfdao.getTaskFreqList();
		System.out.println("tflist" + tfList);

		return tfList;

	}

}
