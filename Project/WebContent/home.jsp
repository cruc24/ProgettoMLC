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
<script>
$(document).ready(function(){
	$("li").mouseover(function(){
		$(this).css("background","#F0F8FF");
	});
});
$(document).ready(function(){
	$("li").mouseout(function(){
		$(this).css("background","#FFFFFF");
	});
});
</script>
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
	<div class="menu" style="float:left;width:30%;height:50%; background:#ffffff; text-align:center;border: 2px solid black;">
	<p>Welcome Back: <%=username %></p>
	<ul style="padding:0;margin:0;display:block;list-style-type:none;">
	<li>Film</li>
	<li>Sale</li>
	<li>logout</li>
	</ul>
</div>
	<div class="sub-content" style="width:50%; height:auto; margin-left:35%;background:#000000;">
		<button class="button" onclick="document.location.href='prova.jsp'">show</button><br />
		<button class="button" onclick="document.location.href='add_film.jsp'">+</button><br />
		<button class="button" onclick="document.location.href='modifica.jsp'">Modifica</button><br />
		<button class="button" onclick="document.location.href='elimina.jsp'">Elimina</button><br />
	
	<form action="http://localhost:8080/Project/Gestione_Sale" method="POST">
		<input class="button" type="submit" name="opzione" value="Sala_1">
		<input class="button" type="submit" name="opzione" value="Sala_2">
		<input class="button" type="submit" name="opzione" value="Sala_3">
		<input class="button" type="submit" name="opzione" value="Sala_4">
	</form>
		<button class="btn" onclick="document.location.href='Sala.jsp?sala=Sala_1'">sala</button><br />
	</div>
</div>
</body>
</html>