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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Access
 */
@WebServlet("/Access")
public class Access extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
				String user="root";
				String pwd="root";
				Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd);
				PreparedStatement statement;
				Utente utente = new Utente();
				utente.setUserName(request.getParameter("username"));
				utente.setPassword(request.getParameter("password"));
				Controllo c= new Controllo();
				if(Controllo.check_User_Null(utente))
				{
				String sql="select username,password,roles from users where username=? && password=? ";
				statement=connection.prepareStatement(sql);
				statement.setString(1, utente.getUserName());
				statement.setString(2, utente.getPassword());
				ResultSet rs= statement.executeQuery();
				String messaggio="Login/Password errati o non presenti.";
				String username= utente.getUserName();
				if(rs.next())
					{
						utente.setRole(rs.getString("roles"));
						response.sendRedirect("Home.jsp");
				    	HttpSession session = request.getSession(true);
				    	session.setAttribute("username", username);
				    	session.setAttribute("role", rs.getString("roles"));
				    	session.setMaxInactiveInterval(30*60);
					}
				else
					{	
					c.get_map().put("error-access", messaggio);
					request.setAttribute("errore", c.get_map());
				    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			        dispatcher.forward(request, response);   
					}
				}
				else
				{
					c.get_map().put("utente-insert", "Username/Password non possono essere null.");
					request.setAttribute("errore", c.get_map());
				    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			        dispatcher.forward(request, response);   
				}
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
