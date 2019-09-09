package com.Project.servlet;
import com.Project.beans.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Access
 */
@WebServlet("/Access")
public class Access extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Access() {
        super();
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
		try {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			String jsp_url="";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
				String user="root";
				String pwd="root";
				Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
				PreparedStatement statement;
				
				Utente utente = new Utente();
				utente.setUserName(request.getParameter("username"));
				utente.setPassword(request.getParameter("password"));
				
				String sql="select * from users where username=? && password=? ";
				statement=connection.prepareStatement(sql);
				statement.setString(1, utente.getUserName());
				statement.setString(2, utente.getPassword());
				
				ResultSet rs= statement.executeQuery();
				if(rs.next())
					{
						request.setAttribute("utente", utente);
				        jsp_url= "/home.jsp";
					}
				else
					{
						response.sendRedirect("login.html");
					}
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp_url);
		        dispatcher.forward(request, response);    
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