package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.TaskFreqDAO;
import com.c2pi.notify.entity.TaskFrequencies;

public class TaskFreqManager {

	public String AddTaskFreq(String name, String desc, String status,
			String created_by) {
		String taskfreqdaores = "";

		TaskFreqDAO taskfreqdao = new TaskFreqDAO();
		try {
			System.out.println("TaskFreq dao addTaskFreq method...");
			taskfreqdaores = taskfreqdao.addTaskFreq(name, desc, status,
					created_by);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskfreqdaores;
	}

	public ArrayList<TaskFrequencies> gettflist() {
		System.out.println("TaskFreqManager gettflist method..");
		ArrayList<TaskFrequencies> tflist = new ArrayList<TaskFrequencies>();
		TaskFreqDAO tfdao = new TaskFreqDAO();

		tflist = tfdao.gettflist();
		System.out.println("tflist" + tflist);

		return tflist;

	}

}
