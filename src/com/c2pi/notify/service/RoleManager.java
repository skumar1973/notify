package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.RolesDAO;
import com.c2pi.notify.entity.Roles;

public class RoleManager {

	private String name;
	private String desc;
	private String status;
	private String created_by;

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
	
	
	public String AddRole(String name,String desc,String status,String created_by){
		String roledaores="";
		RolesDAO roledao= new RolesDAO();
		try {			
			System.out.println("role dao addrole method...");
			roledaores=roledao.addRole(name, desc, status, created_by);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roledaores;
	}

	public ArrayList<Roles> getrolelist(){
		ArrayList<Roles> rolelist=null;
		RolesDAO rdao = new RolesDAO();
		rolelist=rdao.getrolelist();
		return rolelist;
	}
	
}
