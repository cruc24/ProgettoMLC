<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="com.Project.servlet.*,com.Project.beans.*,java.util.*,java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	ArrayList<Film> f= new ArrayList<>();
	f= (ArrayList<Film>) request.getAttribute("film");
	Iterator<Film> i = f.iterator(); 
%>
	<table>
		<tr>
			<th></th>
			<th>Titolo</th>
			<th>Data</th>
			<th>ora_init</th>
			<th>ora_fin</th>
			<th>durata</th>
			<th>sala</th>
		</tr>
	<%
	while(i.hasNext())
		{
		  out.println("<tr>");
		  Film film= new Film();
		  film=i.next();
		  out.println("<td>" + film.getId() + "</td>");
	      out.println("<td>" + film.getTitolo() + "</td>");
	      out.println("<td>" + film.getData() + "</td>");
	      out.println("<td>" + film.getOra_Init() + "</td>");
	      out.println("<td>" + film.getOra_Fine() + "</td>");
	      out.println("<td>" + film.getDurata()+"h" + "</td>");
	      out.println("<td>" + film.getSala() + "</td>");
	      out.println("</tr>");
		}
	%>
	</table>
	<form action="http://localhost:8080/Project/Visualizza_Film" method="POST">
		<input type="submit" name="opzione" value="modifica">
		<input type="submit" name="opzione" value="elimina">
		<input type="submit" name="opzione" value="aggiungi">
	</form>
  		<button onClick="document.location.href='/Project/home.jsp'" >Indietro</button><br>
</body>
</html>