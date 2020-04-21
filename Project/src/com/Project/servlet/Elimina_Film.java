package com.Project.servlet;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Project.beans.Controllo;
import com.Project.beans.Database;
import com.Project.beans.Film;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
			        dispatcher.forward(request, response);
				}
				else
				{
				Database.eliminaFilm(film);
				response.sendRedirect("admin.jsp");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
}
}
