<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Registrazione </title>
<link rel="stylesheet" type="text/css" href="./projectStyle.css">
<script src="jquery-3.4.1.min.js"></script>
<script src="functions.js" type="text/javascript"></script>
<script src="animazioni.js" type="text/javascript"></script>
</head>
<body>
<div class="page">
	<div class="header">
		<h1 class="title">Registrazione</h1>
	</div>
	<div class="content">
		<form action="http://localhost:8080/Project/Register" method="POST">
			Username: <input type="text" name="username"><br /><br />
			Password: <input type="password" name="password"><br /><br />
			<input type="submit" value="Registati">	
		</form>
	</div>
</div>

</body>
</html>