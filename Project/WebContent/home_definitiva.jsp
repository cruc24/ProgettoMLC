<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="./projectStyle.css">
<script src="jquery-3.4.1.min.js"></script>
<script src="functions.js" type="text/javascript"></script>
<script src="animazioni.js" type="text/javascript"></script>
<script src="utility.js" type="text/javascript"></script>
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
		<!--  <button onclick="location.href='./Logout'" class="button_header">Logout</button> -->
		</div>
		<div class="content" style="top:16%;position:relative;width:100;height:100%;" >
		<div class="menu" style="float:left;width:35%;height:100%; background:#000000;color:white; text-align:center;border: 2px solid black;position:relative; ">
		<p style="padding:0 5%;font-size:25px;text-align:left">Welcome Back:<%=username %> <br/></p>
		<p id="time" style="padding:0 5%;font-size:25px;" >Ora</p>
		<hr/>
		<ul style="padding:2% 0;margin:10;display:block;list-style-type:none;">
		<li onclick="mostra()" id="film_mostra"> Film </li>
		<li onclick="aggiungi()"> Aggiungi </li>
		<li onclick="modifica()"> Modifica </li>
		<li onclick="elimina()"> Elimina </li>
		<li onclick="mostra_sala(1)"> Sala 1 </li>
		<li onclick="mostra_sala(2)"> Sala 2 </li>
		<li onclick="mostra_sala(3)"> Sala 3 </li>
		<li onclick="mostra_sala(4)"> Sala 4 </li>
		<li onclick="logout" id="logout">Logout</li>
		<% 
		if(errore != null)
		{
			for (String name : errore.keySet()) {
			out.println("<p>"+errore.get(name)+"</p>");
			}
		}
%> 
		</ul>
		</div>
		<div class="sub-content" id="sub-content"> 
				
		</div>
	 </div>
	</div>
</div>
</body>
</html>