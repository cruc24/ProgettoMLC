package com.Project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Project.beans.Database;
import com.Project.beans.Film;
import com.Project.beans.JsonConverter;
import com.mysql.cj.Session;

/**
 * Servlet implementation class Gestione_Sale
 */
@WebServlet("/Gestione_Sale")
public class Gestione_Sale extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("application/json;charset=UTF-8");
				Database db;
				try{
					db = new Database();
					String sala= (String)request.getParameter("sala");
					System.out.println(sala);
					JsonConverter converter = new JsonConverter();
			        ServletOutputStream out = response.getOutputStream();
			        String output=null;
					if (converter.convertToJson(db.filmSala(sala)) != null)
						output = converter.convertToJson(db.filmSala(sala));
					else
						output = "Non ci sono film in programma nella" + sala + " oggi.";
						out.println(output);
						System.out.println(output);
					} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}	   		
	}  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
				String user="root";
				String pwd="root";
				Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
				PreparedStatement statement=null;
				String url="/Sala.jsp";
				List<Film> f= new ArrayList<Film>();
				String choice=request.getParameter("opzione");
				String message = "Non ci sono eventi in programma";
				Calendar c =  Calendar.getInstance();
				java.util.Date today = c.getTime();
				String  oggi = new SimpleDateFormat("yyyy-MM-dd").format(today);
				LocalTime now =  LocalTime.now();
				String sql="select * from films where sala = ?";
				statement=connection.prepareStatement(sql);
				statement.setString(1,choice);
				ResultSet rs = statement.executeQuery();
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
					LocalTime fine= LocalTime.parse(film.getOra_Fine());
					if(oggi.equals(film.getData()) && now.isBefore(fine))
						f.add(film);
					message="";
					}
				request.setAttribute("messaggio", message);
				request.setAttribute("film", f);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
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
