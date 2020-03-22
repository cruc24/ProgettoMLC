<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="./projectStyle.css">
<script src="jquery-3.4.1.min.js"></script>
<script src="functions.js" type="text/javascript"></script>
<script src="animazioni.js" type="text/javascript"></script>
</head>
<body>
<%
String username= (String)session.getAttribute("username");
if(username == null)
	response.sendRedirect("login.jsp");
%>
<div class="page" style="width:100%;height:100%;background-size:cover">
	<div class="header">
		<h1 class="title">CINEMA HOME</h1>
		<!--  <button onclick="location.href='./Logout'" class="button_header">Logout</button> -->
	</div>
	<div class="content"  >
		<div class="menu" style="float:left;width:30%;height:100%; background:#ffffff; text-align:center;border: 2px solid black; ">
		<p style="padding:5%;">Welcome Back:<%=username %> <br/></p>
		<p id="time">Ora</p>
		<hr/>
		<ul style="padding:0;margin:10;display:block;list-style-type:none;">
		<li onclick="show()"> Film </li>
		<li onclick="aggiungi()"> Aggiungi </li>
		<li onclick="modifica()"> Modifica </li>
		<li onclick="elimina()"> Elimina </li>
		<li onclick="sala(1)"> Sala 1 </li>
		<li onclick="sala(2)"> Sala 2 </li>
		<li onclick="sala(3)"> Sala 3 </li>
		<li onclick="sala(4)"> Sala 4 </li>
		<li onclick="logout" id="logout">logout</li>
		</ul>
		</div>
		<div class="sub-content" id="sub-content" style="width:50%; height:100%; margin-left:35%;">
			<div class="div_table">
				<table class='tabella' id="tabella">
				<thead>
				<th>titolo</th><th>Data</th><th>ora inizio</th><th>ora fine</th><th>sala</th><th>status</th>
				<thead>
				<tbody>
				</tbody>
				</table>
			</div>
			<div class="div_add" >	
					<form action='http://localhost:8080/Project/Add_film' method='POST' style="height:100%;width:100%;position:relative;text-align:center;display:table;" enctype="multipart/form-data">
					<h2>Aggiungi Film </h2>
						<label>Titolo film:</label>
						<input type="text" name="titolo"> <br />
						<label>Data riproduzione film:</label>
						<input type="date" name="giorno" id="giorno"> <br>
						<label>Orario inizio film:</label>
						<input type="time" name="ora_init" id="ora_init"> <br />
						<label>Orario termine film:</label>
						<input type="time" name="ora_fine" id="ora_fine"> <br />
						<label>Sala proiezione del film:</label><br />
						<input type="radio" name="sala" value="Sala_1" checked> Sala 1 <br />
						<input type="radio" name="sala" value="Sala_2"> Sala 2 <br />
						<input type="radio" name="sala" value="Sala_3"> Sala 3 <br />
						<input type="radio" name="sala" value="Sala_4"> Sala 4 <br />
						<label>Locandina film:</label>
						<input type="file" name="file" id="file"> <br /><br />
		    			<input type='submit' value='Aggiungi'>
		    		</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>