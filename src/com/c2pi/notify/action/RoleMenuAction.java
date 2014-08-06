package com.c2pi.notify.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.c2pi.notify.entity.Menu;
import com.c2pi.notify.entity.Role;
import com.c2pi.notify.entity.RoleMenu;
import com.c2pi.notify.service.RoleMenuManager;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RoleMenuAction extends ActionSupport implements SessionAware,
		ModelDriven<RoleMenu> {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(RoleMenuAction.class.getName());
	
	private ArrayList<Role> roleIDList = new ArrayList<Role>();
	private ArrayList<Menu> menuIDList = new ArrayList<Menu>();
	private ArrayList<RoleMenu> roleMenuList= new ArrayList<RoleMenu>();
	private RoleMenu roleMenu = new RoleMenu();
	private RoleMenuManager rmManager=new RoleMenuManager();
	private ArrayList<RoleMenu> roleMenuedit= new ArrayList<RoleMenu>();
	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String execute() {
		String result = "";
		
		logger.info("checking valid login.. start..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return ERROR;
		} else {
			logger.info("checking valid login.. complete..");
			RoleMenuManager rmManager=new RoleMenuManager();
			try {
				result=rmManager.savenupdRoleMenu(roleMenu);
			} catch (MySQLIntegrityConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.addActionError(getText(e.getMessage()));
				return "input";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.addActionError(getText(e.getMessage()));
				return "input";
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.addActionError(getText(e.getMessage()));
				return "input";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.addActionError(getText(e.getMessage()));
				return "input";
			}
			sessionMap.put("result",result);
			
		return "admin";
		}
		
	}

	public void validate() {
		System.out.println("Task action validate");
		
	}

	/**
	 * Reterive the list of emp IDs and role IDs.
	 * 
	 * @return
	 */
	public String getList() throws Exception {
		
		RoleMenuManager rmManager=new RoleMenuManager();
		rmManager.getList();
		
		menuIDList=rmManager.getMenuList();
		roleIDList=rmManager.getRoleList();
		roleMenuList=rmManager.getRoleMenuList();
		
		return "input";

	}
	
public String edit() {
		
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		
		rmManager=new RoleMenuManager();
		
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return ERROR;
		} else {
			logger.info("checking valid login.. complete..");

			try {
				roleMenu = rmManager.getRoleMenuById(Integer.parseInt(request
						.getParameter("id")));
				roleMenuedit.add(roleMenu);
			} catch (NumberFormatException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (ClassNotFoundException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (SQLException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			} catch (IOException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
				return ERROR;
			}

			return "input";
		}
	}
public String delete() {
	String res = null;

	logger.info("checking valid login.. start..");
	if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
			|| ((Integer) sessionMap.get("empID")) == 0) {
		logger.info("ERROR: checking valid login..  failed..");
		this.addActionError(getText("app.notloggedin.error"));
		return ERROR;
	} else {

		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		sessionMap.remove("result");
		try {
			res = rmManager.deleteRM(Integer.parseInt(request
					.getParameter("id")));
		} catch (NumberFormatException e) {
			logger.error("ERROR-" + e.getMessage());
			e.printStackTrace();
			this.addActionError(getText("app.error"));
			return ERROR;
		} catch (ClassNotFoundException e) {
			logger.error("ERROR-" + e.getMessage());
			e.printStackTrace();
			this.addActionError(getText("app.error"));
			return ERROR;
		} catch (SQLException e) {
			logger.error("ERROR-" + e.getMessage());
			e.printStackTrace();
			this.addActionError(getText("app.error"));
			return ERROR;
		} catch (IOException e) {
			logger.error("ERROR-" + e.getMessage());
			e.printStackTrace();
			this.addActionError(getText("app.error"));
			return ERROR;
		}
		sessionMap.put("result", res);
		return "admin";
	}
}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public RoleMenu getModel() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String fmtDate = dateFormat.format(date);
		String loginid = (String) sessionMap.get("loginID");
		if(roleMenu.getId() == 0)
		{
		roleMenu.setCreatedDt(fmtDate);
		roleMenu.setCreatedBy(loginid);
		roleMenu.setUpdatedDt(fmtDate);
		roleMenu.setUpdatedBy(loginid);
		}
		else
		{
		
		roleMenu.setUpdatedDt(fmtDate);
		roleMenu.setUpdatedBy(loginid);
		}

		return roleMenu;
	}

	public ArrayList<Role> getRoleIDList() {
		return roleIDList;
	}

	public void setRoleIDList(ArrayList<Role> roleIDList) {
		this.roleIDList = roleIDList;
	}

	public ArrayList<Menu> getMenuIDList() {
		return menuIDList;
	}

	public void setMenuIDList(ArrayList<Menu> menuIDList) {
		this.menuIDList = menuIDList;
	}

	public ArrayList<RoleMenu> getRoleMenuList() {
		return roleMenuList;
	}

	public void setRoleMenuList(ArrayList<RoleMenu> roleMenuList) {
		this.roleMenuList = roleMenuList;
	}

	public RoleMenu getRoleMenu() {
		return roleMenu;
	}

	public void setRoleMenu(RoleMenu roleMenu) {
		this.roleMenu = roleMenu;
	}

	public RoleMenuManager getRmManager() {
		return rmManager;
	}

	public void setRmManager(RoleMenuManager rmManager) {
		this.rmManager = rmManager;
	}

	public ArrayList<RoleMenu> getRoleMenuedit() {
		return roleMenuedit;
	}

	public void setRoleMenuedit(ArrayList<RoleMenu> roleMenuedit) {
		this.roleMenuedit = roleMenuedit;
	}

	
}
