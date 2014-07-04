package com.c2pi.notify.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.dao.EmployeeDAO;
import com.c2pi.notify.entity.Employees;

public class EmployeesManager {

	public String addemployee(String firstname, String lastname, String email, String design, String status, String created_by) throws SQLException{
		EmployeeDAO edao= new EmployeeDAO();
		String res="";
		res=edao.addEmployee(firstname, lastname, email, design, status, created_by);
		return res;
	}
	
	public ArrayList<Employees> getemplist(){
		ArrayList<Employees> emplist=null;
		EmployeeDAO edao = new EmployeeDAO();
		emplist=edao.getemplist();
		return emplist;
	}
}
