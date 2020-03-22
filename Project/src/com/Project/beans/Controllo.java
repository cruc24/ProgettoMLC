package com.Project.beans;

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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.Project.beans.*;
public class Controllo {
	
	private Map <String,String> error = new HashMap <String,String>();
	
	// controllo se la sala è occupata 
	public boolean Occupato(Film film) throws ClassNotFoundException, SQLException {
		/*
		Class.forName("com.mysql.cj.jdbc.Driver");
		String jdbcUrl = "jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
		String user = "root";
		String pwd = "root";
		*/
		Database db = new Database();
		Connection connection=DriverManager.getConnection(db.jdbcUrl,db.user,db.pwd); // ritorna un oggetto di tipo connection se si connette
	    PreparedStatement statement;
	    String sql = "select * from films where sala=? && giorno=? && id<>?";
		statement = connection.prepareStatement(sql);
		statement.setString(1,film.getSala());
		statement.setString(2,film.getData());
		statement.setString(3,film.getId());
		ResultSet rs= statement.executeQuery();
		LocalTime inizio= LocalTime.parse(film.getOra_Init());
		LocalTime fine= LocalTime.parse(film.getOra_Fine());
		while(rs.next())
		{
			LocalTime rs_init = LocalTime.parse(rs.getString("ora_init"));
			LocalTime rs_fine = LocalTime.parse(rs.getString("ora_fine"));
			if((inizio.isBefore(rs_init)||inizio.equals(rs_init)) && fine.isAfter(rs_init) && (fine.isBefore(rs_fine)|| fine.equals(rs_fine)||fine.isAfter(rs_fine))) {
				error.put("occupato", "sala occupata in quel momento.");
				return true;
			}	
			if(inizio.isAfter(rs_init)  && fine.isAfter(rs_init) && (fine.isBefore(rs_fine)|| fine.equals(rs_fine)||fine.isAfter(rs_fine))) {
				error.put("occupato", "sala occupata in quel momento.");
				return true;
			}
		}
		return false;
	}
	
	public boolean inCorso(Film film){
		LocalTime now = LocalTime.now();
		LocalTime inizio= LocalTime.parse(film.getOra_Init());
		LocalTime fine= LocalTime.parse(film.getOra_Fine());
		if(now.isAfter(inizio) && now.isBefore(fine)) {
			error.put("inCorso","impossibile aggiungere un evento live.");
		}
		return (now.isAfter(inizio) && now.isBefore(fine));
	}
	public boolean ControlOra(Film film) {
		LocalTime inizio= LocalTime.parse(film.getOra_Init());
		LocalTime fine= LocalTime.parse(film.getOra_Fine());
		if(!fine.isAfter(inizio))
			error.put("controlOra","ora fine precedente a quella di inizio.");
		return fine.isAfter(inizio) ;
	}
	public boolean ControlData(Film film) throws ParseException {
		Calendar c =  Calendar.getInstance();
		java.util.Date today = c.getTime();
		java.util.Date date1 =new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(film.getData()+ " " +film.getOra_Init());
		if(!date1.after(today)) {
			error.put("controlData","data/ora inserita precedente a quella attuale.");
		}
		return date1.after(today) || date1.equals(today);	
		
	}
	public boolean controlInsert(Film film) {
		if(film.getTitolo()== null)
			error.put("titolo", "il titolo non può essere nullo.");
		if(film.getData()==null)
			error.put("data", "la data non può essere nulla.");
		if(film.getOra_Init()==null)
			error.put("ora_inizio", "l'ora di inizio non può essere nulla.");
		if(film.getOra_Fine()==null)
			error.put("ora_fine","l'ora di fine non può essere nulla");
		if(film.getFileName()==null)
			error.put("filename","la locandina deve inserita");
		return (film.getTitolo()!= null) && (film.getData() != null) && (film.getOra_Init() != null) && (film.getOra_Fine() != null) && 
			    (film.getFileName() != null);
	}
	
	public Map<String,String> get_map() {
		return error;
	}
	
}