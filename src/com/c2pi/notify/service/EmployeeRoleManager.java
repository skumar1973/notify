package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.EmployeeRoleDAO;
import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.EmployeeRole;
import com.c2pi.notify.entity.Role;

public class EmployeeRoleManager {

	private EmployeeRoleDAO empRoleDAO = null;
	private ArrayList<EmployeeRole> empRoleList = null;
	private ArrayList<Employee> empIDList = null;
	private String empRoleDAOResult = "";
	private ArrayList<Role> roleIDList = null;
	
	public String addEmployeeRole(EmployeeRole empRole) {

		empRoleDAO = new EmployeeRoleDAO();
		try {
			System.out.println("Taskdao addtask method...");
			empRoleDAOResult = empRoleDAO.addEmployeeRole(empRole);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empRoleDAOResult;
	}
	/**
	 * Reterive employee IDs.
	 * 
	 * @return empIDList.
	 * @throws SQLException
	 */
	public ArrayList<Employee> getEmpId() throws SQLException {
		empRoleDAO = new EmployeeRoleDAO();
		empIDList = empRoleDAO.getEmpId();
		return empIDList;
	}
	/**
	 * Reterive employee role IDs.
	 * 
	 * @return roleIdList
	 * @throws SQLException
	 */
	public ArrayList<Role> getRoleId() throws SQLException {
		empRoleDAO = new EmployeeRoleDAO();
		roleIDList = empRoleDAO.getRoleId();
		return roleIDList;
	}
	/**
	 * Reterive employee roles.
	 * 
	 * @return emproleList
	 * @throws SQLException
	 */
	public ArrayList<EmployeeRole> getEmpRole() throws SQLException {
		empRoleDAO = new EmployeeRoleDAO();
		empRoleList = empRoleDAO.getEmpRole();
		return empRoleList;
	}
}