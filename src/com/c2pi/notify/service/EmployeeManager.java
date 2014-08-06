package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.EmployeeDAO;
import com.c2pi.notify.entity.Employee;

/**
 * @author Shailendrak
 *
 */
public class EmployeeManager {

	private Employee emp = null;
	private EmployeeDAO edao = null;
	ArrayList<Employee> empList = null;

	/**
	 * @param employee
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public String addEmployee(Employee employee) throws SQLException, ClassNotFoundException, IOException {	
		String queryResult = "";
		edao = new EmployeeDAO();
		
		queryResult = edao.addEmployee(employee);
		return queryResult;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public ArrayList<Employee> getEmpList() throws SQLException, ClassNotFoundException, IOException {
		ArrayList<Employee> empList = null;
		edao = new EmployeeDAO();
		empList = edao.getEmpList();
		return empList;
	}

	public Employee getEmployeeById(int empID) throws SQLException,
			ClassNotFoundException, IOException {
		System.out.println("Employee get list method..");
		edao = new EmployeeDAO();
		emp = edao.getEmployeeById(empID);
		System.out.println("EmpList" + empList);
		return emp;

	}
}
