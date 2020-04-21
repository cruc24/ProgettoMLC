package com.Project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Project.beans.*;

/**
 * Servlet implementation class Modifica_film
 */
@WebServlet("/Modifica_Film")
public class Modifica_Film extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String jsp_url="";
		try {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			try {
				Controllo c= new Controllo();
				Film film = new Film();
				film.setId(request.getParameter("id"));
				film.setTitolo(request.getParameter("titolo"));
				film.setData(request.getParameter("giorno"));
				film.setOra_Init(request.getParameter("ora_init"));
				film.setOra_Fine(request.getParameter("ora_fine"));
				film.setSala(request.getParameter("sala"));
				c.controlInsert(film);
				if(c.get_map().isEmpty()){
					c.ControlData(film);
					c.ControlOra(film);
					c.inCorso(film, "aggiungi");
					c.Occupato(film,"modifica");
					if(c.get_map().isEmpty())
					{
						Database.modificaFilm(film);
						response.sendRedirect("admin.jsp");
					}
				else
				{
					request.setAttribute("errore",c.get_map());
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
			        dispatcher.forward(request, response);
				}
			}
			else {
				request.setAttribute("errore",c.get_map());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
		        dispatcher.forward(request, response);
				}
			}
			catch(ClassNotFoundException e)	{
				out.println("class not found.");
				} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		catch(SQLException e)
		{
			throw new RuntimeException("cannot connect the database!",e );
		}
	}
}
