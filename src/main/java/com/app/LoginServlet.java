package com.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.util.DBManager;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	User user=new User();
	LoginService service=new LoginService();
       
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   request.getRequestDispatcher("index.jsp").forward(request, response);
	}

   @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String name = request.getParameter("name");
		String password = request.getParameter("password");
		conn = DBManager.getConnection();
		if(service.isUserValid(name,password,conn,user))
		{
			request.getSession().setAttribute("user_id", user.getUser_id());
			response.sendRedirect("todo");

		}
		else
		{
			request.setAttribute("error", "Invalid credientials");
			request.getRequestDispatcher("index.jsp").forward(request, response);

		}

   
   }

}
