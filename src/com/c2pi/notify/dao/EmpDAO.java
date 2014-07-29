package com.c2pi.notify.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.c2pi.notify.common.DBConn;
import com.c2pi.notify.entity.Employee;

/**
 * @author Shailendrak
 * 
 */
public class EmpDAO {

	private DBConn conn = null;
	private Connection con = null;
	private String query = null;
	private PreparedStatement pstmt = null;
	private int ret = 0;
	private ResultSet rs = null;

	/**
	 * @param employee
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public String saveOrUpdateEmp(Employee employee) throws SQLException,
			IOException, ClassNotFoundException {
		conn = new DBConn();
		con = conn.getConn();
		query = "update c2pidb.Employees set first_name=?, last_name=?, email=?, designation=?, status=?, created_dt=?, created_by=?, updated_dt=?, updated_by=?  where id = ?";
		pstmt = con.prepareStatement(query);

		pstmt.setString(1, employee.getFirstName());
		pstmt.setString(2, employee.getLastName());
		pstmt.setString(3, employee.getEmail());
		pstmt.setString(4, employee.getDesignation());
		pstmt.setString(5, employee.getStatus());
		pstmt.setString(6, employee.getCreatedDt());
		pstmt.setString(7, employee.getCreatedBy());
		pstmt.setString(8, employee.getUpdatedDt());
		pstmt.setString(9, employee.getUpdatedBy());
		pstmt.setInt(10, employee.getId());

		System.out.println(pstmt);

		ret = pstmt.executeUpdate();
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
		return ret + " rows saved";
	}

	/**
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public List<Employee> listEmp() throws ClassNotFoundException,
			SQLException, IOException {
		Employee employee = new Employee();
		List<Employee> emplist = new ArrayList<Employee>();
		conn = new DBConn();
		con = conn.getConn();
		query = "select id,first_name,last_name,email,designation,status,created_dt,created_by,updated_dt,updated_by from c2pidb.Employees";
		pstmt = con.prepareStatement(query);
		System.out.println(pstmt);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			employee = new Employee();
			employee.setId(rs.getInt("id"));
			employee.setFirstName(rs.getString("first_Name"));
			employee.setLastName(rs.getString("last_Name"));
			employee.setEmail(rs.getString("email"));
			employee.setDesignation(rs.getString("designation"));
			employee.setStatus(rs.getString("status"));
			employee.setCreatedDt(rs.getString("created_Dt"));
			employee.setCreatedBy(rs.getString("created_By"));
			employee.setUpdatedDt(rs.getString("updated_Dt"));
			employee.setUpdatedBy(rs.getString("updated_By"));
			emplist.add(employee);
		}
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
		return emplist;
	}

	/**
	 * @param empID
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Employee listEmpByID(int empID) throws ClassNotFoundException,
			SQLException, IOException {
		Employee employee = null;
		conn = new DBConn();
		con = conn.getConn();
		query = "select id,first_name,last_name,email,designation,status,created_dt,created_by,updated_dt,updated_by from c2pidb.Employees WHERE id=?";
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, empID);
		System.out.println(pstmt);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			employee = new Employee();
			employee.setId(rs.getInt("id"));
			employee.setFirstName(rs.getString("first_Name"));
			employee.setLastName(rs.getString("last_Name"));
			employee.setEmail(rs.getString("email"));
			employee.setDesignation(rs.getString("designation"));
			employee.setStatus(rs.getString("status"));
			employee.setCreatedDt(rs.getString("created_Dt"));
			employee.setCreatedBy(rs.getString("created_By"));
			employee.setUpdatedDt(rs.getString("updated_Dt"));
			employee.setUpdatedBy(rs.getString("updated_By"));

		}
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
		return employee;
	}

	/**
	 * @param empID
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public String deleteEmp(int empID) throws SQLException, IOException,
			ClassNotFoundException {
		conn = new DBConn();
		con = conn.getConn();
		query = "Delete from c2pidb.Employees where id = ?";
		pstmt = con.prepareStatement(query);

		pstmt.setInt(1, empID);

		System.out.println(pstmt);

		ret = pstmt.executeUpdate();
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
		return ret + " rows deleted";
	}
}
