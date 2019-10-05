<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
 import="com.Project.servlet.*,com.Project.beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./projectStyle.css">
<script src="jquery-3.4.1.min.js"></script>
<style type="text/css">
.btn {
  height: auto;
  width: auto;
  font-size:20pt;
  border-radius: 50%;
  border: 1px solid red;
  background: gold;
  color: #000000; 
  transition: width 0.2s ease-in-out;
}
</style>
</head>
<body>
<% String username= (String)session.getAttribute("username"); %>
<div class="header">
	<h1 class="title">CINEMA HOME</h1>
	<button onclick="document.location.href='login.jsp'" class="button_header">Logout</button>
</div>
<div class="content"  >
	<div class="menu" style="float:left;width:30%; background:#ffffff; text-align:center;border: 2px solid black;">
	<p>Welcome Back: <%=username %></p>
	<ul style="padding:0;margin:0;display:block;list-style-type:none;">
	<li>Film</li>
	<li>Sale</li>
	<li>logout</li>
	</ul>
</div>
	<div class="sub-content" style="width:50%; height:auto; margin-left:35%;background:#000000;">
		<form action= "http://localhost:8080/Project/Visualizza_Film" method="POST">
		<input class= "button" type="submit" name="opzione" value= "Visualizza Film">
		<input class= "button" type= "submit" name= "opzione" value= "Elimina Film">
	    <input class= "button" type="submit" name="opzione" value= "Modifica Film">
	    </form>
		<button class="btn" onclick="document.location.href='prova.jsp'">show</button><br />
		<button class="btn" onclick="document.location.href='add_film.html'">+</button><br />
		<button class="btn" onclick="document.location.href='Sala.jsp?choice=1'" value="Sala_1" name="Sala_1">sala</button><br />
		<button class="btn" onclick="document.location.href='modifica.jsp'">Modifica</button><br />
		<button class="btn" onclick="document.location.href='elimina.jsp'">Elimina</button><br />
	<form action="http://localhost:8080/Project/Gestione_Sale" method="POST">
		<input class="button" type="submit" name="opzione" value="Sala_1">
		<input class="button" type="submit" name="opzione" value="Sala_2">
		<input class="button" type="submit" name="opzione" value="Sala_3">
		<input class="button" type="submit" name="opzione" value="Sala_4">
	</form>
	</div>
</div>
</body>
</html>