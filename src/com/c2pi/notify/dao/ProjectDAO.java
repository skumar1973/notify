package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.c2pi.notify.entity.Project;

public class ProjectDAO {

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt =null;
	String query = "";
	//Project proj = null;
    int res=0;
    public String addProject(String name, String desc, int mgr_id ,String status,
			String created_by) throws SQLException {

		DBConn con = new DBConn();

		try {
			conn = con.getConn();
			stmt = conn.createStatement();
			query = "Insert Into `c2pidb`.`projects` (`name`,`desc`, `mgr_id` ,`status`,"
					+ "`created_by`) "
					+ "values('" + name + "','" + desc + "'," + mgr_id + ",'"
					+ status + "','" + created_by + "')";
			
				
			System.out.println("query:" + query);
			res=stmt.executeUpdate(query);
			System.out.println("res="+res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close() ;
		}

		return (res+"- project added.");
	}
    
    public ArrayList<Project> showProject() throws SQLException {
    	ArrayList<Project> prlist = new ArrayList<Project>();
    	DBConn con = new DBConn();

		try {
			conn = con.getConn();
			String query="SELECT `id`, `name`,`desc`, `mgr_id`,`status`, `created_by` FROM `c2pidb`.`projects`";
			ResultSet rs =null;
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			System.out.println("res == 1 "+query);
			while (rs.next()){
				Project ps = new Project();
				ps.setId(rs.getInt("id"));
				ps.setName(rs.getString("name"));
				ps.setDesc(rs.getString("desc"));
				ps.setMgr_id(rs.getInt("mgr_id"));
				ps.setStatus(rs.getString("status"));
				ps.setCreated_by(rs.getString("created_by"));
				prlist.add(ps);
			}	
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn!= null) conn.close();
		}
    	return prlist;
    }
}
