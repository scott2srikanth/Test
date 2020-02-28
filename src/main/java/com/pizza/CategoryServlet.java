package com.pizza;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.Todo;
import com.app.util.DBManager;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con=null;   
    List<Product> l=new ArrayList<Product>();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		con = DBManager.getConnection();
		l.clear();
		try {
			 
			if (con != null && !con.isClosed()) {
				ResultSet rs = null;

				Statement stmt = con.createStatement();
				String sql = "select type from product";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);

				if (rs != null) {
					
					while (rs.next()) {
						Product p=new Product();
						p.setType(rs.getString("type"));
						l.add(p);
			        }  
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Test size : "+l.size());
		request.setAttribute("products", l);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
