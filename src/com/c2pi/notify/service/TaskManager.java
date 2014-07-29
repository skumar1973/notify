package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.TaskDAO;
import com.c2pi.notify.entity.TaskFrequency;
import com.c2pi.notify.entity.Task;

/**
 * @author Shailendrak
 *
 */
public class TaskManager {
	TaskDAO tdao = null;

	/**
	 * @param task
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String AddTask(Task task) throws SQLException, ClassNotFoundException, IOException {
		String tdaoRes = "";
		tdao = new TaskDAO();
		tdaoRes = tdao.addTask(task);
		return tdaoRes;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<TaskFrequency> getTaskFreqId() throws SQLException, ClassNotFoundException, IOException {
		ArrayList<TaskFrequency> tfList = null;
		tdao = new TaskDAO();
		tfList = tdao.getTaskFreqId();
		return tfList;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<Task> getTaskList() throws SQLException, ClassNotFoundException, IOException {
		ArrayList<Task> tsList = null;
		tdao = new TaskDAO();
		tsList = tdao.getTaskList();
		return tsList;
	}

}
