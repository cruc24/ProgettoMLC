<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="com.Project.servlet.*,com.Project.beans.*,java.util.*,java.io.*,java.awt.image.*,javax.imageio.*" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./projectStyle.css">
<meta charset="ISO-8859-1">
<script src="jquery-3.4.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<%
	ArrayList<Film> f= new ArrayList<>();
	f= (ArrayList<Film>) request.getAttribute("film");
	Iterator<Film> i = f.iterator(); 
%>
<div class="header">
	<h1 class="title">F I L M S</h1>
	<button class="button_header" onClick="document.location.href='/Project/home.jsp'" >Indietro</button>
</div>
<div class="content">
	<table class="tabella">
		<tr>
			<th>Titolo</th>
			<th>Data</th>
			<th>ora_init</th>
			<th>ora_fin</th>
			<th>sala</th>
		</tr>
	<%
	while(i.hasNext())
		{
		  out.println("<tr>");
		  Film film= i.next();
	      out.println("<td>" + film.getTitolo() + "</td>");
	      out.println("<td>" + film.getData() + "</td>");
	      out.println("<td>" + film.getOra_Init() + "</td>");
	      out.println("<td>" + film.getOra_Fine() + "</td>");
	      out.println("<td>" + film.getSala() + "</td>");
	      out.println("<td>" + "<img src='./Locandine_film/"+ film.getFileName()+"' width='50' heigth='50'>" + "</td>");
	      out.println("</tr>");
	      
		}
	%>
	</table>	
</div>
<script>
var table = document.getElementById("mytab1");
var filmss= new Array();
for (var i = 0, row; row = table.rows[i]; i++) {
		var films={};
   		films.titolo = rows[i].cells[0];
   		films.data = rows[i].cells[1];
   		films.ora_init = rows[i].cells[2];
   		films.ora_fine = rows[i].cells[3];	
   	 	filmss.push(films);
   }  
  
 console.log(filmss.lenght); 
</script>
</body>
</html>