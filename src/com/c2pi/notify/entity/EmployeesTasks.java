package com.c2pi.notify.entity;

public class EmployeesTasks {

	private int id;
	private int emp_id;
	private int task_id;
	private String created_dt;
	private String created_by;
	private String updated_dt;
	private String updated_by;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(String created_dt) {
		this.created_dt = created_dt;
	}
	public String getCreated_by() {
		return created_by;
	}
	public String getUpdated_dt() {
		return updated_dt;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	public void setUpdated_dt(String updated_dt) {
		this.updated_dt = updated_dt;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	
	
}
