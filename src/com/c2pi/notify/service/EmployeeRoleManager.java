package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.EmployeeRoleDAO;
import com.c2pi.notify.dao.TaskDAO;
import com.c2pi.notify.entity.EmployeeRole;
import com.c2pi.notify.entity.Employees;
import com.c2pi.notify.entity.Roles;

public class EmployeeRoleManager {

	private EmployeeRoleDAO empRoleDAO;
	
	public String addEmployeeRole(int emp_id, int role_id, String created_by){
		String employeeRoleDAORes="";
		EmployeeRoleDAO employeeRoleDAO= new EmployeeRoleDAO();
		try {			
			System.out.println("Taskdao addtask method...");
			employeeRoleDAORes=employeeRoleDAO.addEmployeeRole(emp_id, role_id, created_by);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeeRoleDAORes;
	}

	/**
	 * Reterive employee IDs.
	 * @return empIDList.
	 */
	public ArrayList<Employees> getEmpId() {
		ArrayList<Employees> empIDList = null;
		empRoleDAO = new EmployeeRoleDAO();
		empIDList = empRoleDAO.getEmpId();
		return empIDList;
	}
	
	/**
	 * Reterive employee role IDs.
	 * @return roleIdList
	 */
	public ArrayList<Roles> getRoleId(){
		ArrayList<Roles> roleIDList = null;
		empRoleDAO = new EmployeeRoleDAO();
		roleIDList = empRoleDAO.getRoleId();
		return roleIDList;
		
	}
	
	/**
	 * Reterive employee roles.
	 * @return emproleList
	 */
	public ArrayList<EmployeeRole> getEmpRole(){
		ArrayList<EmployeeRole> emproleList = null;
		empRoleDAO = new EmployeeRoleDAO();
		emproleList = empRoleDAO.getEmpRole();
		return emproleList;
		
	}
	
}
