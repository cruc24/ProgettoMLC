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
<script src="utility.js" type="text/javascript"></script>
<script type="text/javascript">
var filmsPerPage = 5;
var secondsPerPage = 3;
var newDataAvailable = false;
var currentPage=-1;
var pages;
var films;

function mostra() {
	startLoadData(); // Load new XML file every 60 seconds
	startDisplay(); // Display current data
}

//
function startLoadData(){
	loadNewData();
	newDataAvailable = true;
	setTimeout("startLoadData()", 60000);
}

function startDisplay(){
	if(newDataAvailable){
		createNewPages();
		currentPage = -1;
		newDataAvailable = false;
	}
		
	currentPage++;
	currentPage = currentPage % pages.length;
	$("#sub-content").html(pages[currentPage]);

	setTimeout("startDisplay()", secondsPerPage*1000);
}

function createNewPages(){
	pages = new Array();
	console.log(films.length)
	var numPages = Math.ceil(films.length/filmsPerPage);
	for(var p = 0; p < numPages; p++){
		pages.push(createFilmsPage(p));			
	}
}

function createFilmsPage(n){
	console.log(films);
	var s="";
	s=" <table class='tabella' id='tab'><th>id</th><th>titolo</th><th>Data</th><th>ora inizio</th><th>ora fine</th><th>sala</th><th>action</th>";
	for(i in films)
		{
			s+="<tr>";
			s+="<td>"+films[i].id+"</td>";
			s+="<td>"+films[i].titolo+"</td>";
			s+="<td>"+films[i].data+"</td>";
			s+="<td>"+films[i].ora_init+"</td>";
			s+="<td>"+films[i].ora_fine+"</td>";
			s+="<td>"+films[i].sala_cinema+"</td>";
			s+="</tr>"
		}
	s+="</table>";
	return s;
}



function loadNewData(){
	$.get("Visualizza_Film",function(data){
		films = data;
	});
}

</script>
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
		<li onclick="mostra()" id="film_mostra"> Film </li>
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
			 
		</div>
	</div>
</div>
</body>
</html>