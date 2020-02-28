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
 * Servlet implementation class AddTodoServlet
 */
public class AddTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
	private TodoService todoService = new TodoService(); 
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("todoAdd.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String description = request.getParameter("todo");
		String category = request.getParameter("category");
		int user_id= (Integer)request.getSession().getAttribute("user_id");
		
		try
		{
			conn = DBManager.getConnection();
			Todo t=new Todo();
			t.setCategory(category);
			t.setDescription(description);
			t.setUser_id(user_id);
			todoService.addTodo(t,conn);
			response.sendRedirect("todo");
			
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}

}
