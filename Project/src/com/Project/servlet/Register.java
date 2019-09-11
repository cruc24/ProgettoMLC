package com.Project.servlet;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Register() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.html");
		try {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter(); 
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
				String user="root";
				String pwd="root";
				Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
				PreparedStatement statement;
				String sql="insert into users (username,password) values(?,?)";
				statement=connection.prepareStatement(sql);
				statement.setString(1, request.getParameter("username"));
				statement.setString(2, request.getParameter("password"));
				statement.executeUpdate();
				}
			catch(ClassNotFoundException e)	{
				out.println("class not found.");
				}
			}
		catch(SQLException e)
		{
			throw new RuntimeException("cannot connect the database!",e );
		}
	}

}
