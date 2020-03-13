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

import com.Project.beans.Controllo;
import com.Project.beans.Database;
import com.Project.beans.Film;

@WebServlet("/Add_film")
@MultipartConfig
public class Add_film extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("home.jsp");
		Database db= new Database();
		Film film = new Film();
		film.setTitolo(request.getParameter("titolo"));
		film.setData(request.getParameter("giorno"));
		film.setOra_Init(request.getParameter("ora_init"));
		film.setOra_Fine(request.getParameter("ora_fine"));
		film.setSala(request.getParameter("sala"));
		Part file= request.getPart("file");
		String filename= file.getSubmittedFileName();
		String savePath= this.getServletContext().getRealPath("/Locandine_film")+ File.separatorChar;
		film.setFileName(filename);
		film.setPath(savePath);
		Controllo c= new Controllo();
		try {
			System.out.println(c.Controlinsert(film));
			System.out.println(!c.Occupato(film));
			if( c.Controlinsert(film) && !c.Occupato(film) )
			{
			db.Addfilm(film);
			file.write(savePath+filename);// altrimenti mi da errore di accesso negato perchè non scrivo niente
			film.setFileName(filename);
			film.setPath(savePath);
			}
			else
			System.out.println("un po essere");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// fare controlli dei valori inseriti
	}	

}
