<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
 import="com.Project.servlet.*,com.Project.beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./projectStyle.css">
</head>
<body>
<%	Utente utente= (Utente)request.getAttribute("utente"); %>
	<h1 class="title">Welcome back: ${utente.getUserName()} </h1>
<div id="gestionefilm">
<form action="http://localhost:8080/Project/Visualizza_Film" method="POST">
		<input type="submit" name="opzione" value=" Gestione Film">
</form>
</div>
<div id="gestionesale">
	<button type="submit" onclick="document.location.href='show_sale.jsp'">Gestione Sale</button>
</div>
<div id="gestioneadmin">
<button onclick="document.location.href='show_admin.jsp'">Admins</button>
</div>
<div id="gestioneout">
<button onclick="document.location.href='login.html'">Logout</button>
</div>








</body>
</html>