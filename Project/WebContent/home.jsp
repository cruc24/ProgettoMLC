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
<div class="header">
	<h1 class="title">CINEMA HOME</h1>
	<button onclick="document.location.href='login.jsp'" class="button_header">Logout</button>
</div>
<div class="content"  >
		<form action= "http://localhost:8080/Project/Visualizza_Film" method="POST">
		<table>
		<tr>
		<td><input class= "button" type= "submit"  value= "Aggiungi Film"></td>
		<td><input class= "button" type= "submit" name= "opzione" value= "Elimina Film"></td>
		</tr>
		<tr>
		<td><input class= "button" type="submit" name="opzione" value= "Modifica Film"></td>
		<td><input class= "button" type="submit" name="opzione" value= "Visualizza Film"></td>
		</tr>
		</table>
		</form>
		<br />
		<br />
		<table>
		<tr>
		<td>
	<form action="http://localhost:8080/Project/Gestione_Sale" method="POST">
		</td>
		<td>
		<input class="button" type="submit" name="opzione" value="Sala_1">
		</td>
		<td>
		<input class="button" type="submit" name="opzione" value="Sala_2">
		</td>
		<td>
		<input class="button" type="submit" name="opzione" value="Sala_3">
		</td>
		<td>
		<input class="button" type="submit" name="opzione" value="Sala_4">
		</td>
	</form>
		</tr>
		</table>
</div>
</body>
</html>