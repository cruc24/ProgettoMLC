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
				Class.forName("com.mysql.cj.jdbc.Driver");
				String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
				String user="root";
				String pwd="root";
				Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
				PreparedStatement statement;
				Controllo c= new Controllo();
				Film film = new Film();
				ArrayList<Film> f= new ArrayList<>();
				film.setId(request.getParameter("id"));
				film.setTitolo(request.getParameter("titolo"));
				film.setData(request.getParameter("giorno"));
				film.setOra_Init(request.getParameter("ora_init"));
				film.setOra_Fine(request.getParameter("ora_fine"));
				film.setSala(request.getParameter("sala"));
				String errore="";
				if(c.Occupato(film) || !c.ControlOra(film) || c.inCorso(film,"modifica") || !c.ControlData(film) || !c.controlInsert(film))
				{
					PreparedStatement st=null;
					String sql="select * from films";
					st=connection.prepareStatement(sql);
					ResultSet rs = st.executeQuery();
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
					if(c.Occupato(film)){
						errore+="sala già occupata.\n";
						request.setAttribute("errore", errore);
					}
					if(c.inCorso(film,"modifica")){
						errore+="film in esecuzione impossibile modificare.\n";
						request.setAttribute("errore", errore);
					}
					if(!c.ControlOra(film)|| !c.ControlData(film)) {
						errore+="orario/data non valido.\n";
						request.setAttribute("errore", errore);
					}
					//jsp_url="/modifica.jsp";
					System.out.println(errore);
					request.setAttribute("errore", errore);
					request.setAttribute("film", f);
				}
				else
				{
				String sql="update films set titolo=?,giorno=?,ora_init=?,ora_fine=?,sala=? where id=? ;";
				statement=connection.prepareStatement(sql);
				statement.setString(1, film.getTitolo());
				statement.setString(2, film.getData());
				statement.setString(3, film.getOra_Init());
				statement.setString(4, film.getOra_Fine());
				statement.setString(5, film.getSala());		
				statement.setString(6, film.getPath());
				statement.setString(7, film.getFileName());
				statement.setString(6, film.getId());
				statement.executeUpdate();
				//jsp_url="/home.jsp";
				}
				
				//RequestDispatcher rd = getServletContext().getRequestDispatcher("/home_definitiva.jsp");
				//rd.forward(request, response);
				
				response.sendRedirect("home_definitiva.jsp");
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
