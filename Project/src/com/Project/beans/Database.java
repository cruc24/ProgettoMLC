package com.Project.beans;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Part;

public class Database {
	
	public List<Film> filmList() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
		String user="root";
		String pwd="root";
		Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
		Statement statement=null;
		List<Film> f= new ArrayList<>();
		statement = connection.createStatement();
		String sql="select * from films";
		ResultSet rs = statement.executeQuery(sql);
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
			f.add(film);
		}
		rs.close();
		statement.close();
		connection.close();
		return f;
	}
	//TIME_FORMAT(ADDTIME(CURTIME(),"2:00"),"%H:%i") dell'ora attuale prende ora e minuto in sql
	public List<Film> filmSala(String sala) throws ClassNotFoundException, SQLException{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
			String user="root";
			String pwd="root";
			Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
			PreparedStatement statement=null;
			List<Film> f= new ArrayList<Film>();
			//String sql="select * from films where sala = ? && giorno= CURDATE()";
			String sql="select * from films where sala = ?";
			statement=connection.prepareStatement(sql);
			statement.setString(1,sala);
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
				f.add(film);
				}
			connection.close();
			statement.close();
			rs.close();
			return f;
		}
	
	public void Addfilm(Film film){
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
				String user="root";
				String pwd="root";
				Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
				PreparedStatement statement=null;
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
	public void eliminaFilm(Film film) {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
				String user="root";
				String pwd="root";
				Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd);
				PreparedStatement statement;
				String sql="delete from films where id=?";
				statement=connection.prepareStatement(sql);
				statement.setString(1,film.getId());
				statement.executeUpdate();
			}
			catch(ClassNotFoundException e)	{
				System.out.println("class not found.");
				}
			}
		catch(SQLException e)
		{
			throw new RuntimeException("cannot connect the database!",e );
		}
	}	
}
