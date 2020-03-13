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

import com.Project.beans.*;
public class Controllo {

	
public boolean Occupato(Film film) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	String jdbcUrl = "jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
	String user = "root";
	String pwd = "root";
	Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
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
		if((inizio.isBefore(rs_init)||inizio.equals(rs_init)) && fine.isAfter(rs_init) && (fine.isBefore(rs_fine)|| fine.equals(rs_fine)||fine.isAfter(rs_fine)))
			return true;
		if(inizio.isAfter(rs_init)  && fine.isAfter(rs_init) && (fine.isBefore(rs_fine)|| fine.equals(rs_fine)||fine.isAfter(rs_fine)))
			return true;
	}
	return false;
}
public boolean inCorso(Film film){
	LocalTime now = LocalTime.now();
	LocalTime inizio= LocalTime.parse(film.getOra_Init());
	LocalTime fine= LocalTime.parse(film.getOra_Fine());
	System.out.println("Controllo live:"+ (now.isAfter(inizio) && now.isBefore(fine)) );
	return (now.isAfter(inizio) && now.isBefore(fine));
}
public boolean ControlOra(Film film) {
	LocalTime inizio= LocalTime.parse(film.getOra_Init());
	LocalTime fine= LocalTime.parse(film.getOra_Fine());
	System.out.println("Controllo ora:"+ fine.isAfter(inizio) );
	return fine.isAfter(inizio) ;
}
public boolean ControlData(Film film) throws ParseException {
	Calendar c =  Calendar.getInstance();
	java.util.Date today = c.getTime();
	java.util.Date date1 =new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(film.getData()+ " " +film.getOra_Init());
	System.out.println("Controllo data:"+ date1.after(today) );
	return date1.after(today) || date1.equals(today);	
}
public boolean Controlinsert(Film film) {
	System.out.println("Controllo null:"+ ((film.getTitolo()!= null) && (film.getData() != null) && (film.getOra_Init() != null) && (film.getOra_Fine() != null) && (film.getSala() != null) && (film.getFileName() != null)));
	return (film.getTitolo()!= null) && (film.getData() != null) && (film.getOra_Init() != null) && (film.getOra_Fine() != null) && 
		   (film.getSala() != null) && (film.getFileName() != null);
}

}