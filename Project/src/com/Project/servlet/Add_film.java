package com.Project.servlet;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.Project.beans.Film;

@WebServlet("/Add_film")
public class Add_film extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Add_film() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

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
				PreparedStatement statement=null;
				
				Film film = new Film();
				film.setTitolo(request.getParameter("titolo"));
				film.setData(request.getParameter("giorno"));
				film.setOra_Init(request.getParameter("ora_init"));
				film.setOra_Fine(request.getParameter("ora_fine"));
				film.setSala(request.getParameter("sala_cinema"));
				film.setDurata(request.getParameter("durata"));
				//film.setLocandina(request.getParameter("locandina"));
				
				
				String s= request.getPathTranslated();
				System.out.println(s);
				System.out.println(request.getAttribute("locandina"));
				System.out.println(request.getContentLength());
				System.out.println(request.getContextPath());
				System.out.println(request.getCharacterEncoding());
				System.out.println(request.getContentType());
				System.out.println(request.getHeader("locandina"));
				System.out.println(request.getParameter("locandina"));
				System.out.println(request.getPathInfo());
				
				
				/*
				byte[] i= Base64.decode(s);
				ByteArrayInputStream bis = new ByteArrayInputStream(i);
			    BufferedImage image2 = ImageIO.read(bis);
			    film.setLocandina(image2);
				*/
				String sql="insert into films (titolo,giorno,ora_init,ora_fine,durata,sala) values (?,?,?,?,?,?) ";
				statement=connection.prepareStatement(sql);
				statement.setString(1,film.getTitolo());
				statement.setString(2, film.getData());
				statement.setString(3,film.getOra_Init());
				statement.setString(4,film.getOra_Fine());
				statement.setString(5, film.getDurata());
				statement.setString(6, film.getSala());
				//statement.setString(7, film.getLocandina());
				
				statement.executeUpdate();
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
