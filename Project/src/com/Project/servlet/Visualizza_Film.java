package com.Project.servlet;

import com.google.gson.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.Project.beans.*;
import com.Project.beans.JsonConverter;

/**
 * Servlet implementation class Visualizza_Film
 */

@WebServlet("/Visualizza_Film")
public class Visualizza_Film extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{   	
    	response.setContentType("application/json;charset=UTF-8");
    	try {
    		/*JsonConverter converter = new JsonConverter();
    		ServletOutputStream out = response.getOutputStream();
    		String output = converter.convertToJson(db.filmList());
    		out.println(output);*/
    	    String json = new Gson().toJson(Database.filmList());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
    	}
        	catch(ClassNotFoundException e)	{
			e.printStackTrace();
			}
    		catch(SQLException e)
			{
			throw new RuntimeException("cannot connect the database!",e );
			}
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String url="/show_films.jsp";
			String opzione="";
			response.setContentType("text/html");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
				String user="root";
				String pwd="root";
				Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
				Statement statement=null;
				List<Film> f= new ArrayList<>();
				statement = connection.createStatement();
				String sql="select * from films";
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()) {
					Film film = new Film();
					film.setId(rs.getString("id"));
					film.setTitolo(rs.getString("titolo"));
					film.setData(rs.getString("giorno"));
					film.setOra_Init(rs.getString("ora_init"));
					film.setOra_Fine(rs.getString("ora_fine"));
					film.setSala(rs.getString("sala"));
					film.setFileName(rs.getString("filename"));
					film.setPath(rs.getString("file_path"));
					f.add(film);
				}
				opzione=request.getParameter("opzione");
				if(opzione.equalsIgnoreCase("Modifica Film")){
					url="/modifica.jsp";
				}
				else if(opzione.equalsIgnoreCase("Elimina Film")){
					url="/elimina.jsp";
				}
				request.setAttribute("film", f);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		        dispatcher.forward(request, response);    
			}
			catch(ClassNotFoundException e)	{
				e.printStackTrace();
				}
			}
		catch(SQLException e)
		{
			throw new RuntimeException("cannot connect the database!",e );
		}
	}	
}
