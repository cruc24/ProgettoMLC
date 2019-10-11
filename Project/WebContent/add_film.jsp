<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
 import="com.Project.servlet.*,com.Project.beans.*" %>
<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">"></script>
<script src="animazioni.js"></script>
<link rel="stylesheet" type="text/css" href="projectStyle.css">
<meta charset="ISO-8859-1">
<title>Add_Film</title>
</head>
<body>
<div class="header">
	<h1 class="title">AGGIUNGI FILM</h1>
</div>
<div class="content"  >
	<form action="http://localhost:8080/Project/Add_film" method="POST" enctype="multipart/form-data">	
		<label>Titolo film:</label>
		<input type="text" name="titolo"> <br /><br />
		<label>Data riproduzione film:</label>
		<input type="date" name="giorno" id="giorno"> <label id="check_data"></label> <br /><br />
		<label>Orario inizio film:</label>
		<input type="time" name="ora_init" id="ora_init"> <br /><br />
		<label>Orario termine film:</label>
		<input type="time" name="ora_fine" id="ora_fine"> <br /><br />
		<label>Sala proiezione del film:</label><br />
		<input type="radio" name="sala" value="Sala_1" checked> Sala 1 <br />
		<input type="radio" name="sala" value="Sala_2"> Sala 2 <br />
		<input type="radio" name="sala" value="Sala_3"> Sala 3 <br />
		<input type="radio" name="sala" value="Sala_4"> Sala 4 <br />
		<label>Locandina film:</label>
		<input type="file" name="file" id="file"> <br /><br />
 		<input type="submit" value="Insert">		
	</form>
</div>
</body>
</html>