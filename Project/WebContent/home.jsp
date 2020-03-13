<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
 import="com.Project.servlet.*,com.Project.beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
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
<%
String username= (String)session.getAttribute("username");
if(username == null)
	response.sendRedirect("login.jsp");
	
%>
<div class="header">
	<h1 class="title">CINEMA HOME</h1>
	<button onclick="location.href='./Logout'" class="button_header">Logout</button>
</div>
<div class="content"  >
	<div class="menu" style="float:left;width:30%;height:50%; background:#000000; text-align:center;border: 2px solid black;">
	<p>Welcome Back: <%=username %></p>
	<ul style="padding:0;margin:0;display:block;list-style-type:none;">
	<li>Film</li>
	<li>Sale</li>
	<li>logout</li>
	</ul>
</div>
	<div class="sub-content" style="width:50%; height:auto; margin-left:35%;background:#000000;">
		<button class="button" onclick="document.location.href='prova.jsp'">show</button>
		<button class="button" onclick="document.location.href='add_film.jsp'">+</button>
		<button class="button" onclick="document.location.href='modifica.jsp'">Modifica</button>
		<button class="button" onclick="document.location.href='elimina.jsp'">Elimina</button>
		<br>
		<br><br>
		<button class="button" onclick="document.location.href='Sala.jsp?sala=Sala_1'">Sala 1</button>
		<button class="button" onclick="document.location.href='Sala.jsp?sala=Sala_2'">Sala 2</button>
		<button class="button" onclick="document.location.href='Sala.jsp?sala=Sala_3'">Sala 3</button>
		<button class="button" onclick="document.location.href='Sala.jsp?sala=Sala_4'">Sala 4</button>
		<iframe src="prova.jsp" width='900' height='700'></iframe>
	</div>
</div>
</body>
</html>