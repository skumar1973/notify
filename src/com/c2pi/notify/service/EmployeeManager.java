package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.EmployeeDAO;
import com.c2pi.notify.entity.Employee;

public class EmployeeManager {

	private EmployeeDAO edao = null;
//	public String addEmployee(String firstName, String lastName, String email,
//			String design, String status, String createdBy) throws SQLException {
	public String addEmployee(Employee employee) throws SQLException {	
		String queryResult = "";
		edao = new EmployeeDAO();
		queryResult = edao.addEmployee(employee);
		return queryResult;
	}

	public ArrayList<Employee> getEmpList() throws SQLException {
		ArrayList<Employee> empList = null;
		edao = new EmployeeDAO();
		empList = edao.getEmpList();
		return empList;
	}
}
