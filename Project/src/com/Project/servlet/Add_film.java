package com.Project.servlet;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
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
		try {
			Film film = new Film();
			film.setTitolo(request.getParameter("titolo"));
			film.setData(request.getParameter("giorno"));
			film.setOra_Init(request.getParameter("ora_init"));
			film.setOra_Fine(request.getParameter("ora_fine"));
			film.setSala(request.getParameter("sala"));
			Part file= request.getPart("file");
			String filename= file.getSubmittedFileName();
			String savePath= this.getServletContext().getRealPath("")+ "Locandine_film"+ File.separatorChar;
			String file_path= savePath+filename;
			Controllo c= new Controllo();
			c.controlInsert(film);
			if(filename==null || filename.trim().equals(""))
				c.get_map().put("image-missing","la locandina deve inserita");
			if(c.get_map().isEmpty()) {
				c.ControlData(film);
				c.ControlOra(film);
				c.inCorso(film, "aggiungi");
				c.Occupato(film,"aggiungi");
					if(c.get_map().isEmpty())
					{
						file.write(file_path);
						Database.Addfilm(film,file_path);
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
			} catch (ClassNotFoundException | SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
