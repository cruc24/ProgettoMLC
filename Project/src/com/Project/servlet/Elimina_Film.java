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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Project.beans.Controllo;
import com.Project.beans.Database;
import com.Project.beans.Film;
import com.google.gson.Gson;

/**
 * Servlet implementation class Elimina_film
 */
@WebServlet("/Elimina_Film")
public class Elimina_Film extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Elimina_Film() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//response.setContentType("text/html");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			Film film = new Film();
			film.setId(request.getParameter("id"));
			film.setTitolo(request.getParameter("titolo"));
			film.setData(request.getParameter("giorno"));
			film.setOra_Init(request.getParameter("ora_init"));
			film.setOra_Fine(request.getParameter("ora_fine"));
			film.setSala(request.getParameter("sala"));
			Controllo c= new Controllo();
			try {
				if(c.inCorso(film,"elimina"))
				{
					request.setAttribute("errore",c.get_map());
					System.out.println(c.get_map());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home_definitiva.jsp");
			        dispatcher.forward(request, response);
				}
				else
				{
				Database.eliminaFilm(film);
				}
			response.sendRedirect("home_definitiva.jsp");
			
			} catch (ParseException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				film.setTitolo(request.getParameter("titolo"));
				film.setData(request.getParameter("giorno"));
				film.setOra_Init(request.getParameter("ora_init"));
				film.setOra_Fine(request.getParameter("ora_fine"));
				film.setSala(request.getParameter("sala"));
				ArrayList<Film> f= new ArrayList<>();
				Controllo c= new Controllo();
				String errore="";
				String jsp_url="";
				if(c.inCorso(film))
				{
					PreparedStatement st=null;
					String sql="select * from films";
					st=connection.prepareStatement(sql);
					ResultSet rs = st.executeQuery(sql);
					while(rs.next()) {
						Film filmino = new Film();
						filmino.setId(rs.getString("id"));
						filmino.setTitolo(rs.getString("titolo"));
						filmino.setData(rs.getString("giorno"));
						filmino.setOra_Init(rs.getString("ora_init"));
						filmino.setOra_Fine(rs.getString("ora_fine"));
						filmino.setSala(rs.getString("sala"));
						filmino.setFileName(rs.getString("filename"));
						filmino.setPath(rs.getString("file_path"));
						f.add(filmino);
					}
					if(c.inCorso(film)){
						errore+="film in esecuzione impossibile modificare.\n";
					}
					jsp_url="/elimina.jsp";
					request.setAttribute("errore", errore);
					
					
				}
				else
				{
				film.setId(request.getParameter("id"));
				String sql="delete from films where id=?";
				statement=connection.prepareStatement(sql);
				statement.setString(1,film.getId());
				
				statement.executeUpdate();
				jsp_url="/home.jsp";
				}
				request.setAttribute("film", f);
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
*/
}
