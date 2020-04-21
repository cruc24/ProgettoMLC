<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="/Project/css/projectStyle.css">
<script src="jquery-3.4.1.min.js"></script>
<script src="/Project/javascript/functions.js" type="text/javascript"></script>
<script src="/Project/javascript/functions_users.js" type="text/javascript"></script>
<script src="/Project/javascript/animazioni.js" type="text/javascript"></script>
<script src="/Project/javascript/utility.js" type="text/javascript"></script>
</head>
<body>
<%
Map<String,String> errore= (Map<String,String>) request.getAttribute("errore");
String username= (String)session.getAttribute("username");
if(username == null)
	response.sendRedirect("login.jsp");
%>
<div class="page">
	<div class="container">
		<div class="header">
		<h1 class="title">CINEMA HOME</h1>
		</div>
		<div class="content">
		<div class="menu">
		<p style="padding:0 5%;font-size:25px;text-align:left">Welcome Back:<%=username %> <br/></p>
		<p id="time" style="padding:0 5%;font-size:25px;" >Ora</p>
		<hr/>
		<ul style="padding:3% 0;margin:10;display:block;list-style-type:none;">
			<li onclick="mostra()"> Film </li>
			<li onclick="aggiungi()"> Aggiungi Film </li>
			<li onclick="edit('Modifica')"> Modifica Film</li>
			<li onclick="edit('Elimina')"> Elimina Film </li>
			<li onclick="mostra(1)"> Sala 1 </li>
			<li onclick="mostra(2)"> Sala 2 </li>
			<li onclick="mostra(3)"> Sala 3 </li>
			<li onclick="mostra(4)"> Sala 4 </li>
			<li onclick="mostra_utenti()"> Users </li>
			<li onclick="edit_user('Elimina')"> Modifica_User </li>
			<li onclick="mostra_logs()"> Logs </li>
			<li onclick="logout" id="logout">Logout</li>
		</ul>
		</div>
		<div class="sub-content" id="sub-content"> 
		<% 
		if(errore != null)
		{
			for (String name : errore.keySet()) {
			out.println("<p style='font-size:20px;' class='msg'>"+errore.get(name)+"</p>");
			}
		}
		%> 
		</div>
	 </div>
	</div>
</div>
</body>
</html>