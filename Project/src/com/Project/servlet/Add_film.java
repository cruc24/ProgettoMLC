package com.Project.servlet;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.Project.beans.Film;

@WebServlet("/Add_film")
@MultipartConfig
public class Add_film extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("home.jsp");
		try {
			response.setContentType("text/html;charset=UTF-8");
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
				film.setSala(request.getParameter("sala"));
				
				Part file= request.getPart("file");
				String filename= file.getSubmittedFileName();
				String savePath= "C:\\Users\\Luca\\git\\repository\\Project\\WebContent\\Locandine_film"+ File.separatorChar+filename;
				//File directory= new File(savePath);
				file.write(savePath+File.separatorChar);
				film.setFileName(filename);
				film.setPath(savePath);
				System.out.println(savePath);
				String sql="insert into films (titolo,giorno,ora_init,ora_fine,sala,filename,file_path) values (?,?,?,?,?,?,?);";
				statement=connection.prepareStatement(sql);
				statement.setString(1,film.getTitolo());
				statement.setString(2, film.getData());
				statement.setString(3,film.getOra_Init());
				statement.setString(4,film.getOra_Fine());
				statement.setString(5, film.getSala());
				statement.setString(6, film.getFileName());
				statement.setString(7, film.getPath());
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
