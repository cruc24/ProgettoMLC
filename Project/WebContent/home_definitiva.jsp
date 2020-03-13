<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="./projectStyle.css">
<script src="jquery-3.4.1.min.js"></script>
<script src="functions.js" type="text/javascript"></script>
<script src="animazioni.js" type="text/javascript"></script>
</head>
<body>
<%
String username= (String)session.getAttribute("username");
if(username == null)
	response.sendRedirect("login.jsp");
%>
<div class="page" style="width:100%;height:100%;background-size:cover">
	<div class="header">
		<h1 class="title">CINEMA HOME</h1>
		<button onclick="location.href='./Logout'" class="button_header">Logout</button>
	</div>
	<div class="content"  >
		<div class="menu" style="float:left;width:30%;height:100%; background:#ffffff; text-align:center;border: 2px solid black; ">
		<p style="padding:5%;">Welcome Back:<%=username %> <br/></p>
		<p id="time">Ora</p>
		<hr/>
		<ul style="padding:0;margin:10;display:block;list-style-type:none;">
		<li onclick="mostra()"> Film </li>
		<li onclick="modifica()"> Modifica </li>
		<li onclick="elimina()"> Elimina </li>
		<li onclick="sala(1)"> Sala 1 </li>
		<li onclick="sala(2)"> Sala 2 </li>
		<li onclick="sala(3)"> Sala 3 </li>
		<li onclick="sala(4)"> Sala 4 </li>
		<li onclick="logout" id="logout">logout</li>
		</ul>
		</div>
		<div class="sub-content" id="sub-content" style="width:50%; height:100%; margin-left:35%;"></div>
	</div>
</div>
</body>
</html>