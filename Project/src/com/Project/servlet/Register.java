package com.Project.servlet;
import java.io.*;
import java.sql.*;
import java.text.ParseException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.Project.beans.Controllo;
import com.Project.beans.Database;
import com.Project.beans.Utente;


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
		Controllo c= new Controllo();
		Utente u= new Utente();
		u.setUserName(request.getParameter("username"));
		u.setPassword(request.getParameter("password"));
		u.setRole(request.getParameter("role"));
		c.controlUser(u);
		c.controlAdmin(u);
		if(c.get_map().isEmpty()) {
				Database.addUser(u);
				response.sendRedirect("index.jsp");
		}
		else
		{
			request.setAttribute("errore",c.get_map());
			System.out.println(c.get_map());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
	        dispatcher.forward(request, response);
		}
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
