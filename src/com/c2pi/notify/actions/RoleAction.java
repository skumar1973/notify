package com.c2pi.notify.actions;

import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Roles;
import com.c2pi.notify.service.RoleManager;
import com.opensymphony.xwork2.ActionSupport;



public class RoleAction extends ActionSupport implements SessionAware  {
       
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String desc;
	private String status;
	private String created_by;
	
	ArrayList<Roles> rolelist= new ArrayList<Roles>() ;
	
	Map sessionMap;
	
	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	public void validate() {
		System.out.println("role action validate");
		
//		if ((name == null) || (name.length() == 0)) {
//			this.addFieldError("name", getText("app.name.blank"));
//		}		
//		
		
	}
   
	public String execute() throws Exception {
		
		System.out.println("role action execute");
		System.out.println("name "+name);
		String res="";
		sessionMap.remove("result");
		RoleManager rolemanager = new RoleManager();
		System.out.println("role manager addrole method..");
		res=rolemanager.AddRole(name,desc,status,(String) sessionMap.get("loginid"));
		sessionMap.put("result", res);
		return "admin";
	}
	
	public String getrolelist() {

		RoleManager rm = new RoleManager();
		rolelist=rm.getrolelist();
		
		return "input";
	}
	public void prepare(){
		System.out.println("role action prepare method..");
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public ArrayList<Roles> getRolelist() {
		return rolelist;
	}

	public void setRolelist(ArrayList<Roles> rolelist) {
		this.rolelist = rolelist;
	}

	
}
