package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.EmployeeRoleDAO;
import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.EmployeeRole;
import com.c2pi.notify.entity.Role;

/**
 * @author Shailendrak
 * 
 */
public class EmployeeRoleManager {

	private EmployeeRoleDAO empRoleDAO = null;
	private EmployeeRole empRole = null;
	private ArrayList<EmployeeRole> empRoleList = null;
	private ArrayList<Employee> empIDList = null;
	private String empRoleDAOResult = "";
	private ArrayList<Role> roleIDList = null;

	public String addEmployeeRole(EmployeeRole empRole) throws SQLException,
			ClassNotFoundException, IOException {

		empRoleDAO = new EmployeeRoleDAO();

		empRoleDAOResult = empRoleDAO.addEmployeeRole(empRole);

		return empRoleDAOResult;
	}

	public EmployeeRole getEmployeeById(int empID) throws SQLException,
			ClassNotFoundException, IOException {
		System.out.println("Employeerole get list method..");
		empRoleDAO = new EmployeeRoleDAO();
		empRole = empRoleDAO.getEmployeeById(empID);
		System.out.println("EmpRoleList" + empRoleList);
		return empRole;
	}

	public String deleteEmpRole(int emproleID) throws SQLException,
			ClassNotFoundException, IOException {
		System.out.println("EmployeeRoleManager deleteEmpRole method..");
		empRoleDAO = new EmployeeRoleDAO();
		empRoleDAOResult = empRoleDAO.deleteEmpRole(emproleID);
		System.out.println("empRoleDAOres" + empRoleDAOResult);

		return empRoleDAOResult;
	}

	/**
	 * Reterive employee IDs.
	 * 
	 * @return empIDList.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<Employee> getEmpId() throws SQLException,
			ClassNotFoundException, IOException {
		empRoleDAO = new EmployeeRoleDAO();
		empIDList = empRoleDAO.getEmpId();
		return empIDList;
	}

	/**
	 * Reterive employee role IDs.
	 * 
	 * @return roleIdList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<Role> getRoleId() throws SQLException,
			ClassNotFoundException, IOException {
		empRoleDAO = new EmployeeRoleDAO();
		roleIDList = empRoleDAO.getRoleId();
		return roleIDList;
	}

	/**
	 * Reterive employee roles.
	 * 
	 * @return emproleList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<EmployeeRole> getEmpRole() throws SQLException,
			ClassNotFoundException, IOException {
		empRoleDAO = new EmployeeRoleDAO();
		empRoleList = empRoleDAO.getEmpRole();
		return empRoleList;
	}
}