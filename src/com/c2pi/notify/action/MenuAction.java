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
import com.c2pi.notify.service.MenuManager;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Shailendrak
 * 
 */
public class MenuAction extends ActionSupport implements ModelDriven<Menu>,
		SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Menu> mnList = new ArrayList<Menu>();
	private MenuManager mnMgr = new MenuManager();
	private Menu menus = new Menu();

	Logger logger = Logger.getLogger(MenuAction.class.getName());

	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		/*
		 * System.out.println("menu action execute method..");
		 * //System.out.println("name " + name); MenuManager mm = new
		 * MenuManager(); String res = ""; sessionMap.remove("result");
		 * System.out.println("MenuManager addMenu method.."); res =
		 * mm.addMenu(name, parentID, desc, status, target, (String)
		 * sessionMap.get("loginID")); sessionMap.put("result", res);
		 * 
		 * return "admin";
		 */

		String result = "";

		logger.info("checking valid login.. start..");
		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return ERROR;
		} else {
			logger.info("checking valid login.. complete..");
			MenuManager mm = new MenuManager();
			try {
				result = mm.addMenu(menus);
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
			sessionMap.put("result", result);

			return "admin";
		}
	}

	/*
	 * 
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {
		
		logger.debug("menu action validate method..");

		if ((menus.getName() == null)
				|| (menus.getName().length() == 0)) {
			this.addFieldError("Name",
					getText("app.menu.Name.blank"));
			mnMgr = new MenuManager();
			try {
				mnList = mnMgr.getMenuList();
			} catch (SQLException e) {
				logger.error("ERROR:" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));

			} catch (ClassNotFoundException e) {
				logger.error("ERROR:" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
			} catch (IOException e) {
				logger.error("ERROR:" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText("app.error"));
			}
		}
		
	}

	public Menu getModel() {

		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String fmtDate = dateFormat.format(date);
		String loginid = (String) sessionMap.get("loginID");
		// save
		if ((request.getParameter("id") == null)
				|| (Integer.parseInt(request.getParameter("id")) == 0)) {
			menus.setCreatedDt(fmtDate);
			menus.setCreatedBy(loginid);
			menus.setUpdatedDt(fmtDate);
			menus.setUpdatedBy(loginid);
		} else {
			// update
			menus.setUpdatedDt(fmtDate);
			menus.setUpdatedBy(loginid);
		}
		return menus;
	}

	/**
	 * @return
	 */
	public String getMenuList() {
		MenuManager em = new MenuManager();
		try {
			mnList = em.getMenuList();
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

		return "input";
	}

	public String edit() {
		// empMgr = new EmployeeManager();

		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		if ((sessionMap.isEmpty()) || (sessionMap.get("empID") == null)
				|| ((Integer) sessionMap.get("empID")) == 0) {
			logger.info("ERROR: checking valid login..  failed..");
			this.addActionError(getText("app.notloggedin.error"));
			return "login";
		} else {
			logger.info("checking valid login.. complete..");

			try {
				menus = mnMgr.getMenuById(Integer.parseInt(request
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
			return "login";
		} else {

			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			sessionMap.remove("result");
			try {
				res = mnMgr.deleteMenu(Integer.parseInt(request
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
				this.addActionError(getText(e.getMessage()));
				return ERROR;
			} catch (IOException e) {
				logger.error("ERROR-" + e.getMessage());
				e.printStackTrace();
				this.addActionError(getText(e.getMessage()));
				return ERROR;
			}
			sessionMap.put("result", res);
			return "admin";
		}
	}
	public ArrayList<Menu> getMnList() {
		return mnList;
	}

	public void setMnList(ArrayList<Menu> mnList) {
		this.mnList = mnList;
	}

	public MenuManager getMnMgr() {
		return mnMgr;
	}

	public void setMnMgr(MenuManager mnMgr) {
		this.mnMgr = mnMgr;
	}

	public Menu getMenus() {
		return menus;
	}

	public void setMenus(Menu menus) {
		this.menus = menus;
	}

}
