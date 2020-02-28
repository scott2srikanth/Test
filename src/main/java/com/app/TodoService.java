package com.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TodoService {
	private static List<Todo> todos = new ArrayList<Todo>();

	public List<Todo> retrieveTodos(int user_id,Connection conn) {
		todos.clear();
		try {
			 
			if (conn != null && !conn.isClosed()) {
				ResultSet rs = null;

				Statement stmt = conn.createStatement();
				String sql = "select * from todos where user_id=" + user_id;
				System.out.println(sql);
				rs = stmt.executeQuery(sql);

				if (rs != null) {
					
					while (rs.next()) {
						Todo t=new Todo();
						t.setTodo_id(rs.getInt("todo_id"));
						t.setCategory(rs.getString("category"));
						t.setDescription(rs.getString("description"));
			            todos.add(t);
			        }  
					
				}
					
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(todos.size());
		return todos;
	}
	
	public void addTodo(Todo todo, Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {

				Statement stmt = conn.createStatement();
				String sql = "insert into todos(category,description,user_id) values('"+todo.getCategory()+"','"+todo.getDescription()+"','"+todo.getUser_id()+"')";
				System.out.println(sql);
				int insert = stmt.executeUpdate(sql);
				if(insert==1)
				{
					todos.add(todo);
				}	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
