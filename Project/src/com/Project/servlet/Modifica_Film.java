package com.Project.servlet;

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

import com.Project.beans.Film;
import com.Project.beans.Utente;

/**
 * Servlet implementation class Modifica_film
 */
@WebServlet("/Modifica_Film")
public class Modifica_Film extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modifica_Film() {
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
		response.sendRedirect("home.jsp");
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
				
				Film film = new Film();
				film.setId(request.getParameter("id"));
				film.setTitolo(request.getParameter("titolo"));
				film.setData(request.getParameter("giorno"));
				film.setOra_Init(request.getParameter("ora_init"));
				film.setOra_Fine(request.getParameter("ora_fine"));
				film.setDurata(request.getParameter("durata"));
				film.setSala(request.getParameter("sala"));
				
				out.println(request.getParameter("id"));
				out.println(request.getParameter("titolo"));
				out.println(request.getParameter("giorno"));
				out.println(request.getParameter("ora_init"));
				out.println(request.getParameter("ora_fine"));
				out.println(request.getParameter("durata"));
				out.println(request.getParameter("sala_cinema"));
				
				String sql="update films set titolo=?,giorno=?,ora_init=?,ora_fine=?, durata=?,sala=? where id=? ;";
				statement=connection.prepareStatement(sql);
				statement.setString(7, film.getId());
				statement.setString(1, film.getTitolo());
				statement.setString(2, film.getData());
				statement.setString(3, film.getOra_Init());
				statement.setString(4, film.getOra_Fine());
				statement.setString(5, film.getDurata());
				statement.setString(6, film.getSala());				
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
