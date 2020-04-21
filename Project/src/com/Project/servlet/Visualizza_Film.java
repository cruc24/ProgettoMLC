package com.Project.servlet;

import com.google.gson.*;
import java.io.IOException;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.Project.beans.*;
/**
 * Servlet implementation class Visualizza_Film
 */

@WebServlet("/Visualizza_Film")
public class Visualizza_Film extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{   	
    	response.setContentType("application/json;charset=UTF-8");
    	    String json;
			try {
				json = new Gson().toJson(Database.filmList());
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}
