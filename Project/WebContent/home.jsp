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


<form action="http://localhost:8080/Project/Visualizza_Film" method="POST">
		<input type="submit" name="opzione" value="Film">
</form>









</body>
</html>