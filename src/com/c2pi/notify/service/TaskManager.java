package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.TaskDAO;
import com.c2pi.notify.entity.TaskFrequency;
import com.c2pi.notify.entity.Task;

public class TaskManager {
	TaskDAO tdao = null;

	public String AddTask(Task task) throws SQLException {
		String tdaoRes = "";
		tdao = new TaskDAO();
		tdaoRes = tdao.addTask(task);
		return tdaoRes;
	}

	public ArrayList<TaskFrequency> getTaskFreqId() throws SQLException {
		ArrayList<TaskFrequency> tfList = null;
		tdao = new TaskDAO();
		tfList = tdao.getTaskFreqId();
		return tfList;
	}

	public ArrayList<Task> getTaskList() throws SQLException {
		ArrayList<Task> tsList = null;
		tdao = new TaskDAO();
		tsList = tdao.getTaskList();
		return tsList;
	}

}
