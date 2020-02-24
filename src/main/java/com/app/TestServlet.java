package com.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
       
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
           con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/todo", "postgres", "");
       }
       catch (Exception e)
		{
           e.printStackTrace();
        }
	   if(con!=null) {
		   response.getWriter().append("Connected");
	   }
	   else {
		   response.getWriter().append("Not Connected");
	   }
	   
	}

   @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
