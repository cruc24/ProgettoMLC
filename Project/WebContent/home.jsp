<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
 import="com.Project.servlet.*,com.Project.beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%	Utente utente= (Utente)request.getAttribute("utente"); %>
	<h1>Welcome back: ${utente.getUserName()} </h1>


<button onClick="document.location.href='add_film.html'" >Inserisci Film</button>

<button onClick="document.location.href='modifica.jsp'" >Modifica Film</button>

<button onClick="document.location.href='show_films.jsp'" >Mostra Film</button>







</body>
</html>