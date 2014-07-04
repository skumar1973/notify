package com.c2pi.notify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.c2pi.notify.entity.Roles;

public class RolesDAO {


	public String addRole(String name, String desc, String status,
			String created_by) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "Insert Into `c2pidb`.`Roles` (`name`,`desc`,`status`,`created_by`) values (?,?,?,?)";
		int res=0;
		DBConn con = new DBConn();

		try {
			conn = con.getConn();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			pstmt.setString(3, status);
			pstmt.setString(4, created_by);
				
			System.out.println("query:" + query);
			res=pstmt.executeUpdate();
			System.out.println("res="+res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close() ;
		}

		return (res+"- role added.");
	}

	public ArrayList<Roles> getrolelist(){
		
		ArrayList<Roles> rolelist= new ArrayList<Roles>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		DBConn conn = new DBConn();
		con=conn.getConn();
		String query="SELECT id, name, `desc`, status, created_dt, created_by, updated_dt, updated_by FROM `c2pidb`.`roles`";
		try {
			pstmt=con.prepareStatement(query);
			rs=pstmt.executeQuery();
			System.out.println("query"+query);
			while (rs.next()){
				Roles role = new Roles();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setDesc(rs.getString("desc"));
				role.setStatus(rs.getString("status"));
				role.setCreated_dt(rs.getString("created_dt"));
				role.setCreated_by(rs.getString("created_by"));
				role.setUpdated_dt(rs.getString("updated_dt"));
				role.setUpdated_by(rs.getString("updated_by"));
				rolelist.add(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rolelist;
	}
}
