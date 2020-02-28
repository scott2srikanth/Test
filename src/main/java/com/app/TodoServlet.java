package com.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.util.DBManager;

/**
 * Servlet implementation class TodoServlet
 */
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
	TodoService todoService=new TodoService();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user_id= (Integer)request.getSession().getAttribute("user_id");

		
		try
		{
			conn = DBManager.getConnection();
			
			request.setAttribute("todos", todoService.retrieveTodos(user_id,conn));
			request.getRequestDispatcher("todoList.jsp").forward(request, response);

		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
