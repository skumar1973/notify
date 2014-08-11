package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.TaskDAO;
import com.c2pi.notify.dao.TaskFreqDAO;
import com.c2pi.notify.entity.Task;
import com.c2pi.notify.entity.TaskFrequency;

/**
 * @author Shailendrak
 *
 */
public class TaskManager {
	private TaskDAO tdao = new TaskDAO();
	
	/**
	 * @param task
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String AddTask(Task task) throws SQLException, ClassNotFoundException, IOException {
		String tdaoRes = "";
		
		tdaoRes = tdao.addTask(task);
		return tdaoRes;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<TaskFrequency> getTaskFreqList() throws SQLException, ClassNotFoundException, IOException {
		ArrayList<TaskFrequency> tfList = null;
		TaskFreqDAO tfdao = new TaskFreqDAO();
		tfList = tfdao.getTaskFreqList();
		
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
		
		tsList = tdao.getTaskList();
		return tsList;
	}
	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Task getTaskById(int tsID)
			throws SQLException, ClassNotFoundException, IOException {
		System.out.println("TaskManager gettflist method..");
		Task task = null;
		task = tdao.getTaskById(tsID);
		System.out.println("task" + task);
		return task;

	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public String deleteTask(int tfID) throws SQLException,
			ClassNotFoundException, IOException {
		System.out.println("TaskFreqManager deleteTF method..");
		String tDaoRes = null;
		tDaoRes = tdao.deleteTask(tfID);
		System.out.println("tdaores" + tDaoRes);

		return tDaoRes;

	}

}
