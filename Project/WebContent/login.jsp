<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="com.Project.servlet.*,com.Project.beans.*,java.util.*,java.io.*" %>
<!DOCTYPE html>
<html >
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">"></script>
<script src="animazioni.js"></script>
<link rel="stylesheet" type="text/css" href="./projectStyle.css">
</head>
<body>
	<img src="/Project/images/intro.gif"  id="intro">
	<div id="login">
	<form action="http://localhost:8080/Project/Access" method="POST">
	<h1 class="title">LOGIN </h1>
	Username: <input type="text" name="username" required><br /><br />
	Password: <input type="password" name="password" id="password" required>
	<img src="/Project/images/eye.png" id="togglepwd"><br /><br />
	<input type="submit" value="Accedi">	
	</form>
	<% 
		String msg= (String)request.getAttribute("messaggio");
		if(msg!=null)
		{
			out.println("<p id='msg'>"+ msg +"</p>");
		}
		System.out.println(msg);
	%>
	<p>	Se non sei ancora registrato, fallo adesso.</p>
	<p> PS: posso usare una funzione sola per il print della tabella?
	<button onclick="document.location.href='registrazione.html'">Registrati</button>
	</div>
</body>
</html>