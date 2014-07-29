package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.TaskFreqDAO;
import com.c2pi.notify.entity.TaskFrequency;

/**
 * @author Shailendrak
 * 
 */
public class TaskFreqManager {

	private TaskFreqDAO tfdao = new TaskFreqDAO();
	private String tfdaores = "";
	private TaskFrequency tf= null;
	private ArrayList<TaskFrequency> tfList = null;

	/**
	 * @param taskFreq
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public String AddTaskFreq(TaskFrequency taskFreq)
			throws ClassNotFoundException, SQLException, IOException {
		System.out.println("TaskFreq dao addTaskFreq method...");
		tfdaores = tfdao.addTaskFreq(taskFreq);
		return tfdaores;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<TaskFrequency> getTaskFreqList() throws SQLException,
			ClassNotFoundException, IOException {
		System.out.println("TaskFreqManager gettflist method..");
		tfList = tfdao.getTaskFreqList();
		System.out.println("tflist" + tfList);

		return tfList;

	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public TaskFrequency getTaskFreqById(int tfID)
			throws SQLException, ClassNotFoundException, IOException {
		System.out.println("TaskFreqManager gettflist method..");
		tf = tfdao.getTaskFreqById(tfID);
		System.out.println("tflist" + tfList);
		return tf;

	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public String deleteTF(int tfID) throws SQLException,
			ClassNotFoundException, IOException {
		System.out.println("TaskFreqManager deleteTF method..");
		tfdaores = tfdao.deleteTF(tfID);
		System.out.println("tfdaores" + tfdaores);

		return tfdaores;

	}

}
