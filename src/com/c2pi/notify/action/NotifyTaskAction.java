package com.c2pi.notify.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.EmployeesTaskNotification;
import com.c2pi.notify.entity.Task;
import com.c2pi.notify.service.NotifyTaskManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * @author Shailendrak
 * 
 */
public class NotifyTaskAction extends ActionSupport implements SessionAware,
		Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date periodDate;
	
	Integer[] empTaskIDArray = null;
	ArrayList<Task> empTaskNameList = null;
	ArrayList<EmployeesTaskNotification> etnList = null;
	NotifyTaskManager ntm = null;
	ArrayList<String> dateList = new ArrayList<String>();
	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute()  {
		System.out.println("Notify task execute");
		ntm = new NotifyTaskManager();
		if (sessionMap != null)
			sessionMap.remove("result");
		String res = "";
		try {
			res = ntm.addNotifyTask((Integer) sessionMap.get("empID"),
					empTaskIDArray, periodDate, (String) sessionMap.get("loginID"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			res = ntm.sendNotifyEmail(empTaskIDArray,
					(Integer) sessionMap.get("empID"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sessionMap.put("result", res);

		return "team";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {

		System.out.println("Notify task action validate");
		if (empTaskIDArray.length == 0) {
			addFieldError("periodDate", getText("app.notifytask.submit.blank"));
			ntm = new NotifyTaskManager();
			System.out
					.println("login id=" + (String) sessionMap.get("loginID"));
			try {
				etnList = ntm.getEtnList((String) sessionMap.get("loginID"));
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				try {
					empTaskNameList = ntm.getEmpTaskList((String) sessionMap
							.get("loginID"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setPeriodDate(new Date());
		}

	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String getEmpTaskNameNEtnList() throws Exception {
		System.out.println("Notify task getlist method...");
		ntm = new NotifyTaskManager();
		System.out.println("login id=" + (String) sessionMap.get("loginID"));
		etnList = ntm.getEtnList((String) sessionMap.get("loginID"));
		empTaskNameList = ntm
				.getEmpTaskList((String) sessionMap.get("loginID"));

		setPeriodDate(new Date());
		dateList = ntm.getDateList();		
		return "input";
	}

	public void prepare() {
		System.out.println("Notify task prepare method");

	}

	public Date getPeriodDate() {
		return periodDate;
	}

	public void setPeriodDate(Date periodDate) {
		this.periodDate = periodDate;
	}

	public Integer[] getEmpTaskIDArray() {
		return empTaskIDArray;
	}

	public void setEmpTaskIDArray(Integer[] empTaskIDArray) {
		this.empTaskIDArray = empTaskIDArray;
	}

	public ArrayList<Task> getEmpTaskNameList() {
		return empTaskNameList;
	}

	public void setEmpTaskNameList(ArrayList<Task> empTaskNameList) {
		this.empTaskNameList = empTaskNameList;
	}

	public ArrayList<EmployeesTaskNotification> getEtnList() {
		return etnList;
	}

	public void setEtnList(ArrayList<EmployeesTaskNotification> etnList) {
		this.etnList = etnList;
	}

	public ArrayList<String> getDateList() {
		return dateList;
	}

	public void setDateList(ArrayList<String> dateList) {
		this.dateList = dateList;
	}


}
