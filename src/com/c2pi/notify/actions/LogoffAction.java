package com.c2pi.notify.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoffAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map sessionMap;

	@Override
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public String execute() throws Exception {
		sessionMap.remove("loginid");
		sessionMap.remove("role");
		sessionMap.remove("emp_id");
		sessionMap.remove("result");
		return SUCCESS;
	}
}
