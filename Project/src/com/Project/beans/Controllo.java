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
public class Controllo {
	
	private Map <String,String> error = new HashMap <String,String>();
	
	public boolean Occupato(Film film,String mode) throws ClassNotFoundException, SQLException {
		Connection connection=DriverManager.getConnection(Database.jdbcUrl,Database.user,Database.pwd); // ritorna un oggetto di tipo connection se si connette
	    PreparedStatement statement;
	    String sql="";
	    String param="";
	    if (mode.equals("modifica"))
	    {
	    	sql = "select * from films where sala=? && giorno=? && id<>?";
	    	param=film.getId();
	    }
	    else if(mode.equals("aggiungi"))
	    {
	    	sql = "select * from films where sala=? && giorno=? && titolo<> ?";
	    	param= film.getTitolo();
	    }
	    statement = connection.prepareStatement(sql);
		statement.setString(1,film.getSala());
		statement.setString(2,film.getData());
		statement.setString(3,param);
		ResultSet rs= statement.executeQuery();
		LocalTime inizio= LocalTime.parse(film.getOra_Init());
		LocalTime fine= LocalTime.parse(film.getOra_Fine());
		while(rs.next())
		{
			LocalTime rs_init = LocalTime.parse(rs.getString("ora_init"));
			LocalTime rs_fine = LocalTime.parse(rs.getString("ora_fine"));
			if((inizio.isBefore(rs_init)||inizio.equals(rs_init)) && fine.isAfter(rs_init) && (fine.isBefore(rs_fine)|| fine.equals(rs_fine)||fine.isAfter(rs_fine))) {
				this.error.put("occupato",film.getSala()+" occupata in quel momento.");
				connection.close();
				statement.close();
				rs.close();
				return true;
			}	
			if(inizio.isAfter(rs_init)  && fine.isAfter(rs_init) && (fine.isBefore(rs_fine)|| fine.equals(rs_fine)||fine.isAfter(rs_fine))) {
				this.error.put("occupato", "sala occupata in quel momento.");
				connection.close();
				statement.close();
				rs.close();
				return true;
			}
		}
		connection.close();
		statement.close();
		rs.close();
		return false;
	}
	
	public boolean inCorso(Film film,String mode) throws ParseException{
		Calendar c =  Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
		Date today = c.getTime();
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(film.getData());
		LocalTime now = LocalTime.now();
		LocalTime inizio= LocalTime.parse(film.getOra_Init());
		LocalTime fine= LocalTime.parse(film.getOra_Fine());
		if(now.isAfter(inizio) && now.isBefore(fine)&& today.equals(date1)) {
			this.error.put("inCorso","impossibile eseguire: "+mode+" su un evento live.");
			return true;
		}
		return false;
	}
	public boolean ControlOra(Film film) {
		LocalTime inizio= LocalTime.parse(film.getOra_Init());
		LocalTime fine= LocalTime.parse(film.getOra_Fine());
		if(!fine.isAfter(inizio))
			this.error.put("controlOra","ora fine precedente a quella di inizio.");
		return fine.isAfter(inizio) ;
	}
	public boolean ControlData(Film film) throws ParseException {
		Calendar c =  Calendar.getInstance();
		java.util.Date today = c.getTime();
		java.util.Date date1 =new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(film.getData()+ " " +film.getOra_Init());
		if(!date1.after(today)) {
			this.error.put("controlData","data/ora inserita precedente a quella attuale.");
		}
		return date1.after(today) || date1.equals(today);	
		
	}
	
	public void controlInsert(Film film) {
		if(film.getTitolo()== null || film.getTitolo().trim().equals(""))
			this.error.put("titolo", "il titolo non puo' essere nullo.");
		if(film.getData()==null || film.getData().trim().equals("") )
			this.error.put("data", "la data non puo' essere nulla.");
		if(film.getOra_Init()==null || film.getOra_Init().trim().equals(""))
			this.error.put("ora_inizio", "l'ora di inizio non puo' essere nulla.");
		if(film.getOra_Fine()==null || film.getOra_Fine().trim().equals(""))
			this.error.put("ora_fine","l'ora di fine non puo' essere nulla");
	}
		
	public static boolean check_User_Null(Utente u) {
		if(u.getUserName() == null || u.getUserName().trim().equals("") || u.getPassword() == null || u.getPassword().trim().equals("")) {
			return false;
		}
		return true;
	}
	
	public void controlUser(Utente u) throws SQLException, ClassNotFoundException {
		 if(Controllo.check_User_Null(u)){
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(Database.jdbcUrl,Database.user,Database.pwd); // ritorna un oggetto di tipo connection se si connette
		    PreparedStatement statement;
		    String sql = "select * from users where username=?";
		    statement = connection.prepareStatement(sql);	
			statement.setString(1,u.getUserName());
			ResultSet rs= statement.executeQuery();
			if(rs.next()) {
				this.error.put("utente-insert", "Username: "+u.getUserName()+" gia' prensente nel db.");
				connection.close();
				statement.close();
				rs.close();
			}
	    }
	    else
	    	this.error.put("utente-insert", "Username/Password non possono essere null.");
	}
	
	public void controlAdmin(Utente u) throws ClassNotFoundException, SQLException{
		if(u.getRole().equals("A")) {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(Database.jdbcUrl,Database.user,Database.pwd); // ritorna un oggetto di tipo connection se si connette
	    PreparedStatement statement;
	    String sql = "select * from users where roles='A'";
	    statement = connection.prepareStatement(sql);
		ResultSet rs= statement.executeQuery();
		if(rs.next()) {
			this.error.put("admin-error", "Il ruolo di Admin e' gia' prensente nel db.");
		}
		connection.close();
		statement.close();
		rs.close();
		}
	}
	
	
	public Map<String,String> get_map() {
		return this.error;
	}
	
}