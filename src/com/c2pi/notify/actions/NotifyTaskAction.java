package com.c2pi.notify.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.EmployeesTaskNotification;
import com.c2pi.notify.entity.Tasks;
import com.c2pi.notify.service.NotifyTaskManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class NotifyTaskAction extends ActionSupport implements SessionAware,
		Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date period_date;

	Integer[] emptask = null;

	public Integer[] getEmptask() {
		return emptask;
	}

	public void setEmptask(Integer[] emptask) {
		this.emptask = emptask;
	}

	ArrayList<Tasks> emptasklist = new ArrayList<Tasks>();

	public ArrayList<Tasks> getEmptasklist() {
		return emptasklist;
	}

	public void setEmptasklist(ArrayList<Tasks> emptasklist) {
		this.emptasklist = emptasklist;
	}

	Map sessionMap;

	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Map getSessionMap() {
		return sessionMap;
	}

	ArrayList<EmployeesTaskNotification> etnlist = null;

	public String execute() throws Exception {
		System.out.println("Notify task execute");
		NotifyTaskManager ntm = new NotifyTaskManager();
		if (sessionMap != null)
			sessionMap.remove("result");
		String res = "";
		
		System.out.println("res=" + res);
		int i = 0;
		System.out.println("total task=" + emptask.length);
		while (i < emptask.length) {
			System.out.println("emptask[" + i + "]:" + emptask[i]);
			i++;
		}
		res = ntm.addnotifytask((Integer) sessionMap.get("emp_id"), emptask,
				period_date, (String) sessionMap.get("loginid"));
		res=ntm.sendnotifyemail(emptask);
		sessionMap.put("result", res);

		return "team";
	}

	public void validate() {

		System.out.println("Notify task action validate");

	}

	public String getlist() throws Exception {
		System.out.println("Notify task getlist method...");
		NotifyTaskManager ntm = new NotifyTaskManager();
		System.out.println("login id=" + (String) sessionMap.get("loginid"));
		etnlist = ntm.getetnlist((String) sessionMap.get("loginid"));

		emptasklist = ntm.getemptasklist((String) sessionMap.get("loginid"));
		setPeriod_date(new Date());

		return "input";
	}

	public void prepare() {
		System.out.println("Notify task prepare method");

	}

	public Date getPeriod_date() {
		return period_date;
	}

	public void setPeriod_date(Date period_date) {
		this.period_date = period_date;
	}

	public ArrayList<EmployeesTaskNotification> getEtnlist() {
		return etnlist;
	}

	public void setEtnlist(ArrayList<EmployeesTaskNotification> etnlist) {
		this.etnlist = etnlist;
	}

}
