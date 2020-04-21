<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Registrazione </title>
<link rel="stylesheet" type="text/css" href="/Project/css/projectStyle.css">
<script src="jquery-3.4.1.min.js"></script>
<script src="/Project/javascript/functions.js" type="text/javascript"></script>
<script src="/Project/javascript/animazioni.js" type="text/javascript"></script>
<script src="/Project/javascript/passive_screen_functions.js" type="text/javascript"></script>
</head>
<%
Map<String,String> errore= (Map<String,String>) request.getAttribute("errore");
String messaggio="";
if(errore != null)
{
	for (String name : errore.keySet()) {
		messaggio += errore.get(name);
		}
}	
%>
<body>
<div class="page" style="background-color:brown;">
	<div class="container">
		<header class="header">
		<h1 class="title">Registrazione</h1>
		</header>
		<div class="content">
		<form action="/Project/Register" method="POST" class="form-login" style="padding:15px;background-color:white;">
			<a href="/Project/index.jsp" style="float:right;top:0;"><img src="https://img.icons8.com/color/25/000000/google-home.png" /></a>
			<span class="msg"><%=messaggio%></span>
			<p style="text-align:left;">Username: <input type="text" name="username"></p>
			<p style="text-align:left;">Password: <input type="password" name="password"></p>
			<p style="text-align:left;"> Ruolo: <select name="role">
						<option value="F">Films</option>
  						<option value="S">Sale</option>
  						<option value="A">Admin</option></select></p>
			<input type="submit" value="Registrati">	
		</form>
		</div>
	</div>
</div>
</body>
</html>