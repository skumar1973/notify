/**
 * 
 */
package com.c2pi.notify.actions;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.EmployeeRole;
import com.c2pi.notify.entity.Employees;
import com.c2pi.notify.entity.Roles;
import com.c2pi.notify.service.EmployeeRoleManager;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Rajneeshk
 * 
 */
public class EmployeeRoleAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private int emp_id;
	private int role_id;

	private ArrayList<Employees> empIDList = new ArrayList<Employees>();
	private ArrayList<Roles> roleIDList = new ArrayList<Roles>();
	private ArrayList<EmployeeRole> emproleList = new ArrayList<EmployeeRole>();
	private EmployeeRoleManager empRoleMgr = new EmployeeRoleManager();

	Map sessionMap;

	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		String res = "";
		empRoleMgr = new EmployeeRoleManager();
		if (sessionMap != null)
			sessionMap.remove("result");
		System.out.println("Employee role action execute.");

		res = empRoleMgr.addEmployeeRole(emp_id, role_id,
				(String) sessionMap.get("loginid"));

		System.out.println("res:" + res);
		sessionMap.put("result", res);

		return "admin";
	}

	public void validate() {
		System.out.println("Task action validate");
	}

	/**
	 * Reterive the list of emp IDs and role IDs.
	 * 
	 * @return
	 */
	public String getEmpNRole() throws Exception {
		empRoleMgr = new EmployeeRoleManager();
		System.out
				.println("EmployeeRoleManager get list of employee IDs method ..");
		empIDList = empRoleMgr.getEmpId();
		System.out.println("TaskManager get list of role IDs method ...");
		roleIDList = empRoleMgr.getRoleId();
		System.out.println("TaskManager get list of Emp role method ...");
		emproleList = empRoleMgr.getEmpRole();

		return "input";

	}

	/**
	 * @return the emp_id
	 */
	public int getEmp_id() {
		return emp_id;
	}

	/**
	 * @param emp_id
	 *            the emp_id to set
	 */
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	/**
	 * @return the role_id
	 */
	public int getRole_id() {
		return role_id;
	}

	/**
	 * @param role_id
	 *            the role_id to set
	 */
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	/**
	 * @return the empIDList
	 */
	public ArrayList<Employees> getEmpIDList() {
		return empIDList;
	}

	/**
	 * @param empIDList
	 *            the empIDList to set
	 */
	public void setEmpIDList(ArrayList<Employees> empIDList) {
		this.empIDList = empIDList;
	}

	/**
	 * @return the roleIDList
	 */
	public ArrayList<Roles> getRoleIDList() {
		return roleIDList;
	}

	/**
	 * @param roleIDList
	 *            the roleIDList to set
	 */
	public void setRoleIDList(ArrayList<Roles> roleIDList) {
		this.roleIDList = roleIDList;
	}

	public ArrayList<EmployeeRole> getEmproleList() {
		return emproleList;
	}

	public void setEmproleList(ArrayList<EmployeeRole> emproleList) {
		this.emproleList = emproleList;
	}

}
