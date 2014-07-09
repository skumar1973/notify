package com.c2pi.notify.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoffAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public String execute() throws Exception {
		sessionMap.remove("loginID");
		sessionMap.remove("role");
		sessionMap.remove("empID");
		sessionMap.remove("result");
		return SUCCESS;
	}
}
