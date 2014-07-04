package com.c2pi.notify.actions;

import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.c2pi.notify.entity.Project;
import com.c2pi.notify.service.ProjectsManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class ProjectsAction extends ActionSupport implements SessionAware,
		Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String desc;
	private int mgr_id;
	private String status;

	ArrayList<Project> prlist = new ArrayList<Project>();

	// ArrayList<Tasks> tslist = new ArrayList<Tasks>();

	Map sessionMap;

	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Project> getPrlist() {
		return prlist;
	}

	public void setPrlist(ArrayList<Project> prlist) {
		this.prlist = prlist;
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

	public int getMgr_id() {
		return mgr_id;
	}

	public void setMgr_id(int mgr_id) {
		this.mgr_id = mgr_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String showProjects() throws Exception {
		ProjectsManager pm = new ProjectsManager();
		prlist = pm.Show();
		return "input";
	}

	public String execute() throws Exception {
		String res = "";
		System.out.println("project action execute");
		System.out.println("id " + mgr_id);
		System.out.println("name " + name);
		if (sessionMap != null)
			sessionMap.remove("result");
		ProjectsManager projectman = new ProjectsManager();
		res = projectman.projectAdd(name, desc, mgr_id, status,
				(String) sessionMap.get("loginid"));
		sessionMap.put("result", res);
		return "admin";
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

}
