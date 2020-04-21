package com.Project.beans;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

	public class Database {
	
	static String user="root";
	static String pwd="root";
	static String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
	
	public static String ImagetoString(Blob blob) throws IOException, SQLException {
		InputStream inputStream = blob.getBinaryStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
		    outputStream.write(buffer, 0, bytesRead);
		}
		byte[] imageBytes = outputStream.toByteArray();
		String image = Base64.getEncoder().encodeToString(imageBytes);
		inputStream.close();
        outputStream.close();
		return image;
	}
	
	public static List<Film> filmList() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		//String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
		//String user="root";
		//String pwd="root";
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
			//film.setFileName(rs.getString("filename"));
			//film.setPath(rs.getString("file_path"));
			Blob blob = rs.getBlob("filename");
			String imageString=ImagetoString(blob);
			film.setImage(imageString);
			f.add(film);
			
		}
		rs.close();
		statement.close();
		connection.close();
		Database.insert_log("Film-List", " ");
		return f;
	}
	//TIME_FORMAT(ADDTIME(CURTIME(),"2:00"),"%H:%i") dell'ora attuale prende ora e minuto in sql
	public static List<Film> filmSala(String sala) throws ClassNotFoundException, SQLException, IOException{
			Class.forName("com.mysql.cj.jdbc.Driver");
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
				//film.setFileName(rs.getString("filename"));
				//film.setPath(rs.getString("file_path"));
				Blob blob = rs.getBlob("filename");
				String imageString=ImagetoString(blob);
				film.setImage(imageString);
				f.add(film);
				}
			rs.close();
			statement.close();
			connection.close();
			Database.insert_log("Sala-List", sala);
			return f;
		}
	
	public static void Addfilm(Film film, String file_path){
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
				String user="root";
				String pwd="root";
				Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
				PreparedStatement statement=null;
				String sql="insert into films (titolo,giorno,ora_init,ora_fine,sala,filename) values (?,?,?,?,?,?);";
				statement=connection.prepareStatement(sql);
				statement.setString(1,film.getTitolo());
				statement.setString(2, film.getData());
				statement.setString(3,film.getOra_Init());
				statement.setString(4,film.getOra_Fine());
				statement.setString(5, film.getSala());
				File image= new File(file_path);
				byte[] bytes = Files.readAllBytes(image.toPath());
				Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
				statement.setBlob(6, blob);
				statement.executeUpdate();
				statement.close();
				connection.close();
				Database.insert_log("Add-Film", "titolo:"+film.getTitolo());
			}
			catch(ClassNotFoundException | IOException e)	{
			e.printStackTrace();
			}
			}
			catch(SQLException e)
			{
			throw new RuntimeException("cannot connect the database!",e );
			}
	}
	
	public static void eliminaFilm(Film film) {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd);
				PreparedStatement statement;
				String sql="delete from films where id=?";
				statement=connection.prepareStatement(sql);
				statement.setString(1,film.getId());
				statement.executeUpdate();
				statement.close();
				connection.close();
				Database.insert_log("Remove-Film", "id:"+film.getTitolo());
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
	public static void addUser(Utente u) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd);
		PreparedStatement statement;
		String sql="insert into users (username,password,roles) values(?,?,?)";
		statement=connection.prepareStatement(sql);
		statement.setString(1, u.getUserName());
		statement.setString(2, u.getPassword());
		statement.setString(3, u.getRole());
		statement.executeUpdate();
		statement.close();
		connection.close();
		Database.insert_log("Add-User", "Username:"+u.getUserName());
	}
	public static List<Utente> utentiList() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(jdbcUrl,user,pwd);
		Statement statement=null;
		List<Utente> users= new ArrayList<>();
		statement = connection.createStatement();
		String sql="select * from users where roles <> 'A' ";
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			Utente u = new Utente();
			u.setUserName(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setRole(rs.getString("roles"));
			users.add(u);
		}
		rs.close();
		statement.close();
		connection.close();
		Database.insert_log("User-List", "");
		return users;
	}
	public static void eliminaUser(Utente u) {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd);
				PreparedStatement statement;
				String sql="delete from users where username=?";
				statement=connection.prepareStatement(sql);
				statement.setString(1,u.getUserName());
				statement.executeUpdate();
				statement.close();
				connection.close();
				Database.insert_log("Remove-User", "Username:"+u.getUserName());
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
	public static void modificaFilm(Film film) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
		PreparedStatement statement;
		String sql="update films set titolo=?,giorno=?,ora_init=?,ora_fine=?,sala=? where id=? ;";
		statement=connection.prepareStatement(sql);
		statement.setString(1, film.getTitolo());
		statement.setString(2, film.getData());
		statement.setString(3, film.getOra_Init());
		statement.setString(4, film.getOra_Fine());
		statement.setString(5, film.getSala());		
		statement.setString(6, film.getId());
		statement.executeUpdate();
		statement.close();
		connection.close();
		Database.insert_log("Modifica-Film", "titolo:"+film.getTitolo());
	}
	public static void modificaUser(Utente u) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
		PreparedStatement statement;
		String sql="update users set roles=? where username=? ";
		statement=connection.prepareStatement(sql);
		statement.setString(1, u.getRole());
		statement.setString(2, u.getUserName());
		statement.executeUpdate();
		statement.close();
		connection.close();
		Database.insert_log("Modifica-User", "Username:"+u.getUserName());
	}
	public static void insert_log(String mode,String description) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd);
		PreparedStatement statement;
		String sql="insert into log_activity(activity,data_Act, description_act) values(?,?,?)";
		statement=connection.prepareStatement(sql);
		Date date = new Date();  
        Timestamp ts=new Timestamp(date.getTime());  
		Timestamp sqlDate = new java.sql.Timestamp(ts.getTime());
		statement.setString(1,mode);
		statement.setTimestamp(2,sqlDate);
		statement.setString(3, description);
		statement.executeUpdate();
		connection.close();
		statement.close();
	}
	public static List<Activity> actList() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(jdbcUrl,user,pwd);
		Statement statement=null;
		List<Activity> activities= new ArrayList<>();
		statement = connection.createStatement();
		String sql="select id,activity,data_act,description_act from log_activity";
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			Activity a = new Activity();
			a.setId(rs.getInt("id"));
			a.setAct(rs.getString("activity"));
			a.setDate(rs.getTimestamp("data_act"));
			a.setDescription(rs.getString("description_act"));
			activities.add(a);
		}
		connection.close();
		statement.close();
		rs.close();
		return activities;
	}
}
