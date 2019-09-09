<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.sql.*"%>
	<% Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
	String user="root";
	String pwd="root";
	Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
	Statement statement=null;
	statement = connection.createStatement();
	String sql="select * from films";
	ResultSet rs = statement.executeQuery(sql);
	%>
	<table>
		<tr>
			<th>titolo</th>
			<th>giorno</th>
			<th>ora_init</th>
			<th>ora_fin</th>
			<th>durata</th>
			<th>genere</th>
		</tr>
	<%
	while(rs.next())
		{
		  out.println("<tr>");
	      out.println("<td>'" + rs.getString("titolo") + "'</td>");
	      out.println("<td>" + rs.getString("giorno") + "</td>");
	      out.println("<td>" + rs.getString("ora_init") + "</td>");
	      out.println("<td>" + rs.getString("ora_fine") + "</td>");
	      out.println("<td>" + rs.getString("durata") + "</td>");
	      out.println("<td>" + rs.getString("sala") + "</td>");
	      out.println("</tr>");
		}
	%>
	</table>
  		<button onClick="document.location.href='/Project/home.jsp'" >Indietro</button><br>
	<% 
	rs.close();
	statement.close();
	connection.close(); 
	%>
</body>
</html>