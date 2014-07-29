/**
 * 
 */
package com.c2pi.notify.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Employee;
import com.c2pi.notify.entity.EmployeeRole;
import com.c2pi.notify.entity.Role;
import com.c2pi.notify.service.EmployeeRoleManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Rajneeshk
 * 
 */
public class EmployeeRoleAction extends ActionSupport implements SessionAware,
		ModelDriven<EmployeeRole> {

	private static final long serialVersionUID = 1L;

	ArrayList<Employee> empIDList = null;
	ArrayList<Role> roleIDList = null;
	ArrayList<EmployeeRole> empRoleList = null;
	EmployeeRole empRole = new EmployeeRole();

	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		String result = "";
		EmployeeRoleManager empRoleMgr = null;
		empRoleMgr = new EmployeeRoleManager();
		if (sessionMap != null)
			sessionMap.remove("result");
		System.out.println("Employee role action execute.");
		result = empRoleMgr.addEmployeeRole(empRole);
		System.out.println("result:" + result);
		sessionMap.put("result", result);
		return "admin";
	}

	public void validate() {
		System.out.println("Task action validate");
		if ((empRole.getEmpID() == 0)) {
			this.addFieldError("empID", getText("app.employeeRole.empID.blank"));

			EmployeeRoleManager empRoleMgr = null;
			empRoleMgr = new EmployeeRoleManager();
			System.out
					.println("EmployeeRoleManager get list of employee IDs method ..");
			try {
				empIDList = empRoleMgr.getEmpId();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("TaskManager get list of role IDs method ...");
			try {
				roleIDList = empRoleMgr.getRoleId();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("TaskManager get list of Emp role method ...");
			try {
				empRoleList = empRoleMgr.getEmpRole();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Reterive the list of emp IDs and role IDs.
	 * 
	 * @return
	 */
	public String getEmpNRole() throws Exception {
		EmployeeRoleManager empRoleMgr = null;
		empRoleMgr = new EmployeeRoleManager();
		System.out
				.println("EmployeeRoleManager get list of employee IDs method ..");
		empIDList = empRoleMgr.getEmpId();
		System.out.println("TaskManager get list of role IDs method ...");
		roleIDList = empRoleMgr.getRoleId();
		System.out.println("TaskManager get list of Emp role method ...");
		empRoleList = empRoleMgr.getEmpRole();

		return "input";

	}

	/**
	 * @return the empIDList
	 */
	public ArrayList<Employee> getEmpIDList() {
		return empIDList;
	}

	/**
	 * @param empIDList
	 *            the empIDList to set
	 */
	public void setEmpIDList(ArrayList<Employee> empIDList) {
		this.empIDList = empIDList;
	}

	/**
	 * @return the roleIDList
	 */
	public ArrayList<Role> getRoleIDList() {
		return roleIDList;
	}

	/**
	 * @param roleIDList
	 *            the roleIDList to set
	 */
	public void setRoleIDList(ArrayList<Role> roleIDList) {
		this.roleIDList = roleIDList;
	}

	public ArrayList<EmployeeRole> getEmpRoleList() {
		return empRoleList;
	}

	public void setEmpRoleList(ArrayList<EmployeeRole> empRoleList) {
		this.empRoleList = empRoleList;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public EmployeeRole getModel() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String fmtDate = dateFormat.format(date);
		String loginid = (String) sessionMap.get("loginID");
		empRole.setUpdatedDt(fmtDate);
		empRole.setCreatedDt(fmtDate);
		empRole.setCreatedBy(loginid);
		empRole.setUpdatedBy(loginid);

		return empRole;
	}

}
