package com.Project.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Project.beans.Database;
import com.google.gson.Gson;

/**
 * Servlet implementation class Gestione_Sale
 */
@WebServlet("/Gestione_Sale")
public class Gestione_Sale extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("application/json;charset=UTF-8");
			String json;
				try{
					String sala= (String)request.getParameter("sala");
					json = new Gson().toJson(Database.filmSala(sala));		          
		            response.getWriter().write(json);
					} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}	   		
	}  
}
