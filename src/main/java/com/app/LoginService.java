package com.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.app.util.DBManager;

public class LoginService {
	
	public boolean isUserValid(String username, String password, Connection conn,User user) {
		
		try
		{
			conn = DBManager.getConnection();
			ResultSet rs=null;
		
			if (conn != null && !conn.isClosed()) {

				Statement stmt = conn.createStatement();
				String sql = "select * from login where username='" + username + "' and password='" + password + "'";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);

				if (rs != null && rs.next()) {
					user.setUser_id(rs.getInt("user_id"));
					return true;	
				}

			}
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

}
