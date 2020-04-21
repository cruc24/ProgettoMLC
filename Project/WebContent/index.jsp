<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="com.Project.servlet.*,com.Project.beans.*,java.util.*,java.io.*" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html >
<head>
<meta charset="ISO-8859-1">
<title>CINEMA</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">"></script>
<script src="/Project/javascript/animazioni.js"></script>
<link rel="stylesheet" type="text/css" href="/Project/css/projectStyle.css">
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
</head>
<body style="background-image:url('/Project/images/sala2.png');background-repeat: no-repeat;	background-size: cover;	max-width:100%;max-height:100%;">
	<div id="intro" >
		<img src="/Project/images/intro.gif" class="form-login" style="background-color:black;" >
	</div>
	<div id="login" style="'display':'none';">
	<div class="form-login">
	<form action="/Project/Access" method="POST">
	<h1 class="title" style="color:black;">C I N E M A</h1>
	<p>Username: <input type="text" name="username"></p>
	<p>Password: <input type="password" name="password" id="password">
	<img src="/Project/images/eye.png" id="togglepwd"></p>
	<input type="submit" value="Accedi">	
	</form>
	<p><span class="msg"><%=messaggio%></span></p>
	<p>	Se non sei ancora registrato, fallo adesso.</p>
	<button onclick="document.location.href='registrazione.jsp'">Registrati</button>
	</div>
	</div>
</body>
</html>