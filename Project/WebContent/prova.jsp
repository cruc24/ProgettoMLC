<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">"></script>
<script src="jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="./projectStyle.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
$(document).ready(function(){
$.get("Visualizza_Film",function(data){
	var flightsPerPage = 5;
	var secondsPerPage = 3;
	var newDataAvailable = false;
	var currentPage=-1;
	var pages;
	var flights;
	var now= new Date();
	console.log(now);
	console.log(now.getHours());
	console.log(now.getMinutes());
	var s=" <table class='tabella'><th>titolo</th><th>Data</th><th>ora inizio</th><th>ora fine</th><th>sala</th><th></th>";
	for(i in data.films)
		{
			s+="<tr>";
			s+="<td>"+data.films[i].titolo+"</td>";
			s+="<td>"+data.films[i].data+"</td>";
			s+="<td>"+data.films[i].ora_init+"</td>";
			s+="<td>"+data.films[i].ora_fine+"</td>";
			s+="<td>"+data.films[i].sala_cinema+"</td>";
			//controllo live da rifare
			if (now.getHours()>= data.films[i].ora_init.split(':')[0] && now.getMinutes() > data.films[i].ora_init.split(':')[1] || now.getHours()<=data.films[i].ora_fine.split(':')[0] && now.getMinutes() < data.films[i].ora_fine.split(':')[1])
			{
				s+="<td>"+"Live!"+"</td>";
			}
			else
			{
				s+="<td></td>";
			}
			s+="<td>"+"<img src='"+"./Locandine_film/"+data.films[i].filename+"'width='50' heigth='50'>"+ "</td>";
			s+="</tr>"
		}
	s+="</table>";
	document.getElementById("out").innerHTML=s;
	
	});
});
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
		$("#out").html(pages[currentPage]);

		setTimeout("startDisplay()", secondsPerPage*1000);
	}
	
	function createNewPages(){
		pages = new Array();
		var numPages = Math.ceil(flights.length/flightsPerPage);
		for(var p = 0; p < numPages; p++){
			pages.push(createFlightsPage(p));			
		}
	}
	
	function createFlightsPage(n){
		var s = "<table><tr><th>number</th><th>origin</th><th>scheduled</th><th>expected</th><th>note</th></tr>";
		for(var i = n*flightsPerPage; i<(n+1)*flightsPerPage && i<flights.length; i++){
			s = s + "<tr><td>" + flights[i].getElementsByTagName("id")[0].firstChild.nodeValue + "</td>";
			s = s + "<td>" + flights[i].getElementsByTagName("titolo")[0].firstChild.nodeValue + "</td>";
			s = s + "<td>" + flights[i].getElementsByTagName("orainizio")[0].firstChild.nodeValue + "</td>";
			s = s + "<td>" + flights[i].getElementsByTagName("orafine")[0].firstChild.nodeValue + "</td>";
				s = s + "<td>" + flights[i].getElementsByTagName("sala")[0].firstChild.nodeValue + "</td>";
				s = s + "<td>" + flights[i].getElementsByTagName("data")[0].firstChild.nodeValue + "</td>";
			s = s + "</tr>";
		}
		s = s + "</table><hr />Page " + (n+1);
		return s;
	}
	

	//
	function loadNewData(){
		var a= document.getElementById("path").innerHTML;
		flights = loadXMLDoc("./films.xml").getElementsByTagName("film");
	}
	
	function loadXMLDoc(dname) {
		var xhttp = new XMLHttpRequest();
		xhttp.open("GET", dname, false);
		xhttp.send();
		return xhttp.responseXML;
	}

</script>
</head>
<body>
<div class="header">
	<h1 class="title">F I L M S</h1>
	<button class="button_header" onClick="document.location.href='/Project/home.jsp'" >Indietro</button>
</div>
<div class="content">
<div id="out"></div>
</div>
</body>
</html>